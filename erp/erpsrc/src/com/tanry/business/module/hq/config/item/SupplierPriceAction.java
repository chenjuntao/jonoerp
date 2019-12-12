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
 * 说明： 供应商价格设置
 */
package com.tanry.business.module.hq.config.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.ItemPriceBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.service.PriceManageService;
import com.tanry.framework.acl.NoPrivilegeException;

public class SupplierPriceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemPriceBean itemPriceBean;
	private PriceManageService priceManageService;

	private String supplierId;
	private String jsonData;

	/**
	 * 从价格表中获取已经存在的供应商供货信息
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = itemPriceBean.queryBySupplier(supplierId);

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

	public void savePrice() throws NoPrivilegeException, SQLException, NoConnection {
		priceManageService.savePrice(supplierId, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

	public void setPriceManageService(PriceManageService priceManageService) {
		this.priceManageService = priceManageService;
	}

}
