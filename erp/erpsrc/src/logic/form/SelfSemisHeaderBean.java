/*@lineinfo:filename=SelfSemisHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Jul 08 12:06:04 CST 2016 by czc
 * Last edited on Fri Jul 08 12:06:04 CST 2016 by czc
 * 
 * comment: 半成品加工单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.SelfSemisHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class SelfSemisHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SelfSemisHeaderBean.class);
	
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
    form_idNdx = findColumn("form_id");
    branch_idNdx = findColumn("branch_id");
    branchNdx = findColumn("branch");
    creator_man_idNdx = findColumn("creator_man_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    main_itemNdx = findColumn("main_item");
    statusNdx = findColumn("status");
    audit_time_actualNdx = findColumn("audit_time_actual");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchNdx);
  }
  private int branchNdx;
  public String creator_man_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(creator_man_idNdx);
  }
  private int creator_man_idNdx;
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
  public String main_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(main_itemNdx);
  }
  private int main_itemNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
  public Date audit_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_time_actualNdx);
  }
  private int audit_time_actualNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^288*/
	
	public int saveEntity(SelfSemisHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String branchId = header.getBranchId();
		String branch = header.getBranch();
		String creatorManId = header.getCreatorManId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();
		String mainItem = header.getMainItem();

		/*@lineinfo:generated-code*//*@lineinfo:54^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_SELF_SEMIS_HEADER") 
//  				(FORM_ID, BRANCH_ID, BRANCH, CREATOR_MAN_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL, AUDITOR_ID, AUDITOR, AUDIT_TIME, FORM_NOTE, MAIN_ITEM) 
//  			VALUES (:formId, :branchId, :branch, :creatorManId, :formMaker, :formTime, :formTimeActual, :auditorId, :auditor, :auditTime, :formNote, :mainItem)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = branchId;
  String __sJT_4 = branch;
  String __sJT_5 = creatorManId;
  String __sJT_6 = formMaker;
  Date __sJT_7 = formTime;
  Date __sJT_8 = formTimeActual;
  String __sJT_9 = auditorId;
  String __sJT_10 = auditor;
  Date __sJT_11 = auditTime;
  String __sJT_12 = formNote;
  String __sJT_13 = mainItem;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisHeaderBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setDate(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:58^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(SelfSemisHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String branchId = header.getBranchId();
		String branch = header.getBranch();
		String creatorManId = header.getCreatorManId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(header.getFormTimeActual());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();
		String mainItem = header.getMainItem();

		/*@lineinfo:generated-code*//*@lineinfo:81^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SELF_SEMIS_HEADER")
//  			SET
//  				FORM_NOTE = :formNote,
//  				MAIN_ITEM = :mainItem
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = formNote;
  String __sJT_3 = mainItem;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:89^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:99^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_SELF_SEMIS_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:105^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 餐厅半成品入库审核
	 */
	public int audit(String formId, String autidorId, String autidor, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		/*@lineinfo:generated-code*//*@lineinfo:120^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SELF_SEMIS_HEADER")
//  			SET
//  				AUDITOR_ID = :autidorId,
//  				AUDITOR = :autidor,
//  				AUDIT_TIME = :sAuditTime,
//  				AUDIT_TIME_ACTUAL = :auditTimeActual
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = autidorId;
  String __sJT_3 = autidor;
  Date __sJT_4 = sAuditTime;
  Date __sJT_5 = auditTimeActual;
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisHeaderBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:130^3*/
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
		/*@lineinfo:generated-code*//*@lineinfo:144^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_SELF_SEMIS_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
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
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:158^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String queryType) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.BRANCH_ID = '" + branchId + "'";
		}
		if ("unaudit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		} else if ("audit".equals(queryType)) { // 获取已审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '已审核'";
		}
		return query;
	}
	
	public List<SelfSemisHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:184^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  	    *
//  	    FROM
//  	        (
//  	            SELECT
//  	                t.*,
//  	                DECODE(FORM_ID,NULL,'合计',to_char(ROWNUM)) rownumber,
//  	                ROWNUM                           rowIndex
//  	            FROM
//  	                (
//  			SELECT
//  				h.FORM_ID,
//  	            h.BRANCH_ID,
//  	            h.BRANCH,
//  	            h.CREATOR_MAN_ID,
//  	            h.FORM_MAKER,
//  	            h.FORM_TIME,
//  	            h.FORM_TIME_ACTUAL,
//  	            h.AUDITOR_ID,
//  	            h.AUDITOR,
//  	            h.AUDIT_TIME,
//  	            h.FORM_NOTE,
//  	            h.MAIN_ITEM,
//  	            h.AUDIT_TIME_ACTUAL,
//  	            s.STATUS
//  			FROM
//  			:Com_("D_T1_SELF_SEMIS_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//  			AND (h.FORM_TIME >= :sDate or :sDate is null)
//  			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  		  ORDER BY
//               NVL(h.FORM_ID,' ') DESC ) t
//           WHERE
//               ROWNUM < :endRow)
//  		 WHERE
//  		 	rowIndex >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisHeaderBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:226^26*/
		List<SelfSemisHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public SelfSemisHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:237^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				h.FORM_ID,
//  	            h.BRANCH_ID,
//  	            h.BRANCH,
//  	            h.CREATOR_MAN_ID,
//  	            h.FORM_MAKER,
//  	            h.FORM_TIME,
//  	            h.FORM_TIME_ACTUAL,
//  	            h.AUDITOR_ID,
//  	            h.AUDITOR,
//  	            h.AUDIT_TIME,
//  	            h.FORM_NOTE,
//  	            h.MAIN_ITEM,
//  	            h.AUDIT_TIME_ACTUAL,
//  	            s.STATUS
//          	FROM
//  			:Com_("D_T1_SELF_SEMIS_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:262^3*/
		List<SelfSemisHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<SelfSemisHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<SelfSemisHeader> headerLst = new ArrayList<SelfSemisHeader>();
		while(headerIter.next()) {
			SelfSemisHeader header = new SelfSemisHeader();
			header.setFormId(headerIter.form_id());
			header.setBranchId(headerIter.branch_id());
			header.setBranch(headerIter.branch());
			header.setCreatorManId(headerIter.creator_man_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			header.setFormNote(headerIter.form_note());
			header.setMainItem(headerIter.main_item());
			header.setFormStatus(headerIter.status());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class SelfSemisHeaderBean_SJProfileKeys 
{
  private static SelfSemisHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SelfSemisHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SelfSemisHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.SelfSemisHeaderBean_SJProfile0");
  }
}
