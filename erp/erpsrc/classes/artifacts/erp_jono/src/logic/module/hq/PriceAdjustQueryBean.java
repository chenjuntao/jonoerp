/*@lineinfo:filename=PriceAdjustQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2015
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on December 31, 2015 by liyzh
 * Last edited on December 31, 2015 by liyzh
 * 
 * 说明： 查询采购调价单相关信息
 */
package logic.module.hq;

import java.sql.Date;
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
import com.tanry.framework.util.SqlDateUtil;

public class PriceAdjustQueryBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PriceAdjustQueryBean.class);

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
    supplier_idNdx = findColumn("supplier_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    new_priceNdx = findColumn("new_price");
    adjust_typeNdx = findColumn("adjust_type");
  }
  public String supplier_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplier_idNdx);
  }
  private int supplier_idNdx;
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
  public Double new_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(new_priceNdx);
  }
  private int new_priceNdx;
  public String adjust_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(adjust_typeNdx);
  }
  private int adjust_typeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^39*/
	
	//根据调价类型和生效日期查询调价的具体明细
	public List<Map> queryItem(String adjustType, java.util.Date effectTime) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sTime = SqlDateUtil.getSqlDate(effectTime);
		
		DefaultContext myCtx = getDefaultContext();
		String thetype = adjustType + "%";
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    h.SUPPLIER_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.NEW_PRICE,
//  			    h.ADJUST_TYPE
//  			FROM
//  			:Com_("D_T1_PRICE_ADJUST_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  			ON
//  			    d.FORM_ID = h.FORM_ID
//  			WHERE
//  			    h.ADJUST_TYPE LIKE  :thetype
//  			AND h.EFFECT_TIME = :sTime
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_3 = thetype;
  Date __sJT_4 = sTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceAdjustQueryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:63^3*/
		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("supplierId", itemIter.supplier_id());
			item.put("itemId", itemIter.item_id());
			item.put("itemName", itemIter.item_name());
			item.put("newPrice", itemIter.new_price());
			item.put("priceType", itemIter.adjust_type());
			itemLst.add(item);
		}
		itemIter.close();
		
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class PriceAdjustQueryBean_SJProfileKeys 
{
  private static PriceAdjustQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PriceAdjustQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PriceAdjustQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.hq.PriceAdjustQueryBean_SJProfile0");
  }
}
