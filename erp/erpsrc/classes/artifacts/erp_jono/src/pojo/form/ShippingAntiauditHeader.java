/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Sep 19 11:19:03 CST 2014 by lyz
 * Last edited on Fri Sep 19 11:19:03 CST 2014 by lyz
 * 
 * 说明：餐厅配送反审核单表头
 */
package pojo.form;

import java.util.Date;

public class ShippingAntiauditHeader {
	private String formId;
	private String formRefId;
	private String formName;
	private String antiauditorId;
	private String antiauditor;
	private String antiauditBranchId;
	private String antiauditBranch;
	private Date antiauditTime;
	private Date auditTime;
	private Date auditTimeActual;
	private String formNote;
	private Double allPayAmt;
	private String maxPayItem; // 直接用作主要配送品，反正反审核中的字段没用到

	/**
	 * fields below use in query only
	 */
	private String providerId;
	private String provider;
	private Date receiveTime;
	private String requester;
	private String inputer;
	private Date inputTime;
	private String snote; // shipping note配送单备注

	private String outStorage;
	private String inStorage;
	/**
	 * 反审核状态(反审核中/已反审核)
	 */
	private String antiStatus;
	private String branchType;

	public String getOutStorage() {
		return outStorage;
	}

	public void setOutStorage(String outStorage) {
		this.outStorage = outStorage;
	}

	public String getInStorage() {
		return inStorage;
	}

	public void setInStorage(String inStorage) {
		this.inStorage = inStorage;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormRefId() {
		return this.formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getAntiauditorId() {
		return this.antiauditorId;
	}

	public void setAntiauditorId(String antiauditorId) {
		this.antiauditorId = antiauditorId;
	}

	public String getAntiauditor() {
		return this.antiauditor;
	}

	public void setAntiauditor(String antiauditor) {
		this.antiauditor = antiauditor;
	}

	public String getAntiauditBranchId() {
		return this.antiauditBranchId;
	}

	public void setAntiauditBranchId(String antiauditBranchId) {
		this.antiauditBranchId = antiauditBranchId;
	}

	public String getAntiauditBranch() {
		return this.antiauditBranch;
	}

	public void setAntiauditBranch(String antiauditBranch) {
		this.antiauditBranch = antiauditBranch;
	}

	public Date getAntiauditTime() {
		return this.antiauditTime;
	}

	public void setAntiauditTime(Date antiauditTime) {
		this.antiauditTime = antiauditTime;
	}

	public Date getAuditTime() {
		return auditTime;
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

	public String getSnote() {
		return snote;
	}

	public void setSnote(String Snote) {
		this.snote = Snote;
	}

	public String getAntiStatus() {
		return antiStatus;
	}

	public void setAntiStatus(String antiStatus) {
		this.antiStatus = antiStatus;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public Date getAuditTimeActual() {
		return auditTimeActual;
	}

	public void setAuditTimeActual(Date auditTimeActual) {
		this.auditTimeActual = auditTimeActual;
	}

}