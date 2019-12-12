/*@lineinfo:filename=WorkOrderHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Nov 06 14:41:20 CST 2014 by pw
 * Last edited on Thu Nov 06 14:41:20 CST 2014 by pw
 * 
 * comment: 中央工厂生产工单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.WorkOrderHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class WorkOrderHeaderBean extends ConnectionPool {
	
	public static final int ALL = 0;
	public static final int UN_AUDIT = 1;
	public static final int AUDIT_ED = 2;
	public static final int AUDIT_ED_RECEIVE = 3;

	public static Logger log = Logger.getLogger(WorkOrderHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:42^2*/

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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_dimension2Ndx = findColumn("item_dimension2");
    item_specificationNdx = findColumn("item_specification");
    item_countNdx = findColumn("item_count");
    item_count2Ndx = findColumn("item_count2");
    actual_countNdx = findColumn("actual_count");
    workshopNdx = findColumn("workshop");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    item_priceNdx = findColumn("item_price");
    itemAmtNdx = findColumn("itemAmt");
    complete_timeNdx = findColumn("complete_time");
    inputed_countNdx = findColumn("inputed_count");
    delivery_factorNdx = findColumn("delivery_factor");
    category_idNdx = findColumn("category_id");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String item_dimension2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimension2Ndx);
  }
  private int item_dimension2Ndx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double item_count2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count2Ndx);
  }
  private int item_count2Ndx;
  public Double actual_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actual_countNdx);
  }
  private int actual_countNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
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
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double itemAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemAmtNdx);
  }
  private int itemAmtNdx;
  public Date complete_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(complete_timeNdx);
  }
  private int complete_timeNdx;
  public Double inputed_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inputed_countNdx);
  }
  private int inputed_countNdx;
  public Double delivery_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delivery_factorNdx);
  }
  private int delivery_factorNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^85*/
	
	public int saveEntity(WorkOrderHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String itemId = header.getItemId();
		String itemName = header.getItemName();
		String itemDimension = header.getItemDimension();
		String itemDimension2 = header.getItemDimension2();
		String itemSpecification = header.getItemSpecification();
		Double itemCount = header.getItemCount();
		Double itemCount2 = header.getItemCount2();
		String workshop = header.getWorkshop();
		String formBranchId = header.getFormBranchId();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote(); 
		
		Double inputedCount = header.getInputedCount();
		
		String batchFlag = header.getBatchFlag();
		//

		/*@lineinfo:generated-code*//*@lineinfo:76^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_WORK_ORDER_HEADER") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION,ITEM_DIMENSION2, ITEM_SPECIFICATION, ITEM_COUNT,  ITEM_COUNT2,WORKSHOP, FORM_BRANCH_ID, FORM_MAKER_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL,
//  						AUDITOR_ID, AUDITOR, AUDIT_TIME, FORM_NOTE,RECEIVED_STATUS,INPUTED_COUNT,BATCH_FLAG) 
//  			VALUES (:formId, :itemId, :itemName, :itemDimension,:itemDimension2, :itemSpecification, :itemCount,:itemCount2, :workshop, :formBranchId, :formMakerId, :formMaker, :formTime, :formTimeActual,
//  					:auditorId, :auditor, :auditTime, :formNote,'default', :inputedCount,:batchFlag)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemDimension2;
  String __sJT_7 = itemSpecification;
  Double __sJT_8 = itemCount;
  Double __sJT_9 = itemCount2;
  String __sJT_10 = workshop;
  String __sJT_11 = formBranchId;
  String __sJT_12 = formMakerId;
  String __sJT_13 = formMaker;
  Date __sJT_14 = formTime;
  Date __sJT_15 = formTimeActual;
  String __sJT_16 = auditorId;
  String __sJT_17 = auditor;
  Date __sJT_18 = auditTime;
  String __sJT_19 = formNote;
  Double __sJT_20 = inputedCount;
  String __sJT_21 = batchFlag;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setDate(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setDoubleWrapper(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:82^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(WorkOrderHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Double itemCount = header.getItemCount();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:100^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_WORK_ORDER_HEADER")
//  			SET
//  				ITEM_COUNT = :itemCount,
//  				FORM_MAKER_ID = :formMakerId,
//  				FORM_MAKER = :formMaker,
//  				FORM_TIME = :formTime,
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
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  Double __sJT_2 = itemCount;
  String __sJT_3 = formMakerId;
  String __sJT_4 = formMaker;
  Date __sJT_5 = formTime;
  Date __sJT_6 = auditTime;
  String __sJT_7 = formNote;
  String __sJT_8 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:112^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateBatchFlag(String formId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:122^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_WORK_ORDER_HEADER")
//  			SET
//  			    BATCH_FLAG = 'Y'
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:129^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	
	public int updateInputCount(WorkOrderHeader header)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		Double inputedCount = header.getInputedCount();
		
		/*@lineinfo:generated-code*//*@lineinfo:143^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_WORK_ORDER_HEADER")
//  			SET
//  				INPUTED_COUNT = :inputedCount
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  Double __sJT_2 = inputedCount;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:150^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int output(WorkOrderHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Double actualCount = header.getActualCount();

		/*@lineinfo:generated-code*//*@lineinfo:163^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_WORK_ORDER_HEADER")
//  			SET
//  				ACTUAL_COUNT = :actualCount
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  Double __sJT_2 = actualCount;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:170^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:180^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:186^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int audit(String formId, String userId,String userName, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:197^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_WORK_ORDER_HEADER")
//  			SET
//  				auditor = :userName,
//  				auditor_id = :userId,
//  				audit_time = :sAuditTime
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = userName;
  String __sJT_3 = userId;
  Date __sJT_4 = sAuditTime;
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:206^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:219^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			   count(*) 
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_HEADER") h
//  			JOIN
//  			:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.WORK_ORDER_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			WHERE
//  				:queryStr
//  			AND s.IS_CURRENT = 1
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
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:237^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public int countByFormTime(java.util.Date startDate, java.util.Date endDate, String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:249^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_WORK_ORDER_HEADER") h
//  		JOIN
//  		:Com_("D_T1_ARRENGMENT_DETAIL") d
//  		ON
//  		    h.FORM_ID = d.WORK_ORDER_ID
//  		LEFT JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		WHERE
//  		    :queryStr
//  		AND s.IS_CURRENT = 1
//  		AND (
//  		        h.FORM_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.FORM_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:272^26*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	/**
	 * 与工单相关联的未审核领料单个数
	 */
	public int getCounts(String formId,String type) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:285^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_REQUISITION_HEADER") h
//  		LEFT JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    h.FORM_ID = s.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		WHERE
//  		    h.FORM_REF_ID = :formId
//  		AND s.STATUS = '未审核'
//  		AND h.FORM_TYPE = :type
//  	 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  String __sJT_4 = type;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 9);
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

/*@lineinfo:user-code*//*@lineinfo:301^2*/
		closeDefaultContext(myCtx);
		return total;
	}

	public List<WorkOrderHeader> query(java.util.Date startDate, java.util.Date endDate,String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:313^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    item AS
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            t.THERAPY_NAME,
//  			            ROUND( SUM(p.ITEM_PRICE*t.ITEM_GROSS_COUNT),2) itemAmt
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        LEFT JOIN
//  			        :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            t.ITEM_ID =p.ITEM_ID
//  			        AND p.IS_CURRENT = 1
//  			        AND p.PRICE_TYPE = 'PURCHASE'
//  			        GROUP BY
//  			            t.THERAPY_ID,
//  			            t.THERAPY_NAME
//  			    ),
//  			    receiveCount AS(
//  		    		SELECT
//  			    	    d.ITEM_ID,
//  			    	    h.FORM_REF_ID,
//  			    	    SUM(d.RECEIVE_COUNT) RECEIVE_COUNT
//  			    	FROM
//  			    	:Com_("D_T1_INPUT_HEADER") h
//  			    	INNER JOIN
//  			    	:Com_("D_T1_INPUT_DETAIL") d
//  			    	ON
//  			    	    d.FORM_ID = h.FORM_ID
//  			    	WHERE
//  			    	    h.AUDIT_TIME IS NOT NULL
//  			    	GROUP BY
//  			    	    (d.ITEM_ID,h.FORM_REF_ID)
//  			    		)
//  			SELECT
//  				h.FORM_ID,
//  			    h.ITEM_ID,
//  			    h.ITEM_NAME,
//  			    h.ITEM_DIMENSION,
//  			    h.ITEM_SPECIFICATION,
//  			    ROUND((h.ITEM_COUNT-NVL(r.RECEIVE_COUNT,0))/nvl(u.DELIVERY_FACTOR,1),4) ITEM_COUNT,
//  			    h.ACTUAL_COUNT,
//  			    h.WORKSHOP,
//  			    h.FORM_MAKER_ID,
//  			    h.FORM_MAKER,
//  			    h.FORM_TIME,
//  			    h.AUDITOR_ID,
//  			    h.AUDITOR,
//  			    h.AUDIT_TIME,
//  			    h.FORM_NOTE,
//  			    h.FORM_TIME_ACTUAL,
//  			    h.RECEIVED_STATUS,
//  			    h.INPUTED_COUNT,
//  			    h.FORM_BRANCH_ID,
//  			    h.BATCH_FLAG,
//  			    h.ITEM_DIMENSION2,
//  			    h.ITEM_COUNT2,
//  			    i.itemAmt,
//  			    d.complete_time,
//  			    p.ITEM_PRICE,
//  			    u.DELIVERY_FACTOR,
//  			    m.CATEGORY_ID,
//  			    s.status
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_HEADER") h
//  			JOIN
//  			:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.WORK_ORDER_ID
//  			LEFT JOIN
//  			receiveCount r
//  			ON 
//  			r.FORM_REF_ID = h.FORM_ID
//  			AND r.ITEM_ID = h.ITEM_ID
//      		LEFT JOIN
//      	    	item i
//      	    ON
//      	    	i.THERAPY_ID=h.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID AND s.IS_CURRENT = 1
//      		LEFT JOIN
//  		    :Com_("D_T2_DELIVERY_UNIT") u
//  		    ON
//  		    h.item_id = u.ITEM_ID
//      		LEFT JOIN
//  		    :Com_("D_T2_ITEM_META") m
//  		    ON
//  		    h.item_id = m.ITEM_ID
//      		LEFT JOIN
//      		:Com_("D_T2_ITEM_PRICE") p
//  		    ON
//  			  p.ITEM_ID = h.ITEM_ID AND PRICE_TYPE='BENCHMARK'
//  			WHERE
//  				:queryStr
//  			AND (h.FORM_TIME >= :sDate or :sDate is null)
//  			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  			ORDER BY h.WORKSHOP,h.FORM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = Com_("D_T1_INPUT_HEADER");
  String __sJT_4 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_5 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_6 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_7 = Com_("D_T0_FORM_STATUS");
  String __sJT_8 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_9 = Com_("D_T2_ITEM_META");
  String __sJT_10 = Com_("D_T2_ITEM_PRICE");
  String __sJT_11 = queryStr;
  Date __sJT_12 = sDate;
  Date __sJT_13 = sDate;
  Date __sJT_14 = eDate;
  Date __sJT_15 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 10);
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
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_stmt.setDate(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
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

/*@lineinfo:user-code*//*@lineinfo:414^3*/
		List<WorkOrderHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<WorkOrderHeader> queryNew(java.util.Date startDate, java.util.Date endDate,String queryStr,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:428^3*/

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
//  			            			SELECT
//  			        			    h.*, 
//  			        			    '' itemAmt,
//  			        			    d.complete_time,
//  			        			    p.item_price,
//  			        			    u.DELIVERY_FACTOR,
//  			        			    m.CATEGORY_ID,
//  			        			    s.status
//  			        			FROM
//  			        			:Com_("D_T1_WORK_ORDER_HEADER") h
//  			        			JOIN
//  			        			:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			        			ON
//  			        			    h.FORM_ID = d.WORK_ORDER_ID
//  			        			LEFT JOIN
//  			        			:Com_("D_T0_FORM_STATUS") s
//  			        			ON
//  			        			    s.FORM_ID = h.FORM_ID 
//  			        			AND s.IS_CURRENT = 1
//  	        					LEFT JOIN
//  	        				    :Com_("D_T2_DELIVERY_UNIT") u
//  	        				    ON
//  	        				    h.item_id = u.ITEM_ID
//      				    		LEFT JOIN
//      				    		:Com_("D_T2_ITEM_META") m
//  	        				    ON
//  	        				    h.item_id = m.ITEM_ID
//          			    		LEFT JOIN
//          			    		:Com_("D_T2_ITEM_PRICE") p
//          					    ON
//          						  p.ITEM_ID = h.ITEM_ID AND PRICE_TYPE='BENCHMARK'
//  			        			WHERE
//  			        				:queryStr
//  			        			AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			        			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  			        			ORDER BY  h.FORM_ID
//  			        		) t
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
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = queryStr;
  Date __sJT_8 = sDate;
  Date __sJT_9 = sDate;
  Date __sJT_10 = eDate;
  Date __sJT_11 = eDate;
  int __sJT_12 = endRow;
  int __sJT_13 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 11);
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

/*@lineinfo:user-code*//*@lineinfo:478^34*/
		List<WorkOrderHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<WorkOrderHeader> queryByFormTime(java.util.Date startDate, java.util.Date endDate,String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:492^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    '' itemAmt,
//  			    d.complete_time,
//  			    p.item_price,
//  			    u.DELIVERY_FACTOR,
//  			    m.CATEGORY_ID,
//  			    s.status
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_HEADER") h
//  			JOIN
//  			:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.WORK_ORDER_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			LEFT JOIN
//  		    :Com_("D_T2_DELIVERY_UNIT") u
//  		    ON
//  		    h.item_id = u.ITEM_ID
//      		LEFT JOIN
//      		:Com_("D_T2_ITEM_META") m
//  		    ON
//  		    h.item_id = m.ITEM_ID
//      		LEFT JOIN
//      		:Com_("D_T2_ITEM_PRICE") p
//  		    ON
//  			  p.ITEM_ID = h.ITEM_ID AND PRICE_TYPE='BENCHMARK'
//  			WHERE
//  			    :queryStr
//  			AND (
//  			        h.FORM_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.FORM_TIME <= :eDate
//  			    OR  :eDate IS NULL)
//  			ORDER BY
//  			    h.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = queryStr;
  Date __sJT_8 = sDate;
  Date __sJT_9 = sDate;
  Date __sJT_10 = eDate;
  Date __sJT_11 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 12);
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

/*@lineinfo:user-code*//*@lineinfo:533^17*/
		List<WorkOrderHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<WorkOrderHeader> queryByTime(java.util.Date startDate, java.util.Date endDate,String queryStr,String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:547^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    '' itemAmt,
//  			    h.form_time + nvl(c.PRODUCTION_CYCLE,0) complete_time,
//  			    p.item_price,
//  			    u.DELIVERY_FACTOR,
//  			    m.CATEGORY_ID,
//  			    s.status
//  			FROM
//  				:Com_("D_T1_WORK_ORDER_HEADER") h
//  			left join 
//  				:Com_("D_T2_PRODUCTION_CYCLE") c 
//  			on h.item_id = c.ITEM_ID 
//  			and c.BRANCH_ID = :branchId
//  			LEFT JOIN
//  				:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			LEFT JOIN
//  		    :Com_("D_T2_DELIVERY_UNIT") u
//  		    ON
//  		    h.item_id = u.ITEM_ID
//      		LEFT JOIN
//      		:Com_("D_T2_ITEM_META") m
//  		    ON
//  		    h.item_id = m.ITEM_ID
//      		LEFT JOIN
//      			:Com_("D_T2_ITEM_PRICE") p
//  		    ON
//  			  p.ITEM_ID = h.ITEM_ID AND PRICE_TYPE='BENCHMARK'			
//  			WHERE
//  			    :queryStr
//  			AND (
//  			        h.FORM_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.FORM_TIME <= :eDate
//  			    OR  :eDate IS NULL)
//  			ORDER BY
//  			    h.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_3 = branchId;
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_6 = Com_("D_T2_ITEM_META");
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = queryStr;
  Date __sJT_9 = sDate;
  Date __sJT_10 = sDate;
  Date __sJT_11 = eDate;
  Date __sJT_12 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 13);
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
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
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

/*@lineinfo:user-code*//*@lineinfo:588^17*/
		List<WorkOrderHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	
	public WorkOrderHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:600^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    '' itemAmt,
//  			    d.COMPLETE_TIME,
//  			    u.DELIVERY_FACTOR,
//  			    m.CATEGORY_ID,
//  			    p.item_price
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_HEADER") h
//  			JOIN
//  			:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.WORK_ORDER_ID
//      		LEFT JOIN
//  		    :Com_("D_T2_DELIVERY_UNIT") u
//  		    ON
//  		    h.item_id = u.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") m
//  		    ON
//  		    h.item_id = m.ITEM_ID
//      		LEFT JOIN
//      		:Com_("D_T2_ITEM_PRICE") p
//  		    ON
//  			  p.ITEM_ID = h.ITEM_ID AND PRICE_TYPE='BENCHMARK'
//  			WHERE
//  			    h.form_id = :formId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 14);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:627^27*/
		List<WorkOrderHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<WorkOrderHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<WorkOrderHeader> headerLst = new ArrayList<WorkOrderHeader>();
		while(headerIter.next()) {
			WorkOrderHeader header = new WorkOrderHeader();
			header.setFormId(headerIter.form_id());
			header.setItemId(headerIter.item_id());
			header.setItemName(headerIter.item_name());
			header.setItemDimension(headerIter.item_dimension());
			header.setItemDimension2(headerIter.item_dimension2());
			header.setItemSpecification(headerIter.item_specification());
			header.setItemCount(headerIter.item_count());
			header.setItemCount2(headerIter.item_count2());
			header.setActualCount(headerIter.actual_count());
			header.setWorkshop(headerIter.workshop());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setInputedCount(headerIter.inputed_count());
			header.setCompleteTime(SqlDateUtil.getUtilDate(headerIter.complete_time()));
			header.setItemPrice(headerIter.item_price());
			header.setItemAmt(headerIter.itemAmt());
			header.setDeliveryFactor(headerIter.delivery_factor());
			header.setCategoryId(headerIter.category_id());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class WorkOrderHeaderBean_SJProfileKeys 
{
  private static WorkOrderHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new WorkOrderHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private WorkOrderHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.WorkOrderHeaderBean_SJProfile0");
  }
}
