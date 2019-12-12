/*@lineinfo:filename=InputDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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
import com.tanry.framework.acl.NoPrivilegeException;

public class InputDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(InputDetailBean.class);
	
    /*@lineinfo:generated-code*//*@lineinfo:27^5*/

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
    form_ref_idNdx = findColumn("form_ref_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    order_countNdx = findColumn("order_count");
    receive_countNdx = findColumn("receive_count");
    different_countNdx = findColumn("different_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    pay_amtNdx = findColumn("pay_amt");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    different_amtNdx = findColumn("different_amt");
    category_nameNdx = findColumn("category_name");
    input_timeNdx = findColumn("input_time");
    audit_timeNdx = findColumn("audit_time");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    input_department_idNdx = findColumn("input_department_id");
    input_departmentNdx = findColumn("input_department");
    statusNdx = findColumn("status");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
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
  public String provider_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_idNdx);
  }
  private int provider_idNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
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
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
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
  public Double receive_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_priceNdx);
  }
  private int receive_priceNdx;
  public Double receive_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtNdx);
  }
  private int receive_amtNdx;
  public Double different_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_amtNdx);
  }
  private int different_amtNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public Date input_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timeNdx);
  }
  private int input_timeNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public String input_department_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_department_idNdx);
  }
  private int input_department_idNdx;
  public String input_department() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_departmentNdx);
  }
  private int input_departmentNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:52^17*/
    
    /*@lineinfo:generated-code*//*@lineinfo:54^5*/

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
    order_countNdx = findColumn("order_count");
    receive_countNdx = findColumn("receive_count");
    different_countNdx = findColumn("different_count");
    pay_amtNdx = findColumn("pay_amt");
    receive_amtNdx = findColumn("receive_amt");
    different_amtNdx = findColumn("different_amt");
  }
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double receive_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtNdx);
  }
  private int receive_amtNdx;
  public Double different_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_amtNdx);
  }
  private int different_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:60^24*/
    
    /*@lineinfo:generated-code*//*@lineinfo:62^5*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SumIRIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SumIRIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    order_countsNdx = findColumn("order_counts");
    receive_countsNdx = findColumn("receive_counts");
    different_countsNdx = findColumn("different_counts");
    pay_amtsNdx = findColumn("pay_amts");
    receive_amtsNdx = findColumn("receive_amts");
    different_amtsNdx = findColumn("different_amts");
    return_countsNdx = findColumn("return_counts");
    return_pay_amtsNdx = findColumn("return_pay_amts");
  }
  public Double order_counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countsNdx);
  }
  private int order_countsNdx;
  public Double receive_counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countsNdx);
  }
  private int receive_countsNdx;
  public Double different_counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countsNdx);
  }
  private int different_countsNdx;
  public Double pay_amts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtsNdx);
  }
  private int pay_amtsNdx;
  public Double receive_amts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtsNdx);
  }
  private int receive_amtsNdx;
  public Double different_amts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_amtsNdx);
  }
  private int different_amtsNdx;
  public Double return_counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_countsNdx);
  }
  private int return_countsNdx;
  public Double return_pay_amts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_pay_amtsNdx);
  }
  private int return_pay_amtsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:70^26*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("form_ref_id", "String");
		map.put("form_ref_id2", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("provider_id", "String");
		map.put("provider", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("item_category", "String");
		map.put("order_count", "Number");
		map.put("receive_count", "Number");
		map.put("different_count", "Number");
		map.put("item_unit_price", "Number");
		map.put("pay_amt", "Number");
		map.put("receive_price", "Number");
		map.put("receive_amt", "Number");
		map.put("different_amt", "Number");
		map.put("pay_amt", "Number");
		map.put("category_name", "String");
		map.put("storage_id", "String");
		map.put("storage", "String");
		map.put("input_department_id", "String");
		map.put("input_department", "String");
		map.put("input_time", "Date");
		map.put("audit_time", "Date");
		
		map.put("form_ids", "String");
		map.put("form_ref_ids", "String");
		map.put("item_ids", "String");
		map.put("item_names", "String");
		map.put("providers", "String");
		map.put("provider_ids", "String");
		map.put("item_dimensions", "String");
		map.put("item_specifications", "String");
		map.put("item_categorys", "String");
		map.put("order_counts", "Number");
		map.put("receive_counts", "Number");
		map.put("different_counts", "Number");
		map.put("item_unit_prices", "Number");
		map.put("pay_amts", "Number");
		map.put("receive_prices", "Number");
		map.put("receive_amts", "Number");
		map.put("different_amts", "Number");
		map.put("pay_amts", "Number");
		map.put("category_names", "String");
		map.put("storage_ids", "String");
		map.put("storages", "String");
		map.put("input_department_ids", "String");
		map.put("input_departments", "String");
		map.put("return_counts", "Number");
		map.put("return_pay_amts", "Number");
		map.put("input_times", "Date");
		map.put("audit_times", "Date");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "d");
		map.put("form_ref_id", "h");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("provider_id", "h");
		map.put("provider", "h");
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		map.put("item_category", "d");
		map.put("order_count", "d");
		map.put("receive_count", "d");
		map.put("different_count", "d");
		map.put("item_unit_price", "d");
		map.put("pay_amt", "d");
		map.put("receive_price",  "d");
		map.put("receive_amt",  "d");
		map.put("different_amt",  "d");
		map.put("status", "s");
		map.put("input_time", "h");
		map.put("audit_time", "h");
		map.put("storage_id", "h");
		map.put("storage", "h");
		map.put("input_department_id", "h");
		map.put("input_department", "h");
		map.put("category_name", "c");
		
		map.put("form_ids", "r");
		map.put("form_ref_ids", "r");
		map.put("item_ids", "r");
		map.put("item_names", "r");
		map.put("provider_ids", "r");
		map.put("providers", "r");
		map.put("item_dimensions", "r");
		map.put("item_specifications", "r");
		map.put("item_categorys", "r");
		map.put("order_counts", "r");
		map.put("receive_counts", "r");
		map.put("different_counts", "r");
		map.put("item_unit_prices", "r");
		map.put("pay_amts", "r");
		map.put("receive_prices",  "r");
		map.put("receive_amts",  "r");
		map.put("different_amts",  "r");
		map.put("statuss", "r");
		map.put("input_times", "r");
		map.put("audit_times", "r");
		map.put("storage_ids", "r");
		map.put("storages", "r");
		map.put("input_department_ids", "r");
		map.put("input_departments", "r");
		map.put("category_names", "r");
		
		return map;
	}
	
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:195^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_INPUT_HEADER") h
//  		INNER JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    h.FORM_ID = s.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T2_BRANCH") b
//  		ON
//  		    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  		INNER JOIN
//  		    :Com_("D_T1_INPUT_DETAIL") d
//  		ON
//  		    h.form_id = d.form_id
//  		LEFT JOIN
//  		    :Com_("D_T2_ITEM_CATEGORY") c
//  		ON
//  		    d.ITEM_CATEGORY = c.CATEGORY_ID
//  		WHERE
//  		    1 = 1
//  		AND s.STATUS = '已审核' :queryStr :sortStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  String __sJT_7 = sortStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:221^58*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:232^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM 
//  			                (SELECT
//  			                	    d.FORM_ID,
//  			                	    h.FORM_REF_ID,
//  			                	    d.ITEM_ID,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION,
//  			                	    d.ITEM_CATEGORY,
//  			                	    NVL(d.ORDER_COUNT,0)     ORDER_COUNT,
//  			                	    NVL(d.RECEIVE_COUNT,0)   RECEIVE_COUNT,
//  			                	    NVL(d.DIFFERENT_COUNT,0) DIFFERENT_COUNT,
//  			                	    d.ITEM_UNIT_PRICE,
//  			                	    NVL(d.PAY_AMT,0) PAY_AMT,
//  			                	    d.RECEIVE_PRICE,
//  			                	    ROUND(NVL(d.RECEIVE_AMT,0),2)           RECEIVE_AMT,
//  			                	    ROUND(d.PAY_AMT-NVL(d.RECEIVE_AMT,0),2) DIFFERENT_AMT,
//  			                	    h.input_time,
//  			                	    h.audit_time,
//  			                	    h.storage_id,
//  			                	    h.storage,
//  			                	    h.PROVIDER_ID,
//  			                	    h.PROVIDER,
//  			                	    h.input_department_id,
//  			                	    h.input_department,
//  			                	    c.CATEGORY_NAME,
//  			                	    '已审核' status
//  			                	FROM
//  			                	    :Com_("D_T1_INPUT_HEADER") h
//  			                	INNER JOIN
//  			                	    :Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  			                	INNER JOIN
//  			                	    :Com_("D_T1_INPUT_DETAIL") d
//  			                	ON
//  			                	    h.form_id = d.form_id
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    d.ITEM_CATEGORY = c.CATEGORY_ID
//  			                	WHERE
//  			                	    1 = 1
//  			                	AND h.AUDIT_TIME IS NOT NULL :queryStr :sortStr) t
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
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:287^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr,String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:300^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  				SUM(nvl(d.ORDER_COUNT,0))     ORDER_COUNT,
//  			    SUM(nvl(d.RECEIVE_COUNT,0))   RECEIVE_COUNT,
//  			    SUM(nvl(d.DIFFERENT_COUNT,0)) DIFFERENT_COUNT,
//  			    SUM(nvl(d.PAY_AMT,0))         PAY_AMT,
//  	            SUM(nvl(d.RECEIVE_AMT,0))   RECEIVE_AMT,
//  	            SUM(d.PAY_AMT-d.RECEIVE_AMT)   DIFFERENT_AMT
//          	FROM
//          	    :Com_("D_T1_INPUT_HEADER") h
//          	INNER JOIN
//          	    :Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	INNER JOIN
//          	    :Com_("D_T2_BRANCH") b
//          	ON
//          	    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//          	INNER JOIN
//          	    :Com_("D_T1_INPUT_DETAIL") d
//          	ON
//          	    h.form_id = d.form_id
//          	LEFT JOIN
//          	    :Com_("D_T2_ITEM_CATEGORY") c
//          	ON
//          	    d.ITEM_CATEGORY = c.CATEGORY_ID
//          	WHERE
//          	    1 = 1
//          	AND s.STATUS = '已审核' :queryStr :sortStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  String __sJT_7 = sortStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 2);
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
      sumIter = new SumIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:329^65*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	public Map sumIR( String queryStr,String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		SumIRIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:345^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  				*
//  			FROM (
//  				SELECT
//  				    SUM(nvl(d.ORDER_COUNT,0))     ORDER_COUNTs,
//  				    SUM(nvl(d.RECEIVE_COUNT,0))   RECEIVE_COUNTs,
//  				    SUM(nvl(d.DIFFERENT_COUNT,0)) DIFFERENT_COUNTs,
//  				    SUM(nvl(d.PAY_AMT,0))         PAY_AMTs,
//  		            SUM(nvl(d.RECEIVE_AMT,0))   RECEIVE_AMTs,
//  		            SUM(d.PAY_AMT-d.RECEIVE_AMT)   DIFFERENT_AMTs,
//  		            SUM(NVL(R.return_count,0)) return_counts,
//  		            SUM(NVL(R.return_pay_amt,0)) return_pay_amts,
//                      D.form_id form_ids,
//                      R.FORM_ID form_ref_ids,
//                      RH.RETURN_TIME,
//          		    D.ITEM_ID item_ids,
//          		    D.ITEM_CATEGORY item_categorys,
//          		    H.input_time input_times,
//              	    H.audit_time audit_times,
//              	    H.storage_id storage_ids,
//              	    H.PROVIDER_ID provider_ids,
//              	    H.input_department_id input_department_ids
//  		        FROM 
//  				:Com_("D_T1_INPUT_DETAIL") D
//  				LEFT JOIN 
//  				:Com_("D_T1_INPUT_HEADER") H 
//  				ON 
//  					H.FORM_ID = D.FORM_ID 
//  				inner JOIN
//  				:Com_("D_T0_FORM_STATUS") S 
//  				ON
//  					D.FORM_ID = S.FORM_ID
//  				 LEFT JOIN
//  				 :Com_("D_T1_RETURN_GOODS_HEADER") RH
//  		        ON
//  		            rh.FORM_REF_ID=d.FORM_ID
//  				FULL JOIN 
//  				:Com_("D_T1_RETURN_GOODS_DETAIL") R
//  				ON 
//  					D.FORM_ID = R.FORM_REF_ID AND D.ITEM_ID = R.ITEM_ID 
//  				WHERE
//  					1 =1 AND S.IS_CURRENT = '1' 
//  					AND H.FORM_TYPE in ('MANUAL','PURCHASE') 
//  					AND s.STATUS = '已审核'
//  				GROUP BY
//                  D.form_id ,
//                  R.FORM_ID ,
//                  RH.RETURN_TIME,
//      		    D.ITEM_ID ,
//      		    D.ITEM_CATEGORY ,
//      		    H.input_time ,
//          	    H.audit_time ,
//          	    H.storage_id ,
//          	    H.PROVIDER_ID ,
//          	    H.input_department_id 
//  			) r
//  			WHERE
//  			    1 = 1
//  				:queryStr
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_2 = Com_("D_T1_INPUT_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_5 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      sumIter = new SumIRIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:405^5*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = new HashMap();
		while(sumIter.next()) {
			sumMap.put("order_counts", sumIter.order_counts());
			sumMap.put("receive_counts", sumIter.receive_counts());
			sumMap.put("different_counts", sumIter.different_counts());
			sumMap.put("pay_amts", sumIter.pay_amts());
			sumMap.put("receive_amts", sumIter.receive_amts());
			sumMap.put("different_amts", sumIter.different_amts());
			sumMap.put("return_counts", sumIter.return_counts());
			sumMap.put("return_pay_amts", sumIter.return_pay_amts());
		}
		sumIter.close();
		
		return sumMap;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:425^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class irIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public irIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idsNdx = findColumn("form_ids");
    form_ref_idsNdx = findColumn("form_ref_ids");
    item_idsNdx = findColumn("item_ids");
    item_namesNdx = findColumn("item_names");
    provider_idsNdx = findColumn("provider_ids");
    providersNdx = findColumn("providers");
    item_dimensionsNdx = findColumn("item_dimensions");
    item_specificationsNdx = findColumn("item_specifications");
    item_categorysNdx = findColumn("item_categorys");
    order_countsNdx = findColumn("order_counts");
    receive_countsNdx = findColumn("receive_counts");
    different_countsNdx = findColumn("different_counts");
    item_unit_pricesNdx = findColumn("item_unit_prices");
    pay_amtsNdx = findColumn("pay_amts");
    receive_pricesNdx = findColumn("receive_prices");
    receive_amtsNdx = findColumn("receive_amts");
    different_amtsNdx = findColumn("different_amts");
    input_timesNdx = findColumn("input_times");
    audit_timesNdx = findColumn("audit_times");
    storage_idsNdx = findColumn("storage_ids");
    storagesNdx = findColumn("storages");
    input_department_idsNdx = findColumn("input_department_ids");
    input_departmentsNdx = findColumn("input_departments");
    return_countsNdx = findColumn("return_counts");
    return_pay_amtsNdx = findColumn("return_pay_amts");
    return_reasonsNdx = findColumn("return_reasons");
  }
  public String form_ids() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idsNdx);
  }
  private int form_idsNdx;
  public String form_ref_ids() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idsNdx);
  }
  private int form_ref_idsNdx;
  public String item_ids() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idsNdx);
  }
  private int item_idsNdx;
  public String item_names() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_namesNdx);
  }
  private int item_namesNdx;
  public String provider_ids() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_idsNdx);
  }
  private int provider_idsNdx;
  public String providers() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providersNdx);
  }
  private int providersNdx;
  public String item_dimensions() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionsNdx);
  }
  private int item_dimensionsNdx;
  public String item_specifications() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationsNdx);
  }
  private int item_specificationsNdx;
  public String item_categorys() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categorysNdx);
  }
  private int item_categorysNdx;
  public Double order_counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countsNdx);
  }
  private int order_countsNdx;
  public Double receive_counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countsNdx);
  }
  private int receive_countsNdx;
  public Double different_counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countsNdx);
  }
  private int different_countsNdx;
  public Double item_unit_prices() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_pricesNdx);
  }
  private int item_unit_pricesNdx;
  public Double pay_amts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtsNdx);
  }
  private int pay_amtsNdx;
  public Double receive_prices() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_pricesNdx);
  }
  private int receive_pricesNdx;
  public Double receive_amts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtsNdx);
  }
  private int receive_amtsNdx;
  public Double different_amts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_amtsNdx);
  }
  private int different_amtsNdx;
  public Date input_times() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timesNdx);
  }
  private int input_timesNdx;
  public Date audit_times() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timesNdx);
  }
  private int audit_timesNdx;
  public String storage_ids() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idsNdx);
  }
  private int storage_idsNdx;
  public String storages() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storagesNdx);
  }
  private int storagesNdx;
  public String input_department_ids() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_department_idsNdx);
  }
  private int input_department_idsNdx;
  public String input_departments() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_departmentsNdx);
  }
  private int input_departmentsNdx;
  public Double return_counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_countsNdx);
  }
  private int return_countsNdx;
  public Double return_pay_amts() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_pay_amtsNdx);
  }
  private int return_pay_amtsNdx;
  public String return_reasons() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(return_reasonsNdx);
  }
  private int return_reasonsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:451^25*/
	public List<Map> queryIR(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		irIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:457^3*/

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
//  			                	SELECT * FROM(
//  				                	SELECT
//  				                		D.FORM_ID form_ids,
//  				                		R.FORM_ID form_ref_ids,
//  				                		RH.RETURN_TIME,
//  			                		    D.ITEM_ID item_ids,
//  			                		    D.ITEM_NAME item_names,
//  			                		    D.ITEM_DIMENSION item_dimensions,
//  			                		    D.ITEM_SPECIFICATION item_specifications,
//  			                		    D.ITEM_CATEGORY item_categorys, 
//  			                		    nvl(D.ORDER_COUNT,0) ORDER_COUNTs,
//  			                		    nvl(D.RECEIVE_COUNT,0) RECEIVE_COUNTs,
//  			                		    nvl(D.DIFFERENT_COUNT,0) DIFFERENT_COUNTs,
//  			                		    D.ITEM_UNIT_PRICE item_unit_prices,
//  			                		    nvl(D.PAY_AMT,0) PAY_AMTs,
//  			                		    D.RECEIVE_PRICE receive_prices,
//  				                	    ROUND(NVL(D.RECEIVE_AMT,0),2) RECEIVE_AMTs,
//  				                	    ROUND(D.PAY_AMT-NVL(D.RECEIVE_AMT,0),2) DIFFERENT_AMTs,
//  				                	    H.input_time input_times,
//  				                	    H.audit_time audit_times,
//  				                	    H.storage_id storage_ids,
//  				                	    H.storage storages,
//  				                	    H.PROVIDER_ID provider_ids,
//  				                	    H.PROVIDER providers,
//  				                	    H.input_department_id input_department_ids,
//  				                	    H.input_department input_departments,
//  				                	    NVL(R.RETURN_COUNT,0) return_counts,
//  				                	    NVL(R.RETURN_PAY_AMT,0) return_pay_amts,
//  				                	    R.RETURN_REASON return_reasons
//  				                	FROM 
//  				        			:Com_("D_T1_INPUT_DETAIL") D
//  				        			LEFT JOIN 
//  				        			:Com_("D_T1_INPUT_HEADER") H 
//  				        			ON 
//  				        				H.FORM_ID = D.FORM_ID 
//  				        			inner JOIN
//  				        			:Com_("D_T0_FORM_STATUS") S 
//  				        			ON
//  				        				D.FORM_ID = S.FORM_ID
//  	        						 LEFT JOIN
//  	        						 :Com_("D_T1_RETURN_GOODS_HEADER") RH
//  	        				        ON
//  	        				            rh.FORM_REF_ID=d.FORM_ID
//  				        			FULL JOIN 
//  				        			:Com_("D_T1_RETURN_GOODS_DETAIL") R
//  				        			ON 
//  				        				D.FORM_ID = R.FORM_REF_ID 
//  				        				AND D.ITEM_ID = R.ITEM_ID 
//  				        			WHERE
//  				        				1 =1 AND S.IS_CURRENT = '1' 
//  				        				AND H.FORM_TYPE in ('MANUAL','PURCHASE') 
//  				        				AND s.STATUS = '已审核'
//  			                 ) r
//  			                 where
//  			        				1=1 :queryStr
//  			        				:sortStr) t
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
  String __sJT_1 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_2 = Com_("D_T1_INPUT_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_5 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_6 = queryStr;
  String __sJT_7 = sortStr;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 4);
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
      headerIter = new irIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:526^34*/
		
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("form_ids", headerIter.form_ids());
			header.put("form_ref_ids", headerIter.form_ref_ids());
			header.put("item_ids", headerIter.item_ids());
			header.put("item_names", headerIter.item_names());
			header.put("provider_ids", headerIter.provider_ids());
			header.put("providers", headerIter.providers());
			header.put("item_dimensions", headerIter.item_dimensions());
			header.put("item_specifications", headerIter.item_specifications());
			header.put("item_categorys", headerIter.item_categorys());
			header.put("order_counts", headerIter.order_counts());
			header.put("receive_counts", headerIter.receive_counts());
			header.put("different_counts", headerIter.different_counts());
			header.put("item_unit_prices", headerIter.item_unit_prices());
			header.put("pay_amts", headerIter.pay_amts());
			header.put("receive_prices", headerIter.receive_prices());
			header.put("receive_amts", headerIter.receive_amts());
			header.put("different_amts", headerIter.different_amts());
			header.put("input_times", headerIter.input_times());
			header.put("audit_times", headerIter.audit_times());
			header.put("storage_ids", headerIter.storage_ids());
			header.put("storages", headerIter.storages());
			header.put("input_department_ids", headerIter.input_department_ids());
			header.put("input_departments","["+headerIter.input_department_ids()+"]"+ headerIter.input_departments());
			header.put("return_counts",headerIter.return_counts());
			header.put("return_pay_amts",headerIter.return_pay_amts());
			header.put("return_reasons",headerIter.return_reasons());
			headerLst.add(header);
		}
		
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:566^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class numIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public numIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    numNdx = findColumn("num");
  }
  public int num() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(numNdx);
  }
  private int numNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:566^39*/
	public int countInputReturn(String condition)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int num = 0;
		numIter iter = null;
		/*@lineinfo:generated-code*//*@lineinfo:572^3*/

//  ************************************************************
//  #sql [myCtx] iter = { SELECT count(*) num FROM (
//  				SELECT
//  					D.FORM_ID form_ids,
//  	        		R.FORM_ID form_ref_ids,
//  	        		RH.RETURN_TIME,
//  	    		    D.ITEM_ID item_ids,
//  	    		    D.ITEM_NAME item_names,
//  	    		    D.ITEM_DIMENSION item_dimensions,
//  	    		    D.ITEM_SPECIFICATION item_specifications,
//  	    		    D.ITEM_CATEGORY item_categorys, 
//  	    		    nvl(D.ORDER_COUNT,0) ORDER_COUNTs,
//  	    		    nvl(D.RECEIVE_COUNT,0) RECEIVE_COUNTs,
//  	    		    nvl(D.DIFFERENT_COUNT,0) DIFFERENT_COUNTs,
//  	    		    D.ITEM_UNIT_PRICE item_unit_prices,
//  	    		    nvl(D.PAY_AMT,0) PAY_AMTs,
//  	    		    D.RECEIVE_PRICE receive_prices,
//  	        	    ROUND(NVL(D.RECEIVE_AMT,0),2) RECEIVE_AMTs,
//  	        	    ROUND(D.PAY_AMT-NVL(D.RECEIVE_AMT,0),2) DIFFERENT_AMTs,
//  	        	    H.input_time input_times,
//  	        	    H.audit_time audit_times,
//  	        	    H.storage_id storage_ids,
//  	        	    H.storage storages,
//  	        	    H.PROVIDER_ID provider_ids,
//  	        	    H.PROVIDER providers,
//  	        	    H.input_department_id input_department_ids,
//  	        	    H.input_department input_departments,
//  	        	    NVL(R.RETURN_COUNT,0) return_counts,
//  	        	    NVL(R.RETURN_PAY_AMT,0) return_pay_amts,
//  	        	    R.RETURN_REASON return_reasons
//  				FROM 
//  				:Com_("D_T1_INPUT_DETAIL") d
//  				LEFT JOIN 
//  				:Com_("D_T1_INPUT_HEADER") h 
//  				ON 
//  					D.FORM_ID = H.FORM_ID 
//  				inner JOIN
//  	        	:Com_("D_T0_FORM_STATUS") S 
//  	        	ON
//  	        		D.FORM_ID = S.FORM_ID
//  				 LEFT JOIN
//  				 :Com_("D_T1_RETURN_GOODS_HEADER") RH
//  		        ON
//  		            rh.FORM_REF_ID=d.FORM_ID
//  				FULL JOIN 
//  				:Com_("D_T1_RETURN_GOODS_DETAIL") R 
//  				ON 
//  					D.FORM_ID = R.FORM_REF_ID AND D.ITEM_ID = R.ITEM_ID 
//  				where
//  					1 =1 AND S.IS_CURRENT = '1' 
//  	        		AND H.FORM_TYPE in ('MANUAL','PURCHASE')  
//  					AND s.STATUS = '已审核'
//  	        	) r
//  	        WHERE
//          		1=1 :condition
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_2 = Com_("D_T1_INPUT_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_5 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_6 = condition;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new numIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:627^3*/
		while (iter.next()){
			num = iter.num();
		}
		closeDefaultContext(myCtx);
		iter.close();
		return num;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("order_count", sumIter.order_count());
			sumMap.put("receive_count", sumIter.receive_count());
			sumMap.put("different_count", sumIter.different_count());
			sumMap.put("pay_amt", sumIter.pay_amt());
			sumMap.put("receive_amt", sumIter.receive_amt());
			sumMap.put("different_amt", sumIter.different_amt());
		}
			
		return sumMap;
	}

	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("form_id", headerIter.form_id());
			header.put("form_ref_id", headerIter.form_ref_id());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("provider_id", headerIter.provider_id());
			header.put("provider", headerIter.provider());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("item_category", headerIter.item_category());
			header.put("order_count", headerIter.order_count());
			header.put("receive_count", headerIter.receive_count());
			header.put("different_count", headerIter.different_count());
			header.put("item_unit_price", headerIter.item_unit_price());
			header.put("pay_amt", headerIter.pay_amt());
			header.put("receive_price", headerIter.receive_price());
			header.put("receive_amt", headerIter.receive_amt());
			header.put("different_amt", headerIter.different_amt());
			header.put("input_time", headerIter.input_time());
			header.put("audit_time", headerIter.audit_time());
			header.put("status", headerIter.status());
			header.put("category_name", headerIter.category_name());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage", headerIter.storage());
			header.put("input_department_id", headerIter.input_department_id());
			header.put("input_department","["+headerIter.input_department_id()+"]"+ headerIter.input_department());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class InputDetailBean_SJProfileKeys 
{
  private static InputDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new InputDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private InputDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.InputDetailBean_SJProfile0");
  }
}
