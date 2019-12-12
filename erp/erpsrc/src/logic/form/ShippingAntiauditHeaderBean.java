/*@lineinfo:filename=ShippingAntiauditHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Sep 19 11:39:18 CST 2014 by lyz
 * Last edited on Fri Sep 19 11:39:18 CST 2014 by lyz
 * 
 * comment: 餐厅配送反审核单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ShippingAntiauditHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchType;

public class ShippingAntiauditHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ShippingAntiauditHeaderBean.class);
	
	public int saveEntity(ShippingAntiauditHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formRefId = header.getFormRefId();
		String antiauditorId = header.getAntiauditorId();
		String antiauditor = header.getAntiauditor();
		String antiauditBranchId = header.getAntiauditBranchId();
		String antiauditBranch = header.getAntiauditBranch();
		Date antiauditTime = SqlDateUtil.getSqlDate(header.getAntiauditTime());
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:49^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") 
//  				(FORM_REF_ID, ANTIAUDITOR_ID, ANTIAUDITOR, ANTIAUDIT_BRANCH_ID, ANTIAUDIT_BRANCH, ANTIAUDIT_TIME,AUDIT_TIME, FORM_NOTE) 
//  			VALUES (:formRefId, :antiauditorId, :antiauditor, :antiauditBranchId, :antiauditBranch, :antiauditTime, :auditTime, :formNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = formRefId;
  String __sJT_3 = antiauditorId;
  String __sJT_4 = antiauditor;
  String __sJT_5 = antiauditBranchId;
  String __sJT_6 = antiauditBranch;
  Date __sJT_7 = antiauditTime;
  Date __sJT_8 = auditTime;
  String __sJT_9 = formNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:53^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ShippingAntiauditHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formRefId = header.getFormRefId();
		String antiauditorId = header.getAntiauditorId();
		String antiauditor = header.getAntiauditor();
		String antiauditBranchId = header.getAntiauditBranchId();
		String antiauditBranch = header.getAntiauditBranch();
		Date antiauditTime = SqlDateUtil.getSqlDate(header.getAntiauditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:71^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER")
//  			SET
//  				FORM_NOTE = :formNote
//  			WHERE 
//  				FORM_REF_ID = :formRefId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = formNote;
  String __sJT_3 = formRefId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:78^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formRefId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:88^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER")
//  			WHERE
//  				FORM_REF_ID = :formRefId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = formRefId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:94^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int audit(String formRefId, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:106^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER")
//  			SET
//  				audit_time = :sAuditTime,
//  				AUDIT_TIME_ACTUAL =:auditTimeActual
//  			WHERE 
//  				FORM_REF_ID = :formRefId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  Date __sJT_2 = sAuditTime;
  Date __sJT_3 = auditTimeActual;
  String __sJT_4 = formRefId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:114^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String storageId, String queryType,String branchType) 
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = formQuery(branchId, storageId, queryType, branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:128^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				COUNT(*) 
//  			FROM
//  			:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") ah
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  				ah.ANTIAUDIT_BRANCH_ID = b.BRANCH_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			 	s.FORM_ID = ah.FORM_REF_ID
//  			 	AND s.IS_CURRENT = 1
//  			INNER JOIN
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			ON
//  				h.FORM_ID = ah.FORM_REF_ID
//  			WHERE
//  				:query
//  			AND (ah.ANTIAUDIT_TIME >= :sDate
//  					OR  :sDate IS NULL)
//  			AND (ah.ANTIAUDIT_TIME <= :eDate
//  					OR  :eDate IS NULL)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:152^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public String formQuery(String branchId, String storageId, String queryType, String branchType) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) { 
			// 订货部门
			if (BranchType.RESTAURANT.equals(branchType)) {
				query = query + " AND h.REQUESTER_ID = '" + branchId + "'";
			} else if (BranchType.LOGISTICSCENTER.equals(branchType)) {
				//配送部门
				query = query + " AND h.PROVIDER_ID = '" + branchId + "'";
			}
		}
		
		if (!TextUtil.isEmpty(storageId)) {
			if("LOGISTICSCENTER".equals(branchType)) {
				query = query + " AND h.OUT_STORAGE_ID = '" + storageId + "'";
			}
			if("RESTAURANT".equals(branchType)) {
				query = query + " AND h.IN_STORAGE_ID = '" + storageId + "'";
			}
		}
		
		if ("unaudit".equals(queryType)) { // 获取反审核中记录
			query = query + " AND h.ANTI_STATUS = '反审核中'"; 
   		query = query + " AND '" + branchType + "' != b.BRANCH_TYPE "; 
		}
		
//		if ("self".equals(queryType)) { // 
//			query = query + " AND '" + branchType + "' = b.BRANCH_TYPE "; 
//		}
		
		return query;
	}

	/*@lineinfo:generated-code*//*@lineinfo:190^2*/

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
    form_ref_idNdx = findColumn("form_ref_id");
    antiauditor_idNdx = findColumn("antiauditor_id");
    antiauditorNdx = findColumn("antiauditor");
    antiaudit_branch_idNdx = findColumn("antiaudit_branch_id");
    antiaudit_branchNdx = findColumn("antiaudit_branch");
    antiaudit_timeNdx = findColumn("antiaudit_time");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    providerNdx = findColumn("provider");
    outStorageNdx = findColumn("outStorage");
    inStorageNdx = findColumn("inStorage");
    receive_timeNdx = findColumn("receive_time");
    requesterNdx = findColumn("requester");
    inputerNdx = findColumn("inputer");
    input_timeNdx = findColumn("input_time");
    audit_time_actualNdx = findColumn("audit_time_actual");
    snoteNdx = findColumn("snote");
    anti_statusNdx = findColumn("anti_status");
    branch_typeNdx = findColumn("branch_type");
  }
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String antiauditor_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(antiauditor_idNdx);
  }
  private int antiauditor_idNdx;
  public String antiauditor() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(antiauditorNdx);
  }
  private int antiauditorNdx;
  public String antiaudit_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(antiaudit_branch_idNdx);
  }
  private int antiaudit_branch_idNdx;
  public String antiaudit_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(antiaudit_branchNdx);
  }
  private int antiaudit_branchNdx;
  public Date antiaudit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(antiaudit_timeNdx);
  }
  private int antiaudit_timeNdx;
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
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String outStorage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(outStorageNdx);
  }
  private int outStorageNdx;
  public String inStorage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(inStorageNdx);
  }
  private int inStorageNdx;
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
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
  public Date audit_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_time_actualNdx);
  }
  private int audit_time_actualNdx;
  public String snote() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(snoteNdx);
  }
  private int snoteNdx;
  public String anti_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(anti_statusNdx);
  }
  private int anti_statusNdx;
  public String branch_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_typeNdx);
  }
  private int branch_typeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:201^55*/

	/**
	 * 餐厅只能看到物流中心操作的反审核单，反之亦然
	 */
	public List<ShippingAntiauditHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId,String storage, String queryType,String branchType)   
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storage, queryType,branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:214^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				ah.*,
//  				h.PROVIDER,
//  				h.RECEIVE_TIME,
//  				h.REQUESTER,
//  				h.INPUTER,
//  				h.INPUT_TIME,
//  			    h.OUT_STORAGE outStorage,
//  				h.IN_STORAGE inStorage,
//  				h.FORM_NOTE    AS snote,
//  				h.MAX_PAY_ITEM AS smax,
//  				h.ANTI_STATUS,
//  				b.BRANCH_TYPE
//  			FROM
//  			:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") ah
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  				ah.ANTIAUDIT_BRANCH_ID = b.BRANCH_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  				s.FORM_ID = ah.FORM_REF_ID
//  				AND s.IS_CURRENT = 1
//  			INNER JOIN
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			ON
//  				h.FORM_ID = ah.FORM_REF_ID
//  			WHERE
//  				:query
//  			AND (ah.ANTIAUDIT_TIME >= :sDate
//  					OR  :sDate IS NULL)
//  			AND (ah.ANTIAUDIT_TIME <= :eDate
//  					OR  :eDate IS NULL)
//  			ORDER BY
//  				ah.FORM_REF_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 5);
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
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:251^3*/
		List<ShippingAntiauditHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	
	public List<ShippingAntiauditHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId,String storage, String queryType,String branchType, int startRow, int endRow)  
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storage, queryType,branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:267^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  		    FROM
//  		        (
//  		            SELECT
//  		                t.*,
//  		                ROWNUM rowNumber
//  		            FROM
//  		                (
//  		            			SELECT
//  		        				ah.*,
//  		        				h.PROVIDER,
//  		        				h.RECEIVE_TIME,
//  		        				h.REQUESTER,
//  		        				h.INPUTER,
//  		        				h.INPUT_TIME,
//  		        			    h.OUT_STORAGE outStorage,
//  		        				h.IN_STORAGE inStorage,
//  		        				h.FORM_NOTE    AS snote,
//  		        				h.MAX_PAY_ITEM AS smax,
//  		        				h.ANTI_STATUS,
//  		        				b.BRANCH_TYPE
//  		        			FROM
//  		        			:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") ah
//  		        			LEFT JOIN
//  		        			:Com_("D_T2_BRANCH") b
//  		        			ON
//  		        				ah.ANTIAUDIT_BRANCH_ID = b.BRANCH_ID
//  		        			LEFT JOIN
//  		        			:Com_("D_T0_FORM_STATUS") s
//  		        			ON
//  		        				s.FORM_ID = ah.FORM_REF_ID
//  		        				AND s.IS_CURRENT = 1
//  		        			INNER JOIN
//  		        			:Com_("D_T1_SHIPPING_HEADER") h
//  		        			ON
//  		        				h.FORM_ID = ah.FORM_REF_ID
//  		        			WHERE
//  		        				:query
//  		        			AND (ah.ANTIAUDIT_TIME >= :sDate
//  		        					OR  :sDate IS NULL)
//  		        			AND (ah.ANTIAUDIT_TIME <= :eDate
//  		        					OR  :eDate IS NULL)
//  		        			ORDER BY
//  		        				ah.FORM_REF_ID
//  		        		) t
//  		            WHERE
//  		                ROWNUM < :endRow)
//  		    WHERE
//  		        rowNumber >= :startRow
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:318^3*/
		List<ShippingAntiauditHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public ShippingAntiauditHeader queryById(String formId) 
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:329^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    ah.*,
//  			    h.PROVIDER,
//  			    h.RECEIVE_TIME,
//  			    h.REQUESTER,
//  			    h.INPUTER,
//  			    h.OUT_STORAGE outStorage,
//  				h.IN_STORAGE inStorage,
//  			    h.INPUT_TIME,
//  			    h.FORM_NOTE as snote,
//  				h.ANTI_STATUS,
//  				b.BRANCH_TYPE
//  			FROM
//  			:Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER") ah
//  		    LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		    ON
//  		        s.FORM_ID = ah.FORM_REF_ID
//  		    AND s.IS_CURRENT = 1
//      		LEFT JOIN
//      		:Com_("D_T2_BRANCH") b
//  		ON
//  			ah.ANTIAUDIT_BRANCH_ID = b.BRANCH_ID
//  		    INNER JOIN
//  		    :Com_("D_T1_SHIPPING_HEADER") h
//  		    ON
//  		        h.FORM_ID = ah.FORM_REF_ID
//  		    WHERE
//  				ah.FORM_REF_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_ANTIAUDIT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShippingAntiauditHeaderBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:359^3*/
		List<ShippingAntiauditHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<ShippingAntiauditHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ShippingAntiauditHeader> headerLst = new ArrayList<ShippingAntiauditHeader>();
		while(headerIter.next()) {
			ShippingAntiauditHeader header = new ShippingAntiauditHeader();
			header.setFormRefId(headerIter.form_ref_id());
			header.setAntiauditorId(headerIter.antiauditor_id());
			header.setAntiauditor(headerIter.antiauditor());
			header.setAntiauditBranchId(headerIter.antiaudit_branch_id());
			header.setAntiauditBranch(headerIter.antiaudit_branch());
			header.setAntiauditTime(SqlDateUtil.getUtilDate(headerIter.antiaudit_time()));
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			
			header.setOutStorage(headerIter.outStorage());
			header.setInStorage(headerIter.inStorage());

			header.setProvider(headerIter.provider());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setRequester(headerIter.requester());
			header.setInputer(headerIter.inputer());
			header.setInputTime(SqlDateUtil.getUtilDate(headerIter.input_time()));
			header.setSnote(headerIter.snote());
			header.setAntiStatus(headerIter.anti_status());
			header.setBranchType(headerIter.branch_type());
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ShippingAntiauditHeaderBean_SJProfileKeys 
{
  private static ShippingAntiauditHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShippingAntiauditHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShippingAntiauditHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ShippingAntiauditHeaderBean_SJProfile0");
  }
}
