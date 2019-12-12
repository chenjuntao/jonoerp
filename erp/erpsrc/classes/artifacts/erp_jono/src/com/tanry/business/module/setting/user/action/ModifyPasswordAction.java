/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on May 6, 2015 by liyzh
 * Last edited on May 6, 2015 by liyzh
 * 
 * 说明： 修改用户密码
 */
package com.tanry.business.module.setting.user.action;

import java.io.IOException;
import java.sql.SQLException;

import logic.NoConnection;
import logic.module.setting.SysUserBean;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.SysOption;

public class ModifyPasswordAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private SysUserBean sysUserBean;

	private String userId;
	private String oldPwd;
	private String newPwd;

	/**
	 *  验证老的密码是否正确
	 */
	public void checkOldPwd() throws NoPrivilegeException, SQLException, NoConnection {
		boolean match = false;
		SysUser user = sysUserBean.queryById(userId);
		if (TextUtil.MD5(oldPwd).equals(user.getPassword())) {
			match = true;
		}
		JSONObject result = new JSONObject();
		result.put("match", match);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doModify() throws NoPrivilegeException, SQLException, NoConnection {
		sysUserBean.modifyPwd(userId, TextUtil.MD5(newPwd));
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重置为初始密码
	 */
	public void doReset() throws NoPrivilegeException, SQLException, NoConnection {
		sysUserBean.modifyPwd(userId, TextUtil.MD5(SysOption.INIT_PWD));
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSysUserBean(SysUserBean sysUserBean) {
		this.sysUserBean = sysUserBean;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
