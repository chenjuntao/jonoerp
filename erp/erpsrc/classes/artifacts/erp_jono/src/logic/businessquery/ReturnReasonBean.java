/*@lineinfo:filename=ReturnReasonBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.ReturnReason;

public class ReturnReasonBean extends ConnectionPool
{
	/*@lineinfo:generated-code*//*@lineinfo:20^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ReturnReasonIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ReturnReasonIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    returnAmountNdx = findColumn("returnAmount");
    returnAmtNdx = findColumn("returnAmt");
    returnWhyNdx = findColumn("returnWhy");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
  }
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

/*@lineinfo:user-code*//*@lineinfo:25^16*/
	
	/*@lineinfo:generated-code*//*@lineinfo:27^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ReturnReasonSumIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ReturnReasonSumIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    returnAmountNdx = findColumn("returnAmount");
    returnAmtNdx = findColumn("returnAmt");
  }
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

/*@lineinfo:user-code*//*@lineinfo:27^88*/
	
	public ReturnReasonBean()
	{
	}
	
	public ReturnReason returnReasonSum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		ReturnReason returnReason = new ReturnReason();
		DefaultContext myCtx = getDefaultContext();
		ReturnReasonSumIterator returnReasonSumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:41^4*/

//  ************************************************************
//  #sql [myCtx] returnReasonSumIterator = { SELECT
//  				   CASE
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
//  				        	SELECT
//  							    bs.sRetSendRemark returnWhy,
//  							    SUM( bs.nQty)     returnAmount,
//  							    SUM( bs.nAmt)     returnAmt,
//  							    b.CBRANCH_C       shopC,
//  							    b.CBRANCH_N       shopN
//  							FROM
//  							    :Com_("d_t_food_bill") b
//  							LEFT JOIN
//  							    :Com_("d_t_food_bills") bs
//  							ON
//  							    b.cBill_C = bs.cBill_C
//  							WHERE
//  							    b.CBRANCH_C LIKE :shopC
//  							AND bs.eRetSendFlag = '退品'
//  							AND bs.eSuitFlag <> '套餐子项'
//  							AND DBUSINESS >= TO_Date( :startDate, 'yyyy-MM-dd')
//  							AND DBUSINESS <= TO_Date( :endDate, 'yyyy-MM-dd')
//  							GROUP BY
//  							    bs.sRetSendRemark,
//  							    b.CBRANCH_C ,
//  							    b.CBRANCH_N
//  							ORDER BY
//  							    SUM( bs.nQty) DESC
//  						)t
//  				                
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_food_bills");
  String __sJT_3 = shopC;
  String __sJT_4 = startDate;
  String __sJT_5 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnReasonBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      returnReasonSumIterator = new ReturnReasonSumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:81^4*/
			
			while(returnReasonSumIterator.next()) {
				returnReason.returnAmount = returnReasonSumIterator.returnAmount();
				returnReason.returnAmt = returnReasonSumIterator.returnAmt();
			}
			returnReasonSumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return returnReason;
	}
	
	
	public List<ReturnReason> returnReason(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<ReturnReason> result = new ArrayList<ReturnReason>();
		DefaultContext myCtx = getDefaultContext();
		ReturnReasonIterator returnReasonIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:106^4*/

//  ************************************************************
//  #sql [myCtx] returnReasonIterator = { SELECT
//  				    bs.sRetSendRemark returnWhy,
//  				    SUM( bs.nQty)     returnAmount,
//  				    SUM( bs.nAmt)     returnAmt,
//  				    b.CBRANCH_C       shopC,
//  				    b.CBRANCH_N       shopN
//  				FROM
//  				    :Com_("d_t_food_bill") b
//  				LEFT JOIN
//  				    :Com_("d_t_food_bills") bs
//  				ON
//  				    b.cBill_C = bs.cBill_C
//  				WHERE
//  				    b.CBRANCH_C LIKE :shopC
//  				AND bs.eRetSendFlag = '退品'
//  				AND bs.eSuitFlag <> '套餐子项'
//  				AND DBUSINESS >= TO_Date( :startDate, 'yyyy-MM-dd')
//  				AND DBUSINESS <= TO_Date( :endDate, 'yyyy-MM-dd')
//  				GROUP BY
//  				    bs.sRetSendRemark,
//  				    b.CBRANCH_C ,
//  				    b.CBRANCH_N
//  				ORDER BY
//  				    SUM( bs.nQty) DESC
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_food_bills");
  String __sJT_3 = shopC;
  String __sJT_4 = startDate;
  String __sJT_5 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnReasonBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      returnReasonIterator = new ReturnReasonIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:131^4*/
			
			while(returnReasonIterator.next()) {
				ReturnReason returnReason = new ReturnReason();
				returnReason.returnAmount = returnReasonIterator.returnAmount();
				returnReason.returnAmt = returnReasonIterator.returnAmt();
				returnReason.returnWhy = returnReasonIterator.returnWhy();
				returnReason.shopC = returnReasonIterator.shopC();
				returnReason.shopN = returnReasonIterator.shopN();
				result.add(returnReason);
			}
			returnReasonIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class ReturnReasonBean_SJProfileKeys 
{
  private static ReturnReasonBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ReturnReasonBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ReturnReasonBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.ReturnReasonBean_SJProfile0");
  }
}
