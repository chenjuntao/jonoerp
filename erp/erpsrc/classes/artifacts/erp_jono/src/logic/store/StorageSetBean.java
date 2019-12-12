/*@lineinfo:filename=StorageSetBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.store;

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
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.acl.NoPrivilegeException;

public class StorageSetBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StorageSetBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:26^2*/

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
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
    isLeafNdx = findColumn("isLeaf");
    storage_idNdx = findColumn("storage_id");
    storage_nameNdx = findColumn("storage_name");
    priorityNdx = findColumn("priority");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(nameNdx);
  }
  private int nameNdx;
  public String parent() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parentNdx);
  }
  private int parentNdx;
  public String isLeaf() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(isLeafNdx);
  }
  private int isLeafNdx;
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
  public Double priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(priorityNdx);
  }
  private int priorityNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:33^21*/
	
	
	public List<Map> query()  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:41^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            b.BRANCH_ID id,
//  			            b.BRANCH_NAME,
//  			            b.BRANCH_TYPE parent,
//  			            'N'           isLeaf,
//  			            ''            STORAGE_ID,
//  			            ''            STORAGE_NAME,
//  			            -1            PRIORITY
//  			        FROM
//  			        :Com_("D_T2_BRANCH") b
//  			        WHERE
//  			            b.BRANCH_TYPE IN ('RESTAURANT',
//  			                              'CENTRALFACTORY',
//  			                              'LOGISTICSCENTER','FRANCHISEE','OUTERORDER')
//  			        ORDER BY
//  			            b.BRANCH_TYPE,
//  			            b.BRANCH_ID
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            s.BRANCH_ID id,
//  			            s.STORAGE_ID,
//  			            s.STORAGE_NAME,
//  			            s.PRIORITY
//  			        FROM
//  			        :Com_("D_T2_BRANCH_STORAGE") s
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT
//  			            b.BRANCH_TYPE id,
//  			            CASE
//  			                WHEN b.BRANCH_TYPE = 'RESTAURANT'
//  			                THEN '餐厅'
//  			                WHEN b.BRANCH_TYPE = 'LOGISTICSCENTER'
//  			                THEN '物流中心'
//  			                WHEN b.BRANCH_TYPE = 'CENTRALFACTORY'
//  			                THEN '中央工厂'
//  			                WHEN b.BRANCH_TYPE = 'FRANCHISEE'
//  			                THEN '加盟店'
//  			                WHEN b.BRANCH_TYPE = 'OUTERORDER'
//  			                THEN '外部订货方'
//  			            END    name,
//  			            'root' parent,
//  			            'N'    isLeaf,
//  			            ''     STORAGE_ID,
//  			            ''     STORAGE_NAME,
//  			            -1     PRIORITY
//  			        FROM
//  			        :Com_("D_T2_BRANCH") b
//  			        WHERE
//  			            b.BRANCH_TYPE IN ('RESTAURANT',
//  			                              'CENTRALFACTORY',
//  			                              'LOGISTICSCENTER','FRANCHISEE','OUTERORDER')
//  			        GROUP BY
//  			            b.BRANCH_TYPE
//  			    )
//  			SELECT
//  			    b.id ||b.STORAGE_ID id,
//  			    b.STORAGE_NAME      name,
//  			    b.id                parent,
//  			    'Y'                 isLeaf ,
//  			    b.STORAGE_ID,
//  			    b.STORAGE_NAME,
//  			    b.PRIORITY
//  			FROM
//  			    a
//  			LEFT JOIN
//  			    b
//  			ON
//  			    a.id = b.id
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    a
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    c };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageSetBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:127^9*/
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
			header.put("name", headerIter.name());
			header.put("parent", headerIter.parent());
			header.put("isLeaf", headerIter.isLeaf());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage_name", headerIter.storage_name());
			header.put("priority", headerIter.priority());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class StorageSetBean_SJProfileKeys 
{
  private static StorageSetBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StorageSetBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StorageSetBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.StorageSetBean_SJProfile0");
  }
}
