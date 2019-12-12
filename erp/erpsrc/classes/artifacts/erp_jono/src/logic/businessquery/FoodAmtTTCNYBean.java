/*@lineinfo:filename=FoodAmtTTCNYBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**根据查营业信息询万元出品量
 *read food amount per ten throusand CNY by business info
 *by cjt
 *first created on 2014.7.21
 *last edited on 2014.7.21
 */

package logic.businessquery;

import sqlj.runtime.*;
import sqlj.runtime.ref.*;
import java.sql.*;
import java.math.BigDecimal;
import oracle.sql.*;
import oracle.sqlj.runtime.*;
import oracle.jdbc.driver.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import logic.pool.ConnectionPool;
import logic.*;
import com.tanry.framework.acl.*;
import pojo.businessquery.FoodAmtTTCNY;

public class FoodAmtTTCNYBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd";//设置日期格式

	/*@lineinfo:generated-code*//*@lineinfo:29^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class FoodIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public FoodIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cFood_CNdx = findColumn("cFood_C");
    cFood_NNdx = findColumn("cFood_N");
    sUnitNdx = findColumn("sUnit");
    nQtyNdx = findColumn("nQty");
    amtTTCNYNdx = findColumn("amtTTCNY");
  }
  public String cFood_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cFood_CNdx);
  }
  private int cFood_CNdx;
  public String cFood_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cFood_NNdx);
  }
  private int cFood_NNdx;
  public String sUnit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sUnitNdx);
  }
  private int sUnitNdx;
  public int nQty() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(nQtyNdx);
  }
  private int nQtyNdx;
  public float amtTTCNY() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(amtTTCNYNdx);
  }
  private int amtTTCNYNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:34^18*/ 

	public FoodAmtTTCNYBean() {
	}

	/**
	 * 每万元出品数量计算公式：SUM(c.nAmt) / SUM(c.nQty) = 10000 / 每万元出品数量
	 */
	public List<FoodAmtTTCNY> listFoodAmtTTCNY(String shopC, String startTime, String endTime, String categoryId)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		List<FoodAmtTTCNY> foods = new ArrayList<FoodAmtTTCNY>();
		DefaultContext myCtx = getDefaultContext();
		FoodIterator foodIterator = null;
		if (myCtx != null)
		{
			//查询该时间段内某餐厅总营业额
			BigDecimal amt = BigDecimal.ZERO;
			Object amtObj = null;
			String shopCStr = shopC + "%";
			/*@lineinfo:generated-code*//*@lineinfo:54^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT sum(npayamt) 
//  				FROM :Com_("d_t_food_bill")
//  				WHERE cbranch_c like :shopCStr
//    				AND dbusiness >= to_date(:startTime, :strDateFormat)
//    				AND dbusiness <= to_date(:endTime, :strDateFormat)
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopCStr;
  String __sJT_3 = startTime;
  String __sJT_4 = strDateFormat;
  String __sJT_5 = endTime;
  String __sJT_6 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodAmtTTCNYBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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
    amtObj = __sJT_rtRs.getObject(1, Object.class);
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

/*@lineinfo:user-code*//*@lineinfo:60^4*/
			if (amtObj != null) {
				amt = (BigDecimal)amtObj;
			}
			amt = amt.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			//查询某类别下所有的出品数量
			/*@lineinfo:generated-code*//*@lineinfo:67^4*/

//  ************************************************************
//  #sql [myCtx] foodIterator = { SELECT
//  					    c.cFood_C ,
//  					    c.cFood_N,
//  					    c.sUnit,
//  					    ROUND(SUM(c.nQty),2) nQty,
//  					    CASE
//  					        WHEN (:amt = 0)
//  					        THEN 0
//  					        ELSE ROUND(SUM(c.nQty) * 10000/ :amt,2)
//  					    END amtTTCNY
//  					FROM
//  						:Com_("d_t_food_bills") c
//  					LEFT JOIN
//  						:Com_("d_t_food_bill") a
//  					ON
//  					    a.cBill_C = c.cBill_C
//  					WHERE
//  					    c.cLitCls_C =:categoryId
//  					AND a.cbranch_c like :shopCStr
//  					AND a.DBUSINESS >= to_date(:startTime, 'yyyy-mm-dd')
//  					AND a.DBUSINESS <= to_date(:endTime, 'yyyy-mm-dd')
//  					GROUP BY
//  					    c.cFood_C,
//  					    c.cFood_N,
//  					    c.sUnit
//  					ORDER BY
//  					    1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  BigDecimal __sJT_1 = amt;
  BigDecimal __sJT_2 = amt;
  String __sJT_3 = Com_("d_t_food_bills");
  String __sJT_4 = Com_("d_t_food_bill");
  String __sJT_5 = categoryId;
  String __sJT_6 = shopCStr;
  String __sJT_7 = startTime;
  String __sJT_8 = endTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodAmtTTCNYBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setBigDecimal(1, __sJT_1);
      __sJT_stmt.setBigDecimal(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      foodIterator = new FoodIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:94^11*/
			while(foodIterator.next())
			{
				FoodAmtTTCNY foodAmtTTCNY = new FoodAmtTTCNY();
				foodAmtTTCNY.foodId = foodIterator.cFood_C();
				foodAmtTTCNY.foodName = foodIterator.cFood_N();
				foodAmtTTCNY.foodUnit = foodIterator.sUnit();
				foodAmtTTCNY.foodNum = foodIterator.nQty();
				foodAmtTTCNY.amtTTCNY = foodIterator.amtTTCNY();
				foods.add(foodAmtTTCNY);
			}
			foodIterator.close();
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return foods;
	}
}/*@lineinfo:generated-code*/class FoodAmtTTCNYBean_SJProfileKeys 
{
  private static FoodAmtTTCNYBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodAmtTTCNYBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodAmtTTCNYBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.FoodAmtTTCNYBean_SJProfile0");
  }
}
