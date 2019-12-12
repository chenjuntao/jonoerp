/*@lineinfo:filename=OutOrderScanBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.module.outer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.TransferHeader;
import sqlj.runtime.ref.DefaultContext;

import java.util.HashMap;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class OutOrderScanBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(OutOrderScanBean.class);
	
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
    buyer_nameNdx = findColumn("buyer_name");
    providerNdx = findColumn("provider");
    receive_timeNdx = findColumn("receive_time");
    audit_timeNdx = findColumn("audit_time");
    max_pay_itemNdx = findColumn("max_pay_item");
    all_pay_amtNdx = findColumn("all_pay_amt");
    statusNdx = findColumn("status");
    form_noteNdx = findColumn("form_note");
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
  public String max_pay_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(max_pay_itemNdx);
  }
  private int max_pay_itemNdx;
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^3*/
	
	private String formQuery(String branch_lc,String branch_out,String status) {
		String query = " 1=1";
		
		if (!TextUtil.isEmpty(branch_lc)) {
			query = query + " AND h.PROVIDER_ID = '" + branch_lc+ "'";
		}
		
		if (!TextUtil.isEmpty(branch_out)) {
			query = query + " AND h.BUYER_ID = '" + branch_out+ "'";
		}
		
		if (!TextUtil.isEmpty(status)) {
			query = query + " AND s.status = '"+ status + "'";
		}
		
		return query;
	}
	
	public int count(java.util.Date startDate, java.util.Date endDate,String branch_lc,String branch_out,String status) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(branch_lc,branch_out,status);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:65^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//          	count(*) 
//          FROM
//          :Com_("D_T1_REQUEST_HEADER") h
//          LEFT JOIN
//          :Com_("D_T0_FORM_STATUS") s
//          ON
//              h.FORM_ID = s.FORM_ID
//          WHERE
//          :query
//          AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  		AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//          AND  s.IS_CURRENT = 1
//          AND h.FORM_TYPE = 'outer' };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OutOrderScanBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:79^34*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(java.util.Date startDate, java.util.Date endDate,String branch_lc,String branch_out,String status,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(branch_lc,branch_out,status);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:94^3*/

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
//  			                        h.FORM_ID,
//  			                        h.BUYER_NAME,
//  			                        h.BUYER_ADDRESS,
//  			                        h.PROVIDER,
//  			                        h.RECEIVE_TIME,
//  			                        h.AUDIT_TIME,
//  			                        h.FORM_NOTE,
//  			                        h.ALL_PAY_AMT,
//  			                        h.MAX_PAY_ITEM,
//  			                        CASE
//  		                            WHEN (
//  		                                    SELECT
//  		                                        COUNT(*)
//  		                                    FROM
//  		                                    :Com_("D_T0_FORM_STATUS") s
//  		                                    WHERE
//  		                                        s.STATUS = '已查看'
//  		                                    AND s.FORM_ID = h.FORM_ID) > 0
//  		                            THEN 'Y'
//  		                            ELSE 'N'
//  		                        END status
//  			                    FROM
//  			                    :Com_("D_T1_REQUEST_HEADER") h
//  			                    LEFT JOIN
//  			                    :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        h.FORM_ID = s.FORM_ID
//  			                    WHERE
//  			                    :query
//  			                    AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			        			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  			                    AND  s.IS_CURRENT = 1
//  			                    AND h.FORM_TYPE = 'outer') t
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
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  int __sJT_9 = endRow;
  int __sJT_10 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OutOrderScanBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
      __sJT_stmt.setInt(10, __sJT_10);
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

/*@lineinfo:user-code*//*@lineinfo:141^34*/
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
			header.put("form_id", headerIter.form_id());
			header.put("buyer_name", headerIter.buyer_name());
			header.put("provider", headerIter.provider());
			header.put("receive_time", headerIter.receive_time());
			header.put("audit_time", headerIter.audit_time());
			header.put("max_pay_item", headerIter.max_pay_item());
			header.put("all_pay_amt", headerIter.all_pay_amt());
			header.put("status", headerIter.status());
			header.put("form_note", headerIter.form_note());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class OutOrderScanBean_SJProfileKeys 
{
  private static OutOrderScanBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new OutOrderScanBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private OutOrderScanBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.outer.OutOrderScanBean_SJProfile0");
  }
}
