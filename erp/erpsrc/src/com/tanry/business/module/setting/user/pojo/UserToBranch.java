/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon May 04 16:43:58 CST 2015 by lyz
 * Last edited on Mon May 04 16:43:58 CST 2015 by lyz
 * 
 * 说明：系统用户与部门关联表
 */
package com.tanry.business.module.setting.user.pojo;

public class UserToBranch {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 是否主部门
	 */
	private String isPrimary;
	/**
	 * 排序
	 */
	private Integer weight;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 部门ID
	 */
	private String branchId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public UserToBranch() {
		super();
	}

	public UserToBranch(String id, String isPrimary, Integer weight, String userId, String branchId) {
		super();
		this.id = id;
		this.isPrimary = isPrimary;
		this.weight = weight;
		this.userId = userId;
		this.branchId = branchId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserToBranch other = (UserToBranch) obj;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}