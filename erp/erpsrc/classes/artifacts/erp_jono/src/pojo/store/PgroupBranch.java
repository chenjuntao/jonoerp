/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 03 17:07:55 CST 2015 by lyz
 * Last edited on Tue Mar 03 17:07:55 CST 2015 by lyz
 * 
 * 说明：价格组与门店的对应关系
 */
package pojo.store;

public class PgroupBranch {	
	
	/**
	 * 价格组编码
	 */
	private String priceGroupId;
	/**
	 * 门店编码/外部订货方编码
	 */
	private String branchId;

	public String getPriceGroupId() {
		return this.priceGroupId;
	}

	public void setPriceGroupId(String priceGroupId) {
		this.priceGroupId = priceGroupId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

}