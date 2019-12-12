/*@lineinfo:filename=ShippingAntiauditDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class ShippingAntiauditDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShippingAntiauditDetailBean.class);
	
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
    antiaudit_timeNdx = findColumn("antiaudit_time");
    form_ref_idNdx = findColumn("form_ref_id");
    antiauditorNdx = findColumn("antiauditor");
    antiaudit_branchNdx = findColumn("antiaudit_branch");
    providerNdx = findColumn("provider");
    out_storageNdx = findColumn("out_storage");
    requesterNdx = findColumn("requester");
    in_storageNdx = findColumn("in_storage");
    receive_timeNdx = findColumn("receive_time");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    antiaudit_receive_countNdx = findColumn("antiaudit_receive_count");
    antiaudit_pay_amtNdx = findColumn("antiaudit_pay_amt");
  }
  public Date antiaudit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(antiaudit_timeNdx);
  }
  private int antiaudit_timeNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String antiauditor() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(antiauditorNdx);
  }
  private int antiauditorNdx;
  public String antiaudit_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(antiaudit_branchNdx);
  }
  private int antiaudit_branchNdx;
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
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
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
  public Double antiaudit_receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(antiaudit_receive_countNdx);
  }
  private int antiaudit_receive_countNdx;
  public Double antiaudit_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(antiaudit_pay_amtNdx);
  }
  private int antiaudit_pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:42^2*/

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
    antiaudit_receive_countNdx = findColumn("antiaudit_receive_count");
    antiaudit_pay_amtNdx = findColumn("antiaudit_pay_amt");
  }
  public Double antiaudit_receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(antiaudit_receive_countNdx);
  }
  private int antiaudit_receive_countNdx;
  public Double antiaudit_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(antiaudit_pay_amtNdx);
  }
  private int antiaudit_pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:44^28*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("antiaudit_time", "Date"); 
		map.put("form_ref_id", "String");
		map.put("antiauditor", "String");
		map.put("antiaudit_branch", "String");
		map.put("provider", "String");
		map.put("out_storage", "String");
		map.put("requester", "String");
		map.put("in_storage", "String");
		map.put("receive_time", "Date"); 
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("antiaudit_receive_count", "Number");
		map.put("antiaudit_pay_amt", "Number");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("antiaudit_time", "h"); 
		map.put("form_ref_id", "h");
		map.put("antiauditor", "h");
		map.put("antiaudit_branch", "h");
		map.put("provider", "ph");
		map.put("out_storage", "ph");
		map.put("requester", "ph");
		map.put("in_storage", "ph");
		map.put("receive_time", "ph"); 
		map.put("item_id", "d");
		map.put("item_name", "im");
		map.put("antiaudit_receive_count", "d");
		map.put("antiaudit_receive_count", "d");
		return map;
	}
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:88^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//      	   count(*) 
//      	FROM
//      	:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") h
//      	LEFT JOIN
//      	:Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL") d
//      	ON
//      	    h.FORM_REF_ID = d.FORM_REF_ID
//      	LEFT JOIN
//      	:Com_("D_T2_ITEM_META") im
//      	ON
//      	    d.ITEM_ID = im.ITEM_ID
//      	JOIN
//      	:Com_("D_T1_SHIPPING_HEADER") ph
//      	ON
//      	    h.FORM_REF_ID = ph.FORM_ID
//      	JOIN
//      	:Com_("D_T0_FORM_STATUS") s
//      	ON
//      	    h.FORM_REF_ID = s.FORM_ID
//      	AND s.IS_CURRENT = 1
//      	WHERE
//      	    1 = 1  :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:111^34*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:122^3*/

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
//  			                	    h.ANTIAUDIT_TIME,
//  			                	    h.FORM_REF_ID,
//  			                	    h.ANTIAUDITOR,
//  			                	    h.ANTIAUDIT_BRANCH,
//  			                	    ph.PROVIDER,
//  			                	    ph.OUT_STORAGE,
//  			                	    ph.RECEIVE_TIME,
//  			                	    ph.REQUESTER,
//  			                	    ph.IN_STORAGE,
//  			                	    d.ITEM_ID,
//  			                	    im.ITEM_NAME,
//  			                	    nvl(d.ANTIAUDIT_RECEIVE_COUNT,0) ANTIAUDIT_RECEIVE_COUNT,
//  			                	    nvl(d.ANTIAUDIT_PAY_AMT,0)ANTIAUDIT_PAY_AMT
//  			                	FROM
//  			                	:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") h
//  			                	LEFT JOIN
//  			                	:Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL") d
//  			                	ON
//  			                	    h.FORM_REF_ID = d.FORM_REF_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_ITEM_META") im
//  			                	ON
//  			                	    d.ITEM_ID = im.ITEM_ID
//  			                	JOIN
//  			                	:Com_("D_T1_SHIPPING_HEADER") ph
//  			                	ON
//  			                	    h.FORM_REF_ID = ph.FORM_ID
//  			                	JOIN
//  			                	:Com_("D_T0_FORM_STATUS") s
//  			                	ON
//  			                	    h.FORM_REF_ID = s.FORM_ID
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
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = queryStr;
  String __sJT_7 = sortStr;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditDetailBean_SJProfileKeys.getKey(0), 1);
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
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:169^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:183^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//          	    sum(nvl(d.ANTIAUDIT_RECEIVE_COUNT,0)) ANTIAUDIT_RECEIVE_COUNT,
//          	    sum(nvl(d.ANTIAUDIT_PAY_AMT,0)) ANTIAUDIT_PAY_AMT
//          	FROM
//          	:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") h
//          	LEFT JOIN
//          	:Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL") d
//          	ON
//          	    h.FORM_REF_ID = d.FORM_REF_ID
//          	LEFT JOIN
//          	:Com_("D_T2_ITEM_META") im
//          	ON
//          	    d.ITEM_ID = im.ITEM_ID
//          	JOIN
//          	:Com_("D_T1_SHIPPING_HEADER") ph
//          	ON
//          	    h.FORM_REF_ID = ph.FORM_ID
//          	JOIN
//          	:Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_REF_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	WHERE
//          	    1 = 1  :queryStr :sortStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = queryStr;
  String __sJT_7 = sortStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:207^55*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("antiaudit_receive_count", sumIter.antiaudit_receive_count());
			sumMap.put("antiaudit_pay_amt", sumIter.antiaudit_pay_amt());
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("antiaudit_time", headerIter.antiaudit_time());
			header.put("form_ref_id", headerIter.form_ref_id());
			header.put("antiauditor", headerIter.antiauditor());
			header.put("antiaudit_branch", headerIter.antiaudit_branch());
			header.put("provider", headerIter.provider());
			header.put("out_storage", headerIter.out_storage());
			header.put("requester", headerIter.requester());
			header.put("in_storage", headerIter.in_storage());
			header.put("receive_time", headerIter.receive_time());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("antiaudit_receive_count", headerIter.antiaudit_receive_count());
			header.put("antiaudit_pay_amt", headerIter.antiaudit_pay_amt());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ShippingAntiauditDetailBean_SJProfileKeys 
{
  private static ShippingAntiauditDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingAntiauditDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingAntiauditDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ShippingAntiauditDetailBean_SJProfile0");
  }
}
