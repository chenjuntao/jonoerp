/*@lineinfo:filename=RestApiLogBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年1月25日 by cjt
 * Last edited on 2016年1月25日 by cjt
 */

package logic.restapi;

import java.sql.Date;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.DateTimeUtil;

/**
 * 传递数据日志表
 */
public class RestApiLogBean extends ConnectionPool {
	private String comId = "com";
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class LogIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public LogIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    log_idNdx = findColumn("log_id");
    apiNdx = findColumn("api");
    codeNdx = findColumn("code");
    receiveNdx = findColumn("receive");
    successNdx = findColumn("success");
    failNdx = findColumn("fail");
    log_timeNdx = findColumn("log_time");
    senderNdx = findColumn("sender");
  }
  public String log_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(log_idNdx);
  }
  private int log_idNdx;
  public String api() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(apiNdx);
  }
  private int apiNdx;
  public String code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(codeNdx);
  }
  private int codeNdx;
  public int receive() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(receiveNdx);
  }
  private int receiveNdx;
  public int success() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(successNdx);
  }
  private int successNdx;
  public int fail() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(failNdx);
  }
  private int failNdx;
  public Date log_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(log_timeNdx);
  }
  private int log_timeNdx;
  public String sender() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(senderNdx);
  }
  private int senderNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:45^3*/
	
	public int saveEntity(Map map) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);

		String logId = map.get("logId").toString();
		String api = map.get("api").toString();
		String code = map.get("code").toString();
		int receive = (int)map.get("receive");
		int success = (int)map.get("success");
		int fail = (int)map.get("fail");
		Date logTime = SqlDateUtil.getSqlDate((java.util.Date)map.get("logTime"));
		String sender = map.get("sender").toString();
		
		/*@lineinfo:generated-code*//*@lineinfo:59^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T_RESTAPI_LOG")
//  				(LOG_ID, API, CODE, RECEIVE, SUCCESS, FAIL, LOG_TIME, SENDER) 
//  			VALUES 
//  				(:logId, :api, :code, :receive, :success, :fail, :logTime, :sender)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_LOG");
  String __sJT_2 = logId;
  String __sJT_3 = api;
  String __sJT_4 = code;
  int __sJT_5 = receive;
  int __sJT_6 = success;
  int __sJT_7 = fail;
  Date __sJT_8 = logTime;
  String __sJT_9 = sender;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiLogBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:64^4*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int saveEntity(List<Map> maps) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		for(int i=0;i<maps.size();i++){
			Map map = maps.get(i);
			String logId = map.get("logId").toString();
			String api = map.get("api").toString();
			String code = map.get("code").toString();
			int receive = (int)map.get("receive");
			int success = (int)map.get("success");
			int fail = (int)map.get("fail");
			Date logTime = SqlDateUtil.getSqlDate((java.util.Date)map.get("logTime"));
			String sender = map.get("sender").toString();
			
			/*@lineinfo:generated-code*//*@lineinfo:83^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T_RESTAPI_LOG")
//  					(LOG_ID, API, CODE, RECEIVE, SUCCESS, FAIL, LOG_TIME, SENDER) 
//  				VALUES 
//  					(:logId, :api, :code, :receive, :success, :fail, :logTime, :sender)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_LOG");
  String __sJT_2 = logId;
  String __sJT_3 = api;
  String __sJT_4 = code;
  int __sJT_5 = receive;
  int __sJT_6 = success;
  int __sJT_7 = fail;
  Date __sJT_8 = logTime;
  String __sJT_9 = sender;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiLogBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:88^4*/
		}
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int countLog(String queryTxt) throws SQLException {
		DefaultContext myCtx = getComContext(comId);
		
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:99^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT 
//  				count(*) 
//  			FROM :Com_("D_T_RESTAPI_LOG") l
//  			WHERE 
//  				:queryTxt
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_LOG");
  String __sJT_2 = queryTxt;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiLogBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      __sJT_rtRs = __sJT_result;
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
  try 
  {
    sqlj.runtime.ref.ResultSetIterImpl.checkColumns(__sJT_rtRs, 1);
    if (!__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_NO_ROW_SELECT_INTO();
    }
    total = __sJT_rtRs.getIntNoNull(1);
    if (__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_MULTI_ROW_SELECT_INTO();
    }
  }
  finally 
  {
    __sJT_rtRs.close();
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:105^3*/

		closeDefaultContext(myCtx);
		
		return total;
	}
	
	public List<Map> listLog(String queryTxt, int startRow, int endRow) throws SQLException {
		DefaultContext myCtx = getComContext(comId);
		LogIter logIter = null;
		List<Map> logLst = new ArrayList<Map>();
		
		/*@lineinfo:generated-code*//*@lineinfo:117^3*/

//  ************************************************************
//  #sql [myCtx] logIter = { SELECT
//  			    *
//  			FROM
//  			(
//  				SELECT 
//  					l.*, 
//  					ROWNUM rowNumber 
//  				FROM :Com_("D_T_RESTAPI_LOG") l
//  				WHERE 
//  					:queryTxt
//  				AND ROWNUM < :endRow
//  				ORDER BY log_time DESC
//  			)
//  		    WHERE
//  		        rowNumber >= :startRow
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_LOG");
  String __sJT_2 = queryTxt;
  int __sJT_3 = endRow;
  int __sJT_4 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiLogBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setInt(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      logIter = new LogIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:133^3*/

		while(logIter.next()){
			Map log = new HashMap();
			log.put("logId", logIter.log_id());
			log.put("api", logIter.api());
			log.put("code", logIter.code());
			log.put("receive", logIter.receive());
			log.put("success", logIter.success());
			log.put("fail", logIter.fail());
			log.put("logTime", DateTimeUtil.getDateTime(SqlDateUtil.getUtilDate(logIter.log_time()),""));
			log.put("sender", logIter.sender());
			logLst.add(log);
		}
		logIter.close();
		
		closeDefaultContext(myCtx);
		return logLst;
	}

	/**
	 * 删除指定日期之前7天的日志数据
	 */
	public int deleteByDate(java.util.Date businessDate) 
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		
//		String com = getSessionCom();

		//取前七天的日期，数据保留七日
		Date sBusinessDate = SqlDateUtil.getSqlDate(businessDate);
		Date sevenDaysBefore = SqlDateUtil.addDays(sBusinessDate, -7);
		
		/*@lineinfo:generated-code*//*@lineinfo:166^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM :Com_("D_T_RESTAPI_ERR_MSG") msg
//  			WHERE EXISTS(
//  				SELECT * FROM :Com_("D_T_RESTAPI_LOG") log 
//  				WHERE log.log_id = msg.log_id
//  				--AND log.SENDER = :com
//  				AND log.LOG_TIME < :sevenDaysBefore
//  			)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_ERR_MSG");
  String __sJT_2 = Com_("D_T_RESTAPI_LOG");
  Date __sJT_3 = sevenDaysBefore;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiLogBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:174^3*/
		
		/*@lineinfo:generated-code*//*@lineinfo:176^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM :Com_("D_T_RESTAPI_ERR_JSON") json
//  		    WHERE EXISTS(
//  				SELECT * FROM :Com_("D_T_RESTAPI_LOG") log 
//  				WHERE log.log_id = json.log_id
//  				--AND log.SENDER = :com
//  				AND log.LOG_TIME < :sevenDaysBefore
//  			)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_ERR_JSON");
  String __sJT_2 = Com_("D_T_RESTAPI_LOG");
  Date __sJT_3 = sevenDaysBefore;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiLogBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:184^3*/
				
		/*@lineinfo:generated-code*//*@lineinfo:186^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM :Com_("D_T_RESTAPI_LOG") 
//  			WHERE LOG_TIME < :sevenDaysBefore
//  			--AND SENDER = :com
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_LOG");
  Date __sJT_2 = sevenDaysBefore;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiLogBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:190^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
}/*@lineinfo:generated-code*/class RestApiLogBean_SJProfileKeys 
{
  private static RestApiLogBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RestApiLogBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RestApiLogBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.restapi.RestApiLogBean_SJProfile0");
  }
}
