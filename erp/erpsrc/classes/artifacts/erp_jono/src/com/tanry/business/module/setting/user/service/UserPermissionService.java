/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 18, 2015 by liyzh
 * Last edited on Mar 18, 2015 by liyzh
 * 
 * 说明： 用户权限管理
 */
package com.tanry.business.module.setting.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.module.setting.PermissionBean;
import com.tanry.business.module.setting.user.pojo.Permission;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.constant.LogType;

public class UserPermissionService {

	private PermissionBean permissionBean;

	/**
	 * 保存更改的权限， by cjt
	 * @param principalId 权限主体ID
	 * @param principalType 权限类型
	 * @param menuIds 菜单集合（逗号分隔）
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void savePermission(String principalId, String principalType, String menuIds)
			throws NoPrivilegeException, SQLException, NoConnection {
		if ("region".equals(principalType)) {// 去掉防止不同类型id重复的前缀
			principalId = principalId.substring(6);
		}
		
		List<String> menuLst = permissionBean.queryAllow(principalId, principalType);// 历史记录的权限菜单
		String[] menuArr = menuLst.toArray(new String[menuLst.size()]);
		String[] newMenuArr = menuIds.toString().split(",");// 最新的权限菜单
		String[] lastArr = minus(menuArr, newMenuArr);// 差集，即被修改的权限菜单
		String[] checked = intersect(newMenuArr, lastArr);// 交集，即被选择的权限菜单
		String[] off = minus(checked, lastArr);// 差集，即取消的权限菜单
		String flog = principalId + "对应的菜单";
		if (checked.length > 0) {
			flog += StringUtils.join(checked, ",") + "的权限被赋予,";
		}

		if (off.length > 0) {
			flog += StringUtils.join(off, ",") + "的权限被取消!";
		}

		OperateLogUtil.record(null, LogType.UPDATE, "修改菜单权限", flog);
		
		for(String checkedId : checked){
			Permission permission = new Permission();
			permission.setPrincipalId(principalId);
			permission.setPrincipalType(principalType);
			permission.setResourceType("menu");
			permission.setResourceId(checkedId);
			permissionBean.saveEntity(permission);
		}
		
		for(String offId : off){
			permissionBean.deleteEntity(principalId,principalType,offId,"menu");
		}
		
//		String[] menuArr = menuIds.split(",");
//		for (String menuId : menuArr) {
//			Permission permission = new Permission();
//			permission.setPrincipalId(principalId);
//			permission.setPrincipalType(principalType);
//			permission.setResourceType("menu");
//			permission.setResourceId(menuId);
//			boolean exist = permissionBean.exist(principalId, principalType, menuId);
//			if (!exist) {
//				permissionBean.saveEntity(permission);
//			}
//		}
//		// 删除除此之外的其它记录
//		permissionBean.deleteExcept(principalId, principalType, menuIds);
	}
	
	// 求两个数组的差集
		public static String[] minus(String[] arr1, String[] arr2) {
			LinkedList<String> list = new LinkedList<String>();
			LinkedList<String> history = new LinkedList<String>();
			String[] longerArr = arr1;
			String[] shorterArr = arr2;
			// 找出较长的数组来减较短的数组
			if (arr1.length > arr2.length) {
				longerArr = arr2;
				shorterArr = arr1;
			}
			for (String str : longerArr) {
				if (!list.contains(str)) {
					list.add(str);
				}
			}
			for (String str : shorterArr) {
				if (list.contains(str)) {
					history.add(str);
					list.remove(str);
				} else {
					if (!history.contains(str)) {
						list.add(str);
					}
				}
			}

			String[] result = {};
			return list.toArray(result);
		}

		// 求两个字符串数组的并集，利用set的元素唯一性
		public static String[] union(String[] arr1, String[] arr2) {
			Set<String> set = new HashSet<String>();
			for (String str : arr1) {
				set.add(str);
			}
			for (String str : arr2) {
				set.add(str);
			}
			String[] result = {};
			return set.toArray(result);
		}

		// 求两个数组的交集
		public static String[] intersect(String[] arr1, String[] arr2) {
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			LinkedList<String> list = new LinkedList<String>();
			for (String str : arr1) {
				if (!map.containsKey(str)) {
					map.put(str, Boolean.FALSE);
				}
			}
			for (String str : arr2) {
				if (map.containsKey(str)) {
					map.put(str, Boolean.TRUE);
				}
			}

			for (Entry<String, Boolean> e : map.entrySet()) {
				if (e.getValue().equals(Boolean.TRUE)) {
					list.add(e.getKey());
				}
			}

			String[] result = {};
			return list.toArray(result);
		}

	public void setPermissionBean(PermissionBean permissionBean) {
		this.permissionBean = permissionBean;
	}
}
