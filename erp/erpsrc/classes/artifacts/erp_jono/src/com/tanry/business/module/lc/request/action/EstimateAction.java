/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 4, 2014 by liyzh
 * Last edited on Nov 4, 2014 by liyzh
 * 
 * 说明： 物流中心采购预估单管理
 */
package com.tanry.business.module.lc.request.action;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.store.BranchBean;
import pojo.form.RequestHeader;
import action.common.BaseAction;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class EstimateAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private RequestHeader requestHeader;
	private Date formTime;
	private Date receiveTime;

	private Date auditTime;
	private Date refDateStart1;
	private Date refDateEnd1;
	private Date refDateStart2;
	private Date refDateEnd2;
	private Date refDateStart3;
	private Date refDateEnd3;

	private String jsonData;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心的营业时间作为预估单的制单时间
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = requestHeader.getFormTime();
		String formId = FormUtil.generateFormId("YG", getLoginBranchId(), formTime);// YG预估
		requestHeader.setFormId(formId);

		requestHeader.setFormMaker(getLoginUserId());

		receiveTime = requestHeader.getReceiveTime();
		refDateStart1 = requestHeader.getRefDateStart1();
		refDateEnd1 = requestHeader.getRefDateEnd1();
		refDateStart2 = requestHeader.getRefDateStart2();
		refDateEnd2 = requestHeader.getRefDateEnd2();
		refDateStart3 = requestHeader.getRefDateStart3();
		refDateEnd3 = requestHeader.getRefDateEnd3();
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public String getRefDateStart1() {
		return DateTimeUtil.getDateStr(refDateStart1);
	}

	public String getRefDateEnd1() {
		return DateTimeUtil.getDateStr(refDateEnd1);
	}

	public String getRefDateStart2() {
		return DateTimeUtil.getDateStr(refDateStart2);
	}

	public String getRefDateEnd2() {
		return DateTimeUtil.getDateStr(refDateEnd2);
	}

	public String getRefDateStart3() {
		return DateTimeUtil.getDateStr(refDateStart3);
	}

	public String getRefDateEnd3() {
		return DateTimeUtil.getDateStr(refDateEnd3);
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}
}
