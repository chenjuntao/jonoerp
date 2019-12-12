/*@lineinfo:filename=SupplierBranchItemBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Feb 07 11:17:04 CST 2015 by lyz
 * Last edited on Sat Feb 07 11:17:04 CST 2015 by lyz
 * 
 * comment: 供应商与门店以及商品的对应关系
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

import pojo.store.SupplierBranchItem;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class SupplierBranchItemBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SupplierBranchItemBean.class);
	
	public int saveEntity(SupplierBranchItem sbiRelation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String supplierId = sbiRelation.getSupplierId();
		String branchId = sbiRelation.getBranchId();
		String itemId = sbiRelation.getItemId();
		Integer priority = sbiRelation.getPriority();

		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_SUPPLIER_BRANCH_ITEM") 
//  				(SUPPLIER_ID, BRANCH_ID, ITEM_ID, PRIORITY) 
//  			VALUES (:supplierId, :branchId, :itemId, :priority)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  Integer __sJT_5 = priority;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:49^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(String supplierId, String branchId, String itemId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		if (exist(supplierId, branchId, itemId, myCtx)) {
			// 设置新的主供应商
			/*@lineinfo:generated-code*//*@lineinfo:60^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  				SET
//  					PRIORITY = 0
//  				WHERE
//  					sbi.SUPPLIER_ID = :supplierId
//  				AND sbi.BRANCH_ID = :branchId
//  				AND sbi.ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:69^4*/
		} else {
			//插入一条记录
			/*@lineinfo:generated-code*//*@lineinfo:72^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_SUPPLIER_BRANCH_ITEM") 
//  					(SUPPLIER_ID, BRANCH_ID, ITEM_ID, PRIORITY) 
//  				VALUES (:supplierId, :branchId, :itemId, 0)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:76^4*/
			// 使用进货价初始化供应商的价格
			initSupplierPrice(myCtx, itemId, supplierId);
		}
		// 取消以前的主供应商
		/*@lineinfo:generated-code*//*@lineinfo:81^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			SET
//  				PRIORITY = 1
//  			WHERE
//  				sbi.SUPPLIER_ID != :supplierId
//  			AND sbi.BRANCH_ID = :branchId
//  			AND sbi.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:90^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 使用进货价初始化供应商的价格
	 */
	private void initSupplierPrice(DefaultContext myCtx, String itemId, String supplierId)
			throws NoPrivilegeException, SQLException, NoConnection {
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:102^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				count(*)  
//  			FROM
//  			:Com_("D_T2_ITEM_PRICE") 
//  			WHERE
//  				ITEM_ID = :itemId 
//  			AND PRICE_TYPE = 'SUPPLIER'
//  			AND SUPPLIER_ID = :supplierId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = itemId;
  String __sJT_3 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:111^3*/
		if (total == 0) { // 如果数据不存在
			Double itemPrice = queryPurchasePrice(myCtx, itemId);
			/*@lineinfo:generated-code*//*@lineinfo:114^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_ITEM_PRICE") 
//  					(ITEM_ID, ITEM_PRICE, PRICE_TYPE, SUPPLIER_ID, IS_CURRENT) 
//  				VALUES (:itemId, :itemPrice, 'SUPPLIER', :supplierId, 1)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = itemId;
  Double __sJT_3 = itemPrice;
  String __sJT_4 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:118^4*/
		}
	}

	/*@lineinfo:generated-code*//*@lineinfo:122^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PriceIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PriceIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_priceNdx = findColumn("item_price");
  }
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:122^57*/
	
	/**
	 * 获取相应的进货价
	 */
	private Double queryPurchasePrice(DefaultContext myCtx, String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
		PriceIter priceIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:130^3*/

//  ************************************************************
//  #sql [myCtx] priceIter = { SELECT
//  				ITEM_PRICE
//  			FROM
//  			:Com_("D_T2_ITEM_PRICE") 
//  			WHERE
//  				ITEM_ID = :itemId 
//  			AND PRICE_TYPE = 'PURCHASE'
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      priceIter = new PriceIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:138^3*/
		Double itemPrice = 0.0;
		while (priceIter.next()) {
			itemPrice = priceIter.item_price();
			break;
		}
		priceIter.close();
		
		return itemPrice;
	}

	public int deleteEntity(String supplierId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:153^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM")
//  			WHERE
//  				SUPPLIER_ID = :supplierId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:159^3*/
		/*@lineinfo:generated-code*//*@lineinfo:160^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_ITEM_PRICE")
//  			WHERE
//  				SUPPLIER_ID = :supplierId
//  			AND
//  				PRICE_TYPE = 'SUPPLIER'
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:168^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteUniqueEntity(String branchId,String itemId,String supplierId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:178^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  					FROM
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM")
//  				WHERE
//  					SUPPLIER_ID = :supplierId and BRANCH_ID = :branchId and ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 9);
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

/*@lineinfo:user-code*//*@lineinfo:184^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	
	public int deleteItemEntity(String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:195^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM")
//  				WHERE
//  					ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 10);
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

/*@lineinfo:user-code*//*@lineinfo:201^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int deleteByBranchId(String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:211^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  					:Com_("D_T2_SUPPLIER_BRANCH_ITEM")
//  				WHERE
//  					BRANCH_ID = :branchId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 11);
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

/*@lineinfo:user-code*//*@lineinfo:217^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int deleteBySupplierId(String supplierId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:227^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  					:Com_("D_T2_SUPPLIER_BRANCH_ITEM")
//  				WHERE
//  					SUPPLIER_ID = :supplierId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 12);
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

/*@lineinfo:user-code*//*@lineinfo:233^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public boolean exist(String supplierId, String branchId, String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Integer total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:243^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			WHERE
//  				sbi.SUPPLIER_ID = :supplierId
//  			AND sbi.BRANCH_ID = :branchId
//  			AND sbi.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 13);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:252^3*/
		closeDefaultContext(myCtx);
		
		if (total > 0) {
			return true;
		}
		return false;
	}
	
	public boolean exist(String supplierId, String branchId, String itemId, DefaultContext myCtx) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Integer total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:264^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			WHERE
//  				sbi.SUPPLIER_ID = :supplierId
//  			AND sbi.BRANCH_ID = :branchId
//  			AND sbi.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 14);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:273^3*/
		
		if (total > 0) {
			return true;
		}
		return false;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:281^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class SupplierIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SupplierIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    supplier_idNdx = findColumn("supplier_id");
    supplier_nameNdx = findColumn("supplier_name");
    query_codeNdx = findColumn("query_code");
    addressNdx = findColumn("address");
    contact_manNdx = findColumn("contact_man");
    telephoneNdx = findColumn("telephone");
    priorityNdx = findColumn("priority");
    item_priceNdx = findColumn("item_price");
  }
  public String supplier_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplier_idNdx);
  }
  private int supplier_idNdx;
  public String supplier_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplier_nameNdx);
  }
  private int supplier_nameNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(addressNdx);
  }
  private int addressNdx;
  public String contact_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(contact_manNdx);
  }
  private int contact_manNdx;
  public String telephone() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(telephoneNdx);
  }
  private int telephoneNdx;
  public Integer priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(priorityNdx);
  }
  private int priorityNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:288^21*/
	
	public List<Map> querySupplier(String branchId, String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SupplierIter supplierIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:294^3*/

//  ************************************************************
//  #sql [myCtx] supplierIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            s.BRANCH_ID      AS supplier_id,
//  			            s.BRANCH_NAME    AS supplier_name,
//  			            s.QUERY_CODE     AS query_code,
//  			            s.BRANCH_ADDRESS AS address,
//  			            s.CONTACT_MAN,
//  			            s.TELEPHONE,
//  			            sbi.PRIORITY
//  			        FROM
//  			        :Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			        LEFT JOIN
//  			        :Com_("D_T2_BRANCH") s
//  			        ON
//  			            sbi.SUPPLIER_ID = s.BRANCH_ID
//  			        WHERE
//  			            sbi.BRANCH_ID = :branchId
//  			        AND sbi.ITEM_ID = :itemId
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            ITEM_ID,
//  			            SUM(ITEM_PRICE) AS ITEM_PRICE,
//  			            SUPPLIER_ID
//  			        FROM
//  			            (
//  			                SELECT
//  			                    sbi.ITEM_ID,
//  			                    0 AS ITEM_PRICE,
//  			                    sbi.SUPPLIER_ID
//  			                FROM
//  			                :Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			                INNER JOIN
//  			                    a
//  			                ON
//  			                    a.supplier_id = sbi.SUPPLIER_ID
//  			                WHERE
//  			                    sbi.ITEM_ID = :itemId
//  			                GROUP BY
//  			                    sbi.supplier_id,
//  			                    sbi.ITEM_ID
//  			                UNION ALL
//  			                SELECT
//  			                    p.ITEM_ID,
//  			                    p.ITEM_PRICE,
//  			                    p.SUPPLIER_ID
//  			                FROM
//  			                :Com_("D_T2_ITEM_PRICE") p
//  			                INNER JOIN
//  			                    a
//  			                ON
//  			                    a.supplier_id = p.SUPPLIER_ID
//  			                WHERE
//  			                    p.PRICE_TYPE = 'SUPPLIER'
//  			                AND p.ITEM_ID = :itemId)
//  			        GROUP BY
//  			            supplier_id,
//  			            ITEM_ID
//  			    )
//  			SELECT
//  			    a.*,
//  			    b.item_price
//  			FROM
//  			    a
//  			LEFT JOIN
//  			    b
//  			ON
//  			    a.supplier_id = b.supplier_id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  String __sJT_5 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_6 = itemId;
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 15);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      supplierIter = new SupplierIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:366^37*/
		List<Map> supplierLst = processIter(supplierIter);
		supplierIter.close();
		closeDefaultContext(myCtx);
		return supplierLst;
	}
	
	private List<Map> processIter(SupplierIter supplierIter) 
			throws SQLException {
		List<Map> supplierLst = new ArrayList<Map>();
		while(supplierIter.next()) {
			Map<String, Object> supplier = new HashMap<String, Object>();
			supplier.put("supplierId", supplierIter.supplier_id());
			supplier.put("supplierName", supplierIter.supplier_name());
			supplier.put("queryCode", supplierIter.query_code());
			supplier.put("address", supplierIter.address());
			supplier.put("contactMan", supplierIter.contact_man());
			supplier.put("telephone", supplierIter.telephone());
			supplier.put("priority", supplierIter.priority());
			supplier.put("itemPrice", supplierIter.item_price());
			supplierLst.add(supplier);
		}
		return supplierLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:391^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class mainSupplierIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public mainSupplierIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    supplier_idNdx = findColumn("supplier_id");
    supplierNdx = findColumn("supplier");
  }
  public String supplier_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplier_idNdx);
  }
  private int supplier_idNdx;
  public String supplier() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplierNdx);
  }
  private int supplierNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:391^82*/
	
	public Map<String, Object> queryMainSupplier(String branchId, String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Map<String, Object> supplier = new HashMap<String, Object>();
		mainSupplierIter supplierIter =null;
		/*@lineinfo:generated-code*//*@lineinfo:398^3*/

//  ************************************************************
//  #sql [myCtx] supplierIter = { SELECT 
//  					sbi.SUPPLIER_ID,
//  					s.BRANCH_NAME supplier
//  				 FROM
//  			        :Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			      LEFT JOIN
//  			      :Com_("D_T2_BRANCH") s
//  			      ON
//  			      	sbi.SUPPLIER_ID = s.BRANCH_ID
//  			        WHERE 
//  			        sbi.BRANCH_ID = :branchId
//  					AND sbi.ITEM_ID = :itemId
//  					AND sbi.PRIORITY = 0
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = branchId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 16);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      supplierIter = new mainSupplierIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:412^3*/
		while (supplierIter.next()) {
			supplier.put("supplierId", supplierIter.supplier_id());
			supplier.put("supplierName", supplierIter.supplier());
		}
		supplierIter.close();
		closeDefaultContext(myCtx);
		return supplier;
	}
	
		
	/*@lineinfo:generated-code*//*@lineinfo:423^2*/

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
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:428^29*/
	
	public List<Map> queryItem(String supplierId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:434^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.*
//  			FROM
//  			    (
//  			        SELECT
//  			            sbi.ITEM_ID
//  			        FROM
//  			        :Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			        WHERE
//  			            sbi.SUPPLIER_ID = :supplierId
//  			        GROUP BY
//  			            sbi.ITEM_ID ) i
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    i.ITEM_ID = m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 17);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:451^3*/

		List<Map> itemLst = new ArrayList<Map>();
		while (itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", itemIter.item_id());
			item.put("itemName", itemIter.item_name());
			item.put("itemDimension", itemIter.item_dimension());
			item.put("queryCode", itemIter.query_code());
			item.put("categoryId", itemIter.category_id());
			item.put("itemSpecification", itemIter.item_specification());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	
	//----------------------------------------------------------------------------------
	/*@lineinfo:generated-code*//*@lineinfo:471^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class BranchIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BranchIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    query_codeNdx = findColumn("query_code");
    addressNdx = findColumn("address");
    contact_manNdx = findColumn("contact_man");
    telephoneNdx = findColumn("telephone");
    priorityNdx = findColumn("priority");
  }
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
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(addressNdx);
  }
  private int addressNdx;
  public String contact_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(contact_manNdx);
  }
  private int contact_manNdx;
  public String telephone() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(telephoneNdx);
  }
  private int telephoneNdx;
  public Integer priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(priorityNdx);
  }
  private int priorityNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:476^38*/
	
	public List<Map> queryBranch(String supplierId, String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		BranchIter branchIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:482^3*/

//  ************************************************************
//  #sql [myCtx] branchIter = { SELECT
//  			    b.BRANCH_ID,
//  			    b.BRANCH_NAME,
//  			    b.QUERY_CODE,
//  			    b.BRANCH_ADDRESS AS address,
//  			    b.CONTACT_MAN,
//  			    b.TELEPHONE,
//  			    sbi.PRIORITY
//  			FROM
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  			ON
//  			    sbi.BRANCH_ID = b.BRANCH_ID
//  			WHERE
//  			    sbi.SUPPLIER_ID = :supplierId
//  			AND sbi.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = supplierId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 18);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      branchIter = new BranchIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:500^3*/

		List<Map> branchLst = new ArrayList<Map>();
		while(branchIter.next()) {
			Map<String, Object> branch = new HashMap<String, Object>();
			branch.put("branchId", branchIter.branch_id());
			branch.put("branchName", branchIter.branch_name());
			branch.put("queryCode", branchIter.query_code());
			branch.put("address", branchIter.address());
			branch.put("contactMan", branchIter.contact_man());
			branch.put("telephone", branchIter.telephone());
			branch.put("priority", branchIter.priority());
			branchLst.add(branch);
		}
		branchIter.close();
		closeDefaultContext(myCtx);
		return branchLst;
	}
	
	
	//-------------------------------------------------------------------------------
	/*@lineinfo:generated-code*//*@lineinfo:521^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class SupplierItemsIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SupplierItemsIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    supplierIdNdx = findColumn("supplierId");
    supplierNdx = findColumn("supplier");
    itemIdNdx = findColumn("itemId");
    itemNameNdx = findColumn("itemName");
  }
  public String supplierId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplierIdNdx);
  }
  private int supplierIdNdx;
  public String supplier() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplierNdx);
  }
  private int supplierNdx;
  public String itemId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemIdNdx);
  }
  private int itemIdNdx;
  public String itemName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemNameNdx);
  }
  private int itemNameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:525^19*/
	
	public List<Map> querySupplierItemsByBranchId(String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SupplierItemsIter supplierItemsIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:531^3*/

//  ************************************************************
//  #sql [myCtx] supplierItemsIter = { SELECT
//  			    i.SUPPLIER_ID supplierId,
//  			    b.BRANCH_NAME supplier,
//  			    i.ITEM_ID itemId,
//  			    m.ITEM_NAME itemName
//  			FROM
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") i 
//  			INNER JOIN
//  				:Com_("D_T2_BRANCH") b 
//  			ON
//  			    i.SUPPLIER_ID = b.BRANCH_ID
//  			INNER JOIN
//  				:Com_("D_T2_ITEM_META") m 
//  			ON
//  			    i.ITEM_ID = m.ITEM_ID
//  			WHERE
//  			    i.BRANCH_ID = :branchId
//  			AND i.PRIORITY = 0
//  			ORDER BY
//  			    1 ,
//  			    3 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 19);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      supplierItemsIter = new SupplierItemsIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:552^9*/

		List<Map> branchLst = new ArrayList<Map>();
		while(supplierItemsIter.next()) {
			Map<String, Object> branch = new HashMap<String, Object>();
			branch.put("supplierId", supplierItemsIter.supplierId());
			branch.put("supplier", supplierItemsIter.supplier());
			branch.put("itemId", supplierItemsIter.itemId());
			branch.put("itemName", supplierItemsIter.itemName());
			branchLst.add(branch);
		}
		supplierItemsIter.close();
		closeDefaultContext(myCtx);
		return branchLst;
	}
	
	//-----------------------------------------------------
	/*@lineinfo:generated-code*//*@lineinfo:569^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class BranchItemsIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BranchItemsIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branchIdNdx = findColumn("branchId");
    branchNameNdx = findColumn("branchName");
    itemIdNdx = findColumn("itemId");
    itemNameNdx = findColumn("itemName");
    priorityNdx = findColumn("priority");
  }
  public String branchId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchIdNdx);
  }
  private int branchIdNdx;
  public String branchName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchNameNdx);
  }
  private int branchNameNdx;
  public String itemId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemIdNdx);
  }
  private int itemIdNdx;
  public String itemName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemNameNdx);
  }
  private int itemNameNdx;
  public Integer priority() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(priorityNdx);
  }
  private int priorityNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:574^20*/
	
	public List<Map> queryBranchItemsBySupplier(String supplierId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		BranchItemsIter branchItemsIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:580^3*/

//  ************************************************************
//  #sql [myCtx] branchItemsIter = { SELECT
//  			    i.BRANCH_ID   branchId,
//  			    b.BRANCH_NAME branchName,
//  			    i.ITEM_ID     itemId,
//  			    m.ITEM_NAME   itemName,
//  			    i.PRIORITY
//  			FROM
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") i 
//  			INNER JOIN
//  				:Com_("D_T2_BRANCH") b 
//  			ON
//  			    i.BRANCH_ID = b.BRANCH_ID
//  			INNER JOIN
//  				:Com_("D_T2_ITEM_META") m 
//  			ON
//  			    i.ITEM_ID = m.ITEM_ID
//  			WHERE
//  			    i.SUPPLIER_ID = :supplierId
//  			ORDER BY
//  			    1 ,
//  			    3 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierBranchItemBean_SJProfileKeys.getKey(0), 20);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      branchItemsIter = new BranchItemsIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:601^9*/

		List<Map> branchLst = new ArrayList<Map>();
		while(branchItemsIter.next()) {
			Map<String, Object> branch = new HashMap<String, Object>();
			branch.put("branchId", branchItemsIter.branchId());
			branch.put("branchName", branchItemsIter.branchName());
			branch.put("itemId", branchItemsIter.itemId());
			branch.put("itemName", branchItemsIter.itemName());
			branch.put("priority", branchItemsIter.priority());
			branchLst.add(branch);
		}
		branchItemsIter.close();
		closeDefaultContext(myCtx);
		return branchLst;
	}
	
	
}/*@lineinfo:generated-code*/class SupplierBranchItemBean_SJProfileKeys 
{
  private static SupplierBranchItemBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SupplierBranchItemBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SupplierBranchItemBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.SupplierBranchItemBean_SJProfile0");
  }
}
