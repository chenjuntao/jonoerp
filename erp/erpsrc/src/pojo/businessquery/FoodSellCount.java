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
public class FoodSellCount {

	/**
	 * 类别编码
	 */
	public String categoryId;

	/**
	 * 类别名称
	 */
	public String categoryN;

	/**
	 * 数量
	 */
	public int qty;

	/**
	 * 金额
	 */
	public float amt;

	/**
	 * 折后金额
	 */
	public float afterAmt;

	/**
	 * 门店编码
	 */
	public String shopC;

	/**
	 * 门店名称
	 */
	public String shopN;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryN() {
		return categoryN;
	}

	public void setCategoryN(String categoryN) {
		this.categoryN = categoryN;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public float getAmt() {
		return amt;
	}

	public void setAmt(float amt) {
		this.amt = amt;
	}

	public float getAfterAmt() {
		return afterAmt;
	}

	public void setAfterAmt(float afterAmt) {
		this.afterAmt = afterAmt;
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
