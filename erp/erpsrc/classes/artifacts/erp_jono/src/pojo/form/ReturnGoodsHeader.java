/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Sep 20 21:22:57 CST 2014 by lyz
 * Last edited on Sat Sep 20 21:22:57 CST 2014 by lyz
 * 
 * 说明：餐厅退货单表头
 */
package pojo.form;

import java.util.Date;

public class ReturnGoodsHeader {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 相关联的配送单或采购单编号
	 */
	private String formRefId;
	/**
	 * 退货备注说明
	 */
	private String formNote;
	/**
	 * 退货人员ID
	 */
	private String returnerId;
	/**
	 * 退货人员
	 */
	private String returner;
	/**
	 * 退货时间
	 */
	private Date returnTime;
	/**
	 * 总金额
	 */
	private Double allPayAmt;
	/**
	 * 金额最大的商品
	 */
	private String maxPayItem;// 直接用作主要配送品，反正反审核中的字段没用到

	/**
	 * fields below use in query only
	 */
	private String providerId;
	private String provider;

	private Date receiveTime;
	private Date auditTime;
	private Date auditTimeActual;
	private String requester;
	private String inputer;
	private Date inputTime;
	private String snote; // shipping note配送单备注
	private String requestAddress;

	private String inputDepartment;

	private String storage;
	private String storageId;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormRefId() {
		return this.formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getFormNote() {
		return this.formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

	public String getReturnerId() {
		return this.returnerId;
	}

	public void setReturnerId(String returnerId) {
		this.returnerId = returnerId;
	}

	public String getReturner() {
		return this.returner;
	}

	public void setReturner(String returner) {
		this.returner = returner;
	}

	public Date getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Double getAllPayAmt() {
		return this.allPayAmt;
	}

	public void setAllPayAmt(Double allPayAmt) {
		this.allPayAmt = allPayAmt;
	}

	public String getMaxPayItem() {
		return this.maxPayItem;
	}

	public void setMaxPayItem(String maxPayItem) {
		this.maxPayItem = maxPayItem;
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

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getInputer() {
		return inputer;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getSnote() {
		return snote;
	}

	public void setSnote(String snote) {
		this.snote = snote;
	}

	public String getInputDepartment() {
		return inputDepartment;
	}

	public void setInputDepartment(String inputDepartment) {
		this.inputDepartment = inputDepartment;
	}

	public String getRequestAddress() {
		return requestAddress;
	}

	public void setRequestAddress(String requestAddress) {
		this.requestAddress = requestAddress;
	}

	public Date getAuditTimeActual() {
		return auditTimeActual;
	}

	public void setAuditTimeActual(Date auditTimeActual) {
		this.auditTimeActual = auditTimeActual;
	}

}