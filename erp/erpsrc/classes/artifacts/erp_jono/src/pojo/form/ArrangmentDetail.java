/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 04 17:07:12 CST 2014 by pw
 * Last edited on Tue Nov 04 17:07:12 CST 2014 by pw
 * 
 * 说明：中央工厂生产计划/排程单明细
 */
package pojo.form;

import java.util.Date;

public class ArrangmentDetail {
	private String formId;
	private String itemId;
	private String itemName;
	private String itemDimension;
	private String itemDimension2;
	private String itemSpecification;
	private Double produceCount;
	private Double produceCount2;

	private String workOrderId;
	private String workshop;
	private Date produceTime;
	private Double productionCycle;
	private Date completeTime;
	private String note;
	private Double itemPrice;
	private Double payAmt;
	private String mainName;
	private Double grossCount;
	private Double itemCount;

	public Double getProduceCount2() {
		return produceCount2;
	}

	public void setProduceCount2(Double produceCount2) {
		this.produceCount2 = produceCount2;
	}

	public String getItemDimension2() {
		return itemDimension2;
	}

	public void setItemDimension2(String itemDimension2) {
		this.itemDimension2 = itemDimension2;
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

	public Double getProduceCount() {
		return this.produceCount;
	}

	public void setProduceCount(Double produceCount) {
		this.produceCount = produceCount;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getWorkshop() {
		return this.workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	public Date getProduceTime() {
		return this.produceTime;
	}

	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}

	public Double getProductionCycle() {
		return this.productionCycle;
	}

	public void setProductionCycle(Double productionCycle) {
		this.productionCycle = productionCycle;
	}

	public Date getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public Double getGrossCount() {
		return grossCount;
	}

	public void setGrossCount(Double grossCount) {
		this.grossCount = grossCount;
	}

	public Double getItemCount() {
		return itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

}