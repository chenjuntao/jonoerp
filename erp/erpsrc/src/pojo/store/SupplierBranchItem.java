/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Feb 07 11:17:04 CST 2015 by lyz
 * Last edited on Sat Feb 07 11:17:04 CST 2015 by lyz
 * 
 * 说明：供应商与门店以及商品的对应关系
 */
package pojo.store;

public class SupplierBranchItem {	
	
	/**
	 * 供应商编号
	 */
	private String supplierId;
	/**
	 * 门店编号
	 */
	private String branchId;
	/**
	 * 商品编号
	 */
	private String itemId;
	/**
	 * 供应商优先级（0最高级主供应商，1，2，3...依此降低）
	 */
	private Integer priority;

	public String getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

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

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}