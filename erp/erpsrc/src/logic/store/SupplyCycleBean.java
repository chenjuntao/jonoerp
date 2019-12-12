/*@lineinfo:filename=SupplyCycleBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Feb 06 10:45:30 CST 2015 by lyz
 * Last edited on Fri Feb 06 10:45:30 CST 2015 by lyz
 * 
 * comment: 供应商供应的原材料的供货周期表
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

import pojo.store.SupplyCycle;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class SupplyCycleBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SupplyCycleBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ScycleIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ScycleIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    item_idNdx = findColumn("item_id");
    supply_cycleNdx = findColumn("supply_cycle");
  }
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Integer supply_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(supply_cycleNdx);
  }
  private int supply_cycleNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^89*/
	
	public int saveEntity(SupplyCycle scycle)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String branchId = scycle.getBranchId();
		String itemId = scycle.getItemId();
		Double supplyCycle = scycle.getSupplyCycle();
		Double inventorysCycle = scycle.getInventorysCycle();

		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_SUPPLY_CYCLE") 
//  				(BRANCH_ID, ITEM_ID, SUPPLY_CYCLE, INVENTORYS_CYCLE) 
//  			VALUES (:branchId, :itemId, :supplyCycle, :inventorysCycle)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_2 = branchId;
  String __sJT_3 = itemId;
  Double __sJT_4 = supplyCycle;
  Double __sJT_5 = inventorysCycle;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplyCycleBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:51^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(SupplyCycle scycle)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String branchId = scycle.getBranchId();
		String itemId = scycle.getItemId();
		Double supplyCycle = scycle.getSupplyCycle();
		Double inventorysCycle = scycle.getInventorysCycle();
		
		/*@lineinfo:generated-code*//*@lineinfo:66^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_SUPPLY_CYCLE")
//  			SET
//  				SUPPLY_CYCLE = :supplyCycle,
//  				INVENTORYS_CYCLE = :inventorysCycle
//  			WHERE 
//  				BRANCH_ID = :branchId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLY_CYCLE");
  Double __sJT_2 = supplyCycle;
  Double __sJT_3 = inventorysCycle;
  String __sJT_4 = branchId;
  String __sJT_5 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplyCycleBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:75^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String branchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:85^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_SUPPLY_CYCLE")
//  			WHERE
//  				BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplyCycleBean_SJProfileKeys.getKey(0), 2);
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
	

	/*@lineinfo:generated-code*//*@lineinfo:98^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_typeNdx = findColumn("item_type");
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
    supply_cycleNdx = findColumn("supply_cycle");
    inventorys_cycleNdx = findColumn("inventorys_cycle");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public Double supply_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(supply_cycleNdx);
  }
  private int supply_cycleNdx;
  public Double inventorys_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventorys_cycleNdx);
  }
  private int inventorys_cycleNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:107^4*/
	
	public List<Map> queryItem(String branchId, String itemName,String itemType)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:115^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.*,
//  			    p.SUPPLY_CYCLE,
//  			    p.INVENTORYS_CYCLE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLY_CYCLE") p
//  			ON
//  			    m.ITEM_ID = p.ITEM_ID
//  		    AND p.BRANCH_ID = :branchId
//  			WHERE
//  			1=1 AND m.ITEM_TYPE =:itemType
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_3 = branchId;
  String __sJT_4 = itemType;
  String __sJT_5 = itemName;
  String __sJT_6 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplyCycleBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:134^3*/
		List<Map> itemLst = processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	public List<Map> queryItemById(String branchId, String itemId)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:146^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.*,
//  			    p.SUPPLY_CYCLE,
//  			    p.INVENTORYS_CYCLE
//  			FROM
//  			:Com_("D_T2_SUPPLY_CYCLE") p		
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = p.ITEM_ID
//  		    AND  p.BRANCH_ID = :branchId
//  			WHERE
//  			   m.ITEM_ID = :itemId
//  			ORDER BY
//  			    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplyCycleBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:161^17*/
		List<Map> itemLst = processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	public List<Map> queryItemByCate(String branchId, String cateId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:172^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.*,
//  			    p.SUPPLY_CYCLE,
//  			    p.INVENTORYS_CYCLE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLY_CYCLE") p
//  			ON
//  			    m.ITEM_ID = p.ITEM_ID
//  		    AND p.BRANCH_ID = :branchId
//  			WHERE
//  			 m.CATEGORY_ID = :cateId
//  			ORDER BY
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_3 = branchId;
  String __sJT_4 = cateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplyCycleBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:188^3*/
		List<Map> itemLst = processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}

	private List<Map> processIter(ItemIter itemIter) 
			throws SQLException {
		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", itemIter.item_id());
			item.put("itemName", itemIter.item_name());
			item.put("queryCode", itemIter.query_code());
			item.put("categoryId", itemIter.category_id());
			item.put("itemDimension", itemIter.item_dimension());
			item.put("itemSpecification", itemIter.item_specification());
			item.put("scycle", itemIter.supply_cycle());
			item.put("inventorysCycle", itemIter.inventorys_cycle());
			itemLst.add(item);
		}
		return itemLst;
	}
}/*@lineinfo:generated-code*/class SupplyCycleBean_SJProfileKeys 
{
  private static SupplyCycleBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SupplyCycleBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SupplyCycleBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.SupplyCycleBean_SJProfile0");
  }
}
