/*@lineinfo:filename=CompanyBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年1月25日 by cjt
 * Last edited on 2016年1月25日 by cjt
 */

package logic.restapi;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;
import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 公司/企业dao
 */
public class CompanyBean extends ConnectionPool {
	
	private String comId = "com";
	
	/*@lineinfo:generated-code*//*@lineinfo:31^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ComIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ComIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    COMPANY_IDNdx = findColumn("COMPANY_ID");
    COMPANY_NAMENdx = findColumn("COMPANY_NAME");
    DB_NAMENdx = findColumn("DB_NAME");
    CREATORNdx = findColumn("CREATOR");
    REMARKNdx = findColumn("REMARK");
  }
  public String COMPANY_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(COMPANY_IDNdx);
  }
  private int COMPANY_IDNdx;
  public String COMPANY_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(COMPANY_NAMENdx);
  }
  private int COMPANY_NAMENdx;
  public String DB_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(DB_NAMENdx);
  }
  private int DB_NAMENdx;
  public String CREATOR() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CREATORNdx);
  }
  private int CREATORNdx;
  public String REMARK() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(REMARKNdx);
  }
  private int REMARKNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^3*/
	
	public int saveEntity(Map map) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);

		String companyId = map.get("companyId").toString();
		String companyName = map.get("companyName").toString();
		String dbName = map.get("dbName").toString();
		String creator = map.get("creator").toString();
		String remark = map.get("remark").toString();
		
		/*@lineinfo:generated-code*//*@lineinfo:48^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T_COMPANY")
//  				(COMPANY_ID, COMPANY_NAME, DB_NAME, CREATOR, REMARK) 
//  			VALUES 
//  				(:companyId, :companyName, :dbName, :creator, :remark)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_COMPANY");
  String __sJT_2 = companyId;
  String __sJT_3 = companyName;
  String __sJT_4 = dbName;
  String __sJT_5 = creator;
  String __sJT_6 = remark;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CompanyBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:53^4*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<Map> listCompany() throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		ComIter comIter = null;
		List<Map> comLst = new ArrayList<Map>();
		
		/*@lineinfo:generated-code*//*@lineinfo:64^3*/

//  ************************************************************
//  #sql [myCtx] comIter = { SELECT * FROM :Com_("D_T_COMPANY")
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_COMPANY");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CompanyBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      comIter = new ComIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:66^3*/

		while(comIter.next()){
			Map com = new HashMap();
			com.put("companyId", comIter.COMPANY_ID());
			com.put("companyName", comIter.COMPANY_NAME());
			com.put("dbName", comIter.DB_NAME());
			com.put("creator", comIter.CREATOR());
			com.put("remark", comIter.REMARK());
			comLst.add(com);
		}
		comIter.close();
		
		closeDefaultContext(myCtx);
		return comLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:83^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DBNameIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DBNameIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    COMPANY_IDNdx = findColumn("COMPANY_ID");
    DB_NAMENdx = findColumn("DB_NAME");
  }
  public String COMPANY_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(COMPANY_IDNdx);
  }
  private int COMPANY_IDNdx;
  public String DB_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(DB_NAMENdx);
  }
  private int DB_NAMENdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:86^3*/
	public Map<String,String> mapDbName() throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		DBNameIter dbNameIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:91^3*/

//  ************************************************************
//  #sql [myCtx] dbNameIter = { SELECT COMPANY_ID,DB_NAME FROM :Com_("D_T_COMPANY")
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_COMPANY");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CompanyBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      dbNameIter = new DBNameIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:93^3*/

		Map<String,String> com = new HashMap<String,String>();
		while(dbNameIter.next()){
			com.put( dbNameIter.COMPANY_ID(),dbNameIter.DB_NAME());
		}
		dbNameIter.close();
		
		closeDefaultContext(myCtx);
		return com;
	}
	
	public String getDbNameByComId(String companyId) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		String result = null;
		ComIter comIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:109^3*/

//  ************************************************************
//  #sql [myCtx] comIter = { SELECT * FROM :Com_("D_T_COMPANY")
//  			WHERE COMPANY_ID = :companyId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_COMPANY");
  String __sJT_2 = companyId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CompanyBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      comIter = new ComIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:112^3*/

		while(comIter.next()){
			result = comIter.DB_NAME();
		}
		comIter.close();
		
		closeDefaultContext(myCtx);
		return result;
	}
}/*@lineinfo:generated-code*/class CompanyBean_SJProfileKeys 
{
  private static CompanyBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CompanyBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CompanyBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.restapi.CompanyBean_SJProfile0");
  }
}
