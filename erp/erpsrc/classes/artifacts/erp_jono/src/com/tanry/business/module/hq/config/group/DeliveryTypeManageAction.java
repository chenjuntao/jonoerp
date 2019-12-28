/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 4, 2015 by liyzh
 * Last edited on Feb 4, 2015 by liyzh
 * 
 * 说明： 对于每个物流中心下属的每个区域（分组），可以设置每种物料的配送模式 
 */
package com.tanry.business.module.hq.config.group;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.DeliveryTypeBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.group.service.DeliveryTypeManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class DeliveryTypeManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private DeliveryTypeBean deliveryTypeBean;
	private DeliveryTypeManageService dtypeManageService;

	private String regionId;
	private String regionName;

	private String categoryId;
	private String itemName;

	private String jsonData;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<Map> itemLst = null;
		if (TextUtil.isEmpty(categoryId)) {
			itemLst = deliveryTypeBean.query(regionId, itemName);
		} else {
			itemLst = deliveryTypeBean.queryByCate(regionId, categoryId);
		}

		int rownumber = getStart();
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

	public static void main(String[] args) {
		Date date = new Date();
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		dtypeManageService.doSave(regionId, regionName, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setDeliveryTypeBean(DeliveryTypeBean deliveryTypeBean) {
		this.deliveryTypeBean = deliveryTypeBean;
	}

	public void setDtypeManageService(DeliveryTypeManageService dtypeManageService) {
		this.dtypeManageService = dtypeManageService;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}
