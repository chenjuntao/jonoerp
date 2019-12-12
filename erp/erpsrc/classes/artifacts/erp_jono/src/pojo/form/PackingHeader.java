/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 11 15:06:52 CST 2014 by lyz
 * Last edited on Wed July 22 8:17:12 CST 2015 by lyz
 * 
 * 说明：装箱单表头
 */
package pojo.form;

import java.util.Date;

public class PackingHeader {
	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 装箱部门ID
	 */
	private String packingBranchId;
	/**
	 * 相关联的原始单据编号(捡货单)
	 */
	private String formRefId;
	/**
	 * 制单人员ID
	 */
	private String formMakerId;
	/**
	 * 制单人员
	 */
	private String formMaker;
	/**
	 * 制单日期
	 */
	private Date formTime;
	private Date formTimeActual;
	/**
	 * 装箱日期
	 */
	private Date packingTime;
	/**
	 * 单据下方的说明
	 */
	private String formNote;

	public String getFormId() {
		return this.formId;
	}

	public Date getFormTimeActual() {
		return formTimeActual;
	}

	public void setFormTimeActual(Date formTimeActual) {
		this.formTimeActual = formTimeActual;
	}

	public void setFormId(String formId) {
		this.formId = formId;
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

	public Date getPackingTime() {
		return this.packingTime;
	}

	public void setPackingTime(Date packingTime) {
		this.packingTime = packingTime;
	}

	public String getFormNote() {
		return this.formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

	public String getFormRefId() {
		return formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getPackingBranchId() {
		return packingBranchId;
	}

	public void setPackingBranchId(String packingBranchId) {
		this.packingBranchId = packingBranchId;
	}

}