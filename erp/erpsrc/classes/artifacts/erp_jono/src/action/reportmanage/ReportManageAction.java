package action.reportmanage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.reportmanage.QueryGenUtil;
import service.reportmanage.ReportManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class ReportManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ReportManageService reportManageService;
	private String inputJsonString;
	private String reportFlag;
	private String collectFlag;
	private String append;
	private String jsonData;
	private String type;

	private Date startDate;
	private Date endDate;

	private String branchId;
	private String withOutSum;
	private String dateFormat;
	private String branchFlag;
	private String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		branchId = getLoginBranchId();

		endDate = startDate = reportManageService.getBusinessByBranchId(branchId);

		if (isMobile()) {
			return "mobile";
		}

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		Map prefixMap = reportManageService.getColumnPrefix(reportFlag);

		StringBuilder querySb = new StringBuilder();
		String sortStr = "";

		// filter append condition
		if (!TextUtil.isEmpty(append)) {
			querySb.append(append);
		}

		// filter condition
		if (!TextUtil.isEmpty(inputJsonString) && !inputJsonString.equals("null")
				&& !inputJsonString.equals("undefined")) {
			querySb.append(QueryGenUtil.getQueryStr(inputJsonString, prefixMap));
		}

		// sort
		if (!TextUtil.isEmpty(getSort())) {
			sortStr = QueryGenUtil.getSortStr(getSort(), prefixMap, reportManageService.getColumnType(reportFlag),
					collectFlag);
		}

		JSONArray arr = new JSONArray();
		int total = reportManageService.count(reportFlag, querySb.toString(), sortStr);

		// if return counts is greater than 0, then the total should add the sum
		// object
		if (total > 0 && TextUtil.isEmpty(withOutSum))
			total++;
		setTotal(total);

		if (total > 0) {
			List<Map> headerLst = reportManageService.query(reportFlag, querySb.toString(), sortStr, getStart(),
					getEnd());
			int rownumber = getStart();
			for (Map header : headerLst) {
				JsonConfig config = new JsonConfig();
				if (!TextUtil.isEmpty(dateFormat)) {
					config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor(dateFormat));
				} else {
					config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
				}
				JSONObject json = JSONObject.fromObject(header, config);

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// total
			if (total < getEnd()) {
				if (TextUtil.isEmpty(withOutSum)) {
					Map sumMap = reportManageService.sum(reportFlag, querySb.toString(), sortStr);

					if (sumMap != null) {
						JSONObject sumJObject = JSONObject.fromObject(sumMap);
						sumJObject.put("rownumber", "total");
						arr.add(sumJObject);
					}
				}

			}
		}

		if (isMobile()) {
			JSONObject result = new JSONObject();
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("iTotalDisplayRecords", total);
			result.put("data", arr);// for DataTables
			try {
				this.outJS(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void export() throws NoPrivilegeException, SQLException, NoConnection {

		Map prefixMap = reportManageService.getColumnPrefix(reportFlag);

		StringBuilder querySb = new StringBuilder();
		String sortStr = "";

		// filter append condition
		if (!TextUtil.isEmpty(append)) {
			querySb.append(append);
		}

		// filter condition
		if (!TextUtil.isEmpty(inputJsonString) && !inputJsonString.equals("null")
				&& !inputJsonString.equals("undefined")) {
			querySb.append(QueryGenUtil.getQueryStr(inputJsonString, prefixMap));
		}

		// sort
		if (!TextUtil.isEmpty(getSort())) {
			sortStr = QueryGenUtil.getSortStr(getSort(), prefixMap, reportManageService.getColumnType(reportFlag),
					collectFlag);
		}

		JSONArray arr = new JSONArray();
		List<Map> headerLst = reportManageService.query(reportFlag, querySb.toString(), sortStr, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		String sheetName = structure.getString("sheetName");

		JsonConfig config = new JsonConfig();
		if (!TextUtil.isEmpty(dateFormat)) {
			config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor(dateFormat));
		} else {
			config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
		}

		int rownumber = 1;
		for (Map header : headerLst) {
			Iterator iter = header.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (header.get(key) == null) {
					header.put(key, "");
				}

			}
			JSONObject json = JSONObject.fromObject(header, config);

			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		if ("csv".equals(type)) {
			rownumber = 1;

			List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < headerLst.size(); i++) {
				Map<String, Object> map = headerLst.get(i);
				map.put("rownumber", rownumber++);
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

	public void print() throws NoPrivilegeException, SQLException, NoConnection {

		Map prefixMap = reportManageService.getColumnPrefix(reportFlag);

		StringBuilder querySb = new StringBuilder();
		String sortStr = "";

		// filter condition
		if (!TextUtil.isEmpty(inputJsonString) && !inputJsonString.equals("null")
				&& !inputJsonString.equals("undefined")) {
			querySb.append(QueryGenUtil.getQueryStr(inputJsonString, prefixMap));
		}

		// filter append condition
		if (!TextUtil.isEmpty(append)) {
			querySb.append(append);
		}

		// sort
		if (!TextUtil.isEmpty(getSort())) {
			sortStr = QueryGenUtil.getSortStr(getSort(), prefixMap, reportManageService.getColumnType(reportFlag),
					collectFlag);
		}

		JSONArray arr = new JSONArray();
		int total = reportManageService.count(reportFlag, querySb.toString(), sortStr);

		// if return counts is greater than 0, then the total should add the sum
		// object
		if (total > 0 && TextUtil.isEmpty(withOutSum))
			total++;
		setTotal(total);

		if (total > 0) {
			List<Map> headerLst = reportManageService.query(reportFlag, querySb.toString(), sortStr, 0,
					Integer.MAX_VALUE);
			int rownumber = getStart();
			for (Map header : headerLst) {
				JsonConfig config = new JsonConfig();
				config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
				JSONObject json = JSONObject.fromObject(header, config);

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// total
			if (total < getEnd()) {
				if (TextUtil.isEmpty(withOutSum)) {
					Map sumMap = reportManageService.sum(reportFlag, querySb.toString(), sortStr);

					JSONObject sumJObject = JSONObject.fromObject(sumMap);
					sumJObject.put("rownumber", "total");
					arr.add(sumJObject);
				}
			}
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCollectFlag(String collectFlag) {
		this.collectFlag = collectFlag;
	}

	public void setReportManageService(ReportManageService reportManageService) {
		this.reportManageService = reportManageService;
	}

	public void setInputJsonString(String inputJsonString) {
		this.inputJsonString = inputJsonString;
	}

	public void setReportFlag(String reportFlag) {
		this.reportFlag = reportFlag;
	}

	public void setAppend(String append) {
		this.append = append;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public String getBranchId() {
		return branchId;
	}

	public void setWithOutSum(String withOutSum) {
		this.withOutSum = withOutSum;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}

}
