/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 3, 2015 by liyzh
 * Last edited on Feb 3, 2015 by liyzh
 * 
 * 说明： 物流中心配送分组配置
 */
package com.tanry.business.module.hq.config.group;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.DeliveryRegionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.DeliveryRegion;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.group.service.GroupManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class GroupManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private DeliveryRegionBean groupBean;
	private GroupManageService groupManageService;

	private String regionId;
	private String regionIds;
	private String refRegionId;
	private Integer enabled;
	private DeliveryRegion group;

	/**
	 * 所有的物流中心列表
	 */
	private List<Map> lcCenterLst;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(regionId)) {
			group = groupBean.queryRegionById(regionId);
		} else {
			group = new DeliveryRegion();
			group.setDeliveryCycle(0);
		}
		lcCenterLst = branchBean.listShopByType(BranchTypeEnum.LOGISTICSCENTER);
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkGroup() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		group = groupBean.queryRegionById(regionId);
		if (group != null) {
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
		List<DeliveryRegion> groupLst = groupBean.queryAllRegion();

		int rownumber = getStart();
		for (DeliveryRegion region : groupLst) {
			JSONObject json = JSONObject.fromObject(region);
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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(regionId)) {
			groupBean.updateRegion(regionId, group);
		} else {
			groupBean.saveRegion(group);
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
		groupManageService.enableGroup(regionId, refRegionId, enabled);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		groupManageService.deleteGroup(regionIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public void setRegionIds(String regionIds) {
		this.regionIds = regionIds;
	}

	public void setRefRegionId(String refRegionId) {
		this.refRegionId = refRegionId;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public DeliveryRegion getGroup() {
		return group;
	}

	public void setGroup(DeliveryRegion group) {
		this.group = group;
	}

	public List<Map> getLcCenterLst() {
		return lcCenterLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setGroupBean(DeliveryRegionBean groupBean) {
		this.groupBean = groupBean;
	}

	public void setGroupManageService(GroupManageService groupManageService) {
		this.groupManageService = groupManageService;
	}
}
