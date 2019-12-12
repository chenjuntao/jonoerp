/*@lineinfo:filename=ProductAnalysisBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class ProductAnalysisBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ProductAnalysisBean.class);
	
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
    complete_timeNdx = findColumn("complete_time");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_countNdx = findColumn("item_count");
    actualNdx = findColumn("actual");
    item_id2Ndx = findColumn("item_id2");
    item_name2Ndx = findColumn("item_name2");
    item_dimension2Ndx = findColumn("item_dimension2");
    item_count2Ndx = findColumn("item_count2");
    inputed_countNdx = findColumn("inputed_count");
    diffNdx = findColumn("diff");
    form_idNdx = findColumn("form_id");
  }
  public Date complete_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(complete_timeNdx);
  }
  private int complete_timeNdx;
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actualNdx);
  }
  private int actualNdx;
  public String item_id2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_id2Ndx);
  }
  private int item_id2Ndx;
  public String item_name2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_name2Ndx);
  }
  private int item_name2Ndx;
  public String item_dimension2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimension2Ndx);
  }
  private int item_dimension2Ndx;
  public Double item_count2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count2Ndx);
  }
  private int item_count2Ndx;
  public Double inputed_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inputed_countNdx);
  }
  private int inputed_countNdx;
  public Double diff() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(diffNdx);
  }
  private int diffNdx;
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^18*/
	
	/*@lineinfo:generated-code*//*@lineinfo:41^2*/

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
    item_countNdx = findColumn("item_count");
    actualNdx = findColumn("actual");
    item_count2Ndx = findColumn("item_count2");
    inputed_countNdx = findColumn("inputed_count");
    diffNdx = findColumn("diff");
  }
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actualNdx);
  }
  private int actualNdx;
  public Double item_count2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count2Ndx);
  }
  private int item_count2Ndx;
  public Double inputed_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inputed_countNdx);
  }
  private int inputed_countNdx;
  public Double diff() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(diffNdx);
  }
  private int diffNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:49^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class TotalIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TotalIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    totalsNdx = findColumn("totals");
  }
  public Integer totals() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(totalsNdx);
  }
  private int totalsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:51^3*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("complete_time", "Date");
		map.put("item_id", "String");
		map.put("item_name", "String"); 
		map.put("item_dimension", "String"); 
		map.put("item_count", "Number");
		map.put("actual", "Number");
		map.put("item_id2", "String");
		map.put("item_name2", "String");
		map.put("item_dimension2", "String");
		map.put("item_count2", "Number");
		map.put("inputed_count", "Number"); 
		map.put("diff", "Number"); 
		map.put("form_id", "String"); 
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		
		map.put("complete_time", "t");
		map.put("item_id", "t");
		map.put("item_name", "t"); 
		map.put("item_dimension", "t"); 
		map.put("item_count", "t");
		map.put("actual", "t");
		map.put("item_id2", "t");
		map.put("item_name2", "t");
		map.put("item_dimension2", "t");
		map.put("item_count2", "t");
		map.put("inputed_count", "t"); 
		map.put("diff", "t"); 
		map.put("form_id", "t"); 
		
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		TotalIter totalIter = null;
		int totals = 0; 
		
		/*@lineinfo:generated-code*//*@lineinfo:98^3*/

//  ************************************************************
//  #sql [myCtx] totalIter = { WITH
//  			    t AS
//  			    (
//  			        SELECT
//  			            d.COMPLETE_TIME,
//  			            i.ITEM_ID,
//  			            i.ITEM_NAME,
//  			            i.ITEM_DIMENSION,
//  			            ROUND(t.ITEM_GROSS_COUNT*h.INPUTED_COUNT,4) ITEM_COUNT,
//  			            i.RECEIVED_COUNT -i.RETURNED_COUNT          actual,
//  			            h.ITEM_ID                                   ITEM_ID2,
//  			            h.ITEM_NAME                                 ITEM_NAME2,
//  			            h.ITEM_DIMENSION                            ITEM_DIMENSION2,
//  			            h.ITEM_COUNT                                ITEM_COUNT2,
//  			            h.INPUTED_COUNT,
//  			            ROUND(t.ITEM_GROSS_COUNT*h.INPUTED_COUNT,4) - i.RECEIVED_COUNT+i.RETURNED_COUNT diff,
//  			            h.FORM_ID
//  			        FROM
//  			            :Com_("D_T1_WORK_ORDER_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T1_WORKORDER_ITEM") i
//  			        ON
//  			            h.FORM_ID = i.FORM_ID
//  			        INNER JOIN
//  			            :Com_("D_T2_THERAPY") t
//  			        ON
//  			            h.ITEM_ID = t.THERAPY_ID
//  			        AND i.ITEM_ID = t.ITEM_ID
//  			        INNER JOIN
//  			            :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.WORK_ORDER_ID
//  			    )
//  			SELECT
//  			    COUNT(*) totals
//  			FROM
//  			    t
//  			WHERE
//  			    1 = 1 :queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_3 = Com_("D_T2_THERAPY");
  String __sJT_4 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductAnalysisBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      totalIter = new TotalIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:137^31*/

		if(totalIter.next()) {
			totals = totalIter.totals();
		}
		
		totalIter.close();
		closeDefaultContext(myCtx);
		return totals;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:154^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (WITH
//  			                	    t AS
//  			                	    (SELECT
//  			                	    	    d.COMPLETE_TIME,
//  			                	    	    i.ITEM_ID,
//  			                	    	    i.ITEM_NAME,
//  			                	    	    i.ITEM_DIMENSION,
//  			                	    	    ROUND(t.ITEM_GROSS_COUNT*h.INPUTED_COUNT,4) ITEM_COUNT,
//  			                	    	    i.RECEIVED_COUNT -i.RETURNED_COUNT          actual,
//  			                	    	    h.ITEM_ID                                   ITEM_ID2,
//  			                	    	    h.ITEM_NAME                                 ITEM_NAME2,
//  			                	    	    h.ITEM_DIMENSION                            ITEM_DIMENSION2,
//  			                	    	    h.ITEM_COUNT                                ITEM_COUNT2,
//  			                	    	    h.INPUTED_COUNT,
//  			                	    	    ROUND(t.ITEM_GROSS_COUNT*h.INPUTED_COUNT,4) - i.RECEIVED_COUNT+i.RETURNED_COUNT diff,
//  			                	    	    h.FORM_ID
//  			                	    	FROM
//  			                	    	    :Com_("D_T1_WORK_ORDER_HEADER") h
//  			                	    	INNER JOIN
//  			                	    	    :Com_("D_T1_WORKORDER_ITEM") i
//  			                	    	ON
//  			                	    	    h.FORM_ID = i.FORM_ID
//  			                	    	INNER JOIN
//  			                	    	    :Com_("D_T2_THERAPY") t
//  			                	    	ON
//  			                	    	    h.ITEM_ID = t.THERAPY_ID
//  			                	    	AND i.ITEM_ID = t.ITEM_ID
//  			                	    	INNER JOIN
//  			                	    	    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			                	    	ON
//  			                	    	    h.FORM_ID = d.WORK_ORDER_ID)
//  			                	SELECT
//  			                	    *
//  			                	FROM
//  			                	    t WHERE
//  			                	    1 = 1 :queryStr :sortStr ) t
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
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_3 = Com_("D_T2_THERAPY");
  String __sJT_4 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductAnalysisBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:202^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:214^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { WITH
//          	    t AS
//          	    (SELECT
//          	    	    d.COMPLETE_TIME,
//          	    	    i.ITEM_ID,
//          	    	    i.ITEM_NAME,
//          	    	    i.ITEM_DIMENSION,
//          	    	    ROUND(t.ITEM_GROSS_COUNT*h.INPUTED_COUNT,4) ITEM_COUNT,
//          	    	    i.RECEIVED_COUNT -i.RETURNED_COUNT          actual,
//          	    	    h.ITEM_ID                                   ITEM_ID2,
//          	    	    h.ITEM_NAME                                 ITEM_NAME2,
//          	    	    h.ITEM_DIMENSION                            ITEM_DIMENSION2,
//          	    	    h.ITEM_COUNT                                ITEM_COUNT2,
//          	    	    h.INPUTED_COUNT,
//          	    	    ROUND(t.ITEM_GROSS_COUNT*h.INPUTED_COUNT,4) - i.RECEIVED_COUNT+i.RETURNED_COUNT diff,
//          	    	    h.FORM_ID
//          	    	FROM
//          	    	    :Com_("D_T1_WORK_ORDER_HEADER") h
//          	    	INNER JOIN
//          	    	    :Com_("D_T1_WORKORDER_ITEM") i
//          	    	ON
//          	    	    h.FORM_ID = i.FORM_ID
//          	    	INNER JOIN
//          	    	    :Com_("D_T2_THERAPY") t
//          	    	ON
//          	    	    h.ITEM_ID = t.THERAPY_ID
//          	    	AND i.ITEM_ID = t.ITEM_ID
//          	    	INNER JOIN
//          	    	    :Com_("D_T1_ARRENGMENT_DETAIL") d
//          	    	ON
//          	    	    h.FORM_ID = d.WORK_ORDER_ID)
//          	SELECT
//          		sum(t.ITEM_COUNT)ITEM_COUNT,
//  	    	    sum(t.actual)actual,
//  	    	    sum(t.ITEM_COUNT2)ITEM_COUNT2,
//  	    	    sum(t.INPUTED_COUNT)INPUTED_COUNT,
//  	    	    sum(t.diff) diff
//          	FROM
//          	    t WHERE
//          	    1 = 1 :queryStr  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_3 = Com_("D_T2_THERAPY");
  String __sJT_4 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductAnalysisBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:254^38*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("item_count", sumIter.item_count());
			sumMap.put("actual", sumIter.actual());
			sumMap.put("item_count2", sumIter.item_count2());
			sumMap.put("inputed_count", sumIter.inputed_count());
			sumMap.put("diff", sumIter.diff());
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("complete_time", headerIter.complete_time());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_count", headerIter.item_count());
			header.put("actual", headerIter.actual());
			header.put("item_id2", headerIter.item_id2());
			header.put("item_name2", headerIter.item_name2());
			header.put("item_dimension2", headerIter.item_dimension2());
			header.put("item_count2", headerIter.item_count2());
			header.put("inputed_count", headerIter.inputed_count());
			header.put("diff", headerIter.diff());
			header.put("form_id", headerIter.form_id());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ProductAnalysisBean_SJProfileKeys 
{
  private static ProductAnalysisBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ProductAnalysisBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ProductAnalysisBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ProductAnalysisBean_SJProfile0");
  }
}
