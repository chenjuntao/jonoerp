/*@lineinfo:filename=WorkOrderDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon Nov 17 16:26:19 CST 2014 by pw
 * Last edited on Mon Nov 17 16:26:19 CST 2014 by pw
 * 
 * comment: 中央工厂生产工单明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.WorkOrderDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class WorkOrderDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(WorkOrderDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DetailIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DetailIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    production_stepNdx = findColumn("production_step");
    production_nameNdx = findColumn("production_name");
    production_timeNdx = findColumn("production_time");
    production_countNdx = findColumn("production_count");
    production_manNdx = findColumn("production_man");
    production_noteNdx = findColumn("production_note");
    is_completedNdx = findColumn("is_completed");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public Integer production_step() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(production_stepNdx);
  }
  private int production_stepNdx;
  public String production_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(production_nameNdx);
  }
  private int production_nameNdx;
  public Date production_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(production_timeNdx);
  }
  private int production_timeNdx;
  public Double production_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(production_countNdx);
  }
  private int production_countNdx;
  public String production_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(production_manNdx);
  }
  private int production_manNdx;
  public String production_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(production_noteNdx);
  }
  private int production_noteNdx;
  public Integer is_completed() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(is_completedNdx);
  }
  private int is_completedNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^96*/
	
	public int saveEntity(WorkOrderDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		Integer productionStep = detail.getProductionStep();
		String productionName = detail.getProductionName();
		Date productionTime = SqlDateUtil.getSqlDate(detail.getProductionTime());
		Double productionCount = detail.getProductionCount();
		String productionMan = detail.getProductionMan();
		String productionNote = detail.getProductionNote();

		/*@lineinfo:generated-code*//*@lineinfo:51^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_WORK_ORDER_DETAIL") 
//  				(FORM_ID, PRODUCTION_STEP, PRODUCTION_NAME, PRODUCTION_TIME, PRODUCTION_COUNT, PRODUCTION_MAN, PRODUCTION_NOTE) 
//  			VALUES (:formId, :productionStep, :productionName, :productionTime, :productionCount, :productionMan, :productionNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_DETAIL");
  String __sJT_2 = formId;
  Integer __sJT_3 = productionStep;
  String __sJT_4 = productionName;
  Date __sJT_5 = productionTime;
  Double __sJT_6 = productionCount;
  String __sJT_7 = productionMan;
  String __sJT_8 = productionNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setIntWrapper(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:55^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 批量生成生产工单明细信息
	 */
	public int batchSave(String formId, String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:68^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO
//  			:Com_("D_T1_WORK_ORDER_DETAIL")
//  			    (
//  			        FORM_ID,
//  			        PRODUCTION_NAME,
//  			        PRODUCTION_STEP,
//  			        PRODUCTION_NOTE
//  			    )
//  			SELECT
//  			    :formId FORM_ID,
//  			    STEP_OPERATION         PRODUCTION_NAME ,
//  			    STEP                   PRODUCTION_STEP,
//  			    STEP_NOTE              PRODUCTION_NOTE
//  			FROM
//  			:Com_("D_T2_MAKING_PROCESS") mp
//  			WHERE
//  			    mp.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = Com_("D_T2_MAKING_PROCESS");
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:86^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateEntity(WorkOrderDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String productionName = detail.getProductionName();
		Integer productionStep = detail.getProductionStep();
		Date productionTime = SqlDateUtil.getSqlDate(detail.getProductionTime());
		Double productionCount = detail.getProductionCount();
		String productionMan = detail.getProductionMan();
		String productionNote = detail.getProductionNote();
		Integer isCompleted = detail.getIsCompleted();
		
		/*@lineinfo:generated-code*//*@lineinfo:105^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_WORK_ORDER_DETAIL")
//  			SET
//  				PRODUCTION_COUNT = :productionCount,
//  				PRODUCTION_TIME = :productionTime,
//  				PRODUCTION_MAN = :productionMan,
//  				PRODUCTION_NOTE = :productionNote,
//  				IS_COMPLETED = :isCompleted
//  			WHERE
//  				FORM_ID = :formId
//  			AND PRODUCTION_STEP = :productionStep
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_DETAIL");
  Double __sJT_2 = productionCount;
  Date __sJT_3 = productionTime;
  String __sJT_4 = productionMan;
  String __sJT_5 = productionNote;
  Integer __sJT_6 = isCompleted;
  String __sJT_7 = formId;
  Integer __sJT_8 = productionStep;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setIntWrapper(8, __sJT_8);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:117^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:127^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:133^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<WorkOrderDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:143^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:150^3*/
		List<WorkOrderDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	public WorkOrderDetail getCurrentStep(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:161^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            d.*
//  			        FROM
//  			        :Com_("D_T1_WORK_ORDER_HEADER") h
//  			        LEFT JOIN
//  			        :Com_("D_T1_WORK_ORDER_DETAIL") d
//  			        ON
//  			            d.FORM_ID = h.FORM_ID
//  			        WHERE
//  			            h.form_id = :formId
//  			        AND d.is_completed IS NULL
//  			        ORDER BY
//  			            d.production_step )
//  			WHERE
//  			    rownum = 1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_WORK_ORDER_DETAIL");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:181^3*/
		List<WorkOrderDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		if (detailLst !=null && !detailLst.isEmpty()) {
			return detailLst.get(0);
		}
		return null;
	}

	private List<WorkOrderDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<WorkOrderDetail> detailLst = new ArrayList<WorkOrderDetail>();
		while(detailIter.next()) {
			WorkOrderDetail detail = new WorkOrderDetail();
			detail.setFormId(detailIter.form_id());
			detail.setProductionStep(detailIter.production_step());
			detail.setProductionName(detailIter.production_name());
			detail.setProductionTime(SqlDateUtil.getUtilDate(detailIter.production_time()));
			detail.setProductionCount(detailIter.production_count());
			detail.setProductionMan(detailIter.production_man());
			detail.setProductionNote(detailIter.production_note());
			detail.setIsCompleted(detailIter.is_completed());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class WorkOrderDetailBean_SJProfileKeys 
{
  private static WorkOrderDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new WorkOrderDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private WorkOrderDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.WorkOrderDetailBean_SJProfile0");
  }
}
