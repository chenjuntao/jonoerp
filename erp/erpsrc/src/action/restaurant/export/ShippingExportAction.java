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
import logic.form.ShippingHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.ShippingHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class ShippingExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ShippingHeaderBean shippingHeaderBean;
	private Date startDate;
	private Date endDate;
	private String branchId;
	private String formType;

	private String type;

	private FormStatusBean formStatusBean;

	private String branchType;

	private String storageId;

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void export() throws NoPrivilegeException, SQLException, NoConnection {
		String queryType = null;// 复用sql
		List<ShippingHeader> headerLst = shippingHeaderBean.queryAll(startDate, endDate, branchId, queryType, formType,
				branchType, storageId, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shippingHeaderLst = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (ShippingHeader obj : headerLst) {
			ShippingHeader header = (ShippingHeader) obj;
			Map<String, Object> itemMap = new HashMap<String, Object>();

			itemMap.put("rownumber", rownumber++);

			// 报损单编号
			itemMap.put("formId", header.getFormId());

			// 配送部门
			itemMap.put("provider", header.getProvider());

			// 配送日期
			String receiveTime = getDateStr(header.getReceiveTime());
			itemMap.put("receiveTime", receiveTime);

			// 订货部门
			itemMap.put("requester", header.getRequester());

			// 订货地址
			itemMap.put("requestAddress", nullToStr(header.getRequestAddress()));

			// 制单人员
			itemMap.put("formMaker", nullToStr(header.getFormMaker()));

			// 制单日期
			String formTime = getDateStr(header.getFormTime());
			itemMap.put("formTime", formTime);

			// 审核人员
			itemMap.put("auditor", nullToStr(header.getAuditor()));

			// 审核日期
			String auditTime = getDateStr(header.getAuditTime());
			itemMap.put("auditTime", auditTime);

			// 入库人员
			itemMap.put("inputer", nullToStr(header.getInputer()));

			// 入库时间
			String inputTime = DateTimeUtil.getDateTime(header.getInputTime(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
			itemMap.put("inputTime", inputTime);

			// 备注
			itemMap.put("formNote", nullToStr(header.getFormNote()));

			// 主要配送品
			itemMap.put("maxPayItem", nullToStr(header.getMaxPayItem()));

			// 配送总额
			itemMap.put("allPayAmt", header.getAllPayAmt());

			// 单据状态
			String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
			itemMap.put("formStatus", formStatus);

			String pickStatus = header.getPickStatus();
			String onStatus = header.getOnStatus();
			itemMap.put("pickStatus", pickStatus != null ? pickStatus : "");
			itemMap.put("onStatus", onStatus != null ? onStatus : "");
			itemMap.put("formStatus", formStatus);

			shippingHeaderLst.add(itemMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, shippingHeaderLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(shippingHeaderLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
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

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	private String nullToStr(String str) {
		if (TextUtil.isEmpty(str)) {
			return "";
		}

		return str;
	}
}
