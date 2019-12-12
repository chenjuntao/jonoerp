/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Sep 24 09:38:50 CST 2015 by pw
 * Last edited on Thu Sep 24 09:38:50 CST 2015 by pw
 * 
 * 说明：物料与车间对应关系表
 */
package pojo.store;

public class ItemWorkShop {	
	
	/**
	 * 央厂编码
	 */
	private String factoryId;
	/**
	 * 生产车间编码
	 */
	private String workOrderId;
	/**
	 * 物料编码
	 */
	private String itemId;

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

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}