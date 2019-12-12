/*@lineinfo:filename=ItemPriceBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 27, 2014 by liyzh
 * Last edited on Aug 27, 2014 by liyzh
 * 
 * 说明： 原材料/半成品/成品价格表
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

import pojo.store.ItemPrice;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ItemType;

public class ItemPriceBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger("ItemPriceBean");
	
	/**
	 *  companyId由调用者通过参数指定,by cjt
	 */
	public int saveEntity(ItemPrice price, String comId) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return saveEntityImp(price, myCtx);
	}
	
	/**
	 *  companyId默认通过session访问,by cjt
	 */
	public int saveEntity(ItemPrice price) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return saveEntityImp(price, myCtx);
	}
	
	private int saveEntityImp(ItemPrice price, DefaultContext myCtx) throws NoPrivilegeException, SQLException, NoConnection {
		int result = 0;//如果是插入，则返回1；如果是更新，如果返回2；失败则返回0
		
		String itemId = price.getItemId();
		Double itemPrice = price.getItemPrice();
		String priceType = price.getPriceType();
		String supplierId = price.getSupplierId();
		String effectiveFormId = price.getEffectiveFormId();
		Integer isCurrent = price.getIsCurrent();
		
		if (supplierId == null) {
			supplierId = "NULL";
		}
		
		if (effectiveFormId == null) {
			effectiveFormId = "NULL";
		}
		
		if (isCurrent == null) {
			isCurrent = 1;
		}
		
		boolean isExist = exist(itemId, priceType, supplierId);

		try{
			if (!isExist) {
				/*@lineinfo:generated-code*//*@lineinfo:79^5*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_ITEM_PRICE") 
//  						(ITEM_ID, ITEM_PRICE, PRICE_TYPE, SUPPLIER_ID, EFFITIVE_FORM_ID, IS_CURRENT) 
//  					VALUES (:itemId, :itemPrice, :priceType, :supplierId, :effectiveFormId, :isCurrent)
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = itemId;
  Double __sJT_3 = itemPrice;
  String __sJT_4 = priceType;
  String __sJT_5 = supplierId;
  String __sJT_6 = effectiveFormId;
  Integer __sJT_7 = isCurrent;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setIntWrapper(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:83^5*/
				result = 1;
			} else {
				/*@lineinfo:generated-code*//*@lineinfo:86^5*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  					:Com_("D_T2_ITEM_PRICE")
//  					SET
//  						ITEM_PRICE = :itemPrice,
//  					    EFFITIVE_FORM_ID = :effectiveFormId,
//  					    IS_CURRENT = :isCurrent
//  					    WHERE 
//  						ITEM_ID = :itemId 
//  					AND PRICE_TYPE = :priceType
//  				    AND NVL(SUPPLIER_ID, 'NULL') = :supplierId
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  Double __sJT_2 = itemPrice;
  String __sJT_3 = effectiveFormId;
  Integer __sJT_4 = isCurrent;
  String __sJT_5 = itemId;
  String __sJT_6 = priceType;
  String __sJT_7 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:97^5*/
				result = 2;
			}
		}
		catch(Exception ex){
			log.error(ex.getMessage());
		} finally{
			closeDefaultContext(myCtx);
		}
		
		return result;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:110^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class NumIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public NumIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    numNdx = findColumn("num");
  }
  public int num() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(numNdx);
  }
  private int numNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:110^39*/
	public int checkEnabled(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		NumIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:115^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    count(*) num
//  			FROM
//  				:Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  			LEFT JOIN 
//  				:Com_("D_T2_ITEM_META") M
//  			ON
//  				M.ITEM_ID = D.ITEM_ID
//  			WHERE
//  			    d.FORM_ID = :formId
//  			AND
//  				M.enabled != 1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new NumIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:128^3*/
		int count = 0;
		while (detailIter.next()){
			count = detailIter.num();
		}
		detailIter.close();
		closeDefaultContext(myCtx);
		return count;
	}
	
	public int deleteEntity(String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:142^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T2_ITEM_PRICE")
//  				WHERE
//  					ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:148^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	//根据价格组删除物料
	public int deletePriceGroup(String priceGroupId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:159^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T2_ITEM_PRICE")
//  				WHERE
//  				PRICE_TYPE = :priceGroupId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = priceGroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:165^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	/**
	 * 根据出品ID和价格类别获取相应的价格
	 */
	public boolean exist(String itemId, String priceType, String supplierId)
		throws NoPrivilegeException, SQLException, NoConnection {
		Integer total = 0;
		DefaultContext myCtx = null;
		if (TextUtil.isEmpty(itemId) || TextUtil.isEmpty(supplierId) || TextUtil.isEmpty(priceType)){
			return false;
		}
		try{
			myCtx = getDefaultContext();
			
			/*@lineinfo:generated-code*//*@lineinfo:184^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  					count(*)  
//  				FROM
//  				:Com_("D_T2_ITEM_PRICE") 
//  				WHERE
//  					ITEM_ID = :itemId 
//  				AND PRICE_TYPE = :priceType
//  			    AND NVL(SUPPLIER_ID, 'NULL') = :supplierId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = itemId;
  String __sJT_3 = priceType;
  String __sJT_4 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:193^4*/
		}
		catch(Exception ex){
			//log.error(ex.getMessage());
		} finally{
			if(myCtx!=null){
				closeDefaultContext(myCtx);
			}
		}

		if (total > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 根据出品ID和价格类别获取相应的价格
	 */
	public Double getItemPrice(String itemId, String priceType)
		throws NoPrivilegeException, SQLException, NoConnection {
		Double price = null;
		DefaultContext myCtx = null;
		if (TextUtil.isEmpty(itemId) || TextUtil.isEmpty(priceType)){
			return null;
		}
		try {
			myCtx = getDefaultContext();
			
			/*@lineinfo:generated-code*//*@lineinfo:222^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT 
//  					ITEM_PRICE  
//  				FROM
//  				:Com_("D_T2_ITEM_PRICE") 
//  				WHERE
//  					ITEM_ID = :itemId 
//  				AND PRICE_TYPE = :priceType
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = itemId;
  String __sJT_3 = priceType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 6);
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
    price = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:230^4*/
		} catch (SQLException e) {
			/*price = 0.0;
			log.error("itemId ： " + itemId + ", priceType : " + priceType);
			log.error("Error running the query: " + e);
			String sqlState = e.getSQLState();
			log.error("SQL state = " + sqlState);
			e.printStackTrace();*/
		}finally{
			if(myCtx!=null){
				closeDefaultContext(myCtx);
			}
		}
		
		return price;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:247^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class PriceIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PriceIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_priceNdx = findColumn("item_price");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:247^67*/
	
	/**
	 * 调用eg: 初始化价格组的明细数据
	 */
	public List<ItemPrice> query(String priceType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		PriceIter priceIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:257^3*/

//  ************************************************************
//  #sql [myCtx] priceIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_ITEM_PRICE") p
//  			WHERE
//  				PRICE_TYPE = :priceType
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = priceType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:264^3*/
		List<ItemPrice> priceLst = new ArrayList<ItemPrice>();
		while(priceIter.next()) {
			ItemPrice price = new ItemPrice();
			price.setItemId(priceIter.item_id());
			price.setItemPrice(priceIter.item_price());
			priceLst.add(price);
		}
		priceIter.close();
		closeDefaultContext(myCtx);
		return priceLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:277^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    query_codeNdx = findColumn("query_code");
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_priceNdx = findColumn("item_price");
    cost_priceNdx = findColumn("cost_price");
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
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
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
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double cost_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(cost_priceNdx);
  }
  private int cost_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:278^65*/
	
	/**
	 * 调用eg: 设置供应商的供货价
	 * 查询供应商所有可能关联的原料信息（调价记录后，供应商资格设置后），同时去重，价格为空时设置为零
	 */
	public List<Map> queryBySupplier(String supplierId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:288^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME,
//  			    m.QUERY_CODE,
//  			    m.CATEGORY_ID,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    i.ITEM_PRICE,
//  			    p2.ITEM_PRICE cost_price
//  			FROM
//  			    (
//  			        SELECT
//  			            ITEM_ID,
//  			            SUM(ITEM_PRICE) AS ITEM_PRICE
//  			        FROM
//  			            (
//  			                SELECT
//  			                    sbi.ITEM_ID,
//  			                    0 AS ITEM_PRICE
//  			                FROM
//  			                    :Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			                WHERE
//  			                    sbi.SUPPLIER_ID = :supplierId
//  			                GROUP BY
//  			                    sbi.ITEM_ID
//  			                UNION ALL
//  			                SELECT
//  			                    p.ITEM_ID,
//  			                    p.ITEM_PRICE
//  			                FROM
//  			                    :Com_("D_T2_ITEM_PRICE") p
//  			                WHERE
//  			                    p.PRICE_TYPE = 'SUPPLIER'
//  			                AND p.SUPPLIER_ID = :supplierId )
//  			        GROUP BY
//  			            ITEM_ID) i
//  			INNER JOIN
//  			    :Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = i.ITEM_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    i.item_id = p2.item_id
//  			AND p2.PRICE_TYPE = 'PURCHASE'
//  			AND p2.IS_CURRENT = 1
//  			ORDER BY
//  			    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = supplierId;
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:336^17*/
		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", itemIter.item_id());
			item.put("itemName", itemIter.item_name());
			item.put("queryCode", itemIter.query_code());
			item.put("categoryId", itemIter.category_id());
			item.put("itemDimension", itemIter.item_dimension());
			item.put("itemSpecification", itemIter.item_specification());
			item.put("itemPrice", itemIter.item_price());
			item.put("costPrice", itemIter.cost_price());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	//出品售卖价推送到前台（只对售卖价有效），by cjt
	public void PushSalePrice(String itemId, String priceType, Double price)
	throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		if(existTableCTFOODPRICE(myCtx)){
			//先删除旧的售卖价信息
			/*@lineinfo:generated-code*//*@lineinfo:361^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  					:Com_("C_T_FOOD_PRICE")
//  				WHERE
//  					ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("C_T_FOOD_PRICE");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 9);
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

/*@lineinfo:user-code*//*@lineinfo:367^4*/
			//再添加新的售卖价信息
			//属于该售卖价分组的每个门店都插入一条数据
			/*@lineinfo:generated-code*//*@lineinfo:370^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("C_T_FOOD_PRICE")
//  					(BRANCH_ID ,ITEM_ID, ITEM_PRICE) 
//  				(
//  					SELECT BRANCH_ID, :itemId, :price
//  					FROM :Com_("D_T2_PRICE_GROUP_BRANCH")
//  					WHERE PRICE_GROUP_ID = :priceType
//  				)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("C_T_FOOD_PRICE");
  String __sJT_2 = itemId;
  Double __sJT_3 = price;
  String __sJT_4 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_5 = priceType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:378^4*/

			closeDefaultContext(myCtx);
		}
	}
	
	//判断是否存在用于推送出品价格的表
	public boolean existTableCTFOODPRICE (DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		int count = 0;
		String dbName = this.getCompanyId().toUpperCase();
		/*@lineinfo:generated-code*//*@lineinfo:389^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*) 
//  			from dba_tables 
//  			where owner = :dbName
//  			and TABLE_NAME = 'C_T_FOOD_PRICE'
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = dbName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemPriceBean_SJProfileKeys.getKey(0), 11);
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
    count = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:394^3*/
		return count > 0;
	}
}/*@lineinfo:generated-code*/class ItemPriceBean_SJProfileKeys 
{
  private static ItemPriceBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ItemPriceBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ItemPriceBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.ItemPriceBean_SJProfile0");
  }
}
