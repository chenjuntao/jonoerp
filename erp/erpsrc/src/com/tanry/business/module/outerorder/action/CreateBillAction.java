/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 1, 2014 by liyzh
 * Last edited on Nov 1, 2014 by liyzh
 * 
 * 说明： 外部订货单生成
 */
package com.tanry.business.module.outerorder.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import net.sf.json.JSONObject;
import pojo.form.RequestHeader;
import pojo.store.Branch;
import action.common.BaseAction;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class CreateBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private List<Map> shopLst;
	private String shopName;

	private RequestHeader requestHeader;
	private Date receiveTime;
	private Date formTime;

	private String buyerId;
	private String buyerAddress;

	private Date auditTime;

	private String jsonData;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.queryById(getLoginBranchId());
		Branch branchObj = branchBean.GetBranchById(shopLst.get(0).get("code").toString());
		buyerAddress = branchObj.getBranchAddress();
		formTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取当前账号的营业时间作为外部订货单的制单时间
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		String buyerId = requestHeader.getBuyerId();
		Date businessTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取当前账号的营业时间作为外部订货单的制单时间

		String formId = FormUtil.generateFormId("WBDH", buyerId, businessTime);
		requestHeader.setFormId(formId);

		formTime = businessTime;
		requestHeader.setFormMaker(getLoginUserId());

		receiveTime = requestHeader.getReceiveTime();

		return SUCCESS;
	}

	public String queryAddress() throws NoPrivilegeException, SQLException, NoConnection {
		Branch branchObj = branchBean.GetBranchById(buyerId);
		JSONObject result = new JSONObject();
		result.put("msg", branchObj.getBranchAddress());

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

}
