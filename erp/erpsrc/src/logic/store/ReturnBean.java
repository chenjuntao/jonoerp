/*@lineinfo:filename=ReturnBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.ReturnBill;

public class ReturnBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd"; //设置日期格式

	/*@lineinfo:generated-code*//*@lineinfo:22^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ReturnBeanIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ReturnBeanIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    businessDateNdx = findColumn("businessDate");
    billCNdx = findColumn("billC");
    tableNdx = findColumn("table");
    periodNdx = findColumn("period");
    shiftNdx = findColumn("shift");
    foodBillNdx = findColumn("foodBill");
    foodCNdx = findColumn("foodC");
    foodNNdx = findColumn("foodN");
    unitNdx = findColumn("unit");
    priceNdx = findColumn("price");
    returnAmountNdx = findColumn("returnAmount");
    returnAmtNdx = findColumn("returnAmt");
    returnWhyNdx = findColumn("returnWhy");
    returnTimeNdx = findColumn("returnTime");
    backManNdx = findColumn("backMan");
    suitFlagNdx = findColumn("suitFlag");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
    smallCNdx = findColumn("smallC");
    smallNNdx = findColumn("smallN");
  }
  public String businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessDateNdx);
  }
  private int businessDateNdx;
  public String billC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(billCNdx);
  }
  private int billCNdx;
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
  public String foodBill() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(foodBillNdx);
  }
  private int foodBillNdx;
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
  public String unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(unitNdx);
  }
  private int unitNdx;
  public float price() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(priceNdx);
  }
  private int priceNdx;
  public float returnAmount() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(returnAmountNdx);
  }
  private int returnAmountNdx;
  public float returnAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(returnAmtNdx);
  }
  private int returnAmtNdx;
  public String returnWhy() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(returnWhyNdx);
  }
  private int returnWhyNdx;
  public String returnTime() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(returnTimeNdx);
  }
  private int returnTimeNdx;
  public String backMan() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(backManNdx);
  }
  private int backManNdx;
  public String suitFlag() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(suitFlagNdx);
  }
  private int suitFlagNdx;
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
  public String smallC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(smallCNdx);
  }
  private int smallCNdx;
  public String smallN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(smallNNdx);
  }
  private int smallNNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:26^60*/
	
	/*@lineinfo:generated-code*//*@lineinfo:28^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ReturnSumIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ReturnSumIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    priceNdx = findColumn("price");
    returnAmountNdx = findColumn("returnAmount");
    returnAmtNdx = findColumn("returnAmt");
  }
  public float price() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(priceNdx);
  }
  private int priceNdx;
  public float returnAmount() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(returnAmountNdx);
  }
  private int returnAmountNdx;
  public float returnAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(returnAmtNdx);
  }
  private int returnAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:28^94*/
	
	public ReturnBean()
	{
	}
	
	public ReturnBill getReturnSum(String startDate,String endDate,String shopC,String period, String shift)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		ReturnBill returnBean = new ReturnBill();
		DefaultContext myCtx = getDefaultContext();
		ReturnSumIterator returnSumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:42^4*/

//  ************************************************************
//  #sql [myCtx] returnSumIterator = { SELECT
//  					  CASE
//  				        WHEN SUM(price) IS NULL
//  				        THEN 0
//  				        ELSE SUM(price)
//  				    END price,
//  				    CASE
//  				        WHEN SUM(returnAmount) IS NULL
//  				        THEN 0
//  				        ELSE SUM(returnAmount)
//  				    END returnAmount,
//  				    CASE
//  				        WHEN SUM(returnAmt) IS NULL
//  				        THEN 0
//  				        ELSE SUM(returnAmt)
//  				    END returnAmt
//  				    FROM
//  				        (
//  				            SELECT
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				                b.cBill_C                        billC,
//  				                b.cTable_N                       table,
//  				                b.cPeriod_N                      period,
//  				                b.cShift_N                       shift,
//  				                bs.cFood_C                       foodC,
//  				                bs.sUnit                         unit,
//  				                bs.nPrc                          price,
//  				                bs.nQty                          returnAmount,
//  				                bs.nAmt                          returnAmt,
//  				                bs.sRetSendRemark                returnWhy,
//  				                bs.cPresentBackMan               backMan,
//  				                bs.eSuitFlag                     suitFlag,
//  				                b.cBranch_C                      shopC
//  				            FROM
//  				                :Com_("d_t_food_bill") b
//  				            LEFT JOIN
//  				            	:Com_("d_t_food_Bills") bs
//  				            ON
//  				                b.cBill_C = bs.cBill_C
//  				            WHERE
//  				            	b.CBRANCH_C like :shopC
//  						    and b.CPERIOD_N like :period
//  						    and b.CSHIFT_N like :shift
//  				            AND bs.eRetSendFlag = '退品'
//  		            		AND bs.eSuitFlag <> '套餐子项'
//  				            AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				                (
//  				                    SELECT
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				                    FROM
//  				                    :Com_("d_t_food_bill") d
//  				                    WHERE
//  				                        DBUSINESS >= TO_Date( :startDate, 'yyyy-MM-dd')
//  				                    AND DBUSINESS <= TO_Date( :endDate, 'yyyy-MM-dd')
//  				                    GROUP BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  				            GROUP BY
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                b.cTable_N ,
//  				                b.cBill_C ,
//  				                b.cPeriod_N ,
//  				                b.cShift_N ,
//  				                bs.cFood_C ,
//  				                bs.sUnit ,
//  				                bs.nPrc ,
//  				                bs.nQty ,
//  				                bs.nAmt ,
//  				                bs.sRetSendRemark ,
//  				                bs.cPresentBackMan ,
//  				                bs.eSuitFlag ,
//  				                b.cBranch_C)t
//  				                
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_food_Bills");
  String __sJT_3 = shopC;
  String __sJT_4 = period;
  String __sJT_5 = shift;
  String __sJT_6 = Com_("d_t_food_bill");
  String __sJT_7 = startDate;
  String __sJT_8 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnBean_SJProfileKeys.getKey(0), 0);
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
      returnSumIterator = new ReturnSumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:115^4*/
			
			while(returnSumIterator.next()) {
				returnBean.price = returnSumIterator.price();
				returnBean.returnAmount = returnSumIterator.returnAmount();
				returnBean.returnAmt = returnSumIterator.returnAmt();
			}
			returnSumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return returnBean;
	}
	
	public int getHeadCount(String startDate,String endDate,String shopC,String period,String shift)
			throws NoPrivilegeException,SQLException,NoConnection	{
		int counts = 0;
		
		DefaultContext myCtx = getDefaultContext();
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:139^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    COUNT(*) 
//  				    FROM
//  				        (
//  				            SELECT
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				                b.cBill_C                        billC,
//  				                b.cTable_N                       table,
//  				                b.cPeriod_N                      period,
//  				                b.cShift_N                       shift,
//  				                bs.cFood_C                       foodC,
//  				                bs.sUnit                         unit,
//  				                bs.nPrc                          price,
//  				                bs.nQty                          returnAmount,
//  				                bs.nAmt                          returnAmt,
//  				                bs.sRetSendRemark                returnWhy,
//  				                bs.cPresentBackMan               backMan,
//  				                bs.eSuitFlag                     suitFlag,
//  				                b.cBranch_C                      shopC
//  				            FROM
//  				            :Com_("d_t_food_bill") b
//  				            LEFT JOIN
//  				            :Com_("d_t_food_Bills") bs
//  				            ON
//  				                b.cBill_C = bs.cBill_C
//  				            WHERE
//  				            	b.CBRANCH_C like :shopC
//  						    and b.CPERIOD_N like :period
//  						    and b.CSHIFT_N like :shift
//  				            AND bs.eRetSendFlag = '退品'
//  		            		AND bs.eSuitFlag <> '套餐子项'
//  				            AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				                (
//  				                    SELECT
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				                    FROM
//  				                    :Com_("d_t_food_bill") d
//  				                    WHERE
//  				                        DBUSINESS >= TO_Date( :startDate, 'yyyy-MM-dd')
//  				                    AND DBUSINESS <= TO_Date( :endDate, 'yyyy-MM-dd')
//  				                    GROUP BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  				            GROUP BY
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                b.cTable_N ,
//  				                b.cBill_C ,
//  				                b.cPeriod_N ,
//  				                b.cShift_N ,
//  				                bs.cFood_C ,
//  				                bs.sUnit ,
//  				                bs.nPrc ,
//  				                bs.nQty ,
//  				                bs.nAmt ,
//  				                bs.sRetSendRemark ,
//  				                bs.cPresentBackMan ,
//  				                bs.eSuitFlag ,
//  				                b.cBranch_C)t };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_food_Bills");
  String __sJT_3 = shopC;
  String __sJT_4 = period;
  String __sJT_5 = shift;
  String __sJT_6 = Com_("d_t_food_bill");
  String __sJT_7 = startDate;
  String __sJT_8 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:196^34*/
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public List<ReturnBill> getReturn(String startDate,String endDate,String shopC,String period,String shift,int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<ReturnBill> result = new ArrayList<ReturnBill>();
		DefaultContext myCtx = getDefaultContext();
		ReturnBeanIterator returnBeanIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:214^4*/

//  ************************************************************
//  #sql [myCtx] returnBeanIterator = { SELECT
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
//  				                        b.cBill_C                        billC,
//  				                        b.cTable_N                       table,
//  				                        b.cPeriod_N                      period,
//  				                        b.cShift_N                       shift,
//  				                        bs.cFood_C                       foodC,
//  				                        bs.cFood_N                       foodN,
//  				                        bs.sUnit                         unit,
//  				                        bs.nPrc                          price,
//  				                        bs.nQty                          returnAmount,
//  				                        bs.nAmt                          returnAmt,
//  				                        bs.sRetSendRemark                returnWhy,
//  				                        bs.cPresentBackMan	             backMan,
//  				                        bs.eSuitFlag                     suitFlag,
//  				                        b.cBranch_C                      shopC,
//  				                        b.cBranch_N                      shopN,
//  				                        cLitCls_C                        smallC,
//  				                        cLitCls_N                        smallN
//  				                    FROM
//  				                    :Com_("d_t_food_bill") b
//  				                    LEFT JOIN
//  				                    :Com_("d_t_food_Bills") bs
//  				                    ON
//  				                        b.cBill_C = bs.cBill_C
//  				                    WHERE
//  				                        b.CBRANCH_C LIKE :shopC
//  				                    AND b.CPERIOD_N LIKE :period
//  				                    AND b.CSHIFT_N LIKE :shift
//  				                    AND bs.eRetSendFlag = '退品'
//  	                    		    AND bs.eSuitFlag <> '套餐子项'
//  				                    AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				                        (
//  				                            SELECT
//  				                                TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				                            FROM
//  				                            :Com_("d_t_food_bill") d
//  				                            WHERE
//  				                                DBUSINESS >= TO_Date( :startDate, 'yyyy-MM-dd')
//  				                            AND DBUSINESS <= TO_Date( :endDate, 'yyyy-MM-dd')
//  				                            GROUP BY
//  				                                TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  				                    GROUP BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                        b.cTable_N ,
//  				                        b.cBill_C ,
//  				                        b.cPeriod_N ,
//  				                        b.cShift_N ,
//  				                        bs.cFood_C ,
//  				                        bs.cFood_N ,
//  				                        bs.sUnit ,
//  				                        bs.nPrc ,
//  				                        bs.nQty ,
//  				                        bs.nAmt ,
//  				                        bs.sRetSendRemark ,
//  				                        bs.cPresentBackMan ,
//  				                        bs.eSuitFlag ,
//  				                        b.cBranch_C ,
//  				                        b.cBranch_N ,
//  				                        cLitCls_C,
//  				                        cLitCls_N                        
//  				                    ORDER BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                        b.cBranch_C,
//  				                        b.cBill_C,
//  				                        b.cTable_N,
//  				                        b.cPeriod_N,
//  				                        b.cShift_N,
//  				                        bs.cFood_C) t
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
  String __sJT_2 = Com_("d_t_food_Bills");
  String __sJT_3 = shopC;
  String __sJT_4 = period;
  String __sJT_5 = shift;
  String __sJT_6 = Com_("d_t_food_bill");
  String __sJT_7 = startDate;
  String __sJT_8 = endDate;
  int __sJT_9 = endRow;
  int __sJT_10 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnBean_SJProfileKeys.getKey(0), 2);
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
      returnBeanIterator = new ReturnBeanIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:296^35*/
			
			while(returnBeanIterator.next()) {
				ReturnBill returnBean = new ReturnBill();
				returnBean.businessDate = returnBeanIterator.businessDate();
				returnBean.billC = returnBeanIterator.billC();
				returnBean.table = returnBeanIterator.table();
				returnBean.period = returnBeanIterator.period();
				returnBean.shift = returnBeanIterator.shift();
				returnBean.foodBill = returnBeanIterator.foodBill();
				returnBean.foodC = returnBeanIterator.foodC();
				returnBean.foodN = returnBeanIterator.foodN();
				returnBean.unit = returnBeanIterator.unit();
				returnBean.price = returnBeanIterator.price();
				returnBean.returnAmount = returnBeanIterator.returnAmount();
				returnBean.returnAmt = returnBeanIterator.returnAmt();
				returnBean.returnWhy = returnBeanIterator.returnWhy();
				returnBean.returnTime = returnBeanIterator.returnTime();
				returnBean.backMan = returnBeanIterator.backMan();
				returnBean.suitFlag = returnBeanIterator.suitFlag();
				returnBean.shopC = returnBeanIterator.shopC();
				returnBean.shopN = returnBeanIterator.shopN();
				returnBean.smallC = returnBeanIterator.smallC();
				returnBean.smallN = returnBeanIterator.smallN();
				
				result.add(returnBean);
			}
			returnBeanIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class ReturnBean_SJProfileKeys 
{
  private static ReturnBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ReturnBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ReturnBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.ReturnBean_SJProfile0");
  }
}
