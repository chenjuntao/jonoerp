/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 11 16:14:06 CST 2014 by pw
 * Last edited on Tue Nov 11 16:14:06 CST 2014 by pw
 * 
 * 说明：中央工厂生产领料单表明细
 */
package pojo.form;

import java.util.Date;

public class RequisitionDetail {
	private String formId;
	private String itemId;
	private String itemName;
	private String itemDimension;
	private String itemSpecification;
	private Double itemCount;
	private Double receiveCount;
	private Double differentCount;
	private Date expiredTime;

	private Double receivedCount;
	private Double returnedCount;
	private Double itemPrice;

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDimension() {
		return this.itemDimension;
	}

	public void setItemDimension(String itemDimension) {
		this.itemDimension = itemDimension;
	}

	public String getItemSpecification() {
		return this.itemSpecification;
	}

	public void setItemSpecification(String itemSpecification) {
		this.itemSpecification = itemSpecification;
	}

	public Double getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public Double getReceiveCount() {
		return this.receiveCount;
	}

	public void setReceiveCount(Double receiveCount) {
		this.receiveCount = receiveCount;
	}

	public Double getDifferentCount() {
		return this.differentCount;
	}

	public void setDifferentCount(Double differentCount) {
		this.differentCount = differentCount;
	}

	public Date getExpiredTime() {
		return this.expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Double getReceivedCount() {
		return receivedCount;
	}

	public void setReceivedCount(Double receivedCount) {
		this.receivedCount = receivedCount;
	}

	public Double getReturnedCount() {
		return returnedCount;
	}

	public void setReturnedCount(Double returnedCount) {
		this.returnedCount = returnedCount;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

}