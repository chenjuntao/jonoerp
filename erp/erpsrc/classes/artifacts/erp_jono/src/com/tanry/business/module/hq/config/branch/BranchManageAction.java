/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 26, 2015 by liyzh
 * Last edited on Jan 26, 2015 by liyzh
 * 
 * 说明： 部门基本信息增删改
 */
package com.tanry.business.module.hq.config.branch;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.Branch;
import pojo.store.DeliveryRegion;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.branch.service.BranchManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

import dao.store.DeliveryRegionDao;

public class BranchManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private DeliveryRegionDao deliveryRegionDao;
	private BranchManageService branchManageService;

	private String branchId;
	private String branchIds;
	private Integer enabled;
	private String branchType;
	private Branch branch;
	private List<Map> groupLst;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(branchId)) {
			branch = branchBean.GetBranchById(branchId);
		}
		if (BranchTypeEnum.RESTAURANT.toString().equals(branchType)
				|| BranchTypeEnum.FRANCHISEE.toString().equals(branchType)
				|| BranchTypeEnum.OUTERORDER.equals(branchType)) {// 餐厅,加盟店,外部订货方与配送分组有关
			editRestaurantView();
		}
		return SUCCESS;
	}

	/**
	 * 餐厅或加盟店特有的属性
	 */
	public void editRestaurantView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(branchId)) {
			DeliveryRegion region = deliveryRegionDao.getRegionByBranch(branchId);
			branch.setRegionId(region.getRegionId());
			branch.setRegionName(region.getRegionName());
		}

		List<DeliveryRegion> drs = deliveryRegionDao.queryAllRegion();
		groupLst = new ArrayList<Map>();
		for (int i = 0; i < drs.size(); i++) {
			Map<String, Object> region = new HashMap<String, Object>();
			region.put("id", drs.get(i).getRegionId());
			region.put("name", drs.get(i).getRegionName());
			region.put("deliveryCycle", drs.get(i).getDeliveryCycle());
			groupLst.add(region);
		}
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkBranchId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		Branch branch = branchBean.GetBranchById(branchId);
		if (branch != null) {
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

	/**
	 * 查询餐厅及对应的分组信息
	 */
	public void queryRestaurant() throws NoPrivilegeException, SQLException, NoConnection {
		List<Branch> branchLst = branchBean.queryRestaurant(BranchTypeEnum.getEnum(branchType));

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (Branch branch : branchLst) {
			JSONObject jObject = JSONObject.fromObject(branch);
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jsonArray.add(jObject);
			rownumber++;
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有的餐厅、物流中心、中央工厂的营业日期
	 */
	public void queryAll() throws NoPrivilegeException, SQLException, NoConnection {
		List<Branch> branchLst = branchBean.queryByTypeName(BranchTypeEnum.R_C_L, "");

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (Branch branch : branchLst) {
			JSONObject jObject = JSONObject.fromObject(branch);
			String businessDate = DateTimeUtil.getDateStr(branch.getBusinessDate());
			jObject.put("businessDate", businessDate);
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jsonArray.add(jObject);
			rownumber++;
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据部门类型查询部门数据，按树形结构组织
	 */
	public void treeQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Branch> branchLst = branchBean.queryByTypeName(BranchTypeEnum.getEnum(branchType), "");

		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有部门");
		arr.add(root);

		for (Branch branch : branchLst) {
			JSONObject jObject = JSONObject.fromObject(branch);
			jObject.put("id", branch.getBranchId());
			jObject.put("name", branch.getBranchId() + branch.getBranchName());
			jObject.put("parent", "root");
			arr.add(jObject);
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(branchId)) {
			branchBean.updateEntity(branchId, branch);
		} else {
			branchBean.saveEntity(branch);
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
	 * 保存餐厅及对应的分组信息
	 */
	public void saveRestaurant() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(branchId)) {
			branchManageService.updateRestaurant(branchId, branch);
		} else {
			branchManageService.saveRestaurant(branch);
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
		branchBean.enableBranch(branchId, enabled);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDisable() throws NoPrivilegeException, SQLException, NoConnection {
		branchManageService.doDisable(branchId, enabled);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDisableWithSupplier() throws NoPrivilegeException, SQLException, NoConnection {
		branchManageService.doDisableWithSupplier(branchId, enabled);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void containitems() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();

		boolean flag = branchBean.containsItemsWithSupplier(branchId);
		if (flag) {
			result.put("msg", "go to page");
		} else {
			branchBean.enableBranch(branchId, enabled);
			result.put("msg", "ok");
		}

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void containitemsWithBranch() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();
		String supplierId = branchId;

		boolean flag = branchBean.containsItemsWithBranch(supplierId);
		if (flag) {
			result.put("msg", "go to page");
		} else {
			branchBean.enableBranch(branchId, enabled);
			result.put("msg", "ok");
		}

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		branchManageService.deleteBranch(branchType, branchIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除餐厅及对应的分组关联信息
	 */
	public void deleteRestaurant() throws NoPrivilegeException, SQLException, NoConnection {
		branchManageService.deleteRestaurant(branchIds);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Map> getGroupLst() {
		return groupLst;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchManageService(BranchManageService branchManageService) {
		this.branchManageService = branchManageService;
	}

	public void setDeliveryRegionDao(DeliveryRegionDao deliveryRegionDao) {
		this.deliveryRegionDao = deliveryRegionDao;
	}

}
