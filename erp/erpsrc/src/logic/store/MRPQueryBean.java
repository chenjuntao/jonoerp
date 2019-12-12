/*@lineinfo:filename=MRPQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 25, 2014 by liyzh
 * Last edited on Nov 25, 2014 by liyzh
 * 
 * 说明： 查询库存
 */
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.ItemMeta;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;

public class MRPQueryBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(MRPQueryBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class MrpItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MrpItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_countNdx = findColumn("item_count");
    mrp_countNdx = findColumn("mrp_count");
    receivedNdx = findColumn("received");
    infosNdx = findColumn("infos");
    rownumberNdx = findColumn("rownumber");
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
  public String item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_countNdx);
  }
  private int item_countNdx;
  public String mrp_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(mrp_countNdx);
  }
  private int mrp_countNdx;
  public String received() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receivedNdx);
  }
  private int receivedNdx;
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^3*/
	
	//物流中心已分配量
	/*@lineinfo:generated-code*//*@lineinfo:46^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class LcAMrpItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public LcAMrpItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    form_id2Ndx = findColumn("form_id2");
    item_idNdx = findColumn("item_id");
    mrp_countNdx = findColumn("mrp_count");
    rownumberNdx = findColumn("rownumber");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String form_id2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_id2Ndx);
  }
  private int form_id2Ndx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String mrp_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(mrp_countNdx);
  }
  private int mrp_countNdx;
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:52^3*/
	
	/**
	 * 查询物流中心已分配量
	 */
	public List<Map> queryLcAllocation(String itemId,String branchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		LcAMrpItemIter lcAMrpItemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:62^3*/

//  ************************************************************
//  #sql [myCtx] lcAMrpItemIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            ''FORM_ID2,
//  			            d.ITEM_ID,
//  			            d.ORDER_COUNT
//  			        FROM
//  			            :Com_("D_T1_REQUEST_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T1_REQUEST_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        AND h.PURCHASE_STATUS = '已汇总'
//  			        AND h.SHIPPING_STATUS IS NULL
//  			        AND d.DELIVERY_TYPE ='UNIFIED'
//  			        LEFT JOIN
//  			            :Com_("D_T1_COLLECT_REF_FORM") r
//  			        ON
//  			            d.form_id = r.REF_FORM_ID
//  			        AND d.item_id = r.ITEM_ID
//  			        WHERE
//  			            SUBSTR(r.COLLECT_FORM_ID,1,2) = 'TP' 
//  			        AND d.item_id = :itemId
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            h.FORM_ID,
//  			            '' FORM_ID2,
//  			            d.ITEM_ID,
//  			            d.REQUEST_COUNT
//  			        FROM
//  			            :Com_("D_T1_SHIPPING_HEADER") h
//  			        LEFT JOIN
//  			            :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        LEFT JOIN
//  			            :Com_("D_T1_REQUEST_HEADER") rh
//  			        ON
//  			            h.FORM_REF_ID = rh.FORM_ID
//  			        LEFT JOIN
//  			            :Com_("D_T1_PICKING_REF") r
//  			        ON
//  			            h.FORM_ID = r.FORM_REF_ID
//  			        INNER JOIN
//  			            :Com_("D_T1_SHIPPING_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        WHERE
//  			            h.PICK_STATUS IS NULL
//  			        AND h.FORM_TYPE = 'INNER_UNIFIED'
//  			        AND h.PROVIDER_ID = 'L00'
//  			        AND h.FORM_REF_ID IS NOT NULL
//  			        AND d.ITEM_ID = :itemId
//  			        AND s.STATUS IS NOT NULL
//  			        AND rh.PURCHASE_STATUS IS NOT NULL
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT
//  			            sh.FORM_ID,
//  			            pr.FORM_ID FORM_ID2,
//  			            pr.ITEM_ID,
//  			            pr.ITEM_COUNT
//  			        FROM
//  			            :Com_("D_T1_SHIPPING_HEADER") sh
//  			        INNER JOIN
//  			            :Com_("D_T1_REQUEST_HEADER") rh
//  			        ON
//  			            sh.FORM_REF_ID = rh.FORM_ID
//  			        AND rh.PURCHASE_STATUS IS NOT NULL
//  			        INNER JOIN
//  			            :Com_("D_T1_PICKING_REF") pr
//  			        ON
//  			            sh.FORM_ID = pr.FORM_REF_ID
//  			        AND sh.FORM_REF_ID IS NOT NULL
//  			        AND sh.PROVIDER_ID = 'L00'
//  			        AND sh.FORM_TYPE = 'INNER_UNIFIED'
//  			        INNER JOIN
//  			            :Com_("D_T1_PICKING_HEADER") ph
//  			        ON
//  			            ph.FORM_ID = pr.FORM_ID
//  			        AND pr.ITEM_ID = :itemId
//  			        AND ph.AUDIT_TIME IS NULL
//  			    )
//  			    ,
//  			    d AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            a
//  			        UNION ALL
//  			        SELECT
//  			            *
//  			        FROM
//  			            b
//  			        UNION ALL
//  			        SELECT
//  			            *
//  			        FROM
//  			            c
//  			    )
//  			    ,
//  			    e AS
//  			    (
//  			        SELECT
//  			            d.form_id,
//  			            d.form_id2,
//  			            d.item_id,
//  			            DECODE(form_id2,NULL,d.ORDER_COUNT,u.DELIVERY_FACTOR*d.ORDER_COUNT) ORDER_COUNT
//  			        FROM
//  			            d
//  			        LEFT JOIN
//  			            :Com_("D_T2_DELIVERY_UNIT") u
//  			        ON
//  			            d.item_id = u.ITEM_ID
//  			    )
//  			    ,
//  			    f AS
//  			    (
//  			        SELECT
//  			            FORM_ID,
//  			            FORM_ID2,
//  			            ITEM_ID,
//  			            SUM(ORDER_COUNT) mrp_count
//  			        FROM
//  			            e
//  			        GROUP BY
//  			            GROUPING SETS( ( FORM_ID,FORM_ID2, ITEM_ID),NULL)
//  			        ORDER BY
//  			            DECODE(FORM_ID,NULL,-1,mrp_count) DESC
//  			    )
//  			SELECT
//  			    DECODE(FORM_ID,NULL,'合计',ROWNUM) rownumber,
//  			    f.*
//  			FROM
//  			    f };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_4 = itemId;
  String __sJT_5 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_6 = Com_("D_T0_FORM_STATUS");
  String __sJT_7 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_8 = Com_("D_T1_PICKING_REF");
  String __sJT_9 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_10 = itemId;
  String __sJT_11 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_12 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_13 = Com_("D_T1_PICKING_REF");
  String __sJT_14 = Com_("D_T1_PICKING_HEADER");
  String __sJT_15 = itemId;
  String __sJT_16 = Com_("D_T2_DELIVERY_UNIT");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPQueryBean_SJProfileKeys.getKey(0), 0);
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
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      lcAMrpItemIter = new LcAMrpItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:206^9*/
		
		List<Map> itemLst = new ArrayList<Map>();
		while(lcAMrpItemIter.next()) {
			Map map = new HashMap();
			map.put("form_id", lcAMrpItemIter.form_id());
			map.put("form_id2", lcAMrpItemIter.form_id2());
			map.put("item_id", lcAMrpItemIter.item_id());
			map.put("mrp_count", lcAMrpItemIter.mrp_count());
			map.put("rownumber", lcAMrpItemIter.rownumber());
			itemLst.add(map);
		}
		
		lcAMrpItemIter.close();
		closeDefaultContext(myCtx);
		
		return itemLst;
	}
	
	
	/**
	 * 查询物流中心原料在途量
	 */
	public List<Map> queryLcRawOnTheWay(String itemId,String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		MrpItemIter mrpItemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:234^3*/

//  ************************************************************
//  #sql [myCtx] mrpItemIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_COUNT,
//  			            id.FORM_ID ref_form_id,
//  			            id.RECEIVE_COUNT
//  			        FROM
//  			        	:Com_("D_T1_PURCHASING_HEADER") h 
//  			        INNER JOIN
//  			        	:Com_("D_T0_FORM_STATUS") s 
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        AND s.STATUS IN ('已审核',
//  			                         '已入库')
//  			        AND h.DELIVERY_TYPE = 'UNIFIED'
//  			        INNER JOIN
//  			        	:Com_("D_T1_PURCHASING_DETAIL") d 
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        AND h.REQUESTER_ID = 'L00'
//  			        AND h.PROVIDER_ID != 'F00'
//  			        LEFT JOIN
//  			        	:Com_("D_T1_INPUT_HEADER") ih 
//  			        ON
//  			            d.FORM_ID = ih.FORM_REF_ID
//  			        AND ih.AUDIT_TIME IS NULL
//  			        AND ih.FORM_TYPE != 'MANUAL'
//  			        LEFT JOIN
//  			        	:Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID = id.FORM_ID
//  			        AND d.ITEM_ID = id.ITEM_ID
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            t.FORM_ID,
//  			            t.ITEM_ID,
//  			            t.ITEM_COUNT,
//  			            DECODE(SIGN(t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))),-1,0,t.ITEM_COUNT - SUM(NVL
//  			            (t.RECEIVE_COUNT,0))) mrp_count ,
//  			            listagg('('||t.ref_form_id ||','||t.RECEIVE_COUNT ||')',',') within GROUP (ORDER BY
//  			            t.ITEM_COUNT) infos
//  			        FROM
//  			            a t
//  			        GROUP BY
//  			            t.FORM_ID,
//  			            t.ITEM_ID,
//  			            t.ITEM_COUNT
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT
//  			            FORM_ID,
//  			            ITEM_ID,
//  			            SUM(ITEM_COUNT) ITEM_COUNT,
//  			            SUM( mrp_count) mrp_count,
//  			            infos,
//  			            '' received
//  			        FROM
//  			            b
//  			        WHERE
//  			            item_id = :itemId
//  			        GROUP BY
//  			            GROUPING SETS( (FORM_ID,ITEM_ID,ITEM_COUNT,mrp_count,infos),NULL)
//  			        ORDER BY
//  			            DECODE(FORM_ID,NULL,-1,mrp_count) DESC
//  			    )
//  			SELECT
//  			    DECODE(FORM_ID,NULL,'合计',ROWNUM) rownumber,
//  			    c.*
//  			FROM
//  			    c };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_4 = Com_("D_T1_INPUT_HEADER");
  String __sJT_5 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_6 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPQueryBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      mrpItemIter = new MrpItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:312^9*/
		List<Map> itemLst = processMrpIter(mrpItemIter);
		mrpItemIter.close();
		
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/**
	 * 查询物流中心半成品在途量
	 */
	public List<Map> queryLcSemiOnTheWay(String itemId,String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		MrpItemIter mrpItemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:328^3*/

//  ************************************************************
//  #sql [myCtx] mrpItemIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            h.FORM_ID,
//  			            sh.FORM_ID received ,
//  			            d.ITEM_ID,
//  			            SUM(d.ITEM_COUNT) mrp_count,
//  			            ''                ITEM_COUNT,
//  			            ''                infos
//  			        FROM
//  			        	:Com_("D_T1_PURCHASING_HEADER")  h 
//  			        INNER JOIN
//  			        	:Com_("D_T0_FORM_STATUS") s  
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.STATUS IN( '已审核',
//  			                        '已入库')
//  			        AND s.IS_CURRENT = 1
//  			        AND h.REQUESTER_ID = 'L00'
//  			        AND h.PROVIDER_ID = 'F00'
//  			        AND h.DELIVERY_TYPE = 'UNIFIED'
//  			        AND h.AUDIT_TIME IS NOT NULL
//  			        AND h.PLAN_STATUS = '已汇总'
//  			        AND h.OUT_STATUS IS NULL
//  			        INNER JOIN
//  			        	:Com_("D_T1_PURCHASING_DETAIL") d 
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        AND d.ITEM_ID = :itemId
//  			        LEFT JOIN
//  			        	:Com_("D_T1_SHIPPING_HEADER") sh 
//  			        ON
//  			            h.FORM_ID = sh.FORM_REF_ID
//  			        AND sh.INPUT_TIME IS NULL
//  			        GROUP BY
//  			            GROUPING SETS( (h.FORM_ID, d.ITEM_ID,sh.FORM_ID,d.ITEM_COUNT),NULL)
//  			        ORDER BY
//  			            DECODE(FORM_ID,NULL,-1,mrp_count) DESC
//  			    )
//  			SELECT
//  			    DECODE(FORM_ID,NULL,'合计',ROWNUM) rownumber,
//  			    a.*
//  			FROM
//  			    a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_4 = itemId;
  String __sJT_5 = Com_("D_T1_SHIPPING_HEADER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPQueryBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      mrpItemIter = new MrpItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:373^9*/
		List<Map> itemLst = processMrpIter(mrpItemIter);
		mrpItemIter.close();
		
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/**
	 * 查询中央工厂已分配量
	 */
	public List<Map> queryCfAllocation(String itemId,String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		MrpItemIter mrpItemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:389^3*/

//  ************************************************************
//  #sql [myCtx] mrpItemIter = { WITH
//  			    temp AS
//  			    (
//  			        SELECT
//  			            i.FORM_ID,
//  			            i.ITEM_ID,
//  			            i.ITEM_COUNT,
//  			            rd.FORM_ID ref_form_id,
//  			            rd.RECEIVE_COUNT
//  			        FROM
//  			        :Com_("D_T1_WORK_ORDER_HEADER") h
//  			        INNER JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        AND s.STATUS = '已审核'
//  			        INNER JOIN
//  			        :Com_("D_T1_WORKORDER_ITEM") i
//  			        ON
//  			            h.FORM_ID = i.FORM_ID
//  			        AND i.ITEM_ID = :itemId
//  			        LEFT JOIN
//  			        :Com_("D_T1_REQUISITION_HEADER") rh
//  			        ON
//  			            i.FORM_ID = rh.FORM_REF_ID
//  			        AND rh.AUDIT_TIME IS NOT NULL
//  			        LEFT JOIN
//  			        :Com_("D_T1_REQUISITION_DETAIL") rd
//  			        ON
//  			            rh.FORM_ID = rd.FORM_ID
//  			        AND rd.ITEM_ID = :itemId
//  			        WHERE
//  		        	    (
//  		        	        h.BATCH_FLAG='Y'
//  		        	    AND rh.AUDIT_TIME IS NULL)
//  		        	OR  (
//  		        	        h.BATCH_FLAG!='Y' )
//  			    )
//  			    ,
//  			    temp2 AS
//  			    (
//  			        SELECT
//  			            t.FORM_ID,
//  			            t.ITEM_ID,
//  			            t.ITEM_COUNT,
//  			            decode(sign(t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))),-1,0,t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))) Allocated ,
//  			            SUM(NVL(t.RECEIVE_COUNT,0))                received,
//  			            listagg('('||t.ref_form_id ||','||t.RECEIVE_COUNT ||')',',') within GROUP (ORDER BY
//  			            t.ITEM_COUNT) infos
//  			        FROM
//  			            temp t
//  			        GROUP BY
//  			            t.FORM_ID,
//  			            t.ITEM_ID,
//  			            t.ITEM_COUNT
//  			    )
//  			SELECT
//  			    DECODE(FORM_ID,NULL,'合计',ROWNUM)rownumber ,
//  			    t.*
//  			FROM
//  			    (
//  			        SELECT
//  			            t.FORM_ID,
//  			            t.ITEM_ID,
//  			            SUM( t.ITEM_COUNT) ITEM_COUNT,
//  			            SUM(t.Allocated)   mrp_count,
//  			            SUM(t.received)    received,
//  			            DECODE(length(t.infos),3,'',t.infos)infos
//  			        FROM
//  			            temp2 t
//  			        GROUP BY
//  			            GROUPING SETS( ( t.FORM_ID, t.ITEM_ID,t.ITEM_COUNT,t.Allocated,t.received,t.infos),NULL)
//  			        ORDER BY
//  			            DECODE(FORM_ID,NULL,-1,item_count) DESC) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_4 = itemId;
  String __sJT_5 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_6 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_7 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPQueryBean_SJProfileKeys.getKey(0), 3);
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
      mrpItemIter = new MrpItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:464^58*/
		List<Map> itemLst = processMrpIter(mrpItemIter);
		mrpItemIter.close();
		
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	
	/**
	 * 查询中央工厂在途量
	 */
	public List<Map> queryCfOnTheWay(String itemId,String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		MrpItemIter mrpItemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:480^3*/

//  ************************************************************
//  #sql [myCtx] mrpItemIter = { WITH
//  			    temp AS
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_COUNT,
//  			            id.FORM_ID ref_form_id,
//  			            id.RECEIVE_COUNT
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_HEADER") h
//  			        INNER JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.STATUS IN ('已审核',
//  			                         '已入库')
//  			        AND s.IS_CURRENT = 1
//  			        INNER JOIN
//  			        :Com_("D_T1_PURCHASING_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        AND h.REQUESTER_ID = :branchId
//  			        AND d.ITEM_ID = :itemId
//  			        LEFT JOIN
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        ON
//  			            d.FORM_ID = ih.FORM_REF_ID
//  			        AND ih.AUDIT_TIME IS NOT NULL
//  			        LEFT JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID = id.FORM_ID
//  			        AND id.ITEM_ID = :itemId
//  			    )
//  			    ,
//  			    temp2 AS
//  			    (
//  			        SELECT
//  			            t.FORM_ID,
//  			            t.ITEM_ID,
//  			            t.ITEM_COUNT,
//  			            DECODE(SIGN(t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))),-1,0,t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0)))       ontheway ,
//  			            SUM(NVL(t.RECEIVE_COUNT,0))                received,
//  			            listagg('('||t.ref_form_id ||','||t.RECEIVE_COUNT ||')',',') within GROUP (ORDER BY
//  			            t.ITEM_COUNT) infos
//  			        FROM
//  			            temp t
//  			        GROUP BY
//  			            t.FORM_ID,
//  			            t.ITEM_ID,
//  			            t.ITEM_COUNT
//  			    )
//  			SELECT
//  			    DECODE(FORM_ID,NULL,'合计',ROWNUM)rownumber ,
//  			    t.*
//  			FROM
//  			    (
//  			        SELECT
//  			            t.FORM_ID,
//  			            t.ITEM_ID,
//  			            SUM( t.ITEM_COUNT) ITEM_COUNT,
//  			            SUM(t.ontheway)    mrp_count,
//  			            SUM(t.received)    received,
//  			            DECODE(LENGTH(t.infos),3,'',t.infos) infos
//  			        FROM
//  			            temp2 t
//  			        GROUP BY
//  			            GROUPING SETS( ( t.FORM_ID, t.ITEM_ID,t.ITEM_COUNT,t.ontheway,t.received,t.infos),NULL)
//  			        ORDER BY
//  			            DECODE(FORM_ID,NULL,-1,item_count) DESC) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_4 = branchId;
  String __sJT_5 = itemId;
  String __sJT_6 = Com_("D_T1_INPUT_HEADER");
  String __sJT_7 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_8 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPQueryBean_SJProfileKeys.getKey(0), 4);
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
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      mrpItemIter = new MrpItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:551^58*/
		List<Map> itemLst = processMrpIter(mrpItemIter);
		mrpItemIter.close();
		
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	private List<Map> processMrpIter(MrpItemIter itemIter) 
			throws SQLException {
		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map map = new HashMap();
			map.put("form_id", itemIter.form_id());
			map.put("item_id", itemIter.item_id());
			map.put("item_count", itemIter.item_count());
			map.put("mrp_count", itemIter.mrp_count());
			map.put("received", itemIter.received());
			map.put("infos", itemIter.infos());
			map.put("rownumber", itemIter.rownumber());
			itemLst.add(map);
		}
		
		return itemLst;
	}
	
}/*@lineinfo:generated-code*/class MRPQueryBean_SJProfileKeys 
{
  private static MRPQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new MRPQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private MRPQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.MRPQueryBean_SJProfile0");
  }
}
