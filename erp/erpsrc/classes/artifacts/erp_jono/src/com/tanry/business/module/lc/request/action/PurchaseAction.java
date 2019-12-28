/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 5, 2014 by liyzh
 * Last edited on Nov 5, 2014 by liyzh
 * 
 * 说明： 物流中心由汇总单生成采购单
 */
package com.tanry.business.module.lc.request.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CollectDetailBean;
import logic.form.CollectHeaderBean;
import logic.form.FormLockBean;
import logic.form.FormStatusBean;
import logic.form.PurchasingHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.CollectDetail;
import pojo.form.CollectHeader;
import action.common.BaseAction;

import com.tanry.business.module.lc.request.service.PurchaseService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

@Deprecated
public class PurchaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private CollectHeaderBean collectHeaderBean;
	private CollectDetailBean collectDetailBean;
	private FormLockBean formLockBean;
	private PurchaseService purchaseService;
	private PurchasingHeaderBean purchasingHeaderBean;
	private FormStatusBean formStatusBean;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private String formId;
	private Date businessDate;
	private Date startDate;
	private Date endDate;
	private Date formTime;

	private CollectHeader collectHeader;

	private String branchType;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		endDate = DateTimeUtil.getMonthLastDay(businessDate);
		startDate = DateTimeUtil.getMonthFristDay(businessDate);
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			collectHeader = collectHeaderBean.queryById(formId);
			formTime = collectHeader.getFormTime();
		}
		formLockBean.addLock(formId);// 加锁
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<CollectHeader> headerLst = collectHeaderBean.query(startDate, endDate, getLoginBranchId(), "unaudit");

		int rownumber = 1;
		JSONArray arr = new JSONArray();
		for (CollectHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			String formTime = DateTimeUtil.getDateStr(header.getFormTime());
			json.put("formTime", formTime);

			String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
			json.put("formStatus", formStatus);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		List<CollectDetail> detailLst = collectDetailBean.query(formId);
		int rownumber = 1;
		JSONArray arr = new JSONArray();
		for (CollectDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示当前门店所有未入库的采购单，以树形控件组织，供应商-〉采购单
	 */
	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "采购单");
		arr.add(root);
		List<Map> nodeLst = purchasingHeaderBean.queryUnStorage(getLoginBranchId());
		for (Map node : nodeLst) {
			JSONObject jsonObject = new JSONObject();
			Object nodeId = node.get("id");
			jsonObject.put("id", nodeId);
			if (node.get("name") != null) {
				jsonObject.put("name", node.get("name"));
			} else {
				jsonObject.put("name", nodeId);
			}
			jsonObject.put("parent", node.get("parent"));
			arr.add(jsonObject);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createPurchaseBill() throws NoPrivilegeException, SQLException, NoConnection {
		collectHeader.setFormMaker(getLoginUsername());
		purchaseService.createPurchaseBill(getLoginUserId(), collectHeader);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public CollectHeader getCollectHeader() {
		return collectHeader;
	}

	public void setCollectHeader(CollectHeader collectHeader) {
		this.collectHeader = collectHeader;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setCollectHeaderBean(CollectHeaderBean collectHeaderBean) {
		this.collectHeaderBean = collectHeaderBean;
	}

	public void setCollectDetailBean(CollectDetailBean collectDetailBean) {
		this.collectDetailBean = collectDetailBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setFormLockBean(FormLockBean formLockBean) {
		this.formLockBean = formLockBean;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}
}
