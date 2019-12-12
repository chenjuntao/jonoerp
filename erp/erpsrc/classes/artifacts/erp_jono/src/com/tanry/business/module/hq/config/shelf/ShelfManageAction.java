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
 * 说明： 库位信息配置 ，主要供物流中心捡货使用
 */
package com.tanry.business.module.hq.config.shelf;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.ShelfBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.Shelf;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.shelf.service.ShelfItemService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class ShelfManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ShelfBean shelfBean;
	private ShelfItemService shelfItemService;

	private String shelfId;
	private String shelfIds;
	private Shelf shelf;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(shelfId)) {
			shelf = shelfBean.queryById(shelfId);
		}
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkCode() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		shelf = shelfBean.queryById(shelfId);
		if (shelf != null) {
			exist = true;
		}
		JSONObject result = new JSONObject();
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Shelf> shelfLst = shelfBean.query();

		JSONArray arr = new JSONArray();
		int rownumber = getStart();
		for (Shelf shelf : shelfLst) {
			JSONObject json = JSONObject.fromObject(shelf);
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

	public void treeQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Shelf> shelfLst = shelfBean.query();

		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有库位");
		arr.add(root);

		for (Shelf shelf : shelfLst) {
			JSONObject jObject = JSONObject.fromObject(shelf);
			jObject.put("id", shelf.getShelfId());
			jObject.put("name", shelf.getShelfName());
			jObject.put("parent", "root");
			arr.add(jObject);
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveShelf() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(shelfId)) {
			shelfBean.updateEntity(shelf);
		} else {
			shelfBean.saveEntity(shelf);
		}

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 库位下如果存在物料，不可删除
	 */
	public void deletable() throws NoPrivilegeException, SQLException, NoConnection {
		boolean deletable = shelfBean.deletable(shelfId);

		JSONObject result = new JSONObject();
		result.put("deletable", deletable);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteShelf() throws NoPrivilegeException, SQLException, NoConnection {
		shelfItemService.deleteShelf(shelfIds);

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

	public void setShelfIds(String shelfIds) {
		this.shelfIds = shelfIds;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public void setShelfBean(ShelfBean shelfBean) {
		this.shelfBean = shelfBean;
	}

	public void setShelfItemService(ShelfItemService shelfItemService) {
		this.shelfItemService = shelfItemService;
	}

}
