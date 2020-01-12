/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 5, 2015 by liyzh
 * Last edited on Jan 5, 2015 by liyzh
 * 
 * 说明： 中央工厂产品出库
 */
package com.tanry.business.module.cf.stock;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.PurchasingHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import pojo.form.PurchasingHeader;
import pojo.form.ShippingHeader;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class StockOutAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private PurchasingHeaderBean purchasingHeaderBean;
	private BranchStorageBean branchStorageBean;
	private BranchBean branchBean;

	private String formId;
	private String formRefId;
	private ShippingHeader shippingHeader;
	private List<BranchStorage> storeLst;
	private Date receiveTime;
	private Date formTime;
	private Date startDate;
	private Date endDate;
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

		endDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取中央工厂当前的营业时间
		businessDate = endDate;
		startDate = DateTimeUtil.addDays(endDate, -1);
		if (!TextUtil.isEmpty(isWelcome)&&isWelcome.equals("Y"))startDate = endDate = businessDate = null;
		return SUCCESS;
	}

	public String manualView() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		Branch cfBranch = branchBean.GetBranchById(cfCode);

		storeLst = branchStorageBean.query(cfCode, BranchType.CENTRALFACTORY);

		shippingHeader = new ShippingHeader();
		shippingHeader.setRequesterId(getLcCode());
		shippingHeader.setRequester(getCodeName(BranchType.LOGISTICSCENTER));
		shippingHeader.setProviderId(cfCode);
		shippingHeader.setProvider(cfBranch.getBranchName());

		shippingHeader.setFormMakerId(getLoginUserId());
		shippingHeader.setFormMaker(getLoginUsername());
		formTime = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取中央工厂当前的营业时间
		receiveTime = formTime;// 默认营业时间为出库的配送日期
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		Branch cfBranch = branchBean.GetBranchById(cfCode);

		storeLst = branchStorageBean.query(cfCode, BranchType.CENTRALFACTORY);

		PurchasingHeader pHeader = purchasingHeaderBean.queryById(formRefId);
		shippingHeader = new ShippingHeader();
		shippingHeader.setRequesterId(pHeader.getRequesterId());
		shippingHeader.setRequester(pHeader.getRequester());
		shippingHeader.setProviderId(cfCode);
		shippingHeader.setProvider(cfBranch.getBranchName());

		receiveTime = pHeader.getReceiveTime();// 默认配送日期为采购单的到货日期

		shippingHeader.setFormMakerId(getLoginUserId());
		shippingHeader.setFormMaker(getLoginUsername());
		formTime = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取中央工厂当前的营业时间
		return SUCCESS;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormRefId() {
		return formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
}
