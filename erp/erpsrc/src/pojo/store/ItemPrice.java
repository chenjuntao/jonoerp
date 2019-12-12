/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 8, 2015 by liyzh
 * Last edited on Mar 8, 2015 by liyzh
 * 
 * 说明： TODO
 */
package pojo.store;

import java.util.Date;

public class ItemPrice {
	private String itemId;
	private Double itemPrice;
	private String priceType;
	private Integer isCurrent; // 是否是当前生效价格
	private String effectiveFormId; //生效表单ID
	private Date effectTime; // 生效时间
	private String supplierId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public Integer getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Integer isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getEffectiveFormId()
	{
		return effectiveFormId;
	}

	public void setEffectiveFormId(String effectiveFormId)
	{
		this.effectiveFormId = effectiveFormId;
	}
}
