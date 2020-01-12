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

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 单据出品vo
 */
public class FoodBill {

	public String foodC; // 出品码
	public String foodN; // 出品名称
	
	public String litClsC; // 出品小类
	public String litClsN; // 出品小类
	
	public String unit; // 例牌
	public Double price; // 单价
	public Double quantity; // 数量
	
	public Double amt; // 消费金额
	public Double disAmt; // 折扣金额
	
	public String suitFlag; // 套餐标志
	public String suitBill; // 套餐编号
	public String suitName; // 套餐名称
	
	public String retSendFlag; // 退品或赠送标志
	public String retSendRemark;// 退品或赠送原因
	public String presentBackMan; // 退品或赠送人

	public String getFoodC() throws NoPrivilegeException {
		return this.foodC;
	}

	public String getFoodN() throws NoPrivilegeException {
		return this.foodN;
	}

	public String getLitClsC() throws NoPrivilegeException {
		return this.litClsC;
	}
	
	public String getLitClsN() throws NoPrivilegeException {
		return this.litClsN;
	}

	public String getUnit() throws NoPrivilegeException {
		return this.unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public Double getDisAmt() {
		return disAmt;
	}

	public void setDisAmt(Double disAmt) {
		this.disAmt = disAmt;
	}

	public void setFoodC(String foodC) {
		this.foodC = foodC;
	}

	public void setFoodN(String foodN) {
		this.foodN = foodN;
	}

	public void setLitClsC(String litClsC) {
		this.litClsC = litClsC;
	}
	
	public void setLitClsN(String litClsN) {
		this.litClsN = litClsN;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setSuitFlag(String suitFlag) {
		this.suitFlag = suitFlag;
	}
	
	public void setSuitBill(String suitBill) {
		this.suitBill = suitBill;
	}
	
	public void setSuitName(String suitName) {
		this.suitName = suitName;
	}

	public void setRetSendFlag(String retSendFlag) {
		this.retSendFlag = retSendFlag;
	}

	public void setRetSendRemark(String retSendRemark) {
		this.retSendRemark = retSendRemark;
	}

	public void setPresentBackMan(String presentBackMan) {
		this.presentBackMan = presentBackMan;
	}

	public String getSuitFlag() throws NoPrivilegeException {
		return this.suitFlag;
	}
	
	public String getSuitBill() throws NoPrivilegeException {
		return this.suitBill;
	}
	
	public String getSuitName() throws NoPrivilegeException {
		return this.suitName;
	}

	public String getRetSendFlag() throws NoPrivilegeException {
		return this.retSendFlag;
	}

	public String getRetSendRemark() throws NoPrivilegeException {
		return this.retSendRemark;
	}
	
	public String getPresentBackMan() throws NoPrivilegeException {
		return this.presentBackMan;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
