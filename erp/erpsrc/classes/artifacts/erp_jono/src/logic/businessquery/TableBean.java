/*@lineinfo:filename=TableBean*//*@lineinfo:user-code*//*@lineinfo:1^1*///===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.6.18 by cjt
// Last edited on 2014.7.7 by pw
//
// Comments:read shop business info list.
// 说明：查询各个台位的营业信息列表。
//
//===============================================

package logic.businessquery;

import sqlj.runtime.*;
import sqlj.runtime.ref.*;
import java.sql.*;
import oracle.sql.*;
import oracle.sqlj.runtime.*;
import oracle.jdbc.driver.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import logic.pool.ConnectionPool;
import logic.*;
import com.tanry.framework.acl.*;
import pojo.businessquery.TableBill;

public class TableBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd";//设置日期格式
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class TableIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TableIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cTable_NNdx = findColumn("cTable_N");
    foodAmtNdx = findColumn("foodAmt");
    billNumNdx = findColumn("billNum");
    guestNumNdx = findColumn("guestNum");
  }
  public String cTable_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cTable_NNdx);
  }
  private int cTable_NNdx;
  public float foodAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(foodAmtNdx);
  }
  private int foodAmtNdx;
  public int billNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(billNumNdx);
  }
  private int billNumNdx;
  public int guestNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(guestNumNdx);
  }
  private int guestNumNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^25*/

	/*@lineinfo:generated-code*//*@lineinfo:42^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ShopNameIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ShopNameIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    shopNameNdx = findColumn("shopName");
  }
  public String shopName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shopNameNdx);
  }
  private int shopNameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^63*/
	
	public TableBean()
	{
	}
	
	//根据门店编号获取门店名称
	public String getShopName(String shopC) throws NoPrivilegeException,SQLException,NoConnection{
		DefaultContext myCtx = getDefaultContext();
		ShopNameIterator shopNameIterator = null;
		
		String shopName = "";
		
		if (myCtx != null)		{
			/*@lineinfo:generated-code*//*@lineinfo:56^4*/

//  ************************************************************
//  #sql [myCtx] shopNameIterator = { SELECT cBranch_N shopName 
//  											from :Com_("d_t_food_bill") 
//  											WHERE cBranch_C = :shopC 
//  											and ROWNUM = 1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TableBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopNameIterator = new ShopNameIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:59^26*/
			
			
			while(shopNameIterator.next())			{
				shopName = shopNameIterator.shopName();
			}
			shopNameIterator.close();
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return shopName;
	}
	
	public List<TableBill> listTable(String startTime, String endTime, String shopC)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		List<TableBill> tables = new ArrayList<TableBill>();
		DefaultContext myCtx = getDefaultContext();
		TableIterator tableIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:83^4*/

//  ************************************************************
//  #sql [myCtx] tableIterator = { SELECT DISTINCT
//  				    cTable_N,
//  				    SUM(nFoodAmt)    foodAmt,
//  				    COUNT(cBranch_C) billNum,
//  				    SUM(iGuestNum)   guestNum
//  				FROM
//  				    :Com_("d_t_food_bill")
//  				WHERE
//  				    DBUSINESS >= to_date(:startTime, :strDateFormat)
//  				AND DBUSINESS <= to_date(:endTime, :strDateFormat)
//  				AND cBranch_C = :shopC 
//  				GROUP BY
//  					cTable_N
//  				ORDER BY
//  					cTable_N };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  String __sJT_6 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TableBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      tableIterator = new TableIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:97^14*/
			
			while(tableIterator.next())
			{
				TableBill table = new TableBill();
				table.table = tableIterator.cTable_N();
				table.foodAmt = tableIterator.foodAmt();
				table.billNum = tableIterator.billNum();
				table.guestNum = tableIterator.guestNum();
				
				tables.add(table);
			}
			tableIterator.close();
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return tables;
	}
}/*@lineinfo:generated-code*/class TableBean_SJProfileKeys 
{
  private static TableBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TableBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TableBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.TableBean_SJProfile0");
  }
}
