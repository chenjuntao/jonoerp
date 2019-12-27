//===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.8.7 by cjt
// Last edited on 2014.8.25 by lyz
//
// Comments:read D_T2_STORAGE table.
// 说明：入库单表头。
// by mep
//===============================================

package pojo.form;

import java.util.Date;

public class InputHeader {

	private String formId;
	private String formRefId;
	/**
	 * 入库单类型(PURCHASING:采购入库,PRODUCE:生产入库)
	 */
	private String formType;
	private String formName;
	private String inputDepartmentId;
	private String inputDepartment;
	private String inputerId;
	private String inputer;
	/**
	 * 入库仓库标识(仅对多仓库有效)
	 */
	private String storageId;
	private String storage;
	private Date inputTime;
	private String providerId;
	private String provider;
	private String formNote;
	private String auditorId;
	private String auditor;
	private Date auditTime;
	private double allPayAmt;
	private String maxPayItem;

	private String status;
	private Integer times;
	private Date auditTimeActual;
	private String s;

	public void setS(String s) {
		this.s = s;
	}

	public String getS() {
		return s;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getInputDepartment() {
		return inputDepartment;
	}

	public void setInputDepartment(String inputDepartment) {
		this.inputDepartment = inputDepartment;
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

	public double getAllPayAmt() {
		return allPayAmt;
	}

	public void setAllPayAmt(double allPayAmt) {
		this.allPayAmt = allPayAmt;
	}

	public String getMaxPayItem() {
		return maxPayItem;
	}

	public void setMaxPayItem(String maxPayItem) {
		this.maxPayItem = maxPayItem;
	}

	public String getInputDepartmentId() {
		return inputDepartmentId;
	}

	public void setInputDepartmentId(String inputDepartmentId) {
		this.inputDepartmentId = inputDepartmentId;
	}

	public String getInputerId() {
		return inputerId;
	}

	public void setInputerId(String inputerId) {
		this.inputerId = inputerId;
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

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
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

	public String getFormRefId() {
		return formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Date getAuditTimeActual() {
		return auditTimeActual;
	}

	public void setAuditTimeActual(Date auditTimeActual) {
		this.auditTimeActual = auditTimeActual;
	}

}
