/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 11, 2015 by liyzh
 * Last edited on Mar 11, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.item.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.ItemPriceBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.ItemPrice;

import com.tanry.framework.acl.NoPrivilegeException;

public class PriceManageService {

	private ItemPriceBean itemPriceBean;

	/**
	 * @param priceType
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void savePrice(String priceType, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject item = JSONObject.fromObject(obj);
			ItemPrice price = new ItemPrice();
			price.setPriceType(priceType);
			price.setItemId(item.getString("itemId"));
			price.setItemPrice(Double.parseDouble(item.get("itemPrice") + ""));
			itemPriceBean.saveEntity(price);
		}
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

}
