/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 6, 2015 by liyzh
 * Last edited on Feb 6, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.group.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.DeliveryTypeBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.DeliveryType;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;

public class DeliveryTypeManageService {

	private DeliveryTypeBean deliveryTypeBean;

	/**
	 * @param regionId
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSave(String regionId, String regionName, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		List<String> logList = new ArrayList<String>();

		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			DeliveryType dtype = new DeliveryType();
			dtype.setRegionId(regionId);

			String itemId = (String) json.get("itemId");
			dtype.setItemId(itemId);

			String newDtype = (String) json.get("dtype");
			dtype.setDeliveryType(newDtype);

			Map result = deliveryTypeBean.queryById(regionId, itemId);
			if (result.isEmpty()) {
				deliveryTypeBean.saveEntity(dtype);
			} else {
				String oldDtype = result.get("dtype").toString();
				logList.add("[" + regionId + "]" + regionName + " 配送区域 [" + itemId + "]"
						+ result.get("itemName").toString() + "配送模式由" + convert(oldDtype) + "更改为" + convert(newDtype));

				deliveryTypeBean.updateEntity(dtype);
			}
		}

		OperateLogUtil.record("修改配送模式", StringUtils.join(logList, "、"));
	}

	private String convert(String type) {
		if (type.equals("UNIFIED")) {
			return "统配";
		} else if (type.equals("DIRECT")) {
			return "直配";
		}

		return "越库";
	}

	public void setDeliveryTypeBean(DeliveryTypeBean deliveryTypeBean) {
		this.deliveryTypeBean = deliveryTypeBean;
	}

}
