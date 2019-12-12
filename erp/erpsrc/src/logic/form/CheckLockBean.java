/*@lineinfo:filename=CheckLockBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sun Sep 28 08:55:50 CST 2014 by lyz
 * Last edited on Sun Sep 28 08:55:50 CST 2014 by lyz
 * 
 * comment: 盘点锁库记录表
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.CheckLock;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class CheckLockBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(CheckLockBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class LockIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public LockIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    check_batch_idNdx = findColumn("check_batch_id");
    check_batch_statusNdx = findColumn("check_batch_status");
    lock_branch_idNdx = findColumn("lock_branch_id");
    lock_branchNdx = findColumn("lock_branch");
    lock_storage_idNdx = findColumn("lock_storage_id");
    lock_storageNdx = findColumn("lock_storage");
    lock_man_idNdx = findColumn("lock_man_id");
    lock_manNdx = findColumn("lock_man");
    lock_dateNdx = findColumn("lock_date");
    lock_timeNdx = findColumn("lock_time");
    lock_noteNdx = findColumn("lock_note");
  }
  public String check_batch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_batch_idNdx);
  }
  private int check_batch_idNdx;
  public String check_batch_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_batch_statusNdx);
  }
  private int check_batch_statusNdx;
  public String lock_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(lock_branch_idNdx);
  }
  private int lock_branch_idNdx;
  public String lock_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(lock_branchNdx);
  }
  private int lock_branchNdx;
  public String lock_storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(lock_storage_idNdx);
  }
  private int lock_storage_idNdx;
  public String lock_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(lock_storageNdx);
  }
  private int lock_storageNdx;
  public String lock_man_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(lock_man_idNdx);
  }
  private int lock_man_idNdx;
  public String lock_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(lock_manNdx);
  }
  private int lock_manNdx;
  public Date lock_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(lock_dateNdx);
  }
  private int lock_dateNdx;
  public Date lock_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(lock_timeNdx);
  }
  private int lock_timeNdx;
  public String lock_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(lock_noteNdx);
  }
  private int lock_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^69*/
	/*@lineinfo:generated-code*//*@lineinfo:42^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class BatchIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BatchIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    check_batch_idNdx = findColumn("check_batch_id");
    item_repeatableNdx = findColumn("item_repeatable");
  }
  public String check_batch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_batch_idNdx);
  }
  private int check_batch_idNdx;
  public String item_repeatable() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_repeatableNdx);
  }
  private int item_repeatableNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^79*/
	
	public int newSerial(java.util.Date businessDate, String branchId) 
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String maxId = null;
		int serialLen = FormConstant.DEFAULT_SERIAL_LENGTH;
		//负数(-i)表示截取的开始位置为字符串右端向左数第i个字符
		int negativePos = 0 - serialLen;
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		/*@lineinfo:generated-code*//*@lineinfo:53^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				MAX(SUBSTR(h.CHECK_BATCH_ID, :negativePos, :serialLen) ) 
//  			FROM
//  			    :Com_("D_T1_CHECK_LOCK") h
//  			WHERE
//  			    h.LOCK_DATE = :bDate
//  			AND h.LOCK_BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  int __sJT_1 = negativePos;
  int __sJT_2 = serialLen;
  String __sJT_3 = Com_("D_T1_CHECK_LOCK");
  Date __sJT_4 = bDate;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setInt(1, __sJT_1);
      __sJT_stmt.setInt(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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
    maxId = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:61^3*/
		closeDefaultContext(myCtx);

		int result = 1;
		if (maxId != null) {
			maxId = maxId.substring(maxId.length() - serialLen);
			result = Integer.parseInt(maxId) + 1;
		}
		return result;
	}
	
	public int saveEntity(CheckLock lock)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String checkBatchId = lock.getCheckBatchId();
		String lockBranchId = lock.getLockBranchId();
		String lockBranch = lock.getLockBranch();
		String lockStorageId = lock.getLockStorageId();
		String lockStorage = lock.getLockStorage();
		String lockManId = lock.getLockManId();
		String lockMan = lock.getLockMan();
		Date lockDate = SqlDateUtil.getSqlDate(lock.getLockDate());
		Date lockTime = SqlDateUtil.getSqlDate(lock.getLockTime());
		String lockNote = lock.getLockNote();
		String itemRepeatable = lock.getItemRepeatable();
		String itemCategory = lock.getItemCategory();

		/*@lineinfo:generated-code*//*@lineinfo:89^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_CHECK_LOCK")
//  				(CHECK_BATCH_ID, LOCK_BRANCH_ID, LOCK_BRANCH, LOCK_STORAGE_ID, LOCK_STORAGE, LOCK_MAN_ID, LOCK_MAN, LOCK_DATE, LOCK_TIME, LOCK_NOTE, ITEM_REPEATABLE, ITEM_CATEGORY) 
//  			VALUES (:checkBatchId, :lockBranchId, :lockBranch, :lockStorageId, :lockStorage, :lockManId, :lockMan, :lockDate, :lockTime, :lockNote, :itemRepeatable, :itemCategory)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = checkBatchId;
  String __sJT_3 = lockBranchId;
  String __sJT_4 = lockBranch;
  String __sJT_5 = lockStorageId;
  String __sJT_6 = lockStorage;
  String __sJT_7 = lockManId;
  String __sJT_8 = lockMan;
  Date __sJT_9 = lockDate;
  Date __sJT_10 = lockTime;
  String __sJT_11 = lockNote;
  String __sJT_12 = itemRepeatable;
  String __sJT_13 = itemCategory;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
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

/*@lineinfo:user-code*//*@lineinfo:93^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(CheckLock lock)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String checkBatchId = lock.getCheckBatchId();
		String lockBranchId = lock.getLockBranchId();
		String lockBranch = lock.getLockBranch();
		String lockManId = lock.getLockManId();
		String lockMan = lock.getLockMan();
		Date lockDate = SqlDateUtil.getSqlDate(lock.getLockDate());
		Date lockTime = SqlDateUtil.getSqlDate(lock.getLockTime());
		String lockNote = lock.getLockNote();

		/*@lineinfo:generated-code*//*@lineinfo:112^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_CHECK_LOCK")
//  			SET
//  				CHECK_BATCH_ID = :checkBatchId,
//  				LOCK_BRANCH_ID = :lockBranchId,
//  				LOCK_BRANCH = :lockBranch,
//  				LOCK_MAN_ID = :lockManId,
//  				LOCK_MAN = :lockMan,
//  				LOCK_DATE = :lockDate,
//  				LOCK_TIME = :lockTime,
//  				LOCK_NOTE = :lockNote
//  			WHERE 
//  				CHECK_BATCH_ID = :checkBatchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = checkBatchId;
  String __sJT_3 = lockBranchId;
  String __sJT_4 = lockBranch;
  String __sJT_5 = lockManId;
  String __sJT_6 = lockMan;
  Date __sJT_7 = lockDate;
  Date __sJT_8 = lockTime;
  String __sJT_9 = lockNote;
  String __sJT_10 = checkBatchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:126^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String checkBatchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:136^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T1_CHECK_LOCK")
//  			WHERE
//  				CHECK_BATCH_ID = :checkBatchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = checkBatchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:142^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int finish(String checkBatchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:152^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_CHECK_LOCK")
//  			SET
//  				CHECK_BATCH_STATUS = '已完成'
//  			WHERE 
//  				CHECK_BATCH_ID = :checkBatchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = checkBatchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:159^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:173^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  				:Com_("D_T1_CHECK_LOCK") h
//  			WHERE
//  				:query
//  			AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = query;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:182^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String queryType) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.REQUESTER_ID = '" + branchId + "'";
		}
		if ("unaudit".equals(queryType)) { // 获取未处理记录
			query = query + " AND NVL( s.STATUS, 'null') = '未处理'"; 
		}
		return query;
	}
	
	public List<CheckLock> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		LockIter lockIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:206^3*/

//  ************************************************************
//  #sql [myCtx] lockIter = { SELECT
//  			    *
//  			FROM
//  				:Com_("D_T1_CHECK_LOCK") h
//  			WHERE
//  				:query
//  			AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = query;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      lockIter = new LockIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:215^3*/
		List<CheckLock> lockLst = processIter(lockIter);
		lockIter.close();
		closeDefaultContext(myCtx);
		return lockLst;
	}

	/**
	 * 查询未完成的批次信息
	 */
	public List<Map> queryBatch(String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		BatchIter batchIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:229^3*/

//  ************************************************************
//  #sql [myCtx] batchIter = { SELECT
//  			    l.CHECK_BATCH_ID,
//  			    l.ITEM_REPEATABLE
//  			FROM
//  				:Com_("D_T1_CHECK_LOCK") l
//  			WHERE
//  				l.LOCK_BRANCH_ID = :branchId
//  			AND NVL( l.CHECK_BATCH_STATUS, 'null') != '已完成'
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      batchIter = new BatchIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:238^3*/
		List<Map> batchLst = new ArrayList<Map>();
		while(batchIter.next()) {
			Map batch = new HashMap<String, String>();
			batch.put("batchId", batchIter.check_batch_id());
			batch.put("itemRepeatable", batchIter.item_repeatable());
			batchLst.add(batch);
		}
		batchIter.close();
		closeDefaultContext(myCtx);
		return batchLst;
	}
	
	public CheckLock queryById(String checkBatchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		LockIter lockIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:255^3*/

//  ************************************************************
//  #sql [myCtx] lockIter = { SELECT
//  			    *
//  			FROM
//  				:Com_("D_T1_CHECK_LOCK") h
//  			WHERE
//  				CHECK_BATCH_ID = :checkBatchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = checkBatchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckLockBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      lockIter = new LockIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:262^3*/
		List<CheckLock> lockLst = processIter(lockIter);
		lockIter.close();
		closeDefaultContext(myCtx);
		if (lockLst !=null && !lockLst.isEmpty()) {
			return lockLst.get(0);
		}
		return null;
	}
	
	private List<CheckLock> processIter(LockIter lockIter) 
			throws SQLException {
		List<CheckLock> lockLst = new ArrayList<CheckLock>();
		while(lockIter.next()) {
			CheckLock lock = new CheckLock();
			lock.setCheckBatchId(lockIter.check_batch_id());
			lock.setCheckBatchStatus(lockIter.check_batch_status());
			lock.setLockBranchId(lockIter.lock_branch_id());
			lock.setLockBranch(lockIter.lock_branch());
			lock.setLockStorageId(lockIter.lock_storage_id());
			lock.setLockStorage(lockIter.lock_storage());
			lock.setLockManId(lockIter.lock_man_id());
			lock.setLockMan(lockIter.lock_man());
			lock.setLockDate(SqlDateUtil.getUtilDate(lockIter.lock_date()));
			lock.setLockTime(SqlDateUtil.getUtilDate(lockIter.lock_time()));
			lock.setLockNote(lockIter.lock_note());
			lockLst.add(lock);
		}
		return lockLst;
	}
}/*@lineinfo:generated-code*/class CheckLockBean_SJProfileKeys 
{
  private static CheckLockBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CheckLockBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CheckLockBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.CheckLockBean_SJProfile0");
  }
}
