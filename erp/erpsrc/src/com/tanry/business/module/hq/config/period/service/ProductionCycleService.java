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
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.period.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.ProductionCycleBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.ProductionCycle;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchCode;

public class ProductionCycleService {

	private ProductionCycleBean pcycleBean;

	/**
	 * @param branchId
	 * @param jsonData
	 *            保存生成周期
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSave(String branchId, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ProductionCycle pcycle = new ProductionCycle();
			pcycle.setBranchId(branchId);
			String itemId = (String) json.get("itemId");
			pcycle.setItemId(itemId);
			pcycle.setProductionCycle(Double.valueOf(json.get("pcycle").toString()));
			List<Map> pcycles = new ArrayList<Map>();
			pcycles = pcycleBean.queryById(itemId);
			if (pcycles.isEmpty()) {
				pcycleBean.saveEntity(pcycle);
			} else {
				pcycleBean.updateEntity(pcycle);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSaveAction(String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		doSave(BranchCode.DEFAULT_CENTRALFACTORY, jsonData);
		doSave(BranchCode.DEFAULT_LOGISTICSCENTER, jsonData);
	}

	public void setPcycleBean(ProductionCycleBean pcycleBean) {
		this.pcycleBean = pcycleBean;
	}

}
