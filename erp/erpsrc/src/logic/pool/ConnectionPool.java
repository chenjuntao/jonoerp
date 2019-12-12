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

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sqlj.runtime.ConnectionContext;
import sqlj.runtime.ref.DefaultContext;

public class ConnectionPool {
	private DataSource dataSource_ = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource_ = dataSource;
		// initCompanyId();
	}

	private String getLogKey(Object obj) {
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();// .getName();
		String key = this.getClass().getName() + "." + methodName + "@" + Integer.toHexString(obj.hashCode());
		return key;
	}

	// companyId从session中读取
	public DefaultContext getDefaultContext() {
		this.companyId = getSessionCom();
		return createDefaultContext();
	}

	// companyId不从session读取，而是由调用方直接在调用参数中由comId指定
	public DefaultContext getComContext(String comId) {
		this.companyId = comId;
//		log.debug(companyId + "-------------callNum is " + callNum++);
		return createDefaultContext();
	}

	public DefaultContext createDefaultContext() {
		try {
			if (dataSource_ != null) {
				Connection conn_ = DataSourceUtils.getConnection(dataSource_);
				// conn_ = dataSource_.getConnection();
				DefaultContext dfCtx = new DefaultContext(conn_);
				// StatusUtil.addConnLog(getLogKey(conn_));
				return dfCtx;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UndeclaredThrowableException e) {
			e.getCause().printStackTrace();
		}

		return null;
	}

	public void closeDefaultContext(DefaultContext ctx) throws SQLException {
		ctx.close(ConnectionContext.KEEP_CONNECTION);
		Connection conn = ctx.getConnection();
		// StatusUtil.removeConnLog(getLogKey(conn), "sqlj");
		DataSourceUtils.releaseConnection(conn, dataSource_);
	}

	public Connection getConnection() {
		if (dataSource_ != null) {
			Connection conn_ = DataSourceUtils.getConnection(dataSource_);
			return conn_;
		}
		return null;
	}

	public void releaseConnection(Connection conn_) {
		DataSourceUtils.releaseConnection(conn_, dataSource_);
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

	// 在表名前面加上表示不同企业的前缀
	public String Com_(String tb) {
		return this.companyId + "." + tb;
	}

	// 测试spring scope session bean-----------------------------------
	public static Logger log = Logger.getLogger("ConnectionPool");
	private int callNum = 0;

	// 读取当前Session中的CompanyId，支持访问多企业不同的数据
	public String getSessionCom() {
		String comId = "jono";
		try {
			RequestAttributes requestAttrs = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) requestAttrs).getRequest();
			comId = request.getSession().getAttribute("companyId").toString();
//			log.debug(comId + "-------------callNum is " + callNum++);
		} catch (Exception ex) {
//			log.debug("get CompanyId has init failed=========," + ex.toString());
		}
		return comId;
	}
	// private void initCompanyId(){
	// idStr = service.restapi.util.GuidHelper.generateId();
	//
	// HttpServletRequest request =
	// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	// try{
	// log.debug("tryNum"+tryNum++);
	// companyId = request.getSession().getAttribute("companyId").toString();
	// log.debug(idStr +
	// " has inited success=======================,CompanyId:"+companyId);
	//
	// }catch (Exception ex){
	// log.debug(idStr +
	// " has inited failed========================,"+ex.getMessage());
	// log.debug("exNum"+exNum++);
	// }
	// }
}
