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
 * 有关退品的单据情况报表
 */
public class ReturnBill {
	/**
	 * 营业日期
	 */
	public String businessDate;

	/**
	 * 出品单号
	 */
	public String billC;

	/**
	 * 台位名称
	 */
	public String table;

	/**
	 * 市别
	 */
	public String period;

	/**
	 * 班次
	 */
	public String shift;

	/**
	 * 点单号
	 */
	public String foodBill;

	/**
	 * 出品码
	 */
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
	 * 价格
	 */
	public float price;

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
	 * 退品时间
	 */
	public String returnTime;

	/**
	 * 退品授权人
	 */
	public String backMan;

	/**
	 * 套餐标志
	 */
	public String suitFlag;

	/**
	 * 门店编码
	 */
	public String shopC;

	/**
	 * 门店名称
	 */
	public String shopN;

	/**
	 * 小类编码
	 */
	public String smallC;
	/**
	 * 小类名称
	 */
	public String smallN;

	public String getSmallC() {
		return smallC;
	}

	public void setSmallC(String smallC) {
		this.smallC = smallC;
	}

	public String getSmallN() {
		return smallN;
	}

	public void setSmallN(String smallN) {
		this.smallN = smallN;
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

	public String getFoodBill() {
		return foodBill;
	}

	public void setFoodBill(String foodBill) {
		this.foodBill = foodBill;
	}

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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

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

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getBackMan() {
		return backMan;
	}

	public void setBackMan(String backMan) {
		this.backMan = backMan;
	}

	public String getSuitFlag() {
		return suitFlag;
	}

	public void setSuitFlag(String suitFlag) {
		this.suitFlag = suitFlag;
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
