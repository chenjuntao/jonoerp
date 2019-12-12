/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 31, 2015 by liyzh
 * Last edited on Mar 31, 2015 by liyzh
 * 
 * 说明： 月结关账日设置
 */
package com.tanry.business.module.hq.settle.action;

import java.io.IOException;
import java.sql.SQLException;

import logic.NoConnection;
import logic.store.OptionBean;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.SysOption;

public class CloseDateAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private OptionBean optionBean;

	/**
	 * 关账日，1至28号（取最小月/闰二月的天数）
	 */
	private Integer closeDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String dateStr = optionBean.getOption(SysOption.CLOSE_DATE);
		closeDate = Integer.parseInt(dateStr);
		return SUCCESS;
	}

	/**
	 * 保存关账日的修改
	 */
	public void saveClosedate() throws NoPrivilegeException, SQLException, NoConnection {
		optionBean.setOption(SysOption.CLOSE_DATE, closeDate + "");
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Integer getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Integer closeDate) {
		this.closeDate = closeDate;
	}

	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}
}
