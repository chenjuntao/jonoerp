/*@lineinfo:filename=LossHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 17, 2014 by cjt
 * Last edited on Sep 17, 2014 by cjt
 * 
 * 说明： 餐厅报损单表明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.LossHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class LossHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(LossHeaderBean.class);
	
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
    loss_typeNdx = findColumn("loss_type");
    loss_branch_idNdx = findColumn("loss_branch_id");
    loss_branchNdx = findColumn("loss_branch");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    loss_man_idNdx = findColumn("loss_man_id");
    loss_manNdx = findColumn("loss_man");
    loss_timeNdx = findColumn("loss_time");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    processor_idNdx = findColumn("processor_id");
    processorNdx = findColumn("processor");
    process_timeNdx = findColumn("process_time");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    formStatusNdx = findColumn("formStatus");
    audit_time_actualNdx = findColumn("audit_time_actual");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String loss_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(loss_typeNdx);
  }
  private int loss_typeNdx;
  public String loss_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(loss_branch_idNdx);
  }
  private int loss_branch_idNdx;
  public String loss_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(loss_branchNdx);
  }
  private int loss_branchNdx;
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
  public String loss_man_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(loss_man_idNdx);
  }
  private int loss_man_idNdx;
  public String loss_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(loss_manNdx);
  }
  private int loss_manNdx;
  public Date loss_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(loss_timeNdx);
  }
  private int loss_timeNdx;
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
  public String processor_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(processor_idNdx);
  }
  private int processor_idNdx;
  public String processor() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(processorNdx);
  }
  private int processorNdx;
  public Date process_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(process_timeNdx);
  }
  private int process_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
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
  public String formStatus() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(formStatusNdx);
  }
  private int formStatusNdx;
  public Date audit_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_time_actualNdx);
  }
  private int audit_time_actualNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:56^4*/
	
	public int saveEntity(LossHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String lossType = header.getLossType();
		String lossBranchId = header.getLossBranchId();
		String lossBranch = header.getLossBranch();
		String storageId = header.getStorageId();
		String storage = header.getStorage();
		String lossManId = header.getLossManId();
		String lossMan = header.getLossMan();
		Date lossTime = SqlDateUtil.getSqlDate(header.getLossTime());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String processorId = header.getProcessorId();
		String processor = header.getProcessor();
		Date processTime = SqlDateUtil.getSqlDate(header.getProcessTime());
		String formNote = header.getFormNote();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:81^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_LOSS_HEADER")
//  				(FORM_ID, LOSS_TYPE, LOSS_BRANCH_ID, LOSS_BRANCH, STORAGE_ID, STORAGE, LOSS_MAN_ID, LOSS_MAN, LOSS_TIME, AUDITOR_ID, AUDITOR, AUDIT_TIME, PROCESSOR_ID, PROCESSOR, PROCESS_TIME, FORM_NOTE, ALL_PAY_AMT, MAX_PAY_ITEM) 
//  			VALUES (:formId, :lossType, :lossBranchId, :lossBranch, :storageId, :storage, :lossManId, :lossMan, :lossTime, :auditorId, :auditor, :auditTime, :processorId, :processor, :processTime, :formNote, :allPayAmt, :maxPayItem)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = lossType;
  String __sJT_4 = lossBranchId;
  String __sJT_5 = lossBranch;
  String __sJT_6 = storageId;
  String __sJT_7 = storage;
  String __sJT_8 = lossManId;
  String __sJT_9 = lossMan;
  Date __sJT_10 = lossTime;
  String __sJT_11 = auditorId;
  String __sJT_12 = auditor;
  Date __sJT_13 = auditTime;
  String __sJT_14 = processorId;
  String __sJT_15 = processor;
  Date __sJT_16 = processTime;
  String __sJT_17 = formNote;
  Double __sJT_18 = allPayAmt;
  String __sJT_19 = maxPayItem;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
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

/*@lineinfo:user-code*//*@lineinfo:85^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(LossHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:99^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_LOSS_HEADER")
//  			SET
//  			    ALL_PAY_AMT = :allPayAmt,
//  			    MAX_PAY_ITEM = :maxPayItem
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  Double __sJT_2 = allPayAmt;
  String __sJT_3 = maxPayItem;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:107^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:117^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_LOSS_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:123^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 餐厅要货单审核
	 */
	public int audit(String formId, String autidorId, String autidor, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:138^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_LOSS_HEADER")
//  			SET
//  				AUDITOR_ID = :autidorId,
//  				AUDITOR = :autidor,
//  				AUDIT_TIME = :sAuditTime,
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
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = autidorId;
  String __sJT_3 = autidor;
  Date __sJT_4 = sAuditTime;
  Date __sJT_5 = auditTimeActual;
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:148^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 餐厅要货单处理
	 */
	public int process(String formId, String processorId, String processor, java.util.Date processTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sProcessTime = SqlDateUtil.getSqlDate(processTime);
		/*@lineinfo:generated-code*//*@lineinfo:162^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_LOSS_HEADER")
//  			SET
//  				PROCESSOR_ID = :processorId,
//  				PROCESSOR = :processor,
//  				PROCESS_TIME = :sProcessTime
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = processorId;
  String __sJT_3 = processor;
  Date __sJT_4 = sProcessTime;
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:171^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	private String formQuery(String branchId,String storageId, String lossType, String status)
	{
		String query = " 1=1";
		
		//门店ID
		if (!TextUtil.isEmpty(branchId)) {
			query += " AND h.LOSS_BRANCH_ID = '" + branchId + "'";
		}
		
		//仓库ID
		if (!TextUtil.isEmpty(storageId)) {
			query += " AND h.STORAGE_ID = '" + storageId + "'";
		}
		
		if (!TextUtil.isEmpty(lossType)) {
			query += " AND h.LOSS_TYPE = '" + lossType + "'";
		}
		
		if (!TextUtil.isEmpty(status)) {
			if ("ap".equals(status)) {//audit&process 
				query += " AND ( NVL( s.STATUS, 'null') = '未审核' "
						+ "OR NVL( s.STATUS, 'null') = '未处理')";
			} else {
				query += " AND NVL( s.STATUS, 'null') = '" + status + "'";
			}
		}
		return query;
	}

	public int countAll(java.util.Date startDate, java.util.Date endDate, 
			String branchId, String storageId,String lossType,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId, lossType, "");
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:216^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  	    FROM
//  	    :Com_("D_T1_LOSS_HEADER") h
//  	    LEFT JOIN
//  	    :Com_("D_T2_BRANCH") b
//  	    ON
//  	        h.LOSS_BRANCH_ID = b.BRANCH_ID
//  		WHERE
//  		    :query
//  		AND b.BRANCH_TYPE = :branchType
//  		AND (
//  		        h.LOSS_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.LOSS_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = query;
  String __sJT_4 = branchType;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:235^26*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	/**
	 * 合计
	 */
	public double sum(java.util.Date startDate, java.util.Date endDate, 
			String branchId,String storageId,String lossType,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		String query = formQuery(branchId,storageId, lossType, "");
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		double allPayAmt = 0;
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:254^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    SUM(h.ALL_PAY_AMT)
//  		
//  	    FROM
//  	    :Com_("D_T1_LOSS_HEADER") h
//  	    LEFT JOIN
//  	    :Com_("D_T2_BRANCH") b
//  	    ON
//  	        h.LOSS_BRANCH_ID = b.BRANCH_ID
//  		WHERE
//  		    :query
//  		AND b.BRANCH_TYPE = :branchType
//  		AND (
//  		        h.LOSS_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.LOSS_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = query;
  String __sJT_4 = branchType;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:273^26*/
		
		closeDefaultContext(myCtx);
		return allPayAmt;
	}
	
	/**
	 * branchId 门店ID
	 * storageId 仓库ID
	 * branchType 门店类型
	 */
	public List<LossHeader> queryAll(java.util.Date startDate, java.util.Date endDate, 
			String branchId,String storageId, String lossType,String branchType,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId,lossType, "");
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:293^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (SELECT
//  			                	    h.*,
//  			                	    s.STATUS formStatus
//  			                	FROM
//  			                	:Com_("D_T1_LOSS_HEADER") h
//  			                	INNER JOIN
//  			                	:Com_("D_T0_FORM_STATUS") s
//  			                	ON
//  			                	    h.FORM_ID = s.FORM_ID
//  			                	AND s.IS_CURRENT = 1
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.LOSS_BRANCH_ID = b.BRANCH_ID
//  			                	WHERE
//  			                	    :query
//  			                	AND b.BRANCH_TYPE = :branchType
//  			                	AND (
//  			                	        h.LOSS_TIME >= :sDate
//  			                	    OR  :sDate IS NULL)
//  			                	AND (
//  			                	        h.LOSS_TIME <= :eDate
//  			                	    OR  :eDate IS NULL)
//  			                	ORDER BY
//  			                	    h.FORM_ID DESC) t
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
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = query;
  String __sJT_5 = branchType;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setInt(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:330^34*/
		List<LossHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	/**
	 * 根据状态获取报损单总数
	 */
	public int countByStatus(java.util.Date startDate, java.util.Date endDate, 
			String branchId,String storageId, String lossType, String status,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId, lossType, status);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:349^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_LOSS_HEADER") h
//  		LEFT JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		LEFT JOIN
//  		:Com_("d_t2_branch") b
//  		ON
//  		    h.LOSS_BRANCH_ID = b.BRANCH_ID
//  		WHERE
//  		    :query
//  		AND s.IS_CURRENT = 1
//  	    AND b.BRANCH_TYPE = :branchType
//  		AND (
//  		        h.LOSS_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.LOSS_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("d_t2_branch");
  String __sJT_4 = query;
  String __sJT_5 = branchType;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:373^26*/
		closeDefaultContext(myCtx);
		return total;
	}

	/**
	 * 根据状态获取报损单列表
	 */
	public List<LossHeader> queryByStatus(java.util.Date startDate, java.util.Date endDate, 
			String branchId,String storageId, String lossType, String status,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId, lossType, status);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:390^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			   h.*,s.STATUS formStatus
//  			FROM
//  			:Com_("D_T1_LOSS_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			:Com_("d_t2_branch") b
//  			ON
//  			    h.LOSS_BRANCH_ID = b.BRANCH_ID
//  			WHERE
//  			    :query
//  			AND s.IS_CURRENT = 1
//  		    AND b.BRANCH_TYPE = :branchType
//  			AND (
//  			        h.LOSS_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.LOSS_TIME <= :eDate
//  			    OR  :eDate IS NULL) order by h.form_id desc };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("d_t2_branch");
  String __sJT_4 = query;
  String __sJT_5 = branchType;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:412^51*/
		List<LossHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	public LossHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:423^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    s.STATUS formStatus
//  			FROM
//  			    :Com_("D_T1_LOSS_HEADER") h
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    h.FORM_ID = :formId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 10);
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

/*@lineinfo:user-code*//*@lineinfo:435^27*/
		List<LossHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<LossHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<LossHeader> headerLst = new ArrayList<LossHeader>();
		while(headerIter.next()) {
			LossHeader header = new LossHeader();
			header.setFormId(headerIter.form_id());
			header.setLossType(headerIter.loss_type());
			header.setLossBranchId(headerIter.loss_branch_id());
			header.setLossBranch(headerIter.loss_branch());
			header.setStorageId(headerIter.storage_id());
			header.setStorage(headerIter.storage());
			header.setLossManId(headerIter.loss_man_id());
			header.setLossMan(headerIter.loss_man());
			header.setLossTime(SqlDateUtil.getUtilDate(headerIter.loss_time()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setProcessorId(headerIter.processor_id());
			header.setProcessor(headerIter.processor());
			header.setProcessTime(SqlDateUtil.getUtilDate(headerIter.process_time()));
			header.setFormNote(headerIter.form_note());
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setFormStatus(headerIter.formStatus());
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class LossHeaderBean_SJProfileKeys 
{
  private static LossHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new LossHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private LossHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.LossHeaderBean_SJProfile0");
  }
}
