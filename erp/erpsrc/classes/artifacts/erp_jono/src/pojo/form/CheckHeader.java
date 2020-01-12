/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sun Sep 28 11:08:09 CST 2014 by lyz
 * Last edited on Sun Sep 28 11:08:09 CST 2014 by lyz
 * 
 * 说明：盘点单/清单表头
 */
package pojo.form;

import java.util.Date;

public class CheckHeader {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 单据类型：盘点单/盘点清单
	 */
	private String formType;
	/**
	 * 对应盘点批次
	 */
	private String checkBatchId;
	/**
	 * 盘点部门ID
	 */
	private String checkBranchId;
	/**
	 * 盘点部门
	 */
	private String checkBranch;
	/**
	 * 盘点仓库标识(仅对多仓库有效)
	 */
	private String checkStorageId;
	private String checkStorage;
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
	private Date auditTimeActual;
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
	 * 备注说明
	 */
	private String formNote;
	/**
	 * 打印次数
	 */
	private Integer printCount;
	/**
	 * 总金额
	 */
	private Double allPayAmt;
	/**
	 * 金额最大的商品
	 */
	private String maxPayItem;

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

	public String getCheckBatchId() {
		return this.checkBatchId;
	}

	public void setCheckBatchId(String checkBatchId) {
		this.checkBatchId = checkBatchId;
	}

	public String getCheckBranchId() {
		return this.checkBranchId;
	}

	public void setCheckBranchId(String checkBranchId) {
		this.checkBranchId = checkBranchId;
	}

	public String getCheckBranch() {
		return this.checkBranch;
	}

	public void setCheckBranch(String checkBranch) {
		this.checkBranch = checkBranch;
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

	public Date getFormTimeActual() {
		return formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
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

	public Integer getPrintCount() {
		return this.printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
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

	public String getCheckStorageId() {
		return checkStorageId;
	}

	public void setCheckStorageId(String checkStorageId) {
		this.checkStorageId = checkStorageId;
	}

	public String getCheckStorage() {
		return checkStorage;
	}

	public void setCheckStorage(String checkStorage) {
		this.checkStorage = checkStorage;
	}

	public Date getAuditTimeActual() {
		return auditTimeActual;
	}

	public void setAuditTimeActual(Date auditTimeActual) {
		this.auditTimeActual = auditTimeActual;
	}

}