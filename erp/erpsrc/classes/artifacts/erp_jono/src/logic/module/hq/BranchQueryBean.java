/*@lineinfo:filename=BranchQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 2, 2015 by liyzh
 * Last edited on Sep 2, 2015 by liyzh
 * 
 * 说明： 选择所有物流中心、央厂、配送分组（含下属餐厅）
 */
package logic.module.hq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;

public class BranchQueryBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(BranchQueryBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:34^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class NodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public NodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
    typeNdx = findColumn("type");
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
  public String type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(typeNdx);
  }
  private int typeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:34^83*/
	
	//查询所有物流中心、央厂、配送分组（含下属餐厅），树形结构
	public List<Map> queryAllTree() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:41^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { SELECT
//  			    b.BRANCH_ID   AS id,
//  			    b.BRANCH_NAME AS name,
//  			    'root'        AS parent,
//  			    'branch'      AS type
//  			FROM
//  			:Com_("D_T2_BRANCH") b
//  			WHERE
//  			    b.BRANCH_TYPE IN ('LOGISTICSCENTER',
//  			                      'CENTRALFACTORY')
//  			UNION ALL
//  			SELECT
//  			    r.REGION_ID   AS id ,
//  			    r.REGION_NAME AS name,
//  			    'root'        AS parent,
//  			    'region'      AS type
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION") r
//  			UNION ALL
//  			SELECT
//  			    b.BRANCH_ID   AS id ,
//  			    b.BRANCH_NAME AS name,
//  			    rb.REGION_ID  AS parent,
//  			    'branch'      AS type
//  			FROM
//  				:Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = rb.BRANCH_ID
//  			WHERE
//  				b.BRANCH_ID is not null and b.ENABLED = 1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_3 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_4 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchQueryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:74^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("type", nodeIter.type());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	//查询所有配送分组（含下属餐厅），树形结构
	public List<Map> queryGroupTree()
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:94^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { SELECT
//  			    r.REGION_ID   AS id ,
//  			    r.REGION_NAME AS name,
//  			    'root'        AS parent,
//  			    'region'	  AS type
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION") r
//  			UNION ALL
//  			SELECT
//  			    b.BRANCH_ID   AS id ,
//  			    b.BRANCH_NAME AS name,
//  			    rb.REGION_ID  AS parent,
//  			    'branch'	  AS type
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = rb.BRANCH_ID
//  		    WHERE
//  			    b.BRANCH_ID IS NOT NULL
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_2 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_3 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchQueryBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:116^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("type", nodeIter.type());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	public List<Map> queryStore()
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:135^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { SELECT
//  			    b.branch_id   AS id,
//  			    b.branch_name AS name,
//  			    'root'        AS parent,
//  			    'branch'      AS type
//  			FROM
//  				:Com_("d_T2_branch") b 
//  			WHERE
//  			    b.branch_type IN ('RESTAURANT','FRANCHISEE')
//  				AND b.ENABLED = 1
//  			ORDER BY
//  			    b.BRANCH_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_T2_branch");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchQueryBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:147^19*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("type", nodeIter.type());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
}/*@lineinfo:generated-code*/class BranchQueryBean_SJProfileKeys 
{
  private static BranchQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BranchQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BranchQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.hq.BranchQueryBean_SJProfile0");
  }
}
