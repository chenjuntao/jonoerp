/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Feb 06 10:45:30 CST 2015 by lyz
 * Last edited on Fri Feb 06 10:45:30 CST 2015 by lyz
 * 
 * 说明：供应商供应的原材料的供货周期表
 */
package pojo.store;

public class SupplyCycle {

	/**
	 * 订货方ID(物流中心或中央工厂)
	 */
	private String branchId;
	/**
	 * 商品编号
	 */
	private String itemId;
	/**
	 * 供货周期(天)
	 */
	private Double supplyCycle;

	/**
	 * 库存周期（天）
	 */
	private Double inventorysCycle;

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getSupplyCycle() {
		return supplyCycle;
	}

	public void setSupplyCycle(Double supplyCycle) {
		this.supplyCycle = supplyCycle;
	}

	public Double getInventorysCycle() {
		return inventorysCycle;
	}

	public void setInventorysCycle(Double inventorysCycle) {
		this.inventorysCycle = inventorysCycle;
	}

}