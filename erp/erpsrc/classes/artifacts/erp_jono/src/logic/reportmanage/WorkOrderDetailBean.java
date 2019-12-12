/*@lineinfo:filename=WorkOrderDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class WorkOrderDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(WorkOrderDetailBean.class);
	
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
    production_nameNdx = findColumn("production_name");
    production_stepNdx = findColumn("production_step");
    production_timeNdx = findColumn("production_time");
    production_countNdx = findColumn("production_count");
    production_manNdx = findColumn("production_man");
    production_noteNdx = findColumn("production_note");
    is_completedNdx = findColumn("is_completed");
    complete_timeNdx = findColumn("complete_time");
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
  public String production_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(production_nameNdx);
  }
  private int production_nameNdx;
  public Integer production_step() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(production_stepNdx);
  }
  private int production_stepNdx;
  public Date production_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(production_timeNdx);
  }
  private int production_timeNdx;
  public Double production_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(production_countNdx);
  }
  private int production_countNdx;
  public String production_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(production_manNdx);
  }
  private int production_manNdx;
  public String production_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(production_noteNdx);
  }
  private int production_noteNdx;
  public Integer is_completed() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(is_completedNdx);
  }
  private int is_completedNdx;
  public Date complete_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(complete_timeNdx);
  }
  private int complete_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^22*/
	
	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

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
    production_countNdx = findColumn("production_count");
  }
  public Double production_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(production_countNdx);
  }
  private int production_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^27*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("production_name", "String");
		map.put("production_step", "Number");
		map.put("production_time", "Date");
		map.put("production_count", "Number");
		map.put("production_man", "String");
		map.put("production_note", "String");
		map.put("is_completed", "Number");
		map.put("complete_time", "Date");
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "d");
		map.put("item_id", "h");
		map.put("item_name", "h");
		map.put("production_name", "d");
		map.put("production_step", "d");
		map.put("production_time", "d");
		map.put("production_count", "d");
		map.put("production_man", "d");
		map.put("production_note", "d");
		map.put("is_completed", "d");
		map.put("complete_time", "td");
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:80^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//      	    count(*) 
//      	FROM
//      	:Com_("D_T1_WORK_ORDER_DETAIL") d
//  		LEFT JOIN
//  		:Com_("D_T1_WORK_ORDER_HEADER") h
//      	ON
//      	    d.FORM_ID = h.FORM_ID
//      	JOIN
//      	:Com_("D_T1_ARRENGMENT_DETAIL") td
//      	ON
//      	    d.FORM_ID = td.WORK_ORDER_ID
//      	JOIN
//      	:Com_("D_T0_FORM_STATUS") s
//      	ON
//      	    d.FORM_ID = s.FORM_ID
//      	AND s.IS_CURRENT = 1 
//         WHERE
//         	     1 = 1   :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_DETAIL");
  String __sJT_2 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_3 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:99^39*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:110^3*/

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
//  			                	    h.ITEM_ID,
//  			                	    h.ITEM_NAME,
//  			                	    td.COMPLETE_TIME,
//  			                	    '已审核' STATUS
//  			                	FROM
//  			                	:Com_("D_T1_WORK_ORDER_DETAIL") d
//  			                	LEFT JOIN
//  			                	:Com_("D_T1_WORK_ORDER_HEADER") h
//  			                	ON
//  			                	    d.FORM_ID = h.FORM_ID
//  			                	JOIN
//  			                	:Com_("D_T1_ARRENGMENT_DETAIL") td
//  			                	ON
//  			                	    d.FORM_ID = td.WORK_ORDER_ID
//  			                	WHERE
//  			                	    1 = 1 AND h.AUDIT_TIME IS NOT NULL :queryStr:sortStr) t
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
  String __sJT_1 = Com_("D_T1_WORK_ORDER_DETAIL");
  String __sJT_2 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_3 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_4 = queryStr;
  String __sJT_5 = sortStr;
  int __sJT_6 = endRow;
  int __sJT_7 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:140^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("production_count", sumIter.production_count());
		}
			
		return sumMap;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:165^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//          	  sum(d.PRODUCTION_COUNT) PRODUCTION_COUNT 
//          	FROM
//          	:Com_("D_T1_WORK_ORDER_DETAIL") d
//      		LEFT JOIN
//      		:Com_("D_T1_WORK_ORDER_HEADER") h
//          	ON
//          	    d.FORM_ID = h.FORM_ID
//          	JOIN
//          	:Com_("D_T1_ARRENGMENT_DETAIL") td
//          	ON
//          	    d.FORM_ID = td.WORK_ORDER_ID
//          	JOIN
//          	:Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    d.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	WHERE
//          	  1 = 1   :queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_DETAIL");
  String __sJT_2 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_3 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:184^37*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
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
			header.put("production_name", headerIter.production_name());
			header.put("production_step", headerIter.production_step());
			header.put("production_time", headerIter.production_time());
			header.put("production_count", headerIter.production_count());
			header.put("production_man", headerIter.production_man());
			header.put("production_note", headerIter.production_note());
			header.put("is_completed", headerIter.is_completed());
			header.put("complete_time", headerIter.complete_time());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class WorkOrderDetailBean_SJProfileKeys 
{
  private static WorkOrderDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new WorkOrderDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private WorkOrderDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.WorkOrderDetailBean_SJProfile0");
  }
}
