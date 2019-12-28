/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 19, 2014 by liyzh
 * Last edited on Dec 19, 2014 by liyzh
 * 
 * 说明： 物流中心手动生成采购单
 */
package com.tanry.business.module.lc.request.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.lc.ManualPurchaseBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.PurchasingHeader;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.business.module.lc.request.service.ManualPurchaseService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class ManualPurchaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchStorageBean branchStorageBean;
	private ManualPurchaseBean manualPurchaseBean;
	private ManualPurchaseService manualPurchaseService;

	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private String formId;
	private Date businessDate;
	private Date formTime;
	private String categoryId;

	private String jsonData;
	private PurchasingHeader commonHeader;
	private List<BranchStorage> storeLst;
	private String itemName;
	private String supplierId;
	/** 上传的文件 */
	private File attachment;
	private String branchName;
	private List<Map<String, Object>> billLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);

		List<Map<String, Object>> providerLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> providerMap = new HashMap<String, List<Map<String, Object>>>();
		for (Object obj : arr) {// 根据供应商分组
			JSONObject item = (JSONObject) obj;
			String providerId = (String) item.get("providerId");
			String key = providerId;
			if (providerMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", providerId);
				bill.put("provider", item.get("provider"));
				bill.put("deliveryType", FormConstant.UNIFIED_DELIVERY);
				bill.put("key", key);
				providerLst.add(bill);
				providerMap.put(key, new ArrayList<Map<String, Object>>());
			}
			providerMap.get(key).add(item);
		}
		for (Map<String, Object> bill : providerLst) {
			bill.put("details", providerMap.get(bill.get("key")));
		}
		billLst = providerLst;

		String lcCode = getLoginBranchId();
		formTime = branchBean.GetBranchById(lcCode).getBusinessDate();// 获取物流中心当前的营业时间
		commonHeader = new PurchasingHeader();
		commonHeader.setFormId(formId);
		commonHeader.setRequesterId(lcCode);
		commonHeader.setFormMakerId(getLoginUserId());
		commonHeader.setFormMaker(getLoginUsername());
		String branchType = getLoginBranchType();
		branchName = BranchType.getBrandWord(branchType);
		storeLst = branchStorageBean.query(lcCode, branchType);
		return SUCCESS;
	}

	/**
	 * 根据原料类别查询原料列表及相应的供应商、单价信息
	 */
	public void listItemMeta() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = manualPurchaseBean.queryMaterial(getLoginBranchId(), categoryId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			if (item.get("itemSpecification") == null) {
				json.put("itemSpecification", "");
			}
			if (item.get("itemUnitPrice") == null) {
				json.put("itemUnitPrice", 0);
			}
			if (item.get("receivePrice") == null) {
				json.put("receivePrice", 0);
			}
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

	/* 根据物料编码，名称查询 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = !TextUtil.isEmpty(supplierId) ? manualPurchaseBean.queryBySupplier(getLoginBranchId(),
				supplierId, itemName) : manualPurchaseBean.queryItem(getLoginBranchId(), itemName);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			if (item.get("itemSpecification") == null) {
				json.put("itemSpecification", "");
			}
			if (item.get("itemUnitPrice") == null) {
				json.put("itemUnitPrice", 0);
			}

			String itemId = item.get("itemId").toString();

			String providerId = null;
			try {
				providerId = item.get("providerId").toString();
			} catch (Exception e) {
				providerId = "";
			}

			json.put("id", itemId + providerId);
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

	public void createPurchaseBill() throws NoPrivilegeException, SQLException, NoConnection {
		commonHeader.setRequesterId(getLoginBranchId());
		List<String> idLst = manualPurchaseService.createPurchaseBill(commonHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("idLst", idLst);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析导入文件
	 */
	public void doImport() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = manualPurchaseService.doImport(attachment);
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setManualPurchaseBean(ManualPurchaseBean manualPurchaseBean) {
		this.manualPurchaseBean = manualPurchaseBean;
	}

	public void setManualPurchaseService(ManualPurchaseService manualPurchaseService) {
		this.manualPurchaseService = manualPurchaseService;
	}

	public List<Map<String, Object>> getBillLst() {
		return billLst;
	}

	public void setBillLst(List<Map<String, Object>> billLst) {
		this.billLst = billLst;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public PurchasingHeader getCommonHeader() {
		return commonHeader;
	}

	public void setCommonHeader(PurchasingHeader commonHeader) {
		this.commonHeader = commonHeader;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
