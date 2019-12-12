/*@lineinfo:filename=CheckHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sun Sep 28 11:08:09 CST 2014 by lyz
 * Last edited on Sun Sep 28 11:08:09 CST 2014 by lyz
 * 
 * comment: 盘点单/清单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.CheckHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import org.apache.commons.lang.StringUtils;
import com.tanry.framework.acl.NoPrivilegeException;

public class CheckHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(CheckHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

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
    check_storage_idNdx = findColumn("check_storage_id");
    check_storageNdx = findColumn("check_storage");
    form_typeNdx = findColumn("form_type");
    audit_time_actualNdx = findColumn("audit_time_actual");
    check_batch_idNdx = findColumn("check_batch_id");
    check_branch_idNdx = findColumn("check_branch_id");
    check_branchNdx = findColumn("check_branch");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    print_countNdx = findColumn("print_count");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String check_storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_storage_idNdx);
  }
  private int check_storage_idNdx;
  public String check_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_storageNdx);
  }
  private int check_storageNdx;
  public String form_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_typeNdx);
  }
  private int form_typeNdx;
  public Date audit_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_time_actualNdx);
  }
  private int audit_time_actualNdx;
  public String check_batch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_batch_idNdx);
  }
  private int check_batch_idNdx;
  public String check_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_branch_idNdx);
  }
  private int check_branch_idNdx;
  public String check_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_branchNdx);
  }
  private int check_branchNdx;
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
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public Integer print_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(print_countNdx);
  }
  private int print_countNdx;
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public String max_pay_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(max_pay_itemNdx);
  }
  private int max_pay_itemNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^414*/

	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

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
  }
  public String check_batch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_batch_idNdx);
  }
  private int check_batch_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^55*/
	/*@lineinfo:generated-code*//*@lineinfo:41^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class CheckListIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CheckListIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    check_batch_idNdx = findColumn("check_batch_id");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String check_batch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_batch_idNdx);
  }
  private int check_batch_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^74*/
	
	/**
	 * 根据营业日期和部门得到最大的单据序号，用来生成下一个单据流水号
	 */
	public int newSerial(java.util.Date businessDate, String branchId, String formType) 
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String maxId = null;
		int serialLen = FormConstant.DEFAULT_SERIAL_LENGTH;
		//负数(-i)表示截取的开始位置为字符串右端向左数第i个字符
		int negativePos = 0 - serialLen;
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		/*@lineinfo:generated-code*//*@lineinfo:55^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				MAX(SUBSTR(h.FORM_ID, :negativePos, :serialLen) ) 
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER") h
//  			WHERE
//  			    h.FORM_TIME = :bDate
//  			AND h.CHECK_BRANCH_ID = :branchId
//  			AND h.FORM_TYPE = :formType
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
  String __sJT_3 = Com_("D_T1_CHECK_HEADER");
  Date __sJT_4 = bDate;
  String __sJT_5 = branchId;
  String __sJT_6 = formType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setInt(1, __sJT_1);
      __sJT_stmt.setInt(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:64^3*/
		closeDefaultContext(myCtx);
		
		int result = 1;
		if (maxId != null) {
			maxId = maxId.substring(maxId.length() - serialLen);
			result = Integer.parseInt(maxId) + 1;
		}
		return result;
	}
	
	public int saveEntity(CheckHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String formType = header.getFormType();
		String checkBatchId = header.getCheckBatchId();
		String checkBranchId = header.getCheckBranchId();
		String checkBranch = header.getCheckBranch();
		String checkStorageId = header.getCheckStorageId();
		String checkStorage = header.getCheckStorage();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();
		Integer printCount = header.getPrintCount();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:98^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_CHECK_HEADER") 
//  				(FORM_ID, FORM_TYPE, CHECK_BATCH_ID, CHECK_BRANCH_ID, CHECK_BRANCH, CHECK_STORAGE_ID, CHECK_STORAGE,
//  						FORM_MAKER_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL, AUDITOR_ID, AUDITOR, AUDIT_TIME, FORM_NOTE, PRINT_COUNT, ALL_PAY_AMT, MAX_PAY_ITEM) 
//  			VALUES (:formId, :formType, :checkBatchId, :checkBranchId, :checkBranch, :checkStorageId, :checkStorage,
//  					:formMakerId, :formMaker, :formTime, :formTimeActual, :auditorId, :auditor, :auditTime, :formNote, :printCount, :allPayAmt, :maxPayItem)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formType;
  String __sJT_4 = checkBatchId;
  String __sJT_5 = checkBranchId;
  String __sJT_6 = checkBranch;
  String __sJT_7 = checkStorageId;
  String __sJT_8 = checkStorage;
  String __sJT_9 = formMakerId;
  String __sJT_10 = formMaker;
  Date __sJT_11 = formTime;
  Date __sJT_12 = formTimeActual;
  String __sJT_13 = auditorId;
  String __sJT_14 = auditor;
  Date __sJT_15 = auditTime;
  String __sJT_16 = formNote;
  Integer __sJT_17 = printCount;
  Double __sJT_18 = allPayAmt;
  String __sJT_19 = maxPayItem;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setIntWrapper(17, __sJT_17);
      __sJT_stmt.setDoubleWrapper(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:104^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(CheckHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_CHECK_HEADER")
//  			SET
//  				ALL_PAY_AMT = :allPayAmt,
//  				MAX_PAY_ITEM = :maxPayItem
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  Double __sJT_2 = allPayAmt;
  String __sJT_3 = maxPayItem;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:126^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:136^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 3);
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
	
	/**
	 * 根据批次删除相应的盘点清单和盘点汇总单
	 */
	public int deleteByBatch(String batchId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:155^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER")
//  			WHERE
//  				CHECK_BATCH_ID = :batchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = batchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:161^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int audit(CheckHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		/*@lineinfo:generated-code*//*@lineinfo:176^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_CHECK_HEADER")
//  			SET
//  				auditor_id = :auditorId,
//  				auditor = :auditor,
//  				audit_time = :auditTime,
//  				AUDIT_TIME_ACTUAL =:auditTimeActual
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = auditorId;
  String __sJT_3 = auditor;
  Date __sJT_4 = auditTime;
  Date __sJT_5 = auditTimeActual;
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:186^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String formType, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String fromClause = getFromClause();
		String query = formQuery(branchId, formType, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:201^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//      		FROM :fromClause
//  			WHERE
//  				:query
//      		AND h.FORM_TYPE = :formType
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
  String __sJT_1 = fromClause;
  String __sJT_2 = query;
  String __sJT_3 = formType;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:210^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String formType, String queryType) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.CHECK_BRANCH_ID = '" + branchId + "'";
		}
		if ((FormConstant.CHECK_LIST).equals(formType)) { // 只获取未完成的清单
//			query = query + " AND NVL(l.CHECK_BATCH_STATUS, 'null') != '已完成'";
		}
		if ("unaudit".equals(queryType)) {
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		return query;
	}
	
	private String getFromClause() {
		String com = getCompanyId();
		String fromClause = " " + com +".D_T1_CHECK_HEADER h "
				+ "LEFT JOIN "+com+".D_T0_FORM_STATUS s ON s.FORM_ID = h.FORM_ID AND s.IS_CURRENT = 1 "
				+ "LEFT JOIN "+com+".D_T1_CHECK_LOCK l ON l.CHECK_BATCH_ID = h.CHECK_BATCH_ID";
		return fromClause;
	}
	
	public List<CheckHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String formType, String queryType, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String fromClause = getFromClause();
		String query = formQuery(branchId, formType, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:246^3*/

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
//  			                    SELECT
//  				                    h.FORM_ID,
//  				                    h.FORM_TYPE,
//  				                    h.CHECK_BATCH_ID,
//  				                    h.CHECK_BRANCH_ID,
//  				                    h.CHECK_BRANCH,
//  				                    H.CHECK_STORAGE_ID,
//  				                    H.CHECK_STORAGE,
//  				                    h.FORM_MAKER_ID,
//  				                    h.FORM_MAKER,
//  				                    h.FORM_TIME,
//  				                    h.FORM_TIME_ACTUAL,
//  				                    h.AUDITOR_ID,
//  				                    h.AUDITOR,
//  				                    h.AUDIT_TIME,
//  				                    h.FORM_NOTE,
//  				                    h.PRINT_COUNT,
//  				                    h.AUDIT_TIME_ACTUAL,
//  				                    CASE
//  				                        WHEN h.ALL_PAY_AMT IS NULL
//  				                        THEN 0
//  				                        ELSE h.ALL_PAY_AMT
//  				                    END ALL_PAY_AMT ,
//  				                    h.MAX_PAY_ITEM
//  				    			FROM :fromClause
//  			                    WHERE
//  			                        :query
//  			        			AND h.FORM_TYPE = :formType
//  	        					AND (h.FORM_TIME >= :sDate or :sDate is null)
//  	        					AND (h.FORM_TIME <= :eDate or :eDate is null)
//  			                    ORDER BY
//  			                    	h.FORM_TIME_ACTUAL desc) t
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
  String __sJT_1 = fromClause;
  String __sJT_2 = query;
  String __sJT_3 = formType;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:290^34*/
		List<CheckHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public double sum(java.util.Date startDate, java.util.Date endDate, String branchId, String formType, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, formType, queryType);
		String fromClause = getFromClause();
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		double allPayAmt = 0;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:306^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    CASE
//  				        WHEN SUM(h.ALL_PAY_AMT) IS NULL
//  				        THEN 0
//  				        ELSE SUM(h.ALL_PAY_AMT)
//  				    END
//  			    
//  			    FROM :fromClause
//  				WHERE 
//  					:query
//  				AND h.FORM_TYPE = :formType
//  				AND (h.FORM_TIME >= :sDate or :sDate is null)
//  				AND (h.FORM_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = fromClause;
  String __sJT_2 = query;
  String __sJT_3 = formType;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 8);
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
    allPayAmt = __sJT_rtRs.getDoubleNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:321^3*/
		closeDefaultContext(myCtx);
		return allPayAmt;
	}
	
	public List<String> queryBatch(String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String formType = FormConstant.CHECK_LIST;
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.CHECK_BRANCH_ID = '" + branchId + "'";
		}
		DefaultContext myCtx = getDefaultContext();
		BatchIter batchIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:335^3*/

//  ************************************************************
//  #sql [myCtx] batchIter = { SELECT
//  			    distinct h.CHECK_BATCH_ID
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER") h,
//  				:Com_("D_T1_CHECK_LOCK") l
//  			WHERE
//  				:query
//  			AND h.FORM_TYPE = :formType
//  			AND h.CHECK_BATCH_ID = l.CHECK_BATCH_ID
//  			AND NVL(l.CHECK_BATCH_STATUS, 'null') != '已完成'
//  			ORDER BY
//  				CHECK_BATCH_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = Com_("D_T1_CHECK_LOCK");
  String __sJT_3 = query;
  String __sJT_4 = formType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:348^3*/
		List<String> batchLst = new ArrayList<String>();
		while(batchIter.next()) {
			batchLst.add(batchIter.check_batch_id());
		}
		batchIter.close();
		closeDefaultContext(myCtx);
		return batchLst;
	}
	
	public List<CheckHeader> queryCheckList(String batchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String formType = FormConstant.CHECK_LIST;
		DefaultContext myCtx = getDefaultContext();
		CheckListIter checkIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:363^3*/

//  ************************************************************
//  #sql [myCtx] checkIter = { SELECT
//  			    h.FORM_ID,
//  			    h.CHECK_BATCH_ID
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER") h
//  			WHERE
//  				h.CHECK_BATCH_ID = :batchId
//  			AND h.FORM_TYPE = :formType
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = batchId;
  String __sJT_3 = formType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      checkIter = new CheckListIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:372^3*/
		List<CheckHeader> checkLst = new ArrayList<CheckHeader>();
		while(checkIter.next()) {
			CheckHeader header = new CheckHeader();
			header.setFormId(checkIter.form_id());
			header.setCheckBatchId(checkIter.check_batch_id());
			checkLst.add(header);
		}
		checkIter.close();
		closeDefaultContext(myCtx);
		return checkLst;
	}
	
	
	public List<CheckHeader> queryCheckLists(String batchIds) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String formType = FormConstant.CHECK_LIST;
		String query = " h.CHECK_BATCH_ID IN ('" + batchIds.replaceAll(",", "','") + "') ";
		DefaultContext myCtx = getDefaultContext();
		CheckListIter checkIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:392^3*/

//  ************************************************************
//  #sql [myCtx] checkIter = { SELECT
//  			    h.FORM_ID,
//  			    h.CHECK_BATCH_ID
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER") h
//  			WHERE
//  				:query
//  			AND h.FORM_TYPE = :formType
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = query;
  String __sJT_3 = formType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      checkIter = new CheckListIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:401^3*/
		List<CheckHeader> checkLst = new ArrayList<CheckHeader>();
		while(checkIter.next()) {
			CheckHeader header = new CheckHeader();
			header.setFormId(checkIter.form_id());
			header.setCheckBatchId(checkIter.check_batch_id());
			checkLst.add(header);
		}
		checkIter.close();
		closeDefaultContext(myCtx);
		return checkLst;
	}
	
	/**
	 * 根据批次查询盘点清单
	 */
	public List<CheckHeader> query(String batchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String formType = FormConstant.CHECK_LIST;
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:422^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER") h
//  			LEFT JOIN
//  				:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				h.CHECK_BATCH_ID = :batchId
//  			AND h.FORM_TYPE = :formType
//  --			AND NVL( s.STATUS, 'null') = '已输入'
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = batchId;
  String __sJT_4 = formType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:436^3*/
		List<CheckHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public CheckHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:447^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER") h
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 13);
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

/*@lineinfo:user-code*//*@lineinfo:454^3*/
		List<CheckHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}

	public CheckHeader queryForm(String batchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String formType = FormConstant.CHECK_FORM;
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:469^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  				:Com_("D_T1_CHECK_HEADER") h
//  			WHERE
//  				h.CHECK_BATCH_ID = :batchId
//  			AND h.FORM_TYPE = :formType
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = batchId;
  String __sJT_3 = formType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckHeaderBean_SJProfileKeys.getKey(0), 14);
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

/*@lineinfo:user-code*//*@lineinfo:477^3*/
		List<CheckHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<CheckHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<CheckHeader> headerLst = new ArrayList<CheckHeader>();
		while(headerIter.next()) {
			CheckHeader header = new CheckHeader();
			header.setFormId(headerIter.form_id());
			header.setFormType(headerIter.form_type());
			header.setCheckBatchId(headerIter.check_batch_id());
			header.setCheckBranchId(headerIter.check_branch_id());
			header.setCheckBranch(headerIter.check_branch());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setPrintCount(headerIter.print_count());
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setCheckStorageId(headerIter.check_storage_id());
			header.setCheckStorage(headerIter.check_storage());
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class CheckHeaderBean_SJProfileKeys 
{
  private static CheckHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CheckHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CheckHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.CheckHeaderBean_SJProfile0");
  }
}
