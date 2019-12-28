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
// Comments:调拨单确认
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
import service.restaurant.allocateitem.CreateBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;

public class ConfirmBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private TransferHeaderBean transferHeaderBean;

	private CreateBillService dbCreateBillService;
	private FormStatusBean formStatusBean;

	private Date startDate;
	private Date endDate;

	private String formId;

	private TransferHeader transferHeader;

	private String formTime;
	private String confirmTime;

	private String jsonData;
	private List<Map> shopLst;

	private Date confirmTimeOffMonth;

	private List<BranchStorage> outStorageList;

	private String branchType;

	private BranchStorageBean branchStorageBean;

	private String outStorageId;
	private String outBranchId;

	private Integer preVersion;
	private Integer currentVersion;

	private OperationVersionBean operationVersionBean;

	private Date businessDate;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = null;
		shopLst = branchBean.queryById(getLoginBranchId());

		if (shopLst.size() > 0) {
			outStorageList = branchStorageBean.query(getLoginBranchId(), branchType);

		}
		return SUCCESS;
	}

	public String confirmView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			transferHeader = transferHeaderBean.queryById(formId);

			Date businessDate = branchBean.GetBranchById(transferHeader.getOutBranchId()).getBusinessDate();

			formTime = DateTimeUtil.getDateStr(transferHeader.getFormTime());
			confirmTime = DateTimeUtil.getDateStr(businessDate);

			confirmTimeOffMonth = DateTimeUtil.getMonthFristDay(businessDate);

			transferHeader.setConfirmer(getLoginUsername());
			transferHeader.setConfirmerId(getLoginUserId());
		}

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = transferHeaderBean.count(startDate, endDate, outBranchId, outStorageId, BillStatus.UNADUIT_CN,
				branchType);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<TransferHeader> headerLst = transferHeaderBean.query(startDate, endDate, outBranchId, outStorageId,
					BillStatus.UNADUIT_CN, branchType);

			int rownumber = getStart();
			for (TransferHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);

				String auditTime = getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);

				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				json.put("formStatus", formStatus);
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

	public void doConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		Date outbusinessDate = branchBean.GetBranchById(transferHeader.getOutBranchId()).getBusinessDate();
		transferHeader.setConfirmTime(DateTimeUtil.parse(confirmTime));

		dbCreateBillService.confirmBill(getLoginUserId(), getLoginUsername(), transferHeader, jsonData,
				outbusinessDate, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
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
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
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

	public void setDbCreateBillService(CreateBillService dbCreateBillService) {
		this.dbCreateBillService = dbCreateBillService;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setOutStorageId(String outStorageId) {
		this.outStorageId = outStorageId;
	}

	public void setOutBranchId(String outBranchId) {
		this.outBranchId = outBranchId;
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

	public List<BranchStorage> getOutStorageList() {
		return outStorageList;
	}

	public String getConfirmTimeOffMonth() {
		return getDateStr(confirmTimeOffMonth);
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
