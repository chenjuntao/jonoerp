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

import org.apache.poi.ss.usermodel.Workbook;

import service.reportmanage.SpecialReportManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class SpecialReportManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String append;

	private Date startDate;
	private Date endDate;

	private String flag;
	private String jsonData;
	private String type;

	private SpecialReportManageService specialReportManageService;
	private String branchId;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		branchId = getLoginBranchId();
		endDate = startDate = specialReportManageService.getBusinessByBranchId(branchId);

		if (isMobile()) {
			return "mobile";
		}

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		StringBuilder querySb = new StringBuilder();

		if (!TextUtil.isEmpty(append)) {
			querySb.append(append);
		}

		JSONArray arr = new JSONArray();
		Integer total = specialReportManageService.count(flag, querySb.toString());

		setTotal(total);

		if (total > 0) {
			List<Map> headerLst = specialReportManageService.query(flag, querySb.toString(), getStart(), getEnd());
			int rownumber = getStart();
			for (Map header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doQueryBranch() throws NoPrivilegeException, SQLException, NoConnection {
		StringBuilder querySb = new StringBuilder();

		if (!TextUtil.isEmpty(append)) {
			querySb.append(append);
		}

		JSONArray arr = new JSONArray();
		List<Map> headerLst = specialReportManageService.queryBranch(querySb.toString());

		try {
			this.outJS(JSONArray.fromObject(headerLst).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void export() throws NoPrivilegeException, SQLException, NoConnection {
		StringBuilder querySb = new StringBuilder();

		if (!TextUtil.isEmpty(append)) {
			querySb.append(append);
		}

		JSONArray arr = new JSONArray();
		List<Map> headerLst = specialReportManageService.query(flag, querySb.toString(), 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		String sheetName = structure.getString("sheetName");

		int rownumber = 1;
		for (Map header : headerLst) {
			Iterator iter = header.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (header.get(key) == null) {
					header.put(key, "");
				}

			}
			JSONObject json = JSONObject.fromObject(header);

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

	public void setAppend(String append) {
		this.append = append;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setSpecialReportManageService(SpecialReportManageService specialReportManageService) {
		this.specialReportManageService = specialReportManageService;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBranchId() {
		return branchId;
	}

}
