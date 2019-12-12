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

public class FoodAmtTTCNY {
	public String foodId; // 出品编号
	public String foodName; // 出品名称
	public String foodUnit; // 出品例牌
	public int foodNum; // 出品数量
	public float amtTTCNY; // 每万元出品数量

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodUnit() {
		return foodUnit;
	}

	public void setFoodUnit(String foodUnit) {
		this.foodUnit = foodUnit;
	}

	public int getFoodNum() {
		return foodNum;
	}

	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}

	public float getAmtTTCNY() {
		return amtTTCNY;
	}

	public void setAmtTTCNY(float amtTTCNY) {
		this.amtTTCNY = amtTTCNY;
	}
}
