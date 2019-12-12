/*@lineinfo:filename=PickingHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 11 15:05:12 CST 2014 by lyz
 * Last edited on Wed July 22 8:17:12 CST 2015 by lyz
 * 
 * comment: 捡货单表头
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

import pojo.form.PickingHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BillStatus;

public class PickingHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PickingHeaderBean.class);
	
	public int newSerial(java.util.Date businessDate, String branchId) 
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String maxId = null;
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    MAX(h.FORM_ID) 
//  			FROM
//  			:Com_("D_T1_PICKING_HEADER") h
//  			WHERE
//  			    h.FORM_TIME = :bDate
//  			AND h.PICKING_BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  Date __sJT_2 = bDate;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:53^3*/
		closeDefaultContext(myCtx);

		int result = 1;
		if (maxId != null) {
			maxId = maxId.substring(maxId.length() - FormConstant.DEFAULT_SERIAL_LENGTH);
			result = Integer.parseInt(maxId) + 1;
		}
		return result;
	}
	
	public int saveEntity(PickingHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String pickingBranchId = header.getPickingBranchId();
		String storageId = header.getStorageId();
		String storage = header.getStorage();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String pickingManId = header.getPickingManId();
		String pickingMan = header.getPickingMan();
		Date pickingTime = SqlDateUtil.getSqlDate(header.getPickingTime());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:84^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_PICKING_HEADER") 
//  				(FORM_ID, PICKING_BRANCH_ID, STORAGE_ID, STORAGE, FORM_MAKER_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL,
//  						PICKING_MAN_ID, PICKING_MAN, PICKING_TIME, AUDITOR_ID, AUDITOR, AUDIT_TIME, FORM_NOTE) 
//  			VALUES (:formId, :pickingBranchId, :storageId, :storage, :formMakerId, :formMaker, :formTime, :formTimeActual,
//  					:pickingManId, :pickingMan, :pickingTime, :auditorId, :auditor, :auditTime, :formNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = pickingBranchId;
  String __sJT_4 = storageId;
  String __sJT_5 = storage;
  String __sJT_6 = formMakerId;
  String __sJT_7 = formMaker;
  Date __sJT_8 = formTime;
  Date __sJT_9 = formTimeActual;
  String __sJT_10 = pickingManId;
  String __sJT_11 = pickingMan;
  Date __sJT_12 = pickingTime;
  String __sJT_13 = auditorId;
  String __sJT_14 = auditor;
  Date __sJT_15 = auditTime;
  String __sJT_16 = formNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
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
	
	public int updateEntity(PickingHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
//		String storageId = header.getStorageId();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		String pickingManId = header.getPickingManId();
		String pickingMan = header.getPickingMan();
		Date pickingTime = SqlDateUtil.getSqlDate(header.getPickingTime());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:113^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PICKING_HEADER")
//  			SET
//  				FORM_ID = :formId,
//  --				STORAGE_ID = :storageId,
//  				PICKING_MAN_ID = :pickingManId,
//  				PICKING_MAN = :pickingMan,
//  				PICKING_TIME = :pickingTime,
//  				AUDITOR_ID = :auditorId,
//  				AUDITOR = :auditor,
//  				AUDIT_TIME = :auditTime,
//  				FORM_NOTE = :formNote
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = pickingManId;
  String __sJT_4 = pickingMan;
  Date __sJT_5 = pickingTime;
  String __sJT_6 = auditorId;
  String __sJT_7 = auditor;
  Date __sJT_8 = auditTime;
  String __sJT_9 = formNote;
  String __sJT_10 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:128^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:138^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PICKING_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:144^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 标志是否生成装箱单
	 */
	public int savePackStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:156^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PICKING_HEADER")
//  			SET
//  				PACK_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:163^3*/
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
		/*@lineinfo:generated-code*//*@lineinfo:177^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_PICKING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
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
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:191^3*/
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

	/*@lineinfo:generated-code*//*@lineinfo:207^2*/

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
    form_idNdx = findColumn("form_id");
    picking_branch_idNdx = findColumn("picking_branch_id");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    picking_man_idNdx = findColumn("picking_man_id");
    picking_manNdx = findColumn("picking_man");
    picking_timeNdx = findColumn("picking_time");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String picking_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(picking_branch_idNdx);
  }
  private int picking_branch_idNdx;
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public String form_maker_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_maker_idNdx);
  }
  private int form_maker_idNdx;
  public String form_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_makerNdx);
  }
  private int form_makerNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public Date form_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_time_actualNdx);
  }
  private int form_time_actualNdx;
  public String picking_man_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(picking_man_idNdx);
  }
  private int picking_man_idNdx;
  public String picking_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(picking_manNdx);
  }
  private int picking_manNdx;
  public Date picking_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(picking_timeNdx);
  }
  private int picking_timeNdx;
  public String auditor_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditor_idNdx);
  }
  private int auditor_idNdx;
  public String auditor() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditorNdx);
  }
  private int auditorNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:208^134*/
	
	/*@lineinfo:generated-code*//*@lineinfo:210^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class TotalIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TotalIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    totalNdx = findColumn("total");
  }
  public Integer total() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(totalNdx);
  }
  private int totalNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:210^47*/

	public List<PickingHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:220^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_PICKING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
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
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:234^3*/
		List<PickingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<PickingHeader> queryByStatus(java.util.Date businessDate, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:247^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    auditItem --当天已审核的捡货单
//  			    AS
//  			    (
//  			        SELECT
//  			            ph.FORM_ID
//  			        FROM
//  			        :Com_("D_T1_PICKING_HEADER") ph
//  			        INNER JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = ph.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        AND s.status ='已审核'
//  			        WHERE
//  			            ph.AUDIT_TIME=:bDate
//  			    )
//  			    ,
//  			    shippingForm AS
//  			    ( --找到已审核的捡货单的前制单据号
//  			        SELECT
//  			            P.FORM_REF_ID
//  			        FROM
//  			        	:Com_("D_T1_PICKING_REF")  p
//  			        INNER JOIN
//  			            auditItem a
//  			        ON
//  			            a.FORM_ID = p.FORM_ID
//  			        GROUP BY
//  			            P.FORM_REF_ID
//  			    )
//  			--根据前制单据找未审核的捡货单
//  			SELECT DISTINCT
//  			    h.*,
//  			    s.status
//  			FROM
//  			:Com_("D_T1_PICKING_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_PICKING_REF") pr
//  			ON
//  			    h.FORM_ID = pr.FORM_ID
//  			INNER JOIN
//  			    shippingForm sf
//  			ON
//  			    sf.FORM_REF_ID = pr.FORM_REF_ID
//  			INNER JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			AND NVL( s.STATUS, 'null') = '未审核'
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  Date __sJT_3 = bDate;
  String __sJT_4 = Com_("D_T1_PICKING_REF");
  String __sJT_5 = Com_("D_T1_PICKING_HEADER");
  String __sJT_6 = Com_("D_T1_PICKING_REF");
  String __sJT_7 = Com_("D_T0_FORM_STATUS");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:299^3*/
		List<PickingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public int queryByStatusCount(java.util.Date businessDate, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		
		DefaultContext myCtx = getDefaultContext();
		TotalIter totalIter = null;
		Integer total = null;
		/*@lineinfo:generated-code*//*@lineinfo:313^3*/

//  ************************************************************
//  #sql [myCtx] totalIter = { WITH
//  			    auditItem --当天已审核的捡货单
//  			    AS
//  			    (
//  			        SELECT
//  			            ph.FORM_ID
//  			        FROM
//  			        :Com_("D_T1_PICKING_HEADER") ph
//  			        INNER JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = ph.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        AND s.status ='已审核'
//  			        WHERE
//  			            ph.AUDIT_TIME=:bDate
//  			    )
//  			    ,
//  			    shippingForm AS
//  			    ( --找到已审核的捡货单的前制单据号
//  			        SELECT
//  			            P.FORM_REF_ID
//  			        FROM
//  			        	:Com_("D_T1_PICKING_REF")  p
//  			        INNER JOIN
//  			            auditItem a
//  			        ON
//  			            a.FORM_ID = p.FORM_ID
//  			        GROUP BY
//  			            P.FORM_REF_ID
//  			    )
//  			--根据前制单据找未审核的捡货单
//  			SELECT 
//  			   count(*) total
//  			FROM
//  			:Com_("D_T1_PICKING_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_PICKING_REF") pr
//  			ON
//  			    h.FORM_ID = pr.FORM_ID
//  			INNER JOIN
//  			    shippingForm sf
//  			ON
//  			    sf.FORM_REF_ID = pr.FORM_REF_ID
//  			INNER JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			AND NVL( s.STATUS, 'null') = '未审核'
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  Date __sJT_3 = bDate;
  String __sJT_4 = Com_("D_T1_PICKING_REF");
  String __sJT_5 = Com_("D_T1_PICKING_HEADER");
  String __sJT_6 = Com_("D_T1_PICKING_REF");
  String __sJT_7 = Com_("D_T0_FORM_STATUS");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      totalIter = new TotalIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:364^3*/
		
		if(totalIter.next()){
			total = totalIter.total();
		}
		
		totalIter.close();
		closeDefaultContext(myCtx);
		return total!=null?total:0;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:375^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class NodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public NodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
    form_statusNdx = findColumn("form_status");
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
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:375^90*/
	
	/**
	 * 查询拣货单，以树形控件组织，制单日期-〉拣货单
	 */
	public List<Map> queryTree(String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (BillStatus.UNADUIT_US.equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		} else if (BillStatus.AUDITED_US.equals(queryType)) {
			query = query + " AND NVL( s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"; 
		} else if ("unpack".equals(queryType)) {
			query = query + " AND NVL(s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"; 
			query = query + " AND NVL(h.PACK_STATUS, 'null') != '已生成'";
		}
		
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:394^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  			    picking_bill AS
//  			    (
//  			        SELECT
//  			            h.*,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PICKING_HEADER") h
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  						:query
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS form_status
//  			        FROM
//  			            picking_bill h
//  			        GROUP BY
//  			            h.FORM_TIME
//  			        ORDER BY
//  			            h.FORM_TIME DESC)
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            h.form_id                           AS id,
//  			            h.form_id                           AS name,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            form_status
//  			        FROM
//  			            picking_bill h
//  			        ORDER BY
//  			            h.FORM_ID DESC)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:440^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("status", nodeIter.form_status());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}

	public List<Map> auditTree(java.util.Date startDate, java.util.Date endDate, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (BillStatus.UNADUIT_US.equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		} else if (BillStatus.AUDITED_US.equals(queryType)) {
			query = query + " AND NVL( s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"; 
		} else if ("unpack".equals(queryType)) {
			query = query + " AND NVL(s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"; 
			query = query + " AND NVL(h.PACK_STATUS, 'null') != '已生成'";
		}
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:470^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  			    picking_bill AS
//  			    (
//  			        SELECT
//  			            h.*,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PICKING_HEADER") h
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  						:query
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( h.AUDIT_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( h.AUDIT_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS form_status
//  			        FROM
//  			            picking_bill h
//  			            WHERE
//  			           (h.AUDIT_TIME >= :sDate or :sDate is null)
//  						AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  			        GROUP BY
//  			            h.AUDIT_TIME
//  			        ORDER BY
//  			            h.AUDIT_TIME DESC)
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            h.form_id                           AS id,
//  			            h.form_id                           AS name,
//  			            TO_CHAR( h.AUDIT_TIME, 'yyyy-MM-dd') AS parent,
//  			            form_status
//  			        FROM
//  			            picking_bill h
//  			        ORDER BY
//  			            h.FORM_ID DESC)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:519^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("status", nodeIter.form_status());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	/**
	 * 查询拣货单，以树形控件组织，制单日期-〉拣货单,按时间排序
	 */
	public List<Map> queryTree(java.util.Date startDate, java.util.Date endDate, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (BillStatus.UNADUIT_US.equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		} else if (BillStatus.AUDITED_US.equals(queryType)) {
			query = query + " AND NVL( s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"; 
		} else if ("unpack".equals(queryType)) {
			query = query + " AND NVL(s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"; 
			query = query + " AND NVL(h.PACK_STATUS, 'null') != '已生成'";
		}
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:551^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  			    picking_bill AS
//  			    (
//  			        SELECT
//  			            h.*,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PICKING_HEADER") h
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  						:query
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS form_status
//  			        FROM
//  			            picking_bill h
//  			            WHERE
//  			           (h.FORM_TIME >= :sDate or :sDate is null)
//  						AND (h.FORM_TIME <= :eDate or :eDate is null)
//  			        GROUP BY
//  			            h.FORM_TIME
//  			        ORDER BY
//  			            h.FORM_TIME DESC)
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            h.form_id                           AS id,
//  			            h.form_id                           AS name,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            form_status
//  			        FROM
//  			            picking_bill h
//  			        ORDER BY
//  			            h.FORM_ID DESC)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:600^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("status", nodeIter.form_status());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	public List<Map> queryTree(java.util.Date startDate, java.util.Date endDate, String queryType, String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (BillStatus.UNADUIT_US.equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		} else if (BillStatus.AUDITED_US.equals(queryType)) {
			query = query + " AND NVL( s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"; 
		} else if ("unpack".equals(queryType)) {
			query = query + " AND NVL(s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"; 
			query = query + " AND NVL(h.PACK_STATUS, 'null') != '已生成'";
		}
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:630^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  				 a AS (
//  				        SELECT DISTINCT
//  				            t.FORM_ID
//  				        FROM
//  				        :Com_("D_T1_PICKING_DETAIL") t
//  				        WHERE
//  				            1=1
//  				        AND (
//  				                t.ITEM_ID = :itemName
//  				            OR  t.ITEM_NAME = :itemName)
//  				        ORDER BY
//  				            t.FORM_ID
//  				    ),
//  			    picking_bill AS
//  			    (
//  			        SELECT
//  			            h.*,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PICKING_HEADER") h
//  			        INNER JOIN
//  		        	a
//  		        ON
//  		             a.FORM_ID = h.FORM_ID
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  						:query
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS form_status
//  			        FROM
//  			            picking_bill h
//  			            WHERE
//  			           (h.FORM_TIME >= :sDate or :sDate is null)
//  						AND (h.FORM_TIME <= :eDate or :eDate is null)
//  			        GROUP BY
//  			            h.FORM_TIME
//  			        ORDER BY
//  			            h.FORM_TIME DESC)
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            h.form_id                           AS id,
//  			            h.form_id                           AS name,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            form_status
//  			        FROM
//  			            picking_bill h
//  			        ORDER BY
//  			            h.FORM_ID DESC)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_DETAIL");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = Com_("D_T1_PICKING_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = query;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 12);
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
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:696^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("status", nodeIter.form_status());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	public PickingHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:714^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_PICKING_HEADER") h
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingHeaderBean_SJProfileKeys.getKey(0), 13);
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

/*@lineinfo:user-code*//*@lineinfo:721^3*/
		List<PickingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<PickingHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<PickingHeader> headerLst = new ArrayList<PickingHeader>();
		while(headerIter.next()) {
			PickingHeader header = new PickingHeader();
			header.setFormId(headerIter.form_id());
			header.setPickingBranchId(headerIter.picking_branch_id());
			header.setStorageId(headerIter.storage_id());
			header.setStorage(headerIter.storage());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setPickingManId(headerIter.picking_man_id());
			header.setPickingMan(headerIter.picking_man());
			header.setPickingTime(SqlDateUtil.getUtilDate(headerIter.picking_time()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class PickingHeaderBean_SJProfileKeys 
{
  private static PickingHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PickingHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PickingHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.PickingHeaderBean_SJProfile0");
  }
}
