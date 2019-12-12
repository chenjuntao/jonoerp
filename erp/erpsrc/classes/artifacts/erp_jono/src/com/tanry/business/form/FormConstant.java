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

public class FormConstant {

	/**
	 * 盘点清单
	 */
	public static String CHECK_LIST = "list";

	/**
	* 盘点单
	*/
	public static String CHECK_FORM = "form";

	/**
	* 配送模式，统配UNIFIED
	*/
	public static String UNIFIED_DELIVERY = "UNIFIED";

	/**
	* 配送模式，直配DIRECT
	*/
	public static String DIRECT_DELIVERY = "DIRECT";

	/**
	* 配送模式，越库CROSS
	*/
	public static String CROSS_DELIVERY = "CROSS";

	/**
	* 配送模式，自采SELF
	*/
	public static String SELF_DELIVERY = "SELF";

	/**
	* 模板类别，要货
	*/
	public static String REQUEST_TEMPLATE = "request";

	/**
	* 模板类别，外部订货
	*/
	public static String OUTER_TEMPLATE = "outer";

	/**
	* 模板类别，盘点
	*/
	public static String CHECK_TEMPLATE = "checkstorage";

	/**
	* 模板类别，物流中心需求预估
	*/
	public static String ESTIMATE_TEMPLATE = "estimate";

	/**
	* 要货模板所属类型，公共模板，物流中心为某一区域创建
	*/
	public static String PUBLIC_TEMPLATE = "public";

	/**
	* 要货模板所属类型，自建模板，某一餐厅自己创建的模板
	*/
	public static String SELF_TEMPLATE = "self";

	/**
	* 要货模板所属类型，同时包括自建模板和公共模板
	*/
	public static String BOTH_TEMPLATE = "both";

	/**
	 * 单据编号后缀流水号长度，默认值为4，即1万
	 */
	public static int DEFAULT_SERIAL_LENGTH = 4;

}
