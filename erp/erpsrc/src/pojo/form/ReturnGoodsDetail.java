/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Sep 20 20:22:06 CST 2014 by lyz
 * Last edited on Sat Sep 20 20:22:06 CST 2014 by lyz
 * 
 * 说明：餐厅退货单表明细
 */
package pojo.form;

import java.util.Date;

public class ReturnGoodsDetail {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 相关联的配送单或采购单编号
	 */
	private String formRefId;
	/**
	 * 商品编码
	 */
	private String itemId;
	/**
	 * 退货单退货数
	 */
	private Double returnCount;
	/**
	 * 退货单金额
	 */
	private Double returnPayAmt;
	/**
	 * 退货原因
	 */
	private String returnReason;

	/**
	 * fields below use in query only
	 */
	private String itemName;
	private String itemDimension;
	private String itemSpecification;
	private String itemCategory;
	private Double requestCount;
	private Double shippingCount;
	private Double deliveryCount;
	private Double itemUnitPrice;
	private Date expiredTime;
	private Double receiveCount;
	private Double receivePrice;

	/**
	 * used for input return query
	 */
	private Double orderCount;

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

	public Double getReturnCount() {
		return this.returnCount;
	}

	public void setReturnCount(Double returnCount) {
		this.returnCount = returnCount;
	}

	public Double getReturnPayAmt() {
		return this.returnPayAmt;
	}

	public void setReturnPayAmt(Double returnPayAmt) {
		this.returnPayAmt = returnPayAmt;
	}

	public String getReturnReason() {
		return this.returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getItemName() {
		return itemName;
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

	public String getItemSpecification() {
		return itemSpecification;
	}

	public void setItemSpecification(String itemSpecification) {
		this.itemSpecification = itemSpecification;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Double getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(Double requestCount) {
		this.requestCount = requestCount;
	}

	public Double getShippingCount() {
		return shippingCount;
	}

	public void setShippingCount(Double shippingCount) {
		this.shippingCount = shippingCount;
	}

	public Double getDeliveryCount() {
		return deliveryCount;
	}

	public void setDeliveryCount(Double deliveryCount) {
		this.deliveryCount = deliveryCount;
	}

	public Double getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(Double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Double getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(Double receiveCount) {
		this.receiveCount = receiveCount;
	}

	public Double getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Double orderCount) {
		this.orderCount = orderCount;
	}

	public Double getReceivePrice() {
		return receivePrice;
	}

	public void setReceivePrice(Double receivePrice) {
		this.receivePrice = receivePrice;
	}

}