/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 29, 2015 by liyzh
 * Last edited on Jul 29, 2015 by liyzh
 * 
 * 说明： 操作日志工具类
 */
package com.tanry.framework.log;

import java.sql.SQLException;

import logic.NoConnection;
import logic.module.setting.OperateLogBean;

import org.apache.struts2.ServletActionContext;

import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.pojo.OperateLog;
import com.tanry.framework.util.SysUtil;

public class OperateLogUtil {

	public static void record(String menuId, String menuName, String businessId, String operateType, String title,
			String operateDesc) throws NoPrivilegeException, SQLException, NoConnection {
		SysUser user = SysUtil.getLoginUser();
		if (user == null) {// session过期
			return;
		}
		OperateLog logItem = new OperateLog(ServletActionContext.getRequest(), menuId, menuName, businessId,
				operateType, title, operateDesc, user.getUserId(), user.getUserName(), user.getBranchId());
		OperateLogBean logBean = (OperateLogBean) SysUtil.getBean("operateLogBean");
		logBean.saveEntity(logItem);
	}

	/**
	 * 
	 * @param businessId
	 *            单据编码
	 * @param operateType
	 *            操作类型
	 * @param operateDesc
	 *            操作详细说明
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	public static void record(String businessId, String operateType, String operateDesc) throws NoPrivilegeException,
			SQLException, NoConnection {
		record(null, null, businessId, operateType, null, operateDesc);
	}

	public static void record(String businessId, String operateType, String title, String operateDesc)
			throws NoPrivilegeException, SQLException, NoConnection {
		record(null, null, businessId, operateType, title, operateDesc);
	}

	public static void record(String title, String operateDesc) throws NoPrivilegeException, SQLException, NoConnection {
		record(null, null, null, null, title, operateDesc);
	}

	public static void record(String operateDesc) throws NoPrivilegeException, SQLException, NoConnection {
		record(null, null, null, null, null, operateDesc);
	}

}
