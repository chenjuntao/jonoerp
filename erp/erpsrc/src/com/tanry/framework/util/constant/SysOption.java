/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 31, 2015 by liyzh
 * Last edited on Mar 31, 2015 by liyzh
 * 
 * 说明： 系统选项设置
 */
package com.tanry.framework.util.constant;

public class SysOption {
	/**
	 * 月结关账日
	 */
	public static final String CLOSE_DATE = "CLOSE_DATE";

	/**
	 * 查询万元用量类型(理论值:THEORY/实际值:ACTUAL)
	 */
	public static final String AMT_TTCNY = "AMT_TTCNY";

	/**
	 * 用户初始密码
	 */
	public static final String INIT_PWD = "3456";

	/**
	 * 套餐小类的编码,以40开头
	 */
	public static final String SUIT_CATE_ID = "40";

	/**
	 * 物料等基础数据启用标志
	 */
	public static final Integer ENABLED = 1;

	/**
	 * 物料等基础数据停用标志
	 */
	public static final Integer STOPPED = 0;

	/**
	 * 中文字符串连接符
	 */
	public static final String CHINESE_SPLITTER = "、";

	/**
	 * 导出导入分隔符，csv文件使用
	 */
	public static final String CSV_SPLITTER = "\t";
	public static final String CSV_REGEX_SPLITTER = "\\t";

	public static final String META_ZIP = "出品信息.zip";
	public static final String SEMIS_ZIP = "产品信息.zip";
}
