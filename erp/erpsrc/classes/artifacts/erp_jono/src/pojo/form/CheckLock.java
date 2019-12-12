/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Sep 30 16:21:20 CST 2014 by lyz
 * Last edited on Tue Sep 30 16:21:20 CST 2014 by lyz
 * 
 * 说明：盘点锁库记录表
 */
package pojo.form;

import java.util.Date;

public class CheckLock {

	/**
	 * 盘点批次号
	 */
	private String checkBatchId;
	/**
	 * 盘点批次状态：未完成/已完成（是否由盘点清单生成盘点单）
	 */
	private String checkBatchStatus;
	/**
	 * 锁库部门ID
	 */
	private String lockBranchId;
	/**
	 * 锁库部门
	 */
	private String lockBranch;
	/**
	 * 锁库仓库标识(仅对多仓库有效)
	 */
	private String lockStorageId;
	private String lockStorage;
	/**
	 * 锁库人员ID
	 */
	private String lockManId;
	/**
	 * 锁库人员
	 */
	private String lockMan;
	/**
	 * 锁库日期（营业日期）
	 */
	private Date lockDate;
	/**
	 * 锁库时间（服务器的实际时间）
	 */
	private Date lockTime;
	/**
	 * 备注说明
	 */
	private String lockNote;
	/**
	 * 当前批次的多个盘点清单之间是否允许出现重复原料
	 */
	private String itemRepeatable;

	/**
	 * 盘点原料类别（多个类别逗号分隔）
	 */
	private String itemCategory;

	public String getCheckBatchId() {
		return this.checkBatchId;
	}

	public void setCheckBatchId(String checkBatchId) {
		this.checkBatchId = checkBatchId;
	}

	public String getCheckBatchStatus() {
		return this.checkBatchStatus;
	}

	public void setCheckBatchStatus(String checkBatchStatus) {
		this.checkBatchStatus = checkBatchStatus;
	}

	public String getLockBranchId() {
		return this.lockBranchId;
	}

	public void setLockBranchId(String lockBranchId) {
		this.lockBranchId = lockBranchId;
	}

	public String getLockBranch() {
		return this.lockBranch;
	}

	public void setLockBranch(String lockBranch) {
		this.lockBranch = lockBranch;
	}

	public String getLockManId() {
		return this.lockManId;
	}

	public void setLockManId(String lockManId) {
		this.lockManId = lockManId;
	}

	public String getLockMan() {
		return this.lockMan;
	}

	public void setLockMan(String lockMan) {
		this.lockMan = lockMan;
	}

	public Date getLockDate() {
		return this.lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public Date getLockTime() {
		return this.lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public String getLockNote() {
		return this.lockNote;
	}

	public void setLockNote(String lockNote) {
		this.lockNote = lockNote;
	}

	public String getItemRepeatable() {
		return itemRepeatable;
	}

	public void setItemRepeatable(String itemRepeatable) {
		this.itemRepeatable = itemRepeatable;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getLockStorageId() {
		return lockStorageId;
	}

	public void setLockStorageId(String lockStorageId) {
		this.lockStorageId = lockStorageId;
	}

	public String getLockStorage() {
		return lockStorage;
	}

	public void setLockStorage(String lockStorage) {
		this.lockStorage = lockStorage;
	}

}