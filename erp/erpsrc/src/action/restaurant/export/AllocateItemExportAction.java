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
import logic.form.TransferHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.TransferHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class AllocateItemExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private TransferHeaderBean transferHeaderBean;

	private Date startDate;
	private Date endDate;
	private String inBranchId;
	private String outBranchId;
	private String inStorageId;
	private String outStorageId;

	private String branchType;

	private String type;

	private FormStatusBean formStatusBean;

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setInStorageId(String inStorageId) {
		this.inStorageId = inStorageId;
	}

	public void setOutStorageId(String outStorageId) {
		this.outStorageId = outStorageId;
	}

	public void setInBranchId(String inBranchId) {
		this.inBranchId = inBranchId;
	}

	public void setOutBranchId(String outBranchId) {
		this.outBranchId = outBranchId;
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

	public void setType(String type) {
		this.type = type;
	}

	public void setTransferHeaderBean(TransferHeaderBean transferHeaderBean) {
		this.transferHeaderBean = transferHeaderBean;
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
		List<TransferHeader> headerLst = transferHeaderBean.query(startDate,
				endDate, inBranchId, inStorageId, outBranchId, outStorageId,
				"", branchType, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> transferHeaderLst = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (TransferHeader obj : headerLst) {
			TransferHeader transferHeader = (TransferHeader) obj;
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("rownumber", rownumber++);
			itemMap.put("formId", transferHeader.getFormId());

			String formStatus = formStatusBean.getCurrentStatus(transferHeader
					.getFormId());
			itemMap.put("formStatus", formStatus);

			itemMap.put("inBranch", transferHeader.getInBranch());
			itemMap.put("inStorage", transferHeader.getInStorage());

			itemMap.put("outBranch", transferHeader.getOutBranch());
			itemMap.put("outStorage", transferHeader.getOutStorage());

			itemMap.put("fromMaker", transferHeader.getFromMaker());
			// 制单日期
			String formTime = getDateStr(transferHeader.getFormTime());
			itemMap.put("formTime", formTime);

			itemMap.put("auditor", nullToStr(transferHeader.getAuditor()));
			// 审核日期
			String auditTime = getDateStr(transferHeader.getAuditTime());
			itemMap.put("auditTime", auditTime);

			itemMap.put("confirmer", nullToStr(transferHeader.getConfirmer()));
			// 确认日期
			String confirmTime = getDateStr(transferHeader.getConfirmTime());
			itemMap.put("confirmTime", confirmTime);

			itemMap.put("outMan", nullToStr(transferHeader.getOutMan()));

			// 调出日期
			String outTime = getDateStr(transferHeader.getOutTime());
			itemMap.put("outTime", outTime);

			itemMap.put("formNote", nullToStr(transferHeader.getFormNote()));
			itemMap.put("maxPayItem", nullToStr(transferHeader.getMaxPayItem()));
			itemMap.put("allPayAmt", transferHeader.getAllPayAmt());

			transferHeaderLst.add(itemMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, transferHeaderLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(transferHeaderLst);
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
