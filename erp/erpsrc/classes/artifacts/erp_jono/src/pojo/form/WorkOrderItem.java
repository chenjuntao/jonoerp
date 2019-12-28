/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Jun 12 11:18:33 CST 2015 by pw
 * Last edited on Fri Jun 12 11:18:33 CST 2015 by pw
 * 
 * 说明：中央工厂生产工单原料明细
 */
package pojo.form;

public class WorkOrderItem {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 原料编码
	 */
	private String itemId;
	/**
	 * 原料名称
	 */
	private String itemName;
	/**
	 * 计划领料数量
	 */
	private Double itemCount;
	/**
	 * 已领料数量
	 */
	private Double receivedCount;
	/**
	 * 已退料数量
	 */
	private Double returnedCount;

	private Double inputedCount;

	private String itemDimension;
	private String itemSpecification;

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

	public Double getInputedCount() {
		return inputedCount;
	}

	public void setInputedCount(Double inputedCount) {
		this.inputedCount = inputedCount;
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

	public Double getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public Double getReceivedCount() {
		return this.receivedCount;
	}

	public void setReceivedCount(Double receivedCount) {
		this.receivedCount = receivedCount;
	}

	public Double getReturnedCount() {
		return this.returnedCount;
	}

	public void setReturnedCount(Double returnedCount) {
		this.returnedCount = returnedCount;
	}

}