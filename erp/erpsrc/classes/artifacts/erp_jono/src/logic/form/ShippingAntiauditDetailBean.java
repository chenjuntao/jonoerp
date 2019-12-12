/*@lineinfo:filename=ShippingAntiauditDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Sep 17 15:20:13 CST 2014 by lyz
 * Last edited on Wed Sep 17 15:20:13 CST 2014 by lyz
 * 
 * comment: 餐厅配送反审核单表明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ShippingAntiauditDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ShippingAntiauditDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShippingAntiauditDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

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
    form_ref_idNdx = findColumn("form_ref_id");
    item_idNdx = findColumn("item_id");
    receive_countNdx = findColumn("receive_count");
    pay_amtNdx = findColumn("pay_amt");
    antiaudit_receive_countNdx = findColumn("antiaudit_receive_count");
    antiaudit_pay_amtNdx = findColumn("antiaudit_pay_amt");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    request_countNdx = findColumn("request_count");
    shipping_countNdx = findColumn("shipping_count");
    delivery_countNdx = findColumn("delivery_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    expired_timeNdx = findColumn("expired_time");
  }
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double antiaudit_receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(antiaudit_receive_countNdx);
  }
  private int antiaudit_receive_countNdx;
  public Double antiaudit_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(antiaudit_pay_amtNdx);
  }
  private int antiaudit_pay_amtNdx;
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:45^21*/
	
	public int saveEntity(ShippingAntiauditDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formRefId = detail.getFormRefId();
		String itemId = detail.getItemId();
		Double antiauditReceiveCount = detail.getAntiauditReceiveCount();
		Double antiauditPayAmt = detail.getAntiauditPayAmt();

		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL") 
//  				(FORM_REF_ID, ITEM_ID, ANTIAUDIT_RECEIVE_COUNT, ANTIAUDIT_PAY_AMT) 
//  			VALUES (:formRefId, :itemId, :antiauditReceiveCount, :antiauditPayAmt)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL");
  String __sJT_2 = formRefId;
  String __sJT_3 = itemId;
  Double __sJT_4 = antiauditReceiveCount;
  Double __sJT_5 = antiauditPayAmt;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:60^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ShippingAntiauditDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formRefId = detail.getFormRefId();
		String itemId = detail.getItemId();
		Double antiauditReceiveCount = detail.getAntiauditReceiveCount();
		Double antiauditPayAmt = detail.getAntiauditPayAmt();

		/*@lineinfo:generated-code*//*@lineinfo:75^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL")
//  			SET
//  				ANTIAUDIT_RECEIVE_COUNT = :antiauditReceiveCount,
//  				ANTIAUDIT_PAY_AMT = :antiauditPayAmt
//  			WHERE 
//  				FORM_REF_ID = :formRefId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL");
  Double __sJT_2 = antiauditReceiveCount;
  Double __sJT_3 = antiauditPayAmt;
  String __sJT_4 = formRefId;
  String __sJT_5 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:84^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formRefId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:94^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL")
//  			WHERE
//  				FORM_REF_ID = :formRefId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL");
  String __sJT_2 = formRefId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:100^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<ShippingAntiauditDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:110^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    ad.*,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.PACKAGING_FACTOR,
//  			    d.PACKAGING_UNIT,
//  			    d.REQUEST_COUNT,
//  			    d.SHIPPING_COUNT,
//  			    d.DELIVERY_COUNT,
//  			    d.ITEM_UNIT_PRICE,
//  			    d.EXPIRED_TIME,
//  			    d.RECEIVE_COUNT,
//  			    d.PAY_AMT
//  			FROM
//  				:Com_("D_T1_SHIPPING_DETAIL") d,
//  				:Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL") ad
//  			WHERE
//  			    d.FORM_ID = ad.FORM_REF_ID
//  			AND d.item_id = ad.ITEM_ID
//  			AND ad.FORM_REF_ID = :formId
//  			ORDER BY
//  			    d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = Com_("D_T1_SHIPPING_ANTIAUDIT_DETAIL");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditDetailBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:135^3*/
		List<ShippingAntiauditDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<ShippingAntiauditDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<ShippingAntiauditDetail> detailLst = new ArrayList<ShippingAntiauditDetail>();
		while(detailIter.next()) {
			ShippingAntiauditDetail detail = new ShippingAntiauditDetail();
			detail.setFormRefId(detailIter.form_ref_id());
			detail.setItemId(detailIter.item_id());
			detail.setReceiveCount(detailIter.receive_count());
			detail.setPayAmt(detailIter.pay_amt());
			detail.setAntiauditReceiveCount(detailIter.antiaudit_receive_count());
			detail.setAntiauditPayAmt(detailIter.antiaudit_pay_amt());
			
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setRequestCount(detailIter.request_count());
			detail.setShippingCount(detailIter.shipping_count());
			detail.setDeliveryCount(detailIter.delivery_count());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class ShippingAntiauditDetailBean_SJProfileKeys 
{
  private static ShippingAntiauditDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingAntiauditDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingAntiauditDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ShippingAntiauditDetailBean_SJProfile0");
  }
}
