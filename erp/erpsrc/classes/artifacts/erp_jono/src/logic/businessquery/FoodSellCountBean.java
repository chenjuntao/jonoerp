/*@lineinfo:filename=FoodSellCountBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.FoodSellCount;

public class FoodSellCountBean extends ConnectionPool
{
	/*@lineinfo:generated-code*//*@lineinfo:20^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class FoodSellCountIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public FoodSellCountIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    categoryIdNdx = findColumn("categoryId");
    categoryNNdx = findColumn("categoryN");
    qtyNdx = findColumn("qty");
    amtNdx = findColumn("amt");
    afterAmtNdx = findColumn("afterAmt");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
  }
  public String categoryId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(categoryIdNdx);
  }
  private int categoryIdNdx;
  public String categoryN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(categoryNNdx);
  }
  private int categoryNNdx;
  public int qty() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(qtyNdx);
  }
  private int qtyNdx;
  public float amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(amtNdx);
  }
  private int amtNdx;
  public float afterAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(afterAmtNdx);
  }
  private int afterAmtNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:27^16*/
	
	/*@lineinfo:generated-code*//*@lineinfo:29^2*/

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
    qtyNdx = findColumn("qty");
    amtNdx = findColumn("amt");
    afterAmtNdx = findColumn("afterAmt");
  }
  public int qty() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(qtyNdx);
  }
  private int qtyNdx;
  public float amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(amtNdx);
  }
  private int amtNdx;
  public float afterAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(afterAmtNdx);
  }
  private int afterAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:32^18*/
	
	public FoodSellCountBean()
	{
	}
	
	public FoodSellCount bigCategorySum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		FoodSellCount foodSellCount = new FoodSellCount();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:46^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  				    SUM(qty)      qty,
//  				    SUM(amt)      amt,
//  				    SUM(afterAmt) afterAmt
//  				FROM
//  				    (
//  				        SELECT
//  				            bs.cLitCls_c                categoryId,
//  				            bs.cLitCls_N                categoryN,
//  				            SUM(nQty)                   qty,
//  				            SUM(nAmt)                   amt,
//  				            SUM(nAmt) - SUM(bs.nDisAmt) afterAmt,
//  				            cBranch_C                   shopC,
//  				            cBranch_N                   shopN
//  				        FROM
//  				            :Com_("d_t_food_bills") bs
//  				        LEFT JOIN
//  				            :Com_("d_t_food_bill") b
//  				        ON
//  				            b.CBILL_C = bs.CBILL_C
//  				        WHERE
//  				            DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  				        AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  				        AND b.CBRANCH_C LIKE :shopC
//  				        AND bs.cLitCls_c IS NOT NULL
//  				        AND bs.cLitCls_N <> '套餐'
//  				        GROUP BY
//  				            bs.cLitCls_c ,
//  				            bs.cLitCls_N ,
//  				            b.cBranch_C,
//  				            b.cBranch_N ) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSellCountBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:76^32*/
			
			while(sumIterator.next()) {

				try {
					foodSellCount.qty = sumIterator.qty();
				} catch (Exception e) {
					foodSellCount.qty = 0;
				}
				
				try {
					foodSellCount.amt = sumIterator.amt();
				} catch (Exception e) {
					foodSellCount.amt = 0;
				}

				try {
					foodSellCount.afterAmt = sumIterator.afterAmt();
				} catch (Exception e) {
					foodSellCount.afterAmt = 0;
				}
				
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return foodSellCount;
	}
	
	public FoodSellCount littleCategorySum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		FoodSellCount foodSellCount = new FoodSellCount();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:117^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  				    SUM(qty)      qty,
//  				    SUM(amt)      amt,
//  				    SUM(afterAmt) afterAmt
//  				FROM
//  				    (
//  				        SELECT
//  				            bs.cLitCls_C                categoryId,
//  				            bs.cLitCls_N                categoryN,
//  				            SUM(nQty)                   qty,
//  				            SUM(nAmt)                   amt,
//  				            SUM(nAmt) - SUM(bs.nDisAmt) afterAmt,
//  				            cBranch_C                   shopC,
//  				            cBranch_N                   shopN
//  				        FROM
//  				            :Com_("d_t_food_bills") bs
//  				        LEFT JOIN
//  				            :Com_("d_t_food_bill") b
//  				        ON
//  				            b.CBILL_C = bs.CBILL_C
//  				        WHERE
//  				            DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  				        AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  				        AND b.CBRANCH_C LIKE :shopC
//  				        AND bs.cLitCls_C IS NOT NULL
//  				        AND bs.cLitCls_N <> '套餐'
//  				        GROUP BY
//  				            bs.cLitCls_C,
//  				            bs.cLitCls_N ,
//  				            b.cBranch_C,
//  				            b.cBranch_N) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSellCountBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:148^31*/
			
			while(sumIterator.next()) {

				try {
					foodSellCount.qty = sumIterator.qty();
				} catch (Exception e) {
					foodSellCount.qty = 0;
				}
				
				try {
					foodSellCount.amt = sumIterator.amt();
				} catch (Exception e) {
					foodSellCount.amt = 0;
				}

				try {
					foodSellCount.afterAmt = sumIterator.afterAmt();
				} catch (Exception e) {
					foodSellCount.afterAmt = 0;
				}
				
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return foodSellCount;
	}
	
	
	public List<FoodSellCount> bigCategory(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<FoodSellCount> result = new ArrayList<FoodSellCount>();
		DefaultContext myCtx = getDefaultContext();
		FoodSellCountIterator foodSellCountIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:190^4*/

//  ************************************************************
//  #sql [myCtx] foodSellCountIterator = { SELECT
//  				    bs.cLitCls_c                categoryId,
//  				    bs.cLitCls_N                categoryN,
//  				    SUM(nQty)                   qty,
//  				    SUM(nAmt)                   amt,
//  				    SUM(nAmt) - SUM(bs.nDisAmt) afterAmt,
//  				    cBranch_C                   shopC,
//  				    cBranch_N                   shopN
//  				FROM
//  				    :Com_("d_t_food_bills") bs
//  				LEFT JOIN
//  				    :Com_("d_t_food_bill") b
//  				ON
//  				    b.CBILL_C = bs.CBILL_C
//  				WHERE
//  				    DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  					AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  					AND b.CBRANCH_C LIKE :shopC
//  					AND bs.cLitCls_C IS NOT NULL
//  					AND bs.cLitCls_N <> '套餐'
//  				GROUP BY
//  				    bs.cLitCls_c ,
//  				    bs.cLitCls_N ,
//  				    b.cBranch_C,
//  				    b.cBranch_N
//  				ORDER BY
//  				    bs.cLitCls_C ,
//  				    b.CBRANCH_C
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSellCountBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      foodSellCountIterator = new FoodSellCountIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:219^4*/
			
			while(foodSellCountIterator.next()) {
				FoodSellCount foodSellCount = new FoodSellCount();
				foodSellCount.categoryId = foodSellCountIterator.categoryId();
				foodSellCount.categoryN = foodSellCountIterator.categoryN();
				foodSellCount.qty = foodSellCountIterator.qty();
				foodSellCount.amt = foodSellCountIterator.amt();
				foodSellCount.afterAmt = foodSellCountIterator.afterAmt();
				foodSellCount.shopC = foodSellCountIterator.shopC();
				foodSellCount.shopN = foodSellCountIterator.shopN();
				
				result.add(foodSellCount);
			}
			foodSellCountIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	public List<FoodSellCount> littleCategory(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<FoodSellCount> result = new ArrayList<FoodSellCount>();
		DefaultContext myCtx = getDefaultContext();
		FoodSellCountIterator foodSellCountIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:251^4*/

//  ************************************************************
//  #sql [myCtx] foodSellCountIterator = { SELECT
//  				    bs.cLitCls_C                categoryId,
//  				    bs.cLitCls_N                categoryN,
//  				    SUM(nQty)                   qty,
//  				    SUM(nAmt)                   amt,
//  				    SUM(nAmt) - SUM(bs.nDisAmt) afterAmt,
//  				    cBranch_C                   shopC,
//  				    cBranch_N                   shopN
//  				FROM
//  				    :Com_("d_t_food_bills") bs
//  				LEFT JOIN
//  				    :Com_("d_t_food_bill") b
//  				ON
//  				    b.CBILL_C = bs.CBILL_C
//  				WHERE
//  				
//  					DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  					AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  					AND b.CBRANCH_C LIKE :shopC
//  					AND bs.cLitCls_C IS NOT NULL
//  					AND bs.cLitCls_N <> '套餐'
//  				GROUP BY
//  				    bs.cLitCls_C,
//  				    bs.cLitCls_N ,
//  				    b.cBranch_C,
//  				    b.cBranch_N
//  				ORDER BY
//  				    bs.cLitCls_C,
//  				    b.CBRANCH_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSellCountBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      foodSellCountIterator = new FoodSellCountIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:280^20*/
			
			while(foodSellCountIterator.next()) {
				FoodSellCount foodSellCount = new FoodSellCount();
				foodSellCount.categoryId = foodSellCountIterator.categoryId();
				foodSellCount.categoryN = foodSellCountIterator.categoryN();
				foodSellCount.qty = foodSellCountIterator.qty();
				foodSellCount.amt = foodSellCountIterator.amt();
				foodSellCount.afterAmt = foodSellCountIterator.afterAmt();
				foodSellCount.shopC = foodSellCountIterator.shopC();
				foodSellCount.shopN = foodSellCountIterator.shopN();
				
				result.add(foodSellCount);
			}
			foodSellCountIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class FoodSellCountBean_SJProfileKeys 
{
  private static FoodSellCountBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodSellCountBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodSellCountBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.FoodSellCountBean_SJProfile0");
  }
}
