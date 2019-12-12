/*@lineinfo:filename=DiscountBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
package logic.store;

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
import pojo.businessquery.DiscountBill;

import pojo.businessquery.FreeBill;

public class DiscountBean extends ConnectionPool{
	private String strDateFormat = "yyyy-MM-dd"; //设置日期格式

	/*@lineinfo:generated-code*//*@lineinfo:23^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DiscountIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DiscountIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    billCNdx = findColumn("billC");
    businessDateNdx = findColumn("businessDate");
    tableNdx = findColumn("table");
    shiftNdx = findColumn("shift");
    periodNdx = findColumn("period");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
    foodCNdx = findColumn("foodC");
    foodNNdx = findColumn("foodN");
    priceNdx = findColumn("price");
    qtyNdx = findColumn("qty");
    amtNdx = findColumn("amt");
    unitNdx = findColumn("unit");
    foodAmtNdx = findColumn("foodAmt");
    disAmtNdx = findColumn("disAmt");
    payAmtNdx = findColumn("payAmt");
    disManNdx = findColumn("disMan");
    disWhyNdx = findColumn("disWhy");
  }
  public String billC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(billCNdx);
  }
  private int billCNdx;
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
  public String shift() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shiftNdx);
  }
  private int shiftNdx;
  public String period() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(periodNdx);
  }
  private int periodNdx;
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
  public String foodC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(foodCNdx);
  }
  private int foodCNdx;
  public String foodN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(foodNNdx);
  }
  private int foodNNdx;
  public Double price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(priceNdx);
  }
  private int priceNdx;
  public Double qty() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(qtyNdx);
  }
  private int qtyNdx;
  public Double amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtNdx);
  }
  private int amtNdx;
  public String unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(unitNdx);
  }
  private int unitNdx;
  public Double foodAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(foodAmtNdx);
  }
  private int foodAmtNdx;
  public Double disAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(disAmtNdx);
  }
  private int disAmtNdx;
  public Double payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payAmtNdx);
  }
  private int payAmtNdx;
  public String disMan() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(disManNdx);
  }
  private int disManNdx;
  public String disWhy() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(disWhyNdx);
  }
  private int disWhyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^3*/
	
	public DiscountBean(){
	}
	
	public int getCount(String startDate,String endDate,String shopC,String period,String shift)
			throws NoPrivilegeException,SQLException,NoConnection	{
		int counts = 0;
		
		DefaultContext myCtx = getDefaultContext();
		
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:56^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    COUNT(*) 
//  				    FROM
//  				        (
//  				            SELECT
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				                cBranch_C                        shopC,
//  				                SUM(nFoodAmt)                    foodAmt,
//  				                cTable_N                         table,
//  				                cBill_C                          billC,
//  				                b.cPeriod_N                      period,
//  				                b.cShift_N                       shift,
//  				                b.CDISMANCUR_N                   disMan,
//  				                b.CDISCURWHY                     disWhy,
//  				                SUM(nDisAmt)                     disAmt,
//  				                SUM(nPayAmt)                     payAmt
//  				            FROM
//  				            :Com_("d_t_food_bill") b
//  				            WHERE
//  				            	b.cBranch_C LIKE :shopC
//  							AND b.CPERIOD_N LIKE :period
//  							AND b.CSHIFT_N LIKE  :shift
//  				            AND nDisAmt > 0
//  				            AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				                (
//  				                    SELECT
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				                    FROM
//  				                    :Com_("d_t_food_bill") d
//  				                    WHERE
//  				                    	DBUSINESS >= to_date(:startDate, 'yyyy-MM-dd')
//  							        AND DBUSINESS <= to_date(:endDate,'yyyy-MM-dd')
//  				                    GROUP BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  				            GROUP BY
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                cBranch_C,
//  				                cTable_N ,
//  				                cBill_C,
//  				                b.cPeriod_N ,
//  				                b.cShift_N,
//  				                b.CDISMANCUR_N,
//  				                b.CDISCURWHY ) t };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopC;
  String __sJT_3 = period;
  String __sJT_4 = shift;
  String __sJT_5 = Com_("d_t_food_bill");
  String __sJT_6 = startDate;
  String __sJT_7 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DiscountBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:99^37*/
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public List<DiscountBill> getDiscount(String startDate,String endDate,String shopC,String period,String shift,int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<DiscountBill> result = new ArrayList<DiscountBill>();
		DefaultContext myCtx = getDefaultContext();
		DiscountIterator discountIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:117^4*/

//  ************************************************************
//  #sql [myCtx] discountIterator = { SELECT
//  				    *
//  				    FROM
//  				        (
//  				            SELECT
//  				                t.*,
//  				                ROWNUM rowNumber
//  				            FROM
//  				                (
//  				                    SELECT
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				                        cBranch_C                        shopC,
//  				                        cBranch_N                        shopN,
//  				                        SUM(nFoodAmt)                    foodAmt,
//  				                        cTable_N                         table,
//  				                        cBill_C                          billC,
//  				                        b.cPeriod_N                      period,
//  				                        b.cShift_N                       shift ,
//  				                        b.CDISMANCUR_N                   disMan,
//  				                        b.CDISCURWHY                     disWhy,
//  				                        SUM(nDisAmt)                     disAmt,
//  				                        SUM(nPayAmt)                     payAmt
//  				                    FROM
//  				                    :Com_("d_t_food_bill") b
//  				                    WHERE
//  				                        b.cBranch_C LIKE :shopC
//  				                    AND b.CPERIOD_N LIKE :period
//  				                    AND b.CSHIFT_N LIKE :shift
//  				                    AND nDisAmt > 0
//  				                    AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				                        (
//  				                            SELECT
//  				                                TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				                            FROM
//  				                            :Com_("d_t_food_bill") d
//  				                            WHERE
//  				                                DBUSINESS >= to_date(:startDate, 'yyyy-MM-dd')
//  				                            AND DBUSINESS <= to_date(:endDate,'yyyy-MM-dd')
//  				                            GROUP BY
//  				                                TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  				                    GROUP BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                        cBranch_C,
//  				                        cBranch_N,
//  				                        cTable_N,
//  				                        cBill_C,
//  				                        b.cPeriod_N ,
//  				                        b.cShift_N,
//  				                        b.CDISMANCUR_N,
//  				                        b.CDISCURWHY
//  				                    ORDER BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                        cBill_C,
//  				                        cBranch_C,
//  				                        cTable_N
//  				                        ) t
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
  String __sJT_2 = shopC;
  String __sJT_3 = period;
  String __sJT_4 = shift;
  String __sJT_5 = Com_("d_t_food_bill");
  String __sJT_6 = startDate;
  String __sJT_7 = endDate;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DiscountBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      discountIterator = new DiscountIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:177^35*/
			
			while(discountIterator.next()) {
				DiscountBill discountBill = new DiscountBill();
				discountBill.businessDate = discountIterator.businessDate();
//				discountBill.shopC = discountIterator.shopC();
//				discountBill.shopN = discountIterator.shopN();
//				discountBill.foodAmt = discountIterator.foodAmt();
//				discountBill.table = discountIterator.table();
//				discountBill.billC = discountIterator.billC();
//				discountBill.period = discountIterator.period();
//				discountBill.shift = discountIterator.shift();
//				discountBill.disAmt = discountIterator.disAmt();
//				discountBill.payAmt = discountIterator.payAmt();
//				discountBill.disMan = discountIterator.disMan();
				discountBill.disWhy = discountIterator.disWhy();
				
				result.add(discountBill);
			}
			discountIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class DiscountBean_SJProfileKeys 
{
  private static DiscountBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DiscountBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DiscountBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.DiscountBean_SJProfile0");
  }
}
