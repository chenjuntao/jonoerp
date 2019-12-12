/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 18 11:25:34 CST 2014 by pw
 * Last edited on Tue Nov 18 11:25:34 CST 2014 by pw
 * 
 * 说明：出入库汇总
 */
package pojo.form;

public class StorageInOutSummary {
	/**
	 * 原料编码
	 */
	private String itemId;

	/**
	 * 原料名称
	 */
	private String itemName;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 期初数量
	 */
	private Double orgiCount;

	/**
	 * 期初金额
	 */
	private Double orgiCountMoney;

	/**
	 * 入库数量合计
	 */
	private Double itemCountIn;

	/**
	 * 入库金额合计
	 */
	private Double itemCountInMoney;

	/**
	 * 出库数量合计
	 */
	private Double itemCountOut;

	/**
	 * 出库金额合计
	 */
	private Double itemCountOutMoney;

	/**
	 * 结存数量
	 */
	private Double resultCount;

	/**
	 * 结存金额
	 */
	private Double resultCountMoney;

	/**
	 * 结存单价
	 */
	private Double itemUnitPrice;

	/**
	 * 采购入库数量
	 */
	private Double putinstorage;

	/**
	 * 配送入库数量
	 */
	private Double distribution;

	/**
	 * 配送反审核入库数量
	 */
	private Double antiauditIn;

	/**
	 * 配送反审核出库数量
	 */
	private Double antiauditOut;

	/**
	 * 配送退货数量
	 */
	private Double dreject;

	/**
	 * 采购退货数量
	 */
	private Double preject;

	/**
	 * 原料报损数量
	 */
	private Double rawLoss;

	/**
	 * 出品报损数量
	 */
	private Double dishLoss;

	/**
	 * 调拨入库数量
	 */
	private Double allocateitemIn;

	/**
	 * 调拨出库数量
	 */
	private Double allocateitemOut;

	/**
	 * 盘点入库数量
	 */
	private Double checkstorageIn;

	/**
	 * 盘点出库数量
	 */
	private Double checkstorageOut;

	/**
	 * 理论扣库数量
	 */
	private Double theoryDeduct;

	/**
	 * 门店名称
	 */
	private String branchName;

	/**
	 * 仓库名称
	 */
	private String storageName;
	/**
	 * 原料类别
	 */
	private String itemCategory;

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public Double getTheoryDeduct() {
		return theoryDeduct;
	}

	public void setTheoryDeduct(Double theoryDeduct) {
		this.theoryDeduct = theoryDeduct;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getOrgiCount() {
		return orgiCount;
	}

	public void setOrgiCount(Double orgiCount) {
		this.orgiCount = orgiCount;
	}

	public Double getOrgiCountMoney() {
		return orgiCountMoney;
	}

	public void setOrgiCountMoney(Double orgiCountMoney) {
		this.orgiCountMoney = orgiCountMoney;
	}

	public Double getItemCountIn() {
		return itemCountIn;
	}

	public void setItemCountIn(Double itemCountIn) {
		this.itemCountIn = itemCountIn;
	}

	public Double getItemCountInMoney() {
		return itemCountInMoney;
	}

	public void setItemCountInMoney(Double itemCountInMoney) {
		this.itemCountInMoney = itemCountInMoney;
	}

	public Double getItemCountOut() {
		return itemCountOut;
	}

	public void setItemCountOut(Double itemCountOut) {
		this.itemCountOut = itemCountOut;
	}

	public Double getItemCountOutMoney() {
		return itemCountOutMoney;
	}

	public void setItemCountOutMoney(Double itemCountOutMoney) {
		this.itemCountOutMoney = itemCountOutMoney;
	}

	public Double getResultCount() {
		return resultCount;
	}

	public void setResultCount(Double resultCount) {
		this.resultCount = resultCount;
	}

	public Double getResultCountMoney() {
		return resultCountMoney;
	}

	public void setResultCountMoney(Double resultCountMoney) {
		this.resultCountMoney = resultCountMoney;
	}

	public Double getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(Double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Double getPutinstorage() {
		return putinstorage;
	}

	public void setPutinstorage(Double putinstorage) {
		this.putinstorage = putinstorage;
	}

	public Double getDistribution() {
		return distribution;
	}

	public void setDistribution(Double distribution) {
		this.distribution = distribution;
	}

	public Double getAntiauditIn() {
		return antiauditIn;
	}

	public void setAntiauditIn(Double antiauditIn) {
		this.antiauditIn = antiauditIn;
	}

	public Double getAntiauditOut() {
		return antiauditOut;
	}

	public void setAntiauditOut(Double antiauditOut) {
		this.antiauditOut = antiauditOut;
	}

	public Double getDreject() {
		return dreject;
	}

	public void setDreject(Double dreject) {
		this.dreject = dreject;
	}

	public Double getPreject() {
		return preject;
	}

	public void setPreject(Double preject) {
		this.preject = preject;
	}

	public Double getRawLoss() {
		return rawLoss;
	}

	public void setRawLoss(Double rawLoss) {
		this.rawLoss = rawLoss;
	}

	public Double getDishLoss() {
		return dishLoss;
	}

	public void setDishLoss(Double dishLoss) {
		this.dishLoss = dishLoss;
	}

	public Double getAllocateitemIn() {
		return allocateitemIn;
	}

	public void setAllocateitemIn(Double allocateitemIn) {
		this.allocateitemIn = allocateitemIn;
	}

	public Double getAllocateitemOut() {
		return allocateitemOut;
	}

	public void setAllocateitemOut(Double allocateitemOut) {
		this.allocateitemOut = allocateitemOut;
	}

	public Double getCheckstorageIn() {
		return checkstorageIn;
	}

	public void setCheckstorageIn(Double checkstorageIn) {
		this.checkstorageIn = checkstorageIn;
	}

	public Double getCheckstorageOut() {
		return checkstorageOut;
	}

	public void setCheckstorageOut(Double checkstorageOut) {
		this.checkstorageOut = checkstorageOut;
	}

}