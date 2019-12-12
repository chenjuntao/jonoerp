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
package com.tanry.business.module.hq.config.tag.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.TagDetailBean;
import logic.store.TagHeaderBean;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.TagHeader;

import com.tanry.framework.acl.NoPrivilegeException;

public class TagManageService {

	private TagHeaderBean tagHeaderBean;
	private TagDetailBean tagDetailBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSave(TagHeader tagHeader) throws NoPrivilegeException, SQLException, NoConnection {
		tagHeaderBean.deleteEntity(tagHeader.getTagId());
		tagHeaderBean.saveEntity(tagHeader);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doDelete(String tagId) throws NoPrivilegeException, SQLException, NoConnection {
		tagHeaderBean.deleteEntity(tagId);
		tagDetailBean.deleteByTagId(tagId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doBatchTagSet(String cateIds, String tagIds, String bindTag) throws NoPrivilegeException, SQLException,
			NoConnection {
		tagDetailBean.saveByCateIds(cateIds, tagIds, bindTag);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doISingleTagSet(String itemId, String tagIds) throws NoPrivilegeException, SQLException, NoConnection {
		tagDetailBean.saveByItemId(itemId, tagIds);
	}

	public List<Map> queryItems(String itemCondition, String cateIds, String tagCondition, String itemCategoryType,
			int startRow, int endRow) throws NoPrivilegeException, SQLException, NoConnection {
		return tagDetailBean.queryItems(itemCondition, cateIds, tagCondition, itemCategoryType, startRow, endRow);
	}

	public int itemsCount(String itemCondition, String cateIds, String tagCondition, String itemCategoryType)
			throws NoPrivilegeException, SQLException, NoConnection {
		return tagDetailBean.itemsCount(itemCondition, cateIds, tagCondition, itemCategoryType);
	}

	public void setTagHeaderBean(TagHeaderBean tagHeaderBean) {
		this.tagHeaderBean = tagHeaderBean;
	}

	public void setTagDetailBean(TagDetailBean tagDetailBean) {
		this.tagDetailBean = tagDetailBean;
	}

}
