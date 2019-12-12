/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Nov 06 14:41:20 CST 2014 by pw
 * Last edited on Wed July 22 8:17:12 CST 2015 by lyz
 * 
 * 说明：中央工厂生产工单表头
 */
package pojo.form;

import java.util.Date;

/**
 * @author charles_1018
 *
 */
public class WorkOrderHeader {
	private String formId;
	private String itemId;
	private String itemName;
	private String itemDimension;
	private String itemDimension2;
	private String itemSpecification;
	private Double itemCount;
	private Double itemCount2;
	private Double actualCount;
	private String workshop;
	/**
	 * 制单部门ID
	 */
	private String formBranchId;
	private String formMakerId;
	private String formMaker;
	private Date formTime;
	private String auditorId;
	private String auditor;
	private Date auditTime;
	private String formNote;
	private Date completeTime;
	private Double inputedCount;

	private int productionCycle;

	private String batchFlag;

	private Double itemPrice;

	private Double itemAmt;

	private Double deliveryFactor;

	private String categoryId;

	public String getItemDimension2() {
		return itemDimension2;
	}

	public void setItemDimension2(String itemDimension2) {
		this.itemDimension2 = itemDimension2;
	}

	public Double getItemCount2() {
		return itemCount2;
	}

	public void setItemCount2(Double itemCount2) {
		this.itemCount2 = itemCount2;
	}

	public String getBatchFlag() {
		return batchFlag;
	}

	public void setBatchFlag(String batchFlag) {
		this.batchFlag = batchFlag;
	}

	public int getProductionCycle() {
		return productionCycle;
	}

	public void setProductionCycle(int productionCycle) {
		this.productionCycle = productionCycle;
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

	public String getWorkshop() {
		return this.workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	public String getFormMakerId() {
		return this.formMakerId;
	}

	public void setFormMakerId(String formMakerId) {
		this.formMakerId = formMakerId;
	}

	public String getFormMaker() {
		return this.formMaker;
	}

	public void setFormMaker(String formMaker) {
		this.formMaker = formMaker;
	}

	public Date getFormTime() {
		return this.formTime;
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getAuditorId() {
		return this.auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditor() {
		return this.auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getFormNote() {
		return this.formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

	public Double getActualCount() {
		return actualCount;
	}

	public void setActualCount(Double actualCount) {
		this.actualCount = actualCount;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getFormBranchId() {
		return formBranchId;
	}

	public void setFormBranchId(String formBranchId) {
		this.formBranchId = formBranchId;
	}

	public WorkOrderHeader() {
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getItemAmt() {
		return itemAmt;
	}

	public void setItemAmt(Double itemAmt) {
		this.itemAmt = itemAmt;
	}

	public Double getDeliveryFactor() {
		return deliveryFactor;
	}

	public void setDeliveryFactor(Double deliveryFactor) {
		this.deliveryFactor = deliveryFactor;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

}