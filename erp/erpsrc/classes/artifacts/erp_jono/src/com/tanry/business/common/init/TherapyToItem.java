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

public class TherapyToItem {
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
	 * 遍历成本卡表,验证基本物料是否存在
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> checkBranch(Connection conn) throws SQLException {
		String sql = "SELECT b.ITEM_ID,b.THERAPY_ID FROM JONO.D_T2_THERAPY b WHERE b.ITEM_ID NOT IN(select t.ITEM_ID FROM D_T2_ITEM_META t)";
		System.out.println(sql);
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String item = rs.getString("THERAPY_ID");
			String itemId = rs.getString("ITEM_ID");
			System.out.println("成本卡" + item + "中的物料[" + itemId + "] 在表D_T2_ITEM_META中不存在");
		}
		rs.close();
		st.close();
		return null;
	}
}
