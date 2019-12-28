/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 29, 2014 by liyzh
 * Last edited on Aug 29, 2014 by liyzh
 * 
 * 说明： 要货单审核
 */
package action.restaurant.goodsbill;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.OperationVersionBean;
import logic.form.RequestHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONObject;
import pojo.form.OperationVersion;
import pojo.form.RequestHeader;
import service.restaurant.goodsbill.CreateBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class ConfirmBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private RequestHeaderBean requestHeaderBean;
	private CreateBillService createBillService;

	private String formId;

	private RequestHeader requestHeader;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;

	private Date refDateStart1;
	private Date refDateEnd1;
	private Date refDateStart2;
	private Date refDateEnd2;
	private Date refDateStart3;
	private Date refDateEnd3;

	private Integer preVersion;
	private Integer currentVersion;

	private String jsonData;
	private List<Map> shopLst;
	private String isWelcome;

	private OperationVersionBean operationVersionBean;

	private Date businessDate;

	private String branchType;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y")) businessDate = null;
		shopLst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.queryById(getLoginBranchId());
		if (shopLst.size() > 0) {
			branchType = shopLst.get(0).get("branchType").toString();
		}

		if (!TextUtil.isEmpty(formId)) {
			requestHeader = requestHeaderBean.queryById(formId);
			formTime = requestHeader.getFormTime();
			receiveTime = requestHeader.getReceiveTime();
			refDateStart1 = requestHeader.getRefDateStart1();
			refDateEnd1 = requestHeader.getRefDateEnd1();
			refDateStart2 = requestHeader.getRefDateStart2();
			refDateEnd2 = requestHeader.getRefDateEnd2();
			refDateStart3 = requestHeader.getRefDateStart3();
			refDateEnd3 = requestHeader.getRefDateEnd3();

			auditTime = formTime;
			requestHeader.setAuditor(getLoginUsername());

			preVersion = operationVersionBean.queryVersion(formId).getVersion();
		}

		return SUCCESS;
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		createBillService.auditGoodsBill(getLoginUserId(), requestHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getCurrentVeriosn() throws NoPrivilegeException, SQLException, NoConnection {
		OperationVersion operationVersion = createBillService.getCurrentVeriosn(formId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("version", operationVersion.getVersion());
		result.put("operationName", operationVersion.getOperationName());
		result.put("operationTime",
				DateTimeUtil.getDateTime(operationVersion.getOperationTime(), DateTimeUtil.DEFAULT_DATETIME_FORMAT));
		result.put("operationContent", operationVersion.getOperationContent());
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 餐厅要货单审核，只改变状态，不修改数据
	 */
	public void auditById() throws NoPrivilegeException, SQLException, NoConnection {
		Date businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		createBillService.auditById(formId, getLoginUserId(), businessDate);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getReceiveTime() {
		return getDateStr(receiveTime);
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getFormTime() {
		return getDateStr(formTime);
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getAuditTime() {
		return getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getRefDateStart1() {
		return getDateStr(refDateStart1);
	}

	public void setRefDateStart1(Date refDateStart1) {
		this.refDateStart1 = refDateStart1;
	}

	public String getRefDateEnd1() {
		return getDateStr(refDateEnd1);
	}

	public void setRefDateEnd1(Date refDateEnd1) {
		this.refDateEnd1 = refDateEnd1;
	}

	public String getRefDateStart2() {
		return getDateStr(refDateStart2);
	}

	public void setRefDateStart2(Date refDateStart2) {
		this.refDateStart2 = refDateStart2;
	}

	public String getRefDateEnd2() {
		return getDateStr(refDateEnd2);
	}

	public void setRefDateEnd2(Date refDateEnd2) {
		this.refDateEnd2 = refDateEnd2;
	}

	public String getRefDateStart3() {
		return getDateStr(refDateStart3);
	}

	public void setRefDateStart3(Date refDateStart3) {
		this.refDateStart3 = refDateStart3;
	}

	public String getRefDateEnd3() {
		return getDateStr(refDateEnd3);
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

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setCreateBillService(CreateBillService createBillService) {
		this.createBillService = createBillService;
	}

	public Integer getPreVersion() {
		return preVersion;
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

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

}
