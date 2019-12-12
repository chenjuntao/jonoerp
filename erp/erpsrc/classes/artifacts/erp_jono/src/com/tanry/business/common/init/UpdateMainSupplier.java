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
 * 说明： 初始化数据时，有的原料存在两个甚至两个以上的主供应商，在这里对数据进行修正
 */
package com.tanry.business.common.init;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateMainSupplier {
	private static Connection conn;

	public static void main(String[] args) {
		try {
			conn = DBUtil.getConnection();
			long start = System.currentTimeMillis();
			iterateRaw();
			long end = System.currentTimeMillis();
			System.out.println("elapsed time(in miliseconds) is : " + (end - start));
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历原材料
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static void iterateRaw() throws SQLException {
		String sql = "SELECT sbi.ITEM_ID, sbi.BRANCH_ID, COUNT(*) FROM D_T2_SUPPLIER_BRANCH_ITEM sbi "
				+ "WHERE sbi.PRIORITY = 0 GROUP BY sbi.ITEM_ID, sbi.BRANCH_ID HAVING COUNT(*) >1";
		String selSql = "SELECT sbi.ITEM_ID, sbi.BRANCH_ID, sbi.SUPPLIER_ID FROM D_T2_SUPPLIER_BRANCH_ITEM sbi "
				+ "WHERE sbi.PRIORITY = 0 AND sbi.ITEM_ID = ? AND sbi.BRANCH_ID = ?";
		String updateSql = "update D_T2_SUPPLIER_BRANCH_ITEM sbi set sbi.PRIORITY = 1 "
				+ "where sbi.ITEM_ID = ? and sbi.BRANCH_ID = ? and sbi.SUPPLIER_ID ! = ?";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		PreparedStatement selPs = conn.prepareStatement(selSql);
		PreparedStatement updatePs = conn.prepareStatement(updateSql);
		int count = 1;
		while (rs.next()) {
			String itemId = rs.getString("item_id");
			String branchId = rs.getString("branch_id");
			if (count % 100 == 0) {
				System.out.println(itemId + "/" + branchId);
			}

			selPs.setString(1, itemId);
			selPs.setString(2, branchId);
			ResultSet rs2 = selPs.executeQuery();
			while (rs2.next()) {
				String supplierId = rs2.getString("SUPPLIER_ID");

				updatePs.setString(1, itemId);
				updatePs.setString(2, branchId);
				updatePs.setString(3, supplierId);

				updatePs.addBatch();
				break;
			}

			rs2.close();

			count++;
		}
		System.out.println(count);
		rs.close();
		st.close();

		selPs.close();

		updatePs.executeBatch();
		updatePs.clearBatch();
		updatePs.close();
	}
}
