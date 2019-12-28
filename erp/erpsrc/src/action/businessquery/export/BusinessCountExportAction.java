package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.BusinessCountBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.BusinessCount;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class BusinessCountExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BusinessCountBean businessCountBean;

	private String startDate;
	private String endDate;
	private String shopC;

	private String type;

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void setType(String type) {
		this.type = type;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBusinessCountBean(BusinessCountBean businessCountBean) {
		this.businessCountBean = businessCountBean;
	}

	public String getStartDate() {
		if (!TextUtil.isEmpty(startDate)) {
			return startDate;
		}
		return DateTimeUtil.getDateTime(DateTimeUtil.getMonthFristDay(), DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		if (!TextUtil.isEmpty(endDate)) {
			return endDate;
		}
		return DateTimeUtil.getDate();
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getShopC() {
		return shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public void floorExport() throws NoPrivilegeException, SQLException, NoConnection {

		setConditions();

		List<BusinessCount> businessCounts = businessCountBean.floor(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> businessCountLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (BusinessCount obj : businessCounts) {
			BusinessCount item = (BusinessCount) obj;
			Map<String, Object> businessCountMap = new HashMap<String, Object>();

			businessCountMap.put("rowNumber", rowNumber++);
			businessCountMap.put("billNum", item.getBillNum());
			businessCountMap.put("guestNum", item.getGuestNum());
			businessCountMap.put("foodAmt", item.getFoodAmt());
			businessCountMap.put("disAmt", item.getDisAmt());
			businessCountMap.put("roundAmt", item.getRoundAmt());
			businessCountMap.put("oughtAmt", item.getOughtAmt());
			businessCountMap.put("payAmt", item.getPayAmt());
			businessCountMap.put("presentAmt", item.getPresentAmt());
			businessCountMap.put("shopC", item.getShopC());
			businessCountMap.put("shopN", item.getShopN());

			businessCountLst.add(businessCountMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, businessCountLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(businessCountLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void periodExport() throws NoPrivilegeException, SQLException, NoConnection {

		setConditions();

		List<BusinessCount> businessCounts = businessCountBean.period(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> businessCountLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (BusinessCount obj : businessCounts) {
			BusinessCount item = (BusinessCount) obj;
			Map<String, Object> businessCountMap = new HashMap<String, Object>();

			businessCountMap.put("rowNumber", rowNumber++);
			businessCountMap.put("period", item.getPeriod());
			businessCountMap.put("billNum", item.getBillNum());
			businessCountMap.put("guestNum", item.getGuestNum());
			businessCountMap.put("foodAmt", item.getFoodAmt());
			businessCountMap.put("disAmt", item.getDisAmt());
			businessCountMap.put("roundAmt", item.getRoundAmt());
			businessCountMap.put("oughtAmt", item.getOughtAmt());
			businessCountMap.put("payAmt", item.getPayAmt());
			businessCountMap.put("presentAmt", item.getPresentAmt());
			businessCountMap.put("shopC", item.getShopC());
			businessCountMap.put("shopN", item.getShopN());

			businessCountLst.add(businessCountMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, businessCountLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(businessCountLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void shiftExport() throws NoPrivilegeException, SQLException, NoConnection {

		setConditions();

		List<BusinessCount> businessCounts = businessCountBean.shift(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> businessCountLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (BusinessCount obj : businessCounts) {
			BusinessCount item = (BusinessCount) obj;
			Map<String, Object> businessCountMap = new HashMap<String, Object>();
			businessCountMap.put("rowNumber", rowNumber++);
			businessCountMap.put("shift", item.getShift());
			businessCountMap.put("billNum", item.getBillNum());
			businessCountMap.put("guestNum", item.getGuestNum());
			businessCountMap.put("foodAmt", item.getFoodAmt());
			businessCountMap.put("disAmt", item.getDisAmt());
			businessCountMap.put("roundAmt", item.getRoundAmt());
			businessCountMap.put("oughtAmt", item.getOughtAmt());
			businessCountMap.put("payAmt", item.getPayAmt());
			businessCountMap.put("presentAmt", item.getPresentAmt());
			businessCountMap.put("shopC", item.getShopC());
			businessCountMap.put("shopN", item.getShopN());

			businessCountLst.add(businessCountMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, businessCountLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(businessCountLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setConditions() {
		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}
	}
}
