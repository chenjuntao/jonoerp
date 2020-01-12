/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 24, 2015 by liyzh
 * Last edited on Apr 24, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.common.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static String IP_ADDR = "10.1.1.105";

	// public static String IP_ADDR = "114.55.33.130";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		// String url = "jdbc:oracle:thin:@localhost:1521:XE";
		// String url = "jdbc:oracle:thin:@" + DBUtil.IP_ADDR + ":1521:xe";

		String url = "jdbc:oracle:thin:@" + DBUtil.IP_ADDR + ":1521:orcle";

		String user = "jono";
		String password = "jono";
		return DriverManager.getConnection(url, user, password);
	}
}
