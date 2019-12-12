/*@lineinfo:filename=ArrangmentDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 04 17:07:12 CST 2014 by pw
 * Last edited on Tue Nov 04 17:07:12 CST 2014 by pw
 * 
 * comment: 中央工厂生产计划/排程单明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ArrangmentDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ArrangmentDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ArrangmentDetailBean.class);
	
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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_countNdx = findColumn("item_count");
    item_dimensionNdx = findColumn("item_dimension");
    item_dimension2Ndx = findColumn("item_dimension2");
    item_specificationNdx = findColumn("item_specification");
    produce_countNdx = findColumn("produce_count");
    produce_count2Ndx = findColumn("produce_count2");
    work_order_idNdx = findColumn("work_order_id");
    workshopNdx = findColumn("workshop");
    produce_timeNdx = findColumn("produce_time");
    production_cycleNdx = findColumn("production_cycle");
    complete_timeNdx = findColumn("complete_time");
    noteNdx = findColumn("note");
    item_priceNdx = findColumn("item_price");
    payAmtNdx = findColumn("payAmt");
    mainNameNdx = findColumn("mainName");
    grossCountNdx = findColumn("grossCount");
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
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
  public Double produce_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(produce_countNdx);
  }
  private int produce_countNdx;
  public Double produce_count2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(produce_count2Ndx);
  }
  private int produce_count2Ndx;
  public String work_order_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(work_order_idNdx);
  }
  private int work_order_idNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
  public Date produce_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(produce_timeNdx);
  }
  private int produce_timeNdx;
  public Double production_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(production_cycleNdx);
  }
  private int production_cycleNdx;
  public Date complete_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(complete_timeNdx);
  }
  private int complete_timeNdx;
  public String note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(noteNdx);
  }
  private int noteNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payAmtNdx);
  }
  private int payAmtNdx;
  public String mainName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(mainNameNdx);
  }
  private int mainNameNdx;
  public Double grossCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(grossCountNdx);
  }
  private int grossCountNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:56^3*/
	
	public int saveEntity(ArrangmentDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		
		String itemDimension = detail.getItemDimension();
		String itemDimension2 = detail.getItemDimension2();
		
		Double produceCount = detail.getProduceCount();
		Double produceCount2 = detail.getProduceCount2();
		
		String itemSpecification = detail.getItemSpecification();
		String workOrderId = detail.getWorkOrderId();
		String workshop = detail.getWorkshop();
		Date produceTime = SqlDateUtil.getSqlDate(detail.getProduceTime());
		Double productionCycle = detail.getProductionCycle();
		Date completeTime = SqlDateUtil.getSqlDate(detail.getCompleteTime());
		String note = detail.getNote();

		/*@lineinfo:generated-code*//*@lineinfo:80^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_ARRENGMENT_DETAIL") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_SPECIFICATION, PRODUCE_COUNT, WORK_ORDER_ID, WORKSHOP, PRODUCE_TIME, PRODUCTION_CYCLE, COMPLETE_TIME, NOTE,ITEM_DIMENSION2,PRODUCE_COUNT2) 
//  			VALUES (:formId, :itemId, :itemName, :itemDimension, :itemSpecification, :produceCount, :workOrderId, :workshop, :produceTime, :productionCycle, :completeTime, :note,:itemDimension2,:produceCount2)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  Double __sJT_7 = produceCount;
  String __sJT_8 = workOrderId;
  String __sJT_9 = workshop;
  Date __sJT_10 = produceTime;
  Double __sJT_11 = productionCycle;
  Date __sJT_12 = completeTime;
  String __sJT_13 = note;
  String __sJT_14 = itemDimension2;
  Double __sJT_15 = produceCount2;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setDoubleWrapper(15, __sJT_15);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:84^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ArrangmentDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemDimension2 = detail.getItemDimension2();
		String itemSpecification = detail.getItemSpecification();
		Double produceCount = detail.getProduceCount();
		Double produceCount2 = detail.getProduceCount2();
		String workOrderId = detail.getWorkOrderId();
		String workshop = detail.getWorkshop();
		Date produceTime = SqlDateUtil.getSqlDate(detail.getProduceTime());
		Double productionCycle = detail.getProductionCycle();
		Date completeTime = SqlDateUtil.getSqlDate(detail.getCompleteTime());
		String note = detail.getNote();

		/*@lineinfo:generated-code*//*@lineinfo:109^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_ARRENGMENT_DETAIL") 
//  			SET
//  				FORM_ID = :formId,
//  				ITEM_ID = :itemId,
//  				ITEM_NAME = :itemName,
//  				ITEM_DIMENSION = :itemDimension,
//  				ITEM_DIMENSION2 = :itemDimension2,
//  				ITEM_SPECIFICATION = :itemSpecification,
//  				PRODUCE_COUNT = :produceCount,
//  				PRODUCE_COUNT2 = :produceCount2,
//  				WORK_ORDER_ID = :workOrderId,
//  				WORKSHOP = :workshop,
//  				PRODUCE_TIME = :produceTime,
//  				PRODUCTION_CYCLE = :productionCycle,
//  				COMPLETE_TIME = :completeTime,
//  				NOTE = :note
//  			WHERE 
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemDimension2;
  String __sJT_7 = itemSpecification;
  Double __sJT_8 = produceCount;
  Double __sJT_9 = produceCount2;
  String __sJT_10 = workOrderId;
  String __sJT_11 = workshop;
  Date __sJT_12 = produceTime;
  Double __sJT_13 = productionCycle;
  Date __sJT_14 = completeTime;
  String __sJT_15 = note;
  String __sJT_16 = formId;
  String __sJT_17 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setDate(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
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

	public int updateProduceCount(String formId, String itemId, Double produceCount, java.util.Date completeTime)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Date compTime = SqlDateUtil.getSqlDate(completeTime);
		/*@lineinfo:generated-code*//*@lineinfo:140^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_ARRENGMENT_DETAIL") 
//  			SET
//  				PRODUCE_COUNT = :produceCount,
//  				COMPLETE_TIME = :compTime
//  			WHERE 
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  Double __sJT_2 = produceCount;
  Date __sJT_3 = compTime;
  String __sJT_4 = formId;
  String __sJT_5 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:149^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateByWorkId(ArrangmentDetail detail)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String workOrderId = detail.getWorkOrderId();
		Date completeTime = SqlDateUtil.getSqlDate(detail.getCompleteTime());
		
		/*@lineinfo:generated-code*//*@lineinfo:162^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_ARRENGMENT_DETAIL") 
//  		    SET
//  		        COMPLETE_TIME = :completeTime
//  		    WHERE
//  		        WORK_ORDER_ID = :workOrderId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  Date __sJT_2 = completeTime;
  String __sJT_3 = workOrderId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:169^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:179^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T1_ARRENGMENT_DETAIL") 
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:185^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public boolean isToTransform(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		Double count1 = 0.0,count2 = 0.0;
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:196^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*)
//  			
//  			FROM
//  				:Com_("D_T1_ARRENGMENT_DETAIL")  d
//  			WHERE
//  			    d.FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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
    count1 = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:205^3*/
			    
		/*@lineinfo:generated-code*//*@lineinfo:207^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*)
//  			
//  			FROM
//  				:Com_("D_T1_ARRENGMENT_DETAIL")  d
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    d.WORK_ORDER_ID = s.FORM_ID
//  			WHERE
//  			    d.FORM_ID = :formId
//  		    AND s.STATUS IS NOT NULL
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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
    count2 = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:221^3*/
				    
		closeDefaultContext(myCtx);
		
		return count2 >= count1 ? true : false;
	}
 
	public List<ArrangmentDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:232^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    item AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            (
//  			                SELECT
//  			                    t.THERAPY_ID,
//  			                    t.ITEM_ID,
//  			                    t.ITEM_NAME,
//  			                    t.ITEM_GROSS_COUNT,
//  			                    row_number() over(partition BY t.THERAPY_ID ORDER BY t.ITEM_GROSS_COUNT*t.UNIT_CONVERT_FACTOR DESC )
//  			                    rn
//  			                FROM
//  			                :Com_("D_T2_THERAPY") t )
//  			        WHERE
//  			            rn=1
//  			    )
//  				SELECT
//  			    d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    0 ITEM_COUNT,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_DIMENSION2,
//  			    d.ITEM_SPECIFICATION,
//  			    d.PRODUCE_COUNT,
//  			    d.PRODUCE_COUNT2,
//  			    d.WORK_ORDER_ID,
//  			    d.PRODUCE_TIME,
//  			    d.PRODUCTION_CYCLE,
//  			    d.COMPLETE_TIME, d.NOTE,
//  			    p.ITEM_PRICE,
//  			    p.ITEM_PRICE*d.PRODUCE_COUNT payAmt,
//  			    fw.WORKSHOP,
//  			    i.ITEM_NAME mainName,
//  			    i.ITEM_GROSS_COUNT*d.PRODUCE_COUNT2*u.DELIVERY_FACTOR grossCount
//  				FROM
//  					:Com_("D_T1_ARRENGMENT_DETAIL")  d
//  				LEFT JOIN
//  				    :Com_("D_T2_ITEM_WORKSHOP") wp
//  				ON
//  				    d.ITEM_ID=wp.ITEM_ID
//  	    		LEFT JOIN
//  		    	    item i
//  		    	ON
//  		    	    i.THERAPY_ID = d.ITEM_ID
//      			LEFT JOIN
//      			    :Com_("D_T2_DELIVERY_UNIT") u
//      			ON 
//      			    u.ITEM_ID = d.ITEM_ID
//  	    		LEFT JOIN
//  		    	    :Com_("D_T2_ITEM_PRICE") p
//  		    	ON
//  		    	    p.PRICE_TYPE = 'BENCHMARK'
//  		    	AND p.ITEM_ID = d.ITEM_ID
//  		    	AND p.IS_CURRENT =1
//  				LEFT JOIN
//  				    :Com_("D_T2_FACTORY_WORKSHOP") fw
//  				ON
//  				    fw.WORK_ORDER_ID=wp.WORK_ORDER_ID
//  				WHERE
//  				    d.FORM_ID = :formId
//  				ORDER BY
//  					d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_WORKSHOP");
  String __sJT_4 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_7 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:299^3*/
		List<ArrangmentDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<ArrangmentDetail> query(String formId,String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:310^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    item AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            (
//  			                SELECT
//  			                    t.THERAPY_ID,
//  			                    t.ITEM_ID,
//  			                    t.ITEM_NAME,
//  			                    round(t.ITEM_GROSS_COUNT,2) ITEM_GROSS_COUNT,
//  			                    row_number() over(partition BY t.THERAPY_ID ORDER BY t.ITEM_GROSS_COUNT*t.UNIT_CONVERT_FACTOR DESC )
//  			                    rn
//  			                FROM
//  			                :Com_("D_T2_THERAPY") t )
//  			        WHERE
//  			            rn=1
//  			    )  ,
//  			    itemCount AS
//  			    (
//  			    	SELECT
//  			    	    s.item_id,
//  						round(sum(NVL(s.ITEM_COUNT,0)),2) item_count
//  					FROM
//  					:Com_("D_T2_STORAGE") s
//  					LEFT JOIN
//  					:Com_("D_T2_BRANCH_STORAGE") bs
//  					ON
//  					    s.STORAGE_ID = bs.STORAGE_ID
//  					WHERE
//  					    bs.BRANCH_ID = 'L00'
//  					  AND bs.PRIORITY = 0
//  					GROUP BY
//  					    s.ITEM_ID
//  			    )
//  				SELECT
//  			    d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    nvl(c.ITEM_COUNT,0) ITEM_COUNT,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_DIMENSION2,
//  			    d.ITEM_SPECIFICATION,
//  			    d.PRODUCE_COUNT,
//  			    d.PRODUCE_COUNT2,
//  			    d.WORK_ORDER_ID,
//  			    d.PRODUCE_TIME,
//  			    d.PRODUCTION_CYCLE,
//  			    d.COMPLETE_TIME, d.NOTE,
//  			    p.ITEM_PRICE,
//  			    p.ITEM_PRICE*d.PRODUCE_COUNT payAmt,
//  			    fw.WORKSHOP,
//  			    i.ITEM_NAME mainName,
//  			    round(i.ITEM_GROSS_COUNT*d.PRODUCE_COUNT2*u.DELIVERY_FACTOR,2) grossCount
//  				FROM
//  					:Com_("D_T1_ARRENGMENT_DETAIL")  d
//  				LEFT JOIN
//  				    :Com_("D_T2_ITEM_WORKSHOP") wp
//  				ON
//  				    d.ITEM_ID=wp.ITEM_ID
//  	    		LEFT JOIN
//  		    	    item i
//  		    	ON
//  		    	    i.THERAPY_ID = d.ITEM_ID
//  	    		LEFT JOIN
//      	    	    itemCount c
//      	    	ON
//      	    	    c.ITEM_ID = d.ITEM_ID
//      			LEFT JOIN
//      			    :Com_("D_T2_DELIVERY_UNIT") u
//      			ON 
//      			    u.ITEM_ID = d.ITEM_ID
//  	    		LEFT JOIN
//  		    	    :Com_("D_T2_ITEM_PRICE") p
//  		    	ON
//  		    	    p.PRICE_TYPE = 'BENCHMARK'
//  		    	AND p.ITEM_ID = d.ITEM_ID
//  		    	AND p.IS_CURRENT =1
//  				LEFT JOIN
//  				    :Com_("D_T2_FACTORY_WORKSHOP") fw
//  				ON
//  				    fw.WORK_ORDER_ID=wp.WORK_ORDER_ID
//  				WHERE
//  				    d.FORM_ID = :formId
//  				ORDER BY
//  					d.WORK_ORDER_ID ASC
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = Com_("D_T2_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_4 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_WORKSHOP");
  String __sJT_6 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_9 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 8);
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
      detailIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:398^3*/
		List<ArrangmentDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public Double countByItemId(String itemId)
			throws NoPrivilegeException,SQLException,NoConnection {
		Double produceCount = 0.0;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:409^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    SUM(NVL(d.PRODUCE_COUNT,0))
//  			    FROM
//  			    	:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			    WHERE
//  			        d.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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
    produceCount = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:416^3*/
		closeDefaultContext(myCtx);
		return produceCount;
	}
	
	public ArrangmentDetail queryByWorkOrderId(String workOrderId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:425^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    item AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            (
//  			                SELECT
//  			                    t.THERAPY_ID,
//  			                    t.ITEM_ID,
//  			                    t.ITEM_NAME,
//  			                    t.ITEM_GROSS_COUNT,
//  			                    row_number() over(partition BY t.THERAPY_ID ORDER BY t.ITEM_GROSS_COUNT*t.UNIT_CONVERT_FACTOR DESC )
//  			                    rn
//  			                FROM
//  			                :Com_("D_T2_THERAPY") t )
//  			        WHERE
//  			            rn=1
//  			    )
//  			SELECT
//  			    d.*,
//  			    0 ITEM_COUNT,
//  			    i.ITEM_NAME mainName,
//  			    i.ITEM_GROSS_COUNT*d.PRODUCE_COUNT2 grossCount
//  			    FROM
//  			    	:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			    LEFT JOIN 
//  			    	item i 
//  		    	ON 
//  			    	i.THERAPY_ID = d.ITEM_ID
//  			    WHERE
//  			        d.WORK_ORDER_ID = :workOrderId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = workOrderId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 10);
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

/*@lineinfo:user-code*//*@lineinfo:458^3*/
		List<ArrangmentDetail> detailLst = processIter(detailIter);
		
		
		detailIter.close();
		closeDefaultContext(myCtx);
		
		if(detailLst.size() == 0){
			return null;
		}
		return detailLst.get(0);
	}

	private List<ArrangmentDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<ArrangmentDetail> detailLst = new ArrayList<ArrangmentDetail>();
		while(detailIter.next()) {
			ArrangmentDetail detail = new ArrangmentDetail();
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemCount(detailIter.item_count());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemDimension2(detailIter.item_dimension2());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setProduceCount(detailIter.produce_count());
			detail.setProduceCount2(detailIter.produce_count2());
			detail.setWorkOrderId(detailIter.work_order_id());
			detail.setWorkshop(detailIter.workshop());
			detail.setProduceTime(SqlDateUtil.getUtilDate(detailIter.produce_time()));
			detail.setProductionCycle(detailIter.production_cycle());
			detail.setCompleteTime(SqlDateUtil.getUtilDate(detailIter.complete_time()));
			detail.setNote(detailIter.note());
			detail.setItemPrice(detailIter.item_price());
			detail.setPayAmt(detailIter.payAmt());
			detail.setMainName(detailIter.mainName());
			detail.setGrossCount(detailIter.grossCount());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class ArrangmentDetailBean_SJProfileKeys 
{
  private static ArrangmentDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ArrangmentDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ArrangmentDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ArrangmentDetailBean_SJProfile0");
  }
}
