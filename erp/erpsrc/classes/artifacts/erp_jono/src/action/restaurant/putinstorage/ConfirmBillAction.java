/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 5, 2014 by liyzh
 * Last edited on Sep 5, 2014 by liyzh
 * 
 * 说明：外部入库单审核
 */
package action.restaurant.putinstorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.InputHeaderBean;
import logic.form.OperationVersionBean;
import logic.module.hq.MonthlySettleRecordBean;
import logic.store.BranchBean;
import net.sf.json.JSONObject;
import pojo.form.InputHeader;
import service.restaurant.putinstorage.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class ConfirmBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private InputHeaderBean inputHeaderBean;
	private MonthlySettleRecordBean monthlySettleRecordBean;
	private BillManageService billManageService;

	private String branchName;

	private String formId;

	/**
	 * 允许下次入库
	 */
	private String nextTag;

	private InputHeader inputHeader;
	private Date inputTime;
	private Date auditTime;

	private String jsonData;
	private List<Map> shopLst;
	private String branchType;
	private Date lastDate;
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
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();
			branchType = getLoginBranchType();
			inputHeader = inputHeaderBean.queryById(formId);
			inputTime = inputHeader.getInputTime();
			inputHeader.setAuditor(getLoginUsername());
			lastDate = monthlySettleRecordBean.queryLastSettleMonth();
			lastDate = DateTimeUtil.addDays(lastDate, 1);
			auditTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		}

		return SUCCESS;
	}

	/**
	 * 审核，只改变状态，不修改数据
	 */
	public void auditById() throws NoPrivilegeException, SQLException, NoConnection {
		Date businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		billManageService.auditById(formId, getLoginUserId(), businessDate);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();// 获得当前门店，用于mrp中保存门店id
		inputHeader.setInputDepartmentId(branchId);
		billManageService.auditBill(getLoginUserId(), inputHeader, nextTag, jsonData, currentVersion);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBranchName() {
		return branchName;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public String getInputTime() {
		return getDateStr(inputTime);
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getAuditTime() {
		return getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
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

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setBillManageService(BillManageService billManageService) {
		this.billManageService = billManageService;
	}

	public void setNextTag(String nextTag) {
		this.nextTag = nextTag;
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

	public String getBranchType() {
		return branchType;
	}

	public String getLastDate() {
		return DateTimeUtil.getDateStr(lastDate);
	}

	public void setMonthlySettleRecordBean(MonthlySettleRecordBean monthlySettleRecordBean) {
		this.monthlySettleRecordBean = monthlySettleRecordBean;
	}

}
