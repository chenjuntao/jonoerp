package com.tanry.framework.util.constant;

public class ReasonType {

	/**
	 * 
	 * @param reason
	 *            业务类型
	 * @param branchType
	 *            门店类型
	 * @return
	 */
	public static String getReasonWord(String reason, String branchType) {
		String branchWord = BranchType.getBrandWord(branchType);

		if ("CGRK".equals(reason)) {
			return branchWord + "采购入库";
		}

		if ("PSRK".equals(reason)) {
			return branchWord + "配送入库";
		}

		if ("PSFSH".equals(reason)) {
			return branchWord + "配送反审核";
		}

		if ("PSTH".equals(reason)) {
			return branchWord + "配送退货";
		}

		if ("CGTH".equals(reason)) {
			return branchWord + "采购退货";
		}

		if ("WBTH".equals(reason)) {
			return "外部退货";
		}

		if ("CPBS".equals(reason)) {
			return branchWord + "出品报损";
		}

		if ("YLBS".equals(reason)) {
			return branchWord + "原料报损";
		}

		if ("DB".equals(reason)) {
			return branchWord + "调拨";
		}

		if ("PD".equals(reason)) {
			return branchWord + "盘点";
		}

		if ("LLKU".equals(reason)) {
			return branchWord + "理论扣库";
		}

		if ("PSCK".equals(reason)) {
			return branchWord + "配送出库";
		}

		return reason;
	}

}
