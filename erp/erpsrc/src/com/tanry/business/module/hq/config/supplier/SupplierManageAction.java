/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 22, 2015 by liyzh
 * Last edited on Mar 22, 2015 by liyzh
 * 
 * 说明： 供应商与商品对应关系
 */
package com.tanry.business.module.hq.config.supplier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.SupplierBranchItemBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

public class SupplierManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private SupplierBranchItemBean supplierBranchItemBean;

	private String branchId;
	private String supplierId;
	private String itemId;
	private String jsonData;

	/**
	 * 查找某个供应商提供的商品信息
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = supplierBranchItemBean.queryItem(supplierId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
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

	/**
	 * 根据供应商和原料获取相应的门店信息
	 */
	public void queryBranch() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> branchLst = supplierBranchItemBean.queryBranch(supplierId, itemId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map branch : branchLst) {
			JSONObject json = JSONObject.fromObject(branch);
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

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setSupplierBranchItemBean(SupplierBranchItemBean supplierBranchItemBean) {
		this.supplierBranchItemBean = supplierBranchItemBean;
	}

}
