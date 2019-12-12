/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 11 15:05:12 CST 2014 by lyz
 * Last edited on Wed July 22 8:17:12 CST 2015 by lyz
 * 
 * 说明：捡货单表头
 */
package pojo.form;

import java.util.Date;

public class PickingHeader {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 捡货部门ID
	 */
	private String pickingBranchId;
	/**
	 * 捡货仓库ID
	 */
	private String storageId;
	private String storage;
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
	 * 捡货人员ID
	 */
	private String pickingManId;
	/**
	 * 捡货人员
	 */
	private String pickingMan;
	/**
	 * 捡货日期
	 */
	private Date pickingTime;
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
	 * 单据下方的说明
	 */
	private String formNote;

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getStorageId() {
		return this.storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
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

	public Date getFormTimeActual() {
		return formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
	}

	public Date getFormTime() {
		return this.formTime;
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getPickingManId() {
		return this.pickingManId;
	}

	public void setPickingManId(String pickingManId) {
		this.pickingManId = pickingManId;
	}

	public String getPickingMan() {
		return this.pickingMan;
	}

	public void setPickingMan(String pickingMan) {
		this.pickingMan = pickingMan;
	}

	public Date getPickingTime() {
		return this.pickingTime;
	}

	public void setPickingTime(Date pickingTime) {
		this.pickingTime = pickingTime;
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

	public String getPickingBranchId() {
		return pickingBranchId;
	}

	public void setPickingBranchId(String pickingBranchId) {
		this.pickingBranchId = pickingBranchId;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

}