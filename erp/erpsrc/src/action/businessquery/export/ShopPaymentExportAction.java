package action.businessquery.export;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.ShopBean;
import logic.businessquery.ShopPaymentBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.ShopPayment;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class ShopPaymentExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ShopPaymentBean shopPaymentBean;
	private String startDate;
	private String endDate;

	private String type;

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void setShopBean(ShopBean shopBean) {
	}

	public void setShopPaymentBean(ShopPaymentBean shopPaymentBean) {
		this.shopPaymentBean = shopPaymentBean;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection, UnsupportedEncodingException {

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shopPaymentLst = new ArrayList<Map<String, Object>>();
		Map<String, Object> shopPaymentMap = null;

		Map<String, String> tagMap = new HashMap<String, String>();
		List<ShopPayment> shopPayInfos = shopPaymentBean.getShopPayInfo(startDate, endDate);
		shopPayInfos.add(new ShopPayment());

		for (int i = 0; i < shopPayInfos.size(); i++) {
			ShopPayment item = shopPayInfos.get(i);

			if (!tagMap.containsKey(item.getShopC())) {
				if (i != 0) {
					shopPaymentLst.add(shopPaymentMap);
				}

				shopPaymentMap = new HashMap<String, Object>();

				shopPaymentMap.put("shopC", item.getShopC());
				shopPaymentMap.put("shopN", item.getShopN());
				shopPaymentMap.put("foodAmt", item.getFoodAmt());
				shopPaymentMap.put("billNum", item.getBillNum());
				shopPaymentMap.put("amtPerBill", item.getAmtPerBill());
				shopPaymentMap.put("guestNum", item.getGuestNum());
				shopPaymentMap.put("amtPerGuest", item.getAmtPerGuest());
				shopPaymentMap.put("tableNum", item.getTableNum());
				shopPaymentMap.put("amtPerTable", item.getAmtPerTable());
				shopPaymentMap.put("guestPerTable", item.getGuestPerTable());
				shopPaymentMap.put("disAmt", item.getDisAmt());
				shopPaymentMap.put("roundAmt", item.getRoundAmt());
				shopPaymentMap.put("presentAmt", item.getPresentAmt());
				shopPaymentMap.put("oughtAmt", item.getOughtAmt());
				shopPaymentMap.put("payAmt", item.getPayAmt());

				tagMap.put(item.getShopC(), item.getShopC());
			}

			shopPaymentMap.put("pay_" + item.getPayC(), item.getPayTypeAmt());

		}

		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, shopPaymentLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(shopPaymentLst);
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

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shopPaymentLst = new ArrayList<Map<String, Object>>();
		Map<String, Object> shopPaymentMap = null;

		Map<String, String> dateTagMap = new HashMap<String, String>();
		Map<String, String> shopTagMap = new HashMap<String, String>();

		List<ShopPayment> shopPayInfos = shopPaymentBean.getShopPayInfoByDay(startDate, endDate);

		shopPayInfos.add(new ShopPayment());

		for (int i = 0; i < shopPayInfos.size(); i++) {
			ShopPayment item = shopPayInfos.get(i);

			if (!dateTagMap.containsKey(item.getBusinessDate())) {
				shopTagMap.clear();
			}

			if (!shopTagMap.containsKey(item.getShopC())) {

				if (i != 0) {
					shopPaymentLst.add(shopPaymentMap);
				}
				shopPaymentMap = new HashMap<String, Object>();

				shopPaymentMap.put("businessDate", item.getBusinessDate());
				shopPaymentMap.put("shopC", item.getShopC());
				shopPaymentMap.put("shopN", item.getShopN());
				shopPaymentMap.put("foodAmt", item.getFoodAmt());
				shopPaymentMap.put("billNum", item.getBillNum());
				shopPaymentMap.put("amtPerBill", item.getAmtPerBill());
				shopPaymentMap.put("guestNum", item.getGuestNum());
				shopPaymentMap.put("amtPerGuest", item.getAmtPerGuest());
				shopPaymentMap.put("tableNum", item.getTableNum());
				shopPaymentMap.put("amtPerTable", item.getAmtPerTable());
				shopPaymentMap.put("guestPerTable", item.getGuestPerTable());
				shopPaymentMap.put("disAmt", item.getDisAmt());
				shopPaymentMap.put("roundAmt", item.getRoundAmt());
				shopPaymentMap.put("presentAmt", item.getPresentAmt());
				shopPaymentMap.put("oughtAmt", item.getOughtAmt());
				shopPaymentMap.put("payAmt", item.getPayAmt());

				dateTagMap.put(item.getBusinessDate(), item.getBusinessDate());
				shopTagMap.put(item.getShopC(), item.getShopC());
			}

			shopPaymentMap.put("pay_" + item.getPayC(), item.getPayTypeAmt());

		}

		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, shopPaymentLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(shopPaymentLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
