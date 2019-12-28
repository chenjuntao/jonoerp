/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Oct 30, 2014 by liyzh
 * Last edited on Oct 30, 2014 by liyzh
 * 
 * 说明： 外部订货模板管理
 */
package com.tanry.business.module.outerorder.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.TemplateMetaBean;
import logic.module.restaurant.ArrivePeriodBean;
import logic.store.BranchBean;
import pojo.form.TemplateMeta;
import pojo.store.Branch;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class TemplateManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private TemplateMetaBean templateMetaBean;
	private ArrivePeriodBean arrivePeriodBean;

	private String branchName;

	private String templateId;
	private String templateType;

	private List<Map<String, Integer>> periodLst;

	private TemplateMeta templateMeta;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		initPeriodLst();
		return SUCCESS;
	}

	private void initPeriodLst() throws NoPrivilegeException, SQLException, NoConnection {
		List<Integer> numLst = arrivePeriodBean.queryUnified();
		periodLst = new ArrayList<Map<String, Integer>>();
		for (Integer num : numLst) {
			Map<String, Integer> period = new HashMap<String, Integer>();
			period.put("key", num);
			period.put("value", num);
			periodLst.add(period);
		}
	}

	public String addView() throws NoPrivilegeException, SQLException, NoConnection {
		initPeriodLst();
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(templateId)) {
			templateMeta = templateMetaBean.queryById(templateId);

			String branchId = templateMeta.getBranchId();
			Branch branchObj = branchBean.GetBranchById(branchId);
			branchName = branchObj.getBranchName();
		}
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(templateId)) {
			templateMeta = templateMetaBean.queryById(templateId);

			String branchId = templateMeta.getBranchId();
			Branch branchObj = branchBean.GetBranchById(branchId);
			branchName = branchObj.getBranchName();
		}
		return SUCCESS;
	}

	public String pickModelView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String getBranchName() {
		return branchName;
	}

	public List<Map<String, Integer>> getPeriodLst() {
		return periodLst;
	}

	public TemplateMeta getTemplateMeta() {
		return templateMeta;
	}

	public void setTemplateMeta(TemplateMeta tempateMeta) {
		this.templateMeta = tempateMeta;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setTemplateMetaBean(TemplateMetaBean templateMetaBean) {
		this.templateMetaBean = templateMetaBean;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public void setArrivePeriodBean(ArrivePeriodBean arrivePeriodBean) {
		this.arrivePeriodBean = arrivePeriodBean;
	}

}
