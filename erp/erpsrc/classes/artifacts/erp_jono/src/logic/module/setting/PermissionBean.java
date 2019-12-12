/*@lineinfo:filename=PermissionBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 17 15:05:29 CST 2015 by lyz
 * Last edited on Tue Mar 17 15:05:29 CST 2015 by lyz
 * 
 * comment: 系统权限表
 */
package logic.module.setting;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import com.tanry.business.module.setting.user.pojo.Permission;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PermissionBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PermissionBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class PermissionIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PermissionIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    resource_typeNdx = findColumn("resource_type");
    principal_typeNdx = findColumn("principal_type");
    operateNdx = findColumn("operate");
    resource_idNdx = findColumn("resource_id");
    principal_idNdx = findColumn("principal_id");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String resource_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(resource_typeNdx);
  }
  private int resource_typeNdx;
  public String principal_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(principal_typeNdx);
  }
  private int principal_typeNdx;
  public Integer operate() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(operateNdx);
  }
  private int operateNdx;
  public String resource_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(resource_idNdx);
  }
  private int resource_idNdx;
  public String principal_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(principal_idNdx);
  }
  private int principal_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^151*/
	
	public int saveEntity(Permission permission)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String id = permission.getId();
		String resourceType = permission.getResourceType();
		String principalType = permission.getPrincipalType();
		Integer operate = permission.getOperate();
		String resourceId = permission.getResourceId();
		String principalId = permission.getPrincipalId();

		//插入数据前先判断是否存在相同的项
		/*@lineinfo:generated-code*//*@lineinfo:49^3*/

//  ************************************************************
//  #sql [myCtx] { MERGE INTO :Com_("D_T0_PERMISSION") 
//  			USING dual ON (RESOURCE_ID = :resourceId
//  					AND	RESOURCE_TYPE = :resourceType
//  			    	AND	PRINCIPAL_ID = :principalId
//  			    	AND PRINCIPAL_TYPE = :principalType)
//  			WHEN NOT MATCHED THEN INSERT 
//  				(ID, RESOURCE_TYPE, PRINCIPAL_TYPE, OPERATE, RESOURCE_ID, PRINCIPAL_ID) 
//  			VALUES 
//  				(:id, :resourceType, :principalType, :operate, :resourceId, :principalId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PERMISSION");
  String __sJT_2 = resourceId;
  String __sJT_3 = resourceType;
  String __sJT_4 = principalId;
  String __sJT_5 = principalType;
  String __sJT_6 = id;
  String __sJT_7 = resourceType;
  String __sJT_8 = principalType;
  Integer __sJT_9 = operate;
  String __sJT_10 = resourceId;
  String __sJT_11 = principalId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PermissionBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setIntWrapper(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:59^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 根据四个参数删除一条记录：principalId，principalType，resourceId，resourceType
	 */
	public int deleteEntity(String principalId, String principalType, String resourceId, String resourceType)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:72^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T0_PERMISSION")
//  			WHERE
//  				RESOURCE_TYPE = :resourceType
//  			AND
//  				PRINCIPAL_TYPE = :principalType
//  			AND
//  				RESOURCE_ID = :resourceId
//  			AND
//  				PRINCIPAL_ID = :principalId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PERMISSION");
  String __sJT_2 = resourceType;
  String __sJT_3 = principalType;
  String __sJT_4 = resourceId;
  String __sJT_5 = principalId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PermissionBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:84^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 删除除此之外的其它记录
	 */
	public int deleteExcept(String principalId, String principalType, String menuIds) 
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " 1=1 ";
		if (!TextUtil.isEmpty(menuIds)) { // 非空处理
			query = " RESOURCE_ID NOT IN ('" + menuIds.replaceAll(",", "','") + "') ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:101^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T0_PERMISSION")
//  			WHERE
//  				PRINCIPAL_TYPE = :principalType
//  			AND	PRINCIPAL_ID = :principalId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PERMISSION");
  String __sJT_2 = principalType;
  String __sJT_3 = principalId;
  String __sJT_4 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PermissionBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:109^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:115^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class MenuIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MenuIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:115^42*/
	/**
	 * 根据权限主体和权限类型查询所有权限菜单
	 */
	public List<String> queryAllow(String principalId, String principalType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		MenuIter menuIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:123^3*/

//  ************************************************************
//  #sql [myCtx] menuIter = { SELECT
//  				p.RESOURCE_ID id
//  			FROM
//  				:Com_("D_T0_PERMISSION") p
//  			WHERE	p.PRINCIPAL_TYPE = :principalType
//  			AND	p.PRINCIPAL_ID = :principalId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PERMISSION");
  String __sJT_2 = principalType;
  String __sJT_3 = principalId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PermissionBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      menuIter = new MenuIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:130^3*/
		List<String> menuLst = new ArrayList<String>();
		while(menuIter.next()) {
			menuLst.add(menuIter.id());
		}
		menuIter.close();
		closeDefaultContext(myCtx);
		return menuLst;
	}
	
	private List<Permission> processIter(PermissionIter permissionIter) 
			throws SQLException {
		List<Permission> permissionLst = new ArrayList<Permission>();
		while(permissionIter.next()) {
			Permission permission = new Permission();
			permission.setId(permissionIter.id());
			permission.setResourceType(permissionIter.resource_type());
			permission.setPrincipalType(permissionIter.principal_type());
			permission.setOperate(permissionIter.operate());
			permission.setResourceId(permissionIter.resource_id());
			permission.setPrincipalId(permissionIter.principal_id());
			permissionLst.add(permission);
		}
		return permissionLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:156^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class VisiableIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public VisiableIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    sumNdx = findColumn("sum");
  }
  public int sum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(sumNdx);
  }
  private int sumNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:156^44*/
	public Integer queryVisi(String userId, String menuId) throws SQLException {
		DefaultContext ctx = getDefaultContext();
		VisiableIter iter = null;
		int result = 0;
		/*@lineinfo:generated-code*//*@lineinfo:161^3*/

//  ************************************************************
//  #sql [ctx] iter = { SELECT 
//  					count(*) sum
//  				FROM
//  					:Com_("D_T0_USERTOROLE") U
//  				INNER JOIN
//  					:Com_("D_T0_PERMISSION") P
//  				ON
//  					U.ROLE_ID = P.PRINCIPAL_ID
//  				WHERE
//  					U.USER_ID = :userId
//  				AND
//  					P.RESOURCE_ID = :menuId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = ctx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = Com_("D_T0_PERMISSION");
  String __sJT_3 = userId;
  String __sJT_4 = menuId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PermissionBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new VisiableIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:174^3*/
		while(iter.next()) {
			result = iter.sum();
			break;
		}
		iter.close();
		closeDefaultContext(ctx);
		return result;
	}
}/*@lineinfo:generated-code*/class PermissionBean_SJProfileKeys 
{
  private static PermissionBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PermissionBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PermissionBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.setting.PermissionBean_SJProfile0");
  }
}
