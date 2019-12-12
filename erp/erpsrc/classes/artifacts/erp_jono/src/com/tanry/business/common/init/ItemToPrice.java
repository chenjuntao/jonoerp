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
import java.util.Map;

public class ItemToPrice {
	public static void main(String[] args) {

		try {
			Connection conn = DBUtil.getConnection();
			checkBranch(conn);

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历原材料,在价格表中不同时满足BENCHMARK和PURCHASE的物料编码
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> checkBranch(Connection conn) throws SQLException {
		String sql = "SELECT DISTINCT b.ITEM_ID FROM D_T2_ITEM_META b WHERE b.ITEM_TYPE='RAW'"
				+ "AND b.ITEM_ID NOT IN ( SELECT u.ITEM_ID FROM D_T2_ITEM_PRICE u WHERE u.PRICE_TYPE='BENCHMARK'"
				+ " INTERSECT SELECT u.ITEM_ID FROM D_T2_ITEM_PRICE u WHERE u.PRICE_TYPE='PURCHASE' )";
		System.out.println(sql);
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String item = rs.getString("ITEM_ID");
			System.out.println("[" + item + "] 在表D_T2_ITEM_PRICE中没有同时存在BENCHMARK和PURCHASE");
		}
		rs.close();
		st.close();
		return null;
	}
}
