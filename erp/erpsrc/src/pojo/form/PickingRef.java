/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Apr 23 11:21:45 CST 2015 by lyz
 * Last edited on Thu Apr 23 11:21:45 CST 2015 by lyz
 * 
 * 说明：捡货单表明细对应配送单的关系
 */
package pojo.form;

public class PickingRef {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 相关联的配送单编号
	 */
	private String formRefId;
	/**
	 * 商品编码
	 */
	private String itemId;
	/**
	 * 店名
	 */
	private String branchId;
	/**
	 * 配送单的包装数量
	 */
	private Double itemCount;
	/**
	 * 审核配送单的实际包装数量
	 */
	private Double outCount;
	/**
	 * 物料的标准价
	 */
	private Double itemPrice;
	/**
	 * 配送单位与库存单位的转换因子，即包装因子
	 */
	private Double deliveryFactor;

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormRefId() {
		return this.formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Double getItemCount() {
		return itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public Double getDeliveryFactor() {
		return deliveryFactor;
	}

	public void setDeliveryFactor(Double deliveryFactor) {
		this.deliveryFactor = deliveryFactor;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getOutCount() {
		return outCount;
	}

	public void setOutCount(Double outCount) {
		this.outCount = outCount;
	}

}