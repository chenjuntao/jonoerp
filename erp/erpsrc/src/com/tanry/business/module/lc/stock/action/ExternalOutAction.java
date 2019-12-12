/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 9, 2014 by liyzh
 * Last edited on Dec 9, 2014 by liyzh
 * 
 * 说明： 物流中心外部配送出库管理
 */
package com.tanry.business.module.lc.stock.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import net.sf.json.JSONObject;
import pojo.form.ShippingHeader;
import action.common.BaseAction;

import com.tanry.business.module.lc.stock.service.ExternalOutService;
import com.tanry.framework.acl.NoPrivilegeException;

public class ExternalOutAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private ExternalOutService externalOutService;

	private ShippingHeader shippingHeader;
	private String jsonData;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	/**
	 * 填充配送单
	 */
	public void fillBill() throws NoPrivilegeException, SQLException, NoConnection {
		String userId = getLoginUserId();
		shippingHeader.setInputerId(userId);
		shippingHeader.setInputer(getLoginUsername());
		shippingHeader.setInputTime(new Date());
		externalOutService.fillBill(userId, shippingHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setExternalOutService(ExternalOutService externalOutService) {
		this.externalOutService = externalOutService;
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

}
