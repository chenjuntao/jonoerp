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

import java.util.Map;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 
 */
public class TablePay {
	public String table; // 台位名称
	public float payAmt; // 付款金额
	public Map<String, Float> pay; // 付款方式（可选显示字段）
	public String cbillc;
	public String cPayN;

	public String getCbillc() throws NoPrivilegeException {
		return this.cbillc;
	}
	
	public String getCPayN() throws NoPrivilegeException {
		return this.cPayN;
	}

	public float getPayAmt() throws NoPrivilegeException {
		return this.payAmt;
	}

	public String getTable() throws NoPrivilegeException {
		return this.table;
	}
}
