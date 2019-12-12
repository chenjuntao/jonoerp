/*@lineinfo:filename=ProductionCycleBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Nov 05 16:09:04 CST 2014 by pw
 * Last edited on Wed Nov 05 16:09:04 CST 2014 by pw
 * 
 * comment: 半成品/成品生产周期表
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

import pojo.store.ProductionCycle;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchCode;

public class ProductionCycleBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ProductionCycleBean.class);
	
	public int saveEntity(ProductionCycle detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String branchId = detail.getBranchId();
		String itemId = detail.getItemId();
		Double productionCycle = detail.getProductionCycle();

		/*@lineinfo:generated-code*//*@lineinfo:44^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_PRODUCTION_CYCLE") 
//  				(BRANCH_ID, ITEM_ID, PRODUCTION_CYCLE) 
//  			VALUES (:branchId, :itemId, :productionCycle)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_2 = branchId;
  String __sJT_3 = itemId;
  Double __sJT_4 = productionCycle;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductionCycleBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:48^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:58^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T2_PRODUCTION_CYCLE")
//  				WHERE
//  					ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductionCycleBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:64^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int updateEntity(ProductionCycle pcycle)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String branchId = pcycle.getBranchId();
		String itemId = pcycle.getItemId();
		Double productionCycle = pcycle.getProductionCycle();

		/*@lineinfo:generated-code*//*@lineinfo:78^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_PRODUCTION_CYCLE")
//  			SET
//  				PRODUCTION_CYCLE = :productionCycle
//  			WHERE 
//  				BRANCH_ID = :branchId
//  			AND	ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRODUCTION_CYCLE");
  Double __sJT_2 = productionCycle;
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductionCycleBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:86^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:92^2*/

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
    production_cycleNdx = findColumn("production_cycle");
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
  public Double production_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(production_cycleNdx);
  }
  private int production_cycleNdx;
  public Double inventorys_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventorys_cycleNdx);
  }
  private int inventorys_cycleNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:100^27*/
	
	public List<Map> queryItem(String cfCode, String itemName)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:108^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.*,
//  			    NVL(p.PRODUCTION_CYCLE,0) PRODUCTION_CYCLE,
//  			    NVL(s.INVENTORYS_CYCLE,0) INVENTORYS_CYCLE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_PRODUCTION_CYCLE") p
//  			ON
//  			    m.ITEM_ID = p.ITEM_ID
//      		LEFT JOIN
//      		:Com_("D_T2_SUPPLY_CYCLE") s
//  			ON
//  			    m.ITEM_ID = s.ITEM_ID
//  			AND s.BRANCH_ID = :cfCode
//  			WHERE
//  			    p.BRANCH_ID = :cfCode
//  			AND (
//  			        m.ITEM_ID LIKE :itemName
//  			    OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_3 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_4 = cfCode;
  String __sJT_5 = cfCode;
  String __sJT_6 = itemName;
  String __sJT_7 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductionCycleBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:130^17*/
		List<Map> itemLst = processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:137^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ItemId 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemId(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    branch_idNdx = findColumn("branch_id");
    production_cycleNdx = findColumn("production_cycle");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public Integer production_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(production_cycleNdx);
  }
  private int production_cycleNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:138^46*/
	
	public List<Map> queryById(String itemid)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemId itemId = null;
		/*@lineinfo:generated-code*//*@lineinfo:144^3*/

//  ************************************************************
//  #sql [myCtx] itemId = { SELECT
//  			    p.*
//  			    FROM
//  			    :Com_("D_T2_PRODUCTION_CYCLE") p
//  			WHERE
//  				p.ITEM_ID = :itemid
//  			ORDER BY
//  			    p.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_2 = itemid;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductionCycleBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemId = new ItemId(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:153^3*/
		List<Map> itemLst = processId(itemId);
		itemId.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	public List<Map> queryItemByCate(String cfCode, String cateId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:164^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.*,
//  			    NVL(p.PRODUCTION_CYCLE,0) PRODUCTION_CYCLE,
//  			    NVL(s.INVENTORYS_CYCLE,0) INVENTORYS_CYCLE
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_PRODUCTION_CYCLE") p
//  			ON
//  			    m.ITEM_ID = p.ITEM_ID
//  			AND p.BRANCH_ID = :cfCode
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLY_CYCLE") s
//  			ON
//  			    m.ITEM_ID = s.ITEM_ID
//  			AND s.BRANCH_ID = :cfCode
//  			WHERE
//  			    m.CATEGORY_ID = :cateId
//  			ORDER BY
//  			    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_3 = cfCode;
  String __sJT_4 = Com_("D_T2_SUPPLY_CYCLE");
  String __sJT_5 = cfCode;
  String __sJT_6 = cateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductionCycleBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:184^17*/
		List<Map> itemLst = processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	public Integer queryCycle(String cfCode, String itemId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Integer cycle = null;
		/*@lineinfo:generated-code*//*@lineinfo:195^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    h.PRODUCTION_CYCLE
//  			
//  			FROM
//  			:Com_("D_T2_PRODUCTION_CYCLE") p
//  			WHERE
//  			    ITEM_ID = :itemId
//  			AND p.BRANCH_ID = :cfCode
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_2 = itemId;
  String __sJT_3 = cfCode;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductionCycleBean_SJProfileKeys.getKey(0), 6);
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
    cycle = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:205^3*/
		closeDefaultContext(myCtx);
		return cycle;
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
			item.put("pcycle", itemIter.production_cycle());
			item.put("inventorysCycle", itemIter.inventorys_cycle());
			itemLst.add(item);
		}
		return itemLst;
	}
	
	private List<Map> processId(ItemId itemId) 
			throws SQLException {
		List<Map> itemLst = new ArrayList<Map>();
		while(itemId.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", itemId.item_id());
			item.put("branchId", itemId.branch_id());
			item.put("pcycle", itemId.production_cycle());
			itemLst.add(item);
		}
		return itemLst;
	}
}/*@lineinfo:generated-code*/class ProductionCycleBean_SJProfileKeys 
{
  private static ProductionCycleBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ProductionCycleBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ProductionCycleBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.ProductionCycleBean_SJProfile0");
  }
}
