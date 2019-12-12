/*@lineinfo:filename=StorageBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 2, 2014 by liyzh
 * Last edited on Sep 2, 2014 by liyzh
 * 
 * 说明： 仓库存储信息表
 */
package logic.store;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;

import pojo.store.Storage;
import com.tanry.framework.util.DateTimeUtil;

import com.tanry.framework.util.TextUtil;
public class StorageBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StorageBean.class);

	public int updateShelf(String shelf, String storeId, String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
			
		/*@lineinfo:generated-code*//*@lineinfo:44^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_STORAGE")
//  			SET
//  				SHELF_ID = :shelf
//  			WHERE
//  			  STORAGE_ID = :storeId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = shelf;
  String __sJT_3 = storeId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:52^3*/
		
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public String getShelf(String storeId,String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(storeId) || TextUtil.isEmpty(itemId)){
			return null;
		}
		
		String shelf = "";
		
		DefaultContext myCtx = getDefaultContext();
		try {
			/*@lineinfo:generated-code*//*@lineinfo:69^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  					SHELF_ID  
//  				FROM
//  				:Com_("D_T2_STORAGE") 
//  				WHERE 
//  					 STORAGE_ID = :storeId
//  					 AND ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = storeId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 1);
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
    shelf = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:77^4*/
		} catch (SQLException e) {
//			log.debug(" storeId : " + storeId);
//			log.debug("Error running the query: " + e);
//			String sqlState = e.getSQLState();
//			log.debug("SQL state = " + sqlState);
//			e.printStackTrace();
		}finally{
			closeDefaultContext(myCtx);
		}
		
		return shelf;
	}

	/**
	 * 根据材料ID和仓库ID获取相应的库存量
	 */
	public Double getItemCount(String itemId, String storeId)
		throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(storeId) || TextUtil.isEmpty(itemId)){
			return 0.0;
		}
		Double inventory = null;
		DefaultContext myCtx = getDefaultContext();
		try {
			/*@lineinfo:generated-code*//*@lineinfo:102^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  					NVL(ITEM_COUNT,0)  
//  				FROM
//  				:Com_("D_T2_STORAGE") 
//  				where 
//  					ITEM_ID = :itemId 
//  				and STORAGE_ID = :storeId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = itemId;
  String __sJT_3 = storeId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 2);
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
    inventory = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:110^4*/
		} catch (SQLException e) {
//			log.debug("itemId ： " + itemId + ", storeId : " + storeId);
//			log.debug("Error running the query: " + e);
//			String sqlState = e.getSQLState();
//			log.debug("SQL state = " + sqlState);
//			e.printStackTrace();
		}finally{
			closeDefaultContext(myCtx);
		}
		
		return inventory;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:124^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemCountIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemCountIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    inventoryNdx = findColumn("inventory");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double inventory() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventoryNdx);
  }
  private int inventoryNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:127^3*/
	
	public List<Map> queryItemCount(String formId, String storeId)throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemCountIter itemCountIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:132^3*/

//  ************************************************************
//  #sql [myCtx] itemCountIter = { SELECT
//  			    c.item_id,
//  			    NVL(s.ITEM_COUNT,0) inventory
//  			FROM
//  			:Com_("D_T1_CHECK_DETAIL") c
//  			LEFT JOIN
//  			:Com_("D_T2_STORAGE") s
//  			ON
//  			    s.ITEM_ID = c.ITEM_ID
//  			AND s.STORAGE_ID = :storeId
//  			WHERE c.FORM_ID= :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_2 = Com_("D_T2_STORAGE");
  String __sJT_3 = storeId;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemCountIter = new ItemCountIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:144^3*/
		List<Map> itemCountLst = processIter(itemCountIter);
		itemCountIter.close();
		closeDefaultContext(myCtx);
		return itemCountLst;
	}
	
	private List<Map> processIter(ItemCountIter itemCountIter) 
			throws SQLException {
		List<Map> itemCountLst = new ArrayList<Map>();
		while(itemCountIter.next()) {
			Map headerMap = new HashMap();
			headerMap.put("itemId",itemCountIter.item_id());
			headerMap.put("itemCount",itemCountIter.inventory());
			itemCountLst.add(headerMap);
		}
		return itemCountLst;
	}

	public Double getItemCount(String itemId, String storeId,DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(storeId) || TextUtil.isEmpty(itemId)){
			return 0.0;
		}
			Double inventory = null;
			try {
				/*@lineinfo:generated-code*//*@lineinfo:170^5*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  						NVL(ITEM_COUNT,0)  
//  					FROM
//  					:Com_("D_T2_STORAGE") 
//  					where 
//  						ITEM_ID = :itemId 
//  					and STORAGE_ID = :storeId
//  				 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = itemId;
  String __sJT_3 = storeId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 4);
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
    inventory = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:178^5*/
			} catch (SQLException e) {
//				log.debug("itemId ： " + itemId + ", storeId : " + storeId);
//				log.debug("Error running the query: " + e);
//				String sqlState = e.getSQLState();
//				log.debug("SQL state = " + sqlState);
//				e.printStackTrace();
			}
			
			return inventory;
		}

	/**
	 * 根据材料ID和门店ID获取所有仓库的库存量
	 */
	public Double getAllItemCount(String itemId, String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
		Double inventory = 0.0;
		if (TextUtil.isEmpty(branchId) || TextUtil.isEmpty(itemId)){
			return 0.0;
		}
		DefaultContext myCtx = getDefaultContext();
		try {
			/*@lineinfo:generated-code*//*@lineinfo:201^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  					round(sum(NVL(s.ITEM_COUNT,0)),2) 
//  				
//  				FROM
//  				:Com_("D_T2_STORAGE") s
//  				LEFT JOIN
//  				:Com_("D_T2_BRANCH_STORAGE") bs
//  				ON
//  				    s.STORAGE_ID = bs.STORAGE_ID
//  				WHERE
//  				    s.ITEM_ID = :itemId
//  				AND bs.BRANCH_ID = :branchId
//  				GROUP BY
//  				    s.ITEM_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = itemId;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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
    inventory = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:217^4*/
		} catch (SQLException e) {
//			log.debug("itemId ： " + itemId + ", branchId : " + branchId);
//			log.debug("Error running the query: " + e);
//			String sqlState = e.getSQLState();
//			log.debug("SQL state = " + sqlState);
//			e.printStackTrace();
		}finally{
			closeDefaultContext(myCtx);
		}
		
		return inventory;
	}

	public int saveEntity(Storage header, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		String storeId = header.getStorageId();
		String itemId = header.getItemId();
		Double itemCount = header.getItemCount();
		String shelfId = header.getShelfId();
		Date lastInTime = SqlDateUtil.getSqlDate(header.getLastInTime());
		Date expiredTime = SqlDateUtil.getSqlDate(header.getExpiredTime());

		/*@lineinfo:generated-code*//*@lineinfo:240^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_STORAGE") 
//  				(STORAGE_ID, ITEM_ID, ITEM_COUNT, SHELF_ID, LAST_IN_TIME, EXPIRED_TIME) 
//  			VALUES (:storeId, :itemId, :itemCount, :shelfId, :lastInTime, :expiredTime)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = storeId;
  String __sJT_3 = itemId;
  Double __sJT_4 = itemCount;
  String __sJT_5 = shelfId;
  Date __sJT_6 = lastInTime;
  Date __sJT_7 = expiredTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:244^3*/
		
		return 1;
	}

	public int updateEntity(Storage header, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		String storeId = header.getStorageId();
		String itemId = header.getItemId();
		Double itemCount = header.getItemCount();
		String shelfId = header.getShelfId();
		Date lastInTime = SqlDateUtil.getSqlDate(header.getLastInTime());
		Date expiredTime = SqlDateUtil.getSqlDate(header.getExpiredTime());

		/*@lineinfo:generated-code*//*@lineinfo:258^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_STORAGE")
//  			SET
//  				ITEM_COUNT = :itemCount,
//  				LAST_IN_TIME = :lastInTime,
//  				EXPIRED_TIME = :expiredTime
//  			WHERE
//  				ITEM_ID = :itemId
//  			and STORAGE_ID = :storeId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  Double __sJT_2 = itemCount;
  Date __sJT_3 = lastInTime;
  Date __sJT_4 = expiredTime;
  String __sJT_5 = itemId;
  String __sJT_6 = storeId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:268^3*/
		
		return 1;
	}

	/**
	 * 盘点为赋值操作
	 */
	public int updateCheckStock(String itemId, String storeId, Double amount) throws NoPrivilegeException,
			SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Storage storage = new Storage();
		storage.setStorageId(storeId);
		storage.setItemId(itemId);
		storage.setItemCount(amount);
		storage.setLastInTime(DateTimeUtil.getNow());

		// 有效期默认当前时间往后推一年
		storage.setExpiredTime(DateTimeUtil.addYears(1));

		Double itemCount = getItemCount(itemId, storeId, myCtx);
		if (itemCount != null) {
			updateEntity(storage, myCtx);
		} else {
			// 插入一条记录
			saveEntity(storage, myCtx);
		}

		closeDefaultContext(myCtx);

		return 1;
	}

	/**
	 * 根据门店id和商品id修改库存，diff为正则增加库存，diff为负则减少库存
	 */
	public int updateItemCount(String itemId, String storeId, Double diff) throws NoPrivilegeException, SQLException,
			NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Storage storage = new Storage();
		storage.setStorageId(storeId);
		storage.setItemId(itemId);
		storage.setItemCount(diff);
		storage.setLastInTime(DateTimeUtil.getNow());

		// 有效期默认当前时间往后推一年
		storage.setExpiredTime(DateTimeUtil.addYears(1));

		Double itemCount = getItemCount(itemId, storeId, myCtx);
		if (itemCount != null) {
			storage.setItemCount(itemCount + diff);
			updateEntity(storage, myCtx);
		} else {
			// 插入一条记录
			saveEntity(storage, myCtx);
		}

		closeDefaultContext(myCtx);

		return 1;
	}

	public int updateItemCount(String itemId, String storeId, Double diff, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		Storage storage = new Storage();
		storage.setStorageId(storeId);
		storage.setItemId(itemId);
		storage.setItemCount(diff);
		storage.setLastInTime(DateTimeUtil.getNow());

		// 有效期默认当前时间往后推一年
		storage.setExpiredTime(DateTimeUtil.addYears(1));

		Double itemCount = getItemCount(itemId, storeId, myCtx);
		if (itemCount != null) {
			storage.setItemCount(itemCount + diff);
			updateEntity(storage, myCtx);
		} else {
			// 插入一条记录
			saveEntity(storage, myCtx);
		}

		return 1;
	}

	public int updateItemCount(String itemId, String storeId, Double itemCount, Double diff)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Storage storage = new Storage();
		storage.setStorageId(storeId);
		storage.setItemId(itemId);
		storage.setItemCount(diff);
		storage.setLastInTime(DateTimeUtil.getNow());

		// 有效期默认当前时间往后推一年
		storage.setExpiredTime(DateTimeUtil.addYears(1));

		if (itemCount != null) {
			storage.setItemCount(itemCount + diff);
			updateEntity(storage, myCtx);
		} else {
			// 插入一条记录
			saveEntity(storage, myCtx);
		}

		closeDefaultContext(myCtx);

		return 1;
	}

	public int updateItemCount(String itemId, String storeId, Double itemCount, Double diff, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		Storage storage = new Storage();
		storage.setStorageId(storeId);
		storage.setItemId(itemId);
		storage.setItemCount(diff);
		storage.setLastInTime(DateTimeUtil.getNow());

		// 有效期默认当前时间往后推一年
		storage.setExpiredTime(DateTimeUtil.addYears(1));

		if (itemCount != null) {
			storage.setItemCount(itemCount + diff);
			updateEntity(storage, myCtx);
		} else {
			// 插入一条记录
			saveEntity(storage, myCtx);
		}

		return 1;
	}

	/**
	 * eg: 同时修改门店和物流中心的库存; 门店增加，则中心减少，反之亦然
	 */
	public int storageInOut(String itemId, String inStoreId, String outStoreId, Double diff)
			throws NoPrivilegeException, SQLException, NoConnection {

		DefaultContext myCtx = getDefaultContext();
		updateItemCount(itemId, inStoreId, diff, myCtx);
		updateItemCount(itemId, outStoreId, -diff, myCtx);

		closeDefaultContext(myCtx);

		return 1;
	}
}/*@lineinfo:generated-code*/class StorageBean_SJProfileKeys 
{
  private static StorageBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StorageBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StorageBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.StorageBean_SJProfile0");
  }
}
