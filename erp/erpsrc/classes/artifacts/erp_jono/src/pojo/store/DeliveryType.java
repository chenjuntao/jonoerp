/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Feb 05 14:48:10 CST 2015 by lyz
 * Last edited on Thu Feb 05 14:48:10 CST 2015 by lyz
 * 
 * 说明：配送模式表
 */
package pojo.store;

public class DeliveryType {	
	
	/**
	 * 配送区域ID
	 */
	private String regionId;
	/**
	 * 物料ID
	 */
	private String itemId;
	/**
	 * 配送模式(统配:UNIFIED/直配:DIRECT/越库:CROSS/自采:SELF)
	 */
	private String deliveryType;

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

}