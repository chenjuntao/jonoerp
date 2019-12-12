/*@lineinfo:filename=BillPayDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.BillPayDetail;

public class BillPayDetailBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd"; //设置日期格式
	
	/*@lineinfo:generated-code*//*@lineinfo:22^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class BillPayDetailIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BillPayDetailIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    billCNdx = findColumn("billC");
    payNNdx = findColumn("payN");
    unitNdx = findColumn("unit");
    payAmtNdx = findColumn("payAmt");
    businessDateNdx = findColumn("businessDate");
    tableNdx = findColumn("table");
    periodNdx = findColumn("period");
    shiftNdx = findColumn("shift");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
    vipCNdx = findColumn("vipC");
    vipNNdx = findColumn("vipN");
  }
  public String billC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(billCNdx);
  }
  private int billCNdx;
  public String payN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(payNNdx);
  }
  private int payNNdx;
  public String unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(unitNdx);
  }
  private int unitNdx;
  public float payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(payAmtNdx);
  }
  private int payAmtNdx;
  public String businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessDateNdx);
  }
  private int businessDateNdx;
  public String table() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tableNdx);
  }
  private int tableNdx;
  public String period() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(periodNdx);
  }
  private int periodNdx;
  public String shift() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shiftNdx);
  }
  private int shiftNdx;
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
  public String vipC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(vipCNdx);
  }
  private int vipCNdx;
  public String vipN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(vipNNdx);
  }
  private int vipNNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^3*/
	
	
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class SumIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SumIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    payAmtNdx = findColumn("payAmt");
  }
  public float payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(payAmtNdx);
  }
  private int payAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^54*/
	
	public BillPayDetailBean()
	{

	}
	
	public BillPayDetail sum(String startDate,String endDate,String shopC,String period, String shift,String table)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		BillPayDetail billPayDetail = new BillPayDetail();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:53^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  			    CASE
//  			        WHEN SUM(p.nPayAmt) IS NULL
//  			        THEN 0
//  			        ELSE SUM(p.nPayAmt)
//  			    END payAmt
//  				FROM
//  				    :Com_("d_t_food_bill") b
//  				LEFT JOIN
//  				    :Com_("d_t_bill_pay") p
//  				ON
//  				    b.cBill_C = p.cBill_C
//  				WHERE
//  					DBUSINESS >=TO_Date( :startDate, 'yyyy-MM-dd')
//              	AND DBUSINESS <=TO_Date( :endDate, 'yyyy-MM-dd')
//              	AND b.cBranch_C LIKE :shopC
//              	AND b.cPeriod_N LIKE :period
//              	AND b.cShift_N LIKE :shift
//              	AND b.cTable_N LIKE :table
//              	AND p.cPay_N IS NOT NULL
//  				ORDER BY
//  				    b.cBill_C,
//  				    p.nPayAmt };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = shopC;
  String __sJT_6 = period;
  String __sJT_7 = shift;
  String __sJT_8 = table;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillPayDetailBean_SJProfileKeys.getKey(0), 0);
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
      sumIterator = new SumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:76^18*/
			
			while(sumIterator.next()) {
				billPayDetail.setPayAmt(sumIterator.payAmt());
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return billPayDetail;
	}
	
	
	public int count(String startDate,String endDate,String shopC,String period,String shift,String table)
			throws NoPrivilegeException,SQLException,NoConnection	{
		DefaultContext myCtx = getDefaultContext();
		
		int counts = 0;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:99^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    COUNT(*) 
//  				    FROM
//  				        :Com_("d_t_food_bill") b
//  				    LEFT JOIN
//  				        :Com_("d_t_bill_pay") p
//  				    ON
//  				        b.cBill_C = p.cBill_C
//  				    WHERE
//  				    	DBUSINESS >=TO_Date( :startDate, 'yyyy-MM-dd')
//                  	AND DBUSINESS <=TO_Date( :endDate, 'yyyy-MM-dd')
//                  	AND b.cBranch_C LIKE :shopC
//                  	AND b.cPeriod_N LIKE :period
//                  	AND b.cShift_N LIKE :shift
//                  	AND b.cTable_N LIKE :table
//                  	AND p.cPay_N IS NOT NULL
//  			   };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = shopC;
  String __sJT_6 = period;
  String __sJT_7 = shift;
  String __sJT_8 = table;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillPayDetailBean_SJProfileKeys.getKey(0), 1);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:115^6*/
			
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public List<BillPayDetail> billPay(String startDate,String endDate,String shopC,String period,
																String shift,String table,int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<BillPayDetail> result = new ArrayList<BillPayDetail>();
		DefaultContext myCtx = getDefaultContext();
		BillPayDetailIterator billPayDetailIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:135^4*/

//  ************************************************************
//  #sql [myCtx] billPayDetailIterator = { SELECT
//  				    *
//  				    FROM
//  				        (
//  				            SELECT
//  				                t.*,
//  				                ROWNUM rowNumber
//  				            FROM
//  				                (SELECT
//  				                	    b.CBill_C                        billC,
//  				                	    cPay_N                           payN,
//  				                	    sUnit                            unit,
//  				                	    p.nPayAmt                        payAmt,
//  				                	    TO_CHAR(dBusiness, 'yyyy-MM-dd') businessDate,
//  				                	    b.cTable_N                       table,
//  				                	    b.cPeriod_N                      period,
//  				                	    b.cShift_N                       shift,
//  				                	    cBranch_C                        shopC,
//  				                	    cBranch_N                        shopN,
//  				                	    p.cVIP_c                         vipC,
//  				                	    p.cVIP_n                         vipN
//  				                	FROM
//  				                	    :Com_("d_t_food_bill") b
//  				                	LEFT JOIN
//  				                	    :Com_("d_t_bill_pay") p
//  				                	ON
//  				                	    b.cBill_C = p.cBill_C
//  				                	WHERE
//  				                	    DBUSINESS >=TO_Date( :startDate, 'yyyy-MM-dd')
//  				                	AND DBUSINESS <=TO_Date( :endDate, 'yyyy-MM-dd')
//  				                	AND b.cBranch_C LIKE :shopC
//  				                	AND b.cPeriod_N LIKE :period
//  				                	AND b.cShift_N LIKE :shift
//  				                	AND b.cTable_N LIKE :table
//  				                	AND p.cPay_N IS NOT NULL
//  				                	ORDER BY
//  				                	    b.cBill_C,
//  				                	    p.nPayAmt) t
//  				            WHERE
//  				                ROWNUM <= :endRow)
//  				    WHERE
//  				        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = shopC;
  String __sJT_6 = period;
  String __sJT_7 = shift;
  String __sJT_8 = table;
  int __sJT_9 = endRow;
  int __sJT_10 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillPayDetailBean_SJProfileKeys.getKey(0), 2);
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
      __sJT_stmt.setInt(9, __sJT_9);
      __sJT_stmt.setInt(10, __sJT_10);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      billPayDetailIterator = new BillPayDetailIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:177^35*/
			
			while(billPayDetailIterator.next()) {
				BillPayDetail billPayDetail = new BillPayDetail();
				billPayDetail.setBillC(billPayDetailIterator.billC());
				billPayDetail.setPayN(billPayDetailIterator.payN());
				billPayDetail.setUnit(billPayDetailIterator.unit());
				billPayDetail.setPayAmt(billPayDetailIterator.payAmt());
				billPayDetail.setBusinessDate(billPayDetailIterator.businessDate());
				billPayDetail.setTable(billPayDetailIterator.table());
				billPayDetail.setPeriod(billPayDetailIterator.period());
				billPayDetail.setShift(billPayDetailIterator.shift());
				billPayDetail.setShopC(billPayDetailIterator.shopC());
				billPayDetail.setShopN(billPayDetailIterator.shopN());
				billPayDetail.setVipC(billPayDetailIterator.vipC());
				billPayDetail.setVipN(billPayDetailIterator.vipN());
				
				result.add(billPayDetail);
			}
			billPayDetailIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class BillPayDetailBean_SJProfileKeys 
{
  private static BillPayDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BillPayDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BillPayDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.BillPayDetailBean_SJProfile0");
  }
}
