/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 15, 2014 by liyzh
 * Last edited on Dec 15, 2014 by liyzh
 * 
 * 说明： 物流中心采购直配订单（大小单据关联）表
 */
package pojo.form;

public class PurchasingMapping {
	/**
	 * 直配采购单大单编号
	 */
	private String bigFormId;
	/**
	 * 对应的直配采购单小单编号
	 */
	private String smallFormId;

	public String getBigFormId() {
		return bigFormId;
	}

	public void setBigFormId(String bigFormId) {
		this.bigFormId = bigFormId;
	}

	public String getSmallFormId() {
		return smallFormId;
	}

	public void setSmallFormId(String smallFormId) {
		this.smallFormId = smallFormId;
	}
}
