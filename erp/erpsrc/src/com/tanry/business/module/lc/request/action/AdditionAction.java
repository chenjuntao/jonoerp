/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 6, 2014 by liyzh
 * Last edited on Nov 6, 2014 by liyzh
 * 
 * 说明： 补单操作，直接转化为采购单
 */
package com.tanry.business.module.lc.request.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.RequestHeaderBean;
import logic.module.lc.RequestPurchaseBean;
import logic.module.lc.RequestSummaryBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.CollectDetail;
import pojo.form.RequestHeader;
import action.common.BaseAction;

import com.tanry.business.module.lc.request.service.AdditionService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class AdditionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private RequestSummaryBean lcRequestSummaryBean;
	private RequestPurchaseBean requestPurchaseBean;
	private AdditionService additionService;
	private RequestHeaderBean requestHeaderBean;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private String formId;
	/**
	 * 单据类型（餐厅要货/外部订货）
	 */
	private String formType;
	private String ids;
	private Date businessDate;
	private Date formTime;

	private RequestHeader requestHeader;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			requestHeader = requestHeaderBean.queryById(formId);
			formTime = requestHeader.getFormTime();
		}

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<RequestHeader> headerLst = null;
		headerLst = lcRequestSummaryBean.queryAddition(businessDate, formType);

		int rownumber = 1;
		JSONArray arr = new JSONArray();
		for (RequestHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			String formTime = DateTimeUtil.getDateStr(header.getFormTime());
			json.put("formTime", formTime);
			String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
			json.put("receiveTime", receiveTime);
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

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		List<CollectDetail> detailLst = requestPurchaseBean.query(formId);
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

	public void createPurchaseBill() throws NoPrivilegeException, SQLException, NoConnection {
		requestHeader.setFormMaker(getLoginUsername());
		additionService.createPurchaseBill(getLoginUserId(), requestHeader);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public void setLcRequestSummaryBean(RequestSummaryBean lcRequestSummaryBean) {
		this.lcRequestSummaryBean = lcRequestSummaryBean;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setAdditionService(AdditionService additionService) {
		this.additionService = additionService;
	}

	public void setRequestPurchaseBean(RequestPurchaseBean requestPurchaseBean) {
		this.requestPurchaseBean = requestPurchaseBean;
	}

}
