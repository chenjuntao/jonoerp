/*@lineinfo:filename=ShippingHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class ShippingHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShippingHeaderBean.class);
	
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
    providerNdx = findColumn("provider");
    out_storageNdx = findColumn("out_storage");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    packaging_factorNdx = findColumn("packaging_factor");
    packaging_unitNdx = findColumn("packaging_unit");
    packaging_countNdx = findColumn("packaging_count");
    request_countNdx = findColumn("request_count");
    shipping_countNdx = findColumn("shipping_count");
    delivery_countNdx = findColumn("delivery_count");
    different_countNdx = findColumn("different_count");
    category_nameNdx = findColumn("category_name");
    receive_countNdx = findColumn("receive_count");
  }
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String out_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storageNdx);
  }
  private int out_storageNdx;
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
  public Double packaging_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(packaging_factorNdx);
  }
  private int packaging_factorNdx;
  public String packaging_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(packaging_unitNdx);
  }
  private int packaging_unitNdx;
  public Double packaging_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(packaging_countNdx);
  }
  private int packaging_countNdx;
  public Double request_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(request_countNdx);
  }
  private int request_countNdx;
  public Double shipping_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(shipping_countNdx);
  }
  private int shipping_countNdx;
  public Double delivery_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delivery_countNdx);
  }
  private int delivery_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:44^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:46^2*/

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
    request_countNdx = findColumn("request_count");
    shipping_countNdx = findColumn("shipping_count");
    delivery_countNdx = findColumn("delivery_count");
    receive_countNdx = findColumn("receive_count");
    different_countNdx = findColumn("different_count");
  }
  public Double request_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(request_countNdx);
  }
  private int request_countNdx;
  public Double shipping_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(shipping_countNdx);
  }
  private int shipping_countNdx;
  public Double delivery_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delivery_countNdx);
  }
  private int delivery_countNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:51^26*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("provider", "String");
		map.put("out_storage", "String");
		map.put("category_name", "String");
		map.put("item_id", "String");
		map.put("item_name", "String"); 
		map.put("item_dimension", "String"); 
		map.put("item_specification", "String"); 
		map.put("item_category", "String"); 
		map.put("packaging_unit", "String"); 
		map.put("packaging_factor", "Number"); 
		map.put("packaging_count", "Number"); 
		map.put("request_count", "Number"); 
		map.put("shipping_count", "Number"); 
		map.put("delivery_count", "Number"); 
		map.put("different_count", "Number"); 
		map.put("receive_count", "Number"); 
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("provider", "h");
		map.put("out_storage", "h");
		map.put("item_id", "d");
		map.put("item_name", "d"); 
		map.put("item_dimension", "d"); 
		map.put("item_specification", "d"); 
		map.put("item_category", "d"); 
		map.put("packaging_unit", "d"); 
		map.put("packaging_factor", "d"); 
		map.put("packaging_count", "d"); 
		map.put("request_count", "d"); 
		map.put("shipping_count", "d"); 
		map.put("delivery_count", "d"); 
		map.put("different_count", "d"); 
		map.put("receive_count", "d"); 
		
		map.put("category_name", "c"); 
		
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:103^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (SELECT
//              	   count(*)
//              	FROM
//              	    :Com_("D_T1_SHIPPING_HEADER") h
//              	INNER JOIN
//              	    :Com_("D_T0_FORM_STATUS") s
//              	ON
//              	    h.FORM_ID = s.FORM_ID
//              	AND s.IS_CURRENT = 1
//              	INNER JOIN
//              	    :Com_("D_T1_SHIPPING_DETAIL") d
//              	ON
//              	    h.FORM_ID = d.FORM_ID
//              	INNER JOIN
//              	    :Com_("D_T2_BRANCH") b
//              	ON
//              	    h.PROVIDER_ID = b.BRANCH_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_CATEGORY") c
//              	ON
//              	    d.ITEM_CATEGORY = c.CATEGORY_ID
//              	WHERE
//              	    1 = 1
//              	AND s.STATUS = '已审核' :queryStr
//              	GROUP BY
//              	    h.provider,
//              	    h.PROVIDER_ID,
//              	    h.out_storage,
//              	    h.OUT_STORAGE_ID,
//              	    d.ITEM_ID,
//              	    d.ITEM_NAME,
//              	    d.ITEM_DIMENSION,
//              	    d.ITEM_SPECIFICATION,
//              	    d.ITEM_CATEGORY,
//              	    c.CATEGORY_NAME,
//              	    d.PACKAGING_FACTOR,
//              	    d.PACKAGING_UNIT ) a };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:145^38*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:156^3*/

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
//  			                	    h.provider,
//  			                	    h.PROVIDER_ID,
//  			                	    h.out_storage,
//  			                	    h.OUT_STORAGE_ID,
//  			                	    d.ITEM_ID,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION,
//  			                	    d.ITEM_CATEGORY,
//  			                	    c.CATEGORY_NAME,
//  			                	    d.PACKAGING_FACTOR,
//  			                	    d.PACKAGING_UNIT,
//  			                	    SUM( NVL(d.PACKAGING_COUNT,0)) PACKAGING_COUNT,
//  			                	    SUM( NVL(d.REQUEST_COUNT,0))   REQUEST_COUNT,
//  			                	    SUM( NVL(d.SHIPPING_COUNT,0))  SHIPPING_COUNT,
//  			                	    SUM(NVL(d.DELIVERY_COUNT,0))   DELIVERY_COUNT,
//  			                	    SUM(NVL(d.RECEIVE_COUNT,0))    RECEIVE_COUNT,
//  			                	    SUM(NVL(d.DIFFERENT_COUNT,0))  DIFFERENT_COUNT
//  			                	FROM
//  			                	    :Com_("D_T1_SHIPPING_HEADER") h
//  			                	INNER JOIN
//  			                	    :Com_("D_T1_SHIPPING_DETAIL") d
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	INNER JOIN
//  			                	    :Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.PROVIDER_ID = b.BRANCH_ID
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    d.ITEM_CATEGORY = c.CATEGORY_ID
//  			                	WHERE
//  			                	    1 = 1
//                  	    		AND h.AUDIT_TIME IS NOT NULL :queryStr
//  			                	GROUP BY
//  			                	    h.provider,
//  			                	    h.PROVIDER_ID,
//  			                	    h.out_storage,
//  			                	    h.OUT_STORAGE_ID,
//  			                	    d.ITEM_ID,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION,
//  			                	    d.ITEM_CATEGORY,
//  			                	    c.CATEGORY_NAME,
//  			                	    d.PACKAGING_FACTOR,
//  			                	    d.PACKAGING_UNIT :sortStr) t
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
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:217^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:229^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    ROUND(SUM (REQUEST_COUNT),2)  REQUEST_COUNT,
//  			    ROUND(SUM(SHIPPING_COUNT),2)  SHIPPING_COUNT,
//  			    ROUND(SUM(DELIVERY_COUNT),2)  DELIVERY_COUNT,
//  			    ROUND(SUM(RECEIVE_COUNT),2)   RECEIVE_COUNT,
//  			    ROUND(SUM(DIFFERENT_COUNT),2) DIFFERENT_COUNT
//  			FROM
//  			    (
//  			        SELECT
//  			            ROUND(SUM (REQUEST_COUNT),2)  REQUEST_COUNT,
//  			            ROUND(SUM(SHIPPING_COUNT),2)  SHIPPING_COUNT,
//  			            ROUND(SUM(DELIVERY_COUNT),2)  DELIVERY_COUNT,
//  			            ROUND(SUM(RECEIVE_COUNT),2)   RECEIVE_COUNT,
//  			            ROUND(SUM(DIFFERENT_COUNT),2) DIFFERENT_COUNT
//  			        FROM
//  			            :Com_("D_T1_SHIPPING_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        INNER JOIN
//  			            :Com_("D_T1_SHIPPING_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        INNER JOIN
//  			            :Com_("D_T2_BRANCH") b
//  			        ON
//  			            h.PROVIDER_ID = b.BRANCH_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            d.ITEM_CATEGORY = c.CATEGORY_ID
//  			        WHERE
//  			            1 = 1
//  			        AND s.STATUS = '已审核' :queryStr
//  			        GROUP BY
//  			            h.provider,
//  			            h.PROVIDER_ID,
//  			            h.out_storage,
//  			            h.OUT_STORAGE_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            d.ITEM_CATEGORY,
//  			            c.CATEGORY_NAME,
//  			            d.PACKAGING_FACTOR,
//  			            d.PACKAGING_UNIT ) a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:278^36*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("request_count", sumIter.request_count());
			sumMap.put("shipping_count", sumIter.shipping_count());
			sumMap.put("delivery_count", sumIter.delivery_count());
			sumMap.put("receive_count", sumIter.receive_count());
			sumMap.put("different_count", sumIter.different_count());
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("provider", headerIter.provider());
			header.put("out_storage", headerIter.out_storage());
//			header.put("in_storage", headerIter.in_storage());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			
			header.put("item_specification", headerIter.item_specification());
			header.put("item_category", headerIter.item_category());
			header.put("packaging_factor", headerIter.packaging_factor());
			header.put("packaging_unit", headerIter.packaging_unit());
			header.put("packaging_count", headerIter.packaging_count());
			header.put("request_count", headerIter.request_count());
			header.put("shipping_count", headerIter.shipping_count());
			header.put("delivery_count", headerIter.delivery_count());
			
			header.put("different_count", headerIter.different_count());
			header.put("category_name", headerIter.category_name());
			header.put("receive_count", headerIter.receive_count());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ShippingHeaderBean_SJProfileKeys 
{
  private static ShippingHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ShippingHeaderBean_SJProfile0");
  }
}
