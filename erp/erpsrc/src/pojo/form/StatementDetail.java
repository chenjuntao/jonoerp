/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Mar 13 17:10:05 CST 2015 by pw
 * Last edited on Fri Mar 13 17:10:05 CST 2015 by pw
 * 
 * 说明：对账单明细
 */
package pojo.form;

import java.util.Date;

public class StatementDetail {

	/**
	 * 表头单据编号
	 */
	private String formId;
	/**
	 * 入库单/采购订货退货单/出货单/外部订货退货单
	 */
	private String subFormId;
	/**
	 * 相关联单编号
	 */
	private String formRefId;
	/**
	 * 总金额
	 */
	private Double allPayAmt;
	/**
	 * 操作人员ID
	 */
	private String formOperateId;
	/**
	 * 操作人员
	 */
	private String formOperate;
	/**
	 * 操作日期
	 */
	private Date formOperateTime;
	/**
	 * 备注说明
	 */
	private String formNote;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getSubFormId() {
		return this.subFormId;
	}

	public void setSubFormId(String subFormId) {
		this.subFormId = subFormId;
	}

	public String getFormRefId() {
		return this.formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public Double getAllPayAmt() {
		return this.allPayAmt;
	}

	public void setAllPayAmt(Double allPayAmt) {
		this.allPayAmt = allPayAmt;
	}

	public String getFormOperateId() {
		return this.formOperateId;
	}

	public void setFormOperateId(String formOperateId) {
		this.formOperateId = formOperateId;
	}

	public String getFormOperate() {
		return this.formOperate;
	}

	public void setFormOperate(String formOperate) {
		this.formOperate = formOperate;
	}

	public Date getFormOperateTime() {
		return this.formOperateTime;
	}

	public void setFormOperateTime(Date formOperateTime) {
		this.formOperateTime = formOperateTime;
	}

	public String getFormNote() {
		return this.formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

}