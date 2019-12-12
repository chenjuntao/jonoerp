/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 20, 2014 by liyzh
 * Last edited on Nov 20, 2014 by liyzh
 * 
 * 说明： 物流中心物资清单查询
 */
package com.tanry.business.module.lc.basic.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.lc.MaterialListBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class MaterialListAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private MaterialListBean materialListBean;

	private String branchId;
	private String supplierId;
	private String itemName;

	private Date startDate;
	private Date endDate;

	private String formId;

	private List<Map> shopLst;
	private List<Map> supplierLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		supplierLst = branchBean.listShopByType(BranchTypeEnum.S_Center);// 供应商和中央工厂
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = materialListBean.query(startDate, endDate, itemName.trim(), branchId, supplierId);
		int rownumber = 1;
		JSONArray arr = new JSONArray();
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public List<Map> getSupplierLst() {
		return supplierLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setMaterialListBean(MaterialListBean materialListBean) {
		this.materialListBean = materialListBean;
	}

}
