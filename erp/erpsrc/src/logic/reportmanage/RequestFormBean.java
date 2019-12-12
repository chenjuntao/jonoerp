/*@lineinfo:filename=RequestFormBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class RequestFormBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequestFormBean.class);
	
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
    form_idNdx = findColumn("form_id");
    buyer_idNdx = findColumn("buyer_id");
    buyer_nameNdx = findColumn("buyer_name");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    buyer_addressNdx = findColumn("buyer_address");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    receive_timeNdx = findColumn("receive_time");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    arrive_periodNdx = findColumn("arrive_period");
    delivery_typeNdx = findColumn("delivery_type");
    statusNdx = findColumn("status");
    template_idNdx = findColumn("template_id");
    template_nameNdx = findColumn("template_name");
    form_time_actualNdx = findColumn("form_time_actual");
    purchase_statusNdx = findColumn("purchase_status");
    shipping_statusNdx = findColumn("shipping_status");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public String buyer_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(buyer_addressNdx);
  }
  private int buyer_addressNdx;
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
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
  public String form_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_makerNdx);
  }
  private int form_makerNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public String auditor() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditorNdx);
  }
  private int auditorNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public String max_pay_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(max_pay_itemNdx);
  }
  private int max_pay_itemNdx;
  public int arrive_period() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(arrive_periodNdx);
  }
  private int arrive_periodNdx;
  public String delivery_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_typeNdx);
  }
  private int delivery_typeNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
  public String template_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_idNdx);
  }
  private int template_idNdx;
  public String template_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_nameNdx);
  }
  private int template_nameNdx;
  public Date form_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_time_actualNdx);
  }
  private int form_time_actualNdx;
  public String purchase_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(purchase_statusNdx);
  }
  private int purchase_statusNdx;
  public String shipping_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shipping_statusNdx);
  }
  private int shipping_statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:52^12*/
	
	/*@lineinfo:generated-code*//*@lineinfo:54^2*/

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
    all_pay_amtNdx = findColumn("all_pay_amt");
  }
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:55^22*/
	
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("buyer_id", "String");
		map.put("buyer_name", "String");
		map.put("storage_id", "String");
		map.put("storage", "String");
		map.put("buyer_address", "String");
		map.put("provider_id", "String");
		map.put("provider", "String");
		map.put("receive_time", "Date");
		map.put("form_maker", "String");
		map.put("form_time", "Date");
		map.put("auditor", "String");
		map.put("audit_time", "Date");
		map.put("form_note", "Number");
		map.put("all_pay_amt", "Number");
		map.put("arrive_period", "Number");
		map.put("max_pay_item", "String");
		map.put("delivery_type", "String");
		map.put("status", "String");
		map.put("template_id", "String");
		map.put("template_name", "String");
		map.put("form_time_actual", "Date");
		map.put("purchase_status", "String");
		map.put("shipping_status", "String");
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "h");
		map.put("buyer_id", "h");
		map.put("buyer_name", "h");
		map.put("storage_id", "h");
		map.put("storage", "h");
		map.put("buyer_address", "h");
		map.put("provider_id", "h");
		map.put("provider", "h");
		map.put("receive_time", "h");
		map.put("form_maker", "h");
		map.put("form_time", "h");
		map.put("auditor", "h");
		map.put("audit_time", "h");
		map.put("form_note", "h");
		map.put("all_pay_amt", "h");
		map.put("arrive_period", "h");
		map.put("max_pay_item", "h");
		map.put("delivery_type", "h");
		map.put("template_id", "h");
		map.put("template_name", "h");
		map.put("form_time_actual", "h");
		map.put("status", "s");
		map.put("purchase_status", "h");
		map.put("shipping_status", "h");
		return map;
	}
	
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:122^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//      	   count(*) 
//      	FROM
//      	:Com_("D_T1_REQUEST_HEADER") h
//      	LEFT JOIN
//      	:Com_("D_T0_FORM_STATUS") s
//      	ON
//      	    s.FORM_ID = h.FORM_ID
//      	AND s.IS_CURRENT = 1
//      	WHERE
//      	    1 = 1  :queryStr  };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestFormBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:133^35*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:144^3*/

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
//  			                	    h.* ,
//  			                	    s.STATUS
//  			                	FROM
//  			                	:Com_("D_T1_REQUEST_HEADER") h
//  			                	LEFT JOIN
//  			                	:Com_("D_T0_FORM_STATUS") s
//  			                	ON
//  			                	    s.FORM_ID = h.FORM_ID
//  			                	AND s.IS_CURRENT = 1
//  			                	WHERE
//  			                	    1 = 1  :queryStr :sortStr) t
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
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = queryStr;
  String __sJT_4 = sortStr;
  int __sJT_5 = endRow;
  int __sJT_6 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestFormBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:168^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:181^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//          	    sum(h.all_pay_amt) all_pay_amt
//          	FROM
//          	:Com_("D_T1_REQUEST_HEADER") h
//          	LEFT JOIN
//          	:Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    s.FORM_ID = h.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	WHERE
//          	    1 = 1  :queryStr  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestFormBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:192^39*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}

	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("all_pay_amt", sumIter.all_pay_amt());
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("form_id", headerIter.form_id());
			header.put("buyer_id", headerIter.buyer_id());
			header.put("buyer_name", headerIter.buyer_name());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage", headerIter.storage());
			header.put("buyer_address", headerIter.buyer_address());
			header.put("provider_id", headerIter.provider_id());
			header.put("provider", headerIter.provider());
			header.put("receive_time", headerIter.receive_time());
			header.put("form_maker", headerIter.form_maker());
			header.put("form_time", headerIter.form_time());
			header.put("auditor", headerIter.auditor());
			header.put("audit_time", headerIter.audit_time());
			header.put("form_note", headerIter.form_note());
			header.put("all_pay_amt", headerIter.all_pay_amt());
			header.put("max_pay_item", headerIter.max_pay_item());
			header.put("arrive_period", headerIter.arrive_period());
			header.put("delivery_type", headerIter.delivery_type());
			header.put("template_id", headerIter.template_id());
			header.put("template_name", headerIter.template_name());
			header.put("status", headerIter.status());
			header.put("form_time_actual", headerIter.form_time_actual());
			header.put("purchase_status", headerIter.purchase_status());
			header.put("shipping_status", headerIter.shipping_status());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RequestFormBean_SJProfileKeys 
{
  private static RequestFormBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestFormBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestFormBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.RequestFormBean_SJProfile0");
  }
}
