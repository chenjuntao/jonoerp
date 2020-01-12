/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 28, 2015 by liyzh
 * Last edited on Jan 28, 2015 by liyzh
 * 
 * 说明： TODO
 */
package pojo.store;

public class Therapy {
	private String therapyId; // 配方编码

	private String therapyName;
	private String therapyDimension;
	private String itemId; // 原材料编码

	private String itemName; // 原材料名称
	/**
	 * 库存单位
	 */
	private String stockDimension;
	private String itemDimension; // 配方单位
	private String itemCategory;
	/**
	 * 配方单位相对于库存单位的转换因子(配方单位=库存单位*转换因子)
	 */
	private Double unitConvertFactor;
	private Double itemCount; // 原材料数量

	private String existFlag;// 原料在基本信息中是否存在

	/**
	 * 净料耗率（净料耗量=毛料耗量*净料耗率/转换因子）
	 */
	private Double itemUsageRate;
	/**
	 * 毛料耗量（单位为库存单位）
	 */
	private Double itemGrossCount;
	/**
	 * 进货价
	 */
	private Double purchasePrice;
	private Double purchaseAmt;

	private Double itemPrice;

	/**
	 * 标准价
	 */
	private Double benchmarkPrice;
	private Double benchmarkAmt;
	/**
	 * 原料成本
	 */
	private Double itemAmt;
	/**
	 * 原料类型（主料/辅料/配料）
	 */
	private String itemType;
	private String owner;

	private String itemSpecification;

	public Double getPurchaseAmt() {
		return purchaseAmt;
	}

	public void setPurchaseAmt(Double purchaseAmt) {
		this.purchaseAmt = purchaseAmt;
	}

	public Double getBenchmarkAmt() {
		return benchmarkAmt;
	}

	public void setBenchmarkAmt(Double benchmarkAmt) {
		this.benchmarkAmt = benchmarkAmt;
	}

	public String getTherapyId() {
		return this.therapyId;
	}

	public void setTherapyId(String therapyId) {
		this.therapyId = therapyId;
	}

	public String getTherapyName() {
		return therapyName;
	}

	public void setTherapyName(String therapyName) {
		this.therapyName = therapyName;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDimension() {
		return this.itemDimension;
	}

	public void setItemDimension(String itemDimension) {
		this.itemDimension = itemDimension;
	}

	public Double getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public Double getItemUsageRate() {
		return itemUsageRate;
	}

	public void setItemUsageRate(Double itemUsageRate) {
		this.itemUsageRate = itemUsageRate;
	}

	public Double getItemGrossCount() {
		return itemGrossCount;
	}

	public void setItemGrossCount(Double itemGrossCount) {
		this.itemGrossCount = itemGrossCount;
	}

	public Double getItemAmt() {
		return itemAmt;
	}

	public void setItemAmt(Double itemAmt) {
		this.itemAmt = itemAmt;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Double getUnitConvertFactor() {
		return unitConvertFactor;
	}

	public void setUnitConvertFactor(Double unitConvertFactor) {
		this.unitConvertFactor = unitConvertFactor;
	}

	public String getTherapyDimension() {
		return therapyDimension;
	}

	public void setTherapyDimension(String therapyDimension) {
		this.therapyDimension = therapyDimension;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getStockDimension() {
		return stockDimension;
	}

	public void setStockDimension(String stockDimension) {
		this.stockDimension = stockDimension;
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

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemSpecification() {
		return itemSpecification;
	}

	public void setItemSpecification(String itemSpecification) {
		this.itemSpecification = itemSpecification;
	}

	public String getExistFlag() {
		return existFlag;
	}

	public void setExistFlag(String existFlag) {
		this.existFlag = existFlag;
	}

}
