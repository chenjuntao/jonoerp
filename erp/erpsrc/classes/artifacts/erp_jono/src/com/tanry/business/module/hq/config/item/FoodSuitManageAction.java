/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 10, 2015 by liyzh
 * Last edited on Feb 10, 2015 by liyzh
 * 
 * 说明： 出品套餐各个子项以及子项可替代品编辑查询
 */
package com.tanry.business.module.hq.config.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.FoodSuitBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.service.FoodSuitManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class FoodSuitManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private FoodSuitBean foodSuitBean;
	private FoodSuitManageService foodSuitManageService;

	/**
	 * 选择套餐子项或者换品
	 */
	private String selType;
	private String exceptId;
	private String itemName;
	private String itemId;
	private String suitId;
	private String subItemId;
	private String jsonData;

	/**
	 * 选择出品，查询售价
	 */
	public void queryProduct() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = null;
		if (!TextUtil.isEmpty(exceptId)) {// 在餐厅出品套餐设置中，换品与子项需要互斥
			itemLst = foodSuitBean.queryProduct(itemName, exceptId);
		} else {
			itemLst = foodSuitBean.queryProduct(itemName);
		}

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

	public void querySubItem() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = foodSuitBean.querySubItem(suitId);

		JSONArray arr = new JSONArray();
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			Double allPrice = (Double) item.get("itemCount") * (Double) item.get("salePrice");
			json.put("allPrice", allPrice);
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询子项可替代品
	 */
	public void queryOption() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = foodSuitBean.queryOption(suitId, subItemId);
		JSONArray arr = new JSONArray();
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存套餐子项
	 */
	public void saveSubItem() throws NoPrivilegeException, SQLException, NoConnection {
		foodSuitManageService.saveSubItem(suitId, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存子项可替代品
	 */
	public void saveOption() throws NoPrivilegeException, SQLException, NoConnection {
		foodSuitManageService.saveOption(suitId, subItemId, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setExceptId(String exceptId) {
		this.exceptId = exceptId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSelType() {
		return selType;
	}

	public void setSelType(String selType) {
		this.selType = selType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setSuitId(String suitId) {
		this.suitId = suitId;
	}

	public String getSubItemId() {
		return subItemId;
	}

	public void setSubItemId(String subItemId) {
		this.subItemId = subItemId;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setFoodSuitBean(FoodSuitBean foodSuitBean) {
		this.foodSuitBean = foodSuitBean;
	}

	public void setFoodSuitManageService(FoodSuitManageService foodSuitManageService) {
		this.foodSuitManageService = foodSuitManageService;
	}

}
