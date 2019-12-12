/*@lineinfo:filename=RequisitionDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 11 16:14:06 CST 2014 by pw
 * Last edited on Tue Nov 11 16:14:06 CST 2014 by pw
 * 
 * comment: 中央工厂生产领料单表明细
 */
package logic.reportmanage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.RequisitionDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class RequisitionDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequisitionDetailBean.class);
	
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
    form_typeNdx = findColumn("form_type");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    form_makerNdx = findColumn("form_maker");
    form_noteNdx = findColumn("form_note");
    form_timeNdx = findColumn("form_time");
    receive_countNdx = findColumn("receive_count");
    workshopNdx = findColumn("workshop");
    form_idNdx = findColumn("form_id");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_countNdx = findColumn("item_count");
    different_countNdx = findColumn("different_count");
    expired_timeNdx = findColumn("expired_time");
  }
  public String form_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_typeNdx);
  }
  private int form_typeNdx;
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
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public String form_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_makerNdx);
  }
  private int form_makerNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^21*/
	
	/*@lineinfo:generated-code*//*@lineinfo:49^2*/

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
    receive_countNdx = findColumn("receive_count");
  }
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:50^24*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_type", "String");
		map.put("item_name", "String");
		map.put("item_id", "String");
		map.put("receive_count", "Number");
		map.put("workshop","String");
		map.put("storage_id", "String");
		map.put("storage", "String");
		map.put("form_maker", "String");
		map.put("form_note", "String");
		map.put("form_time", "Date");
		map.put("form_id", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("item_count", "Number");
		map.put("different_count", "Number");
		map.put("expired_time", "Date");
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_type", "h");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("receive_count", "d");
		map.put("workshop","h");
		map.put("storage_id", "h");
		map.put("storage", "h");
		map.put("form_maker", "h");
		map.put("form_note", "h");
		map.put("form_time", "h");
		map.put("form_id", "d");
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		map.put("item_count", "d");
		map.put("different_count", "d");
		map.put("expired_time", "d");
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		
        /*@lineinfo:generated-code*//*@lineinfo:99^9*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//              COUNT(*)
//          
//          FROM
//              :Com_("D_T1_REQUISITION_HEADER") h
//          INNER JOIN
//              :Com_("D_T1_REQUISITION_DETAIL") d
//          ON
//              h.FORM_ID = d.FORM_ID
//          INNER JOIN
//              :Com_("D_T0_FORM_STATUS") s
//          ON
//              s.FORM_ID = d.FORM_ID
//          AND s.IS_CURRENT = 1
//          WHERE
//              1 = 1
//          AND s.STATUS = '已审核' :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:117^47*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:128^3*/

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
//  			                	    h.FORM_TYPE,
//  			                	    h.WORKSHOP,
//  			                	    NVL(h.storage_id,' ') storage_id,
//  			                	    NVL(h.storage,' ')    STORAGE,
//  			                	    '已审核' STATUS,
//  			                	    h.FORM_MAKER,
//  			                	    h.FORM_TIME,
//  			                	    h.FORM_NOTE,
//  			                	    d.*
//  			                	FROM
//  			                	    :Com_("D_T1_REQUISITION_HEADER") h
//  			                	INNER JOIN
//  			                	    :Com_("D_T1_REQUISITION_DETAIL") d
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	WHERE
//  			                	    1 = 1
//  			                	AND h.AUDIT_TIME IS NOT NULL :queryStr :sortStr) t
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
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_3 = queryStr;
  String __sJT_4 = sortStr;
  int __sJT_5 = endRow;
  int __sJT_6 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:159^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:172^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			 	SUM(NVL(RECEIVE_COUNT,0))RECEIVE_COUNT
//          	FROM
//          	    :Com_("D_T1_REQUISITION_HEADER") h
//          	INNER JOIN
//          	    :Com_("D_T1_REQUISITION_DETAIL") d
//          	ON
//          	    h.FORM_ID = d.FORM_ID
//          	INNER JOIN
//          	    :Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    s.FORM_ID = d.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	WHERE
//          	    1 = 1
//          	AND s.STATUS = '已审核' :queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_2 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:188^48*/
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}

	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("receive_count", sumIter.receive_count());
		}
			
		return sumMap;
	}

	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("form_type", headerIter.form_type());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage", headerIter.storage());
			header.put("receive_count", headerIter.receive_count());
			header.put("workshop", headerIter.workshop());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("form_maker", headerIter.form_maker());
			header.put("form_note", headerIter.form_note());
			header.put("form_time", headerIter.form_time());
			header.put("form_id", headerIter.form_id());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("item_count", headerIter.item_count());
			header.put("different_count", headerIter.different_count());
			header.put("expired_time", headerIter.expired_time());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RequisitionDetailBean_SJProfileKeys 
{
  private static RequisitionDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequisitionDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequisitionDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.RequisitionDetailBean_SJProfile0");
  }
}
