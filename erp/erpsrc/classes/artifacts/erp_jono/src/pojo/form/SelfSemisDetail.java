/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Jul 08 12:10:12 CST 2016 by pw
 * Last edited on Fri Jul 08 12:10:12 CST 2016 by pw
 * 
 * 说明：半成品加工单明细
 */
package pojo.form;

public class SelfSemisDetail {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 出品/原料编码
	 */
	private String itemId;
	/**
	 * 出品/原料名称
	 */
	private String itemName;
	/**
	 * 出品例牌/原料单位
	 */
	private String itemDimension;
	/**
	 * 出品统一设置为"FOOD"/原料规格
	 */
	private String itemSpecification;
	/**
	 * 出品/原料类别名称
	 */
	private String itemCategory;
	/**
	 * 出品计划生产数量/原料计划消耗数量
	 */
	private Double itemCountPlan;
	/**
	 * 出品实际生产数量/原料实际消耗数量
	 */
	private Double itemCountActual;

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

	public Double getItemCountPlan() {
		return this.itemCountPlan;
	}

	public void setItemCountPlan(Double itemCountPlan) {
		this.itemCountPlan = itemCountPlan;
	}

	public Double getItemCountActual() {
		return this.itemCountActual;
	}

	public void setItemCountActual(Double itemCountActual) {
		this.itemCountActual = itemCountActual;
	}

}