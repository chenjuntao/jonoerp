/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 8, 2015 by liyzh
 * Last edited on Apr 8, 2015 by liyzh
 * 
 * 说明： 原料、出品等类别设置
 */
package com.tanry.business.module.hq.config.category.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.module.hq.SetupCategoryBean;
import logic.store.ItemCategoryBean;
import pojo.store.ItemCategory;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class CategoryManageService {

	private ItemCategoryBean itemCategoryBean;
	private SetupCategoryBean setupCategoryBean;

	/**
	 * @param categoryId
	 * @param category
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveCate(String cateId, ItemCategory cate) throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(cateId)) {
			setupCategoryBean.updateEntity(cate);
		} else {
			String parentId = cate.getParentId();
			if ("root".equals(parentId)) {
				cate.setParentId("*");
				cate.setCategoryLevel("1");
				String path = "/" + cate.getCategoryId();
				cate.setPath(path);
			} else {
				ItemCategory parentCate = itemCategoryBean.queryById(parentId);
				// cate.setItemType(parentCate.getItemType());
				String level = parentCate.getCategoryLevel();
				int nextLevel = Integer.parseInt(level) + 1;
				cate.setCategoryLevel(nextLevel + "");

				String path = parentCate.getPath() + "/" + cate.getCategoryId();
				cate.setPath(path);
			}
			setupCategoryBean.saveEntity(cate);
		}
	}

	// /**
	// * @param categoryId
	// * @param newParentId
	// */
	// public void moveMenu(String categoryId, String newParentId) throws
	// NoPrivilegeException, SQLException, NoConnection {
	// SysMenu category = categoryBean.queryById(categoryId);
	// SysMenu parentMenu = categoryBean.queryById(newParentId);
	// if (parentMenu.getId().equals(category.getParentId())) {// 不需要移动
	// return;
	// }
	// moveMenu(category, parentMenu);// 使用对象作为参数，尽量减少递归时查询数据库的次数
	//
	// }
	//
	// /**
	// * @param category
	// * @param parentMenu
	// */
	// public void moveMenu(SysMenu category, SysMenu parentMenu) throws
	// NoPrivilegeException, SQLException, NoConnection {
	// String path = parentMenu.getPath() + "/" + category.getId();
	// category.setPath(path);
	// category.setParentId(parentMenu.getId());
	// categoryBean.updateEntity(category);// 更新菜单
	// List<SysMenu> childLst = categoryBean.queryChildren(category.getId());
	// if (childLst != null && !childLst.isEmpty()) {
	// for (SysMenu child : childLst) {
	// moveMenu(child, category);// 递归移动子菜单
	// }
	// }
	// }

	public void setSetupCategoryBean(SetupCategoryBean setupCategoryBean) {
		this.setupCategoryBean = setupCategoryBean;
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}
}
