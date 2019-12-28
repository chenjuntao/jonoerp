/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Sep 17 15:20:13 CST 2014 by lyz
 * Last edited on Wed Sep 17 15:20:13 CST 2014 by lyz
 * 
 * 说明：餐厅配送反审核单表明细
 */
package pojo.form;

import java.util.Date;

public class ShippingAntiauditDetail {
	private String formId;
	private String formRefId;
	private String itemId;
	private Double receiveCount;
	private Double payAmt;
	private Double antiauditReceiveCount;
	private Double antiauditReturnCount;
	private Double antiauditPayAmt;

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

	public Double getReceiveCount() {
		return this.receiveCount;
	}

	public void setReceiveCount(Double receiveCount) {
		this.receiveCount = receiveCount;
	}

	public Double getPayAmt() {
		return this.payAmt;
	}

	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}

	public Double getAntiauditReceiveCount() {
		return this.antiauditReceiveCount;
	}

	public void setAntiauditReceiveCount(Double antiauditReceiveCount) {
		this.antiauditReceiveCount = antiauditReceiveCount;
	}

	public Double getAntiauditReturnCount() {
		return this.antiauditReturnCount;
	}

	public void setAntiauditReturnCount(Double antiauditReturnCount) {
		this.antiauditReturnCount = antiauditReturnCount;
	}

	public Double getAntiauditPayAmt() {
		return this.antiauditPayAmt;
	}

	public void setAntiauditPayAmt(Double antiauditPayAmt) {
		this.antiauditPayAmt = antiauditPayAmt;
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

}