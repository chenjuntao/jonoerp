/*@lineinfo:filename=OperationVersionBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Jul 17 16:50:11 CST 2015 by pw
 * Last edited on Fri Jul 17 16:50:11 CST 2015 by pw
 * 
 * comment: 单据操作版本号表
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.OperationVersion;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class OperationVersionBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(OperationVersionBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DetailIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DetailIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    operation_idNdx = findColumn("operation_id");
    operation_nameNdx = findColumn("operation_name");
    operation_timeNdx = findColumn("operation_time");
    versionNdx = findColumn("version");
    operation_contentNdx = findColumn("operation_content");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String operation_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operation_idNdx);
  }
  private int operation_idNdx;
  public String operation_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operation_nameNdx);
  }
  private int operation_nameNdx;
  public Date operation_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(operation_timeNdx);
  }
  private int operation_timeNdx;
  public Integer version() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(versionNdx);
  }
  private int versionNdx;
  public String operation_content() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(operation_contentNdx);
  }
  private int operation_contentNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^156*/
	
	public int saveEntity(OperationVersion detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String operationId = detail.getOperationId();
		String operationName = detail.getOperationName();
		Date operationTime = SqlDateUtil.getSqlDate(detail.getOperationTime());
		Integer version = detail.getVersion();
		String operationContent = detail.getOperationContent();

		/*@lineinfo:generated-code*//*@lineinfo:48^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_OPERATION_VERSION")
//  				(FORM_ID, OPERATION_ID, OPERATION_NAME, OPERATION_TIME, VERSION, OPERATION_CONTENT) 
//  			VALUES (:formId,:operationId, :operationName, :operationTime, :version, :operationContent)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_OPERATION_VERSION");
  String __sJT_2 = formId;
  String __sJT_3 = operationId;
  String __sJT_4 = operationName;
  Date __sJT_5 = operationTime;
  Integer __sJT_6 = version;
  String __sJT_7 = operationContent;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OperationVersionBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:52^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public OperationVersion queryVersion(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		DetailIter detailIter = null;
		OperationVersion operationVersion = new OperationVersion();
		
		/*@lineinfo:generated-code*//*@lineinfo:65^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  		    *
//  		    FROM
//  		    :Com_("D_T0_OPERATION_VERSION") v
//  		    WHERE
//  		        v.FORM_ID = :formId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_OPERATION_VERSION");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OperationVersionBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:71^30*/
		
		if(detailIter.next()) {
			operationVersion.setFormId(detailIter.form_id());
			operationVersion.setOperationId(detailIter.operation_id());
			operationVersion.setOperationName(detailIter.operation_name());
			operationVersion.setOperationTime(SqlDateUtil.getUtilDate(detailIter.operation_time()));
			operationVersion.setVersion(detailIter.version());
			operationVersion.setOperationContent(detailIter.operation_content());
		}
		
		if(operationVersion.getVersion() == null){
			operationVersion.setVersion(1);
		}
		
		detailIter.close();
		closeDefaultContext(myCtx);
		return operationVersion;
	}
	
	public String getMaxFormIndex(String formBody)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		String indexStr = "";
		
		/*@lineinfo:generated-code*//*@lineinfo:97^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    SUBSTR(MAX(m.FORM_ID),-4) 
//  		FROM
//  		:Com_("D_T0_OPERATION_VERSION") m
//  		WHERE
//  		    m.FORM_ID LIKE :formBody };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_OPERATION_VERSION");
  String __sJT_2 = formBody;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OperationVersionBean_SJProfileKeys.getKey(0), 2);
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
    indexStr = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:103^31*/
		 
		 if(indexStr == null){
			 indexStr = "0000";
		 }
		 
		closeDefaultContext(myCtx);
		
		return indexStr;
	}
	
	
	public int updateEntity(OperationVersion detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String operationId = detail.getOperationId();
		String operationName = detail.getOperationName();
		Date operationTime = SqlDateUtil.getSqlDate(detail.getOperationTime());
		Integer version = detail.getVersion();
		String operationContent = detail.getOperationContent();

		/*@lineinfo:generated-code*//*@lineinfo:126^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T0_OPERATION_VERSION") v
//  			SET
//  			    v.OPERATION_ID = :operationId,
//  			    v.OPERATION_NAME = :operationName,
//  			    v.OPERATION_TIME = :operationTime,
//  			    v.OPERATION_CONTENT = :operationContent,
//  				v.VERSION = :version
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_OPERATION_VERSION");
  String __sJT_2 = operationId;
  String __sJT_3 = operationName;
  Date __sJT_4 = operationTime;
  String __sJT_5 = operationContent;
  Integer __sJT_6 = version;
  String __sJT_7 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, OperationVersionBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:137^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	private List<OperationVersion> processIter(DetailIter detailIter) 
			throws SQLException {
		List<OperationVersion> detailLst = new ArrayList<OperationVersion>();
		while(detailIter.next()) {
			OperationVersion detail = new OperationVersion();
			detail.setFormId(detailIter.form_id());
			detail.setOperationId(detailIter.operation_id());
			detail.setOperationName(detailIter.operation_name());
			detail.setOperationTime(SqlDateUtil.getUtilDate(detailIter.operation_time()));
			detail.setVersion(detailIter.version());
			detail.setOperationContent(detailIter.operation_content());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class OperationVersionBean_SJProfileKeys 
{
  private static OperationVersionBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new OperationVersionBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private OperationVersionBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.OperationVersionBean_SJProfile0");
  }
}
