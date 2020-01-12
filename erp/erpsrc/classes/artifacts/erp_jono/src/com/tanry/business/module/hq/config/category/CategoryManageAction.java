/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 7, 2015 by liyzh
 * Last edited on Apr 7, 2015 by liyzh
 * 
 * 说明： 原料、出品等类别设置
 */
package com.tanry.business.module.hq.config.category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.hq.SetupCategoryBean;
import logic.store.ItemCategoryBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.ItemCategory;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.category.service.CategoryManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class CategoryManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemCategoryBean itemCategoryBean;
	private SetupCategoryBean setupCategoryBean;
	private CategoryManageService categoryManageService;

	private String categoryId;
	private String parentId;
	private String itemType;
	private ItemCategory category;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(categoryId)) {
		}
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkCateId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		ItemCategory category = itemCategoryBean.queryById(categoryId);
		JSONObject result = new JSONObject();
		if (category != null) {
			exist = true;
			result.put("categoryName", category.getCategoryName());
		}
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 类别下如果存在原料，不可删除
	 */
	public void deletable() throws NoPrivilegeException, SQLException, NoConnection {
		boolean deletable = setupCategoryBean.deletable(categoryId);

		JSONObject result = new JSONObject();
		result.put("deletable", deletable);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取出品类别（不含套餐），树形结构
	 */
	public void queryProductCate() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有出品");
		root.put("isLeaf", "N");
		jsonArray.add(root);

		List<ItemCategory> items = itemCategoryBean.queryTree("PRODUCT");

		for (int i = 0; i < items.size(); i++) {
			ItemCategory itemCategory = items.get(i);

			JSONObject jsonObject = new JSONObject();
			String cateId = itemCategory.getCategoryId().trim();
			jsonObject.put("id", cateId);
			String cateName = itemCategory.getCategoryName();
			/*
			 * if ("套餐".equals(cateName)) {// 套餐不允许做更改和增加子项 continue;// 包括大类和小类
			 * }
			 */
			jsonObject.put("name", cateName);
			jsonObject.put("isLeaf", itemCategory.getIsLeaf());
			if (itemCategory.getParentId().equals("*")) {
				jsonObject.put("parent", "root");
			} else {
				jsonObject.put("parent", itemCategory.getParentId().trim());
			}
			jsonObject.put("path", itemCategory.getPath());
			jsonArray.add(jsonObject);
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveCate() throws NoPrivilegeException, SQLException, NoConnection {
		categoryManageService.saveCate(categoryId, category);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveCate() throws NoPrivilegeException, SQLException, NoConnection {
		// menuManageService.moveMenu(menuId, parentId);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteCate() throws NoPrivilegeException, SQLException, NoConnection {
		setupCategoryBean.deleteEntity(categoryId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setCategoryManageService(CategoryManageService categoryManageService) {
		this.categoryManageService = categoryManageService;
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}

	public void setSetupCategoryBean(SetupCategoryBean setupCategoryBean) {
		this.setupCategoryBean = setupCategoryBean;
	}

}
