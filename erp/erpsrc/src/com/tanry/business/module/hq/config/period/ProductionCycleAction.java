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
 * 说明： 中央工厂生产周期配置
 */
package com.tanry.business.module.hq.config.period;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.ProductionCycleBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.period.service.ProductionCycleService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchCode;

public class ProductionCycleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ProductionCycleBean pcycleBean;
	private ProductionCycleService productionCycleService;

	private String categoryId;
	private String itemName;

	private String jsonData;

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<Map> itemLst = null;
		String cfCode = BranchCode.DEFAULT_CENTRALFACTORY;
		if (TextUtil.isEmpty(categoryId)) {
			itemLst = pcycleBean.queryItem(cfCode, itemName);
		} else {
			itemLst = pcycleBean.queryItemByCate(cfCode, categoryId);
		}

		int rownumber = getStart();
		for (Map item : itemLst) {
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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		productionCycleService.doSaveAction(jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void setPcycleBean(ProductionCycleBean pcycleBean) {
		this.pcycleBean = pcycleBean;
	}

	public void setProductionCycleService(ProductionCycleService productionCycleService) {
		this.productionCycleService = productionCycleService;
	}

}
