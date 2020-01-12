/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 28, 2014 by liyzh
 * Last edited on Sep 28, 2014 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.form;

import java.util.Date;

import com.tanry.framework.util.DateTimeUtil;

public class FormUtil {

	/**
	 * @param prefix
	 * @param branchId
	 * @param businessTime
	 * @return
	 */
	public static String generateFormId(String prefix, String branchId, Date businessTime) {
		Date now = new Date();
		// 自动生成，拼音缩写+部门编号+日期+流水号
		String formId = prefix + branchId + DateTimeUtil.getDateTime(businessTime, "yyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss");
		return formId;
	}

	/**
	 * 生成单据号的主体部分，除了流水号之外，增加程序的灵活性
	 * 
	 * @param prefix
	 * @param branchId
	 * @param businessTime
	 * @return
	 */
	public static String genFormIdBody(String prefix, String branchId, Date businessTime) {
		// 自动生成，拼音缩写+部门编号+日期
		String formId = prefix + branchId + DateTimeUtil.getDateTime(businessTime, "yyMMdd");
		return formId;
	}

	/**
	 * 补齐六位数编码，预计一个部门一天的最大单据处理量为一百万，如果超过，以后再说吧
	 * 
	 * @param serialNum
	 * @return
	 */
	public static String formatSerial(int serialNum) {
		String serial = serialNum + "";
		int addLength = FormConstant.DEFAULT_SERIAL_LENGTH - serial.length();// 补齐六位数编码，预计一个部门一天的最大单据处理量为一百万，如果超过，以后再说吧
		for (int i = 0; i < addLength; i++) {
			serial = "0" + serial;
		}
		return serial;
	}

}
