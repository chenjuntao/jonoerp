package com.tanry.business.module.cf.workShop.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.FactoryWorkShopBean;
import pojo.store.FactoryWorkShop;

import com.tanry.framework.acl.NoPrivilegeException;

public class WorkShopManageService {
	private FactoryWorkShopBean factoryWorkShopBean;
	private FactoryWorkShop factoryWorkShop;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(FactoryWorkShop factoryWorkShop) throws NoPrivilegeException, SQLException, NoConnection {
		factoryWorkShopBean.updateEntity(factoryWorkShop);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insert(FactoryWorkShop factoryWorkShop) throws NoPrivilegeException, SQLException, NoConnection {
		factoryWorkShopBean.saveEntity(factoryWorkShop);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int del(String workOrderId) throws NoPrivilegeException, SQLException, NoConnection {
		return factoryWorkShopBean.deleteEntity(workOrderId);
	}

	public FactoryWorkShopBean getFactoryWorkShopBean() {
		return factoryWorkShopBean;
	}

	public void setMain(FactoryWorkShop factoryWorkShop) throws NoPrivilegeException, SQLException, NoConnection {
		factoryWorkShopBean.setMain(factoryWorkShop);

	}

	public void setFactoryWorkShopBean(FactoryWorkShopBean factoryWorkShopBean) {
		this.factoryWorkShopBean = factoryWorkShopBean;
	}

	public FactoryWorkShop getFactoryWorkShop() {
		return factoryWorkShop;
	}

	public void setFactoryWorkShop(FactoryWorkShop factoryWorkShop) {
		this.factoryWorkShop = factoryWorkShop;
	}

}
