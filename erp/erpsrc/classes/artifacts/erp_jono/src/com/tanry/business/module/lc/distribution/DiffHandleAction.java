/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 5, 2015 by liyzh
 * Last edited on Aug 5, 2015 by liyzh
 * 
 * 说明： 物流中心处理配送差异
 */
package com.tanry.business.module.lc.distribution;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.lc.DisDiffHandleBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import action.common.BaseAction;

import com.tanry.business.module.lc.distribution.service.DiffHandleService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class DiffHandleAction extends BaseAction {

	private static final long serialVersionUID = 1873279279585830206L;

	private DisDiffHandleBean disDiffHandleBean;
	private DiffHandleService diffHandleService;

	private String formId;

	private ShippingHeader shippingHeader;

	private String jsonData;

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<ShippingHeader> headerLst = disDiffHandleBean.query();
		int rownumber = getStart();
		JSONArray arr = new JSONArray();
		for (ShippingHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			String formTime = DateTimeUtil.getDateStr(header.getFormTime());
			json.put("formTime", formTime);
			String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
			json.put("receiveTime", receiveTime);
			String inputTime = DateTimeUtil.getDateStr(header.getInputTime());
			json.put("inputTime", inputTime);

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
		List<ShippingDetail> detailLst = disDiffHandleBean.queryDetail(formId);

		int rownumber = 1;
		JSONArray arr = new JSONArray();
		for (ShippingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);

			String differentReason = !TextUtil.isEmpty(detail.getDifferentReason()) ? detail.getDifferentReason() : "";
			json.put("differentNote", differentReason);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doHandle() throws NoPrivilegeException, SQLException, NoConnection {
		formId = diffHandleService.doHandle(getLoginUserId(), shippingHeader, jsonData);

		JSONObject result = new JSONObject();
		if (formId != null) {
			result.put("formId", formId);
		}

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

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setDisDiffHandleBean(DisDiffHandleBean disDiffHandleBean) {
		this.disDiffHandleBean = disDiffHandleBean;
	}

	public void setDiffHandleService(DiffHandleService diffHandleService) {
		this.diffHandleService = diffHandleService;
	}

}
