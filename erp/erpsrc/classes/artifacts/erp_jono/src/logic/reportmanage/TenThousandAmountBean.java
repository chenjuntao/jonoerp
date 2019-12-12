/*@lineinfo:filename=TenThousandAmountBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.acl.NoPrivilegeException;

public class TenThousandAmountBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(TenThousandAmountBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:26^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    costNdx = findColumn("cost");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public Double cost() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(costNdx);
  }
  private int costNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:30^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:32^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class AmtIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public AmtIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    amtNdx = findColumn("amt");
  }
  public Double amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amtNdx);
  }
  private int amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:34^2*/
	
	public Double getAmt(java.util.Date startDate1, java.util.Date endDate1,String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		Date _startDate1 = SqlDateUtil.getSqlDate(startDate1);
		Date _endDate1 = SqlDateUtil.getSqlDate(endDate1);
		
		DefaultContext myCtx = getDefaultContext();
		Double amt = null;
		
		AmtIter amtIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:46^3*/

//  ************************************************************
//  #sql [myCtx] amtIter = { SELECT
//  			    SUM(b.BUSINESS_AMT) amt
//  			    FROM
//  			    :Com_("D_T3_DAILY_BUSINESS_AMT") b
//  			    WHERE
//  			        b.BRANCH_ID = :branchId
//  			    AND b.BUSINESS_DATE >= :_startDate1
//  			    AND b.BUSINESS_DATE <= :_endDate1
//  			    GROUP BY
//  			        b.BRANCH_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_BUSINESS_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = _startDate1;
  Date __sJT_4 = _endDate1;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TenThousandAmountBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      amtIter = new AmtIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:56^23*/
		
		if(amtIter.next()){
			amt = amtIter.amt();
		}
		
		closeDefaultContext(myCtx);
					
		return amt;
	}
	
	public List<Map> query(String branchId,String itemId,Double amt,java.util.Date startDate, java.util.Date endDate)  
			throws NoPrivilegeException,SQLException,NoConnection {
		Date _startDate = SqlDateUtil.getSqlDate(startDate);
		Date _endDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:74^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            t.ITEM_ID,
//  			            t.ITEM_NAME
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        WHERE
//  			            t.THERAPY_ID = :itemId
//  			    )
//  			SELECT
//  			    a.item_id,
//  			    a.item_name,
//  			    ROUND( SUM( NVL(r.AMT_ACTUAL,r.AMT_THEORY)* r.item_price/:amt * 10000),2) cost
//  			FROM
//  			    a
//  			JOIN
//  			:Com_("D_T3_DAILY_RAW_AMT") r
//  			ON
//  			    a.ITEM_ID = r.ITEM_ID
//  			AND r.BRANCH_ID = :branchId
//  			AND r.PRICE_TYPE = 'BENCHMARK'
//  		    AND r.BUSINESS_DATE >= :_startDate
//  		    AND r.BUSINESS_DATE <= :_endDate
//  			GROUP BY
//  			    a.ITEM_ID,
//  			    a.ITEM_NAME
//  			ORDER BY
//  				cost desc };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = itemId;
  Double __sJT_3 = amt;
  String __sJT_4 = Com_("D_T3_DAILY_RAW_AMT");
  String __sJT_5 = branchId;
  Date __sJT_6 = _startDate;
  Date __sJT_7 = _endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TenThousandAmountBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:104^14*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("cost", headerIter.cost());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class TenThousandAmountBean_SJProfileKeys 
{
  private static TenThousandAmountBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TenThousandAmountBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TenThousandAmountBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.TenThousandAmountBean_SJProfile0");
  }
}
