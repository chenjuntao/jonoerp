/*@lineinfo:filename=BusinessCountBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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
import pojo.businessquery.BusinessCount;

public class BusinessCountBean extends ConnectionPool
{
	//总的统计
	/*@lineinfo:generated-code*//*@lineinfo:21^2*/

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
    billNumNdx = findColumn("billNum");
    guestNumNdx = findColumn("guestNum");
    foodAmtNdx = findColumn("foodAmt");
    disAmtNdx = findColumn("disAmt");
    roundAmtNdx = findColumn("roundAmt");
    oughtAmtNdx = findColumn("oughtAmt");
    payAmtNdx = findColumn("payAmt");
    presentAmtNdx = findColumn("presentAmt");
  }
  public int billNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(billNumNdx);
  }
  private int billNumNdx;
  public int guestNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(guestNumNdx);
  }
  private int guestNumNdx;
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
  public float roundAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(roundAmtNdx);
  }
  private int roundAmtNdx;
  public float oughtAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(oughtAmtNdx);
  }
  private int oughtAmtNdx;
  public float payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(payAmtNdx);
  }
  private int payAmtNdx;
  public float presentAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(presentAmtNdx);
  }
  private int presentAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:30^4*/
	
	//加限制条件的统计，比如：按班次、时段、楼层等
	/*@lineinfo:generated-code*//*@lineinfo:33^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ConditionIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ConditionIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    conditionCNdx = findColumn("conditionC");
    conditionNNdx = findColumn("conditionN");
    billNumNdx = findColumn("billNum");
    guestNumNdx = findColumn("guestNum");
    foodAmtNdx = findColumn("foodAmt");
    disAmtNdx = findColumn("disAmt");
    roundAmtNdx = findColumn("roundAmt");
    oughtAmtNdx = findColumn("oughtAmt");
    payAmtNdx = findColumn("payAmt");
    presentAmtNdx = findColumn("presentAmt");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
  }
  public String conditionC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(conditionCNdx);
  }
  private int conditionCNdx;
  public String conditionN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(conditionNNdx);
  }
  private int conditionNNdx;
  public int billNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(billNumNdx);
  }
  private int billNumNdx;
  public int guestNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(guestNumNdx);
  }
  private int guestNumNdx;
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
  public float roundAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(roundAmtNdx);
  }
  private int roundAmtNdx;
  public float oughtAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(oughtAmtNdx);
  }
  private int oughtAmtNdx;
  public float payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(payAmtNdx);
  }
  private int payAmtNdx;
  public float presentAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(presentAmtNdx);
  }
  private int presentAmtNdx;
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

/*@lineinfo:user-code*//*@lineinfo:46^4*/
		
		
	public BusinessCount shiftSum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		BusinessCount businessCount = new BusinessCount();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:57^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  				    SUM(billNum)    billNum,
//  				    SUM(guestNum)   guestNum,
//  				    SUM(foodAmt)    foodAmt,
//  				    SUM(disAmt)     disAmt,
//  				    SUM(roundAmt)   roundAmt,
//  				    SUM(oughtAmt)   oughtAmt,
//  				    SUM(payAmt)     payAmt,
//  				    SUM(presentAmt) presentAmt
//  				FROM
//  				    (
//  				        SELECT
//  				            b.CSHIFT_N       shift,
//  				            COUNT(cBranch_C) billNum,
//  				            SUM(iGuestNum)   guestNum,
//  				            SUM(nFoodAmt)    foodAmt,
//  				            SUM(nDisAmt)     disAmt,
//  				            SUM(nRoundAmt)   roundAmt,
//  				            SUM(nOughtAmt)   oughtAmt,
//  				            SUM(nPayAmt)     payAmt,
//  				            SUM(nPresentAmt) presentAmt,
//  				            cBranch_C        shopC,
//  				            cBranch_N        shopN
//  				        FROM
//  				            :Com_("d_t_food_bill") b
//  				        WHERE
//  				        	DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  						AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  						AND b.CBRANCH_C LIKE :shopC
//  				        GROUP BY
//  				            b.CSHIFT_N ,
//  				            cBranch_C,
//  				            cBranch_N ) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BusinessCountBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:90^30*/
			
			while(sumIterator.next()) {
				processSumIter(businessCount, sumIterator);
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return businessCount;
	}

	public BusinessCount periodSum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		BusinessCount businessCount = new BusinessCount();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:113^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  				    SUM(billNum)    billNum,
//  				    SUM(guestNum)   guestNum,
//  				    SUM(foodAmt)    foodAmt,
//  				    SUM(disAmt)     disAmt,
//  				    SUM(roundAmt)   roundAmt,
//  				    SUM(oughtAmt)   oughtAmt,
//  				    SUM(payAmt)     payAmt,
//  				    SUM(presentAmt) presentAmt
//  				FROM
//  				    (SELECT
//  				    	    b.CPERIOD_N      period,
//  				    	    COUNT(cBranch_C) billNum,
//  				    	    SUM(iGuestNum)   guestNum,
//  				    	    SUM(nFoodAmt)    foodAmt,
//  				    	    SUM(nDisAmt)     disAmt,
//  				    	    SUM(nRoundAmt)   roundAmt,
//  				    	    SUM(nOughtAmt)   oughtAmt,
//  				    	    SUM(nPayAmt)     payAmt,
//  				    	    SUM(nPresentAmt) presentAmt,
//  				    	    cBranch_C        shopC,
//  				    	    cBranch_N        shopName
//  				    	FROM
//  				    	    :Com_("d_t_food_bill") b
//  				    	WHERE
//  				    			DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  							AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  							AND b.CBRANCH_C LIKE :shopC
//  				    	GROUP BY
//  				    	    b.CPERIOD_N ,
//  				    	    cBranch_C,
//  				    	    cBranch_N
//  				    	) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BusinessCountBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:146^13*/
			
			while(sumIterator.next()) {
				processSumIter(businessCount, sumIterator);
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return businessCount;
	}
	
	public BusinessCount floorSum(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		BusinessCount businessCount = new BusinessCount();
		DefaultContext myCtx = getDefaultContext();
		SumIterator sumIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:169^4*/

//  ************************************************************
//  #sql [myCtx] sumIterator = { SELECT
//  				    SUM(billNum)    billNum,
//  				    SUM(guestNum)   guestNum,
//  				    SUM(foodAmt)    foodAmt,
//  				    SUM(disAmt)     disAmt,
//  				    SUM(roundAmt)   roundAmt,
//  				    SUM(oughtAmt)   oughtAmt,
//  				    SUM(payAmt)     payAmt,
//  				    SUM(presentAmt) presentAmt
//  				FROM
//  				    (SELECT
//  				    		cBranch_C        shopC,
//  					        cBranch_N        shopN,
//  				    	    COUNT(cBranch_C) billNum,
//  				    	    SUM(iGuestNum)   guestNum,
//  				    	    SUM(nFoodAmt)    foodAmt,
//  				    	    SUM(nDisAmt)     disAmt,
//  				    	    SUM(nRoundAmt)   roundAmt,
//  				    	    SUM(nOughtAmt)   oughtAmt,
//  				    	    SUM(nPayAmt)     payAmt,
//  				    	    SUM(nPresentAmt) presentAmt
//  				    	FROM
//  				    	    :Com_("d_t_food_bill") b
//  				    	WHERE
//  				    		DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  						AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  						AND b.CBRANCH_C LIKE :shopC
//  				    	GROUP BY
//  				    	    cBranch_C,
//  				    	    cBranch_N
//  				    	ORDER BY
//  				    	    b.CBRANCH_C) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BusinessCountBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:201^28*/
			
			while(sumIterator.next()) {
				processSumIter(businessCount, sumIterator);
			}
			sumIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return businessCount;
	}
	
	private void processSumIter(BusinessCount businessCount,SumIterator sumIterator)
	{
		try {
			businessCount.billNum = sumIterator.billNum();
		} catch (Exception e) {
			businessCount.billNum = 0;
		}
	
		try {
			businessCount.guestNum = sumIterator.guestNum();
		} catch (Exception e) {
			businessCount.guestNum = 0;
		}
		
		try {
			businessCount.foodAmt = sumIterator.foodAmt();
		} catch (Exception e) {
			businessCount.foodAmt = 0;
		}
		
		try {
			businessCount.disAmt = sumIterator.disAmt();
		} catch (Exception e) {
			businessCount.disAmt = 0;
		}
		
		try {
			businessCount.roundAmt = sumIterator.roundAmt();
		} catch (Exception e) {
			businessCount.roundAmt = 0;
		}
		
		try {
			businessCount.oughtAmt = sumIterator.oughtAmt();
		} catch (Exception e) {
			businessCount.oughtAmt = 0;
		}
		
		try {
			businessCount.payAmt = sumIterator.payAmt();
		} catch (Exception e) {
			businessCount.payAmt = 0;
		}
		
		try {
			businessCount.presentAmt = sumIterator.presentAmt();
		} catch (Exception e) {
			businessCount.presentAmt = 0;
		}	
	}
	
	public List<BusinessCount> shift(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<BusinessCount> result = new ArrayList<BusinessCount>();
		DefaultContext myCtx = getDefaultContext();
		ConditionIterator conditionIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:275^4*/

//  ************************************************************
//  #sql [myCtx] conditionIterator = { SELECT
//  				    b.CSHIFT_N       conditionN,
//  				    COUNT(cBranch_C) billNum,
//  				    SUM(iGuestNum)   guestNum,
//  				    SUM(nFoodAmt)    foodAmt,
//  				    SUM(nDisAmt)     disAmt,
//  				    SUM(nRoundAmt)   roundAmt,
//  				    SUM(nOughtAmt)   oughtAmt,
//  				    SUM(nPayAmt)     payAmt,
//  				    SUM(nPresentAmt) presentAmt,
//  				    cBranch_C        shopC,
//  				    cBranch_N        shopN
//  				FROM
//  				    :Com_("d_t_food_bill") b
//  				WHERE
//  				    DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  				AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  				AND b.CBRANCH_C LIKE :shopC
//  				GROUP BY
//  				    b.CSHIFT_N ,
//  				    cBranch_C,
//  				    cBranch_N
//  				ORDER BY
//  				    b.CSHIFT_N,
//  				    cBranch_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BusinessCountBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      conditionIterator = new ConditionIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:300^18*/
			
			while(conditionIterator.next()) {
				BusinessCount businessCount = processConditionIter(conditionIterator);		
				businessCount.shift = conditionIterator.conditionN();
				result.add(businessCount);
			}
			conditionIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	public List<BusinessCount> period(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<BusinessCount> result = new ArrayList<BusinessCount>();
		DefaultContext myCtx = getDefaultContext();
		ConditionIterator conditionIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:325^4*/

//  ************************************************************
//  #sql [myCtx] conditionIterator = { SELECT
//  				    b.CPERIOD_N      conditionN,
//  				    COUNT(cBranch_C) billNum,
//  				    SUM(iGuestNum)   guestNum,
//  				    SUM(nFoodAmt)    foodAmt,
//  				    SUM(nDisAmt)     disAmt,
//  				    SUM(nRoundAmt)   roundAmt,
//  				    SUM(nOughtAmt)   oughtAmt,
//  				    SUM(nPayAmt)     payAmt,
//  				    SUM(nPresentAmt) presentAmt,
//  				    cBranch_C        shopC,
//  				    cBranch_N        shopN
//  				FROM
//  				    :Com_("d_t_food_bill") b
//  				WHERE
//  						DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  					AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  					AND b.CBRANCH_C LIKE :shopC
//  				GROUP BY
//  				    b.CPERIOD_N ,
//  				    cBranch_C,
//  				    cBranch_N
//  				ORDER BY
//  				    b.CPERIOD_N };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BusinessCountBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      conditionIterator = new ConditionIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:349^20*/
			
			while(conditionIterator.next()) {
				BusinessCount businessCount = processConditionIter(conditionIterator);		
				businessCount.period = conditionIterator.conditionN();
				result.add(businessCount);
			}
			conditionIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	public List<BusinessCount> floor(String startDate,String endDate,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<BusinessCount> result = new ArrayList<BusinessCount>();
		DefaultContext myCtx = getDefaultContext();
		ConditionIterator conditionIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:374^4*/

//  ************************************************************
//  #sql [myCtx] conditionIterator = { SELECT
//  					cBranch_C        shopC,
//  					cBranch_N        shopN,
//  				    COUNT(cBranch_C) billNum,
//  				    SUM(iGuestNum)   guestNum,
//  				    SUM(nFoodAmt)    foodAmt,
//  				    SUM(nDisAmt)     disAmt,
//  				    SUM(nRoundAmt)   roundAmt,
//  				    SUM(nOughtAmt)   oughtAmt,
//  				    SUM(nPayAmt)     payAmt,
//  				    SUM(nPresentAmt) presentAmt,
//  				FROM
//  				    :Com_("d_t_food_bill") b
//  				WHERE
//  					DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  				AND DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  				AND b.CBRANCH_C LIKE :shopC
//  				GROUP BY
//  				    cBranch_C,
//  				    cBranch_N
//  				ORDER BY
//  				    b.CBRANCH_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BusinessCountBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      conditionIterator = new ConditionIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:396^20*/
			
			while(conditionIterator.next()) {
				BusinessCount businessCount = processConditionIter(conditionIterator);		
				result.add(businessCount);
			}
			conditionIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	private BusinessCount processConditionIter(ConditionIterator conditionIterator)  throws SQLException
	{
		BusinessCount businessCount = new BusinessCount();
		businessCount.billNum = conditionIterator.billNum();
		businessCount.guestNum = conditionIterator.guestNum();
		businessCount.foodAmt = conditionIterator.foodAmt();
		businessCount.disAmt = conditionIterator.disAmt();
		businessCount.roundAmt = conditionIterator.roundAmt();
		businessCount.oughtAmt = conditionIterator.oughtAmt();
		businessCount.payAmt = conditionIterator.payAmt();
		businessCount.presentAmt = conditionIterator.presentAmt();
		businessCount.shopC = conditionIterator.shopC();
		businessCount.shopN = conditionIterator.shopN();
		
		return businessCount;
	}
}/*@lineinfo:generated-code*/class BusinessCountBean_SJProfileKeys 
{
  private static BusinessCountBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BusinessCountBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BusinessCountBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.BusinessCountBean_SJProfile0");
  }
}
