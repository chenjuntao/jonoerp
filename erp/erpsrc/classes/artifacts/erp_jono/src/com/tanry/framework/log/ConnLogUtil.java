/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on March 14, 2016 by liyzh
 * Last edited on March 14, 2016 by liyzh
 * 
 * 说明： jdbc连接日志工具类
 */
package com.tanry.framework.log;

import java.sql.SQLException;

import logic.module.setting.OperateLogBean;

import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.pojo.ConnectionLog;
import com.tanry.framework.util.SysUtil;

public class ConnLogUtil {

	public static void record(String api, String apiType, Long consumeTime, Integer connInUse)
			throws NoPrivilegeException, SQLException {
		SysUser user = SysUtil.getLoginUser();
		if (user == null) {// session过期
			return;
		}
		ConnectionLog log = new ConnectionLog(api, apiType, consumeTime, connInUse, user.getUserId(),
				user.getUserName());
		OperateLogBean logBean = (OperateLogBean) SysUtil.getBean("operateLogBean");
		logBean.saveConnLog(log);
	}

}
