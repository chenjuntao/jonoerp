/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 18, 2016 by cjt
 * Last edited on Jan 18, 2014 by cjt
 * 
 * 说明： 单据付款明细po
 */

package pojo.businessquery;

public class BillPayDetail {

	/**
	 * 台卡号
	 */

	/**
	 * 单据编号 出品单号
	 */
	private String billC;

	/**
	 * 付款方式
	 */
	private String payN;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 本币金额
	 */
	private float payAmt;

	/**
	 * 营业日期
	 */
	private String businessDate;

	/**
	 * 台位
	 */
	private String table;

	/**
	 * 班次
	 */
	private String shift;

	/**
	 * 市别
	 */
	private String period;

	/**
	 * 门店编号
	 */
	private String shopC;

	/**
	 * 门店名称
	 */
	private String shopN;

	/**
	 * 会员卡号
	 */
	private String vipC;

	/**
	 * 会员名称
	 */
	private String vipN;

	/**
	 * 消费人
	 */

	public String getBillC() {
		return billC;
	}

	public void setBillC(String billC) {
		this.billC = billC;
	}

	public String getPayN() {
		return payN;
	}

	public void setPayN(String payN) {
		this.payN = payN;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(float payAmt) {
		this.payAmt = payAmt;
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

	public String getVipC() {
		return vipC;
	}

	public void setVipC(String vipC) {
		this.vipC = vipC;
	}

	public String getVipN() {
		return vipN;
	}

	public void setVipN(String vipN) {
		this.vipN = vipN;
	}

}
