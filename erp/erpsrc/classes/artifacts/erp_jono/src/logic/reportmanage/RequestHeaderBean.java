/*@lineinfo:filename=RequestHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class RequestHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequestHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:27^2*/

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
    buyer_idNdx = findColumn("buyer_id");
    buyer_nameNdx = findColumn("buyer_name");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    category_nameNdx = findColumn("category_name");
    inventoryNdx = findColumn("inventory");
    order_countNdx = findColumn("order_count");
    pay_amtNdx = findColumn("pay_amt");
  }
  public String buyer_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(buyer_idNdx);
  }
  private int buyer_idNdx;
  public String buyer_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(buyer_nameNdx);
  }
  private int buyer_nameNdx;
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
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public Double inventory() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventoryNdx);
  }
  private int inventoryNdx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:45^2*/

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
    pay_amtNdx = findColumn("pay_amt");
  }
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^18*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("buyer_name", "String");
		map.put("buyer_id", "String");
		map.put("storage_id", "String");
		map.put("storage", "String");
		map.put("provider_id", "String");
		map.put("provider", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("item_category", "String");
		map.put("category_name", "String");
		map.put("inventory", "Number");
		map.put("order_count", "Number");
		map.put("pay_amt", "Number");
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("buyer_name", "h");
		map.put("buyer_id", "h");
		map.put("storage_id", "h");
		map.put("storage", "h");
		map.put("provider_id", "h");
		map.put("provider", "h");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		map.put("item_category", "d");
		map.put("category_name", "c");
		map.put("inventory", "d");
		map.put("order_count", "d");
		map.put("pay_amt", "d");
		return map;
	}
	
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:95^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (
//  		        SELECT
//  		            COUNT(*)
//  		        FROM
//  		            :Com_("D_T1_REQUEST_HEADER") h
//  		        INNER JOIN
//  		            :Com_("D_T0_FORM_STATUS") s
//  		        ON
//  		            h.FORM_ID = s.FORM_ID
//  		        AND s.IS_CURRENT = 1
//  		        INNER JOIN
//  		            :Com_("D_T1_REQUEST_DETAIL") d
//  		        ON
//  		            h.FORM_ID = d.FORM_ID
//  		        LEFT JOIN
//  		            :Com_("D_T2_ITEM_CATEGORY") c
//  		        ON
//  		            c.CATEGORY_ID = d.ITEM_CATEGORY
//  		        WHERE
//  		            1 = 1
//  		        AND s.STATUS = '已审核' :queryStr
//  		        GROUP BY
//  		            h.BUYER_ID,
//  		            h.BUYER_NAME,
//  		            h.STORAGE_ID,
//  		            h.STORAGE,
//  		            h.PROVIDER_ID,
//  		            h.PROVIDER,
//  		            d.ITEM_ID,
//  		            d.ITEM_NAME,
//  		            d.ITEM_DIMENSION,
//  		            d.ITEM_SPECIFICATION,
//  		            d.ITEM_CATEGORY,
//  		            c.CATEGORY_NAME ) a };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:134^34*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:145^3*/

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
//  			                        h.BUYER_ID,
//  			                        h.BUYER_NAME,
//  			                        h.STORAGE_ID,
//  			                        h.STORAGE,
//  			                        h.PROVIDER_ID,
//  			                        h.PROVIDER,
//  			                        d.ITEM_ID,
//  			                        d.ITEM_NAME,
//  			                        d.ITEM_DIMENSION,
//  			                        d.ITEM_SPECIFICATION,
//  			                        d.ITEM_CATEGORY,
//  			                        c.CATEGORY_NAME,
//  			                        SUM(NVL(INVENTORY,0))  INVENTORY,
//  			                        SUM(NVL(ORDER_COUNT,0))ORDER_COUNT,
//  			                        SUM(NVL(PAY_AMT,0))    PAY_AMT
//  			                    FROM
//  			                        :Com_("D_T1_REQUEST_HEADER") h
//  			                    INNER JOIN
//  			                        :Com_("D_T1_REQUEST_DETAIL") d
//  			                    ON
//  			                        h.FORM_ID = d.FORM_ID
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_ITEM_CATEGORY") c
//  			                    ON
//  			                        c.CATEGORY_ID = d.ITEM_CATEGORY
//  			                    WHERE
//  			                        1 = 1
//                          		AND h.AUDIT_TIME IS NOT NULL :queryStr
//  			                    GROUP BY
//  			                        h.BUYER_ID,
//  			                        h.BUYER_NAME,
//  			                        h.STORAGE_ID,
//  			                        h.STORAGE,
//  			                        h.PROVIDER_ID,
//  			                        h.PROVIDER,
//  			                        d.ITEM_ID,
//  			                        d.ITEM_NAME,
//  			                        d.ITEM_DIMENSION,
//  			                        d.ITEM_SPECIFICATION,
//  			                        d.ITEM_CATEGORY,
//  			                        c.CATEGORY_NAME :sortStr) t
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
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_4 = queryStr;
  String __sJT_5 = sortStr;
  int __sJT_6 = endRow;
  int __sJT_7 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:200^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:212^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    SUM (ORDER_COUNT) ORDER_COUNT,
//  			    SUM(PAY_AMT)      PAY_AMT
//  			FROM
//  			    (
//  			        SELECT
//  			            SUM(NVL(ORDER_COUNT,0))ORDER_COUNT,
//  			            SUM(NVL(PAY_AMT,0))    PAY_AMT
//  			        FROM
//  			            :Com_("D_T1_REQUEST_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        INNER JOIN
//  			            :Com_("D_T1_REQUEST_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            c.CATEGORY_ID = d.ITEM_CATEGORY
//  			        WHERE
//  			            1 = 1
//  			        AND s.STATUS = '已审核' :queryStr
//  			        GROUP BY
//  			            h.BUYER_ID,
//  			            h.BUYER_NAME,
//  			            h.STORAGE_ID,
//  			            h.STORAGE,
//  			            h.PROVIDER_ID,
//  			            h.PROVIDER,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            d.ITEM_CATEGORY,
//  			            c.CATEGORY_NAME ) a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:251^35*/
		
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
			sumMap.put("pay_amt", sumIter.pay_amt());
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("buyer_name", headerIter.buyer_name());
			header.put("buyer_id", headerIter.buyer_id());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage", headerIter.storage());
			header.put("provider_id", headerIter.provider_id());
			header.put("provider", headerIter.provider());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("item_category", headerIter.item_category());
			header.put("category_name", headerIter.category_name());
			header.put("inventory", headerIter.inventory());
			header.put("order_count", headerIter.order_count());
			header.put("pay_amt", headerIter.pay_amt());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RequestHeaderBean_SJProfileKeys 
{
  private static RequestHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.RequestHeaderBean_SJProfile0");
  }
}
