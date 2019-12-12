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
public class TableBill {
	public String table; // 台位名称
	public float foodAmt; // 消费金额
	public int billNum; // 单数
	public int guestNum; // 人数

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public float getFoodAmt() {
		return foodAmt;
	}

	public void setFoodAmt(float foodAmt) {
		this.foodAmt = foodAmt;
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

}
