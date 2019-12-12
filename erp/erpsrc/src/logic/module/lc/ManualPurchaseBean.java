/*@lineinfo:filename=ManualPurchaseBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 19, 2014 by liyzh
 * Last edited on Dec 19, 2014 by liyzh
 * 
 * 说明： 物流中心手动生成采购单
 */
package logic.module.lc;

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
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.constant.ItemType;

public class ManualPurchaseBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ManualPurchaseBean.class);
	
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
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    category_idNdx = findColumn("category_id");
    item_unit_priceNdx = findColumn("item_unit_price");
    receive_priceNdx = findColumn("receive_price");
  }
  public String provider_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_idNdx);
  }
  private int provider_idNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
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
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double receive_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_priceNdx);
  }
  private int receive_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^95*/
	
	public List<Map> queryMaterial(String lcCode, String categoryId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:44^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    m.*,
//  			    s.SUPPLIER_ID AS PROVIDER_ID,
//  			    b.BRANCH_NAME AS PROVIDER,
//  			    p.ITEM_PRICE  AS ITEM_UNIT_PRICE,
//  			    p2.ITEM_PRICE AS RECEIVE_PRICE
//  			FROM
//  				:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			ON
//  			    s.BRANCH_ID = :lcCode
//  			AND s.ITEM_ID = m.ITEM_ID
//  			AND s.PRIORITY = 0
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = s.SUPPLIER_ID
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.ITEM_ID = m.ITEM_ID
//  			AND p2.PRICE_TYPE = 'PURCHASE'
//  			AND p2.IS_CURRENT = 1
//  			WHERE
//  			    m.CATEGORY_ID = :categoryId AND m.ENABLED = 1
//  			ORDER BY
//  			    s.SUPPLIER_ID,
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = lcCode;
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = categoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ManualPurchaseBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:80^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> queryItem(String lcCode, String itemName)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = " (ITEM_TYPE = '" + ItemType.RAW + "' OR  ITEM_TYPE = '" + ItemType.SEMIS + "')";
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:93^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    m.*,
//  			    s.SUPPLIER_ID AS PROVIDER_ID,
//  			    b.BRANCH_NAME AS PROVIDER,
//  			    p.ITEM_PRICE  AS ITEM_UNIT_PRICE,
//  			    p2.ITEM_PRICE AS RECEIVE_PRICE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			ON
//  			    s.BRANCH_ID = :lcCode
//  			AND s.ITEM_ID = m.ITEM_ID
//  			AND s.PRIORITY=0
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = s.SUPPLIER_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.ITEM_ID = m.ITEM_ID
//  			AND p2.PRICE_TYPE = 'PURCHASE'
//  			AND p2.IS_CURRENT = 1
//  			WHERE
//  					:query AND m.ENABLED = 1
//  	            AND (
//  	                    m.ITEM_ID LIKE :itemName
//  	                OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    s.SUPPLIER_ID,
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = lcCode;
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = query;
  String __sJT_8 = itemName;
  String __sJT_9 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ManualPurchaseBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:132^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> queryBySupplier(String lcCode, String supplierId,String itemName)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = " (ITEM_TYPE = '" + ItemType.RAW + "' OR  ITEM_TYPE = '" + ItemType.SEMIS + "')";
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:145^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    m.*,
//  			    s.SUPPLIER_ID AS PROVIDER_ID,
//  			    b.BRANCH_NAME AS PROVIDER,
//  			    p.ITEM_PRICE  AS ITEM_UNIT_PRICE,
//  			    NVL(p2.ITEM_PRICE, p3.ITEM_PRICE) AS RECEIVE_PRICE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			INNER JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			ON
//  			    s.BRANCH_ID = :lcCode
//      		And s.SUPPLIER_ID = :supplierId
//  			AND s.ITEM_ID = m.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = :supplierId
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.ITEM_ID = m.ITEM_ID
//  	    	AND	p2.PRICE_TYPE = 'SUPPLIER'
//  			AND p2.SUPPLIER_ID = :supplierId
//  			AND p2.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p3
//  			ON
//  			    p3.ITEM_ID = m.ITEM_ID
//  			AND p3.PRICE_TYPE = 'PURCHASE'
//  			AND p3.IS_CURRENT = 1
//  			WHERE
//  					:query
//  	            AND (
//  	                    m.ITEM_ID LIKE :itemName
//  	                OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    s.SUPPLIER_ID,
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = lcCode;
  String __sJT_4 = supplierId;
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = supplierId;
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = Com_("D_T2_ITEM_PRICE");
  String __sJT_9 = supplierId;
  String __sJT_10 = Com_("D_T2_ITEM_PRICE");
  String __sJT_11 = query;
  String __sJT_12 = itemName;
  String __sJT_13 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ManualPurchaseBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:191^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> queryCFItem(String lcCode, String itemName)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = "ITEM_TYPE = '" + ItemType.RAW + "'";
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:204^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    m.*,
//  			    s.SUPPLIER_ID AS PROVIDER_ID,
//  			    b.BRANCH_NAME AS PROVIDER,
//  			    p.ITEM_PRICE  AS ITEM_UNIT_PRICE,
//  			    p2.ITEM_PRICE AS RECEIVE_PRICE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			ON
//  			    s.BRANCH_ID = :lcCode
//  			AND s.ITEM_ID = m.ITEM_ID
//  			AND s.PRIORITY = 0
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = s.SUPPLIER_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.ITEM_ID = m.ITEM_ID
//  			AND p2.PRICE_TYPE = 'PURCHASE'
//  			AND p2.IS_CURRENT = 1
//  			WHERE
//  					:query
//  	            AND (
//  	                    m.ITEM_ID LIKE :itemName
//  	                OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    s.SUPPLIER_ID,
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = lcCode;
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = query;
  String __sJT_8 = itemName;
  String __sJT_9 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ManualPurchaseBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:243^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> queryCFItem(String lcCode,String supplierId, String itemName)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = "ITEM_TYPE = '" + ItemType.RAW + "'";
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:256^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    m.*,
//  			    s.SUPPLIER_ID AS PROVIDER_ID,
//  			    b.BRANCH_NAME AS PROVIDER,
//  			    p.ITEM_PRICE  AS ITEM_UNIT_PRICE,
//  			    p2.ITEM_PRICE AS RECEIVE_PRICE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			INNER JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			ON
//  			    s.BRANCH_ID = :lcCode
//  		    AND s.SUPPLIER_ID =:supplierId
//  			AND s.ITEM_ID = m.ITEM_ID
//  			AND s.PRIORITY = 0
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = :supplierId
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.ITEM_ID = m.ITEM_ID
//  			AND p2.PRICE_TYPE = 'PURCHASE'
//  			AND p2.IS_CURRENT = 1
//  			WHERE
//  					:query
//  	            AND (
//  	                    m.ITEM_ID LIKE :itemName
//  	                OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    s.SUPPLIER_ID,
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = lcCode;
  String __sJT_4 = supplierId;
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = supplierId;
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = Com_("D_T2_ITEM_PRICE");
  String __sJT_9 = query;
  String __sJT_10 = itemName;
  String __sJT_11 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ManualPurchaseBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:296^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<Map> processDetailIter(DetailIter detailIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("providerId", detailIter.provider_id());
			detail.put("provider", detailIter.provider());
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemCategory", detailIter.category_id());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("itemSpecification", detailIter.item_specification());
			detail.put("itemUnitPrice", detailIter.item_unit_price());
			detail.put("receivePrice", detailIter.receive_price());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class ManualPurchaseBean_SJProfileKeys 
{
  private static ManualPurchaseBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ManualPurchaseBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ManualPurchaseBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.ManualPurchaseBean_SJProfile0");
  }
}
