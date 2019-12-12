/*@lineinfo:filename=RestApiErrMsgBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
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

import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 传递数据出现错误的具体出错信息表
 */
public class RestApiErrMsgBean extends ConnectionPool {
	private String comId = "com";
	public static Logger log = Logger.getLogger("RestApiErrMsgBean");
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ErrMsgIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ErrMsgIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    LOG_IDNdx = findColumn("LOG_ID");
    ERR_IDNdx = findColumn("ERR_ID");
    MSG_IDNdx = findColumn("MSG_ID");
    CODENdx = findColumn("CODE");
    MESSAGENdx = findColumn("MESSAGE");
  }
  public String LOG_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(LOG_IDNdx);
  }
  private int LOG_IDNdx;
  public String ERR_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ERR_IDNdx);
  }
  private int ERR_IDNdx;
  public String MSG_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(MSG_IDNdx);
  }
  private int MSG_IDNdx;
  public String CODE() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CODENdx);
  }
  private int CODENdx;
  public String MESSAGE() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(MESSAGENdx);
  }
  private int MESSAGENdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^3*/
	
	public int saveEntity(Map map) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);

		String logId = map.get("logId").toString();
		String errId = map.get("errId").toString();
		String msgId = map.get("msgId").toString();
		String code = map.get("code").toString();
		String message = map.get("message").toString();
		
		/*@lineinfo:generated-code*//*@lineinfo:54^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T_RESTAPI_ERR_MSG")
//  				(LOG_ID, ERR_ID, MSG_ID, CODE, MESSAGE) 
//  			VALUES 
//  				(:logId, :errId, :msgId, :code, :message)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_ERR_MSG");
  String __sJT_2 = logId;
  String __sJT_3 = errId;
  String __sJT_4 = msgId;
  String __sJT_5 = code;
  String __sJT_6 = message;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiErrMsgBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:59^4*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int saveEntity(List<Map> maps) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		for(int i=0;i<maps.size();i++){
			Map map = maps.get(i);
			String logId = map.get("logId").toString();
			String errId = map.get("errId").toString();
			String msgId = map.get("msgId").toString();
			String code = map.get("code").toString();
			String message = map.get("message").toString();
			
			/*@lineinfo:generated-code*//*@lineinfo:75^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T_RESTAPI_ERR_MSG")
//  					(LOG_ID, ERR_ID, MSG_ID, CODE, MESSAGE) 
//  				VALUES 
//  					(:logId, :errId, :msgId, :code, :message)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_ERR_MSG");
  String __sJT_2 = logId;
  String __sJT_3 = errId;
  String __sJT_4 = msgId;
  String __sJT_5 = code;
  String __sJT_6 = message;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiErrMsgBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:80^4*/
		}
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<Map> listErrMsg(String logId, String errId) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		ErrMsgIter errMsgIter = null;
		List<Map> errMsgLst = new ArrayList<Map>();
		
		/*@lineinfo:generated-code*//*@lineinfo:92^3*/

//  ************************************************************
//  #sql [myCtx] errMsgIter = { SELECT * FROM :Com_("D_T_RESTAPI_ERR_MSG") 
//  			WHERE LOG_ID = :logId
//  			AND ERR_ID = :errId
//  			ORDER BY ERR_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_ERR_MSG");
  String __sJT_2 = logId;
  String __sJT_3 = errId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiErrMsgBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      errMsgIter = new ErrMsgIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:97^3*/

		while(errMsgIter.next()){
			Map errMsg = new HashMap();
			errMsg.put("logId", errMsgIter.LOG_ID());
			errMsg.put("errId", errMsgIter.ERR_ID());
			errMsg.put("msgId", errMsgIter.MSG_ID());
			errMsg.put("code", errMsgIter.CODE());
			errMsg.put("message", errMsgIter.MESSAGE());
			errMsgLst.add(errMsg);
		}
		errMsgIter.close();
		
		closeDefaultContext(myCtx);
		return errMsgLst;
	}
}/*@lineinfo:generated-code*/class RestApiErrMsgBean_SJProfileKeys 
{
  private static RestApiErrMsgBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RestApiErrMsgBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RestApiErrMsgBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.restapi.RestApiErrMsgBean_SJProfile0");
  }
}
