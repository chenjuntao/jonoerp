/*@lineinfo:filename=PurchaseManageBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 17, 2014 by liyzh
 * Last edited on Dec 17, 2014 by liyzh
 * 
 * 说明： 物流中心采购单管理，包括统配、直配、越库
 */
package logic.module.lc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.SqlDateUtil;

public class PurchaseManageBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PurchaseManageBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class NodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public NodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
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

/*@lineinfo:user-code*//*@lineinfo:37^104*/
	
	/**
	 * 根据制单日期获取所有未入库的采购单，树形结构数据，直配单获取大单
	 */
	public List<Map> queryTree(String lcCode, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if ("unaudit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:51^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
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
//  						:query
//  			        AND h.REQUESTER_ID = :lcCode
//  					AND h.RECEIVER_ID IS NULL --直配小单除外
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  				SELECT
//  				    TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS id,
//  				    TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS name,
//  				    'root'                              AS parent,
//  				    ''                                  AS dtype,
//  				    ''                                  AS form_status
//  				FROM
//  				    purchase p
//  				GROUP BY
//  				    p.FORM_TIME  
//  			    ORDER BY
//  			    	p.FORM_TIME DESC )
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
//  			            SUBSTR(p.FORM_ID, 3) DESC )
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = lcCode;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseManageBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:103^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("dtype", nodeIter.dtype());
			node.put("status", nodeIter.form_status());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	/**
	 * 根据制单日期获取所有未入库的采购单，树形结构数据，直配单获取大单
	 */
	public List<Map> queryTree(java.util.Date startDate, java.util.Date endDate,String lcCode, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if ("unaudit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:131^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
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
//  						:query
//  			        AND h.REQUESTER_ID = :lcCode
//  					AND h.RECEIVER_ID IS NULL --直配小单除外
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  				SELECT
//  				    TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS id,
//  				    TO_CHAR( p.FORM_TIME, 'yyyy-MM-dd') AS name,
//  				    'root'                              AS parent,
//  				    ''                                  AS dtype,
//  				    ''                                  AS form_status
//  				FROM
//  				    purchase p
//  				    WHERE
//  			           (p.FORM_TIME >= :sDate or :sDate is null)
//  						AND (p.FORM_TIME <= :eDate or :eDate is null)
//  				GROUP BY
//  				    p.FORM_TIME  
//  			    ORDER BY
//  			    	p.FORM_TIME DESC )
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
//  			            SUBSTR(p.FORM_ID, 3) DESC )
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = lcCode;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseManageBean_SJProfileKeys.getKey(0), 1);
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
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:186^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("dtype", nodeIter.dtype());
			node.put("status", nodeIter.form_status());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	/**
	 * 中央工厂采购单 临时处理
	 */
	public List<Map> queryCFTree(String cfCode, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if ("unaudit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:214^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  			    purchase AS
//  			    (
//  			        SELECT DISTINCT
//  			            h.FORM_ID,
//  			            h.FORM_TIME,
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
//  			            :query
//  			        AND h.REQUESTER_ID = :cfCode
//  			        ORDER BY
//  			            h.FORM_ID
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
//  			            p.FORM_TIME DESC )
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
  String __sJT_3 = query;
  String __sJT_4 = cfCode;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseManageBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:266^43*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("dtype", nodeIter.dtype());
			node.put("status", nodeIter.form_status());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	/**
	 * 中央工厂采购单 临时处理
	 */
	public List<Map> queryCFTree(java.util.Date startDate, java.util.Date endDate,String cfCode, String queryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		if ("unaudit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:296^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  			    purchase AS
//  			    (
//  			        SELECT DISTINCT
//  			            h.FORM_ID,
//  			            h.FORM_TIME,
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
//  			            :query
//  			        AND h.REQUESTER_ID = :cfCode
//  			        ORDER BY
//  			            h.FORM_ID
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
//  				           (p.FORM_TIME >= :sDate or :sDate is null)
//  							AND (p.FORM_TIME <= :eDate or :eDate is null)
//  			        GROUP BY
//  			            p.FORM_TIME
//  			        ORDER BY
//  			            p.FORM_TIME DESC )
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
  String __sJT_3 = query;
  String __sJT_4 = cfCode;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseManageBean_SJProfileKeys.getKey(0), 3);
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
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:351^43*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("dtype", nodeIter.dtype());
			node.put("status", nodeIter.form_status());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	/*@lineinfo:generated-code*//*@lineinfo:366^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    receiver_idNdx = findColumn("receiver_id");
    receiverNdx = findColumn("receiver");
    receive_addressNdx = findColumn("receive_address");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_unit_priceNdx = findColumn("item_unit_price");
    item_countNdx = findColumn("item_count");
    pay_amtNdx = findColumn("pay_amt");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    supply_cycleNdx = findColumn("supply_cycle");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public String receive_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receive_addressNdx);
  }
  private int receive_addressNdx;
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
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
  public Double supply_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(supply_cycleNdx);
  }
  private int supply_cycleNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:370^42*/

	public List<Map> queryDirect(String bigFormId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:376^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    sh.FORM_ID,
//  			    sh.RECEIVER,
//  			    sh.RECEIVER_ID,
//  			    sh.RECEIVE_ADDRESS,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.ITEM_UNIT_PRICE,
//  			    d.RECEIVE_PRICE,
//  			    d.RECEIVE_AMT,
//  			    d.ITEM_COUNT,
//  			    d.PAY_AMT,
//  	            0.0 SUPPLY_CYCLE
//  			FROM
//  			:Com_("D_T1_PURCHASING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T1_PURCHASING_MAPPING") m
//  			ON
//  			    m.BIG_FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T1_PURCHASING_HEADER") sh
//  			ON
//  			    sh.FORM_ID = m.SMALL_FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T1_PURCHASING_DETAIL") d
//  			ON
//  			    d.FORM_ID = sh.FORM_ID
//  			WHERE
//  			    h.FORM_ID = :bigFormId
//  			ORDER BY
//  				d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T1_PURCHASING_MAPPING");
  String __sJT_3 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_4 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_5 = bigFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseManageBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:411^3*/
		List<Map> itemLst = processItemIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}

	public List<Map> queryDirect(String bigFormId,String lcCode)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:422^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    sh.FORM_ID,
//  			    sh.RECEIVER,
//  			    sh.RECEIVER_ID,
//  			    sh.RECEIVE_ADDRESS,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.ITEM_UNIT_PRICE,
//  			    d.RECEIVE_PRICE,
//  			    d.RECEIVE_AMT,
//  			    d.ITEM_COUNT,
//  			    d.PAY_AMT,
//  	            DECODE (ma.SUPPLY_CYCLE, NULL, ma.PRODUCTION_CYCLE,ma.SUPPLY_CYCLE) SUPPLY_CYCLE
//  			FROM
//  			:Com_("D_T1_PURCHASING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T1_PURCHASING_MAPPING") m
//  			ON
//  			    m.BIG_FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T1_PURCHASING_HEADER") sh
//  			ON
//  			    sh.FORM_ID = m.SMALL_FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T1_PURCHASING_DETAIL") d
//  			ON
//  			    d.FORM_ID = sh.FORM_ID
//      		LEFT JOIN
//  	        :Com_("D_V_MRP_AMOUNT_LC") ma
//  	        ON
//  	            ma.ITEM_ID = d.ITEM_ID
//  	        AND ma.BRANCH_ID = :lcCode
//  			WHERE
//  			    h.FORM_ID = :bigFormId
//  			ORDER BY
//  				d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T1_PURCHASING_MAPPING");
  String __sJT_3 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_4 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_5 = Com_("D_V_MRP_AMOUNT_LC");
  String __sJT_6 = lcCode;
  String __sJT_7 = bigFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseManageBean_SJProfileKeys.getKey(0), 5);
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
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:462^3*/
		List<Map> itemLst = processItemIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}

	private List<Map> processItemIter(ItemIter itemIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("formId", itemIter.form_id());
			detail.put("receiverId", itemIter.receiver_id());
			detail.put("receiver", itemIter.receiver());
			detail.put("receiveAddress", itemIter.receive_address());
			detail.put("itemId", itemIter.item_id());
			detail.put("itemName", itemIter.item_name());
			detail.put("itemCategory", itemIter.item_category());
			detail.put("itemDimension", itemIter.item_dimension());
			detail.put("itemSpecification", itemIter.item_specification());
			detail.put("itemCount", itemIter.item_count());
			detail.put("itemUnitPrice", itemIter.item_unit_price());
			detail.put("payAmt", itemIter.pay_amt());
			detail.put("receivePrice", itemIter.receive_price());
			detail.put("receiveAmt", itemIter.receive_amt());
			detail.put("supplyCycle", itemIter.supply_cycle());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	
	/*@lineinfo:generated-code*//*@lineinfo:495^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SmallFormIdIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SmallFormIdIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    samllFormIdNdx = findColumn("samllFormId");
  }
  public String samllFormId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(samllFormIdNdx);
  }
  private int samllFormIdNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:495^58*/
	public List<String> queryLittleFormIds(String bigFormId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SmallFormIdIter smallFormIdIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:500^3*/

//  ************************************************************
//  #sql [myCtx] smallFormIdIter = { SELECT
//  			    p.SMALL_FORM_ID samllFormId
//  		    FROM
//  		    	:Com_("D_T1_PURCHASING_MAPPING") p 
//  		    WHERE
//  		        p.BIG_FORM_ID = :bigFormId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_MAPPING");
  String __sJT_2 = bigFormId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseManageBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      smallFormIdIter = new SmallFormIdIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:506^37*/
		

		List<String> detailLst = new ArrayList<String>();
		while(smallFormIdIter.next()) {
			detailLst.add( smallFormIdIter.samllFormId());
		}
	
		smallFormIdIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	
	/**
	 * 通过采购单反查相应的要货单
	 */
	public List<Map> queryReference(String pFormId)
			throws NoPrivilegeException,SQLException,NoConnection {
		return null;
	}
}/*@lineinfo:generated-code*/class PurchaseManageBean_SJProfileKeys 
{
  private static PurchaseManageBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PurchaseManageBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PurchaseManageBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.PurchaseManageBean_SJProfile0");
  }
}
