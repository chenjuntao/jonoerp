/*@lineinfo:filename=OrderStatementBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.module.outer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;


import com.tanry.business.form.FormConstant;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class OrderStatementBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(OrderStatementBean.class);
	
	 /*@lineinfo:generated-code*//*@lineinfo:26^3*/

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
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    receive_timeNdx = findColumn("receive_time");
    requesterNdx = findColumn("requester");
    request_addressNdx = findColumn("request_address");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    form_timeNdx = findColumn("form_time");
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
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
  public String request_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(request_addressNdx);
  }
  private int request_addressNdx;
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
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^10*/
	 
		public List<Map> queryOutReturn(java.util.Date startDate, java.util.Date endDate, String supplierId,String formIdText,String branchId,String branchType, int startRow, int endRow) 
				throws NoPrivilegeException,SQLException,NoConnection {
			Date sDate = SqlDateUtil.getSqlDate(startDate);
			Date eDate = SqlDateUtil.getSqlDate(endDate);
			
			String query = formQuery(supplierId,formIdText,branchId,"",branchType);
			String returnQuery =  returnFormQuery(supplierId,formIdText,branchId,"");
			DefaultContext myCtx = getDefaultContext();
			
			HeaderIter headerIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:51^4*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    *
//  				    FROM
//  				        (
//  				            SELECT
//  				                t.*,
//  				                ROWNUM rowNumber
//  				            FROM
//  				                (
//  	                		   SELECT
//  		                        *
//  		                    FROM
//  		                        (
//  		                		WITH
//  		                	    	inform AS
//  		                	    (
//  			                		SELECT
//  				                	    h.FORM_ID,
//  				                	    h.FORM_REF_ID,
//  				                	    h.PROVIDER_ID,
//  				                	    h.PROVIDER,
//  				                	    h.RECEIVE_TIME,
//  				                	    h.REQUESTER_ID,
//  				                	    h.REQUESTER,
//  				                	    h.REQUEST_ADDRESS,
//  				                	    h.FORM_NOTE,
//  				                	    h.ALL_PAY_AMT,
//  				                	    h.MAX_PAY_ITEM,
//  				                	    h.FORM_TIME,
//  				                	    s1.status
//  				                	FROM
//  				                	:Com_("D_T1_SHIPPING_HEADER") h
//  				                	LEFT JOIN
//  				                	:Com_("D_T0_FORM_STATUS") s
//  				                	ON
//  				                	    h.FORM_ID = s.FORM_ID
//  	                	    		LEFT JOIN
//                          			:Com_("D_T0_FORM_STATUS") s1
//      	                        	ON
//      	                        		s1.FORM_ID = h.FORM_ID
//                              		AND s1.IS_CURRENT = 2
//  				                	WHERE
//  				                	  :query
//  				                    AND (h.FORM_TIME >= :sDate or :sDate is null)
//  				        			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  				                	AND s.STATUS IN('已审核')
//  				                	AND s.IS_CURRENT = 1
//  		                			AND h.INPUT_STATUS = '已入库'
//  				                	AND h.FORM_ID NOT IN
//                                      ( SELECT DISTINCT
//                                              d.SUB_FORM_ID
//                                              FROM
//                                              :Com_("D_T1_STATEMENT_HEADER") h
//                                              LEFT JOIN
//                                              :Com_("D_T1_STATEMENT_DETAIL") d
//                                              ON
//                                                  h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )
//  		                	    		  )
//  		                	    		  SELECT
//  		                	    		      *
//  		                	    		  FROM
//  		                	    		      inform
//  		                	    		  UNION ALL
//  		                	    		  SELECT
//  		                	    		      gh.FORM_ID,
//  		                	    		      gh.FORM_REF_ID,
//  		                	    		      sh.PROVIDER_ID,
//  		                	    		      sh.PROVIDER,
//  		                	    		      gh.RETURN_TIME RECEIVE_TIME,
//  		                	    		      sh.REQUESTER_ID,
//  		                	    		      sh.REQUESTER,
//  		                	    		      sh.REQUEST_ADDRESS,
//  		                	    		      gh.FORM_NOTE,
//  		                	    		      NVL( -gh.ALL_PAY_AMT,0) ALL_PAY_AMT,
//  		                	    		      gh.MAX_PAY_ITEM,
//  		                	    		      gh.RETURN_TIME FORM_TIME,
//  		                	    		      s1.status
//  		                	    		  FROM
//  		                	  		        :Com_("D_T1_RETURN_GOODS_HEADER") gh
//  		                	  		        LEFT JOIN
//  		                	  		        :Com_("D_T1_SHIPPING_HEADER") sh
//  		                	  		        ON
//  		                	  		            gh.FORM_REF_ID = sh.FORM_ID
//  		                	  		        LEFT JOIN
//  		                	  		        :Com_("D_T0_FORM_STATUS") s
//  		                	  		        ON
//  		                	  		            gh.FORM_ID = s.FORM_ID
//              	  		            		LEFT JOIN
//                                  			:Com_("D_T0_FORM_STATUS") s1
//              	                        	ON
//              	                        		s1.FORM_ID = gh.FORM_ID
//                                      		AND s1.IS_CURRENT = 2
//  		                	  		        WHERE
//  		                	  		            :returnQuery
//  		                	  		        AND (
//  		                	  		                gh.RETURN_TIME >= :sDate
//  		                	  		            OR  :sDate IS NULL)
//  		                	  		        AND (
//  		                	  		                gh.RETURN_TIME <= :eDate
//  		                	  		            OR  :eDate IS NULL)
//  		                	  		        AND s.IS_CURRENT = 1
//  		                	  		        AND s.STATUS IN('已处理')
//  		                	  		        AND gh.FORM_ID NOT IN
//                                                ( SELECT DISTINCT
//                                                        d.SUB_FORM_ID
//                                                        FROM
//                                                        :Com_("D_T1_STATEMENT_HEADER") h
//                                                        LEFT JOIN
//                                                        :Com_("D_T1_STATEMENT_DETAIL") d
//                                                        ON
//                                                            h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )
//  		                        		)
//  					                    ORDER BY
//  					                    RECEIVE_TIME,
//  					                        FORM_ID) t
//  				            WHERE
//  				                ROWNUM < :endRow)
//  				    WHERE
//  				        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_10 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_11 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_12 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_13 = Com_("D_T0_FORM_STATUS");
  String __sJT_14 = Com_("D_T0_FORM_STATUS");
  String __sJT_15 = returnQuery;
  Date __sJT_16 = sDate;
  Date __sJT_17 = sDate;
  Date __sJT_18 = eDate;
  Date __sJT_19 = eDate;
  String __sJT_20 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_21 = Com_("D_T1_STATEMENT_DETAIL");
  int __sJT_22 = endRow;
  int __sJT_23 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OrderStatementBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setInt(22, __sJT_22);
      __sJT_stmt.setInt(23, __sJT_23);
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

/*@lineinfo:user-code*//*@lineinfo:170^35*/
			List<Map> headerLst = processIter(headerIter);
			headerIter.close();
			closeDefaultContext(myCtx);
			return headerLst;
		}
	
	private String formQuery(String supplierId, String formIdText,String branchId,String status,String branchType) {
		String query = " 1=1";
		if (!TextUtil.isEmpty(supplierId)) {
			query = query + " AND h.PROVIDER_ID = '" + supplierId + "'";
		}
		
		if (!TextUtil.isEmpty(formIdText)) {
			query = query + " AND upper(h.FORM_ID) like '%" + formIdText + "%'";
		}
		
		if (!TextUtil.isEmpty(status)) {
			query = query + " AND s.STATUS in ('" + status.replaceAll(",", "'") + "')";
		}
		
		if("OUTERORDER".equals(branchType)){
			query = query + " AND h.FORM_TYPE = 'OUTER' ";
		}else{
			query = query + " AND h.FORM_TYPE in ('INNER_UNIFIED','INNER_CROSS')";
		}
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND h.REQUESTER_ID = '" + branchId + "'";
		}
		return query;
	}
	
	/**
	 * 采购退货单动态条件
	 */
	private String returnFormQuery(String supplierId,String formIdText,String branchId,String status) {
		String query = " 1=1";
//		if (!TextUtil.isEmpty(supplierId)) {
//			query = query + " AND i.PROVIDER_ID = '" + supplierId + "'";
//		}
		
		if (!TextUtil.isEmpty(formIdText)) {
			query = query + " AND upper(gh.FORM_ID) like '%" + formIdText + "%'";
		}
		
		if (!TextUtil.isEmpty(status)) {
			query = query + " AND s.STATUS in ('" + status.replaceAll(",", "'") + "')";
		}
		
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND sh.REQUESTER_ID = '" + branchId + "'";
		}
		return query;
	}
	
	public int outReturnCount(java.util.Date startDate, java.util.Date endDate, String supplierId,String formIdText,String branchId,String branchType)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(supplierId,formIdText,branchId,"",branchType);
		String returnQuery =  returnFormQuery(supplierId,formIdText,branchId,"");
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:235^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		(
//          		WITH
//      	    	inform AS
//      	    (
//          		SELECT
//              	    h.FORM_ID,
//              	    h.FORM_REF_ID,
//              	    h.PROVIDER,
//              	    h.RECEIVE_TIME,
//              	    h.REQUESTER_ID,
//              	    h.REQUESTER,
//              	    h.REQUEST_ADDRESS,
//              	    h.FORM_NOTE,
//              	    h.ALL_PAY_AMT,
//              	    h.MAX_PAY_ITEM,
//              	    h.FORM_TIME,
//              	    s1.status
//              	FROM
//              	:Com_("D_T1_SHIPPING_HEADER") h
//              	LEFT JOIN
//              	:Com_("D_T0_FORM_STATUS") s
//              	ON
//              	    h.FORM_ID = s.FORM_ID
//  	    		LEFT JOIN
//      			:Com_("D_T0_FORM_STATUS") s1
//              	ON
//              		s1.FORM_ID = h.FORM_ID
//          		AND s1.IS_CURRENT = 2
//              	WHERE
//              	  :query
//                  AND (h.FORM_TIME >= :sDate or :sDate is null)
//      			AND (h.FORM_TIME <= :eDate or :eDate is null)
//              	AND s.STATUS IN('已审核')
//              	AND s.IS_CURRENT = 1
//      			AND h.INPUT_STATUS = '已入库'
//              	AND h.FORM_ID NOT IN
//                  ( SELECT DISTINCT
//                          d.SUB_FORM_ID
//                          FROM
//                          :Com_("D_T1_STATEMENT_HEADER") h
//                          LEFT JOIN
//                          :Com_("D_T1_STATEMENT_DETAIL") d
//                          ON
//                              h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )
//      	    		  )
//      	    		  SELECT
//      	    		      *
//      	    		  FROM
//      	    		      inform
//      	    		  UNION ALL
//      	    		  SELECT
//  	    		      gh.FORM_ID,
//  	    		      gh.FORM_REF_ID,
//  	    		      sh.PROVIDER,
//  	    		      gh.RETURN_TIME RECEIVE_TIME,
//  	    		      sh.REQUESTER_ID,
//  	    		      sh.REQUESTER,
//  	    		      sh.REQUEST_ADDRESS,
//  	    		      gh.FORM_NOTE,
//  	    		      NVL( -gh.ALL_PAY_AMT,0) ALL_PAY_AMT,
//  	    		      gh.MAX_PAY_ITEM,
//  	    		      gh.RETURN_TIME FORM_TIME,
//  	    		      s1.status
//  	    		  FROM
//  	  		        :Com_("D_T1_RETURN_GOODS_HEADER") gh
//  	  		        LEFT JOIN
//  	  		        :Com_("D_T1_SHIPPING_HEADER") sh
//  	  		        ON
//  	  		            gh.FORM_REF_ID = sh.FORM_ID
//  	  		        LEFT JOIN
//  	  		        :Com_("D_T0_FORM_STATUS") s
//  	  		        ON
//  	  		            gh.FORM_ID = s.FORM_ID
//  	            		LEFT JOIN
//          			:Com_("D_T0_FORM_STATUS") s1
//                  	ON
//                  		s1.FORM_ID = gh.FORM_ID
//              		AND s1.IS_CURRENT = 2
//  	  		        WHERE
//  	  		            :returnQuery
//  	  		        AND (
//  	  		                gh.RETURN_TIME >= :sDate
//  	  		            OR  :sDate IS NULL)
//  	  		        AND (
//  	  		                gh.RETURN_TIME <= :eDate
//  	  		            OR  :eDate IS NULL)
//  	  		        AND s.IS_CURRENT = 1
//  	  		        AND s.STATUS IN('已处理')
//  	  		        AND gh.FORM_ID NOT IN
//                        ( SELECT DISTINCT
//                                d.SUB_FORM_ID
//                                FROM
//                                :Com_("D_T1_STATEMENT_HEADER") h
//                                LEFT JOIN
//                                :Com_("D_T1_STATEMENT_DETAIL") d
//                                ON
//                                    h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )
//            	    		  )
//  	 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_10 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_11 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_12 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_13 = Com_("D_T0_FORM_STATUS");
  String __sJT_14 = Com_("D_T0_FORM_STATUS");
  String __sJT_15 = returnQuery;
  Date __sJT_16 = sDate;
  Date __sJT_17 = sDate;
  Date __sJT_18 = eDate;
  Date __sJT_19 = eDate;
  String __sJT_20 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_21 = Com_("D_T1_STATEMENT_DETAIL");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OrderStatementBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
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

/*@lineinfo:user-code*//*@lineinfo:340^2*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public Double outReturnSum(java.util.Date startDate, java.util.Date endDate, String supplierId,String formIdText,String branchId,String branchType)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(supplierId,formIdText,branchId,"",branchType);
		String returnQuery =  returnFormQuery(supplierId,formIdText,branchId,"");
		
		DefaultContext myCtx = getDefaultContext();
		Double total = 0.0;
		/*@lineinfo:generated-code*//*@lineinfo:355^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    SUM(ALL_PAY_AMT)
//  		
//  		FROM
//  		(
//          		WITH
//      	    	inform AS
//      	    (
//          		SELECT
//              	    h.FORM_ID,
//              	    h.FORM_REF_ID,
//              	    h.PROVIDER,
//              	    h.RECEIVE_TIME,
//              	    h.REQUESTER_ID,
//              	    h.REQUESTER,
//              	    h.REQUEST_ADDRESS,
//              	    h.FORM_NOTE,
//              	    h.ALL_PAY_AMT,
//              	    h.MAX_PAY_ITEM,
//              	    h.FORM_TIME,
//              	    s1.status
//              	FROM
//              	:Com_("D_T1_SHIPPING_HEADER") h
//              	LEFT JOIN
//              	:Com_("D_T0_FORM_STATUS") s
//              	ON
//              	    h.FORM_ID = s.FORM_ID
//  	    		LEFT JOIN
//      			:Com_("D_T0_FORM_STATUS") s1
//              	ON
//              		s1.FORM_ID = h.FORM_ID
//          		AND s1.IS_CURRENT = 2
//              	WHERE
//              	  :query
//                  AND (h.FORM_TIME >= :sDate or :sDate is null)
//      			AND (h.FORM_TIME <= :eDate or :eDate is null)
//              	AND s.STATUS IN('已审核')
//              	AND s.IS_CURRENT = 1
//      			AND h.INPUT_STATUS = '已入库'
//              	AND h.FORM_ID NOT IN
//                  ( SELECT DISTINCT
//                          d.SUB_FORM_ID
//                          FROM
//                          :Com_("D_T1_STATEMENT_HEADER") h
//                          LEFT JOIN
//                          :Com_("D_T1_STATEMENT_DETAIL") d
//                          ON
//                              h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )
//      	    		  )
//      	    		  SELECT
//      	    		      *
//      	    		  FROM
//      	    		      inform
//      	    		  UNION ALL
//      	    		  SELECT
//  	    		      gh.FORM_ID,
//  	    		      gh.FORM_REF_ID,
//  	    		      sh.PROVIDER,
//  	    		      gh.RETURN_TIME RECEIVE_TIME,
//  	    		      sh.REQUESTER_ID,
//  	    		      sh.REQUESTER,
//  	    		      sh.REQUEST_ADDRESS,
//  	    		      gh.FORM_NOTE,
//  	    		      NVL( -gh.ALL_PAY_AMT,0) ALL_PAY_AMT,
//  	    		      gh.MAX_PAY_ITEM,
//  	    		      gh.RETURN_TIME FORM_TIME,
//  	    		      s1.status
//  	    		  FROM
//  	  		        :Com_("D_T1_RETURN_GOODS_HEADER") gh
//  	  		        LEFT JOIN
//  	  		        :Com_("D_T1_SHIPPING_HEADER") sh
//  	  		        ON
//  	  		            gh.FORM_REF_ID = sh.FORM_ID
//  	  		        LEFT JOIN
//  	  		        :Com_("D_T0_FORM_STATUS") s
//  	  		        ON
//  	  		            gh.FORM_ID = s.FORM_ID
//  	            		LEFT JOIN
//          			:Com_("D_T0_FORM_STATUS") s1
//                  	ON
//                  		s1.FORM_ID = gh.FORM_ID
//              		AND s1.IS_CURRENT = 2
//  	  		        WHERE
//  	  		            :returnQuery
//  	  		        AND (
//  	  		                gh.RETURN_TIME >= :sDate
//  	  		            OR  :sDate IS NULL)
//  	  		        AND (
//  	  		                gh.RETURN_TIME <= :eDate
//  	  		            OR  :eDate IS NULL)
//  	  		        AND s.IS_CURRENT = 1
//  	  		        AND s.STATUS IN('已处理')
//  	  		        AND gh.FORM_ID NOT IN
//                        ( SELECT DISTINCT
//                                d.SUB_FORM_ID
//                                FROM
//                                :Com_("D_T1_STATEMENT_HEADER") h
//                                LEFT JOIN
//                                :Com_("D_T1_STATEMENT_DETAIL") d
//                                ON
//                                    h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )
//            	    		  )
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_10 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_11 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_12 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_13 = Com_("D_T0_FORM_STATUS");
  String __sJT_14 = Com_("D_T0_FORM_STATUS");
  String __sJT_15 = returnQuery;
  Date __sJT_16 = sDate;
  Date __sJT_17 = sDate;
  Date __sJT_18 = eDate;
  Date __sJT_19 = eDate;
  String __sJT_20 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_21 = Com_("D_T1_STATEMENT_DETAIL");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OrderStatementBean_SJProfileKeys.getKey(0), 2);
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
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
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
    total = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:459^3*/
			closeDefaultContext(myCtx);
			return total;
	}
	
	public List<Map> queryInBySForm(String sformId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:469^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.form_id,
//  			    h.form_ref_id,
//  			    h.provider,
//  			    h.PROVIDER_ID,
//  			    h.receive_time,
//  			    h.requester,
//  			    h.request_address,
//  			    h.form_note,
//  			    h.all_pay_amt,
//  			    h.max_pay_item,
//  			    h.form_time,
//  			    s.STATUS
//  			FROM
//  			:Com_("D_T1_STATEMENT") t
//  			LEFT JOIN
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			ON
//  			    t.SUB_FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			WHERE
//  			    s.IS_CURRENT = 2
//  			AND t.FORM_ID = :sformId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT");
  String __sJT_2 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = sformId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OrderStatementBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:496^4*/
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
			header.put("form_ref_id", headerIter.form_ref_id());
			header.put("provider", "["+headerIter.provider_id()+"]"+headerIter.provider());
			header.put("receive_time", headerIter.receive_time());
			header.put("requester", headerIter.requester());
			header.put("request_address", headerIter.request_address());
			header.put("form_note", headerIter.form_note());
			header.put("all_pay_amt", headerIter.all_pay_amt());
			header.put("max_pay_item", headerIter.max_pay_item());
			header.put("form_time", headerIter.form_time());
			header.put("status", headerIter.status());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class OrderStatementBean_SJProfileKeys 
{
  private static OrderStatementBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new OrderStatementBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private OrderStatementBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.outer.OrderStatementBean_SJProfile0");
  }
}
