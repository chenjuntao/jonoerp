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

public class ItemToCategory {
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
	 * 遍历物料基本表,验证物料类别是否存在
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> checkBranch(Connection conn) throws SQLException {
		String sql = "SELECT b.ITEM_ID,b.CATEGORY_ID FROM JONO.D_T2_ITEM_META b WHERE b.CATEGORY_ID NOT IN(select t.CATEGORY_ID FROM D_T2_ITEM_CATEGORY t)";
		System.out.println(sql);
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String item = rs.getString("CATEGORY_ID");
			String itemId = rs.getString("ITEM_ID");
			System.out.println("物料" + itemId + "的物料类别[" + item + "] 在表D_T2_ITEM_CATEGORY中不存在");
		}
		rs.close();
		st.close();
		return null;
	}
}
