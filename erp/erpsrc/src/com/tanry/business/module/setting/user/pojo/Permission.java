/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 17 15:05:29 CST 2015 by lyz
 * Last edited on Tue Mar 17 15:05:29 CST 2015 by lyz
 * 
 * 说明：系统权限表
 */
package com.tanry.business.module.setting.user.pojo;

public class Permission {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 资源类型，菜单或其它
	 */
	private String resourceType;
	/**
	 * 权限主体类型，用户、部门、角色等
	 */
	private String principalType;
	/**
	 * 操作类型，使用，授权等
	 */
	private Integer operate;
	/**
	 * 资源ID
	 */
	private String resourceId;
	/**
	 * 权限主体ID
	 */
	private String principalId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getPrincipalType() {
		return this.principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	public Integer getOperate() {
		return this.operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getPrincipalId() {
		return this.principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	public Permission() {
		super();
	}

	public Permission(String id, String resourceType, String principalType, Integer operate, String resourceId,
			String principalId) {
		super();
		this.id = id;
		this.resourceType = resourceType;
		this.principalType = principalType;
		this.operate = operate;
		this.resourceId = resourceId;
		this.principalId = principalId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((principalId == null) ? 0 : principalId.hashCode());
		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
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
		Permission other = (Permission) obj;
		if (principalId == null) {
			if (other.principalId != null)
				return false;
		} else if (!principalId.equals(other.principalId))
			return false;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		return true;
	}

}