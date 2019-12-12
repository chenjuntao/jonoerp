/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 16, 2015 by liyzh
 * Last edited on Jul 16, 2015 by liyzh
 * 
 * 说明： 多品牌出品信息设置
 */
package com.tanry.business.module.hq.config.brand.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.BrandProductBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.BrandProduct;

import com.tanry.framework.acl.NoPrivilegeException;

public class BrandProductService {

	private BrandProductBean brandProductBean;

	/**
	 * @param brandId
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSave(String brandId, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		List<String> itemIdLst = new ArrayList<String>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			BrandProduct product = new BrandProduct();
			String itemId = (String) json.get("itemId");
			product.setBrandId(brandId);
			product.setItemId(itemId);

			if (json.get("brandId") == null) {
				brandProductBean.saveEntity(product);
			}
			itemIdLst.add(itemId);
		}
		// 删除除此之外的其它记录
		brandProductBean.deleteExcept(brandId, itemIdLst);
	}

	public void setBrandProductBean(BrandProductBean brandProductBean) {
		this.brandProductBean = brandProductBean;
	}

}
