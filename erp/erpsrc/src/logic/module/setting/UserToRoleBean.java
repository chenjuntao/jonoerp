/*@lineinfo:filename=UserToRoleBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 17 15:04:23 CST 2015 by lyz
 * Last edited on Tue Mar 17 15:04:23 CST 2015 by lyz
 * 
 * comment: 系统用户角色关联表
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

import com.tanry.business.module.setting.user.pojo.UserToRole;
import com.tanry.framework.acl.NoPrivilegeException;

public class UserToRoleBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(UserToRoleBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:34^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class RelationIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public RelationIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    weightNdx = findColumn("weight");
    user_idNdx = findColumn("user_id");
    role_idNdx = findColumn("role_id");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public Integer weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(weightNdx);
  }
  private int weightNdx;
  public String user_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(user_idNdx);
  }
  private int user_idNdx;
  public String role_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(role_idNdx);
  }
  private int role_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:34^94*/
	
	public int saveEntity(UserToRole relation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String id = relation.getId();
		Integer weight = relation.getWeight();
		String userId = relation.getUserId();
		String roleId = relation.getRoleId();

		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_USERTOROLE") 
//  				(ID, WEIGHT, USER_ID, ROLE_ID) 
//  			VALUES (:id, :weight, :userId, :roleId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = id;
  Integer __sJT_3 = weight;
  String __sJT_4 = userId;
  String __sJT_5 = roleId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setIntWrapper(3, __sJT_3);
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
	
	public int saveEntity(String userId, String roleId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		if (exist(userId)) {
			/*@lineinfo:generated-code*//*@lineinfo:60^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T0_USERTOROLE")
//  				SET
//  					ROLE_ID = :roleId
//  				WHERE 
//  					USER_ID = :userId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = roleId;
  String __sJT_3 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:67^4*/
		} else {
			/*@lineinfo:generated-code*//*@lineinfo:69^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_USERTOROLE") 
//  					(USER_ID, ROLE_ID) 
//  				VALUES (:userId, :roleId)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = userId;
  String __sJT_3 = roleId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:73^4*/
		}
		closeDefaultContext(myCtx);
		return 1;
	}
	
	public int updateEntity(UserToRole relation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String id = relation.getId();
		Integer weight = relation.getWeight();
		String userId = relation.getUserId();
		String roleId = relation.getRoleId();

		/*@lineinfo:generated-code*//*@lineinfo:88^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T0_USERTOROLE")
//  			SET
//  				WEIGHT = :weight,
//  				USER_ID = :userId,
//  				ROLE_ID = :roleId
//  			WHERE 
//  				ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  Integer __sJT_2 = weight;
  String __sJT_3 = userId;
  String __sJT_4 = roleId;
  String __sJT_5 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setIntWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:97^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String id)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:107^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T0_USERTOROLE")
//  			WHERE
//  				ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:113^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteByRole(String roleId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:123^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T0_USERTOROLE")
//  			WHERE
//  				ROLE_ID = :roleId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = roleId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:129^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<UserToRole> query() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RelationIter relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:139^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T0_USERTOROLE")
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      relationIter = new RelationIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:144^3*/
		List<UserToRole> relationLst = processIter(relationIter);
		relationIter.close();
		closeDefaultContext(myCtx);
		return relationLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:151^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class UIDIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public UIDIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    USER_IDNdx = findColumn("USER_ID");
    ROLE_IDNdx = findColumn("ROLE_ID");
  }
  public String USER_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(USER_IDNdx);
  }
  private int USER_IDNdx;
  public String ROLE_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ROLE_IDNdx);
  }
  private int ROLE_IDNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:151^66*/
	
	public List<UserToRole> listUserToRole() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		UIDIterator relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:157^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T0_USERTOROLE")
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      relationIter = new UIDIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:162^3*/
		List<UserToRole> relationLst = new ArrayList();
		try {
			while(relationIter.next()) {
				UserToRole userToRole = new UserToRole();
				userToRole.setUserId(relationIter.USER_ID());
				userToRole.setRoleId(relationIter.ROLE_ID());
				relationLst.add(userToRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		relationIter.close();
		closeDefaultContext(myCtx);
		return relationLst;
	}

	public boolean exist(String userId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:183^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*) 
//  			FROM
//  			:Com_("D_T0_USERTOROLE")
//  			WHERE
//  				USER_ID = :userId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:190^3*/
		closeDefaultContext(myCtx);
		if (total > 0) {
			return true;
		}
		return false;
	}
	
	public String queryRoleByUserId(String userId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String roleId = null;
		UIDIterator relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:203^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  				USER_ID, ROLE_ID
//  			FROM
//  			:Com_("D_T0_USERTOROLE")
//  			WHERE
//  				USER_ID = :userId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOROLE");
  String __sJT_2 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToRoleBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      relationIter = new UIDIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:210^3*/
		
		while(relationIter.next()) {
			roleId = relationIter.ROLE_ID();
			break;
		}
		closeDefaultContext(myCtx);
		
		return roleId;
	}
	
	private List<UserToRole> processIter(RelationIter relationIter) 
			throws SQLException {
		List<UserToRole> relationLst = new ArrayList<UserToRole>();
		while(relationIter.next()) {
			UserToRole relation = new UserToRole();
			relation.setId(relationIter.id());
			relation.setWeight(relationIter.weight());
			relation.setUserId(relationIter.user_id());
			relation.setRoleId(relationIter.role_id());
			relationLst.add(relation);
		}
		return relationLst;
	}
}/*@lineinfo:generated-code*/class UserToRoleBean_SJProfileKeys 
{
  private static UserToRoleBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new UserToRoleBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private UserToRoleBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.setting.UserToRoleBean_SJProfile0");
  }
}
