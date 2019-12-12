/*@lineinfo:filename=SysUserBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 17 11:33:49 CST 2015 by lyz
 * Last edited on Tue Mar 17 11:33:49 CST 2015 by lyz
 * 
 * comment: 系统用户表
 */
package logic.module.setting;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.business.module.setting.user.vo.UserInfo;
import com.tanry.framework.acl.NoPrivilegeException;

public class SysUserBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SysUserBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class UserIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public UserIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    user_idNdx = findColumn("user_id");
    user_nameNdx = findColumn("user_name");
    passwordNdx = findColumn("password");
    weightNdx = findColumn("weight");
    emailNdx = findColumn("email");
    telephoneNdx = findColumn("telephone");
    genderNdx = findColumn("gender");
    role_nameNdx = findColumn("role_name");
  }
  public String user_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(user_idNdx);
  }
  private int user_idNdx;
  public String user_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(user_nameNdx);
  }
  private int user_nameNdx;
  public String password() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(passwordNdx);
  }
  private int passwordNdx;
  public Integer weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(weightNdx);
  }
  private int weightNdx;
  public String email() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(emailNdx);
  }
  private int emailNdx;
  public String telephone() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(telephoneNdx);
  }
  private int telephoneNdx;
  public String gender() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(genderNdx);
  }
  private int genderNdx;
  public String role_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(role_nameNdx);
  }
  private int role_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^3*/
	
	public int saveEntity(SysUser user)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String userId = user.getUserId();
		String userName = user.getUserName();
		String password = user.getPassword();
		Integer weight = user.getWeight();
		String email = user.getEmail();
		String telephone = user.getTelephone();
		String gender = user.getGender();
		
		/*@lineinfo:generated-code*//*@lineinfo:60^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("d_t2_user")
//  				(USER_ID, USER_NAME, PASSWORD, WEIGHT, EMAIL, TELEPHONE, GENDER) 
//  			VALUES (:userId, :userName, :password, :weight, :email, :telephone, :gender)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  String __sJT_2 = userId;
  String __sJT_3 = userName;
  String __sJT_4 = password;
  Integer __sJT_5 = weight;
  String __sJT_6 = email;
  String __sJT_7 = telephone;
  String __sJT_8 = gender;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setIntWrapper(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:64^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(String oldUserId, SysUser user)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String userId = user.getUserId();
		String userName = user.getUserName();
		String password = user.getPassword();
		Integer weight = user.getWeight();
		String email = user.getEmail();
		String telephone = user.getTelephone();
		String gender = user.getGender();

		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("d_t2_user")
//  			SET
//  				USER_ID = :userId,
//  				USER_NAME = :userName,
//  				-- PASSWORD = :password,
//  				WEIGHT = :weight,
//  				EMAIL = :email,
//  				TELEPHONE = :telephone,
//  				GENDER = :gender
//  			WHERE 
//  				USER_ID = :oldUserId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  String __sJT_2 = userId;
  String __sJT_3 = userName;
  Integer __sJT_4 = weight;
  String __sJT_5 = email;
  String __sJT_6 = telephone;
  String __sJT_7 = gender;
  String __sJT_8 = oldUserId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:95^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int modifyPwd(String userId, String newPwd)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:104^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("d_t2_user")
//  			SET
//  				PASSWORD = :newPwd
//  			WHERE 
//  				USER_ID = :userId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  String __sJT_2 = newPwd;
  String __sJT_3 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:111^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String userId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:120^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("d_t2_user")
//  			WHERE
//  				USER_ID = :userId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  String __sJT_2 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:126^3*/
		/*@lineinfo:generated-code*//*@lineinfo:127^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T0_USERTOBRANCH")
//  			WHERE
//  				USER_ID = :userId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOBRANCH");
  String __sJT_2 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:133^3*/
		/*@lineinfo:generated-code*//*@lineinfo:134^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T0_USERTOROLE")
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:140^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<SysUser> query(String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		UserIter userIter = null;

		/*@lineinfo:generated-code*//*@lineinfo:151^3*/

//  ************************************************************
//  #sql [myCtx] userIter = { SELECT
//  			    u.*,tr.role_name
//  			    FROM
//  			        :Com_("d_t2_user") u
//  			    INNER JOIN
//  			        :Com_("d_t0_userToBranch") u2b
//  			    ON
//  			        u2b.USER_ID = u.USER_ID
//  			    LEFT JOIN
//  			        :Com_("D_T0_USERTOROLE") ut
//  			    ON
//  			        u.USER_ID = ut.USER_ID
//  			    LEFT JOIN
//  			        :Com_("D_T0_ROLE") tr
//  			    ON
//  			        ut.ROLE_ID = tr.ROLE_ID
//  			    WHERE
//  			        u2b.BRANCH_ID = :branchId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  String __sJT_2 = Com_("d_t0_userToBranch");
  String __sJT_3 = Com_("D_T0_USERTOROLE");
  String __sJT_4 = Com_("D_T0_ROLE");
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      userIter = new UserIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:169^37*/
		List<SysUser> userLst = processIter(userIter);
		userIter.close();
		closeDefaultContext(myCtx);
		return userLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:176^2*/

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
  }
  public String USER_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(USER_IDNdx);
  }
  private int USER_IDNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:178^4*/
	public List<SysUser> listSysUser() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		UIDIterator userIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:183^3*/

//  ************************************************************
//  #sql [myCtx] userIter = { SELECT
//  			    u.*
//  			FROM
//  			    :Com_("d_t2_user") u
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      userIter = new UIDIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:188^3*/
		List<SysUser> userLst = new ArrayList();
		try {
			while(userIter.next()) {
				SysUser sysUser = new SysUser();
				sysUser.setUserId( userIter.USER_ID());
				userLst.add(sysUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userIter.close();
		closeDefaultContext(myCtx);
		return userLst;
	}
	
	public SysUser queryById(String userId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		UserIter userIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:208^3*/

//  ************************************************************
//  #sql [myCtx] userIter = { SELECT
//  			    u.*,tr.role_name
//  			FROM
//  				:Com_("d_t2_user") u
//  			LEFT JOIN
//  		        :Com_("D_T0_USERTOROLE") ut
//  		    ON
//  		        u.USER_ID = ut.USER_ID
//  		    LEFT JOIN
//  		        :Com_("D_T0_ROLE") tr
//  		    ON
//  		        ut.ROLE_ID = tr.ROLE_ID
//  			WHERE
//  				u.USER_ID = :userId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  String __sJT_2 = Com_("D_T0_USERTOROLE");
  String __sJT_3 = Com_("D_T0_ROLE");
  String __sJT_4 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      userIter = new UserIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:223^3*/
		List<SysUser> userLst = processIter(userIter);
		userIter.close();
		closeDefaultContext(myCtx);
		if (userLst !=null && !userLst.isEmpty()) {
			return userLst.get(0);
		}
		return null;
	}
	
	public List<SysUser> GetAllUsers() 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<SysUser> result = new ArrayList<SysUser>();
		DefaultContext myCtx = getDefaultContext();
		UserIter userIter = null;

		/*@lineinfo:generated-code*//*@lineinfo:239^3*/

//  ************************************************************
//  #sql [myCtx] userIter = { SELECT
//  		    	b.*,tr.role_name
//  			FROM
//  				:Com_("d_t2_user") b
//  			LEFT JOIN
//  				:Com_("D_T0_USERTOROLE") ut
//  		    ON
//  		        u.USER_ID = ut.USER_ID
//  		    LEFT JOIN
//  		        :Com_("D_T0_ROLE") tr
//  		    ON
//  		        ut.ROLE_ID = tr.ROLE_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  String __sJT_2 = Com_("D_T0_USERTOROLE");
  String __sJT_3 = Com_("D_T0_ROLE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      userIter = new UserIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:252^3*/

		List<SysUser> userLst = processIter(userIter);
		userIter.close();
		closeDefaultContext(myCtx);
		return userLst;
	}
	
	private List<SysUser> processIter(UserIter userIter) 
			throws SQLException {
		List<SysUser> userLst = new ArrayList<SysUser>();
		while(userIter.next()) {
			SysUser user = new SysUser();
			user.setRoleName(userIter.role_name());
			user.setUserId(userIter.user_id());
			user.setUserName(userIter.user_name());
			user.setPassword(userIter.password());
			user.setWeight(userIter.weight());
			user.setEmail(userIter.email());
			user.setTelephone(userIter.telephone());
			user.setGender(userIter.gender());
			userLst.add(user);
		}
		return userLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:278^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class UserInfoIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public UserInfoIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    user_idNdx = findColumn("user_id");
    user_nameNdx = findColumn("user_name");
    role_idNdx = findColumn("role_id");
    role_nameNdx = findColumn("role_name");
  }
  public String user_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(user_idNdx);
  }
  private int user_idNdx;
  public String user_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(user_nameNdx);
  }
  private int user_nameNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:278^103*/
	
	public UserInfo queryUserInfo(String userId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		UserInfoIter userIter = null;

		/*@lineinfo:generated-code*//*@lineinfo:285^3*/

//  ************************************************************
//  #sql [myCtx] userIter = { SELECT
//  			    u.*,
//  			    r.ROLE_ID,
//  			    r.ROLE_NAME
//  			FROM
//  			    :Com_("d_t2_user") u
//  			LEFT JOIN
//  			    :Com_("d_t0_userToRole") u2r
//  			ON
//  			    u.USER_ID = u2r.USER_ID
//  			LEFT JOIN
//  			   :Com_("D_T0_ROLE") r
//  			ON
//  			    r.ROLE_ID = u2r.ROLE_ID
//  			WHERE
//  				u.USER_ID = :userId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_user");
  String __sJT_2 = Com_("d_t0_userToRole");
  String __sJT_3 = Com_("D_T0_ROLE");
  String __sJT_4 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      userIter = new UserInfoIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:302^3*/

		UserInfo userInfo = new UserInfo();
		while(userIter.next()) {
			userInfo.setUserId(userIter.user_id());
			userInfo.setUserName(userIter.user_name());
			userInfo.setRoleId(userIter.role_id());
			userInfo.setRoleName(userIter.role_name());
			break;
		}
		userIter.close();
		closeDefaultContext(myCtx);
		return userInfo;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:317^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class roleInfoIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public roleInfoIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    roleIdNdx = findColumn("roleId");
    roleNameNdx = findColumn("roleName");
    userNameNdx = findColumn("userName");
    rootNameNdx = findColumn("rootName");
    userIdNdx = findColumn("userId");
  }
  public String roleId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(roleIdNdx);
  }
  private int roleIdNdx;
  public String roleName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(roleNameNdx);
  }
  private int roleNameNdx;
  public String userName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(userNameNdx);
  }
  private int userNameNdx;
  public String rootName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rootNameNdx);
  }
  private int rootNameNdx;
  public String userId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(userIdNdx);
  }
  private int userIdNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:323^4*/
	public List<Map> roleSearch() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		roleInfoIter iter = null;
		/*@lineinfo:generated-code*//*@lineinfo:328^3*/

//  ************************************************************
//  #sql [myCtx] iter = { WITH
//  			    roles AS
//  			    (
//  			        SELECT
//  			            TYPE_ID   AS id ,
//  			            TYPE_NAME AS name,
//  			            'root'    AS parent,
//  			            'btype'   AS type
//  			        FROM
//  			        	:Com_("D_T2_BRANCH_TYPE") 
//  			        UNION ALL
//  			        SELECT
//  			            r.ROLE_ID     AS id ,
//  			            r.ROLE_NAME   AS name,
//  			            r.BRANCH_TYPE AS parent,
//  			            'role'        AS type
//  			        FROM
//  			        	:Com_("D_T0_ROLE")   r
//  			    ),
//  			    b as(
//  					SELECT
//  					    ID,
//  					    NAME,
//  					    PARENT,
//  					    TYPE,
//  					    CONNECT_BY_ROOT NAME "ROOT",
//  					    CONNECT_BY_ISLEAF    is_leaf,
//  					    LEVEL levels,
//  					    SYS_CONNECT_BY_PATH(ID, '/') "PATH"
//  					FROM
//  					    roles u START WITH PARENT = 'root' CONNECT BY PRIOR ID = PARENT
//  			    ),
//  				c as(
//  					select 
//  						b.id,
//  						b.name,
//  						b.root 
//  					from 
//  						b where b.levels = 2
//  				)
//  				select 
//  					cc.id roleId,
//  					cc.Name roleName,
//  					cc.Root rootName,
//  					us.user_name userName,
//  					us.USER_ID userId
//  				from c cc 
//  				inner join 
//  					:Com_("D_T0_USERTOROLE") u 
//  				on 
//  					cc.id = u.role_id 
//  				inner join 
//  					:Com_("D_T2_USER") us 
//  				on 
//  					u.user_id = us.USER_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_TYPE");
  String __sJT_2 = Com_("D_T0_ROLE");
  String __sJT_3 = Com_("D_T0_USERTOROLE");
  String __sJT_4 = Com_("D_T2_USER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new roleInfoIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:384^3*/
		
		List<Map> nodeLst = new ArrayList<Map>();
		while(iter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("roleId", iter.roleId());
			node.put("roleName", iter.roleName());
			node.put("rootName", iter.rootName());
			node.put("userName", iter.userName());
			node.put("userId", iter.userId());
			nodeLst.add(node);
		}
		iter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
		
	}
	
	
	/*@lineinfo:generated-code*//*@lineinfo:403^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class powerInfoIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public powerInfoIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    roleIdNdx = findColumn("roleId");
    roleNameNdx = findColumn("roleName");
    powerIdNdx = findColumn("powerId");
    rootNameNdx = findColumn("rootName");
  }
  public String roleId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(roleIdNdx);
  }
  private int roleIdNdx;
  public String roleName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(roleNameNdx);
  }
  private int roleNameNdx;
  public String powerId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(powerIdNdx);
  }
  private int powerIdNdx;
  public String rootName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rootNameNdx);
  }
  private int rootNameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:408^2*/
	public List<Map> powerSearch() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		powerInfoIter iter = null;
		/*@lineinfo:generated-code*//*@lineinfo:413^3*/

//  ************************************************************
//  #sql [myCtx] iter = { WITH
//  			    roles AS
//  			    (
//  			        SELECT
//  			            TYPE_ID   AS id ,
//  			            TYPE_NAME AS name,
//  			            'root'    AS parent,
//  			            'btype'   AS type
//  			        FROM
//  			        	:Com_("D_T2_BRANCH_TYPE")
//  			        UNION ALL
//  			        SELECT
//  			            r.ROLE_ID     AS id ,
//  			            r.ROLE_NAME   AS name,
//  			            r.BRANCH_TYPE AS parent,
//  			            'role'        AS type
//  			        FROM
//  			        	:Com_("D_T0_ROLE") r
//  			    )
//  			    ,
//  			    temp1 AS
//  			    (
//  			        SELECT
//  			            ID,
//  			            NAME,
//  			            PARENT,
//  			            TYPE,
//  			            CONNECT_BY_ROOT NAME "ROOT",
//  			            CONNECT_BY_ISLEAF    is_leaf,
//  			            LEVEL,
//  			            SYS_CONNECT_BY_PATH(ID, '/') "PATH"
//  			        FROM
//  			            roles u START WITH PARENT = 'root' CONNECT BY PRIOR ID = PARENT
//  			    )
//  			    ,
//  			    temp2 AS
//  			    (
//  			        SELECT
//  			            t1.ID   ids,
//  			            t1.name names,
//  			            t1.type types,
//  			            t1.root roots
//  			        FROM
//  			            temp1 t1
//  			        WHERE
//  			            IS_LEAF = 1
//  			    )
//  			SELECT
//  			    temp2.Ids roleId,
//  			    temp2.Names roleName,
//  			    temp2.roots rootName,
//  			    p.RESOURCE_ID powerId
//  			FROM
//  			    temp2
//  			INNER JOIN
//  				:Com_("D_T0_PERMISSION p")
//  			ON
//  			    temp2.ids = p.PRINCIPAL_ID
//  			order by 
//  				roleId,rootName,powerId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_TYPE");
  String __sJT_2 = Com_("D_T0_ROLE");
  String __sJT_3 = Com_("D_T0_PERMISSION p");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SysUserBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new powerInfoIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:474^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(iter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("roleId", iter.roleId());
			node.put("roleName", iter.roleName());
			node.put("rootName", iter.rootName());
			node.put("powerId", iter.powerId());
			nodeLst.add(node);
		}
		iter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
//	#sql private iterator powerInfoIter(
//			String roleId,
//			String roleName,
//			String powerName,
//			String powerRoot,
//			String rootName
//			);
//	public List<Map> powerSearch() 
//			throws NoPrivilegeException,SQLException,NoConnection {
//		DefaultContext myCtx = getDefaultContext();
//		powerInfoIter iter = null;
//		#sql [myCtx] iter = {
//				WITH
//			    roles AS
//			    (
//			        SELECT
//			            TYPE_ID   AS id ,
//			            TYPE_NAME AS name,
//			            'root'    AS parent,
//			            'btype'   AS type
//			        FROM
//			        	:{Com_("D_T2_BRANCH_TYPE")}
//			        UNION ALL
//			        SELECT
//			            r.ROLE_ID     AS id ,
//			            r.ROLE_NAME   AS name,
//			            r.BRANCH_TYPE AS parent,
//			            'role'        AS type
//			        FROM
//			        	:{Com_("D_T0_ROLE")} r
//			    )
//			    ,
//			    temp1 AS
//			    (
//			        SELECT
//			            ID,
//			            NAME,
//			            PARENT,
//			            TYPE,
//			            CONNECT_BY_ROOT NAME "ROOT",
//			            CONNECT_BY_ISLEAF    is_leaf,
//			            LEVEL,
//			            SYS_CONNECT_BY_PATH(ID, '/') "PATH"
//			        FROM
//			            roles u START WITH PARENT = 'root' CONNECT BY PRIOR ID = PARENT
//			    )
//			    ,
//			    temp2 AS
//			    (
//			        SELECT
//			            t1.ID ids,
//			            t1.name names,
//			            t1.type types,
//			            t1.root roots
//			        FROM
//			            temp1 t1
//			        WHERE
//			            IS_LEAF = 1
//			    )
//			    ,
//			    menu AS
//			    (
//			        SELECT
//			            ID,
//			            NAME,
//			            PARENT_ID,
//			            URL,
//			            CONNECT_BY_ROOT NAME         "ROOT",
//			            CONNECT_BY_ISLEAF            is_leaf,
//			            LEVEL                        levels,
//			            SYS_CONNECT_BY_PATH(ID, '/') "PATH"
//			        FROM
//			            (
//			                SELECT
//			                    *
//			                FROM
//			                	:{Com_("D_T0_MENU")} m
//			                WHERE
//			                    m.PRIORITY !=0 ) m START WITH m.PARENT_ID IS NULL CONNECT BY PRIOR m.ID =
//			            m.PARENT_ID ORDER SIBLINGS BY m.PRIORITY DESC
//			    )
//			    ,
//			    results AS
//			    (
//			        SELECT
//			            t2.*,
//			            m.name,
//			            m.ROOT,
//			            m.LEVELS,
//			            CASE
//			                WHEN p.RESOURCE_ID IS NULL
//			                THEN 0
//			                ELSE 1
//			            END AS checked
//			        FROM
//			            menu m
//			        LEFT JOIN
//			        	:{Com_("D_T0_PERMISSION")} p
//			        ON
//			            p.RESOURCE_ID= m.id
//			        inner JOIN
//			            temp2 t2
//			        ON
//			            p.PRINCIPAL_TYPE = t2.types
//			        AND p.PRINCIPAL_ID = t2.ids
//			    )
//			SELECT
//			    s.ids roleId,
//				s.names roleName,
//				s.roots rootName,
//				s.name powerName,
//				s.root powerRoot
//			FROM
//			    results s
//			WHERE
//			    s.CHECKED = 1
//			AND s.LEVELS = 3
//			order by s.ids,s.root
//		};
//		List<Map> nodeLst = new ArrayList<Map>();
//		while(iter.next()) {
//			Map<String, String> node = new HashMap<String, String>();
//			node.put("roleId", iter.roleId());
//			node.put("roleName", iter.roleName());
//			node.put("rootName", iter.rootName());
//			node.put("powerName", iter.powerName());
//			node.put("powerRoot", iter.powerRoot());
//			nodeLst.add(node);
//		}
//		iter.close();
//		closeDefaultContext(myCtx);
//		return nodeLst;
//		
//	}
	
}/*@lineinfo:generated-code*/class SysUserBean_SJProfileKeys 
{
  private static SysUserBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SysUserBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SysUserBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.setting.SysUserBean_SJProfile0");
  }
}
