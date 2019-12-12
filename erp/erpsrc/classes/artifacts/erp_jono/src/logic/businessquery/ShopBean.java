/*@lineinfo:filename=ShopBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**查询各个门店的营业信息列表
 *read shop business info list
 *by cjt
 *first created on 2014.6.12
 *last edited on 2014.7.7
 *
 *edited by pw on 2014.7.28
 */

package logic.businessquery;

import sqlj.runtime.ref.*;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import logic.pool.ConnectionPool;
import logic.*;
import com.tanry.framework.acl.*;
import java.sql.Date;
import com.tanry.framework.util.DateTimeUtil;
import pojo.businessquery.ShopBill;

public class ShopBean extends ConnectionPool{
	
	private String strDateFormat = "yyyy-MM-dd";//设置日期格式

	/*@lineinfo:generated-code*//*@lineinfo:28^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ShopIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ShopIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    businessDateNdx = findColumn("businessDate");
    shopCNdx = findColumn("shopC");
    shopNameNdx = findColumn("shopName");
    foodAmtNdx = findColumn("foodAmt");
    billNumNdx = findColumn("billNum");
    amtPerBillNdx = findColumn("amtPerBill");
    guestNumNdx = findColumn("guestNum");
    amtPerGuestNdx = findColumn("amtPerGuest");
    tableNumNdx = findColumn("tableNum");
    amtPerTableNdx = findColumn("amtPerTable");
    guestPerTableNdx = findColumn("guestPerTable");
    disAmtNdx = findColumn("disAmt");
    roundAmtNdx = findColumn("roundAmt");
    presentAmtNdx = findColumn("presentAmt");
    oughtAmtNdx = findColumn("oughtAmt");
    payAmtNdx = findColumn("payAmt");
  }
  public String businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessDateNdx);
  }
  private int businessDateNdx;
  public String shopC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shopCNdx);
  }
  private int shopCNdx;
  public String shopName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shopNameNdx);
  }
  private int shopNameNdx;
  public Double foodAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(foodAmtNdx);
  }
  private int foodAmtNdx;
  public Integer billNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(billNumNdx);
  }
  private int billNumNdx;
  public Double amtPerBill() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtPerBillNdx);
  }
  private int amtPerBillNdx;
  public Integer guestNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(guestNumNdx);
  }
  private int guestNumNdx;
  public Double amtPerGuest() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtPerGuestNdx);
  }
  private int amtPerGuestNdx;
  public Integer tableNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(tableNumNdx);
  }
  private int tableNumNdx;
  public Double amtPerTable() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtPerTableNdx);
  }
  private int amtPerTableNdx;
  public Double guestPerTable() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(guestPerTableNdx);
  }
  private int guestPerTableNdx;
  public Double disAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(disAmtNdx);
  }
  private int disAmtNdx;
  public Double roundAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(roundAmtNdx);
  }
  private int roundAmtNdx;
  public Double presentAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(presentAmtNdx);
  }
  private int presentAmtNdx;
  public Double oughtAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(oughtAmtNdx);
  }
  private int oughtAmtNdx;
  public Double payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payAmtNdx);
  }
  private int payAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:44^17*/ 
	
	/*@lineinfo:generated-code*//*@lineinfo:46^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DateIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DateIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    settletimeNdx = findColumn("settletime");
  }
  public String settletime() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(settletimeNdx);
  }
  private int settletimeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^60*/

	public ShopBean()
	{
	}
	
	/**
	 * 按天查询分页，得到总的不相同的天数
	 */
	public int countByDay(String startTime, String endTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		int total = 0;
		DefaultContext myCtx = getDefaultContext();
		if (myCtx != null) {
			/*@lineinfo:generated-code*//*@lineinfo:60^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT count(DISTINCT
//                      TO_CHAR(DBUSINESS, 'yyyy-MM-dd') ) 
//                  FROM
//                  	:Com_("d_t_food_bill") b
//                  WHERE
//                  	TO_CHAR(DBUSINESS, 'yyyy-MM-dd') >= :startTime
//                  AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') <= :endTime
//                  ORDER BY
//                      TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = endTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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
    total = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:70^4*/
		} else {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		
		return total;
	}
	
	public List<ShopBill> byDay(String startTime, String endTime)
			throws NoPrivilegeException,SQLException,NoConnection	{
			
			List<ShopBill> shops = new ArrayList<ShopBill>();
			DefaultContext myCtx = getDefaultContext();
			ShopIterator shopByDayIterator = null;
			if (myCtx != null)		{
				/*@lineinfo:generated-code*//*@lineinfo:86^5*/

//  ************************************************************
//  #sql [myCtx] shopByDayIterator = { SELECT
//  					    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') businessDate,
//  					    cBranch_C        shopC,
//  					    cBranch_N        shopName,
//  					    SUM(nFoodAmt)    foodAmt,
//  					    COUNT(cBranch_C) billNum,
//  					    CASE
//  					        WHEN (COUNT(cBranch_C) = 0)
//  					        THEN 0
//  					        ELSE ROUND(SUM(nFoodAmt) / COUNT(cBranch_C),2)
//  					    END            amtPerBill,
//  					    SUM(iGuestNum) guestNum,
//  					    CASE
//  					        WHEN (SUM(iGuestNum) = 0)
//  					        THEN 0
//  					        ELSE ROUND(SUM(nFoodAmt) / SUM(iGuestNum) ,2)
//  					    END                      amtPerGuest,
//  					    COUNT(DISTINCT cTable_N) tableNum,
//  					    CASE
//  					        WHEN (SUM(nFoodAmt) = 0)
//  					        THEN 0
//  					        ELSE ROUND(SUM(nFoodAmt) / COUNT(DISTINCT cTable_N) ,2)
//  					    END amtPerTable,
//  					    CASE
//  					        WHEN (SUM(iGuestNum)=0)
//  					        THEN 0
//  					        ELSE ROUND(SUM(iGuestNum) / COUNT(DISTINCT cTable_N) ,2)
//  					    END              guestPerTable,
//  					    SUM(nDisAmt)     disAmt,
//  					    SUM(nRoundAmt)   roundAmt,
//  					    SUM(nPresentAmt) presentAmt,
//  					    SUM(nOughtAmt)   oughtAmt,
//  					    SUM(nPayAmt)     payAmt
//  					FROM
//  					    :Com_("d_t_food_bill")
//  					WHERE
//  					    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') IN
//  					    (
//  					        SELECT
//  					            TO_CHAR(DBUSINESS, 'yyyy-MM-dd')
//  					        FROM
//  					        	:Com_("d_t_food_bill") d
//  					        WHERE
//  					            DBUSINESS >= to_date(:startTime, 'yyyy-MM-dd')
//  					        AND DBUSINESS <= to_date(:endTime,'yyyy-MM-dd')
//  					        GROUP BY
//  					            TO_CHAR(DBUSINESS, 'yyyy-MM-dd') )
//  					GROUP BY
//  					    TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  					    cBranch_C,
//  					    cBranch_N
//  					ORDER BY
//  					    TO_CHAR(DBUSINESS, 'yyyy-MM-dd'),
//  					    cBranch_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = startTime;
  String __sJT_4 = endTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopByDayIterator = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:139^19*/
				
				while(shopByDayIterator.next()){
					ShopBill shopBill = processIter(shopByDayIterator);
					shopBill.businessDate = shopByDayIterator.businessDate();
					shops.add(shopBill);
				}
				shopByDayIterator.close();

			}
			else{
	            throw new NoConnection();
	        }
			
			closeDefaultContext(myCtx);
			return shops;
		}

	public List<ShopBill> listShop(String startTime, String endTime, DefaultContext myCtx)
		throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<ShopBill> shops = new ArrayList<ShopBill>();
		ShopIterator shopIterator = null;
		if (myCtx != null)		{
			/*@lineinfo:generated-code*//*@lineinfo:163^4*/

//  ************************************************************
//  #sql [myCtx] shopIterator = { SELECT
//  			  		'all' businessDate,
//  				     cBranch_C shopC,
//  				     cBranch_N shopName,
//  				     SUM(nFoodAmt) foodAmt,
//  				     COUNT(cBranch_C) billNum,
//  				     
//  				     CASE
//  				        WHEN (COUNT(cBranch_C)  = 0)
//  				        THEN 0
//  				        ELSE ROUND(SUM(nFoodAmt) / COUNT(cBranch_C),2)
//  				     END amtPerBill,
//  				    
//  				     SUM(iGuestNum) guestNum,
//  				     
//  				     CASE
//  				        WHEN (SUM(iGuestNum)  = 0)
//  				        THEN 0
//  				        ELSE ROUND(SUM(nFoodAmt) / SUM(iGuestNum) ,2)
//  				     END amtPerGuest,
//  				     
//  				     COUNT(DISTINCT  cTable_N) tableNum,
//  			
//  				     CASE
//  				        WHEN (SUM(nFoodAmt)  = 0)
//  				        THEN 0
//  				        ELSE DECODE( COUNT(DISTINCT cTable_N),0,0,ROUND(SUM(nFoodAmt) / COUNT(DISTINCT cTable_N) ,2))
//  				     END amtPerTable,
//  				     
//  				     CASE
//  				        WHEN (SUM(iGuestNum)=0)
//  				        THEN 0
//  				        ELSE DECODE(COUNT(DISTINCT cTable_N),0,0,ROUND(SUM(iGuestNum) / COUNT(DISTINCT cTable_N) ,2))
//  				     END guestPerTable,
//  				     
//  			         SUM(nDisAmt)disAmt,
//  			         SUM(nRoundAmt) roundAmt,
//  			         SUM(nPresentAmt) presentAmt,
//  			         SUM(nOughtAmt) oughtAmt,
//  			         SUM(nPayAmt) payAmt
//  				FROM
//  					:Com_("d_t_food_bill")
//  				WHERE
//  				     DBUSINESS >= to_date(:startTime, :strDateFormat)
//  				AND  DBUSINESS <= to_date(:endTime, :strDateFormat)
//  				GROUP BY
//  				     cBranch_C,
//  				     cBranch_N
//  				ORDER BY
//  				     cBranch_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopIterator = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:213^19*/
			
			while(shopIterator.next()){
				ShopBill shopBill = processIter(shopIterator);
				shops.add(shopBill);
			}
			shopIterator.close();
		}
		else{
            throw new NoConnection();
        }
		
		return shops;
	}
	
	public List<ShopBill> listShop(String startTime, String endTime)
			throws NoPrivilegeException,SQLException,NoConnection	{
			List<ShopBill> shops = new ArrayList<ShopBill>();
			DefaultContext myCtx = getDefaultContext();
			ShopIterator shopIterator = null;
			if (myCtx != null)		{
				/*@lineinfo:generated-code*//*@lineinfo:234^5*/

//  ************************************************************
//  #sql [myCtx] shopIterator = { SELECT
//  				  		'all' businessDate,
//  					     cBranch_C shopC,
//  					     cBranch_N shopName,
//  					     SUM(nFoodAmt) foodAmt,
//  					     COUNT(cBranch_C) billNum,
//  					     
//  					     CASE
//  					        WHEN (COUNT(cBranch_C)  = 0)
//  					        THEN 0
//  					        ELSE  round(SUM(nFoodAmt) / COUNT(cBranch_C),2)
//  					     END amtPerBill,
//  					    
//  					     SUM(iGuestNum) guestNum,
//  					     
//  					     CASE
//  					        WHEN (SUM(iGuestNum)  = 0)
//  					        THEN 0
//  					        ELSE   round(SUM(nFoodAmt) / SUM(iGuestNum) ,2)
//  					     END amtPerGuest,
//  					     
//  					     COUNT(DISTINCT  cTable_N) tableNum,
//  					     
//  					     CASE
//  					        WHEN (SUM(nFoodAmt)  = 0)
//  					        THEN 0
//  					        ELSE DECODE( COUNT(DISTINCT cTable_N) ,0,0,ROUND(SUM(nFoodAmt) / COUNT(DISTINCT cTable_N) ,
//  					                2))
//  					     END amtPerTable,
//  					     
//  					     CASE
//  					        WHEN (SUM(iGuestNum)=0)
//  					        THEN 0
//  					        ELSE ROUND( DECODE(COUNT(DISTINCT cTable_N),0,0,SUM(iGuestNum) / COUNT(DISTINCT cTable_N))
//  					                ,2)
//  					     END guestPerTable,
//  					     
//  				         SUM(nDisAmt)disAmt,
//  				         SUM(nRoundAmt) roundAmt,
//  				         SUM(nPresentAmt) presentAmt,
//  				         SUM(nOughtAmt) oughtAmt,
//  				         SUM(nPayAmt) payAmt
//  					FROM
//  						:Com_("d_t_food_bill")
//  					WHERE
//  					     DBUSINESS >= to_date(:startTime, :strDateFormat)
//  					AND  DBUSINESS <= to_date(:endTime, :strDateFormat)
//  					GROUP BY
//  					     cBranch_C,
//  					     cBranch_N
//  					ORDER BY
//  					     cBranch_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopIterator = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:286^20*/
				
				while(shopIterator.next()){
					ShopBill shopBill = processIter(shopIterator);
					shops.add(shopBill);
				}
				shopIterator.close();
			}
			else{
	            throw new NoConnection();
	        }
			
			closeDefaultContext(myCtx);
			return shops;
		}
		
	/**
	 * 按天查询分页，得到数据库中实际的开始日期和结束日期
	 */
	public Map<String, List> listShopByDay(String startTime, String endTime, int startRow, int endRow)
		throws NoPrivilegeException,SQLException,NoConnection {
		Map<String, List> result = new HashMap<String, List>();
		DateIterator dateIterator = null;
		List<String> dateLst = new ArrayList<String>();
		DefaultContext myCtx = getDefaultContext();
		if (myCtx != null) {
			/*@lineinfo:generated-code*//*@lineinfo:312^4*/

//  ************************************************************
//  #sql [myCtx] dateIterator = { SELECT
//  				    *
//  				FROM
//  				    (
//  				        SELECT
//  				            t.*,
//  				            ROWNUM rn
//  				        FROM
//  				            (
//  				                SELECT DISTINCT
//  				                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') settletime
//  				                FROM
//  				                	:Com_("d_t_food_bill") b
//  				                WHERE
//  				                	TO_CHAR(DBUSINESS, 'yyyy-MM-dd') >= :startTime
//  				                AND TO_CHAR(DBUSINESS, 'yyyy-MM-dd') <= :endTime
//  				                ORDER BY
//  				                    TO_CHAR(DBUSINESS, 'yyyy-MM-dd') ) t
//  				        WHERE
//  				            rownum < :endRow )
//  				WHERE
//  				    rn >= :startRow	
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = endTime;
  int __sJT_4 = endRow;
  int __sJT_5 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShopBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      dateIterator = new DateIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:335^4*/
			while(dateIterator.next()) {
				dateLst.add(dateIterator.settletime());
			}
			dateIterator.close();

			//将开始和结束日期之间的每天都列出来进行按天查询
			for (int i = 0; i < dateLst.size(); i++) {
				String date = dateLst.get(i);
				List shopList = listShop(date, date, myCtx); 
				if(shopList.size()>0) {
					result.put(date, shopList);
				}
			}
		} else {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		
		return result;
	}

	private ShopBill processIter(ShopIterator shopIterator) throws SQLException{		
		ShopBill shopBill = new ShopBill();
		shopBill.shopC = shopIterator.shopC();
		shopBill.shopName = shopIterator.shopName();
		shopBill.foodAmt = shopIterator.foodAmt();
		shopBill.billNum = shopIterator.billNum();
		shopBill.amtPerBill = shopIterator.amtPerBill();
		shopBill.guestNum = shopIterator.guestNum();
		shopBill.amtPerGuest = shopIterator.amtPerGuest();
		shopBill.tableNum = shopIterator.tableNum();
		shopBill.amtPerTable = shopIterator.amtPerTable();
		shopBill.guestPerTable = shopIterator.guestPerTable();
		shopBill.disAmt = shopIterator.disAmt();
		shopBill.roundAmt = shopIterator.roundAmt();
		shopBill.presentAmt = shopIterator.presentAmt();
		shopBill.oughtAmt = shopIterator.oughtAmt();
		shopBill.payAmt = shopIterator.payAmt();
		return shopBill;
	}
}/*@lineinfo:generated-code*/class ShopBean_SJProfileKeys 
{
  private static ShopBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShopBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShopBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.ShopBean_SJProfile0");
  }
}
