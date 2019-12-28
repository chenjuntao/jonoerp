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

public class BranchToRegion {
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
	 * 遍历配送区域关联的门店表,验证配送区域是否存在
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> checkBranch(Connection conn) throws SQLException {
		String sql = "SELECT b.BRANCH_ID,b.REGION_ID FROM D_T2_DELIVERY_REGION_BRANCH b WHERE b.REGION_ID NOT IN(select t.REGION_ID FROM D_T2_DELIVERY_REGION t )";
		System.out.println(sql);
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String item = rs.getString("BRANCH_ID");
			String itemId = rs.getString("REGION_ID");
			System.out.println("门店" + item + "中的配送区域[" + itemId + "] 在表D_T2_DELIVERY_REGION中不存在");
		}
		rs.close();
		st.close();
		return null;
	}
}
