/*@lineinfo:filename=TablePayBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**查询各个台位的付款方式列表
 *read shop pay type list
 *by cjt
 *first created on 2014.7.7
 *last edited 2014.7.7
 */

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
import pojo.businessquery.TablePay;

public class TablePayBean extends ConnectionPool
{	
	private String strDateFormat = "yyyy-MM-dd";//设置日期格式
	
	/*@lineinfo:generated-code*//*@lineinfo:28^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class TableIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TableIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cTable_NNdx = findColumn("cTable_N");
  }
  public String cTable_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cTable_NNdx);
  }
  private int cTable_NNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:28^60*/
	/*@lineinfo:generated-code*//*@lineinfo:29^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class BillIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BillIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cBill_CNdx = findColumn("cBill_C");
    nPayAmtNdx = findColumn("nPayAmt");
  }
  public String cBill_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBill_CNdx);
  }
  private int cBill_CNdx;
  public float nPayAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(nPayAmtNdx);
  }
  private int nPayAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:29^73*/
	/*@lineinfo:generated-code*//*@lineinfo:30^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PayIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PayIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cPay_NNdx = findColumn("cPay_N");
    nPayAmtNdx = findColumn("nPayAmt");
  }
  public String cPay_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPay_NNdx);
  }
  private int cPay_NNdx;
  public float nPayAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getFloatNoNull(nPayAmtNdx);
  }
  private int nPayAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:30^70*/

	public TablePayBean()
	{
	}
	
	public List<TablePay> listTablePay(String startTime, String endTime, String shopC)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		List<TablePay> tables = new ArrayList<TablePay>();
		DefaultContext myCtx = getDefaultContext();
		TableIterator tableIterator = null;
		if (myCtx != null)
		{
			String strDateFormat = "yyyy-MM-dd";//设置日期格式
			//遍历查找指定门店的台位列表
			/*@lineinfo:generated-code*//*@lineinfo:46^4*/

//  ************************************************************
//  #sql [myCtx] tableIterator = { select distinct cTable_N
//  											from :Com_("d_t_food_bill")
//  											where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  											and DBUSINESS <= to_date(:endTime, :strDateFormat)
//  											and cBranch_C = :shopC order by cTable_N };
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
  String __sJT_6 = shopC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TablePayBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      tableIterator = new TableIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:50^52*/
			while(tableIterator.next())
			{
				TablePay table = new TablePay();
				table.table = tableIterator.cTable_N();
				tables.add(table);
			}
			tableIterator.close();

			for (int i=0; i<tables.size(); i++) 
			{
				TablePay table = tables.get(i);
				String table_N = table.getTable();

				//遍历查找属于指定台位的所有单据
				BillIterator billIterator = null;
				/*@lineinfo:generated-code*//*@lineinfo:66^5*/

//  ************************************************************
//  #sql [myCtx] billIterator = { select cBill_C, nPayAmt 
//  											from :Com_("d_t_food_bill")
//  											where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  											and DBUSINESS <= to_date(:endTime, :strDateFormat)
//  											and cBranch_C = :shopC and cTable_N = :table_N };
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
  String __sJT_6 = shopC;
  String __sJT_7 = table_N;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TablePayBean_SJProfileKeys.getKey(0), 1);
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
      billIterator = new BillIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:70^58*/
				List billCList = new ArrayList();//存储每个单据的编号
	 			while(billIterator.next())
				{
					billCList.add(billIterator.cBill_C());
					table.payAmt += billIterator.nPayAmt();
				}
				billIterator.close();

				//遍历查找每个台位的单据付款类型
				PayIterator payIterator = null;
				table.pay = new HashMap<String, Float>();
				for (int j=0; j<billCList.size(); j++)
				{
					String billC = (String)billCList.get(j);
					/*@lineinfo:generated-code*//*@lineinfo:85^6*/

//  ************************************************************
//  #sql [myCtx] payIterator = { select cPay_N, nPayAmt 
//  												from :Com_("d_t_bill_pay") 
//  												where cBill_C = :billC };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_bill_pay");
  String __sJT_2 = billC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TablePayBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      payIterator = new PayIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:87^35*/
					while (payIterator.next()) 
					{
						String payN = payIterator.cPay_N();
						if (table.pay.containsKey(payN))
						{
							float payAmt = table.pay.get(payN);
							payAmt += payIterator.nPayAmt();
							table.pay.put(payN, payAmt);
						}
						else
						{
							table.pay.put(payN, payIterator.nPayAmt());
						}
					}
					payIterator.close();
				}
			}
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return tables;
	}

	public Map<String, List> listTablePayByDay(String startTime, String endTime, String shopC)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		Map<String, List> result = new TreeMap<String, List>();
		List<String> dateList = getDates(startTime, endTime);
		for (int i=0; i<dateList.size()-1; i++) 
		{
			String date = dateList.get(i);
			String nextDate = dateList.get(i+1);
			List<TablePay> shopList = listTablePay(date, nextDate, shopC); 
			if(shopList.size()>0)
			{
				result.put(date, shopList);
			}
		}
		return result;
	}

	/**
	 * Get the Dates between Start Date and End Date.
	 * @param start_date	Start Date
	 * @param end_date		End Date
	 * @return Dates List
	 */
	public List<String> getDates(String start_date, String end_date) 
	{	
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		Calendar p_start = new GregorianCalendar();
		Calendar p_end = new GregorianCalendar();

		try 
		{
			p_start.setTime(sdf.parse(start_date));
			p_end.setTime(sdf.parse(end_date));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}

		List<String> result = new ArrayList<String>();
		if(p_start.before(p_end))
		{
			while (p_start.before(p_end)) 
			{
				result.add(sdf.format(p_start.getTime()));
				p_start.add(Calendar.DAY_OF_YEAR, 1);
			}
			result.add(sdf.format(p_start.getTime()));
		}
		return result;
	}
}/*@lineinfo:generated-code*/class TablePayBean_SJProfileKeys 
{
  private static TablePayBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TablePayBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TablePayBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.TablePayBean_SJProfile0");
  }
}
