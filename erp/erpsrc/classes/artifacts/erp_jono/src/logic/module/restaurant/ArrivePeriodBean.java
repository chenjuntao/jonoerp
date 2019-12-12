/*@lineinfo:filename=ArrivePeriodBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Oct 28, 2014 by liyzh
 * Last edited on Oct 28, 2014 by liyzh
 * 
 * 说明： 到货周期查询
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

import pojo.store.ItemMeta;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchCode;

public class ArrivePeriodBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ArrivePeriodBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class PeriodIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PeriodIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    periodNdx = findColumn("period");
  }
  public Integer period() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(periodNdx);
  }
  private int periodNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^49*/
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

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
    item_name_engNdx = findColumn("item_name_eng");
    item_typeNdx = findColumn("item_type");
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
    item_picNdx = findColumn("item_pic");
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
  public String item_name_eng() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_name_engNdx);
  }
  private int item_name_engNdx;
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
  public String item_pic() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_picNdx);
  }
  private int item_picNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:45^19*/
	
	/**
	 * 统配方式时查询相应的可能的到货周期
	 * 中央工厂生产周期或者供应商供货周期+物流中心配送周期
	 */
	public List<Integer> queryUnified() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		PeriodIter periodIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:55^3*/

//  ************************************************************
//  #sql [myCtx] periodIter = { WITH
//  			    pc AS
//  			    (
//  			    	SELECT DISTINCT
//  			    	    period
//  			    	FROM
//  			    	    (-- 统配既可能是向中央工厂采购，也可能是向供应商
//  			    	        SELECT DISTINCT
//  			    	            PRODUCTION_CYCLE period
//  			    	        FROM
//  			    	        :Com_("D_T2_PRODUCTION_CYCLE")
//  			    	        UNION ALL
//  			    	        SELECT DISTINCT
//  			    	        	s.SUPPLY_CYCLE period
//  			    	        FROM
//  			    	        :Com_("D_T2_SUPPLY_CYCLE") s)
//  			    	ORDER BY
//  			    	    period
//  			    )
//  			    ,
//  			    dc AS
//  			    (
//  			        SELECT DISTINCT
//  			            DELIVERY_CYCLE
//  			        FROM
//  			        :Com_("D_T2_DELIVERY_REGION")
//  			        ORDER BY
//  			            DELIVERY_CYCLE
//  			    )
//  			SELECT DISTINCT
//  			    dc.DELIVERY_CYCLE + pc.period AS period
//  			FROM
//  			    dc,
//  			    pc
//  			ORDER BY
//  			    1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_2 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_3 = Com_("D_T2_DELIVERY_REGION");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrivePeriodBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      periodIter = new PeriodIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:92^3*/
		List<Integer> periodLst = new ArrayList<Integer>();
		while(periodIter.next()) {
			periodLst.add(periodIter.period());
		}
		periodIter.close();
		closeDefaultContext(myCtx);
		return periodLst;
	}
	
	/**
	 * 直配方式时查询相应的可能的到货周期
	 * 供应商供货周期
	 */
	public List<Integer> queryDirect() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		PeriodIter periodIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:110^3*/

//  ************************************************************
//  #sql [myCtx] periodIter = { SELECT DISTINCT
//  		    	s.SUPPLY_CYCLE period
//  		    FROM
//  		    :Com_("D_T2_SUPPLY_CYCLE") s
//  	    	ORDER BY
//  	    	    1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLY_CYCLE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrivePeriodBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      periodIter = new PeriodIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:117^3*/
		List<Integer> periodLst = new ArrayList<Integer>();
		while(periodIter.next()) {
			periodLst.add(periodIter.period());
		}
		periodIter.close();
		closeDefaultContext(myCtx);
		return periodLst;
	}
	
	/**
	 * 越库方式时查询相应的可能的到货周期
	 * 供应商供货周期+物流中心配送周期
	 */
	public List<Integer> queryCross() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		PeriodIter periodIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:135^3*/

//  ************************************************************
//  #sql [myCtx] periodIter = { WITH
//  			    sc AS
//  			    (
//  			    	SELECT DISTINCT
//  			        	s.SUPPLY_CYCLE period
//  			        FROM
//  			        :Com_("D_T2_SUPPLY_CYCLE") s
//  			    )
//  			    ,
//  			    dc AS
//  			    (
//  			        SELECT DISTINCT
//  			            DELIVERY_CYCLE
//  			        FROM
//  			        :Com_("D_T2_DELIVERY_REGION")
//  			        ORDER BY
//  			            DELIVERY_CYCLE
//  			    )
//  			SELECT DISTINCT
//  			    dc.DELIVERY_CYCLE+ sc.period AS period
//  			FROM
//  			    dc,
//  			    sc
//  			ORDER BY
//  			    1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_2 = Com_("D_T2_DELIVERY_REGION");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrivePeriodBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      periodIter = new PeriodIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:161^3*/
		List<Integer> periodLst = new ArrayList<Integer>();
		while(periodIter.next()) {
			periodLst.add(periodIter.period());
		}
		periodIter.close();
		closeDefaultContext(myCtx);
		return periodLst;
	}
	
	/**
	 * 根据区域和配送方式查询匹配的原材料
	 */
	public List<ItemMeta> queryItem(String regionId, String deliveryType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:178^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    i.*
//  			FROM
//  			:Com_("D_T2_DELIVERY_TYPE") t,
//  			:Com_("D_T2_ITEM_META") i
//  			WHERE
//  			    i.ITEM_ID = t.ITEM_ID
//  			AND t.DELIVERY_TYPE = :deliveryType
//  			AND t.REGION_ID = :regionId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = deliveryType;
  String __sJT_4 = regionId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrivePeriodBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:188^3*/
		List<ItemMeta> itemLst = new ArrayList<ItemMeta>();
		while(itemIter.next()) {
			ItemMeta item = new ItemMeta();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setItemNameEng(itemIter.item_name_eng());
			item.setItemType(itemIter.item_type());
			item.setCategoryId(itemIter.category_id());
			item.setItemDimension(itemIter.item_dimension());
			item.setQueryCode(itemIter.query_code());
			String itemSpecification = itemIter.item_specification();
			if (itemSpecification != null) {
				itemSpecification = itemSpecification.trim();
			}else{
				itemSpecification = "";
			}
			item.setItemSpecification(itemSpecification);
			item.setItemPic(itemIter.item_pic());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class ArrivePeriodBean_SJProfileKeys 
{
  private static ArrivePeriodBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ArrivePeriodBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ArrivePeriodBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.ArrivePeriodBean_SJProfile0");
  }
}
