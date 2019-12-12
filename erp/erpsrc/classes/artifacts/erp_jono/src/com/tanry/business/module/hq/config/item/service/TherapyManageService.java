/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 27, 2015 by liyzh
 * Last edited on Jan 27, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.item.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.store.ItemMetaBean;
import logic.store.ItemPriceBean;
import logic.store.TherapyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.ItemMeta;
import pojo.store.ItemPrice;
import pojo.store.Therapy;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.constant.ItemType;
import com.tanry.framework.util.constant.LogType;
import com.tanry.framework.util.constant.PriceType;

public class TherapyManageService {

	private ItemMetaBean itemMetaBean;
	private TherapyBean therapyBean;
	private ItemPriceBean itemPriceBean;

	/**
	 * @param therapyId
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTherapy(String therapyId, String therapyName, String jsonData, Double productPrice, String change)
			throws NoPrivilegeException, SQLException, NoConnection {
		ItemMeta product = itemMetaBean.GetItemById(therapyId);
		String owner = BranchType.RESTAURANT;
		if (ItemType.SEMIS.equals(product.getItemType())) {
			owner = BranchType.CENTRALFACTORY;
		}
		JSONArray arr = JSONArray.fromObject(jsonData);
		List<String> itemIdLst = new ArrayList<String>();
		// 删除所有记录
		therapyBean.deleteExcept(therapyId, itemIdLst);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			Therapy therapy = new Therapy();
			therapy.setTherapyId(therapyId);
			therapy.setTherapyName(therapyName);
			therapy.setTherapyDimension((String) json.get("therapyDimension"));
			String itemId = (String) json.get("itemId");
			therapy.setItemId(itemId);
			therapy.setItemName((String) json.get("itemName"));
			therapy.setItemDimension((String) json.get("itemDimension"));
			therapy.setItemCount(Double.parseDouble(json.get("itemCount") + ""));
			therapy.setItemUsageRate(Double.parseDouble(json.get("itemUsageRate") + ""));

			BigDecimal unitConvertFactor = new BigDecimal(Double.parseDouble(json.get("unitConvertFactor") + ""));
			BigDecimal gCount = new BigDecimal(Double.parseDouble(json.get("itemGrossCount") + ""));
			gCount = gCount.divide(unitConvertFactor, 5, BigDecimal.ROUND_HALF_EVEN);
			therapy.setUnitConvertFactor(unitConvertFactor.doubleValue());
			therapy.setItemGrossCount(gCount.doubleValue());

			therapy.setOwner(owner);
			// if (json.get("therapyId") == null) {
			therapyBean.saveEntity(therapy);
			// } else {
			// therapyBean.updateEntity(therapy);
			// }
			// itemIdLst.add(itemId);
		}
		savePrice(therapyId, productPrice);
		saveOperation("", LogType.UPDATE, "添加/修改[" + therapyId + "]" + therapyName + "成本卡:" + change);
	}

	public void savePrice(String itemId, Double itemPrice) throws NoPrivilegeException, SQLException, NoConnection {
		ItemPrice price = new ItemPrice();
		price.setPriceType(PriceType.PURCHASE);
		price.setItemId(itemId);
		price.setItemPrice(itemPrice);
		itemPriceBean.saveEntity(price);
		// 保存供应商价
		// price.setPriceType(PriceType.SUPPLIER);
		// price.setSupplierId("F00");
		// itemPriceBean.saveEntity(price);
	}

	public void saveOperation(String formId, String logType, String msg) throws NoPrivilegeException, SQLException,
			NoConnection {
		OperateLogUtil.record(formId, logType, msg);
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public TherapyBean getTherapyBean() {
		return therapyBean;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

}
