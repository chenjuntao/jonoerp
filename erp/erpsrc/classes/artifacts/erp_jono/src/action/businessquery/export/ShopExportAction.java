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

import logic.NoConnection;
import logic.businessquery.ShopBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.ShopBill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class ShopExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ShopBean shopBean;
	private String startDate;
	private String endDate;

	private String type;

	public void setShopBean(ShopBean shopBean) {
		this.shopBean = shopBean;
	}

	public ShopBean getShopBean() {
		return shopBean;
	}

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection, UnsupportedEncodingException {
		verifyDate();

		List<ShopBill> shops = shopBean.listShop(startDate, endDate);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shopLst = new ArrayList<Map<String, Object>>();

		for (ShopBill item : shops) {
			Map<String, Object> shopMap = new HashMap<String, Object>();
			shopMap.put("shopC", item.getShopC());
			shopMap.put("shopName", item.getShopName());
			shopMap.put("foodAmt", item.getFoodAmt());
			shopMap.put("billNum", item.getBillNum());
			shopMap.put("amtPerBill", item.getAmtPerBill());
			shopMap.put("guestNum", item.getGuestNum());
			shopMap.put("amtPerGuest", item.getAmtPerGuest());
			shopMap.put("tableNum", item.getTableNum());
			shopMap.put("amtPerTable", item.getAmtPerTable());
			shopMap.put("guestPerTable", item.getGuestPerTable());
			shopMap.put("disAmt", item.getDisAmt());
			shopMap.put("roundAmt", item.getRoundAmt());
			shopMap.put("presentAmt", item.getPresentAmt());
			shopMap.put("oughtAmt", item.getOughtAmt());
			shopMap.put("payAmt", item.getPayAmt());

			shopLst.add(shopMap);
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

	public void byDay() throws NoPrivilegeException, SQLException, NoConnection, UnsupportedEncodingException {
		verifyDate();

		List<ShopBill> shops = shopBean.byDay(startDate, endDate);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shopLst = new ArrayList<Map<String, Object>>();

		for (ShopBill item : shops) {
			Map<String, Object> shopMap = new HashMap<String, Object>();
			shopMap.put("businessDate", item.getBusinessDate());
			shopMap.put("shopC", item.getShopC());
			shopMap.put("shopName", item.getShopName());
			shopMap.put("foodAmt", item.getFoodAmt());
			shopMap.put("billNum", item.getBillNum());
			shopMap.put("amtPerBill", item.getAmtPerBill());
			shopMap.put("guestNum", item.getGuestNum());
			shopMap.put("amtPerGuest", item.getAmtPerGuest());
			shopMap.put("tableNum", item.getTableNum());
			shopMap.put("amtPerTable", item.getAmtPerTable());
			shopMap.put("guestPerTable", item.getGuestPerTable());
			shopMap.put("disAmt", item.getDisAmt());
			shopMap.put("roundAmt", item.getRoundAmt());
			shopMap.put("presentAmt", item.getPresentAmt());
			shopMap.put("oughtAmt", item.getOughtAmt());
			shopMap.put("payAmt", item.getPayAmt());

			shopLst.add(shopMap);
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

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

}
