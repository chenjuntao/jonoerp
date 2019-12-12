/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 26, 2014 by liyzh
 * Last edited on Aug 26, 2014 by liyzh
 * 
 * 说明： 餐厅要货、外部订货商订货需求表明细
 */
package pojo.form;

import java.util.Date;

public class PurchasingDetail {

	private String formId;
	private String itemId;
	private String itemName;
	private String itemDimension;
	private String itemSpecification;
	private String itemCategory;
	private Double itemCount;
	private String receiverId;
	/**
	 * 收货部门(只针对越库有效,其他为空)
	 */
	private String receiver;
	private Double itemUnitPrice;
	private Double payAmt;
	private Date expiredTime;

	private Double receivePrice;
	private Double receiveAmt;

	private String rownumber;
	private Double supplyCycle;

	public String getRownumber() {
		return rownumber;
	}

	public void setRownumber(String rownumber) {
		this.rownumber = rownumber;
	}

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

	public Double getItemCount() {
		return itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public Double getReceivePrice() {
		return receivePrice;
	}

	public void setReceivePrice(Double receivePrice) {
		this.receivePrice = receivePrice;
	}

	public Double getReceiveAmt() {
		return receiveAmt;
	}

	public void setReceiveAmt(Double receiveAmt) {
		this.receiveAmt = receiveAmt;
	}

	public Double getSupplyCycle() {
		return supplyCycle;
	}

	public void setSupplyCycle(Double supplyCycle) {
		this.supplyCycle = supplyCycle;
	}

}
