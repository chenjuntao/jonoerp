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
 * 
 */
public class ReturnReason {
	/**
	 * 退品数量
	 */
	public float returnAmount;

	/**
	 * 退品金额
	 */
	public float returnAmt;

	/**
	 * 退品原因
	 */
	public String returnWhy;

	/**
	 * 门店编码
	 */
	public String shopC;

	/**
	 * 门店名称
	 */
	public String shopN;

	public float getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(float returnAmount) {
		this.returnAmount = returnAmount;
	}

	public float getReturnAmt() {
		return returnAmt;
	}

	public void setReturnAmt(float returnAmt) {
		this.returnAmt = returnAmt;
	}

	public String getReturnWhy() {
		return returnWhy;
	}

	public void setReturnWhy(String returnWhy) {
		this.returnWhy = returnWhy;
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
