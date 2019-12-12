/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 13, 2015 by liyzh
 * Last edited on Mar 13, 2015 by liyzh
 * 
 * 说明： 编辑成本卡时，选择原料，用于传递原料基本信息，配方单位及其转换因子，进货价等信息
 */
package com.tanry.business.module.hq.config.item.vo;

public class TherapyItem {

	private String itemId; // 原材料、半成品、成品编码
	private String itemName; // 名称
	private String itemNameEng; // 英文名称
	private String itemType; // 类型(原材料1/半成品2/成品3)
	private String categoryId; // 类别编码
	private String itemDimension; // 库存（基本）单位（出品例牌）
	private String queryCode; // 助记码
	/**
	 * 规格
	 */
	private String itemSpecification;
	/**
	 * 配方单位
	 */
	private String recipeUnit;
	/**
	 * 配方单位转换因子
	 */
	private Double recipeFactor;
	/**
	 * 标准价
	 */
	private Double benchmarkPrice;
	/**
	 * 进货价
	 */
	private Double purchasePrice;

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

	public String getItemNameEng() {
		return itemNameEng;
	}

	public void setItemNameEng(String itemNameEng) {
		this.itemNameEng = itemNameEng;
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

	public String getRecipeUnit() {
		return recipeUnit;
	}

	public void setRecipeUnit(String recipeUnit) {
		this.recipeUnit = recipeUnit;
	}

	public Double getRecipeFactor() {
		return recipeFactor;
	}

	public void setRecipeFactor(Double recipeFactor) {
		this.recipeFactor = recipeFactor;
	}

	public Double getBenchmarkPrice() {
		return benchmarkPrice;
	}

	public void setBenchmarkPrice(Double benchmarkPrice) {
		this.benchmarkPrice = benchmarkPrice;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


}
