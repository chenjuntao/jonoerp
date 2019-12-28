/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 15, 2016 by cjt
 * Last edited on Jan 15, 2014 by cjt
 * 
 * 说明： 单据表数据库表对应po
 */

package pojo.businessquery;

public class Bill {

	public String billC; // 单据编号
	public String dBusiness; // 营业日期
	public String period; // 市别
	public String shift; // 班次
	public Integer guestNum; // 人数
	public String billTime; // 开台时间
	public String settleTime; // 结账时间
	public String shopName; // 门店名
	public String shopC; // 门店编码
	public String table; // 台位名
	public String createMan; // 开台人
	public String settleMan; // 结账人
	public Double foodAmt; // 消费金额
	public Double roundAmt; // 舍尾金额
	public Double presentAmt; // 赠送金额
	public Double oughtAmt; // 应付款金额
	public Double payAmt; // 付款金额
	public Double disAmt; // 总折扣额
	public String disCurMan; // 照单折扣人
	public String disCurWhy; // 照单折扣原因
	public String vipC; // 会员卡号
	public String vipN; // 会员名字
	public String remark; // 备注信息

	public Bill() {
	}

	public String getShopC() {
		return shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public String getdBusiness() {
		return dBusiness;
	}

	public void setdBusiness(String dBusiness) {
		this.dBusiness = dBusiness;
	}

	public String getBillC() {
		return this.billC;
	}

	public String getPeriod() {
		return this.period;
	}

	public String getShift() {
		return this.shift;
	}

	public int getGuestNum() {
		return this.guestNum;
	}

	public String getBillTime() {
		return this.billTime;
	}

	public String getSettleTime() {
		return this.settleTime;
	}

	public String getShopName() {
		return this.shopName;
	}

	public String getTable() {
		return this.table;
	}

	public String getCreateMan() {
		return this.createMan;
	}

	public String getSettleMan() {
		return this.settleMan;
	}

	public String getDisCurMan() {
		return this.disCurMan;
	}

	public String getDisCurWhy() {
		return this.disCurWhy;
	}

	public String getVipC() {
		return this.vipC;
	}

	public String getVipN() {
		return this.vipN;
	}

	public String getRemark() {
		return this.remark;
	}

	public Double getFoodAmt() {
		return foodAmt;
	}

	public void setFoodAmt(Double foodAmt) {
		this.foodAmt = foodAmt;
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

	public Double getDisAmt() {
		return disAmt;
	}

	public void setDisAmt(Double disAmt) {
		this.disAmt = disAmt;
	}

	public void setBillC(String billC) {
		this.billC = billC;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setGuestNum(Integer guestNum) {
		this.guestNum = guestNum;
	}

	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}

	public void setSettleTime(String settleTime) {
		this.settleTime = settleTime;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public void setSettleMan(String settleMan) {
		this.settleMan = settleMan;
	}

	public void setDisCurMan(String disCurMan) {
		this.disCurMan = disCurMan;
	}

	public void setDisCurWhy(String disCurWhy) {
		this.disCurWhy = disCurWhy;
	}

	public void setVipC(String vipC) {
		this.vipC = vipC;
	}

	public void setVipN(String vipN) {
		this.vipN = vipN;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
