/*@lineinfo:filename=SupplierStatement*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Mar 05 10:28:15 CST 2015 by pw
 * Last edited on Thu Mar 05 10:28:15 CST 2015 by pw
 * 
 * comment: 供应商 总部财务 对账单 相关功能
 */
package logic.module.supplier;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.InputHeader;

import com.tanry.business.form.FormConstant;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class SupplierStatement extends ConnectionPool {

	public static Logger log = Logger.getLogger(SupplierStatement.class);
	
	//入库单
	 /*@lineinfo:generated-code*//*@lineinfo:38^3*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class InIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public InIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    input_departmentNdx = findColumn("input_department");
    operaterNdx = findColumn("operater");
    operateTimeNdx = findColumn("operateTime");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
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
  public String input_department() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_departmentNdx);
  }
  private int input_departmentNdx;
  public String operater() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operaterNdx);
  }
  private int operaterNdx;
  public Date operateTime() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(operateTimeNdx);
  }
  private int operateTimeNdx;
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
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:49^10*/
	 
	 /**
		 * startDate  入库开始时间
		 * endDate	  入库结束时间
		 * supplierId 供应商Id
		 * formIdText 单据编号 以供单据号模糊查询使用
		 * branchId 门店ID 餐厅 物流中心 中央工厂
		 * 
		 * 功能：查询满足条件的入库单以及采购退货单，合并生成对账单。 入库单金额之和减去采购退货单之和的结果等于对账单金额的总数。
		 * 
		 */
		public List<InputHeader> queryInReturn(java.util.Date startDate, java.util.Date endDate, String supplierId,String formIdText,String branchId, int startRow, int endRow) 
				throws NoPrivilegeException,SQLException,NoConnection {
			Date sDate = SqlDateUtil.getSqlDate(startDate);
			Date eDate = SqlDateUtil.getSqlDate(endDate);
			
			String query = formQuery(supplierId,formIdText,branchId,"");
			String returnQuery =  returnFormQuery(supplierId,formIdText,branchId,"");
			
			DefaultContext myCtx = getDefaultContext();
			InIter headerIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:71^4*/

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
//  				                    SELECT
//  				                        *
//  				                    FROM
//  				                        (
//  				                            WITH
//  				                                inform AS
//  				                                (
//  				                                    SELECT
//  				                                    	h.INPUT_DEPARTMENT_ID,
//  				                                    	h.PROVIDER_ID,
//  				                                        h.FORM_ID,
//  				                                        h.FORM_REF_ID,
//  				                                        h.INPUT_DEPARTMENT,
//  				                                        h.INPUTER    operater,
//  				                                        h.INPUT_TIME operateTime,
//  				                                        h.PROVIDER,
//  				                                        h.FORM_NOTE,
//  				                                        NVL( h.ALL_PAY_AMT,0) ALL_PAY_AMT,
//  				                                        s1.STATUS
//  				                                    FROM
//  				                                    :Com_("D_T1_INPUT_HEADER") h
//  				                                    LEFT JOIN
//  				                                    :Com_("D_T0_FORM_STATUS") s
//  				                                    ON
//  				                                        h.FORM_ID = s.FORM_ID
//  				                                    LEFT JOIN
//  				                                    :Com_("D_T2_BRANCH") b
//  				                                    ON
//  				                                        h.PROVIDER_ID = b.BRANCH_ID
//  	                                        		LEFT JOIN
//  	                                    			:Com_("D_T0_FORM_STATUS") s1
//  	                	                        	ON
//  	                	                        		s1.FORM_ID = h.FORM_ID
//  	                                        		AND s1.IS_CURRENT = 2
//  				                                    WHERE
//  				                                    :query
//  				                                    AND b.BRANCH_TYPE = 'SUPPLIER'
//  				                                    AND s.STATUS IN('已审核')
//  				                                    AND s.IS_CURRENT = 1
//  		                                    		 AND (
//  			         				                            h.INPUT_TIME >= :sDate
//  			         				                        OR  :sDate IS NULL)
//  			         				                    AND (
//  			         				                            h.INPUT_TIME <= :eDate
//  			         				                        OR  :eDate IS NULL)
//  				                                    AND h.FORM_ID NOT IN
//  				                                                          ( SELECT DISTINCT
//  				                                                                  d.SUB_FORM_ID
//  				                                                                  FROM
//  				                                                                  :Com_("D_T1_STATEMENT_HEADER") h
//  				                                                                  LEFT JOIN
//  				                                                                  :Com_("D_T1_STATEMENT_DETAIL") d
//  				                                                                  ON
//  				                                                                      h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )
//  				                                )
//  				                            SELECT
//  				                                *
//  				                            FROM
//  				                                inform
//  				                            UNION ALL
//  				                            SELECT
//  				                            	i.INPUT_DEPARTMENT_ID,
//  				                                i.PROVIDER_ID,
//  				                                gh.FORM_ID,
//  				                                gh.FORM_REF_ID,
//  				                                i.INPUT_DEPARTMENT,
//  				                                gh.RETURNER    operater,
//  				                                gh.RETURN_TIME operateTime,
//  				                                i.PROVIDER ,
//  				                                gh.FORM_NOTE,
//  				                                NVL( -gh.ALL_PAY_AMT,0) ALL_PAY_AMT,
//  				                                s1.STATUS
//  				                            FROM
//  				                            :Com_("D_T1_RETURN_GOODS_HEADER") gh
//  				                            LEFT JOIN
//  				                            :Com_("D_T1_INPUT_HEADER") i
//  				                            ON
//  				                                gh.FORM_REF_ID = i.FORM_ID
//  				                            LEFT JOIN
//  				                            :Com_("D_T0_FORM_STATUS") s
//  				                            ON
//  				                                gh.FORM_ID = s.FORM_ID
//  	                                		LEFT JOIN
//  	                            			:Com_("D_T0_FORM_STATUS") s1
//  	        	                        	ON
//  	        	                        		s1.FORM_ID = gh.FORM_ID
//  	                                		AND s1.IS_CURRENT = 2
//  				                            WHERE
//  				                            :returnQuery
//  				                            AND (
//           				                            gh.RETURN_TIME >= :sDate
//           				                        OR  :sDate IS NULL)
//           				                    AND (
//           				                            gh.RETURN_TIME <= :eDate
//           				                        OR  :eDate IS NULL)
//  				                            AND    s.IS_CURRENT = 1
//  				                            AND s.STATUS IN( '已处理'
//  				                                             )
//  	                                         AND gh.FORM_ID NOT IN
//  	                                          ( SELECT DISTINCT
//  	                                                  d.SUB_FORM_ID
//  	                                                  FROM
//  	                                                  :Com_("D_T1_STATEMENT_HEADER") h
//  	                                                  LEFT JOIN
//  	                                                  :Com_("D_T1_STATEMENT_DETAIL") d
//  	                                                  ON
//  	                                                      h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL ))
//  				                    ORDER BY
//  				                        operateTime,
//  				                        FORM_ID) t
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
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  String __sJT_10 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_11 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_12 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_13 = Com_("D_T1_INPUT_HEADER");
  String __sJT_14 = Com_("D_T0_FORM_STATUS");
  String __sJT_15 = Com_("D_T0_FORM_STATUS");
  String __sJT_16 = returnQuery;
  Date __sJT_17 = sDate;
  Date __sJT_18 = sDate;
  Date __sJT_19 = eDate;
  Date __sJT_20 = eDate;
  String __sJT_21 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_22 = Com_("D_T1_STATEMENT_DETAIL");
  int __sJT_23 = endRow;
  int __sJT_24 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierStatement_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      __sJT_stmt.setDate(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setInt(23, __sJT_23);
      __sJT_stmt.setInt(24, __sJT_24);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new InIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:194^35*/
			List<InputHeader> headerLst = processInIter(headerIter);
			headerIter.close();
			closeDefaultContext(myCtx);
			return headerLst;
		}
		
	/**
	 * 入库单动态条件
	 */
	private String formQuery(String supplierId, String formIdText,String branchId,String status) {
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
		
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND h.INPUT_DEPARTMENT_ID = '" + branchId + "'";
		}
		return query;
	}
	
	/**
	 * 采购退货单动态条件
	 */
	private String returnFormQuery(String supplierId, String formIdText,String branchId,String status) {
		String query = " 1=1";
		if (!TextUtil.isEmpty(supplierId)) {
			query = query + " AND i.PROVIDER_ID = '" + supplierId + "'";
		}
		
		if (!TextUtil.isEmpty(formIdText)) {
			query = query + " AND upper(gh.FORM_ID) like '%" + formIdText + "%'";
		}
		
		if (!TextUtil.isEmpty(status)) {
			query = query + " AND s.STATUS in ('" + status.replaceAll(",", "'") + "')";
		}
		
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND i.INPUT_DEPARTMENT_ID = '" + branchId + "'";
		}
		return query;
	}
	
	/**
	 * 入库单以及采购退货单的总单数之和，以供分页
	 */
	public int inReturnCount(java.util.Date startDate, java.util.Date endDate, String supplierId,String formIdText,String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(supplierId,formIdText,branchId,"");
		String returnQuery =  returnFormQuery(supplierId,formIdText,branchId,"");
		
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:261^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (
//  		        WITH
//  		            inform AS
//  		            (
//  		                SELECT
//  		                    h.INPUT_DEPARTMENT_ID,
//  		                    h.PROVIDER_ID,
//  		                    h.FORM_ID,
//  		                    h.FORM_REF_ID,
//  		                    h.INPUT_DEPARTMENT,
//  		                    h.INPUTER    operater,
//  		                    h.INPUT_TIME operateTime,
//  		                    h.PROVIDER,
//  		                    h.FORM_NOTE,
//  		                    NVL( h.ALL_PAY_AMT,0) ALL_PAY_AMT,
//  		                    s.STATUS
//  		                FROM
//  		                :Com_("D_T1_INPUT_HEADER") h
//  		                LEFT JOIN
//  		                :Com_("D_T0_FORM_STATUS") s
//  		                ON
//  		                    h.FORM_ID = s.FORM_ID
//  		                LEFT JOIN
//  		                :Com_("D_T2_BRANCH") b
//  		                ON
//  		                    h.PROVIDER_ID = b.BRANCH_ID
//  		                WHERE
//  		                    :query
//  		                AND b.BRANCH_TYPE = 'SUPPLIER'
//  		                AND s.STATUS IN('已审核')
//  		                AND s.IS_CURRENT = 1
//  		                AND (
//  		                        h.INPUT_TIME >= :sDate
//  		                    OR  :sDate IS NULL)
//  		                AND (
//  		                        h.INPUT_TIME <= :eDate
//  		                    OR  :eDate IS NULL)
//  		                AND s.FORM_ID NOT IN
//  		                                      ( SELECT DISTINCT
//                                                        d.SUB_FORM_ID
//                                                        FROM
//                                                        :Com_("D_T1_STATEMENT_HEADER") h
//                                                        LEFT JOIN
//                                                        :Com_("D_T1_STATEMENT_DETAIL") d
//                                                        ON
//                                                            h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL)
//  		            )
//  		        SELECT
//  		            *
//  		        FROM
//  		            inform
//  		        UNION ALL
//  		        SELECT
//              	i.INPUT_DEPARTMENT_ID,
//                  i.PROVIDER_ID,
//                  gh.FORM_ID,
//                  gh.FORM_REF_ID,
//                  i.INPUT_DEPARTMENT,
//                  gh.RETURNER    operater,
//                  gh.RETURN_TIME operateTime,
//                  i.PROVIDER ,
//                  gh.FORM_NOTE,
//                  NVL( -gh.ALL_PAY_AMT,0) ALL_PAY_AMT,
//                  s1.STATUS
//              FROM
//              :Com_("D_T1_RETURN_GOODS_HEADER") gh
//              LEFT JOIN
//              :Com_("D_T1_INPUT_HEADER") i
//              ON
//                  gh.FORM_REF_ID = i.FORM_ID
//              LEFT JOIN
//              :Com_("D_T0_FORM_STATUS") s
//              ON
//                  gh.FORM_ID = s.FORM_ID
//      		LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s1
//          	ON
//          		s1.FORM_ID = gh.FORM_ID
//      		AND s1.IS_CURRENT = 2
//              WHERE
//              :returnQuery
//              AND (
//                       gh.RETURN_TIME >= :sDate
//                   OR  :sDate IS NULL)
//               AND (
//                       gh.RETURN_TIME <= :eDate
//                   OR  :eDate IS NULL)
//              AND    s.IS_CURRENT = 1
//              AND s.STATUS IN( '已处理'
//                               )
//               AND gh.FORM_ID NOT IN
//                ( SELECT DISTINCT
//                        d.SUB_FORM_ID
//                        FROM
//                        :Com_("D_T1_STATEMENT_HEADER") h
//                        LEFT JOIN
//                        :Com_("D_T1_STATEMENT_DETAIL") d
//                        ON
//                            h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_10 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_11 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_12 = Com_("D_T1_INPUT_HEADER");
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierStatement_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:365^83*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	/**
	 * 对账单总金额：入库单总金额之和减去采购退货单总金额之和
	 */
	public Double inReturnSum(java.util.Date startDate, java.util.Date endDate, String supplierId,String formIdText,String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(supplierId,formIdText,branchId,"");
		String returnQuery =  returnFormQuery(supplierId,formIdText,branchId,"");
		
		DefaultContext myCtx = getDefaultContext();
		Double total = 0.0;
		/*@lineinfo:generated-code*//*@lineinfo:383^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    sum(ALL_PAY_AMT)
//  		
//  		FROM
//  		    (
//  		        WITH
//  		            inform AS
//  		            (
//  		                SELECT
//  		                    h.INPUT_DEPARTMENT_ID,
//  		                    h.PROVIDER_ID,
//  		                    h.FORM_ID,
//  		                    h.FORM_REF_ID,
//  		                    h.INPUT_DEPARTMENT,
//  		                    h.INPUTER    operater,
//  		                    h.INPUT_TIME operateTime,
//  		                    h.PROVIDER,
//  		                    h.FORM_NOTE,
//  		                    NVL( h.ALL_PAY_AMT,0) ALL_PAY_AMT,
//  		                    s.STATUS
//  		                FROM
//  		                :Com_("D_T1_INPUT_HEADER") h
//  		                LEFT JOIN
//  		                :Com_("D_T0_FORM_STATUS") s
//  		                ON
//  		                    h.FORM_ID = s.FORM_ID
//  		                LEFT JOIN
//  		                :Com_("D_T2_BRANCH") b
//  		                ON
//  		                    h.PROVIDER_ID = b.BRANCH_ID
//  		                WHERE
//  		                    :query
//  		                AND b.BRANCH_TYPE = 'SUPPLIER'
//  		                AND s.STATUS IN('已审核')
//  		                AND s.IS_CURRENT = 1
//  		                AND (
//  		                        h.INPUT_TIME >= :sDate
//  		                    OR  :sDate IS NULL)
//  		                AND (
//  		                        h.INPUT_TIME <= :eDate
//  		                    OR  :eDate IS NULL)
//  		                AND s.FORM_ID NOT IN
//  		                                      ( SELECT DISTINCT
//                                                        d.SUB_FORM_ID
//                                                        FROM
//                                                        :Com_("D_T1_STATEMENT_HEADER") h
//                                                        LEFT JOIN
//                                                        :Com_("D_T1_STATEMENT_DETAIL") d
//                                                        ON
//                                                            h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL)
//  		            )
//  		        SELECT
//  		            *
//  		        FROM
//  		            inform
//  		        UNION ALL
//  		        SELECT
//              	i.INPUT_DEPARTMENT_ID,
//                  i.PROVIDER_ID,
//                  gh.FORM_ID,
//                  gh.FORM_REF_ID,
//                  i.INPUT_DEPARTMENT,
//                  gh.RETURNER    operater,
//                  gh.RETURN_TIME operateTime,
//                  i.PROVIDER ,
//                  gh.FORM_NOTE,
//                  NVL( -gh.ALL_PAY_AMT,0) ALL_PAY_AMT,
//                  s1.STATUS
//              FROM
//              :Com_("D_T1_RETURN_GOODS_HEADER") gh
//              LEFT JOIN
//              :Com_("D_T1_INPUT_HEADER") i
//              ON
//                  gh.FORM_REF_ID = i.FORM_ID
//              LEFT JOIN
//              :Com_("D_T0_FORM_STATUS") s
//              ON
//                  gh.FORM_ID = s.FORM_ID
//      		LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s1
//          	ON
//          		s1.FORM_ID = gh.FORM_ID
//      		AND s1.IS_CURRENT = 2
//              WHERE
//              :returnQuery
//              AND (
//                       gh.RETURN_TIME >= :sDate
//                   OR  :sDate IS NULL)
//               AND (
//                       gh.RETURN_TIME <= :eDate
//                   OR  :eDate IS NULL)
//              AND    s.IS_CURRENT = 1
//              AND s.STATUS IN( '已处理'
//                               )
//               AND gh.FORM_ID NOT IN
//                ( SELECT DISTINCT
//                        d.SUB_FORM_ID
//                        FROM
//                        :Com_("D_T1_STATEMENT_HEADER") h
//                        LEFT JOIN
//                        :Com_("D_T1_STATEMENT_DETAIL") d
//                        ON
//                            h.FORM_ID = d.FORM_ID WHERE d.SUB_FORM_ID is not NULL )) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = Com_("D_T1_STATEMENT_HEADER");
  String __sJT_10 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_11 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_12 = Com_("D_T1_INPUT_HEADER");
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierStatement_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:487^83*/
			closeDefaultContext(myCtx);
			return total;
	}

	private List<InputHeader> processInIter(InIter headerIter) 
			throws SQLException {
		List<InputHeader> headerLst = new ArrayList<InputHeader>();
		while(headerIter.next()) {
			InputHeader header = new InputHeader();
			header.setFormId(headerIter.form_id());
			header.setFormRefId(headerIter.form_ref_id());
			header.setInputDepartment(headerIter.input_department());
			header.setInputer(headerIter.operater());
            header.setInputTime(SqlDateUtil.getUtilDate(headerIter.operateTime()));
			header.setProvider("["+headerIter.provider_id()+"]"+headerIter.provider());
			header.setFormNote(headerIter.form_note());
			header.setAllPayAmt(headerIter.all_pay_amt());
			
			header.setStatus(headerIter.status());

			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class SupplierStatement_SJProfileKeys 
{
  private static SupplierStatement_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SupplierStatement_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SupplierStatement_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.supplier.SupplierStatement_SJProfile0");
  }
}
