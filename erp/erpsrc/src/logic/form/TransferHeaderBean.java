/*@lineinfo:filename=TransferHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Sep 18 15:26:39 CST 2014 by pw
 * Last edited on Thu Sep 18 15:26:39 CST 2014 by pw
 * 
 * comment: 餐厅调拨单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.TransferHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class TransferHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(TransferHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

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
    in_branch_idNdx = findColumn("in_branch_id");
    in_branchNdx = findColumn("in_branch");
    in_storage_idNdx = findColumn("in_storage_id");
    in_storageNdx = findColumn("in_storage");
    out_branch_idNdx = findColumn("out_branch_id");
    out_branchNdx = findColumn("out_branch");
    out_storage_idNdx = findColumn("out_storage_id");
    out_storageNdx = findColumn("out_storage");
    from_maker_idNdx = findColumn("from_maker_id");
    from_makerNdx = findColumn("from_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    confirmer_idNdx = findColumn("confirmer_id");
    confirmerNdx = findColumn("confirmer");
    confirm_timeNdx = findColumn("confirm_time");
    out_man_idNdx = findColumn("out_man_id");
    out_manNdx = findColumn("out_man");
    out_timeNdx = findColumn("out_time");
    audit_time_actualNdx = findColumn("audit_time_actual");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String in_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_branch_idNdx);
  }
  private int in_branch_idNdx;
  public String in_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_branchNdx);
  }
  private int in_branchNdx;
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
  public String out_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_branch_idNdx);
  }
  private int out_branch_idNdx;
  public String out_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_branchNdx);
  }
  private int out_branchNdx;
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
  public String from_maker_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(from_maker_idNdx);
  }
  private int from_maker_idNdx;
  public String from_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(from_makerNdx);
  }
  private int from_makerNdx;
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
  public String confirmer_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(confirmer_idNdx);
  }
  private int confirmer_idNdx;
  public String confirmer() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(confirmerNdx);
  }
  private int confirmerNdx;
  public Date confirm_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(confirm_timeNdx);
  }
  private int confirm_timeNdx;
  public String out_man_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_man_idNdx);
  }
  private int out_man_idNdx;
  public String out_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_manNdx);
  }
  private int out_manNdx;
  public Date out_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(out_timeNdx);
  }
  private int out_timeNdx;
  public Date audit_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_time_actualNdx);
  }
  private int audit_time_actualNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^534*/
	
	public int saveEntity(TransferHeader header)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();
			
			String formId = header.getFormId();
			String inBranchId = header.getInBranchId();
			String inBranch = header.getInBranch();
			String inStorageId = header.getInStorageId();
			String inStorage = header.getInStorage();
			String outBranchId = header.getOutBranchId();
			String outBranch = header.getOutBranch();
			String outStorageId = header.getOutStorageId();
			String outStorage = header.getOutStorage();
			String fromMakerId = header.getFromMakerId();
			String fromMaker = header.getFromMaker();
			Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
			Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
			String auditorId = header.getAuditorId();
			String auditor = header.getAuditor();
			Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
			String confirmerId = header.getConfirmerId();
			String confirmer = header.getConfirmer();
			Date confirmTime = SqlDateUtil.getSqlDate(header.getConfirmTime());
			String outManId = header.getOutManId();
			String outMan = header.getOutMan();
			Date outTime = SqlDateUtil.getSqlDate(header.getOutTime());
			String formNote = header.getFormNote();
			Double allPayAmt = header.getAllPayAmt();
			String maxPayItem = header.getMaxPayItem();

			/*@lineinfo:generated-code*//*@lineinfo:67^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_TRANSFER_HEADER") 
//  					(FORM_ID, IN_BRANCH_ID, IN_BRANCH, IN_STORAGE_ID, IN_STORAGE, OUT_BRANCH_ID, OUT_BRANCH, OUT_STORAGE_ID, OUT_STORAGE, FROM_MAKER_ID, FROM_MAKER, FORM_TIME, FORM_TIME_ACTUAL,
//  							AUDITOR_ID, AUDITOR, AUDIT_TIME, CONFIRMER_ID, CONFIRMER, CONFIRM_TIME, OUT_MAN_ID, OUT_MAN, OUT_TIME, FORM_NOTE, ALL_PAY_AMT, MAX_PAY_ITEM) 
//  				VALUES (:formId, :inBranchId, :inBranch, :inStorageId, :inStorage, :outBranchId, :outBranch, :outStorageId, :outStorage, :fromMakerId, :fromMaker, :formTime, :formTimeActual,
//  						:auditorId, :auditor, :auditTime, :confirmerId, :confirmer, :confirmTime, :outManId, :outMan, :outTime, :formNote, :allPayAmt, :maxPayItem)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = inBranchId;
  String __sJT_4 = inBranch;
  String __sJT_5 = inStorageId;
  String __sJT_6 = inStorage;
  String __sJT_7 = outBranchId;
  String __sJT_8 = outBranch;
  String __sJT_9 = outStorageId;
  String __sJT_10 = outStorage;
  String __sJT_11 = fromMakerId;
  String __sJT_12 = fromMaker;
  Date __sJT_13 = formTime;
  Date __sJT_14 = formTimeActual;
  String __sJT_15 = auditorId;
  String __sJT_16 = auditor;
  Date __sJT_17 = auditTime;
  String __sJT_18 = confirmerId;
  String __sJT_19 = confirmer;
  Date __sJT_20 = confirmTime;
  String __sJT_21 = outManId;
  String __sJT_22 = outMan;
  Date __sJT_23 = outTime;
  String __sJT_24 = formNote;
  Double __sJT_25 = allPayAmt;
  String __sJT_26 = maxPayItem;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setDate(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setDate(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setDate(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setDoubleWrapper(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:73^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int updateEntity(TransferHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:87^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_TRANSFER_HEADER")
//  			SET
//  				ALL_PAY_AMT = :allPayAmt,
//  				MAX_PAY_ITEM = :maxPayItem
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  Double __sJT_2 = allPayAmt;
  String __sJT_3 = maxPayItem;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:95^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:105^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_TRANSFER_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:111^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	
	//调拨单确认
	public int confirm(String formId, String userId,String userName, java.util.Date confirmTime)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		Date sConfirmTime = SqlDateUtil.getSqlDate(confirmTime);
		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		/*@lineinfo:generated-code*//*@lineinfo:125^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_TRANSFER_HEADER")
//  			SET
//  			confirmer_id = :userId,
//  			confirmer = :userName,
//  			confirm_time = :sConfirmTime,
//  			AUDIT_TIME_ACTUAL =:auditTimeActual
//  			WHERE 
//  			FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = userId;
  String __sJT_3 = userName;
  Date __sJT_4 = sConfirmTime;
  Date __sJT_5 = auditTimeActual;
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:135^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	private String formQuery(String inBranchId,String inStorageId,String outBranchId,String outStorageId, String formStatus, String branchType) {
		String query = " 1=1";
		
		StringBuilder group1 = new StringBuilder();
		StringBuilder group2 = new StringBuilder();
		
		
		if(!TextUtil.isEmpty(inBranchId) && !TextUtil.isEmpty(inStorageId)){
			group1.append(" (  h.IN_BRANCH_ID = '" + inBranchId+ "' AND h.IN_STORAGE_ID = '" + inStorageId+ "') ");
		}else{
			if (!TextUtil.isEmpty(inBranchId)) {
				group1.append("  (h.IN_BRANCH_ID = '" + inBranchId+ "') ");
			}
			
			if (!TextUtil.isEmpty(inStorageId)) {
				group1.append("  (h.IN_STORAGE_ID = '" + inStorageId+ "') ");
			}
		}
		
		if(!TextUtil.isEmpty(outBranchId) && !TextUtil.isEmpty(outStorageId)){
			group2.append(" (  h.OUT_BRANCH_ID = '" + outBranchId+ "' AND h.OUT_STORAGE_ID = '" + outStorageId+ "') ");
		}else{
			if (!TextUtil.isEmpty(outBranchId)) {
				group2.append("  (h.OUT_BRANCH_ID = '" + outBranchId+ "') ");
			}
			
			if (!TextUtil.isEmpty(outStorageId)) {
				group2.append("  (h.OUT_STORAGE_ID = '" + outStorageId+ "') ");
			}
		}
		
		if(!TextUtil.isEmpty(group1.toString()) && !TextUtil.isEmpty(group2.toString())){
			query += " and (" + group1.toString() + " or " + group2.toString() + ") ";
		}else{
			if (!TextUtil.isEmpty(group1.toString())) {
				query += " and " + group1.toString();
			}
			
			if (!TextUtil.isEmpty(group2.toString())) {
				query += " and " + group2.toString();
			}
		}
		
		if (!TextUtil.isEmpty(formStatus)) {
			query = query + " AND NVL( s.STATUS, 'null') = '"+ formStatus + "'";
		}
		
//		if (!TextUtil.isEmpty(branchType)) {
//			query = query + " AND b.BRANCH_TYPE = '"+ branchType + "'";
//		}
		
		return query;
	}
	
	
	public int count(java.util.Date startDate, java.util.Date endDate,String outBranchId,String outStorageId, String formStatus,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return count(startDate,endDate,"","",outBranchId,outStorageId,formStatus,branchType);
	}
	
	public int count(java.util.Date startDate, java.util.Date endDate,String inBranchId,String inStorageId,String outBranchId,String outStorageId, String formStatus,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(inBranchId,inStorageId,outBranchId,outStorageId,formStatus,branchType);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:210^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				count(*) 
//  			FROM
//  				:Com_("D_T1_TRANSFER_HEADER") h
//  			LEFT JOIN
//  				:Com_("D_T0_FORM_STATUS") s
//  		    ON
//  		        s.FORM_ID = h.FORM_ID
//  		    WHERE
//  		    	:query
//  			AND s.IS_CURRENT = 1
//      	    AND (h.FORM_TIME >= :sDate or h.FORM_TIME >= :sDate or :sDate is null)
//          	AND (h.FORM_TIME <= :eDate or h.FORM_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:224^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<TransferHeader> query(java.util.Date startDate, java.util.Date endDate,String outBranchId,String outStorageId, String formStatus,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return query(startDate,endDate,"","",outBranchId,outStorageId,formStatus,branchType,0, Integer.MAX_VALUE);
	}
	
	public List<TransferHeader> queryBill(java.util.Date startDate, java.util.Date endDate,String inBranchId,String inStorageId,String outBranchId,String outStorageId, String formStatus,String branchType,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(inBranchId,inStorageId,outBranchId,outStorageId,formStatus,branchType);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:244^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (SELECT
//  			                	    h.*
//  			                	    FROM
//  			                	    	:Com_("D_T1_TRANSFER_HEADER") h
//  			                	    LEFT JOIN
//  			                	    	:Com_("D_T0_FORM_STATUS") s
//  			                	    ON
//  			                	        s.FORM_ID = h.FORM_ID
//  			                	    WHERE
//  			                	    	:query
//  			                	    AND s.IS_CURRENT = 1
//  	                	    		AND (h.FORM_TIME >= :sDate or h.FORM_TIME >= :sDate or :sDate is null)
//  			            			AND (h.FORM_TIME <= :eDate or h.FORM_TIME <= :eDate or :eDate is null)
//  			                	    ORDER BY
//  			                	        H.FORM_ID desc) t
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
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:271^34*/
		List<TransferHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<TransferHeader> query(java.util.Date startDate, java.util.Date endDate,String inBranchId,String inStorageId,String outBranchId,String outStorageId, String formStatus,String branchType,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(inBranchId,inStorageId,outBranchId,outStorageId,formStatus,branchType);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:287^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (SELECT
//  			                	    h.*
//  			                	    FROM
//  			                	    :Com_("D_T1_TRANSFER_HEADER") h
//  			                	        --LEFT JOIN :{Com_("D_T2_BRANCH")} b ON h.IN_BRANCH_ID = b.BRANCH_ID
//  			                	    LEFT JOIN
//  			                	    :Com_("D_T0_FORM_STATUS") s
//  			                	    ON
//  			                	        s.FORM_ID = h.FORM_ID
//  			                	    LEFT JOIN
//  			                	    :Com_("D_T2_BRANCH") d
//  			                	    ON
//  			                	        h.IN_BRANCH_ID = d.BRANCH_ID
//  			                	    LEFT JOIN
//  			                	    :Com_("D_T2_BRANCH") d2
//  			                	    ON
//  			                	        h.OUT_BRANCH_ID = d2.BRANCH_ID
//  			                	    WHERE
//  			                	    	:query
//  			                	    AND s.IS_CURRENT = 1
//  	                	    		AND (d.BUSINESS_DATE >= :sDate or d2.BUSINESS_DATE >= :sDate or :sDate is null)
//  			            			AND (d.BUSINESS_DATE <= :eDate or d2.BUSINESS_DATE <= :eDate or :eDate is null)
//  			                	    ORDER BY
//  			                	        H.FORM_ID desc) t
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
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  Date __sJT_11 = eDate;
  int __sJT_12 = endRow;
  int __sJT_13 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 6);
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
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setInt(12, __sJT_12);
      __sJT_stmt.setInt(13, __sJT_13);
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

/*@lineinfo:user-code*//*@lineinfo:323^34*/
		List<TransferHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public double sum(java.util.Date startDate, java.util.Date endDate,String outBranchId,String outStorageId, String formStatus,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return sum(startDate,endDate,"","",outBranchId,outStorageId,formStatus,branchType);
	}
	
	public double sum(java.util.Date startDate, java.util.Date endDate,String inBranchId,String inStorageId,String outBranchId,String outStorageId, String formStatus,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = formQuery(inBranchId,inStorageId,outBranchId,outStorageId,formStatus,branchType);
		
		double allPayAmt = 0;
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:345^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    SUM(ALL_PAY_AMT)
//  		
//  		FROM
//  		    (SELECT
//  		    			h.ALL_PAY_AMT
//              	    FROM
//              	    :Com_("D_T1_TRANSFER_HEADER") h
//          	        	--LEFT JOIN :{Com_("D_T2_BRANCH")} b ON h.IN_BRANCH_ID = b.BRANCH_ID
//              	    LEFT JOIN
//              	    :Com_("D_T0_FORM_STATUS") s
//              	    ON
//              	        s.FORM_ID = h.FORM_ID
//              	    LEFT JOIN
//              	    :Com_("D_T2_BRANCH") d
//              	    ON
//              	        h.IN_BRANCH_ID = d.BRANCH_ID
//              	    LEFT JOIN
//              	    :Com_("D_T2_BRANCH") d2
//              	    ON
//              	        h.OUT_BRANCH_ID = d2.BRANCH_ID
//              	    WHERE
//              	    	:query
//              	    AND s.IS_CURRENT = 1
//      	    		AND (d.BUSINESS_DATE >= :sDate or d2.BUSINESS_DATE >= :sDate or :sDate is null)
//          			AND (d.BUSINESS_DATE <= :eDate or d2.BUSINESS_DATE <= :eDate or :eDate is null)
//              	    ) t };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  Date __sJT_11 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 7);
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
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:373^21*/
		closeDefaultContext(myCtx);
		return allPayAmt;
	}
	
	

	public TransferHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:384^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_TRANSFER_HEADER") h
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:391^3*/
		List<TransferHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<TransferHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<TransferHeader> headerLst = new ArrayList<TransferHeader>();
		while(headerIter.next()) {
			TransferHeader header = new TransferHeader();
			header.setFormId(headerIter.form_id());
			header.setInBranchId(headerIter.in_branch_id());
			header.setInBranch(headerIter.in_branch());
			header.setInStorageId(headerIter.in_storage_id());
			header.setInStorage(headerIter.in_storage());
			header.setOutBranchId(headerIter.out_branch_id());
			header.setOutBranch(headerIter.out_branch());
			header.setOutStorageId(headerIter.out_storage_id());
			header.setOutStorage(headerIter.out_storage());
			header.setFromMakerId(headerIter.from_maker_id());
			header.setFromMaker(headerIter.from_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setConfirmerId(headerIter.confirmer_id());
			header.setConfirmer(headerIter.confirmer());
			header.setConfirmTime(SqlDateUtil.getUtilDate(headerIter.confirm_time()));
			header.setOutManId(headerIter.out_man_id());
			header.setOutMan(headerIter.out_man());
			header.setOutTime(SqlDateUtil.getUtilDate(headerIter.out_time()));
			header.setFormNote(headerIter.form_note());
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class TransferHeaderBean_SJProfileKeys 
{
  private static TransferHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TransferHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TransferHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.TransferHeaderBean_SJProfile0");
  }
}
