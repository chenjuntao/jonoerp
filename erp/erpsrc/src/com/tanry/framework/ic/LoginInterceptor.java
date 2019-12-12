/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 9, 2015 by liyzh
 * Last edited on Mar 9, 2015 by cjt
 * 
 * 说明： 对用户是否登录作验证
 */
package com.tanry.framework.ic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import action.common.BaseAction;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.tanry.business.module.setting.user.pojo.SysUser;

public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	/*
	 * 验证是否已登录 LOGIN_TYPE = 1 :已登录 LOGIN_TYPE = 0 :未登录
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		BaseAction baseAction = (BaseAction) invocation.getAction();
		Map<String, Object> sessionMap = baseAction.getSessionMap();

		SysUser sysUser = (SysUser) sessionMap.get("loginUser");
		if (sysUser == null) {
			HttpServletRequest request = baseAction.getServletRequest();
			String heardeName = request.getHeader("x-requested-with");

			String hostname = "";
			try {
				hostname = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			String loginResult = Action.LOGIN;
			if (hostname.indexOf("tanry") > -1) {
				loginResult = "tanry-login";
			}

			if (null == heardeName) {// Clinet send a Synchronous request
				return loginResult;
			} else {// ajax request
				return "ajaxerror";
			}
		} else {
			String myLginUserId = baseAction.getMyLoginUserId();
			if (myLginUserId != null) {
				if (!sysUser.getUserId().equals(myLginUserId)) {
					return Action.LOGIN;
				}
			}

		}

		return invocation.invoke();
	}

}
