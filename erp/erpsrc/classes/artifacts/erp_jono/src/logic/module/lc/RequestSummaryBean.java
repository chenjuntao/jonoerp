/*@lineinfo:filename=RequestSummaryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 3, 2014 by liyzh
 * Last edited on Nov 3, 2014 by liyzh
 * 
 * 说明： 餐厅要货、外部订货商订货需求汇总
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.form.CollectRefForm;
import pojo.form.CollectDetail;
import pojo.form.RequestHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.TextUtil;

public class RequestSummaryBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequestSummaryBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:44^2*/

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
    form_statusNdx = findColumn("form_status");
    template_IdNdx = findColumn("template_Id");
    template_NameNdx = findColumn("template_Name");
    deliveryTypeNdx = findColumn("deliveryType");
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
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
  public String template_Id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_IdNdx);
  }
  private int template_IdNdx;
  public String template_Name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_NameNdx);
  }
  private int template_NameNdx;
  public String deliveryType() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(deliveryTypeNdx);
  }
  private int deliveryTypeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:61^23*/
	
	public int saveEntity(String refIds,String formIds)throws NoPrivilegeException,SQLException,NoConnection {
		String query1 = " d.FORM_ID IN ('" + refIds.replaceAll(",", "','") + "') ";
		String query2 = " d.FORM_ID IN ('" + formIds.replaceAll(",", "','") + "') ";
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_COLLECT_REF_FORM") 
//  				(COLLECT_FORM_ID, REF_FORM_ID) 
//  				WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            d.form_id,
//  			            d.item_id
//  			        FROM
//  			        :Com_("D_T1_REQUEST_DETAIL") d
//  			        WHERE
//  			        :query1
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            d.form_id,
//  			            d.item_id
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_DETAIL") d
//  			        WHERE
//  			        :query2
//  			    )
//  			SELECT DISTINCT
//  			    b.form_id,
//  			    a.form_id
//  			FROM
//  			    a
//  			INNER JOIN
//  			    b
//  			ON
//  			    a.item_id = b.item_id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = query1;
  String __sJT_4 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_5 = query2;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:101^3*/
		closeDefaultContext(myCtx);
		return 1;
	}
	
	public List<RequestHeader> query(String lcCode, java.util.Date startDate, java.util.Date endDate, String formType)
			throws NoPrivilegeException,SQLException,NoConnection {
		return query(lcCode, startDate, endDate,  formType,"");
	}
	
	public List<RequestHeader> query(String lcCode, java.util.Date startDate, java.util.Date endDate, String formType,String deliveryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (FormConstant.REQUEST_TEMPLATE.equals(formType)) { // 如果是餐厅要货，只对统配方式的单据进行汇总
			//query = query + " AND h.delivery_type = 'UNIFIED'";
		}
		
		if (!TextUtil.isEmpty(deliveryType)) {
			query += "  AND t.DELIVERY_TYPE = '"+ deliveryType + "' ";
		}
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:127^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    s.STATUS AS FORM_STATUS,
//  			    decode(t.DELIVERY_TYPE,'DIRECT','直配','UNIFIED','统配','CROSS','越库','') deliveryType
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH") b
//  			ON
//  			    h.BUYER_ID = b.BRANCH_ID
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_TYPE") t
//  			ON
//  			    b.REGION_ID = t.REGION_ID
//  			AND SUBSTR(h.MAX_PAY_ITEM, INSTR(h.MAX_PAY_ITEM,'[')+1 ,INSTR(h.MAX_PAY_ITEM,']')-2) = t.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    :query
//  			AND h.PROVIDER_ID = :lcCode
//  			AND NVL(s.STATUS, 'null') = '已审核'
//  			AND NVL(h.PURCHASE_STATUS, 'null') != '已汇总'
//  			AND h.FORM_TYPE = :formType
//  			AND (
//  			        h.AUDIT_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.AUDIT_TIME <= :eDate
//  			    OR  :eDate IS NULL)
//  			ORDER BY
//  			    h.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_3 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  String __sJT_6 = lcCode;
  String __sJT_7 = formType;
  Date __sJT_8 = sDate;
  Date __sJT_9 = sDate;
  Date __sJT_10 = eDate;
  Date __sJT_11 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:161^17*/
		List<RequestHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/**
	 * 物流中心根据营业时间查询未汇总的需求预估单
	 */
	public List<RequestHeader> queryEstimate(java.util.Date businessDate)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:177^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//              	h.*, s.STATUS AS FORM_STATUS,'' deliveryType
//              FROM
//              :Com_("D_T1_REQUEST_HEADER") h
//      		LEFT JOIN
//      		:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//              WHERE
//  				NVL(s.STATUS, 'null') = '已审核'
//  			AND NVL(h.PURCHASE_STATUS, 'null') != '已汇总'
//  			AND h.FORM_TYPE = 'estimate'
//              AND h.FORM_TIME = :bDate 
//      		ORDER BY
//      			h.FORM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  Date __sJT_3 = bDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:194^3*/
		List<RequestHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/**
	 * 查询补单记录，即满足如下条件的单据：到货日期-到货周期<=营业日期，不参与汇总，直接转化为采购单
	 * 条件二：未被汇总的单据
	 */
	public List<RequestHeader> queryAddition(java.util.Date businessDate, String formType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (FormConstant.REQUEST_TEMPLATE.equals(formType)) { // 如果是餐厅要货，只对统配方式的单据进行汇总
//			query = query + " AND h.delivery_type = 'UNIFIED'";
			//先不论统配、直配、越库，补单按相同的方式处理
		}
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:216^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//                  *,
//                  '' deliveryType
//              FROM
//              :Com_("D_T1_REQUEST_HEADER") h
//      		LEFT JOIN
//      		:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//              WHERE
//  				:query
//  			AND	NVL( s.STATUS, 'null') = '已审核'
//              AND h.FORM_TYPE = :formType
//              AND h.RECEIVE_TIME - h.ARRIVE_PERIOD <= :bDate
//      		ORDER BY
//      			h.FORM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = formType;
  Date __sJT_5 = bDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:234^3*/
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
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setArrivePeriod(headerIter.arrive_period());
			header.setFormStatus(headerIter.form_status());
			header.setTemplateId(headerIter.template_Id());
			header.setTemplateName(headerIter.template_Name());
			header.setDeliveryType(headerIter.deliveryType());
			headerLst.add(header);
		}
		return headerLst;
	}
	
	
	/**
	 * 获取最大的到货日期
	 */
	public java.util.Date queryReceiveTime(String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " h.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";

		Date receiveTime = null;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:279^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    MAX(h.RECEIVE_TIME) 
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  		    WHERE
//  	    		:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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
    receiveTime = __sJT_rtRs.getDate(1);
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

/*@lineinfo:user-code*//*@lineinfo:286^3*/
		closeDefaultContext(myCtx);
		return SqlDateUtil.getUtilDate(receiveTime);
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:291^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class AverageIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public AverageIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    avg_countNdx = findColumn("avg_count");
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
  public Double avg_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(avg_countNdx);
  }
  private int avg_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:292^20*/

	/**
	 * 查询日均要货量
	 */
	public List<Map> queryAverageRequest(java.util.Date startDate, java.util.Date endDate, String[] ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		long days = DateTimeUtil.diffDays(endDate, startDate) + 1;
		String query = " d.ITEM_ID IN ('" + StringUtils.join(ids, ",").replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		AverageIter avgIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:306^3*/

//  ************************************************************
//  #sql [myCtx] avgIter = { SELECT
//  			    d.item_id,
//  			    d.ITEM_NAME,
//  			    SUM(d.ORDER_COUNT) / :days AS avg_count
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_REQUEST_DETAIL") d
//  			ON
//  			    d.FORM_ID = h.FORM_ID
//  			WHERE
//  			    h.FORM_TIME >= :sDate
//  			AND h.FORM_TIME <= :eDate
//  			AND	:query
//  			GROUP BY
//  			    d.item_id,
//  			    d.ITEM_NAME
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  long __sJT_1 = days;
  String __sJT_2 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_3 = Com_("D_T1_REQUEST_DETAIL");
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  String __sJT_6 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setLong(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      avgIter = new AverageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:324^3*/
		
		List<Map> itemLst = new ArrayList<Map>();
		while(avgIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", avgIter.item_id());
			item.put("itemName", avgIter.item_name());
			item.put("avgCount", avgIter.avg_count());
			itemLst.add(item);
		}
		avgIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:339^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class MRPIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MRPIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    shelf_nameNdx = findColumn("shelf_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    category_nameNdx = findColumn("category_name");
    infosNdx = findColumn("infos");
    order_countNdx = findColumn("order_count");
    allocationNdx = findColumn("allocation");
    real_countNdx = findColumn("real_count");
    on_the_wayNdx = findColumn("on_the_way");
    min_order_countNdx = findColumn("min_order_count");
    supply_periodNdx = findColumn("supply_period");
    inventorys_cycleNdx = findColumn("inventorys_cycle");
    distributionNdx = findColumn("distribution");
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
  public String shelf_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_nameNdx);
  }
  private int shelf_nameNdx;
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
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double allocation() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(allocationNdx);
  }
  private int allocationNdx;
  public Double real_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(real_countNdx);
  }
  private int real_countNdx;
  public Double on_the_way() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(on_the_wayNdx);
  }
  private int on_the_wayNdx;
  public Double min_order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(min_order_countNdx);
  }
  private int min_order_countNdx;
  public Double supply_period() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(supply_periodNdx);
  }
  private int supply_periodNdx;
  public Double inventorys_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventorys_cycleNdx);
  }
  private int inventorys_cycleNdx;
  public Double distribution() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(distributionNdx);
  }
  private int distributionNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:354^3*/
			
	public List<Map> queryMRP(String lcCode, String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " h.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		MRPIter mrpIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:362^3*/

//  ************************************************************
//  #sql [myCtx] mrpIter = { WITH
//  			    a1 AS
//  			    (
//  			        SELECT
//  			            d.ITEM_ID,
//  			            SUM(d.ORDER_COUNT) ORDER_COUNT
//  			        FROM
//  			            :Com_("D_T1_REQUEST_HEADER")  h
//  			        INNER JOIN
//  			            :Com_("D_T1_REQUEST_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        AND :query
//  		            LEFT JOIN
//  		            	:Com_("D_T2_DELIVERY_REGION_BRANCH") rb 
//  			        ON
//  			            rb.BRANCH_ID = h.BUYER_ID
//  			        INNER JOIN
//  			        	:Com_("D_T2_DELIVERY_TYPE") dt 
//  			        ON
//  			            dt.REGION_ID = rb.REGION_ID
//  			        AND dt.ITEM_ID = d.item_id
//  			        AND dt.DELIVERY_TYPE = 'UNIFIED'
//  			        GROUP BY
//  			            d.ITEM_ID
//  			    )
//  			    ,
//  			     items AS
//  			    (
//  			        SELECT
//  			            a1.item_id,
//  			            a1.ORDER_COUNT,
//  			            a2.item_count ALLOCATION
//  			        FROM
//  			            a1
//  			        LEFT JOIN
//  			        	:Com_("d_v_mrp_a_lc") a2
//  			        ON
//  			            a1.item_id = a2.item_id
//  			    )
//  			    ,
//  			    shelf_one AS
//  			    (
//  			        SELECT
//  			            s.ITEM_ID,
//  			            s.SHELF_ID,
//  			            ts.SHELF_NAME
//  			        FROM
//  			            :Com_("D_T2_SHELF_ITEM") s
//  			        LEFT JOIN
//  			            :Com_("D_T2_SHELF") ts
//  			        ON
//  			            ts.SHELF_ID =s.SHELF_ID
//  			        WHERE
//  			            s.PRIORITY =0
//  			    )
//  			     ,
//  			    temp AS
//  			    (
//  			        SELECT
//  			            item_id,
//  			            ORDER_COUNT,
//  			            ALLOCATION,
//  			            REAL_COUNT,
//  			            MIN_ORDER_COUNT,
//  			            supply_period,
//  			            inventorys_cycle,
//  			            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP (ORDER BY PRICE_TYPE
//  			            ) infos
//  			        FROM
//  			            (
//  			                SELECT
//  			                    i.* ,
//  			                    ma.REAL_COUNT,
//  			                    ma.MIN_ORDER_COUNT,
//  			                    DECODE (ma.SUPPLY_CYCLE, NULL, ma.PRODUCTION_CYCLE,ma.SUPPLY_CYCLE)
//  			                    supply_period,
//  			                    DECODE (ma.inventorysCycle, NULL, ma.PRODUCTION_CYCLE,ma.inventorysCycle)
//  			                    inventorys_cycle,
//  			                    p.PRICE_TYPE,
//  			                    p.ITEM_PRICE
//  			                FROM
//  			                    items i
//  			                LEFT JOIN
//  			                	:Com_("D_V_MRP_AMOUNT_LC") ma
//  			                ON
//  			                    ma.ITEM_ID = i.ITEM_ID
//  			                AND ma.BRANCH_ID = :lcCode
//  			                LEFT JOIN
//  			                    :Com_("D_T2_ITEM_PRICE") p
//  			                ON
//  			                    p.ITEM_ID = i.ITEM_ID
//  			                AND p.IS_CURRENT = 1
//  			                AND(
//  			                        p.SUPPLIER_ID IS NULL
//  			                    OR  p.SUPPLIER_ID= 'NULL'
//  			                    OR  ma.SUPPLIER_ID = p.SUPPLIER_ID)
//  			                AND p.PRICE_TYPE IN ('PURCHASE',
//  			                                     'BENCHMARK',
//  			                                     'SUPPLIER') )
//  			        GROUP BY
//  			            item_id,
//  			            ORDER_COUNT,
//  			            ALLOCATION,
//  			            REAL_COUNT,
//  			            MIN_ORDER_COUNT,
//  			            supply_period,
//  			            inventorys_cycle
//  			    )
//  			SELECT
//  			    t.item_id,
//  			    t.ORDER_COUNT,
//  			    s.SHELF_NAME,
//  			    t.ALLOCATION,
//  			    t.REAL_COUNT,
//  			    nvl(t.MIN_ORDER_COUNT,1) MIN_ORDER_COUNT,
//  			    t.supply_period,
//  			    t.inventorys_cycle,
//  			    t.infos,
//  			    v2.item_count ON_THE_WAY,
//  			    m.ITEM_NAME,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    c.CATEGORY_ID ITEM_CATEGORY,
//  			    c.CATEGORY_NAME,
//  			    DECODE(SIGN(mod(t.ORDER_COUNT,NVL(du.DELIVERY_FACTOR,1))),0,t.ORDER_COUNT,t.ORDER_COUNT-mod
//  			    (t.ORDER_COUNT,NVL(du.DELIVERY_FACTOR,1))+du.DELIVERY_FACTOR) distribution
//  			FROM
//  			    temp t
//  			LEFT JOIN
//  				:Com_("d_v_mrp_o_lc")  v2
//  			ON
//  			    t.item_id = v2.item_id
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_META") m
//  			ON
//  			    t.item_id = m.ITEM_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    m.CATEGORY_ID = c.CATEGORY_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_DELIVERY_UNIT") du
//  			ON
//  			    t.item_id = du.item_id
//  			LEFT JOIN
//  				shelf_one s
//  			ON
//  			    s.ITEM_ID=m.ITEM_ID
//  			ORDER BY
//  			    s.SHELF_ID,
//  			    t.item_id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = query;
  String __sJT_4 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_5 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_6 = Com_("d_v_mrp_a_lc");
  String __sJT_7 = Com_("D_T2_SHELF_ITEM");
  String __sJT_8 = Com_("D_T2_SHELF");
  String __sJT_9 = Com_("D_V_MRP_AMOUNT_LC");
  String __sJT_10 = lcCode;
  String __sJT_11 = Com_("D_T2_ITEM_PRICE");
  String __sJT_12 = Com_("d_v_mrp_o_lc");
  String __sJT_13 = Com_("D_T2_ITEM_META");
  String __sJT_14 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_15 = Com_("D_T2_DELIVERY_UNIT");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 6);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      mrpIter = new MRPIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:515^3*/
		List<Map> itemLst = new ArrayList<Map>();
		while(mrpIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", mrpIter.item_id());
			item.put("itemName", mrpIter.item_name());
			item.put("shelfName", mrpIter.shelf_name());
			item.put("itemCategory", mrpIter.item_category());
			item.put("categoryName", mrpIter.category_name());
			item.put("itemDimension", mrpIter.item_dimension());
			item.put("itemSpecification", mrpIter.item_specification());
			item.put("requestCount", mrpIter.order_count());
			item.put("minOrderCount", mrpIter.min_order_count());
			item.put("realCount", mrpIter.real_count());
			item.put("allocation", mrpIter.allocation());
			item.put("roadCount", mrpIter.on_the_way());
			item.put("supplyPeriod", mrpIter.supply_period());
			item.put("inventorysCycle", mrpIter.inventorys_cycle());
			item.put("distribution", mrpIter.distribution());
			
			String[] infos = mrpIter.infos().toString().split(",");
			for (int i = 0; i < infos.length; i++) {
				String[] temp = infos[i].replace("(", "").replace(")", "").split("\\|");
				int length = temp.length;
				
				for (int j = 0; j < temp.length; j++) {
					if(length>0){
						if("BENCHMARK".equals(temp[0].toString())){
							item.put("itemUnitPrice",temp[1]);
						}
					}
					
					if(length>1){
						if("PURCHASE".equals(temp[0].toString())){
							item.put("itemPrice",temp[1]);
						}
					}
					
					if(length>2){
						if("SUPPLIER".equals(temp[0].toString())){
							item.put("itemPrice",temp[1]);
						}
					}
				}
			}
		
			itemLst.add(item);
		}
		mrpIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:568^2*/

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
    form_typeNdx = findColumn("form_type");
    buyer_idNdx = findColumn("buyer_id");
    buyer_nameNdx = findColumn("buyer_name");
    supply_periodNdx = findColumn("supply_period");
    inventorys_cycleNdx = findColumn("inventorys_cycle");
    delivery_typeNdx = findColumn("delivery_type");
    region_idNdx = findColumn("region_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_priceNdx = findColumn("item_price");
    item_unit_priceNdx = findColumn("item_unit_price");
    order_countNdx = findColumn("order_count");
    min_order_countNdx = findColumn("min_order_count");
    pay_amtNdx = findColumn("pay_amt");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
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
  public Integer supply_period() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(supply_periodNdx);
  }
  private int supply_periodNdx;
  public Integer inventorys_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(inventorys_cycleNdx);
  }
  private int inventorys_cycleNdx;
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
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
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
  public Double min_order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(min_order_countNdx);
  }
  private int min_order_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:574^79*/
	
	public List<Map> querySummary(String lcCode, String ids, String deliveryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " h.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		String orderClause = " dt.DELIVERY_TYPE, s.SUPPLIER_ID, d.ITEM_ID, h.BUYER_ID";
		if (FormConstant.DIRECT_DELIVERY.equals(deliveryType)) {
			query = query + " AND dt.DELIVERY_TYPE = 'DIRECT' ";
			// orderClause = " dt.DELIVERY_TYPE, s.SUPPLIER_ID, h.BUYER_ID, d.ITEM_ID";
		} else if (FormConstant.CROSS_DELIVERY.equals(deliveryType)) {
			query = query + " AND dt.DELIVERY_TYPE = 'CROSS' ";
		} else {
			query = query + " AND dt.DELIVERY_TYPE IN( 'UNIFIED', NULL) ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:591^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    h.FORM_ID,
//  			    h.BUYER_ID,
//  			    h.BUYER_NAME,
//  			    h.FORM_TYPE,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    NVL(p.ITEM_PRICE, p2.ITEM_PRICE) AS ITEM_PRICE,
//  			    p3.ITEM_PRICE AS ITEM_UNIT_PRICE,
//  			    CEIL(d.ORDER_COUNT * 10000/ nvl(mrp.MIN_ORDER_COUNT,1))*nvl(mrp.MIN_ORDER_COUNT,1)/10000 ORDER_COUNT,
//  			    mrp.MIN_ORDER_COUNT,
//  			    d.PAY_AMT,
//  			    rb.REGION_ID,
//  			    dt.DELIVERY_TYPE,
//  			    s.SUPPLIER_ID AS PROVIDER_ID,
//  			    DECODE (mrp.SUPPLY_CYCLE, NULL, mrp.PRODUCTION_CYCLE,mrp.SUPPLY_CYCLE)
//                  supply_period,
//                  DECODE (mrp.inventorysCycle, NULL, mrp.PRODUCTION_CYCLE,mrp.inventorysCycle)
//                  inventorys_cycle,
//  			    b.BRANCH_NAME AS PROVIDER
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
//  			AND dt.ITEM_ID = d.item_id
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			ON
//  			    ((
//  			            s.BRANCH_ID = h.BUYER_ID
//  			        AND dt.DELIVERY_TYPE = 'DIRECT') --只有直配与门店关联
//  			    OR  (
//  			            s.BRANCH_ID = :lcCode
//  			        AND dt.DELIVERY_TYPE != 'DIRECT') )
//  			AND s.ITEM_ID = d.ITEM_ID
//  			AND s.PRIORITY = 0 --主供应商
//  					LEFT JOIN
//  					:Com_("D_T2_ITEM_PRICE") p
//  				ON
//  				    p.PRICE_TYPE = 'SUPPLIER'
//  				AND p.SUPPLIER_ID = s.SUPPLIER_ID --获取供应商的价格
//  				AND p.ITEM_ID = d.ITEM_ID
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p2
//  				ON
//  				    p2.PRICE_TYPE = 'PURCHASE'
//  				AND p2.ITEM_ID = d.ITEM_ID
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p3
//  			ON
//  			    p3.PRICE_TYPE = 'BENCHMARK'
//  			AND p3.ITEM_ID = d.ITEM_ID
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = s.SUPPLIER_ID
//  			LEFT JOIN
//  				:Com_("D_V_MRP_AMOUNT_LC") mrp
//  			ON
//  			    mrp.item_id = d.ITEM_ID
//  			AND mrp.BRANCH_ID = :lcCode
//  		    WHERE
//  	    		:query
//  			ORDER BY
//  				:orderClause
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
  String __sJT_5 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_6 = lcCode;
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = Com_("D_T2_ITEM_PRICE");
  String __sJT_9 = Com_("D_T2_ITEM_PRICE");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = Com_("D_V_MRP_AMOUNT_LC");
  String __sJT_12 = lcCode;
  String __sJT_13 = query;
  String __sJT_14 = orderClause;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 7);
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
      __sJT_stmt.setString(14, __sJT_14);
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

/*@lineinfo:user-code*//*@lineinfo:670^3*/
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
			detail.put("formType", detailIter.form_type());
			detail.put("buyerId", detailIter.buyer_id());
			detail.put("buyerName", detailIter.buyer_name());
			detail.put("supplyPeriod", detailIter.supply_period());
			detail.put("inventorysCycle", detailIter.inventorys_cycle());
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemCategory", detailIter.item_category());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("itemSpecification", detailIter.item_specification());
			
			Double orderCount = detailIter.order_count();
			Double itemUnitPrice = detailIter.item_unit_price();
			Double payAmt = null;
			if (itemUnitPrice != null) {
				payAmt = orderCount * itemUnitPrice;
			}
			Double itemPrice = detailIter.item_price();
			Double unitAmt = null;
			if (itemPrice != null) {
				unitAmt = orderCount * itemPrice;
			}
			detail.put("orderCount", orderCount);
			detail.put("minOrderCount", detailIter.min_order_count());
			detail.put("itemUnitPrice", itemUnitPrice);
			detail.put("itemPrice", itemPrice);
			detail.put("payAmt", payAmt);
			detail.put("unitAmt", unitAmt);
			
			detail.put("regionId", detailIter.region_id());
			detail.put("deliveryType", detailIter.delivery_type());
			detail.put("providerId", detailIter.provider_id());
			detail.put("provider", detailIter.provider());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class RequestSummaryBean_SJProfileKeys 
{
  private static RequestSummaryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestSummaryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestSummaryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.RequestSummaryBean_SJProfile0");
  }
}
