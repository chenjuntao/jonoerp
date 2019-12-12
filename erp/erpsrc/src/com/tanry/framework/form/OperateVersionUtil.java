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
package com.tanry.framework.form;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.form.OperationVersionBean;
import pojo.form.OperationVersion;

import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SysUtil;

public class OperateVersionUtil {

	/**
	 * 插入单据版本号信息
	 */
	public static void save(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		SysUser user = SysUtil.getLoginUser();
		OperationVersion operationVersion = new OperationVersion(user.getUserId(), user.getUserName(), new Date(
				System.currentTimeMillis()), OperationVersion.INIT_VERSION, OperationVersion.SAVED, formId);
		OperationVersionBean operationVersionBean = (OperationVersionBean) SysUtil.getBean("operationVersionBean");
		operationVersionBean.saveEntity(operationVersion);
	}

	/**
	 * 更新单据版本号信息
	 */
	public static void update(String formId, Integer currentVersion, String operationContent)
			throws NoPrivilegeException, SQLException, NoConnection {
		SysUser user = SysUtil.getLoginUser();
		if (currentVersion == null) {
			currentVersion = 0;
		}

		OperationVersion operationVersion = new OperationVersion(user.getUserId(), user.getUserName(), new Date(
				System.currentTimeMillis()), (currentVersion + 1), operationContent, formId);
		OperationVersionBean operationVersionBean = (OperationVersionBean) SysUtil.getBean("operationVersionBean");
		operationVersionBean.updateEntity(operationVersion);
	}

}
