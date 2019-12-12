/*@lineinfo:filename=PutinstorageBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 8, 2014 by liyzh
 * Last edited on Dec 8, 2014 by liyzh
 * 
 * 说明： 查询物流中心需要入库的采购单
 */
package logic.module.lc;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.InputDetail;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BranchType;

public class PutinstorageBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PutinstorageBean.class);
	
	/**
	 * 未入库的采购单，树形结构
	 */
	/*@lineinfo:generated-code*//*@lineinfo:41^2*/

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

/*@lineinfo:user-code*//*@lineinfo:41^109*/
	
	/**
	 * 根据制单日期获取所有未入库的采购单，树形结构数据，
	 * 单据当前状态为未入库，或者已入库（分多次入库的情况，已结案才是真正地完成入库操作）
	 * 下发到央厂的采购单除外
	 */
	public List<Map> queryUnStorage(String lcCode) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String cfType = BranchType.CENTRALFACTORY;
		
		DefaultContext myCtx = getDefaultContext();
		UnstorageIter unstorageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:54^3*/

//  ************************************************************
//  #sql [myCtx] unstorageIter = { WITH
//  			    purchase AS
//  			    (
//  			        SELECT
//  			            h.FORM_TIME,
//  			            h.FORM_ID,
//  			            h.DELIVERY_TYPE,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_HEADER") h
//  		            INNER JOIN
//  		            :Com_("D_T2_BRANCH") b
//  			        ON
//  			            h.PROVIDER_ID = b.BRANCH_ID
//  			        AND b.BRANCH_TYPE != :cfType
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  		            	h.REQUESTER_ID = :lcCode
//  		            AND h.DELIVERY_TYPE !='DIRECT'
//  			        AND (
//  			                S.STATUS = '已审核'
//  			            OR  S.STATUS = '已入库' )
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS dtype,
//  			            ''                                  AS form_status
//  			        FROM
//  			            purchase p
//  			        GROUP BY
//  			            p.FORM_TIME
//  			        ORDER BY
//  			            p.FORM_TIME DESC) a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            p.FORM_ID                           AS id,
//  			            p.FORM_ID                           AS name,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            p.DELIVERY_TYPE                     AS dtype,
//  			            form_status
//  			        FROM
//  			            purchase p
//  			        ORDER BY
//  			            SUBSTR(p.FORM_ID, 3) DESC ) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = cfType;
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = lcCode;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:112^43*/
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
	
	public List<Map> queryUnStorage(java.util.Date startDate, java.util.Date endDate,String lcCode) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		String cfType = BranchType.CENTRALFACTORY;
		
		DefaultContext myCtx = getDefaultContext();
		UnstorageIter unstorageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:136^3*/

//  ************************************************************
//  #sql [myCtx] unstorageIter = { WITH
//  			    purchase AS
//  			    (
//  			        SELECT
//  			            h.FORM_TIME,
//  			            h.FORM_ID,
//  			            h.DELIVERY_TYPE,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_HEADER") h
//  		            INNER JOIN
//  		            :Com_("D_T2_BRANCH") b
//  			        ON
//  			            h.PROVIDER_ID = b.BRANCH_ID
//  			        AND b.BRANCH_TYPE != :cfType
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  		            	h.REQUESTER_ID = :lcCode
//  		            AND h.DELIVERY_TYPE !='DIRECT'
//  			        AND (
//  			                S.STATUS = '已审核'
//  			            OR  S.STATUS = '已入库' )
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS dtype,
//  			            ''                                  AS form_status
//  			        FROM
//  			            purchase p
//  			            WHERE
//  			            (p.FORM_TIME >= :sDate or :sDate is null)
//  						AND (p.FORM_TIME <= :eDate or :eDate is null)
//  			        GROUP BY
//  			            p.FORM_TIME
//  			        ORDER BY
//  			            p.FORM_TIME DESC) a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            p.FORM_ID                           AS id,
//  			            p.FORM_ID                           AS name,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            p.DELIVERY_TYPE                     AS dtype,
//  			            form_status
//  			        FROM
//  			            purchase p
//  			        ORDER BY
//  			            SUBSTR(p.FORM_ID, 3) DESC ) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = cfType;
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = lcCode;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 1);
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
      unstorageIter = new UnstorageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:197^43*/
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
	
	public List<Map> queryUnStorage(java.util.Date startDate, java.util.Date endDate,String lcCode,String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		String cfType = BranchType.CENTRALFACTORY;
		
		DefaultContext myCtx = getDefaultContext();
		UnstorageIter unstorageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:221^3*/

//  ************************************************************
//  #sql [myCtx] unstorageIter = { WITH
//  				 a AS (
//  				        SELECT DISTINCT
//  				            t.FORM_ID
//  				        FROM
//  				        :Com_("D_T1_PURCHASING_DETAIL") t
//  				        WHERE
//  				            1=1
//  				        AND (
//  				                t.ITEM_ID = :itemName
//  				            OR  t.ITEM_NAME = :itemName)
//  				        ORDER BY
//  				            t.FORM_ID
//  				    ),
//  				    purchase AS
//  				    (
//  				        SELECT
//  				            h.FORM_ID,
//  				            h.FORM_TIME,
//  				            h.DELIVERY_TYPE,
//  				            s.STATUS AS form_status
//  				        FROM
//  				        :Com_("D_T1_PURCHASING_HEADER") h
//  				        INNER JOIN
//  				        	a
//  				        ON
//  				             a.FORM_ID = h.FORM_ID
//  		            INNER JOIN
//  		            :Com_("D_T2_BRANCH") b
//  			        ON
//  			            h.PROVIDER_ID = b.BRANCH_ID
//  			        AND b.BRANCH_TYPE != :cfType
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  		            	h.REQUESTER_ID = :lcCode
//  		            AND h.DELIVERY_TYPE !='DIRECT'
//  			        AND (
//  			                S.STATUS = '已审核'
//  			            OR  S.STATUS = '已入库' )
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS dtype,
//  			            ''                                  AS form_status
//  			        FROM
//  			            purchase p
//  			            WHERE
//  			            (p.FORM_TIME >= :sDate or :sDate is null)
//  						AND (p.FORM_TIME <= :eDate or :eDate is null)
//  			        GROUP BY
//  			            p.FORM_TIME
//  			        ORDER BY
//  			            p.FORM_TIME DESC) a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            p.FORM_ID                           AS id,
//  			            p.FORM_ID                           AS name,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            p.DELIVERY_TYPE                     AS dtype,
//  			            form_status
//  			        FROM
//  			            purchase p
//  			        ORDER BY
//  			            SUBSTR(p.FORM_ID, 3) DESC ) };
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
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = cfType;
  String __sJT_7 = Com_("D_T0_FORM_STATUS");
  String __sJT_8 = lcCode;
  Date __sJT_9 = sDate;
  Date __sJT_10 = sDate;
  Date __sJT_11 = eDate;
  Date __sJT_12 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 2);
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
      unstorageIter = new UnstorageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:299^43*/
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
	
	public List<Map> queryCFUnStorage(java.util.Date startDate, java.util.Date endDate,String cfCode) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		UnstorageIter unstorageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:321^3*/

//  ************************************************************
//  #sql [myCtx] unstorageIter = { WITH
//  			    purchase AS
//  			    (
//  			        SELECT
//  			            h.FORM_TIME,
//  			            h.FORM_ID,
//  			            h.DELIVERY_TYPE,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_HEADER") h
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  			            h.REQUESTER_ID = :cfCode
//  			        AND (
//  			                S.STATUS = '已审核'
//  			            OR  S.STATUS = '已入库' )
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS dtype,
//  			            ''                                  AS form_status
//  			        FROM
//  			            purchase p
//  			            WHERE
//  			            (p.FORM_TIME >= :sDate or :sDate is null)
//  						AND (p.FORM_TIME <= :eDate or :eDate is null)
//  			        GROUP BY
//  			            p.FORM_TIME
//  			        ORDER BY
//  			            p.FORM_TIME DESC) a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            p.FORM_ID                           AS id,
//  			            p.FORM_ID                           AS name,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            p.DELIVERY_TYPE                     AS dtype,
//  			            form_status
//  			        FROM
//  			            purchase p
//  			        ORDER BY
//  			            SUBSTR(p.FORM_ID, 3) DESC ) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = cfCode;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 3);
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
      unstorageIter = new UnstorageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:376^43*/
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
	
	public List<Map> queryCFUnStorage(java.util.Date startDate, java.util.Date endDate,String cfCode,String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		UnstorageIter unstorageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:398^3*/

//  ************************************************************
//  #sql [myCtx] unstorageIter = { WITH
//  				 a AS (
//  				        SELECT DISTINCT
//  				            t.FORM_ID
//  				        FROM
//  				        :Com_("D_T1_PURCHASING_DETAIL") t
//  				        WHERE
//  				            1=1
//  				        AND (
//  				                t.ITEM_ID = :itemName
//  				            OR  t.ITEM_NAME = :itemName)
//  				        ORDER BY
//  				            t.FORM_ID
//  				    ),
//  				    purchase AS
//  				    (
//  				        SELECT
//  				            h.FORM_ID,
//  				            h.FORM_TIME,
//  				            h.DELIVERY_TYPE,
//  				            s.STATUS AS form_status
//  				        FROM
//  				        :Com_("D_T1_PURCHASING_HEADER") h
//  				        INNER JOIN
//  				        	a
//  				        ON
//  				             a.FORM_ID = h.FORM_ID
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  			            h.REQUESTER_ID = :cfCode
//  			        AND (
//  			                S.STATUS = '已审核'
//  			            OR  S.STATUS = '已入库' )
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS dtype,
//  			            ''                                  AS form_status
//  			        FROM
//  			            purchase p
//  			            WHERE
//  			            (p.FORM_TIME >= :sDate or :sDate is null)
//  						AND (p.FORM_TIME <= :eDate or :eDate is null)
//  			        GROUP BY
//  			            p.FORM_TIME
//  			        ORDER BY
//  			            p.FORM_TIME DESC) a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            p.FORM_ID                           AS id,
//  			            p.FORM_ID                           AS name,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            p.DELIVERY_TYPE                     AS dtype,
//  			            form_status
//  			        FROM
//  			            purchase p
//  			        ORDER BY
//  			            SUBSTR(p.FORM_ID, 3) DESC ) };
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
  String __sJT_6 = cfCode;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
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

/*@lineinfo:user-code*//*@lineinfo:470^43*/
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
	
	public List<Map> queryCFUnStorage(String cfCode) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		UnstorageIter unstorageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:490^3*/

//  ************************************************************
//  #sql [myCtx] unstorageIter = { WITH
//  			    purchase AS
//  			    (
//  			        SELECT
//  			            h.FORM_TIME,
//  			            h.FORM_ID,
//  			            h.DELIVERY_TYPE,
//  			            s.STATUS AS form_status
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_HEADER") h
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  			            h.REQUESTER_ID = :cfCode
//  			        AND (
//  			                S.STATUS = '已审核'
//  			            OR  S.STATUS = '已入库' )
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			            'root'                              AS parent,
//  			            ''                                  AS dtype,
//  			            ''                                  AS form_status
//  			        FROM
//  			            purchase p
//  			        GROUP BY
//  			            p.FORM_TIME
//  			        ORDER BY
//  			            p.FORM_TIME DESC) a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            p.FORM_ID                           AS id,
//  			            p.FORM_ID                           AS name,
//  			            TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS parent,
//  			            p.DELIVERY_TYPE                     AS dtype,
//  			            form_status
//  			        FROM
//  			            purchase p
//  			        ORDER BY
//  			            SUBSTR(p.FORM_ID, 3) DESC ) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = cfCode;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:542^43*/
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
	/*@lineinfo:generated-code*//*@lineinfo:557^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class crossDetailIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public crossDetailIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_countNdx = findColumn("item_count");
    sum_item_countNdx = findColumn("sum_item_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    pay_amtNdx = findColumn("pay_amt");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    expired_timeNdx = findColumn("expired_time");
    out_receive_rateNdx = findColumn("out_receive_rate");
    received_countNdx = findColumn("received_count");
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
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double sum_item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum_item_countNdx);
  }
  private int sum_item_countNdx;
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double receive_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_priceNdx);
  }
  private int receive_priceNdx;
  public Double receive_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtNdx);
  }
  private int receive_amtNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
  public Double out_receive_rate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(out_receive_rateNdx);
  }
  private int out_receive_rateNdx;
  public Double received_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(received_countNdx);
  }
  private int received_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:566^69*/
	
	public List<InputDetail> queryCrossDetail(String pFormId,String supplierId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		crossDetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:572^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    item AS
//  			    (
//  			        SELECT
//  			            FORM_ID,
//  			            ITEM_ID,
//  			            ITEM_NAME,
//  			            ITEM_DIMENSION,
//  			            ITEM_SPECIFICATION,
//  			            ITEM_CATEGORY,
//  			            EXPIRED_TIME,
//  			            SUM(d.ITEM_COUNT)  AS ITEM_COUNT
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_DETAIL") d
//  			        WHERE
//  			            d.FORM_ID =:pFormId
//  			        GROUP BY
//  			            FORM_ID,
//  			            ITEM_ID,
//  			            ITEM_NAME,
//  			            ITEM_DIMENSION,
//  			            ITEM_SPECIFICATION,
//  			            ITEM_CATEGORY,
//  			            EXPIRED_TIME,
//  			        ORDER BY
//  			            d.ITEM_ID
//  			    )
//  			    ,
//  			    instore AS --查询已入库的数量
//  			    (
//  			        SELECT
//  			            id.ITEM_ID,
//  			            SUM(id.RECEIVE_COUNT) AS RECEIVED_COUNT
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        LEFT JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            id.FORM_ID = ih.FORM_ID
//  			        WHERE
//  			            ih.FORM_REF_ID = :pFormId
//  			        GROUP BY
//  			            id.ITEM_ID
//  			    )
//  			SELECT
//  			    i.*,
//  			    e.RECEIVED_COUNT,
//  			    u.OUT_RECEIVE_RATE,
//  			    i.ITEM_COUNT                AS SUM_ITEM_COUNT,
//  			    p.ITEM_PRICE                AS RECEIVE_PRICE,
//  			    p3.ITEM_PRICE               AS ITEM_UNIT_PRICE,
//  			    p.ITEM_PRICE* i.ITEM_COUNT  AS RECEIVE_AMT,
//  			    p3.ITEM_PRICE* i.ITEM_COUNT AS PAY_AMT
//  			FROM
//  			    item i
//  			LEFT JOIN
//  			    instore e
//  			ON
//  			    i.ITEM_ID = e.ITEM_ID
//  			INNER JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = i.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.PRICE_TYPE = 'SUPPLIER'
//  			AND p.SUPPLIER_ID = :supplierId
//  			AND p.ITEM_ID = i.ITEM_ID
//  			AND p.IS_CURRENT =1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.PRICE_TYPE = 'PURCHASE'
//  			AND p2.ITEM_ID = i.ITEM_ID
//  			AND p2.IS_CURRENT =1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p3
//  			ON
//  			    p3.PRICE_TYPE = 'BENCHMARK'
//  			AND p3.ITEM_ID = i.ITEM_ID
//  			AND p3.IS_CURRENT =1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = pFormId;
  String __sJT_3 = Com_("D_T1_INPUT_HEADER");
  String __sJT_4 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_5 = pFormId;
  String __sJT_6 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = supplierId;
  String __sJT_9 = Com_("D_T2_ITEM_PRICE");
  String __sJT_10 = Com_("D_T2_ITEM_PRICE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 6);
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
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new crossDetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:653^24*/
		List<InputDetail> detailLst = processCrossDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:660^2*/

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
    item_categoryNdx = findColumn("item_category");
    item_countNdx = findColumn("item_count");
    sum_item_countNdx = findColumn("sum_item_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    supply_cycleNdx = findColumn("supply_cycle");
    receiver_idNdx = findColumn("receiver_id");
    receiverNdx = findColumn("receiver");
    pay_amtNdx = findColumn("pay_amt");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    expired_timeNdx = findColumn("expired_time");
    out_receive_rateNdx = findColumn("out_receive_rate");
    received_countNdx = findColumn("received_count");
    work_order_idNdx = findColumn("work_order_id");
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
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double sum_item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum_item_countNdx);
  }
  private int sum_item_countNdx;
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double supply_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(supply_cycleNdx);
  }
  private int supply_cycleNdx;
  public String receiver_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receiver_idNdx);
  }
  private int receiver_idNdx;
  public String receiver() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receiverNdx);
  }
  private int receiverNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double receive_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_priceNdx);
  }
  private int receive_priceNdx;
  public Double receive_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtNdx);
  }
  private int receive_amtNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
  public Double out_receive_rate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(out_receive_rateNdx);
  }
  private int out_receive_rateNdx;
  public Double received_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(received_countNdx);
  }
  private int received_countNdx;
  public String work_order_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(work_order_idNdx);
  }
  private int work_order_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:670^91*/
	
	public List<InputDetail> queryUnifiedDetail(String pFormId ,String supplierId,String lcCode)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:676^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    item AS
//  			    (
//  			        SELECT
//  			            FORM_ID,
//  			            ITEM_ID,
//  			            SUM(d.ITEM_COUNT) AS SUM_ITEM_COUNT
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_DETAIL") d
//  			        WHERE
//  			        	d.FORM_ID = :pFormId
//  			        GROUP BY
//  			            FORM_ID,
//  			            ITEM_ID
//  			        ORDER BY
//  			            d.ITEM_ID
//  			    ),
//  			    instore AS --查询已入库的数量
//  			    (
//  			        SELECT
//  			            id.ITEM_ID,
//  			            SUM(id.RECEIVE_COUNT) AS RECEIVED_COUNT
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        LEFT JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            id.FORM_ID = ih.FORM_ID
//  			        WHERE
//  			            ih.FORM_REF_ID = :pFormId
//  			        GROUP BY
//  			            id.ITEM_ID
//  			    )
//  			SELECT
//  			    d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.ITEM_COUNT,
//  			    d.RECEIVER_ID,
//  			    d.RECEIVER,
//  			    d.EXPIRED_TIME,
//  			    u.OUT_RECEIVE_RATE,
//  			    i.RECEIVED_COUNT,
//  			    ic.SUM_ITEM_COUNT-nvl(i.RECEIVED_COUNT,0) SUM_ITEM_COUNT,
//  			    t.SUPPLY_CYCLE,
//  			    NVL(p.ITEM_PRICE,p2.ITEM_PRICE)                   AS RECEIVE_PRICE,
//  			    p3.ITEM_PRICE                  AS ITEM_UNIT_PRICE,
//  			    p.ITEM_PRICE* d.ITEM_COUNT  AS RECEIVE_AMT,
//  			    p3.ITEM_PRICE* d.ITEM_COUNT AS PAY_AMT,
//  			    '' WORK_ORDER_ID
//  			FROM
//  			:Com_("D_T1_PURCHASING_DETAIL") d
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = d.ITEM_ID
//  			LEFT JOIN
//  			    instore i
//  			ON
//  			    i.ITEM_ID = d.ITEM_ID
//  			LEFT JOIN
//  				item ic
//  			ON
//  			    ic.ITEM_ID = d.ITEM_ID
//  		    Left JOIN 
//  		    :Com_("D_T2_SUPPLY_CYCLE") t
//  		    ON
//  		       t.ITEM_ID = d.ITEM_ID
//  		       AND t.BRANCH_ID =:lcCode
//      		LEFT JOIN
//      		:Com_("D_T2_ITEM_PRICE") p
//  	    	ON
//  	    	    p.PRICE_TYPE = 'SUPPLIER'
//  	    	AND p.SUPPLIER_ID = :supplierId
//  	    	AND p.ITEM_ID = d.ITEM_ID
//  	    	AND p.IS_CURRENT =1
//  	    	LEFT JOIN
//  	    	:Com_("D_T2_ITEM_PRICE") p2
//  	    	ON
//  	    	    p2.PRICE_TYPE = 'PURCHASE'
//  	    	AND p2.ITEM_ID = d.ITEM_ID
//  	    	AND p2.IS_CURRENT =1
//  	    	LEFT JOIN
//  	    	:Com_("D_T2_ITEM_PRICE") p3
//  	    	ON
//  	    	    p3.PRICE_TYPE = 'BENCHMARK'
//  	    	AND p3.ITEM_ID = d.ITEM_ID
//  	    	AND p3.IS_CURRENT =1
//  			WHERE
//  			    d.FORM_ID = :pFormId
//  			ORDER BY
//  			 DECODE(d.RECEIVER_ID,NULL,'',d.RECEIVER_ID)  ,DECODE(d.ITEM_ID,NULL,'',d.ITEM_ID)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = pFormId;
  String __sJT_3 = Com_("D_T1_INPUT_HEADER");
  String __sJT_4 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_5 = pFormId;
  String __sJT_6 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_7 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_8 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_9 = lcCode;
  String __sJT_10 = Com_("D_T2_ITEM_PRICE");
  String __sJT_11 = supplierId;
  String __sJT_12 = Com_("D_T2_ITEM_PRICE");
  String __sJT_13 = Com_("D_T2_ITEM_PRICE");
  String __sJT_14 = pFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 7);
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
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
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

/*@lineinfo:user-code*//*@lineinfo:772^3*/
		List<InputDetail> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<InputDetail> queryUnOrder(String pFormId ,String supplierId,String lcCode)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:783^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    DELIVERY AS --已出货数
//  			    (
//  			        SELECT DISTINCT
//  			            sh.FORM_REF_ID FORM_ID,
//  			            sd.ITEM_ID,
//  			            NVL(SUM(sd.DELIVERY_COUNT),0) DELIVERY_COUNT
//  			        FROM
//  			        :Com_("D_T1_SHIPPING_HEADER") sh
//  			        INNER JOIN
//  			        :Com_("D_T1_SHIPPING_DETAIL") sd
//  			        ON
//  			            sd.FORM_ID = sh.FORM_ID
//  			        AND sh.PROVIDER_ID='F00'
//      				WHERE
//      	            sh.FORM_REF_ID= :pFormId
//      	            AND sh.AUDIT_TIME IS NOT NULL
//  			        GROUP BY
//  			            (sh.FORM_REF_ID, sd.ITEM_ID)
//  			    )
//  			    ,
//  			    RECEIVE AS --已入库数
//  			    (
//  			        SELECT DISTINCT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            NVL(SUM(id.RECEIVE_COUNT),0) RECEIVE_COUNT
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_DETAIL") d
//  			        INNER JOIN
//  			        :Com_("D_T1_COLLECT_REF_FORM") f
//  			        ON
//  			            f.REF_FORM_ID=d.FORM_ID
//  			        AND d.ITEM_ID = f.ITEM_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_ARRENGMENT_DETAIL") ad
//  			        ON
//  			            f.COLLECT_FORM_ID=ad.FORM_ID
//  			        AND ad.ITEM_ID =f.ITEM_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        ON
//  			            ih.FORM_REF_ID=ad.WORK_ORDER_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            id.FORM_ID = ih.FORM_ID
//      				WHERE
//      			    d.FORM_ID = :pFormId
//  		    		AND ih.AUDIT_TIME IS NOT NULL
//  			        GROUP BY
//  			            ( d.FORM_ID, d.ITEM_ID)
//  			    )
//  			SELECT
//  			DISTINCT
//  			    d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.ITEM_COUNT,
//  			    d.RECEIVER_ID,
//  			    d.RECEIVER,
//  			    d.EXPIRED_TIME,
//  			    u.OUT_RECEIVE_RATE,
//  			    NVL(de.DELIVERY_COUNT,0) RECEIVED_COUNT,
//  			    NVL(r.RECEIVE_COUNT,0)   SUM_ITEM_COUNT,
//  			    t.SUPPLY_CYCLE,
//  			    NVL(p.ITEM_PRICE,p2.ITEM_PRICE)                    AS RECEIVE_PRICE,
//  			    p3.ITEM_PRICE                  AS ITEM_UNIT_PRICE,
//  			    p.ITEM_PRICE* d.ITEM_COUNT  AS RECEIVE_AMT,
//  			    p3.ITEM_PRICE* d.ITEM_COUNT AS PAY_AMT,
//  			    ar.WORK_ORDER_ID
//  			FROM
//  			:Com_("D_T1_PURCHASING_DETAIL") d
//  			INNER JOIN
//  			    RECEIVE r
//  			ON
//  			    d.ITEM_ID = r.ITEM_ID
//  			LEFT JOIN
//  			    DELIVERY de
//  			ON
//  			    r.ITEM_ID =de.ITEM_ID
//  			INNER JOIN
//  			:Com_("D_T1_COLLECT_REF_FORM") f
//  			ON
//  			    f.ITEM_ID =d.ITEM_ID
//  			AND f.REF_FORM_ID=d.FORM_ID
//  			INNER JOIN
//  			:Com_("D_T1_ARRENGMENT_DETAIL") ar
//  			ON
//  			    ar.FORM_ID=f.COLLECT_FORM_ID
//      		AND ar.ITEM_ID = d.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = d.ITEM_ID
//  		    Left JOIN 
//  		    :Com_("D_T2_SUPPLY_CYCLE") t
//  		    ON
//  		       t.ITEM_ID = d.ITEM_ID
//  		       AND t.BRANCH_ID =:lcCode
//      		LEFT JOIN
//      		:Com_("D_T2_ITEM_PRICE") p
//  	    	ON
//  	    	    p.PRICE_TYPE = 'SUPPLIER'
//  	    	AND p.SUPPLIER_ID = :supplierId
//  	    	AND p.ITEM_ID = d.ITEM_ID
//  	    	AND p.IS_CURRENT =1
//  	    	LEFT JOIN
//  	    	:Com_("D_T2_ITEM_PRICE") p2
//  	    	ON
//  	    	    p2.PRICE_TYPE = 'PURCHASE'
//  	    	AND p2.ITEM_ID = d.ITEM_ID
//  	    	AND p2.IS_CURRENT =1
//  	    	LEFT JOIN
//  	    	:Com_("D_T2_ITEM_PRICE") p3
//  	    	ON
//  	    	    p3.PRICE_TYPE = 'BENCHMARK'
//  	    	AND p3.ITEM_ID = d.ITEM_ID
//  	    	AND p3.IS_CURRENT =1
//  			WHERE
//  			    d.FORM_ID = :pFormId
//      		AND NVL(de.DELIVERY_COUNT,0) <NVL(r.RECEIVE_COUNT,0)
//  			ORDER BY
//  			 DECODE(d.RECEIVER_ID,NULL,'',d.RECEIVER_ID)  ,DECODE(d.ITEM_ID,NULL,'',d.ITEM_ID)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_3 = pFormId;
  String __sJT_4 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_5 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_6 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_7 = Com_("D_T1_INPUT_HEADER");
  String __sJT_8 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_9 = pFormId;
  String __sJT_10 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_11 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_12 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_13 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_14 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_15 = lcCode;
  String __sJT_16 = Com_("D_T2_ITEM_PRICE");
  String __sJT_17 = supplierId;
  String __sJT_18 = Com_("D_T2_ITEM_PRICE");
  String __sJT_19 = Com_("D_T2_ITEM_PRICE");
  String __sJT_20 = pFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PutinstorageBean_SJProfileKeys.getKey(0), 8);
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
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
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

/*@lineinfo:user-code*//*@lineinfo:911^3*/
		List<InputDetail> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	private List<InputDetail> processDetailIter(DetailIter detailIter) 
			throws SQLException {
		List<InputDetail> detailLst = new ArrayList<InputDetail>();
		while(detailIter.next()) {
			InputDetail detail = new InputDetail();
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setSumItemCount(detailIter.sum_item_count());
			detail.setOrderCount(detailIter.item_count());
			detail.setReceivedCount(detailIter.received_count());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setPayAmt(detailIter.pay_amt());
			detail.setSupplyCycle(detailIter.supply_cycle());
			detail.setReceivePrice(detailIter.receive_price());
			detail.setReceiveAmt(detailIter.receive_amt());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setOutReceiveRate(detailIter.out_receive_rate());
			detail.setReceive(detailIter.receiver());
			detail.setReceiveId(detailIter.receiver_id());
			detail.setWorkOrderId(detailIter.work_order_id());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	private List<InputDetail> processCrossDetailIter(crossDetailIter detailIter) 
			throws SQLException {
		List<InputDetail> detailLst = new ArrayList<InputDetail>();
		while(detailIter.next()) {
			InputDetail detail = new InputDetail();
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setSumItemCount(detailIter.sum_item_count());
			detail.setOrderCount(detailIter.item_count());
			detail.setReceivedCount(detailIter.received_count());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setPayAmt(detailIter.pay_amt());
			detail.setReceivePrice(detailIter.receive_price());
			detail.setReceiveAmt(detailIter.receive_amt());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setOutReceiveRate(detailIter.out_receive_rate());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class PutinstorageBean_SJProfileKeys 
{
  private static PutinstorageBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PutinstorageBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PutinstorageBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.PutinstorageBean_SJProfile0");
  }
}
