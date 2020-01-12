/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 6, 2014 by liyzh
 * Last edited on Nov 6, 2014 by liyzh
 * 
 * 说明： 补单操作，直接转化为采购单
 */
package com.tanry.business.module.lc.request.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import logic.module.lc.RequestPurchaseBean;
import logic.store.BranchBean;
import pojo.form.CollectDetail;
import pojo.form.FormStatus;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import pojo.form.RequestHeader;
import pojo.store.Branch;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchCode;

public class AdditionService {

	private FormStatusBean formStatusBean;

	private BranchBean branchBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private PurchasingDetailBean purchasingDetailBean;
	private RequestPurchaseBean requestPurchaseBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createPurchaseBill(String userId, RequestHeader header) throws NoPrivilegeException, SQLException,
			NoConnection {
		String formId = header.getFormId();
		String deliveryType = header.getDeliveryType();
		String buyerId = header.getBuyerId();
		String buyerName = header.getBuyerName();
		String buyerAddress = header.getBuyerAddress();
		if (FormConstant.UNIFIED_DELIVERY.equals(deliveryType)) {// 统配的单据都要送回物流中心
			buyerId = BranchCode.DEFAULT_LOGISTICSCENTER;
			Branch branch = branchBean.GetBranchById(buyerId);
			buyerName = branch.getBranchName();
			buyerAddress = branch.getBranchAddress();
		}
		Date businessDate = branchBean.GetBranchById(BranchCode.DEFAULT_LOGISTICSCENTER).getBusinessDate();// 获取物流中心当前的营业时间
		String formMaker = header.getFormMaker();
		List<Map> providerLst = requestPurchaseBean.queryProvider(formId);
		String commonFormId = FormUtil.generateFormId("CG", buyerId, businessDate);// 采购CG；计算机同时生成多张采购单，要用序号加以区分
		int count = 0;
		for (Map provider : providerLst) {
			PurchasingHeader pHeader = new PurchasingHeader();
			String pFormId = commonFormId + count++;
			pHeader.setFormId(pFormId);
			pHeader.setFormRefId(formId);
			pHeader.setFormTime(businessDate);
			String providerId = (String) provider.get("providerId");
			pHeader.setProviderId(providerId);
			pHeader.setProvider((String) provider.get("provider"));
			pHeader.setRequesterId(buyerId);
			pHeader.setRequester(buyerName);
			pHeader.setReceiveAddress(buyerAddress);
			pHeader.setFormMakerId(userId);
			pHeader.setFormMaker(formMaker);
			purchasingHeaderBean.saveEntity(pHeader);

			List<CollectDetail> detailLst = requestPurchaseBean.query(formId, providerId);
			for (CollectDetail detail : detailLst) {
				PurchasingDetail pDetail = new PurchasingDetail();
				pDetail.setFormId(pFormId);
				pDetail.setItemId(detail.getItemId());
				pDetail.setItemName(detail.getItemName());
				pDetail.setItemDimension(detail.getItemDimension());
				pDetail.setItemSpecification(detail.getItemSpecification());
				pDetail.setItemCount(detail.getItemCount());
				pDetail.setItemUnitPrice(detail.getItemUnitPrice());
				pDetail.setPayAmt(detail.getPayAmt());
				purchasingDetailBean.saveEntity(pDetail);
			}

			FormStatus fstatus = new FormStatus();
			fstatus.setFormId(pFormId);
			fstatus.setStatus("已审核");
			fstatus.setStatusTime(new Date());
			fstatus.setOperator(userId);
			formStatusBean.saveEntity(fstatus);
		}

		// 设置要货单/外部订货单的状态为已下发采购单
		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("已下发");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public void setRequestPurchaseBean(RequestPurchaseBean requestPurchaseBean) {
		this.requestPurchaseBean = requestPurchaseBean;
	}

}
