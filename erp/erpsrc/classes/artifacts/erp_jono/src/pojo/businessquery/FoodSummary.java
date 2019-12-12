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
public class FoodSummary {

	public String foodC;

	/**
	 * 出品名称
	 */
	public String foodN;

	/**
	 * 单位
	 */
	public String unit;

	/**
	 * 销售数量
	 */
	public float qty;

	/**
	 * 赠送数量
	 */
	public float pesentQty;

	/**
	 * 退品数量
	 */
	public float returnQty;

	public String getFoodC() {
		return foodC;
	}

	public void setFoodC(String foodC) {
		this.foodC = foodC;
	}

	public String getFoodN() {
		return foodN;
	}

	public void setFoodN(String foodN) {
		this.foodN = foodN;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public float getPesentQty() {
		return pesentQty;
	}

	public void setPesentQty(float pesentQty) {
		this.pesentQty = pesentQty;
	}

	public float getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(float returnQty) {
		this.returnQty = returnQty;
	}

}
