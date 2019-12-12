/*@lineinfo:filename=SupplierReturnBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.module.supplier;

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

public class SupplierReturnBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SupplierReturnBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:30^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ReturnIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ReturnIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    returnerNdx = findColumn("returner");
    return_timeNdx = findColumn("return_time");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    providerNdx = findColumn("provider");
    input_departmentNdx = findColumn("input_department");
    statusNdx = findColumn("status");
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
  public String returner() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(returnerNdx);
  }
  private int returnerNdx;
  public Date return_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(return_timeNdx);
  }
  private int return_timeNdx;
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
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String input_department() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_departmentNdx);
  }
  private int input_departmentNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^3*/
	
	//	动态条件
	private String formQuery( String supplierId,String branchId,String formIdText1,String formIdText2,String initStatus,String status){
		String query = " 1=1 ";
		if (!TextUtil.isEmpty(supplierId)) { 
			query = query + " AND ih.PROVIDER_ID = '" + supplierId + "'";
		}
		
		if (!TextUtil.isEmpty(branchId)) { 
			query = query + " AND ih.INPUT_DEPARTMENT_ID = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(formIdText1)) { 
			query = query + " AND upper(gh.FORM_ID)  like'%" + formIdText1 + "%'";
		}
		
		if (!TextUtil.isEmpty(formIdText2)) { 
			query = query + " AND upper(ih.FORM_ID)  like'%" + formIdText2 + "%'";
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

	public int count(java.util.Date startDate, java.util.Date endDate, String supplierId,String branchId,String formIdText1,String formIdText2,String initStatus,String status)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(supplierId,branchId,formIdText1,formIdText2,initStatus,status);
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		int total = 0;
		
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:88^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_RETURN_GOODS_HEADER") gh
//  		LEFT JOIN
//  		:Com_("D_T1_INPUT_HEADER") ih
//  		ON
//  		    gh.FORM_REF_ID = ih.FORM_ID
//  		LEFT JOIN
//  		:Com_("d_t2_branch") b
//  		ON
//  		    ih.PROVIDER_ID = b.BRANCH_ID
//  		LEFT JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    gh.FORM_ID = s.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		LEFT JOIN
//  		:Com_("D_T0_FORM_STATUS") s1
//      	ON
//      		s1.FORM_ID = gh.FORM_ID
//  		AND s1.IS_CURRENT = 2
//  		WHERE
//  		    :query
//  		AND b.BRANCH_TYPE = 'SUPPLIER'
//  		AND (
//  		        gh.RETURN_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        gh.RETURN_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_HEADER");
  String __sJT_3 = Com_("d_t2_branch");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = query;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierReturnBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
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

/*@lineinfo:user-code*//*@lineinfo:121^26*/
		closeDefaultContext(myCtx);
		return total;
	}

	
	/**
	 * startDate 	退货开始日期
	 * endDate	  	退货结束日期
	 * supplierId 	供应商Id
	 * 
	 * 退货单查询
	 */
	public List<Map> query(java.util.Date startDate, java.util.Date endDate, String supplierId,String branchId,String formIdText1,String formIdText2,String initStatus,String status,int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(supplierId,branchId,formIdText1,formIdText2,initStatus,status);
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		ReturnIter returnIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:143^3*/

//  ************************************************************
//  #sql [myCtx] returnIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                    SELECT
//  			                        gh.FORM_ID,
//  			                        gh.FORM_REF_ID,
//  			                        gh.RETURNER,
//  			                        gh.RETURN_TIME,
//  			                        gh.FORM_NOTE,
//  			                        gh.ALL_PAY_AMT,
//  			                        gh.MAX_PAY_ITEM,
//  			                        ih.PROVIDER,
//  			                        ih.INPUT_DEPARTMENT,
//  			                        s1.STATUS
//  			                    FROM
//  			                    :Com_("D_T1_RETURN_GOODS_HEADER") gh
//  			                    LEFT JOIN
//  			                    :Com_("D_T1_INPUT_HEADER") ih
//  			                    ON
//  			                        gh.FORM_REF_ID = ih.FORM_ID
//  			                    LEFT JOIN
//  			                    :Com_("d_t2_branch") b
//  			                    ON
//  			                        ih.PROVIDER_ID = b.BRANCH_ID
//  			                    LEFT JOIN
//  			                    :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        gh.FORM_ID = s.FORM_ID
//  			                    AND s.IS_CURRENT = 1
//  	                    		LEFT JOIN
//                      			:Com_("D_T0_FORM_STATUS") s1
//  	                        	ON
//  	                        		s1.FORM_ID = gh.FORM_ID
//                          		AND s1.IS_CURRENT = 2
//  			                    WHERE
//  			                        :query
//  			                    AND b.BRANCH_TYPE = 'SUPPLIER'
//  			                    AND (
//  			                            gh.RETURN_TIME >= :sDate
//  			                        OR  :sDate IS NULL)
//  			                    AND (
//  			                            gh.RETURN_TIME <= :eDate
//  			                        OR  :eDate IS NULL)) t
//  			            WHERE
//  			                ROWNUM < :endRow )
//  			    WHERE
//  			        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_HEADER");
  String __sJT_3 = Com_("d_t2_branch");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = query;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  int __sJT_11 = endRow;
  int __sJT_12 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierReturnBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
      __sJT_stmt.setInt(12, __sJT_12);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      returnIter = new ReturnIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:196^34*/
		List<Map> returnLst = returnProcessIter(returnIter);
		returnIter.close();
		closeDefaultContext(myCtx);
		return returnLst;
	}
	
	
	private List<Map> returnProcessIter(ReturnIter returnIter) 
			throws SQLException {
		List<Map> headLst = new ArrayList<Map>();
		while(returnIter.next()) {
			Map<String, Object> head = new HashMap<String, Object>();
			head.put("form_id", returnIter.form_id());
			head.put("form_ref_id", returnIter.form_ref_id());
			head.put("returner", returnIter.returner());
			head.put("return_time", returnIter.return_time());
			head.put("form_note", returnIter.form_note());
			head.put("all_pay_amt", returnIter.all_pay_amt());
			head.put("max_pay_item", returnIter.max_pay_item());
			head.put("provider", returnIter.provider());
			head.put("input_department", returnIter.input_department());
			head.put("status", returnIter.status());
			
			headLst.add(head);
		}
		return headLst;
	}
	
}/*@lineinfo:generated-code*/class SupplierReturnBean_SJProfileKeys 
{
  private static SupplierReturnBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SupplierReturnBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SupplierReturnBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.supplier.SupplierReturnBean_SJProfile0");
  }
}
