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
package com.tanry.business.module.lc.request.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import pojo.store.Branch;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.constant.LogType;

public class ManualDistributionService {

	private ShippingHeaderBean shippingHeaderBean;
	private ShippingDetailBean shippingDetailBean;
	private FormStatusBean formStatusBean;

	private OperationVersionBean operationVersionBean;

	private BranchBean branchBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updatePrice(String formIds) throws NoPrivilegeException, SQLException, NoConnection {
		shippingDetailBean.updatePrice(formIds);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<String> doSave(String branchIds, ShippingHeader shippingHeader, String loginUserId, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		List<String> sidLst = new ArrayList<String>();

		String[] branchArr = branchIds.split(",");
		for (String branchId : branchArr) {
			Branch branch = branchBean.GetBranchById(branchId);
			shippingHeader.setRequesterId(branch.getBranchId());
			shippingHeader.setRequester("[" + branch.getBranchId() + "]" + branch.getBranchName());
			shippingHeader.setRequestAddress(branch.getBranchAddress());
			sidLst.add(createBill(loginUserId, shippingHeader, branchId, jsonData));
		}

		shippingDetailBean.updatePrice(StringUtils.join(sidLst, ","));

		return sidLst;
	}

	/**
	 * 生成配送单
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String createBill(String userId, ShippingHeader header, String branchId, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		Date formTime = header.getFormTime();
		String requesterId = header.getRequesterId();

		String prefix = "PST";
		String formBody = FormUtil.genFormIdBody(prefix, requesterId, formTime);
		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);

		header.setFormId(formId);

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			BigDecimal shippingCount = new BigDecimal(json.getString(branchId));
			if (shippingCount.compareTo(BigDecimal.ZERO) <= 0) {
				continue;
			}

			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("itemDimension"));
			Object itemSpecification = json.get("itemSpecification");
			if (!JSONNull.getInstance().equals(itemSpecification)) {
				detail.setItemSpecification(itemSpecification.toString());
			}
			detail.setItemUnitPrice(0.0);

			detail.setPackagingUnit((String) json.get("packagingUnit"));
			detail.setPackagingFactor(Double.parseDouble(json.get("packagingFactor") + ""));
			BigDecimal packagingFactor = new BigDecimal(json.getString("packagingFactor"));

			BigDecimal packagingCount = shippingCount.divide(packagingFactor, 0, BigDecimal.ROUND_UP); // 默认向上取整，以后可能修改为参数可配置

			detail.setShippingCount(shippingCount.doubleValue());
			detail.setDeliveryCount(shippingCount.doubleValue());
			detail.setPackagingCount(packagingCount.doubleValue());

			Double payAmt = json.getDouble("itemUnitPrice") * shippingCount.doubleValue();
			detail.setPayAmt(0.0);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}
			shippingDetailBean.saveEntity(detail);
		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);

		shippingHeaderBean.saveEntity(header);

		// 设置配送单状态
		formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));

		OperateVersionUtil.save(formId);
		OperateLogUtil.record(formId, LogType.CREATE, "手动生成配送单");

		return formId;
	}

	public void setShippingDetailBean(ShippingDetailBean shippingDetailBean) {
		this.shippingDetailBean = shippingDetailBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

}
