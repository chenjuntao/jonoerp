/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 3, 2014 by liyzh
 * Last edited on Nov 3, 2014 by liyzh
 * 
 * 说明： 物流中心出货单生成
 */
package com.tanry.business.module.lc.request.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.RequestHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import pojo.form.RequestHeader;
import pojo.form.ShippingHeader;
import pojo.store.Branch;
import action.common.BaseAction;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class DeliveryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private RequestHeaderBean requestHeaderBean;

	private String branchId;
	private String branchName;

	private String formId;
	private String formRefId;
	private ShippingHeader shippingHeader;

	private String ids;

	private String jsonData;
	private List<Map> shopLst;

	private Date formTime;
	private Date receiveTime;

	private Date businessDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间

		shopLst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		RequestHeader rHeader = requestHeaderBean.queryById(formRefId);
		shippingHeader = new ShippingHeader();
		shippingHeader.setRequesterId(rHeader.getBuyerId());
		shippingHeader.setRequester(rHeader.getBuyerName());
		shippingHeader.setRequestAddress(rHeader.getBuyerAddress());

		String lcCode = getLoginBranchId();
		Branch lcBranch = branchBean.GetBranchById(lcCode);
		shippingHeader.setProviderId(lcCode);
		shippingHeader.setProvider(lcBranch.getBranchName());

		receiveTime = rHeader.getReceiveTime();// 默认配送日期为要货单的到货日期
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		branchId = shippingHeader.getRequesterId();// 根据要货餐厅编号生成单据号
		formTime = new Date();// 物流中心暂时没有营业时间的概念
		formId = FormUtil.generateFormId("CH", branchId, formTime);// CH出货

		receiveTime = shippingHeader.getReceiveTime();

		shippingHeader.setFormMakerId(getLoginUserId());
		shippingHeader.setFormMaker(getLoginUsername());
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

}
