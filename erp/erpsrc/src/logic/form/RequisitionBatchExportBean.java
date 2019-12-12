/*@lineinfo:filename=RequisitionBatchExportBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 11 10:31:07 CST 2014 by pw
 * Last edited on Tue Nov 11 10:31:07 CST 2014 by pw
 * 
 * comment: 中央工厂生产领料单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.RequisitionHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class RequisitionBatchExportBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequisitionBatchExportBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

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
    workshopNdx = findColumn("workshop");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_countNdx = findColumn("item_count");
  }
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
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
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^3*/
	
	
	public Integer count(java.util.Date startDate, java.util.Date endDate, String workshop) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		Integer total = null;
		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (SELECT
//  		    	    h.WORKSHOP,
//  		    	    d.ITEM_ID,
//  		    	    m.ITEM_NAME,
//  		    	    m.ITEM_DIMENSION
//  		    	FROM
//  		    	:Com_("D_T1_REQUISITION_HEADER") h
//  		    	INNER JOIN
//  		    	:Com_("D_T0_FORM_STATUS") s
//  		    	ON
//  		    	    h.FORM_ID = s.FORM_ID
//  		    	AND s.IS_CURRENT = 1
//  		    	AND s.STATUS = '未审核'
//  		    	INNER JOIN
//  		    	:Com_("D_T1_REQUISITION_DETAIL") d
//  		    	ON
//  		    	    h.FORM_ID = d.FORM_ID
//  		    	INNER JOIN
//  		    	:Com_("D_T2_ITEM_META") m
//  		    	ON
//  		    	    d.ITEM_ID = m.ITEM_ID
//  		    	WHERE
//  		    	    h.FORM_type = 'produce'
//  		    	AND h.WORKSHOP = :workshop
//  		    	AND (
//  		    	        h.FORM_TIME >= :sDate
//  		    	    OR  :sDate IS NULL)
//  		    	AND (
//  		    	        h.FORM_TIME <= :eDate
//  		    	    OR  :eDate IS NULL)
//  		    	GROUP BY
//  		    	    h.WORKSHOP,
//  		    	    d.ITEM_ID,
//  		    	    m.ITEM_NAME,
//  		    	    m.ITEM_DIMENSION) a };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = workshop;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionBatchExportBean_SJProfileKeys.getKey(0), 0);
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
    total = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:96^31*/
		closeDefaultContext(myCtx);
		
		total = total==null?0:total;
		return total;
	}
	
	
	public List<Map> query(java.util.Date startDate, java.util.Date endDate, String workshop)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:111^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.WORKSHOP,
//  			    d.ITEM_ID,
//  			    m.ITEM_NAME,
//  			    m.ITEM_DIMENSION,
//  			    ROUND(SUM(d.ITEM_COUNT),2)item_count
//  			FROM
//  			:Com_("D_T1_REQUISITION_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			AND s.STATUS = '未审核'
//  			INNER JOIN
//  			:Com_("D_T1_REQUISITION_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.FORM_ID
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    d.ITEM_ID = m.ITEM_ID
//  			WHERE
//  			    h.FORM_type = 'produce'
//  			AND h.WORKSHOP = :workshop
//  			AND (
//  			        h.FORM_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.FORM_TIME <= :eDate
//  			    OR  :eDate IS NULL)
//  			GROUP BY
//  			    h.WORKSHOP,
//  			    d.ITEM_ID,
//  			    m.ITEM_NAME,
//  			    m.ITEM_DIMENSION
//  			ORDER BY
//  			    ROUND(SUM(d.ITEM_COUNT),2)DESC,
//  			    ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = workshop;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionBatchExportBean_SJProfileKeys.getKey(0), 1);
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
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:150^15*/
		
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		while(headerIter.next()) {
			Map headerMap = new HashMap();
			headerMap.put("workshop",headerIter.workshop());
			headerMap.put("item_id",headerIter.item_id());
			headerMap.put("item_name",headerIter.item_name());
			headerMap.put("item_dimension",headerIter.item_dimension());
			headerMap.put("item_count",headerIter.item_count());
			headerLst.add(headerMap);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RequisitionBatchExportBean_SJProfileKeys 
{
  private static RequisitionBatchExportBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequisitionBatchExportBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequisitionBatchExportBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.RequisitionBatchExportBean_SJProfile0");
  }
}
