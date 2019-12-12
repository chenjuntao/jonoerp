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
 * 营业数据免单报表vo
 */
public class FreeBill {

	/****************************** head ************************/

	/**
	 * 班次
	 */
	public String shift;

	/**
	 * 市别
	 */
	public String period;

	/**
	 * 日期
	 */
	public String businessDate;

	/**
	 * 付款金额
	 */
	public float payAmt;

	/**
	 * 消费金额
	 */
	public float foodAmt;

	/**
	 * 单据编号 出品单号
	 */
	public String billC;
	/**
	 * 台位
	 */
	public String table;
	/**
	 * 门店编号
	 */
	public String shopC;
	/**
	 * 门店名称
	 */
	public String shopN;

	/****************************** detail ************************/

	/**
	 * 出品名称
	 */
	public String foodN;

	/**
	 * 单价
	 */
	public float price;

	/**
	 * 数量
	 */
	public float qty;

	/**
	 * 消费金额
	 */
	public float amt;

	/**
	 * 单位
	 */
	public String unit;

	/**
	 * 大类编码
	 */
	public String bigC;

	/**
	 * 大类名称
	 */
	public String bigN;

	/**
	 * 小类编码
	 */
	public String smallC;

	/**
	 * 小类名称
	 */
	public String smallN;

	public float getFoodAmt() {
		return foodAmt;
	}

	public void setFoodAmt(float foodAmt) {
		this.foodAmt = foodAmt;
	}

	public String getFoodN() {
		return foodN;
	}

	public void setFoodN(String foodN) {
		this.foodN = foodN;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public float getAmt() {
		return amt;
	}

	public void setAmt(float amt) {
		this.amt = amt;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getBigC() {
		return bigC;
	}

	public void setBigC(String bigC) {
		this.bigC = bigC;
	}

	public String getBigN() {
		return bigN;
	}

	public void setBigN(String bigN) {
		this.bigN = bigN;
	}

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

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public float getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(float payAmt) {
		this.payAmt = payAmt;
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
