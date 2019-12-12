/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Nov 05 16:09:04 CST 2014 by pw
 * Last edited on Wed Nov 05 16:09:04 CST 2014 by pw
 * 
 * 说明：半成品/成品生产周期表
 */
package pojo.store;

public class ProductionCycle {
	private String branchId;
	private String itemId;
	private Double productionCycle;

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

	public Double getProductionCycle() {
		return productionCycle;
	}

	public void setProductionCycle(Double productionCycle) {
		this.productionCycle = productionCycle;
	}

}