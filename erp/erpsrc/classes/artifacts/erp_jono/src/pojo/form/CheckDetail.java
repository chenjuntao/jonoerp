/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sun Sep 28 10:22:31 CST 2014 by lyz
 * Last edited on Sun Sep 28 10:22:31 CST 2014 by lyz
 * 
 * 说明：盘点单/清单明细
 */
package pojo.form;

import java.util.Date;

public class CheckDetail {

	private String rownumber;

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 商品编码
	 */
	private String itemId;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 单位
	 */
	private String itemDimension;
	/**
	 * 规格
	 */
	private String itemSpecification;
	/**
	 * 类别
	 */
	private String itemCategory;
	/**
	 * 盘点数量
	 */
	private Double checkCount;
	/**
	 * 理论数量
	 */
	private Double theoryCount;
	/**
	 * 商品有效期
	 */
	private Date expiredTime;
	/**
	 * 商品顺序
	 */
	private Integer itemOrder;

	/**
	 * 商品盘点状态（漏盘:LEAK,正常:NORMAL）
	 */
	private String itemStatus;

	private Double itemPrice;

	private Double itemAmt;

	/**
	 * 盘盈
	 */
	private Double diffCount;

	/**
	 * 盘盈金额
	 */
	private Double diffAmt;
	
	

	public String getRownumber() {
		return rownumber;
	}

	public void setRownumber(String rownumber) {
		this.rownumber = rownumber;
	}

	public Double getDiffCount() {
		return diffCount;
	}

	public void setDiffCount(Double diffCount) {
		this.diffCount = diffCount;
	}

	public Double getDiffAmt() {
		return diffAmt;
	}

	public void setDiffAmt(Double diffAmt) {
		this.diffAmt = diffAmt;
	}

	public Double getItemAmt() {
		return itemAmt;
	}

	public void setItemAmt(Double itemAmt) {
		this.itemAmt = itemAmt;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

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

	public Double getCheckCount() {
		return this.checkCount;
	}

	public void setCheckCount(Double checkCount) {
		this.checkCount = checkCount;
	}

	public Date getExpiredTime() {
		return this.expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getItemOrder() {
		return this.itemOrder;
	}

	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

	public Double getTheoryCount() {
		return theoryCount;
	}

	public void setTheoryCount(Double theoryCount) {
		this.theoryCount = theoryCount;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

}