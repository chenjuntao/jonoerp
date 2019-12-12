/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 25, 2014 by liyzh
 * Last edited on Nov 25, 2014 by liyzh
 * 
 * 说明： 查询库存
 */
package com.tanry.business.store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.ItemMetaBean;
import logic.store.MRPQueryBean;
import net.sf.json.JSONArray;
import pojo.store.ItemMeta;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchType;

public class MRPQueryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String branchType;
	private String itemId;
	private String mrpType;

	private MRPQueryBean mRPQueryBean;
	private ItemMetaBean itemMetaBean;

	private String itemType;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		itemMeta = itemMetaBean.GetItemById(itemId);
		return SUCCESS;
	}

	private ItemMeta itemMeta;

	/**
	 * 根据多个类别查询原材料及半成品信息（含库存、供应商）
	 */
	public void query() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();
		List<Map> itemLst = new ArrayList<Map>();

		if (BranchType.LOGISTICSCENTER.equals(branchType)) {
			if ("onTheWay".equals(mrpType)) {
				if ("RAW".equals(itemType)) {
					itemLst = mRPQueryBean.queryLcRawOnTheWay(itemId, branchId);
				} else {
					itemLst = mRPQueryBean.queryLcSemiOnTheWay(itemId, branchId);
				}
			} else {
				itemLst = mRPQueryBean.queryLcAllocation(itemId, branchId);
			}
		} else {
			if ("onTheWay".equals(mrpType)) {
				itemLst = mRPQueryBean.queryCfOnTheWay(itemId, branchId);
			} else {
				itemLst = mRPQueryBean.queryCfAllocation(itemId, branchId);
			}
		}

		try {
			this.outJS(JSONArray.fromObject(itemLst).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setmRPQueryBean(MRPQueryBean mRPQueryBean) {
		this.mRPQueryBean = mRPQueryBean;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getMrpType() {
		return mrpType;
	}

	public void setMrpType(String mrpType) {
		this.mrpType = mrpType;
	}

	public ItemMeta getItemMeta() {
		return itemMeta;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}
