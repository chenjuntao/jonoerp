package action.restaurant.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckHeaderBean;
import logic.form.FormStatusBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.CheckHeader;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class CheckstorageExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String type;

	private CheckHeaderBean checkHeaderBean;
	private FormStatusBean formStatusBean;

	private String queryType;

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	private String nullToStr(String str) {
		if (TextUtil.isEmpty(str)) {
			return "";
		}

		return str;
	}

	public void export() throws NoPrivilegeException, SQLException, NoConnection {

		List<CheckHeader> headerLst = checkHeaderBean.query(startDate, endDate, branchId, FormConstant.CHECK_FORM,
				queryType, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> checkHeaderLst = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (CheckHeader obj : headerLst) {
			CheckHeader header = (CheckHeader) obj;
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("rownumber", rownumber++);

			// 清单编号
			itemMap.put("formId", header.getFormId());

			// 盘点部门
			itemMap.put("checkBranch", header.getCheckBranch());

			// 盘点批次
			itemMap.put("checkBatchId", header.getCheckBatchId());

			// 制单人员
			itemMap.put("formMaker", header.getFormMaker());

			// 制单日期
			String formTime = getDateStr(header.getFormTime());
			itemMap.put("formTime", formTime);

			// 备注
			itemMap.put("formNote", nullToStr(header.getFormNote()));

			// 主要盘点物
			itemMap.put("maxPayItem", nullToStr(header.getMaxPayItem()));

			// 盘点总额
			itemMap.put("allPayAmt", header.getAllPayAmt());

			// 单据状态
			String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
			itemMap.put("formStatus", formStatus);

			checkHeaderLst.add(itemMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, checkHeaderLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(checkHeaderLst);
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
