/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 12, 2014 by liyzh
 * Last edited on Sep 12, 2014 by liyzh
 * 
 * 说明： 餐厅配送单、物流中心出货单、中央工厂出货单表头
 */
package pojo.form;

import java.util.Date;

public class ShippingHeader {
	private String formId;
	private String formRefId;
	private String formType;
	private String providerId;
	private String provider;
	/**
	 * 出库仓库标识(仅对多仓库有效)
	 */
	private String outStorageId;
	private String outStorage;
	private Date receiveTime;
	private String requesterId;
	private String requester;
	private String requestAddress;
	/**
	 * 入库仓库标识(仅对多仓库有效)
	 */
	private String inStorageId;
	private String inStorage;
	private String formMakerId;
	private String formMaker;
	private Date formTime;
	private Date formTimeActual;
	private String auditorId;
	private String auditor;
	private Date auditTime;
	private String inputerId;
	private String inputer;
	private Date inputTime;
	private String formNote;
	private Double allPayAmt;
	private String maxPayItem;
	private String formStatus;
	private Date auditTimeActual;
	private Date inputTimeActual;
	/**
	 * 捡货状态(未捡货/已捡货)
	 */
	private String pickStatus;
	/**
	 * 入库状态(未入库/已入库)
	 */
	private String inputStatus;
	/**
	 * 退货状态(退货中/已退货)
	 */
	private String returnStatus;
	/**
	 * 反审核状态(反审核中/已反审核)
	 */
	private String antiStatus;

	private String onStatus;

	private String isAddForm;

	private String printTimes;

	public String getPrintTimes() {
		return printTimes;
	}

	public void setPrintTimes(String printTimes) {
		this.printTimes = printTimes;
	}

	public String getIsAddForm() {
		return isAddForm;
	}

	public void setIsAddForm(String isAddForm) {
		this.isAddForm = isAddForm;
	}

	public String getOnStatus() {
		return onStatus;
	}

	public void setOnStatus(String onStatus) {
		this.onStatus = onStatus;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
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

	public String getRequestAddress() {
		return requestAddress;
	}

	public Date getFormTimeActual() {
		return formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
	}

	public void setRequestAddress(String requestAddress) {
		this.requestAddress = requestAddress;
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

	public String getFormNote() {
		return formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
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

	public String getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getFormMakerId() {
		return formMakerId;
	}

	public void setFormMakerId(String formMakerId) {
		this.formMakerId = formMakerId;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getInputerId() {
		return inputerId;
	}

	public void setInputerId(String inputerId) {
		this.inputerId = inputerId;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getOutStorageId() {
		return outStorageId;
	}

	public void setOutStorageId(String outStorageId) {
		this.outStorageId = outStorageId;
	}

	public String getOutStorage() {
		return outStorage;
	}

	public void setOutStorage(String outStorage) {
		this.outStorage = outStorage;
	}

	public String getInStorageId() {
		return inStorageId;
	}

	public void setInStorageId(String inStorageId) {
		this.inStorageId = inStorageId;
	}

	public String getInStorage() {
		return inStorage;
	}

	public void setInStorage(String inStorage) {
		this.inStorage = inStorage;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getFormRefId() {
		return formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getInputStatus() {
		return inputStatus;
	}

	public void setInputStatus(String inputStatus) {
		this.inputStatus = inputStatus;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getAntiStatus() {
		return antiStatus;
	}

	public void setAntiStatus(String antiStatus) {
		this.antiStatus = antiStatus;
	}

	public String getPickStatus() {
		return pickStatus;
	}

	public void setPickStatus(String pickStatus) {
		this.pickStatus = pickStatus;
	}

	public Date getAuditTimeActual() {
		return auditTimeActual;
	}

	public void setAuditTimeActual(Date auditTimeActual) {
		this.auditTimeActual = auditTimeActual;
	}

	public Date getInputTimeActual() {
		return inputTimeActual;
	}

	public void setInputTimeActual(Date inputTimeActual) {
		this.inputTimeActual = inputTimeActual;
	}

}
