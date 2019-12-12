/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 14, 2014 by liyzh
 * Last edited on Aug 14, 2014 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.framework.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tanry.framework.util.constant.SysOption;

/**
 * @author liyzh
 *
 */
public class CsvUtil {

	/**
	 * @param colsJA
	 * @param dataLst
	 * @return
	 */
	public static StringBuffer exportCsv(JSONArray colsJA, List<Map<String, Object>> dataLst) {
		StringBuffer sb = new StringBuffer();
		int colCnt = colsJA.size();
		String[] fields = new String[colsJA.size()];

		// 输出表头
		for (int i = 0; i < colCnt; i++) {
			JSONObject colJO = (JSONObject) colsJA.get(i);
			String text = colJO.getString("display");
			String field = colJO.getString("name");
			fields[i] = field;
			sb.append(text);
			if (i < colCnt - 1) {
				sb.append(",");
			}
		}
		sb.append("\n");

		// 输出数据
		Map<String, Object> sumMap = new HashMap<String, Object>();
		for (Map<String, Object> map : dataLst) {
			for (int i = 0; i < colCnt; i++) {
				String field = fields[i];
				Object value = map.get(field);
				if (value == null) {
					value = "";
				}
				sb.append(value);
				if (i < colCnt - 1) {
					sb.append(",");
				}

				if ("".equals(value)) {
					continue;
				}

				/** sum begin **/
				JSONObject colJO = (JSONObject) colsJA.get(i);
				JSONObject sumJO = (JSONObject) colJO.get("sum");
				if (sumJO != null) {
					Boolean count = (Boolean) sumJO.get("count");
					if (!count) {// 设置合计文本
						if (sumMap.get(field) == null) {// 只设置一次
							String text = (String) sumJO.get("text");
							sumMap.put(field, text);
						}
					} else if (count) {// 累计合计值
						BigDecimal sum = (BigDecimal) sumMap.get(field);
						if (sum == null) {
							sum = BigDecimal.ZERO;
						}
						sumMap.put(field, sum.add(new BigDecimal(value.toString())));
					}
				}
				/** sum end **/
			}
			sb.append("\n");
		}

		// 输出合计
		for (int i = 0; i < colCnt; i++) {
			Object value = sumMap.get(fields[i]);
			if (value == null) {
				value = "";
			}
			sb.append(value);
			if (i < colCnt - 1) {
				sb.append(",");
			}
		}
		return sb;
	}

	public static StringBuffer exportZip(JSONArray colsJA, List<Map<String, Object>> dataLst) {
		StringBuffer sb = new StringBuffer();
		int colCnt = colsJA.size();
		String[] fields = new String[colsJA.size()];

		// 输出表头
		for (int i = 0; i < colCnt; i++) {
			JSONObject colJO = (JSONObject) colsJA.get(i);
			String text = colJO.getString("display");
			String field = colJO.getString("name");
			fields[i] = field;
			sb.append(text);
			if (i < colCnt - 1) {
				sb.append(SysOption.CSV_SPLITTER);
			}
		}
		sb.append("\n");

		// 输出数据
		Map<String, Object> sumMap = new HashMap<String, Object>();
		for (Map<String, Object> map : dataLst) {
			for (int i = 0; i < colCnt; i++) {
				String field = fields[i];
				Object value = map.get(field);
				if (value == null) {
					value = "";
				}
				sb.append(value);
				if (i < colCnt - 1) {
					sb.append(SysOption.CSV_SPLITTER);
				}

				if ("".equals(value)) {
					continue;
				}

			}
			sb.append("\n");
		}

		return sb;
	}
}
