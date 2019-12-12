/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Dec 19 11:53:43 CST 2014 by pw
 * Last edited on Fri Dec 19 11:53:43 CST 2014 by pw
 * 
 * 说明：餐厅调拨单表头
 */
package pojo.form;

import java.util.Date;

public class TransferHeader {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 调入部门ID
	 */
	private String inBranchId;
	/**
	 * 调入部门
	 */
	private String inBranch;
	/**
	 * 入库仓库标识(仅对多仓库有效)
	 */
	private String inStorageId;
	/**
	 * 入库仓库名称(仅对多仓库有效)
	 */
	private String inStorage;
	/**
	 * 调出部门ID
	 */
	private String outBranchId;
	/**
	 * 调出部门
	 */
	private String outBranch;
	/**
	 * 出库仓库标识(仅对多仓库有效)
	 */
	private String outStorageId;
	/**
	 * 出库仓库名称(仅对多仓库有效)
	 */
	private String outStorage;
	/**
	 * 制单人员ID
	 */
	private String fromMakerId;
	/**
	 * 制单人员
	 */
	private String fromMaker;
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
	private Date AuditTimeActual;
	/**
	 * 确认人员ID
	 */
	private String confirmerId;
	/**
	 * 确认人员
	 */
	private String confirmer;
	/**
	 * 确认日期
	 */
	private Date confirmTime;
	/**
	 * 调出人员ID
	 */
	private String outManId;
	/**
	 * 调出人员
	 */
	private String outMan;
	/**
	 * 调出日期
	 */
	private Date outTime;
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

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getInBranchId() {
		return this.inBranchId;
	}

	public void setInBranchId(String inBranchId) {
		this.inBranchId = inBranchId;
	}

	public Date getFormTimeActual() {
		return formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
	}

	public String getInBranch() {
		return this.inBranch;
	}

	public void setInBranch(String inBranch) {
		this.inBranch = inBranch;
	}

	public String getInStorageId() {
		return this.inStorageId;
	}

	public void setInStorageId(String inStorageId) {
		this.inStorageId = inStorageId;
	}

	public String getInStorage() {
		return this.inStorage;
	}

	public void setInStorage(String inStorage) {
		this.inStorage = inStorage;
	}

	public String getOutBranchId() {
		return this.outBranchId;
	}

	public void setOutBranchId(String outBranchId) {
		this.outBranchId = outBranchId;
	}

	public String getOutBranch() {
		return this.outBranch;
	}

	public void setOutBranch(String outBranch) {
		this.outBranch = outBranch;
	}

	public String getOutStorageId() {
		return this.outStorageId;
	}

	public void setOutStorageId(String outStorageId) {
		this.outStorageId = outStorageId;
	}

	public String getOutStorage() {
		return this.outStorage;
	}

	public void setOutStorage(String outStorage) {
		this.outStorage = outStorage;
	}

	public String getFromMakerId() {
		return this.fromMakerId;
	}

	public void setFromMakerId(String fromMakerId) {
		this.fromMakerId = fromMakerId;
	}

	public String getFromMaker() {
		return this.fromMaker;
	}

	public void setFromMaker(String fromMaker) {
		this.fromMaker = fromMaker;
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

	public String getConfirmerId() {
		return this.confirmerId;
	}

	public void setConfirmerId(String confirmerId) {
		this.confirmerId = confirmerId;
	}

	public String getConfirmer() {
		return this.confirmer;
	}

	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
	}

	public Date getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getOutManId() {
		return this.outManId;
	}

	public void setOutManId(String outManId) {
		this.outManId = outManId;
	}

	public String getOutMan() {
		return this.outMan;
	}

	public void setOutMan(String outMan) {
		this.outMan = outMan;
	}

	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
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

	public Date getAuditTimeActual() {
		return AuditTimeActual;
	}

	public void setAuditTimeActual(Date auditTimeActual) {
		AuditTimeActual = auditTimeActual;
	}

}