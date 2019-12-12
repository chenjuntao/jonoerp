/*@lineinfo:filename=WorkOrderItemBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Jun 12 11:18:33 CST 2015 by pw
 * Last edited on Fri Jun 12 11:18:33 CST 2015 by pw
 * 
 * comment: 中央工厂生产工单原料明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.WorkOrderItem;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class WorkOrderItemBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(WorkOrderItemBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

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
    received_countNdx = findColumn("received_count");
    returned_countNdx = findColumn("returned_count");
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

/*@lineinfo:user-code*//*@lineinfo:44^3*/
	
	public int saveEntity(WorkOrderItem detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension =  detail.getItemDimension();
		String itemSpecification =  detail.getItemSpecification();
		
		Double itemCount = detail.getItemCount();
		Double receivedCount = detail.getReceivedCount();
		Double returnedCount = detail.getReturnedCount();

		/*@lineinfo:generated-code*//*@lineinfo:60^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_WORKORDER_ITEM") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME,ITEM_DIMENSION,ITEM_SPECIFICATION, ITEM_COUNT, RECEIVED_COUNT, RETURNED_COUNT) 
//  			VALUES (:formId, :itemId, :itemName,:itemDimension,:itemSpecification, :itemCount, :receivedCount, :returnedCount)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  Double __sJT_7 = itemCount;
  Double __sJT_8 = receivedCount;
  Double __sJT_9 = returnedCount;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderItemBean_SJProfileKeys.getKey(0), 0);
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
	
	public int updateEntity(WorkOrderItem detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		
		Double itemCount = detail.getItemCount();
		Double receivedCount = detail.getReceivedCount();
		Double returnedCount = detail.getReturnedCount();

		/*@lineinfo:generated-code*//*@lineinfo:81^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_WORKORDER_ITEM")
//  			SET
//  				RECEIVED_COUNT = :receivedCount
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
  String __sJT_1 = Com_("D_T1_WORKORDER_ITEM");
  Double __sJT_2 = receivedCount;
  String __sJT_3 = formId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderItemBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:89^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateReturn(WorkOrderItem detail)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		
		Double itemCount = detail.getItemCount();
		Double returnedCount = detail.getReturnedCount();
		
		/*@lineinfo:generated-code*//*@lineinfo:105^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_WORKORDER_ITEM")
//  		    SET
//  		        RETURNED_COUNT = :returnedCount
//  		    WHERE
//  		        FORM_ID = :formId
//  		    AND ITEM_ID = :itemId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORKORDER_ITEM");
  Double __sJT_2 = returnedCount;
  String __sJT_3 = formId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderItemBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:112^28*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int insert(String formId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:122^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT
//  			INTO
//  			:Com_("D_T1_WORKORDER_ITEM")
//  		    (
//  		        FORM_ID,
//  		        ITEM_ID,
//  		        ITEM_NAME,
//  		        ITEM_DIMENSION,
//  		        ITEM_SPECIFICATION,
//  		        ITEM_COUNT,
//  		        RECEIVED_COUNT,
//  		        RETURNED_COUNT
//  		    )
//  		WITH
//  		    a AS
//  		    (
//  		        SELECT
//  		            t.ITEM_ID,
//  		            SUM( ROUND(NVL(h.ITEM_COUNT * ITEM_GROSS_COUNT,0),4)) ITEM_COUNT
//  		        FROM
//  		        :Com_("D_T1_WORK_ORDER_HEADER") h
//  		        INNER JOIN
//  		        :Com_("D_T2_THERAPY") t
//  		        ON
//  		            h.ITEM_ID = t.THERAPY_ID
//  		        AND t.OWNER = 'CENTRALFACTORY'
//  		        LEFT JOIN
//  		        :Com_("D_T2_ITEM_META") m
//  		        ON
//  		            t.ITEM_ID = m.ITEM_ID
//  		        WHERE
//  		            h.FORM_ID = :formId
//  		        AND m.ITEM_TYPE = 'RAW'
//  		        GROUP BY
//  		            t.ITEM_ID
//  		    )
//  		    ,
//  		    b AS
//  		    (
//  		        SELECT
//  		            t.ITEM_ID,
//  		            SUM( ROUND(NVL(h.ITEM_COUNT * ITEM_GROSS_COUNT,0),4)) ITEM_COUNT
//  		        FROM
//  		        :Com_("D_T1_WORK_ORDER_HEADER") h
//  		        INNER JOIN
//  		        :Com_("D_T2_THERAPY") t
//  		        ON
//  		            h.ITEM_ID = t.THERAPY_ID
//  		        AND t.OWNER = 'CENTRALFACTORY'
//  		        LEFT JOIN
//  		        :Com_("D_T2_ITEM_META") m
//  		        ON
//  		            t.ITEM_ID = m.ITEM_ID
//  		        WHERE
//  		            h.FORM_ID = :formId
//  		        AND m.ITEM_TYPE = 'SEMIS'
//  		        GROUP BY
//  		            t.ITEM_ID
//  		    )
//  		SELECT
//  			:formId 	FORM_ID,
//  		    u.item_id,
//  		    m.item_name,
//  		    m.ITEM_DIMENSION,
//  		    m.ITEM_SPECIFICATION,
//  		    SUM(item_count) item_count,
//  		    0               RECEIVED_COUNT,
//  		    0               RETURNED_COUNT
//  		FROM
//  		    (
//  		        SELECT
//  		            *
//  		        FROM
//  		            a
//  		        UNION ALL
//  		        SELECT
//  		            t.ITEM_ID,
//  		            SUM( ROUND(NVL(b.ITEM_COUNT * ITEM_GROSS_COUNT,0),4)) ITEM_COUNT
//  		        FROM
//  		            b
//  		        LEFT JOIN
//  		        :Com_("D_T2_THERAPY") t
//  		        ON
//  		            b.item_id = t.THERAPY_ID
//  		        GROUP BY
//  		            t.ITEM_ID) u
//  		LEFT JOIN
//  		:Com_("D_T2_ITEM_META") m
//  		ON
//  		    u.item_id = m.ITEM_ID
//  		GROUP BY
//  		    u.item_id,
//  		    m.ITEM_NAME,
//  		    m.ITEM_DIMENSION,
//  		    m.ITEM_SPECIFICATION
//  		HAVING
//  		    SUM(item_count) > 0
//  		ORDER BY
//  		    u.item_id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_2 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_3 = Com_("D_T2_THERAPY");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = formId;
  String __sJT_6 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_7 = Com_("D_T2_THERAPY");
  String __sJT_8 = Com_("D_T2_ITEM_META");
  String __sJT_9 = formId;
  String __sJT_10 = formId;
  String __sJT_11 = Com_("D_T2_THERAPY");
  String __sJT_12 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderItemBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:221^16*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:231^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_WORKORDER_ITEM")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderItemBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:237^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<WorkOrderItem> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:247^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    item_dimension,
//  			    item_specification,
//  			    round(d.ITEM_COUNT,2) ITEM_COUNT,
//  			    round(d.RECEIVED_COUNT,2) RECEIVED_COUNT,
//  			    round(d.RETURNED_COUNT,2) RETURNED_COUNT
//  			FROM
//  			:Com_("D_T1_WORKORDER_ITEM") d
//  			WHERE
//  			    d.FORM_ID = :formId
//  			ORDER BY
//  				d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderItemBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:265^3*/
		List<WorkOrderItem> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<WorkOrderItem> processIter(DetailIter detailIter) 
			throws SQLException {
		List<WorkOrderItem> detailLst = new ArrayList<WorkOrderItem>();
		while(detailIter.next()) {
			WorkOrderItem detail = new WorkOrderItem();
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());

			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			
			detail.setItemCount(detailIter.item_count());
			detail.setReceivedCount(detailIter.received_count());
			detail.setReturnedCount(detailIter.returned_count());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class WorkOrderItemBean_SJProfileKeys 
{
  private static WorkOrderItemBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new WorkOrderItemBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private WorkOrderItemBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.WorkOrderItemBean_SJProfile0");
  }
}
