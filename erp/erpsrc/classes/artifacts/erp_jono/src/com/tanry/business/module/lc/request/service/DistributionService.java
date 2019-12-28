/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 5, 2014 by liyzh
 * Last edited on Sep 5, 2014 by liyzh
 * 
 * 说明： 配送入库管理
 */
package com.tanry.business.module.lc.request.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PurchasingHeaderBean;
import logic.form.RequestHeaderBean;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.form.StorageInOutBean;
import logic.module.lc.CreateDistributionBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.RequestHeader;
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import pojo.form.StorageInOut;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormType;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.LogType;

public class DistributionService {

	private ShippingHeaderBean shippingHeaderBean;
	private ShippingDetailBean shippingDetailBean;
	private RequestHeaderBean requestHeaderBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private CreateDistributionBean createDistributionBean;
	private FormStatusBean formStatusBean;
	private StorageBean storageBean;
	private BranchStorageBean branchStorageBean;
	private StorageInOutBean storageInOutBean;

	private OperationVersionBean operationVersionBean;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	/**
	 * 生成配送单
	 * 
	 * @param header
	 * @param jsonData
	 */
	public String createBill(String userId, String formRefId, ShippingHeader header, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formType = header.getFormType();
		String prefix = "PST";
		String msg = "新增配送单";
		String mainStorageId = branchStorageBean.queryMainStore(header.getProviderId()).getStorageId();
		Date businessDate = branchBean.GetBranchById(header.getProviderId()).getBusinessDate();
		if (FormType.DISTRI_OUTER.equals(formType.toUpperCase())) {
			prefix = "CH";
		} else if (FormType.DISTRI_PRODUCT.equals(formType.toUpperCase())) {
			prefix = "CK";
		}

		String formBody = FormUtil.genFormIdBody(prefix, header.getRequesterId(), header.getFormTime());

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);

		header.setFormId(formId);

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			if (json.containsKey("itemCategory")) {
				detail.setItemCategory(json.getString("itemCategory"));
			}
			detail.setItemDimension(json.getString("itemDimension"));
			Object itemSpecification = json.get("itemSpecification");
			if (!JSONNull.getInstance().equals(itemSpecification)) {
				detail.setItemSpecification((String) itemSpecification);
			}
			detail.setItemUnitPrice(0.0);

			if (json.get("packagingFactor") != null) {
				detail.setPackagingUnit((String) json.get("packagingUnit"));
				detail.setPackagingFactor(Double.parseDouble(json.get("packagingFactor") + ""));
				detail.setPackagingCount(Double.parseDouble(json.get("packagingCount") + ""));
			}
			if (json.get("requestCount") != null) {
				detail.setRequestCount(json.getDouble("requestCount"));
			}
			if (json.get("shippingCount") != null) {
				detail.setShippingCount(Double.parseDouble(json.get("shippingCount") + ""));
			}

			if (json.get("deliveryCount") != null) {
				if (!FormType.DISTRI_UNIFIED.equals(formType.toUpperCase())) {
					detail.setDeliveryCount(json.getDouble("deliveryCount"));
					detail.setDifferentCount(0.0);
				}
			}

			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(0.0);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}
			shippingDetailBean.saveEntity(detail);

			if (FormType.DISTRI_PRODUCT.equals(formType)) {
			}

			if (FormType.DISTRI_OUTER.equals(formType)) {
				Double deliveryCount = json.getDouble("deliveryCount");

				// 入库数量
				Double itemCountIn = 0.0;

				// 出库数量
				Double itemCountOut = deliveryCount;

				// 期初数量
				Double orgiCount = storageBean.getItemCount(itemId, mainStorageId);

				// 结存数量
				Double resultCount = (orgiCount != null ? orgiCount : 0) - itemCountOut;

				StorageInOut storageInOut = new StorageInOut(header.getProviderId(), mainStorageId, businessDate,
						DateTimeUtil.getNow(), itemId, detail.getItemUnitPrice(), orgiCount, itemCountIn, itemCountOut,
						resultCount, formId, "WBCH");

				storageInOutBean.saveEntity(storageInOut);
				storageBean.updateItemCount(itemId, mainStorageId, -deliveryCount);
			}

		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		header.setFormRefId(formRefId);
		shippingHeaderBean.saveEntity(header);

		if (FormType.DISTRI_OUTER.equals(formType.toUpperCase())) {
			shippingHeaderBean.audit(formId, userId, new Date());// 出货单直接审核入库
			shippingHeaderBean.saveInputStatus(formId, "未入库");
			// 设置配送单状态
			FormStatus fstatus = new FormStatus(formId, "已审核", userId);
			fstatus.setStatusTime(new Date());
			formStatusBean.saveEntity(fstatus);
			// 要货单标记为已经生成过配送单，不允许重复生成
			requestHeaderBean.saveShippingStatus(formRefId, "已出货处理");
		} else if (FormType.DISTRI_PRODUCT.equals(formType.toUpperCase())) {
			// 设置配送单状态
			formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
			purchasingHeaderBean.saveOutStatus(formRefId, "已出库");
		} else {
			// 设置配送单状态
			formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
			// 要货单标记为已经生成过配送单，不允许重复生成
			requestHeaderBean.saveShippingStatus(formRefId, "已配送处理");
		}
		OperateVersionUtil.save(formId);
		OperateLogUtil.record(formId, LogType.CREATE, msg);

		// update form items price
		shippingDetailBean.updatePrice(formId);

		return formId;
	}

	/**
	 * 生成配送单
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<String> batchCreate(String formRefIds, ShippingHeader commonHeader) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formType = commonHeader.getFormType();
		String prefix = "PST";
		String msg = "批量新增配送单";

		if (FormType.DISTRI_OUTER.equals(formType)) {
			prefix = "CH";
		} else if (FormType.DISTRI_PRODUCT.equals(formType)) {
			prefix = "CK";
		}

		Date formTime = commonHeader.getFormTime();
		String userId = commonHeader.getFormMakerId();
		String[] idArr = formRefIds.split(",");
		List<String> sidLst = new ArrayList<String>();
		for (String formRefId : idArr) {
			RequestHeader rHeader = requestHeaderBean.queryById(formRefId);
			ShippingHeader header = new ShippingHeader();
			String requesterId = rHeader.getBuyerId();

			String formBody = FormUtil.genFormIdBody(prefix, requesterId, formTime);
			Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
			String formId = formBody + FormUtil.formatSerial(newSerial);

			header.setFormId(formId);
			sidLst.add(formId);
			header.setFormRefId(formRefId);
			header.setFormType(formType);
			header.setProvider(commonHeader.getProvider());
			header.setProviderId(commonHeader.getProviderId());
			header.setRequesterId(requesterId);
			header.setRequester(rHeader.getBuyerName());
			header.setRequestAddress(rHeader.getBuyerAddress());
			header.setFormTime(formTime);
			header.setReceiveTime(rHeader.getReceiveTime());
			header.setFormMakerId(userId);
			header.setFormMaker(commonHeader.getFormMaker());
			header.setOutStorage(commonHeader.getOutStorage());
			header.setOutStorageId(commonHeader.getOutStorageId());

			List<Map> detailLst = createDistributionBean.queryDetail(formRefId);
			double allPayAmt = 0.0, maxPayAmt = -1.0;
			String maxPayItem = "";

			for (Map detail : detailLst) {
				String deliveryType = (String) detail.get("deliveryType");
				// 查询统配原料
				if (FormConstant.UNIFIED_DELIVERY.equals(deliveryType)) {
					ShippingDetail sDetail = new ShippingDetail();
					sDetail.setFormId(formId);
					String itemId = (String) detail.get("itemId");
					sDetail.setItemId(itemId);
					sDetail.setItemName((String) detail.get("itemName"));
					sDetail.setItemCategory((String) detail.get("itemCategory"));
					sDetail.setItemDimension((String) detail.get("itemDimension"));
					sDetail.setItemSpecification((String) detail.get("itemSpecification"));
					BigDecimal itemUnitPrice = BigDecimal.valueOf((Double) detail.get("itemUnitPrice"));
					sDetail.setItemUnitPrice(0.0);

					String deliveryUnit = (String) detail.get("deliveryUnit");
					sDetail.setPackagingUnit(deliveryUnit);
					BigDecimal unitConvertFactor = BigDecimal.valueOf((Double) detail.get("unitConvertFactor"));
					sDetail.setPackagingFactor(unitConvertFactor.doubleValue());

					BigDecimal orderCount = BigDecimal.valueOf((Double) detail.get("orderCount"));
					BigDecimal packagingCount = orderCount.divide(unitConvertFactor, 0, BigDecimal.ROUND_UP); // 默认向上取整，以后可能修改为参数可配置
					sDetail.setPackagingCount(packagingCount.doubleValue());
					BigDecimal shippingCount = packagingCount.multiply(unitConvertFactor);

					sDetail.setRequestCount(orderCount.doubleValue());
					sDetail.setPackagingCount(packagingCount.doubleValue());
					sDetail.setShippingCount(shippingCount.doubleValue());
					sDetail.setDeliveryCount(shippingCount.doubleValue());

					// 按配送数重新计算金额
					BigDecimal payAmt = itemUnitPrice.multiply(shippingCount);
					sDetail.setPayAmt(0.0);
					double amt = payAmt.doubleValue();
					allPayAmt = allPayAmt + amt;
					if (amt > maxPayAmt) {
						maxPayAmt = amt;
						maxPayItem = "[" + itemId + "]" + (String) detail.get("itemName");
					}
					shippingDetailBean.saveEntity(sDetail);
				}

				OperateVersionUtil.save(formId);
			}
			header.setAllPayAmt(allPayAmt);
			header.setMaxPayItem(maxPayItem);
			shippingHeaderBean.saveEntity(header);

			// 设置配送单状态
			formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));

			// 要货单标记为已经生成过配送单，不允许重复生成
			requestHeaderBean.saveShippingStatus(formRefId, "已配送处理");

			OperateLogUtil.record(formId, LogType.CREATE, msg);

			// batch update form items price
			String formIds = StringUtils.join(sidLst, ",");
			shippingDetailBean.updatePrice(formIds);
		}
		return sidLst;
	}

	public void saveOperation(String formId, String logType, String msg) throws NoPrivilegeException, SQLException,
			NoConnection {
		OperateLogUtil.record(formId, logType, msg);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBill(String formId, String userId, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		shippingDetailBean.deleteByForm(formId);
		shippingHeaderBean.deleteEntity(formId);
		saveOperation(formId, LogType.DELETE, "删除配送单");
	}

	public void invalidBill(String formId, String userId, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.CANCELED, userId));
		saveOperation(formId, LogType.CANCELED, "作废配送单");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(String userId, ShippingHeader header, String jsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		updateBill(header, jsonData);
		saveOperation(header.getFormId(), LogType.UPDATE, "编辑配送单");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(ShippingHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		// 计算总额
		double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(json.getString("formId"));
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			double shippingCount = json.getDouble("shippingCount");
			detail.setShippingCount(shippingCount);

			double deliveryCount = json.getDouble("deliveryCount");
			detail.setDeliveryCount(deliveryCount);
			// 按配送数重新计算金额
			double amt = json.getDouble("payAmt");
			allPayAmt = allPayAmt + amt;
			if (amt > maxPayAmt) {
				maxPayAmt = amt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}
			detail.setPayAmt(amt);
			shippingDetailBean.updateShippingCount(detail);
		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		shippingHeaderBean.updateEntity(header);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateDeliveryCount(String userId, ShippingHeader header, String jsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		updateDeliveryCount(header, jsonData);
		String formId = header.getFormId();
		saveOperation(header.getFormId(), LogType.UPDATE, "编辑配送数");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateDeliveryCount(ShippingHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		// 计算总额
		double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(json.getString("formId"));
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			double deliveryCount = json.getDouble("deliveryCount");
			detail.setDeliveryCount(deliveryCount);
			double differentCount = json.getDouble("differentCount");
			detail.setDifferentCount(differentCount);
			// 按配送数重新计算金额
			double amt = json.getDouble("payAmt");
			allPayAmt = allPayAmt + amt;
			if (amt > maxPayAmt) {
				maxPayAmt = amt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}
			detail.setPayAmt(amt);
			shippingDetailBean.updateEntity(detail);
		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		shippingHeaderBean.updateEntity(header);
	}

	/**
	 * 越库配送单审核，其它单据暂时并不提供审核过程
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, ShippingHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formId = header.getFormId();
		String branchId = header.getProviderId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		// 操作时间
		Date operationTime = DateTimeUtil.getNow();
		Date businessDate = header.getAuditTime();
		String reason = "CPCK";
		if (!header.getFormType().equals("PRODUCT")) {
			reason = "YKCK";
		}
		String storageId = header.getOutStorageId();
		Map<String, Double> storageMap = shippingDetailBean.queryStorage(formId, storageId);
		Double allPayAmt = 0.0, maxPayAmt = -0.1;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			detail.setDeliveryCount(json.getDouble("deliveryCount"));// 对于越库配送单只设置实发数
			Double payAmt = json.getDouble("payAmt");
			Double unitPrice = json.getDouble("itemUnitPrice");
			detail.setPayAmt(payAmt);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}
			shippingDetailBean.updateEntity(detail);
			// 入库数量
			Double itemCountIn = 0.0;

			// 出库数量
			Double itemCountOut = detail.getDeliveryCount();

			// 期初数量
			Double orgiCount = storageMap.get(itemId);

			// 结存数量
			Double resultCount = orgiCount - itemCountOut;
			storageMap.put(itemId, resultCount);

			StorageInOut storageInOut = new StorageInOut(branchId, storageId, businessDate, operationTime, itemId,
					unitPrice, orgiCount, itemCountIn, itemCountOut, resultCount, formId, reason);

			storageInOutBean.saveEntity(storageInOut);
			storageBean.updateItemCount(itemId, storageId, -itemCountOut);
		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		shippingHeaderBean.updateEntity(header);
		shippingHeaderBean.audit(formId, header.getAuditor(), header.getAuditTime());

		// 审核以后变为已审核，未入库
		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.AUDITED_CN, userId));
		shippingHeaderBean.saveInputStatus(formId, BillStatus.UNSTORED_CN);
		saveOperation(formId, LogType.AUDIT, "编辑配送单");
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setShippingDetailBean(ShippingDetailBean shippingDetailBean) {
		this.shippingDetailBean = shippingDetailBean;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setCreateDistributionBean(CreateDistributionBean createDistributionBean) {
		this.createDistributionBean = createDistributionBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}
}
