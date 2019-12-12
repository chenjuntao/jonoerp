/*@lineinfo:filename=ShippingAntiauditFormBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class ShippingAntiauditFormBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShippingAntiauditFormBean.class);
	
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
    form_noteNdx = findColumn("form_note");
    providerNdx = findColumn("provider");
    out_storageNdx = findColumn("out_storage");
    requesterNdx = findColumn("requester");
    in_storageNdx = findColumn("in_storage");
    inputerNdx = findColumn("inputer");
    input_timeNdx = findColumn("input_time");
    receive_timeNdx = findColumn("receive_time");
    max_pay_itemNdx = findColumn("max_pay_item");
    statusNdx = findColumn("status");
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
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
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
  public String inputer() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(inputerNdx);
  }
  private int inputerNdx;
  public Date input_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timeNdx);
  }
  private int input_timeNdx;
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
  public String max_pay_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(max_pay_itemNdx);
  }
  private int max_pay_itemNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^3*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("antiaudit_time", "Date"); 
		map.put("form_ref_id", "String");
		map.put("antiauditor", "String");
		map.put("antiaudit_branch", "String");
		map.put("form_note", "String"); 
		map.put("provider", "String");
		map.put("out_storage", "String");
		map.put("requester", "String");
		map.put("in_storage", "String");
		map.put("inputer", "String");
		map.put("input_time", "Date"); 
		map.put("receive_time", "Date"); 
		map.put("max_pay_item", "String");
		map.put("status", "String");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("antiaudit_time", "h"); 
		map.put("form_ref_id", "h");
		map.put("antiauditor", "h");
		map.put("antiaudit_branch", "h");
		map.put("form_note", "h"); 
		map.put("provider", "ph");
		map.put("out_storage", "ph");
		map.put("requester", "ph");
		map.put("in_storage", "ph");
		map.put("inputer", "ph");
		map.put("input_time", "ph"); 
		map.put("receive_time", "ph"); 
		map.put("max_pay_item", "ph");
		map.put("status", "s");
		return map;
	}
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:87^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//      	  count(*) 
//      	FROM
//      	:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") h
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
//      	    1 = 1 :queryStr :sortStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  String __sJT_5 = sortStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditFormBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:102^50*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:113^3*/

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
//  			                	    h.FORM_NOTE,
//  			                	    ph.PROVIDER,
//  			                	    ph.OUT_STORAGE,
//  			                	    ph.RECEIVE_TIME,
//  			                	    ph.REQUESTER,
//  			                	    ph.IN_STORAGE,
//  			                	    ph.INPUTER,
//  			                	    ph.INPUT_TIME,
//  			                	    ph.MAX_PAY_ITEM,
//  			                	    s.STATUS
//  			                	FROM
//  			                	:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") h
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
//  			                	    1 = 1 :queryStr :sortStr) t
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
  String __sJT_2 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  String __sJT_5 = sortStr;
  int __sJT_6 = endRow;
  int __sJT_7 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditFormBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:153^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
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
			header.put("form_note", headerIter.form_note());
			header.put("provider", headerIter.provider());
			header.put("out_storage", headerIter.out_storage());
			header.put("requester", headerIter.requester());
			header.put("in_storage", headerIter.in_storage());
			header.put("inputer", headerIter.inputer());
			header.put("input_time", headerIter.input_time());
			header.put("receive_time", headerIter.receive_time());
			header.put("max_pay_item", headerIter.max_pay_item());
			header.put("status", headerIter.status());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ShippingAntiauditFormBean_SJProfileKeys 
{
  private static ShippingAntiauditFormBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingAntiauditFormBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingAntiauditFormBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ShippingAntiauditFormBean_SJProfile0");
  }
}
