/*@lineinfo:filename=DailySettleRecordBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
*
* Copyright (c) 2014
* Tanry Electronic Technology Co., Ltd.
* ChangSha, China
*
* All Rights Reserved.
*
* First created on Sep 29, 2014 by cjt
* Last edited on Sep 29, 2014 by cjt
*
* 说明： 访问餐厅每日记录表
*
*/

package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import pojo.store.DailySettleRecord;

public class DailySettleRecordBean extends ConnectionPool {
	
    /*@lineinfo:generated-code*//*@lineinfo:36^5*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DBIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DBIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    business_dateNdx = findColumn("business_date");
    operator_idNdx = findColumn("operator_id");
    operator_nameNdx = findColumn("operator_name");
    operating_timeNdx = findColumn("operating_time");
    settle_statusNdx = findColumn("settle_status");
    settle_noteNdx = findColumn("settle_note");
    is_currentNdx = findColumn("is_current");
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
  public Date business_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(business_dateNdx);
  }
  private int business_dateNdx;
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
  public Integer is_current() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(is_currentNdx);
  }
  private int is_currentNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^2*/
    
    /*@lineinfo:generated-code*//*@lineinfo:48^5*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class OptionIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public OptionIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
    isCheckNdx = findColumn("isCheck");
    isLeafNdx = findColumn("isLeaf");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(nameNdx);
  }
  private int nameNdx;
  public String parent() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parentNdx);
  }
  private int parentNdx;
  public String isCheck() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(isCheckNdx);
  }
  private int isCheckNdx;
  public String isLeaf() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(isLeafNdx);
  }
  private int isLeafNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:54^2*/

	public int saveEntity(DailySettleRecord item)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
	
		String branchId = item.getBranchId();
		String branchName = item.getBranchName();
		Date businessDate = SqlDateUtil.getSqlDate(item.getBusinessDate());
		String operatorId = item.getOperatorId();
		String operatorName = item.getOperatorName();
		Date operatingTime = SqlDateUtil.getSqlDate(item.getOperatingTime());
		String settleStatus = item.getSettleStatus();
		String settleNote = item.getSettleNote();
		Integer isCurrent = item.getIsCurrent();

		/*@lineinfo:generated-code*//*@lineinfo:70^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T3_DAILY_SETTLE_RECORD")
//  				(BRANCH_ID, BRANCH_NAME, BUSINESS_DATE, OPERATOR_ID, 
//  					OPERATOR_NAME, OPERATING_TIME, SETTLE_STATUS, SETTLE_NOTE,IS_CURRENT) 
//  			VALUES (:branchId, 
//  					:branchName,
//  					:businessDate, 
//  					:operatorId,
//  					:operatorName,  
//  					:operatingTime,
//  					:settleStatus, 
//  					:settleNote,
//  					:isCurrent)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_SETTLE_RECORD");
  String __sJT_2 = branchId;
  String __sJT_3 = branchName;
  Date __sJT_4 = businessDate;
  String __sJT_5 = operatorId;
  String __sJT_6 = operatorName;
  Date __sJT_7 = operatingTime;
  String __sJT_8 = settleStatus;
  String __sJT_9 = settleNote;
  Integer __sJT_10 = isCurrent;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailySettleRecordBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setIntWrapper(10, __sJT_10);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:83^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteEntity(String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:93^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  			    :Com_("D_T3_DAILY_SETTLE_RECORD") r
//  			WHERE
//  			    r.BRANCH_ID = :branchId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_SETTLE_RECORD");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailySettleRecordBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:98^31*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	

	public List<DailySettleRecord> query(String branchId,java.util.Date startDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		
		DefaultContext myCtx = getDefaultContext();
		DBIter dbIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:111^3*/

//  ************************************************************
//  #sql [myCtx] dbIter = { SELECT
//  			    *
//  			    FROM
//  			        :Com_("D_T3_DAILY_SETTLE_RECORD") r
//  			    WHERE
//  			        r.BUSINESS_DATE >= :sDate
//  			    AND r.BRANCH_ID = :branchId
//  			    AND r.IS_CURRENT = 1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_SETTLE_RECORD");
  Date __sJT_2 = sDate;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailySettleRecordBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      dbIter = new DBIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:119^28*/
		List<DailySettleRecord> result = processIter(dbIter);
		dbIter.close();
		closeDefaultContext(myCtx);
		return result;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:126^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ShopIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ShopIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    BRANCH_TYPENdx = findColumn("BRANCH_TYPE");
  }
  public String BRANCH_TYPE() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(BRANCH_TYPENdx);
  }
  private int BRANCH_TYPENdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:126^55*/
	public int queryCount(String branchId,java.util.Date startDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		
		DefaultContext myCtx = getDefaultContext();
		DBIter dbIter = null;
		ShopIterator shopItr = null;
		String type = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:136^3*/

//  ************************************************************
//  #sql [myCtx] shopItr = { SELECT
//  				    BRANCH_TYPE
//  				FROM
//  				:Com_("D_T2_BRANCH")
//  				WHERE
//  					BRANCH_ID = :branchId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailySettleRecordBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopItr = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:143^4*/
			
			while(shopItr.next()) {
				type = shopItr.BRANCH_TYPE();
			}
		shopItr.close();
		if (type.equals("FRANCHISEE")||type.equals("OUTERORDER")) 
			return 1; 
		int count=0;
		/*@lineinfo:generated-code*//*@lineinfo:152^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			    FROM
//  			        :Com_("D_T3_DAILY_SETTLE_RECORD") r
//  			    WHERE
//  			        r.BUSINESS_DATE = :sDate
//  			    AND r.BRANCH_ID = :branchId
//  			    AND r.IS_CURRENT = 1
//  			     };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_SETTLE_RECORD");
  Date __sJT_2 = sDate;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailySettleRecordBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:161^8*/
//		List<DailySettleRecord> result = processIter(dbIter);
//		dbIter.close();
		closeDefaultContext(myCtx);
		return count;
	}
	
	public List<Map> queryBranchTree() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		OptionIter optionIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:172^3*/

//  ************************************************************
//  #sql [myCtx] optionIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            b.BRANCH_ID                             id ,
//  			            '['||b.BRANCH_ID||']'||b.BRANCH_NAME AS name,
//  			            b.BRANCH_TYPE                           parent,
//  			            'N'                                     isCheck,
//  			            'Y'                                     isLeaf
//  			        FROM
//  			        	:Com_("D_T2_BRANCH") b 
//  			        WHERE
//  			            b.BRANCH_TYPE = 'RESTAURANT'
//  			        UNION ALL
//  			        SELECT
//  			            'RESTAURANT',
//  			            '餐厅',
//  			            'root',
//  			            'N',
//  			            'N'
//  			        FROM
//  			            dual
//  			    )
//  			    SELECT * FROM a order by 1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailySettleRecordBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      optionIter = new OptionIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:195^34*/
		List<Map> nodeLst = optionIter(optionIter);
		optionIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	public List<Map> queryTree(String branchId,String startDate,String endDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		OptionIter optionIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:208^3*/

//  ************************************************************
//  #sql [myCtx] optionIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            TO_CHAR(to_date(:startDate,'YYYY-MM-DD')+Rownum-1,'yyyy-mm-dd') ymd,
//  			            TO_CHAR(to_date(:startDate,'YYYY-MM-DD')+Rownum-1,'yyyy-mm')    parent
//  			        FROM
//  			            dual CONNECT BY rownum< to_date(:endDate,'YYYY-MM-DD')-to_date(:startDate,
//  			            'YYYY-MM-DD') +2
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            TO_CHAR(to_date(:startDate,'YYYY-MM-DD')+Rownum-1,'yyyy-mm') ym,
//  			            'root'    parent
//  			        FROM
//  			            dual CONNECT BY rownum< to_date(:endDate,'YYYY-MM-DD')-to_date(:startDate,
//  			            'YYYY-MM-DD') +2
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT DISTINCT
//  			            b.*
//  			        FROM
//  			            b
//  			    ), 
//  			    d as(
//  			SELECT
//  			    a.ymd id,
//  			    a.ymd name,
//  			    a.parent,
//  			    DECODE(r.IS_CURRENT,1,'Y','N') isCheck,
//  			    'Y'                            isLeaf
//  			FROM
//  			    a
//  			LEFT JOIN
//  				:Com_("D_T3_DAILY_SETTLE_RECORD") r
//  			ON
//  			    to_date(a.ymd,'YYYY-MM-DD') = r.BUSINESS_DATE
//  			AND r.BRANCH_ID = :branchId
//  			UNION ALL
//  			SELECT
//  			    c.ym id,
//  			    c.ym name,
//  			    c.parent,
//  			    'N' isCheck,
//  			    'N' isLeaf
//  			FROM
//  			    c) 
//  		    SELECT * from  d order by d.id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = startDate;
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = startDate;
  String __sJT_5 = startDate;
  String __sJT_6 = endDate;
  String __sJT_7 = startDate;
  String __sJT_8 = Com_("D_T3_DAILY_SETTLE_RECORD");
  String __sJT_9 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailySettleRecordBean_SJProfileKeys.getKey(0), 6);
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
      __sJT_stmt.setString(9, __sJT_9);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      optionIter = new OptionIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:261^3*/
		List<Map> result = optionIter(optionIter);
		optionIter.close();
		closeDefaultContext(myCtx);
		return result;
	}
	private List<Map> optionIter(OptionIter optionIter) 
			throws SQLException {
		List<Map> result = new ArrayList<Map>();
		while(optionIter.next()) {
			Map tempMap = new HashMap<String, String>();
			tempMap.put("id",optionIter.id());
			tempMap.put("name",optionIter.name());
			tempMap.put("parent",optionIter.parent());
			tempMap.put("isCheck",optionIter.isCheck());
			tempMap.put("isLeaf",optionIter.isLeaf());
			result.add(tempMap);
		}
		return result;
	}
	

	private List<DailySettleRecord> processIter(DBIter dbIter) 
			throws SQLException {
		List<DailySettleRecord> result = new ArrayList<DailySettleRecord>();
		while(dbIter.next()) {
			DailySettleRecord item = new DailySettleRecord();
			item.setBranchId(dbIter.branch_id());
			item.setBranchName(dbIter.branch_name());
			item.setBusinessDate(SqlDateUtil.getUtilDate(dbIter.business_date()));
			item.setOperatorId(dbIter.operator_id());
			item.setOperatorName(dbIter.operator_name());
			item.setOperatingTime(SqlDateUtil.getUtilDate(dbIter.operating_time()));
			item.setSettleStatus(dbIter.settle_status());
			item.setSettleNote(dbIter.settle_note());
			item.setIsCurrent(dbIter.is_current());
			result.add(item);
		}
		return result;
	}
}/*@lineinfo:generated-code*/class DailySettleRecordBean_SJProfileKeys 
{
  private static DailySettleRecordBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DailySettleRecordBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DailySettleRecordBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.DailySettleRecordBean_SJProfile0");
  }
}
