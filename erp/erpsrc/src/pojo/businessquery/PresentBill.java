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
 * 营业数据赠送情况报表vo
 */
public class PresentBill {
	/**
	 * 日期
	 */
	public String businessDate;

	/**
	 * 台位名称
	 */
	public String table;

	/**
	 * 单据编号 出品单号
	 */
	public String billC;

	/**
	 * 市别名称
	 */
	public String period;

	/**
	 * 班次名称
	 */
	public String shift;

	/**
	 * 出品名称
	 */
	public String foodName;

	/**
	 * 单位
	 */
	public String unit;

	/**
	 * 赠送数量
	 */
	public float presentAmount;

	/**
	 * 价格
	 */
	public float price;

	/**
	 * 赠送金额
	 */
	public float presentPrice;

	/**
	 * 加价
	 */
	public float extracPrice;

	/**
	 * 赠送授权人
	 */
	public String presentMan;

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

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getBillC() {
		return billC;
	}

	public void setBillC(String billC) {
		this.billC = billC;
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

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(float presentAmount) {
		this.presentAmount = presentAmount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPresentPrice() {
		return presentPrice;
	}

	public void setPresentPrice(float presentPrice) {
		this.presentPrice = presentPrice;
	}

	public float getExtracPrice() {
		return extracPrice;
	}

	public void setExtracPrice(float extracPrice) {
		this.extracPrice = extracPrice;
	}

	public String getPresentMan() {
		return presentMan;
	}

	public void setPresentMan(String presentMan) {
		this.presentMan = presentMan;
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
