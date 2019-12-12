/*@lineinfo:filename=MRPAmountBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 16, 2015 by liyzh
 * Last edited on Jan 16, 2015 by liyzh
 * 
 * 说明： MRP计算用到的相关量
 */
package logic.store;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.NumberUtil;

public class MRPAmountBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(MRPAmountBean.class);
	
	/**
	 * 根据部门id和商品id修改MRP相关的记录是否存在
	 */
	public boolean exist(String itemId, String branchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		int total = 0;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:39^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				count(*) 
//  			FROM
//  			:Com_("D_T2_MRP_AMOUNT") 
//  			where 
//  				ITEM_ID = :itemId
//  			and BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MRP_AMOUNT");
  String __sJT_2 = itemId;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPAmountBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:47^3*/
		closeDefaultContext(myCtx);
		
		if (total == 0) {
			 return false;
		}
		return true;
	}
	
	/**
	 * 根据部门id和商品id修改MRP相关的已分配量，diff为正则增加已分配量，diff为负则减少已分配量
	 */
	public int updateAllocation(String itemId, String branchId, Double diff)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		if (exist(itemId, branchId)) {
			/*@lineinfo:generated-code*//*@lineinfo:63^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T2_MRP_AMOUNT")
//  				SET
//  					ALLOCATION =  nvl(ALLOCATION,0)  + :diff
//  				WHERE
//  					ITEM_ID = :itemId
//  				and BRANCH_ID = :branchId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MRP_AMOUNT");
  Double __sJT_2 = diff;
  String __sJT_3 = itemId;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPAmountBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:71^4*/
		} else {
			//插入一条记录
			Map<String, Object> mrpAmount = new HashMap<String, Object>();
			mrpAmount.put("branchId", branchId);
			mrpAmount.put("itemId", itemId);
			mrpAmount.put("allocation", diff);
			mrpAmount.put("roadCount", 0);
			saveEntity(mrpAmount, myCtx);
		}
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 根据部门id和商品id修改MRP相关的在途量，diff为正则增加在途量，diff为负则减少在途量
	 */
	public int updateRoadCount(String itemId, String branchId, Double diff)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		if (exist(itemId, branchId)) {
			/*@lineinfo:generated-code*//*@lineinfo:94^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T2_MRP_AMOUNT")
//  				SET
//  					ON_THE_WAY =  nvl(ON_THE_WAY,0) + :diff
//  				WHERE
//  					ITEM_ID = :itemId
//  				and BRANCH_ID = :branchId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MRP_AMOUNT");
  Double __sJT_2 = diff;
  String __sJT_3 = itemId;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPAmountBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:102^4*/
		} else {
			//插入一条记录
			Map<String, Object> mrpAmount = new HashMap<String, Object>();
			mrpAmount.put("branchId", branchId);
			mrpAmount.put("itemId", itemId);
			mrpAmount.put("allocation", 0.0);
			mrpAmount.put("roadCount", diff);
			saveEntity(mrpAmount, myCtx);
		}
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int saveEntity(Map mrpAmount, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = (String)mrpAmount.get("branchId");
		String itemId = (String)mrpAmount.get("itemId");
		Double allocation = (Double)mrpAmount.get("allocation");
		Double roadCount = NumberUtil.getDouble(mrpAmount.get("roadCount"));

		/*@lineinfo:generated-code*//*@lineinfo:125^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_MRP_AMOUNT") 
//  				(BRANCH_ID, ITEM_ID, ALLOCATION, ON_THE_WAY) 
//  			VALUES (:branchId, :itemId, :allocation, :roadCount)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MRP_AMOUNT");
  String __sJT_2 = branchId;
  String __sJT_3 = itemId;
  Double __sJT_4 = allocation;
  Double __sJT_5 = roadCount;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MRPAmountBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:129^3*/
		
		return 1;
	}
	
}/*@lineinfo:generated-code*/class MRPAmountBean_SJProfileKeys 
{
  private static MRPAmountBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new MRPAmountBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private MRPAmountBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.MRPAmountBean_SJProfile0");
  }
}
