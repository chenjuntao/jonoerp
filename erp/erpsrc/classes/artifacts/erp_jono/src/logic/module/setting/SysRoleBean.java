/*@lineinfo:filename=SysRoleBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 17 15:04:23 CST 2015 by lyz
 * Last edited on Tue Mar 17 15:04:23 CST 2015 by lyz
 * 
 * comment: 系统角色表
 */
package logic.module.setting;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.setting.user.pojo.SysRole;
import com.tanry.framework.acl.NoPrivilegeException;

public class SysRoleBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SysRoleBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:34^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class RoleIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public RoleIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    role_idNdx = findColumn("role_id");
    role_nameNdx = findColumn("role_name");
    branch_typeNdx = findColumn("branch_type");
    branch_type_nameNdx = findColumn("branch_type_name");
    descriptionNdx = findColumn("description");
  }
  public String role_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(role_idNdx);
  }
  private int role_idNdx;
  public String role_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(role_nameNdx);
  }
  private int role_nameNdx;
  public String branch_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_typeNdx);
  }
  private int branch_typeNdx;
  public String branch_type_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_type_nameNdx);
  }
  private int branch_type_nameNdx;
  public String description() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(descriptionNdx);
  }
  private int descriptionNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:34^130*/
	
	public int saveEntity(SysRole role)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String roleId = role.getRoleId();
		String roleName = role.getRoleName();
		String branchType = role.getBranchType();
		String description = role.getDescription();

		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_ROLE") 
//  				(ROLE_ID, ROLE_NAME, BRANCH_TYPE, DESCRIPTION) 
//  			VALUES (:roleId, :roleName, :branchType, :description)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_ROLE");
  String __sJT_2 = roleId;
  String __sJT_3 = roleName;
  String __sJT_4 = branchType;
  String __sJT_5 = description;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:49^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(String oldRoleId, SysRole role)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String roleId = role.getRoleId();
		String roleName = role.getRoleName();
		String description = role.getDescription();

		/*@lineinfo:generated-code*//*@lineinfo:63^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T0_ROLE")
//  			SET
//  				ROLE_ID = :roleId,
//  				ROLE_NAME = :roleName,
//  				DESCRIPTION = :description
//  			WHERE 
//  				ROLE_ID = :oldRoleId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_ROLE");
  String __sJT_2 = roleId;
  String __sJT_3 = roleName;
  String __sJT_4 = description;
  String __sJT_5 = oldRoleId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:72^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public boolean deletable(String roleId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*) 
//  			FROM
//  			:Com_("D_T0_USERTOROLE") u2r
//  		    WHERE
//  		        u2r.ROLE_ID = :roleId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = roleId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:89^3*/
		closeDefaultContext(myCtx);
		
		if (total > 0) {
			return false;
		}
		return true;
	}

	public int deleteEntity(String roleId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:102^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T0_ROLE")
//  			WHERE
//  				ROLE_ID = :roleId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_ROLE");
  String __sJT_2 = roleId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:108^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteMenuUser(String roleId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:118^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  					:Com_("D_T0_PERMISSION")
//  				WHERE
//  					PRINCIPAL_TYPE = 'role'
//  				and
//  					PRINCIPAL_ID = :roleId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_PERMISSION");
  String __sJT_2 = roleId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:126^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public List<SysRole> query() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RoleIter roleIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:136^3*/

//  ************************************************************
//  #sql [myCtx] roleIter = { SELECT
//  			    r.*,
//  			    t.TYPE_NAME AS branch_type_name
//  			FROM
//  			:Com_("D_T0_ROLE") r
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH_TYPE") t
//  			ON
//  			    t.TYPE_ID = r.BRANCH_TYPE
//  			ORDER BY
//  				r.BRANCH_TYPE,
//  				r.ROLE_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_ROLE");
  String __sJT_2 = Com_("D_T2_BRANCH_TYPE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      roleIter = new RoleIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:149^3*/
		List<SysRole> roleLst = processIter(roleIter);
		roleIter.close();
		closeDefaultContext(myCtx);
		return roleLst;
	}
	/*@lineinfo:generated-code*//*@lineinfo:155^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SysRoleIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SysRoleIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    role_idNdx = findColumn("role_id");
  }
  public String role_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(role_idNdx);
  }
  private int role_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:155^50*/
	
	public List<SysRole> listSysRole() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SysRoleIter roleIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:161^3*/

//  ************************************************************
//  #sql [myCtx] roleIter = { SELECT
//  			    r.*
//  			FROM
//  			:Com_("D_T0_ROLE") r
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_ROLE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      roleIter = new SysRoleIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:166^3*/
		List<SysRole> roleLst = new ArrayList<SysRole>();
		while(roleIter.next()) {
			SysRole sysRole = new SysRole();
			sysRole.setRoleId(roleIter.role_id());
			roleLst.add(sysRole);
		}
		roleIter.close();
		closeDefaultContext(myCtx);
		return roleLst;
	}
	/**
	 * 根据部门类型查询角色
	 */
	public List<SysRole> queryByBranchType(String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RoleIter roleIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:184^3*/

//  ************************************************************
//  #sql [myCtx] roleIter = { SELECT
//  			    r.*,
//  			    '' AS branch_type_name
//  			FROM
//  			:Com_("D_T0_ROLE") r
//  			WHERE
//  				BRANCH_TYPE = :branchType
//  			ORDER BY
//  				r.ROLE_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_ROLE");
  String __sJT_2 = branchType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      roleIter = new RoleIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:194^3*/
		List<SysRole> roleLst = processIter(roleIter);
		roleIter.close();
		closeDefaultContext(myCtx);
		return roleLst;
	}
	
	public SysRole queryById(String roleId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RoleIter roleIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:205^3*/

//  ************************************************************
//  #sql [myCtx] roleIter = { SELECT
//  			    r.*,
//  			    t.TYPE_NAME AS branch_type_name
//  			FROM
//  			:Com_("D_T0_ROLE") r
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH_TYPE") t
//  			ON
//  			    t.TYPE_ID = r.BRANCH_TYPE
//  			WHERE
//  				ROLE_ID = :roleId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_ROLE");
  String __sJT_2 = Com_("D_T2_BRANCH_TYPE");
  String __sJT_3 = roleId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      roleIter = new RoleIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:217^3*/
		List<SysRole> roleLst = processIter(roleIter);
		roleIter.close();
		closeDefaultContext(myCtx);
		if (roleLst !=null && !roleLst.isEmpty()) {
			return roleLst.get(0);
		}
		return null;
	}

	public SysRole queryRole(String userId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RoleIter roleIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:231^3*/

//  ************************************************************
//  #sql [myCtx] roleIter = { SELECT
//  			    r.*, '' as branch_type_name
//  			FROM
//  			:Com_("D_T0_USERTOROLE") u2r
//  			INNER JOIN
//  			:Com_("D_T0_ROLE") r
//  			ON
//  			    r.ROLE_ID = u2r.ROLE_ID
//  			WHERE
//  			    u2r.USER_ID = :userId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = Com_("D_T0_ROLE");
  String __sJT_3 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysRoleBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      roleIter = new RoleIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:242^3*/
		List<SysRole> roleLst = processIter(roleIter);
		roleIter.close();
		closeDefaultContext(myCtx);
		if (roleLst !=null && !roleLst.isEmpty()) {
			return roleLst.get(0);
		}
		return null;
	}
	
	private List<SysRole> processIter(RoleIter roleIter) 
			throws SQLException {
		List<SysRole> roleLst = new ArrayList<SysRole>();
		while(roleIter.next()) {
			SysRole role = new SysRole();
			role.setRoleId(roleIter.role_id());
			role.setRoleName(roleIter.role_name());
			role.setBranchType(roleIter.branch_type());
			role.setBranchTypeName(roleIter.branch_type_name());
			role.setDescription(roleIter.description());
			roleLst.add(role);
		}
		return roleLst;
	}
}/*@lineinfo:generated-code*/class SysRoleBean_SJProfileKeys 
{
  private static SysRoleBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SysRoleBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SysRoleBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.setting.SysRoleBean_SJProfile0");
  }
}
