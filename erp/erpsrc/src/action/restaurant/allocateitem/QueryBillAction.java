//===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014年9月18日 by pw
// Last edited on 2014年9月18日 by pw
//
// Comments:调拨单查询
//
//===============================================
package action.restaurant.allocateitem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.TransferHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.TransferHeader;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class QueryBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private TransferHeaderBean transferHeaderBean;

	private FormStatusBean formStatusBean;

	private Date startDate;
	private Date endDate;

	private String formId;

	private TransferHeader transferHeader;

	private Date formTime;
	private Date confirmTime;

	private String jsonData;
	private List<Map> shopLst;

	private List<BranchStorage> inStorageList;
	private List<BranchStorage> outStorageList;
	private String branchType;

	private BranchStorageBean branchStorageBean;

	private String inBranchId;
	private String inStorageId;
	private String outBranchId;
	private String outStorageId;

	private Integer preVersion;
	private Integer currentVersion;

	private OperationVersionBean operationVersionBean;

	public String getInBranchId() {
		return inBranchId;
	}

	public void setInBranchId(String inBranchId) {
		this.inBranchId = inBranchId;
	}

	public String getInStorageId() {
		return inStorageId;
	}

	public void setInStorageId(String inStorageId) {
		this.inStorageId = inStorageId;
	}

	public String getOutBranchId() {
		return outBranchId;
	}

	public void setOutBranchId(String outBranchId) {
		this.outBranchId = outBranchId;
	}

	public String getOutStorageId() {
		return outStorageId;
	}

	public void setOutStorageId(String outStorageId) {
		this.outStorageId = outStorageId;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public List<BranchStorage> getInStorageList() {
		return inStorageList;
	}

	public List<BranchStorage> getOutStorageList() {
		return outStorageList;
	}

	public String getConfirmTime() {
		return getDateStr(confirmTime);
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	private Date businessDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		shopLst = branchBean.queryById(getLoginBranchId());

		if (shopLst.size() > 0) {
			inStorageList = branchStorageBean.query(getLoginBranchId(), branchType);

			outStorageList = inStorageList;
		}
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			transferHeader = transferHeaderBean.queryById(formId);

			formTime = transferHeader.getFormTime();
			confirmTime = transferHeader.getConfirmTime();

			preVersion = operationVersionBean.queryVersion(formId).getVersion();
		}

		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			transferHeader = transferHeaderBean.queryById(formId);

			formTime = transferHeader.getFormTime();
			confirmTime = transferHeader.getConfirmTime();
		}

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		int total = transferHeaderBean.count(startDate, endDate, inBranchId, inStorageId, outBranchId, outStorageId,
				"", branchType);

		setTotal(total);

		if (total > 0) {
			List<TransferHeader> headerLst = transferHeaderBean.queryBill(startDate, endDate, inBranchId, inStorageId,
					outBranchId, outStorageId, "", branchType, getStart(), getEnd());

			int rownumber = getStart();
			for (TransferHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				// 制单日期
				String formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
				json.put("formTimeActual", formTimeActual);
				// 审核日期
				String auditTime = getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);

				// 确认日期
				String confirmTime = getDateStr(header.getConfirmTime());
				json.put("confirmTime", confirmTime);

				// 调出日期
				String outTime = getDateStr(header.getOutTime());
				json.put("outTime", outTime);

				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				json.put("formStatus", formStatus);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd() && !isMobile()) {
				double allPayAmt = transferHeaderBean.sum(startDate, endDate, inBranchId, inStorageId, outBranchId,
						outStorageId, "", branchType);
				TransferHeader item = new TransferHeader();
				JSONObject sumJObject = JSONObject.fromObject(item);
				sumJObject.put("rownumber", "合计");
				sumJObject.put("allPayAmt", allPayAmt);
				arr.add(sumJObject);
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

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public TransferHeader getTransferHeader() {
		return transferHeader;
	}

	public void setTransferHeader(TransferHeader transferHeader) {
		this.transferHeader = transferHeader;
	}

	public String getFormTime() {
		return getDateStr(formTime);
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setTransferHeaderBean(TransferHeaderBean transferHeaderBean) {
		this.transferHeaderBean = transferHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public Integer getPreVersion() {
		return preVersion;
	}

	public void setPreVersion(Integer preVersion) {
		this.preVersion = preVersion;
	}

	public Integer getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	private String getDateStr(Date date, String format) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, format);
		}
		return "";
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
}
