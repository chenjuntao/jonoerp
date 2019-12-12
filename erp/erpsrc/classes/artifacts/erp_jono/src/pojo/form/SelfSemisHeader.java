/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Jul 08 12:06:04 CST 2016 by pw
 * Last edited on Fri Jul 08 12:06:04 CST 2016 by pw
 * 
 * 说明：半成品加工单表头
 */
package pojo.form;

import java.util.Date;

public class SelfSemisHeader {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 加工部门ID（多仓库的情况下是仓库ID）
	 */
	private String branchId;
	/**
	 * 加工部门名称
	 */
	private String branch;
	/**
	 * 制单人员ID
	 */
	private String creatorManId;
	/**
	 * 制单人员
	 */
	private String formMaker;
	/**
	 * 制单时间
	 */
	private Date formTime;
	/**
	 * 制单日期(实际时间，包含时分秒)
	 */
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

	private Date auditTimeActual;
	/**
	 * 备注说明
	 */
	private String formNote;
	/**
	 * 主要加工的产品
	 */
	private String mainItem;

	private String formStatus;

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCreatorManId() {
		return this.creatorManId;
	}

	public void setCreatorManId(String creatorManId) {
		this.creatorManId = creatorManId;
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

	public Date getFormTimeActual() {
		return this.formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
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

	public String getMainItem() {
		return this.mainItem;
	}

	public void setMainItem(String mainItem) {
		this.mainItem = mainItem;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public Date getAuditTimeActual() {
		return auditTimeActual;
	}

	public void setAuditTimeActual(Date auditTimeActual) {
		this.auditTimeActual = auditTimeActual;
	}

}