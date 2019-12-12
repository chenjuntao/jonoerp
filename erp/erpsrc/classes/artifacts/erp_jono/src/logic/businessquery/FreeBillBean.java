/*@lineinfo:filename=FreeBillBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
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

import pojo.businessquery.FreeBill;

public class FreeBillBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd"; //设置日期格式

	/*@lineinfo:generated-code*//*@lineinfo:23^2*/

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
    payAmtNdx = findColumn("payAmt");
    foodAmtNdx = findColumn("foodAmt");
    billCNdx = findColumn("billC");
    tableNdx = findColumn("table");
    periodNdx = findColumn("period");
    shiftNdx = findColumn("shift");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
  }
  public String businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessDateNdx);
  }
  private int businessDateNdx;
  public Double payAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(payAmtNdx);
  }
  private int payAmtNdx;
  public Double foodAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(foodAmtNdx);
  }
  private int foodAmtNdx;
  public String billC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(billCNdx);
  }
  private int billCNdx;
  public String table() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tableNdx);
  }
  private int tableNdx;
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

/*@lineinfo:user-code*//*@lineinfo:32^16*/
	
	/*@lineinfo:generated-code*//*@lineinfo:34^2*/

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
    idNdx = findColumn("id");
    rownumberNdx = findColumn("rownumber");
    businessDateNdx = findColumn("businessDate");
    billCNdx = findColumn("billC");
    tableNdx = findColumn("table");
    periodNdx = findColumn("period");
    shiftNdx = findColumn("shift");
    shopCNdx = findColumn("shopC");
    shopNNdx = findColumn("shopN");
    foodNNdx = findColumn("foodN");
    priceNdx = findColumn("price");
    qtyNdx = findColumn("qty");
    amtNdx = findColumn("amt");
    unitNdx = findColumn("unit");
    smallCNdx = findColumn("smallC");
    smallNNdx = findColumn("smallN");
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
  public String businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(businessDateNdx);
  }
  private int businessDateNdx;
  public String billC() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(billCNdx);
  }
  private int billCNdx;
  public String table() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tableNdx);
  }
  private int tableNdx;
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
  public String foodN() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(foodNNdx);
  }
  private int foodNNdx;
  public Double price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(priceNdx);
  }
  private int priceNdx;
  public Double qty() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(qtyNdx);
  }
  private int qtyNdx;
  public Double amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtNdx);
  }
  private int amtNdx;
  public String unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(unitNdx);
  }
  private int unitNdx;
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

/*@lineinfo:user-code*//*@lineinfo:50^17*/
	

	public FreeBillBean(){
	}
	
	public int getHeadCount(String startDate,String endDate,String shopC,String period,String shift)
			throws NoPrivilegeException,SQLException,NoConnection	{
		int counts = 0;
		DefaultContext myCtx = getDefaultContext();
		
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:63^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  					count(*) 
//  				    FROM
//  				    :Com_("D_T_FOOD_BILL") b
//  				    LEFT JOIN
//  				    :Com_("d_t_bill_pay") p
//  				    ON
//  				        b.CBILL_C = p.CBILL_C
//  				    WHERE
//  				    	b.CBRANCH_C like :shopC
//  				    and b.CPERIOD_N like :period
//  				    and b.CSHIFT_N like :shift
//  					AND p.cPay_C = '05'
//  					AND TO_CHAR(b.DBUSINESS,'yyyy-mm-dd')>=:startDate
//  					AND TO_CHAR(b.DBUSINESS,'yyyy-mm-dd')<=:endDate
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = shopC;
  String __sJT_4 = period;
  String __sJT_5 = shift;
  String __sJT_6 = startDate;
  String __sJT_7 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FreeBillBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:79^4*/
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return counts;
	}
	

	public List<FreeBill> getHead(String startDate,String endDate,String shopC,String period,
			String shift,int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<FreeBill> result = new ArrayList<FreeBill>();
		DefaultContext myCtx = getDefaultContext();
		
		HeadIterator headIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:100^4*/

//  ************************************************************
//  #sql [myCtx] headIterator = { SELECT
//  				    *
//  				    FROM
//  				        (
//  				            SELECT
//  				                t.*,
//  				                ROWNUM rowNumber
//  				            FROM
//  				                (
//  				                    SELECT
//  				                        TO_CHAR(b.DBUSINESS,'yyyy-mm-dd') businessDate,
//  				                        p.nPayAmt                         payAmt,
//  				                        b.cSettleMan                      payMan,
//  				                        b.nFoodAmt                        foodAmt,
//  				                        p.cBill_C                         billC,
//  				                        b.cTable_N                        table,
//  				                        b.cPeriod_N                       period,
//  				                        b.cShift_N                        shift,
//  				                        b.cBranch_C                       shopC,
//  				                        b.cBranch_N                       shopN
//  				                    FROM
//  				                    :Com_("D_T_FOOD_BILL") b
//  				                    LEFT JOIN
//  				                    :Com_("d_t_bill_pay") p
//  				                    ON
//  				                        b.CBILL_C = p.CBILL_C
//  				                    WHERE
//  				                        b.CBRANCH_C LIKE :shopC
//  				                    AND b.CPERIOD_N LIKE :period
//  				                    AND b.CSHIFT_N LIKE :shift
//  				                    AND p.cPay_C = '05'
//  				                    AND TO_CHAR(b.DBUSINESS,'yyyy-mm-dd')>=:startDate
//  				                    AND TO_CHAR(b.DBUSINESS,'yyyy-mm-dd')<=:endDate
//  				                    ORDER BY
//  				                        TO_CHAR(b.DBUSINESS,'yyyy-mm-dd'),
//  				                        b.CBILL_C
//  			                         )t
//  				            WHERE
//  				                ROWNUM <= :endRow)
//  				    WHERE
//  				    rowNumber >= :startRow
//  		     };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = shopC;
  String __sJT_4 = period;
  String __sJT_5 = shift;
  String __sJT_6 = startDate;
  String __sJT_7 = endDate;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FreeBillBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:142^7*/
			
			while(headIterator.next()) {
				FreeBill freeBillBean = new FreeBill();
				freeBillBean.shift = headIterator.shift();
				freeBillBean.period = headIterator.period();
//				freeBillBean.businessDate = headIterator.businessDate();
//				freeBillBean.payAmt = headIterator.payAmt();
//				freeBillBean.foodAmt = headIterator.foodAmt();
//				freeBillBean.payMan = headIterator.payMan();
//				freeBillBean.billC = headIterator.billC();
//				freeBillBean.table = headIterator.table();
//				freeBillBean.shopC = headIterator.shopC();
//				freeBillBean.shopN = headIterator.shopN();
				result.add(freeBillBean);
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
	
	public List<Map> getDetail(String queryText,int startRow,int endRow)
					throws NoPrivilegeException,SQLException,NoConnection	{
		List<Map> headerLst = new ArrayList<Map>();
		DefaultContext myCtx = getDefaultContext();
		
		DetailIterator detailIterator = null;
		if (myCtx != null){
			/*@lineinfo:generated-code*//*@lineinfo:175^4*/

//  ************************************************************
//  #sql [myCtx] detailIterator = { SELECT
//  				    *
//  				    FROM
//  				        (
//  				            SELECT
//  					            t.*,
//  					            ROWNUM                                   rowIndex,
//  					            DECODE(billC,NULL,'合计', TO_CHAR(ROWNUM)) rownumber,
//  					            ROWNUM                                   id
//  				            FROM
//  				                (
//  				                    SELECT
//  				                        b.cBill_C                         billC,
//  				                        TO_CHAR(b.DBUSINESS,'yyyy-mm-dd') businessDate,
//  				                        b.cTable_N                        table,
//  				                        b.cPeriod_N                       period,
//  				                        b.cShift_N                        shift,
//  				                        b.cBranch_C                       shopC,
//  				                        b.cBranch_N                       shopN,
//  				                        bs.cFood_C                        foodC,
//  				                        bs.cFood_N                        foodN,
//  				                        bs.nPrc                           price,
//  				                        SUM(bs.nQty)                      qty,
//  				                        SUM(bs.nAmt)                      amt,
//  				                        bs.sUnit                          unit,
//  				                        bs.cLitCls_C                      smallC,
//  				                        bs.cLitCls_N                      smallN
//  				                    FROM
//  				                        :Com_("D_T_FOOD_BILL") b
//  				                    INNER JOIN
//  				                        :Com_("d_t_food_Bills") bs
//  				                    ON
//  				                        b.cBill_C = bs.cBill_C  :queryText
//  			                        INNER JOIN
//  				                        :Com_("d_t_bill_pay") p
//  				                    ON
//  				                        b.CBILL_C = p.CBILL_C  AND p.CPAY_N = '免单' 
//  	                        		  GROUP BY
//  		                                  GROUPING SETS( ( b.cBill_C , TO_CHAR(b.DBUSINESS,'yyyy-mm-dd'),bs.cFood_C,
//  		                                  bs.cFood_N, b.cTable_N, b.cPeriod_N, b.cShift_N, b.cBranch_C,
//  		                                  b.cBranch_N, bs.nPrc, bs.nQty, bs.nAmt, bs.sUnit,
//  		                                  bs.cLitCls_C, bs.cLitCls_N ),NULL)
//  				                    ORDER BY
//  				                        TO_CHAR(b.DBUSINESS,'yyyy-mm-dd'),
//  				                        b.CBILL_C )t
//  				            WHERE
//  				                ROWNUM <= :endRow)
//  				    WHERE
//  				    	rowIndex >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  String __sJT_2 = Com_("d_t_food_Bills");
  String __sJT_3 = queryText;
  String __sJT_4 = Com_("d_t_bill_pay");
  int __sJT_5 = endRow;
  int __sJT_6 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FreeBillBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:224^31*/
			
			while(detailIterator.next()) {
				Map header = new HashMap();
				
				header.put("id", detailIterator.id());
				header.put("rownumber", detailIterator.rownumber());
				header.put("shift", detailIterator.shift());
//				header.put("period", detailIterator.period());
//				header.put("businessDate", detailIterator.businessDate());
//				header.put("billC", detailIterator.billC());
//				header.put("table", detailIterator.table());
//				header.put("shopC", detailIterator.shopC());
//				header.put("shopN", detailIterator.shopN());
//				header.put("foodN", detailIterator.foodN());
//				header.put("price", detailIterator.price());
//				header.put("qty", detailIterator.qty());
//				header.put("amt", detailIterator.amt());
//				header.put("unit", detailIterator.unit());
//				header.put("smallC", detailIterator.smallC());
//				header.put("smallN", detailIterator.smallN());
				
				headerLst.add(header);
			}
			detailIterator.close();
		}else{
			throw new NoConnection();
		}
		
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public int getDetailCount(String queryText) throws NoPrivilegeException,SQLException,NoConnection{
		DefaultContext myCtx = getDefaultContext();
		
		int counts = 0;
		if (myCtx != null){
			/*@lineinfo:generated-code*//*@lineinfo:262^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  	            	COUNT(*) 
//  	            FROM
//  	            	:Com_("D_T_FOOD_BILL") b
//  	            INNER JOIN
//  	            	:Com_("d_t_food_Bills") bs
//  	            ON
//  	                b.cBill_C = bs.cBill_C :queryText
//              	INNER JOIN
//  	            	:Com_("d_t_bill_pay") p
//  	            ON
//  	                b.CBILL_C = p.CBILL_C  AND p.CPAY_N = '免单' 
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  String __sJT_2 = Com_("d_t_food_Bills");
  String __sJT_3 = queryText;
  String __sJT_4 = Com_("d_t_bill_pay");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FreeBillBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:275^4*/
		}else{
			throw new NoConnection();
		}
		
		closeDefaultContext(myCtx);
		return counts;
	}
}/*@lineinfo:generated-code*/class FreeBillBean_SJProfileKeys 
{
  private static FreeBillBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FreeBillBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FreeBillBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.FreeBillBean_SJProfile0");
  }
}
