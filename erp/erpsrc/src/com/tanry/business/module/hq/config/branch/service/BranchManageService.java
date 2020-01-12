/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 4, 2015 by liyzh
 * Last edited on Feb 4, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.branch.service;

import java.sql.SQLException;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.DeliveryRegionBean;
import logic.store.PgroupBranchBean;
import logic.store.SupplierBranchItemBean;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.Branch;
import pojo.store.BranchStorage;
import pojo.store.DeliveryRegionBranch;
import pojo.store.PgroupBranch;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.constant.PriceType;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class BranchManageService {
	private BranchStorageBean branchStorageBean;
	private BranchBean branchBean;
	private PgroupBranchBean pgroupBranchBean;
	private DeliveryRegionBean deliveryRegionBean;
	private SupplierBranchItemBean supplierBranchItemBean;

	/**
	 * 新建餐厅、加盟店、外部订货方
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveRestaurant(Branch branch) throws NoPrivilegeException, SQLException, NoConnection {
		String DbName = branchBean.getSessionCom();

		saveRestaurant(branch, DbName);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateRestaurant(String branchId, Branch branch) throws NoPrivilegeException, SQLException,
			NoConnection {
		branchBean.updateEntity(branchId, branch);
		deliveryRegionBean.updateRegionBranch(branchId, new DeliveryRegionBranch(branch.getRegionId(), branchId));
	}

	/**
	 * 新建餐厅、加盟店、外部订货方
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveRestaurant(Branch branch, String DbName) throws NoPrivilegeException, SQLException, NoConnection {

		branchBean.saveEntity(branch, DbName);

		String branchId = branch.getBranchId();
		String branchType = branch.getBranchType();

		// 门店相关的配送区域
		DeliveryRegionBranch groupRelation = new DeliveryRegionBranch();
		groupRelation.setBranchId(branchId);
		groupRelation.setRegionId(branch.getRegionId());
		deliveryRegionBean.saveRegionBranch(groupRelation, DbName);

		if (branchType.equals(BranchTypeEnum.RESTAURANT.toString())) {
			// 门店默认的仓库
			BranchStorage branchStorage = new BranchStorage();
			branchStorage.setStorageId(branchId); // 门店默认只有一个仓库，仓库编码与门店编码相同
			branchStorage.setStorageName(branch.getBranchName() + "仓库");
			branchStorage.setPriority(0);
			branchStorage.setBranchId(branch.getBranchId());
			branchStorageBean.saveEntity(branchStorage, DbName);
		}

		// 门店默认的配送价分组和门店默认的售卖价分组
		String priceType = "";
		if (branchType.equals(BranchTypeEnum.RESTAURANT.toString())) {
			priceType = PriceType.BENCHMARK;
		} else if (branchType.equals(BranchTypeEnum.FRANCHISEE.toString())) {
			priceType = PriceType.WHOLESALE;
		} else if (branchType.equals(BranchTypeEnum.OUTERORDER.toString())) {
			priceType = PriceType.JOIN;
		}
		if (!priceType.equals("")) {
			PgroupBranch priceGroup = new PgroupBranch();
			priceGroup.setPriceGroupId(priceType);
			priceGroup.setBranchId(branchId);
			pgroupBranchBean.saveEntity(priceGroup, DbName);

			if (BranchTypeEnum.RESTAURANT.toString().equals(branch.getBranchType())) {
				priceGroup.setPriceGroupId(PriceType.SALE);
				pgroupBranchBean.saveEntity(priceGroup, DbName);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBranch(String branchType, String branchIds) throws NoPrivilegeException, SQLException,
			NoConnection {
		String[] branchArr = branchIds.split(",");
		for (String branchId : branchArr) {
			branchBean.deleteEntity(branchId);
			deliveryRegionBean.deleteRegionBranch(branchId);
			if (BranchType.SUPPLIER.equals(branchType)) {
				supplierBranchItemBean.deleteEntity(branchId);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doDisable(String branchId, Integer enabled) throws NoPrivilegeException, SQLException, NoConnection {
		supplierBranchItemBean.deleteByBranchId(branchId);
		branchBean.enableBranch(branchId, enabled);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doDisableWithSupplier(String branchId, Integer enabled) throws NoPrivilegeException, SQLException,
			NoConnection {
		supplierBranchItemBean.deleteBySupplierId(branchId);
		branchBean.enableBranch(branchId, enabled);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteRestaurant(String branchIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] branchArr = branchIds.split(",");
		for (String branchId : branchArr) {
			branchBean.deleteEntity(branchId);
			deliveryRegionBean.deleteRegionBranch(branchId);
		}
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setDeliveryRegionBean(DeliveryRegionBean deliveryRegionBean) {
		this.deliveryRegionBean = deliveryRegionBean;
	}

	public void setSupplierBranchItemBean(SupplierBranchItemBean supplierBranchItemBean) {
		this.supplierBranchItemBean = supplierBranchItemBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setPgroupBranchBean(PgroupBranchBean pgroupBranchBean) {
		this.pgroupBranchBean = pgroupBranchBean;
	}
}
