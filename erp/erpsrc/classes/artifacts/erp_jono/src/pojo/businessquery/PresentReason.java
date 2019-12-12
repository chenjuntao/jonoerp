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
public class PresentReason {

	/**
	 * 赠送数量
	 */
	public float presentAmount;

	/**
	 * 赠送金额
	 */
	public float presentPrice;

	/**
	 * 赠送原因
	 */
	public String presentWhy;

	/**
	 * 门店编码
	 */
	public String shopC;

	/**
	 * 门店名称
	 */
	public String shopN;

	public float getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(float presentAmount) {
		this.presentAmount = presentAmount;
	}

	public float getPresentPrice() {
		return presentPrice;
	}

	public void setPresentPrice(float presentPrice) {
		this.presentPrice = presentPrice;
	}

	public String getPresentWhy() {
		return presentWhy;
	}

	public void setPresentWhy(String presentWhy) {
		this.presentWhy = presentWhy;
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
