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
 * 说明： jdbc连接监控日志
 */
package com.tanry.framework.log.pojo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class ConnectionLog {
	/**
	 * 主键，uuid
	 */
	private String id;
	/**
	 * action, service, dao或sqlj函数名（含包名、类名）
	 */
	private String api;
	/**
	 * action, service, dao或sqlj
	 */
	private String apiType;

	/**
	 * 消耗时间，毫秒
	 */
	private Long consumeTime;
	/**
	 * 同一时间活动连接数
	 */
	private Integer connInUse;
	/**
	 * 服务器IP和端口
	 */
	private String host;
	/**
	 * 操作人员编号
	 */
	private String operatorId;
	/**
	 * 操作人员名称
	 */
	private String operatorName;
	/**
	 * 记录时间，含时分秒
	 */
	private Date logTime;

	/**
	 * 生成日志记录对象
	 */
	public ConnectionLog(String api, String apiType, Long consumeTime, Integer connInUse, String operatorId,
			String operatorName) {
		this.setApi(api);
		this.setApiType(apiType);
		this.setConsumeTime(consumeTime);
		this.setConnInUse(connInUse);
		this.setOperatorId(operatorId);
		this.setOperatorName(operatorName);

		HttpServletRequest req = ServletActionContext.getRequest();
		String host = null;
		try {
			host = InetAddress.getLocalHost() + (req.getServerPort() != 80 ? ":" + req.getServerPort() : "");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.setHost(host);

		this.setLogTime(new Date(System.currentTimeMillis()));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public Long getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Long consumeTime) {
		this.consumeTime = consumeTime;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
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

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public Integer getConnInUse() {
		return connInUse;
	}

	public void setConnInUse(Integer connInUse) {
		this.connInUse = connInUse;
	}

}
