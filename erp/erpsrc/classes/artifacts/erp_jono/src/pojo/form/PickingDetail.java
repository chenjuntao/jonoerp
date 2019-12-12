/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 11 15:05:12 CST 2014 by lyz
 * Last edited on Thu Dec 11 15:05:12 CST 2014 by lyz
 * 
 * 说明：捡货单表明细
 */
package pojo.form;

public class PickingDetail {

	/**
	 * 库位编号
	 */
	private String shelfId;
	/**
	 * 库位名称
	 */
	private String shelfName;
	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 商品编码
	 */
	private String itemId;

	/**
	 * 商品规格
	 */
	private String itemSpecification;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 包装单位
	 */
	private String itemDimension;
	/**
	 * 单位体积
	 */
	private Double unitVolume;
	/**
	 * 商品类别
	 */
	private String itemCategory;
	/**
	 * 捡货数量
	 */
	private Double itemCount;
	/**
	 * 店名
	 */
	private String branchId;
	private String branchName;

	// 小计
	private Double sumCount;

	// 库存
	private Double storageCount;

	public String getItemSpecification() {
		return itemSpecification;
	}

	public void setItemSpecification(String itemSpecification) {
		this.itemSpecification = itemSpecification;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
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

	public String getItemCategory() {
		return this.itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Double getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Double getUnitVolume() {
		return unitVolume;
	}

	public void setUnitVolume(Double unitVolume) {
		this.unitVolume = unitVolume;
	}

	public String getShelfId() {
		return shelfId;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public Double getSumCount() {
		return sumCount;
	}

	public void setSumCount(Double sumCount) {
		this.sumCount = sumCount;
	}

	public Double getStorageCount() {
		return storageCount;
	}

	public void setStorageCount(Double storageCount) {
		this.storageCount = storageCount;
	}

}