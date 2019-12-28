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

public class RequestDetail {
	private String rownumber;
	private String formId;
	private String itemId;
	private String intlBarCode;
	private String itemName;
	private String itemCategory;
	private String itemDimension;
	private String itemSpecification;
	private Double amtTTCNY1;
	private Double amtTTCNY2;
	private Double amtTTCNY3;
	private Double requireCount1;
	private Double requireCount2;
	private Double requireCount3;
	private Double inventory;
	private Double suggestCount1;
	private Double suggestCount2;
	private Double suggestCount3;
	private Double orderCount;
	private Double itemUnitPrice;
	private Double payAmt;

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

	public String getIntlBarCode() {
		return intlBarCode;
	}

	public void setIntlBarCode(String intlBarCode) {
		this.intlBarCode = intlBarCode;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSpecification() {
		return itemSpecification;
	}

	public void setItemSpecification(String itemSpecification) {
		this.itemSpecification = itemSpecification;
	}

	public Double getAmtTTCNY1() {
		return amtTTCNY1;
	}

	public void setAmtTTCNY1(Double amtTTCNY1) {
		this.amtTTCNY1 = amtTTCNY1;
	}

	public Double getAmtTTCNY2() {
		return amtTTCNY2;
	}

	public void setAmtTTCNY2(Double amtTTCNY2) {
		this.amtTTCNY2 = amtTTCNY2;
	}

	public Double getAmtTTCNY3() {
		return amtTTCNY3;
	}

	public void setAmtTTCNY3(Double amtTTCNY3) {
		this.amtTTCNY3 = amtTTCNY3;
	}

	public Double getRequireCount1() {
		return requireCount1;
	}

	public void setRequireCount1(Double requireCount1) {
		this.requireCount1 = requireCount1;
	}

	public Double getRequireCount2() {
		return requireCount2;
	}

	public void setRequireCount2(Double requireCount2) {
		this.requireCount2 = requireCount2;
	}

	public Double getRequireCount3() {
		return requireCount3;
	}

	public void setRequireCount3(Double requireCount3) {
		this.requireCount3 = requireCount3;
	}

	public Double getInventory() {
		return inventory;
	}

	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}

	public Double getSuggestCount1() {
		return suggestCount1;
	}

	public void setSuggestCount1(Double suggestCount1) {
		this.suggestCount1 = suggestCount1;
	}

	public Double getSuggestCount2() {
		return suggestCount2;
	}

	public void setSuggestCount2(Double suggestCount2) {
		this.suggestCount2 = suggestCount2;
	}

	public Double getSuggestCount3() {
		return suggestCount3;
	}

	public void setSuggestCount3(Double suggestCount3) {
		this.suggestCount3 = suggestCount3;
	}

	public Double getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Double orderCount) {
		this.orderCount = orderCount;
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
}
