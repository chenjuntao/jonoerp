/*@lineinfo:filename=SelTherapyItemBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 16, 2015 by liyzh
 * Last edited on Mar 16, 2015 by liyzh
 * 
 * 说明： 按配方表的需要查询原料和半成品
 */
package logic.module.hq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.hq.config.item.vo.TherapyItem;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.ItemType;

public class SelTherapyItemBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SelTherapyItemBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:34^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_typeNdx = findColumn("item_type");
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
    recipe_unitNdx = findColumn("recipe_unit");
    recipe_factorNdx = findColumn("recipe_factor");
    item_unit_priceNdx = findColumn("item_unit_price");
    receive_priceNdx = findColumn("receive_price");
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
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public String recipe_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(recipe_unitNdx);
  }
  private int recipe_unitNdx;
  public Double recipe_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(recipe_factorNdx);
  }
  private int recipe_factorNdx;
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

/*@lineinfo:user-code*//*@lineinfo:40^117*/
	
	public List<TherapyItem> queryItem(String itemType, String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = " ITEM_TYPE = '" + itemType + "'";
		if (ItemType.R_S.equals(itemType)) {
			query = " (ITEM_TYPE = '" + ItemType.RAW 
					+ "' OR  ITEM_TYPE = '" + ItemType.SEMIS
					+ "' OR  ITEM_TYPE = '" + ItemType.SELFSEMIS + "')";
		}
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:54^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.*,
//  			    u.RECIPE_UNIT,
//  			    u.RECIPE_FACTOR,
//  			    p.ITEM_PRICE  AS ITEM_UNIT_PRICE,
//  			    p2.ITEM_PRICE AS RECEIVE_PRICE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    m.ITEM_ID = u.ITEM_ID
//      		LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID =  m.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.ITEM_ID = m.ITEM_ID
//  			AND p2.PRICE_TYPE = 'PURCHASE'
//  			AND p2.IS_CURRENT = 1
//  			WHERE
//  				:query
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = query;
  String __sJT_6 = itemName;
  String __sJT_7 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelTherapyItemBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:84^3*/

		List<TherapyItem> itemLst = new ArrayList<TherapyItem>();
		while (itemIter.next()) {
			TherapyItem item = new TherapyItem();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setItemType(itemIter.item_type());
			item.setCategoryId(itemIter.category_id());
			item.setRecipeUnit(itemIter.recipe_unit());
			item.setItemDimension(itemIter.item_dimension());
			item.setRecipeFactor(itemIter.recipe_factor());
			item.setQueryCode(itemIter.query_code());
			item.setItemSpecification(itemIter.item_specification());
			item.setBenchmarkPrice(itemIter.item_unit_price());
			item.setPurchasePrice(itemIter.receive_price());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class SelTherapyItemBean_SJProfileKeys 
{
  private static SelTherapyItemBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SelTherapyItemBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SelTherapyItemBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.hq.SelTherapyItemBean_SJProfile0");
  }
}
