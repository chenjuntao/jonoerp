/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Apr 16 10:47:57 CST 2015 by lyz
 * Last edited on Thu Apr 16 10:47:57 CST 2015 by lyz
 * 
 * 说明：库位信息表，主要供物流中心捡货使用
 */
package pojo.store;

public class Shelf {	
	
	/**
	 * 编号
	 */
	private String shelfId;
	/**
	 * 名称
	 */
	private String shelfName;
	/**
	 * 描述
	 */
	private String description;

	public String getShelfId() {
		return this.shelfId;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	public String getShelfName() {
		return this.shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}