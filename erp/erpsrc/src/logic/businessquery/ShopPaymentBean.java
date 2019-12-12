/*@lineinfo:filename=ShopPaymentBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.businessquery;

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
import pojo.businessquery.ShopPayment;

public class ShopPaymentBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd";//设置日期格式
	
	/*@lineinfo:generated-code*//*@lineinfo:22^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PayInfoIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PayInfoIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    businessDateNdx = findColumn("businessDate");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
    foodAmtNdx = findColumn("foodAmt");
    billNumNdx = findColumn("billNum");
    amtPerBillNdx = findColumn("amtPerBill");
    guestNumNdx = findColumn("guestNum");
    amtPerGuestNdx = findColumn("amtPerGuest");
    tableNumNdx = findColumn("tableNum");
    amtPerTableNdx = findColumn("amtPerTable");
    guestPerTableNdx = findColumn("guestPerTable");
    disAmtNdx = findColumn("disAmt");
    roundAmtNdx = findColumn("roundAmt");
    presentAmtNdx = findColumn("presentAmt");
    oughtAmtNdx = findColumn("oughtAmt");
    payAmtNdx = findColumn("payAmt");
    payCNdx = findColumn("payC");
    payNNdx = findColumn("payN");
    payTypeAmtNdx = findColumn("payTypeAmt");
  }
  public String businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessDateNdx);
  }
  private int businessDateNdx;
  public String shopC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shopCNdx);
  }
  private int shopCNdx;
  public String shopN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shopNNdx);
  }
  private int shopNNdx;
  public Double foodAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(foodAmtNdx);
  }
  private int foodAmtNdx;
  public Integer billNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(billNumNdx);
  }
  private int billNumNdx;
  public Double amtPerBill() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtPerBillNdx);
  }
  private int amtPerBillNdx;
  public Integer guestNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(guestNumNdx);
  }
  private int guestNumNdx;
  public Double amtPerGuest() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtPerGuestNdx);
  }
  private int amtPerGuestNdx;
  public Integer tableNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(tableNumNdx);
  }
  private int tableNumNdx;
  public Double amtPerTable() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtPerTableNdx);
  }
  private int amtPerTableNdx;
  public Double guestPerTable() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(guestPerTableNdx);
  }
  private int guestPerTableNdx;
  public Double disAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(disAmtNdx);
  }
  private int disAmtNdx;
  public Double roundAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(roundAmtNdx);
  }
  private int roundAmtNdx;
  public Double presentAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(presentAmtNdx);
  }
  private int presentAmtNdx;
  public Double oughtAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(oughtAmtNdx);
  }
  private int oughtAmtNdx;
  public Double payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payAmtNdx);
  }
  private int payAmtNdx;
  public String payC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(payCNdx);
  }
  private int payCNdx;
  public String payN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(payNNdx);
  }
  private int payNNdx;
  public Double payTypeAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payTypeAmtNdx);
  }
  private int payTypeAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^21*/
	
	public ShopPaymentBean() {
	}

	public List<ShopPayment> getShopPayInfo(String startDate, String endDate)
			throws NoPrivilegeException,SQLException,NoConnection
		{
			List<ShopPayment> results = new ArrayList<ShopPayment>();
			DefaultContext myCtx = getDefaultContext();
			PayInfoIterator withoutDatePayInfoIterator = null;
			if (myCtx != null)
			{
					//查询所有的门店列表及其付款金额
					/*@lineinfo:generated-code*//*@lineinfo:55^6*/

//  ************************************************************
//  #sql [myCtx] withoutDatePayInfoIterator = { SELECT
//  						    a.*,
//  						    b.payC,
//  						    b.payN,
//  						    b.payTypeAmt
//  						FROM
//  						    (
//  						        SELECT
//  						        	'all' businessDate,
//  						            cBranch_C        shopC,
//  						            cBranch_N        shopN,
//  						            SUM(nFoodAmt)    foodAmt,
//  						            COUNT(cBranch_C) billNum,
//  						            CASE
//  						                WHEN (COUNT(cBranch_C) = 0)
//  						                THEN 0
//  						                ELSE decode( COUNT(cBranch_C),0,0,  ROUND(SUM(nFoodAmt) / COUNT(cBranch_C),2))
//  						            END            amtPerBill,
//  						            SUM(iGuestNum) guestNum,
//  						            CASE
//  						                WHEN (SUM(iGuestNum) = 0)
//  						                THEN 0
//  						                ELSE decode( SUM(iGuestNum),0,0,ROUND(SUM(nFoodAmt) / SUM(iGuestNum) ,2)) 
//  						            END                      amtPerGuest,
//  						            COUNT(DISTINCT cTable_N) tableNum,
//  						            CASE
//  						                WHEN (SUM(nFoodAmt) = 0)
//  						                THEN 0
//  						                ELSE decode( COUNT(DISTINCT cTable_N),0,0,ROUND(SUM(nFoodAmt) / COUNT(DISTINCT cTable_N) ,2))
//  						            END amtPerTable,
//  						            CASE
//  						                WHEN (SUM(iGuestNum)=0)
//  						                THEN 0
//  						                ELSE decode(COUNT(DISTINCT cTable_N),0,0,ROUND(SUM(iGuestNum) / COUNT(DISTINCT cTable_N) ,2))
//  						            END              guestPerTable,
//  						            SUM(nDisAmt)     disAmt,
//  						            SUM(nRoundAmt)   roundAmt,
//  						            SUM(nPresentAmt) presentAmt,
//  						            SUM(nOughtAmt)   oughtAmt,
//  						            SUM(nPayAmt)     payAmt
//  						        FROM
//  						            :Com_("d_t_food_bill")
//  						        WHERE
//  						            DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  						        AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  						        GROUP BY
//  						            cBranch_C,
//  						            cBranch_N
//  						        ORDER BY
//  						            cBranch_C)a,
//  						    (
//  						        SELECT
//  						            b.cBranch_C    shopC,
//  						            b.CBRANCH_N    ShopN,
//  						            p.cPay_C       payC,
//  						            p.cPay_N       payN,
//  						            SUM(p.nPayAmt) payTypeAmt
//  						        FROM
//  						            :Com_("d_t_food_bill") b,
//  						            :Com_("d_t_bill_pay") p
//  						        WHERE
//  						            b.cBill_C = p.cBill_C
//  						        AND DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  						        AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  						        GROUP BY
//  						            b.cBranch_C,
//  						            b.CBRANCH_N,
//  						            p.cPay_C,
//  						            p.cPay_N
//  						        ORDER BY
//  						            b.cBranch_C,
//  						            p.CPAY_C)b
//  						WHERE
//  						    a.shopC = b.shopC
//  					 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = Com_("d_t_food_bill");
  String __sJT_5 = Com_("d_t_bill_pay");
  String __sJT_6 = startDate;
  String __sJT_7 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPaymentBean_SJProfileKeys.getKey(0), 0);
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
      withoutDatePayInfoIterator = new PayInfoIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:130^6*/
					
					while(withoutDatePayInfoIterator.next())
					{
						ShopPayment bean = processIter(withoutDatePayInfoIterator);
						results.add(bean);
					}
					withoutDatePayInfoIterator.close();
			}
			else
	        {
	            throw new NoConnection();
	        }
			closeDefaultContext(myCtx);
			return results;
		}
	
	
	public List<ShopPayment> getShopPayInfoByDay(String startDate, String endDate)
			throws NoPrivilegeException,SQLException,NoConnection
		{
			List<ShopPayment> results = new ArrayList<ShopPayment>();
			DefaultContext myCtx = getDefaultContext();
			PayInfoIterator withDateIterator = null;
			if (myCtx != null)
			{
					/*@lineinfo:generated-code*//*@lineinfo:156^6*/

//  ************************************************************
//  #sql [myCtx] withDateIterator = { SELECT
//  						    a.*,
//  						    b.PAYC,
//  						    b.PAYN,
//  						    b.PAYTYPEAMT
//  						FROM
//  						    (
//  						        SELECT
//  						            TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  						            cBranch_C                        shopC,
//  						            cBranch_N                        shopN,
//  						            SUM(nFoodAmt)                    foodAmt,
//  						            COUNT(cBranch_C)                 billNum,
//  						            CASE
//  						                WHEN (COUNT(cBranch_C) = 0)
//  						                THEN 0
//  						                ELSE decode(COUNT(cBranch_C),0,0,ROUND(SUM(nFoodAmt) / COUNT(cBranch_C),2))
//  						            END            amtPerBill,
//  						            SUM(iGuestNum) guestNum,
//  						            CASE
//  						                WHEN (SUM(iGuestNum) = 0)
//  						                THEN 0
//  						                ELSE decode(SUM(iGuestNum),0,0,ROUND(SUM(nFoodAmt) / SUM(iGuestNum) ,2))
//  						            END                      amtPerGuest,
//  						            COUNT(DISTINCT cTable_N) tableNum,
//  						            CASE
//  						                WHEN (SUM(nFoodAmt) = 0)
//  						                THEN 0
//  						                ELSE decode(COUNT(DISTINCT cTable_N),0,0,ROUND(SUM(nFoodAmt) / COUNT(DISTINCT cTable_N) ,2))
//  						            END amtPerTable,
//  						            CASE
//  						                WHEN (SUM(iGuestNum)=0)
//  						                THEN 0
//  						                ELSE decode(COUNT(DISTINCT cTable_N),0,0,ROUND(SUM(iGuestNum) / COUNT(DISTINCT cTable_N) ,2)) 
//  						            END              guestPerTable,
//  						            SUM(nDisAmt)     disAmt,
//  						            SUM(nRoundAmt)   roundAmt,
//  						            SUM(nPresentAmt) presentAmt,
//  						            SUM(nOughtAmt)   oughtAmt,
//  						            SUM(nPayAmt)     payAmt
//  						        FROM
//  						            :Com_("d_t_food_bill")
//  						        WHERE
//  						            TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  						            (
//  						                SELECT
//  						                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  						                FROM
//  						                    :Com_("d_t_food_bill") d
//  						                WHERE
//  						                    DBUSINESS >= to_date(:startDate, 'yyyy-MM-dd')
//  						                AND DBUSINESS <= to_date(:endDate,'yyyy-MM-dd')
//  						                GROUP BY
//  						                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') )
//  						        GROUP BY
//  						            TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  						            cBranch_C,
//  						            cBranch_N
//  						        ORDER BY
//  						            TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  						            cBranch_C)a,
//  						    (
//  						        SELECT
//  						            TO_CHAR(a.DBUSINESS, 'yyyy-MM-dd') businessDate,
//  						            a.cBranch_C                        shopC,
//  						            a.CBRANCH_N                        shopN,
//  						            b.cPay_C                           payC,
//  						            b.cPay_N                           payN,
//  						            SUM(b.nPayAmt)                     payTypeAmt
//  						        FROM
//  						            :Com_("d_t_food_bill") a,
//  						            :Com_("d_t_bill_pay") b
//  						        WHERE
//  						            a.cBill_C = b.cBill_C
//  						        AND TO_CHAR(a.DBUSINESS, 'yyyy-MM-dd') IN
//  						            (
//  						                SELECT
//  						                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  						                FROM
//  						                    :Com_("d_t_food_bill") d
//  						                WHERE
//  						                    DBUSINESS >= to_date(:startDate, 'yyyy-MM-dd')
//  						                AND DBUSINESS <= to_date(:endDate,'yyyy-MM-dd')
//  						                GROUP BY
//  						                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') )
//  						        GROUP BY
//  						            TO_CHAR(a.DBUSINESS, 'yyyy-MM-dd'),
//  						            a.cBranch_C,
//  						            a.CBRANCH_N,
//  						            b.cPay_C,
//  						            b.cPay_N
//  						        ORDER BY
//  						            TO_CHAR(a.DBUSINESS, 'yyyy-MM-dd'),
//  						            a.cBranch_C)b
//  						WHERE
//  						    a.businessDate = b.businessDate
//  						AND a.shopC = b.shopC
//  						ORDER BY
//  						    a.BUSINESSDATE,
//  						    a.SHOPC,
//  						    b.PAYC
//  					 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = Com_("d_t_food_bill");
  String __sJT_6 = Com_("d_t_bill_pay");
  String __sJT_7 = Com_("d_t_food_bill");
  String __sJT_8 = startDate;
  String __sJT_9 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopPaymentBean_SJProfileKeys.getKey(0), 1);
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
      withDateIterator = new PayInfoIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:258^6*/
					while(withDateIterator.next())
					{
						ShopPayment bean = processIter(withDateIterator);
						results.add(bean);
					}
					withDateIterator.close();
			}
			else
	        {
	            throw new NoConnection();
	        }
			closeDefaultContext(myCtx);
			return results;
		}
	
	private ShopPayment processIter(PayInfoIterator payInfoIterator)throws SQLException
	{
		ShopPayment bean = new ShopPayment();
		//pay
		bean.payC = payInfoIterator.payC();
		bean.payN = payInfoIterator.payN();
		bean.payTypeAmt = payInfoIterator.payTypeAmt();
		//shop
		bean.shopC = payInfoIterator.shopC();
		bean.shopN = payInfoIterator.shopN();
		bean.foodAmt = payInfoIterator.foodAmt();
		bean.billNum = payInfoIterator.billNum();
		bean.amtPerBill = payInfoIterator.amtPerBill();
		bean.guestNum = payInfoIterator.guestNum();
		bean.amtPerGuest = payInfoIterator.amtPerGuest();
		bean.tableNum = payInfoIterator.tableNum();
		bean.amtPerTable = payInfoIterator.amtPerTable();
		bean.guestPerTable = payInfoIterator.guestPerTable();
		bean.disAmt = payInfoIterator.disAmt();
		bean.roundAmt = payInfoIterator.roundAmt();
		bean.presentAmt = payInfoIterator.presentAmt();
		bean.oughtAmt = payInfoIterator.oughtAmt();
		bean.payAmt = payInfoIterator.payAmt();
		bean.businessDate = payInfoIterator.businessDate();
		return bean;
	}
}/*@lineinfo:generated-code*/class ShopPaymentBean_SJProfileKeys 
{
  private static ShopPaymentBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShopPaymentBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShopPaymentBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.ShopPaymentBean_SJProfile0");
  }
}
