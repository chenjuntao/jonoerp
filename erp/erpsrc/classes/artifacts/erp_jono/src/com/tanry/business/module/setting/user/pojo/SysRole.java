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
 * 说明：系统角色表
 */
package com.tanry.business.module.setting.user.pojo;

public class SysRole {

	/**
	 * 编号
	 */
	private String roleId;
	/**
	 * 名称
	 */
	private String roleName;
	/**
	 * 部门类型
	 */
	private String branchType;
	private String branchTypeName;
	/**
	 * 描述
	 */
	private String description;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getBranchTypeName() {
		return branchTypeName;
	}

	public void setBranchTypeName(String branchTypeName) {
		this.branchTypeName = branchTypeName;
	}

	public SysRole() {
		super();
	}

	public SysRole(String roleId, String roleName, String branchType, String branchTypeName, String description) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.branchType = branchType;
		this.branchTypeName = branchTypeName;
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		SysRole other = (SysRole) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

}