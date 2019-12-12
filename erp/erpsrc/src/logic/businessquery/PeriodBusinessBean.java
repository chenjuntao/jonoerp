/*@lineinfo:filename=PeriodBusinessBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.businessquery;

import sqlj.runtime.ref.*;
import java.sql.*;
import java.util.*;
import logic.pool.ConnectionPool;
import logic.*;
import com.tanry.framework.acl.*;
import java.util.Map;
import java.util.HashMap;

public class PeriodBusinessBean extends ConnectionPool{
	/*@lineinfo:generated-code*//*@lineinfo:13^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PeriodBusinessIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PeriodBusinessIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    rownumberNdx = findColumn("rownumber");
    timeSlotNdx = findColumn("timeSlot");
    guestCountNdx = findColumn("guestCount");
    billCountNdx = findColumn("billCount");
    guestPerTimeNdx = findColumn("guestPerTime");
    guestPerCostNdx = findColumn("guestPerCost");
    foodAmtSumNdx = findColumn("foodAmtSum");
    payAmtNdx = findColumn("payAmt");
    shopCNdx = findColumn("shopC");
    shopNameNdx = findColumn("shopName");
    oughtAmtNdx = findColumn("oughtAmt");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
  public String timeSlot() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(timeSlotNdx);
  }
  private int timeSlotNdx;
  public Integer guestCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(guestCountNdx);
  }
  private int guestCountNdx;
  public Integer billCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(billCountNdx);
  }
  private int billCountNdx;
  public Integer guestPerTime() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(guestPerTimeNdx);
  }
  private int guestPerTimeNdx;
  public Double guestPerCost() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(guestPerCostNdx);
  }
  private int guestPerCostNdx;
  public Double foodAmtSum() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(foodAmtSumNdx);
  }
  private int foodAmtSumNdx;
  public Double payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payAmtNdx);
  }
  private int payAmtNdx;
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
  public Double oughtAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(oughtAmtNdx);
  }
  private int oughtAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:25^19*/
	
	public PeriodBusinessBean(){
	}
	
	public List<Map> listPeriodBusiness(String queryText)
		throws NoPrivilegeException,SQLException,NoConnection{
		DefaultContext myCtx = getDefaultContext();
		PeriodBusinessIterator periodBusinessIterator = null;
		
		List<Map> headerLst = new ArrayList<Map>();
		if (myCtx != null){
			
			/*@lineinfo:generated-code*//*@lineinfo:38^4*/

//  ************************************************************
//  #sql [myCtx] periodBusinessIterator = { WITH
//  				    t1 AS
//  				    (
//  				        SELECT
//  				            TO_CHAR(b.dtBillTime,'hh24')                               timeSlot,
//  				            SUM(b.iGuestNum)                                           guestCount,
//  				            COUNT(b.CBILL_C)                                           billCount,
//  				            DECODE(SUM(b.iGuestNum),0,0,floor(60 / SUM(b.iGuestNum)) )               guestPerTime,
//  				            DECODE(SUM(b.iGuestNum), 0,0,ROUND(SUM(nFoodAmt) / SUM(b.iGuestNum),2 )) guestPerCost,
//  				            SUM(nFoodAmt)                                                            foodAmtSum,
//  				            SUM (nPayAmt)                                                            payAmt,
//  				            SUM(nOughtAmt)                                                           oughtAmt,
//  				            b.CBRANCH_N                                                              shopName,
//  				            b.CBRANCH_C                                                              shopC
//  				        FROM
//  				            :Com_("d_t_food_bill") b
//  				        WHERE
//  				        	:queryText
//  				        GROUP BY
//  				            b.CBRANCH_C,
//  				            b.CBRANCH_N,
//  				            TO_CHAR(b.dtBillTime,'hh24')
//  				    )
//  				    ,
//  				    t2 AS
//  				    (
//  				        SELECT
//  				            t1.timeSlot,
//  				            SUM(t1.guestCount) guestCount,
//  				            SUM(t1.billCount)  billCount,
//  				            t1.guestPerTime,
//  				            t1.guestPerCost,
//  				            SUM(t1.foodAmtSum)foodAmtSum,
//  				            SUM(t1.payAmt)    payAmt,
//  				            SUM(t1.oughtAmt)  oughtAmt,
//  				            t1.shopName,
//  				            t1.shopC
//  				        FROM
//  				            t1
//  				        GROUP BY
//  				            GROUPING SETS( (t1.shopC,t1.shopName,t1.timeSlot,t1.guestCount,t1.billCount,
//  				            t1.guestPerTime, t1.guestPerCost, t1.foodAmtSum ,t1.payAmt,t1.oughtAmt),NULL)
//  				        ORDER BY
//  				            t1.timeSlot,
//  				            t1.shopC
//  				    )
//  				SELECT
//  				    DECODE(t2.timeSlot,NULL,'合计',rownum) rownumber,
//  				    DECODE(t2.timeSlot,NULL,'合计',rownum) id,
//  				    t2.*
//  				FROM
//  				    t2 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = queryText;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PeriodBusinessBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      periodBusinessIterator = new PeriodBusinessIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:90^11*/

			while(periodBusinessIterator.next()){
				Map header = new HashMap();
				
				header.put("id", periodBusinessIterator.id());
				header.put("rownumber", periodBusinessIterator.rownumber());
				header.put("timeSlot", periodBusinessIterator.timeSlot());
				header.put("guestCount", periodBusinessIterator.guestCount());
				header.put("billCount", periodBusinessIterator.billCount());
				header.put("guestPerTime", periodBusinessIterator.guestPerTime());
				header.put("guestPerCost", periodBusinessIterator.guestPerCost());
				header.put("foodAmtSum", periodBusinessIterator.foodAmtSum());
				header.put("oughtAmt", periodBusinessIterator.oughtAmt());
				header.put("payAmt", periodBusinessIterator.payAmt());
				header.put("shopC", periodBusinessIterator.shopC());
				header.put("shopName", periodBusinessIterator.shopName());
				headerLst.add(header);
			}
			periodBusinessIterator.close();
		}else {
            throw new NoConnection();
        }
		
		closeDefaultContext(myCtx);
		return headerLst;
	}
}/*@lineinfo:generated-code*/class PeriodBusinessBean_SJProfileKeys 
{
  private static PeriodBusinessBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PeriodBusinessBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PeriodBusinessBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.PeriodBusinessBean_SJProfile0");
  }
}
