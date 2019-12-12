/*@lineinfo:filename=DisDiffHandleBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 5, 2015 by liyzh
 * Last edited on Aug 5, 2015 by liyzh
 * 
 * 说明： 物流中心处理配送差异
 */
package logic.module.lc;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;

public class DisDiffHandleBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(DisDiffHandleBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    receive_timeNdx = findColumn("receive_time");
    requester_idNdx = findColumn("requester_id");
    requesterNdx = findColumn("requester");
    request_addressNdx = findColumn("request_address");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    inputer_idNdx = findColumn("inputer_id");
    inputerNdx = findColumn("inputer");
    input_timeNdx = findColumn("input_time");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    out_storage_idNdx = findColumn("out_storage_id");
    out_storageNdx = findColumn("out_storage");
    in_storage_idNdx = findColumn("in_storage_id");
    in_storageNdx = findColumn("in_storage");
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
  public String provider_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_idNdx);
  }
  private int provider_idNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
  public String requester_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requester_idNdx);
  }
  private int requester_idNdx;
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
  public String request_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(request_addressNdx);
  }
  private int request_addressNdx;
  public String form_maker_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_maker_idNdx);
  }
  private int form_maker_idNdx;
  public String form_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_makerNdx);
  }
  private int form_makerNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public String auditor_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditor_idNdx);
  }
  private int auditor_idNdx;
  public String auditor() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditorNdx);
  }
  private int auditorNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String inputer_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(inputer_idNdx);
  }
  private int inputer_idNdx;
  public String inputer() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(inputerNdx);
  }
  private int inputerNdx;
  public Date input_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timeNdx);
  }
  private int input_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public String max_pay_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(max_pay_itemNdx);
  }
  private int max_pay_itemNdx;
  public String out_storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storage_idNdx);
  }
  private int out_storage_idNdx;
  public String out_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storageNdx);
  }
  private int out_storageNdx;
  public String in_storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_storage_idNdx);
  }
  private int in_storage_idNdx;
  public String in_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_storageNdx);
  }
  private int in_storageNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:59^21*/
	
	public List<ShippingHeader> query()
			throws NoPrivilegeException,SQLException,NoConnection {
		//配退是最后的标志，如果该配送单已经做了配退，那么该单就不能再做差异处理。
		//同时，如果要做配退，则必须这张配送单是没有差异的
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:68^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    diff AS
//  			    (
//  			        SELECT
//  			            h.FORM_ID
//  			        FROM
//  			        :Com_("D_T1_SHIPPING_DETAIL") d
//  			        INNER JOIN
//  			        :Com_("D_T1_SHIPPING_HEADER") h
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        AND NVL(h.DIFFERENT_STATUS, 'null') != '已处理差异'
//  	        		AND h.RETURN_STATUS is null
//  			        WHERE
//  			            d.RECEIVE_COUNT IS NOT NULL
//  			        AND d.DELIVERY_COUNT != d.RECEIVE_COUNT
//  			        GROUP BY
//  			            h.FORM_ID
//  			    )
//  			SELECT
//  			    h.*
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			INNER JOIN
//  			    diff f
//  			ON
//  			    f.form_id = h.form_id
//  			ORDER BY
//  			    h.FORM_TIME DESC,
//  			    h.form_id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_3 = Com_("D_T1_SHIPPING_HEADER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DisDiffHandleBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:99^3*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	private List<ShippingHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ShippingHeader> headerLst = new ArrayList<ShippingHeader>();
		while(headerIter.next()) {
			ShippingHeader header = new ShippingHeader();
			header.setFormId(headerIter.form_id());
			header.setFormRefId(headerIter.form_ref_id());
			header.setProviderId(headerIter.provider_id());
			header.setProvider(headerIter.provider());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setRequesterId(headerIter.requester_id());
			header.setRequester(headerIter.requester());
			header.setRequestAddress(headerIter.request_address());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setInputerId(headerIter.inputer_id());
			header.setInputer(headerIter.inputer());
			header.setInputTime(SqlDateUtil.getUtilDate(headerIter.input_time()));
			header.setFormNote(headerIter.form_note());
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setOutStorageId(headerIter.out_storage_id());
			header.setOutStorage(headerIter.out_storage());
			header.setInStorageId(headerIter.in_storage_id());
			header.setInStorage(headerIter.in_storage());
			headerLst.add(header);
		}
		return headerLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:140^2*/

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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:155^18*/
	
	public List<ShippingDetail> queryDetail(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:161^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.PACKAGING_FACTOR,
//  			    d.PACKAGING_UNIT,
//  			    d.PACKAGING_COUNT,
//  			    d.REQUEST_COUNT,
//  			    d.SHIPPING_COUNT,
//  			    d.DELIVERY_COUNT,
//  			    d.RECEIVE_COUNT,
//  			    d.DIFFERENT_COUNT,
//  			    d.DIFFERENT_REASON,
//  			    d.ITEM_UNIT_PRICE,
//  			    ROUND(d.ITEM_UNIT_PRICE * d.DIFFERENT_COUNT,2)PAY_AMT,
//  			    d.EXPIRED_TIME
//  			FROM
//  			:Com_("D_T1_SHIPPING_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = :formId
//  			AND d.RECEIVE_COUNT IS NOT NULL
//  			AND d.DELIVERY_COUNT != d.RECEIVE_COUNT
//  			ORDER BY
//  			    d.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DisDiffHandleBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:188^17*/
		List<ShippingDetail> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	private List<ShippingDetail> processDetailIter(DetailIter detailIter) 
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
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class DisDiffHandleBean_SJProfileKeys 
{
  private static DisDiffHandleBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DisDiffHandleBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DisDiffHandleBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.DisDiffHandleBean_SJProfile0");
  }
}
