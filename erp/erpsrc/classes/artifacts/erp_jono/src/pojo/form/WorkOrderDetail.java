/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon Nov 17 16:26:19 CST 2014 by pw
 * Last edited on Mon Nov 17 16:26:19 CST 2014 by pw
 * 
 * 说明：中央工厂生产工单明细
 */
package pojo.form;

import java.util.Date;

public class WorkOrderDetail {
	private String formId;
	private String productionName;
	private Integer productionStep;
	private Date productionTime;
	private Double productionCount;
	private String productionMan;
	private String productionNote;
	private Integer isCompleted;

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getProductionName() {
		return this.productionName;
	}

	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}

	public Date getProductionTime() {
		return this.productionTime;
	}

	public void setProductionTime(Date productionTime) {
		this.productionTime = productionTime;
	}

	public Double getProductionCount() {
		return this.productionCount;
	}

	public void setProductionCount(Double productionCount) {
		this.productionCount = productionCount;
	}

	public String getProductionMan() {
		return this.productionMan;
	}

	public void setProductionMan(String productionMan) {
		this.productionMan = productionMan;
	}

	public String getProductionNote() {
		return this.productionNote;
	}

	public void setProductionNote(String productionNote) {
		this.productionNote = productionNote;
	}

	public Integer getProductionStep() {
		return productionStep;
	}

	public void setProductionStep(Integer productionStep) {
		this.productionStep = productionStep;
	}

	public Integer getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}

}