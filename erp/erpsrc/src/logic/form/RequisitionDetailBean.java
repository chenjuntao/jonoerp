/*@lineinfo:filename=RequisitionDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 11 16:14:06 CST 2014 by pw
 * Last edited on Tue Nov 11 16:14:06 CST 2014 by pw
 * 
 * comment: 中央工厂生产领料单表明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.RequisitionDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequisitionDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequisitionDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:41^2*/

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
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_countNdx = findColumn("item_count");
    item_priceNdx = findColumn("item_price");
    receive_countNdx = findColumn("receive_count");
    different_countNdx = findColumn("different_count");
    received_countNdx = findColumn("received_count");
    returned_countNdx = findColumn("returned_count");
    expired_timeNdx = findColumn("expired_time");
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
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
  public Double received_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(received_countNdx);
  }
  private int received_countNdx;
  public Double returned_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(returned_countNdx);
  }
  private int returned_countNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:54^2*/
	
	/*@lineinfo:generated-code*//*@lineinfo:56^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class TransformDetailIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TransformDetailIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_countNdx = findColumn("item_count");
    differentCountNdx = findColumn("differentCount");
    receive_countNdx = findColumn("receive_count");
    received_countNdx = findColumn("received_count");
    returned_countNdx = findColumn("returned_count");
  }
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
  public Double differentCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(differentCountNdx);
  }
  private int differentCountNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public Double received_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(received_countNdx);
  }
  private int received_countNdx;
  public Double returned_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(returned_countNdx);
  }
  private int returned_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:66^3*/
	
	public int saveEntity(RequisitionDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		Double itemCount = detail.getItemCount();
		Double receiveCount = detail.getReceiveCount();
		Double differentCount = detail.getDifferentCount();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());

		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_REQUISITION_DETAIL") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_SPECIFICATION, ITEM_COUNT, RECEIVE_COUNT, DIFFERENT_COUNT, EXPIRED_TIME) 
//  			VALUES (:formId, :itemId, :itemName, :itemDimension, :itemSpecification, :itemCount, :receiveCount, :differentCount, :expiredTime)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  Double __sJT_7 = itemCount;
  Double __sJT_8 = receiveCount;
  Double __sJT_9 = differentCount;
  Date __sJT_10 = expiredTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
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
	
	public int updateEntity(RequisitionDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		Double itemCount = detail.getItemCount();
		Double receiveCount = detail.getReceiveCount();
		Double differentCount = detail.getDifferentCount();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());

		/*@lineinfo:generated-code*//*@lineinfo:106^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_REQUISITION_DETAIL")
//  			SET
//  				FORM_ID = :formId,
//  				ITEM_ID = :itemId,
//  				ITEM_NAME = :itemName,
//  				ITEM_DIMENSION = :itemDimension,
//  				ITEM_SPECIFICATION = :itemSpecification,
//  				ITEM_COUNT = :itemCount,
//  				RECEIVE_COUNT = :receiveCount,
//  				DIFFERENT_COUNT = :differentCount,
//  				EXPIRED_TIME = :expiredTime
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
  String __sJT_1 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  Double __sJT_7 = itemCount;
  Double __sJT_8 = receiveCount;
  Double __sJT_9 = differentCount;
  Date __sJT_10 = expiredTime;
  String __sJT_11 = formId;
  String __sJT_12 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:122^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	//更新领料数
	public int updateReceiveCount(RequisitionDetail detail)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String formId = detail.getFormId();
			String itemId = detail.getItemId();
			Double receiveCount = detail.getReceiveCount();

			/*@lineinfo:generated-code*//*@lineinfo:137^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_REQUISITION_DETAIL")
//  				SET
//  					FORM_ID = :formId,
//  					ITEM_ID = :itemId,
//  					RECEIVE_COUNT = :receiveCount
//  				WHERE 
//  					FORM_ID = :formId
//  				AND ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  Double __sJT_4 = receiveCount;
  String __sJT_5 = formId;
  String __sJT_6 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:147^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}

	//更新退料数
	public int updateItemCount(RequisitionDetail detail)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String formId = detail.getFormId();
			String itemId = detail.getItemId();
			Double itemCount = detail.getItemCount();

			/*@lineinfo:generated-code*//*@lineinfo:162^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_REQUISITION_DETAIL")
//  				SET
//  					FORM_ID = :formId,
//  					ITEM_ID = :itemId,
//  					ITEM_COUNT = :itemCount
//  				WHERE 
//  					FORM_ID = :formId
//  				AND ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  Double __sJT_4 = itemCount;
  String __sJT_5 = formId;
  String __sJT_6 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:172^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:182^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_REQUISITION_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:188^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	
	public int updateReceiveStatus(String formId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			Integer counts1 = 0;
			Integer counts2 = 0;
			
			/*@lineinfo:generated-code*//*@lineinfo:202^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    COUNT(*) 
//  				    FROM
//  				    :Com_("D_T1_WORKORDER_ITEM") i
//  				    WHERE
//  				     i.FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 5);
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
    counts1 = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:209^4*/
			
			/*@lineinfo:generated-code*//*@lineinfo:211^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*) 
//  			    FROM
//  			    :Com_("D_T1_WORKORDER_ITEM") i
//  			    WHERE
//  			        i.RECEIVED_COUNT >= i.ITEM_COUNT
//  			    AND i.FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 6);
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
    counts2 = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:219^4*/
			
			if(counts1.intValue() != counts2.intValue()){
				if(counts2.intValue() > 0){
					/*@lineinfo:generated-code*//*@lineinfo:223^6*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  						:Com_("D_T1_WORK_ORDER_HEADER") h
//  						    SET
//  						        h.RECEIVED_STATUS = 'receiving'
//  						    WHERE
//  						        h.FORM_ID = :formId
//  						 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:230^7*/
				}
			}else{
				/*@lineinfo:generated-code*//*@lineinfo:233^5*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_WORK_ORDER_HEADER") h
//  				    SET
//  				        h.RECEIVED_STATUS = 'received'
//  				    WHERE
//  				        h.FORM_ID = :formId
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:240^5*/
			}
			
			closeDefaultContext(myCtx);
			
			return counts2;
		}
	
	public List<RequisitionDetail> transformToDetail(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		TransformDetailIter transformDetailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:252^3*/

//  ************************************************************
//  #sql [myCtx] transformDetailIter = { SELECT
//  			    i.ITEM_ID ,
//  			    i.ITEM_NAME ,
//  			    m.ITEM_DIMENSION ,
//  			    i.ITEM_COUNT,
//  			    i.RECEIVED_COUNT,
//  			    CASE
//  			    	WHEN round((i.ITEM_COUNT -  i.RECEIVED_COUNT) ,4) >= 0
//  			    	THEN round((i.ITEM_COUNT -  i.RECEIVED_COUNT) ,4)
//  		        ELSE 0
//  		        END receive_count,
//  		        round((i.ITEM_COUNT -  i.RECEIVED_COUNT) ,4) differentCount,
//  			    i.RETURNED_COUNT,
//  			    m.ITEM_SPECIFICATION
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_WORKORDER_ITEM") i
//  			ON
//  			    h.FORM_ID = i.FORM_ID
//      		INNER JOIN
//      		:Com_("D_T2_ITEM_META") m
//  			ON
//  			    i.ITEM_ID = m.ITEM_ID
//      		INNER JOIN
//      		:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      transformDetailIter = new TransformDetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:283^19*/
		
		List<RequisitionDetail> detailLst = new ArrayList<RequisitionDetail>();
		while(transformDetailIter.next()) {
			RequisitionDetail detail = new RequisitionDetail();
			detail.setItemId(transformDetailIter.item_id());
			detail.setItemName(transformDetailIter.item_name());
			detail.setItemDimension(transformDetailIter.item_dimension());
			detail.setItemSpecification(transformDetailIter.item_specification());
			detail.setItemCount(transformDetailIter.item_count());
			
			detail.setReceiveCount(transformDetailIter.receive_count());
			detail.setReceivedCount(transformDetailIter.received_count());
			detail.setReturnedCount(transformDetailIter.returned_count());
			
			detail.setDifferentCount(transformDetailIter.differentCount());
			detail.setExpiredTime(null);
			detailLst.add(detail);
		}
		transformDetailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	 public int count(String formId)
				throws NoPrivilegeException,SQLException,NoConnection {
			
			DefaultContext myCtx = getDefaultContext();
			int total = 0;
			/*@lineinfo:generated-code*//*@lineinfo:312^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				  count(*) 
//  				FROM
//  				:Com_("D_T1_REQUISITION_HEADER") s
//  				WHERE
//  			   s.FORM_REF_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 10);
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

/*@lineinfo:user-code*//*@lineinfo:319^4*/
			closeDefaultContext(myCtx);
			return total;
		}	
	 
	public List<RequisitionDetail> transformToDetail(String formId,String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		TransformDetailIter transformDetailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:328^3*/

//  ************************************************************
//  #sql [myCtx] transformDetailIter = { SELECT
//  			    i.ITEM_ID ,
//  			    i.ITEM_NAME ,
//  			    m.ITEM_DIMENSION ,
//  			    i.ITEM_COUNT,
//  			    i.RECEIVED_COUNT,
//  			    CASE
//  			    	WHEN round((i.ITEM_COUNT -  i.RECEIVED_COUNT) ,4) >= 0
//  			    	THEN round((i.ITEM_COUNT -  i.RECEIVED_COUNT) ,4)
//  		        ELSE 0
//  		        END receive_count,
//  		        round((i.ITEM_COUNT -  i.RECEIVED_COUNT) ,4) differentCount,
//  			    i.RETURNED_COUNT,
//  			    m.ITEM_SPECIFICATION
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_WORKORDER_ITEM") i
//  			ON
//  			    h.FORM_ID = i.FORM_ID
//  			    AND h.FORM_ID=:formId
//      		INNER JOIN
//      		:Com_("D_T2_ITEM_META") m
//  			ON
//  			    i.ITEM_ID = m.ITEM_ID
//      		INNER JOIN
//      		:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_3 = formId;
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      transformDetailIter = new TransformDetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:360^19*/
		
		List<RequisitionDetail> detailLst = new ArrayList<RequisitionDetail>();
		while(transformDetailIter.next()) {
			RequisitionDetail detail = new RequisitionDetail();
			detail.setItemId(transformDetailIter.item_id());
			detail.setItemName(transformDetailIter.item_name());
			detail.setItemDimension(transformDetailIter.item_dimension());
			detail.setItemSpecification(transformDetailIter.item_specification());
			detail.setItemCount(transformDetailIter.item_count());
			
			detail.setReceiveCount(transformDetailIter.receive_count());
			detail.setReceivedCount(transformDetailIter.received_count());
			detail.setReturnedCount(transformDetailIter.returned_count());
			
			detail.setDifferentCount(transformDetailIter.differentCount());
			detail.setExpiredTime(null);
			detailLst.add(detail);
		}
		transformDetailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<RequisitionDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:388^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			 d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_COUNT,
//  			    d.RECEIVE_COUNT,
//  			    d.DIFFERENT_COUNT,
//  			    d.EXPIRED_TIME,
//  			    p.ITEM_PRICE,
//  			    i.RECEIVED_COUNT,
//  			    i.RETURNED_COUNT
//  			FROM
//  			:Com_("D_T1_REQUISITION_DETAIL") d
//  			LEFT JOIN
//  			:Com_("D_T1_REQUISITION_HEADER") h
//  			ON
//  			    h.FORM_ID = d.FORM_ID
//      		LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = d.ITEM_ID
//      		AND p.PRICE_TYPE = 'BENCHMARK' --标准价
//  	        AND p.IS_CURRENT =1
//  			LEFT JOIN
//  			:Com_("D_T1_WORKORDER_ITEM") i
//  			ON
//  			    h.FORM_REF_ID = i.FORM_ID
//  			AND d.ITEM_ID = i.ITEM_ID
//  			WHERE
//  			    d.FORM_ID = :formId
//  			ORDER BY
//  			    d.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_2 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:422^17*/
		List<RequisitionDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	
	/*@lineinfo:generated-code*//*@lineinfo:430^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class OrginCountIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public OrginCountIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_countNdx = findColumn("item_count");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:433^2*/
	
	public List<Map> getItemOrginCount(String formId,String storageId) throws NoPrivilegeException,SQLException,NoConnection{
		DefaultContext myCtx = getDefaultContext();
		OrginCountIter orginCountIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:438^3*/

//  ************************************************************
//  #sql [myCtx] orginCountIter = { SELECT
//  			    d.ITEM_ID,
//  			    s.ITEM_COUNT
//  			FROM
//  			:Com_("D_T1_REQUISITION_DETAIL") d
//  			LEFT JOIN
//  			:Com_("D_T2_STORAGE") s
//  			ON
//  			    d.ITEM_ID = s.ITEM_ID
//  			AND s.STORAGE_ID = :storageId
//  			WHERE
//  			    d.FORM_ID = :formId
//  			ORDER BY
//  			    d.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_2 = Com_("D_T2_STORAGE");
  String __sJT_3 = storageId;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 13);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      orginCountIter = new OrginCountIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:452^17*/
		
		List<Map> detailLst = new ArrayList<Map>();
		while(orginCountIter.next()) {
			Map map = new HashMap();
			map.put("item_id",orginCountIter.item_id());
			map.put("item_count",orginCountIter.item_count());
			detailLst.add(map);
		}
		
		orginCountIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<RequisitionDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<RequisitionDetail> detailLst = new ArrayList<RequisitionDetail>();
		while(detailIter.next()) {
			RequisitionDetail detail = new RequisitionDetail();
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCount(detailIter.item_count());
			detail.setItemPrice(detailIter.item_price());
			detail.setReceiveCount(detailIter.receive_count());
			detail.setDifferentCount(detailIter.different_count());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setReceivedCount(detailIter.received_count());
			detail.setReturnedCount(detailIter.returned_count());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class RequisitionDetailBean_SJProfileKeys 
{
  private static RequisitionDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequisitionDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequisitionDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.RequisitionDetailBean_SJProfile0");
  }
}
