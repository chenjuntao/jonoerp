/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon Dec 28 16:12:43 CST 2015 by pw
 * Last edited on Mon Dec 28 16:12:43 CST 2015 by pw
 * 
 * 说明：打印次数表
 */
package pojo.store;

public class PrintTimes {

	/**
	 * 单据编号
	 */
	private String formId;
	/**
	 * 打印次数
	 */
	private Integer times;

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Integer getTimes() {
		return this.times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public PrintTimes(String formId) {
		this.formId = formId;
	}

	public PrintTimes() {
	}

}