/*@lineinfo:filename=SelMaterialBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 26, 2015 by liyzh
 * Last edited on Mar 26, 2015 by liyzh
 * 
 * 说明： 餐厅要货单选择原料、半成品
 */
package logic.module.restaurant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import vo.restaurant.RequestItemVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.ItemType;

public class SelMaterialBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(SelMaterialBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:34^2*/

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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    query_codeNdx = findColumn("query_code");
    item_categoryNdx = findColumn("item_category");
    item_dimensionNdx = findColumn("item_dimension");
    shelf_nameNdx = findColumn("shelf_name");
    item_specificationNdx = findColumn("item_specification");
    item_priceNdx = findColumn("item_price");
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
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String shelf_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_nameNdx);
  }
  private int shelf_nameNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^21*/
	
	/**
	 * 餐厅要货单选择原料、半成品
	 */
	public List<RequestItemVO> queryItem(String itemType, String itemName, String priceType)   
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
		/*@lineinfo:generated-code*//*@lineinfo:59^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME, m.QUERY_CODE,
//  			    m.CATEGORY_ID AS item_category,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    p.ITEM_PRICE,
//  			    s.SHELF_NAME
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID   AND p.PRICE_TYPE = :priceType
//  			LEFT JOIN
//  				:Com_("D_T2_SHELF_ITEM") si 
//  			ON
//  			    m.ITEM_ID = si.ITEM_ID    AND si.PRIORITY = 0
//  			LEFT JOIN
//  				:Com_("D_T2_SHELF") s 
//  			ON
//  			    si.SHELF_ID = s.SHELF_ID
//  			WHERE
//  				:query
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//              AND m.ENABLED = 1
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
  String __sJT_4 = Com_("D_T2_SHELF_ITEM");
  String __sJT_5 = Com_("D_T2_SHELF");
  String __sJT_6 = query;
  String __sJT_7 = itemName;
  String __sJT_8 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelMaterialBean_SJProfileKeys.getKey(0), 0);
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
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:90^3*/
		List<RequestItemVO> itemLst = new ArrayList<RequestItemVO>();
		while(itemIter.next()) {
			RequestItemVO item = new RequestItemVO();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setQueryCode(itemIter.query_code());
			item.setItemCategory(itemIter.item_category());
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			item.setItemPrice(itemIter.item_price());
			item.setShelfName(itemIter.shelf_name());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:109^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemInventoryIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemInventoryIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    query_codeNdx = findColumn("query_code");
    item_categoryNdx = findColumn("item_category");
    shelf_nameNdx = findColumn("shelf_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_priceNdx = findColumn("item_price");
    inventoryNdx = findColumn("inventory");
    item_typeNdx = findColumn("item_type");
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
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public String shelf_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_nameNdx);
  }
  private int shelf_nameNdx;
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
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double inventory() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventoryNdx);
  }
  private int inventoryNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:115^83*/
	
	/**
	 * 报损单根据库存自选原料（包括半成品），获取标准价
	 */
	public List<RequestItemVO> queryItemInventory(String itemName, String storageId ,String priceType)   
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = " (ITEM_TYPE = '" + ItemType.RAW + "' OR  ITEM_TYPE = '" + ItemType.SEMIS + "')";
		
		DefaultContext myCtx = getDefaultContext();
		ItemInventoryIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:127^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME, m.QUERY_CODE,
//  			    m.CATEGORY_ID AS item_category,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    m.ITEM_TYPE,
//  			    p.ITEM_PRICE,
//  			    s.SHELF_NAME,
//  			    round(s.ITEM_COUNT,2) as inventory
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = :priceType
//  			LEFT JOIN
//  			:Com_("D_T2_STORAGE") s
//  			ON
//  				s.ITEM_ID = m.ITEM_ID	AND s.STORAGE_ID = :storageId
//  			LEFT JOIN
//  			:Com_("D_T2_SHELF_ITEM") si 
//  			ON
//  			    m.ITEM_ID = si.ITEM_ID    AND si.PRIORITY = 0
//  			LEFT JOIN
//  				:Com_("D_T2_SHELF") s 
//  			ON
//  			    si.SHELF_ID = s.SHELF_ID
//  			WHERE
//  				:query
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//              AND m.ENABLED = 1
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
  String __sJT_4 = Com_("D_T2_STORAGE");
  String __sJT_5 = storageId;
  String __sJT_6 = Com_("D_T2_SHELF_ITEM");
  String __sJT_7 = Com_("D_T2_SHELF");
  String __sJT_8 = query;
  String __sJT_9 = itemName;
  String __sJT_10 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelMaterialBean_SJProfileKeys.getKey(0), 1);
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
      itemIter = new ItemInventoryIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:165^3*/
		List<RequestItemVO> itemLst = new ArrayList<RequestItemVO>();
		while(itemIter.next()) {
			RequestItemVO item = new RequestItemVO();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setQueryCode(itemIter.query_code());
			item.setItemCategory(itemIter.item_category());
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			item.setItemPrice(itemIter.item_price());
			item.setInventory(itemIter.inventory());
			item.setShelfName(itemIter.shelf_name());
			item.setItemType(itemIter.item_type());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/**
	 * 报损单根据库存自选原料（不包括半成品），获取标准价
	 */
	public List<RequestItemVO> queryInventory(String itemName, String storageId,String priceType)   
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query =  " (ITEM_TYPE = '" + ItemType.RAW + "')";
		
		DefaultContext myCtx = getDefaultContext();
		ItemInventoryIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:196^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME, m.QUERY_CODE,
//  			    m.CATEGORY_ID AS item_category,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    m.ITEM_TYPE,
//  			    p.ITEM_PRICE,
//  			    '' SHELF_NAME,
//  			    round(s.ITEM_COUNT,2) as inventory
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = :priceType
//  			LEFT JOIN
//  			:Com_("D_T2_STORAGE") s
//  			ON
//  			    s.ITEM_ID = m.ITEM_ID
//  			AND s.STORAGE_ID = :storageId
//  			WHERE
//  				:query
//  			AND m.ENABLED = 1
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
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
  String __sJT_4 = Com_("D_T2_STORAGE");
  String __sJT_5 = storageId;
  String __sJT_6 = query;
  String __sJT_7 = itemName;
  String __sJT_8 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelMaterialBean_SJProfileKeys.getKey(0), 2);
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
      itemIter = new ItemInventoryIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:227^3*/
		List<RequestItemVO> itemLst = new ArrayList<RequestItemVO>();
		while(itemIter.next()) {
			RequestItemVO item = new RequestItemVO();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setQueryCode(itemIter.query_code());
			item.setItemCategory(itemIter.item_category());
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			item.setItemPrice(itemIter.item_price());
			item.setInventory(itemIter.inventory());
			item.setItemType(itemIter.item_type());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class SelMaterialBean_SJProfileKeys 
{
  private static SelMaterialBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SelMaterialBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SelMaterialBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.SelMaterialBean_SJProfile0");
  }
}
