/*@lineinfo:filename=PresentReasonBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.PresentReason;

public class PresentReasonBean extends ConnectionPool
{
	/*@lineinfo:generated-code*//*@lineinfo:20^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PresentReasonIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PresentReasonIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    presentAmountNdx = findColumn("presentAmount");
    presentPriceNdx = findColumn("presentPrice");
    presentWhyNdx = findColumn("presentWhy");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
  }
  public float presentAmount() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(presentAmountNdx);
  }
  private int presentAmountNdx;
  public float presentPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(presentPriceNdx);
  }
  private int presentPriceNdx;
  public String presentWhy() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(presentWhyNdx);
  }
  private int presentWhyNdx;
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

/*@lineinfo:user-code*//*@lineinfo:26^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:28^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PresentReasonSumIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PresentReasonSumIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    presentAmountNdx = findColumn("presentAmount");
    presentPriceNdx = findColumn("presentPrice");
  }
  public float presentAmount() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(presentAmountNdx);
  }
  private int presentAmountNdx;
  public float presentPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(presentPriceNdx);
  }
  private int presentPriceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:28^93*/
	
	public PresentReasonBean()
	{
	}
	
	public PresentReason presentReasonSum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		PresentReason presentReason = new PresentReason();
		DefaultContext myCtx = getDefaultContext();
		PresentReasonSumIterator presentReasonSumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:42^4*/

//  ************************************************************
//  #sql [myCtx] presentReasonSumIterator = { SELECT
//  			    CASE
//  			        WHEN SUM(presentAmount)IS NULL
//  			        THEN 0
//  			        ELSE SUM(presentAmount)
//  			    END presentAmount ,
//  			    CASE
//  			        WHEN SUM(presentPrice)IS NULL
//  			        THEN 0
//  			        ELSE SUM(presentPrice)
//  			    END presentPrice
//  				FROM
//  				    (
//  				        SELECT
//  				            b.cBranch_C       shopC,
//  				            b.cBranch_N       shopN,
//  				            SUM( bs.nQty)     presentAmount ,
//  				            SUM(bs.nAmt )     presentPrice,
//  				            bs.sRetSendRemark presentWhy
//  				        FROM
//  				            :Com_("d_t_food_bill") b
//  				        LEFT JOIN
//  				            :Com_("d_t_food_bills") bs
//  				        ON
//  				            b.cBill_C = bs.cBill_C
//  				        WHERE
//  				            b.cBranch_C LIKE :shopC
//  				        AND bs.eRetSendFlag = '赠送'
//  				        AND DBUSINESS >=TO_Date( :startDate, 'yyyy-MM-dd')
//  				        AND DBUSINESS <= TO_Date(:endDate, 'yyyy-MM-dd')
//  				        GROUP BY
//  				            b.cBranch_C,
//  				            b.cBranch_N ,
//  				            bs.sRetSendRemark) t };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PresentReasonBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      presentReasonSumIterator = new PresentReasonSumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:76^37*/
			
			while(presentReasonSumIterator.next()) {
				presentReason.presentAmount = presentReasonSumIterator.presentAmount();
				presentReason.presentPrice = presentReasonSumIterator.presentPrice();
			}
			presentReasonSumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return presentReason;
	}
	
	public List<PresentReason> presentReason(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<PresentReason> result = new ArrayList<PresentReason>();
		DefaultContext myCtx = getDefaultContext();
		PresentReasonIterator presentReasonIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:100^4*/

//  ************************************************************
//  #sql [myCtx] presentReasonIterator = { SELECT
//  				    b.cBranch_C       shopC,
//  				    b.cBranch_N       shopN,
//  				    SUM( bs.nQty)     presentAmount ,
//  				    SUM(bs.nAmt )     presentPrice,
//  				    bs.sRetSendRemark presentWhy
//  				FROM
//  		            :Com_("d_t_food_bill") b
//  		        LEFT JOIN
//  		            :Com_("d_t_food_bills") bs
//  				ON
//  				    b.cBill_C = bs.cBill_C
//  				WHERE
//  				    b.cBranch_C LIKE :shopC
//  				AND bs.eRetSendFlag = '赠送'
//  				AND DBUSINESS >=TO_Date( :startDate, 'yyyy-MM-dd')
//  				AND DBUSINESS <= TO_Date(:endDate, 'yyyy-MM-dd')
//  				GROUP BY
//  				    b.cBranch_C,
//  				    b.cBranch_N ,
//  				    bs.sRetSendRemark };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PresentReasonBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      presentReasonIterator = new PresentReasonIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:121^26*/
			
			while(presentReasonIterator.next()) {
				PresentReason presentReason = new PresentReason();
				presentReason.presentAmount = presentReasonIterator.presentAmount();
				presentReason.presentPrice = presentReasonIterator.presentPrice();
				presentReason.presentWhy = presentReasonIterator.presentWhy();
				presentReason.shopC = presentReasonIterator.shopC();
				presentReason.shopN = presentReasonIterator.shopN();
				result.add(presentReason);
			}
			presentReasonIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class PresentReasonBean_SJProfileKeys 
{
  private static PresentReasonBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PresentReasonBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PresentReasonBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.PresentReasonBean_SJProfile0");
  }
}
