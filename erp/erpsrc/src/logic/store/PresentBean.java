/*@lineinfo:filename=PresentBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.PresentBill;

public class PresentBean extends ConnectionPool
{
	/**
	 * -------------detail--------------------
	 */
	/*@lineinfo:generated-code*//*@lineinfo:23^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DetailIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DetailIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    businessDateNdx = findColumn("businessDate");
    tableNdx = findColumn("table");
    billCNdx = findColumn("billC");
    periodNdx = findColumn("period");
    shiftNdx = findColumn("shift");
    foodNNdx = findColumn("foodN");
    unitNdx = findColumn("unit");
    presentAmountNdx = findColumn("presentAmount");
    priceNdx = findColumn("price");
    presentPriceNdx = findColumn("presentPrice");
    extracPriceNdx = findColumn("extracPrice");
    presentManNdx = findColumn("presentMan");
    presentWhyNdx = findColumn("presentWhy");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
    bigCNdx = findColumn("bigC");
    bigNNdx = findColumn("bigN");
    smallCNdx = findColumn("smallC");
    smallNNdx = findColumn("smallN");
  }
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
  public String billC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(billCNdx);
  }
  private int billCNdx;
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
  public float presentAmount() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(presentAmountNdx);
  }
  private int presentAmountNdx;
  public float price() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(priceNdx);
  }
  private int priceNdx;
  public float presentPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(presentPriceNdx);
  }
  private int presentPriceNdx;
  public float extracPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(extracPriceNdx);
  }
  private int extracPriceNdx;
  public String presentMan() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(presentManNdx);
  }
  private int presentManNdx;
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
  public String bigC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(bigCNdx);
  }
  private int bigCNdx;
  public String bigN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(bigNNdx);
  }
  private int bigNNdx;
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

/*@lineinfo:user-code*//*@lineinfo:43^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:45^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DetailSumIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DetailSumIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    presentAmountNdx = findColumn("presentAmount");
    presentPriceNdx = findColumn("presentPrice");
    extracPriceNdx = findColumn("extracPrice");
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
  public float extracPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(extracPriceNdx);
  }
  private int extracPriceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:45^104*/
	
	/**
	 * -------------head--------------------
	 */
	/*@lineinfo:generated-code*//*@lineinfo:50^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class HeadIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeadIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    businessDateNdx = findColumn("businessDate");
    tableNdx = findColumn("table");
    billCNdx = findColumn("billC");
    periodNdx = findColumn("period");
    shiftNdx = findColumn("shift");
    presentAmountNdx = findColumn("presentAmount");
    presentPriceNdx = findColumn("presentPrice");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
  }
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
  public String billC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(billCNdx);
  }
  private int billCNdx;
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

/*@lineinfo:user-code*//*@lineinfo:60^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:62^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class HeadSumIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeadSumIterator(sqlj.runtime.profile.RTResultSet resultSet) 
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

/*@lineinfo:user-code*//*@lineinfo:62^84*/
	
	public PresentBean()
	{
	}
	
	public PresentBill getDetailSum(String startDate,String endDate,String shopC,String period, String shift)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		PresentBill presentBean = new PresentBill();
		DefaultContext myCtx = getDefaultContext();
		DetailSumIterator detailSumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:76^4*/

//  ************************************************************
//  #sql [myCtx] detailSumIterator = { SELECT
//  						  CASE
//  					        WHEN SUM(presentAmount) IS NULL
//  					        THEN 0
//  					        ELSE SUM(presentAmount)
//  					    END presentAmount,
//  					    CASE
//  					        WHEN SUM(presentPrice) IS NULL
//  					        THEN 0
//  					        ELSE SUM(presentPrice)
//  					    END presentPrice,
//  					    CASE
//  					        WHEN SUM(extracPrice) IS NULL
//  					        THEN 0
//  					        ELSE SUM(extracPrice)
//  					    END extracPrice
//  				    FROM
//  				        (
//  				            SELECT
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				                b.cBranch_C                      shopC,
//  				                b.cTable_N                       table,
//  				                b.cBill_C                        billC,
//  				                bs.cFood_C                       foodC,
//  				                b.cPeriod_N,
//  				                b.cShift_N,
//  				                bs.sUnit           unit,
//  				                bs.nQty            presentAmount ,
//  				                bs.nPrc            price,
//  				                bs.nAmt            presentPrice,
//  				                bs.cPresentBackMan 	   presentMan,
//  				                bs.sRetSendRemark  presentWhy
//  				            FROM
//  				            :Com_("d_t_food_bill") b
//  				            LEFT JOIN
//  				            :Com_("d_t_food_Bills") bs
//  				            ON
//  				                b.cBill_C = bs.cBill_C
//  				            WHERE
//  				            	b.cBranch_C LIKE :shopC
//  		                    AND b.CPERIOD_N LIKE :period
//  		                    AND b.CSHIFT_N LIKE :shift
//  				            AND bs.eRetSendFlag = '赠送'
//  				            AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				                (
//  				                    SELECT
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				                    FROM
//  				                    :Com_("d_t_food_bill") d
//  				                    WHERE
//  				                        DBUSINESS >= to_date(:startDate, 'yyyy-MM-dd')
//  				                    AND DBUSINESS <= to_date(:endDate,'yyyy-MM-dd')
//  				                    GROUP BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  				            GROUP BY
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                b.cBranch_C,
//  				                b.cTable_N,
//  				                b.cBill_C,
//  				                bs.cFood_C ,
//  				                b.cPeriod_N,
//  				                b.cShift_N,
//  				                bs.sUnit ,
//  				                bs.nQty ,
//  				                bs.nPrc ,
//  				                bs.nAmt ,
//  				                bs.cPresentBackMan ,
//  				                bs.sRetSendRemark ) t
//  				 };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PresentBean_SJProfileKeys.getKey(0), 0);
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
      detailSumIterator = new DetailSumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:145^5*/
			
			while(detailSumIterator.next()) {
				presentBean.presentAmount = detailSumIterator.presentAmount();
				presentBean.presentPrice = detailSumIterator.presentPrice();
				presentBean.extracPrice = detailSumIterator.extracPrice();
			}
			detailSumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return presentBean;
	}
	
	public PresentBill getHeadSum(String startDate,String endDate,String shopC,String period, String shift)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		PresentBill presentBean = new PresentBill();
		DefaultContext myCtx = getDefaultContext();
		HeadSumIterator headSumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:170^4*/

//  ************************************************************
//  #sql [myCtx] headSumIterator = { SELECT
//  					CASE
//  						WHEN SUM(presentAmount) IS NULL
//  					THEN 0
//  						ELSE SUM(presentAmount)
//  					END presentAmount,
//  					CASE
//  						WHEN SUM(presentPrice) IS NULL
//  					THEN 0
//  						ELSE SUM(presentPrice)
//  					END presentPrice
//  					FROM
//  					(
//  					        SELECT
//  					        TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				            b.cBranch_C                            shopC,
//  				            b.cBranch_N                            shopN,
//  				            b.cTable_N                             table,
//  				            b.cBill_C                              billC,
//  				            b.cPeriod_N                            period,
//  				            b.cShift_N                             shift,
//  				            SUM(NVL( bs.nQty,0)) presentAmount ,
//  				            SUM(NVL(bs.nAmt,0))  presentPrice
//  				        FROM
//  				        :Com_("d_t_food_bill") b
//  				        LEFT JOIN
//  				        :Com_("d_t_food_Bills") bs
//  				        ON
//  				            b.cBill_C = bs.cBill_C
//  				        WHERE
//  				        	b.cBranch_C LIKE :shopC
//  						AND b.CPERIOD_N LIKE :period
//  						AND b.CSHIFT_N LIKE :shift
//  						AND bs.eRetSendFlag = '赠送'
//  						AND bs.eSuitFlag <> '套餐子项'
//  						AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  						(
//  								SELECT
//  								TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  								FROM
//  								:Com_("d_t_food_bill") d
//  								WHERE
//  								DBUSINESS >= TO_Date(:startDate, 'yyyy-MM-dd')
//  								AND DBUSINESS <= TO_Date(:endDate, 'yyyy-MM-dd')
//  								GROUP BY
//  								TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  				        GROUP BY
//  				        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				            b.cBranch_C,
//  				            b.cBranch_N,
//  				            table,
//  				            b.cBill_C,
//  				            period,
//  				            shift
//  			       ) t
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PresentBean_SJProfileKeys.getKey(0), 1);
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
      headSumIterator = new HeadSumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:226^4*/
			
			while(headSumIterator.next()) {
				presentBean.presentAmount = headSumIterator.presentAmount();
				presentBean.presentPrice = headSumIterator.presentPrice();
			}
			headSumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return presentBean;
	}
	
	public int getDetailCount(String startDate,String endDate,String shopC,String period,String shift)
			throws NoPrivilegeException,SQLException,NoConnection	{
		int counts = 0;
		
		DefaultContext myCtx = getDefaultContext();
		
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:250^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    COUNT( *) 
//  				    FROM
//  				        (
//  				            SELECT
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				                b.cBranch_C                      shopC,
//  				                b.cTable_N                       table,
//  				                b.cBill_C                        billC,
//  				                bs.cFood_C                       foodC,
//  				                b.cPeriod_N,
//  				                b.cShift_N,
//  				                bs.sUnit           unit,
//  				                bs.nQty            presentAmount ,
//  				                bs.nPrc            price,
//  				                bs.nAmt            presentPrice,
//  				                bs.cPresentBackMan 	   presentMan,
//  				                bs.sRetSendRemark  presentWhy
//  				            FROM
//  				            :Com_("d_t_food_bill") b
//  				            LEFT JOIN
//  				            :Com_("d_t_food_Bills") bs
//  				            ON
//  				                b.cBill_C = bs.cBill_C
//  				            WHERE
//  				            	b.cBranch_C LIKE :shopC
//  		                    AND b.CPERIOD_N LIKE :period
//  		                    AND b.CSHIFT_N LIKE :shift
//  				            AND bs.eRetSendFlag = '赠送'
//  				            AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				                (
//  				                    SELECT
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				                    FROM
//  				                    :Com_("d_t_food_bill") d
//  				                    WHERE
//  				                        DBUSINESS >= to_date(:startDate, 'yyyy-MM-dd')
//  				                    AND DBUSINESS <= to_date(:endDate,'yyyy-MM-dd')
//  				                    GROUP BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  				            GROUP BY
//  				                TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                b.cBranch_C,
//  				                b.cTable_N,
//  				                b.cBill_C,
//  				                bs.cFood_C ,
//  				                b.cPeriod_N,
//  				                b.cShift_N,
//  				                bs.sUnit ,
//  				                bs.nQty ,
//  				                bs.nPrc ,
//  				                bs.nAmt ,
//  				                bs.cPresentBackMan ,
//  				                bs.sRetSendRemark ) t
//  				 };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PresentBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:305^5*/
			
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public int getHeadCount(String startDate,String endDate,String shopC,String period,String shift)
			throws NoPrivilegeException,SQLException,NoConnection	{
		int counts = 0;
		
		DefaultContext myCtx = getDefaultContext();
		
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:324^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				COUNT( *) 
//  				FROM
//  				(
//  				        SELECT
//  				        TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  			            b.cBranch_C                            shopC,
//  			            b.cBranch_N                            shopN,
//  			            b.cTable_N                             table,
//  			            b.cBill_C                              billC,
//  			            b.cPeriod_N                            period,
//  			            b.cShift_N                             shift,
//  			            SUM(NVL( bs.nQty,0)) presentAmount ,
//  			            SUM(NVL(bs.nAmt,0))  presentPrice
//  			        FROM
//  			        :Com_("d_t_food_bill") b
//  			        LEFT JOIN
//  			        :Com_("d_t_food_Bills") bs
//  			        ON
//  			            b.cBill_C = bs.cBill_C
//  			        WHERE
//  			        	b.cBranch_C LIKE :shopC
//  					AND b.CPERIOD_N LIKE :period
//  					AND b.CSHIFT_N LIKE :shift
//  					AND bs.eRetSendFlag = '赠送'
//  					AND bs.eSuitFlag <> '套餐子项'
//  					AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  					(
//  							SELECT
//  							TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  							FROM
//  							:Com_("d_t_food_bill") d
//  							WHERE
//  							DBUSINESS >= TO_Date(:startDate, 'yyyy-MM-dd')
//  							AND DBUSINESS <= TO_Date(:endDate, 'yyyy-MM-dd')
//  							GROUP BY
//  							TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  			        GROUP BY
//  			        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  			            b.cBranch_C,
//  			            b.cBranch_N,
//  			            b.cTable_N ,
//  			            b.cBill_C,
//  			            period,
//  			            shift
//  		       ) t
//  			 };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PresentBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:371^4*/	
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public List<PresentBill> getDetail(String startDate,String endDate,String shopC,String period,
			String shift,int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<PresentBill> result = new ArrayList<PresentBill>();
		DefaultContext myCtx = getDefaultContext();
		DetailIterator detailIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:390^4*/

//  ************************************************************
//  #sql [myCtx] detailIterator = { SELECT
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
//  				                        b.cBranch_C                      shopC,
//  				                        b.cBranch_N                      shopN,
//  				                        b.cTable_N                       table,
//  				                        b.cBill_C                        billC,
//  				                        bs.cFood_C                       foodC,
//  				                        bs.cFood_N                       foodN,
//  				                        b.cPeriod_N                      period,
//  				                        b.cShift_N                       shift,
//  				                        bs.sUnit           unit,
//  				                        bs.nQty            presentAmount ,
//  				                        bs.nPrc            price,
//  				                        bs.nAmt            presentPrice,
//  				                        bs.cPresentBackMan     presentMan,
//  				                        bs.sRetSendRemark  presentWhy,
//  				                        bs.cLitCls_C       smallC,
//  									    bs.cLitCls_N       smallN
//  				                    FROM
//  				                    :Com_("d_t_food_bill") b
//  				                    LEFT JOIN
//  				                    :Com_("d_t_food_Bills") bs
//  				                    ON
//  				                        b.cBill_C = bs.cBill_C
//  				                    WHERE
//  				                        b.cBranch_C LIKE :shopC
//  				                    AND b.CPERIOD_N LIKE :period
//  				                    AND b.CSHIFT_N LIKE :shift
//  				                    AND bs.eRetSendFlag = '赠送'
//  	                    		    AND bs.eSuitFlag <> '套餐子项'
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
//  				                        b.cBranch_C,
//  				                        b.cBranch_N,
//  				                        b.cTable_N ,
//  				                        b.cBill_C,
//  				                        bs.cFood_C ,
//  				                        bs.cFood_N ,
//  				                        period,
//  				                        shift,
//  				                        bs.sUnit ,
//  				                        bs.nQty ,
//  				                        bs.nPrc ,
//  				                        bs.nAmt ,
//  				                        bs.cPresentBackMan ,
//  				                        bs.sRetSendRemark,
//  				                        bs.cLitCls_C,
//  									    bs.cLitCls_N 
//  				                    ORDER BY
//  				                        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				                        b.cTable_N,
//  				                        b.cBranch_C,
//  				                        b.cBill_C,
//  				                        bs.cFood_C) t
//  				            WHERE
//  				                ROWNUM <= :endRow)
//  				    WHERE
//  				        rowNumber >= :startRow
//  		      };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PresentBean_SJProfileKeys.getKey(0), 4);
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
      detailIterator = new DetailIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:469^8*/
			
			while(detailIterator.next()) {
				PresentBill presentBean = new PresentBill();
				presentBean.businessDate = detailIterator.businessDate();
				presentBean.table = detailIterator.table();
				presentBean.billC = detailIterator.billC();
				presentBean.period = detailIterator.period();
				presentBean.shift = detailIterator.shift();
				presentBean.foodName = detailIterator.foodN();
				presentBean.unit = detailIterator.unit();
				presentBean.presentAmount = detailIterator.presentAmount();
				presentBean.price = detailIterator.price();
				presentBean.presentPrice = detailIterator.presentPrice();
				presentBean.extracPrice = detailIterator.extracPrice();
				presentBean.presentMan = detailIterator.presentMan();
				presentBean.presentWhy = detailIterator.presentWhy();
				presentBean.shopC = detailIterator.shopC();
				presentBean.shopN = detailIterator.shopN();
				presentBean.bigC = detailIterator.bigC();
				presentBean.bigN = detailIterator.bigN();
				presentBean.smallC = detailIterator.smallC();
				presentBean.smallN = detailIterator.smallN();
				result.add(presentBean);
			}
			detailIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	public List<PresentBill> getHead(String startDate,String endDate,String shopC,String period,
			String shift,int startRow,int endRow)
					throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<PresentBill> result = new ArrayList<PresentBill>();
		DefaultContext myCtx = getDefaultContext();
		HeadIterator headIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:513^4*/

//  ************************************************************
//  #sql [myCtx] headIterator = { SELECT
//  					*
//  					FROM
//  					(
//  							SELECT
//  							t.*,
//  							ROWNUM rowNumber
//  							FROM
//  							(
//  							        SELECT
//  							        TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  						            b.cBranch_C                            shopC,
//  						            b.cBranch_N                            shopN,
//  						            b.cTable_N                             table,
//  						            b.cBill_C                              billC,
//  						            b.cPeriod_N                            period,
//  						            b.cShift_N                             shift,
//  						            SUM(NVL( bs.nQty,0)) presentAmount ,
//  						            SUM(NVL(bs.nAmt,0))  presentPrice
//  						        FROM
//  						        :Com_("d_t_food_bill") b
//  						        LEFT JOIN
//  						        :Com_("d_t_food_Bills") bs
//  						        ON
//  						            b.cBill_C = bs.cBill_C
//  						        WHERE
//  						        	b.cBranch_C LIKE :shopC
//  								AND b.CPERIOD_N LIKE :period
//  								AND b.CSHIFT_N LIKE :shift
//  								AND bs.eRetSendFlag = '赠送'
//  								AND bs.eSuitFlag <> '套餐子项'
//  								AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  								(
//  										SELECT
//  										TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  										FROM
//  										:Com_("d_t_food_bill") d
//  										WHERE
//  										DBUSINESS >= TO_Date(:startDate, 'yyyy-MM-dd')
//  										AND DBUSINESS <= TO_Date(:endDate, 'yyyy-MM-dd')
//  										GROUP BY
//  										TO_CHAR(DBUSINESS, 'yyyy-MM-dd'))
//  						        GROUP BY
//  						        TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  						            b.cBranch_C,
//  						            b.cBranch_N,
//  						            b.cTable_N ,
//  						            b.cBill_C,
//  						            period,
//  						            shift
//  					       ) t
//  							WHERE
//  								ROWNUM <= :endRow)
//  								WHERE
//  								rowNumber >= :startRow
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
  int __sJT_9 = endRow;
  int __sJT_10 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PresentBean_SJProfileKeys.getKey(0), 5);
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
      headIterator = new HeadIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:569^4*/
			
			while(headIterator.next()) {
				PresentBill presentBean = new PresentBill();
				presentBean.businessDate = headIterator.businessDate();
				presentBean.table = headIterator.table();
				presentBean.billC = headIterator.billC();
				presentBean.period = headIterator.period();
				presentBean.shift = headIterator.shift();
				presentBean.presentAmount = headIterator.presentAmount();
				presentBean.presentPrice = headIterator.presentPrice();
				presentBean.shopC = headIterator.shopC();
				presentBean.shopN = headIterator.shopN();
				result.add(presentBean);
			}
			headIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class PresentBean_SJProfileKeys 
{
  private static PresentBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PresentBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PresentBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.PresentBean_SJProfile0");
  }
}
