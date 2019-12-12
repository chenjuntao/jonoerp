/*@lineinfo:filename=MonthlySettleRecordBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 25, 2015 by liyzh
 * Last edited on Mar 25, 2015 by liyzh
 * 
 * 说明： 存储每个门店、物流中心和中央工厂以及财务对账时每月的月结记录
 */
package logic.module.hq;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.business.module.hq.settle.pojo.MonthlySettleRecord;

public class MonthlySettleRecordBean extends ConnectionPool {
	
    /*@lineinfo:generated-code*//*@lineinfo:31^5*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SettleIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SettleIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    month_dateNdx = findColumn("month_date");
    operator_idNdx = findColumn("operator_id");
    operator_nameNdx = findColumn("operator_name");
    operating_timeNdx = findColumn("operating_time");
    settle_statusNdx = findColumn("settle_status");
    settle_noteNdx = findColumn("settle_note");
  }
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String branch_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_nameNdx);
  }
  private int branch_nameNdx;
  public Date month_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(month_dateNdx);
  }
  private int month_dateNdx;
  public String operator_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operator_idNdx);
  }
  private int operator_idNdx;
  public String operator_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operator_nameNdx);
  }
  private int operator_nameNdx;
  public Date operating_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(operating_timeNdx);
  }
  private int operating_timeNdx;
  public String settle_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(settle_statusNdx);
  }
  private int settle_statusNdx;
  public String settle_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(settle_noteNdx);
  }
  private int settle_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:32^109*/

	public int saveEntity(MonthlySettleRecord record)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
	
		String branchId = record.getBranchId();
		String branchName = record.getBranchName();
		Date monthDate = SqlDateUtil.getSqlDate(record.getMonthDate());
		String operatorId = record.getOperatorId();
		String operatorName = record.getOperatorName();
		Date operatingTime = SqlDateUtil.getSqlDate(record.getOperatingTime());
		String settleStatus = record.getSettleStatus();
		String settleNote = record.getSettleNote();

		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T3_MONTHLY_SETTLE_RECORD")
//  				(BRANCH_ID, BRANCH_NAME, MONTH_DATE, OPERATOR_ID, 
//  					OPERATOR_NAME, OPERATING_TIME, SETTLE_STATUS, SETTLE_NOTE) 
//  			VALUES (:branchId, 
//  					:branchName,
//  					:monthDate, 
//  					:operatorId,
//  					:operatorName,  
//  					:operatingTime,
//  					:settleStatus, 
//  					:settleNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_MONTHLY_SETTLE_RECORD");
  String __sJT_2 = branchId;
  String __sJT_3 = branchName;
  Date __sJT_4 = monthDate;
  String __sJT_5 = operatorId;
  String __sJT_6 = operatorName;
  Date __sJT_7 = operatingTime;
  String __sJT_8 = settleStatus;
  String __sJT_9 = settleNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MonthlySettleRecordBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:59^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public boolean exist(int year, int month) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:69^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT 
//  				COUNT(*)  
//  			FROM 
//  			:Com_("D_T3_MONTHLY_SETTLE_RECORD")
//  			WHERE
//  			--TO_CHAR(sysdate, 'yyyy-MM') = :monthDate
//  			    EXTRACT(YEAR FROM MONTH_DATE) = :year
//  			AND EXTRACT(MONTH FROM MONTH_DATE) = :month
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_MONTHLY_SETTLE_RECORD");
  int __sJT_2 = year;
  int __sJT_3 = month;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MonthlySettleRecordBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setInt(2, __sJT_2);
      __sJT_stmt.setInt(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:78^3*/
		closeDefaultContext(myCtx);
		
		if (total > 0) {
			return true;
		}
		return false;
	}
	
	public boolean exist(java.util.Date monthDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Date mDate = SqlDateUtil.getSqlDate(monthDate);
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:92^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT 
//  				COUNT(*)  
//  			FROM 
//  			:Com_("D_T3_MONTHLY_SETTLE_RECORD")
//  			WHERE
//  				MONTH_DATE >= :mDate
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_MONTHLY_SETTLE_RECORD");
  Date __sJT_2 = mDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MonthlySettleRecordBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:99^3*/
		closeDefaultContext(myCtx);
		
		if (total > 0) {
			return true;
		}
		return false;
	}
	
	public List<MonthlySettleRecord> query(java.util.Date startDate, java.util.Date endDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		SettleIter settleIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:115^3*/

//  ************************************************************
//  #sql [myCtx] settleIter = { SELECT * FROM :Com_("D_T3_MONTHLY_SETTLE_RECORD")
//  			WHERE (MONTH_DATE >= :sDate or :sDate is null)
//  			and (MONTH_DATE <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_MONTHLY_SETTLE_RECORD");
  Date __sJT_2 = sDate;
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  Date __sJT_5 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MonthlySettleRecordBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      settleIter = new SettleIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:119^3*/
		List<MonthlySettleRecord> settleLst = processIter(settleIter);
		settleIter.close();
		closeDefaultContext(myCtx);
		return settleLst;
	}

	private List<MonthlySettleRecord> processIter(SettleIter settleIter) 
			throws SQLException {
		List<MonthlySettleRecord> settleLst = new ArrayList<MonthlySettleRecord>();
		while(settleIter.next()) {
			MonthlySettleRecord record = new MonthlySettleRecord();
			record.setBranchId(settleIter.branch_id());
			record.setBranchName(settleIter.branch_name());
			record.setMonthDate(SqlDateUtil.getUtilDate(settleIter.month_date()));
			record.setOperatorId(settleIter.operator_id());
			record.setOperatorName(settleIter.operator_name());
			record.setOperatingTime(SqlDateUtil.getUtilDate(settleIter.operating_time()));
			record.setSettleStatus(settleIter.settle_status());
			record.setSettleNote(settleIter.settle_note());
			settleLst.add(record);
		}
		return settleLst;
	}
	

	/**
	 * 查询最近的月结操作月份
	 */
	public java.util.Date queryLastSettleMonth() 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date lastMonth = null;
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:153^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    MAX(MONTH_DATE) 
//  			FROM
//  			:Com_("D_T3_MONTHLY_SETTLE_RECORD")
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_MONTHLY_SETTLE_RECORD");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MonthlySettleRecordBean_SJProfileKeys.getKey(0), 4);
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
    lastMonth = __sJT_rtRs.getDate(1);
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

/*@lineinfo:user-code*//*@lineinfo:158^3*/

		if(lastMonth ==null){
			lastMonth = SqlDateUtil.getSqlDate(DateTimeUtil.parse("1990-10-18"));
		}
		closeDefaultContext(myCtx);
		return SqlDateUtil.getUtilDate(lastMonth);
	}
}/*@lineinfo:generated-code*/class MonthlySettleRecordBean_SJProfileKeys 
{
  private static MonthlySettleRecordBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new MonthlySettleRecordBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private MonthlySettleRecordBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.hq.MonthlySettleRecordBean_SJProfile0");
  }
}
