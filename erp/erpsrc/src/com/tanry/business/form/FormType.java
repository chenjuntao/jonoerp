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
 * 说明： 多种单据共用一个数据表时，用类型进行区分
 */
package com.tanry.business.form;

public class FormType {

	/**
	 * 盘点清单
	 */
	public static String CHECK_LIST = "list";

	/**
	 * 盘点单
	 */
	public static String CHECK_FORM = "form";

	/**
	 * D_T1_REQUEST_HEADER，餐厅要货
	 */
	public static String REQUEST_REST = "restaurant";

	/**
	 * D_T1_REQUEST_HEADER，外部订货
	 */
	public static String REQUEST_OUTER = "OUTER";

	/**
	 * D_T1_REQUEST_HEADER，物流中心需求预估
	 */
	public static String REQUEST_ESTIMATE = "estimate";

	/**
	 * D_T1_INPUT_HEADER，采购入库
	 */
	public static String INPUT_PURCHASE = "PURCHASE";

	/**
	 * D_T1_INPUT_HEADER，生产入库（中央工厂产品入库）
	 */
	public static String INPUT_PRODUCE = "PRODUCE";

	/**
	 * D_T1_INPUT_HEADER，手动入库
	 */
	public static String INPUT_MANUAL = "MANUAL";

	/**
	 * D_T1_INPUT_HEADER,半成品入库
	 */

	public static String INPUT_SEMIS = "SEMIS";

	/**
	 * D_T1_INPUT_HEADER，外部入库
	 */
	public static String INPUT_OUTER = "OUTER";

	/**
	 * D_T1_INPUT_HEADER,拒收入库
	 */
	public static String INPUT_JSRK = "JSRK";

	/**
	 * D_T1_REQUISITION_HEADER，生产领料单PRODUCE_REQUEST_MATERIAL
	 */
	public static String REQUISITION_PRODUCE = "produce";

	/**
	 * D_T1_REQUISITION_HEADER，超领单EXTRA_REQUEST_MATERIAL
	 */
	public static String REQUISITION_EXTRA = "extra";

	/**
	 * D_T1_REQUISITION_HEADER，非工单领料单NOTWORK_REQUEST_MATERIAL
	 */
	public static String REQUISITION_MANUAL = "manual";

	/**
	 * D_T1_REQUISITION_HEADER，生产退料单PRODUCE_RETURN_MATERIAL
	 */
	public static String REQUISITION_RETURN = "return";

	/**
	 * D_T1_SHIPPING_HEADER，统配配送单
	 */
	public static String DISTRI_UNIFIED = "INNER_UNIFIED";

	/**
	 * D_T1_SHIPPING_HEADER，越库配送单
	 */
	public static String DISTRI_CROSS = "INNER_CROSS";

	/**
	 * D_T1_SHIPPING_HEADER，外部订货方出货单
	 */
	public static String DISTRI_OUTER = "OUTER";

	/**
	 * D_T1_SHIPPING_HEADER，央厂产品出货单
	 */
	public static String DISTRI_PRODUCT = "PRODUCT";

}
