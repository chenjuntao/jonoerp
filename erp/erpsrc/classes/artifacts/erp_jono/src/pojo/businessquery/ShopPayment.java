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
public class ShopPayment {
	/* -----------------------pay info--------------------- */
	public String payC;
	public String payN;
	public Double payTypeAmt;

	/* -----------------------shop info--------------------- */
	public String shopC;
	public String shopN;
	public Double foodAmt; // 消费金额
	public Integer billNum; // 单数
	public Double amtPerBill; // 单均消费金额
	public Integer guestNum; // 人数
	public Double amtPerGuest; // 人均消费
	public Integer tableNum; // 台位数
	public Double amtPerTable; // 平均每台位消费额
	public Double guestPerTable; // 台位周转率
	public Double disAmt; // 总折扣额
	public Double roundAmt; // 舍尾金额
	public Double presentAmt; // 赠送金额
	public Double oughtAmt; // 应付款金额
	public Double payAmt; // 付款金额
	public String businessDate;

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public String getShopN() {
		return shopN;
	}

	public void setShopN(String shopN) {
		this.shopN = shopN;
	}

	public String getPayC() {
		return payC;
	}

	public void setPayC(String payC) {
		this.payC = payC;
	}

	public String getPayN() {
		return payN;
	}

	public void setPayN(String payN) {
		this.payN = payN;
	}

	public Double getPayTypeAmt() {
		return payTypeAmt;
	}

	public void setPayTypeAmt(Double payTypeAmt) {
		this.payTypeAmt = payTypeAmt;
	}

	public Double getFoodAmt() {
		return foodAmt;
	}

	public void setFoodAmt(Double foodAmt) {
		this.foodAmt = foodAmt;
	}

	public Integer getBillNum() {
		return billNum;
	}

	public void setBillNum(Integer billNum) {
		this.billNum = billNum;
	}

	public Double getAmtPerBill() {
		return amtPerBill;
	}

	public void setAmtPerBill(Double amtPerBill) {
		this.amtPerBill = amtPerBill;
	}

	public Integer getGuestNum() {
		return guestNum;
	}

	public void setGuestNum(Integer guestNum) {
		this.guestNum = guestNum;
	}

	public Double getAmtPerGuest() {
		return amtPerGuest;
	}

	public void setAmtPerGuest(Double amtPerGuest) {
		this.amtPerGuest = amtPerGuest;
	}

	public Integer getTableNum() {
		return tableNum;
	}

	public void setTableNum(Integer tableNum) {
		this.tableNum = tableNum;
	}

	public Double getAmtPerTable() {
		return amtPerTable;
	}

	public void setAmtPerTable(Double amtPerTable) {
		this.amtPerTable = amtPerTable;
	}

	public Double getGuestPerTable() {
		return guestPerTable;
	}

	public void setGuestPerTable(Double guestPerTable) {
		this.guestPerTable = guestPerTable;
	}

	public Double getDisAmt() {
		return disAmt;
	}

	public void setDisAmt(Double disAmt) {
		this.disAmt = disAmt;
	}

	public Double getRoundAmt() {
		return roundAmt;
	}

	public void setRoundAmt(Double roundAmt) {
		this.roundAmt = roundAmt;
	}

	public Double getPresentAmt() {
		return presentAmt;
	}

	public void setPresentAmt(Double presentAmt) {
		this.presentAmt = presentAmt;
	}

	public Double getOughtAmt() {
		return oughtAmt;
	}

	public void setOughtAmt(Double oughtAmt) {
		this.oughtAmt = oughtAmt;
	}

	public Double getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getShopC() {
		return shopC;
	}

}
