/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 17 15:04:23 CST 2015 by lyz
 * Last edited on Tue Mar 17 15:04:23 CST 2015 by lyz
 * 
 * 说明：系统用户角色关联表
 */
package com.tanry.business.module.setting.user.pojo;

public class UserToRole {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 排序
	 */
	private Integer weight;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 角色ID
	 */
	private String roleId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public UserToRole() {
		super();
	}

	public UserToRole(String id, Integer weight, String userId, String roleId) {
		super();
		this.id = id;
		this.weight = weight;
		this.userId = userId;
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		UserToRole other = (UserToRole) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}