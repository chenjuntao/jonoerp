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
 * 说明： 用户权限管理
 */
package com.tanry.business.module.setting.user.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.setting.PermissionBean;
import logic.module.setting.UserToBranchBean;
import logic.store.BranchTypeBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.common.menu.Menu;
import com.tanry.business.module.setting.user.service.UserPermissionService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.business.module.setting.user.vo.SysMenu;
import com.tanry.framework.util.TextUtil;

@SuppressWarnings("rawtypes")
public class UserPermissionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private PermissionBean permissionBean;
	private UserToBranchBean userToBranchBean;
	private BranchTypeBean branchTypeBean;
	private UserPermissionService userPermissionService;

	private String principalId;
	private String principal;
	private String principalType;
	private String menuIds;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	/**
	 * 查询所有用户，以部门树的方式展现
	 */
	public void queryUserTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有部门");
		arr.add(root);

		List<Map> userLst = userToBranchBean.queryUser();
		for (Map user : userLst) {
			JSONObject json = JSONObject.fromObject(user);
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有角色，按部门类型组织成树形结构
	 */
	public void queryRoleTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有角色");
		arr.add(root);

		List<Map> roleLst = branchTypeBean.queryRoleTree();
		for (Map user : roleLst) {
			JSONObject json = JSONObject.fromObject(user);
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取菜单树形列表
	 * 
	 * @throws IOException
	 */
	public void queryMenuTree() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		
		//查询该角色所有可访问的菜单Id
		List<String> allowMenuIds = permissionBean.queryAllow(principalId, principalType);
		
		//遍历所有的菜单
		JSONArray arr = new JSONArray();
		//所有顶部菜单
		List<SysMenu> allTopMenus = Menu.getTopMenus();
		for (SysMenu topMenu : allTopMenus) {
			if(topMenu.getPriority()==0){ //排除不显示的菜单
				continue;
			}
			JSONObject jsonTop = JSONObject.fromObject(topMenu);
			jsonTop.put("parent", "root");
			arr.add(jsonTop);
			
			//顶部菜单下所有左侧菜单
			List<SysMenu> leftMenus = Menu.getLeftMenus(topMenu.getId());
			for (SysMenu leftMenu : leftMenus) {
				if(leftMenu.getPriority()==0){ //排除不显示的菜单
					continue;
				}
				
				JSONObject jsonLeft = JSONObject.fromObject(leftMenu);
				jsonLeft.put("parent", leftMenu.getParentId());
				
				//判断该角色是否拥有访问该菜单的权限
				if(allowMenuIds.contains(leftMenu.getId())){
					jsonLeft.put("checked", true);
				} else {
					jsonLeft.put("checked", false);
				}
				arr.add(jsonLeft);
			}
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void savePermission() throws NoPrivilegeException, SQLException, NoConnection {
		userPermissionService.savePermission(principalId, principalType, menuIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public void setUserPermissionService(UserPermissionService userPermissionService) {
		this.userPermissionService = userPermissionService;
	}

	public void setPermissionBean(PermissionBean permissionBean) {
		this.permissionBean = permissionBean;
	}

	public void setBranchTypeBean(BranchTypeBean branchTypeBean) {
		this.branchTypeBean = branchTypeBean;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public void setUserToBranchBean(UserToBranchBean userToBranchBean) {
		this.userToBranchBean = userToBranchBean;
	}

}
