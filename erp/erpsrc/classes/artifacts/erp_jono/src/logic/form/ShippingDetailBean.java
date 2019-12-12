/*@lineinfo:filename=ShippingDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 12, 2014 by liyzh
 * Last edited on Sep 12, 2014 by liyzh
 * 
 * 说明：  餐厅配送单、物流中心出货单、中央工厂出货单明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;


import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ShippingDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ShippingDetailBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(ShippingDetailBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class IdIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public IdIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    category_idNdx = findColumn("category_id");
  }
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:43^2*/

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
    item_categoryNdx = findColumn("item_category");
    packaging_factorNdx = findColumn("packaging_factor");
    packaging_unitNdx = findColumn("packaging_unit");
    packaging_countNdx = findColumn("packaging_count");
    request_countNdx = findColumn("request_count");
    shipping_countNdx = findColumn("shipping_count");
    delivery_countNdx = findColumn("delivery_count");
    receive_countNdx = findColumn("receive_count");
    different_countNdx = findColumn("different_count");
    different_reasonNdx = findColumn("different_reason");
    item_unit_priceNdx = findColumn("item_unit_price");
    pay_amtNdx = findColumn("pay_amt");
    received_countNdx = findColumn("received_count");
    sum_item_countNdx = findColumn("sum_item_count");
    expired_timeNdx = findColumn("expired_time");
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
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public Double packaging_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(packaging_factorNdx);
  }
  private int packaging_factorNdx;
  public String packaging_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(packaging_unitNdx);
  }
  private int packaging_unitNdx;
  public Double packaging_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(packaging_countNdx);
  }
  private int packaging_countNdx;
  public Double request_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(request_countNdx);
  }
  private int request_countNdx;
  public Double shipping_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(shipping_countNdx);
  }
  private int shipping_countNdx;
  public Double delivery_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delivery_countNdx);
  }
  private int delivery_countNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
  public String different_reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(different_reasonNdx);
  }
  private int different_reasonNdx;
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double received_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(received_countNdx);
  }
  private int received_countNdx;
  public Double sum_item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum_item_countNdx);
  }
  private int sum_item_countNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:61^21*/

	public List<ShippingDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.*,
//  			    0 SUM_ITEM_COUNT,
//  			    0 RECEIVED_COUNT
//  			FROM
//  			:Com_("D_T1_SHIPPING_DETAIL") d
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
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:78^3*/
		List<ShippingDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<ShippingDetail> queryUnOrder(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:89^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    DELIVERY AS --已出货数
//  			    (
//  			        SELECT DISTINCT
//  			            sh.FORM_REF_ID FORM_ID,
//  			            sd.ITEM_ID,
//  			            NVL(SUM(sd.DELIVERY_COUNT),0) DELIVERY_COUNT
//  		            FROM
//  		            :Com_("D_T1_SHIPPING_HEADER") h
//  			        INNER JOIN
//  			        :Com_("D_T1_SHIPPING_HEADER") sh
//  			        ON
//  			        h.FORM_REF_ID = sh.FORM_REF_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_SHIPPING_DETAIL") sd
//  			        ON
//  			            sd.FORM_ID = sh.FORM_ID
//  			        AND sh.PROVIDER_ID='F00'
//      				WHERE
//      	            h.FORM_ID= :formId
//      	            AND sh.AUDIT_TIME IS NOT NULL
//  			        GROUP BY
//  			            (sh.FORM_REF_ID, sd.ITEM_ID)
//  			    )
//  			    ,
//  			    RECEIVE AS --已入库数
//  			    (
//  			        SELECT DISTINCT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            NVL(SUM(id.RECEIVE_COUNT),0) RECEIVE_COUNT
//  			            FROM
//  			            :Com_("D_T1_SHIPPING_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T1_PURCHASING_DETAIL") d
//  			        ON
//  			            d.FORM_ID = h.FORM_REF_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_COLLECT_REF_FORM") f
//  			        ON
//  			            f.REF_FORM_ID=d.FORM_ID
//  			        AND d.ITEM_ID = f.ITEM_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_ARRENGMENT_DETAIL") ad
//  			        ON
//  			            f.COLLECT_FORM_ID=ad.FORM_ID
//  			        AND ad.ITEM_ID =f.ITEM_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        ON
//  			            ih.FORM_REF_ID=ad.WORK_ORDER_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            id.FORM_ID = ih.FORM_ID
//      				WHERE
//      			    h.FORM_ID = :formId
//  		    		AND ih.AUDIT_TIME IS NOT NULL
//  			        GROUP BY
//  			            ( d.FORM_ID, d.ITEM_ID)
//  			    )
//  			SELECT
//  			    d.*,
//  			    NVL(de.DELIVERY_COUNT,0) RECEIVED_COUNT,
//  			    NVL(r.RECEIVE_COUNT,0)   SUM_ITEM_COUNT
//  			FROM
//  			:Com_("D_T1_SHIPPING_DETAIL") d
//  			INNER JOIN
//  			    RECEIVE r
//  			ON
//  			    d.ITEM_ID = r.ITEM_ID
//  			LEFT JOIN
//  			    DELIVERY de
//  			ON
//  			    r.ITEM_ID =de.ITEM_ID
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
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_3 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_4 = formId;
  String __sJT_5 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_6 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_7 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_8 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_9 = Com_("D_T1_INPUT_HEADER");
  String __sJT_10 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_11 = formId;
  String __sJT_12 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_13 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
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

/*@lineinfo:user-code*//*@lineinfo:169^3*/
		List<ShippingDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}


	private List<ShippingDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<ShippingDetail> detailLst = new ArrayList<ShippingDetail>();
		while(detailIter.next()) {
			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setPackagingFactor(detailIter.packaging_factor());
			detail.setPackagingUnit(detailIter.packaging_unit());
			detail.setPackagingCount(detailIter.packaging_count());
			detail.setRequestCount(detailIter.request_count());
			detail.setShippingCount(detailIter.shipping_count());
			detail.setDeliveryCount(detailIter.delivery_count());
			detail.setReceiveCount(detailIter.receive_count());
			detail.setDifferentCount(detailIter.different_count());
			detail.setDifferentReason(detailIter.different_reason());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setPayAmt(detailIter.pay_amt());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setSumItemCount(detailIter.sum_item_count());
			detail.setReceivedCount(detailIter.received_count());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	/**
	 * 保存明细
	 */
	public int saveEntity(ShippingDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		String itemCategory = detail.getItemCategory();
		Double packagingFactor = detail.getPackagingFactor();
		String packagingUnit = detail.getPackagingUnit();
		Double packagingCount = detail.getPackagingCount();
		Double requestCount = detail.getRequestCount();
		Double shippingCount = detail.getShippingCount();
		Double deliveryCount = detail.getDeliveryCount();
		Double receiveCount = detail.getReceiveCount();
		Double differentCount = detail.getDifferentCount();
		String differentReason = detail.getDifferentReason();
		Double itemUnitPrice = detail.getItemUnitPrice();
		Double payAmt = detail.getPayAmt();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());
		IdIter id = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:233^3*/

//  ************************************************************
//  #sql [myCtx] id = { SELECT 
//  				H.CATEGORY_ID  category_id
//  			FROM 
//  				:Com_("D_T2_ITEM_CATEGORY") H
//  			WHERE 
//  				H.CATEGORY_NAME = :itemCategory
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_2 = itemCategory;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      id = new IdIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:240^3*/
		
		while(id.next()) {
			itemCategory = id.category_id();
		}
	
		
		/*@lineinfo:generated-code*//*@lineinfo:247^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_SHIPPING_DETAIL") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_SPECIFICATION, ITEM_CATEGORY, 
//  					PACKAGING_FACTOR, PACKAGING_UNIT, PACKAGING_COUNT, REQUEST_COUNT, SHIPPING_COUNT, DELIVERY_COUNT, 
//  					RECEIVE_COUNT, DIFFERENT_COUNT, DIFFERENT_REASON, ITEM_UNIT_PRICE, PAY_AMT, EXPIRED_TIME) 
//  			VALUES (:formId, 
//  					:itemId, 
//  					:itemName, 
//  					:itemDimension, 
//  					:itemSpecification, 
//  					:itemCategory, 
//  					:packagingFactor, 
//  					:packagingUnit, :packagingCount, 
//  					:requestCount, 
//  					:shippingCount, 
//  					:deliveryCount, 
//  					:receiveCount, 
//  					:differentCount, 
//  					:differentReason, 
//  					:itemUnitPrice, 
//  					:payAmt, 
//  					:expiredTime)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  String __sJT_7 = itemCategory;
  Double __sJT_8 = packagingFactor;
  String __sJT_9 = packagingUnit;
  Double __sJT_10 = packagingCount;
  Double __sJT_11 = requestCount;
  Double __sJT_12 = shippingCount;
  Double __sJT_13 = deliveryCount;
  Double __sJT_14 = receiveCount;
  Double __sJT_15 = differentCount;
  String __sJT_16 = differentReason;
  Double __sJT_17 = itemUnitPrice;
  Double __sJT_18 = payAmt;
  Date __sJT_19 = expiredTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setDoubleWrapper(14, __sJT_14);
      __sJT_stmt.setDoubleWrapper(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setDoubleWrapper(17, __sJT_17);
      __sJT_stmt.setDoubleWrapper(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:269^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateEntity(ShippingDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		String itemCategory = detail.getItemCategory();
		Double packagingFactor = detail.getPackagingFactor();
		String packagingUnit = detail.getPackagingUnit();
		Double requestCount = detail.getRequestCount();
		Double shippingCount = detail.getShippingCount();
		Double deliveryCount = detail.getDeliveryCount();
		Double receiveCount = detail.getReceiveCount();
		Double differentCount = detail.getDifferentCount();
		String differentReason = detail.getDifferentReason();
		Double itemUnitPrice = detail.getItemUnitPrice();
		Double payAmt = detail.getPayAmt();
	
		/*@lineinfo:generated-code*//*@lineinfo:295^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_DETAIL")
//  			SET
//  				DELIVERY_COUNT = :deliveryCount,
//  				RECEIVE_COUNT = :receiveCount,
//  				DIFFERENT_COUNT = :differentCount,
//  				DIFFERENT_REASON = :differentReason,
//  			    PAY_AMT = :payAmt
//  			WHERE
//  				FORM_ID = :formId
//  			and ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  Double __sJT_2 = deliveryCount;
  Double __sJT_3 = receiveCount;
  Double __sJT_4 = differentCount;
  String __sJT_5 = differentReason;
  Double __sJT_6 = payAmt;
  String __sJT_7 = formId;
  String __sJT_8 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:307^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateShippingCount(ShippingDetail detail)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();
			String formId = detail.getFormId();
			String itemId = detail.getItemId();
			String itemName = detail.getItemName();
			String itemDimension = detail.getItemDimension();
			String itemSpecification = detail.getItemSpecification();
			String itemCategory = detail.getItemCategory();
			Double packagingFactor = detail.getPackagingFactor();
			String packagingUnit = detail.getPackagingUnit();
			Double requestCount = detail.getRequestCount();
			Double shippingCount = detail.getShippingCount();
			Double deliveryCount = detail.getDeliveryCount();
			Double receiveCount = detail.getReceiveCount();
			Double differentCount = detail.getDifferentCount();
			String differentReason = detail.getDifferentReason();
			Double itemUnitPrice = detail.getItemUnitPrice();
			Double payAmt = detail.getPayAmt();
			//Date expiredTime = detail.getExpiredTime();
		
			/*@lineinfo:generated-code*//*@lineinfo:334^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_SHIPPING_DETAIL")
//  				SET
//  					SHIPPING_COUNT = :shippingCount,
//  					    	PAY_AMT = :payAmt
//  				WHERE
//  					FORM_ID = :formId
//  				and ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  Double __sJT_2 = shippingCount;
  Double __sJT_3 = payAmt;
  String __sJT_4 = formId;
  String __sJT_5 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:343^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}

	public int updatePrice(String formIds)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();
			String query = " h.FORM_ID IN ('" + formIds.replaceAll(",", "','") + "') ";		
			/*@lineinfo:generated-code*//*@lineinfo:353^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_DETAIL") d1
//  			SET
//  			    (
//  			        d1.ITEM_UNIT_PRICE,
//  			        d1.PAY_AMT
//  			    )
//  			    =
//  			    (
//  			        SELECT
//  			            NVL(p.ITEM_PRICE,0)   ITEM_PRICE,
//  			            NVL(p.ITEM_PRICE,0) * NVL(d.SHIPPING_COUNT,d.DELIVERY_COUNT)
//  			        FROM
//  			        :Com_("D_T1_SHIPPING_HEADER") h
//  			        INNER JOIN
//  			        :Com_("D_T1_SHIPPING_DETAIL") d
//  			        ON
//  			            d.FORM_ID=h.FORM_ID
//  			        INNER JOIN
//  			        :Com_("D_T2_PRICE_GROUP_BRANCH") pg
//  			        ON
//  			            pg.BRANCH_ID =h.REQUESTER_ID AND pg.PRICE_GROUP_ID NOT LIKE 'SALE%'
//  			        INNER JOIN
//  			        :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            p.PRICE_TYPE=pg.PRICE_GROUP_ID
//  			        AND p.ITEM_ID=d.ITEM_ID
//  			        WHERE
//  			        	:query
//  			        AND d1.FORM_ID = d.FORM_ID
//  			        AND d1.ITEM_ID = d.ITEM_ID)
//  			WHERE
//  			    EXISTS
//  			    (
//  			        SELECT
//  			            1
//  		            FROM
//  			        :Com_("D_T1_SHIPPING_HEADER") h
//  			        INNER JOIN
//  			        :Com_("D_T1_SHIPPING_DETAIL") d
//  			        ON
//  			            d.FORM_ID=h.FORM_ID
//  			        INNER JOIN
//  			        :Com_("D_T2_PRICE_GROUP_BRANCH") pg
//  			        ON
//  			            pg.BRANCH_ID =h.REQUESTER_ID AND pg.PRICE_GROUP_ID NOT LIKE 'SALE%'
//  			        INNER JOIN
//  			        :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            p.PRICE_TYPE=pg.PRICE_GROUP_ID
//  			        AND p.ITEM_ID=d.ITEM_ID
//  			        WHERE
//  			        	:query
//  			        AND d1.FORM_ID = d.FORM_ID
//  			        AND d1.ITEM_ID = d.ITEM_ID)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_3 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = query;
  String __sJT_7 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_8 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_9 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_10 = Com_("D_T2_ITEM_PRICE");
  String __sJT_11 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:409^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
		
	/**
	 * 捡货单审核后更新配送单的包装数量和实发数（实发数=包装数量*包装因子）
	 */
	public int updatePackageCount(String formId, String itemId, Double packagingCount, Double deliveryCount)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:421^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_DETAIL")
//  			SET
//  				PACKAGING_COUNT = :packagingCount,
//  				DELIVERY_COUNT = :deliveryCount
//  			WHERE
//  				FORM_ID = :formId
//  			and ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  Double __sJT_2 = packagingCount;
  Double __sJT_3 = deliveryCount;
  String __sJT_4 = formId;
  String __sJT_5 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:430^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:436^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class StorageIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public StorageIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_countNdx = findColumn("item_count");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:439^3*/
	
	/**
	 * query items storage
	 */
	public Map<String,Double> queryStorage(String formId,String storageId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		StorageIter storageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:449^3*/

//  ************************************************************
//  #sql [myCtx] storageIter = { WITH
//  			    items AS
//  			    (
//  			        SELECT DISTINCT
//  			            ITEM_ID
//  			        FROM
//  			        	:Com_("D_T1_SHIPPING_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T1_SHIPPING_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        WHERE
//  			            d.FORM_ID = :formId
//  			            )
//  			SELECT
//  			    i.ITEM_ID,
//  			    NVL(s.ITEM_COUNT,0)ITEM_COUNT
//  			FROM
//  			    items i
//  			LEFT JOIN
//  			 :Com_("D_T2_STORAGE") s
//  			ON
//  			    i.ITEM_ID = s.ITEM_ID
//  			AND s.STORAGE_ID = :storageId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_3 = formId;
  String __sJT_4 = Com_("D_T2_STORAGE");
  String __sJT_5 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      storageIter = new StorageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:473^33*/
		
		Map<String,Double> resultMap = new HashMap<String,Double>();
		while(storageIter.next()) {
			resultMap.put(storageIter.item_id(),storageIter.item_count());
		}
		
		storageIter.close();
		closeDefaultContext(myCtx);
		return resultMap;
	}
	
	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:489^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_SHIPPING_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingDetailBean_SJProfileKeys.getKey(0), 9);
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

/*@lineinfo:user-code*//*@lineinfo:495^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
}/*@lineinfo:generated-code*/class ShippingDetailBean_SJProfileKeys 
{
  private static ShippingDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ShippingDetailBean_SJProfile0");
  }
}
