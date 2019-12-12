/*@lineinfo:filename=IncomeDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.IncomeDetail;

public class IncomeDetailBean extends ConnectionPool
{
	/*@lineinfo:generated-code*//*@lineinfo:20^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DayIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DayIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    businessDateNdx = findColumn("businessDate");
    categoryNNdx = findColumn("categoryN");
    period1Ndx = findColumn("period1");
    period2Ndx = findColumn("period2");
    period3Ndx = findColumn("period3");
    period4Ndx = findColumn("period4");
    countsNdx = findColumn("counts");
  }
  public String businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessDateNdx);
  }
  private int businessDateNdx;
  public String categoryN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(categoryNNdx);
  }
  private int categoryNNdx;
  public float period1() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period1Ndx);
  }
  private int period1Ndx;
  public float period2() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period2Ndx);
  }
  private int period2Ndx;
  public float period3() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period3Ndx);
  }
  private int period3Ndx;
  public float period4() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period4Ndx);
  }
  private int period4Ndx;
  public float counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(countsNdx);
  }
  private int countsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:28^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:30^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class MonthIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MonthIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    businessMonthNdx = findColumn("businessMonth");
    categoryNNdx = findColumn("categoryN");
    period1Ndx = findColumn("period1");
    period2Ndx = findColumn("period2");
    period3Ndx = findColumn("period3");
    period4Ndx = findColumn("period4");
    countsNdx = findColumn("counts");
  }
  public String businessMonth() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessMonthNdx);
  }
  private int businessMonthNdx;
  public String categoryN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(categoryNNdx);
  }
  private int categoryNNdx;
  public float period1() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period1Ndx);
  }
  private int period1Ndx;
  public float period2() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period2Ndx);
  }
  private int period2Ndx;
  public float period3() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period3Ndx);
  }
  private int period3Ndx;
  public float period4() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period4Ndx);
  }
  private int period4Ndx;
  public float counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(countsNdx);
  }
  private int countsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

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
    period1Ndx = findColumn("period1");
    period2Ndx = findColumn("period2");
    period3Ndx = findColumn("period3");
    period4Ndx = findColumn("period4");
    countsNdx = findColumn("counts");
  }
  public float period1() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period1Ndx);
  }
  private int period1Ndx;
  public float period2() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period2Ndx);
  }
  private int period2Ndx;
  public float period3() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period3Ndx);
  }
  private int period3Ndx;
  public float period4() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(period4Ndx);
  }
  private int period4Ndx;
  public float counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(countsNdx);
  }
  private int countsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^4*/
	
	public IncomeDetailBean()
	{
	}
	
	public IncomeDetail daySum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		IncomeDetail incomeDetail = new IncomeDetail();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:60^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  				    SUM(period1) period1 ,
//  				    SUM(period2) period2 ,
//  				    SUM(period3) period3 ,
//  				    SUM(period4) period4,
//  				    SUM(counts)  counts
//  				FROM
//  				    (
//  				        SELECT
//  				            TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				            CATEGORY_ID,
//  				            CATEGORY_NAME categoryN,
//  				            SUM(
//  						        CASE
//  						            WHEN b.CPERIOD_N = '01'
//  						            THEN bs.nAmt
//  						            ELSE 0
//  						        END)  period1,
//  						    SUM(
//  						        CASE
//  						            WHEN b.CPERIOD_N = '02'
//  						            THEN bs.nAmt
//  						            ELSE 0
//  						        END)  period2,
//  						    SUM(
//  						        CASE
//  						            WHEN b.CPERIOD_N = '03'
//  						            THEN bs.nAmt
//  						            ELSE 0
//  						        END)  period3,
//  						    SUM(
//  						        CASE
//  						            WHEN b.CPERIOD_N = '04'
//  						            THEN bs.nAmt
//  						            ELSE 0
//  						        END)       period4,
//  						    SUM(bs.nAmt)  counts
//  				        FROM
//  				        	:Com_("d_t2_item_category") c
//  				        LEFT JOIN
//  				        	:Com_("d_t_food_bills") bs
//  				        ON
//  				            c.CATEGORY_ID = bs.cLitCls_c
//  				        LEFT JOIN
//  				        	:Com_("d_t_food_bill") b
//  				        ON
//  				            b.CBILL_C = bs.CBILL_C
//  				        WHERE
//  				            TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				            (
//  				                SELECT
//  				                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				                FROM
//  				                	:Com_("d_t_food_bill") d
//  				                WHERE
//  				                	DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  								AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  				                GROUP BY
//  				                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') )
//  	                    AND b.CBRANCH_C LIKE :shopC
//  				        AND c.ITEM_TYPE = 'PRODUCT'
//  				        AND c.PARENT_ID = '*'
//  				        GROUP BY
//  				            TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				            CATEGORY_ID,
//  				            CATEGORY_NAME
//  				        ORDER BY
//  				            TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				            c.CATEGORY_ID) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_category");
  String __sJT_2 = Com_("d_t_food_bills");
  String __sJT_3 = Com_("d_t_food_bill");
  String __sJT_4 = Com_("d_t_food_bill");
  String __sJT_5 = startDate;
  String __sJT_6 = endDate;
  String __sJT_7 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, IncomeDetailBean_SJProfileKeys.getKey(0), 0);
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
      sumIterator = new SumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:129^33*/
			
			while(sumIterator.next()) {
				try {
					incomeDetail.period1 = sumIterator.period1();
				} catch (Exception e) {
					incomeDetail.period1 = 0;
				}
				
				try {
					incomeDetail.period2 = sumIterator.period2();
				} catch (Exception e) {
					incomeDetail.period2 = 0;
				}
				
				try {
					incomeDetail.period3 = sumIterator.period3();
				} catch (Exception e) {
					incomeDetail.period3 = 0;
				}
				
				try {
					incomeDetail.period4 = sumIterator.period4();
				} catch (Exception e) {
					incomeDetail.period4 = 0;
				}
				
				try {
					incomeDetail.counts = sumIterator.counts();
				} catch (Exception e) {
					incomeDetail.counts = 0;
				}
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return incomeDetail;
	}
	
	public IncomeDetail monthSum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		IncomeDetail incomeDetail = new IncomeDetail();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:180^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  				    SUM(period1) period1 ,
//  				    SUM(period2) period2 ,
//  				    SUM(period3) period3 ,
//  				    SUM(period4) period4,
//  				    SUM(counts)     counts
//  				FROM
//  				    (
//  							SELECT
//  						    TO_CHAR(DBUSINESS, 'yyyy-MM') businessMonth,
//  						    CATEGORY_ID,
//  						    CATEGORY_NAME categoryN,
//  						    SUM(
//  						        CASE
//  						            WHEN b.CPERIOD_N = '01'
//  						            THEN bs.nAmt
//  						            ELSE 0
//  						        END)  period1,
//  						    SUM(
//  						        CASE
//  						            WHEN b.CPERIOD_N = '02'
//  						            THEN bs.nAmt
//  						            ELSE 0
//  						        END)  period2,
//  						    SUM(
//  						        CASE
//  						            WHEN b.CPERIOD_N = '03'
//  						            THEN bs.nAmt
//  						            ELSE 0
//  						        END)  period3,
//  						    SUM(
//  						        CASE
//  						            WHEN b.CPERIOD_N = '04'
//  						            THEN bs.nAmt
//  						            ELSE 0
//  						        END)  period4,
//  						    SUM(bs.nAmt) counts
//  						FROM
//  							:Com_("d_t2_item_category") c
//  						LEFT JOIN
//  							:Com_("d_t_food_bills") bs
//  						ON
//  						    c.CATEGORY_ID = bs.cLitCls_c
//  						LEFT JOIN
//  						    :Com_("d_t_food_bill") b
//  						ON
//  						    b.CBILL_C = bs.CBILL_C
//  						WHERE
//  						    TO_CHAR(DBUSINESS, 'yyyy-MM') IN
//  						    (
//  						        SELECT
//  						            TO_CHAR(DBUSINESS, 'yyyy-MM')
//  						        FROM
//  						            :Com_("d_t_food_bill") d
//  						        WHERE
//  						            DBUSINESS >= to_date(:startDate, 'yyyy-mm')
//  						        AND DBUSINESS <= to_date(:endDate, 'yyyy-mm')
//  						        GROUP BY
//  						            TO_CHAR(DBUSINESS, 'yyyy-MM') )
//  						AND b.CBRANCH_C LIKE :shopC
//  						AND c.ITEM_TYPE = 'PRODUCT'
//  						AND c.PARENT_ID = '*'
//  						GROUP BY
//  						    TO_CHAR(DBUSINESS, 'yyyy-MM'),
//  						    CATEGORY_ID,
//  						    CATEGORY_NAME
//  						ORDER BY
//  						    TO_CHAR(DBUSINESS, 'yyyy-MM'),
//  						    c.CATEGORY_ID) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_category");
  String __sJT_2 = Com_("d_t_food_bills");
  String __sJT_3 = Com_("d_t_food_bill");
  String __sJT_4 = Com_("d_t_food_bill");
  String __sJT_5 = startDate;
  String __sJT_6 = endDate;
  String __sJT_7 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, IncomeDetailBean_SJProfileKeys.getKey(0), 1);
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
      sumIterator = new SumIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:249^27*/
			
			while(sumIterator.next()) {
				try {
					incomeDetail.period1 = sumIterator.period1();
				} catch (Exception e) {
					incomeDetail.period1 = 0;
				}
				
				try {
					incomeDetail.period2 = sumIterator.period2();
				} catch (Exception e) {
					incomeDetail.period2 = 0;
				}
				
				try {
					incomeDetail.period3 = sumIterator.period3();
				} catch (Exception e) {
					incomeDetail.period3 = 0;
				}
				
				try {
					incomeDetail.period4 = sumIterator.period4();
				} catch (Exception e) {
					incomeDetail.period4 = 0;
				}
				
				try {
					incomeDetail.counts = sumIterator.counts();
				} catch (Exception e) {
					incomeDetail.counts = 0;
				}
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return incomeDetail;
	}
	public List<IncomeDetail> monthIncomeDetail(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<IncomeDetail> result = new ArrayList<IncomeDetail>();
		DefaultContext myCtx = getDefaultContext();
		MonthIterator monthIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:299^4*/

//  ************************************************************
//  #sql [myCtx] monthIterator = { SELECT
//  				    TO_CHAR(DBUSINESS, 'yyyy-MM') businessMonth,
//  				    CATEGORY_ID,
//  				    CATEGORY_NAME categoryN,
//  				    SUM(
//  				        CASE
//  				            WHEN b.CPERIOD_N = '01'
//  				            THEN bs.nAmt
//  				            ELSE 0
//  				        END) period1,
//  				    SUM(
//  				        CASE
//  				            WHEN b.CPERIOD_N = '02'
//  				            THEN bs.nAmt
//  				            ELSE 0
//  				        END) period2,
//  				    SUM(
//  				        CASE
//  				            WHEN b.CPERIOD_N = '03'
//  				            THEN bs.nAmt
//  				            ELSE 0
//  				        END) period3,
//  				    SUM(
//  				        CASE
//  				            WHEN b.CPERIOD_N = '04'
//  				            THEN bs.nAmt
//  				            ELSE 0
//  				        END) period4,
//  				    SUM(bs.nAmt) counts
//  				FROM
//  				    :Com_("d_t2_item_category") c
//  				LEFT JOIN
//  				    :Com_("d_t_food_bills") bs
//  				ON
//  				    c.CATEGORY_ID = bs.cLitCls_c
//  				LEFT JOIN
//  				    :Com_("d_t_food_bill") b
//  				ON
//  				    b.CBILL_C = bs.CBILL_C
//  				WHERE
//  				    TO_CHAR(DBUSINESS, 'yyyy-MM') IN
//  				    (
//  				        SELECT
//  				            TO_CHAR(DBUSINESS, 'yyyy-MM')
//  				        FROM
//  				            :Com_("d_t_food_bill") d
//  				        WHERE
//  				            DBUSINESS >= to_date(:startDate, 'yyyy-mm')
//  				        AND DBUSINESS <= to_date(:endDate, 'yyyy-mm')
//  				        GROUP BY
//  				            TO_CHAR(DBUSINESS, 'yyyy-MM') )
//  				AND b.CBRANCH_C LIKE :shopC
//  				AND c.ITEM_TYPE = 'PRODUCT'
//  				AND c.PARENT_ID = '*'
//  				GROUP BY
//  				    TO_CHAR(DBUSINESS, 'yyyy-MM'),
//  				    CATEGORY_ID,
//  				    CATEGORY_NAME
//  				ORDER BY
//  				    TO_CHAR(DBUSINESS, 'yyyy-MM'),
//  				    c.CATEGORY_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_category");
  String __sJT_2 = Com_("d_t_food_bills");
  String __sJT_3 = Com_("d_t_food_bill");
  String __sJT_4 = Com_("d_t_food_bill");
  String __sJT_5 = startDate;
  String __sJT_6 = endDate;
  String __sJT_7 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, IncomeDetailBean_SJProfileKeys.getKey(0), 2);
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
      monthIterator = new MonthIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:360^22*/
			
			while(monthIterator.next()) {
				IncomeDetail incomeDetail = new IncomeDetail();
				incomeDetail.businessMonth = monthIterator.businessMonth();
				incomeDetail.categoryN = monthIterator.categoryN();
				incomeDetail.period1 = monthIterator.period1();
				incomeDetail.period2 = monthIterator.period2();
				incomeDetail.period3 = monthIterator.period3();
				incomeDetail.period4 = monthIterator.period4();
				incomeDetail.counts = monthIterator.counts();
				
				result.add(incomeDetail);
			}
			monthIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	public List<IncomeDetail> dayIncomeDetail(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<IncomeDetail> result = new ArrayList<IncomeDetail>();
		DefaultContext myCtx = getDefaultContext();
		DayIterator dayIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:392^4*/

//  ************************************************************
//  #sql [myCtx] dayIterator = { SELECT
//  				    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  				    CATEGORY_ID,
//  				    CATEGORY_NAME categoryN,
//  				    SUM(
//  				        CASE
//  				            WHEN b.CPERIOD_N = '01'
//  				            THEN bs.nAmt
//  				            ELSE 0
//  				        END)  period1,
//  				    SUM(
//  				        CASE
//  				            WHEN b.CPERIOD_N = '02'
//  				            THEN bs.nAmt
//  				            ELSE 0
//  				        END)  period2,
//  				    SUM(
//  				        CASE
//  				            WHEN b.CPERIOD_N = '03'
//  				            THEN bs.nAmt
//  				            ELSE 0
//  				        END)  period3,
//  				    SUM(
//  				        CASE
//  				            WHEN b.CPERIOD_N = '04'
//  				            THEN bs.nAmt
//  				            ELSE 0
//  				        END) period4,
//  				    SUM(bs.nAmt)  counts
//  				FROM
//  					:Com_("d_t2_item_category") c
//  				LEFT JOIN
//  					:Com_("d_t_food_bills") bs
//  				ON
//  				    c.CATEGORY_ID = bs.cLitCls_c
//  				LEFT JOIN
//  				    :Com_("d_t_food_bill") b
//  				ON
//  				    b.CBILL_C = bs.CBILL_C
//  				WHERE
//  				    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  				    (
//  				        SELECT
//  				            TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  				        FROM
//  				            :Com_("d_t_food_bill") d
//  				        WHERE
//  				        	DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  						AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  				        GROUP BY
//  				            TO_CHAR(DBUSINESS, 'yyyy-MM-dd') )
//  				AND b.CBRANCH_C LIKE :shopC
//  				AND c.ITEM_TYPE = 'PRODUCT'
//  				AND c.PARENT_ID = '*'
//  				GROUP BY
//  				    TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				    CATEGORY_ID,
//  				    CATEGORY_NAME
//  				ORDER BY
//  				    TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  				    c.CATEGORY_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_category");
  String __sJT_2 = Com_("d_t_food_bills");
  String __sJT_3 = Com_("d_t_food_bill");
  String __sJT_4 = Com_("d_t_food_bill");
  String __sJT_5 = startDate;
  String __sJT_6 = endDate;
  String __sJT_7 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, IncomeDetailBean_SJProfileKeys.getKey(0), 3);
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
      dayIterator = new DayIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:453^22*/
			
			while(dayIterator.next()) {
				IncomeDetail incomeDetail = new IncomeDetail();
				incomeDetail.businessDate = dayIterator.businessDate();
				incomeDetail.categoryN = dayIterator.categoryN();
				incomeDetail.period1 = dayIterator.period1();
				incomeDetail.period2 = dayIterator.period2();
				incomeDetail.period3 = dayIterator.period3();
				incomeDetail.period4 = dayIterator.period4();
				incomeDetail.counts = dayIterator.counts();
				
				result.add(incomeDetail);
			}
			dayIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
}/*@lineinfo:generated-code*/class IncomeDetailBean_SJProfileKeys 
{
  private static IncomeDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new IncomeDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private IncomeDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.IncomeDetailBean_SJProfile0");
  }
}
