/*@lineinfo:filename=FactoryWorkShopBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon Sep 21 10:20:44 CST 2015 by pw
 * Last edited on Mon Sep 21 10:20:44 CST 2015 by pw
 * 
 * comment: 央厂与车间对应关系表
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.FactoryWorkShop;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class FactoryWorkShopBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(FactoryWorkShopBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

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
    factory_idNdx = findColumn("factory_id");
    work_order_idNdx = findColumn("work_order_id");
    workshopNdx = findColumn("workshop");
    priorityNdx = findColumn("priority");
  }
  public String factory_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(factory_idNdx);
  }
  private int factory_idNdx;
  public String work_order_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(work_order_idNdx);
  }
  private int work_order_idNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
  public Integer priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(priorityNdx);
  }
  private int priorityNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^109*/
	
	public int saveEntity(FactoryWorkShop header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String factoryId = header.getFactoryId();
		String workOrderId = header.getWorkOrderId();
		String workshop = header.getWorkshop();
		Integer priority = header.getPriority();

		/*@lineinfo:generated-code*//*@lineinfo:48^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_FACTORY_WORKSHOP") 
//  				(FACTORY_ID, WORK_ORDER_ID, WORKSHOP, PRIORITY) 
//  			VALUES (:factoryId, :workOrderId, :workshop, :priority)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_2 = factoryId;
  String __sJT_3 = workOrderId;
  String __sJT_4 = workshop;
  Integer __sJT_5 = priority;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setIntWrapper(5, __sJT_5);
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
	
	public int updateEntity(FactoryWorkShop header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String factoryId = header.getFactoryId();
		String workOrderId = header.getWorkOrderId();
		String workshop = header.getWorkshop();
		Integer priority = header.getPriority();

		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_FACTORY_WORKSHOP")
//  			SET
//  				WORKSHOP = :workshop
//  			WHERE 
//  				WORK_ORDER_ID = :workOrderId and FACTORY_ID = :factoryId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_2 = workshop;
  String __sJT_3 = workOrderId;
  String __sJT_4 = factoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:74^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:84^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_FACTORY_WORKSHOP")
//  			WHERE
//  			WORK_ORDER_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:90^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:96^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class HeadIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeadIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
    isLeafNdx = findColumn("isLeaf");
    workOrderIdNdx = findColumn("workOrderId");
    workshopNdx = findColumn("workshop");
    priorityNdx = findColumn("priority");
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
  public String isLeaf() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(isLeafNdx);
  }
  private int isLeafNdx;
  public String workOrderId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workOrderIdNdx);
  }
  private int workOrderIdNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
  public Integer priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(priorityNdx);
  }
  private int priorityNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:103^22*/
	
	public List<Map> query()  
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeadIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:109^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            b.BRANCH_ID id,
//  			            b.BRANCH_NAME name,
//  			            'root' parent,
//  			            'N'    isLeaf,
//  			            ''     WORK_ORDER_ID,
//  			            ''     WORKSHOP,
//  			            -1     PRIORITY
//  			        FROM
//  			        :Com_("D_T2_BRANCH") b
//  			        WHERE
//  			            b.BRANCH_TYPE IN ( 'CENTRALFACTORY')
//  			        ORDER BY
//  			            b.BRANCH_TYPE,
//  			            b.BRANCH_ID
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            w.FACTORY_ID id,
//  			            w.WORK_ORDER_ID,
//  			            w.WORKSHOP,
//  			            w.PRIORITY
//  			        FROM
//  			        :Com_("D_T2_FACTORY_WORKSHOP") w
//  			    )
//  			SELECT
//  			    b.id ||b.WORK_ORDER_ID id,
//  			    b.WORKSHOP             name,
//  			    b.id                   parent,
//  			    'Y'                    isLeaf ,
//  			    b.WORK_ORDER_ID workOrderId,
//  			    b.WORKSHOP workshop,
//  			    b.PRIORITY
//  			FROM
//  			    a
//  			LEFT JOIN
//  			    b
//  			ON
//  			    a.id = b.id
//  			UNION ALL
//  			SELECT
//  			    *
//  			FROM
//  			    a
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = Com_("D_T2_FACTORY_WORKSHOP");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeadIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:159^3*/
		List<Map> headerLst = proIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public FactoryWorkShop queryByOrder(String factoryId,Integer priority) 
			throws NoPrivilegeException,SQLException,NoConnection {
			DefaultContext myCtx = getDefaultContext();
			HeaderIter headerIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:170^4*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_FACTORY_WORKSHOP") fw
//  			WHERE
//  				fw.FACTORY_ID =:factoryId 
//  				AND
//  	            fw.PRIORITY =:priority
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_2 = factoryId;
  Integer __sJT_3 = priority;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setIntWrapper(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:179^4*/
			List<FactoryWorkShop> headerLst = processIter(headerIter);
			headerIter.close();
			closeDefaultContext(myCtx);
			if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
			}
			return null;
			}
	
	public FactoryWorkShop queryById(String factoryId,String workOrderId) 
			throws NoPrivilegeException,SQLException,NoConnection {
			DefaultContext myCtx = getDefaultContext();
			HeaderIter headerIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:193^4*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_FACTORY_WORKSHOP") fw
//  			WHERE
//  				fw.FACTORY_ID =:factoryId 
//  				AND
//  	            fw.WORK_ORDER_ID =:workOrderId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_2 = factoryId;
  String __sJT_3 = workOrderId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:202^4*/
			List<FactoryWorkShop> headerLst = processIter(headerIter);
			headerIter.close();
			closeDefaultContext(myCtx);
			if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
			}
			return null;
			}
	
	public List <FactoryWorkShop> queryById(String factoryId) 
			throws NoPrivilegeException,SQLException,NoConnection {
			DefaultContext myCtx = getDefaultContext();
			HeaderIter headerIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:216^4*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_FACTORY_WORKSHOP") fw
//  			WHERE
//  				fw.FACTORY_ID =:factoryId 
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_2 = factoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:223^4*/
			List<FactoryWorkShop> headerLst = processIter(headerIter);
			headerIter.close();
			closeDefaultContext(myCtx);
			if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst;
			}
			return null;
			}
	private List<Map> proIter(HeadIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("id", headerIter.id());
			header.put("name", headerIter.name());
			header.put("parent", headerIter.parent());
			header.put("isLeaf", headerIter.isLeaf());
			header.put("workOrderId", headerIter.workOrderId());
			header.put("workshop", headerIter.workshop());
			header.put("priority", headerIter.priority());
			
			headerLst.add(header);
		}
		return headerLst;
	}
	
	public int setMain(FactoryWorkShop factoryWorkShop)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String workOrderId = factoryWorkShop.getWorkOrderId();
		String factoryId = factoryWorkShop.getFactoryId();
		Integer priority = factoryWorkShop.getPriority();
		
		//更新主车间
		/*@lineinfo:generated-code*//*@lineinfo:260^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_FACTORY_WORKSHOP") fws
//  			SET
//  				priority = :priority
//  			WHERE 
//  			fws.PRIORITY = 0
//  		    AND fws.FACTORY_ID = :factoryId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_FACTORY_WORKSHOP");
  Integer __sJT_2 = priority;
  String __sJT_3 = factoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:268^3*/
		
		//设置主车间
		/*@lineinfo:generated-code*//*@lineinfo:271^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_FACTORY_WORKSHOP") fws
//  			SET
//  				priority = 0
//  			WHERE 
//  			fws.WORK_ORDER_ID = :workOrderId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_2 = workOrderId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FactoryWorkShopBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:278^3*/
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	private List<FactoryWorkShop> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<FactoryWorkShop> headerLst = new ArrayList<FactoryWorkShop>();
		while(headerIter.next()) {
			FactoryWorkShop header = new FactoryWorkShop();
			header.setFactoryId(headerIter.factory_id());
			header.setWorkOrderId(headerIter.work_order_id());
			header.setWorkshop(headerIter.workshop());
			header.setPriority(headerIter.priority());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class FactoryWorkShopBean_SJProfileKeys 
{
  private static FactoryWorkShopBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FactoryWorkShopBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FactoryWorkShopBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.FactoryWorkShopBean_SJProfile0");
  }
}
