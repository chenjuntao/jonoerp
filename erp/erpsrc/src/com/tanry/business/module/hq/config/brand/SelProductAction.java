/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 16, 2015 by liyzh
 * Last edited on Jul 16, 2015 by liyzh
 * 
 * 说明： 根据名称或编码查询出品
 */
package com.tanry.business.module.hq.config.brand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.BrandProductBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.vo.ItemVO;
import com.tanry.framework.acl.NoPrivilegeException;

public class SelProductAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BrandProductBean brandProductBean;

	private String itemName;

	/**
	 * 根据名称或编码查询出品
	 */
	public void queryProduct() throws NoPrivilegeException, SQLException, NoConnection {
		List<ItemVO> itemLst = brandProductBean.queryProduct(itemName);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (ItemVO item : itemLst) {
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

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setBrandProductBean(BrandProductBean brandProductBean) {
		this.brandProductBean = brandProductBean;
	}

}
