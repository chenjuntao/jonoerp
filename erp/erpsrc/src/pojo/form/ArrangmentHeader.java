/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 04 17:07:13 CST 2014 by pw
 * Last edited on Wed July 22 8:17:12 CST 2015 by lyz
 * 
 * 说明：中央工厂生产计划/排程单表头
 */
package pojo.form;

import java.util.Date;

public class ArrangmentHeader {
	private String formId;
	private String formRefId;
	/**
	 * 制单部门ID
	 */
	private String formBranchId;
	private String formMakerId;
	private String formMaker;
	private Date formTime;
	private Date formTimeActual;
	private String auditorId;
	private String auditor;
	private Date auditTime;
	private String formNote;
	private String workShop;
	/**
	 * 是否审核
	 */
	private String formStatus;
	/**
	 * 是否下发采购单
	 */
	private String purchaseStatus;

	private String status;

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Date getFormTimeActual() {
		return formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
	}

	public String getFormRefId() {
		return this.formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getFormMakerId() {
		return this.formMakerId;
	}

	public void setFormMakerId(String formMakerId) {
		this.formMakerId = formMakerId;
	}

	public String getFormMaker() {
		return this.formMaker;
	}

	public void setFormMaker(String formMaker) {
		this.formMaker = formMaker;
	}

	public Date getFormTime() {
		return this.formTime;
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getAuditorId() {
		return this.auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditor() {
		return this.auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getFormNote() {
		return this.formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getFormBranchId() {
		return formBranchId;
	}

	public void setFormBranchId(String formBranchId) {
		this.formBranchId = formBranchId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkShop() {
		return workShop;
	}

	public void setWorkShop(String workShop) {
		this.workShop = workShop;
	}

}