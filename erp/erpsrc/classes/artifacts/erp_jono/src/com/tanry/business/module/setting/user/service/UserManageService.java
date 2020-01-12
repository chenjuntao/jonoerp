/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on May 4, 2015 by liyzh
 * Last edited on May 4, 2015 by liyzh
 * 
 * 说明： 用户管理
 */
package com.tanry.business.module.setting.user.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.module.setting.SysUserBean;
import logic.module.setting.UserToBranchBean;
import logic.module.setting.UserToRoleBean;
import net.sf.json.JSONArray;

import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.business.module.setting.user.pojo.UserToBranch;
import com.tanry.framework.acl.NoPrivilegeException;

public class UserManageService {

	private SysUserBean sysUserBean;
	private UserToBranchBean userToBranchBean;
	private UserToRoleBean userToRoleBean;

	/**
	 * 新增用户时必须选择一个角色
	 * 
	 * @param user
	 * @param roleId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addUser(SysUser user, String roleId) throws NoPrivilegeException, SQLException, NoConnection {
		sysUserBean.saveEntity(user);

		UserToBranch relation = new UserToBranch();
		relation.setUserId(user.getUserId());
		relation.setBranchId(user.getBranchId());
		relation.setIsPrimary("0");
		userToBranchBean.saveEntity(relation);

		userToRoleBean.saveEntity(user.getUserId(), roleId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteUser(String userIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] userIdArr = userIds.split(",");

		// 删除用户，暂时只删除用户表相关信息
		for (int i = 0; i < userIdArr.length; i++) {
			String userId = userIdArr[i];
			sysUserBean.deleteEntity(userId);
		}
	}

	/**
	 * 设置多部门，增加或减少
	 * 
	 * @param userId
	 * @param jsonData
	 */
	public void setMultiDept(String userId, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		JSONArray addArr = JSONArray.fromObject(arr.get(0));
		JSONArray delArr = JSONArray.fromObject(arr.get(1));
		if (!addArr.isEmpty()) {
			for (Object ele : addArr) {
				UserToBranch relation = new UserToBranch();
				relation.setUserId(userId);
				relation.setBranchId((String) ele);
				relation.setIsPrimary("1");
				userToBranchBean.saveEntity(relation);
			}
		}
		if (!delArr.isEmpty()) {
			userToBranchBean.deleteEntity(userId, delArr);
		}
	}

	public void setSysUserBean(SysUserBean sysUserBean) {
		this.sysUserBean = sysUserBean;
	}

	public void setUserToRoleBean(UserToRoleBean userToRoleBean) {
		this.userToRoleBean = userToRoleBean;
	}

	public void setUserToBranchBean(UserToBranchBean userToBranchBean) {
		this.userToBranchBean = userToBranchBean;
	}
}
