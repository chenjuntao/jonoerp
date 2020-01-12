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
package com.tanry.business.module.setting.user.pojo;

public class SysUser {

	/**
	 * 编号
	 */
	private String userId;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 排序
	 */
	private Integer weight;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 手机号
	 */
	private String telephone;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 所属部门编号
	 */
	private String branchId;

	private String roleName;

	public SysUser() {
	}

	public SysUser(String userId, String userName, String branchId) {
		this.userId = userId;
		this.userName = userName;
		this.branchId = branchId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public SysUser(String userId, String userName, String password, Integer weight, String email, String telephone,
			String gender, String branchId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.weight = weight;
		this.email = email;
		this.telephone = telephone;
		this.gender = gender;
		this.branchId = branchId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		SysUser other = (SysUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}