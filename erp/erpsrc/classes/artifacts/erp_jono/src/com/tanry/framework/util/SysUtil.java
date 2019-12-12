/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jun 18, 2015 by liyzh
 * Last edited on Jun 18, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.framework.util;

import java.io.File;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.framework.service.SysConfigService;

public class SysUtil {
	private static String webappRoot;

	/**
	 * 获取导出CSV文件的目录
	 * @return
	 */
	public static String getExportCsvDir() {
		return getValueByKey("exportcsv.dir");
	}

	public static String getValueByKey(String key) {
		String runtimeParamFilePath = webappRoot + "WEB-INF" + File.separator + "sysOption.properties";
		SysConfigService sysOptionService = SysConfigService.getInstance(runtimeParamFilePath);
		String value = sysOptionService.getString(key);
		return value;

	}

	public static String getWebappRoot() {
		return webappRoot;
	}

	public static void setWebappRoot(String webappRoot) {
		SysUtil.webappRoot = webappRoot;
	}

	public static Object getBean(String beanID) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(beanID);
	}

	public static SysUser getLoginUser() {
		SysUser sysUser = (SysUser) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		return sysUser;
	}
}
