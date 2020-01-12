package com.tanry.framework.util.constant;

public class BranchType {
	/**
	 * 餐厅
	 */
	public final static String RESTAURANT = "RESTAURANT";

	/**
	 * 中央工厂
	 */
	public final static String CENTRALFACTORY = "CENTRALFACTORY";

	/**
	 * 物流中心
	 */
	public final static String LOGISTICSCENTER = "LOGISTICSCENTER";

	/**
	 * 供应商
	 */
	public final static String SUPPLIER = "SUPPLIER";

	/**
	 * 加盟店
	 */
	public final static String FRANCHISEE = "FRANCHISEE";

	/**
	 * 外部订货方
	 */
	public final static String OUTERORDER = "OUTERORDER";
	

	public static String getBrandWord(String branchType) {
		if (LOGISTICSCENTER.equals(branchType)) {
			return "物流中心";
		} else if (CENTRALFACTORY.equals(branchType)) {
			return "中央工厂";
		} else if (SUPPLIER.equals(branchType)) {
			return "供应商";
		} else if (FRANCHISEE.equals(branchType)) {
			return "加盟店";
		} else if (OUTERORDER.equals(branchType)) {
			return "外部订货方";
		} else {
			return "餐厅";
		}
	}
}
