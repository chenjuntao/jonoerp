/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 26, 2014 by liyzh
 * Last edited on Aug 26, 2014 by liyzh
 * 
 * 说明：餐厅要货、外部订货商订货需求表头
 */
package pojo.form;

import java.util.Date;

public class RequestHeader {
	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 单据类型（餐厅要货/外部订货）
	 */
	private String formType;
	/**
	 * 订货部门ID
	 */
	private String buyerId;
	/**
	 * 订货部门
	 */
	private String buyerName;
	/**
	 * 订货仓库标识(仅对多仓库有效)
	 */
	private String storageId;
	private String storage;
	/**
	 * 订货地址
	 */
	private String buyerAddress;
	/**
	 * 到货时间
	 */
	private Date receiveTime;
	/**
	 * 制单人员
	 */
	private String formMaker;
	/**
	 * 制单日期
	 */
	private Date formTime;
	/**
	 * 制单时间
	 */
	private Date formTimeActual;
	/**
	 * 审核人员
	 */
	private String auditor;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 备注信息
	 */
	private String formNote;
	/**
	 * 是否进行采购汇总
	 */
	private String purchaseStatus;
	/**
	 * 是否进行配送处理
	 */
	private String shippingStatus;
	/**
	 * 参考时间段1开始日期
	 */
	private Date refDateStart1;
	/**
	 * 参考时间段1开始日期
	 */
	private Date refDateEnd1;
	/**
	 * 参考时间段2开始日期
	 */
	private Date refDateStart2;
	/**
	 * 参考时间段2开始日期
	 */
	private Date refDateEnd2;
	/**
	 * 参考时间段3开始日期
	 */
	private Date refDateStart3;
	/**
	 * 参考时间段3开始日期
	 */
	private Date refDateEnd3;
	/**
	 * 延滞期金额
	 */
	private Double delayPredict;
	/**
	 * 进货周期金额
	 */
	private Double purchasePredict;
	/**
	 * 安全存量
	 */
	private Double safetyStock;
	/**
	 * 预估销售额
	 */
	private Double sellPredict;
	/**
	 * 总金额
	 */
	private Double allPayAmt;
	/**
	 * 金额最大的商品
	 */
	private String maxPayItem;
	/**
	 * 到货周期
	 */
	private Integer arrivePeriod;
	/**
	 * 配送模式
	 */
	private String deliveryType;

	private String formStatus;

	/**
	 * 供货部门ID
	 */
	private String providerId;

	/**
	 * 供货部门
	 */
	private String provider;

	/**
	 * 模板编号
	 */
	private String templateId;
	/**
	 * 模板名称
	 */
	private String templateName;

	private String isAddForm;

	private String rownumber;

	public String getRownumber() {
		return rownumber;
	}

	public void setRownumber(String rownumber) {
		this.rownumber = rownumber;
	}

	public String getIsAddForm() {
		return isAddForm;
	}

	public void setIsAddForm(String isAddForm) {
		this.isAddForm = isAddForm;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getFormMaker() {
		return formMaker;
	}

	public void setFormMaker(String formMaker) {
		this.formMaker = formMaker;
	}

	public Date getFormTime() {
		return formTime;
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getFormNote() {
		return formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

	public Date getRefDateStart1() {
		return refDateStart1;
	}

	public void setRefDateStart1(Date refDateStart1) {
		this.refDateStart1 = refDateStart1;
	}

	public Date getRefDateEnd1() {
		return refDateEnd1;
	}

	public void setRefDateEnd1(Date refDateEnd1) {
		this.refDateEnd1 = refDateEnd1;
	}

	public Date getRefDateStart2() {
		return refDateStart2;
	}

	public void setRefDateStart2(Date refDateStart2) {
		this.refDateStart2 = refDateStart2;
	}

	public Date getRefDateEnd2() {
		return refDateEnd2;
	}

	public void setRefDateEnd2(Date refDateEnd2) {
		this.refDateEnd2 = refDateEnd2;
	}

	public Date getRefDateStart3() {
		return refDateStart3;
	}

	public void setRefDateStart3(Date refDateStart3) {
		this.refDateStart3 = refDateStart3;
	}

	public Date getRefDateEnd3() {
		return refDateEnd3;
	}

	public void setRefDateEnd3(Date refDateEnd3) {
		this.refDateEnd3 = refDateEnd3;
	}

	public Double getDelayPredict() {
		return delayPredict;
	}

	public void setDelayPredict(Double delayPredict) {
		this.delayPredict = delayPredict;
	}

	public Double getPurchasePredict() {
		return purchasePredict;
	}

	public void setPurchasePredict(Double purchasePredict) {
		this.purchasePredict = purchasePredict;
	}

	public Double getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(Double safetyStock) {
		this.safetyStock = safetyStock;
	}

	public Double getSellPredict() {
		return sellPredict;
	}

	public void setSellPredict(Double sellPredict) {
		this.sellPredict = sellPredict;
	}

	public String getFormType() {
		return this.formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public Double getAllPayAmt() {
		return allPayAmt;
	}

	public void setAllPayAmt(Double allPayAmt) {
		this.allPayAmt = allPayAmt;
	}

	public String getMaxPayItem() {
		return maxPayItem;
	}

	public void setMaxPayItem(String maxPayItem) {
		this.maxPayItem = maxPayItem;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Integer getArrivePeriod() {
		return arrivePeriod;
	}

	public void setArrivePeriod(Integer arrivePeriod) {
		this.arrivePeriod = arrivePeriod;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public Date getFormTimeActual() {
		return formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
	}

}
