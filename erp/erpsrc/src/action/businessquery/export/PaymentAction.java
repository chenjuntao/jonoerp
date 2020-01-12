/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on June 30, 2014 by pw
 * Last edited on Aug 6, 2014 by liyzh
 * 
 * 说明： 付款方式查询导出action
 */
package action.businessquery.export;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import logic.NoConnection;
import logic.businessquery.ShopPayBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.ShopPay;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class PaymentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ShopPayBean shopPayBean;

	private String startDate;
	private String endDate;

	private String shopC;

	private String type;

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void doOneShopQuery() throws NoPrivilegeException, SQLException, NoConnection, UnsupportedEncodingException {
		verifyDate();

		List<ShopPay> shops = shopPayBean.listOneShopPay(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> payLst = new ArrayList<Map<String, Object>>();
		Map<String, Double> paySumMaps = new HashMap<String, Double>();
		for (ShopPay pay : shops) {
			Map<String, Object> payMap = new HashMap<String, Object>();
			payMap.put("shopC", pay.getShopC());
			payMap.put("shopName", pay.getShopName());

			Map<String, Double> map = pay.getPay();

			for (Entry<String, Double> entry : map.entrySet()) {
				String key = entry.getKey();
				Double value = entry.getValue();
				payMap.put(key, value);

				if (paySumMaps.containsKey(key)) {
					Double sumValue = paySumMaps.get(key) + value;
					paySumMaps.put(key, sumValue);
				} else {
					paySumMaps.put(key, value);
					JSONObject col = new JSONObject();
					col.put("display", key.substring(key.indexOf("seperator") + 12));
					// col.put("display", key);
					col.put("name", key);
					col.put("width", 80);
					col.put("align", "right");
					colsJA.add(col);
				}
			}
			payLst.add(payMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, payLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(payLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void byDay() throws NoPrivilegeException, SQLException, NoConnection, UnsupportedEncodingException {
		verifyDate();

		List<ShopPay> shops = shopPayBean.byDay(startDate, endDate);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shopLst = new ArrayList<Map<String, Object>>();

		for (ShopPay bean : shops) {
			Map<String, Object> shopPayMap = new HashMap<String, Object>();
			shopPayMap.put("businessDate", bean.getDbusinessDate());
			shopPayMap.put("shopC", bean.getShopC());
			shopPayMap.put("shopName", bean.getShopName());

			shopPayMap.put("payName", bean.getPayName());
			shopPayMap.put("payCode", bean.getPayCode());
			shopPayMap.put("payAmt", bean.getPayAmt());

			shopLst.add(shopPayMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, shopLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(shopLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection, UnsupportedEncodingException {
		verifyDate();

		List<ShopPay> shops = shopPayBean.listShopPay(startDate, endDate);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> payLst = new ArrayList<Map<String, Object>>();
		Map<String, Double> paySumMaps = new HashMap<String, Double>();
		for (ShopPay pay : shops) {
			Map<String, Object> payMap = new HashMap<String, Object>();
			payMap.put("shopC", pay.getShopC());
			payMap.put("shopName", pay.getShopName());

			Map<String, Double> map = pay.getPay();

			JSONObject sumJO = new JSONObject();
			sumJO.put("count", true);
			for (Entry<String, Double> entry : map.entrySet()) {
				String key = entry.getKey();
				Double value = entry.getValue();
				payMap.put(key, value);

				if (paySumMaps.containsKey(key)) {
					Double sumValue = paySumMaps.get(key) + value;
					paySumMaps.put(key, sumValue);
				} else {
					paySumMaps.put(key, value);
					JSONObject col = new JSONObject();
					col.put("display", key.substring(key.indexOf("seperator") + 12));
					// col.put("display", key);
					col.put("name", key);
					col.put("width", 80);
					col.put("align", "right");
					col.put("sum", sumJO);
					colsJA.add(col);
				}
			}
			payLst.add(payMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, payLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(payLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setShopPayBean(ShopPayBean shopPayBean) {
		this.shopPayBean = shopPayBean;
	}

	public String getStartDate() {
		if (!TextUtil.isEmpty(startDate)) {
			return startDate;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1);
		return DateTimeUtil.getDateTime(cal.getTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

	public String getEndDate() {
		if (!TextUtil.isEmpty(endDate)) {
			return endDate;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 8000);
		return DateTimeUtil.getDateTime(cal.getTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

	private void verifyDate() {
		if (TextUtil.isEmpty(startDate)) {
			startDate = getStartDate();
		}

		if (TextUtil.isEmpty(endDate)) {
			endDate = getEndDate();
		}
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

}
