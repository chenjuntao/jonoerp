/*@lineinfo:filename=InputHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class InputHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(InputHeaderBean.class);
	
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
    input_department_idNdx = findColumn("input_department_id");
    input_departmentNdx = findColumn("input_department");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
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
  }
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^24*/
    
    /*@lineinfo:generated-code*//*@lineinfo:49^5*/

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

/*@lineinfo:user-code*//*@lineinfo:55^24*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("input_department_id", "String");
		map.put("input_department", "String");
		map.put("storage_id", "String");
		map.put("storage", "String");
		map.put("provider_id", "String");
		map.put("provider", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
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
		map.put("category_name", "String");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("input_department_id", "h");
		map.put("input_department", "h");
		map.put("storage_id", "h");
		map.put("storage", "h");
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
		map.put("category_name", "c");
		
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:114^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (SELECT
//  		    		COUNT(*)
//              	FROM
//              	    :Com_("D_T1_INPUT_HEADER") h
//              	INNER JOIN
//              	    :Com_("D_T0_FORM_STATUS") s
//              	ON
//              	    h.FORM_ID = s.FORM_ID
//              	AND s.IS_CURRENT = 1
//              	INNER JOIN
//              	    :Com_("D_T1_INPUT_DETAIL") d
//              	ON
//              	    h.FORM_ID = d.FORM_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_CATEGORY") c
//              	ON
//              	    d.ITEM_CATEGORY = c.CATEGORY_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_BRANCH") b
//              	ON
//              	    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//              	WHERE
//              	    1 = 1
//              	AND s.STATUS = '已审核' :queryStr
//              	GROUP BY
//              	    h.INPUT_DEPARTMENT_ID,
//              	    h.INPUT_DEPARTMENT,
//              	    h.STORAGE_ID,
//              	    h.STORAGE,
//              	    h.PROVIDER_ID,
//              	    h.PROVIDER,
//              	    d.ITEM_ID ,
//              	    d.ITEM_NAME,
//              	    d.ITEM_DIMENSION,
//              	    d.ITEM_SPECIFICATION,
//              	    d.ITEM_CATEGORY,
//              	    d.ITEM_UNIT_PRICE,
//              	    d.RECEIVE_PRICE,
//              	    c.CATEGORY_NAME) a };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:158^36*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:169^3*/

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
//  			                	    h.INPUT_DEPARTMENT_ID,
//  			                	    h.INPUT_DEPARTMENT,
//  			                	    h.STORAGE_ID,
//  			                	    h.STORAGE,
//  			                	    h.PROVIDER_ID,
//  			                	    h.PROVIDER,
//  			                	    d.ITEM_ID,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION,
//  			                	    d.ITEM_CATEGORY,
//  			                	    c.CATEGORY_NAME,
//  			                	    SUM( NVL(d.ORDER_COUNT,0))   ORDER_COUNT ,
//  			                	    SUM( NVL(d.RECEIVE_COUNT,0)) RECEIVE_COUNT,
//  			                	    SUM(NVL(d.DIFFERENT_COUNT,0))DIFFERENT_COUNT ,
//  			                	    d.ITEM_UNIT_PRICE,
//  			                	    SUM( NVL(d.PAY_AMT,0)) PAY_AMT,
//  			                	    d.RECEIVE_PRICE,
//  			                	    SUM( ROUND(NVL(d.RECEIVE_AMT,0),2))                                RECEIVE_AMT,
//  			                	    ROUND( SUM( NVL(d.PAY_AMT,0))-SUM( ROUND(NVL(d.RECEIVE_AMT,0),2))) DIFFERENT_AMT
//  			                	FROM
//  			                	    :Com_("D_T1_INPUT_HEADER") h
//  			                	INNER JOIN
//  			                	    :Com_("D_T1_INPUT_DETAIL") d
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    d.ITEM_CATEGORY = c.CATEGORY_ID
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  			                	WHERE
//  			                	    1 = 1
//                  	    		AND h.AUDIT_TIME IS NOT NULL :queryStr
//  			                	GROUP BY
//  			                	    h.INPUT_DEPARTMENT_ID,
//  			                	    h.INPUT_DEPARTMENT,
//  			                	    h.STORAGE_ID,
//  			                	    h.STORAGE,
//  			                	    h.PROVIDER_ID,
//  			                	    h.PROVIDER,
//  			                	    d.ITEM_ID ,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION,
//  			                	    d.ITEM_CATEGORY,
//  			                	    d.ITEM_UNIT_PRICE,
//  			                	    d.RECEIVE_PRICE,
//  			                	    c.CATEGORY_NAME :sortStr) t
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
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:234^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:246^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    SUM( ORDER_COUNT)        ORDER_COUNT ,
//  			    SUM( RECEIVE_COUNT)      RECEIVE_COUNT,
//  			    SUM(DIFFERENT_COUNT)     DIFFERENT_COUNT ,
//  			    SUM( PAY_AMT)            PAY_AMT,
//  			    SUM(RECEIVE_AMT)         RECEIVE_AMT,
//  			    SUM(PAY_AMT-RECEIVE_AMT) DIFFERENT_AMT
//  			FROM
//  			    (
//  			        SELECT
//  			            SUM( ORDER_COUNT)        ORDER_COUNT ,
//  			            SUM( RECEIVE_COUNT)      RECEIVE_COUNT,
//  			            SUM(DIFFERENT_COUNT)     DIFFERENT_COUNT ,
//  			            SUM( PAY_AMT)            PAY_AMT,
//  			            SUM(RECEIVE_AMT)         RECEIVE_AMT,
//  			            SUM(PAY_AMT-RECEIVE_AMT) DIFFERENT_AMT
//  			        FROM
//  			            :Com_("D_T1_INPUT_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        INNER JOIN
//  			            :Com_("D_T1_INPUT_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            d.ITEM_CATEGORY = c.CATEGORY_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_BRANCH") b
//  			        ON
//  			            h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  			        WHERE
//  			            1 = 1
//  			        AND s.STATUS = '已审核' :queryStr
//  			        GROUP BY
//  			            h.INPUT_DEPARTMENT_ID,
//  			            h.INPUT_DEPARTMENT,
//  			            h.STORAGE_ID,
//  			            h.STORAGE,
//  			            h.PROVIDER_ID,
//  			            h.PROVIDER,
//  			            d.ITEM_ID ,
//  			            d.ITEM_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            d.ITEM_CATEGORY,
//  			            d.ITEM_UNIT_PRICE,
//  			            d.RECEIVE_PRICE,
//  			            c.CATEGORY_NAME ) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:299^33*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
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
			header.put("input_department_id", headerIter.input_department_id());
			header.put("input_department","["+headerIter.input_department_id()+"]"+ headerIter.input_department());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage", headerIter.storage());
			header.put("provider_id", headerIter.provider_id());
			header.put("provider", headerIter.provider());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
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
			header.put("category_name", headerIter.category_name());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class InputHeaderBean_SJProfileKeys 
{
  private static InputHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new InputHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private InputHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.InputHeaderBean_SJProfile0");
  }
}
