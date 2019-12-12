/*@lineinfo:filename=OperateLogQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class OperateLogQueryBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(OperateLogQueryBean.class);
	
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
    idNdx = findColumn("id");
    menu_idNdx = findColumn("menu_id");
    menu_nameNdx = findColumn("menu_name");
    urlNdx = findColumn("url");
    business_idNdx = findColumn("business_id");
    operate_typeNdx = findColumn("operate_type");
    titleNdx = findColumn("title");
    operate_descNdx = findColumn("operate_desc");
    operator_idNdx = findColumn("operator_id");
    operator_nameNdx = findColumn("operator_name");
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    operate_timeNdx = findColumn("operate_time");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String menu_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(menu_idNdx);
  }
  private int menu_idNdx;
  public String menu_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(menu_nameNdx);
  }
  private int menu_nameNdx;
  public String url() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(urlNdx);
  }
  private int urlNdx;
  public String business_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(business_idNdx);
  }
  private int business_idNdx;
  public String operate_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operate_typeNdx);
  }
  private int operate_typeNdx;
  public String title() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(titleNdx);
  }
  private int titleNdx;
  public String operate_desc() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operate_descNdx);
  }
  private int operate_descNdx;
  public String operator_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operator_idNdx);
  }
  private int operator_idNdx;
  public String operator_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operator_nameNdx);
  }
  private int operator_nameNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String branch_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_nameNdx);
  }
  private int branch_nameNdx;
  public Date operate_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(operate_timeNdx);
  }
  private int operate_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^3*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("id", "String");
		map.put("menu_id", "String");
		map.put("menu_name", "String");
		map.put("url", "String");
		map.put("business_id", "String");
		map.put("operate_type", "String");
		map.put("title", "String");
		map.put("operate_desc", "String");
		map.put("operator_id", "String");
		map.put("operator_name", "String");
		map.put("branch_id", "String");
		map.put("branch_name", "String");
		map.put("operate_time", "Date");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("id", "o");
		map.put("menu_id", "o");
		map.put("menu_name", "o");
		map.put("url", "o");
		map.put("business_id", "o");
		map.put("operate_type", "o");
		map.put("title", "o");
		map.put("operate_desc", "o");
		map.put("operator_id", "o");
		map.put("operator_name", "o");
		map.put("operate_time", "o");
		map.put("branch_id", "o");
		map.put("branch_name", "b");
		return map;
	}
	
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:86^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			count(*) 
//      	FROM
//      	:Com_("D_T0_OPERATELOG") o
//      	LEFT JOIN
//      	:Com_("D_T2_BRANCH") b
//      	ON
//      	    o.BRANCH_ID = b.BRANCH_ID
//  	    WHERE
//  	    	1 = 1  :queryStr  };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_OPERATELOG");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OperateLogQueryBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:96^32*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:107^3*/

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
//  			                	    o.ID,
//  			                	    o.MENU_ID,
//  			                	    o.MENU_NAME,
//  			                	    o.URL,
//  			                	    decode(o.BUSINESS_ID,'qualificationSupplier','',o.BUSINESS_ID) BUSINESS_ID,
//  			                	    o.OPERATE_TYPE,
//  			                	    o.TITLE,
//  			                	    o.OPERATE_DESC,
//  			                	    o.OPERATOR_ID,
//  			                	    o.OPERATOR_NAME,
//  			                	    o.BRANCH_ID,
//  			                	    o.OPERATE_TIME,
//  			                	    b.BRANCH_NAME
//  			                	FROM
//  			                	:Com_("D_T0_OPERATELOG") o
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    o.BRANCH_ID = b.BRANCH_ID 
//  			                	WHERE      1 = 1  :queryStr :sortStr) t
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
  String __sJT_1 = Com_("D_T0_OPERATELOG");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = queryStr;
  String __sJT_4 = sortStr;
  int __sJT_5 = endRow;
  int __sJT_6 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OperateLogQueryBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:140^34*/
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
			header.put("id", headerIter.id());
			header.put("menu_id", headerIter.menu_id());
			header.put("menu_name", headerIter.menu_name());
			header.put("url", headerIter.url());
			header.put("business_id", headerIter.business_id());
			header.put("operate_type", headerIter.operate_type());
			header.put("title", headerIter.title());
			header.put("operate_desc", headerIter.operate_desc());
			header.put("operator_id", headerIter.operator_id());
			header.put("operator_name", headerIter.operator_name());
			header.put("branch_id", headerIter.branch_id());
			header.put("branch_name", headerIter.branch_name());
			header.put("operate_time", headerIter.operate_time());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class OperateLogQueryBean_SJProfileKeys 
{
  private static OperateLogQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new OperateLogQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private OperateLogQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.OperateLogQueryBean_SJProfile0");
  }
}
