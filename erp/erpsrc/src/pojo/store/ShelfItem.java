/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Apr 16 10:47:58 CST 2015 by lyz
 * Last edited on Thu Apr 16 10:47:58 CST 2015 by lyz
 * 
 * 说明：库位与物料关系表，主要供物流中心捡货使用
 */
package pojo.store;

public class ShelfItem {	
	
	/**
	 * 库位
	 */
	private String shelfId;
	/**
	 * 商品编码
	 */
	private String itemId;
	/**
	 * 库位优先级（0最高级，1，2，3...依此降低）
	 */
	private Integer priority;

	public String getShelfId() {
		return this.shelfId;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}