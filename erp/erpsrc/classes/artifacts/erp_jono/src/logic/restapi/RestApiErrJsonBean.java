/*@lineinfo:filename=RestApiErrJsonBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
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
 * 传递数据出现错误的JSON数据表
 */
public class RestApiErrJsonBean extends ConnectionPool {

	private String comId = "com";
	public static Logger log = Logger.getLogger("RestApiErrJsonBean");
	
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ErrJsonIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ErrJsonIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    LOG_IDNdx = findColumn("LOG_ID");
    ERR_IDNdx = findColumn("ERR_ID");
    JSON_STRNdx = findColumn("JSON_STR");
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
  public String JSON_STR() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(JSON_STRNdx);
  }
  private int JSON_STRNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^3*/
	
	public int saveEntity(Map map) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);

		String logId = map.get("logId").toString();
		String errId = map.get("errId").toString();
		String jsonStr = map.get("jsonStr").toString();
		
		/*@lineinfo:generated-code*//*@lineinfo:51^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T_RESTAPI_ERR_JSON")
//  				(LOG_ID, ERR_ID, JSON_STR) 
//  			VALUES 
//  				(:logId, :errId, :jsonStr)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_ERR_JSON");
  String __sJT_2 = logId;
  String __sJT_3 = errId;
  String __sJT_4 = jsonStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiErrJsonBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:56^4*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int saveEntity(List<Map> maps) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		for(int i=0;i<maps.size();i++){
			Map map = maps.get(i);
			String logId = map.get("logId").toString();
			String errId = map.get("errId").toString();
			String jsonStr = map.get("jsonStr").toString();
			
			/*@lineinfo:generated-code*//*@lineinfo:70^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T_RESTAPI_ERR_JSON")
//  					(LOG_ID, ERR_ID, JSON_STR) 
//  				VALUES 
//  					(:logId, :errId, :jsonStr)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_ERR_JSON");
  String __sJT_2 = logId;
  String __sJT_3 = errId;
  String __sJT_4 = jsonStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiErrJsonBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:75^4*/
		}
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<Map> listErrJson(String logId) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		ErrJsonIter errJsonIter = null;
		List<Map> errJsonLst = new ArrayList<Map>();
		
		/*@lineinfo:generated-code*//*@lineinfo:87^3*/

//  ************************************************************
//  #sql [myCtx] errJsonIter = { SELECT * FROM :Com_("D_T_RESTAPI_ERR_JSON") 
//  			WHERE LOG_ID = :logId
//  			ORDER BY ERR_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_RESTAPI_ERR_JSON");
  String __sJT_2 = logId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestApiErrJsonBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      errJsonIter = new ErrJsonIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:91^3*/

		while(errJsonIter.next()){
			Map errJson = new HashMap();
			errJson.put("logId", errJsonIter.LOG_ID());
			errJson.put("errId", errJsonIter.ERR_ID());
			errJson.put("jsonStr", errJsonIter.JSON_STR());
			errJsonLst.add(errJson);
		}
		errJsonIter.close();
		
		closeDefaultContext(myCtx);
		return errJsonLst;
	}
}/*@lineinfo:generated-code*/class RestApiErrJsonBean_SJProfileKeys 
{
  private static RestApiErrJsonBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RestApiErrJsonBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RestApiErrJsonBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.restapi.RestApiErrJsonBean_SJProfile0");
  }
}
