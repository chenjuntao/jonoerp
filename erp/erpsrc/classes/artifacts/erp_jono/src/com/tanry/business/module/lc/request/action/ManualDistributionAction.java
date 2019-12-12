/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 21, 2014 by liyzh
 * Last edited on Dec 21, 2014 by liyzh
 * 
 * 说明： 物流中心手动生成配送单
 */
package com.tanry.business.module.lc.request.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.lc.ManualDistributionBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ShippingHeader;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.form.FormType;
import com.tanry.business.module.lc.request.service.ManualDistributionService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class ManualDistributionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private ManualDistributionBean manualDistributionBean;
	private ManualDistributionService manualDistributionService;
	private BranchStorageBean branchStorageBean;
	private BranchStorage branchStorage;

	private String formId;
	private Date businessDate;
	private Date formTime;
	private String categoryId;
	private String branchIds;
	private String itemName;
	private String jsonData;
	private ShippingHeader shippingHeader;
	private List<Map> shopLst;
	private String branchType;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = null;
		shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	/**
	 * 根据原料类别查询原料列表及相应的配送单位、单价信息
	 */
	public void listItemMeta() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = manualDistributionBean.queryMaterial(categoryId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			if (item.get("itemUnitPrice") == null) {
				json.put("itemUnitPrice", 0);
			}
			String deliveryUnit = (String) item.get("deliveryUnit");
			json.put("packagingUnit", deliveryUnit);

			BigDecimal unitConvertFactor = BigDecimal.ZERO;
			if (item.get("unitConvertFactor") != null) {
				unitConvertFactor = BigDecimal.valueOf((Double) item.get("unitConvertFactor"));
			}
			json.put("packagingFactor", unitConvertFactor);

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

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = manualDistributionBean.queryItem(itemName);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			if (item.get("itemUnitPrice") == null) {
				json.put("itemUnitPrice", 0);
			}
			String deliveryUnit = (String) item.get("deliveryUnit");
			json.put("packagingUnit", deliveryUnit);

			BigDecimal unitConvertFactor = BigDecimal.ZERO;
			if (item.get("unitConvertFactor") != null) {
				unitConvertFactor = BigDecimal.valueOf((Double) item.get("unitConvertFactor"));
			}
			json.put("packagingFactor", unitConvertFactor);

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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String lcCode = getLoginBranchId();

		businessDate = branchBean.GetBranchById(lcCode).getBusinessDate();// 获取物流中心当前的营业时间
		branchStorage = branchStorageBean.queryMainStore(lcCode);

		Branch lcBranch = branchBean.GetBranchById(lcCode);
		shippingHeader.setProviderId(lcCode);
		shippingHeader.setProvider(lcBranch.getBranchName());

		shippingHeader.setFormType(FormType.DISTRI_UNIFIED);
		shippingHeader.setFormTime(businessDate);
		shippingHeader.setOutStorage(branchStorage.getStorageName());
		shippingHeader.setOutStorageId(branchStorage.getStorageId());
		shippingHeader.setFormMakerId(getLoginUserId());
		shippingHeader.setFormMaker(getLoginUsername());

		List<String> sidLst = manualDistributionService.doSave(branchIds, shippingHeader, getLoginUserId(), jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("sidLst", sidLst);
		try {
			this.outJS(result.toString());
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

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setManualDistributionBean(ManualDistributionBean manualDistributionBean) {
		this.manualDistributionBean = manualDistributionBean;
	}

	public void setManualDistributionService(ManualDistributionService manualDistributionService) {
		this.manualDistributionService = manualDistributionService;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public BranchStorage getBranchStorage() {
		return branchStorage;
	}

	public void setBranchStorage(BranchStorage branchStorage) {
		this.branchStorage = branchStorage;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

}
