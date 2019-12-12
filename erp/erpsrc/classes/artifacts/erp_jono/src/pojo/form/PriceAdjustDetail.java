/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Feb 12 11:09:52 CST 2015 by lyz
 * Last edited on Thu Feb 12 11:09:52 CST 2015 by lyz
 * 
 * 说明：中央工厂售价调价单、中央工厂采购调价单、物流中心采购调价单、物流中心标准调价单、物流中心加盟调价单、物流中心零售调价单明细
 */
package pojo.form;

public class PriceAdjustDetail {	
	
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
	 * 包装因子
	 */
	private Double itemPackager;
	/**
	 * 原价
	 */
	private Double oldPrice;
	/**
	 * 启用
	 */
	private String enabled;
	
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * 新价
	 */
	private Double newPrice;

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

	public Double getItemPackager() {
		return this.itemPackager;
	}

	public void setItemPackager(Double itemPackager) {
		this.itemPackager = itemPackager;
	}

	public Double getOldPrice() {
		return this.oldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Double getNewPrice() {
		return this.newPrice;
	}

	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}

}