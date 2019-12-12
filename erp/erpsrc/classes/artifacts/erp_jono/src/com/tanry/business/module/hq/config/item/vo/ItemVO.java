/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Oct 23, 2014 by liyzh
 * Last edited on Oct 23, 2014 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.item.vo;

import java.util.Date;

public class ItemVO {
	private String itemId; // 原材料、半成品、成品编码

	private String itemName; // 名称

	private String itemType; // 类型(原材料1/半成品2/成品3)

	private String categoryId; // 类别编码
	private String categoryName;
	private String itemDimension; // 库存（基本）单位（出品例牌）

	private String queryCode; // 助记码

	private String shelfName;// 库位
	/**
	 * 规格
	 */
	private String itemSpecification;
	/**
	 * 箱子类型(冷藏/非冷藏)
	 */
	private String boxType;
	/**
	 * 是否启用(0未启用，1已启用)
	 */
	private Integer enabled;
	/**
	 * 生效日期(出品新增和修改定时生效)
	 */
	private Date effectDate;
	/**
	 * 原材料和半成品对外的几种价格
	 */
	private Double purchasePrice;
	private Double benchmarkPrice;
	private Double joinPrice;
	private Double retailPrice;
	private Double wholesalePrice;

	/**
	 * 出品和半成品对内的几种价格（售卖价，成本价） 半成品有些人格分裂，既有售卖价又有进货价
	 */
	private Double salePrice;
	private Double costPrice;

	/**
	 * 多品牌多价格组
	 */
	private Double groupPrice;

	/**
	 * 显示半成品是存在成本卡
	 */
	private Boolean hasTherapy;

	private String deliveryType;

	private String mainSuppiler;


	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getMainSuppiler() {
		return mainSuppiler;
	}

	public void setMainSuppiler(String mainSuppiler) {
		this.mainSuppiler = mainSuppiler;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getItemDimension() {
		return itemDimension;
	}

	public void setItemDimension(String itemDimension) {
		this.itemDimension = itemDimension;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getItemSpecification() {
		return itemSpecification;
	}

	public void setItemSpecification(String itemSpecification) {
		this.itemSpecification = itemSpecification;
	}

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

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public String getBoxType() {
		return boxType;
	}

	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	public Boolean getHasTherapy() {
		return hasTherapy;
	}

	public void setHasTherapy(Boolean hasTherapy) {
		this.hasTherapy = hasTherapy;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Double getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	
	//以下仅用作出品----------------------------------------------------------------------------------------------------
	private int bInHandPro;//手持下载
	private int bDisFood;//出品折扣
	private int bDisCount;//照单折扣

	//（出品）手持下载
	public int getbInHandPro() {
		return bInHandPro;
	}

	//（出品）手持下载
	public void setbInHandPro(int bInHandPro) {
		this.bInHandPro = bInHandPro;
	}

	//（出品）出品折扣
	public int getbDisFood() {
		return bDisFood;
	}

	//（出品）出品折扣
	public void setbDisFood(int bDisFood) {
		this.bDisFood = bDisFood;
	}

	//（出品）照单折扣
	public int getbDisCount() {
		return bDisCount;
	}

	//（出品）照单折扣
	public void setbDisCount(int bDisCount) {
		this.bDisCount = bDisCount;
	}
}
