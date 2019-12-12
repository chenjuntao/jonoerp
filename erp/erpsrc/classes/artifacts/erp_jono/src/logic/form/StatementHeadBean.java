/*@lineinfo:filename=StatementHeadBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Mar 13 15:18:15 CST 2015 by pw
 * Last edited on Fri Mar 13 15:18:15 CST 2015 by pw
 * 
 * comment: 对账单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.StatementHead;
import com.tanry.business.form.FormConstant;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class StatementHeadBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StatementHeadBean.class);
	
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
    statusNdx = findColumn("status");
    form_idNdx = findColumn("form_id");
    form_typeNdx = findColumn("form_type");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    all_pay_amtNdx = findColumn("all_pay_amt");
    start_dateNdx = findColumn("start_date");
    end_dateNdx = findColumn("end_date");
    form_noteNdx = findColumn("form_note");
  }
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
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
  public String provider_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_idNdx);
  }
  private int provider_idNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
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
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public Date start_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(start_dateNdx);
  }
  private int start_dateNdx;
  public Date end_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(end_dateNdx);
  }
  private int end_dateNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^357*/
	
	public int saveEntity(StatementHead header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String formType = header.getFormType();
		String providerId = header.getProviderId();
		String provider = header.getProvider();
		String branchId = header.getBranchId();
		String branchName = header.getBranchName();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		Double allPayAmt = header.getAllPayAmt();
		Date startDate = SqlDateUtil.getSqlDate(header.getStartDate());
		Date endDate = SqlDateUtil.getSqlDate(header.getEndDate());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:60^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_STATEMENT_HEADER") 
//  				(FORM_ID, FORM_TYPE, PROVIDER_ID, PROVIDER, BRANCH_ID, BRANCH_NAME, FORM_MAKER_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL, AUDITOR_ID, AUDITOR, AUDIT_TIME, ALL_PAY_AMT, START_DATE, END_DATE, FORM_NOTE) 
//  			VALUES (:formId, :formType, :providerId, :provider, :branchId, :branchName, :formMakerId, :formMaker, :formTime, :formTimeActual, :auditorId, :auditor, :auditTime, :allPayAmt, :startDate, :endDate, :formNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formType;
  String __sJT_4 = providerId;
  String __sJT_5 = provider;
  String __sJT_6 = branchId;
  String __sJT_7 = branchName;
  String __sJT_8 = formMakerId;
  String __sJT_9 = formMaker;
  Date __sJT_10 = formTime;
  Date __sJT_11 = formTimeActual;
  String __sJT_12 = auditorId;
  String __sJT_13 = auditor;
  Date __sJT_14 = auditTime;
  Double __sJT_15 = allPayAmt;
  Date __sJT_16 = startDate;
  Date __sJT_17 = endDate;
  String __sJT_18 = formNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementHeadBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setDate(14, __sJT_14);
      __sJT_stmt.setDoubleWrapper(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:64^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(StatementHead header)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String formId = header.getFormId();
			Double allPayAmt = header.getAllPayAmt();

			/*@lineinfo:generated-code*//*@lineinfo:77^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_STATEMENT_HEADER")
//  				SET
//  					ALL_PAY_AMT = :allPayAmt
//  				WHERE 
//  					FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_HEADER");
  Double __sJT_2 = allPayAmt;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementHeadBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:84^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int newSerial(java.util.Date businessDate,String formType)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String maxId = null;
			Date bDate = SqlDateUtil.getSqlDate(businessDate);
			/*@lineinfo:generated-code*//*@lineinfo:96^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    MAX(h.FORM_ID) 
//  				FROM
//  				:Com_("D_T1_STATEMENT_HEADER") h
//  				WHERE
//  				    h.FORM_TIME = :bDate
//  				AND h.FORM_TYPE = :formType
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_HEADER");
  Date __sJT_2 = bDate;
  String __sJT_3 = formType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementHeadBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:104^4*/
			closeDefaultContext(myCtx);

			int result = 1;
			if (maxId != null) {
				maxId = maxId.substring(maxId.length() - FormConstant.DEFAULT_SERIAL_LENGTH);
				result = Integer.parseInt(maxId) + 1;
			}
			return result;
		}
	

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:120^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_STATEMENT_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementHeadBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:126^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	

	public int count(java.util.Date startDate, java.util.Date endDate,String supplierId,String branchId, String formIdText, String status,String formType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(supplierId, branchId,formIdText,status, formType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:141^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_STATEMENT_HEADER") h
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
  String __sJT_1 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementHeadBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:155^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String supplierId,String branchId,String formIdText,String status,String formType) {
		String query = " 1=1";
		
		if (!TextUtil.isEmpty(supplierId)) {
			query = query + " AND h.PROVIDER_ID = '" + supplierId + "'";
		}
		
		if (!TextUtil.isEmpty(formIdText)) {
			query = query + " AND upper(h.FORM_ID) like '%" + formIdText + "%'";
		}
		
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND h.BRANCH_ID = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(formType)) {
			query = query + " AND h.FORM_TYPE = '" + formType + "'";
		}
		
		if (!TextUtil.isEmpty(status)) {
			query = query + " AND s.STATUS =  '" + status + "'";
		}
		
		return query;
	}
	
	public List<StatementHead> query(java.util.Date startDate, java.util.Date endDate,String supplierId,String branchId, String formIdText, String status, String formType,int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(supplierId, branchId,formIdText,status, formType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:194^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,s.status
//  			FROM
//  			:Com_("D_T1_STATEMENT_HEADER") h
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
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementHeadBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:208^3*/
		List<StatementHead> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public StatementHead queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:219^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,s.status
//  			FROM
//  			:Com_("D_T1_STATEMENT_HEADER") h 
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			WHERE
//  				h.form_id = :formId 
//  			AND s.IS_CURRENT = 1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementHeadBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:231^3*/
		List<StatementHead> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<StatementHead> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<StatementHead> headerLst = new ArrayList<StatementHead>();
		while(headerIter.next()) {
			StatementHead header = new StatementHead();
			header.setFormId(headerIter.form_id());
			header.setFormType(headerIter.form_type());
			header.setProviderId(headerIter.provider_id());
			header.setProvider(headerIter.provider());
			header.setBranchId(headerIter.branch_id());
			header.setBranchName(headerIter.branch_name());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setStartDate(SqlDateUtil.getUtilDate(headerIter.start_date()));
			header.setEndDate(SqlDateUtil.getUtilDate(headerIter.end_date()));
			header.setFormNote(headerIter.form_note());
			header.setStatus(headerIter.status());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class StatementHeadBean_SJProfileKeys 
{
  private static StatementHeadBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StatementHeadBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StatementHeadBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.StatementHeadBean_SJProfile0");
  }
}
