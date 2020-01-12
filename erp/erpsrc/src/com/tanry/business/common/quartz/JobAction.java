/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 18, 2015 by liyzh
 * Last edited on Sep 18, 2015 by liyzh
 * 
 * 说明： 手动调用定时任务
 */
package com.tanry.business.common.quartz;

import java.io.IOException;
import java.sql.SQLException;

import logic.NoConnection;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

public class JobAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private JobBean jobBean;

	public void transformProduct() throws NoPrivilegeException, SQLException, NoConnection {
		String description = "将当天生效的出品信息插入到中间表中";
		jobBean.callProc(description, "transformProduct");

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setJobBean(JobBean jobBean) {
		this.jobBean = jobBean;
	}
}
