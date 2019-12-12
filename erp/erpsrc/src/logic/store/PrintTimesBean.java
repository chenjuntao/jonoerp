/*@lineinfo:filename=PrintTimesBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon Dec 28 16:12:43 CST 2015 by pw
 * Last edited on Mon Dec 28 16:12:43 CST 2015 by pw
 * 
 * comment: 打印次数表
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.PrintTimes;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PrintTimesBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PrintTimesBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    timesNdx = findColumn("times");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public Integer times() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(timesNdx);
  }
  private int timesNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^64*/
	
	public int saveEntity(PrintTimes header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		Integer times = header.getTimes();

		/*@lineinfo:generated-code*//*@lineinfo:44^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_PRINT_TIMES") 
//  				(FORM_ID, TIMES) 
//  			VALUES (:formId, :times)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PRINT_TIMES");
  String __sJT_2 = formId;
  Integer __sJT_3 = times;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PrintTimesBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setIntWrapper(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:48^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	private int saveEntity(PrintTimes header,DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
			String formId = header.getFormId();
			Integer times = header.getTimes();

			/*@lineinfo:generated-code*//*@lineinfo:59^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_PRINT_TIMES") 
//  					(FORM_ID, TIMES) 
//  				VALUES (:formId, :times)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PRINT_TIMES");
  String __sJT_2 = formId;
  Integer __sJT_3 = times;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PrintTimesBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setIntWrapper(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:63^4*/
			
			return 1;
		}
	
	public int updateEntity(PrintTimes header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Integer times = queryTimesByFormId(formId,myCtx)+1;
		header.setTimes(times);
		
		deleteEntity(formId,myCtx);
		saveEntity(header,myCtx);
		
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:88^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T0_PRINT_TIMES")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PRINT_TIMES");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PrintTimesBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:94^3*/
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	private int deleteEntity(String formId,DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
			/*@lineinfo:generated-code*//*@lineinfo:103^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T0_PRINT_TIMES")
//  				WHERE
//  					FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PRINT_TIMES");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PrintTimesBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:109^4*/
			
			return 1;
		}
	
	public Integer queryTimesByFormId(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Integer times = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:119^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    h.TIMES 
//  			FROM
//  			:Com_("D_T0_PRINT_TIMES") h
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PRINT_TIMES");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PrintTimesBean_SJProfileKeys.getKey(0), 4);
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
    times = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:126^3*/
		
		closeDefaultContext(myCtx);
		
		times = times == null?0:times;
		return times;
	}
	
	private Integer queryTimesByFormId(String formId,DefaultContext myCtx) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Integer times = null;
		
		try {
			/*@lineinfo:generated-code*//*@lineinfo:139^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    h.TIMES 
//  				FROM
//  				:Com_("D_T0_PRINT_TIMES") h
//  				WHERE
//  					h.form_id = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PRINT_TIMES");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PrintTimesBean_SJProfileKeys.getKey(0), 5);
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
    times = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:146^4*/
		} catch (Exception e) {
		}
		
		times = times == null?0:times;
		return times;
	}
	
	private List<PrintTimes> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<PrintTimes> headerLst = new ArrayList<PrintTimes>();
		while(headerIter.next()) {
			PrintTimes header = new PrintTimes();
			header.setFormId(headerIter.form_id());
			header.setTimes(headerIter.times());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class PrintTimesBean_SJProfileKeys 
{
  private static PrintTimesBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PrintTimesBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PrintTimesBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.PrintTimesBean_SJProfile0");
  }
}
