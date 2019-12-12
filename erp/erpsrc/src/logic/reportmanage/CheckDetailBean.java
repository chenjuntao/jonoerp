/*@lineinfo:filename=CheckDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class CheckDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PurchasingDetailBean.class);
	
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
    form_idNdx = findColumn("form_id");
    form_timeNdx = findColumn("form_time");
    check_branchNdx = findColumn("check_branch");
    check_storageNdx = findColumn("check_storage");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    category_nameNdx = findColumn("category_name");
    check_countNdx = findColumn("check_count");
    theory_countNdx = findColumn("theory_count");
    diffCountNdx = findColumn("diffCount");
    item_unit_priceNdx = findColumn("item_unit_price");
    pay_amtNdx = findColumn("pay_amt");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public String check_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_branchNdx);
  }
  private int check_branchNdx;
  public String check_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(check_storageNdx);
  }
  private int check_storageNdx;
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
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public Double check_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(check_countNdx);
  }
  private int check_countNdx;
  public Double theory_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(theory_countNdx);
  }
  private int theory_countNdx;
  public Double diffCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(diffCountNdx);
  }
  private int diffCountNdx;
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:44^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SumIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SumIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    check_countNdx = findColumn("check_count");
    pay_amtNdx = findColumn("pay_amt");
  }
  public Double check_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(check_countNdx);
  }
  private int check_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^18*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("form_time", "Date"); 
		map.put("check_branch", "String");
		map.put("check_storage", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("item_category", "String");
		map.put("category_name", "String"); 
		map.put("check_count", "Number"); 
		map.put("theory_count", "Number");
		map.put("diffCount", "Number");
		map.put("item_unit_price", "Number"); 
		map.put("pay_amt", "Number"); 
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "h");
		map.put("form_time", "h"); 
		map.put("check_branch", "h");
		map.put("check_storage", "h");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		map.put("item_category", "d");
		map.put("category_name", "d"); 
		map.put("check_count", "d"); 
		map.put("theory_count", "d");
		map.put("diffCount", "d");
		map.put("item_unit_price", "d"); 
		map.put("pay_amt", "d"); 
		
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:95^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_CHECK_HEADER") h
//  		INNER JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    h.FORM_ID = s.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T1_CHECK_DETAIL") d
//  		ON
//  		    h.FORM_ID = d.FORM_ID
//  		LEFT JOIN
//  		    :Com_("D_T2_ITEM_CATEGORY") c
//  		ON
//  		    d.ITEM_CATEGORY = c.CATEGORY_ID
//  		LEFT JOIN
//  		    :Com_("D_T2_ITEM_PRICE") p
//  		ON
//  		    d.ITEM_ID = p.ITEM_ID
//  		AND p.PRICE_TYPE = 'BENCHMARK'
//  		AND p.IS_CURRENT = 1
//  		WHERE
//  		    1 = 1
//  		AND s.STATUS = '已审核'
//  		AND h.FORM_TYPE = 'form' :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:124^45*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr, String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:135^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                    SELECT
//  			                        h.form_id,
//  			                        h.form_time,
//  			                        h.check_branch,
//  			                        h.check_storage,
//  			                        d.item_id,
//  			                        d.item_name,
//  			                        d.item_dimension,
//  			                        d.item_specification,
//  			                        d.item_category,
//  			                        c.category_name,
//  			                        d.check_count,
//  			                        d.theory_count,
//  			                        d.check_count-d.theory_count                      diffCount,
//  			                        NVL(p.ITEM_PRICE,0)                               item_unit_price,
//  			                        ROUND(NVL(d.CHECK_COUNT,0)*NVL(p.ITEM_PRICE,0),2) pay_amt
//  			                    FROM
//  			                        :Com_("D_T1_CHECK_HEADER") h
//  			                    INNER JOIN
//  			                        :Com_("D_T1_CHECK_DETAIL") d
//  			                    ON
//  			                        h.FORM_ID = d.FORM_ID
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_ITEM_CATEGORY") c
//  			                    ON
//  			                        d.ITEM_CATEGORY = c.CATEGORY_ID
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_ITEM_PRICE") p
//  			                    ON
//  			                        d.ITEM_ID = p.ITEM_ID
//  			                    AND p.PRICE_TYPE = 'BENCHMARK'
//  			                    AND p.IS_CURRENT = 1
//  			                    WHERE
//  			                        1 = 1
//                          		AND h.AUDIT_TIME IS NOT NULL
//  			                    AND h.FORM_TYPE = 'form' :queryStr :sortStr) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
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

/*@lineinfo:user-code*//*@lineinfo:184^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:197^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//                  ROUND(SUM(d.check_count),2) check_count,
//  			    ROUND(SUM(d.check_count* p.ITEM_PRICE),2) PAY_AMT
//              FROM
//                  :Com_("D_T1_CHECK_HEADER") h
//              INNER JOIN
//                  :Com_("D_T0_FORM_STATUS") s
//              ON
//                  h.FORM_ID = s.FORM_ID
//              AND s.IS_CURRENT = 1
//              INNER JOIN
//                  :Com_("D_T1_CHECK_DETAIL") d
//              ON
//                  h.FORM_ID = d.FORM_ID
//              LEFT JOIN
//                  :Com_("D_T2_ITEM_CATEGORY") c
//              ON
//                  d.ITEM_CATEGORY = c.CATEGORY_ID
//              LEFT JOIN
//                  :Com_("D_T2_ITEM_PRICE") p
//              ON
//                  d.ITEM_ID = p.ITEM_ID
//              AND p.PRICE_TYPE = 'BENCHMARK'
//              AND p.IS_CURRENT = 1
//              WHERE
//                  1 = 1
//              AND s.STATUS = '已审核'
//              AND h.FORM_TYPE = 'form' :queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      sumIter = new SumIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:225^55*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("check_count", sumIter.check_count());
			sumMap.put("pay_amt", sumIter.pay_amt());
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("form_id", headerIter.form_id());
			header.put("form_time", headerIter.form_time());
			header.put("check_branch", headerIter.check_branch());
			header.put("check_storage", headerIter.check_storage());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("item_category", headerIter.item_category());
			header.put("category_name", headerIter.category_name());
			header.put("check_count", headerIter.check_count());
			header.put("item_unit_price", headerIter.item_unit_price());
			header.put("pay_amt", headerIter.pay_amt());
			header.put("theory_count", headerIter.theory_count());
			header.put("diffCount", headerIter.diffCount());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class CheckDetailBean_SJProfileKeys 
{
  private static CheckDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CheckDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CheckDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.CheckDetailBean_SJProfile0");
  }
}
