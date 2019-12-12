/*@lineinfo:filename=ManualDistributionBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 21, 2014 by liyzh
 * Last edited on Dec 21, 2014 by liyzh
 * 
 * 说明： 物流中心手动生成配送单
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
import com.tanry.framework.util.constant.PriceType;

public class ManualDistributionBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ManualDistributionBean.class);
	
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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    category_idNdx = findColumn("category_id");
    item_unit_priceNdx = findColumn("item_unit_price");
    delivery_unitNdx = findColumn("delivery_unit");
    delivery_factorNdx = findColumn("delivery_factor");
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
  public String delivery_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_unitNdx);
  }
  private int delivery_unitNdx;
  public Double delivery_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delivery_factorNdx);
  }
  private int delivery_factorNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^48*/
	
	public List<Map> queryMaterial(String categoryId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String priceType = PriceType.BENCHMARK;
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    m.*,
//  			    p.ITEM_PRICE AS ITEM_UNIT_PRICE,
//  			    u.DELIVERY_UNIT,
//  			    u.DELIVERY_FACTOR
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = :priceType
//  			AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = m.ITEM_ID
//  			WHERE
//  			    m.CATEGORY_ID = :categoryId
//  			ORDER BY
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = priceType;
  String __sJT_4 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_5 = categoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ManualDistributionBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:67^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	
	public List<Map> queryItem(String itemName)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String priceType = PriceType.BENCHMARK;
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:81^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    m.*,
//  			    p.ITEM_PRICE AS ITEM_UNIT_PRICE,
//  			    u.DELIVERY_UNIT,
//  			    u.DELIVERY_FACTOR
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = :priceType
//  			AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = m.ITEM_ID
//  			WHERE
//  			1=1 AND (
//  	                    m.ITEM_ID LIKE :itemName
//  	                OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = priceType;
  String __sJT_4 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_5 = itemName;
  String __sJT_6 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ManualDistributionBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:105^3*/
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
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemCategory", detailIter.category_id());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("itemSpecification", detailIter.item_specification());
			detail.put("itemUnitPrice", detailIter.item_unit_price());
			detail.put("deliveryUnit", detailIter.delivery_unit());
			detail.put("unitConvertFactor", detailIter.delivery_factor());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class ManualDistributionBean_SJProfileKeys 
{
  private static ManualDistributionBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ManualDistributionBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ManualDistributionBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.ManualDistributionBean_SJProfile0");
  }
}
