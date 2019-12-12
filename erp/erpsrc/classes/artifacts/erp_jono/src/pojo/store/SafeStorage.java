/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 04 17:27:23 CST 2014 by pw
 * Last edited on Thu Dec 04 17:27:23 CST 2014 by pw
 * 
 * 说明：仓库安全库存量
 */
package pojo.store;

public class SafeStorage {

	/**
	 * 仓库标识
	 */
	private String storageId;
	/**
	 * 商品编码
	 */
	private String itemId;
	/**
	 * 商品数量
	 */
	private Double itemSafeCount;

	public String getStorageId() {
		return this.storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getItemSafeCount() {
		return this.itemSafeCount;
	}

	public void setItemSafeCount(Double itemSafeCount) {
		this.itemSafeCount = itemSafeCount;
	}

}