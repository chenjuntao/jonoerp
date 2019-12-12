/*@lineinfo:filename=DeliveryRegionBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Feb 03 18:04:35 CST 2015 by lyz
 * Last edited on Tue Feb 03 18:04:35 CST 2015 by lyz
 * 
 * comment: 配送区域表
 */
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.DeliveryRegion;
import pojo.store.DeliveryRegionBranch;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.acl.NoPrivilegeException;

public class DeliveryRegionBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(DeliveryRegionBean.class);
	
	//DeliveryRegionBranch相关--------------------------------------------------------
	public int saveRegionBranch(DeliveryRegionBranch groupRelation)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return saveRegionBranchImp(groupRelation, myCtx);
	}
	
	public int saveRegionBranch(DeliveryRegionBranch groupRelation, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return saveRegionBranchImp(groupRelation, myCtx);
	}
	
	private int saveRegionBranchImp(DeliveryRegionBranch groupRelation, DefaultContext myCtx)
		throws NoPrivilegeException, SQLException, NoConnection {
		
		String regionId = groupRelation.getRegionId();
		String branchId = groupRelation.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:54^3*/

//  ************************************************************
//  #sql [myCtx] { MERGE INTO :Com_("D_T2_DELIVERY_REGION_BRANCH") USING dual 
//  			ON (REGION_ID=:regionId AND BRANCH_ID=:branchId)
//  			WHEN NOT MATCHED THEN INSERT (REGION_ID, BRANCH_ID) 
//  			    VALUES (:regionId, :branchId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_2 = regionId;
  String __sJT_3 = branchId;
  String __sJT_4 = regionId;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:59^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateRegionBranch(String odlBranchId, DeliveryRegionBranch groupRelation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String regionId = groupRelation.getRegionId();
		String branchId = groupRelation.getBranchId();
		
		/*@lineinfo:generated-code*//*@lineinfo:72^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH")
//  			SET
//  				REGION_ID = :regionId,
//  				BRANCH_ID = :branchId
//  			WHERE 
//  				BRANCH_ID = :odlBranchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_2 = regionId;
  String __sJT_3 = branchId;
  String __sJT_4 = odlBranchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:80^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteRegionBranch(String branchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:90^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH")
//  			WHERE
//  				BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:96^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	//DeliveryRegion相关---------------------------------------------------------------------
	public int saveRegion(DeliveryRegion region)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return saveRegionImp(region, myCtx);
	}
	public int saveRegion(DeliveryRegion region, String comId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return saveRegionImp(region, myCtx);
	}
	private int saveRegionImp(DeliveryRegion region, DefaultContext myCtx)
		throws NoPrivilegeException, SQLException, NoConnection {
		
		String regionId = region.getRegionId();
		String regionName = region.getRegionName();
		String branchId = region.getBranchId();
		Integer deliveryCycle = region.getDeliveryCycle();

		/*@lineinfo:generated-code*//*@lineinfo:121^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_DELIVERY_REGION") 
//  				(REGION_ID, REGION_NAME, BRANCH_ID, DELIVERY_CYCLE) 
//  			VALUES (:regionId, :regionName, :branchId, :deliveryCycle)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_2 = regionId;
  String __sJT_3 = regionName;
  String __sJT_4 = branchId;
  Integer __sJT_5 = deliveryCycle;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:125^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateRegion(String oldRegionId, DeliveryRegion region)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String regionId = region.getRegionId();
		String regionName = region.getRegionName();
		String branchId = region.getBranchId();
		Integer deliveryCycle = region.getDeliveryCycle();

		/*@lineinfo:generated-code*//*@lineinfo:140^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_DELIVERY_REGION")
//  			SET
//  				REGION_ID = :regionId,
//  				REGION_NAME = :regionName,
//  				BRANCH_ID = :branchId,
//  				DELIVERY_CYCLE = :deliveryCycle
//  			WHERE 
//  				REGION_ID = :oldRegionId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_2 = regionId;
  String __sJT_3 = regionName;
  String __sJT_4 = branchId;
  Integer __sJT_5 = deliveryCycle;
  String __sJT_6 = oldRegionId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setIntWrapper(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:150^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteRegion(String regionId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:160^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION")
//  			WHERE
//  				REGION_ID = :regionId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_2 = regionId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:166^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 启用/停用区域
	 */
	public int enableRegion(String regionId, Integer enabled)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:179^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_DELIVERY_REGION")
//  			SET
//  			   	ENABLED = :enabled
//  			WHERE
//  				REGION_ID = :regionId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  Integer __sJT_2 = enabled;
  String __sJT_3 = regionId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:186^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:192^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class RegionIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public RegionIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    region_idNdx = findColumn("region_id");
    region_nameNdx = findColumn("region_name");
    branch_idNdx = findColumn("branch_id");
    delivery_cycleNdx = findColumn("delivery_cycle");
    enabledNdx = findColumn("enabled");
  }
  public String region_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(region_idNdx);
  }
  private int region_idNdx;
  public String region_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(region_nameNdx);
  }
  private int region_nameNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public Integer delivery_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(delivery_cycleNdx);
  }
  private int delivery_cycleNdx;
  public Integer enabled() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(enabledNdx);
  }
  private int enabledNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:197^19*/
	
	/**
	 * 查询全部区域
	 */
	public List<DeliveryRegion> queryAllRegion() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RegionIter regionIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:206^3*/

//  ************************************************************
//  #sql [myCtx] regionIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION")
//  			ORDER BY
//  				REGION_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      regionIter = new RegionIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:213^3*/
		List<DeliveryRegion> regionLst = processIter(regionIter);
		regionIter.close();
		closeDefaultContext(myCtx);
		return regionLst;
	}

	/**
	 * 根据区域编码regionId查询区域
	 */
	public DeliveryRegion queryRegionById(String regionId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RegionIter regionIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:227^3*/

//  ************************************************************
//  #sql [myCtx] regionIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION") r
//  			WHERE
//  				r.REGION_ID = :regionId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_2 = regionId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      regionIter = new RegionIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:234^3*/
		List<DeliveryRegion> regionLst = processIter(regionIter);
		regionIter.close();
		closeDefaultContext(myCtx);
		if (regionLst !=null && !regionLst.isEmpty()) {
			return regionLst.get(0);
		}
		return null;
	}
	
	/**
	 * 根据区域所属的物流中心编码lcId查询该物流中心下的下所有区域
	 */
	public List<DeliveryRegion> queryRegionByLcId(String lcId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return queryRegionByLcIdImp(lcId, myCtx);
	}
	
	public List<DeliveryRegion> queryRegionByLcId(String lcId, String comId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return queryRegionByLcIdImp(lcId, myCtx);
	}
	private List<DeliveryRegion> queryRegionByLcIdImp(String lcId, DefaultContext myCtx) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		RegionIter regionIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:262^3*/

//  ************************************************************
//  #sql [myCtx] regionIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION") r
//  			WHERE
//  				r.BRANCH_ID = :lcId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_2 = lcId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      regionIter = new RegionIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:269^3*/
		List<DeliveryRegion> regionLst = processIter(regionIter);
		regionIter.close();
		closeDefaultContext(myCtx);
		return regionLst;
	}
	
	
	/**
	 *  根据餐厅Id获取其所属的区域
	 */
	public DeliveryRegion getRegionByBranch(String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RegionIter regionIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:284^3*/

//  ************************************************************
//  #sql [myCtx] regionIter = { SELECT
//  			    r.*
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			INNER JOIN
//  			:Com_("D_T2_DELIVERY_REGION") r
//  			ON
//  			    r.REGION_ID = rb.REGION_ID
//  			WHERE
//  			    rb.BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_2 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryRegionBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      regionIter = new RegionIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:295^3*/
		List<DeliveryRegion> regionLst = processIter(regionIter);
		regionIter.close();
		closeDefaultContext(myCtx);
		if (regionLst !=null && !regionLst.isEmpty()) {
			return regionLst.get(0);
		}
		return null;
	}

	private List<DeliveryRegion> processIter(RegionIter regionIter) throws SQLException {
		List<DeliveryRegion> regionLst = new ArrayList<DeliveryRegion>();
		while(regionIter.next()) {
			DeliveryRegion region = new DeliveryRegion();
			region.setRegionId(regionIter.region_id());
			region.setRegionName(regionIter.region_name());
			region.setBranchId(regionIter.branch_id());
			region.setDeliveryCycle(regionIter.delivery_cycle());
			region.setEnabled(regionIter.enabled());
			regionLst.add(region);
		}
		return regionLst;
	}
	
}/*@lineinfo:generated-code*/class DeliveryRegionBean_SJProfileKeys 
{
  private static DeliveryRegionBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DeliveryRegionBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DeliveryRegionBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.DeliveryRegionBean_SJProfile0");
  }
}
