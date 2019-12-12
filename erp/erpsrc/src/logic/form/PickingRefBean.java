/*@lineinfo:filename=PickingRefBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Apr 23 11:21:45 CST 2015 by lyz
 * Last edited on Thu Apr 23 11:21:45 CST 2015 by lyz
 * 
 * comment: 捡货单表明细对应配送单的关系
 */
package logic.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PickingRef;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.PriceType;

public class PickingRefBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PickingRefBean.class);
	
	public int saveEntity(PickingRef ref)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = ref.getFormId();
		String formRefId = ref.getFormRefId();
		String itemId = ref.getItemId();
		String branchId = ref.getBranchId();
		Double itemCount = ref.getItemCount();

		/*@lineinfo:generated-code*//*@lineinfo:44^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_PICKING_REF") 
//  				(FORM_ID, FORM_REF_ID, ITEM_ID, BRANCH_ID, ITEM_COUNT) 
//  			VALUES (:formId, :formRefId, :itemId, :branchId, :itemCount)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_REF");
  String __sJT_2 = formId;
  String __sJT_3 = formRefId;
  String __sJT_4 = itemId;
  String __sJT_5 = branchId;
  Double __sJT_6 = itemCount;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingRefBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:48^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(PickingRef ref)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = ref.getFormId();
		String formRefId = ref.getFormRefId();
		String itemId = ref.getItemId();
		String branchId = ref.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:63^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PICKING_REF")
//  			SET
//  				FORM_ID = :formId,
//  				FORM_REF_ID = :formRefId,
//  				ITEM_ID = :itemId,
//  				BRANCH_ID = :branchId
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
  String __sJT_1 = Com_("D_T1_PICKING_REF");
  String __sJT_2 = formId;
  String __sJT_3 = formRefId;
  String __sJT_4 = itemId;
  String __sJT_5 = branchId;
  String __sJT_6 = formId;
  String __sJT_7 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingRefBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:74^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateCount(PickingRef ref)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String formId = ref.getFormId();
			String itemId = ref.getItemId();
			String branchId = ref.getBranchId();
			Double itemCount = ref.getItemCount();
			
			/*@lineinfo:generated-code*//*@lineinfo:89^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_PICKING_REF")
//  				SET
//  					ITEM_COUNT = :itemCount
//  				WHERE 
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  			AND BRANCH_ID = :branchId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_REF");
  Double __sJT_2 = itemCount;
  String __sJT_3 = formId;
  String __sJT_4 = itemId;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingRefBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:98^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:108^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PICKING_REF")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_REF");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingRefBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:114^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:120^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class RefIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public RefIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    item_idNdx = findColumn("item_id");
    branch_idNdx = findColumn("branch_id");
    item_countNdx = findColumn("item_count");
    item_priceNdx = findColumn("item_price");
    delivery_factorNdx = findColumn("delivery_factor");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
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
  public Double delivery_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delivery_factorNdx);
  }
  private int delivery_factorNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:121^82*/
	
	public List<PickingRef> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String priceType = PriceType.BENCHMARK;
		
		DefaultContext myCtx = getDefaultContext();
		RefIter refIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:129^3*/

//  ************************************************************
//  #sql [myCtx] refIter = { SELECT
//  			    d.*,
//  			    p.ITEM_PRICE,
//  			    sd.PACKAGING_FACTOR DELIVERY_FACTOR
//  			FROM
//  			    :Com_("D_T1_PICKING_REF") d
//  			LEFT JOIN
//  			    :Com_("D_T1_SHIPPING_DETAIL") sd
//  			ON
//  			    d.FORM_REF_ID = sd.FORM_ID AND d.ITEM_ID = sd.ITEM_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = d.ITEM_ID
//  			AND p.IS_CURRENT =1
//  			AND p.PRICE_TYPE = :priceType
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
  String __sJT_1 = Com_("D_T1_PICKING_REF");
  String __sJT_2 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = priceType;
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingRefBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      refIter = new RefIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:149^17*/
		List<PickingRef> refLst = processIter(refIter);
		refIter.close();
		closeDefaultContext(myCtx);
		return refLst;
	}

	private List<PickingRef> processIter(RefIter refIter) 
			throws SQLException {
		List<PickingRef> refLst = new ArrayList<PickingRef>();
		while(refIter.next()) {
			PickingRef ref = new PickingRef();
			ref.setFormId(refIter.form_id());
			ref.setFormRefId(refIter.form_ref_id());
			ref.setItemId(refIter.item_id());
			ref.setBranchId(refIter.branch_id());
			ref.setItemCount(refIter.item_count());
			ref.setItemPrice(refIter.item_price());
			ref.setDeliveryFactor(refIter.delivery_factor());
			refLst.add(ref);
		}
		return refLst;
	}
}/*@lineinfo:generated-code*/class PickingRefBean_SJProfileKeys 
{
  private static PickingRefBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PickingRefBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PickingRefBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.PickingRefBean_SJProfile0");
  }
}
