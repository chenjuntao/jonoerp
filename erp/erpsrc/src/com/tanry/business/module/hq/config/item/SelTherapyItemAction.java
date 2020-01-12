/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 28, 2015 by liyzh
 * Last edited on Jan 28, 2015 by liyzh
 * 
 * 说明： 按配方表的需要查询原料和半成品
 */
package com.tanry.business.module.hq.config.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.hq.SelTherapyItemBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.vo.TherapyItem;
import com.tanry.framework.acl.NoPrivilegeException;

/**
 * @author charles_1018
 *
 */
public class SelTherapyItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private SelTherapyItemBean selTherapyItemBean;

	private String itemType;
	private String itemId;
	private String itemName;

	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		List<TherapyItem> itemLst = selTherapyItemBean.queryItem(itemType, itemName);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (TherapyItem item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			// 映射基础信息表与配方表的字段，对外提供统一的接口
			json.put("stockDimension", item.getItemDimension());
			json.put("itemDimension", item.getRecipeUnit());
			json.put("unitConvertFactor", item.getRecipeFactor());
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSelTherapyItemBean(SelTherapyItemBean selTherapyItemBean) {
		this.selTherapyItemBean = selTherapyItemBean;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
