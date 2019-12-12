/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 29, 2014 by liyzh
 * Last edited on Aug 29, 2014 by liyzh
 * 
 * 说明： 要货单模板管理
 */
package com.tanry.business.form.template;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ArrangmentDetailBean;
import logic.form.OperationVersionBean;
import logic.form.PurchasingDetailBean;
import logic.form.TemplateItemBean;
import logic.form.TemplateMetaBean;
import logic.store.BranchBean;
import logic.store.ItemPriceBean;
import logic.store.SafeStorageBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.TemplateItem;
import pojo.form.TemplateMeta;
import pojo.store.Branch;
import pojo.store.DeliveryRegion;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.PriceType;
import com.tanry.framework.util.enums.BranchTypeEnum;

import dao.store.DeliveryRegionDao;

@SuppressWarnings("rawtypes")
public class TemplateManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private TemplateManageService outTemplateManageService;
	private BranchBean branchBean;
	private DeliveryRegionDao deliveryRegionDao;
	private TemplateMetaBean templateMetaBean;
	private TemplateItemBean templateItemBean;
	private ItemPriceBean itemPriceBean;
	private StorageBean storageBean;

	private String branchId;
	private String branchName;
	private String branchType;

	private String templateId;
	private String templateType;
	private String itemName;

	private String ids;

	private String jsonData;
	private List<Map> shopLst;

	private TemplateMeta templateMeta;

	private ArrangmentDetailBean arrangmentDetailBean;

	private OperationVersionBean operationVersionBean;

	private List<Map> regionLst;

	private String isSord;

	public void setArrangmentDetailBean(ArrangmentDetailBean arrangmentDetailBean) {
		this.arrangmentDetailBean = arrangmentDetailBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
	}

	public void setSafeStorageBean(SafeStorageBean safeStorageBean) {
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		return SUCCESS;
	}

	public String addView() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(templateId)) {
			templateMeta = templateMetaBean.queryById(templateId);

			String branchId = templateMeta.getBranchId();
			Branch branchObj = branchBean.GetBranchById(branchId);
			branchName = branchObj.getBranchName();
		}
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(templateId)) {
			templateMeta = templateMetaBean.queryById(templateId);

			String branchId = templateMeta.getBranchId();
			Branch branchObj = branchBean.GetBranchById(branchId);
			branchName = branchObj.getBranchName();
		}
		return SUCCESS;
	}

	public String copyView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(templateId)) {
			templateMeta = templateMetaBean.queryById(templateId);

			List<DeliveryRegion> drs = deliveryRegionDao.queryRegionByLcId(getLoginBranchId());
			regionLst = new ArrayList<Map>();
			for (int i = 0; i < drs.size(); i++) {
				Map<String, Object> region = new HashMap<String, Object>();
				region.put("id", drs.get(i).getRegionId());
				region.put("name", drs.get(i).getRegionName());
				region.put("deliveryCycle", drs.get(i).getDeliveryCycle());
				regionLst.add(region);
			}
		}

		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		return SUCCESS;
	}

	public String pickModelView() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
		return SUCCESS;
	}

	public String saveModelView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = templateMetaBean.count(branchId, templateType, itemName);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<TemplateMeta> metaLst = templateMetaBean.query(branchId, templateType, itemName);
			int rownumber = getStart();
			for (TemplateMeta meta : metaLst) {
				JSONObject json = JSONObject.fromObject(meta);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<TemplateItem> itemLst = templateItemBean.query(templateId);
		int rownumber = 1;
		for (TemplateItem item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			Double distributePrice = itemPriceBean.getItemPrice(item.getItemId(), PriceType.BENCHMARK);
			json.put("itemUnitPrice", distributePrice);

			String itemSpecification = item.getItemSpecification();
			if (itemSpecification != null) {
				itemSpecification = itemSpecification.trim();
			}
			json.put("itemSpecification", itemSpecification);

			item.getItemId();
			if (!TextUtil.isEmpty(branchId)) {
				// Double itemCount = storageBean.getItemCount(itemId,
				// branchId);// 库存量
				// json.put("inventory", itemCount);
			}
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryItemByCF() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<TemplateItem> itemLst = templateItemBean.query(templateId);
		int rownumber = 1;
		for (TemplateItem item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			Double distributePrice = itemPriceBean.getItemPrice(item.getItemId(), PriceType.BENCHMARK);
			json.put("itemUnitPrice", distributePrice);

			String itemSpecification = item.getItemSpecification();
			if (itemSpecification != null) {
				itemSpecification = itemSpecification.trim();
			}
			json.put("itemSpecification", itemSpecification);

			String itemId = item.getItemId();
			if (!TextUtil.isEmpty(branchId)) {
				// 库存量
				Double inventory = storageBean.getItemCount(itemId, branchId);
				inventory = inventory != null ? inventory : 0;
				json.put("inventory", inventory);

				Double onTheWay = 0.0;
				json.put("onTheWay", onTheWay);

				Double safeStorage = 1.0;
				json.put("safeStorage", safeStorage);

				// 计划生产量 对应原料已审核的生产工单
				// 测试数据 暂和库存量一致
				Double itemCount = arrangmentDetailBean.countByItemId(itemId);
				itemCount = itemCount != null ? itemCount : 0;
				json.put("itemCount", itemCount);

				// 实际生产量
				Double realCount = inventory + onTheWay - safeStorage - itemCount;
				realCount = realCount < 0 ? 0 : realCount;
				json.put("realCount", realCount);
			}
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
		String myTemplateId = null;

		if (!TextUtil.isEmpty(templateId)) {// update
			myTemplateId = templateId;
		} else {
			// create
			String branchId = templateMeta.getBranchId();
			Date settleTime = branchBean.GetBranchById(branchId).getBusinessDate();
			String tType = templateMeta.getTemplateType();
			String prefix = "YHMB";
			if (FormConstant.OUTER_TEMPLATE.equals(tType)) {
				prefix = "WBMB";
			} else if (FormConstant.CHECK_TEMPLATE.equals(tType)) {
				prefix = "PDMB";
			} else if (FormConstant.ESTIMATE_TEMPLATE.equals(tType)) {
				prefix = "YDMB";
			}

			String formBody = FormUtil.genFormIdBody(prefix, branchId, settleTime);

			Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
			templateId = formBody + FormUtil.formatSerial(newSerial);
		}

		templateMeta.setTemplateId(templateId);
		outTemplateManageService.createTemplate(myTemplateId, templateMeta, jsonData, isSord);

		// 记录版本与操作信息

		myTemplateId = templateId;

		JSONObject result = new JSONObject();

		result.put("templateId", myTemplateId);
		result.put("msg", "ok");

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doCopy() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();
		Date settleTime = branchBean.GetBranchById(branchId).getBusinessDate();
		Date now = new Date();
		// 自动生成，拼音缩写+部门编号+日期+流水号
		String newTempId = "YHMB" + branchId + DateTimeUtil.getDateTime(settleTime, "yyyyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss");
		templateMeta.setTemplateId(newTempId);
		outTemplateManageService.copyTemplate(templateId, templateMeta);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		outTemplateManageService.deleteTemplate(ids);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public TemplateMeta getTemplateMeta() {
		return templateMeta;
	}

	public void setTemplateMeta(TemplateMeta tempateMeta) {
		this.templateMeta = tempateMeta;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setOutTemplateManageService(TemplateManageService outTemplateManageService) {
		this.outTemplateManageService = outTemplateManageService;
	}

	public void setTemplateMetaBean(TemplateMetaBean templateMetaBean) {
		this.templateMetaBean = templateMetaBean;
	}

	public void setTemplateItemBean(TemplateItemBean templateItemBean) {
		this.templateItemBean = templateItemBean;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
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

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public List<Map> getRegionLst() {
		return regionLst;
	}

	public void setDeliveryRegionDao(DeliveryRegionDao deliveryRegionDao) {
		this.deliveryRegionDao = deliveryRegionDao;
	}

	public void setIsSord(String isSord) {
		this.isSord = isSord;
	}

}
