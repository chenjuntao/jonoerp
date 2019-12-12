/*@lineinfo:filename=ItemWorkShopBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Sep 24 09:38:50 CST 2015 by pw
 * Last edited on Thu Sep 24 09:38:50 CST 2015 by pw
 * 
 * comment: 物料与车间对应关系表
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.ItemWorkShop;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ItemWorkShopBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ItemWorkShopBean.class);
	
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
    factory_idNdx = findColumn("factory_id");
    work_order_idNdx = findColumn("work_order_id");
    item_idNdx = findColumn("item_id");
  }
  public String factory_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(factory_idNdx);
  }
  private int factory_idNdx;
  public String work_order_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(work_order_idNdx);
  }
  private int work_order_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^90*/
	
	public int saveEntity(ItemWorkShop header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String factoryId = header.getFactoryId();
		String workOrderId = header.getWorkOrderId();
		String itemId = header.getItemId();

		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_ITEM_WORKSHOP") 
//  				(FACTORY_ID, WORK_ORDER_ID, ITEM_ID) 
//  			VALUES (:factoryId, :workOrderId, :itemId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_WORKSHOP");
  String __sJT_2 = factoryId;
  String __sJT_3 = workOrderId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemWorkShopBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:49^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ItemWorkShop header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String factoryId = header.getFactoryId();
		String workOrderId = header.getWorkOrderId();
		String itemId = header.getItemId();

		/*@lineinfo:generated-code*//*@lineinfo:63^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_ITEM_WORKSHOP")
//  			SET
//  				FACTORY_ID = :factoryId,
//  				WORK_ORDER_ID = :workOrderId
//  			WHERE 
//  			ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_WORKSHOP");
  String __sJT_2 = factoryId;
  String __sJT_3 = workOrderId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemWorkShopBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:71^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String itemId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:81^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_ITEM_WORKSHOP")
//  			WHERE
//  			ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_WORKSHOP");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemWorkShopBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:87^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	
	public ItemWorkShop queryById(String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:98^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_ITEM_WORKSHOP") h
//  			WHERE
//  				h.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_WORKSHOP");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemWorkShopBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:105^3*/
		List<ItemWorkShop> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<ItemWorkShop> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ItemWorkShop> headerLst = new ArrayList<ItemWorkShop>();
		while(headerIter.next()) {
			ItemWorkShop header = new ItemWorkShop();
			header.setFactoryId(headerIter.factory_id());
			header.setWorkOrderId(headerIter.work_order_id());
			header.setItemId(headerIter.item_id());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ItemWorkShopBean_SJProfileKeys 
{
  private static ItemWorkShopBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ItemWorkShopBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ItemWorkShopBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.ItemWorkShopBean_SJProfile0");
  }
}
