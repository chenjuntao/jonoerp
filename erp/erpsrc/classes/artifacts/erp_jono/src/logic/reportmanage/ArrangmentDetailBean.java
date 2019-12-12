/*@lineinfo:filename=ArrangmentDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ArrangmentDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ArrangmentDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:27^2*/

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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    produce_countNdx = findColumn("produce_count");
    work_order_idNdx = findColumn("work_order_id");
    workshopNdx = findColumn("workshop");
    produce_timeNdx = findColumn("produce_time");
    production_cycleNdx = findColumn("production_cycle");
    complete_timeNdx = findColumn("complete_time");
    form_timeNdx = findColumn("form_time");
    audit_timeNdx = findColumn("audit_time");
    noteNdx = findColumn("note");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public Double produce_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(produce_countNdx);
  }
  private int produce_countNdx;
  public String work_order_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(work_order_idNdx);
  }
  private int work_order_idNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
  public Date produce_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(produce_timeNdx);
  }
  private int produce_timeNdx;
  public Double production_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(production_cycleNdx);
  }
  private int production_cycleNdx;
  public Date complete_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(complete_timeNdx);
  }
  private int complete_timeNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(noteNdx);
  }
  private int noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^15*/
	
	   /*@lineinfo:generated-code*//*@lineinfo:43^5*/

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
    produce_countNdx = findColumn("produce_count");
  }
  public Double produce_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(produce_countNdx);
  }
  private int produce_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:44^25*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("produce_count", "Number");
		map.put("work_order_id", "String");
		map.put("workshop", "String");
		map.put("produce_time", "Date");
		map.put("complete_time", "Date");
		map.put("form_time", "Date");
		map.put("audit_time", "Date");
		map.put("production_cycle", "Number");
		map.put("form_note", "note");
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "d");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		map.put("produce_count", "d");
		map.put("work_order_id", "d");
		map.put("workshop", "d");
		map.put("produce_time", "d");
		map.put("production_cycle", "d");
		map.put("form_note", "d");
		map.put("complete_time", "d");
		map.put("form_time", "h");
		map.put("audit_time", "h");
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:89^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_ARRENGMENT_HEADER") h
//  		INNER JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    h.FORM_ID = s.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  		ON
//  		    h.FORM_ID = d.FORM_ID
//  		WHERE
//  		    1 = 1
//  		AND s.STATUS = '已审核' :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_4 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:107^41*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

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
//  			                	    d.*,
//  			                	    h.form_time,
//  			                	    h.audit_time
//  			                	FROM
//  			                	    :Com_("D_T1_ARRENGMENT_HEADER") h
//  			                	INNER JOIN
//  			                	    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	WHERE
//  			                	    1 = 1
//                  	    		AND h.AUDIT_TIME IS NOT NULL :queryStr :sortStr) t
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
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = queryStr;
  String __sJT_4 = sortStr;
  int __sJT_5 = endRow;
  int __sJT_6 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:143^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Double produce_count = 0.0;
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:156^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			 	SUM(PRODUCE_COUNT)PRODUCE_COUNT
//          	FROM
//          	    :Com_("D_T1_ARRENGMENT_HEADER") h
//          	INNER JOIN
//          	    :Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	INNER JOIN
//          	    :Com_("D_T1_ARRENGMENT_DETAIL") d
//          	ON
//          	    h.FORM_ID = d.FORM_ID
//          	WHERE
//          	    1 = 1
//          	AND s.STATUS = '已审核' :queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_4 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:172^48*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	

	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("produce_count", sumIter.produce_count());
		}
			
		return sumMap;
	}

	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("form_id", headerIter.form_id());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("produce_count", headerIter.produce_count());
			header.put("work_order_id", headerIter.work_order_id());
			header.put("workshop", headerIter.workshop());
			header.put("produce_time", headerIter.produce_time());
			header.put("production_cycle", headerIter.production_cycle());
			header.put("complete_time", headerIter.complete_time());
			header.put("note", headerIter.note());
			header.put("form_time", headerIter.form_time());
			header.put("audit_time", headerIter.audit_time());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ArrangmentDetailBean_SJProfileKeys 
{
  private static ArrangmentDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ArrangmentDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ArrangmentDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ArrangmentDetailBean_SJProfile0");
  }
}
