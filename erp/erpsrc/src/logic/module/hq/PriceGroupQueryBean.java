/*@lineinfo:filename=PriceGroupQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 20, 2015 by liyzh
 * Last edited on Jul 20, 2015 by liyzh
 * 
 * 说明： 多品牌多价格组查询价格
 */
package logic.module.hq;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.hq.config.item.vo.ItemVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;

public class PriceGroupQueryBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(PriceGroupQueryBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private static class ProductIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ProductIter(sqlj.runtime.profile.RTResultSet resultSet) 
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
    sale_priceNdx = findColumn("sale_price");
    group_priceNdx = findColumn("group_price");
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
  public Double sale_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sale_priceNdx);
  }
  private int sale_priceNdx;
  public Double group_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(group_priceNdx);
  }
  private int group_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^21*/
	
	/**
	 * 根据价格组编号查询相应的出品信息及分组价格
	 */
	public List<ItemVO> queryProduct(String pgroupId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ProductIter productIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] productIter = { SELECT
//  			    m.*,
//  			    ps.ITEM_PRICE AS sale_price,
//  			    p2.ITEM_PRICE AS group_price
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP") pg
//  			INNER JOIN
//  			:Com_("D_T2_BRAND_PRODUCT") bp
//  			ON
//  			    pg.OWNER = bp.BRAND_ID
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = bp.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") ps
//  			ON
//  			    ps.ITEM_ID = m.ITEM_ID
//  			AND ps.PRICE_TYPE = 'SALE'
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.ITEM_ID = m.ITEM_ID
//  			AND p2.PRICE_TYPE = pg.PRICE_GROUP_ID
//  			WHERE
//  			    pg.PRICE_GROUP_ID = :pgroupId
//  			ORDER BY
//  				m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = Com_("D_T2_BRAND_PRODUCT");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = pgroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupQueryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:76^3*/

		List<ItemVO> itemLst = new ArrayList<ItemVO>();
		while (productIter.next()) {
			itemLst.add(iterateProduct(productIter));
		}
		productIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}

	
	private ItemVO iterateProduct(ProductIter productIter) throws SQLException {
		ItemVO item = new ItemVO();
		item.setItemId(productIter.item_id());
		item.setItemName(productIter.item_name());
		item.setItemType(productIter.item_type());
		item.setCategoryId(productIter.category_id());
		item.setItemDimension(productIter.item_dimension());
		item.setQueryCode(productIter.query_code());
		String itemSpecification = productIter.item_specification();
		item.setItemSpecification(itemSpecification);
		
		item.setSalePrice(productIter.sale_price());
		item.setGroupPrice(productIter.group_price());
		return item;
	}
}/*@lineinfo:generated-code*/class PriceGroupQueryBean_SJProfileKeys 
{
  private static PriceGroupQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PriceGroupQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PriceGroupQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.hq.PriceGroupQueryBean_SJProfile0");
  }
}
