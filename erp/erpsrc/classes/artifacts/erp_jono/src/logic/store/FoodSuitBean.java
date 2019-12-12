/*@lineinfo:filename=FoodSuitBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Feb 10 11:46:15 CST 2015 by lyz
 * Last edited on Tue Feb 10 11:46:15 CST 2015 by lyz
 * 
 * comment: 存储出品套餐各个子项以及子项可替代品
 */
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.store.FoodSuit;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class FoodSuitBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(FoodSuitBean.class);

	/**
	 * 判断子项是否存在
	 */
	public boolean exist(String foodSuitId, String foodId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:44^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				COUNT(*) 
//  			FROM
//  			:Com_("D_T2_ITEM_FOOD_SUIT")
//  			WHERE
//  				FOOD_SUIT_ID = :foodSuitId
//  			AND FOOD_OPTION_ID IS NULL --换品
//  			AND FOOD_ID = :foodId --子项
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_2 = foodSuitId;
  String __sJT_3 = foodId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      __sJT_rtRs = __sJT_result;
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
  try 
  {
    sqlj.runtime.ref.ResultSetIterImpl.checkColumns(__sJT_rtRs, 1);
    if (!__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_NO_ROW_SELECT_INTO();
    }
    total = __sJT_rtRs.getIntNoNull(1);
    if (__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_MULTI_ROW_SELECT_INTO();
    }
  }
  finally 
  {
    __sJT_rtRs.close();
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:53^3*/
		closeDefaultContext(myCtx);
		
		if (total > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断换品是否存在
	 */
	public boolean exist(String foodSuitId, String foodId, String foodOptionId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:69^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				COUNT(*) 
//  			FROM
//  			:Com_("D_T2_ITEM_FOOD_SUIT")
//  			WHERE
//  				FOOD_SUIT_ID = :foodSuitId
//  			AND FOOD_OPTION_ID = :foodOptionId
//  			AND FOOD_ID = :foodId --子项
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_2 = foodSuitId;
  String __sJT_3 = foodOptionId;
  String __sJT_4 = foodId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      __sJT_rtRs = __sJT_result;
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
  try 
  {
    sqlj.runtime.ref.ResultSetIterImpl.checkColumns(__sJT_rtRs, 1);
    if (!__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_NO_ROW_SELECT_INTO();
    }
    total = __sJT_rtRs.getIntNoNull(1);
    if (__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_MULTI_ROW_SELECT_INTO();
    }
  }
  finally 
  {
    __sJT_rtRs.close();
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:78^3*/
		closeDefaultContext(myCtx);
		
		if (total > 0) {
			return true;
		}
		return false;
	}
	
	public int saveEntity(FoodSuit suit)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String foodSuitId = suit.getFoodSuitId();
		String foodId = suit.getFoodId();
		String foodOptionId = suit.getFoodOptionId();
		Double itemCount = suit.getItemCount();
		Integer isCurrent = suit.getIsCurrent();

		/*@lineinfo:generated-code*//*@lineinfo:97^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_ITEM_FOOD_SUIT") 
//  				(FOOD_SUIT_ID, FOOD_ID, FOOD_OPTION_ID, ITEM_COUNT, IS_CURRENT) 
//  			VALUES (:foodSuitId, :foodId, :foodOptionId, :itemCount, :isCurrent)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_2 = foodSuitId;
  String __sJT_3 = foodId;
  String __sJT_4 = foodOptionId;
  Double __sJT_5 = itemCount;
  Integer __sJT_6 = isCurrent;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:101^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateSubItem(FoodSuit suit)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String foodSuitId = suit.getFoodSuitId();
		String foodId = suit.getFoodId();
		String foodOptionId = suit.getFoodOptionId();
		Double itemCount = suit.getItemCount();
		Double itemPrice = suit.getItemPrice();
		Integer isCurrent = suit.getIsCurrent();

		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_ITEM_FOOD_SUIT")
//  			SET
//  				ITEM_COUNT = :itemCount,
//  				ITEM_PRICE = :itemPrice,
//  				IS_CURRENT = :isCurrent
//  			WHERE 
//  				FOOD_SUIT_ID = :foodSuitId
//  			AND FOOD_OPTION_ID IS NULL --换品
//  			AND FOOD_ID = :foodId --子项
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  Double __sJT_2 = itemCount;
  Double __sJT_3 = itemPrice;
  Integer __sJT_4 = isCurrent;
  String __sJT_5 = foodSuitId;
  String __sJT_6 = foodId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:129^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateOption(FoodSuit suit)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String foodSuitId = suit.getFoodSuitId();
		String foodId = suit.getFoodId();
		String foodOptionId = suit.getFoodOptionId();
		Double itemCount = suit.getItemCount();
		Integer isCurrent = suit.getIsCurrent();

		/*@lineinfo:generated-code*//*@lineinfo:145^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_ITEM_FOOD_SUIT")
//  			SET
//  				ITEM_COUNT = :itemCount,
//  				IS_CURRENT = :isCurrent
//  			WHERE 
//  				FOOD_SUIT_ID = :foodSuitId
//  			AND FOOD_OPTION_ID = :foodOptionId
//  			AND FOOD_ID = :foodId --子项
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  Double __sJT_2 = itemCount;
  Integer __sJT_3 = isCurrent;
  String __sJT_4 = foodSuitId;
  String __sJT_5 = foodOptionId;
  String __sJT_6 = foodId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setIntWrapper(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:155^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String foodSuitId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:165^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_ITEM_FOOD_SUIT")
//  			WHERE
//  				FOOD_SUIT_ID = :foodSuitId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_2 = foodSuitId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:171^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 删除除此之外的其它套餐子项，同时删除子项对应的换品
	 */
	public int deleteSubExcept(String suitId, List<String> itemIdLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " FOOD_ID IS NOT NULL ";
		if (itemIdLst.size() != 0) { // 非空处理
			query = " FOOD_ID NOT IN ('" + StringUtils.join(itemIdLst, ",").replaceAll(",", "','") + "') ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:188^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_ITEM_FOOD_SUIT")
//  			WHERE
//  				FOOD_SUIT_ID = :suitId
//  		--	AND FOOD_OPTION_ID IS NULL
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_2 = suitId;
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:196^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 删除除此之外的其它套餐子项的换品
	 */
	public int deleteOptionExcept(String suitId, String subItemId, List<String> itemIdLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " FOOD_OPTION_ID IS NOT NULL ";
		if (itemIdLst.size() != 0) { // 非空处理
			query = " FOOD_OPTION_ID NOT IN ('" + StringUtils.join(itemIdLst, ",").replaceAll(",", "','") + "') ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:213^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_ITEM_FOOD_SUIT")
//  			WHERE
//  				FOOD_SUIT_ID = :suitId
//  			AND FOOD_ID = :subItemId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_2 = suitId;
  String __sJT_3 = subItemId;
  String __sJT_4 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:221^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:227^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SubItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SubItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    food_suit_idNdx = findColumn("food_suit_id");
    food_idNdx = findColumn("food_id");
    sub_item_nameNdx = findColumn("sub_item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_countNdx = findColumn("item_count");
    sale_priceNdx = findColumn("sale_price");
  }
  public String food_suit_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(food_suit_idNdx);
  }
  private int food_suit_idNdx;
  public String food_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(food_idNdx);
  }
  private int food_idNdx;
  public String sub_item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sub_item_nameNdx);
  }
  private int sub_item_nameNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double sale_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sale_priceNdx);
  }
  private int sale_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:228^63*/
	
	public List<Map> querySubItem(String suitId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SubItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:234^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    s.*,
//  			    m.ITEM_NAME AS SUB_ITEM_NAME,
//  			    m.ITEM_DIMENSION,
//  			    NVL(s.ITEM_PRICE,p.ITEM_PRICE) sale_price, --如果套餐价为空，则取价格表的(出品售卖价:SALE)
//  			    p.PRICE_TYPE
//  			FROM
//  			:Com_("D_T2_ITEM_FOOD_SUIT") s
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = s.FOOD_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'SALE'
//  			WHERE
//  			    s.FOOD_SUIT_ID = :suitId
//  			AND s.FOOD_OPTION_ID IS NULL
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = suitId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new SubItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:255^3*/

		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("suitId", itemIter.food_suit_id());
			item.put("subItemId", itemIter.food_id());
			item.put("subItemName", itemIter.sub_item_name());
			item.put("itemDimension", itemIter.item_dimension());
			item.put("itemCount", itemIter.item_count());
			item.put("salePrice", itemIter.sale_price());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:273^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class OptionIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public OptionIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    food_suit_idNdx = findColumn("food_suit_id");
    food_idNdx = findColumn("food_id");
    food_option_idNdx = findColumn("food_option_id");
    option_nameNdx = findColumn("option_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_countNdx = findColumn("item_count");
    sale_priceNdx = findColumn("sale_price");
  }
  public String food_suit_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(food_suit_idNdx);
  }
  private int food_suit_idNdx;
  public String food_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(food_idNdx);
  }
  private int food_idNdx;
  public String food_option_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(food_option_idNdx);
  }
  private int food_option_idNdx;
  public String option_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(option_nameNdx);
  }
  private int option_nameNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double sale_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sale_priceNdx);
  }
  private int sale_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:274^63*/
	
	public List<Map> queryOption(String suitId, String subItemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		OptionIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:280^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    s.*,
//  			    m.ITEM_NAME AS OPTION_NAME,
//  			    m.ITEM_DIMENSION,
//  			    p.ITEM_PRICE AS sale_price,
//  			    p.PRICE_TYPE
//  			FROM
//  			:Com_("D_T2_ITEM_FOOD_SUIT") s
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = s.FOOD_OPTION_ID -- join condition
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'SALE'
//  			WHERE
//  			    s.FOOD_SUIT_ID = :suitId
//  			AND s.FOOD_ID = :subItemId
//  			AND s.FOOD_OPTION_ID IS NOT NULL
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = suitId;
  String __sJT_5 = subItemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new OptionIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:302^3*/

		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("suitId", itemIter.food_suit_id());
			item.put("subItemId", itemIter.food_id());
			item.put("optionId", itemIter.food_option_id());
			item.put("optionName", itemIter.option_name());
			item.put("itemDimension", itemIter.item_dimension());
			item.put("itemCount", itemIter.item_count());
			item.put("salePrice", itemIter.sale_price());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:321^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ProductIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ProductIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    sale_priceNdx = findColumn("sale_price");
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
  public Double sale_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sale_priceNdx);
  }
  private int sale_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:322^44*/
	
	public List<Map> queryProduct(String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		List<Map> itemLst = new ArrayList<Map>();
		DefaultContext myCtx = getDefaultContext();
		ProductIter productIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:331^3*/

//  ************************************************************
//  #sql [myCtx] productIter = { SELECT
//  			    m.*,
//  			    p.ITEM_PRICE AS sale_price
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'SALE'
//  			WHERE
//  				m.ITEM_TYPE = 'PRODUCT'
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
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = itemName;
  String __sJT_4 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      productIter = new ProductIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:347^3*/
		while (productIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", productIter.item_id());
			item.put("itemName", productIter.item_name());
			item.put("itemDimension", productIter.item_dimension());
			item.put("salePrice", productIter.sale_price());
			itemLst.add(item);
		}
		productIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}

	public List<Map> queryProduct(String itemName, String exceptId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		DefaultContext myCtx = getDefaultContext();
		ProductIter productIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:367^3*/

//  ************************************************************
//  #sql [myCtx] productIter = { SELECT
//  			    m.*,
//  			    p.ITEM_PRICE AS sale_price
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = 'SALE'
//  			WHERE
//  				m.ITEM_TYPE = 'PRODUCT'
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//              AND m.ITEM_ID != :exceptId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = itemName;
  String __sJT_4 = itemName;
  String __sJT_5 = exceptId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSuitBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      productIter = new ProductIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:384^3*/
		
		List<Map> itemLst = new ArrayList<Map>();
		while (productIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", productIter.item_id());
			item.put("itemName", productIter.item_name());
			item.put("itemDimension", productIter.item_dimension());
			item.put("salePrice", productIter.sale_price());
			itemLst.add(item);
		}
		productIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class FoodSuitBean_SJProfileKeys 
{
  private static FoodSuitBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodSuitBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodSuitBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.FoodSuitBean_SJProfile0");
  }
}
