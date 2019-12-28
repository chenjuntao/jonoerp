/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 7, 2015 by liyzh
 * Last edited on Feb 7, 2015 by liyzh
 * 
 * 说明： 主要是供应商供应的货物名称、价格、计价时段等信息，此界面功能能够进行供应商供货信息的增删改查（价格修改必须经历调价流程）。 
 */
package com.tanry.business.module.hq.config.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.ItemMetaBean;
import logic.store.SupplierBranchItemBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.ItemMeta;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.service.SupplierManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ItemType;

public class SupplierManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemMetaBean itemMetaBean;
	private SupplierBranchItemBean supplierBranchItemBean;
	private SupplierManageService supplierManageService;

	private String branchId;
	private String supplierId;
	private String supplier;
	private String itemId;
	private String exceptId;
	private String categoryId;
	private String itemType;
	private String itemName;
	private String jsonData;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(itemId)) {
		}
		return SUCCESS;
	}

	/**
	 * 从餐厅原材料信息数据表中读取信息
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		List<ItemMeta> itemLst = null;
		if (TextUtil.isEmpty(categoryId)) {
			if (TextUtil.isEmpty(itemType)) {
				itemType = ItemType.RAW;
			}
			if (!TextUtil.isEmpty(exceptId)) {// 在餐厅出品套餐设置中，换品与子项需要互斥
				itemLst = itemMetaBean.queryItem(itemType, itemName, exceptId);
			} else {
				itemLst = itemMetaBean.queryItem(itemType, itemName);
			}
		} else {
			itemLst = itemMetaBean.queryByCategory(categoryId);
		}

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (ItemMeta item : itemLst) {
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
	 * 根据部门和原料获取相应的供应商信息
	 */
	public void querySupplier() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> supplierLst = supplierBranchItemBean.querySupplier(branchId, itemId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map supplier : supplierLst) {
			JSONObject json = JSONObject.fromObject(supplier);
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

	public void querySupplierItemsByBranchId() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> supplierLst = supplierBranchItemBean.querySupplierItemsByBranchId(branchId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map supplier : supplierLst) {
			JSONObject json = JSONObject.fromObject(supplier);
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

	public void queryBranchItemsBySupplier() throws NoPrivilegeException, SQLException, NoConnection {
		String supplierId = branchId;
		List<Map> supplierLst = supplierBranchItemBean.queryBranchItemsBySupplier(supplierId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map supplier : supplierLst) {
			JSONObject json = JSONObject.fromObject(supplier);
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

	public void deleteRelation() throws NoPrivilegeException, SQLException, NoConnection {
		supplierManageService.deleteRelation(supplierId, branchId, itemId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveRelation() throws NoPrivilegeException, SQLException, NoConnection {
		supplierManageService.saveRelation(supplierId, supplier, jsonData);

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

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setExceptId(String exceptId) {
		this.exceptId = exceptId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setSupplierBranchItemBean(SupplierBranchItemBean supplierBranchItemBean) {
		this.supplierBranchItemBean = supplierBranchItemBean;
	}

	public void setSupplierManageService(SupplierManageService supplierManageService) {
		this.supplierManageService = supplierManageService;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}
