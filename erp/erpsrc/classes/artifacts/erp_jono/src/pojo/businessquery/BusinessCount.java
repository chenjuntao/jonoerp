/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年1月18日 by cjt
 * Last edited on 2016年1月18日 by cjt
 * 
 * 说明： 付款明细po
 */
package pojo.businessquery;

/**
 * @author cjt
 * 
 */
public class BusinessCount {


	/**
	 * 班次
	 */
	public String shift;

	/**
	 * 单数
	 */
	public int billNum;

	/**
	 * 人数
	 */
	public int guestNum;

	/**
	 * 消费金额
	 */
	public float foodAmt;

	/**
	 * 折扣金额
	 */
	public float disAmt;
	
	/**
	 * 舍尾金额
	 */
	public float roundAmt;

	/**
	 * 应收金额
	 */
	public float oughtAmt;

	/**
	 * 付款金额
	 */
	public float payAmt;

	/**
	 * 赠送金额
	 */
	public float presentAmt;

	/**
	 * 门店编号
	 */
	public String shopC;

	/**
	 * 门店名称
	 */
	public String shopN;


	/**
	 * 市别
	 */
	public String period;


	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public int getBillNum() {
		return billNum;
	}

	public void setBillNum(int billNum) {
		this.billNum = billNum;
	}

	public int getGuestNum() {
		return guestNum;
	}

	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
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

	public float getRoundAmt() {
		return roundAmt;
	}

	public void setRoundAmt(float roundAmt) {
		this.roundAmt = roundAmt;
	}

	public float getOughtAmt() {
		return oughtAmt;
	}

	public void setOughtAmt(float oughtAmt) {
		this.oughtAmt = oughtAmt;
	}

	public float getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(float payAmt) {
		this.payAmt = payAmt;
	}

	public float getPresentAmt() {
		return presentAmt;
	}

	public void setPresentAmt(float presentAmt) {
		this.presentAmt = presentAmt;
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

}
