/*@lineinfo:filename=UserToBranchBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon May 04 16:43:58 CST 2015 by lyz
 * Last edited on Mon May 04 16:43:58 CST 2015 by lyz
 * 
 * comment: 系统用户与部门关联表
 */
package logic.module.setting;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.store.Branch;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.setting.user.pojo.UserToBranch;
import com.tanry.framework.acl.NoPrivilegeException;

public class UserToBranchBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(UserToBranchBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

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
    is_primaryNdx = findColumn("is_primary");
    weightNdx = findColumn("weight");
    user_idNdx = findColumn("user_id");
    branch_idNdx = findColumn("branch_id");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String is_primary() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(is_primaryNdx);
  }
  private int is_primaryNdx;
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
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^115*/
	
	public int saveEntity(UserToBranch relation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String id = relation.getId();
		String isPrimary = relation.getIsPrimary();
		Integer weight = relation.getWeight();
		String userId = relation.getUserId();
		String branchId = relation.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:50^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_USERTOBRANCH") 
//  				(ID, IS_PRIMARY, WEIGHT, USER_ID, BRANCH_ID) 
//  			VALUES (:id, :isPrimary, :weight, :userId, :branchId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOBRANCH");
  String __sJT_2 = id;
  String __sJT_3 = isPrimary;
  Integer __sJT_4 = weight;
  String __sJT_5 = userId;
  String __sJT_6 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToBranchBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:54^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(UserToBranch relation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String id = relation.getId();
		String isPrimary = relation.getIsPrimary();
		Integer weight = relation.getWeight();
		String userId = relation.getUserId();
		String branchId = relation.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:70^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T0_USERTOBRANCH")
//  			SET
//  				ID = :id,
//  				IS_PRIMARY = :isPrimary,
//  				WEIGHT = :weight,
//  				USER_ID = :userId,
//  				BRANCH_ID = :branchId
//  			WHERE 
//  		    	USER_ID = :userId
//  			AND BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOBRANCH");
  String __sJT_2 = id;
  String __sJT_3 = isPrimary;
  Integer __sJT_4 = weight;
  String __sJT_5 = userId;
  String __sJT_6 = branchId;
  String __sJT_7 = userId;
  String __sJT_8 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToBranchBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:82^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteEntity(String userId, List<String> branchIdLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " BRANCH_ID IN ('" + StringUtils.join(branchIdLst, "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:93^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE 
//  			FROM
//  			:Com_("D_T0_USERTOBRANCH")
//  			WHERE
//  				USER_ID = :userId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOBRANCH");
  String __sJT_2 = userId;
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToBranchBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:100^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 检测一个用户是否同时属于多个部门
	 */
	public boolean isMulti(String userId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		int count = 0;
		/*@lineinfo:generated-code*//*@lineinfo:114^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T0_USERTOBRANCH") u
//  		INNER JOIN
//  			:Com_("D_T2_BRANCH") b 
//  		ON
//  		    u.BRANCH_ID = b.BRANCH_ID
//  		AND b.ENABLED = 1
//  		WHERE
//  		    u.USER_ID = :userId };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOBRANCH");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToBranchBean_SJProfileKeys.getKey(0), 3);
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
    count = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:127^26*/
		closeDefaultContext(myCtx);
		
		if (count > 1) {
			return true;
		}
		return false;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:136^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class BranchIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BranchIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    query_codeNdx = findColumn("query_code");
    branch_typeNdx = findColumn("branch_type");
  }
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
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String branch_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_typeNdx);
  }
  private int branch_typeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:137^40*/
	
	/**
	 * 查询一个用户所在的部门列表
	 */
	public List<Branch> queryBranch(String userId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		BranchIter branchIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:146^3*/

//  ************************************************************
//  #sql [myCtx] branchIter = { SELECT
//  			    b.BRANCH_ID,
//  			    '['||b.BRANCH_ID||']'||b.BRANCH_NAME BRANCH_NAME,
//  			    b.QUERY_CODE,
//  			    b.BRANCH_TYPE
//  			FROM
//  				:Com_("D_T0_USERTOBRANCH") u2b
//  			INNER JOIN
//  				:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = u2b.BRANCH_ID
//  			WHERE
//  			    u2b.USER_ID = :userId and b.ENABLED = 1
//  			ORDER BY
//  			    u2b.IS_PRIMARY,b.BRANCH_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOBRANCH");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = userId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToBranchBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      branchIter = new BranchIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:162^3*/
		List<Branch> branchLst = new ArrayList<Branch>();
		while(branchIter.next()) {
			Branch branch = new Branch();
			branch.setBranchId(branchIter.branch_id());
			branch.setBranchName(branchIter.branch_name());
			branch.setQueryCode(branchIter.query_code());
			branch.setBranchType(branchIter.branch_type());
			branchLst.add(branch);
		}
		branchIter.close();
		closeDefaultContext(myCtx);
		return branchLst;
	}
	
	public List<UserToBranch> GetAllUserToBranchs() 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<UserToBranch> result = new ArrayList<UserToBranch>();
		DefaultContext myCtx = getDefaultContext();
		RelationIter relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:182^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  		    	b.*,
//  			    '' BRANCH_ID,
//  			    '' BRANCH_NAME
//  			FROM
//  			:Com_("D_T0_USERTOBRANCH") b
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOBRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToBranchBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:189^3*/

		List<UserToBranch> userLst = processIter(relationIter);
		relationIter.close();
		closeDefaultContext(myCtx);
		return userLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:197^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class UBIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public UBIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    user_idNdx = findColumn("user_id");
    branch_idNdx = findColumn("branch_id");
  }
  public String user_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(user_idNdx);
  }
  private int user_idNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:197^63*/
	public List<UserToBranch> listUserToBranch() 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<UserToBranch> result = new ArrayList<UserToBranch>();
		DefaultContext myCtx = getDefaultContext();
		UBIter relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:203^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  		    	b.*
//  			FROM
//  			:Com_("D_T0_USERTOBRANCH") b
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_USERTOBRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToBranchBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      relationIter = new UBIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:208^3*/

		List<UserToBranch> userLst = new ArrayList();
		try {
			while(relationIter.next()) {
				UserToBranch userToBranch = new UserToBranch();
				userToBranch.setUserId(relationIter.user_id());
				userToBranch.setBranchId(relationIter.branch_id());
				userLst.add(userToBranch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		relationIter.close();
		closeDefaultContext(myCtx);
		return userLst;
	}
	
	private List<UserToBranch> processIter(RelationIter relationIter) 
			throws SQLException {
		List<UserToBranch> relationLst = new ArrayList<UserToBranch>();
		while(relationIter.next()) {
			UserToBranch relation = new UserToBranch();
			relation.setId(relationIter.id());
			relation.setIsPrimary(relationIter.is_primary());
			relation.setWeight(relationIter.weight());
			relation.setUserId(relationIter.user_id());
			relation.setBranchId(relationIter.branch_id());
			relationLst.add(relation);
		}
		return relationLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:241^2*/

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

/*@lineinfo:user-code*//*@lineinfo:241^99*/
	/**
	 * 查询所有用户，按部门类型组织成树形结构
	 */
	public List<Map> queryUser() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		UserIter userIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:249^3*/

//  ************************************************************
//  #sql [myCtx] userIter = { WITH
//  			    users AS
//  			    (
//  					SELECT
//  					    TYPE_ID   AS id ,
//  					    TYPE_NAME AS name,
//  					    'root'    AS parent,
//  					    'btype'    AS type
//  					FROM
//  					:Com_("D_T2_BRANCH_TYPE")
//  					UNION ALL
//  					SELECT
//  					    b.BRANCH_ID   AS id ,
//  					    b.BRANCH_NAME AS name,
//  					    b.BRANCH_TYPE AS parent,
//  					    'branch'      AS type
//  					FROM
//  					:Com_("D_T2_BRANCH") b
//  			        UNION ALL
//  			        SELECT
//  				        u.USER_ID     AS id ,
//  				        u.USER_NAME   AS name,
//  				        u2b.BRANCH_ID AS parent,
//  				        'user'        AS type
//  				    FROM
//  				    :Com_("D_T2_USER") u
//  				    INNER JOIN
//  				    :Com_("D_T0_USERTOBRANCH") u2b
//  				    ON
//  				        u2b.USER_ID = u.USER_ID
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
//  			    users u START WITH PARENT = 'root' CONNECT BY PRIOR ID = PARENT
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_TYPE");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_USER");
  String __sJT_4 = Com_("D_T0_USERTOBRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserToBranchBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:292^3*/
		List<Map> userLst = new ArrayList<Map>();
		while(userIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", userIter.id());
			node.put("name", userIter.name());
			node.put("parent", userIter.parent());
			node.put("type", userIter.type());
			node.put("isLeaf", userIter.is_leaf());
			userLst.add(node);
		}
		userIter.close();
		closeDefaultContext(myCtx);
		return userLst;
	}
}/*@lineinfo:generated-code*/class UserToBranchBean_SJProfileKeys 
{
  private static UserToBranchBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new UserToBranchBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private UserToBranchBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.setting.UserToBranchBean_SJProfile0");
  }
}
