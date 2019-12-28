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
 * 说明： 物流中心需求预估单模板管理
 */
package com.tanry.business.module.lc.request.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.TemplateMetaBean;
import logic.store.BranchBean;
import pojo.form.TemplateMeta;
import pojo.store.Branch;
import pojo.store.DeliveryRegion;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

import dao.store.DeliveryRegionDao;

@Deprecated
public class TemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private DeliveryRegionDao deliveryRegionDao;
	private TemplateMetaBean templateMetaBean;

	private String branchName;

	private String templateId;
	private String templateType;
	private String templateOwner;

	private List<Map> shopLst;
	private List<Map> regionLst;

	private TemplateMeta templateMeta;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		List<DeliveryRegion> drs = deliveryRegionDao.queryRegionByLcId(getLoginBranchId());
		regionLst = new ArrayList<Map>();
		for(int i=0;i<drs.size();i++){
			Map<String, Object> region = new HashMap<String, Object>();
			region.put("id", drs.get(i).getRegionId());
			region.put("name", drs.get(i).getRegionName());
			region.put("deliveryCycle", drs.get(i).getDeliveryCycle());
			regionLst.add(region);
		}
		return SUCCESS;
	}

	public String addView() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		List<DeliveryRegion> drs = deliveryRegionDao.queryRegionByLcId(getLoginBranchId());
		regionLst = new ArrayList<Map>();
		for(int i=0;i<drs.size();i++){
			Map<String, Object> region = new HashMap<String, Object>();
			region.put("id", drs.get(i).getRegionId());
			region.put("name", drs.get(i).getRegionName());
			region.put("deliveryCycle", drs.get(i).getDeliveryCycle());
			regionLst.add(region);
		}
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

	public String copyView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(templateId)) {
			templateMeta = templateMetaBean.queryById(templateId);
		}
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		return SUCCESS;
	}

	public String pickModelView() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		return SUCCESS;
	}

	public String saveModelView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String getBranchName() {
		return branchName;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public List<Map> getRegionLst() {
		return regionLst;
	}

	public TemplateMeta getTemplateMeta() {
		return templateMeta;
	}

	public void setTemplateMeta(TemplateMeta tempateMeta) {
		this.templateMeta = tempateMeta;
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

	public void setTemplateOwner(String templateOwner) {
		this.templateOwner = templateOwner;
	}

	public String getTemplateOwner() {
		return templateOwner;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setTemplateMetaBean(TemplateMetaBean templateMetaBean) {
		this.templateMetaBean = templateMetaBean;
	}

	public void setDeliveryRegionDao(DeliveryRegionDao deliveryRegionDao) {
		this.deliveryRegionDao = deliveryRegionDao;
	}

}
