/*@lineinfo:filename=SupplierBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 27, 2014 by liyzh
 * Last edited on Aug 27, 2014 by liyzh
 * 
 * 说明：供应商基本信息表
 */
package logic.store;

import java.sql.SQLException;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.acl.NoPrivilegeException;

public class SupplierBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(SupplierBean.class);
	
	/**
	 * 根据原材料ID获取相应的主供应商信息
	 */
	public String getSupplier(String itemId)
		throws NoPrivilegeException, SQLException, NoConnection {
		String supplier = null;
		DefaultContext myCtx = getDefaultContext();
		try {
			/*@lineinfo:generated-code*//*@lineinfo:37^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    s.BRANCH_NAME 
//  				FROM
//  					:Com_("D_T2_BRANCH") s,
//  					:Com_("D_T2_SUPPLIER_BRANCH_ITEM") i
//  				WHERE
//  				    i.SUPPLIER_ID = s.BRANCH_ID
//  				AND i.ITEM_ID = :itemId
//  				AND i.PRIORITY = 0
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBean_SJProfileKeys.getKey(0), 0);
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
    supplier = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:47^4*/
		} catch (SQLException e) {
			log.error("get main supplier for itemId : " + itemId);
			log.error("Error running the query: " + e);
			String sqlState = e.getSQLState();
			log.error("SQL state = " + sqlState);
			e.printStackTrace();
		}
		closeDefaultContext(myCtx);
		
		return supplier;
	}
	
	/**
	 * 根据原材料ID获取相应的主供应商信息id
	 */
	public String getSupplierId(String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
		String supplierId = null;
		DefaultContext myCtx = getDefaultContext();
		try {
			/*@lineinfo:generated-code*//*@lineinfo:68^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  					s.SUPPLIER_ID 
//  				FROM
//  					:Com_("D_T2_SUPPLIER") s,
//  					:Com_("D_T2_SUPPLIER_BRANCH_ITEM") i
//  				WHERE
//  					i.SUPPLIER_ID = s.SUPPLIER_ID
//  				AND i.ITEM_ID = :itemId
//  				AND i.PRIORITY = 0
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBean_SJProfileKeys.getKey(0), 1);
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
    supplierId = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:78^4*/
		} catch (SQLException e) {
			log.error("get main supplier for itemId : " + itemId);
			log.error("Error running the query: " + e);
			String sqlState = e.getSQLState();
			log.error("SQL state = " + sqlState);
			e.printStackTrace();
		}
		closeDefaultContext(myCtx);
		
		return supplierId;
	}
}/*@lineinfo:generated-code*/class SupplierBean_SJProfileKeys 
{
  private static SupplierBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SupplierBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SupplierBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.SupplierBean_SJProfile0");
  }
}
