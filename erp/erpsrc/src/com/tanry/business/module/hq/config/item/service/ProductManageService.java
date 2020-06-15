/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 16, 2015 by liyzh
 * Last edited on Mar 16, 2015 by liyzh
 * 
 * 说明： 餐厅出品信息设置
 */
package com.tanry.business.module.hq.config.item.service;

import java.sql.SQLException;

import logic.NoConnection;
import logic.store.ItemMetaBean;
import logic.store.ItemPriceBean;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.ItemMeta;
import pojo.store.ItemPrice;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.PriceType;

public class ProductManageService {

	private ItemMetaBean itemMetaBean;
	private ItemPriceBean itemPriceBean;

	/**
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveProduct(String oldItemId, ItemMeta itemMeta, Double salePrice)
			throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(oldItemId)) {
			itemMetaBean.updateEntity(oldItemId, itemMeta);
		} else {
			itemMetaBean.saveEntity(itemMeta);
			
			// 新增时初始化出品售卖价
			ItemPrice itemPrice = new ItemPrice();
			itemPrice.setPriceType(PriceType.SALE);
			itemPrice.setItemId(itemMeta.getItemId());
			itemPrice.setItemPrice(salePrice);
			itemPriceBean.saveEntity(itemPrice);
			
			//向前台推送出品价格信息
			itemPriceBean.PushSalePrice(itemMeta.getItemId(), PriceType.SALE, salePrice);
		}
	}



	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}
}
