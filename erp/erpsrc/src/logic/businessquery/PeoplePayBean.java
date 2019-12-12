/*@lineinfo:filename=PeoplePayBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.businessquery;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;

public class PeoplePayBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PeoplePayBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:25^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DataIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DataIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cBranch_CNdx = findColumn("cBranch_C");
    cBranch_NNdx = findColumn("cBranch_N");
    cSettleManNdx = findColumn("cSettleMan");
    cPay_CNdx = findColumn("cPay_C");
    cPay_NNdx = findColumn("cPay_N");
    readPayCodeNdx = findColumn("readPayCode");
    nPayAmtNdx = findColumn("nPayAmt");
  }
  public String cBranch_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBranch_CNdx);
  }
  private int cBranch_CNdx;
  public String cBranch_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBranch_NNdx);
  }
  private int cBranch_NNdx;
  public String cSettleMan() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cSettleManNdx);
  }
  private int cSettleManNdx;
  public String cPay_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPay_CNdx);
  }
  private int cPay_CNdx;
  public String cPay_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPay_NNdx);
  }
  private int cPay_NNdx;
  public String readPayCode() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(readPayCodeNdx);
  }
  private int readPayCodeNdx;
  public Double nPayAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nPayAmtNdx);
  }
  private int nPayAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:33^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ColumnIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ColumnIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cPay_CNdx = findColumn("cPay_C");
    cPay_NNdx = findColumn("cPay_N");
  }
  public String cPay_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPay_CNdx);
  }
  private int cPay_CNdx;
  public String cPay_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPay_NNdx);
  }
  private int cPay_NNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^3*/
	
	/**
	 * startDate 	营业开始日期
	 * endDate	  	营业结束日期
	 * branchId 	门店Id
	 * peopleId     结帐人员Id
	 */
	public List<Map> query(java.util.Date startDate, java.util.Date endDate, String branchId, String peopleId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		
		if (!TextUtil.isEmpty(branchId)) { 
			query = query + " AND b.cBranch_C = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(peopleId)) { 
			query = query + " AND b.cSettleMan = '" + peopleId + "'";
		}
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		DataIter dataIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:63^3*/

//  ************************************************************
//  #sql [myCtx] dataIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                b.cBranch_C ,
//  			                '['|| cBranch_C || ']'|| b.cBranch_N cBranch_N,
//  			                b.cSettleMan cSettleMan,
//  			                'pay_' || p.cPay_C cPay_C,
//  			                p.cPay_C readPayCode,
//  			                p.cPay_N,
//  			                SUM(p.nPayAmt) nPayAmt
//  			            FROM
//  			                :Com_("d_t_food_bill") b
//  			            LEFT JOIN
//  			                :Com_("d_t_bill_pay") p
//  			            ON
//  			                b.cBill_C = p.cBill_C
//  			            WHERE
//  			                :query
//  			            AND (
//  			                    DBUSINESS >= :sDate
//  			                OR  :sDate IS NULL)
//  			            AND (
//  			                    DBUSINESS <= :eDate
//  			                OR  :eDate IS NULL)
//  			            AND p.CPAY_C IS NOT NULL
//  			            GROUP BY
//  			                b.cBranch_C ,
//  			                b.cBranch_N ,
//  			                b.cSettleMan,
//  			                p.cPay_C,
//  			                p.cPay_N
//  			            UNION ALL
//  			            SELECT
//  			                b.cBranch_C ,
//  			                '['|| cBranch_C || ']'|| b.cBranch_N cBranch_N,
//  			                b.cSettleMan cSettleMan,
//  			                'pay_sum'      cPay_C,
//  			                 '' readPayCode,
//  			                '合计'           cPay_N ,
//  			                SUM(p.nPayAmt) nPayAmt
//  			            FROM
//  			                :Com_("d_t_food_bill") b
//  			            LEFT JOIN
//  			                :Com_("d_t_bill_pay") p
//  			            ON
//  			                b.cBill_C = p.cBill_C
//  			            WHERE
//  			                :query
//  			            AND (
//  			                    DBUSINESS >= :sDate
//  			                OR  :sDate IS NULL)
//  			            AND (
//  			                    DBUSINESS <= :eDate
//  			                OR  :eDate IS NULL)
//  			            AND p.CPAY_C IS NOT NULL
//  			            GROUP BY
//  			                b.cBranch_C ,
//  			                b.cBranch_N ,
//  			                b.CSETTLEMAN) a
//  			    ORDER BY
//  			        cBranch_C,
//  			        CSETTLEMAN,
//  			        CPAY_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  String __sJT_8 = Com_("d_t_food_bill");
  String __sJT_9 = Com_("d_t_bill_pay");
  String __sJT_10 = query;
  Date __sJT_11 = sDate;
  Date __sJT_12 = sDate;
  Date __sJT_13 = eDate;
  Date __sJT_14 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PeoplePayBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_stmt.setDate(14, __sJT_14);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      dataIter = new DataIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:128^18*/
		List<Map> returnLst = returnProcessIter(dataIter);
		dataIter.close();
		closeDefaultContext(myCtx);
		return returnLst;
	}
	
	/**
	 * startDate 	营业开始日期
	 * endDate	  	营业结束日期
	 * branchId 	门店Id
	 * peopleId     结帐人员Id
	 */
	public List<Map> queryColumns(java.util.Date startDate, java.util.Date endDate, String branchId, String peopleId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		
		if (!TextUtil.isEmpty(branchId)) { 
			query = query + " AND b.cBranch_C = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(peopleId)) { 
			query = query + " AND b.cSettleMan = '" + peopleId + "'";
		}
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		ColumnIter columnIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:158^3*/

//  ************************************************************
//  #sql [myCtx] columnIter = { SELECT
//  				'pay_' || p.cPay_C cPay_C,
//  			    p.cPay_N
//  			FROM
//  			    :Com_("d_t_food_bill") b
//  			LEFT JOIN
//  			    :Com_("d_t_bill_pay") p
//  			ON
//  			    b.cBill_C = p.cBill_C
//  			WHERE
//  			    :query
//  			AND (
//  			        DBUSINESS >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        DBUSINESS <= :eDate
//  			    OR  :eDate IS NULL)
//  			AND p.CPAY_C IS NOT NULL
//  			GROUP BY
//  			    p.cPay_C,
//  			    p.cPay_N
//  			ORDER BY
//  			    p.CPAY_C };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PeoplePayBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      columnIter = new ColumnIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:181^16*/
		List<Map> returnLst = returnColumnIter(columnIter);
		columnIter.close();
		closeDefaultContext(myCtx);
		return returnLst;
	}
	
	
	private List<Map> returnColumnIter(ColumnIter columnIter) 
			throws SQLException {
		List<Map> headLst = new ArrayList<Map>();
		while(columnIter.next()) {
			Map<String, Object> head = new HashMap<String, Object>();
			head.put("cPay_C", columnIter.cPay_C());
			head.put("cPay_N", columnIter.cPay_N());
			
			headLst.add(head);
		}
		return headLst;
	}
	
	private List<Map> returnProcessIter(DataIter dataIter) 
			throws SQLException {
		List<Map> headLst = new ArrayList<Map>();
		while(dataIter.next()) {
			Map<String, Object> head = new HashMap<String, Object>();
			head.put("cBranch_C", dataIter.cBranch_C());
			head.put("cBranch_N", dataIter.cBranch_N());
			head.put("cSettleMan", dataIter.cSettleMan());
			head.put("cPay_C", dataIter.cPay_C());
			head.put("cPay_N", dataIter.cPay_N());
			head.put("readPayCode", dataIter.readPayCode());
			head.put("nPayAmt", dataIter.nPayAmt());
			
			headLst.add(head);
		}
		return headLst;
	}
	
}/*@lineinfo:generated-code*/class PeoplePayBean_SJProfileKeys 
{
  private static PeoplePayBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PeoplePayBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PeoplePayBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.PeoplePayBean_SJProfile0");
  }
}
