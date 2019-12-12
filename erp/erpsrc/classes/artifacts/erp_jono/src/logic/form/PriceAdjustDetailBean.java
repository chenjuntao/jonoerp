/*@lineinfo:filename=PriceAdjustDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Feb 12 11:09:52 CST 2015 by lyz
 * Last edited on Thu Feb 12 11:09:52 CST 2015 by lyz
 * 
 * comment: 中央工厂售价调价单、中央工厂采购调价单、物流中心采购调价单、物流中心标准调价单、物流中心加盟调价单、物流中心零售调价单明细
 */
package logic.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.form.PriceAdjustDetail;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.ItemType;
import org.apache.commons.lang.StringUtils;

public class PriceAdjustDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PriceAdjustDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DetailIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DetailIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_packagerNdx = findColumn("item_packager");
    old_priceNdx = findColumn("old_price");
    new_priceNdx = findColumn("new_price");
    enabledNdx = findColumn("enabled");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public Double item_packager() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_packagerNdx);
  }
  private int item_packagerNdx;
  public Double old_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(old_priceNdx);
  }
  private int old_priceNdx;
  public Double new_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(new_priceNdx);
  }
  private int new_priceNdx;
  public String enabled() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(enabledNdx);
  }
  private int enabledNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:45^18*/
	
	public int saveEntity(PriceAdjustDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		Double itemPackager = detail.getItemPackager();
		Double oldPrice = detail.getOldPrice();
		Double newPrice = detail.getNewPrice();

		/*@lineinfo:generated-code*//*@lineinfo:60^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_PRICE_ADJUST_DETAIL") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_SPECIFICATION, ITEM_PACKAGER, OLD_PRICE, NEW_PRICE) 
//  			VALUES (:formId, :itemId, :itemName, :itemDimension, :itemSpecification, :itemPackager, :oldPrice, :newPrice)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  Double __sJT_7 = itemPackager;
  Double __sJT_8 = oldPrice;
  Double __sJT_9 = newPrice;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceAdjustDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:64^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(PriceAdjustDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		Double itemPackager = detail.getItemPackager();
		Double oldPrice = detail.getOldPrice();
		Double newPrice = detail.getNewPrice();

		/*@lineinfo:generated-code*//*@lineinfo:83^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PRICE_ADJUST_DETAIL")
//  			SET
//  				FORM_ID = :formId,
//  				ITEM_ID = :itemId,
//  				ITEM_NAME = :itemName,
//  				ITEM_DIMENSION = :itemDimension,
//  				ITEM_SPECIFICATION = :itemSpecification,
//  				ITEM_PACKAGER = :itemPackager,
//  				OLD_PRICE = :oldPrice,
//  				NEW_PRICE = :newPrice
//  			WHERE 
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  Double __sJT_7 = itemPackager;
  Double __sJT_8 = oldPrice;
  Double __sJT_9 = newPrice;
  String __sJT_10 = formId;
  String __sJT_11 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceAdjustDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:98^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:108^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PRICE_ADJUST_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceAdjustDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:114^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/**
	 * 删除除此之外的其它调价明细
	 */
	public int deleteExcept(String formId, List<String> itemIdLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " 1=1 ";
		if (itemIdLst.size() != 0) { // 非空处理
			query = " (1,ITEM_ID) NOT IN ((1,'" + StringUtils.join(itemIdLst, ",").replaceAll(",", "'),(1,'") + "')) ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:131^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PRICE_ADJUST_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceAdjustDetailBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:138^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<PriceAdjustDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:148^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.*,
//  			    nvl(m.enabled,0) enabled
//  			FROM
//  				:Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  			LEFT JOIN 
//  				:Com_("D_T2_ITEM_META") M
//  			ON
//  				M.ITEM_ID = D.ITEM_ID
//  			WHERE
//  			    d.FORM_ID = :formId
//  			ORDER BY
//  				d.ITEM_ID
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceAdjustDetailBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:162^3*/
		List<PriceAdjustDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<PriceAdjustDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<PriceAdjustDetail> detailLst = new ArrayList<PriceAdjustDetail>();
		while(detailIter.next()) {
			PriceAdjustDetail detail = new PriceAdjustDetail();
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemPackager(detailIter.item_packager());
			detail.setOldPrice(detailIter.old_price());
			detail.setNewPrice(detailIter.new_price());
			detail.setEnabled(detailIter.enabled().equals("1")?"启用":"未启用");
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:188^2*/

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
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_packagerNdx = findColumn("item_packager");
    item_priceNdx = findColumn("item_price");
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
  public Double item_packager() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_packagerNdx);
  }
  private int item_packagerNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:193^21*/
	
	public List<PriceAdjustDetail> queryItem(String itemType, String itemName, String priceType)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = " ITEM_TYPE = '" + itemType + "'";
		if (ItemType.R_S.equals(itemType)) {
			query = " (ITEM_TYPE = '" + ItemType.RAW 
					+ "' OR  ITEM_TYPE = '" + ItemType.SEMIS
					+ "' OR  ITEM_TYPE = '" + ItemType.SELFSEMIS + "')";
		}
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:207^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.*,
//  			    p.ITEM_PRICE,
//  			    u.DELIVERY_FACTOR AS item_packager
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.IS_CURRENT =1
//  			AND	p.PRICE_TYPE = :priceType
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = m.ITEM_ID
//  			WHERE
//  				:query
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//              ORDER BY
//              	m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = priceType;
  String __sJT_4 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_5 = query;
  String __sJT_6 = itemName;
  String __sJT_7 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceAdjustDetailBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:231^3*/
		List<PriceAdjustDetail> detailLst = new ArrayList<PriceAdjustDetail>();
		while(itemIter.next()) {
			PriceAdjustDetail detail = new PriceAdjustDetail();
			detail.setItemId(itemIter.item_id());
			detail.setItemName(itemIter.item_name());
			detail.setItemDimension(itemIter.item_dimension());
			detail.setItemSpecification(itemIter.item_specification());
			detail.setItemPackager(itemIter.item_packager());
			detail.setOldPrice(itemIter.item_price());
			detail.setNewPrice(itemIter.item_price());
			detailLst.add(detail);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<PriceAdjustDetail> queryBySupplier(String supplierId, String itemType, String itemName, String priceType)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = " ITEM_TYPE = '" + itemType + "'";
		if (ItemType.R_S.equals(itemType)) {
			query = " (ITEM_TYPE = '" + ItemType.RAW 
					+ "' OR  ITEM_TYPE = '" + ItemType.SEMIS
					+ "' OR  ITEM_TYPE = '" + ItemType.SELFSEMIS + "')";
		}
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:260^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME,
//  			    m.QUERY_CODE,
//  			    m.CATEGORY_ID,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    i.ITEM_PRICE,
//  			    u.DELIVERY_FACTOR AS item_packager
//  			    
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
//  			                :Com_("D_T2_SUPPLIER_BRANCH_ITEM") sbi
//  			                WHERE
//  			                    sbi.SUPPLIER_ID = :supplierId
//  			                GROUP BY
//  			                    sbi.ITEM_ID
//  			                UNION ALL
//  			                SELECT
//  			                    p.ITEM_ID,
//  			                    p.ITEM_PRICE
//  			                FROM
//  			                :Com_("D_T2_ITEM_PRICE") p
//  			                WHERE
//  		                    	p.PRICE_TYPE = 'SUPPLIER' 
//  			                AND p.SUPPLIER_ID = :supplierId 
//  			                AND p.IS_CURRENT =1)
//  			        GROUP BY
//  			            ITEM_ID) i
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = i.ITEM_ID
//  			    		  LEFT JOIN
//  			    		  :Com_("D_T2_DELIVERY_UNIT") u
//  						ON
//  						    u.ITEM_ID = m.ITEM_ID
//  			    		WHERE
//  						:query
//  		            AND (
//  		                    m.ITEM_ID LIKE :itemName
//  		                OR  m.ITEM_NAME LIKE :itemName)
//  		            ORDER BY
//  		            	m.ITEM_ID
//  		 
//  		 };
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
  String __sJT_6 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_7 = query;
  String __sJT_8 = itemName;
  String __sJT_9 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceAdjustDetailBean_SJProfileKeys.getKey(0), 6);
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
      __sJT_stmt.setString(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:315^3*/
		List<PriceAdjustDetail> detailLst = new ArrayList<PriceAdjustDetail>();
		while(itemIter.next()) {
			PriceAdjustDetail detail = new PriceAdjustDetail();
			detail.setItemId(itemIter.item_id());
			detail.setItemName(itemIter.item_name());
			detail.setItemDimension(itemIter.item_dimension());
			detail.setItemSpecification(itemIter.item_specification());
			detail.setItemPackager(itemIter.item_packager());
			detail.setOldPrice(itemIter.item_price());
			detail.setNewPrice(itemIter.item_price());
			detailLst.add(detail);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
}/*@lineinfo:generated-code*/class PriceAdjustDetailBean_SJProfileKeys 
{
  private static PriceAdjustDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PriceAdjustDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PriceAdjustDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.PriceAdjustDetailBean_SJProfile0");
  }
}
