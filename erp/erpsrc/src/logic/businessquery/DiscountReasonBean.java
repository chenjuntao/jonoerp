/*@lineinfo:filename=DiscountReasonBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.DiscountReason;

import pojo.businessquery.FreeBill;

public class DiscountReasonBean extends ConnectionPool
{
	/*@lineinfo:generated-code*//*@lineinfo:22^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DiscountReasonIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DiscountReasonIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
    foodAmtNdx = findColumn("foodAmt");
    disAmtNdx = findColumn("disAmt");
    payAmtNdx = findColumn("payAmt");
    disWhyNdx = findColumn("disWhy");
  }
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
  public float foodAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(foodAmtNdx);
  }
  private int foodAmtNdx;
  public float disAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(disAmtNdx);
  }
  private int disAmtNdx;
  public float payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(payAmtNdx);
  }
  private int payAmtNdx;
  public String disWhy() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(disWhyNdx);
  }
  private int disWhyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:28^17*/
	
	/*@lineinfo:generated-code*//*@lineinfo:30^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DiscountReasonSumIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DiscountReasonSumIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    foodAmtNdx = findColumn("foodAmt");
    disAmtNdx = findColumn("disAmt");
    payAmtNdx = findColumn("payAmt");
  }
  public float foodAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(foodAmtNdx);
  }
  private int foodAmtNdx;
  public float disAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(disAmtNdx);
  }
  private int disAmtNdx;
  public float payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(payAmtNdx);
  }
  private int payAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:33^16*/
	
	public DiscountReasonBean()
	{
	}
	
	public DiscountReason discountReasonSum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		DiscountReason discountReason = new DiscountReason();
		DefaultContext myCtx = getDefaultContext();
		DiscountReasonSumIterator discountReasonSumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:47^4*/

//  ************************************************************
//  #sql [myCtx] discountReasonSumIterator = { SELECT
//  				    SUM(foodAmt)    foodAmt,
//  				    SUM(disAmt)     disAmt,
//  				    SUM(payAmt)     payAmt
//  				FROM
//  				    (
//  				        SELECT
//  				            cBranch_C        shopC,
//  				            cBranch_N        shopN,
//  				            SUM(nFoodAmt)    foodAmt,
//  				            b.CDISCURWHY     disWhy,
//  				            SUM(nDisAmt)     disAmt,
//  				            SUM(nPayAmt)     payAmt
//  				        FROM
//  				            :Com_("d_t_food_bill") b
//  				        WHERE
//  				            b.cBranch_C LIKE :shopC
//  				        AND nDisAmt > 0
//  				        AND DBUSINESS >= TO_Date(:startDate, 'yyyy-MM-dd')
//  				        AND DBUSINESS <= TO_Date(:endDate, 'yyyy-MM-dd')
//  				        GROUP BY
//  				            cBranch_C,
//  				            cBranch_N,
//  				            b.CDISCURWHY) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopC;
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DiscountReasonBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      discountReasonSumIterator = new DiscountReasonSumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:71^32*/
			
			while(discountReasonSumIterator.next()) {
				try {
					discountReason.foodAmt = discountReasonSumIterator.foodAmt();
				} catch (Exception e) {
					discountReason.foodAmt = 0;
				}
			
				try {
					discountReason.disAmt = discountReasonSumIterator.disAmt();
				} catch (Exception e) {
					discountReason.disAmt = 0;
				}
			
				try {
					discountReason.payAmt = discountReasonSumIterator.payAmt();
				} catch (Exception e) {
					discountReason.payAmt = 0;
				}
				
			}
			discountReasonSumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return discountReason;
	}
	
	public List<DiscountReason> discountReason(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<DiscountReason> result = new ArrayList<DiscountReason>();
		DefaultContext myCtx = getDefaultContext();
		DiscountReasonIterator discountReasonIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:111^4*/

//  ************************************************************
//  #sql [myCtx] discountReasonIterator = { SELECT
//  				    cBranch_C        shopC,
//  				    cBranch_N        shopN,
//  				    SUM(nFoodAmt)    foodAmt,
//  				    b.CDISCURWHY     disWhy,
//  				    SUM(nDisAmt)     disAmt,
//  				    SUM(nPayAmt)     payAmt
//  				FROM
//  				    :Com_("d_t_food_bill") b
//  				WHERE
//  				    b.cBranch_C LIKE :shopC
//  				AND nDisAmt > 0
//  				AND DBUSINESS >= TO_Date( :startDate, 'yyyy-MM-dd')
//  				AND DBUSINESS <= TO_Date( :endDate, 'yyyy-MM-dd')
//  				GROUP BY
//  				    cBranch_C,
//  				    cBranch_N,
//  				    b.CDISCURWHY
//  		     };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopC;
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DiscountReasonBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      discountReasonIterator = new DiscountReasonIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:130^7*/
			
			while(discountReasonIterator.next()) {
				DiscountReason discountReason = new DiscountReason();
				discountReason.shopC = discountReasonIterator.shopC();
				discountReason.shopN = discountReasonIterator.shopN();
				discountReason.foodAmt = discountReasonIterator.foodAmt();
				discountReason.disAmt = discountReasonIterator.disAmt();
				discountReason.payAmt = discountReasonIterator.payAmt();
				discountReason.disWhy = discountReasonIterator.disWhy();
				
				result.add(discountReason);
			}
			discountReasonIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class DiscountReasonBean_SJProfileKeys 
{
  private static DiscountReasonBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DiscountReasonBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DiscountReasonBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.DiscountReasonBean_SJProfile0");
  }
}
