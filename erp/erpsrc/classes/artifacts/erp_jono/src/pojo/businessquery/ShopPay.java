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

import java.util.HashMap;
import java.util.Map;

import com.tanry.framework.acl.NoPrivilegeException;

public class ShopPay {
	public String shopC; // 门店编号
	public String shopName; // 门店名称
	public Double payAmt; // 付款金额

	public String dbusinessDate;
	public String payCode;
	public String payName;

	public String getDbusinessDate() {
		return dbusinessDate;
	}

	public void setDbusinessDate(String dbusinessDate) {
		this.dbusinessDate = dbusinessDate;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	// 付款方式及各付款方式下的付款金额（可选显示字段）
	public Map<String, Double> pay = new HashMap<String, Double>();

	public String getShopC() throws NoPrivilegeException {
		return this.shopC;
	}

	public String getShopName() throws NoPrivilegeException {
		return this.shopName;
	}

	public Double getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Map<String, Double> getPay() {
		return pay;
	}

	public void setPay(Map<String, Double> pay) {
		this.pay = pay;
	}

}
