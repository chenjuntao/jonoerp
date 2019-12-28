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
 * 说明：门店多品牌
 */
package pojo.store;

public class Brand {	
	
	/**
	 * 品牌分组编码
	 */
	private String brandId;
	/**
	 * 品牌分组名称
	 */
	private String brandName;
	/**
	 * 品牌分组备注信息
	 */
	private String brandNote;

	public String getBrandId() {
		return this.brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandNote() {
		return this.brandNote;
	}

	public void setBrandNote(String brandNote) {
		this.brandNote = brandNote;
	}

}