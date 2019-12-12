/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 17 11:33:49 CST 2015 by lyz
 * Last edited on Tue Mar 17 11:33:49 CST 2015 by lyz
 * 
 * 说明：系统用户表
 */
package com.tanry.business.module.setting.user.vo;

public class UserInfo {

	/**
	 * 编号
	 */
	private String userId;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 所属部门编号
	 */
	private String branchId;
	/**
	 * 部门类型
	 */
	private String branchType;
	/**
	 * 所属角色编号
	 */
	private String roleId;
	/**
	 * 所属角色名称
	 */
	private String roleName;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}