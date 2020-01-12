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
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.SupplyCycleBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.SupplyCycle;

import com.tanry.framework.acl.NoPrivilegeException;

public class SupplyCycleService {

	private SupplyCycleBean supplyCycleBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSave(String branchId, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			String itemId = (String) json.get("itemId");
			List<Map> scycl = supplyCycleBean.queryItemById(branchId, itemId);
			SupplyCycle scycle = new SupplyCycle();
			scycle.setBranchId(branchId);
			scycle.setItemId(itemId);
			scycle.setSupplyCycle(Double.valueOf(json.get("scycle").toString()));
			scycle.setInventorysCycle(Double.valueOf(json.get("inventorysCycle").toString()));

			if (scycl.size() > 0) {
				supplyCycleBean.updateEntity(scycle);
			} else {
				supplyCycleBean.saveEntity(scycle);
			}
		}
	}

	public void setSupplyCycleBean(SupplyCycleBean supplyCycleBean) {
		this.supplyCycleBean = supplyCycleBean;
	}

}
