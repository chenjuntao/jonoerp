/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Feb 10 11:46:15 CST 2015 by lyz
 * Last edited on Tue Feb 10 11:46:15 CST 2015 by lyz
 * 
 * 说明：存储出品套餐各个子项以及子项可替代品
 */
package pojo.store;

public class FoodSuit {

	/**
	 * 套餐出品编码
	 */
	private String foodSuitId;
	/**
	 * 套餐子项编码
	 */
	private String foodId;
	/**
	 * 套餐子项可换项编码
	 */
	private String foodOptionId;
	/**
	 * 套餐子项或换品数量
	 */
	private Double itemCount;

	private Double itemPrice;
	/**
	 * 该套餐子项是否是当前所选(0表示非当前，1表示当前)
	 */
	private Integer isCurrent;

	public String getFoodSuitId() {
		return this.foodSuitId;
	}

	public void setFoodSuitId(String foodSuitId) {
		this.foodSuitId = foodSuitId;
	}

	public String getFoodId() {
		return this.foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodOptionId() {
		return this.foodOptionId;
	}

	public void setFoodOptionId(String foodOptionId) {
		this.foodOptionId = foodOptionId;
	}

	public Integer getIsCurrent() {
		return this.isCurrent;
	}

	public void setIsCurrent(Integer isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Double getItemCount() {
		return itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

}