/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 17, 2015 by liyzh
 * Last edited on May 6, 2015 by liyzh
 * 
 * 说明： 系统用户管理，设置角色，设置多部门
 */
package com.tanry.business.module.setting.user.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.setting.SysUserBean;
import logic.module.setting.UserToBranchBean;
import logic.module.setting.UserToRoleBean;
import logic.store.BranchBean;
import logic.store.BranchTypeBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import pojo.store.Branch;
import action.common.BaseAction;

import com.tanry.business.common.menu.Menu;
import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.business.module.setting.user.service.UserManageService;
import com.tanry.business.module.setting.user.vo.UserInfo;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.business.module.setting.user.vo.SysMenu;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.SysOption;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class UserManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private SysUserBean sysUserBean;
	private BranchBean branchBean;
	private BranchTypeBean branchTypeBean;
	private UserToRoleBean userToRoleBean;
	private UserToBranchBean userToBranchBean;
	private UserManageService userManageService;

	private String userId;
	private String roleId;
	private String branchId;
	private Map<String,String> idName;
	private Map<String,String> idRoot;
	/**
	 * 一个用户所在的多个部门编号，逗号分隔
	 */
	private String branchIds;
	private String branchType;

	private SysUser user;

	private String jsonData;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(userId)) {
			user = sysUserBean.queryById(userId);
		}
		return SUCCESS;
	}

	/**
	 * 设置角色
	 */
	public String setRoleView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(userId)) {
			UserInfo userInfo = sysUserBean.queryUserInfo(userId);
			roleId = userInfo.getRoleId();
		}
		// 选择角色时需要根据部门类型来查询角色列表
		Branch branch = branchBean.GetBranchById(branchId);
		branchType = branch.getBranchType();
		return SUCCESS;
	}

	/**
	 * 设置多部门，不包括本部门（因为一个用户至少要从属于一个部门）
	 */
	public String setDeptView() throws NoPrivilegeException, SQLException, NoConnection {
		List<Branch> branchLst = userToBranchBean.queryBranch(userId);
		List<String> idLst = new ArrayList<String>();
		for (Branch branch : branchLst) {
			String bId = branch.getBranchId();
			if (branchId.equals(bId)) {
				continue;// 不包括本部门
			}
			idLst.add(bId);
		}
		setBranchIds(StringUtils.join(idLst, ","));

		// 多部门只能在同一部门类型下设置
		Branch branch = branchBean.GetBranchById(branchId);
		branchType = branch.getBranchType();
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkUserId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		SysUser user = sysUserBean.queryById(userId);
		if (user != null) {
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

	/**
	 * 按部门类型组织部门树
	 */
	public void queryDeptTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有部门");
		arr.add(root);

		List<Map> deptLst = branchTypeBean.queryDeptTree();
		int rownumber = getStart();
		for (Map branch : deptLst) {
			JSONObject json = JSONObject.fromObject(branch);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据部门类型查询部门列表数据，用来设置多部门，不包括本部门（因为一个用户至少要从属于一个部门）
	 */
	public void queryDept() throws NoPrivilegeException, SQLException, NoConnection {
		List<Branch> branchLst = branchBean.queryByTypeName(BranchTypeEnum.getEnum(branchType), "");

		JSONArray arr = new JSONArray();
		for (Branch branch : branchLst) {
			if (branchId.equals(branch.getBranchId())) {// 不包括本部门
				continue;
			}
			JSONObject jObject = JSONObject.fromObject(branch);
			arr.add(jObject);
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据部门查询用户列表
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<SysUser> userLst = sysUserBean.query(branchId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (SysUser sysUser : userLst) {
			if ("admin".equals(sysUser.getUserId())) {
				continue;
			}
			JSONObject jObject = JSONObject.fromObject(sysUser);
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
		if (!TextUtil.isEmpty(userId)) {
			sysUserBean.updateEntity(userId, user);
		} else {
			user.setPassword(TextUtil.MD5(SysOption.INIT_PWD));
			userManageService.addUser(user, roleId);
		}
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchRole() throws NoPrivilegeException, SQLException, NoConnection {
		
		JSONArray arr = new JSONArray();

		List<Map> deptLst = sysUserBean.roleSearch();
		int rownumber = getStart();
		for (Map branch : deptLst) {
			JSONObject json = JSONObject.fromObject(branch);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchPower() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		
		JSONArray arr = new JSONArray();

		List<Map> deptLst = sysUserBean.powerSearch();
		List<SysMenu> menuLst = Menu.query();
		idName = new HashMap<String,String>();
		idRoot = new HashMap<String,String>();
		for (SysMenu menu : menuLst){
			idName.put(menu.getId(), menu.getName());
			String path = menu.getPath();
			String[] menuArr = path.split("/");
			idRoot.put(menu.getId(), menuArr[1]);
		}
		int rownumber = getStart();
		for (Map branch : deptLst) {
			JSONObject json = JSONObject.fromObject(branch);
			json.put("rownumber", rownumber);
			json.put("powerName", idName.get(json.getString("powerId")));
			json.put("powerRoot", idName.get(idRoot.get(json.getString("powerId"))));
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		userManageService.deleteUser(userId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设定用户所属的角色，暂定一对一
	 */
	public void setUserRole() throws NoPrivilegeException, SQLException, NoConnection {
		userToRoleBean.saveEntity(userId, roleId);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置多部门，增加或减少
	 */
	public void setMultiDept() throws NoPrivilegeException, SQLException, NoConnection {
		userManageService.setMultiDept(userId, jsonData);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
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

	public void setBranchTypeBean(BranchTypeBean branchTypeBean) {
		this.branchTypeBean = branchTypeBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setUserManageService(UserManageService userManageService) {
		this.userManageService = userManageService;
	}

}
