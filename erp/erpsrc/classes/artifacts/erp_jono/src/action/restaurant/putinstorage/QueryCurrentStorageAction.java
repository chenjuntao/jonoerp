/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 12, 2014 by cjt
 * Last edited on Sep 12, 2014 by cjt
 * 
 * 说明： 查询当前的原料库存
 */
package action.restaurant.putinstorage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class QueryCurrentStorageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private List<Map> shopList;
	private String shopC;

	private String jsonData;

	private List<BranchStorage> storageList;
	private String branchType;

	private BranchStorageBean branchStorageBean;

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopList = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));

		if (shopList.size() > 0) {
			storageList = branchStorageBean.query(shopList.get(0).get("code").toString(), branchType);
		}
		return SUCCESS;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public List<Map> getShopList() {
		return shopList;
	}

	public String getShopC() {
		return shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}
}
