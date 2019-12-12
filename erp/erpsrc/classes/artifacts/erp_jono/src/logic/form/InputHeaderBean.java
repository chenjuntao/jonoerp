/*@lineinfo:filename=InputHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*///===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
//===============================================

package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import pojo.form.InputHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormPrefix;
import com.tanry.business.form.FormType;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;

public class InputHeaderBean extends ConnectionPool {

    /*@lineinfo:generated-code*//*@lineinfo:34^5*/

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
    input_department_idNdx = findColumn("input_department_id");
    input_departmentNdx = findColumn("input_department");
    inputer_idNdx = findColumn("inputer_id");
    inputerNdx = findColumn("inputer");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    input_timeNdx = findColumn("input_time");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    form_noteNdx = findColumn("form_note");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    audit_time_actualNdx = findColumn("audit_time_actual");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    statusNdx = findColumn("status");
    timesNdx = findColumn("times");
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
  public String input_department_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_department_idNdx);
  }
  private int input_department_idNdx;
  public String input_department() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_departmentNdx);
  }
  private int input_departmentNdx;
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
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public Date input_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timeNdx);
  }
  private int input_timeNdx;
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
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
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
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
  public Integer times() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(timesNdx);
  }
  private int timesNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^121*/

    /**
	 * 保存
	 */
	public int saveEntity(InputHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formRefId = header.getFormRefId();
		String formType = header.getFormType();
		String inputDepartmentId = header.getInputDepartmentId();
		String inputDepartment = header.getInputDepartment();
		String inputerId = header.getInputerId();
		String inputer = header.getInputer();
		String storageId = header.getStorageId();
		String storage = header.getStorage();
		Date inputTime = SqlDateUtil.getSqlDate(header.getInputTime());
		String providerId = header.getProviderId();
		String provider = header.getProvider();
		String formNote = header.getFormNote();
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		Date auditTimeActual = SqlDateUtil.getSqlDate(header.getAuditTimeActual());
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();
		
		String maxId = null;
		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    MAX(h.FORM_ID) 
//  			FROM
//  			:Com_("D_T1_INPUT_HEADER") h
//  			WHERE
//  			    h.INPUT_TIME = :inputTime
//  			AND h.INPUT_DEPARTMENT_ID = :inputDepartmentId
//  			AND h.FORM_TYPE = :formType
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  Date __sJT_2 = inputTime;
  String __sJT_3 = inputDepartmentId;
  String __sJT_4 = formType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:76^3*/
		
		int newSerial = 1;
		if (maxId != null) {
			maxId = maxId.substring(maxId.length() - FormConstant.DEFAULT_SERIAL_LENGTH);
			newSerial = Integer.parseInt(maxId) + 1;
		}
		String prefix = FormPrefix.STOCK_IN;
		if (FormType.INPUT_MANUAL.equals(formType)) {
			prefix = FormPrefix.STOCK_IN_MANUAL;
		} else if (FormType.INPUT_PRODUCE.equals(formType)||FormType.INPUT_SEMIS.equals(formType)) {
			prefix = FormPrefix.STOCK_IN_PRODUCT;
		}else if(FormType.INPUT_JSRK.equals(formType)){
			prefix = FormPrefix.STOCK_IN_JSRK;
		}
		String formId = FormUtil.genFormIdBody(prefix, inputDepartmentId, inputTime)
				+ FormUtil.formatSerial(newSerial);
		header.setFormId(formId);

		/*@lineinfo:generated-code*//*@lineinfo:95^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_INPUT_HEADER")
//  				(FORM_ID, FORM_REF_ID, FORM_TYPE, INPUT_DEPARTMENT_ID, INPUT_DEPARTMENT, INPUTER_ID, INPUTER,
//  						STORAGE_ID, STORAGE, INPUT_TIME,
//  					PROVIDER_ID, PROVIDER, FORM_NOTE, AUDITOR_ID, AUDITOR, AUDIT_TIME,AUDIT_TIME_ACTUAL, ALL_PAY_AMT, MAX_PAY_ITEM) 
//  			VALUES (:formId, 
//  					:formRefId, :formType,
//  					:inputDepartmentId,
//  					:inputDepartment,  
//  					:inputerId, 
//  					:inputer, :storageId, :storage,
//  					:inputTime, 
//  					:providerId, 
//  					:provider, 
//  					:formNote,  
//  					:auditorId, 
//  					:auditor, 
//  					:auditTime,
//  					:auditTimeActual,
//  					:allPayAmt, 
//  					:maxPayItem)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formRefId;
  String __sJT_4 = formType;
  String __sJT_5 = inputDepartmentId;
  String __sJT_6 = inputDepartment;
  String __sJT_7 = inputerId;
  String __sJT_8 = inputer;
  String __sJT_9 = storageId;
  String __sJT_10 = storage;
  Date __sJT_11 = inputTime;
  String __sJT_12 = providerId;
  String __sJT_13 = provider;
  String __sJT_14 = formNote;
  String __sJT_15 = auditorId;
  String __sJT_16 = auditor;
  Date __sJT_17 = auditTime;
  Date __sJT_18 = auditTimeActual;
  Double __sJT_19 = allPayAmt;
  String __sJT_20 = maxPayItem;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setDoubleWrapper(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:116^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(InputHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String inputDepartment = header.getInputDepartment();
		String inputer = header.getInputer();
		Date inputTime = SqlDateUtil.getSqlDate(header.getInputTime());
		String formNote = header.getFormNote();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:136^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_INPUT_HEADER")
//  			SET
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
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  Double __sJT_2 = allPayAmt;
  String __sJT_3 = maxPayItem;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:144^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:154^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_INPUT_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:160^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/**
	 * 设置退货状态(未退货/已退货)
	 */
	public int saveReturnStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:172^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_INPUT_HEADER")
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
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:179^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 餐厅要货单审核
	 */
	public int audit(String formId, String userId, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:194^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_INPUT_HEADER")
//  			SET
//  				auditor_id = :userId,
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
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = userId;
  Date __sJT_3 = sAuditTime;
  Date __sJT_4 = auditTimeActual;
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:203^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return count(startDate, endDate, branchId, "",  queryType, branchType);
	}
	
	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String storageId, String queryType,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, storageId, queryType, branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		int total = 0;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:222^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_INPUT_HEADER") h
//  		INNER JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		LEFT JOIN
//  		:Com_("D_T2_BRANCH_STORAGE") bs
//  		ON
//  		    bs.STORAGE_ID = h.STORAGE_ID
//  		INNER JOIN
//  		:Com_("d_t2_branch") b
//  		ON
//  		    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  		WHERE
//  		    :query
//  		AND (
//  		        h.INPUT_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.INPUT_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_4 = Com_("d_t2_branch");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:249^26*/
		closeDefaultContext(myCtx);
		
		return total;
	}
	
	public Integer count( String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Integer total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:259^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*)
//  			
//  			FROM
//  			:Com_("D_T1_INPUT_HEADER") h
//  			WHERE
//  			h.FORM_REF_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 7);
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
    total = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:268^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public Integer countNew( String condition) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Integer total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:277^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_INPUT_HEADER") h
//  		INNER JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		:Com_("d_t2_branch") b
//  		ON
//  		    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  		WHERE
//  		   1= 1  :condition };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("d_t2_branch");
  String __sJT_4 = condition;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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
    total = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:294^30*/ 
		    
	    total = total == null ? 0 :total;
	    
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String queryType, String branchType) {
		return formQuery(branchId, "", queryType, branchType);
	}
	
	private String formQuery(String branchId, String storageId, String queryType, String branchType) {
		String query = " 1=1";
		
		if (!"-1".equals(branchId)) {
			query = query + " AND h.INPUT_DEPARTMENT_ID = '" + branchId + "'";
		}
		if (!TextUtil.isEmpty(branchType)) {
			if("CENTRALFACTORY".equals(branchType)){
				query +=" AND (h.FORM_TYPE = 'PURCHASE' OR h.FORM_TYPE = 'MANUAL')";
			}
			query = query + " AND b.BRANCH_TYPE = '" + branchType + "'";
		}

		if (!TextUtil.isEmpty(storageId)) {
			query = query + " AND h.STORAGE_ID = '" + storageId + "'";
		}
		
		
		if ("audited".equals(queryType)) { // 获取已审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '已审核'";
		} else if (BillStatus.UNADUIT_US.equals(queryType)) {
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'";
		} else if ("unreturn".equals(queryType)) {
			query = query + " AND NVL( s.STATUS, 'null') = '已审核'";
			query = query + " AND NVL(h.RETURN_STATUS, 'null') != '已退货'";
		}
		return query;
	}
	
	/**
	 * 合计
	 */
	public double sum(java.util.Date startDate, java.util.Date endDate, String branchId,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return sum(startDate, endDate, branchId, "", branchType);
	}
	
	public double sum(java.util.Date startDate, java.util.Date endDate, String branchId,String storageId, String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery("-1", "", branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		double allPayAmt = 0;
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:352^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				SUM(h.ALL_PAY_AMT)
//  			
//  			FROM
//  			:Com_("D_T1_INPUT_HEADER") h
//  			LEFT JOIN
//  			:Com_("d_t2_branch") b
//  			ON
//  				h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  			WHERE
//  				:query
//  			AND b.BRANCH_TYPE = :branchType
//  			AND (
//  					h.INPUT_TIME >= :sDate
//  					OR  :sDate IS NULL)
//  			AND (
//  					h.INPUT_TIME <= :eDate
//  					OR  :eDate IS NULL)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("d_t2_branch");
  String __sJT_3 = query;
  String __sJT_4 = branchType;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 9);
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
    allPayAmt = __sJT_rtRs.getDoubleNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:372^3*/
		closeDefaultContext(myCtx);
		return allPayAmt;
	}
	
	public List<InputHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId,
			int startRow,int endRow, String queryType,String branchType) 
					throws NoPrivilegeException,SQLException,NoConnection {
		return query(startDate, endDate, branchId, "", startRow, endRow, queryType, branchType);
	}
	
	public List<InputHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String storageId,
			int startRow,int endRow, String queryType, String branchType) 
					throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, storageId, queryType, branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:392^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				*
//  			FROM
//  			(
//  				SELECT
//  					t.*,
//  					ROWNUM rowNumber
//  				FROM
//  				(SELECT
//  					    h.*,
//  					    s.status,
//  					    '' times
//  					    FROM
//  					    :Com_("D_T1_INPUT_HEADER") h
//  					    INNER JOIN
//  					    :Com_("D_T0_FORM_STATUS") s
//  					    ON
//  					        s.FORM_ID = h.FORM_ID
//  					    AND s.IS_CURRENT = 1
//  					    left JOIN
//  					    :Com_("D_T2_BRANCH_STORAGE") bs
//  					    ON
//  					        bs.STORAGE_ID = h.STORAGE_ID
//  					    INNER JOIN
//  					    :Com_("d_t2_branch") b
//  					    ON
//  					        h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  					    WHERE
//  					        :query
//  					    AND (
//  					            h.INPUT_TIME >= :sDate
//  					        OR  :sDate IS NULL)
//  					    AND (
//  					            h.INPUT_TIME <= :eDate
//  					        OR  :eDate IS NULL)
//  					    ORDER BY
//  					        h.FORM_ID DESC) t
//  				WHERE
//  					ROWNUM < :endRow)
//  			WHERE
//  				rowNumber >= :startRow					
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_4 = Com_("d_t2_branch");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 10);
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
      __sJT_stmt.setInt(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:434^3*/
		List<InputHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<InputHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String storageId,
			int startRow,int endRow, String queryType, String branchType ,String itemName) 
					throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, storageId, queryType, branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:449^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				*
//  			FROM
//  			(
//  				SELECT
//  					t.*,
//  					ROWNUM rowNumber
//  				FROM
//  				(	WITH
//  					    filter AS
//  					    (
//  					        SELECT DISTINCT
//  					            t.FORM_ID
//  					        FROM
//  					        :Com_("D_T1_INPUT_DETAIL") t
//  					        WHERE
//  					            1=1
//  					        AND (
//  					                t.ITEM_ID = :itemName
//  					            OR  t.ITEM_NAME = :itemName)
//  					        ORDER BY
//  					            t.FORM_ID
//  					    )
//  					SELECT
//  					    h.*,s.status,
//  					    '' times
//  					    FROM
//  					    :Com_("D_T1_INPUT_HEADER") h
//  					     INNER JOIN
//  					       filter f
//  					    ON
//  					        f.FORM_ID = h.FORM_ID
//  					    INNER JOIN
//  					    :Com_("D_T0_FORM_STATUS") s
//  					    ON
//  					        s.FORM_ID = h.FORM_ID
//  					    AND s.IS_CURRENT = 1
//  					    left JOIN
//  					    :Com_("D_T2_BRANCH_STORAGE") bs
//  					    ON
//  					        bs.STORAGE_ID = h.STORAGE_ID
//  					    INNER JOIN
//  					    :Com_("d_t2_branch") b
//  					    ON
//  					        h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  					    WHERE
//  					        :query
//  					    AND (
//  					            h.INPUT_TIME >= :sDate
//  					        OR  :sDate IS NULL)
//  					    AND (
//  					            h.INPUT_TIME <= :eDate
//  					        OR  :eDate IS NULL)
//  					    ORDER BY
//  					        h.FORM_ID DESC) t
//  				WHERE
//  					ROWNUM < :endRow)
//  			WHERE
//  				rowNumber >= :startRow					
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = Com_("D_T1_INPUT_HEADER");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_7 = Com_("d_t2_branch");
  String __sJT_8 = query;
  Date __sJT_9 = sDate;
  Date __sJT_10 = sDate;
  Date __sJT_11 = eDate;
  Date __sJT_12 = eDate;
  int __sJT_13 = endRow;
  int __sJT_14 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 11);
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
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setInt(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
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

/*@lineinfo:user-code*//*@lineinfo:509^3*/
		List<InputHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<InputHeader> queryNew(int startRow,int endRow,String condition) 
					throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:520^3*/

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
//  			                    SELECT
//  			                        h.*,s.status,
//  								    '' times
//  			                    FROM
//  			                    :Com_("D_T1_INPUT_HEADER") h
//  			                    INNER JOIN
//  			                    :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        s.FORM_ID = h.FORM_ID
//  			                    AND s.IS_CURRENT = 1
//  			                    INNER JOIN
//  			                    :Com_("d_t2_branch") b
//  			                    ON
//  			                        h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  			                    WHERE
//  			                    	1= 1  :condition
//  			                    ORDER BY
//  			                        h.FORM_ID DESC) t
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
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("d_t2_branch");
  String __sJT_4 = condition;
  int __sJT_5 = endRow;
  int __sJT_6 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:551^34*/
		List<InputHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	public InputHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:562^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,s.status,t.times
//  			FROM
//  			:Com_("D_T1_INPUT_HEADER") h
//  		    INNER JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//              ON
//                  s.FORM_ID = h.FORM_ID
//              AND s.IS_CURRENT = 1
//      		LEFT JOIN
//  		    :Com_("D_T0_PRINT_TIMES") t
//              ON
//                  t.FORM_ID = h.FORM_ID	
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T0_PRINT_TIMES");
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputHeaderBean_SJProfileKeys.getKey(0), 13);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:578^3*/
		List<InputHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<InputHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<InputHeader> headerLst = new ArrayList<InputHeader>();
		while(headerIter.next()) {
			InputHeader header = new InputHeader();
			header.setFormId(headerIter.form_id());
			header.setFormRefId(headerIter.form_ref_id());
			header.setFormType(headerIter.form_type());
			header.setInputDepartmentId(headerIter.input_department_id());
			header.setInputDepartment(headerIter.input_department());
			header.setInputerId(headerIter.inputer_id());
			header.setInputer(headerIter.inputer());
            header.setInputTime(SqlDateUtil.getUtilDate(headerIter.input_time()));
			header.setProviderId(headerIter.provider_id());
			header.setProvider(headerIter.provider());
			header.setFormNote(headerIter.form_note());
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
            header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());

			header.setStorageId(headerIter.storage_id());
			header.setStorage(headerIter.storage());
			header.setStatus(headerIter.status());
			header.setTimes(headerIter.times());
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class InputHeaderBean_SJProfileKeys 
{
  private static InputHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new InputHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private InputHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.InputHeaderBean_SJProfile0");
  }
}
