/*@lineinfo:filename=OutOrderHandleBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.module.outer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.form.CollectDetail;
import pojo.form.RequestHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;

public class OutOrderHandleBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(OutOrderHandleBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:30^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class OutOrderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public OutOrderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    buyer_nameNdx = findColumn("buyer_name");
    buyer_addressNdx = findColumn("buyer_address");
    providerNdx = findColumn("provider");
    receive_timeNdx = findColumn("receive_time");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    statusNdx = findColumn("status");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String buyer_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(buyer_nameNdx);
  }
  private int buyer_nameNdx;
  public String buyer_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(buyer_addressNdx);
  }
  private int buyer_addressNdx;
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
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^3*/
	
	
	/**
	 * startDate 	审核开始日期
	 * endDate	  	审核结束日期
	 * branchId 	物流中心Id
	 * outOrderId   外部订货方Id
	 * initstatus   初始状态
	 * status		状态
	 */
	public List<Map> query(java.util.Date startDate, java.util.Date endDate, String branchId,String outOrderId, String initStatus, String status)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = queryCondition(branchId,outOrderId,initStatus,status);
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		OutOrderIter outOrderIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:61^3*/

//  ************************************************************
//  #sql [myCtx] outOrderIter = { SELECT
//  			    h.FORM_ID,
//  			    h.BUYER_NAME,
//  			    h.BUYER_ADDRESS,
//  			    h.PROVIDER,
//  			    h.RECEIVE_TIME,
//  			    h.AUDIT_TIME,
//  			    h.FORM_NOTE,
//  			    h.ALL_PAY_AMT,
//  			    h.MAX_PAY_ITEM,
//  			    s.STATUS
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			 :query
//  			AND h.FORM_TYPE = 'outer'
//  			AND (
//  			        h.AUDIT_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.AUDIT_TIME <= :eDate
//  			    OR  :eDate IS NULL) order by h.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OutOrderHandleBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      outOrderIter = new OutOrderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:88^46*/
		List<Map> resultLst = outerOrderIter(outOrderIter);
		outOrderIter.close();
		
		closeDefaultContext(myCtx);
		return resultLst;
	}
	
	private String queryCondition(String branchId,String outOrderId,String initStatus, String status){
		String query = " 1=1";
		
		if (!TextUtil.isEmpty(branchId)) { 
			query = query + " AND h.PROVIDER_ID = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(outOrderId)) { 
			query = query + " AND h.BUYER_ID = '" + outOrderId + "'";
		}
		
		if (!TextUtil.isEmpty(initStatus)) { 
			query = query + " AND s.STATUS in ('" + initStatus.replaceAll(",", "','") + "')";
		}
		
		if (!TextUtil.isEmpty(status)) { 
			query = query + " AND s.STATUS = '" + status + "'";
		}
		
		return query;
	}
	
	/**
	 * startDate 	审核开始日期
	 * endDate	  	审核结束日期
	 * branchId 	物流中心Id
	 * outOrderId   外部订货方Id
	 */
	public Double sum(java.util.Date startDate, java.util.Date endDate, String branchId,String outOrderId,String initStatus, String status)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = queryCondition(branchId,outOrderId,initStatus,status);
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		Double sumAmount = 0.0;
		
		DefaultContext myCtx = getDefaultContext();
		OutOrderIter outOrderIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:135^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				round(sum(h.ALL_PAY_AMT),2) 
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			 :query
//  			AND h.FORM_TYPE = 'outer'
//  			AND (
//  			        h.AUDIT_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.AUDIT_TIME <= :eDate
//  			    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OutOrderHandleBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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
    sumAmount = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:153^27*/
		
		closeDefaultContext(myCtx);
		return sumAmount;
	}
	
	private List<Map> outerOrderIter(OutOrderIter outOrderIter) 
			throws SQLException {
		List<Map> headLst = new ArrayList<Map>();
		while(outOrderIter.next()) {
			Map<String, Object> head = new HashMap<String, Object>();
			head.put("form_id", outOrderIter.form_id());
			head.put("buyer_name", outOrderIter.buyer_name());
			head.put("buyer_address", outOrderIter.buyer_address());
			head.put("provider", outOrderIter.provider());
			head.put("receive_time", outOrderIter.receive_time());
			head.put("audit_time", outOrderIter.audit_time());
			head.put("form_note", outOrderIter.form_note());
			head.put("all_pay_amt", outOrderIter.all_pay_amt());
			head.put("max_pay_item", outOrderIter.max_pay_item());
			head.put("status", outOrderIter.status());
			
			headLst.add(head);
		}
		return headLst;
	}
	
}/*@lineinfo:generated-code*/class OutOrderHandleBean_SJProfileKeys 
{
  private static OutOrderHandleBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new OutOrderHandleBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private OutOrderHandleBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.outer.OutOrderHandleBean_SJProfile0");
  }
}
