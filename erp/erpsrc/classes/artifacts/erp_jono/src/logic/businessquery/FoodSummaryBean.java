/*@lineinfo:filename=FoodSummaryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.FoodSummary;
import com.tanry.framework.util.TextUtil;

public class FoodSummaryBean extends ConnectionPool
{
	/*@lineinfo:generated-code*//*@lineinfo:21^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class FoodSummaryIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public FoodSummaryIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    foodCNdx = findColumn("foodC");
    foodNNdx = findColumn("foodN");
    unitNdx = findColumn("unit");
    qtyNdx = findColumn("qty");
    pesentQtyNdx = findColumn("pesentQty");
    returnQtyNdx = findColumn("returnQty");
  }
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
  public float qty() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(qtyNdx);
  }
  private int qtyNdx;
  public float pesentQty() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(pesentQtyNdx);
  }
  private int pesentQtyNdx;
  public float returnQty() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(returnQtyNdx);
  }
  private int returnQtyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:27^19*/
	
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
    pesentQtyNdx = findColumn("pesentQty");
    returnQtyNdx = findColumn("returnQty");
  }
  public float qty() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(qtyNdx);
  }
  private int qtyNdx;
  public float pesentQty() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(pesentQtyNdx);
  }
  private int pesentQtyNdx;
  public float returnQty() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(returnQtyNdx);
  }
  private int returnQtyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:32^19*/
	
	public FoodSummaryBean()
	{
	}
	
	public FoodSummary foodSummarySum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		String queryStr = "";
		if(!TextUtil.isEmpty(shopC)){
			queryStr =  "AND b.CBRANCH_C = '"+ shopC +"' ";
		}
		
		FoodSummary foodSummary = new FoodSummary();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:50^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  				    SUM(qty)       qty ,
//  				    SUM(pesentQty) pesentQty,
//  				    SUM(returnQty) returnQty
//  				FROM
//  				    (
//  				        SELECT
//  				        	bs.CFOOD_C foodC,
//  				            bs.cFood_N       foodN,
//  				            sUnit            unit,
//  				            SUM(nQty)        qty,
//  				            SUM(
//  				                CASE
//  				                    WHEN bs.eRetSendFlag = '赠送'
//  				                    THEN nQty
//  				                    ELSE 0
//  				                END) pesentQty,
//  				            SUM(
//  				                CASE
//  				                    WHEN bs.eRetSendFlag = '退品'
//  				                    THEN nQty
//  				                    ELSE 0
//  				                END) returnQty
//  				        FROM
//  				            :Com_("d_t_food_bills") bs
//  				        LEFT JOIN
//  				            :Com_("d_t_food_bill") b
//  				        ON
//  				            bs.cBill_C = b.cBill_C
//  				        WHERE
//  				            DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  				        AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  		        		:queryStr
//  				        AND bs.eSuitFlag <> '套餐'
//  				        GROUP BY
//  				        	bs.CFOOD_C ,
//  				            bs.cFood_N,
//  				            sUnit ) t
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
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSummaryBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:89^4*/
			
			while(sumIterator.next()) {

				try {
					foodSummary.qty = sumIterator.qty();
				} catch (Exception e) {
					foodSummary.qty = 0;
				}

				try {
					foodSummary.pesentQty = sumIterator.pesentQty();
				} catch (Exception e) {
					foodSummary.pesentQty = 0;
				}
				
				try {
					foodSummary.returnQty = sumIterator.returnQty();
				} catch (Exception e) {
					foodSummary.returnQty = 0;
				}
				
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return foodSummary;
	}
	
	
	public List<FoodSummary> foodSummary(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		String queryStr = "";
		
		List<FoodSummary> result = new ArrayList<FoodSummary>();
		DefaultContext myCtx = getDefaultContext();
		FoodSummaryIterator foodSummaryIterator = null;
		
		if(!TextUtil.isEmpty(shopC)){
			queryStr =  "AND b.CBRANCH_C = '"+ shopC +"' ";
		}
		
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:137^4*/

//  ************************************************************
//  #sql [myCtx] foodSummaryIterator = { SELECT
//  				    bs.CFOOD_C foodC,
//  				    bs.cFood_N                foodN,
//  				    sUnit                     unit,
//  				    SUM(nQty)                 qty,
//  				    SUM(
//  				        CASE
//  				            WHEN bs.eRetSendFlag = '赠送'
//  				            THEN nQty
//  				            ELSE 0
//  				        END) pesentQty,
//  				    SUM(
//  				        CASE
//  				            WHEN bs.eRetSendFlag = '退品'
//  				            THEN nQty
//  				            ELSE 0
//  				        END) returnQty
//  				FROM
//  				    :Com_("d_t_food_bills") bs
//  				LEFT JOIN
//  				    :Com_("d_t_food_bill") b
//  				ON
//  				    bs.cBill_C = b.cBill_C
//  				WHERE
//  					DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  				AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  				:queryStr
//  				AND bs.eSuitFlag <> '套餐'
//  				GROUP BY
//  					bs.CFOOD_C ,
//  				    bs.cFood_N,
//  				    sUnit
//  				ORDER BY
//  				   1 };
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
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodSummaryBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      foodSummaryIterator = new FoodSummaryIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:171^9*/
			
			while(foodSummaryIterator.next()) {
				FoodSummary foodSummary = new FoodSummary();
				foodSummary.foodC = foodSummaryIterator.foodC();
				foodSummary.foodN = foodSummaryIterator.foodN();
				foodSummary.unit = foodSummaryIterator.unit();
				foodSummary.qty = foodSummaryIterator.qty();
				foodSummary.pesentQty = foodSummaryIterator.pesentQty();
				foodSummary.returnQty = foodSummaryIterator.returnQty();
				result.add(foodSummary);
			}
			foodSummaryIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class FoodSummaryBean_SJProfileKeys 
{
  private static FoodSummaryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodSummaryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodSummaryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.FoodSummaryBean_SJProfile0");
  }
}
