/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 11, 2015 by liyzh
 * Last edited on Feb 11, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.item.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.FoodSuitBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.FoodSuit;

import com.tanry.framework.acl.NoPrivilegeException;

public class FoodSuitManageService {

	private FoodSuitBean foodSuitBean;

	/**
	 * @param suitId
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveSubItem(String suitId, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		List<String> itemIdLst = new ArrayList<String>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			FoodSuit suit = new FoodSuit();
			suit.setFoodSuitId(suitId);
			String subItemId = (String) json.get("subItemId");
			suit.setFoodId(subItemId);
			suit.setItemCount(json.getDouble("itemCount"));
			suit.setItemPrice(json.getDouble("salePrice"));
			if (foodSuitBean.exist(suitId, subItemId)) {
				foodSuitBean.updateSubItem(suit);
			} else {
				foodSuitBean.saveEntity(suit);
			}
			itemIdLst.add(subItemId);
		}
		// 删除除此之外的其它记录
		foodSuitBean.deleteSubExcept(suitId, itemIdLst);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOption(String suitId, String subItemId, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		List<String> itemIdLst = new ArrayList<String>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			FoodSuit suit = new FoodSuit();
			suit.setFoodSuitId(suitId);
			suit.setFoodId(subItemId);
			String optionId = (String) json.get("optionId");
			suit.setFoodOptionId(optionId);
			// suit.setItemCount(json.getDouble("itemCount"));
			if (foodSuitBean.exist(suitId, subItemId, optionId)) {
				foodSuitBean.updateOption(suit);
			} else {
				foodSuitBean.saveEntity(suit);
			}
			itemIdLst.add(optionId);
		}
		// 删除除此之外的其它记录
		foodSuitBean.deleteOptionExcept(suitId, subItemId, itemIdLst);
	}

	public void setFoodSuitBean(FoodSuitBean foodSuitBean) {
		this.foodSuitBean = foodSuitBean;
	}

}
