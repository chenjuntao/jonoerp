/*@lineinfo:filename=DailyFoodAmtBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
*
* Copyright (c) 2014
* Tanry Electronic Technology Co., Ltd.
* ChangSha, China
*
* All Rights Reserved.
*
* First created on Sep 28, 2014 by cjt
* Last edited on Sep 28, 2014 by cjt
*
* 说明： 访问餐厅每日万元出品量表
*
*/

package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class DailyFoodAmtBean extends ConnectionPool {
	
//    #sql private iterator FoodIterator(String cFood_C, Integer nQty); 

    //计算餐厅营业额
    public int calcBusinessAmt(String branchId, java.util.Date settleDate) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Date sDate = SqlDateUtil.getSqlDate(settleDate);
		if (myCtx != null) {
//			Double amt = 0.0;
//			#sql [myCtx] {
//				SELECT sum(npayamt) INTO :amt
//				FROM :{Com_("D_T_FOOD_BILL")}
//				WHERE cbranch_c = :branchId
//  				AND dbusiness = :sDate
//			};

			/*@lineinfo:generated-code*//*@lineinfo:47^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T3_DAILY_BUSINESS_AMT")
//  					(BRANCH_ID, BUSINESS_DATE, BUSINESS_AMT) 
//  				(
//  					SELECT :branchId, :sDate, sum(npayamt)
//  					FROM :Com_("D_T_FOOD_BILL")
//  					WHERE cbranch_c = :branchId
//  	  				AND dbusiness = :sDate
//  		  		)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_BUSINESS_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  String __sJT_4 = Com_("D_T_FOOD_BILL");
  String __sJT_5 = branchId;
  Date __sJT_6 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyFoodAmtBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:56^4*/
		} else {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return 1;
    }
    
    //计算当天的出品量
    public int calcFoodAmt(String branchId, java.util.Date settleDate) 
    		throws NoPrivilegeException, SQLException, NoConnection {
  		DefaultContext myCtx = getDefaultContext();
  		Date sDate = SqlDateUtil.getSqlDate(settleDate);
  		if (myCtx != null) {
  			/*@lineinfo:generated-code*//*@lineinfo:70^6*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T3_DAILY_FOOD_AMT")
//  					(BRANCH_ID, BUSINESS_DATE, ITEM_ID, FOOD_AMT)
//  				(
//  					SELECT
//  						:branchId,
//  						:sDate,
//  					   c.cFood_C cFood_C,
//  					   sum(c.nQty) AS nQty
//  					FROM
//  						:Com_("d_t_food_bills") c,
//  						:Com_("d_t_food_bill") a
//  					WHERE
//  						a.cBill_C = c.cBill_C
//  					AND	a.cbranch_c = :branchId
//  					AND a.DBUSINESS = :sDate
//  					GROUP BY
//  					    c.cFood_C
//  				)
//    			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_FOOD_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  String __sJT_4 = Com_("d_t_food_bills");
  String __sJT_5 = Com_("d_t_food_bill");
  String __sJT_6 = branchId;
  Date __sJT_7 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyFoodAmtBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:89^6*/
//  			while(foodIterator.next()) {
//  				String itemId = foodIterator.cFood_C();
//  				Integer nqty = foodIterator.nQty();
//  				#sql [myCtx] {
//  					INSERT INTO :{Com_("D_T3_DAILY_FOOD_AMT")}
//  						(BRANCH_ID, BUSINESS_DATE, ITEM_ID, FOOD_AMT) 
//  					VALUES (:branchId, 
//  							:sDate, 
//  							:itemId,
//  							:nqty)
//  				};
//  			}
//  			foodIterator.close();
  		} else {
              throw new NoConnection();
        }
  		closeDefaultContext(myCtx);
  		return 1;
    }

	//查询万元出品量
	public Double queryFoodAmtTTCNY(java.util.Date startDate, java.util.Date endDate, String branchId, String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		//查询出品总量
		Double foodAmt = 0.0;
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT SUM(FOOD_AMT) 
//  			FROM :Com_("D_T3_DAILY_FOOD_AMT")
//  			WHERE BRANCH_ID = :branchId
//  		    AND (BUSINESS_DATE >= :sDate or :sDate is null)
//  			AND (BUSINESS_DATE <= :eDate or :eDate is null)
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_FOOD_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  String __sJT_7 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyFoodAmtBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
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
    foodAmt = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:125^3*/
		//查询总营业额
		Double businessAmt = 0.0;
		/*@lineinfo:generated-code*//*@lineinfo:128^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT SUM(BUSINESS_AMT) 
//  			FROM :Com_("D_T3_DAILY_BUSINESS_AMT") 
//  			WHERE BRANCH_ID = :branchId
//  		    AND (BUSINESS_DATE >= :sDate or :sDate is null)
//  			AND (BUSINESS_DATE <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_BUSINESS_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyFoodAmtBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
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
    businessAmt = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:134^3*/
		closeDefaultContext(myCtx);
		if(businessAmt == 0.0)
		{
			return 0.0;
		}else{
		    return foodAmt/businessAmt;
		}
	}
}/*@lineinfo:generated-code*/class DailyFoodAmtBean_SJProfileKeys 
{
  private static DailyFoodAmtBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DailyFoodAmtBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DailyFoodAmtBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.DailyFoodAmtBean_SJProfile0");
  }
}
