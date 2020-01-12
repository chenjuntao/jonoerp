/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Jul 17 16:50:11 CST 2015 by pw
 * Last edited on Fri Jul 17 16:50:11 CST 2015 by pw
 * 
 * 说明：单据操作版本号表
 */
package pojo.form;

import java.util.Date;

public class OperationVersion {

	/**
	 * 初始版本号
	 */
	public static final int INIT_VERSION = 1;

	/**
	 * 已保存
	 */
	public static final String SAVED = "已保存";

	/**
	 * 已编辑
	 */
	public static final String EDITED = "已编辑";

	/**
	 * 已作废
	 */
	public static final String CANCELED = "已作废";

	/**
	 * 已删除
	 */
	public static final String DELETED = "已删除";

	/**
	 * 已结案
	 */
	public static final String FINISHED = "已结案";

	/**
	 * 已审核
	 */
	public static final String AUDITED = "已审核";

	/**
	 * 操作人编码
	 */
	private String operationId;
	/**
	 * 操作人姓名
	 */
	private String operationName;
	/**
	 * 操作时间
	 */
	private Date operationTime;
	/**
	 * 版本号
	 */
	private Integer version;
	/**
	 * 操作内容 1.已保存 2.已编辑 3.已作废 4.已删除 5.已结案 6.审核
	 */
	private String operationContent;

	private String formId;

	public OperationVersion(String operationId, String operationName, Date operationTime, Integer version,
			String operationContent, String formId) {
		this.operationId = operationId;
		this.operationName = operationName;
		this.operationTime = operationTime;
		this.version = version;
		this.operationContent = operationContent;
		this.formId = formId;
	}

	public OperationVersion() {
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getOperationId() {
		return this.operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getOperationName() {
		return this.operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Date getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOperationContent() {
		return this.operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

}