/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 25, 2015 by liyzh
 * Last edited on Mar 25, 2015 by liyzh
 * 
 * 说明： 存储每个门店、物流中心和中央工厂以及财务对账时每月的月结记录
 */
package com.tanry.business.module.hq.settle.pojo;

import java.util.Date;

public class MonthlySettleRecord {

	private String branchId;
	private String branchName;
	private Date monthDate;
	private String operatorId;
	private String operatorName;
	private Date operatingTime;
	private String settleStatus;
	private String settleNote;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(Date operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getSettleNote() {
		return settleNote;
	}

	public void setSettleNote(String settleNote) {
		this.settleNote = settleNote;
	}

	public Date getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(Date monthDate) {
		this.monthDate = monthDate;
	}
}
