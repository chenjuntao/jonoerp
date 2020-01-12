/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 03 17:07:55 CST 2015 by lyz
 * Last edited on Tue Mar 03 17:07:55 CST 2015 by lyz
 * 
 * 说明：价格组
 */
package pojo.store;

public class PriceGroup {

	/**
	 * 价格组编码
	 */
	private String priceGroupId;
	/**
	 * 价格组名称
	 */
	private String priceGroupName;
	/**
	 * 价格组类型(物流中心销售价格组/门店多品牌价格组)
	 */
	private String priceGroupType;
	/**
	 * 价格组备注信息
	 */
	private String priceGroupNote;
	/**
	 * 价格组所属的父级编号(物流中心编号/门店多品牌分组编号)
	 */
	private String owner;
	private String ownerName;

	public String getPriceGroupId() {
		return this.priceGroupId;
	}

	public void setPriceGroupId(String priceGroupId) {
		this.priceGroupId = priceGroupId;
	}

	public String getPriceGroupName() {
		return this.priceGroupName;
	}

	public void setPriceGroupName(String priceGroupName) {
		this.priceGroupName = priceGroupName;
	}

	public String getPriceGroupType() {
		return this.priceGroupType;
	}

	public void setPriceGroupType(String priceGroupType) {
		this.priceGroupType = priceGroupType;
	}

	public String getPriceGroupNote() {
		return this.priceGroupNote;
	}

	public void setPriceGroupNote(String priceGroupNote) {
		this.priceGroupNote = priceGroupNote;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}