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
package com.tanry.business.module.setting.user.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.common.menu.Menu;
import com.tanry.business.module.setting.user.service.MenuManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.business.module.setting.user.vo.SysMenu;
import com.tanry.framework.util.TextUtil;

public class MenuManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private MenuManageService menuManageService;

	private String menuId;
	private String parentId;

	/**
	 * 是否过滤隐藏菜单
	 */
	private String filter;

	private SysMenu menu;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(menuId)) {
		}
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 * 
	 * @throws IOException
	 */
	public void checkMenuId() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		boolean exist = false;
		SysMenu menu = Menu.queryById(menuId);
		JSONObject result = new JSONObject();
		if (menu != null) {
			exist = true;
			result.put("menuName", menu.getName());
		}
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有菜单记录，包含隐藏的菜单（权重为0）
	 */
	public void queryAllMenu() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		//查询所有菜单时，重新读取菜单文件
		Menu.readMenuJsonFile();
		
		//遍历所有的菜单
		JSONArray arr = new JSONArray();
		//所有顶部菜单
		List<SysMenu> allTopMenus = Menu.getTopMenus();
		for (SysMenu topMenu : allTopMenus) {
			JSONObject jsonTop = JSONObject.fromObject(topMenu);
			jsonTop.put("parent", "root");
			arr.add(jsonTop);
			
			//顶部菜单下所有左侧菜单
			List<SysMenu> leftMenus = Menu.getLeftMenus(topMenu.getId());
			for (SysMenu leftMenu : leftMenus) {
				JSONObject jsonLeft = JSONObject.fromObject(leftMenu);
				jsonLeft.put("parent", leftMenu.getParentId());
				arr.add(jsonLeft);
			}
		}
		
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 模糊查询所有可用菜单
	 * 
	 * @throws IOException
	 */
	public void queryMenu() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		JSONArray arr = new JSONArray();

		String menuName = menu.getName();
		if (!"".equals(menuName.trim())) {
			boolean filterHidden = true;// 默认过滤隐藏的菜单
			if ("false".equals(filter)) {
				filterHidden = false;
			}
			List<SysMenu> menuLst = menuManageService.queryMenu(menuName, filterHidden);
			if (menuLst != null) {
				for (SysMenu menu : menuLst) {
					JSONObject json = JSONObject.fromObject(menu);
					String parentId = menu.getParentId();
					if (TextUtil.isEmpty(parentId)) {
						parentId = "root";
					}
					json.put("parent", parentId);
					arr.add(json);
				}
			}
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveMenu() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		menuManageService.saveMenu(menuId, menu);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveMenu() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		menuManageService.moveMenu(menuId, parentId);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SysMenu getMenu() {
		return menu;
	}

	public void setMenu(SysMenu menu) {
		this.menu = menu;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void setMenuManageService(MenuManageService menuManageService) {
		this.menuManageService = menuManageService;
	}
}
