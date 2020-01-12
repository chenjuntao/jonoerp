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
import java.util.ArrayList;
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

public class FranchiseePGroupAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private PriceGroupBean priceGroupBean;
	private PgroupBranchBean pgroupBranchBean;
	private BrandBean brandBean;
	private PriceGroupService priceGroupService;

	private String itemIdorName;
	private String priceGroupId;
	private String priceGroupName;
	private String priceGroupType;
	private PriceGroup priceGroup;
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
			priceGroup = priceGroupBean.fqueryById(priceGroupId);
		} else {// 新增
			refGroupLst = priceGroupBean.Fquery();
		}
		if ("LC".equals(priceGroupType)) {// 品牌下分价格组
			if (!TextUtil.isEmpty(priceGroupId)) {
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

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<PriceGroup> groupLst = priceGroupBean.Fquery();

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
		String BranchType = "";
		String GroupTypeID = "";
		String theName = "";
		if (priceGroupId.indexOf("JOIN") != -1) {
			BranchType = "FRANCHISEE";
			GroupTypeID = "JOIN";
			theName = "加盟店";
		} else {
			if (priceGroupId.indexOf("RETAIL") != -1) {
				BranchType = "OUTERORDER";
				GroupTypeID = "RETAIL";
				theName = "外部订货方";
			} else {
				if (priceGroupId.indexOf("SALE") != -1) {
					BranchType = "RESTAURANT";
					GroupTypeID = "SALE";
					theName = "直营店";
				} else {
					BranchType = "CENTRALFACTORY,LOGISTICSCENTER,RESTAURANT";
					GroupTypeID = "Normal";
					theName = "门店";
				}
			}
		}
		root.put("id", "root");
		root.put("name", theName);
		arr.add(root);
		// 查询该价格组所在品牌的门店列表，并且这个品牌中被其它价格组包含的门店除外
		List<Map> branchLst = pgroupBranchBean.queryAllFranchisee(priceGroupId, BranchType);
		int rownumber = getStart();
		for (Map branch : branchLst) {
			JSONObject json = JSONObject.fromObject(branch);
			json.put("rownumber", rownumber);
			String checked = (String) branch.get("checked");
			if (checked.equals("Y")) {
				json.put("checked", true);
			} else {
				json.put("checked", false);
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

	public void queryProduct() throws NoPrivilegeException, SQLException, NoConnection {
		if (itemIdorName == null || itemIdorName.isEmpty())
			itemIdorName = "";
		int total = priceGroupBean.count(priceGroupId);
		setTotal(total);

		List<Map> itemList = new ArrayList<Map>();
		if (total > 0) {
			itemList = priceGroupBean.queryItem(priceGroupId, itemIdorName, getStart(), getEnd());
		}

		JSONArray arr = new JSONArray();
		int rownumber = getStart();
		for (Map item : itemList) {
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

	public void queryExport() throws NoPrivilegeException, SQLException, NoConnection {
		if (itemIdorName == null || itemIdorName.isEmpty())
			itemIdorName = "";
		int total = priceGroupBean.count(priceGroupId);
		setTotal(total);

		List<Map> itemList = new ArrayList<Map>();
		if (total > 0) {
			itemList = priceGroupBean.queryItem(priceGroupId, itemIdorName, 0, total);
		}

		JSONArray arr = new JSONArray();
		int rownumber = getStart();
		for (Map item : itemList) {
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
	 * 新增或修改时保存价格组的基本信息 priceGroupId 老的编码，用于修改
	 */
	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		priceGroupService.doSave(priceGroup, priceGroupId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = priceGroupService.doDelete(priceGroup, priceGroupId);

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

	public void setItemIdorName(String itemIdorName) {
		this.itemIdorName = itemIdorName;
	}

	public String getItemIdorName() {
		return this.itemIdorName;
	}

	public void setPriceGroupId(String priceGroupId) {
		this.priceGroupId = priceGroupId;
	}

	public String getPriceGroupId() {
		return this.priceGroupId;
	}

	public String getPriceGroupName() {
		return priceGroupName;
	}

	public void setPriceGroupName(String priceGroupName) {
		this.priceGroupName = priceGroupName;
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
		this.priceGroupType = "LC";
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

}
