package com.tanry.framework.util.constant;

public class BillStatus {
	/**
	 * 未审核
	 */
	public static final String UNADUIT_CN = "未审核";

	/**
	 * unaudit
	 */
	public static final String UNADUIT_US = "unaudit";

	/**
	 * 已审核
	 */
	public static final String AUDITED_CN = "已审核";

	/**
	 * 已审核
	 */
	public static final String AUDITED_US = "audited";

	/**
	 * 已结案
	 */
	public static final String SETTLED_CN = "已结案";

	/**
	 * 已结案
	 */
	public static final String SETTLED_US = "settled";
	/**
	 * 未入库
	 */
	public static String UNSTORED_CN = "未入库";

	/**
	 * 未入库
	 */
	public static String UNSTORED_US = "unstored";
	/**
	 * 已入库
	 */
	public static String STORED_CN = "已入库";

	/**
	 * 已入库
	 */
	public static String STORED_US = "stored";

	/**
	 * 已汇总
	 */
	public static String AGGREGATED_CN = "已汇总";

	/**
	 * 已汇总
	 */
	public static String AGGREGATED_US = "aggregated";

	/**
	 * 已配送处理
	 */
	public static String DISTRIBUTED_CN = "已配送处理";

	/**
	 * 已配送处理
	 */
	public static String DISTRIBUTED_US = "distributed";

	/**
	 * 未确认
	 */
	public static final String UNCONFIRM_CN = "未确认";

	public static final String CANCELED = "已作废";

	public static final String SUPPLIER_CONFIRMED_US = "supplier_confirmed";
	public static final String SUPPLIER_CONFIRMED_CN = "供应商已确认";

	public static final String PAYED_US = "payed";
	public static final String PAYED_CN = "已付款";

	public static final String OUT_CONFIRMED_US = "out_confirmed";
	public static final String OUT_CONFIRMED_CN = "外部订货方已确认";

	public static String tansferUSToCN(String USStr) {
		if (UNADUIT_US.equals(USStr)) {
			return UNADUIT_CN;
		}

		if (SUPPLIER_CONFIRMED_US.equals(USStr)) {
			return SUPPLIER_CONFIRMED_CN;
		}

		if (PAYED_US.equals(USStr)) {
			return PAYED_CN;
		}

		if (OUT_CONFIRMED_US.equals(USStr)) {
			return OUT_CONFIRMED_CN;
		}

		return "";
	}
}
