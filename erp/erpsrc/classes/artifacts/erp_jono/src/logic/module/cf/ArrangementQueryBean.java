/*@lineinfo:filename=ArrangementQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 23, 2015 by liyzh
 * Last edited on Apr 23, 2015 by liyzh
 * 
 * 说明： 中央工厂生产计划/排程单表头查询
 */
package logic.module.cf;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ArrangmentHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchCode;

public class ArrangementQueryBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ArrangementQueryBean.class);
	
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
    ref_idsNdx = findColumn("ref_ids");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    form_statusNdx = findColumn("form_status");
    purchase_statusNdx = findColumn("purchase_status");
    workShopNdx = findColumn("workShop");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String ref_ids() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ref_idsNdx);
  }
  private int ref_idsNdx;
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
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
  public String purchase_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(purchase_statusNdx);
  }
  private int purchase_statusNdx;
  public String workShop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workShopNdx);
  }
  private int workShopNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^62*/

	public int count(java.util.Date startDate, java.util.Date endDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:51^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_ARRENGMENT_HEADER") h
//  			WHERE
//  				(h.FORM_TIME >= :sDate or :sDate is null)
//  			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  Date __sJT_2 = sDate;
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  Date __sJT_5 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangementQueryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:59^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public List<ArrangmentHeader> query(java.util.Date startDate, java.util.Date endDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:71^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				'' workShop,
//  			    h.*, s.STATUS AS FORM_STATUS,
//  			    a.ref_ids
//  			FROM
//  			    (
//  			        SELECT
//  			            form_id,
//  			            LISTAGG(REF_FORM_ID, ',') WITHIN GROUP (ORDER BY REF_FORM_ID) AS ref_ids
//  			        FROM
//  			            (
//  			                SELECT
//  			                    h.FORM_ID,
//  			                    r.REF_FORM_ID
//  			                FROM
//  			                :Com_("D_T1_ARRENGMENT_HEADER") h
//  			                LEFT JOIN
//  			                :Com_("D_T1_COLLECT_REF_FORM") r
//  			                ON
//  			                    r.COLLECT_FORM_ID = h.FORM_ID
//  	                		WHERE 
//  	            				(h.FORM_TIME >= :sDate or :sDate is null)
//  	            			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  			                GROUP BY
//  			                    h.FORM_ID,
//  			                    r.REF_FORM_ID)
//  			        GROUP BY
//  			            form_id) a
//  			INNER JOIN
//  			:Com_("D_T1_ARRENGMENT_HEADER") h
//  			ON
//  			    h.form_id = a.form_id
//          	LEFT JOIN
//          	:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T1_COLLECT_REF_FORM");
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  String __sJT_7 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_8 = Com_("D_T0_FORM_STATUS");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangementQueryBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
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

/*@lineinfo:user-code*//*@lineinfo:109^3*/
		List<ArrangmentHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ArrangmentHeader> query(java.util.Date startDate, java.util.Date endDate, int startRow, int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:123^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  		    FROM
//  		        (
//  		            SELECT
//  		                t.*,
//  		                ROWNUM rowNumber
//  		            FROM
//  		                (
//  		            			SELECT DISTINCT
//  		                        d.WORKSHOP,
//  		        			    h.*, s.STATUS AS FORM_STATUS,
//  		        			    a.ref_ids
//  		        			FROM
//  		        			    (
//  		        			        SELECT
//  		        			            form_id,
//  		        			            LISTAGG(REF_FORM_ID, ',') WITHIN GROUP (ORDER BY REF_FORM_ID) AS ref_ids
//  		        			        FROM
//  		        			            (
//  		        			                SELECT
//  		        			                    h.FORM_ID,
//  		        			                    r.REF_FORM_ID
//  		        			                FROM
//  		        			                :Com_("D_T1_ARRENGMENT_HEADER") h
//  		        			                LEFT JOIN
//  		        			                :Com_("D_T1_COLLECT_REF_FORM") r
//  		        			                ON
//  		        			                    r.COLLECT_FORM_ID = h.FORM_ID
//  		        	                		WHERE 
//  		        	            				(h.FORM_TIME >= :sDate or :sDate is null)
//  		        	            			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  		        			                GROUP BY
//  		        			                    h.FORM_ID,
//  		        			                    r.REF_FORM_ID)
//  		        			        GROUP BY
//  		        			            form_id) a
//  		        			INNER JOIN
//  		        			:Com_("D_T1_ARRENGMENT_HEADER") h
//  		        			ON
//  		        			    h.form_id = a.form_id
//      			    		 INNER JOIN
//      			    		 :Com_("D_T1_ARRENGMENT_DETAIL") d
//      		                ON
//      		                    h.FORM_ID=d.FORM_ID
//  		                	LEFT JOIN
//  		                	:Com_("D_T0_FORM_STATUS") s
//  		        			ON
//  		        			    s.FORM_ID = h.FORM_ID
//  		        			AND s.IS_CURRENT = 1
//  		        		) t
//  		            WHERE
//  		                ROWNUM < :endRow)
//  		    WHERE
//  		        rowNumber >= :startRow
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T1_COLLECT_REF_FORM");
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  String __sJT_7 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_8 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_9 = Com_("D_T0_FORM_STATUS");
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangementQueryBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:179^3*/
		List<ArrangmentHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private List<ArrangmentHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ArrangmentHeader> headerLst = new ArrayList<ArrangmentHeader>();
		while(headerIter.next()) {
			ArrangmentHeader header = new ArrangmentHeader();
			header.setWorkShop(headerIter.workShop());
			header.setFormId(headerIter.form_id());
			header.setFormRefId(headerIter.ref_ids());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setFormStatus(headerIter.form_status());
			header.setPurchaseStatus(headerIter.purchase_status());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ArrangementQueryBean_SJProfileKeys 
{
  private static ArrangementQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ArrangementQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ArrangementQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.cf.ArrangementQueryBean_SJProfile0");
  }
}
