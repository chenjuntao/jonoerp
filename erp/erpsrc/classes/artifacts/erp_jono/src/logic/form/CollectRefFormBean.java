/*@lineinfo:filename=CollectRefFormBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon Nov 03 10:14:53 CST 2014 by pw
 * Last edited on Mon Nov 03 10:14:53 CST 2014 by pw
 * 
 */
package logic.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.form.CollectRefForm;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;

public class CollectRefFormBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(CollectRefFormBean.class);
	
	public int saveEntity(CollectRefForm header)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String collectFormId = header.getCollectFormId();
		String itemId = header.getItemId();
		String refFormId = header.getRefFormId();

		/*@lineinfo:generated-code*//*@lineinfo:41^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_COLLECT_REF_FORM")
//  				(COLLECT_FORM_ID, ITEM_ID, REF_FORM_ID) 
//  			VALUES (:collectFormId, :itemId, :refFormId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_2 = collectFormId;
  String __sJT_3 = itemId;
  String __sJT_4 = refFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CollectRefFormBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:45^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:55^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T1_COLLECT_REF_FORM")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CollectRefFormBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:61^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:67^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class RefIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public RefIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    ref_form_idNdx = findColumn("ref_form_id");
  }
  public String ref_form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ref_form_idNdx);
  }
  private int ref_form_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:67^50*/
	
	public String queryRefs(String formId, String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RefIter refIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:73^3*/

//  ************************************************************
//  #sql [myCtx] refIter = { SELECT
//  			    r.REF_FORM_ID
//  			FROM
//  			:Com_("D_T1_COLLECT_REF_FORM") r
//  			WHERE
//  				r.COLLECT_FORM_ID = :formId
//  			and r.item_id = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CollectRefFormBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      refIter = new RefIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:81^3*/
		List<String> refLst = new ArrayList<String>();
		while(refIter.next()) {
			refLst.add(refIter.ref_form_id());
		}
		refIter.close();
		closeDefaultContext(myCtx);
		return StringUtils.join(refLst, ",");
	}
	
	public Integer getSummarycount(String refFormIds) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		String query = " r.REF_FORM_ID IN ('" + refFormIds.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		Integer total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:98^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_COLLECT_REF_FORM") r
//  		WHERE
//  			:query };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_2 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CollectRefFormBean_SJProfileKeys.getKey(0), 3);
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
    total = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:106^19*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public String queryRefs(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RefIter refIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:115^3*/

//  ************************************************************
//  #sql [myCtx] refIter = { SELECT DISTINCT
//  			    r.REF_FORM_ID
//  			    FROM
//  			    :Com_("D_T1_COLLECT_REF_FORM") r
//  			    WHERE
//  			        r.COLLECT_FORM_ID = :formId
//  			    ORDER BY
//  			        r.REF_FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CollectRefFormBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      refIter = new RefIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:123^25*/
		List<String> refLst = new ArrayList<String>();
		while(refIter.next()) {
			refLst.add(refIter.ref_form_id());
		}
		refIter.close();
		closeDefaultContext(myCtx);
		return StringUtils.join(refLst, ",");
	}
	
	public String queryDirectRefs(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RefIter refIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:137^3*/

//  ************************************************************
//  #sql [myCtx] refIter = { SELECT DISTINCT
//  			    r.REF_FORM_ID
//  			    FROM
//  			    :Com_("D_T1_COLLECT_REF_FORM") r
//  			INNER JOIN
//  				:Com_("D_T1_PURCHASING_HEADER") ch
//  			ON
//  			    ch.FORM_ID=r.COLLECT_FORM_ID
//  			INNER JOIN
//  			:Com_("D_T1_PURCHASING_MAPPING") m
//  			ON
//  			    m.SMALL_FORM_ID = ch.FORM_ID
//  			WHERE
//  			    m.BIG_FORM_ID=:formId
//  			    ORDER BY
//  			        r.REF_FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_2 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_3 = Com_("D_T1_PURCHASING_MAPPING");
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CollectRefFormBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      refIter = new RefIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:153^25*/
		List<String> refLst = new ArrayList<String>();
		while(refIter.next()) {
			refLst.add(refIter.ref_form_id());
		}
		refIter.close();
		closeDefaultContext(myCtx);
		return StringUtils.join(refLst, ",");
	}
}/*@lineinfo:generated-code*/class CollectRefFormBean_SJProfileKeys 
{
  private static CollectRefFormBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CollectRefFormBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CollectRefFormBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.CollectRefFormBean_SJProfile0");
  }
}
