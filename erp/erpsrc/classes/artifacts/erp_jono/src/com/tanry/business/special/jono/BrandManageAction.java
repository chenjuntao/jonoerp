/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 13, 2015 by liyzh
 * Last edited on Aug 13, 2015 by liyzh
 * 
 * 说明： 一个品牌默认只有一个价格组(专门满足金牛角王现有需求）
 * 特例处理遵循以下原则：查询尽可能共用以前的，增删改特别处理
 */
package com.tanry.business.special.jono;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.BrandBean;
import net.sf.json.JSONObject;
import pojo.store.Brand;
import action.common.BaseAction;

import com.tanry.business.special.jono.service.BrandManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class BrandManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BrandBean brandBean;
	private BrandManageService brandManageService;

	private String brandId;
	/**
	 * 新建品牌时选择一个已经存在的品牌，参考它初始化默认价格组下各个出品的价格，这里供后台保存数据
	 */
	private String refBrandId;
	/**
	 * 新建品牌时选择一个已经存在的品牌，参考它初始化默认价格组下各个出品的价格，这里是供前台生成下拉列表
	 */
	private List<Brand> refBrandLst;

	private String brandIds;
	private String branchIds;
	private Brand brand;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(brandId)) { // 新增
			refBrandLst = brandBean.query();
		} else {
			brand = brandBean.queryById(brandId);
		}
		return SUCCESS;
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		brandManageService.saveBrand(brandId, brand, refBrandId);
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

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public void setRefBrandId(String refBrandId) {
		this.refBrandId = refBrandId;
	}

	public List<Brand> getRefBrandLst() {
		return refBrandLst;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public String getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
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

	public void setBrandManageService(BrandManageService brandManageService) {
		this.brandManageService = brandManageService;
	}

}
