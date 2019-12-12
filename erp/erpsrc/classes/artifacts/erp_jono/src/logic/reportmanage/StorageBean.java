/*@lineinfo:filename=StorageBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class StorageBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StorageBean.class);
	
    /*@lineinfo:generated-code*//*@lineinfo:27^5*/

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
    branchIdNdx = findColumn("branchId");
    branchNameNdx = findColumn("branchName");
    storage_idNdx = findColumn("storage_id");
    storage_nameNdx = findColumn("storage_name");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
    item_countNdx = findColumn("item_count");
    shelf_idNdx = findColumn("shelf_id");
    last_in_timeNdx = findColumn("last_in_time");
    expired_timeNdx = findColumn("expired_time");
  }
  public String branchId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchIdNdx);
  }
  private int branchIdNdx;
  public String branchName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchNameNdx);
  }
  private int branchNameNdx;
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_nameNdx);
  }
  private int storage_nameNdx;
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
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public String shelf_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_idNdx);
  }
  private int shelf_idNdx;
  public Date last_in_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(last_in_timeNdx);
  }
  private int last_in_timeNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^30*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("branchId", "String");
		map.put("branchName", "String");
		map.put("storage_id", "String");
		map.put("storage_name", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("category_id", "String");
		map.put("category_name", "String");
		map.put("item_count", "Number");
		map.put("shelf_id", "String");
		map.put("last_in_time", "Date");
		map.put("expired_time", "Date");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("branchId", "b");
		map.put("branchName", "b");
		map.put("storage_id", "s");
		map.put("storage_name", "bs");
		map.put("item_id", "s");
		map.put("item_name", "m");
		map.put("category_id", "c");
		map.put("category_name", "c");
		map.put("item_count", "s");
		map.put("shelf_id", "s");
		map.put("last_in_time", "s");
		map.put("expired_time", "s");
		
		return map;
	}
	
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:83^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  	    FROM
//  	    :Com_("D_T2_STORAGE") s
//      	LEFT JOIN
//      	:Com_("D_T2_BRANCH_STORAGE") bs
//      	ON
//      	    s.STORAGE_ID = bs.STORAGE_ID
//      	LEFT JOIN
//      	:Com_("D_T2_BRANCH") b
//      	ON
//      	    b.BRANCH_ID = bs.BRANCH_ID
//      	LEFT JOIN
//      	:Com_("D_T2_ITEM_META") m
//      	ON
//      	    s.ITEM_ID = m.ITEM_ID
//      	LEFT JOIN
//      	:Com_("D_T2_ITEM_CATEGORY") c
//      	ON
//      	    m.CATEGORY_ID = c.CATEGORY_ID
//  		WHERE
//  		    1 = 1 :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:107^30*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr)  
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
//  			                	    b.BRANCH_ID,
//  			                	    b.BRANCH_NAME,
//  			                	    bs.STORAGE_NAME,
//  			                	    m.ITEM_NAME,
//  			                	    c.CATEGORY_ID,
//  			                	    c.CATEGORY_NAME,
//  			                	    s. *
//  			                	FROM
//  			                	:Com_("D_T2_STORAGE") s
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_BRANCH_STORAGE") bs
//  			                	ON
//  			                	    s.STORAGE_ID = bs.STORAGE_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    b.BRANCH_ID = bs.BRANCH_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_ITEM_META") m
//  			                	ON
//  			                	    s.ITEM_ID = m.ITEM_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    m.CATEGORY_ID = c.CATEGORY_ID
//  			                	WHERE
//  			                	    1 = 1 :queryStr) t
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
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
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

/*@lineinfo:user-code*//*@lineinfo:158^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		Double item_count = 0.0;
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:171^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    SUM(item_count)
//  		
//  	    FROM
//  	    :Com_("D_T2_STORAGE") s
//      	LEFT JOIN
//      	:Com_("D_T2_BRANCH_STORAGE") bs
//      	ON
//      	    s.STORAGE_ID = bs.STORAGE_ID
//      	LEFT JOIN
//      	:Com_("D_T2_BRANCH") b
//      	ON
//      	    b.BRANCH_ID = bs.BRANCH_ID
//      	LEFT JOIN
//      	:Com_("D_T2_ITEM_META") m
//      	ON
//      	    s.ITEM_ID = m.ITEM_ID
//      	LEFT JOIN
//      	:Com_("D_T2_ITEM_CATEGORY") c
//      	ON
//      	    m.CATEGORY_ID = c.CATEGORY_ID
//  		WHERE
//  		    1 = 1 :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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
    item_count = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:195^30*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = new HashMap();
		sumMap.put("item_count", item_count);
		
		return sumMap;
	}

	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("branchId", headerIter.branchId());
			header.put("branchName", headerIter.branchName());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage_name", headerIter.storage_name());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("category_id", headerIter.category_id());
			header.put("category_name", headerIter.category_name());
			header.put("item_count", headerIter.item_count());
			header.put("shelf_id", headerIter.shelf_id());
			header.put("last_in_time", headerIter.last_in_time());
			header.put("expired_time", headerIter.expired_time());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class StorageBean_SJProfileKeys 
{
  private static StorageBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StorageBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StorageBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.StorageBean_SJProfile0");
  }
}
