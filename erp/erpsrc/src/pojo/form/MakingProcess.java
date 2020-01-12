/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Nov 15 11:33:05 CST 2014 by pw
 * Last edited on Sat Nov 15 11:33:05 CST 2014 by pw
 * 
 * 说明：半成品制程表
 */
package pojo.form;

public class MakingProcess {	
	private String itemId;
	private Integer step;
	private String stepOperation;
	private String stepNote;

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getStep() {
		return this.step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getStepOperation() {
		return this.stepOperation;
	}

	public void setStepOperation(String stepOperation) {
		this.stepOperation = stepOperation;
	}

	public String getStepNote() {
		return this.stepNote;
	}

	public void setStepNote(String stepNote) {
		this.stepNote = stepNote;
	}

}