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
 * 说明：出入库表
 */
package pojo.form;

import java.util.Date;

public class StorageInOut {
	/**
	 * 门店ID
	 */
	private String branchId;

	/**
	 * 门店
	 */
	private String branchName;

	/**
	 * 仓库ID
	 */
	private String storageId;

	/**
	 * 仓库
	 */
	private String storageName;

	/**
	 * 原料编码
	 */
	private String itemId;

	/**
	 * 原料名称
	 */
	private String itemName;

	/**
	 * 操作时间
	 */
	private Date operationTime;

	/**
	 * 单据日期
	 */
	private Date businessDate;

	/**
	 * 单据号码
	 */
	private String formId;

	/**
	 * 业务类型
	 */
	private String reason;

	/**
	 * 期初数量
	 */
	private Double orgiCount;

	/**
	 * 入库数量
	 */
	private Double itemCountIn;

	/**
	 * 入库金额
	 */
	private Double itemCountInMoney;

	/**
	 * 出库数量
	 */
	private Double itemCountOut;

	/**
	 * 出库金额
	 */
	private Double itemCountOutMoney;

	/**
	 * 结存数量
	 */
	private Double resultCount;

	/**
	 * 结存单价
	 */
	private Double itemUnitPrice;

	/**
	 * 结存金额
	 */
	private Double resultCountMoney;

	private String unit;

	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public Double getItemCountInMoney() {
		return itemCountInMoney;
	}

	public void setItemCountInMoney(Double itemCountInMoney) {
		this.itemCountInMoney = itemCountInMoney;
	}

	public Double getItemCountOutMoney() {
		return itemCountOutMoney;
	}

	public void setItemCountOutMoney(Double itemCountOutMoney) {
		this.itemCountOutMoney = itemCountOutMoney;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Double getResultCountMoney() {
		return resultCountMoney;
	}

	public void setResultCountMoney(Double resultCountMoney) {
		this.resultCountMoney = resultCountMoney;
	}

	public StorageInOut(String branchId, String storageId, Date businessDate, Date operationTime, String itemId,
			Double itemUnitPrice, Double orgiCount, Double itemCountIn, Double itemCountOut, Double resultCount,
			String formId, String reason) {
		this.branchId = branchId;
		this.storageId = storageId;
		this.businessDate = businessDate;
		this.operationTime = operationTime;
		this.itemId = itemId;
		this.itemUnitPrice = itemUnitPrice;
		this.orgiCount = orgiCount;
		this.itemCountIn = itemCountIn;
		this.itemCountOut = itemCountOut;
		this.resultCount = resultCount;
		this.formId = formId;
		this.reason = reason;

		Date myDate = new Date();
		this.timestamp = myDate.getTime() + "";
	}

	public StorageInOut(String branchId, String storageId, Date businessDate, Date operationTime, String itemId,
			Double itemUnitPrice, Double orgiCount, Double itemCountIn, Double itemCountOut, Double resultCount,
			String formId, String reason, String timeStamp) {
		this.branchId = branchId;
		this.storageId = storageId;
		this.businessDate = businessDate;
		this.operationTime = operationTime;
		this.itemId = itemId;
		this.itemUnitPrice = itemUnitPrice;
		this.orgiCount = orgiCount;
		this.itemCountIn = itemCountIn;
		this.itemCountOut = itemCountOut;
		this.resultCount = resultCount;
		this.formId = formId;
		this.reason = reason;
		this.timestamp = timeStamp;
	}

	public StorageInOut() {
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Date getBusinessDate() {
		return this.businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public Date getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getItemUnitPrice() {
		return this.itemUnitPrice;
	}

	public void setItemUnitPrice(Double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public Double getOrgiCount() {
		return this.orgiCount;
	}

	public void setOrgiCount(Double orgiCount) {
		this.orgiCount = orgiCount;
	}

	public Double getItemCountIn() {
		return this.itemCountIn;
	}

	public void setItemCountIn(Double itemCountIn) {
		this.itemCountIn = itemCountIn;
	}

	public Double getItemCountOut() {
		return this.itemCountOut;
	}

	public void setItemCountOut(Double itemCountOut) {
		this.itemCountOut = itemCountOut;
	}

	public Double getResultCount() {
		return this.resultCount;
	}

	public void setResultCount(Double resultCount) {
		this.resultCount = resultCount;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}