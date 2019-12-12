/*@lineinfo:filename=ShippingAntiauditHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class ShippingAntiauditHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShippingAntiauditHeaderBean.class);
	
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
    antiaudit_branch_idNdx = findColumn("antiaudit_branch_id");
    antiaudit_branchNdx = findColumn("antiaudit_branch");
    providerNdx = findColumn("provider");
    out_storageNdx = findColumn("out_storage");
    requesterNdx = findColumn("requester");
    in_storageNdx = findColumn("in_storage");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    antiaudit_receive_countNdx = findColumn("antiaudit_receive_count");
    antiaudit_pay_amtNdx = findColumn("antiaudit_pay_amt");
  }
  public String antiaudit_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(antiaudit_branch_idNdx);
  }
  private int antiaudit_branch_idNdx;
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

/*@lineinfo:user-code*//*@lineinfo:37^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

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

/*@lineinfo:user-code*//*@lineinfo:41^28*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("antiaudit_branch_id", "String");
		map.put("antiaudit_branch", "String");
		map.put("provider", "String");
		map.put("out_storage", "String");
		map.put("requester", "String");
		map.put("in_storage", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("antiaudit_receive_count", "Number");
		map.put("antiaudit_pay_amt", "Number");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("antiaudit_branch_id", "h");
		map.put("antiaudit_branch", "h");
		map.put("provider", "ph");
		map.put("out_storage", "ph");
		map.put("requester", "ph");
		map.put("in_storage", "ph");
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
		/*@lineinfo:generated-code*//*@lineinfo:79^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*) 
//  			from (SELECT
//              	   count(*)
//              	FROM
//              	:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") h
//              	LEFT JOIN
//              	:Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL") d
//              	ON
//              	    h.FORM_REF_ID = d.FORM_REF_ID
//              	LEFT JOIN
//              	:Com_("D_T2_ITEM_META") im
//              	ON
//              	    d.ITEM_ID = im.ITEM_ID
//              	JOIN
//              	:Com_("D_T1_SHIPPING_HEADER") ph
//              	ON
//              	    h.FORM_REF_ID = ph.FORM_ID
//              	JOIN
//              	:Com_("D_T0_FORM_STATUS") s
//              	ON
//              	    h.FORM_REF_ID = s.FORM_ID
//              	AND s.IS_CURRENT = 1
//              	WHERE
//              	    1 = 1  :queryStr
//              	GROUP BY
//              	    h.ANTIAUDIT_BRANCH_ID,
//              	    h.ANTIAUDIT_BRANCH,
//              	    ph.PROVIDER,
//              	    ph.OUT_STORAGE,
//              	    ph.REQUESTER,
//              	    ph.IN_STORAGE,
//              	    d.ITEM_ID,
//              	    im.ITEM_NAME :sortStr)a
//  		 };
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
  String __sJT_7 = sortStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:113^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:124^3*/

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
//  			                		h.ANTIAUDIT_BRANCH_ID,
//  			                	    h.ANTIAUDIT_BRANCH,
//  			                	    ph.PROVIDER,
//  			                	    ph.OUT_STORAGE,
//  			                	    ph.REQUESTER,
//  			                	    ph.IN_STORAGE,
//  			                	    d.ITEM_ID,
//  			                	    im.ITEM_NAME,
//  			                	    SUM(NVL(d.ANTIAUDIT_RECEIVE_COUNT,0)) ANTIAUDIT_RECEIVE_COUNT,
//  			                	    SUM(NVL(d.ANTIAUDIT_PAY_AMT,0))       ANTIAUDIT_PAY_AMT
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
//  			                	    1 = 1  :queryStr
//  			                	GROUP BY
//  			                	    h.ANTIAUDIT_BRANCH_ID,
//  			                	    h.ANTIAUDIT_BRANCH,
//  			                	    ph.PROVIDER,
//  			                	    ph.OUT_STORAGE,
//  			                	    ph.REQUESTER,
//  			                	    ph.IN_STORAGE,
//  			                	    d.ITEM_ID,
//  			                	    im.ITEM_NAME :sortStr) t
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:177^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:191^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    round(SUM (ANTIAUDIT_RECEIVE_COUNT),2) ANTIAUDIT_RECEIVE_COUNT,
//  			    round(SUM(ANTIAUDIT_PAY_AMT),2)     ANTIAUDIT_PAY_AMT
//  		FROM
//  		    (SELECT
//              	    SUM(NVL(d.ANTIAUDIT_RECEIVE_COUNT,0)) ANTIAUDIT_RECEIVE_COUNT,
//              	    SUM(NVL(d.ANTIAUDIT_PAY_AMT,0))       ANTIAUDIT_PAY_AMT
//              	FROM
//              	:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") h
//              	LEFT JOIN
//              	:Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL") d
//              	ON
//              	    h.FORM_REF_ID = d.FORM_REF_ID
//              	LEFT JOIN
//              	:Com_("D_T2_ITEM_META") im
//              	ON
//              	    d.ITEM_ID = im.ITEM_ID
//              	JOIN
//              	:Com_("D_T1_SHIPPING_HEADER") ph
//              	ON
//              	    h.FORM_REF_ID = ph.FORM_ID
//              	JOIN
//              	:Com_("D_T0_FORM_STATUS") s
//              	ON
//              	    h.FORM_REF_ID = s.FORM_ID
//              	AND s.IS_CURRENT = 1
//              	WHERE
//              	    1 = 1  :queryStr
//              	GROUP BY
//              	    h.ANTIAUDIT_BRANCH_ID,
//              	    h.ANTIAUDIT_BRANCH,
//              	    ph.PROVIDER,
//              	    ph.OUT_STORAGE,
//              	    ph.REQUESTER,
//              	    ph.IN_STORAGE,
//              	    d.ITEM_ID,
//              	    im.ITEM_NAME ) a };
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
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:228^34*/
		
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
			header.put("antiaudit_branch_id", headerIter.antiaudit_branch_id());
			header.put("antiaudit_branch", headerIter.antiaudit_branch());
			header.put("provider", headerIter.provider());
			header.put("out_storage", headerIter.out_storage());
			header.put("requester", headerIter.requester());
			header.put("in_storage", headerIter.in_storage());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("antiaudit_receive_count", headerIter.antiaudit_receive_count());
			header.put("antiaudit_pay_amt", headerIter.antiaudit_pay_amt());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ShippingAntiauditHeaderBean_SJProfileKeys 
{
  private static ShippingAntiauditHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingAntiauditHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingAntiauditHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ShippingAntiauditHeaderBean_SJProfile0");
  }
}
