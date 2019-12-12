/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 16, 2015 by liyzh
 * Last edited on Apr 16, 2015 by liyzh
 * 
 * 说明： 库位与物料关系配置 ，主要供物流中心捡货使用
 */
package com.tanry.business.module.hq.config.shelf.service;

import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.ShelfBean;
import logic.store.ShelfItemBean;
import pojo.store.ShelfItem;

import com.tanry.framework.acl.NoPrivilegeException;

public class ShelfItemService {

	private ShelfBean shelfBean;
	private ShelfItemBean shelfItemBean;

	/**
	 * @param shelfId
	 * @param itemIds
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addItem(String shelfId, String itemIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] idArr = itemIds.split(",");
		for (String itemId : idArr) {
			ShelfItem relation = new ShelfItem();
			relation.setShelfId(shelfId);
			relation.setItemId(itemId);
			shelfItemBean.saveEntity(relation);
		}
	}

	/**
	 * @param shelfIds
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteShelf(String shelfIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] idArr = shelfIds.split(",");
		for (String shelfId : idArr) {
			shelfBean.deleteEntity(shelfId);
		}
	}

	/**
	 * @param shelfId
	 * @param itemIds
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteItem(String shelfId, String itemIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] idArr = itemIds.split(",");
		shelfItemBean.deleteEntity(shelfId, Arrays.asList(idArr));
	}

	public void setShelfItemBean(ShelfItemBean shelfItemBean) {
		this.shelfItemBean = shelfItemBean;
	}

	public void setShelfBean(ShelfBean shelfBean) {
		this.shelfBean = shelfBean;
	}

}
