/*@lineinfo:filename=ShelfItemBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Apr 16 10:47:58 CST 2015 by lyz
 * Last edited on Thu Apr 16 10:47:58 CST 2015 by lyz
 * 
 * comment: 库位与物料关系表，主要供物流中心捡货使用
 */
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.store.ShelfItem;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;

public class ShelfItemBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShelfItemBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ShelfItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ShelfItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    shelf_idNdx = findColumn("shelf_id");
    item_idNdx = findColumn("item_id");
    priorityNdx = findColumn("priority");
  }
  public String shelf_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_idNdx);
  }
  private int shelf_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Integer priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(priorityNdx);
  }
  private int priorityNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^87*/
	
	public int saveEntity(ShelfItem shelfItem)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String shelfId = shelfItem.getShelfId();
		String itemId = shelfItem.getItemId();

		// 取消以前的主库位
		/*@lineinfo:generated-code*//*@lineinfo:46^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_SHELF_ITEM")
//  			SET
//  				PRIORITY = 1
//  			WHERE
//  				SHELF_ID != :shelfId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = shelfId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:54^3*/
				
		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_SHELF_ITEM") 
//  				(SHELF_ID, ITEM_ID, PRIORITY) 
//  			VALUES (:shelfId, :itemId, 0)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = shelfId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:60^3*/
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateMainShelf(String shelfId, String itemId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:71^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_SHELF_ITEM")
//  			SET
//  				PRIORITY = 0
//  			WHERE 
//  				SHELF_ID = :shelfId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = shelfId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:79^3*/

		// 取消以前的主库位
		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_SHELF_ITEM")
//  			SET
//  				PRIORITY = 1
//  			WHERE
//  				SHELF_ID != :shelfId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = shelfId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:90^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ShelfItem shelfItem)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String shelfId = shelfItem.getShelfId();
		String itemId = shelfItem.getItemId();
		Integer priority = shelfItem.getPriority();

		/*@lineinfo:generated-code*//*@lineinfo:104^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_SHELF_ITEM")
//  			SET
//  				SHELF_ID = :shelfId,
//  				ITEM_ID = :itemId,
//  				PRIORITY = :priority
//  			WHERE 
//  				SHELF_ID = :shelfId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = shelfId;
  String __sJT_3 = itemId;
  Integer __sJT_4 = priority;
  String __sJT_5 = shelfId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:113^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String shelfId, List<String> itemIdLst)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String query = " ITEM_ID IN ('" + StringUtils.join(itemIdLst, "','") + "') ";
		/*@lineinfo:generated-code*//*@lineinfo:124^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_SHELF_ITEM")
//  			WHERE
//  				SHELF_ID = :shelfId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = shelfId;
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:131^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteEntity(String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();
			/*@lineinfo:generated-code*//*@lineinfo:140^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T2_SHELF_ITEM") h
//  			WHERE
//  				h.ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:146^4*/
			closeDefaultContext(myCtx);
			return 1;
		}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:155^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T2_SHELF_ITEM") h
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
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

/*@lineinfo:user-code*//*@lineinfo:160^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public List<ShelfItem> query() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ShelfItemIter shelfItemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:169^3*/

//  ************************************************************
//  #sql [myCtx] shelfItemIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_SHELF_ITEM") h
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shelfItemIter = new ShelfItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:174^3*/
		List<ShelfItem> shelfItemLst = processIter(shelfItemIter);
		shelfItemIter.close();
		closeDefaultContext(myCtx);
		return shelfItemLst;
	}
	
	public ShelfItem queryById(String shelfId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ShelfItemIter shelfItemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:185^3*/

//  ************************************************************
//  #sql [myCtx] shelfItemIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_SHELF_ITEM") h
//  			WHERE
//  				h.SHELF_ID = :shelfId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = shelfId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shelfItemIter = new ShelfItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:192^3*/
		List<ShelfItem> shelfItemLst = processIter(shelfItemIter);
		shelfItemIter.close();
		closeDefaultContext(myCtx);
		if (shelfItemLst !=null && !shelfItemLst.isEmpty()) {
			return shelfItemLst.get(0);
		}
		return null;
	}
	
	public ShelfItem queryByItemId(String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ShelfItemIter shelfItemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:206^3*/

//  ************************************************************
//  #sql [myCtx] shelfItemIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_SHELF_ITEM") h
//  			WHERE
//  				h.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shelfItemIter = new ShelfItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:213^3*/
		List<ShelfItem> shelfItemLst = processIter(shelfItemIter);
		shelfItemIter.close();
		closeDefaultContext(myCtx);
		if (shelfItemLst !=null && !shelfItemLst.isEmpty()) {
			return shelfItemLst.get(0);
		}
		return null;
	}
	
	private List<ShelfItem> processIter(ShelfItemIter shelfItemIter) 
			throws SQLException {
		List<ShelfItem> shelfItemLst = new ArrayList<ShelfItem>();
		while(shelfItemIter.next()) {
			ShelfItem shelfItem = new ShelfItem();
			shelfItem.setShelfId(shelfItemIter.shelf_id());
			shelfItem.setItemId(shelfItemIter.item_id());
			shelfItem.setPriority(shelfItemIter.priority());
			shelfItemLst.add(shelfItem);
		}
		return shelfItemLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:236^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    query_codeNdx = findColumn("query_code");
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    priorityNdx = findColumn("priority");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public Integer priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(priorityNdx);
  }
  private int priorityNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:238^47*/
	
	/**
	 * 从关系表中根据库位获取相应的商品列表
	 */
	public List<Map> queryItem(String shelfId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:247^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME,
//  			    m.QUERY_CODE,
//  			    m.CATEGORY_ID,
//  			    c.CATEGORY_NAME,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    s.PRIORITY
//  			FROM
//  			:Com_("D_T2_SHELF_ITEM") s
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = s.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			WHERE
//  				s.SHELF_ID = :shelfId
//  			ORDER BY
//  				m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_4 = shelfId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:271^3*/
		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", itemIter.item_id());
			item.put("itemName", itemIter.item_name());
			item.put("queryCode", itemIter.query_code());
			item.put("categoryId", itemIter.category_id());
			item.put("categoryName", itemIter.category_name());
			item.put("itemDimension", itemIter.item_dimension());
			item.put("itemSpecification", itemIter.item_specification());
			item.put("priority", itemIter.priority());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/**
	 * 模糊查询商品，属于当前库位的除外
	 */
	public List<Map> queryExceptItem(String itemName, String shelfId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:299^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME,
//  			    m.QUERY_CODE,
//  			    m.CATEGORY_ID,
//  			    c.CATEGORY_NAME,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    1 AS PRIORITY
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			WHERE
//  			    m.ITEM_ID NOT IN --except current shelf
//  			    (
//  			        SELECT
//  			            s.ITEM_ID
//  			        FROM
//  			        :Com_("D_T2_SHELF_ITEM") s
//  			        WHERE
//  			            s.SHELF_ID = :shelfId
//  			    )
//  			AND (
//  			        m.ITEM_ID LIKE :itemName
//  			    OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T2_SHELF_ITEM");
  String __sJT_4 = shelfId;
  String __sJT_5 = itemName;
  String __sJT_6 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShelfItemBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:330^3*/
		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", itemIter.item_id());
			item.put("itemName", itemIter.item_name());
			item.put("queryCode", itemIter.query_code());
			item.put("categoryId", itemIter.category_id());
			item.put("categoryName", itemIter.category_name());
			item.put("itemDimension", itemIter.item_dimension());
			item.put("itemSpecification", itemIter.item_specification());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class ShelfItemBean_SJProfileKeys 
{
  private static ShelfItemBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShelfItemBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShelfItemBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.ShelfItemBean_SJProfile0");
  }
}
