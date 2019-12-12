/*@lineinfo:filename=DirectStockInBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 10, 2014 by liyzh
 * Last edited on Dec 10, 2014 by liyzh
 * 
 * 说明： 直配单餐厅入库
 */
package logic.module.restaurant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BillStatus;

public class DirectStockInBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(DirectStockInBean.class);

	/**
	 * 未入库的采购单，树形结构
	 */
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class UnstorageIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public UnstorageIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
    dtypeNdx = findColumn("dtype");
    form_statusNdx = findColumn("form_status");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(nameNdx);
  }
  private int nameNdx;
  public String parent() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parentNdx);
  }
  private int parentNdx;
  public String dtype() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(dtypeNdx);
  }
  private int dtypeNdx;
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^109*/
	
	/**
	 * 根据制单日期获取所有未入库的采购单，树形结构数据，
	 * 单据当前状态为未入库，或者已入库（分多次入库的情况，已结案才是真正地完成入库操作）
	 */
	public List<Map> queryUnStorage(String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.RECEIVER_ID = '" + branchId + "'";
		}
		query = query + " AND (NVL( s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"
				+ " OR NVL( s.STATUS, 'null') = '" + BillStatus.STORED_CN + "')";
		DefaultContext myCtx = getDefaultContext();
		UnstorageIter unstorageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:54^3*/

//  ************************************************************
//  #sql [myCtx] unstorageIter = { WITH
//  			    purchase AS
//  			    (
//  			        SELECT
//  			            h.FORM_ID,
//  			            h.FORM_TIME,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_HEADER") h
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  			            h.DELIVERY_TYPE = 'DIRECT'
//  			        AND h.RECEIVE_ADDRESS IS NOT NULL --直配单获取小单
//  			        AND :query
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS dtype,
//  			            ''                                  AS form_status
//  			        FROM
//  			            purchase h
//  			        GROUP BY
//  			            h.FORM_TIME
//  			        ORDER BY
//  			            h.FORM_TIME DESC) a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            h.form_id                           AS id,
//  			            h.form_id                           AS name,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            'DIRECT'                            AS dtype,
//  			            form_status
//  			        FROM
//  			            purchase h
//  			        ORDER BY
//  			            SUBSTR(h.FORM_ID, 3) DESC ) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DirectStockInBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      unstorageIter = new UnstorageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:104^43*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(unstorageIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", unstorageIter.id());
			node.put("name", unstorageIter.name());
			node.put("parent", unstorageIter.parent());
			node.put("dtype", unstorageIter.dtype());
			node.put("status", unstorageIter.form_status());
			nodeLst.add(node);
		}
		unstorageIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	public List<Map> queryUnStorage(String branchId,String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.RECEIVER_ID = '" + branchId + "'";
		}
		query = query + " AND (NVL( s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'"
				+ " OR NVL( s.STATUS, 'null') = '" + BillStatus.STORED_CN + "')";
		
		DefaultContext myCtx = getDefaultContext();
		UnstorageIter unstorageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:131^3*/

//  ************************************************************
//  #sql [myCtx] unstorageIter = { WITH
//  			 a AS (
//  			        SELECT DISTINCT
//  			            t.FORM_ID
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_DETAIL") t
//  			        WHERE
//  			            1=1
//  			        AND (
//  			                t.ITEM_ID = :itemName
//  			            OR  t.ITEM_NAME = :itemName)
//  			        ORDER BY
//  			            t.FORM_ID
//  			    ),
//  			    purchase AS
//  			    (
//  			        SELECT
//  			            h.FORM_ID,
//  			            h.FORM_TIME,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_HEADER") h
//  			        INNER JOIN
//  			        	a
//  			        ON
//  			             a.FORM_ID = h.FORM_ID
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  
//  			        WHERE
//  			            h.DELIVERY_TYPE = 'DIRECT'
//  			        AND h.RECEIVE_ADDRESS IS NOT NULL --直配单获取小单
//  			        AND :query
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS dtype,
//  			            ''                                  AS form_status
//  			        FROM
//  			            purchase h
//  			        GROUP BY
//  			            h.FORM_TIME
//  			        ORDER BY
//  			            h.FORM_TIME DESC) a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            h.form_id                           AS id,
//  			            h.form_id                           AS name,
//  			            TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            'DIRECT'                            AS dtype,
//  			            form_status
//  			        FROM
//  			            purchase h
//  			        ORDER BY
//  			            SUBSTR(h.FORM_ID, 3) DESC ) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DirectStockInBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      unstorageIter = new UnstorageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:199^43*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(unstorageIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", unstorageIter.id());
			node.put("name", unstorageIter.name());
			node.put("parent", unstorageIter.parent());
			node.put("dtype", unstorageIter.dtype());
			node.put("status", unstorageIter.form_status());
			nodeLst.add(node);
		}
		unstorageIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	public boolean checkAudit(String pFormId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int count = 0;
		/*@lineinfo:generated-code*//*@lineinfo:219^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*) 
//  			FROM
//  			:Com_("D_T1_INPUT_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			AND s.STATUS = '未审核'
//  			WHERE
//  			    h.FORM_REF_ID = :pFormId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = pFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DirectStockInBean_SJProfileKeys.getKey(0), 2);
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
    count = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:232^3*/
		closeDefaultContext(myCtx);
		if (count > 0) {
			return true;
		}
		return false;
	}
}/*@lineinfo:generated-code*/class DirectStockInBean_SJProfileKeys 
{
  private static DirectStockInBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DirectStockInBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DirectStockInBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.DirectStockInBean_SJProfile0");
  }
}
