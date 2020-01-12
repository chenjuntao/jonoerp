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
 * 说明：库位与物料关系配置 ，主要供物流中心捡货使用
 */
package com.tanry.business.module.hq.config.shelf;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.ShelfItemBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.shelf.service.ShelfItemService;
import com.tanry.framework.acl.NoPrivilegeException;

public class ShelfItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ShelfItemBean shelfItemBean;
	private ShelfItemService shelfItemService;

	private String shelfId;
	private String itemName;
	private String itemIds;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	/**
	 * 从关系表中根据库位获取相应的商品列表
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = shelfItemBean.queryItem(shelfId);

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

	/**
	 * 模糊查询商品，属于当前库位的除外
	 */
	public void queryExceptItem() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = shelfItemBean.queryExceptItem(itemName, shelfId);

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

	public void saveItem() throws NoPrivilegeException, SQLException, NoConnection {
		shelfItemService.addItem(shelfId, itemIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteRelation() throws NoPrivilegeException, SQLException, NoConnection {
		shelfItemService.deleteItem(shelfId, itemIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getShelfId() {
		return shelfId;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public void setShelfItemBean(ShelfItemBean shelfItemBean) {
		this.shelfItemBean = shelfItemBean;
	}

	public void setShelfItemService(ShelfItemService shelfItemService) {
		this.shelfItemService = shelfItemService;
	}

}
