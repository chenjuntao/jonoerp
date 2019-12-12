/*@lineinfo:filename=WorkOrderHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class WorkOrderHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(WorkOrderHeaderBean.class);
	
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
    item_countNdx = findColumn("item_count");
    actual_countNdx = findColumn("actual_count");
    workshopNdx = findColumn("workshop");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    statusNdx = findColumn("status");
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double actual_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actual_countNdx);
  }
  private int actual_countNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
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
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^17*/
	
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
    item_countNdx = findColumn("item_count");
    actual_countNdx = findColumn("actual_count");
  }
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double actual_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actual_countNdx);
  }
  private int actual_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^23*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("item_count", "Number");
		map.put("actual_count", "Number");
		map.put("workshop", "String");
		map.put("form_maker_id", "String");
		map.put("form_maker", "String");
		map.put("form_time", "Date");
		map.put("auditor_id", "String");
		map.put("auditor", "String");
		map.put("audit_time", "Date");
		map.put("form_note", "String");
		map.put("status", "String");
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "h");
		map.put("item_id", "h");
		map.put("item_name", "h");
		map.put("item_dimension", "h");
		map.put("item_specification", "h");
		map.put("item_count", "h");
		map.put("actual_count", "h");
		map.put("workshop", "h");
		map.put("form_maker_id", "h");
		map.put("form_maker", "h");
		map.put("form_time", "h");
		map.put("auditor_id", "h");
		map.put("auditor", "h");
		map.put("audit_time", "h");
		map.put("form_note", "h");
		map.put("status", "s");
		return map;
	}
	
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:97^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_WORK_ORDER_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			 1 = 1
//               :queryStr
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:110^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:121^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM 
//  			                (
//  			                    SELECT
//  			                        h.*, 
//  			                        '已审核' STATUS 
//  			                    FROM
//  			                    :Com_("D_T1_WORK_ORDER_HEADER") h
//  			                    WHERE
//  			                    	1 = 1
//  			                    AND h.AUDIT_TIME IS NOT NULL :queryStr
//  			            		) t
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
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = queryStr;
  int __sJT_3 = endRow;
  int __sJT_4 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setInt(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
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
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("item_count", sumIter.item_count());
			sumMap.put("actual_count", sumIter.actual_count());
		}
			
		return sumMap;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:169^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  		    SUM( h.item_count) item_count,
//  		    SUM(h.actual_count)     actual_count
//  		FROM
//  		:Com_("D_T1_WORK_ORDER_HEADER") h
//  		LEFT JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		WHERE
//  		    1 = 1 :queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WorkOrderHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:181^30*/
		
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
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("item_count", headerIter.item_count());
			header.put("actual_count", headerIter.actual_count());
			header.put("workshop", headerIter.workshop());
			header.put("form_maker_id", headerIter.form_maker_id());
			header.put("form_maker", headerIter.form_maker());
			header.put("form_time", headerIter.form_time());
			header.put("auditor_id", headerIter.auditor_id());
			header.put("auditor", headerIter.auditor());
			header.put("audit_time", headerIter.audit_time());
			
			header.put("form_note", headerIter.form_note());
			header.put("status", headerIter.status());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class WorkOrderHeaderBean_SJProfileKeys 
{
  private static WorkOrderHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new WorkOrderHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private WorkOrderHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.WorkOrderHeaderBean_SJProfile0");
  }
}
