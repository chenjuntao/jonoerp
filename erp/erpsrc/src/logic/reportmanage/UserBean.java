/*@lineinfo:filename=UserBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class UserBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(UserBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:28^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    user_idNdx = findColumn("user_id");
    user_nameNdx = findColumn("user_name");
    role_nameNdx = findColumn("role_name");
    emailNdx = findColumn("email");
    telephoneNdx = findColumn("telephone");
    genderNdx = findColumn("gender");
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
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
  public String role_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(role_nameNdx);
  }
  private int role_nameNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^3*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("user_id", "String");
		map.put("user_name", "String");
		map.put("role_name", "String");
		map.put("email", "String");
		map.put("telephone", "String");
		map.put("gender", "String");
		map.put("branch_id", "String");
		map.put("branch_name", "String"); 
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("user_id", "u");
		map.put("user_name", "u");
		map.put("role_name", "tr");
		map.put("email", "u");
		map.put("telephone", "u");
		map.put("gender", "u");
		map.put("branch_id", "ub");
		map.put("branch_name", "b"); 
		return map;
	}
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:71^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T2_USER") u
//  		INNER JOIN
//  		    :Com_("D_T0_USERTOBRANCH") ub
//  		ON
//  		    ub.USER_ID = u.USER_ID
//  		INNER JOIN
//  		    :Com_("D_T2_BRANCH") b
//  		ON
//  		    ub.BRANCH_ID = b.BRANCH_ID
//  		LEFT JOIN
//  		    :Com_("D_T0_USERTOROLE") ut
//  		ON
//  		    u.USER_ID = ut.USER_ID
//  		LEFT JOIN
//  		    :Com_("D_T0_ROLE") tr
//  		ON
//  		    ut.ROLE_ID = tr.ROLE_ID
//  		WHERE
//  		    1 = 1 :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_USER");
  String __sJT_2 = Com_("D_T0_USERTOBRANCH");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T0_USERTOROLE");
  String __sJT_5 = Com_("D_T0_ROLE");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:95^30*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:106^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                    SELECT
//  			                        u.*,
//  			                        ub.BRANCH_ID,
//  			                        b.BRANCH_NAME,
//  			                        tr.ROLE_NAME
//  			                    FROM
//  			                        :Com_("D_T2_USER") u
//  			                    INNER JOIN
//  			                        :Com_("D_T0_USERTOBRANCH") ub
//  			                    ON
//  			                        ub.USER_ID = u.USER_ID
//  			                    INNER JOIN
//  			                        :Com_("D_T2_BRANCH") b
//  			                    ON
//  			                        ub.BRANCH_ID = b.BRANCH_ID
//  			                    LEFT JOIN
//  			                        :Com_("D_T0_USERTOROLE") ut
//  			                    ON
//  			                        u.USER_ID = ut.USER_ID
//  			                    LEFT JOIN
//  			                        :Com_("D_T0_ROLE") tr
//  			                    ON
//  			                        ut.ROLE_ID = tr.ROLE_ID
//  			                    WHERE
//  			                        1 = 1 :queryStr :sortStr) t
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
  String __sJT_1 = Com_("D_T2_USER");
  String __sJT_2 = Com_("D_T0_USERTOBRANCH");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T0_USERTOROLE");
  String __sJT_5 = Com_("D_T0_ROLE");
  String __sJT_6 = queryStr;
  String __sJT_7 = sortStr;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, UserBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:144^34*/
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
			header.put("user_id", headerIter.user_id());
			header.put("user_name", headerIter.user_name());
			header.put("role_name", headerIter.role_name());
			header.put("email", headerIter.email());
			header.put("telephone", headerIter.telephone());
			header.put("gender", headerIter.gender());
			header.put("branch_id", headerIter.branch_id());
			header.put("branch_name", headerIter.branch_name());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class UserBean_SJProfileKeys 
{
  private static UserBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new UserBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private UserBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.UserBean_SJProfile0");
  }
}
