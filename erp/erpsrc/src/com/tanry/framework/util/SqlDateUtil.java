package com.tanry.framework.util;

import java.sql.Date;
import java.util.Calendar;

public class SqlDateUtil {

	/**
	 * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差天数
	 */
	public static long diffDays(Date one, Date two) {
		return (two.getTime() - one.getTime()) / (24 * 60 * 60 * 1000);
	}

	public static Date getSqlDate(java.util.Date date) {
		if (date != null) {
			return new Date(date.getTime());
		}
		return null;
	}

	public static java.util.Date getUtilDate(Date date) {
		if (date != null) {
			return new java.util.Date(date.getTime());
		}
		return null;
	}

	/**
	 * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
	 * 
	 * @param date
	 *            基准日期
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.add(Calendar.DATE, days);
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * 判断参数中的日期是不是比当前时间加上指定的分钟后要早，用于单据解锁
	 * eg: 如果一个单据被锁的时间超过30分钟，可能是用户离开了，或页面被关闭了，在这种情况下把锁释放
	 * @param date 要比较的日期
	 * @param diffMin 分钟数
	 * @return
	 */
	public static boolean beforeNow(Date date, int diffMin) {
		Calendar cal = Calendar.getInstance();
		java.util.Date now = cal.getTime();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, diffMin);
		if (cal.getTime().before(now)) {
			return true;
		}
		return false;
	}
}