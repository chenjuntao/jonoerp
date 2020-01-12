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
import java.util.List;
import java.util.Map;

public class CheckToExist {
	public static void main(String[] args) {
		List<Map<String, Object>> checkLst = new ArrayList<Map<String, Object>>();

		// 物料价格验证在价格组中存在
		// SELECT DISTINCT b.PRICE_TYPE FROM D_T2_ITEM_PRICE b WHERE 1=1 AND
		// b.PRICE_TYPE NOT IN ( SELECT u.PRICE_GROUP_ID FROM D_T2_PRICE_GROUP u
		// WHERE 1=1)

		String branchs = "D_T2_PRICE_GROUP";
		Map<String, Object> checkMap1 = new HashMap<String, Object>();
		checkMap1.put("mainTable", "D_T2_ITEM_PRICE");// 主表
		checkMap1.put("mainItem", "PRICE_TYPE");// 验证关联,主表字段
		checkMap1.put("tableItem", "PRICE_GROUP_ID");// 验证关联，从表字段
		checkMap1.put("branchs", branchs);// 从表
		// 附加条件，没有则为1=1
		checkMap1.put("mainCondition", "1=1");// 主表条件
		checkMap1.put("condition", "1=1");// 从表条件

		checkLst.add(checkMap1);

		// Map<String, Object> checkMap2 = new HashMap<String, Object>();
		// checkMap2.put("mainTable", "D_T2_ITEM_META");// 主表
		// checkMap2.put("mainItem", "ITEM_ID");// 验证关联,主表字段
		// checkMap2.put("tableItem", "ITEM_ID");// 验证关联，从表字段
		// checkMap2.put("branchs", "D_T2_ITEM_PRICE");// 从表
		// // 附加条件，没有则为1=1
		// checkMap2.put("mainCondition", "1=1");
		// checkMap2.put("condition",
		// "u.PRICE_TYPE in('BENCHMARK','PURCHASE')");
		//
		// checkLst.add(checkMap2);
		//
		Map<String, Object> checkMap3 = new HashMap<String, Object>();
		checkMap3.put("mainTable", "D_T2_ITEM_META");// 主表
		checkMap3.put("mainItem", "ITEM_ID");// 验证关联,主表字段
		checkMap3.put("tableItem", "ITEM_ID");// 验证关联，从表字段
		checkMap3.put("branchs", "D_T2_DELIVERY_UNIT");// 从表
		// 附加条件，没有则为1=1
		checkMap3.put("mainCondition", "b.ITEM_TYPE !='PRODUCT'");
		checkMap3.put("condition", "1=1");

		checkLst.add(checkMap3);

		try {
			Connection conn = DBUtil.getConnection();
			String[] tableLst;
			String tableItem;
			String mainCondition;
			String condition;
			String mainTable;
			String mainItem;
			for (Map<String, Object> check : checkLst) {
				mainItem = check.get("mainItem").toString();
				mainTable = check.get("mainTable").toString();
				String item = check.get("branchs").toString();
				tableLst = item.split(",");
				tableItem = check.get("tableItem").toString();
				mainCondition = check.get("mainCondition").toString();
				condition = check.get("condition").toString();
				for (String tableName : tableLst) {
					checkBranch(conn, mainTable, mainItem, tableName, tableItem, mainCondition, condition);
				}

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
	public static Map<String, String> checkBranch(Connection conn, String mainTable, String mainItem, String tableName,
			String tableItem, String mainCondition, String condition) throws SQLException {
		String sql = "SELECT DISTINCT b." + mainItem + " FROM " + mainTable + " b WHERE " + mainCondition + " AND b."
				+ mainItem + " NOT IN ( SELECT u." + tableItem + " FROM " + tableName + " u WHERE " + condition + ")";
		System.out.println(sql);
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String item = rs.getString(mainItem);
			System.out.println("[" + item + "] 在表" + tableName + "中缺失数据");
		}
		rs.close();
		st.close();
		return null;
	}
}
