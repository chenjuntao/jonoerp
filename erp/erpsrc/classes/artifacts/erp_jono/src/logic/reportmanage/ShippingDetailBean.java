/*@lineinfo:filename=ShippingDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class ShippingDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShippingDetailBean.class);
	
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
    form_ref_idNdx = findColumn("form_ref_id");
    providerNdx = findColumn("provider");
    out_storageNdx = findColumn("out_storage");
    requesterNdx = findColumn("requester");
    in_storageNdx = findColumn("in_storage");
    form_timeNdx = findColumn("form_time");
    audit_timeNdx = findColumn("audit_time");
    receive_timeNdx = findColumn("receive_time");
    input_timeNdx = findColumn("input_time");
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
    different_reasonNdx = findColumn("different_reason");
    category_nameNdx = findColumn("category_name");
    item_unit_priceNdx = findColumn("item_unit_price");
    pay_amtNdx = findColumn("pay_amt");
    receive_countNdx = findColumn("receive_count");
    different_statusNdx = findColumn("different_status");
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
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
  public String in_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_storageNdx);
  }
  private int in_storageNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
  public Date input_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timeNdx);
  }
  private int input_timeNdx;
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
  public String different_reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(different_reasonNdx);
  }
  private int different_reasonNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
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
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public String different_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(different_statusNdx);
  }
  private int different_statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:55^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:57^2*/

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
    pay_amtNdx = findColumn("pay_amt");
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
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:63^18*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("form_ref_id", "String");
		map.put("provider", "String");
		map.put("out_storage", "String");
		map.put("requester", "String");
		map.put("in_storage", "String");
		
		map.put("form_time", "Date"); 
		map.put("audit_time", "Date"); 
		map.put("receive_time", "Date"); 
		map.put("input_time", "Date"); 
		map.put("category_name", "String");
		map.put("item_id", "String");
		map.put("item_name", "String"); 
		map.put("item_dimension", "String"); 
		map.put("item_specification", "String"); 
		map.put("item_category", "String"); 
		map.put("packaging_unit", "String"); 
		map.put("different_reason", "String"); 
		map.put("packaging_factor", "Number"); 
		map.put("packaging_count", "Number"); 
		map.put("request_count", "Number"); 
		map.put("shipping_count", "Number"); 
		map.put("delivery_count", "Number"); 
		map.put("different_count", "Number"); 
		map.put("item_unit_price", "Number"); 
		map.put("receive_count", "Number"); 
		map.put("pay_amt", "Number"); 
		map.put("different_status", "String");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "h");
		map.put("form_ref_id", "h");
		map.put("provider", "h");
		map.put("out_storage", "h");
		map.put("requester", "h");
		map.put("in_storage", "h");
		map.put("form_time", "h"); 
		map.put("audit_time", "h"); 
		map.put("receive_time", "h"); 
		map.put("input_time", "h"); 
		map.put("different_status", "h");
		
		map.put("item_id", "d");
		map.put("item_name", "d"); 
		map.put("item_dimension", "d"); 
		map.put("item_specification", "d"); 
		map.put("item_category", "d"); 
		map.put("packaging_unit", "d"); 
		map.put("different_reason", "d"); 
		map.put("packaging_factor", "d"); 
		map.put("packaging_count", "d"); 
		map.put("request_count", "d"); 
		map.put("shipping_count", "d"); 
		map.put("delivery_count", "d"); 
		map.put("different_count", "d"); 
		map.put("item_unit_price", "d"); 
		map.put("receive_count", "d"); 
		map.put("pay_amt", "d"); 
		
		map.put("category_name", "c"); 
		
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:140^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_SHIPPING_HEADER") h
//  		INNER JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    h.FORM_ID = s.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T1_SHIPPING_DETAIL") d
//  		ON
//  		    h.FORM_ID = d.FORM_ID
//  		INNER JOIN
//  		    :Com_("D_T2_BRANCH") b
//  		ON
//  		    h.PROVIDER_ID = b.BRANCH_ID
//  		LEFT JOIN
//  		    :Com_("D_T2_ITEM_CATEGORY") c
//  		ON
//  		    d.ITEM_CATEGORY = c.CATEGORY_ID
//  		WHERE
//  		    1 = 1
//  		AND s.STATUS = '已审核' :queryStr };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:166^41*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:177^3*/

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
//  			                	    d.ITEM_ID,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION,
//  			                	    d.ITEM_CATEGORY,
//  			                	    d.PACKAGING_FACTOR,
//  			                	    d.PACKAGING_UNIT,
//  			                	    d.PACKAGING_COUNT,
//  			                	    NVL(d.REQUEST_COUNT,0)   REQUEST_COUNT,
//  			                	    NVL(d.SHIPPING_COUNT,0)  SHIPPING_COUNT,
//  			                	    NVL(d.DELIVERY_COUNT,0)  DELIVERY_COUNT,
//  			                	    NVL(d.RECEIVE_COUNT,0)   RECEIVE_COUNT,
//  			                	    NVL(d.DIFFERENT_COUNT,0) DIFFERENT_COUNT,
//  			                	    d.DIFFERENT_REASON,
//  			                	    d.ITEM_UNIT_PRICE,
//  			                	    NVL(d.PAY_AMT,0) PAY_AMT,
//  			                	    h.form_ref_id,
//  			                	    h.provider,
//  			                	    h.out_storage,
//  			                	    h.requester,
//  			                	    h.in_storage,
//  			                	    h.form_time,
//  			                	    h.audit_time,
//  			                	    h.input_time,
//  			                	    h.receive_time,
//  			                	    h.different_status,
//  			                	    c.CATEGORY_NAME
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
//                  	    		AND h.AUDIT_TIME IS NOT NULL :queryStr :sortStr) t
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:235^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:247^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  				SUM( d.request_count) request_count,
//  			    SUM( d.shipping_count) shipping_count,
//  			    SUM( d.delivery_count) delivery_count,
//  			    SUM( d.receive_count) receive_count,
//  			    SUM(d.pay_amt) pay_amt,
//  			    SUM(d.different_count)     different_count
//          	FROM
//          	    :Com_("D_T1_SHIPPING_HEADER") h
//          	INNER JOIN
//          	    :Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	INNER JOIN
//          	    :Com_("D_T1_SHIPPING_DETAIL") d
//          	ON
//          	    h.FORM_ID = d.FORM_ID
//          	INNER JOIN
//          	    :Com_("D_T2_BRANCH") b
//          	ON
//          	    h.PROVIDER_ID = b.BRANCH_ID
//          	LEFT JOIN
//          	    :Com_("D_T2_ITEM_CATEGORY") c
//          	ON
//          	    d.ITEM_CATEGORY = c.CATEGORY_ID
//          	WHERE
//          	    1 = 1
//          	AND s.STATUS = '已审核' :queryStr };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:276^48*/
		
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
			header.put("form_ref_id", headerIter.form_ref_id());
			header.put("provider", headerIter.provider());
			header.put("out_storage", headerIter.out_storage());
			header.put("requester", headerIter.requester());
			header.put("in_storage", headerIter.in_storage());
			header.put("form_time", headerIter.form_time());
			header.put("audit_time", headerIter.audit_time());
			header.put("input_time", headerIter.input_time());
			header.put("receive_time", headerIter.receive_time());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("different_status", headerIter.different_status());
			
			header.put("item_specification", headerIter.item_specification());
			header.put("item_category", headerIter.item_category());
			header.put("packaging_factor", headerIter.packaging_factor());
			header.put("packaging_unit", headerIter.packaging_unit());
			header.put("packaging_count", headerIter.packaging_count());
			header.put("request_count", headerIter.request_count());
			header.put("shipping_count", headerIter.shipping_count());
			header.put("delivery_count", headerIter.delivery_count());
			
			header.put("different_count", headerIter.different_count());
			header.put("different_reason", headerIter.different_reason());
			header.put("category_name", headerIter.category_name());
			header.put("item_unit_price", headerIter.item_unit_price());
			header.put("pay_amt", headerIter.pay_amt());
			header.put("receive_count", headerIter.receive_count());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ShippingDetailBean_SJProfileKeys 
{
  private static ShippingDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ShippingDetailBean_SJProfile0");
  }
}
