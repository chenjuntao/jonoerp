/*@lineinfo:filename=ShippingHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 12, 2014 by liyzh
 * Last edited on Sep 12, 2014 by liyzh
 * 
 * 说明： 餐厅配送单、物流中心出货单、中央工厂出货单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ShippingHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormType;
import com.tanry.business.form.StatusConst;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchType;

public class ShippingHeaderBean  extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShippingHeaderBean.class);
	
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
    form_ref_idNdx = findColumn("form_ref_id");
    form_typeNdx = findColumn("form_type");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    receive_timeNdx = findColumn("receive_time");
    requester_idNdx = findColumn("requester_id");
    requesterNdx = findColumn("requester");
    request_addressNdx = findColumn("request_address");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    audit_time_actualNdx = findColumn("audit_time_actual");
    inputer_idNdx = findColumn("inputer_id");
    inputerNdx = findColumn("inputer");
    input_timeNdx = findColumn("input_time");
    input_time_actualNdx = findColumn("input_time_actual");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    out_storage_idNdx = findColumn("out_storage_id");
    out_storageNdx = findColumn("out_storage");
    in_storage_idNdx = findColumn("in_storage_id");
    in_storageNdx = findColumn("in_storage");
    form_statusNdx = findColumn("form_status");
    pick_statusNdx = findColumn("pick_status");
    input_statusNdx = findColumn("input_status");
    return_statusNdx = findColumn("return_status");
    anti_statusNdx = findColumn("anti_status");
    on_statusNdx = findColumn("on_status");
    isAddFormNdx = findColumn("isAddForm");
    printTimesNdx = findColumn("printTimes");
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
  public String form_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_typeNdx);
  }
  private int form_typeNdx;
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
  public Date form_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_time_actualNdx);
  }
  private int form_time_actualNdx;
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
  public Date audit_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_time_actualNdx);
  }
  private int audit_time_actualNdx;
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
  public Date input_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_time_actualNdx);
  }
  private int input_time_actualNdx;
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
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
  public String pick_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(pick_statusNdx);
  }
  private int pick_statusNdx;
  public String input_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_statusNdx);
  }
  private int input_statusNdx;
  public String return_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(return_statusNdx);
  }
  private int return_statusNdx;
  public String anti_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(anti_statusNdx);
  }
  private int anti_statusNdx;
  public String on_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(on_statusNdx);
  }
  private int on_statusNdx;
  public String isAddForm() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(isAddFormNdx);
  }
  private int isAddFormNdx;
  public String printTimes() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(printTimesNdx);
  }
  private int printTimesNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:73^3*/

	/**
	 * 保存
	 */
	public int saveEntity(ShippingHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = header.getFormId();
		String formRefId = header.getFormRefId();
		String formType = header.getFormType();
		String providerId = header.getProviderId();
		String provider = header.getProvider();
		String outStorageId = header.getOutStorageId();
		String outStorage = header.getOutStorage();
		Date receiveTime = SqlDateUtil.getSqlDate(header.getReceiveTime());
		String requesterId = header.getRequesterId();
		String requester = header.getRequester();
		String requestAddress = header.getRequestAddress();
		String inStorageId = header.getInStorageId();
		String inStorage = header.getInStorage();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String inputer = header.getInputer();
		Date inputTime = SqlDateUtil.getSqlDate(header.getInputTime());
		String formNote = header.getFormNote();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:106^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_SHIPPING_HEADER") 
//  				(FORM_ID, FORM_REF_ID, FORM_TYPE, PROVIDER_ID, PROVIDER, OUT_STORAGE_ID, OUT_STORAGE, RECEIVE_TIME, 
//  						REQUESTER_ID, REQUESTER, REQUEST_ADDRESS, IN_STORAGE_ID, IN_STORAGE, 
//  					FORM_MAKER_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL, AUDITOR, AUDIT_TIME, INPUTER, INPUT_TIME, 
//  					FORM_NOTE, ALL_PAY_AMT, MAX_PAY_ITEM,ON_STATUS) 
//  			VALUES (:formId, :formRefId, :formType,
//  					:providerId,
//  					:provider, :outStorageId, :outStorage,
//  					:receiveTime, 
//  					:requesterId,
//  					:requester, 
//  					:requestAddress, :inStorageId, :inStorage,
//  					:formMakerId,
//  					:formMaker, 
//  					:formTime, :formTimeActual,
//  					:auditor, 
//  					:auditTime, 
//  					:inputer, 
//  					:inputTime, 
//  					:formNote, 
//  					:allPayAmt, 
//  					:maxPayItem,'')
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formRefId;
  String __sJT_4 = formType;
  String __sJT_5 = providerId;
  String __sJT_6 = provider;
  String __sJT_7 = outStorageId;
  String __sJT_8 = outStorage;
  Date __sJT_9 = receiveTime;
  String __sJT_10 = requesterId;
  String __sJT_11 = requester;
  String __sJT_12 = requestAddress;
  String __sJT_13 = inStorageId;
  String __sJT_14 = inStorage;
  String __sJT_15 = formMakerId;
  String __sJT_16 = formMaker;
  Date __sJT_17 = formTime;
  Date __sJT_18 = formTimeActual;
  String __sJT_19 = auditor;
  Date __sJT_20 = auditTime;
  String __sJT_21 = inputer;
  Date __sJT_22 = inputTime;
  String __sJT_23 = formNote;
  Double __sJT_24 = allPayAmt;
  String __sJT_25 = maxPayItem;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setDate(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setDate(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setDoubleWrapper(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:129^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ShippingHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String providerId = header.getProviderId();
		Date receiveTime = SqlDateUtil.getSqlDate(header.getReceiveTime());
		String requester = header.getRequester();
		String requestAddress = header.getRequestAddress();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String inputerId = header.getInputerId();
		String inputer = header.getInputer();
		Date inputTime = SqlDateUtil.getSqlDate(header.getInputTime());
		Date inputTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String formNote = header.getFormNote();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:156^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  			    RECEIVE_TIME =:receiveTime,
//  				FORM_NOTE = :formNote,
//  			    ALL_PAY_AMT = :allPayAmt,
//  			    MAX_PAY_ITEM = :maxPayItem,
//  			    INPUTER_ID = :inputerId,
//  			    INPUTER = :inputer,
//  			    INPUT_TIME = :inputTime,
//  			    INPUT_TIME_ACTUAL = :inputTimeActual
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  Date __sJT_2 = receiveTime;
  String __sJT_3 = formNote;
  Double __sJT_4 = allPayAmt;
  String __sJT_5 = maxPayItem;
  String __sJT_6 = inputerId;
  String __sJT_7 = inputer;
  Date __sJT_8 = inputTime;
  Date __sJT_9 = inputTimeActual;
  String __sJT_10 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:170^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateInStorage(ShippingHeader header)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String formId = header.getFormId();
			String in_storage_id = header.getInStorageId();
			String in_storage = header.getInStorage();

			/*@lineinfo:generated-code*//*@lineinfo:184^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_SHIPPING_HEADER")
//  				SET
//  						IN_STORAGE_ID = :in_storage_id,
//  						IN_STORAGE = :in_storage
//  				WHERE 
//  					FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = in_storage_id;
  String __sJT_3 = in_storage;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:192^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int updateOutStorage(ShippingHeader header)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String out_storage_id = header.getOutStorageId();
		String out_storage = header.getOutStorage();
		
		/*@lineinfo:generated-code*//*@lineinfo:206^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				OUT_STORAGE_ID = :out_storage_id,
//  				OUT_STORAGE = :out_storage
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = out_storage_id;
  String __sJT_3 = out_storage;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:214^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:224^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:230^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public boolean exist(java.util.Date businessDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:242^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				h.FORM_TIME = :bDate
//  			AND h.REQUESTER_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  Date __sJT_3 = bDate;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:255^3*/
		closeDefaultContext(myCtx);
		if (total > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 设置捡货状态(未捡货/已捡货)
	 */
	public int savePickStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:269^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				PICK_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:276^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/**
	 * 设置入库状态(未入库/已入库)
	 */
	public int saveInputStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:287^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				INPUT_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:294^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/**
	 * 设置配送差异处理状态(未处理/已处理)
	 */
	public int saveDifferentStatus(String formId, String status)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:305^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				DIFFERENT_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:312^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/**
	 * 设置退货状态(退货中/已退货)
	 */
	public int saveReturnStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:323^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				RETURN_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 9);
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

/*@lineinfo:user-code*//*@lineinfo:330^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/**
	 * 设置反审核状态(反审核中/已反审核)
	 */
	public int saveAntiStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:341^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				ANTI_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 10);
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

/*@lineinfo:user-code*//*@lineinfo:348^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/**
	 * 设置在途状态(空/在途)
	 */
	public int saveOnStatus(ShippingHeader header, String status)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = header.getFormId();
		String auditor = header.getAuditor();
		String auditorId=header.getAuditorId();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		/*@lineinfo:generated-code*//*@lineinfo:364^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				ON_STATUS = :status,
//  				AUDITOR_ID = :auditorId,
//  				AUDITOR = :auditor, 
//  				AUDIT_TIME = :auditTime,
//  				AUDIT_TIME_ACTUAL = :auditTimeActual
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = auditorId;
  String __sJT_4 = auditor;
  Date __sJT_5 = auditTime;
  Date __sJT_6 = auditTimeActual;
  String __sJT_7 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:375^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int saveOnStatus(String formId, String status)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:384^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				ON_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 12);
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

/*@lineinfo:user-code*//*@lineinfo:391^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/**
	 * 餐厅配送单审核
	 */
	public int audit(String formId, String userId, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:405^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_HEADER")
//  			SET
//  				auditor = :userId,
//  				audit_time = :sAuditTime,
//  				AUDIT_TIME_ACTUAL =:auditTimeActual
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = userId;
  Date __sJT_3 = sAuditTime;
  Date __sJT_4 = auditTimeActual;
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 13);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:414^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, String formType,String branchType,String storageId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:428^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//  			AND (h.INPUT_TIME >= :sDate or :sDate is null)
//  			AND (h.INPUT_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 14);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:442^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public int countByCross(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, String formType,String branchType,String storageId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:455^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_SHIPPING_HEADER") h
//  		LEFT JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		WHERE
//  		    :query
//  		AND (
//  		        h.RECEIVE_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.RECEIVE_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 15);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:474^26*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String queryType, String formType, String branchType, String storageId) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
//			if (BranchType.LOGISTICSCENTER.equals(branchType)) {
//				query = query + " AND h.PROVIDER_ID = '" + branchId + "'";
//			} else {
//				query = query + " AND h.REQUESTER_ID = '" + branchId + "'";
//			}
			
			query = query + " AND h.REQUESTER_ID = '" + branchId + "'";//总部登陆的物流中心账号（会影响物流中心反审核）
		}

		if ("uninput".equals(queryType)) { // 获取未入库记录出货单
			query = query + " AND NVL(h.INPUT_STATUS, 'null') != '" + BillStatus.STORED_CN + "'";
		}
		
		if ("unaudit".equals(queryType)) { // 获取未审核记录越库单
			query = query + " AND NVL( s.STATUS, 'null') = '" + BillStatus.UNADUIT_CN + "'";
		}
		
		if ("antiaudit".equals(queryType)) { // 获取适合反审核的记录
			//已经做了差异处理和配退的配送单不能再进行反审核
			query = query + " AND NVL(h.ANTI_STATUS, 'null') not in ('反审核中','已反审核') ";
			query = query + " AND NVL(h.DIFFERENT_STATUS, 'null') not in ('已处理差异') ";
			query = query + " AND NVL(h.RETURN_STATUS, 'null') not in ('退货中','已退货') ";
			query = query + " AND h.INPUT_STATUS = '" + BillStatus.STORED_CN + "'";
			
		} else if ("return".equals(queryType)) { // 获取适合退货的记录
			//配退的前提是这种配送单没有差异，如果有差异则这张单先经过差异处理
			//暂时实现不了
			query = query + " AND NVL(h.ANTI_STATUS, 'null') != '反审核中'";
			query = query + " AND NVL(h.RETURN_STATUS, 'null') != '退货中'";
			query = query + " AND h.INPUT_STATUS = '" + BillStatus.STORED_CN + "'";
		}
		
		if ("request".equals(formType)) {// 餐厅同时查询越库配送单和统配配送单
			query = query + " AND h.FORM_TYPE in ('" + FormType.DISTRI_CROSS + "', '" + FormType.DISTRI_UNIFIED + "')";
		} else {
			query = query + " AND h.FORM_TYPE = '" + formType + "'";
		}

		if (BranchType.RESTAURANT.equals(branchType)) {
			if (storageId != null && !storageId.equals("")) {
				query = query + " AND h.IN_STORAGE_ID = '" + storageId + "'";
			}
		} else if(BranchType.LOGISTICSCENTER.equals(branchType)) {
			if (storageId != null && !storageId.equals("")) {
				query = query + " AND h.OUT_STORAGE_ID = '" + storageId + "'";
			}
		}
		return query;
	}
	
	/*未分页*/
	public List<ShippingHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, String formType,String branchType,String storageId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:541^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//      			h.*, s.STATUS AS FORM_STATUS, '' isAddForm, '' printTimes
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//  			AND (h.INPUT_TIME >= :sDate or :sDate is null)
//  			AND (h.INPUT_TIME <= :eDate or :eDate is null)
//  			ORDER BY
//  				h.FORM_ID DESC
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 16);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:557^3*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/*未分页*/
	public List<ShippingHeader> queryByCross(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, String formType,String branchType,String storageId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:573^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//      			h.*, s.STATUS AS FORM_STATUS, '' isAddForm, '' printTimes
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//  			AND (h.RECEIVE_TIME >= :sDate or :sDate is null)
//  			AND (h.RECEIVE_TIME <= :eDate or :eDate is null)
//  			ORDER BY
//  				h.FORM_ID DESC
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 17);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:589^3*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/*分页*/
	public List<ShippingHeader> queryByCross(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, String formType,String branchType,String storageId, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
			String query = formQuery(branchId, queryType, formType, branchType, storageId);
			Date sDate = SqlDateUtil.getSqlDate(startDate);
			Date eDate = SqlDateUtil.getSqlDate(endDate);
			
			DefaultContext myCtx = getDefaultContext();
			HeaderIter headerIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:605^4*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  					    *
//  				    FROM
//  				        (
//  				            SELECT
//  				                t.*,
//  				                ROWNUM rowNumber
//  				            FROM
//  				                (
//  				                		SELECT
//  				            			h.*, s.STATUS AS FORM_STATUS , '' isAddForm,'' printTimes
//  				        			FROM
//  				        			:Com_("D_T1_SHIPPING_HEADER") h
//  				        			LEFT JOIN
//  				        			:Com_("D_T0_FORM_STATUS") s
//  				        			ON
//  				        			    s.FORM_ID = h.FORM_ID
//  				        			AND s.IS_CURRENT = 1
//  				        			WHERE
//  				        				:query
//  				        			AND (h.RECEIVE_TIME >= :sDate or :sDate is null)
//  				        			AND (h.RECEIVE_TIME <= :eDate or :eDate is null)
//  				        			ORDER BY
//  				        				h.FORM_ID DESC ) t
//  				            WHERE
//  				                ROWNUM < :endRow)
//  				    WHERE
//  				        rowNumber >= :startRow
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 18);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:634^5*/
			List<ShippingHeader> headerLst = processIter(headerIter);
			headerIter.close();
			closeDefaultContext(myCtx);
			return headerLst;
		}
	/*分页（添加明细查询条件）*/
	public List<ShippingHeader> queryByCross(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, String formType,String branchType,String storageId,String itemName, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:649^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (WITH
//  			        			    filter AS
//  			        			    (
//  			        			        SELECT DISTINCT
//  			        			            sd.FORM_ID
//  			        			        FROM
//  			        			        :Com_("D_T1_SHIPPING_DETAIL") sd
//  			        			        WHERE
//  			        			            1=1
//  			        			        AND (
//  			        			                sd.ITEM_ID = :itemName
//  			        			            OR  sd.ITEM_NAME = :itemName)
//  			        			        ORDER BY
//  			        			            sd.FORM_ID
//  			        			    )
//  			                		SELECT
//  			            			h.*, s.STATUS AS FORM_STATUS, '' isAddForm,'' printTimes
//  			        			FROM
//  			        			:Com_("D_T1_SHIPPING_HEADER") h
//  		        			    INNER JOIN
//  		        			    filter
//  		        			    ON
//  		        			    filter.FORM_ID = h.FORM_ID
//  			        			LEFT JOIN
//  			        			:Com_("D_T0_FORM_STATUS") s
//  			        			ON
//  			        			    s.FORM_ID = h.FORM_ID
//  			        			AND s.IS_CURRENT = 1
//  			        			WHERE
//  			        				:query
//  			        			AND (h.RECEIVE_TIME >= :sDate or :sDate is null)
//  			        			AND (h.RECEIVE_TIME <= :eDate or :eDate is null)
//  			        			ORDER BY
//  			        				h.FORM_ID DESC ) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = query;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  int __sJT_11 = endRow;
  int __sJT_12 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 19);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
      __sJT_stmt.setInt(12, __sJT_12);
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

/*@lineinfo:user-code*//*@lineinfo:696^4*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/*分页（添加明细查询条件）*/
	public List<ShippingHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, String formType,String branchType,String storageId,String itemName, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:712^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (WITH
//  			        			    filter AS
//  			        			    (
//  			        			        SELECT DISTINCT
//  			        			            sd.FORM_ID
//  			        			        FROM
//  			        			        :Com_("D_T1_SHIPPING_DETAIL") sd
//  			        			        WHERE
//  			        			            1=1
//  			        			        AND (
//  			        			                sd.ITEM_ID = :itemName
//  			        			            OR  sd.ITEM_NAME = :itemName)
//  			        			        ORDER BY
//  			        			            sd.FORM_ID
//  			        			    )
//  			                		SELECT
//  			            			h.*, s.STATUS AS FORM_STATUS,'' isAddForm, '' printTimes
//  			        			FROM
//  			        			:Com_("D_T1_SHIPPING_HEADER") h
//  		        			    INNER JOIN
//  		        			    filter
//  		        			    ON
//  		        			    filter.FORM_ID = h.FORM_ID
//  			        			LEFT JOIN
//  			        			:Com_("D_T0_FORM_STATUS") s
//  			        			ON
//  			        			    s.FORM_ID = h.FORM_ID
//  			        			AND s.IS_CURRENT = 1
//  			        			WHERE
//  			        				:query
//  			        			AND (h.INPUT_TIME >= :sDate or :sDate is null)
//  			        			AND (h.INPUT_TIME <= :eDate or :eDate is null)
//  			        			ORDER BY
//  			        				h.FORM_ID DESC ) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = query;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  int __sJT_11 = endRow;
  int __sJT_12 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 20);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
      __sJT_stmt.setInt(12, __sJT_12);
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

/*@lineinfo:user-code*//*@lineinfo:759^4*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

/*分页*/
public List<ShippingHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, String formType,String branchType,String storageId, int startRow, int endRow) 
		throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:775^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                		SELECT
//  			            			h.*, s.STATUS AS FORM_STATUS, '' isAddForm,'' printTimes
//  			        			FROM
//  			        			:Com_("D_T1_SHIPPING_HEADER") h
//  			        			LEFT JOIN
//  			        			:Com_("D_T0_FORM_STATUS") s
//  			        			ON
//  			        			    s.FORM_ID = h.FORM_ID
//  			        			AND s.IS_CURRENT = 1
//  			        			WHERE
//  			        				:query
//  			        			AND (h.INPUT_TIME >= :sDate or :sDate is null)
//  			        			AND (h.INPUT_TIME <= :eDate or :eDate is null)
//  			        			ORDER BY
//  			        				h.FORM_ID DESC ) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 21);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:804^4*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	/**
	 * 根据配送日期查询配送单，用于生成捡货单
	 */
	public List<ShippingHeader> queryByReceiveTime(java.util.Date receiveTime,String status)
			throws NoPrivilegeException,SQLException,NoConnection {
		final int  NORMAL = 1;
		final int  ADD = 2;
		
		Date rTime = SqlDateUtil.getSqlDate(receiveTime);
		String query = " 1=1";
		// 越库配送单不参与捡货
		query = query + " AND h.FORM_TYPE not in ('" + FormType.DISTRI_CROSS + "', '" + FormType.DISTRI_PRODUCT + "', '" +  FormType.REQUEST_OUTER + "') ";
		String unaudit = BillStatus.UNADUIT_CN;
		
		switch (Integer.valueOf(status)) {
		case NORMAL:
			query += " AND ((rh.TEMPLATE_ID is not null) or (h.FORM_REF_ID is null))　";
			break;
		case ADD:
			query += " AND rh.TEMPLATE_ID is  null  AND h.FORM_REF_ID is not null ";
			break;
		}
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:835^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    '' printTimes,
//  			    DECODE(h.FORM_TYPE,'INNER_UNIFIED',DECODE(h.FORM_REF_ID,NULL,'正常要货',DECODE(rh.ISADDFORM,'Y','加单','正常要货')),'正常要货')  isAddForm ,
//  			    s.STATUS                            AS FORM_STATUS
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  		    LEFT JOIN
//  		    :Com_("D_T1_REQUEST_HEADER") rh
//  			ON
//  			    h.FORM_REF_ID = rh.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    h.RECEIVE_TIME = :rTime
//  			AND NVL( s.STATUS, 'null') = :unaudit
//  			AND NVL(h.PICK_STATUS, 'null') != '已捡货'
//  			AND :query
//  			ORDER BY
//  			    h.FORM_ID DESC };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  Date __sJT_4 = rTime;
  String __sJT_5 = unaudit;
  String __sJT_6 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 22);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:858^22*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/**
	 * 根据配送日期查询配送单，用于生成捡货单
	 */
	public List<ShippingHeader> queryByReceiveTime(java.util.Date receiveTime,String status,String itemName)
			throws NoPrivilegeException,SQLException,NoConnection {
		final int  NORMAL = 1;
		final int  ADD = 2;
		
		Date rTime = SqlDateUtil.getSqlDate(receiveTime);
		String query = " 1=1";
		// 越库配送单不参与捡货
		query = query + " AND h.FORM_TYPE not in ('" + FormType.DISTRI_CROSS + "', '" + FormType.DISTRI_PRODUCT + "', '" +  FormType.REQUEST_OUTER + "') ";
		String unaudit = BillStatus.UNADUIT_CN;
		
		switch (Integer.valueOf(status)) {
		case NORMAL:
			query += " AND ((rh.TEMPLATE_ID is not null) or (h.FORM_REF_ID is null))　";
			break;
		case ADD:
			query += " AND rh.TEMPLATE_ID is  null  AND h.FORM_REF_ID is not null ";
			break;
		}
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:890^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  				 a AS (
//  				        SELECT DISTINCT
//  				            t.FORM_ID
//  				        FROM
//  				        :Com_("D_T1_SHIPPING_DETAIL") t
//  				        WHERE
//  				            1=1
//  				        AND (
//  				                t.ITEM_ID = :itemName
//  				            OR  t.ITEM_NAME = :itemName)
//  				        ORDER BY
//  				            t.FORM_ID
//  				    )
//  				
//  			SELECT
//  			    h.*,
//  			    '' printTimes,
//  			    DECODE(h.FORM_TYPE,'INNER_UNIFIED',DECODE(h.FORM_REF_ID,NULL,'正常要货',DECODE(rh.ISADDFORM,'Y','加单','正常要货')),'正常要货')  isAddForm ,
//  			    s.STATUS                            AS FORM_STATUS
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			 INNER JOIN
//  	        	a
//  	        ON
//  	             a.FORM_ID = h.FORM_ID
//  		    LEFT JOIN
//  		    :Com_("D_T1_REQUEST_HEADER") rh
//  			ON
//  			    h.FORM_REF_ID = rh.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    h.RECEIVE_TIME = :rTime
//  			AND NVL( s.STATUS, 'null') = :unaudit
//  			AND NVL(h.PICK_STATUS, 'null') != '已捡货'
//  			AND :query
//  			ORDER BY
//  			    h.FORM_ID DESC };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_6 = Com_("D_T0_FORM_STATUS");
  Date __sJT_7 = rTime;
  String __sJT_8 = unaudit;
  String __sJT_9 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 23);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:932^22*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ShippingHeader> queryAll(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,String formType,String branchType,String storageId, int startRow,int endRow,String sortQuery) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:948^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                	SELECT
//  	                        		h.*, s.STATUS AS FORM_STATUS,nvl(pt.TIMES,'') printTimes,
//  	                        		DECODE(h.FORM_TYPE,'INNER_UNIFIED',DECODE(h.FORM_REF_ID,NULL,'',DECODE(rh.ISADDFORM,'Y','Y','')),'')    isAddForm     
//  		                	    FROM
//  		                	    :Com_("D_T1_SHIPPING_HEADER") h
//  	                	        LEFT JOIN
//  	                	        :Com_("D_T1_REQUEST_HEADER") rh
//  		            			ON
//  		            			    h.FORM_REF_ID = rh.FORM_ID
//  		                	    LEFT JOIN
//  		                	    :Com_("D_T0_FORM_STATUS") s
//  		                	    ON
//  		                	        s.FORM_ID = h.FORM_ID
//  		                	    AND s.IS_CURRENT = 1
//  		                	    LEFT JOIN
//  		                	    :Com_("D_T0_PRINT_TIMES") pt
//  		                	    ON h.FORM_ID = pt.FORM_ID
//  		                	    WHERE
//  		                	        :query
//  		                	    AND (
//  		                	            h.FORM_TIME >= :sDate
//  		                	        OR  :sDate IS NULL)
//  		                	    AND (
//  		                	            h.FORM_TIME <= :eDate
//  		                	        OR  :eDate IS NULL)
//  	                	        :sortQuery ) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T0_PRINT_TIMES");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  String __sJT_10 = sortQuery;
  int __sJT_11 = endRow;
  int __sJT_12 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 24);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
      __sJT_stmt.setInt(12, __sJT_12);
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

/*@lineinfo:user-code*//*@lineinfo:987^34*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ShippingHeader> queryAll(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,String formType,String branchType,String storageId, int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return queryAll(startDate, endDate, branchId, queryType, formType, branchType, storageId, startRow, endRow,"");
	}
	
	public int countAll(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,  String formType,String branchType,String storageId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		int counts = 0;
		String query = formQuery(branchId, queryType, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:1007^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//          			COUNT(*)
//      			
//          	    FROM
//          	    :Com_("D_T1_SHIPPING_HEADER") h
//          	    LEFT JOIN
//          	    :Com_("D_T0_FORM_STATUS") s
//          	    ON
//          	        s.FORM_ID = h.FORM_ID
//          	    AND s.IS_CURRENT = 1
//          	    WHERE
//          	        :query
//          	    AND (
//          	            h.FORM_TIME >= :sDate
//          	        OR  :sDate IS NULL)
//          	    AND (
//          	            h.FORM_TIME <= :eDate
//          	        OR  :eDate IS NULL)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 25);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:1027^3*/
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public double sumAll(java.util.Date startDate, java.util.Date endDate, String branchId, String formType, String branchType, String storageId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Double allPayAmt = null;
		String query = formQuery(branchId, null, formType, branchType, storageId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:1040^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				SUM(ALL_PAY_AMT)
//  			
//      	    FROM
//      	    :Com_("D_T1_SHIPPING_HEADER") h
//      	    LEFT JOIN
//      	    :Com_("D_T0_FORM_STATUS") s
//      	    ON
//      	        s.FORM_ID = h.FORM_ID
//      	    AND s.IS_CURRENT = 1
//      	    WHERE
//      	        :query
//      	    AND (
//      	            h.FORM_TIME >= :sDate
//      	        OR  :sDate IS NULL)
//      	    AND (
//      	            h.FORM_TIME <= :eDate
//      	        OR  :eDate IS NULL)
//      	    ORDER BY
//      	        H.FORM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 26);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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
    allPayAmt = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:1062^3*/
		
		closeDefaultContext(myCtx);
		if (allPayAmt == null) {
			return 0;
		}
		return allPayAmt;
	}

	public ShippingHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:1075^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    s.STATUS FORM_STATUS,
//  			    ''       isAddForm,
//  			    ''       printTimes
//  			FROM
//  			    :Com_("D_T1_SHIPPING_HEADER") h
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    h.form_id = :formId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingHeaderBean_SJProfileKeys.getKey(0), 27);
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

/*@lineinfo:user-code*//*@lineinfo:1089^27*/
		List<ShippingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<ShippingHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ShippingHeader> headerLst = new ArrayList<ShippingHeader>();
		while(headerIter.next()) {
			ShippingHeader header = new ShippingHeader();
			header.setFormId(headerIter.form_id());
			header.setFormRefId(headerIter.form_ref_id());
			header.setFormType(headerIter.form_type());
			header.setProviderId(headerIter.provider_id());
			header.setProvider(headerIter.provider());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setRequesterId(headerIter.requester_id());
			header.setRequester(headerIter.requester());
			header.setRequestAddress(headerIter.request_address());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
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
			header.setFormStatus(headerIter.form_status());
			header.setPickStatus(headerIter.pick_status());
			header.setInputStatus(headerIter.input_status());
			header.setReturnStatus(headerIter.return_status());
			header.setAntiStatus(headerIter.anti_status());
			header.setOnStatus(headerIter.on_status());
			header.setIsAddForm(headerIter.isAddForm());
			header.setPrintTimes(headerIter.printTimes());
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			header.setInputTimeActual(SqlDateUtil.getUtilDate(headerIter.input_time_actual()));
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ShippingHeaderBean_SJProfileKeys 
{
  private static ShippingHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ShippingHeaderBean_SJProfile0");
  }
}
