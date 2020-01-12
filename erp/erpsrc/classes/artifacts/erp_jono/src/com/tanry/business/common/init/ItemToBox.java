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

public class ItemToBox {
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
	 * 遍历物料基本表,验证物料箱子类型是否存在
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> checkBranch(Connection conn) throws SQLException {
		String sql = "SELECT b.ITEM_ID,b.BOX_TYPE FROM JONO.D_T2_ITEM_META b WHERE b.BOX_TYPE NOT IN(select t.TYPE_ID FROM D_T2_BOX_TYPE t)";
		System.out.println(sql);
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String item = rs.getString("BOX_TYPE");
			String itemId = rs.getString("ITEM_ID");
			System.out.println("物料" + itemId + "的物料箱子类型[" + item + "] 在表D_T2_BOX_TYPE中不存在");
		}
		rs.close();
		st.close();
		return null;
	}
}
