/*@lineinfo:filename=ObservationHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Aug 04 17:01:18 CST 2015 by pw
 * Last edited on Tue Aug 04 17:01:18 CST 2015 by pw
 * 
 * comment: 出品岗位观察表头
 */
package logic.form;

import java.sql.Date;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ObservationHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ObservationHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ObservationHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    part_idNdx = findColumn("part_id");
    part_nameNdx = findColumn("part_name");
    part_remarkNdx = findColumn("part_remark");
    is_templateNdx = findColumn("is_template");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String part_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(part_idNdx);
  }
  private int part_idNdx;
  public String part_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(part_nameNdx);
  }
  private int part_nameNdx;
  public String part_remark() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(part_remarkNdx);
  }
  private int part_remarkNdx;
  public String is_template() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(is_templateNdx);
  }
  private int is_templateNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^59*/
	
	public int saveEntity(ObservationHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String itemId = header.getItemId();
		String partId = header.getPartId();
		String partName = header.getPartName();
		String partRemark = header.getPartRemark();
		String isTemplate = header.getIsTemplate();

		/*@lineinfo:generated-code*//*@lineinfo:49^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_OBSERVATION_HEADER") 
//  				(ITEM_ID, PART_ID, PART_NAME, PART_REMARK,IS_TEMPLATE) 
//  			VALUES (:itemId, :partId, :partName, :partRemark, :isTemplate)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  String __sJT_2 = itemId;
  String __sJT_3 = partId;
  String __sJT_4 = partName;
  String __sJT_5 = partRemark;
  String __sJT_6 = isTemplate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:53^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ObservationHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String itemId = header.getItemId();
		String partId = header.getPartId();
		String partName = header.getPartName();
		String partRemark = header.getPartRemark();

		/*@lineinfo:generated-code*//*@lineinfo:68^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_OBSERVATION_HEADER")
//  			SET
//  				PART_NAME = :partName,
//  				PART_REMARK = :partRemark
//  			WHERE 
//  				ITEM_ID = :itemId
//  				
//  				AND PART_ID = :partId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  String __sJT_2 = partName;
  String __sJT_3 = partRemark;
  String __sJT_4 = itemId;
  String __sJT_5 = partId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:78^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateTemplate(ObservationHeader header)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String partId = header.getPartId();
		String partName = header.getPartName();
		String partRemark = header.getPartRemark();
		
		/*@lineinfo:generated-code*//*@lineinfo:92^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_OBSERVATION_HEADER")
//  			SET
//  				PART_NAME = :partName,
//  				PART_REMARK = :partRemark
//  			WHERE 
//  				PART_ID = :partId AND IS_TEMPLATE = 'Y'
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  String __sJT_2 = partName;
  String __sJT_3 = partRemark;
  String __sJT_4 = partId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:100^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String itemId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:110^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			 FROM
//  			 :Com_("D_T2_OBSERVATION_HEADER")
//  			WHERE
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:116^3*/
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	

	public Integer count(String itemId)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		Integer total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:130^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T2_OBSERVATION_HEADER") h
//  			WHERE
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 4);
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
    total = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:137^3*/
		
		if(total == null){
			total  = 0;
		}
		
		closeDefaultContext(myCtx);
		return total;
	}
	
	public Integer countTemplate()
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		Integer total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:153^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				count(*) 
//  			FROM
//  			:Com_("D_T2_OBSERVATION_HEADER") h
//  			WHERE
//  				IS_TEMPLATE = 'Y'
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
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
    total = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:160^3*/
		
		if(total == null){
			total  = 0;
		}
		
		closeDefaultContext(myCtx);
		return total;
	}
	
	public List<ObservationHeader> queryByItemId(String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:174^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_OBSERVATION_HEADER") 
//  			WHERE
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:181^3*/
		List<ObservationHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ObservationHeader> loadTemplate() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:192^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  					*
//  				FROM
//  				:Com_("D_T2_OBSERVATION_HEADER")
//  				WHERE
//  					IS_TEMPLATE = 'Y'
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
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

/*@lineinfo:user-code*//*@lineinfo:199^3*/
		List<ObservationHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ObservationHeader> loadData(String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:210^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  					*
//  				FROM
//  				:Com_("D_T2_OBSERVATION_HEADER")
//  				WHERE
//  					IS_TEMPLATE != 'Y' AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_HEADER");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:217^3*/
		List<ObservationHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private List<ObservationHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ObservationHeader> headerLst = new ArrayList<ObservationHeader>();
		while(headerIter.next()) {
			ObservationHeader header = new ObservationHeader();
			header.setItemId(headerIter.item_id());
			header.setPartId(headerIter.part_id());
			header.setPartName(headerIter.part_name());
			header.setPartRemark(headerIter.part_remark());
			header.setIsTemplate(headerIter.is_template());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ObservationHeaderBean_SJProfileKeys 
{
  private static ObservationHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ObservationHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ObservationHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ObservationHeaderBean_SJProfile0");
  }
}
