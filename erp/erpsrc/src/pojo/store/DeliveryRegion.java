/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Feb 03 18:04:35 CST 2015 by lyz
 * Last edited on Tue Feb 03 18:04:35 CST 2015 by lyz
 * 
 * 说明：配送区域表
 */
package pojo.store;

public class DeliveryRegion {

	/**
	 * 配送区域ID
	 */
	private String regionId;
	/**
	 * 配送区域名称
	 */
	private String regionName;
	/**
	 * 区域所属物流中心ID
	 */
	private String branchId;
	/**
	 * 配送周期(天)
	 */
	private Integer deliveryCycle;

	private Integer enabled;

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Integer getDeliveryCycle() {
		return this.deliveryCycle;
	}

	public void setDeliveryCycle(Integer deliveryCycle) {
		this.deliveryCycle = deliveryCycle;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}