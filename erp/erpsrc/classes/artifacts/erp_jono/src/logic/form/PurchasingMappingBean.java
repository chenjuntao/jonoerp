/*@lineinfo:filename=PurchasingMappingBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 15, 2014 by liyzh
 * Last edited on Dec 15, 2014 by liyzh
 * 
 * 说明： 物流中心采购直配订单（大小单据关联）表
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PurchasingMapping;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PurchasingMappingBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PurchasingMappingBean.class);
	
	/**
	 * 保存
	 */
	public int saveEntity(PurchasingMapping mapping)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String bigFormId = mapping.getBigFormId();
		String smallFormId = mapping.getSmallFormId();

		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_PURCHASING_MAPPING")
//  				(BIG_FORM_ID, SMALL_FORM_ID) 
//  			VALUES (:bigFormId, :smallFormId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_MAPPING");
  String __sJT_2 = bigFormId;
  String __sJT_3 = smallFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingMappingBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:51^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:57^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class SmallIdIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SmallIdIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    small_form_idNdx = findColumn("small_form_id");
  }
  public String small_form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(small_form_idNdx);
  }
  private int small_form_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:57^62*/
		
	public List<String> querySmallFormId(String bigFormId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		SmallIdIter smallIdIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:64^3*/

//  ************************************************************
//  #sql [myCtx] smallIdIter = { SELECT
//  				SMALL_FORM_ID
//  			FROM
//  			:Com_("D_T1_PURCHASING_MAPPING")
//  			WHERE
//  				BIG_FORM_ID = :bigFormId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_MAPPING");
  String __sJT_2 = bigFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingMappingBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      smallIdIter = new SmallIdIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:71^3*/

		List<String> formIdLst = new ArrayList<String>();
		while (smallIdIter.next()) {
			formIdLst.add(smallIdIter.small_form_id());
		}
		smallIdIter.close();
		closeDefaultContext(myCtx);
		return formIdLst;
	}
	
	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:86^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PURCHASING_MAPPING")
//  			WHERE
//  				BIG_FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_MAPPING");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingMappingBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:92^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	
}/*@lineinfo:generated-code*/class PurchasingMappingBean_SJProfileKeys 
{
  private static PurchasingMappingBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PurchasingMappingBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PurchasingMappingBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.PurchasingMappingBean_SJProfile0");
  }
}
