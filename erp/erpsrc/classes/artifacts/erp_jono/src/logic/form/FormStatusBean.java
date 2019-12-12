/*@lineinfo:filename=FormStatusBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 29, 2014 by liyzh
 * Last edited on Aug 29, 2014 by liyzh
 * 
 * 说明： 单据状态表
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.FormStatus;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class FormStatusBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(FormStatusBean.class);
	
	/**
	 * 保存
	 */
	public int saveEntity(FormStatus fstatus)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = fstatus.getFormId();
		String status = fstatus.getStatus();
		Date statusTime = SqlDateUtil.getSqlDate(fstatus.getStatusTime());
		String operator = fstatus.getOperator();
		Integer statusOrder = getMaxOrder(formId,myCtx);
		if (statusOrder != null) {
			statusOrder++;
		} else {
			statusOrder = 0;
		}
		// 先将其他状态记录设置为非当前
		/*@lineinfo:generated-code*//*@lineinfo:50^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T0_FORM_STATUS")
//  			SET
//  				IS_CURRENT = 0
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FormStatusBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:57^3*/
		// IS_CURRENT ： 1表示当前，0表示非当前
		/*@lineinfo:generated-code*//*@lineinfo:59^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_FORM_STATUS")
//  				(FORM_ID, STATUS, STATUS_TIME, OPERATOR, STATUS_ORDER, IS_CURRENT) 
//  				VALUES (:formId, 
//  						:status, 
//  						:statusTime, 
//  						:operator,
//  						:statusOrder,
//  						1)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = formId;
  String __sJT_3 = status;
  Date __sJT_4 = statusTime;
  String __sJT_5 = operator;
  Integer __sJT_6 = statusOrder;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FormStatusBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:68^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 保存特殊含义的单据状态
	 */
	public int saveSpecial(FormStatus fstatus)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();
			String formId = fstatus.getFormId();
			String status = fstatus.getStatus();
			Date statusTime = SqlDateUtil.getSqlDate(fstatus.getStatusTime());
			String operator = fstatus.getOperator();
			Integer statusOrder = getMaxOrder(formId,myCtx);
			Integer isCurrent = fstatus.getIsCurrent();
			if (statusOrder != null) {
				statusOrder++;
			} else {
				statusOrder = 0;
			}
			// IS_CURRENT ： 1表示当前，0表示非当前
			/*@lineinfo:generated-code*//*@lineinfo:92^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_FORM_STATUS")
//  					(FORM_ID, STATUS, STATUS_TIME, OPERATOR, STATUS_ORDER, IS_CURRENT) 
//  					VALUES (:formId, 
//  							:status, 
//  							:statusTime, 
//  							:operator,
//  							:statusOrder,
//  							:isCurrent)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = formId;
  String __sJT_3 = status;
  Date __sJT_4 = statusTime;
  String __sJT_5 = operator;
  Integer __sJT_6 = statusOrder;
  Integer __sJT_7 = isCurrent;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FormStatusBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_stmt.setIntWrapper(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:101^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	/**
	 * 保持单据状态，但是插入一个历史状态
	 */
	public int saveEntityWithoutSetCurrent(FormStatus fstatus)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = fstatus.getFormId();
		String status = fstatus.getStatus();
		Date statusTime = SqlDateUtil.getSqlDate(fstatus.getStatusTime());
		String operator = fstatus.getOperator();
		Integer statusOrder = getMaxOrder(formId, myCtx);
		if (statusOrder != null) {
			statusOrder++;
		} else {
			statusOrder = 0;
		}
		
		// IS_CURRENT ： 1表示当前，0表示非当前
		/*@lineinfo:generated-code*//*@lineinfo:125^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_FORM_STATUS")
//  			(FORM_ID, STATUS, STATUS_TIME, OPERATOR, STATUS_ORDER, IS_CURRENT) 
//  			VALUES (:formId, 
//  					:status, 
//  					:statusTime, 
//  					:operator,
//  					:statusOrder,
//  					0)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = formId;
  String __sJT_3 = status;
  Date __sJT_4 = statusTime;
  String __sJT_5 = operator;
  Integer __sJT_6 = statusOrder;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FormStatusBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:134^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	private Integer getMaxOrder(String formId,DefaultContext myCtx) 
		throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(formId)){
			return null;
		}
		Integer maxOrder = 0;
		try {
			/*@lineinfo:generated-code*//*@lineinfo:147^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    MAX(STATUS_ORDER) 
//  				FROM
//  				:Com_("D_T0_FORM_STATUS")
//  				WHERE
//  					FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FormStatusBean_SJProfileKeys.getKey(0), 4);
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
    maxOrder = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:154^4*/
		} catch (SQLException e) {
			log.debug("getMaxOrder, formId ： " + formId);
			log.debug("Error running the query: " + e);
			String sqlState = e.getSQLState();
			log.debug("SQL state = " + sqlState);
		}
		
		return maxOrder;
	}
	
	public String getCurrentStatus(String formId) 
		throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(formId)){
			return null;
		}
		
		String status = null;
		DefaultContext myCtx = null;
		try {
			myCtx = getDefaultContext();
			
			/*@lineinfo:generated-code*//*@lineinfo:176^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  					STATUS 
//  				FROM
//  				:Com_("D_T0_FORM_STATUS")
//  				WHERE
//  					FORM_ID = :formId
//  				AND IS_CURRENT = 1
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FormStatusBean_SJProfileKeys.getKey(0), 5);
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
    status = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:184^4*/
		} catch (SQLException e) {
			log.debug("getCurrentStatus, formId ： " + formId);
			log.debug("Error running the query: " + e);
			String sqlState = e.getSQLState();
			log.debug("SQL state = " + sqlState);
		}finally{
			if(myCtx!=null){
				closeDefaultContext(myCtx);
			}
		}
		
		return status;
	}
	
	public boolean hasStatus(String formId,String status) 
			throws NoPrivilegeException, SQLException, NoConnection {
		boolean hasStatus = false;
		Integer counts = 0;
		DefaultContext myCtx = null;
		if (TextUtil.isEmpty(formId)){
			return false;
		}
		
		try {
			myCtx = getDefaultContext();
			
			/*@lineinfo:generated-code*//*@lineinfo:211^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*)
//  			
//  			FROM
//  			:Com_("D_T0_FORM_STATUS") s
//  			WHERE
//  			    s.FORM_ID = :formId
//  			AND s.STATUS = :status };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = formId;
  String __sJT_3 = status;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FormStatusBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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
    counts = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:220^26*/
		} catch (SQLException e) {
			String sqlState = e.getSQLState();
			log.debug("SQL state = " + sqlState);
		}finally{
			if(myCtx!=null){
				closeDefaultContext(myCtx);
			}
		}
		
		if(counts > 0){
			hasStatus = true;
		}
		
		return hasStatus;
	}
}/*@lineinfo:generated-code*/class FormStatusBean_SJProfileKeys 
{
  private static FormStatusBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FormStatusBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FormStatusBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.FormStatusBean_SJProfile0");
  }
}
