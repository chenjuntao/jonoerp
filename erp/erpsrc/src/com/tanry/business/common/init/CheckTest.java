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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckTest {
	public static void main(String[] args) {
		List<Map<String, Object>> checkLst = new ArrayList<Map<String, Object>>();

		// D_T0_OPTION表数据验证
		String duv = "AMT_TTCNY,CLOSE_DATE,BOX_VOLUME,测试";
		Map<String, Object> checkMap1 = new HashMap<String, Object>();
		checkMap1.put("mainTable", "D_T0_OPTION");// 主表
		checkMap1.put("tableItem", "OPTION_KEY");// 验证表主键
		checkMap1.put("duv", duv);// 验证对象
		checkMap1.put("condition", "1=1");// 附加条件，没有则为1=1

		checkLst.add(checkMap1);

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
		// String bulSql =
		// "CREATE GLOBAL TEMPORARY TABLE TMP_TEST( NAME VARCHAR2(32) ) ON COMMIT PRESERVE ROWS";
		// Statement stb = conn.createStatement();
		// stb.executeUpdate(bulSql);
		// stb.close();

		String innerSql = "insert into TMP_TEST(NAME) values (?)";
		PreparedStatement insps = conn.prepareStatement(innerSql);
		String[] itemLst = duvs.split(",");
		for (String item : itemLst) {
			insps.setString(1, item);
			insps.addBatch();
		}
		insps.executeBatch();
		insps.clearBatch();
		insps.close();

		String sql = "SELECT t.NAME FROM " + mainTable + " p RIGHT JOIN TMP_TEST t ON t.NAME=p." + tableItem
				+ " WHERE 1=1 AND p." + tableItem + " IS NULL";
		System.out.println(sql);
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println(mainTable + "表中缺失" + rs.getString("NAME"));
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
