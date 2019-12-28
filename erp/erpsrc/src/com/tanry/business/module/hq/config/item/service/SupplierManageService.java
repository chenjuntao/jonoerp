/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 3, 2015 by liyzh
 * Last edited on Mar 3, 2015 by liyzh
 * 
 * 说明： 管理供应商与门店以及商品的对应关系
 */
package com.tanry.business.module.hq.config.item.service;

import java.sql.SQLException;

import logic.NoConnection;
import logic.store.SupplierBranchItemBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.constant.LogType;

public class SupplierManageService {

	private SupplierBranchItemBean supplierBranchItemBean;

	/**
	 * @param supplierId
	 * @param jsonData
	 *            包含门店id数组和原料数据数组
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveRelation(String supplierId, String supplier, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		JSONObject json = JSONObject.fromObject(jsonData);
		String branchIds = json.getString("branchIds");
		String[] branchArr = branchIds.split(",");
		JSONArray itemArr = json.getJSONArray("items");
		String branchNames = json.getString("branchNames");
		String itemName = "";
		for (String branchId : branchArr) {
			String itemNames = "";
			for (Object obj : itemArr) {
				JSONObject item = (JSONObject) obj;
				String itemId = item.getString("itemId");
				itemNames += item.getString("itemName") + ",";
				supplierBranchItemBean.updateEntity(supplierId, branchId, itemId);
			}
			itemName = itemNames;
		}
		OperateLogUtil.record("qualificationSupplier", LogType.UPDATE, "门店" + branchNames + "将原料" + itemName
				+ "的供应商，更变为" + supplier);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteRelation(String supplierId, String branchId, String itemId) throws NoPrivilegeException,
			SQLException, NoConnection {
		supplierBranchItemBean.deleteUniqueEntity(branchId, itemId, supplierId);
		OperateLogUtil.record("deleteRelation", LogType.DELETE, "删除[" + supplierId + "供应商],[" + branchId + "门店],["
				+ itemId + "物料]对应关系");
	}

	public void setSupplierBranchItemBean(SupplierBranchItemBean supplierBranchItemBean) {
		this.supplierBranchItemBean = supplierBranchItemBean;
	}

}
