/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Mar 13 15:18:15 CST 2015 by pw
 * Last edited on Fri Mar 13 15:18:15 CST 2015 by pw
 * 
 * 说明：对账单表头
 */
package pojo.form;

import java.util.Date;

public class StatementHead {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 对账单据类型（供应商总部财务对账G，外部总部财务对账W）
	 */
	private String formType;
	/**
	 * 供应商ID
	 */
	private String providerId;
	/**
	 * 供应商
	 */
	private String provider;
	/**
	 * 部门编号
	 */
	private String branchId;
	/**
	 * 部门名称
	 */
	private String branchName;
	/**
	 * 制单人员ID
	 */
	private String formMakerId;
	/**
	 * 制单人员
	 */
	private String formMaker;
	/**
	 * 制单日期
	 */
	private Date formTime;
	private Date formTimeActual;
	/**
	 * 审核人员ID
	 */
	private String auditorId;
	/**
	 * 审核人员
	 */
	private String auditor;
	/**
	 * 审核日期
	 */
	private Date auditTime;
	/**
	 * 总金额
	 */
	private Double allPayAmt;
	/**
	 * 对账开始日期
	 */
	private Date startDate;
	/**
	 * 对账结束日期
	 */
	private Date endDate;
	/**
	 * 备注说明
	 */
	private String formNote;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormType() {
		return this.formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getProviderId() {
		return this.providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Date getFormTimeActual() {
		return formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public Double getAllPayAmt() {
		return this.allPayAmt;
	}

	public void setAllPayAmt(Double allPayAmt) {
		this.allPayAmt = allPayAmt;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFormNote() {
		return this.formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

}