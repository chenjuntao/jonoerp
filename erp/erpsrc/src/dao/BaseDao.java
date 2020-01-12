/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年6月14日 by cjt
 * Last edited on 2016年6月14日 by cjt
 * 
 * 说明： 数据库访问层
 */
package dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author cjt
 */
public class BaseDao {
	
	//是否使用mysql数据库
	protected boolean beUseMySql = false;
	
	// ----------------------------------------------------
	// 用于定义当前登录的用户属于哪个品牌或企业（company）
	private String companyId = "jono";

	// 读取当前Session中的CompanyId，支持访问多企业不同的数据
	protected String getCom() {
		try {
			RequestAttributes requestAttrs = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) requestAttrs).getRequest();
			companyId = request.getSession().getAttribute("companyId").toString();
//			log.debug(companyId + "-------------callNusm is " + callNum++);
		} catch (Exception ex) {
//			log.debug("get CompanyId has init failed=========," + ex.toString());
		}
		return companyId;
	}
}
