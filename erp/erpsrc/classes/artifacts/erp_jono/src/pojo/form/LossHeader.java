/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Dec 17 11:51:46 CST 2014 by pw
 * Last edited on Wed Dec 17 11:51:46 CST 2014 by pw
 * 
 * 说明：餐厅、中央工厂、物流中心报损单表头
 */
package pojo.form;

import java.util.Date;

public class LossHeader {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 报损单类型：RAWLOSS原料报损/DISHLOSS出品报损
	 */
	private String lossType;
	/**
	 * 报损部门ID
	 */
	private String lossBranchId;
	/**
	 * 报损部门
	 */
	private String lossBranch;
	/**
	 * 报损仓库标识
	 */
	private String storageId;
	/**
	 * 报损仓库名称
	 */
	private String storage;
	/**
	 * 报损人员ID
	 */
	private String lossManId;
	/**
	 * 报损人员
	 */
	private String lossMan;
	/**
	 * 报损日期
	 */
	private Date lossTime;
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

	private Date auditTimeActual;
	/**
	 * 处理人员ID
	 */
	private String processorId;
	/**
	 * 处理人员
	 */
	private String processor;
	/**
	 * 审核日期
	 */
	private Date processTime;
	/**
	 * 备注说明
	 */
	private String formNote;
	/**
	 * 总金额
	 */
	private Double allPayAmt;
	/**
	 * 金额最大的商品
	 */
	private String maxPayItem;

	private String formStatus;

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getLossType() {
		return this.lossType;
	}

	public void setLossType(String lossType) {
		this.lossType = lossType;
	}

	public String getLossBranchId() {
		return this.lossBranchId;
	}

	public void setLossBranchId(String lossBranchId) {
		this.lossBranchId = lossBranchId;
	}

	public String getLossBranch() {
		return this.lossBranch;
	}

	public void setLossBranch(String lossBranch) {
		this.lossBranch = lossBranch;
	}

	public String getStorageId() {
		return this.storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorage() {
		return this.storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getLossManId() {
		return this.lossManId;
	}

	public void setLossManId(String lossManId) {
		this.lossManId = lossManId;
	}

	public String getLossMan() {
		return this.lossMan;
	}

	public void setLossMan(String lossMan) {
		this.lossMan = lossMan;
	}

	public Date getLossTime() {
		return this.lossTime;
	}

	public void setLossTime(Date lossTime) {
		this.lossTime = lossTime;
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

	public Date getAuditTimeActual() {
		return auditTimeActual;
	}

	public void setAuditTimeActual(Date auditTimeActual) {
		this.auditTimeActual = auditTimeActual;
	}

	public String getProcessorId() {
		return this.processorId;
	}

	public void setProcessorId(String processorId) {
		this.processorId = processorId;
	}

	public String getProcessor() {
		return this.processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public Date getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getFormNote() {
		return this.formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
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

}