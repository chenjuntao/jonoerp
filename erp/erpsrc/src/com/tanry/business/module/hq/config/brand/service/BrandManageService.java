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
package com.tanry.business.module.hq.config.brand.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.BrandBean;
import logic.store.BrandBranchBean;
import pojo.store.BrandBranch;

import com.tanry.framework.acl.NoPrivilegeException;

public class BrandManageService {

	private BrandBean brandBean;
	private BrandBranchBean brandBranchBean;

	/**
	 * @param brandId
	 * @param branchIds
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setBranch(String brandId, String branchIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] branchArr = branchIds.split(",");
		for (String branchId : branchArr) {
			BrandBranch relation = new BrandBranch();
			relation.setBrandId(brandId);
			relation.setBranchId(branchId);
			boolean exist = brandBranchBean.exist(brandId, branchId);
			if (!exist) {
				brandBranchBean.saveEntity(relation);
			}
		}
		// 删除除此之外的其它记录
		brandBranchBean.deleteExcept(brandId, branchIds);
	}

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

}
