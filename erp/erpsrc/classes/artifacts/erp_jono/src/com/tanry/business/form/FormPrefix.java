/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 7, 2015 by liyzh
 * Last edited on Jan 7, 2015 by liyzh
 * 
 * 说明： 单据编号前缀
 */
package com.tanry.business.form;

public class FormPrefix {

	/**
	 * 盘点清单
	 */
	public static String CHECK_LIST = "PDQD";

	/**
	 * 盘点单
	 */
	public static String CHECK_FORM = "PDD";

	/**
	 * D_T1_REQUEST_HEADER，餐厅要货
	 */
	public static String REQUEST_REST = "YH";

	/**
	 * D_T1_REQUEST_HEADER，外部订货
	 */
	public static String REQUEST_OUTER = "RQ";

	/**
	 * D_T1_REQUEST_HEADER，物流中心需求预估
	 */
	public static String REQUEST_ESTIMATE = "YG";

	/**
	 * D_T1_INPUT_HEADER，入库
	 */
	public static String STOCK_IN = "LK";
	/**
	 * D_T1_INPUT_HEADER，手动入库
	 */
	public static String STOCK_IN_MANUAL = "LKM";

	/**
	 * D_T1_INPUT_HEADER,拒收入库
	 */
	public static String STOCK_IN_JSRK = "LKJ";

	/**
	 * D_T1_INPUT_HEADER，中央工厂产品入库
	 */
	public static String STOCK_IN_PRODUCT = "LKP";

	/**
	 * D_T1_PRICE_ADJUST_HEADER，调价单
	 */
	public static String PRICEADJUST = "TJ";

}
