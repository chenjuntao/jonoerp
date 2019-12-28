package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.TableBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.TableBill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class TableExportAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private TableBean tableBean;

	private String startDate;
	private String endDate;
	private String shopC;

	private String type;

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	private List<TableBill> tables;

	public void setType(String type) {
		this.type = type;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
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

	public List<TableBill> getTables() {
		return tables;
	}

	public TableBean getTableBean() {
		return tableBean;
	}

	public void setTableBean(TableBean tableBean) {
		this.tableBean = tableBean;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {

		tables = tableBean.listTable(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shopLst = new ArrayList<Map<String, Object>>();

		for (TableBill table : tables) {
			Map<String, Object> shopMap = new HashMap<String, Object>();
			shopMap.put("table", table.getTable());
			shopMap.put("foodAmt", table.getFoodAmt());
			shopMap.put("billNum", table.getBillNum());
			shopMap.put("guestNum", table.getGuestNum());

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
}
