/*@lineinfo:filename=RequestHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 26, 2014 by liyzh
 * Last edited on Aug 26, 2014 by liyzh
 * 
 * 说明： 餐厅要货、外部订货商订货需求表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.RequestHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormType;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class RequestHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequestHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

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
    rownumberNdx = findColumn("rownumber");
    form_idNdx = findColumn("form_id");
    form_typeNdx = findColumn("form_type");
    buyer_idNdx = findColumn("buyer_id");
    buyer_nameNdx = findColumn("buyer_name");
    buyer_addressNdx = findColumn("buyer_address");
    receive_timeNdx = findColumn("receive_time");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    purchase_statusNdx = findColumn("purchase_status");
    shipping_statusNdx = findColumn("shipping_status");
    form_noteNdx = findColumn("form_note");
    ref_date_start1Ndx = findColumn("ref_date_start1");
    ref_date_end1Ndx = findColumn("ref_date_end1");
    ref_date_start2Ndx = findColumn("ref_date_start2");
    ref_date_end2Ndx = findColumn("ref_date_end2");
    ref_date_start3Ndx = findColumn("ref_date_start3");
    ref_date_end3Ndx = findColumn("ref_date_end3");
    delay_predictNdx = findColumn("delay_predict");
    purchase_predictNdx = findColumn("purchase_predict");
    safety_stockNdx = findColumn("safety_stock");
    sell_predictNdx = findColumn("sell_predict");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    arrive_periodNdx = findColumn("arrive_period");
    delivery_typeNdx = findColumn("delivery_type");
    form_statusNdx = findColumn("form_status");
    providerNdx = findColumn("provider");
    provider_IdNdx = findColumn("provider_Id");
    template_IdNdx = findColumn("template_Id");
    template_NameNdx = findColumn("template_Name");
    isAddFormNdx = findColumn("isAddForm");
  }
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
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
  public Date form_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_time_actualNdx);
  }
  private int form_time_actualNdx;
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
  public String purchase_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(purchase_statusNdx);
  }
  private int purchase_statusNdx;
  public String shipping_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shipping_statusNdx);
  }
  private int shipping_statusNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public Date ref_date_start1() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(ref_date_start1Ndx);
  }
  private int ref_date_start1Ndx;
  public Date ref_date_end1() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(ref_date_end1Ndx);
  }
  private int ref_date_end1Ndx;
  public Date ref_date_start2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(ref_date_start2Ndx);
  }
  private int ref_date_start2Ndx;
  public Date ref_date_end2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(ref_date_end2Ndx);
  }
  private int ref_date_end2Ndx;
  public Date ref_date_start3() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(ref_date_start3Ndx);
  }
  private int ref_date_start3Ndx;
  public Date ref_date_end3() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(ref_date_end3Ndx);
  }
  private int ref_date_end3Ndx;
  public Double delay_predict() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delay_predictNdx);
  }
  private int delay_predictNdx;
  public Double purchase_predict() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(purchase_predictNdx);
  }
  private int purchase_predictNdx;
  public Double safety_stock() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(safety_stockNdx);
  }
  private int safety_stockNdx;
  public Double sell_predict() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sell_predictNdx);
  }
  private int sell_predictNdx;
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
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String provider_Id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_IdNdx);
  }
  private int provider_IdNdx;
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
  public String isAddForm() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(isAddFormNdx);
  }
  private int isAddFormNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:74^3*/
	
	public int newSerial(java.util.Date businessDate, String branchId) 
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String maxId = null;
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    MAX(h.FORM_ID) 
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			WHERE
//  			    h.FORM_TIME = :bDate
//  			AND h.BUYER_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  Date __sJT_2 = bDate;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
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
    maxId = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:90^3*/
		closeDefaultContext(myCtx);

		int result = 1;
		if (maxId != null) {
			maxId = maxId.substring(maxId.length() - FormConstant.DEFAULT_SERIAL_LENGTH);
			result = Integer.parseInt(maxId) + 1;
		}
		return result;
	}
	
	/**
	 * 保存
	 */
	public int saveEntity(RequestHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String formType = header.getFormType();
		String buyerId = header.getBuyerId();
		String buyerName = header.getBuyerName();
		String storageId = header.getStorageId();
		String storage = header.getStorage();
		String buyerAddress = header.getBuyerAddress();
		
		String providerId = header.getProviderId();
		String provider = header.getProvider();
		
		String templateId = header.getTemplateId();
		String templateName = header.getTemplateName();
		
		Date receiveTime = SqlDateUtil.getSqlDate(header.getReceiveTime());
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();
		Date refDateStart1 = SqlDateUtil.getSqlDate(header.getRefDateStart1());
		Date refDateEnd1 = SqlDateUtil.getSqlDate(header.getRefDateEnd1());
		Date refDateStart2 = SqlDateUtil.getSqlDate(header.getRefDateStart2());
		Date refDateEnd2 = SqlDateUtil.getSqlDate(header.getRefDateEnd2());
		Date refDateStart3 = SqlDateUtil.getSqlDate(header.getRefDateStart3());
		Date refDateEnd3 = SqlDateUtil.getSqlDate(header.getRefDateEnd3());
		Double delayPredict = header.getDelayPredict();
		Double purchasePredict = header.getPurchasePredict();
		Double safetyStock = header.getSafetyStock();
		Double sellPredict = header.getSellPredict();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();
		int arrivePeriod = header.getArrivePeriod();
		String deliveryType = header.getDeliveryType();
		String isAddForm = header.getIsAddForm();

		if (FormType.REQUEST_REST.equals(formType)) {
			/*@lineinfo:generated-code*//*@lineinfo:146^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    s.STORAGE_ID,
//  				    s.STORAGE_NAME 
//  				FROM
//  				:Com_("D_T2_BRANCH_STORAGE") s
//  				WHERE
//  				    s.BRANCH_ID = :buyerId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_2 = buyerId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 1);
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
    sqlj.runtime.ref.ResultSetIterImpl.checkColumns(__sJT_rtRs, 2);
    if (!__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_NO_ROW_SELECT_INTO();
    }
    storageId = __sJT_rtRs.getString(1);
    storage = __sJT_rtRs.getString(2);
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

/*@lineinfo:user-code*//*@lineinfo:154^4*/
		}

		/*@lineinfo:generated-code*//*@lineinfo:157^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_REQUEST_HEADER") 
//  				(FORM_ID, FORM_TYPE, BUYER_ID, BUYER_NAME, STORAGE_ID, STORAGE, BUYER_ADDRESS,PROVIDER_ID,PROVIDER,TEMPLATE_ID,TEMPLATE_NAME, RECEIVE_TIME, FORM_MAKER, 
//  						FORM_TIME, FORM_TIME_ACTUAL, AUDITOR, AUDIT_TIME, FORM_NOTE, 
//  						REF_DATE_START1, REF_DATE_END1, REF_DATE_START2, REF_DATE_END2, REF_DATE_START3, REF_DATE_END3,
//  						DELAY_PREDICT, PURCHASE_PREDICT, SAFETY_STOCK, SELL_PREDICT, ALL_PAY_AMT, MAX_PAY_ITEM, ARRIVE_PERIOD, DELIVERY_TYPE,ISADDFORM) 
//  						VALUES (:formId, :formType,
//  								:buyerId, :buyerName, :storageId, :storage,
//  								:buyerAddress,
//  								:providerId,
//  								:provider,
//  								:templateId,
//  								:templateName,
//  								:receiveTime, 
//  								:formMaker, 
//  								:formTime, :formTimeActual,
//  								:auditor, 
//  								:auditTime, 
//  								:formNote, 
//  								:refDateStart1, 
//  								:refDateEnd1, 
//  								:refDateStart2, 
//  								:refDateEnd2, 
//  								:refDateStart3, 
//  								:refDateEnd3, 
//  								:delayPredict, 
//  								:purchasePredict, 
//  								:safetyStock, 
//  								:sellPredict, :allPayAmt, :maxPayItem, :arrivePeriod, :deliveryType,:isAddForm)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formType;
  String __sJT_4 = buyerId;
  String __sJT_5 = buyerName;
  String __sJT_6 = storageId;
  String __sJT_7 = storage;
  String __sJT_8 = buyerAddress;
  String __sJT_9 = providerId;
  String __sJT_10 = provider;
  String __sJT_11 = templateId;
  String __sJT_12 = templateName;
  Date __sJT_13 = receiveTime;
  String __sJT_14 = formMaker;
  Date __sJT_15 = formTime;
  Date __sJT_16 = formTimeActual;
  String __sJT_17 = auditor;
  Date __sJT_18 = auditTime;
  String __sJT_19 = formNote;
  Date __sJT_20 = refDateStart1;
  Date __sJT_21 = refDateEnd1;
  Date __sJT_22 = refDateStart2;
  Date __sJT_23 = refDateEnd2;
  Date __sJT_24 = refDateStart3;
  Date __sJT_25 = refDateEnd3;
  Double __sJT_26 = delayPredict;
  Double __sJT_27 = purchasePredict;
  Double __sJT_28 = safetyStock;
  Double __sJT_29 = sellPredict;
  Double __sJT_30 = allPayAmt;
  String __sJT_31 = maxPayItem;
  int __sJT_32 = arrivePeriod;
  String __sJT_33 = deliveryType;
  String __sJT_34 = isAddForm;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 2);
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
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setDate(20, __sJT_20);
      __sJT_stmt.setDate(21, __sJT_21);
      __sJT_stmt.setDate(22, __sJT_22);
      __sJT_stmt.setDate(23, __sJT_23);
      __sJT_stmt.setDate(24, __sJT_24);
      __sJT_stmt.setDate(25, __sJT_25);
      __sJT_stmt.setDoubleWrapper(26, __sJT_26);
      __sJT_stmt.setDoubleWrapper(27, __sJT_27);
      __sJT_stmt.setDoubleWrapper(28, __sJT_28);
      __sJT_stmt.setDoubleWrapper(29, __sJT_29);
      __sJT_stmt.setDoubleWrapper(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setInt(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:186^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(RequestHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Date receiveTime = SqlDateUtil.getSqlDate(header.getReceiveTime());
		String formNote = header.getFormNote();
		Double delayPredict = header.getDelayPredict();
		Double purchasePredict = header.getPurchasePredict();
		Double safetyStock = header.getSafetyStock();
		Double sellPredict = header.getSellPredict();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:206^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_REQUEST_HEADER")
//  			SET
//  				RECEIVE_TIME = :receiveTime,
//  				FORM_NOTE = :formNote,
//  				DELAY_PREDICT = :delayPredict,
//  				PURCHASE_PREDICT = :purchasePredict,
//  				SAFETY_STOCK = :safetyStock,
//  				SELL_PREDICT = :sellPredict,
//  			    ALL_PAY_AMT = :allPayAmt,
//  			    MAX_PAY_ITEM = :maxPayItem
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  Date __sJT_2 = receiveTime;
  String __sJT_3 = formNote;
  Double __sJT_4 = delayPredict;
  Double __sJT_5 = purchasePredict;
  Double __sJT_6 = safetyStock;
  Double __sJT_7 = sellPredict;
  Double __sJT_8 = allPayAmt;
  String __sJT_9 = maxPayItem;
  String __sJT_10 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:220^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateDeliveryType(String formId) 
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:230^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			    :Com_("D_T1_REQUEST_DETAIL") d2
//  			    SET
//  			        d2.DELIVERY_TYPE =
//  			        (
//  			            SELECT
//  			                DECODE(m.ITEM_TYPE,'RAW',dt.DELIVERY_TYPE,'UNIFIED')DELIVERY_TYPE
//  			            FROM
//  			                :Com_("D_T1_REQUEST_DETAIL") d
//  			            INNER JOIN
//  			                :Com_("D_T1_REQUEST_HEADER") h
//  			            ON
//  			                d.FORM_ID = h.FORM_ID
//  			            AND h.FORM_ID = :formId
//  			            LEFT JOIN
//  			                :Com_("D_T2_DELIVERY_REGION_BRANCH") drb
//  			            ON
//  			                h.BUYER_ID = drb.BRANCH_ID
//  			            LEFT JOIN
//  			                :Com_("D_T2_DELIVERY_TYPE") dt
//  			            ON
//  			                drb.REGION_ID = dt.REGION_ID
//  			            AND d.ITEM_ID = dt.ITEM_ID
//  			            LEFT JOIN
//  			                :Com_("D_T2_ITEM_META") m
//  			            ON
//  			                d.ITEM_ID = m.ITEM_ID
//  			            WHERE
//  			                d.FORM_ID = :formId
//                  		AND d.FORM_ID=d2.FORM_ID
//  			            AND d.ITEM_ID = d2.ITEM_ID)
//  			    WHERE
//  			        EXISTS
//  			        (
//  			            SELECT
//  			                1
//  			            FROM
//  			                :Com_("D_T1_REQUEST_DETAIL") d
//  			            INNER JOIN
//  			                :Com_("D_T1_REQUEST_HEADER") h
//  			            ON
//  			                d.FORM_ID = h.FORM_ID
//  			            AND h.FORM_ID = :formId
//  			            LEFT JOIN
//  			                :Com_("D_T2_DELIVERY_REGION_BRANCH") drb
//  			            ON
//  			                h.BUYER_ID = drb.BRANCH_ID
//  			            LEFT JOIN
//  			                :Com_("D_T2_DELIVERY_TYPE") dt
//  			            ON
//  			                drb.REGION_ID = dt.REGION_ID
//  			            AND d.ITEM_ID = dt.ITEM_ID
//  			            LEFT JOIN
//  			                :Com_("D_T2_ITEM_META") m
//  			            ON
//  			                d.ITEM_ID = m.ITEM_ID
//  			            WHERE
//  			                d.FORM_ID = :formId
//                  		AND d.FORM_ID=d2.FORM_ID
//  			            AND d.ITEM_ID = d2.ITEM_ID) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_4 = formId;
  String __sJT_5 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_6 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_7 = Com_("D_T2_ITEM_META");
  String __sJT_8 = formId;
  String __sJT_9 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_10 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_11 = formId;
  String __sJT_12 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_13 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_14 = Com_("D_T2_ITEM_META");
  String __sJT_15 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 4);
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
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:290^43*/
			closeDefaultContext(myCtx);
			
			return 1;
		}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:300^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:306^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/**
	 * 设置是否进行采购汇总
	 */
	public int savePurchaseStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:318^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_REQUEST_HEADER")
//  			SET
//  				PURCHASE_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:325^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/**
	 * 设置是否进行配送处理
	 */
	public int saveShippingStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:336^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_REQUEST_HEADER")
//  			SET
//  				SHIPPING_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:343^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 餐厅要货单审核
	 */
	public int audit(String formId, String userId, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:357^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_REQUEST_HEADER")
//  			SET
//  				auditor = :userId,
//  				audit_time = :sAuditTime
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = userId;
  Date __sJT_3 = sAuditTime;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:365^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String formType, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:379^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//              AND h.FORM_TYPE = :formType
//              AND (h.FORM_TIME >= :sDate or :sDate is null)
//  			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = formType;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 9);
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
    total = __sJT_rtRs.getIntNoNull(1);
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
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String queryType) {
		String query = " 1=1";
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND h.BUYER_ID = '" + branchId + "'";
		}
		if ("unaudit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		} else if ("audit".equals(queryType)) { // 获取已审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '已审核'";
		} else if ("handled".equals(queryType)) {//物流中心生成外部出货单后过滤已出货单
			query = query + " AND NVL( s.STATUS, 'null') = '已审核' AND h.SHIPPING_STATUS IS NULL";
		} else if ("dcreate".equals(queryType)) { // 获取已审核的统配要货单记录，用于生成相应的配送单
			query = query + " AND NVL( s.STATUS, 'null') = '已审核' AND h.DELIVERY_TYPE = 'UNIFIED'";
		} else if ("pcreate".equals(queryType)) { // 获取已审核的非统配要货单记录，用于生成相应的采购单（即直配和越库单据）
			query = query + " AND NVL( s.STATUS, 'null') = '已审核' AND h.DELIVERY_TYPE != 'UNIFIED'";
		}
		return query;
	}
	
	/**
	 * 查询所有状态的要货单， 查询时间是该门店的营业时间
	 */
	public List<RequestHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, int startRow, int endRow, String formType, String queryType)  
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:429^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                DECODE(FORM_ID,NULL,'合计',to_char(ROWNUM)) rownumber,
//  			                ROWNUM                           rowIndex
//  			            FROM
//  			                (
//  			                    SELECT
//  			                        h.FORM_ID,
//  			                        h.FORM_TYPE,
//  			                        h.BUYER_ID,
//  			                        h.BUYER_NAME,
//  			                        h.STORAGE_ID,
//  			                        h.STORAGE,
//  			                        h.BUYER_ADDRESS,
//  			                        h.PROVIDER_ID,
//  			                        h.PROVIDER,
//  			                        h.RECEIVE_TIME,
//  			                        h.FORM_MAKER,
//  			                        h.FORM_TIME,
//  			                        h.AUDITOR,
//  			                        h.AUDIT_TIME,
//  			                        h.FORM_NOTE,
//  			                        h.REF_DATE_START1,
//  			                        h.REF_DATE_END1,
//  			                        h.REF_DATE_START2,
//  			                        h.REF_DATE_END2,
//  			                        h.REF_DATE_START3,
//  			                        h.REF_DATE_END3,
//  			                        h.DELAY_PREDICT,
//  			                        h.PURCHASE_PREDICT,
//  			                        h.SAFETY_STOCK,
//  			                        h.SELL_PREDICT,
//  			                        SUM(h.ALL_PAY_AMT) ALL_PAY_AMT,
//  			                        h.MAX_PAY_ITEM,
//  			                        h.ARRIVE_PERIOD,
//  			                        h.DELIVERY_TYPE,
//  			                        h.PURCHASE_STATUS,
//  			                        h.SHIPPING_STATUS,
//  			                        h.FORM_TIME_ACTUAL,
//  			                        h.TEMPLATE_ID,
//  			                        h.TEMPLATE_NAME,
//  			                        h.ISADDFORM,
//  			                        s.STATUS AS FORM_STATUS
//  			                    FROM
//  			                        :Com_("D_T1_REQUEST_HEADER") h
//  			                    LEFT JOIN
//  			                        :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        s.FORM_ID = h.FORM_ID
//  			                    AND s.IS_CURRENT = 1
//  			                    WHERE
//  			                        :query
//  			                    AND h.FORM_TYPE = :formType
//  			                    AND (
//  			                            h.FORM_TIME >= :sDate
//  			                        OR  :sDate IS NULL)
//  			                    AND (
//  			                            h.FORM_TIME <= :eDate
//  			                        OR  :eDate IS NULL)
//  			                    GROUP BY
//  			                        GROUPING SETS( ( h.FORM_ID, h.FORM_TYPE, h.BUYER_ID, h.BUYER_NAME, h.STORAGE_ID
//  			                        , h.STORAGE, h.BUYER_ADDRESS, h.PROVIDER_ID, h.PROVIDER, h.RECEIVE_TIME,
//  			                        h.FORM_MAKER, h.FORM_TIME, h.AUDITOR, h.AUDIT_TIME, h.FORM_NOTE,
//  			                        h.REF_DATE_START1, h.REF_DATE_END1, h.REF_DATE_START2, h.REF_DATE_END2,
//  			                        h.REF_DATE_START3, h.REF_DATE_END3, h.DELAY_PREDICT, h.PURCHASE_PREDICT,
//  			                        h.SAFETY_STOCK, h.SELL_PREDICT, h.ALL_PAY_AMT, h.MAX_PAY_ITEM, h.ARRIVE_PERIOD,
//  			                        h.DELIVERY_TYPE, h.PURCHASE_STATUS, h.SHIPPING_STATUS, h.FORM_TIME_ACTUAL,
//  			                        h.TEMPLATE_ID, h.TEMPLATE_NAME, h.ISADDFORM, s.STATUS),NULL)
//  			                    ORDER BY
//  			                        NVL(h.FORM_ID,' ') DESC ) t
//  		                        WHERE
//  					                ROWNUM < :endRow)
//  					    WHERE
//  					    	rowIndex >= :startRow };
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
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  int __sJT_9 = endRow;
  int __sJT_10 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 10);
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
      __sJT_stmt.setInt(9, __sJT_9);
      __sJT_stmt.setInt(10, __sJT_10);
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

/*@lineinfo:user-code*//*@lineinfo:507^32*/
		List<RequestHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public RequestHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:518^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    s.STATUS FORM_STATUS,
//  			    ''       rownumber
//  			FROM
//  				:Com_("D_T1_REQUEST_HEADER") h
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    h.form_id =  :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestHeaderBean_SJProfileKeys.getKey(0), 11);
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

/*@lineinfo:user-code*//*@lineinfo:532^3*/
		List<RequestHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<RequestHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<RequestHeader> headerLst = new ArrayList<RequestHeader>();
		while(headerIter.next()) {
			RequestHeader header = new RequestHeader();
			header.setRownumber(headerIter.rownumber());
			header.setFormId(headerIter.form_id());
			header.setFormType(headerIter.form_type());
			header.setBuyerId(headerIter.buyer_id());
			header.setBuyerName(headerIter.buyer_name());
			header.setBuyerAddress(headerIter.buyer_address());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setPurchaseStatus(headerIter.purchase_status());
			header.setShippingStatus(headerIter.shipping_status());
			header.setFormNote(headerIter.form_note());
			header.setRefDateStart1(SqlDateUtil.getUtilDate(headerIter.ref_date_start1()));
			header.setRefDateEnd1(SqlDateUtil.getUtilDate(headerIter.ref_date_end1()));
			header.setRefDateStart2(SqlDateUtil.getUtilDate(headerIter.ref_date_start2()));
			header.setRefDateEnd2(SqlDateUtil.getUtilDate(headerIter.ref_date_end2()));
			header.setRefDateStart3(SqlDateUtil.getUtilDate(headerIter.ref_date_start3()));
			header.setRefDateEnd3(SqlDateUtil.getUtilDate(headerIter.ref_date_end3()));
			header.setDelayPredict(headerIter.delay_predict());
			header.setPurchasePredict(headerIter.purchase_predict());
			header.setSafetyStock(headerIter.safety_stock());
			header.setSellPredict(headerIter.sell_predict());
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setArrivePeriod(headerIter.arrive_period());
			header.setDeliveryType(headerIter.delivery_type());
			header.setFormStatus(headerIter.form_status());
			header.setProviderId(headerIter.provider_Id());
			header.setProvider(headerIter.provider());
			header.setTemplateId(headerIter.template_Id());
			header.setTemplateName(headerIter.template_Name());
			
			header.setIsAddForm(headerIter.isAddForm());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RequestHeaderBean_SJProfileKeys 
{
  private static RequestHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.RequestHeaderBean_SJProfile0");
  }
}
