/*@lineinfo:filename=RequisitionHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 11 10:31:07 CST 2014 by pw
 * Last edited on Tue Nov 11 10:31:07 CST 2014 by pw
 * 
 * comment: 中央工厂生产领料单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.RequisitionHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class RequisitionHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequisitionHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

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
    form_typeNdx = findColumn("form_type");
    form_ref_idNdx = findColumn("form_ref_id");
    item_nameNdx = findColumn("item_name");
    mainNameNdx = findColumn("mainName");
    workshopNdx = findColumn("workshop");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    audit_time_actualNdx = findColumn("audit_time_actual");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    form_statusNdx = findColumn("form_status");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String form_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_typeNdx);
  }
  private int form_typeNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String mainName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(mainNameNdx);
  }
  private int mainNameNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
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
  public Date audit_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_time_actualNdx);
  }
  private int audit_time_actualNdx;
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
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^130*/
	
	public int newSerial(java.util.Date businessDate, String formType, String branchId) 
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String maxId = null;
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		/*@lineinfo:generated-code*//*@lineinfo:46^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    MAX(h.FORM_ID) 
//  			FROM
//  			:Com_("D_T1_REQUISITION_HEADER") h
//  			WHERE
//  			    h.FORM_TIME = :bDate
//  			AND h.FORM_TYPE = :formType
//  			AND h.FORM_BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  Date __sJT_2 = bDate;
  String __sJT_3 = formType;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:55^3*/
		closeDefaultContext(myCtx);
		
		int result = 1;
		if (maxId != null) {
			maxId = maxId.substring(maxId.length() - FormConstant.DEFAULT_SERIAL_LENGTH);
			result = Integer.parseInt(maxId) + 1;
		}
		return result;
	}
	
	public int saveEntity(RequisitionHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String formType = header.getFormType();
		String formRefId = header.getFormRefId();
		String workshop = header.getWorkshop();
		String storageId = header.getStorageId();
		String storage = header.getStorage();
		String formBranchId = header.getFormBranchId();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:86^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_REQUISITION_HEADER")
//  				(FORM_ID, FORM_TYPE, FORM_REF_ID, WORKSHOP, STORAGE_ID, STORAGE, FORM_BRANCH_ID, FORM_MAKER_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL,
//  						AUDITOR_ID, AUDITOR, AUDIT_TIME, FORM_NOTE) 
//  			VALUES (:formId, :formType, :formRefId, :workshop, :storageId, :storage, :formBranchId, :formMakerId, :formMaker, :formTime, :formTimeActual,
//  					:auditorId, :auditor, :auditTime, :formNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formType;
  String __sJT_4 = formRefId;
  String __sJT_5 = workshop;
  String __sJT_6 = storageId;
  String __sJT_7 = storage;
  String __sJT_8 = formBranchId;
  String __sJT_9 = formMakerId;
  String __sJT_10 = formMaker;
  Date __sJT_11 = formTime;
  Date __sJT_12 = formTimeActual;
  String __sJT_13 = auditorId;
  String __sJT_14 = auditor;
  Date __sJT_15 = auditTime;
  String __sJT_16 = formNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:92^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(RequisitionHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String auditor_id =header.getAuditorId();
		String auditor =header.getAuditor();
		String storage = header.getStorage();
		String storageId = header.getStorageId();
		String workshop = header.getWorkshop();
		/*@lineinfo:generated-code*//*@lineinfo:109^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_REQUISITION_HEADER")
//  			SET
//  				STORAGE = :storage,
//  				STORAGE_ID = :storageId,
//  				WORKSHOP = :workshop
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = storage;
  String __sJT_3 = storageId;
  String __sJT_4 = workshop;
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:118^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int audit(RequisitionHeader header)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String formId = header.getFormId();
			Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
			Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
			String auditor_id =header.getAuditorId();
			String auditor =header.getAuditor();
			String storage = header.getStorage();
			String storageId = header.getStorageId();
			String workshop = header.getWorkshop();
			/*@lineinfo:generated-code*//*@lineinfo:136^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_REQUISITION_HEADER")
//  				SET
//  					AUDIT_TIME = :auditTime,
//  					AUDITOR_ID = :auditor_id,
//  					AUDITOR = :auditor,
//  					AUDIT_TIME_ACTUAL =:auditTimeActual
//  				WHERE 
//  					FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  Date __sJT_2 = auditTime;
  String __sJT_3 = auditor_id;
  String __sJT_4 = auditor;
  Date __sJT_5 = auditTimeActual;
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:146^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:156^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_REQUISITION_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:162^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int count(java.util.Date startDate, java.util.Date endDate, String formType, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:176^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_REQUISITION_HEADER") h
//  	        LEFT JOIN
//  	        :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//              AND h.FORM_TYPE = :formType
//  			AND (h.FORM_TIME >= :sDate or :sDate is null)
//  			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = formType;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
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

	
	public int count(String queryType ,String formRefId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(queryType);
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:202^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_REQUISITION_HEADER") h
//  	        LEFT JOIN
//  	        :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//              AND  (h.FORM_TYPE = 'return'
//                      OR  h.FORM_TYPE = 'extra' )
//              AND h.FORM_REF_ID= :formRefId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = formRefId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:217^3*/
		closeDefaultContext(myCtx);
		return total;
	}

	private String formQuery(String queryType) {
		String query = " 1=1";
		if(!TextUtil.isEmpty(queryType)){
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		
		return query;
	}
	
	public List<RequisitionHeader> query(java.util.Date startDate, java.util.Date endDate, int startRow, int endRow, String formType, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:239^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  		                		WITH
//  		                	    item AS
//  		                	    (
//  		                	        SELECT
//  		                	            *
//  		                	        FROM
//  		                	            (
//  		                	                SELECT
//  		                	                    d.FORM_ID,
//  		                	                    d.ITEM_ID,
//  		                	                    d.ITEM_NAME,
//  		                	                    row_number() over(partition BY d.FORM_ID ORDER BY d.RECEIVE_COUNT*nvl(p.ITEM_PRICE,0)
//  		                	                    DESC ) rn
//  		                	                FROM
//  		                	                :Com_("D_T1_REQUISITION_DETAIL") d
//  		                	                LEFT JOIN
//  		                	                :Com_("D_T2_ITEM_PRICE") p
//  		                	                ON
//  		                	                    p.ITEM_ID =d.ITEM_ID
//  		                	                AND p.PRICE_TYPE='BENCHMARK'
//  		                	                 )
//  		                	        WHERE
//  		                	            rn=1
//  		                	    )
//  			                    SELECT
//  			                        h.*,
//  			                        w.ITEM_NAME,
//  			                        i.ITEM_NAME mainName,
//  			                        s.STATUS AS FORM_STATUS
//  			                    FROM
//  			                    :Com_("D_T1_REQUISITION_HEADER") h
//  			                    LEFT JOIN
//  			                    :Com_("D_T1_WORK_ORDER_HEADER") w
//  			                    ON 
//  			                        w.FORM_ID = h.FORM_REF_ID
//  			                    LEFT JOIN 
//  			                    item i
//  			                    ON 
//  			                    i.FORM_ID = h.FORM_ID
//  			                    LEFT JOIN
//  			                    :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        s.FORM_ID = h.FORM_ID
//  			                    AND s.IS_CURRENT = 1
//  			                    WHERE
//  			                        :query
//  			                    AND h.FORM_TYPE = :formType
//  			                    AND (
//  			                            h.FORM_TIME >= :sDate
//  			                        OR  :sDate IS NULL)
//  			                    AND (
//  			                            h.FORM_TIME <= :eDate
//  			                        OR  :eDate IS NULL)
//  			                    ORDER BY
//  			                        h.FORM_ID DESC) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_4 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = query;
  String __sJT_7 = formType;
  Date __sJT_8 = sDate;
  Date __sJT_9 = sDate;
  Date __sJT_10 = eDate;
  Date __sJT_11 = eDate;
  int __sJT_12 = endRow;
  int __sJT_13 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 7);
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
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setInt(12, __sJT_12);
      __sJT_stmt.setInt(13, __sJT_13);
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

/*@lineinfo:user-code*//*@lineinfo:307^34*/
		List<RequisitionHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	public RequisitionHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:318^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				h.*,
//  				w.ITEM_NAME,
//  				'' mainName,
//  				'' as FORM_STATUS
//  			FROM
//  			:Com_("D_T1_REQUISITION_HEADER") h
//  			 LEFT JOIN
//  			 :Com_("D_T1_WORK_ORDER_HEADER") w
//           ON 
//               w.FORM_ID = h.FORM_REF_ID
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:332^3*/
		List<RequisitionHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<RequisitionHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<RequisitionHeader> headerLst = new ArrayList<RequisitionHeader>();
		while(headerIter.next()) {
			RequisitionHeader header = new RequisitionHeader();
			header.setFormId(headerIter.form_id());
			header.setFormType(headerIter.form_type());
			header.setFormRefId(headerIter.form_ref_id());
			header.setItemName(headerIter.item_name());
			header.setWorkshop(headerIter.workshop());
			header.setStorageId(headerIter.storage_id());
			header.setStorage(headerIter.storage());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setFormStatus(headerIter.form_status());
			header.setMainName(headerIter.mainName());
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RequisitionHeaderBean_SJProfileKeys 
{
  private static RequisitionHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequisitionHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequisitionHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.RequisitionHeaderBean_SJProfile0");
  }
}
