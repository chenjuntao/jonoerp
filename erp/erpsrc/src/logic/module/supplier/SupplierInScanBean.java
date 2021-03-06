/*@lineinfo:filename=SupplierInScanBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.module.supplier;

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

public class SupplierInScanBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SupplierInScanBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:30^2*/

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
    providerNdx = findColumn("provider");
    form_ref_idNdx = findColumn("form_ref_id");
    input_departmentNdx = findColumn("input_department");
    inputerNdx = findColumn("inputer");
    form_typeNdx = findColumn("form_type");
    input_timeNdx = findColumn("input_time");
    form_noteNdx = findColumn("form_note");
    audit_timeNdx = findColumn("audit_time");
    all_pay_amtNdx = findColumn("all_pay_amt");
    statusNdx = findColumn("status");
    max_pay_itemNdx = findColumn("max_pay_item");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String input_department() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_departmentNdx);
  }
  private int input_departmentNdx;
  public String inputer() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(inputerNdx);
  }
  private int inputerNdx;
  public String form_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_typeNdx);
  }
  private int form_typeNdx;
  public Date input_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timeNdx);
  }
  private int input_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
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
  public String max_pay_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(max_pay_itemNdx);
  }
  private int max_pay_itemNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^23*/
	
	
	private String formQuery( String supplierId,String branchId,String formIdText1,String formIdText2,String initStatus,String status){
		String query = " 1=1 ";
		if (!TextUtil.isEmpty(supplierId)) { 
			query = query + " AND h.PROVIDER_ID = '" + supplierId + "'";
		}
		
		if (!TextUtil.isEmpty(branchId)) { 
			query = query + " AND h.INPUT_DEPARTMENT_ID = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(formIdText1)) { 
			query = query + " AND upper(h.FORM_ID)  like'%" + formIdText1 + "%'";
		}
		
		if (!TextUtil.isEmpty(formIdText2)) { 
			query = query + " AND upper(h.FORM_REF_ID)  like'%" + formIdText2 + "%'";
		}
		
		if (!TextUtil.isEmpty(initStatus)) { 
			query = query + " AND s.status  in('" + initStatus.replaceAll(",", "','") + "')";
		}
		
		if (!TextUtil.isEmpty(status)) {
			if("供应商已确认".equals(status)){
				query = query + " AND s1.status  = '" + status + "'";
			}else{
				query = query + " AND s1.status is null ";
			}
		}
		
		return query;
	}
	
	/**
	 * startDate 	审核开始日期
	 * endDate	  	审核结束日期
	 * supplierId 	供应商Id
	 * 
	 * 查看入库单状态
	 */
	public List<Map> inScan(java.util.Date startDate, java.util.Date endDate, String supplierId,String branchId,String formIdText1,String formIdText2,String initStatus,String status,int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(supplierId,branchId,formIdText1,formIdText2,initStatus,status);
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
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
//  			                       h.*,
//  			                       s1.status status
//  			                    FROM
//  			                    :Com_("D_T1_INPUT_HEADER") h
//  			                    LEFT JOIN
//  			                    :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        s.FORM_ID = h.FORM_ID
//  			                    LEFT JOIN
//  			                    :Com_("d_t2_branch") b
//  			                    ON
//  			                        h.PROVIDER_ID = b.BRANCH_ID
//                      			LEFT JOIN
//                      			:Com_("D_T0_FORM_STATUS") s1
//  	                        	ON
//  	                        		s1.FORM_ID = h.FORM_ID
//                          		AND s1.IS_CURRENT = 2
//  			                    WHERE
//  			                    	:query
//  			                    AND b.BRANCH_TYPE = 'SUPPLIER'
//  							    AND s.IS_CURRENT = 1
//  							    AND (
//  							            h.AUDIT_TIME >= :sDate
//  							        OR  :sDate IS NULL)
//  							    AND (
//  							            h.AUDIT_TIME <= :eDate
//  							        OR  :eDate IS NULL)
//  							        ORDER BY h.AUDIT_TIME DESC
//  			                		) t
//  			            WHERE
//  			            	ROWNUM < :endRow )
//  			    WHERE
//  			    	rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("d_t2_branch");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierInScanBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setInt(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:137^31*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public int count(java.util.Date startDate, java.util.Date endDate, String supplierId,String branchId,String formIdText1,String formIdText2,String initStatus,String status)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(supplierId,branchId,formIdText1,formIdText2,initStatus,status);
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		int counts = 0;
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:154^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			     count(*) 
//  			    FROM
//  			    :Com_("D_T1_INPUT_HEADER") h
//  			    LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			    ON
//  			        s.FORM_ID = h.FORM_ID
//  			    LEFT JOIN
//  			    :Com_("d_t2_branch") b
//  			    ON
//  			        h.PROVIDER_ID = b.BRANCH_ID
//          		LEFT JOIN
//      			:Com_("D_T0_FORM_STATUS") s1
//              	ON
//              		s1.FORM_ID = h.FORM_ID
//          		AND s1.IS_CURRENT = 2					
//                  WHERE
//                  	:query
//                  AND b.BRANCH_TYPE = 'SUPPLIER'
//  			    AND s.IS_CURRENT = 1
//  			    AND (
//  			            h.AUDIT_TIME >= :sDate
//  			        OR  :sDate IS NULL)
//  			    AND (
//  			            h.AUDIT_TIME <= :eDate
//  			        OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("d_t2_branch");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierInScanBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:181^31*/
		closeDefaultContext(myCtx);
		return counts;
	}
	
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headLst = new ArrayList<Map>();
		while(headerIter.next()) {
			Map<String, Object> head = new HashMap<String, Object>();
			head.put("form_id", headerIter.form_id());
			head.put("provider", headerIter.provider());
			head.put("form_ref_id", headerIter.form_ref_id());
			head.put("input_department", headerIter.input_department());
			head.put("inputer", headerIter.inputer());
			head.put("form_type", headerIter.form_type());
			head.put("input_time", headerIter.input_time());
			head.put("form_note", headerIter.form_note());
			head.put("audit_time", headerIter.audit_time());
			head.put("status", headerIter.status());
			head.put("all_pay_amt", headerIter.all_pay_amt());
			head.put("max_pay_item", headerIter.max_pay_item());
			
			headLst.add(head);
		}
		return headLst;
	}
	
}/*@lineinfo:generated-code*/class SupplierInScanBean_SJProfileKeys 
{
  private static SupplierInScanBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SupplierInScanBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SupplierInScanBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.supplier.SupplierInScanBean_SJProfile0");
  }
}
