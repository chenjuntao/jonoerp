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
 * 说明：箱子类型(冷藏、非冷藏等)配置 
 */
package com.tanry.business.module.hq.config.box;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.BoxTypeBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.BoxType;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.box.service.BoxTypeManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class BoxTypeManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BoxTypeBean boxTypeBean;
	private BoxTypeManageService boxTypeManageService;

	private String typeId;
	private String typeIds;
	private Integer enabled;
	private BoxType boxType;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(typeId)) {
			setBoxType(boxTypeBean.queryById(typeId));
		}
		return SUCCESS;
	}

	/**
	 *  验证编号是否重复
	 */
	public void checkCode() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		boxType = boxTypeBean.queryById(typeId);
		if (boxType != null) {
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
		JSONArray arr = new JSONArray();
		List<BoxType> typeLst = boxTypeBean.query(null);

		int rownumber = getStart();
		for (BoxType btype : typeLst) {
			JSONObject json = JSONObject.fromObject(btype);
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

	public void saveType() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(typeId)) {
			boxTypeBean.updateEntity(boxType);
		} else {
			boxTypeBean.saveEntity(boxType);
		}
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doEnable() throws NoPrivilegeException, SQLException, NoConnection {
		boxTypeBean.enableType(typeId, enabled);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		boxTypeManageService.deleteType(typeIds);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}

	public BoxType getBoxType() {
		return boxType;
	}

	public void setBoxType(BoxType boxType) {
		this.boxType = boxType;
	}

	public void setBoxTypeBean(BoxTypeBean boxTypeBean) {
		this.boxTypeBean = boxTypeBean;
	}

	public void setBoxTypeManageService(BoxTypeManageService boxTypeManageService) {
		this.boxTypeManageService = boxTypeManageService;
	}
}
