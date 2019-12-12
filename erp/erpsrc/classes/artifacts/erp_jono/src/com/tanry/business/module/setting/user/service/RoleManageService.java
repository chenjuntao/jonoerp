/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on May 26, 2015 by liyzh
 * Last edited on May 26, 2015 by liyzh
 * 
 * 说明： 系统角色管理
 */
package com.tanry.business.module.setting.user.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.module.setting.SysRoleBean;
import logic.module.setting.UserToRoleBean;

import com.tanry.framework.acl.NoPrivilegeException;

public class RoleManageService {

	private SysRoleBean sysRoleBean;
	private UserToRoleBean userToRoleBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteRole(String roleIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] roleArr = roleIds.split(",");
		for (String roleId : roleArr) {
			sysRoleBean.deleteEntity(roleId);
			sysRoleBean.deleteMenuUser(roleId);
			userToRoleBean.deleteByRole(roleId);
		}
	}

	public void setSysRoleBean(SysRoleBean sysRoleBean) {
		this.sysRoleBean = sysRoleBean;
	}

	public void setUserToRoleBean(UserToRoleBean userToRoleBean) {
		this.userToRoleBean = userToRoleBean;
	}

}
