// **********************************************************************
//
// Copyright (c) 2006
// National University of Defence Technology
// ChangSha, China
//
// All Rights Reserved
//
// Authors: Huang Jie
//
// $Id: ConnectionPool.java,v 1.3 2006/09/27 02:31:27 HuangJie Exp $
//
// **********************************************************************

package logic.pool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcConnectionPool {
	private DataSource dataSource_ = null;
	private Connection conn_ = null;

	public void setDataSource(DataSource dataSource) {
		dataSource_ = dataSource;
	}

	public Connection getConnection() {
		try {
			conn_ = dataSource_.getConnection();
			return conn_;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection() {
		if (conn_ != null) {
			try {
				conn_.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ----------------------------------------------------
	// edited by cjt 20160107
	// 用于定义当前登录的用户属于哪个品牌或企业（company）
	private String companyId = "jono";

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
