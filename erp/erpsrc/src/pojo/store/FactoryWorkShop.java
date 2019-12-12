/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon Sep 21 10:20:44 CST 2015 by pw
 * Last edited on Mon Sep 21 10:20:44 CST 2015 by pw
 * 
 * 说明：央厂与车间对应关系表
 */
package pojo.store;

public class FactoryWorkShop {	
	
	/**
	 * 央厂编码
	 */
	private String factoryId;
	/**
	 * 生产车间编码
	 */
	private String workOrderId;
	/**
	 * 生产车间名称
	 */
	private String workshop;
	/**
	 * 优先级
	 */
	private Integer priority;

	public String getFactoryId() {
		return this.factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getWorkshop() {
		return this.workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}