/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 17, 2015 by liyzh
 * Last edited on Mar 17, 2015 by liyzh
 * 
 * 说明： 系统角色管理
 */
package com.tanry.business.module.setting.user.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.setting.SysRoleBean;
import logic.store.BranchTypeBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.BranchType;
import action.common.BaseAction;

import com.tanry.business.module.setting.user.pojo.SysRole;
import com.tanry.business.module.setting.user.service.RoleManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class RoleManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private SysRoleBean sysRoleBean;
	private RoleManageService roleManageService;
	private BranchTypeBean branchTypeBean;

	private String roleId;
	private String roleIds;
	private String branchType;

	private SysRole role;
	private List<BranchType> branchTypeLst;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(roleId)) {
			role = sysRoleBean.queryById(roleId);
		} else {// 新增时设置部门类型
			branchTypeLst = branchTypeBean.query();
		}
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkRoleId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		SysRole role = sysRoleBean.queryById(roleId);
		if (role != null) {
			exist = true;
		}
		JSONObject result = new JSONObject();
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<SysRole> roleLst = null;
		if (TextUtil.isEmpty(branchType)) {
			roleLst = sysRoleBean.query();
		} else {
			roleLst = sysRoleBean.queryByBranchType(branchType);
		}

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (SysRole sysRole : roleLst) {
			JSONObject jObject = JSONObject.fromObject(sysRole);
			jObject.put("rownumber", rownumber);
			arr.add(jObject);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(roleId)) {
			sysRoleBean.updateEntity(roleId, role);
		} else {
			sysRoleBean.saveEntity(role);
		}
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 角色下如果存在用户，不可删除
	 */
	public void deletable() throws NoPrivilegeException, SQLException, NoConnection {
		boolean deletable = sysRoleBean.deletable(roleId);

		JSONObject result = new JSONObject();
		result.put("deletable", deletable);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		roleManageService.deleteRole(roleIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public List<BranchType> getBranchTypeLst() {
		return branchTypeLst;
	}

	public void setSysRoleBean(SysRoleBean sysRoleBean) {
		this.sysRoleBean = sysRoleBean;
	}

	public void setBranchTypeBean(BranchTypeBean branchTypeBean) {
		this.branchTypeBean = branchTypeBean;
	}

	public void setRoleManageService(RoleManageService roleManageService) {
		this.roleManageService = roleManageService;
	}

}
