/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 30, 2015 by liyzh
 * Last edited on Mar 30, 2015 by liyzh
 * 
 * 说明： 初始化所有的配送方式数据
 */
package com.tanry.business.common.init;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckToTable {
	public static void main(String[] args) {
		List<Map<String, Object>> checkLst = new ArrayList<Map<String, Object>>();

		// D_T0_OPTION单表数据是否存在进行验证
		// SELECT p.OPTION_KEY FROM D_T0_OPTION p WHERE 1=1 AND p.OPTION_KEY IN
		// ('AMT_TTCNY','CLOSE_DATE','BOX_VOLUME','测试') GROUP BY p.OPTION_KEY
		// HAVING COUNT(*)>0
		String duv = "AMT_TTCNY,CLOSE_DATE,BOX_VOLUME";
		Map<String, Object> checkMap1 = new HashMap<String, Object>();
		checkMap1.put("mainTable", "D_T0_OPTION");// 主表
		checkMap1.put("tableItem", "OPTION_KEY");// 验证表主键
		checkMap1.put("duv", duv);// 验证对象
		checkMap1.put("condition", "1=1");// 附加条件，没有则为1=1

		checkLst.add(checkMap1);

		// D_T2_BOX_TYPE表数据验证
		duv = "非冷藏,冷藏,泡沫箱,测试";
		Map<String, Object> checkMap2 = new HashMap<String, Object>();
		checkMap2.put("mainTable", "D_T2_BOX_TYPE");// 主表
		checkMap2.put("tableItem", "TYPE_NAME");// 验证表主键
		checkMap2.put("duv", duv);// 验证对象
		checkMap2.put("condition", "1=1");// 附加条件，没有则为1=1

		checkLst.add(checkMap2);

		// D_T2_USER表数据验证
		duv = "pw,lc,cf,pengwei,测试";
		Map<String, Object> checkMap3 = new HashMap<String, Object>();
		checkMap3.put("mainTable", "D_T2_USER");// 主表
		checkMap3.put("tableItem", "USER_ID");// 验证表主键
		checkMap3.put("duv", duv);// 验证对象
		checkMap3.put("condition", "1=1");// 附加条件，没有则为1=1

		checkLst.add(checkMap3);

		// D_T0_ROLE表数据验证
		duv = "GYS,03,003,004,33,sdf,WBGHF,005,006,001,002,3,14,15,16,008,a,007,测试";
		Map<String, Object> checkMap4 = new HashMap<String, Object>();
		checkMap4.put("mainTable", "D_T0_ROLE");// 主表
		checkMap4.put("tableItem", "ROLE_ID");// 验证表主键
		checkMap4.put("duv", duv);// 验证对象
		checkMap4.put("condition", "1=1");// 附加条件，没有则为1=1

		checkLst.add(checkMap4);

		// D_T2_BRANCH_TYPE表数据验证
		duv = "RESTAURANT,FRANCHISEE,CENTRALFACTORY,LOGISTICSCENTER,SUPPLIER,OUTERORDER,HEADQUATER,测试";
		Map<String, Object> checkMap5 = new HashMap<String, Object>();
		checkMap5.put("mainTable", "D_T2_BRANCH_TYPE");// 主表
		checkMap5.put("tableItem", "TYPE_ID");// 验证表主键
		checkMap5.put("duv", duv);// 验证对象
		checkMap5.put("condition", "1=1");// 附加条件，没有则为1=1

		checkLst.add(checkMap5);

		try {
			Connection conn = DBUtil.getConnection();
			String tableItem;
			String condition;
			String mainTable;
			String duvs;
			for (Map<String, Object> check : checkLst) {
				mainTable = check.get("mainTable").toString();
				duvs = check.get("duv").toString();
				tableItem = check.get("tableItem").toString();
				condition = check.get("condition").toString();
				chackTable(conn, mainTable, duvs, tableItem, condition);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历原材料
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> chackTable(Connection conn, String mainTable, String duvs, String tableItem,
			String condition) throws SQLException {
		String sql = "SELECT p." + tableItem + " FROM " + mainTable + " p WHERE " + condition + " AND p." + tableItem
				+ " IN ('" + duvs.replaceAll(",", "','") + "') GROUP BY p." + tableItem + " HAVING COUNT(*)>0";
		System.out.println(sql);
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);
		String[] arr1 = duvs.split(",");
		rs.last(); // 移到最后一行
		int rowCount = rs.getRow(); // 得到当前行号，也就是记录数
		rs.beforeFirst(); // 如果还要用结果集，就把指针再移到初始化的位置
		String[] arr2 = new String[rowCount];
		int i = 0;
		while (rs.next()) {
			arr2[i] = rs.getString(tableItem);
			i++;
		}
		String[] result_minus = minus(arr1, arr2);
		for (String str : result_minus) {
			System.out.println(mainTable + "表中缺失" + str);
		}
		rs.close();
		st.close();
		return null;
	}

	// 求两个数组的差集
	public static String[] minus(String[] arr1, String[] arr2) {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> history = new LinkedList<String>();
		String[] longerArr = arr1;
		String[] shorterArr = arr2;
		// 找出较长的数组来减较短的数组
		if (arr1.length > arr2.length) {
			longerArr = arr2;
			shorterArr = arr1;
		}
		for (String str : longerArr) {
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : shorterArr) {
			if (list.contains(str)) {
				history.add(str);
				list.remove(str);
			} else {
				if (!history.contains(str)) {
					list.add(str);
				}
			}
		}

		String[] result = {};
		return list.toArray(result);
	}
}
