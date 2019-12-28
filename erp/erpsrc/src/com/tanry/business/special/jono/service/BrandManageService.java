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
 * 说明： 保存品牌，及品牌与门店的关联关系等数据
 */
package com.tanry.business.special.jono.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.BrandBean;
import logic.store.BrandBranchBean;
import logic.store.ItemPriceBean;
import logic.store.PgroupBranchBean;
import logic.store.PriceGroupBean;
import pojo.store.Brand;
import pojo.store.BrandBranch;
import pojo.store.ItemPrice;
import pojo.store.PgroupBranch;
import pojo.store.PriceGroup;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class BrandManageService {

	private BrandBean brandBean;
	private BrandBranchBean brandBranchBean;
	private PriceGroupBean priceGroupBean;
	private PgroupBranchBean pgroupBranchBean;
	private ItemPriceBean itemPriceBean;

	/**
	 * 保存品牌记录的同时，保存品牌所对应的默认价格分组
	 * @param brandId
	 * @param branchIds
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveBrand(String brandId, Brand brand, String refBrandId) throws NoPrivilegeException, SQLException,
			NoConnection {
		String pgroupId = brand.getBrandId() + "_defalut";
		String pgroupName = brand.getBrandName() + "默认价";
		if (!TextUtil.isEmpty(brandId)) {
			brandBean.updateEntity(brandId, brand);

			priceGroupBean.updateName(pgroupId, pgroupName);
		} else {
			brandBean.saveEntity(brand);

			PriceGroup priceGroup = new PriceGroup();
			priceGroup.setPriceGroupId(pgroupId);
			priceGroup.setPriceGroupName(pgroupName);
			priceGroup.setPriceGroupType("BRAND");
			priceGroup.setOwner(brandId);
			priceGroupBean.saveEntity(priceGroup);

			String newGroupId = pgroupId;
			String refGroupId = refBrandId + "_defalut";
			List<ItemPrice> priceLst = itemPriceBean.query(refGroupId);
			for (ItemPrice price : priceLst) {// 新建价格分组时选择一个已经存在的价格分组，用于初始化相应的原料价格
				price.setPriceType(newGroupId);
				itemPriceBean.saveEntity(price);
			}
		}
	}

	/**
	 * 设置餐厅与品牌关系的同时，初始化餐厅与品牌所对应的默认价格分组的关系（jono个性化需求）
	 * @param brandId
	 * @param branchIds
	 */
	public void setBranch(String brandId, String branchIds) throws NoPrivilegeException, SQLException, NoConnection {
		String pgroupId = brandId + "_defalut";

		String[] branchArr = branchIds.split(",");
		for (String branchId : branchArr) {
			BrandBranch relation = new BrandBranch();
			relation.setBrandId(brandId);
			relation.setBranchId(branchId);
			boolean exist = brandBranchBean.exist(brandId, branchId);
			if (!exist) {
				brandBranchBean.saveEntity(relation);
			}

			// 默认与品牌的门店对应关系保持一致
			PgroupBranch pRelation = new PgroupBranch();
			pRelation.setPriceGroupId(pgroupId);
			pRelation.setBranchId(branchId);
			exist = pgroupBranchBean.exist(pgroupId, branchId);
			if (!exist) {
				pgroupBranchBean.saveEntity(pRelation);
			}
		}
		// 删除除此之外的其它记录
		brandBranchBean.deleteExcept(brandId, branchIds);

		pgroupBranchBean.deleteExcept(pgroupId, branchIds);
	}

	/**
	 * 删除品牌记录的同时，删除品牌所对应的默认价格分组（及其它下属价格等数据）
	 * @param brandIds
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBrand(String brandIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] brandArr = brandIds.split(",");
		for (String brandId : brandArr) {
			brandBean.deleteEntity(brandId);
			brandBranchBean.deleteByBrand(brandId);
		}
	}

	public void setBrandBranchBean(BrandBranchBean brandBranchBean) {
		this.brandBranchBean = brandBranchBean;
	}

	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
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
