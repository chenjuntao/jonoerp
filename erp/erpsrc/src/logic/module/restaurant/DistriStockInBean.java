/*@lineinfo:filename=DistriStockInBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 17, 2015 by liyzh
 * Last edited on Sep 17, 2015 by liyzh
 * 
 * 说明： 餐厅配送入库，或物流中心半成品入库（由央厂配送）
 */
package logic.module.restaurant;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ShippingHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchType;

public class DistriStockInBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(DistriStockInBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

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
    form_typeNdx = findColumn("form_type");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    out_storage_idNdx = findColumn("out_storage_id");
    out_storageNdx = findColumn("out_storage");
    receive_timeNdx = findColumn("receive_time");
    requester_idNdx = findColumn("requester_id");
    requesterNdx = findColumn("requester");
    request_addressNdx = findColumn("request_address");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    form_statusNdx = findColumn("form_status");
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
  public String form_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_typeNdx);
  }
  private int form_typeNdx;
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
  public String out_storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storage_idNdx);
  }
  private int out_storage_idNdx;
  public String out_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storageNdx);
  }
  private int out_storageNdx;
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
  public String requester_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requester_idNdx);
  }
  private int requester_idNdx;
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
  public String form_maker_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_maker_idNdx);
  }
  private int form_maker_idNdx;
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
  public Date form_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_time_actualNdx);
  }
  private int form_time_actualNdx;
  public String auditor_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditor_idNdx);
  }
  private int auditor_idNdx;
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
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:58^43*/
	
	/*@lineinfo:generated-code*//*@lineinfo:60^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class CountIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CountIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    totalNdx = findColumn("total");
  }
  public int total() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(totalNdx);
  }
  private int totalNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:61^13*/
	

	public int count(java.util.Date startDate, java.util.Date endDate, String requesterId)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		String query = " NVL(h.INPUT_STATUS, 'null') != '" + BillStatus.STORED_CN + "'";
		
		DefaultContext myCtx = getDefaultContext();
		CountIter countIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:73^3*/

//  ************************************************************
//  #sql [myCtx] countIter = { WITH
//      	    a AS
//      	    (
//      	        SELECT DISTINCT
//      	            h.form_id,
//      	            p.form_id                        form_id2,
//      	            DECODE(s.STATUS,'未审核',0,'已审核',1) flag
//      	        FROM
//      	           :Com_("D_T1_SHIPPING_HEADER") h
//      	        INNER JOIN
//      	           :Com_("D_T1_PICKING_REF") p
//      	        ON
//      	            p.FORM_REF_ID = h.FORM_ID
//      	        INNER JOIN
//      	           :Com_("D_T0_FORM_STATUS") s
//      	        ON
//      	            s.FORM_ID = p.FORM_ID
//      	        AND s.IS_CURRENT = 1
//      	        WHERE
//      	        :query
//      			AND h.REQUESTER_ID = :requesterId
//  				AND (
//      			        h.RECEIVE_TIME >= :sDate
//      			    OR  :sDate IS NULL)
//      			AND (
//      			        h.RECEIVE_TIME <= :eDate
//      			    OR  :eDate IS NULL)
//      	        ), b as(
//      	SELECT
//      	    *
//      	FROM
//      	    (
//      	        SELECT
//      	            a.*,
//      	            row_number() over(partition BY a.form_id ORDER BY a.flag ) rn
//      	        FROM
//      	            a )
//      	WHERE
//      	    rn=1
//      	),c as(
//      	SELECT
//      	  sh. * ,
//      	   '' FORM_STATUS 
//      	FROM
//      	       :Com_("D_T1_SHIPPING_HEADER") sh
//      	INNER JOIN
//      	      b
//      	ON 
//      	   b.FORM_ID= sh.FORM_ID
//      	WHERE
//      	       b.FLAG = 1
//  	   union all 
//  	   select 
//      	   s. *,
//      	   '' FORM_STATUS
//      	FROM
//      	       :Com_("D_T1_SHIPPING_HEADER") s
//          WHERE  
//      	        s.AUDIT_TIME is not null 
//  	        AND s.FORM_TYPE = 'INNER_CROSS'
//  			AND s.REQUESTER_ID = :requesterId
//  			AND (
//  					s.RECEIVE_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  					s.RECEIVE_TIME <= :eDate
//  			    OR  :eDate IS NULL))
//  		    SELECT
//  			    COUNT(*) total
//  			FROM
//  			    c
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_PICKING_REF");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = query;
  String __sJT_5 = requesterId;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  String __sJT_10 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_11 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_12 = requesterId;
  Date __sJT_13 = sDate;
  Date __sJT_14 = sDate;
  Date __sJT_15 = eDate;
  Date __sJT_16 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DistriStockInBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_stmt.setDate(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      countIter = new CountIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:144^3*/
		
		int total = 0;
		while(countIter.next()) {
			total= countIter.total();
		}
		countIter.close();
		closeDefaultContext(myCtx);
		return total;
	}
	
	public int countByAuditTime(java.util.Date startDate, java.util.Date endDate, String requesterId, String branchType)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		String query = " NVL(h.INPUT_STATUS, 'null') != '" + BillStatus.STORED_CN + "'";
		query += formQuery(branchType);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:165^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*)
//  			
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    :query
//  			AND h.REQUESTER_ID = :requesterId
//  			AND (
//  			        h.AUDIT_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.AUDIT_TIME <= :eDate
//  			    OR  :eDate IS NULL)
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
  String __sJT_3 = query;
  String __sJT_4 = requesterId;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DistriStockInBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:186^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public int inputCount(java.util.Date startDate, java.util.Date endDate, String requesterId, String branchType)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		String query = "h.INPUT_STATUS= '" + BillStatus.STORED_CN + "'";
		query += formQuery(branchType);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:201^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*)
//  			
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    :query
//  			AND h.REQUESTER_ID = :requesterId
//  			AND (
//  			        h.RECEIVE_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.RECEIVE_TIME <= :eDate
//  			    OR  :eDate IS NULL)
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
  String __sJT_3 = query;
  String __sJT_4 = requesterId;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DistriStockInBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:222^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	public List<ShippingHeader> inputQuery(java.util.Date startDate, java.util.Date endDate, String requesterId, String branchType, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		String query = " h.INPUT_STATUS = '" + BillStatus.STORED_CN + "'";
		query += formQuery(branchType);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:236^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                		SELECT
//  			        			    h.*,
//  			        			    s.STATUS AS FORM_STATUS
//  			        			FROM
//  			        			:Com_("D_T1_SHIPPING_HEADER") h
//  			        			LEFT JOIN
//  			        			:Com_("D_T0_FORM_STATUS") s
//  			        			ON
//  			        			    s.FORM_ID = h.FORM_ID
//  			        			AND s.IS_CURRENT = 1
//  			        			WHERE
//  			        			    :query
//  			        			AND h.REQUESTER_ID = :requesterId
//  			        			AND (
//  			        			        h.RECEIVE_TIME >= :sDate
//  			        			    OR  :sDate IS NULL)
//  			        			AND (
//  			        			        h.RECEIVE_TIME <= :eDate
//  			        			    OR  :eDate IS NULL)
//  			        			ORDER BY
//  			        			    h.FORM_ID DESC ) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = requesterId;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  int __sJT_9 = endRow;
  int __sJT_10 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DistriStockInBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:271^4*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	private String formQuery(String branchType) {
		String query = "";
		if (BranchType.RESTAURANT.equals(branchType)) {
			query = " AND NVL( s.STATUS, 'null') = '" + BillStatus.AUDITED_CN + "'";
		}
		return query;
	}
	
	public List<ShippingHeader> query(java.util.Date startDate, java.util.Date endDate, String requesterId, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		String query = " NVL(h.INPUT_STATUS, 'null') != '" + BillStatus.STORED_CN + "'";

		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:294^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (WITH
//  			                	    a AS
//  			                	    (
//  			                	        SELECT DISTINCT
//  			                	            h.form_id,
//  			                	            p.form_id                        form_id2,
//  			                	            DECODE(s.STATUS,'未审核',0,'已审核',1) flag
//  			                	        FROM
//  			                	           :Com_("D_T1_SHIPPING_HEADER") h
//  			                	        INNER JOIN
//  			                	           :Com_("D_T1_PICKING_REF") p
//  			                	        ON
//  			                	            p.FORM_REF_ID = h.FORM_ID
//  			                	        INNER JOIN
//  			                	           :Com_("D_T0_FORM_STATUS") s
//  			                	        ON
//  			                	            s.FORM_ID = p.FORM_ID
//  			                	        AND s.IS_CURRENT = 1
//  			                	        WHERE
//  			                	        :query
//  					        			AND h.REQUESTER_ID = :requesterId
//  			        					AND (
//  					        			        h.RECEIVE_TIME >= :sDate
//  					        			    OR  :sDate IS NULL)
//  					        			AND (
//  					        			        h.RECEIVE_TIME <= :eDate
//  					        			    OR  :eDate IS NULL)
//  			                	        ), b as(
//  			                	SELECT
//  			                	    *
//  			                	FROM
//  			                	    (
//  			                	        SELECT
//  			                	            a.*,
//  			                	            row_number() over(partition BY a.form_id ORDER BY a.flag ) rn
//  			                	        FROM
//  			                	            a )
//  			                	WHERE
//  			                	    rn=1
//  			                	)
//  
//  			                	SELECT
//  			                	  sh. * ,
//  			                	   '' FORM_STATUS 
//  			                	FROM
//  			                	       :Com_("D_T1_SHIPPING_HEADER") sh
//  			                	INNER JOIN
//  			                	      b
//  			                	ON 
//  			                	   b.FORM_ID= sh.FORM_ID
//  			                	WHERE
//  			                	       b.FLAG = 1
//  		                	   union all 
//  		                	   select 
//  			                	   s. *,
//  			                	   '' FORM_STATUS
//  			                	FROM
//  			                	       :Com_("D_T1_SHIPPING_HEADER") s
//  	                	        WHERE  
//  			                	        s.AUDIT_TIME is not null 
//  			                	        AND s.INPUT_TIME IS NULL
//  			                	        AND s.FORM_TYPE = 'INNER_CROSS'
//  		        			AND s.REQUESTER_ID = :requesterId
//  		        			AND (
//  		        					s.RECEIVE_TIME >= :sDate
//  		        			    OR  :sDate IS NULL)
//  		        			AND (
//  		        					s.RECEIVE_TIME <= :eDate
//  		        			    OR  :eDate IS NULL)) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_PICKING_REF");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = query;
  String __sJT_5 = requesterId;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  String __sJT_10 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_11 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_12 = requesterId;
  Date __sJT_13 = sDate;
  Date __sJT_14 = sDate;
  Date __sJT_15 = eDate;
  Date __sJT_16 = eDate;
  int __sJT_17 = endRow;
  int __sJT_18 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DistriStockInBean_SJProfileKeys.getKey(0), 4);
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
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_stmt.setDate(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setInt(17, __sJT_17);
      __sJT_stmt.setInt(18, __sJT_18);
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

/*@lineinfo:user-code*//*@lineinfo:376^4*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ShippingHeader> query(java.util.Date startDate, java.util.Date endDate, String requesterId,String itemName, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		String query = " NVL(h.INPUT_STATUS, 'null') != '" + BillStatus.STORED_CN + "'";

		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:392^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (WITH   
//  			                		header AS(
//  		            			        SELECT DISTINCT
//  		            			            t.FORM_ID
//  		            			        FROM
//  		            			        :Com_("D_T1_SHIPPING_DETAIL") t
//  		            			        WHERE
//  		            			            1=1
//  		            			        AND (
//  		            			                t.ITEM_ID = :itemName
//  		            			            OR  t.ITEM_NAME = :itemName)
//  		            			        ORDER BY
//  		            			            t.FORM_ID
//  			                				),
//  	                				a AS
//  			                	    (
//  			                	        SELECT DISTINCT
//  			                	            h.form_id,
//  			                	            p.form_id                        form_id2,
//  			                	            DECODE(s.STATUS,'未审核',0,'已审核',1) flag
//  			                	        FROM
//  			                	           :Com_("D_T1_SHIPPING_HEADER") h
//  		                	            INNER JOIN
//  		                	            	header
//  			                	        ON
//  			                	        	header.FORM_ID = h.FORM_ID
//  			                	        INNER JOIN
//  			                	           :Com_("D_T1_PICKING_REF") p
//  			                	        ON
//  			                	            p.FORM_REF_ID = h.FORM_ID
//  			                	        INNER JOIN
//  			                	           :Com_("D_T0_FORM_STATUS") s
//  			                	        ON
//  			                	            s.FORM_ID = p.FORM_ID
//  			                	        AND s.IS_CURRENT = 1
//  			                	        WHERE
//  			                	        :query
//  					        			AND h.REQUESTER_ID = :requesterId
//  			        					AND (
//  					        			        h.RECEIVE_TIME >= :sDate
//  					        			    OR  :sDate IS NULL)
//  					        			AND (
//  					        			        h.RECEIVE_TIME <= :eDate
//  					        			    OR  :eDate IS NULL)
//  			                	        ), b as(
//  			                	SELECT
//  			                	    *
//  			                	FROM
//  			                	    (
//  			                	        SELECT
//  			                	            a.*,
//  			                	            row_number() over(partition BY a.form_id ORDER BY a.flag ) rn
//  			                	        FROM
//  			                	            a )
//  			                	WHERE
//  			                	    rn=1
//  			                	)
//  
//  			                	SELECT
//  			                	  sh. * ,
//  			                	   '' FORM_STATUS 
//  			                	FROM
//  			                	       :Com_("D_T1_SHIPPING_HEADER") sh
//  			                	INNER JOIN
//  			                	      b
//  			                	ON 
//  			                	   b.FORM_ID= sh.FORM_ID
//  			                	WHERE
//  			                	       b.FLAG = 1
//  		                	   union all 
//  		                	   select 
//  			                	   s. *,
//  			                	   '' FORM_STATUS
//  			                	FROM
//  			                	       :Com_("D_T1_SHIPPING_HEADER") s
//  	                	        WHERE  
//  			                	        s.AUDIT_TIME is not null 
//  			                	        AND s.FORM_TYPE = 'INNER_CROSS'
//  		        			AND s.REQUESTER_ID = :requesterId
//  		        			AND (
//  		        					s.RECEIVE_TIME >= :sDate
//  		        			    OR  :sDate IS NULL)
//  		        			AND (
//  		        					s.RECEIVE_TIME <= :eDate
//  		        			    OR  :eDate IS NULL)) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = Com_("D_T1_PICKING_REF");
  String __sJT_6 = Com_("D_T0_FORM_STATUS");
  String __sJT_7 = query;
  String __sJT_8 = requesterId;
  Date __sJT_9 = sDate;
  Date __sJT_10 = sDate;
  Date __sJT_11 = eDate;
  Date __sJT_12 = eDate;
  String __sJT_13 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_14 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_15 = requesterId;
  Date __sJT_16 = sDate;
  Date __sJT_17 = sDate;
  Date __sJT_18 = eDate;
  Date __sJT_19 = eDate;
  int __sJT_20 = endRow;
  int __sJT_21 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DistriStockInBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      __sJT_stmt.setInt(20, __sJT_20);
      __sJT_stmt.setInt(21, __sJT_21);
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

/*@lineinfo:user-code*//*@lineinfo:490^4*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ShippingHeader> queryByAuditTime(java.util.Date startDate, java.util.Date endDate, String requesterId, String branchType, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		String query = " NVL(h.INPUT_STATUS, 'null') != '" + BillStatus.STORED_CN + "'";
		query += formQuery(branchType);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:507^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                		SELECT
//  			        			    h.*,
//  			        			    s.STATUS AS FORM_STATUS
//  			        			FROM
//  			        			:Com_("D_T1_SHIPPING_HEADER") h
//  			        			LEFT JOIN
//  			        			:Com_("D_T0_FORM_STATUS") s
//  			        			ON
//  			        			    s.FORM_ID = h.FORM_ID
//  			        			AND s.IS_CURRENT = 1
//  			        			WHERE
//  			        			    :query
//  			        			AND h.REQUESTER_ID = :requesterId
//  			        			AND (
//  			        			        h.AUDIT_TIME >= :sDate
//  			        			    OR  :sDate IS NULL)
//  			        			AND (
//  			        			        h.AUDIT_TIME <= :eDate
//  			        			    OR  :eDate IS NULL)
//  			        			ORDER BY
//  			        			    h.FORM_ID DESC ) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = requesterId;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  int __sJT_9 = endRow;
  int __sJT_10 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DistriStockInBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:542^4*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private List<ShippingHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ShippingHeader> headerLst = new ArrayList<ShippingHeader>();
		while(headerIter.next()) {
			ShippingHeader header = new ShippingHeader();
			header.setFormId(headerIter.form_id());
			header.setFormRefId(headerIter.form_ref_id());
			header.setFormType(headerIter.form_type());
			header.setProviderId(headerIter.provider_id());
			header.setProvider(headerIter.provider());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setRequesterId(headerIter.requester_id());
			header.setRequester(headerIter.requester());
			header.setRequestAddress(headerIter.request_address());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setOutStorageId(headerIter.out_storage_id());
			header.setOutStorage(headerIter.out_storage());
			header.setFormStatus(headerIter.form_status());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class DistriStockInBean_SJProfileKeys 
{
  private static DistriStockInBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DistriStockInBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DistriStockInBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.DistriStockInBean_SJProfile0");
  }
}
