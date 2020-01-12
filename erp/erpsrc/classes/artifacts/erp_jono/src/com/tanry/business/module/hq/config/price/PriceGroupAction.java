/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 4, 2015 by liyzh
 * Last edited on Mar 4, 2015 by liyzh
 * 
 * 说明： 价格组增删改
 */
package com.tanry.business.module.hq.config.price;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BrandBean;
import logic.store.PgroupBranchBean;
import logic.store.PriceGroupBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.Brand;
import pojo.store.PriceGroup;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.price.service.PriceGroupService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class PriceGroupAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private PriceGroupBean priceGroupBean;
	private PgroupBranchBean pgroupBranchBean;
	private BrandBean brandBean;
	private PriceGroupService priceGroupService;

	private String priceGroupId;
	private String priceGroupType;
	private PriceGroup priceGroup;
	private String priceGroupIds;
	private String itemId;
	private String supplierId;
	/**
	 * 新建价格分组时选择所属的品牌
	 */
	private List<Brand> brandLst;
	/**
	 * 新建价格分组时选择一个已经存在的价格分组，用于初始化相应的原料价格，这里是供前台生成下拉列表
	 */
	private List<PriceGroup> refGroupLst;
	/**
	 * 新建价格分组时选择一个已经存在的价格分组，用于初始化相应的原料价格，这里供后台保存数据
	 */
	private String refGroupId;
	/**
	 * 设置一个价格组下包含哪些餐厅
	 */
	private String branchIds;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(priceGroupId)) {
			priceGroup = priceGroupBean.queryById(priceGroupId);
		} else {// 新增
			refGroupLst = priceGroupBean.query(priceGroupType);
		}
		if ("BRAND".equals(priceGroupType)) {// 品牌下分价格组
			if (!TextUtil.isEmpty(priceGroupId)) {
				Brand brand = brandBean.queryById(priceGroup.getOwner());
				priceGroup.setOwnerName(brand.getBrandName());
			} else {// 新增
				brandLst = brandBean.query();
			}
		}
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkGroupId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		PriceGroup priceGroup = priceGroupBean.queryById(priceGroupId);
		if (priceGroup != null) {
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

	public void queryExport() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		String priceType = ".";
		if (!TextUtil.isEmpty(itemId))
			itemId = itemId.trim();
		if (!TextUtil.isEmpty(supplierId))
			supplierId = supplierId.trim();
		int total = priceGroupBean.queryPriceCount(itemId, supplierId, priceType);
		setTotal(total);
		List<Map> prices = priceGroupBean.queryPrice(itemId, supplierId, priceType, 0, total);
		int rownumber = getStart();
		JSONArray arr = new JSONArray();
		for (Map p : prices) {
			JSONObject json = JSONObject.fromObject(p);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryPrice() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		String priceType = ".";
		if (!TextUtil.isEmpty(itemId))
			itemId = itemId.trim();
		if (!TextUtil.isEmpty(supplierId))
			supplierId = supplierId.trim();
		int total = priceGroupBean.queryPriceCount(itemId, supplierId, priceType);
		setTotal(total);
		List<Map> prices = priceGroupBean.queryPrice(itemId, supplierId, priceType, getStart(), getEnd());
		int rownumber = getStart();
		JSONArray arr = new JSONArray();
		for (Map p : prices) {
			JSONObject json = JSONObject.fromObject(p);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<PriceGroup> groupLst = priceGroupBean.query(priceGroupType);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (PriceGroup priceGroup : groupLst) {
			JSONObject jObject = JSONObject.fromObject(priceGroup);
			jObject.put("rownumber", rownumber);
			arr.add(jObject);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryBranch() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有部门");
		arr.add(root);

		priceGroup = priceGroupBean.queryById(priceGroupId);
		// 查询该价格组所在品牌的门店列表，并且这个品牌中被其它价格组包含的门店除外
		List<Map> branchLst = pgroupBranchBean.queryTree(priceGroupId, priceGroup.getOwner());
		int rownumber = getStart();
		for (Map branch : branchLst) {
			JSONObject json = JSONObject.fromObject(branch);
			json.put("rownumber", rownumber);
			String checked = (String) branch.get("checked");
			if (!TextUtil.isEmpty(checked)) {
				json.put("checked", true);
			}
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
	 * 新增或修改时保存价格组的基本信息 priceGroupId 老的编码，用于修改
	 */
	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		priceGroupService.savePriceGroup(priceGroupId, priceGroup, refGroupId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		priceGroupService.deletePriceGroup(priceGroupIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置一个价格组下包含哪些餐厅
	 */
	public void setBranch() throws NoPrivilegeException, SQLException, NoConnection {
		priceGroupService.setBranch(priceGroupId, branchIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPriceGroupId() {
		return priceGroupId;
	}

	public void setPriceGroupId(String priceGroupId) {
		this.priceGroupId = priceGroupId;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public PriceGroup getPriceGroup() {
		return priceGroup;
	}

	public void setPriceGroup(PriceGroup priceGroup) {
		this.priceGroup = priceGroup;
	}

	public List<Brand> getBrandLst() {
		return brandLst;
	}

	public List<PriceGroup> getRefGroupLst() {
		return refGroupLst;
	}

	public void setRefGroupId(String refGroupId) {
		this.refGroupId = refGroupId;
	}

	public String getPriceGroupType() {
		return priceGroupType;
	}

	public void setPriceGroupType(String priceGroupType) {
		this.priceGroupType = priceGroupType;
	}

	public void setPriceGroupBean(PriceGroupBean priceGroupBean) {
		this.priceGroupBean = priceGroupBean;
	}

	public void setPgroupBranchBean(PgroupBranchBean pgroupBranchBean) {
		this.pgroupBranchBean = pgroupBranchBean;
	}

	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
	}

	public void setPriceGroupService(PriceGroupService priceGroupService) {
		this.priceGroupService = priceGroupService;
	}

	public void setPriceGroupIds(String priceGroupIds) {
		this.priceGroupIds = priceGroupIds;
	}

}
