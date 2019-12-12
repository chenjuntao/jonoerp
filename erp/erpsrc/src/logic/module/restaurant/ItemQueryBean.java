/*@lineinfo:filename=ItemQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 1, 2015 by liyzh
 * Last edited on Sep 1, 2015 by liyzh
 * 
 * 说明： 餐厅物料信息查询
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

public class ItemQueryBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(ItemQueryBean.class);

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
    itemIdNdx = findColumn("itemId");
    itemNameNdx = findColumn("itemName");
    itemNameEngNdx = findColumn("itemNameEng");
    itemTypeNdx = findColumn("itemType");
    categoryIdNdx = findColumn("categoryId");
    categoryNameNdx = findColumn("categoryName");
    itemDimensionNdx = findColumn("itemDimension");
    queryCodeNdx = findColumn("queryCode");
    itemSpecificationNdx = findColumn("itemSpecification");
    itemPicNdx = findColumn("itemPic");
    boxTypeNdx = findColumn("boxType");
    itemUnitPriceNdx = findColumn("itemUnitPrice");
  }
  public String itemId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemIdNdx);
  }
  private int itemIdNdx;
  public String itemName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemNameNdx);
  }
  private int itemNameNdx;
  public String itemNameEng() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemNameEngNdx);
  }
  private int itemNameEngNdx;
  public String itemType() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemTypeNdx);
  }
  private int itemTypeNdx;
  public String categoryId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(categoryIdNdx);
  }
  private int categoryIdNdx;
  public String categoryName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(categoryNameNdx);
  }
  private int categoryNameNdx;
  public String itemDimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemDimensionNdx);
  }
  private int itemDimensionNdx;
  public String queryCode() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(queryCodeNdx);
  }
  private int queryCodeNdx;
  public String itemSpecification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemSpecificationNdx);
  }
  private int itemSpecificationNdx;
  public String itemPic() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemPicNdx);
  }
  private int itemPicNdx;
  public String boxType() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(boxTypeNdx);
  }
  private int boxTypeNdx;
  public Double itemUnitPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemUnitPriceNdx);
  }
  private int itemUnitPriceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^24*/

	/**
	 * 餐厅物料信息查询
	 */
	public List<Map> query(String categoryId, String priceType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<Map> headerLst = new ArrayList<Map>();
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID itemId,
//  			    m.ITEM_NAME itemName,
//  			    m.ITEM_NAME_ENG itemNameEng,
//  			    m.ITEM_TYPE itemType,
//  			    m.CATEGORY_ID categoryId,
//  			    NVL(c.CATEGORY_NAME,'') categoryName,
//  			    m.ITEM_DIMENSION itemDimension,
//  			    m.QUERY_CODE queryCode,
//  			    m.ITEM_SPECIFICATION itemSpecification,
//  			    m.ITEM_PIC itemPic,
//  			    m.BOX_TYPE boxType,
//  			    nvl(p.item_price,0) itemUnitPrice
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    m.item_id = p.item_id
//  			    AND p.PRICE_TYPE = :priceType
//  			    AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  				trim(m.CATEGORY_ID) = c.CATEGORY_ID
//  			WHERE
//  	            trim(m.CATEGORY_ID) = :categoryId
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
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = categoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemQueryBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:84^3*/
		headerLst = processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	
	/**
	 * 根据物料id餐厅物料信息查询
	 */
	public List<Map> queryByName(String itemName, String priceType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<Map> headerLst = new ArrayList<Map>();
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		itemName = "%" + itemName + "%";
		/*@lineinfo:generated-code*//*@lineinfo:101^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID itemId,
//  			    m.ITEM_NAME itemName,
//  			    m.ITEM_NAME_ENG itemNameEng,
//  			    m.ITEM_TYPE itemType,
//  			    m.CATEGORY_ID categoryId,
//  			    NVL(c.CATEGORY_NAME,'') categoryName,
//  			    m.ITEM_DIMENSION itemDimension,
//  			    m.QUERY_CODE queryCode,
//  			    m.ITEM_SPECIFICATION itemSpecification,
//  			    m.ITEM_PIC itemPic,
//  			    m.BOX_TYPE boxType,
//  			    nvl(p.item_price,0) itemUnitPrice
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    m.item_id = p.item_id
//  			    AND p.PRICE_TYPE = :priceType
//  			    AND p.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  				trim(m.CATEGORY_ID) = c.CATEGORY_ID
//  			WHERE
//  			    1=1
//  			AND (
//  			        m.ITEM_ID LIKE :itemName
//  			    OR  m.ITEM_NAME LIKE :itemName)
//  			AND m.ITEM_TYPE IN ('RAW',
//  			                    'SEMIS')
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
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = itemName;
  String __sJT_6 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemQueryBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:134^3*/
		headerLst = processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	private List<Map> processIter(ItemIter itemIter) 
			throws SQLException {
		List<Map> itemLst = new ArrayList<Map>();
		
		while(itemIter.next()) {
			Map item = new HashMap();
			item.put("itemId", itemIter.itemId());
			item.put("itemName", itemIter.itemName());
			item.put("itemNameEng", itemIter.itemNameEng());
			item.put("itemType", itemIter.itemType());
			item.put("categoryId", itemIter.categoryId());
			item.put("categoryName", itemIter.categoryName());
			item.put("itemDimension", itemIter.itemDimension());
			item.put("queryCode", itemIter.queryCode());
			item.put("itemSpecification", itemIter.itemSpecification());
			item.put("itemPic", itemIter.itemPic());
			item.put("boxType", itemIter.boxType());
			item.put("itemUnitPrice", itemIter.itemUnitPrice());
			
			itemLst.add(item);
		}
		return itemLst;
	}
}/*@lineinfo:generated-code*/class ItemQueryBean_SJProfileKeys 
{
  private static ItemQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ItemQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ItemQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.ItemQueryBean_SJProfile0");
  }
}
