/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年1月19日 by cjt
 * Last edited on 2016年1月19日 by cjt
 * 
 * 说明： 
 */

package pojo.businessquery;

/**
 * @author cjt
 * 
 */
public class DiscountReason {
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
	 * 折扣原因
	 */
	public String disWhy;

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

	public String getDisWhy() {
		return disWhy;
	}

	public void setDisWhy(String disWhy) {
		this.disWhy = disWhy;
	}

}
