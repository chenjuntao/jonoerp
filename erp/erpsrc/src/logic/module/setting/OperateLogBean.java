/*@lineinfo:filename=OperateLogBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 29, 2015 by liyzh
 * Last edited on Jul 29, 2015 by liyzh
 * 
 * 说明： 保存操作日志等
 */
package logic.module.setting;

import java.sql.Date;
import java.sql.SQLException;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.pojo.ConnectionLog;
import com.tanry.framework.log.pojo.OperateLog;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;

public class OperateLogBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(OperateLogBean.class);
	
	public int saveEntity(OperateLog logItem)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String id = TextUtil.uuid();
		String menuId = logItem.getMenuId();
		String menuName = logItem.getMenuName();
		String url = logItem.getUrl();
		
		String businessId = logItem.getBusinessId();
		
		String operateType = logItem.getOperateType();
		String title = logItem.getTitle();
		String operateDesc = logItem.getOperateDesc();
		
		String operatorId = logItem.getOperatorId();
		String operatorName = logItem.getOperatorName();
		Date operateTime = SqlDateUtil.getSqlDate(logItem.getOperateTime());
		
		String branchId = logItem.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_OPERATELOG") (ID, MENU_ID, MENU_NAME, URL, BUSINESS_ID, 
//  				OPERATE_TYPE, TITLE, OPERATE_DESC, OPERATOR_ID, OPERATOR_NAME, OPERATE_TIME,BRANCH_ID) 
//  			VALUES (:id, :menuId, :menuName, :url, :businessId, 
//  				:operateType, :title, :operateDesc, :operatorId, :operatorName, :operateTime,:branchId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_OPERATELOG");
  String __sJT_2 = id;
  String __sJT_3 = menuId;
  String __sJT_4 = menuName;
  String __sJT_5 = url;
  String __sJT_6 = businessId;
  String __sJT_7 = operateType;
  String __sJT_8 = title;
  String __sJT_9 = operateDesc;
  String __sJT_10 = operatorId;
  String __sJT_11 = operatorName;
  Date __sJT_12 = operateTime;
  String __sJT_13 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OperateLogBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:61^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int saveConnLog(ConnectionLog log)
		throws NoPrivilegeException, SQLException {
		DefaultContext myCtx = getDefaultContext();
		
		String id = TextUtil.uuid();
		
		String api = log.getApi();
		String apiType = log.getApiType();
		Long consumeTime = log.getConsumeTime();
		Integer connInUse = log.getConnInUse();

		String host = log.getHost();
		
		String operatorId = log.getOperatorId();
		String operatorName = log.getOperatorName();
		Date logTime = SqlDateUtil.getSqlDate(log.getLogTime());

		/*@lineinfo:generated-code*//*@lineinfo:84^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_CONN_LOG") (ID, API,  
//  				API_TYPE, CONSUME_TIME, CONN_IN_USE, HOST, OPERATOR_ID, OPERATOR_NAME, LOG_TIME) 
//  			VALUES (:id, :api,  
//  				:apiType, :consumeTime, :connInUse, :host, :operatorId, :operatorName, :logTime)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_CONN_LOG");
  String __sJT_2 = id;
  String __sJT_3 = api;
  String __sJT_4 = apiType;
  Long __sJT_5 = consumeTime;
  Integer __sJT_6 = connInUse;
  String __sJT_7 = host;
  String __sJT_8 = operatorId;
  String __sJT_9 = operatorName;
  Date __sJT_10 = logTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OperateLogBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setLongWrapper(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:89^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
}/*@lineinfo:generated-code*/class OperateLogBean_SJProfileKeys 
{
  private static OperateLogBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new OperateLogBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private OperateLogBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.setting.OperateLogBean_SJProfile0");
  }
}
