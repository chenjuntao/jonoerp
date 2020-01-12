/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Sep 17 17:22:11 CST 2014 by pw
 * Last edited on Wed Sep 17 17:22:11 CST 2014 by pw
 * 
 * 说明：餐厅调拨单明细
 */
package pojo.form;

import java.util.Date;

public class TransferDetail {
	private String formId;
	private String itemId;
	private String itemName;
	private String itemDimension;
	private String itemSpecification;
	private String itemCategory;
	private Double applyCount;
	private Double actualCount;
	private Double differentCount;
	private Double unitPrice;
	private Double payAmt;
	private Date expiredTime;

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

	public String getItemCategory() {
		return this.itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Double getApplyCount() {
		return this.applyCount;
	}

	public void setApplyCount(Double applyCount) {
		this.applyCount = applyCount;
	}

	public Double getActualCount() {
		return this.actualCount;
	}

	public void setActualCount(Double actualCount) {
		this.actualCount = actualCount;
	}

	public Double getDifferentCount() {
		return this.differentCount;
	}

	public void setDifferentCount(Double differentCount) {
		this.differentCount = differentCount;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getPayAmt() {
		return this.payAmt;
	}

	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}

	public Date getExpiredTime() {
		return this.expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

}