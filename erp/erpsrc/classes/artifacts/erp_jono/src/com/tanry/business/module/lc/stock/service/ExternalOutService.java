/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 9, 2014 by liyzh
 * Last edited on Dec 9, 2014 by liyzh
 * 
 * 说明： 物流中心外部配送出库管理
 */
package com.tanry.business.module.lc.stock.service;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.form.StorageInOutBean;
import logic.store.BranchBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.FormStatus;
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import pojo.form.StorageInOut;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class ExternalOutService {

	private ShippingHeaderBean shippingHeaderBean;
	private ShippingDetailBean shippingDetailBean;
	private StorageBean storageBean;
	private FormStatusBean formStatusBean;

	private StorageInOutBean storageInOutBean;
	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}
	
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	/**
	 * 外部出货单填充
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fillBill(String userId, ShippingHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formId = header.getFormId();
		String branchId = header.getRequesterId();
		String lcCode = header.getProviderId();
		String outStorageId = "100a";// 默认为物流中心主仓库

		// -----------------------------------------

		// 操作时间
		Date operationTime = DateTimeUtil.getNow();

		// 业务类型 餐厅配送入库
		String reason = "PSRK";
		// -----------------------------------------

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = 0.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			Double receiveCount = json.getDouble("receiveCount");
			detail.setReceiveCount(receiveCount);
			detail.setDifferentCount(json.getDouble("differentCount"));

			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}
			shippingDetailBean.updateEntity(detail);

			// -----------------------------------------
			// 出入库记录

			// -------------- 物流中心
			Double centreStorage = storageBean.getItemCount(itemId, lcCode);

			// 入库数量
			Double centreItemCountIn = 0.0;

			// 出库数量
			Double centreItemCountOut = receiveCount;

			// 期初数量
			Double centreOrgiCount = (centreStorage == null ? 0 : centreStorage);

			// 结存数量
			Double centreResultCount = centreOrgiCount - receiveCount;
			Date businessDt = branchBean.GetBranchById(lcCode).getBusinessDate();
			StorageInOut centreStorageInOut = new StorageInOut(lcCode, outStorageId,
					businessDt, operationTime, itemId, json.getDouble("itemUnitPrice"),
					centreOrgiCount, centreItemCountIn, centreItemCountOut, centreResultCount, formId, reason);

			storageInOutBean.saveEntity(centreStorageInOut);

			// -----------------------------------------

			// 确认之后，餐厅库存增加实收数量的货物，物流中心仓库减少实收数量的货物
			// 100对应物流中心
			storageBean.storageInOut(itemId, branchId, lcCode, receiveCount);
		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		shippingHeaderBean.updateEntity(header);

		// 配送单状态变为已入库
		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("已入库");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	public void setShippingDetailBean(ShippingDetailBean shippingDetailBean) {
		this.shippingDetailBean = shippingDetailBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

}
