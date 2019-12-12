/*@lineinfo:filename=CreateDistributionBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 2, 2014 by liyzh
 * Last edited on Dec 2, 2014 by liyzh
 * 
 * 说明： 物流中心生成配送单
 */
package logic.module.lc;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.CollectDetail;
import pojo.form.RequestHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class CreateDistributionBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(CreateDistributionBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

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
    form_typeNdx = findColumn("form_type");
    buyer_idNdx = findColumn("buyer_id");
    buyer_nameNdx = findColumn("buyer_name");
    buyer_addressNdx = findColumn("buyer_address");
    receive_timeNdx = findColumn("receive_time");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    arrive_periodNdx = findColumn("arrive_period");
    delivery_typeNdx = findColumn("delivery_type");
    form_statusNdx = findColumn("form_status");
    template_nameNdx = findColumn("template_name");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String form_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_typeNdx);
  }
  private int form_typeNdx;
  public String buyer_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(buyer_idNdx);
  }
  private int buyer_idNdx;
  public String buyer_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(buyer_nameNdx);
  }
  private int buyer_nameNdx;
  public String buyer_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(buyer_addressNdx);
  }
  private int buyer_addressNdx;
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
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
  public Integer arrive_period() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(arrive_periodNdx);
  }
  private int arrive_periodNdx;
  public String delivery_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_typeNdx);
  }
  private int delivery_typeNdx;
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
  public String template_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_nameNdx);
  }
  private int template_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:56^24*/
	
	public List<RequestHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.BUYER_ID = '" + branchId + "'";
		}
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:69^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    request AS
//  			    (
//  			        SELECT
//  			            h.FORM_ID,
//  			            h.BUYER_ID,
//  			            h.BUYER_NAME,
//  			            h.FORM_TYPE,
//  			            h.template_name,
//  			            h.RECEIVE_TIME,
//  			            h.ARRIVE_PERIOD,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ORDER_COUNT,
//  			            rb.REGION_ID,
//  			            dt.DELIVERY_TYPE
//  			        FROM
//  			        :Com_("D_T1_REQUEST_HEADER") h
//  			        INNER JOIN
//  			        :Com_("D_T1_REQUEST_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        LEFT JOIN
//  			        :Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			        ON
//  			            rb.BRANCH_ID = h.BUYER_ID
//  			        LEFT JOIN
//  			        :Com_("D_T2_DELIVERY_TYPE") dt
//  			        ON
//  			            dt.REGION_ID = rb.REGION_ID
//  			        AND dt.ITEM_ID = d.item_id
//  			        WHERE
//  			            dt.DELIVERY_TYPE IN( 'UNIFIED')
//  			        AND h.FORM_TYPE = 'request'
//  	        		AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  	    			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  			    )
//  			SELECT
//              	h.*, s.STATUS AS FORM_STATUS
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			 :query
//  			 AND   h.FORM_ID IN
//  			    (
//  			        SELECT
//  			            r.form_id
//  			        FROM
//  			            request r
//  			        GROUP BY
//  			            r.form_id)
//  			AND	NVL(s.STATUS, 'null') = '已审核'
//  	  	--	AND NVL(h.PURCHASE_STATUS, 'null') = '已汇总'
//  	  		AND NVL(h.SHIPPING_STATUS, 'null') != '已配送处理'
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
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_4 = Com_("D_T2_DELIVERY_TYPE");
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_10 = Com_("D_T0_FORM_STATUS");
  String __sJT_11 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CreateDistributionBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:132^3*/
		List<RequestHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<RequestHeader> queryByReceiveTime(java.util.Date startDate, java.util.Date endDate, String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.BUYER_ID = '" + branchId + "'";
		}
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:150^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    request AS
//  			    (
//  			        SELECT
//  			            h.FORM_ID,
//  			            h.BUYER_ID,
//  			            h.BUYER_NAME,
//  			            h.template_name,
//  			            h.FORM_TYPE,
//  			            h.RECEIVE_TIME,
//  			            h.ARRIVE_PERIOD,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ORDER_COUNT,
//  			            rb.REGION_ID,
//  			            dt.DELIVERY_TYPE
//  			        FROM
//  			        :Com_("D_T1_REQUEST_HEADER") h
//  			        INNER JOIN
//  			        :Com_("D_T1_REQUEST_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        LEFT JOIN
//  			        :Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			        ON
//  			            rb.BRANCH_ID = h.BUYER_ID
//  			        LEFT JOIN
//  			        :Com_("D_T2_DELIVERY_TYPE") dt
//  			        ON
//  			            dt.REGION_ID = rb.REGION_ID
//  			        AND dt.ITEM_ID = d.item_id
//  			        WHERE
//  			            dt.DELIVERY_TYPE IN( 'UNIFIED')
//  			        AND h.FORM_TYPE = 'request'
//  	                AND (h.RECEIVE_TIME >= :sDate or :sDate is null)
//  	    			AND (h.RECEIVE_TIME <= :eDate or :eDate is null)
//  			    )
//  			SELECT
//              	h.*, s.STATUS AS FORM_STATUS
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			 :query
//  			 AND   h.FORM_ID IN
//  			    (
//  			        SELECT
//  			            r.form_id
//  			        FROM
//  			            request r
//  			        GROUP BY
//  			            r.form_id)
//  	  		AND NVL(h.PURCHASE_STATUS, 'null') = '已汇总'
//  	  		AND NVL(h.SHIPPING_STATUS, 'null') != '已配送处理'
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
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_4 = Com_("D_T2_DELIVERY_TYPE");
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_10 = Com_("D_T0_FORM_STATUS");
  String __sJT_11 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CreateDistributionBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:212^3*/
		List<RequestHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private List<RequestHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<RequestHeader> headerLst = new ArrayList<RequestHeader>();
		while(headerIter.next()) {
			RequestHeader header = new RequestHeader();
			header.setFormId(headerIter.form_id());
			header.setFormType(headerIter.form_type());
			header.setBuyerId(headerIter.buyer_id());
			header.setBuyerName(headerIter.buyer_name());
			header.setBuyerAddress(headerIter.buyer_address());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setTemplateName(headerIter.template_name());
			
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setArrivePeriod(headerIter.arrive_period());
			header.setDeliveryType(headerIter.delivery_type());
			header.setFormStatus(headerIter.form_status());
			headerLst.add(header);
		}
		return headerLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:247^2*/

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
    delivery_typeNdx = findColumn("delivery_type");
    region_idNdx = findColumn("region_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_unit_priceNdx = findColumn("item_unit_price");
    order_countNdx = findColumn("order_count");
    pay_amtNdx = findColumn("pay_amt");
    delivery_unitNdx = findColumn("delivery_unit");
    delivery_factorNdx = findColumn("delivery_factor");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String delivery_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_typeNdx);
  }
  private int delivery_typeNdx;
  public String region_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(region_idNdx);
  }
  private int region_idNdx;
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public String delivery_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_unitNdx);
  }
  private int delivery_unitNdx;
  public Double delivery_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delivery_factorNdx);
  }
  private int delivery_factorNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:252^48*/
	
	public List<Map> queryDetail(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:258^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    h.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.ITEM_UNIT_PRICE,
//  			    d.ORDER_COUNT,
//  			    d.PAY_AMT,
//  			    rb.REGION_ID,
//  			    dt.DELIVERY_TYPE,
//  			    u.DELIVERY_UNIT,
//  			    u.delivery_factor
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_REQUEST_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			ON
//  			    rb.BRANCH_ID = h.BUYER_ID
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_TYPE") dt
//  			ON
//  			    dt.REGION_ID = rb.REGION_ID
//  			AND dt.ITEM_ID = d.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = d.ITEM_ID
//  			WHERE
//  			    h.FORM_ID = :formId
//  			ORDER BY
//  			    dt.DELIVERY_TYPE,
//  			    d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_4 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_5 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CreateDistributionBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:297^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	private List<Map> processDetailIter(DetailIter detailIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("formId", detailIter.form_id());
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemCategory", detailIter.item_category());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("itemSpecification", detailIter.item_specification());
			detail.put("orderCount", detailIter.order_count());
			detail.put("itemUnitPrice", detailIter.item_unit_price());
			detail.put("payAmt", detailIter.pay_amt());
			detail.put("regionId", detailIter.region_id());
			detail.put("deliveryType", detailIter.delivery_type());
			detail.put("deliveryUnit", detailIter.delivery_unit());
			detail.put("unitConvertFactor", detailIter.delivery_factor());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class CreateDistributionBean_SJProfileKeys 
{
  private static CreateDistributionBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CreateDistributionBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CreateDistributionBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.CreateDistributionBean_SJProfile0");
  }
}
