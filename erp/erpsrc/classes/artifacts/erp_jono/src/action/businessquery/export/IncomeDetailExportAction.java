package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.IncomeDetailBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.IncomeDetail;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class IncomeDetailExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String shopC;

	private String type;

	private IncomeDetailBean incomeDetailBean;

	public void setType(String type) {
		this.type = type;
	}

	public void setIncomeDetailBean(IncomeDetailBean incomeDetailBean) {
		this.incomeDetailBean = incomeDetailBean;
	}

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
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

	public String getStartDate() {
		if (TextUtil.isEmpty(startDate)) {
			startDate = DateTimeUtil.getDateOff(DateTimeUtil.getDate(), -1);
		}
		return startDate;
	}

	public String getEndDate() {
		if (TextUtil.isEmpty(endDate)) {
			endDate = DateTimeUtil.getDateOff(DateTimeUtil.getDate(), -1);
		}
		return endDate;
	}

	public void dayExport() throws NoPrivilegeException, SQLException, NoConnection {

		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}

		List<IncomeDetail> incomeDetails = incomeDetailBean.dayIncomeDetail(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> incomeDetailLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (IncomeDetail item : incomeDetails) {
			Map<String, Object> incomeDetailMap = new HashMap<String, Object>();
			incomeDetailMap.put("rowNumber", rowNumber);
			incomeDetailMap.put("businessDate", item.getBusinessDate());
			incomeDetailMap.put("categoryN", item.getCategoryN());
			incomeDetailMap.put("period1", item.getPeriod1());
			incomeDetailMap.put("period2", item.getPeriod2());
			incomeDetailMap.put("period3", item.getPeriod3());
			incomeDetailMap.put("period4", item.getPeriod4());
			incomeDetailMap.put("counts", item.getCounts());

			incomeDetailLst.add(incomeDetailMap);

			rowNumber++;
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, incomeDetailLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(incomeDetailLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void monthExport() throws NoPrivilegeException, SQLException, NoConnection {

		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}

		startDate = DateTimeUtil.getDateTime(DateTimeUtil.parse(startDate, "yyyy-MM"), "yyyy-MM");
		endDate = DateTimeUtil.getDateTime(DateTimeUtil.parse(endDate, "yyyy-MM"), "yyyy-MM");

		List<IncomeDetail> incomeDetails = incomeDetailBean.monthIncomeDetail(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> incomeDetailLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (IncomeDetail item : incomeDetails) {
			Map<String, Object> incomeDetailMap = new HashMap<String, Object>();
			incomeDetailMap.put("rowNumber", rowNumber);
			incomeDetailMap.put("businessMonth", item.getBusinessMonth());
			incomeDetailMap.put("categoryN", item.getCategoryN());
			incomeDetailMap.put("period1", item.getPeriod1());
			incomeDetailMap.put("period2", item.getPeriod2());
			incomeDetailMap.put("period3", item.getPeriod3());
			incomeDetailMap.put("period4", item.getPeriod4());
			incomeDetailMap.put("counts", item.getCounts());

			incomeDetailLst.add(incomeDetailMap);

			rowNumber++;
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, incomeDetailLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(incomeDetailLst);
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
