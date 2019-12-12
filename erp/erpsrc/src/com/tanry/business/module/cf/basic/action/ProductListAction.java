/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 30, 2014 by liyzh
 * Last edited on Dec 30, 2014 by liyzh
 * 
 * 说明： 中央工厂产品信息查询，链接工序信息查询
 */
package com.tanry.business.module.cf.basic.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.form.MakingProcessBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.MakingProcess;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

public class ProductListAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private MakingProcessBean makingProcessBean;

	private String itemId;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void queryProcess() throws NoPrivilegeException, SQLException, NoConnection {
		List<MakingProcess> processLst = makingProcessBean.queryByItem(itemId);
		JSONArray arr = new JSONArray();
		for (MakingProcess process : processLst) {
			JSONObject json = JSONObject.fromObject(process);
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
	}

	public void setMakingProcessBean(MakingProcessBean makingProcessBean) {
		this.makingProcessBean = makingProcessBean;
	}
}
