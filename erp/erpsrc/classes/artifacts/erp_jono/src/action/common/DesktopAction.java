package action.common;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.module.setting.PermissionBean;
import logic.module.setting.UserToRoleBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.common.menu.Menu;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.business.module.setting.user.vo.SysMenu;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class DesktopAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private PermissionBean permissionBean;
	private UserToRoleBean userToRoleBean;
	private BranchBean branchBean;

	private String parentId;

	private String branchName;

	private Date businessDate;

	/**
	 * 顶级菜单
	 */
	private List<SysMenu> moduleLst;
	private String firstModuleId;
	private String stuta;

	public String topView() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		moduleLst = getTopAllowMenu();
		if (!moduleLst.isEmpty()) {
			firstModuleId = moduleLst.get(0).getId();
		}

		String branchId = getLoginBranchId();
		// businessDate = businessDateBean.getBusinessDate(branchId);
		businessDate = DateTimeUtil.parse(getSessionMap().get("businessDate").toString());
		branchName = branchBean.GetBranchById(branchId).getBranchName();
		stuta = "notLess";
		String newDate = DateTimeUtil.getDate();
		String busDate = DateTimeUtil.getDateStr(businessDate);
		int res = busDate.compareTo(newDate);
		if (res < 0) {
			stuta = "less";	//当前营业日期小于系统时间，需要提示去日结
		}
		return SUCCESS;
	}

	/**
	 * 获取顶部菜单，并根据权限进行过滤
	 */
	private List<SysMenu> getTopAllowMenu()
			throws IOException, NoPrivilegeException, SQLException, NoConnection {
		List<SysMenu> allTopMenus = Menu.getTopMenus();
		List<SysMenu> allowedLst = new ArrayList<SysMenu>();
		if (getLoginUserId().equals("admin")) {  
			//管理员可以查看全部菜单，但是隐藏菜单除外
			for(SysMenu menu : allTopMenus){
				if(menu.getPriority()!=0){ //排除不显示的菜单
					allowedLst.add(menu);
				}
			}
		}else{
			//根据用户Id获取角色ID
			String roleId = userToRoleBean.queryRoleByUserId(getLoginUserId());
			
			//查询该角色所有可访问的菜单Id
			List<String> allowMenuIds = permissionBean.queryAllow(roleId, "role");
			
			//由于权限表中只保存了叶子节点，所以这里需要将叶子节点的父级节点都遍历出来
			for(SysMenu menu : allTopMenus){
				if(menu.getPriority()!=0){ //排除不显示的菜单
					for(String menuId : allowMenuIds){
						if(Menu.getLeftMenuById(menu.getId(), menuId) != null){
							allowedLst.add(menu);
							break;
						}
					}
				}
			}
		}
		
		return allowedLst;
	}

	/**
	 * 获取菜单列表
	 */
	public void listMenu() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		List<SysMenu> allowedLst = getLeftAllowMenu(parentId);
		JSONArray arr = new JSONArray();
		for (SysMenu menu : allowedLst) {
			JSONObject json = JSONObject.fromObject(menu);
			String parentId = menu.getParentId();
			if (TextUtil.isEmpty(parentId)) {
				parentId = "root";
			}
			json.put("parent", parentId);
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取左侧菜单，并根据权限进行过滤
	 */
	private List<SysMenu> getLeftAllowMenu(String topMenuId)
			throws IOException, NoPrivilegeException, SQLException, NoConnection {
		List<SysMenu> leftMenus = Menu.getLeftMenus(topMenuId);
		List<SysMenu> allowedLst = new ArrayList<SysMenu>();
		if (getLoginUserId().equals("admin")) {  
			//管理员可以查看全部菜单，但是隐藏菜单除外
			for (SysMenu menu : leftMenus) {
				if(menu.getPriority()!=0){ //排除不显示的菜单
					allowedLst.add(menu);
				}
			}
		}else{
			//根据用户Id获取角色ID
			String roleId = userToRoleBean.queryRoleByUserId(getLoginUserId());
			
			//查询该角色所有可访问的菜单Id
			List<String> allowMenuIds = permissionBean.queryAllow(roleId, "role");
			
			//由于权限表中只保存了叶子节点，所以这里需要将叶子节点的父级节点都遍历出来
			List<String> parentMenuIds = new ArrayList<String>(); // 叶子节点的父节点ID集合
			for (SysMenu menu : leftMenus) {
				if(menu.getPriority()!=0){ //排除不显示的菜单
					if(allowMenuIds.contains(menu.getId())){
						//获取父节点，防止父节点重复加入
						if(!parentMenuIds.contains(menu.getParentId())){
							SysMenu parent = Menu.getLeftMenuById(topMenuId, menu.getParentId());
							if(parent != null){
								parentMenuIds.add(menu.getParentId());
								allowedLst.add(parent);
							}
						}
						
						allowedLst.add(menu);
					}
				}
			}
		}
		
		return allowedLst;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateTime(businessDate, DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

	public String getBranchName() {
		return branchName;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<SysMenu> getModuleLst() {
		return moduleLst;
	}

	public String getFirstModuleId() {
		return firstModuleId;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String getStuta() {
		return stuta;
	}

	public void setPermissionBean(PermissionBean permissionBean) {
		this.permissionBean = permissionBean;
	}

	public void setUserToRoleBean(UserToRoleBean userToRoleBean) {
		this.userToRoleBean = userToRoleBean;
	}

}