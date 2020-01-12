/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 6, 2015 by liyzh
 * Last edited on Jan 6, 2015 by liyzh
 * 
 * 说明： 产出记录管理
 */
package com.tanry.business.module.cf.production;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.form.WorkOrderHeaderBean;
import logic.store.BranchBean;
import pojo.form.InputDetail;
import pojo.form.InputHeader;
import pojo.form.WorkOrderHeader;
import pojo.store.Branch;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class OutputRecordAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private WorkOrderHeaderBean workOrderHeaderBean;
	private BranchBean branchBean;

	private String formRefId;

	private InputHeader inputHeader;
	private InputDetail inputDetail;
	private Date inputTime;

	public String stockinView() throws NoPrivilegeException, SQLException, NoConnection {
		WorkOrderHeader header = workOrderHeaderBean.queryById(formRefId);
		inputHeader = new InputHeader();
		inputHeader.setFormRefId(header.getFormId());

		String cfCode = getLoginBranchId();
		Branch cfBranch = branchBean.GetBranchById(cfCode);
		inputHeader.setInputDepartmentId(cfCode);
		inputHeader.setInputDepartment(cfBranch.getBranchName());

		inputHeader.setInputerId(getLoginUserId());
		inputHeader.setInputer(getLoginUsername());
		inputTime = branchBean.GetBranchById(cfCode).getBusinessDate();

		inputDetail = new InputDetail();
		inputDetail.setItemId(header.getItemId());
		inputDetail.setItemName(header.getItemName());
		inputDetail.setItemDimension(header.getItemDimension());
		inputDetail.setReceiveCount(header.getActualCount());
		return SUCCESS;
	}

	public String getFormRefId() {
		return formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public InputDetail getInputDetail() {
		return inputDetail;
	}

	public void setInputDetail(InputDetail inputDetail) {
		this.inputDetail = inputDetail;
	}

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

}
