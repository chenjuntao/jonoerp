/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Dec 16 11:43:26 CST 2014 by pw
 * Last edited on Tue Dec 16 11:43:26 CST 2014 by pw
 * 
 * 说明：仓库部门关联表
 */
package pojo.store;

public class BranchStorage {

	/**
	 * 部门标识
	 */
	private String branchId;

	private String branchType;
	/**
	 * 仓库标识
	 */
	private String storageId;
	/**
	 * 仓库名称
	 */
	private String storageName;
	/**
	 * 仓库优先级（0最高级，1，2，3...依此降低）
	 */
	private Integer priority;

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getStorageId() {
		return this.storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorageName() {
		return this.storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public BranchStorage() {
	}

	public BranchStorage(String branchId, String branchType, String storageId, String storageName, Integer priority) {
		super();
		this.branchId = branchId;
		this.branchType = branchType;
		this.storageId = storageId;
		this.storageName = storageName;
		this.priority = priority;
	}

	public BranchStorage(String storageId, String storageName) {
		this.storageId = storageId;
		this.storageName = storageName;
	}

}