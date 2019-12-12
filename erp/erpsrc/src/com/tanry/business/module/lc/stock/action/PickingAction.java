/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 10, 2014 by liyzh
 * Last edited on Dec 10, 2014 by liyzh
 * 
 * 说明： 物流中心拣货单创建
 */
package com.tanry.business.module.lc.stock.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.PickingHeaderBean;
import logic.form.ShippingHeaderBean;
import logic.module.lc.CreatePickingBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.PickingHeader;
import pojo.form.ShippingHeader;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.module.lc.stock.service.PickingService;
import com.tanry.business.module.lc.stock.util.PickInstance;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class PickingAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private BranchStorageBean branchStorageBean;
	private ShippingHeaderBean shippingHeaderBean;
	private PickingHeaderBean pickingHeaderBean;
	private CreatePickingBean createPickingBean;
	private PickingService pickingService;
	private Date businessDate;
	private String formTime;

	private String ids;
	private PickingHeader pickingHeader;
	private String branchIds;
	private List<BranchStorage> storeLst;

	private String status;
	private String itemName;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		return SUCCESS;
	}

	public String aggregateView() throws NoPrivilegeException, SQLException, NoConnection {
		pickingHeader = new PickingHeader();
		pickingHeader.setFormMakerId(getLoginUserId());
		pickingHeader.setFormMaker(getLoginUsername());
		formTime = DateTimeUtil.getDateStr(branchBean.GetBranchById(getLoginBranchId()).getBusinessDate());// 获取物流中心当前的营业时间
		storeLst = branchStorageBean.query(getLoginBranchId(), BranchType.LOGISTICSCENTER);
		return SUCCESS;
	}

	/**
	 * 根据配送日期查询配送单
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(itemName)) {
			itemName = itemName.trim();
		}

		List<ShippingHeader> headerLst = !TextUtil.isEmpty(itemName) ? shippingHeaderBean.queryByReceiveTime(
				businessDate, status, itemName) : shippingHeaderBean.queryByReceiveTime(businessDate, status);
		int rownumber = getStart();
		JSONArray arr = new JSONArray();
		for (ShippingHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			// 配送日期
			String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
			json.put("receiveTime", receiveTime);
			// 制单日期
			String formTime = DateTimeUtil.getDateStr(header.getFormTime());
			json.put("formTime", formTime);
			// 审核日期
			String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
			json.put("auditTime", auditTime);
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
	 * 根据营业日期查询未审核完的同天捡货单
	 */
	public void queryByStatus() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		List<PickingHeader> headerLst = pickingHeaderBean.queryByStatus(businessDate, status);
		int rownumber = getStart();
		JSONArray arr = new JSONArray();
		for (PickingHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			// 制单日期
			String formTime = DateTimeUtil.getDateStr(header.getFormTime());
			json.put("formTime", formTime);
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
	 * 根据配送单汇总拣货数据
	 */
	public void querySummary() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = createPickingBean.query(ids);
		List<JSONObject> itemLst2 = PickInstance.group(itemLst);
		JSONObject result = PickInstance.transfer(itemLst2);

		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doQuerySummary() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> headerLst = createPickingBean.queryGroup(ids);
		List<Map> branchLst = createPickingBean.queryBranch(ids);
		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		result.put("headerLst", arr);
		result.put("branchLst", branchLst);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createPickingBill() throws NoPrivilegeException, SQLException, NoConnection {
		pickingHeader.setPickingBranchId(getLoginBranchId());
		String formIds = pickingService.createBill(getLoginUserId(), pickingHeader, ids);

		JSONObject result = new JSONObject();
		result.put("formId", formIds);
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

	public void setStatus(String status) {
		this.status = status;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setCreatePickingBean(CreatePickingBean createPickingBean) {
		this.createPickingBean = createPickingBean;
	}

	public void setPickingService(PickingService pickingService) {
		this.pickingService = pickingService;
	}

	public PickingHeader getPickingHeader() {
		return pickingHeader;
	}

	public void setPickingHeader(PickingHeader pickingHeader) {
		this.pickingHeader = pickingHeader;
	}

	public String getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public String getFormTime() {
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	public PickingHeaderBean getPickingHeaderBean() {
		return pickingHeaderBean;
	}

	public void setPickingHeaderBean(PickingHeaderBean pickingHeaderBean) {
		this.pickingHeaderBean = pickingHeaderBean;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
