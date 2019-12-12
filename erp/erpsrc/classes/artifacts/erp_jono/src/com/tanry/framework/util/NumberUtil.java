package com.tanry.framework.util;

import java.math.BigDecimal;

import net.sf.json.JSONNull;

public class NumberUtil {

	/**
	 * cast Object to Double
	 * 
	 * @return
	 */
	public static Double getDouble(Object num) {
		double result = 0;
		if (num == null || JSONNull.getInstance().equals(num)) {
			return result;
		}
		if (num instanceof Integer) {
			result = (int) num;
		} else {
			result = (double) num;
		}
		return result;
	}

	/**
	 * 设置要保留的小数位数，并且去掉小数末尾多余的零，eg: 1 - 1.6
	 * 
	 * @param num
	 * @param scale
	 * @return
	 */
	public static Double scale(Double num, int scale) {
		BigDecimal result = BigDecimal.valueOf(num);
		result = result.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		return result.doubleValue();
	}

	public static void main(String[] args) {
	}
}
