package action.restaurant.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.LossHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.LossHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class ReportdamageExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private LossHeaderBean lossHeaderBean;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String type;
	private String lossType;
	private String branchType;

	private String storageId;

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setLossType(String lossType) {
		this.lossType = lossType;
	}

	private FormStatusBean formStatusBean;

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date,
					DateTimeUtil.DEFAULT_DATE_FORMAT);
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

	public void export() throws NoPrivilegeException, SQLException,
			NoConnection {

		List<LossHeader> headerLst = lossHeaderBean.queryAll(startDate,
				endDate, branchId, storageId, lossType, branchType, 0,
				Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> lossHeaderLst = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (LossHeader obj : headerLst) {
			LossHeader header = (LossHeader) obj;
			Map<String, Object> itemMap = new HashMap<String, Object>();

			itemMap.put("rownumber", rownumber++);

			// 报损单编号
			itemMap.put("formId", header.getFormId());

			// 报损部门
			itemMap.put("lossBranch", header.getLossBranch());

			// 报损仓库
			itemMap.put("storage", header.getStorage());

			// 报损人员
			itemMap.put("lossMan", nullToStr(header.getLossMan()));

			// 报损日期
			String loss_time = getDateStr(header.getLossTime());
			itemMap.put("lossTime", loss_time);

			// 审核人员
			itemMap.put("auditor", nullToStr(header.getAuditor()));

			// 审核日期
			String audit_time = getDateStr(header.getAuditTime());
			itemMap.put("auditTime", audit_time);

			// 备注
			itemMap.put("formNote", nullToStr(header.getFormNote()));

			// 主要报损品
			itemMap.put("maxPayItem", nullToStr(header.getMaxPayItem()));

			// 报损总额
			itemMap.put("allPayAmt", header.getAllPayAmt());

			// 单据状态
			String formStatus = formStatusBean.getCurrentStatus(header
					.getFormId());
			itemMap.put("formStatus", formStatus);

			lossHeaderLst.add(itemMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, lossHeaderLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(lossHeaderLst);
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
