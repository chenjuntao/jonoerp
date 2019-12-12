/*@lineinfo:filename=RequisitionHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
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

import pojo.form.RequisitionHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class RequisitionHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequisitionHeaderBean.class);
	
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
    workshopNdx = findColumn("workshop");
    receive_countNdx = findColumn("receive_count");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
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
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^29*/
	
	/*@lineinfo:generated-code*//*@lineinfo:45^2*/

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

/*@lineinfo:user-code*//*@lineinfo:46^24*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_type", "String");
		map.put("item_name", "String");
		map.put("item_id", "String");
		map.put("receive_count", "Number");
		map.put("workshop","String");
		map.put("storage_id", "String");
		map.put("storage", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
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
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		return map;
	}
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:81^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (SELECT
//  		    	    COUNT(*)
//  		    FROM
//  		    :Com_("D_T1_REQUISITION_HEADER") h
//  		    LEFT JOIN
//  		    :Com_("D_T1_REQUISITION_DETAIL") d
//  		    ON
//  		        h.FORM_ID = d.FORM_ID
//  		    LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		    ON
//  		        s.FORM_ID = d.FORM_ID
//  		    AND s.IS_CURRENT = 1
//  		    WHERE
//  		        1 = 1 AND s.STATUS NOT IN ('未审核') :queryStr
//  		    GROUP BY
//  		        h.FORM_TYPE,
//  		        h.STORAGE_ID,
//  		        h.STORAGE,
//  		        h.FORM_MAKER,
//  		        d.ITEM_ID,
//  		        d.ITEM_NAME
//  		       ) a };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:109^13*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:120^3*/

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
//  			          		        h.FORM_TYPE,
//  			          		        h.STORAGE_ID,
//  			          		        h.STORAGE,
//  			          		        h.WORKSHOP,
//  			          		        d.ITEM_ID,
//  			          		        d.ITEM_NAME,
//  				         			d.item_dimension, 
//  				         			d.item_specification,
//  			                	    SUM(NVL(RECEIVE_COUNT,0))RECEIVE_COUNT
//  			                	FROM
//  			                	:Com_("D_T1_REQUISITION_HEADER") h
//  			        		    LEFT JOIN
//  			        		    :Com_("D_T1_REQUISITION_DETAIL") d
//  			        		    ON
//  			        		        h.FORM_ID = d.FORM_ID
//  			    		        WHERE
//  		                	    1 = 1 AND h.AUDIT_TIME IS NOT NULL :queryStr
//  		                	GROUP BY
//  		                	 h.FORM_TYPE,
//  		          		        h.STORAGE_ID,
//  		          		        h.STORAGE,
//  		          		        h.WORKSHOP,
//  		          		        d.ITEM_ID,
//  		          		        d.ITEM_NAME,
//  			         			d.item_dimension, 
//  			         			d.item_specification :sortStr) t
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 1);
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
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:172^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    SUM (RECEIVE_COUNT) RECEIVE_COUNT
//  			FROM
//  			    (
//  			        SELECT
//  			            SUM(NVL(RECEIVE_COUNT,0))RECEIVE_COUNT
//  			            FROM
//  			            :Com_("D_T1_REQUISITION_HEADER") h
//  				    LEFT JOIN
//  				    :Com_("D_T1_REQUISITION_DETAIL") d
//  				    ON
//  				        h.FORM_ID = d.FORM_ID
//  				    LEFT JOIN
//  				    :Com_("D_T0_FORM_STATUS") s
//  				    ON
//  				        s.FORM_ID = d.FORM_ID
//  				    AND s.IS_CURRENT = 1
//                  	WHERE
//                  	    1 = 1 AND s.STATUS NOT IN ('未审核') :queryStr
//  			        GROUP BY
//  			        h.FORM_TYPE,
//  			        h.STORAGE_ID,
//  			        h.STORAGE,
//  			        h.FORM_MAKER,
//  			        d.ITEM_ID,
//  			        d.ITEM_NAME) a };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequisitionHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:197^26*/
		
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
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RequisitionHeaderBean_SJProfileKeys 
{
  private static RequisitionHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequisitionHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequisitionHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.RequisitionHeaderBean_SJProfile0");
  }
}
