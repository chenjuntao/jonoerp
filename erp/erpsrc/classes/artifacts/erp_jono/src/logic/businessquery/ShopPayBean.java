/*@lineinfo:filename=ShopPayBean*//*@lineinfo:user-code*//*@lineinfo:1^1*///===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.7.7 by cjt
// Last edited on 2014.8.7 by cjt
//
// Comments:read shop pay type list.
// 说明：查询各个门店的付款方式列表。
//
//===============================================
package logic.businessquery;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import pojo.businessquery.ShopPay;

public class ShopPayBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd";//设置日期格式
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ShopIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ShopIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cBranch_CNdx = findColumn("cBranch_C");
    cBranch_NNdx = findColumn("cBranch_N");
    sumPayAmtNdx = findColumn("sumPayAmt");
  }
  public String cBranch_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBranch_CNdx);
  }
  private int cBranch_CNdx;
  public String cBranch_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBranch_NNdx);
  }
  private int cBranch_NNdx;
  public Double sumPayAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sumPayAmtNdx);
  }
  private int sumPayAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^95*/ 
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PayIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PayIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cBranch_CNdx = findColumn("cBranch_C");
    cPay_NNdx = findColumn("cPay_N");
    cPay_CNdx = findColumn("cPay_C");
    payTypeAmtNdx = findColumn("payTypeAmt");
  }
  public String cBranch_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBranch_CNdx);
  }
  private int cBranch_CNdx;
  public String cPay_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPay_NNdx);
  }
  private int cPay_NNdx;
  public String cPay_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPay_CNdx);
  }
  private int cPay_CNdx;
  public Double payTypeAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payTypeAmtNdx);
  }
  private int payTypeAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^107*/ 
	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DateIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DateIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    settletimeNdx = findColumn("settletime");
  }
  public String settletime() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(settletimeNdx);
  }
  private int settletimeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^60*/
	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PayBillIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PayBillIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    CPAY_NNdx = findColumn("CPAY_N");
    CPAY_CNdx = findColumn("CPAY_C");
    payTypeAmtNdx = findColumn("payTypeAmt");
  }
  public String CPAY_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CPAY_NNdx);
  }
  private int CPAY_NNdx;
  public String CPAY_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CPAY_CNdx);
  }
  private int CPAY_CNdx;
  public Double payTypeAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payTypeAmtNdx);
  }
  private int payTypeAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^92*/ 
	/*@lineinfo:generated-code*//*@lineinfo:41^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PayNameIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PayNameIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    CPAY_NNdx = findColumn("CPAY_N");
  }
  public String CPAY_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CPAY_NNdx);
  }
  private int CPAY_NNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^59*/ 
	/*@lineinfo:generated-code*//*@lineinfo:42^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PayByDayIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PayByDayIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    shopCNdx = findColumn("shopC");
    shopNameNdx = findColumn("shopName");
    payNameNdx = findColumn("payName");
    payCodeNdx = findColumn("payCode");
    payAmtNdx = findColumn("payAmt");
    businessDateNdx = findColumn("businessDate");
  }
  public String shopC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shopCNdx);
  }
  private int shopCNdx;
  public String shopName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shopNameNdx);
  }
  private int shopNameNdx;
  public String payName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(payNameNdx);
  }
  private int payNameNdx;
  public String payCode() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(payCodeNdx);
  }
  private int payCodeNdx;
  public Double payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payAmtNdx);
  }
  private int payAmtNdx;
  public String businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessDateNdx);
  }
  private int businessDateNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^142*/ 
	
	public ShopPayBean()
	{
	}
	
	public List<Map> getBillPay(String CBILL_C)
	throws	NoPrivilegeException,SQLException,NoConnection	
	{
		List<Map> payBillLists = new ArrayList<Map>();
		
		DefaultContext myCtx = getDefaultContext();
		PayBillIterator payBillIterator = null;
		
		if (myCtx != null){
			/*@lineinfo:generated-code*//*@lineinfo:57^4*/

//  ************************************************************
//  #sql [myCtx] payBillIterator = { SELECT
//  			    pay.CPAY_C,
//  			    pay.CPAY_N,
//  			    pay.CBILL_C,
//  			    ROUND(SUM(pay.nPayAmt) ,2) payTypeAmt
//  			FROM
//  			    :Com_("d_t_bill_pay") pay
//  			WHERE
//  			    pay.CBILL_C = :CBILL_C
//  			GROUP BY
//  			    pay.CPAY_C,
//  			    pay.CPAY_N,
//  			    pay.CBILL_C
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_bill_pay");
  String __sJT_2 = CBILL_C;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      payBillIterator = new PayBillIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:71^4*/
			
				while(payBillIterator.next()){
					Map temp = new TreeMap();
					temp.put("CBILL_C", CBILL_C);
					temp.put("CPAY_N", payBillIterator.CPAY_N());
					temp.put("CPAY_C", payBillIterator.CPAY_C());
					temp.put("payTypeAmt", payBillIterator.payTypeAmt());
					payBillLists.add(temp);
				}
				payBillIterator.close();
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return payBillLists;
	}
	
	public List<ShopPay> byDay(String startTime, String endTime)
			throws NoPrivilegeException,SQLException,NoConnection	{
			
			List<ShopPay> shops = new ArrayList<ShopPay>();
			DefaultContext myCtx = getDefaultContext();
			PayByDayIterator payByDayIterator = null;
			if (myCtx != null)		{
				/*@lineinfo:generated-code*//*@lineinfo:98^5*/

//  ************************************************************
//  #sql [myCtx] payByDayIterator = { SELECT
//  					    TO_CHAR(a.DBUSINESS, 'yyyy-MM-dd') businessDate,
//  					    a.cBranch_C                        shopC,
//  					    a.CBRANCH_N                        shopName,
//  					    b.cPay_C                           payCode,
//  					    b.cPay_N                           payName,
//  					    SUM(b.nPayAmt)                     payAmt
//  					FROM
//  					    :Com_("d_t_food_bill") a,
//  					    :Com_("d_t_bill_pay") b
//  					WHERE
//  					    a.cBill_C = b.cBill_C
//  					AND TO_CHAR(a.DBUSINESS, 'yyyy-MM-dd') IN
//  					    (
//  					        SELECT
//  					            TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  					        FROM
//  					            :Com_("d_t_food_bill") d
//  					        WHERE
//  					            DBUSINESS >= to_date(:startTime, 'yyyy-mm-dd')
//  					        AND DBUSINESS <= to_date(:endTime, 'yyyy-mm-dd')
//  					        GROUP BY
//  					            TO_CHAR(DBUSINESS, 'yyyy-MM-dd') )
//  					GROUP BY
//  					    TO_CHAR(a.DBUSINESS, 'yyyy-MM-dd'),
//  					    a.cBranch_C,
//  					    a.CBRANCH_N,
//  					    b.cPay_C,
//  					    b.cPay_N
//  					ORDER BY
//  					    TO_CHAR(a.DBUSINESS, 'yyyy-MM-dd'),
//  					    a.cBranch_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = Com_("d_t_food_bill");
  String __sJT_4 = startTime;
  String __sJT_5 = endTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      payByDayIterator = new PayByDayIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:129^21*/
				
				while(payByDayIterator.next()){
					ShopPay shopPay = new ShopPay();
					shopPay.dbusinessDate = payByDayIterator.businessDate();
					shopPay.shopC = payByDayIterator.shopC();
					shopPay.shopName = payByDayIterator.shopName();
					shopPay.payCode = payByDayIterator.payCode();
					shopPay.payName = payByDayIterator.payName();
					shopPay.payAmt = payByDayIterator.payAmt();
					shops.add(shopPay);
				}
				payByDayIterator.close();

			}
			else{
	            throw new NoConnection();
	        }
			
			closeDefaultContext(myCtx);
			return shops;
		}
	
	public List<ShopPay> listOneShopPay(String startTime, String endTime,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection
		{
			List<ShopPay> shops = new ArrayList<ShopPay>();
			DefaultContext myCtx = getDefaultContext();
			ShopIterator shopIterator = null;
			if (myCtx != null)
			{
				int billCount = 0;
				//查询当前日期范围内是否有数据
				/*@lineinfo:generated-code*//*@lineinfo:162^5*/

//  ************************************************************
//  #sql [myCtx] { select count(cBill_C)  
//  							from :Com_("d_t_food_bill")
//  							where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  							and DBUSINESS <= to_date(:endTime, :strDateFormat) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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
    billCount = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:165^58*/
				//如果当前日期范围内有数据才继续查询，如果无数据则直接返回
				if (billCount > 0) 
				{
					//查询所有的门店列表及其付款金额
					/*@lineinfo:generated-code*//*@lineinfo:170^6*/

//  ************************************************************
//  #sql [myCtx] shopIterator = { select cBranch_C, cBranch_N, sum(nPayAmt) sumPayAmt
//  												from :Com_("d_t_food_bill")
//  												where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  												and DBUSINESS <= to_date(:endTime, :strDateFormat)
//  												and cBranch_C = :shopC
//  												group by cBranch_C, cBranch_N 
//  												order by cBranch_C };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopIterator = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:176^31*/
					while(shopIterator.next())
					{
						ShopPay shop = new ShopPay();
						shop.shopC = shopIterator.cBranch_C();
						shop.shopName = shopIterator.cBranch_N();
						shop.payAmt = shopIterator.sumPayAmt();
						shops.add(shop);
					}
					shopIterator.close();

					//查询每个门店的分类付款方式下的付款金额
					PayIterator payIterator = null;
					/*@lineinfo:generated-code*//*@lineinfo:189^6*/

//  ************************************************************
//  #sql [myCtx] payIterator = { select a.cBranch_C, b.cPay_C,b.cPay_N, sum(b.nPayAmt) payTypeAmt
//  												from :Com_("d_t_food_bill") a, :Com_("d_t_bill_pay") b 
//  												where a.cBill_C = b.cBill_C 
//  												and a.DBUSINESS >= to_date(:startTime, :strDateFormat)
//  												and a.DBUSINESS <= to_date(:endTime, :strDateFormat)
//  												group by a.cBranch_C,b.cPay_C, b.cPay_N };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = startTime;
  String __sJT_4 = strDateFormat;
  String __sJT_5 = endTime;
  String __sJT_6 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      payIterator = new PayIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:194^52*/
					while(payIterator.next())
					{
						for (int i=0; i<shops.size(); i++) 
						{
							ShopPay shop = shops.get(i);
							if (shop.shopC.equals(payIterator.cBranch_C()))
							{
								shop.pay.put(payIterator.cPay_C()+"separator"+payIterator.cPay_N(), payIterator.payTypeAmt());
								break;
							}
						}
					}
					payIterator.close();
				}
			}
			else
	        {
	            throw new NoConnection();
	        }
			closeDefaultContext(myCtx);
			return shops;
		}

	public List<ShopPay> listShopPay(String startTime, String endTime)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		List<ShopPay> shops = new ArrayList<ShopPay>();
		DefaultContext myCtx = getDefaultContext();
		ShopIterator shopIterator = null;
		if (myCtx != null)
		{
			int billCount = 0;
			//查询当前日期范围内是否有数据
			/*@lineinfo:generated-code*//*@lineinfo:228^4*/

//  ************************************************************
//  #sql [myCtx] { select count(cBill_C)  
//  						from :Com_("d_t_food_bill")
//  						where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  						and DBUSINESS <= to_date(:endTime, :strDateFormat) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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
    billCount = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:231^57*/
			//如果当前日期范围内有数据才继续查询，如果无数据则直接返回
			if (billCount > 0) 
			{
				//查询所有的门店列表及其付款金额
				/*@lineinfo:generated-code*//*@lineinfo:236^5*/

//  ************************************************************
//  #sql [myCtx] shopIterator = { select cBranch_C, cBranch_N, sum(nPayAmt) sumPayAmt
//  											from :Com_("d_t_food_bill")
//  											where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  											and DBUSINESS <= to_date(:endTime, :strDateFormat)
//  											group by cBranch_C, cBranch_N 
//  											order by cBranch_C };
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
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopIterator = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:241^30*/
				while(shopIterator.next())
				{
					ShopPay shop = new ShopPay();
					shop.shopC = shopIterator.cBranch_C();
					shop.shopName = shopIterator.cBranch_N();
					shop.payAmt = shopIterator.sumPayAmt();
					shops.add(shop);
				}
				shopIterator.close();

				//查询每个门店的分类付款方式下的付款金额
				PayIterator payIterator = null;
				/*@lineinfo:generated-code*//*@lineinfo:254^5*/

//  ************************************************************
//  #sql [myCtx] payIterator = { select a.cBranch_C, b.cPay_C,b.cPay_N, sum(b.nPayAmt) payTypeAmt
//  											from :Com_("d_t_food_bill") a, :Com_("d_t_bill_pay") b 
//  											where a.cBill_C = b.cBill_C 
//  											and a.DBUSINESS >= to_date(:startTime, :strDateFormat)
//  											and a.DBUSINESS <= to_date(:endTime, :strDateFormat)
//  											group by a.cBranch_C,b.cPay_C, b.cPay_N };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = startTime;
  String __sJT_4 = strDateFormat;
  String __sJT_5 = endTime;
  String __sJT_6 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      payIterator = new PayIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:259^51*/
				while(payIterator.next())
				{
					for (int i=0; i<shops.size(); i++) 
					{
						ShopPay shop = shops.get(i);
						if (shop.shopC.equals(payIterator.cBranch_C()))
						{
							shop.pay.put(payIterator.cPay_C()+"separator"+payIterator.cPay_N(), payIterator.payTypeAmt());
							break;
						}
					}
				}
				payIterator.close();
			}
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return shops;
	}

	/**
	 * 按天查询分页，得到总的不相同的天数
	 */
	public int countByDay(Date startTime, Date endTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		int total = 0;
		DefaultContext myCtx = getDefaultContext();
		if (myCtx != null) {
			/*@lineinfo:generated-code*//*@lineinfo:291^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT count(DISTINCT
//                      TO_CHAR(DBUSINESS, 'yyyy-MM-dd') ) 
//                  FROM
//                      :Com_("d_t_food_bill") b
//                  WHERE
//                      DBUSINESS >= :startTime
//                  AND DBUSINESS <= :endTime
//                  ORDER BY
//                      TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  Date __sJT_2 = startTime;
  Date __sJT_3 = endTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:301^4*/
		} else {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		
		return total;
	}
	
	public String getPayName(String payC)
		throws NoPrivilegeException, SQLException, NoConnection {
		String payName ="";
		
		DefaultContext myCtx = getDefaultContext();
		PayNameIterator payNameIterator = null;
		
		if (myCtx != null){
			/*@lineinfo:generated-code*//*@lineinfo:318^4*/

//  ************************************************************
//  #sql [myCtx] payNameIterator = { SELECT p.CPAY_N  
//  					from :Com_("d_t_bill_pay") p 
//  					WHERE p.CPAY_C = :payC 
//  					and rownum = 1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_bill_pay");
  String __sJT_2 = payC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      payNameIterator = new PayNameIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:322^20*/
			
				while(payNameIterator.next()){
					payName = payNameIterator.CPAY_N();
				}
				payNameIterator.close();
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return payName;
	}

	/**
	 * 按天查询分页，得到数据库中实际的开始日期和结束日期
	 */
	public Map<String, List> listShopPayByDay(Date startTime, Date endTime, int startRow, int endRow)
		throws NoPrivilegeException,SQLException,NoConnection {
		Map<String, List> result = new HashMap<String, List>();
		DateIterator dateIterator = null;
		List<String> dateLst = new ArrayList<String>();
		DefaultContext myCtx = getDefaultContext();
		if (myCtx != null) {
			/*@lineinfo:generated-code*//*@lineinfo:347^4*/

//  ************************************************************
//  #sql [myCtx] dateIterator = { SELECT
//  				    *
//  				FROM
//  				    (
//  				        SELECT
//  				            t.*,
//  				            ROWNUM rn
//  				        FROM
//  				            (
//  				                SELECT DISTINCT
//  				                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') settletime
//  				                FROM
//  				                    :Com_("d_t_food_bill") b
//  				                WHERE
//  				                    DBUSINESS >= :startTime
//  				                AND DBUSINESS <= :endTime
//  				                ORDER BY
//  				                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') ) t
//  				        WHERE
//  				            rownum <= :endRow )
//  				WHERE
//  				    rn >= :startRow	
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  Date __sJT_2 = startTime;
  Date __sJT_3 = endTime;
  int __sJT_4 = endRow;
  int __sJT_5 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPayBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      dateIterator = new DateIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:370^4*/
			while(dateIterator.next()) {
				dateLst.add(dateIterator.settletime());
			}
			dateIterator.close();

			//将开始和结束日期之间的每天都列出来进行按天查询
			for (int i = 0; i < dateLst.size(); i++) {
				String date = dateLst.get(i);
				List shopList = listShopPay(date, date); 
				if(shopList.size()>0) {
					result.put(date, shopList);
				}
			}
		} else {
            throw new NoConnection();
        }
		myCtx.close();
		
		return result;
	}
}/*@lineinfo:generated-code*/class ShopPayBean_SJProfileKeys 
{
  private static ShopPayBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShopPayBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShopPayBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.ShopPayBean_SJProfile0");
  }
}
