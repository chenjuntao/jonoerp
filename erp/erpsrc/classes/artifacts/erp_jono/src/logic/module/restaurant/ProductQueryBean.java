/*@lineinfo:filename=ProductQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 24, 2015 by liyzh
 * Last edited on Aug 24, 2015 by liyzh
 * 
 * 说明： 餐厅查看出品，获取售卖价（优先查看餐厅所属品牌的价格，如果没有，则查询出品的默认价格）
 */
package logic.module.restaurant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.hq.config.item.vo.ItemVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ItemType;
import com.tanry.framework.util.constant.PriceType;

public class ProductQueryBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(ProductQueryBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

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
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_priceNdx = findColumn("item_price");
    brand_priceNdx = findColumn("brand_price");
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
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
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
  public Double brand_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(brand_priceNdx);
  }
  private int brand_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^68*/
	
	/**
	 * 根据名称或编码模糊查询，或者根据出品分类查询
	 */
	public List<ItemVO> query(String branchId, String priceType,String itemName, String categoryId)   
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		String query = " m.ITEM_ID LIKE '%" + itemName 
				+ "%' OR  m.ITEM_NAME LIKE '%" + itemName + "%'";
		if (!TextUtil.isEmpty(categoryId)) {
			query = " m.CATEGORY_ID = '" + categoryId + "'";
		}
		
		String itemType = ItemType.PRODUCT;
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:59^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME, 
//  			    m.QUERY_CODE,
//  			    m.CATEGORY_ID,
//  			    c.CATEGORY_NAME,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    p.ITEM_PRICE,
//  			    pb.ITEM_PRICE as brand_price
//  			FROM
//  			:Com_("d_t2_item_meta") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = :priceType
//  			LEFT JOIN
//  			    (
//  			        SELECT
//  			            p.ITEM_ID ,
//  			            p.ITEM_PRICE
//  			        FROM
//  			        :Com_("D_T2_BRAND_BRANCH") b
//  			        LEFT JOIN
//  			        :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            p.PRICE_TYPE = (b.BRAND_ID||'_default')
//  			        WHERE
//  			            b.BRANCH_ID = :branchId) pb
//  			ON
//  			    pb.item_id = m.ITEM_ID
//  			WHERE
//  			    m.ITEM_TYPE = :itemType
//              AND :query
//  			ORDER BY
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_meta");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = priceType;
  String __sJT_5 = Com_("D_T2_BRAND_BRANCH");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = branchId;
  String __sJT_8 = itemType;
  String __sJT_9 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductQueryBean_SJProfileKeys.getKey(0), 0);
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
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:101^3*/
		List<ItemVO> itemLst = new ArrayList<ItemVO>();
		while(itemIter.next()) {
			ItemVO item = new ItemVO();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setQueryCode(itemIter.query_code());
			item.setCategoryId(itemIter.category_id());
			item.setCategoryName(itemIter.category_name());
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			item.setSalePrice(itemIter.item_price());
			
			Double brandPrice = itemIter.brand_price();
			if (brandPrice != null) {
				item.setSalePrice(brandPrice);
			}
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class ProductQueryBean_SJProfileKeys 
{
  private static ProductQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ProductQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ProductQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.ProductQueryBean_SJProfile0");
  }
}
