/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 21, 2014 by liyzh
 * Last edited on Aug 21, 2014 by liyzh
 * 
 * 说明： 自选原料
 */
package com.tanry.business.store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.ItemCategoryBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.ItemCategory;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ItemType;

public class SelMaterialAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemCategoryBean itemCategoryBean;

	private String itemType;

	/**
	 * 从类别表中获取原材料及半成品的分类信息
	 */
	public void queryTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有物料");
		root.put("isLeaf", "N");
		jsonArray.add(root);

		if (TextUtil.isEmpty(itemType)) {
			itemType = ItemType.R_S;
		}
		List<ItemCategory> categoryLst = itemCategoryBean.queryTree(itemType);
		for (ItemCategory category : categoryLst) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", category.getCategoryId().trim());
			jsonObject.put("name", category.getCategoryName());
			jsonObject.put("parent", category.getParentId().trim());
			jsonObject.put("itemType", category.getItemType());

			if (category.getParentId().equals("*")) {
				jsonObject.put("parent", "root");
			} else {
				jsonObject.put("parent", category.getParentId().trim());
			}
			jsonArray.add(jsonObject);
		}
		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}

}
