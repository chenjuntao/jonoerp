/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 03 17:06:46 CST 2015 by lyz
 * Last edited on Tue Mar 03 17:06:46 CST 2015 by lyz
 * 
 * 说明：多品牌分组与门店的对应关系
 */
package pojo.store;

public class BrandBranch {	
	
	/**
	 * 品牌分组编码
	 */
	private String brandId;
	/**
	 * 门店编码
	 */
	private String branchId;

	public String getBrandId() {
		return this.brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

}