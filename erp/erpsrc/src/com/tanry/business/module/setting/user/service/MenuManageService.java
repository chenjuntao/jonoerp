/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 20, 2015 by liyzh
 * Last edited on Mar 20, 2015 by liyzh
 * 
 * 说明： 菜单管理，eg:新增、移动，调整排序等
 */
package com.tanry.business.module.setting.user.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logic.NoConnection;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tanry.business.common.menu.Menu;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.business.module.setting.user.vo.SysMenu;
import com.tanry.framework.util.TextUtil;

public class MenuManageService {

	/**
	 * @param menuId
	 * @param menu
	 * @throws IOException 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveMenu(String menuId, SysMenu menu) throws NoPrivilegeException, SQLException, NoConnection, IOException {
		if (!TextUtil.isEmpty(menuId)) {
//			SysMenu oldMenu = menuBean.queryById(menuId);
			SysMenu oldMenu = Menu.queryById(menuId);
			menu.setParentId(oldMenu.getParentId());
			menu.setPath(oldMenu.getPath());
//			menuBean.updateEntity(menu);
			Menu.updateEntity(menu);
		} else {
//			SysMenu parentMenu = menuBean.queryById(menu.getParentId());
			SysMenu parentMenu = Menu.queryById(menu.getParentId());
			String path = parentMenu.getPath() + "/" + menu.getId();
			menu.setPath(path);
			//menuBean.saveEntity(menu);
			Menu.saveEntity(menu);
		}
	}

	/**
	 * @param menuId
	 * @param newParentId
	 * @throws IOException 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void moveMenu(String menuId, String newParentId) throws NoPrivilegeException, SQLException, NoConnection, IOException {
//		SysMenu menu = menuBean.queryById(menuId);
		SysMenu menu = Menu.queryById(menuId);
//		SysMenu parentMenu = menuBean.queryById(newParentId);
		SysMenu parentMenu = Menu.queryById(newParentId);
		if (parentMenu.getId().equals(menu.getParentId())) {// 不需要移动
			return;
		}
		moveMenu(menu, parentMenu);// 使用对象作为参数，尽量减少递归时查询数据库的次数

	}

	/**
	 * @param menu
	 * @param parentMenu
	 * @throws IOException 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void moveMenu(SysMenu menu, SysMenu parentMenu) throws NoPrivilegeException, SQLException, NoConnection, IOException {
		String path = parentMenu.getPath() + "/" + menu.getId();
		menu.setPath(path);
		menu.setParentId(parentMenu.getId());
		Menu.updateEntity(menu);// 更新菜单
		Menu.saveEntity(menu);
//		List<SysMenu> childLst = menuBean.queryChildren(menu.getId());
		List<SysMenu> childLst = Menu.queryChildren(menu.getId());
		if (childLst != null && !childLst.isEmpty()) {
			for (SysMenu child : childLst) {
				moveMenu(child, menu);// 递归移动子菜单
			}
		}
	}

	public List<SysMenu> queryMenu(String menuName, boolean filter) throws SQLException, IOException {
//		List<SysMenu> menuLst = menuBean.query(menuName, filter);
		List<SysMenu> menuLst = Menu.query(menuName, filter);
		if (menuLst != null && !menuLst.isEmpty()) {
			Set<String> idSet = new HashSet<String>();
			for (SysMenu menu : menuLst) {
				idSet.add(menu.getId());

				String path = menu.getPath();
				int endIndex = path.lastIndexOf("/");
				if (endIndex > 0) {
					String parentPath = path.substring(1, endIndex);
					String[] pidArr = parentPath.split("/");
					for (String pid : pidArr) {
						idSet.add(pid);
					}
				}
			}
//			menuLst = menuBean.query(idSet, filter);
			menuLst = Menu.query(idSet, filter);
			return menuLst;
		}
		return null;
	}

}
