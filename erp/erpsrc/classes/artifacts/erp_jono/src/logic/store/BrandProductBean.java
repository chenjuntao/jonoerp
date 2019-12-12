/*@lineinfo:filename=BrandProductBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 15, 2015 by liyzh
 * Last edited on Jul 15, 2015 by liyzh
 * 
 * 说明： 多品牌出品信息
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.store.BrandProduct;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.hq.config.item.vo.ItemVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;

public class BrandProductBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(BrandProductBean.class);
	
	public int saveEntity(BrandProduct product)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String brandId = product.getBrandId();
		String itemId = product.getItemId();

		/*@lineinfo:generated-code*//*@lineinfo:46^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT
//  			INTO
//  			:Com_("D_T2_BRAND_PRODUCT")
//  			    (
//  			        BRAND_ID,
//  			        ITEM_ID
//  			    )
//  			    VALUES
//  			    (
//  			    	:brandId,
//  			    	:itemId
//  			    )
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_PRODUCT");
  String __sJT_2 = brandId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandProductBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:59^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 删除除此之外的其它记录
	 */
	public int deleteExcept(String brandId, List<String> itemIdLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " 1=1 ";
		if (!itemIdLst.isEmpty()) {
			query = " ITEM_ID NOT IN ('" + StringUtils.join(itemIdLst, ",").replaceAll(",", "','") + "') ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:76^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_BRAND_PRODUCT")
//  			WHERE
//  				BRAND_ID = :brandId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_PRODUCT");
  String __sJT_2 = brandId;
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandProductBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:83^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 启停用出品
	 */
	public int enableItem(String itemId, String itemType, Integer enabled, java.util.Date effectDate)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		Date sDate = SqlDateUtil.getSqlDate(effectDate);
		/*@lineinfo:generated-code*//*@lineinfo:97^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_ITEM_META")
//  			SET
//  			   	ENABLED = :enabled,
//  			   	EFFECT_DATE = :sDate
//  			WHERE 
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  Integer __sJT_2 = enabled;
  Date __sJT_3 = sDate;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandProductBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setIntWrapper(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:105^3*/
		
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:112^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ProductIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ProductIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    brand_idNdx = findColumn("brand_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
  }
  public String brand_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(brand_idNdx);
  }
  private int brand_idNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:112^85*/
	
	public List<Map> query(String brandId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ProductIter productIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] productIter = { SELECT
//  			    p.*,
//  			    m.ITEM_NAME
//  			FROM
//  			:Com_("D_T2_BRAND_PRODUCT") p
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  				m.ITEM_ID = p.ITEM_ID
//  			WHERE
//  				p.BRAND_ID = :brandId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_PRODUCT");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = brandId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandProductBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      productIter = new ProductIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:130^3*/
		List<Map> productLst = new ArrayList<Map>();
		while(productIter.next()) {
			Map<String, String> product = new HashMap<String, String>();
			product.put("brandId", productIter.brand_id());
			product.put("itemId", productIter.item_id());
			product.put("itemName", productIter.item_name());
			productLst.add(product);
		}
		productIter.close();
		closeDefaultContext(myCtx);
		return productLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:144^2*/

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
    category_nameNdx = findColumn("category_name");
    item_dimensionNdx = findColumn("item_dimension");
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
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:149^29*/
	
	/**
	 * 根据名称或编码查询出品
	 */
	public List<ItemVO> queryProduct(String itemName)   
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:160^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME, 
//  			    m.QUERY_CODE,
//  			    m.CATEGORY_ID,
//  			    c.CATEGORY_NAME,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  				c.CATEGORY_ID = m.CATEGORY_ID
//  			WHERE
//                  m.ITEM_ID LIKE :itemName
//              OR  m.ITEM_NAME LIKE :itemName
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
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = itemName;
  String __sJT_4 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandProductBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:180^3*/
		List<ItemVO> itemLst = new ArrayList<ItemVO>();
		while(itemIter.next()) {
			ItemVO item = new ItemVO();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setQueryCode(itemIter.query_code());
			item.setCategoryId(itemIter.category_id());
			item.setCategoryName(itemIter.category_name());
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
}/*@lineinfo:generated-code*/class BrandProductBean_SJProfileKeys 
{
  private static BrandProductBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BrandProductBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BrandProductBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.BrandProductBean_SJProfile0");
  }
}
