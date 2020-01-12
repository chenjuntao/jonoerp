/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 20, 2015 by liyzh
 * Last edited on Jul 20, 2015 by liyzh
 * 
 * 说明： 多品牌多价格组设置价格
 */
package com.tanry.business.module.hq.config.price;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.hq.PriceGroupQueryBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.vo.ItemVO;
import com.tanry.business.module.hq.config.price.service.PriceGroupService;
import com.tanry.framework.acl.NoPrivilegeException;

public class PriceManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private PriceGroupQueryBean priceGroupQueryBean;
	private PriceGroupService priceGroupService;

	private String priceGroupId;

	private String jsonData;

	/**
	 * 根据价格组编号查询相应的出品信息及分组价格
	 */
	public void queryProduct() throws NoPrivilegeException, SQLException, NoConnection {
		List<ItemVO> productLst = priceGroupQueryBean.queryProduct(priceGroupId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (ItemVO product : productLst) {
			JSONObject jObject = JSONObject.fromObject(product);
			Double groupPrice = product.getGroupPrice();
			Double salePrice = product.getSalePrice();
			if (groupPrice != null) {
				jObject.put("itemPrice", groupPrice);
			} else {// 如果分组价格为空，则使用原始的售卖价进行初始化
				if (salePrice == null) {
					salePrice = 0.0;
				}
				jObject.put("itemPrice", salePrice);
			}
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

	/**
	 * 保存出品相应的分组价格
	 */
	public void savePrice() throws NoPrivilegeException, SQLException, NoConnection {
		priceGroupService.savePrice(priceGroupId, jsonData);

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

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setPriceGroupService(PriceGroupService priceGroupService) {
		this.priceGroupService = priceGroupService;
	}

	public void setPriceGroupQueryBean(PriceGroupQueryBean priceGroupQueryBean) {
		this.priceGroupQueryBean = priceGroupQueryBean;
	}

}
