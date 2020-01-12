/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 18 14:47:03 CST 2014 by pw
 * Last edited on Tue Nov 18 14:47:03 CST 2014 by pw
 * 
 * 说明：仓库存储信息表
 */
package pojo.store;

import java.util.Date;

public class Storage {
	private String storageId;
	private String itemId;
	private Double itemCount;
	private String shelfId;
	private Date lastInTime;
	private Date expiredTime;

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

	public Double getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public String getShelfId() {
		return this.shelfId;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	public Date getLastInTime() {
		return this.lastInTime;
	}

	public void setLastInTime(Date lastInTime) {
		this.lastInTime = lastInTime;
	}

	public Date getExpiredTime() {
		return this.expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

}