/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 16, 2014 by liyzh
 * Last edited on Dec 16, 2014 by liyzh
 * 
 * 说明： 物流中心装箱单创建
 */
package com.tanry.business.module.lc.stock.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.lc.CreatePackingBean;
import logic.store.BranchBean;
import net.sf.json.JSONObject;
import pojo.form.PackingDetail;
import pojo.form.PackingHeader;
import action.common.BaseAction;

import com.tanry.business.module.lc.stock.service.PackingService;
import com.tanry.business.module.lc.stock.util.PackInstance;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class CreatePackingAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}
	private CreatePackingBean createPackingBean;
	private PackingService packingService;

	private PackingHeader packingHeader;
	/**
	 * 装箱单编号
	 */
	private String formId;
	/**
	 * 捡货单编号
	 */
	private String formRefId;
	private Map<String, Map<String, Object>> boxMap;
	private Date formTime;
	private Date startDate;
	private Date endDate;
	private Date businessDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		endDate = DateTimeUtil.getMonthLastDay(businessDate);
		startDate = DateTimeUtil.getMonthFristDay(businessDate);
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		packingHeader = new PackingHeader();
		packingHeader.setFormMakerId(getLoginUserId());
		packingHeader.setFormMaker(getLoginUsername());

		formTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();

		List<PackingDetail> itemLst = createPackingBean.queryPacking(formRefId);
		boxMap = PackInstance.iterate(itemLst);
		return SUCCESS;
	}

	/**
	 * 根据捡货单构造装箱数据
	 */
	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		List<PackingDetail> itemLst = createPackingBean.queryPacking(formRefId);
		Map<String, Map<String, Object>> boxMap = PackInstance.iterate(itemLst);
		JSONObject result = JSONObject.fromObject(boxMap);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createPackingBill() throws NoPrivilegeException, SQLException, NoConnection {
		List<PackingDetail> itemLst = createPackingBean.queryPacking(formRefId);
		boxMap = PackInstance.iterate(itemLst);
		packingHeader.setFormRefId(formRefId);
		packingHeader.setPackingBranchId(getLoginBranchId());
		formId = packingService.createBill(getLoginUserId(), packingHeader, boxMap);
		JSONObject result = new JSONObject();
		result.put("formId", formId);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public PackingHeader getPackingHeader() {
		return packingHeader;
	}

	public void setPackingHeader(PackingHeader packingHeader) {
		this.packingHeader = packingHeader;
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

	public Map<String, Map<String, Object>> getBoxMap() {
		return boxMap;
	}

	public void setCreatePackingBean(CreatePackingBean createPackingBean) {
		this.createPackingBean = createPackingBean;
	}

	public void setPackingService(PackingService packingService) {
		this.packingService = packingService;
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
