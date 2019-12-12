/*@lineinfo:filename=BoxTypeBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Apr 16 10:36:50 CST 2015 by lyz
 * Last edited on Thu Apr 16 10:36:50 CST 2015 by lyz
 * 
 * comment: 箱子类型(冷藏、非冷藏等)
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.BoxType;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class BoxTypeBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(BoxTypeBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class BoxTypeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BoxTypeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    type_idNdx = findColumn("type_id");
    type_nameNdx = findColumn("type_name");
    volumeNdx = findColumn("volume");
    weightNdx = findColumn("weight");
    enabledNdx = findColumn("enabled");
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
  public Double volume() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(volumeNdx);
  }
  private int volumeNdx;
  public Double weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(weightNdx);
  }
  private int weightNdx;
  public Integer enabled() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(enabledNdx);
  }
  private int enabledNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^115*/
	
	public int saveEntity(BoxType boxType)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String typeId = boxType.getTypeId();
		String typeName = boxType.getTypeName();
		Double volume = boxType.getVolume();
		Double weight = boxType.getWeight();

		/*@lineinfo:generated-code*//*@lineinfo:46^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_BOX_TYPE") 
//  				(TYPE_ID, TYPE_NAME, VOLUME, WEIGHT) 
//  			VALUES (:typeId, :typeName, :volume, :weight)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BOX_TYPE");
  String __sJT_2 = typeId;
  String __sJT_3 = typeName;
  Double __sJT_4 = volume;
  Double __sJT_5 = weight;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BoxTypeBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
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
	
	public int updateEntity(BoxType boxType)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String typeId = boxType.getTypeId();
		String typeName = boxType.getTypeName();
		Double volume = boxType.getVolume();
		Double weight = boxType.getWeight();

		/*@lineinfo:generated-code*//*@lineinfo:65^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BOX_TYPE")
//  			SET
//  				TYPE_ID = :typeId,
//  				TYPE_NAME = :typeName,
//  				VOLUME = :volume,
//  				WEIGHT = :weight
//  			WHERE 
//  				TYPE_ID = :typeId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BOX_TYPE");
  String __sJT_2 = typeId;
  String __sJT_3 = typeName;
  Double __sJT_4 = volume;
  Double __sJT_5 = weight;
  String __sJT_6 = typeId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BoxTypeBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:75^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String typeId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:85^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_BOX_TYPE")
//  			WHERE
//  				TYPE_ID = :typeId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BOX_TYPE");
  String __sJT_2 = typeId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BoxTypeBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:91^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 启停用箱子类型
	 */
	public int enableType(String typeId, Integer enabled)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:104^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BOX_TYPE")
//  			SET
//  			   	ENABLED = :enabled
//  			WHERE 
//  				TYPE_ID = :typeId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BOX_TYPE");
  Integer __sJT_2 = enabled;
  String __sJT_3 = typeId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BoxTypeBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setIntWrapper(2, __sJT_2);
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

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:121^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T2_BOX_TYPE") h
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BOX_TYPE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BoxTypeBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:126^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public List<BoxType> query(Integer enabled) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String query = " 1=1 ";
		if (enabled != null) {
			query = " ENABLED = 1";
		}
		
		BoxTypeIter boxTypeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:141^3*/

//  ************************************************************
//  #sql [myCtx] boxTypeIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_BOX_TYPE") h
//  			WHERE
//  				:query
//  			ORDER BY
//  				TYPE_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BOX_TYPE");
  String __sJT_2 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BoxTypeBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      boxTypeIter = new BoxTypeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:150^3*/
		List<BoxType> boxTypeLst = processIter(boxTypeIter);
		boxTypeIter.close();
		closeDefaultContext(myCtx);
		return boxTypeLst;
	}
	
	public BoxType queryById(String typeId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		BoxTypeIter boxTypeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:161^3*/

//  ************************************************************
//  #sql [myCtx] boxTypeIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_BOX_TYPE") h
//  			WHERE
//  				h.TYPE_ID = :typeId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BOX_TYPE");
  String __sJT_2 = typeId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BoxTypeBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      boxTypeIter = new BoxTypeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:168^3*/
		List<BoxType> boxTypeLst = processIter(boxTypeIter);
		boxTypeIter.close();
		closeDefaultContext(myCtx);
		if (boxTypeLst !=null && !boxTypeLst.isEmpty()) {
			return boxTypeLst.get(0);
		}
		return null;
	}
	
	private List<BoxType> processIter(BoxTypeIter boxTypeIter) 
			throws SQLException {
		List<BoxType> boxTypeLst = new ArrayList<BoxType>();
		while(boxTypeIter.next()) {
			BoxType boxType = new BoxType();
			boxType.setTypeId(boxTypeIter.type_id());
			boxType.setTypeName(boxTypeIter.type_name());
			boxType.setVolume(boxTypeIter.volume());
			boxType.setWeight(boxTypeIter.weight());
			boxType.setEnabled(boxTypeIter.enabled());
			boxTypeLst.add(boxType);
		}
		return boxTypeLst;
	}
}/*@lineinfo:generated-code*/class BoxTypeBean_SJProfileKeys 
{
  private static BoxTypeBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BoxTypeBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BoxTypeBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.BoxTypeBean_SJProfile0");
  }
}
