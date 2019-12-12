/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 15, 2015 by liyzh
 * Last edited on Jul 15, 2015 by liyzh
 * 
 * 说明： 多品牌出品信息设置
 */
package com.tanry.business.module.hq.config.brand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BrandProductBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.brand.service.BrandProductService;
import com.tanry.framework.acl.NoPrivilegeException;

public class BrandProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BrandProductBean brandProductBean;
	private BrandProductService brandProductService;

	private String brandId;
	private String jsonData;

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> productLst = brandProductBean.query(brandId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map product : productLst) {
			JSONObject jObject = JSONObject.fromObject(product);
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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		brandProductService.doSave(brandId, jsonData);

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

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBrandProductBean(BrandProductBean brandProductBean) {
		this.brandProductBean = brandProductBean;
	}

	public void setBrandProductService(BrandProductService brandProductService) {
		this.brandProductService = brandProductService;
	}

}
