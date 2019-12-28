/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 3, 2015 by liyzh
 * Last edited on Mar 3, 2015 by liyzh
 * 
 * 说明： 门店多品牌设置
 */
package com.tanry.business.module.hq.config.brand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BrandBean;
import logic.store.BrandBranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.Brand;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.brand.service.BrandManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class BrandManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BrandBean brandBean;
	private BrandBranchBean brandBranchBean;
	private BrandManageService brandManageService;

	private String brandId;
	private String brandIds;
	private String branchIds;
	private Brand brand;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(brandId)) {
			brand = brandBean.queryById(brandId);
		}
		return SUCCESS;
	}

	public String branchView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(brandId)) {
			brand = brandBean.queryById(brandId);
		}
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkBrandId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		Brand brand = brandBean.queryById(brandId);
		if (brand != null) {
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
		List<Brand> brandLst = brandBean.query();

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Brand brand : brandLst) {
			JSONObject jObject = JSONObject.fromObject(brand);
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

		List<Map> branchLst = brandBranchBean.queryTree(brandId);
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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(brandId)) {
			brandBean.updateEntity(brandId, brand);
		} else {
			brandBean.saveEntity(brand);
		}
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranch() throws NoPrivilegeException, SQLException, NoConnection {
		brandManageService.setBranch(brandId, branchIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		brandManageService.deleteBrand(brandIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
	}

	public void setBrandBranchBean(BrandBranchBean brandBranchBean) {
		this.brandBranchBean = brandBranchBean;
	}

	public void setBrandManageService(BrandManageService brandManageService) {
		this.brandManageService = brandManageService;
	}

}
