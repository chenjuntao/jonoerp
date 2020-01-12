/**
 * for menu action
 * by yxg
 * 2016-06-20
 */
package com.tanry.business.common.menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.MainTest;

import com.tanry.business.module.setting.user.vo.SysMenu;
import com.tanry.framework.util.TextUtil;

public class Menu {
	public static Logger log = Logger.getLogger("MenuAccessor");
	
	private static List<SysMenu> menuLst;
	private static SysMenu theMenu;
	private static JSONArray menuJsonArr;
	
	private static Map<String, SysMenu> allMenus;//全部菜单列表
	private static List<SysMenu> topMenus;//顶部一级菜单列表
	private static Map<String, List<SysMenu>> subMenus;//左侧二三级菜单列表
	
	/**
	 * 获取所有顶部菜单
	 */
	public static List<SysMenu> getTopMenus() throws IOException{
		if(topMenus == null){
			readMenuJsonFile();
		}
		return topMenus;
	}
	
	/**
	 * 根据顶部菜单ID获取该顶部菜单下属的所有左侧子菜单
	 * @param topMenuId 顶部父级菜单ID
	 */
	public static List<SysMenu> getLeftMenus(String topMenuId){
		if(subMenus.containsKey(topMenuId)){
			return subMenus.get(topMenuId);
		}else{
			return null;
		}
	}
	
	/** 
	 * 根据菜单ID获取左侧菜单，如果没有找到则返回null
	 */
	public static SysMenu getLeftMenuById(String topMenuId, String menuId){
		if(subMenus.containsKey(topMenuId)){
		List<SysMenu> menus = subMenus.get(topMenuId);
			for(SysMenu menu : menus){
				if(menu.getId().equals(menuId)){
					return menu;
				}
			}
		}
		return null;
	}
	
	private static JSONArray getMenuJsonArr() throws IOException{
		if(menuJsonArr == null){
			readMenuJsonFile();
		}
		
		return menuJsonArr;
	}
	
	//读取menu.json文件中的菜单数据, by cjt
	public static void readMenuJsonFile() throws IOException {
		String path = Menu.class.getResource("/").getPath();
		path = path.substring(0, path.indexOf("classes"));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path + "menu.json"), "UTF-8"));
		String data = "";
		String results = "";
		while ((data = br.readLine()) != null) {
			results += data;
		}
		br.close();
		menuJsonArr = JSONArray.fromObject(results); 
		
		//根据菜单数据json遍历获取所有的菜单对象SysMenu
		topMenus = new ArrayList<SysMenu>();
		subMenus = new HashMap<String, List<SysMenu>>();
		
		if(menuJsonArr.size()==1){
			JSONObject menuRoot = menuJsonArr.getJSONObject(0); //所有菜单的根节点
			if(menuRoot.getString("Id").equals("root")){
				//获取顶部一级菜单
				JSONArray menuTopArr = JSONArray.fromObject(menuRoot.get("Children"));
				for(int i=0;i<menuTopArr.size();i++){
					JSONObject menuTop = menuTopArr.getJSONObject(i);
					SysMenu menu0 = new SysMenu("","", menuTop);
					topMenus.add(menu0);
					
					//获取左侧二级菜单
					List<SysMenu> menuSubLst = new ArrayList<SysMenu>();
					JSONArray menuLeftArr = JSONArray.fromObject(menuTop.get("Children"));
					for(int j=0;j<menuLeftArr.size();j++){
						JSONObject menuLeft = menuLeftArr.getJSONObject(j);
						SysMenu menu1 = new SysMenu(menu0.getId(),menu0.getPath(), menuLeft);
						menuSubLst.add(menu1);
						
						//获取左侧三级菜单
						JSONArray menuLeaves = JSONArray.fromObject(menuLeft.get("Children"));
						for(int k=0;k<menuLeaves.size();k++){
							JSONObject menuLeaf = menuLeaves.getJSONObject(k);
							SysMenu menu2 = new SysMenu(menu1.getId(),menu1.getPath(), menuLeaf);
							menuSubLst.add(menu2);
						}
					}
					subMenus.put(menu0.getId(), menuSubLst);
				}
			}else{
				log.error("菜单文件menu.json读取错误!");
			}
		}else{
			log.error("菜单文件menu.json读取错误!");
		}
	}

	public static void saveEntity(SysMenu menu) throws IOException {
		JSONObject jObject = new JSONObject();
		jObject.put("Id", menu.getId());
		jObject.put("Name", menu.getName());
		jObject.put("Path", menu.getPath());
		jObject.put("ParentId", menu.getParentId());
		jObject.put("Priority", menu.getPriority());
		jObject.put("Url", menu.getUrl());
		jObject.put("Description", menu.getDescription());
		jObject.put("ImageName", menu.getImageName());

		JSONArray arr = getMenuJsonArr();
		JSONArray tarr;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.getJSONObject(i).getString("Id").equals("root")) {
				tarr = JSONArray.fromObject(arr.getJSONObject(i).get("Children"));// tarr为上面菜单
																					// 也就是arr的孩子
				arr.getJSONObject(i).remove("Children");
				if (TextUtil.isEmpty(menu.getParentId().toString()) || menu.getParentId().toString() == "null") {// 新增菜单如果parent为空则为上面菜单
					tarr.add(jObject);
				} else {
					for (int j = 0; j < tarr.size(); j++) {// 遍历上面菜单
						JSONArray ttarr = JSONArray.fromObject(tarr.getJSONObject(j).get("Children"));// 左边的一级菜单
						tarr.getJSONObject(j).remove("Children");
						if (!TextUtil.isEmpty(menu.getParentId().toString())
								&& menu.getParentId().toString().equals(tarr.getJSONObject(j).getString("Id"))) {
							ttarr.add(jObject);// 新增的父亲为上面菜单则其为左边一级菜单
						} else {
							for (int k = 0; k < ttarr.size(); k++) {
								JSONArray tttarr = JSONArray.fromObject(ttarr.getJSONObject(k).get("Children"));// 左边的二级菜单即功能
								ttarr.getJSONObject(k).remove("Children");
								if (!TextUtil.isEmpty(menu.getParentId().toString())
										&& menu.getParentId().toString().equals(ttarr.getJSONObject(k).getString("Id"))) {
									if (tttarr.getJSONObject(0).isEmpty())
										tttarr.remove(0);
									tttarr.add(jObject);
								}
								ttarr.getJSONObject(k).put("Children", tttarr);
							}
						}
						tarr.getJSONObject(j).put("Children", ttarr);
					}
				}
				arr.getJSONObject(i).put("Children", tarr);
			}
		}
		String str1 = arr.toString();
		String str2 = MainTest.formatJson(str1);
		String path = Menu.class.getResource("/").getPath();
		path = path.substring(1, path.indexOf("classes"));
		File file = new File(path + "menu.json");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		// FileWriter fw = new FileWriter(file.getAbsoluteFile());
		// BufferedWriter bw = new BufferedWriter(fw);
		bw.write(str2);
		bw.close();
	}

	public static void updateEntity(SysMenu menu) throws IOException {
		JSONObject jObject = new JSONObject();
		jObject.put("Id", menu.getId());
		jObject.put("Name", menu.getName());
		jObject.put("Path", menu.getPath());
		jObject.put("ParentId", menu.getParentId());
		jObject.put("Priority", menu.getPriority());
		jObject.put("Url", menu.getUrl());
		jObject.put("Description", menu.getDescription());
		jObject.put("ImageName", menu.getImageName());

		JSONArray arr = getMenuJsonArr();
		for (int i = 0; i < arr.size(); i++) {
			if (arr.getJSONObject(i).getString("Id").equals("root")) {
				JSONArray tarr = JSONArray.fromObject(arr.getJSONObject(i).get("Children"));
				arr.getJSONObject(i).remove("Children");
				for (int j = 0; j < tarr.size(); j++) {// tarr为上面菜单
					JSONArray ttarr = JSONArray.fromObject(tarr.getJSONObject(j).get("Children"));
					tarr.getJSONObject(j).remove("Children");
					if (tarr.getJSONObject(j).get("Id").toString().equals(menu.getId())) {
						tarr.getJSONObject(j).put("Id", menu.getId());
						tarr.getJSONObject(j).put("Name", menu.getName());
						tarr.getJSONObject(j).put("Path", menu.getPath());
						tarr.getJSONObject(j).put("ParentId", menu.getParentId());
						tarr.getJSONObject(j).put("Priority", menu.getPriority());
						tarr.getJSONObject(j).put("Url", menu.getUrl());
						tarr.getJSONObject(j).put("Description", menu.getDescription());
						tarr.getJSONObject(j).put("ImageName", menu.getImageName());
					}
					for (int k = 0; k < ttarr.size(); k++) {// 左边一级菜单
						JSONArray tttarr = JSONArray.fromObject(ttarr.getJSONObject(k).get("Children"));
						ttarr.getJSONObject(k).remove("Children");
						if (ttarr.getJSONObject(k).get("Id").toString().equals(menu.getId())) {
							ttarr.getJSONObject(k).put("Id", menu.getId());
							ttarr.getJSONObject(k).put("Name", menu.getName());
							ttarr.getJSONObject(k).put("Path", menu.getPath());
							ttarr.getJSONObject(k).put("ParentId", menu.getParentId());
							ttarr.getJSONObject(k).put("Priority", menu.getPriority());
							ttarr.getJSONObject(k).put("Url", menu.getUrl());
							ttarr.getJSONObject(k).put("Description", menu.getDescription());
							ttarr.getJSONObject(k).put("ImageName", menu.getImageName());
						}
						for (int l = 0; l < tttarr.size(); l++) {// 左边二级选项
							JSONObject json = tttarr.getJSONObject(l);
							if (json.get("Id").equals(menu.getId())) {
								tttarr.getJSONObject(l).put("Id", menu.getId());
								tttarr.getJSONObject(l).put("Name", menu.getName());
								tttarr.getJSONObject(l).put("Path", menu.getPath());
								tttarr.getJSONObject(l).put("ParentId", menu.getParentId());
								tttarr.getJSONObject(l).put("Priority", menu.getPriority());
								tttarr.getJSONObject(l).put("Url", menu.getUrl());
								tttarr.getJSONObject(l).put("Description", menu.getDescription());
								tttarr.getJSONObject(l).put("ImageName", menu.getImageName());
							}
						}
						ttarr.getJSONObject(k).put("Children", tttarr);
					}
					tarr.getJSONObject(j).put("Children", ttarr);
				}
				arr.getJSONObject(i).put("Children", tarr);
			}
		}
		String str1 = arr.toString();
		String str2 = MainTest.formatJson(str1);
		String path = Menu.class.getResource("/").getPath();
		path = path.substring(1, path.indexOf("classes"));
		File file = new File(path + "menu.json");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		// FileWriter fw = new FileWriter(file.getAbsoluteFile());
		// BufferedWriter bw = new BufferedWriter(fw);
		bw.write(str2);
		bw.close();
	}


	public static List<SysMenu> queryChildren(String parentId) throws IOException {
		menuLst = new ArrayList<SysMenu>();
		JSONArray arr = getMenuJsonArr();
		JSONArray rarr = null;
		JSONArray tarr;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.getJSONObject(i).getString("Id").equals("root")) {
				tarr = JSONArray.fromObject(arr.getJSONObject(i).get("Children"));
				if (TextUtil.isEmpty(parentId) || parentId == "null") {
					rarr = tarr;
				} else {
					for (int j = 0; j < tarr.size(); j++) {
						JSONArray ttarr = JSONArray.fromObject(tarr.getJSONObject(j).get("Children"));
						if (!TextUtil.isEmpty(parentId) && parentId.equals(tarr.getJSONObject(j).getString("Id"))) {
							rarr = ttarr;
						} else {
							for (int k = 0; k < ttarr.size(); k++) {
								JSONArray tttarr = JSONArray.fromObject(ttarr.getJSONObject(k).get("Children"));
								if (!TextUtil.isEmpty(parentId)
										&& parentId.equals(ttarr.getJSONObject(k).getString("Id"))) {
									rarr = tttarr;
								}
							}
						}
					}
				}
			}
		}
		if (rarr == null)
			return null;
		getMenuLst(rarr);
		return menuLst;
	}

	public static SysMenu queryById(String menuId) throws IOException {
		JSONArray arr = getMenuJsonArr();
		JSONArray tarr;
		tarr = JSONArray.fromObject(arr.getJSONObject(0).get("Children"));
		theMenu = null;
		getMenu(tarr, menuId);
		return theMenu;
	}

	public static List<SysMenu> query() throws IOException {
		JSONArray arr = getMenuJsonArr();
		JSONArray tarr;
		tarr = JSONArray.fromObject(arr.getJSONObject(0).get("Children"));
		menuLst = new ArrayList<SysMenu>();
		getMenuLst(tarr, false);
		return menuLst;
	}

	public static List<SysMenu> query(String menuName, boolean filter) throws IOException {
		JSONArray arr = getMenuJsonArr();
		JSONArray tarr;
		tarr = JSONArray.fromObject(arr.getJSONObject(0).get("Children"));
		menuLst = new ArrayList<SysMenu>();
		getPMenuLst(tarr, menuName);
		return menuLst;
	}

	public static List<SysMenu> query(Set<String> menuIdSet, boolean filter) throws IOException {
		JSONArray arr = getMenuJsonArr();
		JSONArray tarr;
		tarr = JSONArray.fromObject(arr.getJSONObject(0).get("Children"));
		menuLst = new ArrayList<SysMenu>();
		getPMenuLst(tarr, menuIdSet, filter);
		return menuLst;
	}

	public static void removeMenu(SysMenu menu) throws IOException {
		JSONArray arr = getMenuJsonArr();
		arr = JSONArray.fromObject(arr.getJSONObject(0).get("Children"));
		for (int i = 0; i < arr.size(); i++) {
			JSONArray tarr = JSONArray.fromObject(arr.getJSONObject(i).get("Children"));
			arr.getJSONObject(i).remove("Children");// 左边一级菜单
			int fj = -1;
			for (int j = 0; j < tarr.size(); j++) {
				if (menu.getId().equals(tarr.getJSONObject(j).getString("Id")))
					fj = j;
				JSONArray ttarr = JSONArray.fromObject(tarr.getJSONObject(j).get("Children"));
				tarr.getJSONObject(j).remove("Children");
				int fk = -1;
				for (int k = 0; k < ttarr.size(); k++) {
					if (menu.getId().equals(ttarr.getJSONObject(k).getString("Id")))
						fk = k;
				}
				if (fk != -1)
					ttarr.remove(fk);
				tarr.getJSONObject(j).put("Children", ttarr);
			}
			if (fj != -1)
				tarr.remove(fj);
			arr.getJSONObject(i).put("Children", tarr);
		}
		JSONObject root = new JSONObject();
		root.put("Id", "root");
		root.put("Children", arr);
		JSONArray tttarr = new JSONArray();
		tttarr.add(root);
		String str1 = tttarr.toString();
		String str2 = MainTest.formatJson(str1);
		String path = Menu.class.getResource("/").getPath();
		path = path.substring(1, path.indexOf("classes"));
		File file = new File(path + "menu.json");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		// FileWriter fw = new FileWriter(file.getAbsoluteFile());
		// BufferedWriter bw = new BufferedWriter(fw);
		bw.write(str2);
		bw.close();
	}

	public static void getMenuLst(JSONArray arr) {
		for (int i = 0; i < arr.size(); i++) {
			JSONObject json = arr.getJSONObject(i);
			SysMenu menu = new SysMenu();
			menu.setId(!json.has("Id") ? "" : json.getString("Id"));
			menu.setName(!json.has("Name") ? "" : json.getString("Name"));
			menu.setImageName(!json.has("ImageName") ? "" : json.getString("ImageName"));
			menu.setParentId(!json.has("ParentId") ? "" : json.getString("ParentId"));
			menu.setPriority(json.getInt("Priority"));
			menu.setUrl(!json.has("Url") ? "" : json.getString("Url"));
			menu.setPath(!json.has("Path") ? "" : json.getString("Path"));
			if (json.get("Children") != null) {
				menu.setIsLeaf(0);
			} else {
				menu.setIsLeaf(1);
			}
			menuLst.add(menu);
		}
	}

	public static void getMenuLst(JSONArray arr, boolean filter) {
		for (int i = 0; i < arr.size(); i++) {
			JSONObject json = arr.getJSONObject(i);
			if (filter && json.getInt("Priority") == 0)
				continue;
			SysMenu menu = new SysMenu();
			menu.setId(!json.has("Id") ? "" : json.getString("Id"));
			menu.setName(!json.has("Name") ? "" : json.getString("Name"));
			menu.setImageName(!json.has("ImageName") ? "" : json.getString("ImageName"));
			menu.setParentId(!json.has("ParentId") ? "" : json.getString("ParentId"));
			menu.setPriority(json.getInt("Priority"));
			menu.setUrl(!json.has("Url") ? "" : json.getString("Url"));
			menu.setPath(!json.has("Path") ? "" : json.getString("Path"));
			if (json.get("Children") != null) {
				menu.setIsLeaf(0);
				getMenuLst(JSONArray.fromObject(arr.getJSONObject(i).get("Children")), filter);
			} else {
				menu.setIsLeaf(1);
			}
			menuLst.add(menu);
		}
	}

	public static void getMenu(JSONArray arr, String menuId) {
		for (int i = 0; i < arr.size(); i++) {
			JSONObject json = arr.getJSONObject(i);
			SysMenu menu = new SysMenu();
			menu.setId(!json.has("Id") ? "" : json.getString("Id"));
			menu.setName(!json.has("Name") ? "" : json.getString("Name"));
			menu.setImageName(!json.has("ImageName") ? "" : json.getString("ImageName"));
			menu.setParentId(!json.has("ParentId") ? "" : json.getString("ParentId"));
			menu.setPriority(json.getInt("Priority"));
			menu.setUrl(!json.has("Url") ? "" : json.getString("Url"));
			menu.setPath(!json.has("Path") ? "" : json.getString("Path"));
			if (json.get("Children") != null) {
				menu.setIsLeaf(0);
				getMenu(JSONArray.fromObject(arr.getJSONObject(i).get("Children")), menuId);
			} else {
				menu.setIsLeaf(1);
			}
			if (menuId.equals(json.getString("Id"))) {
				theMenu = menu;
			}
		}
	}

	public static void getPMenuLst(JSONArray arr, Set<String> menuIdSet, boolean filter) {
		for (int i = 0; i < arr.size(); i++) {
			JSONObject json = arr.getJSONObject(i);
			if (filter && json.getInt("Priority") == 0)
				continue;
			SysMenu menu = new SysMenu();
			menu.setId(!json.has("Id") ? "" : json.getString("Id"));
			menu.setName(!json.has("Name") ? "" : json.getString("Name"));
			menu.setImageName(!json.has("ImageName") ? "" : json.getString("ImageName"));
			menu.setParentId(!json.has("ParentId") ? "" : json.getString("ParentId"));
			menu.setPriority(json.getInt("Priority"));
			menu.setUrl(!json.has("Url") ? "" : json.getString("Url"));
			menu.setPath(!json.has("Path") ? "" : json.getString("Path"));
			if (json.get("Children") != null) {
				menu.setIsLeaf(0);
				getPMenuLst(JSONArray.fromObject(arr.getJSONObject(i).get("Children")), menuIdSet, filter);
			} else {
				menu.setIsLeaf(1);
			}
			if (menuIdSet.contains(json.getString("Id")))
				menuLst.add(menu);
		}
	}

	public static void getPMenuLst(JSONArray arr, String menuName) {
		for (int i = 0; i < arr.size(); i++) {
			JSONObject json = arr.getJSONObject(i);
			SysMenu menu = new SysMenu();
			menu.setId(!json.has("Id") ? "" : json.getString("Id"));
			menu.setName(!json.has("Name") ? "" : json.getString("Name"));
			menu.setImageName(!json.has("ImageName") ? "" : json.getString("ImageName"));
			menu.setParentId(!json.has("ParentId") ? "" : json.getString("ParentId"));
			menu.setPriority(json.getInt("Priority"));
			menu.setUrl(!json.has("Url") ? "" : json.getString("Url"));
			menu.setPath(!json.has("Path") ? "" : json.getString("Path"));
			if (json.get("Children") != null) {
				menu.setIsLeaf(0);
				getPMenuLst(JSONArray.fromObject(arr.getJSONObject(i).get("Children")), menuName);
			} else {
				menu.setIsLeaf(1);
			}
			if (menuName.indexOf(json.getString("Name")) != -1)
				menuLst.add(menu);
		}
	}

	public static boolean isEquals(String path, String parentId) {
		String[] menuArr = path.split("/");
		for (int i = 0; i < menuArr.length; i++) {
			if (parentId.equals(menuArr[i]))
				return true;
		}
		return false;
	}
}
