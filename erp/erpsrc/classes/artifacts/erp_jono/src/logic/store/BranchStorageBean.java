/*@lineinfo:filename=BranchStorageBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Dec 16 11:43:26 CST 2014 by pw
 * Last edited on Tue Dec 16 11:43:26 CST 2014 by pw
 * 
 * comment: 仓库部门关联表
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.BranchStorage;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class BranchStorageBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(BranchStorageBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class StoreIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public StoreIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_typeNdx = findColumn("branch_type");
    branch_idNdx = findColumn("branch_id");
    storage_idNdx = findColumn("storage_id");
    storage_nameNdx = findColumn("storage_name");
    priorityNdx = findColumn("priority");
  }
  public String branch_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_typeNdx);
  }
  private int branch_typeNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_nameNdx);
  }
  private int storage_nameNdx;
  public Integer priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(priorityNdx);
  }
  private int priorityNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^20*/
	
	public int updateEntity(BranchStorage store)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String storageId = store.getStorageId();
			String storageName = store.getStorageName();

			/*@lineinfo:generated-code*//*@lineinfo:49^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T2_BRANCH_STORAGE")
//  				SET
//  					STORAGE_NAME = :storageName
//  				WHERE 
//  					STORAGE_ID = :storageId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = storageName;
  String __sJT_3 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:56^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int setMainStorage(BranchStorage store)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String storageId = store.getStorageId();
		String branchId = store.getBranchId();
		Integer priority = store.getPriority();
		
		//更新主仓
		/*@lineinfo:generated-code*//*@lineinfo:71^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BRANCH_STORAGE") bs
//  			SET
//  				priority = :priority
//  			WHERE 
//  				bs.PRIORITY = 0
//  		    AND bs.BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  Integer __sJT_2 = priority;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setIntWrapper(2, __sJT_2);
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
		
		//设置主仓
		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BRANCH_STORAGE") bs
//  			SET
//  				priority = 0
//  			WHERE 
//  				bs.STORAGE_ID = :storageId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:89^3*/
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int saveEntity(BranchStorage store)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return saveEntityImp(store, myCtx);
	}
	
	public int saveEntity(BranchStorage store, String comId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return saveEntityImp(store, myCtx);
	}
	
	private int saveEntityImp(BranchStorage store, DefaultContext myCtx)
		throws NoPrivilegeException, SQLException, NoConnection {

		String branchId = store.getBranchId();
		String storageId = store.getStorageId();
		String storageName = store.getStorageName();
		Integer priority = store.getPriority();
	
		/*@lineinfo:generated-code*//*@lineinfo:116^3*/

//  ************************************************************
//  #sql [myCtx] { MERGE INTO :Com_("D_T2_BRANCH_STORAGE") USING dual 
//  			ON (BRANCH_ID=:branchId AND STORAGE_ID=:storageId)
//  			WHEN MATCHED THEN UPDATE SET STORAGE_NAME=:storageName, PRIORITY=:priority
//  			WHEN NOT MATCHED THEN INSERT (BRANCH_ID, STORAGE_ID, STORAGE_NAME, PRIORITY) 
//  			    VALUES (:branchId, :storageId, :storageName, :priority)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = branchId;
  String __sJT_3 = storageId;
  String __sJT_4 = storageName;
  Integer __sJT_5 = priority;
  String __sJT_6 = branchId;
  String __sJT_7 = storageId;
  String __sJT_8 = storageName;
  Integer __sJT_9 = priority;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setIntWrapper(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setIntWrapper(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:122^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int delete(String branchId,String storageId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:132^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_BRANCH_STORAGE")
//  			WHERE
//  				BRANCH_ID = :branchId
//  			AND
//  				STORAGE_ID = :storageId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = branchId;
  String __sJT_3 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:140^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int delete(String storageId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:150^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T2_BRANCH_STORAGE")
//  			WHERE
//  				STORAGE_ID = :storageId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:156^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int count(String storageId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int count = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:167^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*) 
//  		FROM
//  		:Com_("D_T2_STORAGE") s
//  		WHERE
//  		    s.STORAGE_ID = :storageId
//  		    AND s.ITEM_COUNT != 0
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 6);
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
    count = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:175^3*/
		closeDefaultContext(myCtx);
		
		return count;
	}
	
	public int getMaxPriority(String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Integer priority = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:186^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    	MAX(bs.PRIORITY) 
//  		    FROM
//  		    :Com_("D_T2_BRANCH_STORAGE") bs
//  		    WHERE
//  		        bs.BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 7);
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
    priority = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:193^3*/
		closeDefaultContext(myCtx);
		
		if(priority != null){
			return priority + 1;
		}else{
			return 0;
		}
	}
	
	private String queryCondition(String branchId, String branchType){
		String query = " 1=1";
		
		// storageId 仓库ID
		if (!TextUtil.isEmpty(branchId) && !"-1".equals(branchId)) {
			query += " AND sd.BRANCH_ID = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(branchType)) {
			if(branchType.indexOf(",") != -1){
				query += "AND b.BRANCH_TYPE in ('" + branchType.replaceAll(",", "','") + "')";
			}else{
				query += "AND b.BRANCH_TYPE = '" + branchType + "'";
			}
		}
		
		return query;
	}
	
	public String getStorageName(String branchId,String storageId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String storageName = "";
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:227^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    	'['|| sd.STORAGE_ID || ']'|| sd.STORAGE_NAME 
//  		    FROM
//  		    :Com_("D_T2_BRANCH_STORAGE") sd
//  		    WHERE
//  		        sd.BRANCH_ID = :branchId
//  		    AND sd.STORAGE_ID = :storageId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = branchId;
  String __sJT_3 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 8);
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
    storageName = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:235^3*/
		closeDefaultContext(myCtx);
		return storageName;
	}
	
	/**
	 * 根据部门查询对应的主仓库
	 */
	public BranchStorage queryMainStore(String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		StoreIter storeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:247^3*/

//  ************************************************************
//  #sql [myCtx] storeIter = { SELECT
//  			    bs.*,
//  			    '' as branch_type
//  			FROM
//  			:Com_("D_T2_BRANCH_STORAGE") bs
//  		    WHERE
//  		    	bs.BRANCH_ID = :branchId
//  		    AND PRIORITY = 0
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      storeIter = new StoreIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:256^3*/
		BranchStorage store = new BranchStorage();
		while(storeIter.next()) {
			store.setBranchId(storeIter.branch_id());
			store.setStorageId(storeIter.storage_id());
			store.setStorageName(storeIter.storage_name());
			store.setPriority(storeIter.priority());
			break;
		}
		storeIter.close();
		closeDefaultContext(myCtx);
		return store;
	}

	/**
	 * 根据部门编号或部门类型查询对应的仓库列表
	 */
	public List<BranchStorage> query(String branchId, String branchType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = queryCondition(branchId,branchType);
		
		DefaultContext myCtx = getDefaultContext();
		StoreIter storeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:279^3*/

//  ************************************************************
//  #sql [myCtx] storeIter = { SELECT
//  			    b.BRANCH_TYPE,
//  			    sd.BRANCH_ID,
//  			    sd.STORAGE_ID,
//  			    '['|| sd.STORAGE_ID || ']'|| sd.STORAGE_NAME STORAGE_NAME,
//  			    sd.PRIORITY
//  		    FROM
//  		    	:Com_("D_T2_BRANCH_STORAGE") sd
//  		    LEFT JOIN
//  		    	:Com_("D_T2_BRANCH") b 
//  		    ON
//  		        sd.BRANCH_ID = b.BRANCH_ID
//  		    WHERE
//  		    	:query AND b.ENABLED = 1
//  		    ORDER BY
//  		    	sd.BRANCH_ID,
//  		    	sd.PRIORITY
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      storeIter = new StoreIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:297^3*/
		List<BranchStorage> storeLst = processIter(storeIter);
		storeIter.close();
		closeDefaultContext(myCtx);
		return storeLst;
	}
	
	public BranchStorage queryByStorageId(String storageId)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		StoreIter storeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:309^3*/

//  ************************************************************
//  #sql [myCtx] storeIter = { SELECT
//  			    bs.*,'' branch_type
//  			    FROM
//  			    :Com_("D_T2_BRANCH_STORAGE") bs
//  			    WHERE
//  			        bs.STORAGE_ID = :storageId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchStorageBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      storeIter = new StoreIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:315^38*/
		
		BranchStorage store = null;
		if(storeIter.next()) {
			store = new BranchStorage();
			store.setBranchType(storeIter.branch_type());
			store.setBranchId(storeIter.branch_id());
			store.setStorageId(storeIter.storage_id());
			store.setStorageName(storeIter.storage_name());
			store.setPriority(storeIter.priority());
		}
		
		storeIter.close();
		closeDefaultContext(myCtx);
		return store;
	}
	
	private List<BranchStorage> processIter(StoreIter storeIter) 
			throws SQLException {
		List<BranchStorage> storeLst = new ArrayList<BranchStorage>();
		while(storeIter.next()) {
			BranchStorage store = new BranchStorage();
			store.setBranchType(storeIter.branch_type());
			store.setBranchId(storeIter.branch_id());
			store.setStorageId(storeIter.storage_id());
			store.setStorageName(storeIter.storage_name());
			store.setPriority(storeIter.priority());
			storeLst.add(store);
		}
		return storeLst;
	}
}/*@lineinfo:generated-code*/class BranchStorageBean_SJProfileKeys 
{
  private static BranchStorageBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BranchStorageBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BranchStorageBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.BranchStorageBean_SJProfile0");
  }
}
