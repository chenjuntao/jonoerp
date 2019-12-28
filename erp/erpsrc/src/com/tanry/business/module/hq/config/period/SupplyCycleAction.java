/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 6, 2015 by liyzh
 * Last edited on Feb 6, 2015 by liyzh
 * 
 * 说明： 供应商供货周期配置 
 */
package com.tanry.business.module.hq.config.period;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.OptionBean;
import logic.store.SupplyCycleBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.period.service.SupplyCycleService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class SupplyCycleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private SupplyCycleBean supplyCycleBean;
	private OptionBean optionBean;
	private SupplyCycleService supplyCycleService;

	private String branchId;
	private String categoryId;
	private String itemName;
	private String itemType;
	private String jsonData;
	private boolean busFlag;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String flag = "";
		try {
			flag = optionBean.getOption("SAFE_STORAGE");
		} catch (Exception e) {
			flag = "";
		}

		busFlag = false;

		if (!TextUtil.isEmpty(flag) && flag.toUpperCase().equals("STATIC")) {
			busFlag = true;
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<Map> itemLst = null;
		if (TextUtil.isEmpty(categoryId)) {
			itemLst = supplyCycleBean.queryItem(branchId, itemName, itemType);
		} else {
			itemLst = supplyCycleBean.queryItemByCate(branchId, categoryId);
		}

		int rownumber = getStart();
		for (Map item : itemLst) {
			String scycle = String.valueOf(item.get("scycle"));
			if (scycle.equals("null") || "".equals(scycle)) {
				item.put("scycle", 0);
			}
			JSONObject json = JSONObject.fromObject(item);
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

	// 保存原料库存周期和供货周期，半成品库存周期
	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		supplyCycleService.doSave(branchId, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setSupplyCycleBean(SupplyCycleBean supplyCycleBean) {
		this.supplyCycleBean = supplyCycleBean;
	}

	public void setSupplyCycleService(SupplyCycleService supplyCycleService) {
		this.supplyCycleService = supplyCycleService;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public boolean isBusFlag() {
		return busFlag;
	}

	public void setBusFlag(boolean busFlag) {
		this.busFlag = busFlag;
	}

	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}

}
