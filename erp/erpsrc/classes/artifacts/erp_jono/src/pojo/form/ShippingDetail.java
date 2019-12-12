/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 12, 2014 by liyzh
 * Last edited on Sep 12, 2014 by liyzh
 * 
 * 说明： 餐厅配送单、物流中心出货单、中央工厂出货单明细
 */
package pojo.form;

import java.util.Date;

public class ShippingDetail {
	private String formId;
	private String itemId;
	private String itemName;
	private String itemDimension;
	private String itemSpecification;
	private String itemCategory;
	private Double packagingFactor;
	private String packagingUnit;
	private Double packagingCount;
	private Double receivedCount; // 已出库数
	private Double sumItemCount; // 已入库数
	/**
	 * 订货数
	 */
	private Double requestCount;

	/**
	 * 配送数
	 */
	private Double shippingCount;

	/**
	 * 实发数
	 */
	private Double deliveryCount;

	/**
	 * 实收数
	 */
	private Double receiveCount;

	/**
	 * 差异数
	 */
	private Double differentCount;
	private String differentReason;
	private Double itemUnitPrice;
	private Double payAmt;
	private Date expiredTime;

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
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

	public Double getPackagingFactor() {
		return packagingFactor;
	}

	public void setPackagingFactor(Double packagingFactor) {
		this.packagingFactor = packagingFactor;
	}

	public String getPackagingUnit() {
		return packagingUnit;
	}

	public void setPackagingUnit(String packagingUnit) {
		this.packagingUnit = packagingUnit;
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

	public Double getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(Double receiveCount) {
		this.receiveCount = receiveCount;
	}

	public Double getDifferentCount() {
		return differentCount;
	}

	public void setDifferentCount(Double differentCount) {
		this.differentCount = differentCount;
	}

	public Double getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(Double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public Double getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Double getPackagingCount() {
		return packagingCount;
	}

	public void setPackagingCount(Double packagingCount) {
		this.packagingCount = packagingCount;
	}

	public String getDifferentReason() {
		return differentReason;
	}

	public void setDifferentReason(String differentReason) {
		this.differentReason = differentReason;
	}

	public Double getReceivedCount() {
		return receivedCount;
	}

	public void setReceivedCount(Double receivedCount) {
		this.receivedCount = receivedCount;
	}

	public Double getSumItemCount() {
		return sumItemCount;
	}

	public void setSumItemCount(Double sumItemCount) {
		this.sumItemCount = sumItemCount;
	}

}
