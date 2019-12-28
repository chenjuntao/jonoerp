package action.centralfactory.cost;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.reportmanage.CfCostBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import service.reportmanage.ReportManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class CentralfactoryCostAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private ReportManageService reportManageService;

	private CfCostBean rCfCostBean;
	private String branchType;

	private String jsonData;
	private String type;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		startDate = endDate = reportManageService.getBusinessDate(branchType);

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray
				.fromObject(rCfCostBean.query(startDate, endDate, DateTimeUtil.addDays(startDate, -1)));

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String[] perArr = { "actualCostPer", "beforeCostPer", "afterCostPer", "staffCostPer", "presentCostPer", "dishPer",
			"rawPer", "lossGainPer", "totalPer" };

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void export() throws NoPrivilegeException, SQLException, NoConnection {

		List<Map> headerLst = rCfCostBean.query(startDate, endDate, DateTimeUtil.addDays(startDate, -1));

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");
		String sheetName = structure.getString("sheetName");

		JSONArray arr = JSONArray.fromObject(headerLst);

		for (Map header : headerLst) {
			Iterator iter = header.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (header.get(key) == null) {
					header.put(key, "");
				} else if (TextUtil.containStr(perArr, key)) {
					header.put(key, header.get(key).toString() + "%");
				}
			}
		}

		arr = JSONArray.fromObject(headerLst);

		if ("csv".equals(type)) {
			List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < headerLst.size(); i++) {
				Map<String, Object> map = headerLst.get(i);
				maplist.add(map);
			}

			StringBuffer sb = CsvUtil.exportCsv(colsJA, maplist);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setrCfCostBean(CfCostBean rCfCostBean) {
		this.rCfCostBean = rCfCostBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setReportManageService(ReportManageService reportManageService) {
		this.reportManageService = reportManageService;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

}
