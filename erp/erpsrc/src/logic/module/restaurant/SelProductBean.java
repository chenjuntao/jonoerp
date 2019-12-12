/*@lineinfo:filename=SelProductBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 30, 2015 by liyzh
 * Last edited on Apr 30, 2015 by liyzh
 * 
 * 说明：生成报损单时选择出品（餐厅）或半成品（央厂）
 */
package logic.module.restaurant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import com.tanry.framework.util.SqlDateUtil;
import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import vo.restaurant.RequestItemVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.ItemType;

public class SelProductBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(SelProductBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

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
    item_specificationNdx = findColumn("item_specification");
    item_priceNdx = findColumn("item_price");
    item_unit_priceNdx = findColumn("item_unit_price");
    itemCategoryNameNdx = findColumn("itemCategoryName");
    workshopNdx = findColumn("workshop");
    productionCycleNdx = findColumn("productionCycle");
    businessDateNdx = findColumn("businessDate");
    completeTimeNdx = findColumn("completeTime");
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public String itemCategoryName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemCategoryNameNdx);
  }
  private int itemCategoryNameNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
  public Double productionCycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(productionCycleNdx);
  }
  private int productionCycleNdx;
  public Date businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(businessDateNdx);
  }
  private int businessDateNdx;
  public Date completeTime() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(completeTimeNdx);
  }
  private int completeTimeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:49^2*/
	
	/**
	 * 生成报损单时选择出品（餐厅）或半成品（央厂），获取成本价
	 */
	public List<RequestItemVO> queryProduct(String itemName, String itemType ,String priceType)   
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = " m.ITEM_TYPE = '" + itemType + "'";
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:61^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME,
//  			    m.QUERY_CODE,
//  			    m.CATEGORY_ID AS item_category,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  		    t.cost_price                                  	AS ITEM_PRICE,
//  		    p3.ITEM_PRICE                    				AS ITEM_UNIT_PRICE,
//  			    c.CATEGORY_NAME                                  itemCategoryName,
//  			    NVL(fw.WORKSHOP,fws.WORKSHOP)                    workshop,
//  			    bd.BUSINESS_DATE                                 businessDate,
//  			    NVL(pc.PRODUCTION_CYCLE,0)                       productionCycle,
//  			    bd.BUSINESS_DATE + NVL(pc.PRODUCTION_CYCLE,0)    completeTime
//  			FROM
//  				:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            ROUND(SUM(t.ITEM_GROSS_COUNT*p.ITEM_PRICE), 2) AS cost_price
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        LEFT JOIN
//  			        :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            p.ITEM_ID = t.ITEM_ID
//  			        AND p.PRICE_TYPE = 'PURCHASE'
//  			        GROUP BY
//  			            t.THERAPY_ID) t
//  			ON
//  			    t.THERAPY_ID = m.ITEM_ID
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") bd
//  			ON
//  			    bd.BRANCH_ID = 'F00'
//  			LEFT JOIN
//  				:Com_("D_T2_PRODUCTION_CYCLE") pc
//  			ON
//  			    pc.ITEM_ID = m.item_id
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p3
//  	    	ON
//  	    	    p3.PRICE_TYPE = :priceType
//  	    	AND p3.ITEM_ID = m.ITEM_ID
//  	    	AND p3.IS_CURRENT =1
//  			LEFT JOIN
//  				:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    m.item_id = u.ITEM_ID
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    m.CATEGORY_ID = c.CATEGORY_ID
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_WORKSHOP") wp
//  			ON
//  			    m.ITEM_ID=wp.ITEM_ID
//  			LEFT JOIN
//  				:Com_("D_T2_FACTORY_WORKSHOP") fw
//  			ON
//  			    fw.WORK_ORDER_ID=wp.WORK_ORDER_ID
//  			LEFT JOIN
//  				:Com_("D_T2_FACTORY_WORKSHOP") fws
//  			ON
//  			    fws.FACTORY_ID ='F00'
//  			AND fws.PRIORITY ='0'
//  		WHERE
//  			:query
//  			AND m.ENABLED = 1
//  	        AND (
//  	                m.ITEM_ID LIKE :itemName
//  	            OR  m.ITEM_NAME LIKE :itemName)
//  				ORDER BY
//  				    WORKSHOP
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_THERAPY");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = priceType;
  String __sJT_8 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_9 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_10 = Com_("D_T2_ITEM_WORKSHOP");
  String __sJT_11 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_12 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_13 = query;
  String __sJT_14 = itemName;
  String __sJT_15 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelProductBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:137^3*/
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
			item.setItemUnitPrice(itemIter.item_unit_price());
			item.setItemCategoryName(itemIter.itemCategoryName());
			item.setWorkshop(itemIter.workshop());
			item.setProductionCycle(itemIter.productionCycle());
			item.setBusinessDate(SqlDateUtil.getUtilDate(itemIter.businessDate()));
			item.setCompleteTime(SqlDateUtil.getUtilDate(itemIter.completeTime()));
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class SelProductBean_SJProfileKeys 
{
  private static SelProductBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SelProductBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SelProductBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.SelProductBean_SJProfile0");
  }
}
