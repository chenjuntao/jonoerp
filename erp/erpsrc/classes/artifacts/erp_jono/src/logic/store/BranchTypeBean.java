/*@lineinfo:filename=BranchTypeBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Apr 28 10:42:27 CST 2015 by lyz
 * Last edited on Tue Apr 28 10:42:27 CST 2015 by lyz
 * 
 * comment: 部门类型表(暂定七大类)
 */
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.BranchType;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class BranchTypeBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(BranchTypeBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class BranchTypeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BranchTypeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    type_idNdx = findColumn("type_id");
    type_nameNdx = findColumn("type_name");
    descriptionNdx = findColumn("description");
  }
  public String type_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(type_idNdx);
  }
  private int type_idNdx;
  public String type_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(type_nameNdx);
  }
  private int type_nameNdx;
  public String description() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(descriptionNdx);
  }
  private int descriptionNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^91*/
	
	public int saveEntity(BranchType branchType)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String typeId = branchType.getTypeId();
		String typeName = branchType.getTypeName();
		String description = branchType.getDescription();

		/*@lineinfo:generated-code*//*@lineinfo:46^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_BRANCH_TYPE") 
//  				(TYPE_ID, TYPE_NAME, DESCRIPTION) 
//  			VALUES (:typeId, :typeName, :description)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_TYPE");
  String __sJT_2 = typeId;
  String __sJT_3 = typeName;
  String __sJT_4 = description;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchTypeBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:50^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(BranchType branchType)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String typeId = branchType.getTypeId();
		String typeName = branchType.getTypeName();
		String description = branchType.getDescription();

		/*@lineinfo:generated-code*//*@lineinfo:64^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BRANCH_TYPE")
//  			SET
//  				TYPE_ID = :typeId,
//  				TYPE_NAME = :typeName,
//  				DESCRIPTION = :description
//  			WHERE 
//  				TYPE_ID = :typeId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_TYPE");
  String __sJT_2 = typeId;
  String __sJT_3 = typeName;
  String __sJT_4 = description;
  String __sJT_5 = typeId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchTypeBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:73^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public List<BranchType> query()
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		BranchTypeIter branchTypeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:83^3*/

//  ************************************************************
//  #sql [myCtx] branchTypeIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_BRANCH_TYPE")
//  			ORDER BY
//  				TYPE_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_TYPE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchTypeBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      branchTypeIter = new BranchTypeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:90^3*/
		List<BranchType> branchTypeLst = processIter(branchTypeIter);
		branchTypeIter.close();
		closeDefaultContext(myCtx);
		return branchTypeLst;
	}

	private List<BranchType> processIter(BranchTypeIter branchTypeIter) 
			throws SQLException {
		List<BranchType> branchTypeLst = new ArrayList<BranchType>();
		while(branchTypeIter.next()) {
			BranchType branchType = new BranchType();
			branchType.setTypeId(branchTypeIter.type_id());
			branchType.setTypeName(branchTypeIter.type_name());
			branchType.setDescription(branchTypeIter.description());
			branchTypeLst.add(branchType);
		}
		return branchTypeLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:110^2*/

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

/*@lineinfo:user-code*//*@lineinfo:110^83*/
	
	public List<Map> queryDeptTree() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:116^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { SELECT
//  			    TYPE_ID   AS id ,
//  			    TYPE_NAME AS name,
//  			    'root'    AS parent,
//  			    'btype'    AS type
//  			FROM
//  				:Com_("D_T2_BRANCH_TYPE")
//  			UNION ALL
//  			SELECT
//  			    b.BRANCH_ID   AS id ,
//  			    '['||b.BRANCH_ID||']'||b.BRANCH_NAME AS name,
//  			    b.BRANCH_TYPE AS parent,
//  			    'branch'      AS type
//  			FROM
//  				:Com_("D_T2_BRANCH") b 
//  			WHERE 
//  				b.ENABLED = 1 order by 1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_TYPE");
  String __sJT_2 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchTypeBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:134^3*/
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

	/*@lineinfo:generated-code*//*@lineinfo:149^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class RoleNodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public RoleNodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
    typeNdx = findColumn("type");
    is_leafNdx = findColumn("is_leaf");
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
  public String is_leaf() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(is_leafNdx);
  }
  private int is_leafNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:149^103*/
	
	public List<Map> queryRoleTree() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RoleNodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:155^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  			    roles AS
//  			    (
//  			        SELECT
//  			            TYPE_ID   AS id ,
//  			            TYPE_NAME AS name,
//  			            'root'    AS parent,
//  			            'btype'   AS type
//  			        FROM
//  			        :Com_("D_T2_BRANCH_TYPE")
//  			        UNION ALL
//  			        SELECT
//  			            r.ROLE_ID     AS id ,
//  			            r.ROLE_NAME   AS name,
//  			            r.BRANCH_TYPE AS parent,
//  			            'role'        AS type
//  			        FROM
//  			        :Com_("D_T0_ROLE") r
//  			    )
//  			SELECT
//  			    ID,
//  			    NAME,
//  			    PARENT,
//  			    TYPE,
//  			    CONNECT_BY_ROOT NAME "ROOT",
//  			    CONNECT_BY_ISLEAF    is_leaf,
//  			    LEVEL,
//  			    SYS_CONNECT_BY_PATH(ID, '/') "PATH"
//  			FROM
//  			    roles u START WITH PARENT = 'root' CONNECT BY PRIOR ID = PARENT
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_TYPE");
  String __sJT_2 = Com_("D_T0_ROLE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchTypeBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new RoleNodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:186^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("type", nodeIter.type());
			node.put("isLeaf", nodeIter.is_leaf());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
}/*@lineinfo:generated-code*/class BranchTypeBean_SJProfileKeys 
{
  private static BranchTypeBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BranchTypeBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BranchTypeBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.BranchTypeBean_SJProfile0");
  }
}
