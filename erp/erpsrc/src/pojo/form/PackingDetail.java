/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 11 15:06:51 CST 2014 by lyz
 * Last edited on Thu Dec 11 15:06:51 CST 2014 by lyz
 * 
 * 说明：装箱单明细
 */
package pojo.form;

public class PackingDetail {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 送货门店编码
	 */
	private String branchId;
	/**
	 * 送货门店名称
	 */
	private String branchName;
	/**
	 * 箱子编号
	 */
	private String boxId;
	/**
	 * 箱子名称(eg:海东青1号箱)
	 */
	private String boxName;
	/**
	 * 箱子类别编号
	 */
	private String boxTypeId;
	/**
	 * 箱子类别名称
	 */
	private String boxTypeName;
	/**
	 * 商品编码
	 */
	private String itemId;
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
	 * 单位重量
	 */
	private Double unitWeight;
	/**
	 * 数量
	 */
	private Double itemCount;
	/**
	 * 体积
	 */
	private Double volume;
	/**
	 * 重量
	 */
	private Double weight;
	/**
	 * 箱子体积
	 */
	private Double boxVolume;
	/**
	 * 箱子重量
	 */
	private Double boxWeight;
	/**
	 * 类别
	 */
	private String itemCategory;

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public String getBoxTypeId() {
		return boxTypeId;
	}

	public void setBoxTypeId(String boxTypeId) {
		this.boxTypeId = boxTypeId;
	}

	public String getBoxTypeName() {
		return boxTypeName;
	}

	public void setBoxTypeName(String boxTypeName) {
		this.boxTypeName = boxTypeName;
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
		return itemDimension;
	}

	public void setItemDimension(String itemDimension) {
		this.itemDimension = itemDimension;
	}

	public Double getUnitVolume() {
		return unitVolume;
	}

	public void setUnitVolume(Double unitVolume) {
		this.unitVolume = unitVolume;
	}

	public Double getUnitWeight() {
		return unitWeight;
	}

	public void setUnitWeight(Double unitWeight) {
		this.unitWeight = unitWeight;
	}

	public Double getItemCount() {
		return itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getItemCategory() {
		return this.itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Double getBoxVolume() {
		return boxVolume;
	}

	public void setBoxVolume(Double boxVolume) {
		this.boxVolume = boxVolume;
	}

	public Double getBoxWeight() {
		return boxWeight;
	}

	public void setBoxWeight(Double boxWeight) {
		this.boxWeight = boxWeight;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBoxName() {
		return boxName;
	}

	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}

}