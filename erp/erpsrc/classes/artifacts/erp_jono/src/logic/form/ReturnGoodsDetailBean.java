/*@lineinfo:filename=ReturnGoodsDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Sep 20 20:05:30 CST 2014 by lyz
 * Last edited on Sat Sep 20 20:05:30 CST 2014 by lyz
 * 
 * comment: 餐厅退货单表明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ReturnGoodsDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ReturnGoodsDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ReturnGoodsDetailBean.class);
	
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
    form_idNdx = findColumn("form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    item_idNdx = findColumn("item_id");
    return_countNdx = findColumn("return_count");
    return_pay_amtNdx = findColumn("return_pay_amt");
    return_reasonNdx = findColumn("return_reason");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    request_countNdx = findColumn("request_count");
    shipping_countNdx = findColumn("shipping_count");
    delivery_countNdx = findColumn("delivery_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    expired_timeNdx = findColumn("expired_time");
    receive_countNdx = findColumn("receive_count");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public Double return_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_countNdx);
  }
  private int return_countNdx;
  public Double return_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_pay_amtNdx);
  }
  private int return_pay_amtNdx;
  public String return_reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(return_reasonNdx);
  }
  private int return_reasonNdx;
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
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:45^43*/
	
	public int saveEntity(ReturnGoodsDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String formRefId = detail.getFormRefId();
		String itemId = detail.getItemId();
		Double returnCount = detail.getReturnCount();
		Double returnPayAmt = detail.getReturnPayAmt();
		String returnReason = detail.getReturnReason();

		/*@lineinfo:generated-code*//*@lineinfo:58^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_RETURN_GOODS_DETAIL") 
//  				(FORM_ID, FORM_REF_ID, ITEM_ID, RETURN_COUNT, RETURN_PAY_AMT, RETURN_REASON) 
//  			VALUES (:formId, :formRefId, :itemId, :returnCount, :returnPayAmt, :returnReason)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = formRefId;
  String __sJT_4 = itemId;
  Double __sJT_5 = returnCount;
  Double __sJT_6 = returnPayAmt;
  String __sJT_7 = returnReason;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:62^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ReturnGoodsDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String formRefId = detail.getFormRefId();
		String itemId = detail.getItemId();
		Double returnCount = detail.getReturnCount();
		Double returnPayAmt = detail.getReturnPayAmt();
		String returnReason = detail.getReturnReason();

		/*@lineinfo:generated-code*//*@lineinfo:79^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_RETURN_GOODS_DETAIL")
//  			SET
//  				FORM_ID = :formId,
//  				RETURN_COUNT = :returnCount,
//  				RETURN_PAY_AMT = :returnPayAmt,
//  				RETURN_REASON = :returnReason
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
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_2 = formId;
  Double __sJT_3 = returnCount;
  Double __sJT_4 = returnPayAmt;
  String __sJT_5 = returnReason;
  String __sJT_6 = formId;
  String __sJT_7 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:90^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:100^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_RETURN_GOODS_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:106^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteByItem(String formId,String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:116^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T1_RETURN_GOODS_DETAIL")
//  				WHERE
//  					FORM_ID = :formId
//  					AND ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:123^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int deleteByCount(String formId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:133^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T1_RETURN_GOODS_DETAIL")
//  				WHERE
//  					FORM_ID = :formId
//  					AND RETURN_COUNT = 0
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:140^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	public List<ReturnGoodsDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:149^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    rd.*,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.REQUEST_COUNT,
//  			    d.SHIPPING_COUNT,
//  			    d.DELIVERY_COUNT,
//  			    d.ITEM_UNIT_PRICE,
//  			    d.EXPIRED_TIME,
//  			    d.RECEIVE_COUNT
//  			FROM
//  			:Com_("D_T1_RETURN_GOODS_DETAIL") rd,
//  			:Com_("D_T1_SHIPPING_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = rd.FORM_REF_ID
//  			AND d.item_id = rd.ITEM_ID
//  			AND rd.FORM_ID = :formId
//  			ORDER BY
//  				rd.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_2 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:171^3*/
		List<ReturnGoodsDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<ReturnGoodsDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<ReturnGoodsDetail> detailLst = new ArrayList<ReturnGoodsDetail>();
		while(detailIter.next()) {
			ReturnGoodsDetail detail = new ReturnGoodsDetail();
			detail.setFormId(detailIter.form_id());
			detail.setFormRefId(detailIter.form_ref_id());
			detail.setItemId(detailIter.item_id());
			detail.setReturnCount(detailIter.return_count());
			detail.setReturnPayAmt(detailIter.return_pay_amt());
			detail.setReturnReason(detailIter.return_reason());

			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setRequestCount(detailIter.request_count());
			detail.setShippingCount(detailIter.shipping_count());
			detail.setDeliveryCount(detailIter.delivery_count());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setReceiveCount(detailIter.receive_count());
			
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class ReturnGoodsDetailBean_SJProfileKeys 
{
  private static ReturnGoodsDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ReturnGoodsDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ReturnGoodsDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ReturnGoodsDetailBean_SJProfile0");
  }
}
