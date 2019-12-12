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

/**
 * 
 */
public class IncomeDetail {
	/**
	 * 日期
	 */
	public String businessDate;

	/**
	 * 月份
	 */
	public String businessMonth;

	/**
	 * 大类名称
	 */
	public String categoryN;

	public float period1;
	public float period2;
	public float period3;
	public float period4;
	public float counts;

	public String getBusinessMonth() {
		return businessMonth;
	}

	public void setBusinessMonth(String businessMonth) {
		this.businessMonth = businessMonth;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getCategoryN() {
		return categoryN;
	}

	public void setCategoryN(String categoryN) {
		this.categoryN = categoryN;
	}

	public float getPeriod1() {
		return period1;
	}

	public void setPeriod1(float period) {
		this.period1 = period;
	}

	public float getPeriod2() {
		return period2;
	}

	public void setPeriod2(float period) {
		this.period2 = period;
	}

	public float getPeriod3() {
		return period3;
	}

	public void setPeriod3(float period) {
		this.period3 = period;
	}

	public float getPeriod4() {
		return period4;
	}

	public void setPeriod4(float period) {
		this.period4 = period;
	}

	public float getCounts() {
		return counts;
	}

	public void setCounts(float counts) {
		this.counts = counts;
	}

}
