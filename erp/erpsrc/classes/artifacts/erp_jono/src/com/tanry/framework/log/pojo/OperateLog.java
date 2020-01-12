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
 * 说明： 操作日志
 */
package com.tanry.framework.log.pojo;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class OperateLog {
	/**
	 * 主键，uuid
	 */
	private String id;
	/**
	 * 菜单id，可为空，如果能获取到最好
	 */
	private String menuId;
	/**
	 * 菜单名称，查询数据库获取最准确的名称或者自定义（增加灵活性）
	 */
	private String menuName;
	/**
	 * 菜单地址，从请求中自动获取
	 */
	private String url;
	/**
	 * 要记录日志的业务数据id，比如要货单的编号，可为空
	 */
	private String businessId;

	/**
	 * 操作类型：增加，删除，修改，查询等
	 */
	private String operateType;
	/**
	 * 简短说明
	 */
	private String title;
	/**
	 * 详细说明
	 */
	private String operateDesc;
	/**
	 * 操作人员编号
	 */
	private String operatorId;
	/**
	 * 操作人员名称
	 */
	private String operatorName;
	/**
	 * 操作时间，含时分秒
	 */
	private Date operateTime;

	/**
	 * 登录门店编码
	 */
	private String branchId;

	/**
	 * 生成日志记录对象
	 * 
	 * @param menuName
	 * @param businessId
	 * @param title
	 * @param req
	 *            当前的请求对象
	 * @param operateDesc
	 *            操作人的操作描述
	 * @param operatorId
	 *            操作人id
	 * @param operatorName
	 *            操作人姓名
	 * @param operateType
	 *            操作类型：增加，删除，修改，查询等
	 */
	public OperateLog(HttpServletRequest req, String menuId, String menuName, String businessId, String operateType,
			String title, String operateDesc, String operatorId, String operatorName, String branchId) {
		this.setMenuId(menuId);
		this.setMenuName(menuName);
		if (req != null) {
			String url = req.getServerName() + (req.getServerPort() != 80 ? ":" + req.getServerPort() : "")
					+ req.getRequestURI() + (req.getQueryString() != null ? "?" + req.getQueryString() : "");
			this.setUrl(url);
		}

		this.setBusinessId(businessId);

		this.setOperateType(operateType);
		this.setTitle(title);
		this.setOperateDesc(operateDesc);
		this.setOperatorId(operatorId);
		this.setOperatorName(operatorName);

		this.setOperateTime(new Date(System.currentTimeMillis()));

		this.setBranchId(branchId);
	}
	
	public OperateLog(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

}
