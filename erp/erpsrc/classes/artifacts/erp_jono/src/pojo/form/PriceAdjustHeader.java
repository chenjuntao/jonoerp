/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Feb 12 11:09:53 CST 2015 by lyz
 * Last edited on Thu Feb 12 11:09:53 CST 2015 by lyz
 * 
 * 说明：中央工厂售价调价单、中央工厂采购调价单、物流中心采购调价单、物流中心标准调价单、物流中心加盟调价单、物流中心零售调价单表头
 */
package pojo.form;

import java.util.Date;

public class PriceAdjustHeader {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 单据日期
	 */
	private Date formTime;
	/**
	 * 调价类型
	 */
	private String adjustType;
	
	private String adjustTypeName;
	/**
	 * 调价范围
	 */
	private String adjustScope;
	private String supplierId;
	private String supplierName;
	/**
	 * 生效方式
	 */
	private String effectType;
	/**
	 * 生效时间
	 */
	private Date effectTime;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 制表人员
	 */
	private String formMaker;
	/**
	 * 制表日期
	 */
	private Date makeTime;
	/**
	 * 审核人员
	 */
	private String auditor;
	/**
	 * 审核日期
	 */
	private Date auditTime;
	/**
	 * 单据下方的备注说明
	 */
	private String formNote;
	private String formStatus;
	
	

	public String getAdjustTypeName() {
		return adjustTypeName;
	}

	public void setAdjustTypeName(String adjustTypeName) {
		this.adjustTypeName = adjustTypeName;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Date getFormTime() {
		return this.formTime;
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	public String getAdjustScope() {
		return this.adjustScope;
	}

	public void setAdjustScope(String adjustScope) {
		//this.adjustScope = adjustScope;
		this.adjustScope = this.adjustType;
	}

	public String getEffectType() {
		return this.effectType;
	}

	public void setEffectType(String effectType) {
		this.effectType = effectType;
	}

	public Date getEffectTime() {
		return this.effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFormMaker() {
		return this.formMaker;
	}

	public void setFormMaker(String formMaker) {
		this.formMaker = formMaker;
	}

	public Date getMakeTime() {
		return this.makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
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

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}