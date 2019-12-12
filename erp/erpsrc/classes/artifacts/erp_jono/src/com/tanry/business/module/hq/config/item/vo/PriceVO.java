/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 12, 2015 by liyzh
 * Last edited on Mar 12, 2015 by liyzh
 * 
 * 说明： 新增时初始化几种特定价格
 */
package com.tanry.business.module.hq.config.item.vo;

public class PriceVO {
	private Double purchasePrice;
	private Double benchmarkPrice;
	private Double joinPrice;
	private Double retailPrice;
	private Double wholesalePrice;

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getBenchmarkPrice() {
		return benchmarkPrice;
	}

	public void setBenchmarkPrice(Double benchmarkPrice) {
		this.benchmarkPrice = benchmarkPrice;
	}

	public Double getJoinPrice() {
		return joinPrice;
	}

	public void setJoinPrice(Double joinPrice) {
		this.joinPrice = joinPrice;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(Double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
}
