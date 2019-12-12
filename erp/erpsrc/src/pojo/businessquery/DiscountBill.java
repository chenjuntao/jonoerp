/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年1月21日 by cjt
 * Last edited on 2016年1月21日 by cjt
 * 
 * 说明： 
 */

package pojo.businessquery;

/**
 * 打折单据情况报表vo
 */
public class DiscountBill {

	/**
	 * 班次名称
	 */
	public String shift;

	/**
	 * 市别名称
	 */
	public String period;

	/**
	 * 日期
	 */
	public String businessDate;

	/**
	 * 单据编号 出品单号
	 */
	public String billC;
	/**
	 * 台位名称
	 */
	public String table;
	/**
	 * 门店编号
	 */
	public String shopC;
	/**
	 * 门店名称
	 */
	public String shopN;

	/**
	 * 消费金额
	 */
	public float foodAmt;

	/**
	 * 折扣金额
	 */
	public float disAmt;

	/**
	 * 付款金额
	 */
	public float payAmt;

	/**
	 * 折扣人
	 */
	public String disMan;

	/**
	 * 折扣原因
	 */
	public String disWhy;

	public String getDisMan() {
		return disMan;
	}

	public void setDisMan(String disMan) {
		this.disMan = disMan;
	}

	public String getDisWhy() {
		return disWhy;
	}

	public void setDisWhy(String disWhy) {
		this.disWhy = disWhy;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getBillC() {
		return billC;
	}

	public void setBillC(String billC) {
		this.billC = billC;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getShopC() {
		return shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public String getShopN() {
		return shopN;
	}

	public void setShopN(String shopN) {
		this.shopN = shopN;
	}

	public float getFoodAmt() {
		return foodAmt;
	}

	public void setFoodAmt(float foodAmt) {
		this.foodAmt = foodAmt;
	}

	public float getDisAmt() {
		return disAmt;
	}

	public void setDisAmt(float disAmt) {
		this.disAmt = disAmt;
	}

	public float getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(float payAmt) {
		this.payAmt = payAmt;
	}

}
