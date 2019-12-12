/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 5, 2015 by liyzh
 * Last edited on Mar 5, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.price.service;

import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.ItemPriceBean;
import logic.store.PgroupBranchBean;
import logic.store.PriceGroupBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.ItemPrice;
import pojo.store.PgroupBranch;
import pojo.store.PriceGroup;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class PriceGroupService {

	private PriceGroupBean priceGroupBean;
	private PgroupBranchBean pgroupBranchBean;
	private ItemPriceBean itemPriceBean;

	/**
	 * @param oldGroupId
	 *            老的编码，用于修改
	 * @param priceGroup
	 * @param refGroupId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void savePriceGroup(String oldGroupId, PriceGroup priceGroup, String refGroupId)
			throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(oldGroupId)) {
			priceGroupBean.updateEntity(oldGroupId, priceGroup);
		} else {
			priceGroupBean.saveEntity(priceGroup);
			String newGroupId = priceGroup.getPriceGroupId();
			List<ItemPrice> priceLst = itemPriceBean.query(refGroupId);
			for (ItemPrice price : priceLst) {// 新建价格分组时选择一个已经存在的价格分组，用于初始化相应的原料价格
				price.setPriceType(newGroupId);
				itemPriceBean.saveEntity(price);
			}
		}
	}

	/**
	 * 删除售卖价格组
	 * 
	 * @param priceGroupIds
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deletePriceGroup(String priceGroupIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] idArr = priceGroupIds.split(",");
		for (String priceGroupId : idArr) {
			priceGroupBean.deleteEntity(priceGroupId);
			itemPriceBean.deletePriceGroup(priceGroupId);
		}
	}

	/**
	 * 保存出品相应的分组价格
	 * 
	 * @param pgroupId
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void savePrice(String pgroupId, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray productArr = JSONArray.fromObject(jsonData);
		for (Object obj : productArr) {
			JSONObject product = (JSONObject) obj;
			ItemPrice price = new ItemPrice();
			price.setItemId(product.getString("itemId"));
			price.setItemPrice(product.getDouble("itemPrice"));
			price.setPriceType(pgroupId);
			itemPriceBean.saveEntity(price);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setBranch(String priceGroupId, String branchIds) throws NoPrivilegeException, SQLException,
			NoConnection {
		String type;
		if (priceGroupId.indexOf("_") != -1)
			type = priceGroupId.substring(0, priceGroupId.indexOf("_"));
		else
			type = priceGroupId;
		String[] branchArr = branchIds.split(",");
		if (!TextUtil.isEmpty(branchIds)) {
			for (String branchId : branchArr) {
				PgroupBranch relation = new PgroupBranch();
				relation.setPriceGroupId(priceGroupId);
				relation.setBranchId(branchId);
				boolean exist = pgroupBranchBean.exist(priceGroupId, branchId);
				if (!exist) {
					pgroupBranchBean.saveEntity(relation);
				}
			}
		}
		// 删除除此之外的其它记录
		if (!TextUtil.isEmpty(branchIds)) {
			pgroupBranchBean.updateExcept(priceGroupId, branchIds, type);
		} else {
			pgroupBranchBean.deleteAll(priceGroupId, type);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSave(PriceGroup priceGroup, String priceGroupId) throws NoPrivilegeException, SQLException,
			NoConnection {
		if (!TextUtil.isEmpty(priceGroupId)) {
			priceGroup.setPriceGroupType("LC");
			priceGroupBean.updateEntity(priceGroupId, priceGroup);
		} else {
			priceGroup.setPriceGroupType("LC");
			priceGroupBean.saveEntity(priceGroup);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JSONObject doDelete(PriceGroup priceGroup, String priceGroupId) throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONObject result = new JSONObject();
		String[] ids = priceGroupId.split(",");
		int tag = 0;
		String defaultId = "";
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].indexOf("JOIN") != -1)
				defaultId = "JOIN";
			if (ids[i].indexOf("RETAIL") != -1)
				defaultId = "RETAIL";
			if (ids[i].indexOf("BENCHMARK") != -1)
				defaultId = "BENCHMARK";
			priceGroupBean.deleteAll(ids[i], defaultId);
		}
		for (int i = 0; i < ids.length; i++) {
			if (priceGroupBean.deletePriceGroupSoOn(ids[i]) != 1) {
				tag = 1;
				break;
			}
		}

		if (tag == 1) {
			result.put("msg", "fail");
		} else {
			result.put("msg", "ok");
		}

		return result;
	}

	public void setPriceGroupBean(PriceGroupBean priceGroupBean) {
		this.priceGroupBean = priceGroupBean;
	}

	public void setPgroupBranchBean(PgroupBranchBean pgroupBranchBean) {
		this.pgroupBranchBean = pgroupBranchBean;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

}
