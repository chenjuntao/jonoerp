/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Feb 04 17:44:54 CST 2015 by lyz
 * Last edited on Wed Feb 04 17:44:54 CST 2015 by lyz
 * 
 * 说明：配送区域关联的门店表
 */
package pojo.store;

public class DeliveryRegionBranch {

	/**
	 * 配送区域ID
	 */
	private String regionId;
	/**
	 * 区域所关联的门店ID
	 */
	private String branchId;

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public DeliveryRegionBranch() {
	}

	public DeliveryRegionBranch(String regionId, String branchId) {
		this.regionId = regionId;
		this.branchId = branchId;
	}

}