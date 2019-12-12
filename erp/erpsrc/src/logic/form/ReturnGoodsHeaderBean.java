/*@lineinfo:filename=ReturnGoodsHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Sep 20 21:22:57 CST 2014 by lyz
 * Last edited on Sat Sep 20 21:22:57 CST 2014 by lyz
 * 
 * comment: 餐厅退货单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ReturnGoodsHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ReturnGoodsHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ReturnGoodsHeaderBean.class);
	
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
    form_ref_idNdx = findColumn("form_ref_id");
    form_noteNdx = findColumn("form_note");
    returner_idNdx = findColumn("returner_id");
    returnerNdx = findColumn("returner");
    return_timeNdx = findColumn("return_time");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    providerNdx = findColumn("provider");
    receive_timeNdx = findColumn("receive_time");
    requesterNdx = findColumn("requester");
    inputerNdx = findColumn("inputer");
    input_timeNdx = findColumn("input_time");
    audit_timeNdx = findColumn("audit_time");
    snoteNdx = findColumn("snote");
    smaxNdx = findColumn("smax");
    statusNdx = findColumn("status");
    audit_time_actualNdx = findColumn("audit_time_actual");
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
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public String returner_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(returner_idNdx);
  }
  private int returner_idNdx;
  public String returner() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(returnerNdx);
  }
  private int returnerNdx;
  public Date return_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(return_timeNdx);
  }
  private int return_timeNdx;
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
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String snote() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(snoteNdx);
  }
  private int snoteNdx;
  public String smax() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(smaxNdx);
  }
  private int smaxNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
  public Date audit_time_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_time_actualNdx);
  }
  private int audit_time_actualNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:54^4*/
	
	public int saveEntity(ReturnGoodsHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String formRefId = header.getFormRefId();
		String formNote = header.getFormNote();
		String returnerId = header.getReturnerId();
		String returner = header.getReturner();
		Date returnTime = SqlDateUtil.getSqlDate(header.getReturnTime());
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:70^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_RETURN_GOODS_HEADER") 
//  				(FORM_ID, FORM_REF_ID, FORM_NOTE, RETURNER_ID, RETURNER, RETURN_TIME,AUDIT_TIME, ALL_PAY_AMT, MAX_PAY_ITEM) 
//  			VALUES (:formId, :formRefId, :formNote, :returnerId, :returner, :returnTime, :auditTime, :allPayAmt, :maxPayItem)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formRefId;
  String __sJT_4 = formNote;
  String __sJT_5 = returnerId;
  String __sJT_6 = returner;
  Date __sJT_7 = returnTime;
  Date __sJT_8 = auditTime;
  Double __sJT_9 = allPayAmt;
  String __sJT_10 = maxPayItem;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:74^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ReturnGoodsHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String formRefId = header.getFormRefId();
		String formNote = header.getFormNote();
		String returnerId = header.getReturnerId();
		String returner = header.getReturner();
		Date returnTime = SqlDateUtil.getSqlDate(header.getReturnTime());
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		Double allPayAmt = header.getAllPayAmt();
		String maxPayItem = header.getMaxPayItem();

		/*@lineinfo:generated-code*//*@lineinfo:94^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_RETURN_GOODS_HEADER")
//  			SET
//  				FORM_ID = :formId,
//  				FORM_NOTE = :formNote,
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
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formNote;
  Double __sJT_4 = allPayAmt;
  String __sJT_5 = maxPayItem;
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:104^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:114^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_RETURN_GOODS_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:120^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int audit(String formId, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		Date auditTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:132^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_RETURN_GOODS_HEADER")
//  			SET
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
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  Date __sJT_2 = sAuditTime;
  Date __sJT_3 = auditTimeActual;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:140^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,String branchType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType,branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:154^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		:Com_("D_T1_RETURN_GOODS_HEADER") rh
//  		LEFT JOIN
//  		:Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = rh.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		:Com_("D_T1_SHIPPING_HEADER") h
//  		ON
//  		    h.FORM_ID = rh.FORM_REF_ID
//  		LEFT JOIN
//  		:Com_("d_t2_branch") b
//  		ON
//  		    h.REQUESTER_ID = b.BRANCH_ID
//  		WHERE
//  		    :query
//  		AND (
//  		        rh.RETURN_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        rh.RETURN_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_4 = Com_("d_t2_branch");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:181^26*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String queryType,String branchType) {
		String query = " 1=1";
		if (!"-1".equals(branchId)&&!TextUtil.isEmpty(branchId)) {
			query = query + " AND h.REQUESTER_ID = '" + branchId + "'";
		}
		if ("audit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		
		return query;
	}
	
	public List<ReturnGoodsHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,String branchType,int startRow, int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType,branchType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:206^3*/

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
//  			                	    rh.*,
//  			                	    h.PROVIDER,
//  			                	    h.RECEIVE_TIME,
//  			                	    h.REQUESTER,
//  			                	    h.INPUTER,
//  			                	    h.INPUT_TIME,
//  			                	    h.FORM_NOTE    AS snote,
//  			                	    h.MAX_PAY_ITEM AS smax,
//  			                	    s.status 
//  			                	FROM
//  			                	:Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			                	LEFT JOIN
//  			                	:Com_("D_T0_FORM_STATUS") s
//  			                	ON
//  			                	    s.FORM_ID = rh.FORM_ID
//  			                	AND s.IS_CURRENT = 1
//  			                	INNER JOIN
//  			                	:Com_("D_T1_SHIPPING_HEADER") h
//  			                	ON
//  			                	    h.FORM_ID = rh.FORM_REF_ID
//  			                	LEFT JOIN
//  			                	:Com_("d_t2_branch") b
//  			                	ON
//  			                	    h.REQUESTER_ID = b.BRANCH_ID
//  			                	WHERE
//  			                	    :query
//  			                	AND (
//  			                	        rh.RETURN_TIME >= :sDate
//  			                	    OR  :sDate IS NULL)
//  			                	AND (
//  			                	        rh.RETURN_TIME <= :eDate
//  			                	    OR  :eDate IS NULL)
//  			                	ORDER BY
//  			                	    rh.form_id) t
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
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_4 = Com_("d_t2_branch");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:253^34*/
		List<ReturnGoodsHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public ReturnGoodsHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:264^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    rh.*,
//  			    h.PROVIDER,
//  			    h.RECEIVE_TIME,
//  			    h.AUDIT_TIME,
//  			    h.REQUESTER,
//  			    h.INPUTER,
//  			    h.INPUT_TIME,
//  			    h.FORM_NOTE as snote,
//  			    h.MAX_PAY_ITEM AS smax,
//  			    s.status 
//  			FROM
//  			:Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = rh.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			INNER JOIN
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			ON
//  			    h.FORM_ID = rh.FORM_REF_ID
//  			WHERE
//  				rh.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:289^3*/
		List<ReturnGoodsHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<ReturnGoodsHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ReturnGoodsHeader> headerLst = new ArrayList<ReturnGoodsHeader>();
		while(headerIter.next()) {
			ReturnGoodsHeader header = new ReturnGoodsHeader();
			header.setFormId(headerIter.form_id());
			header.setFormRefId(headerIter.form_ref_id());
			header.setFormNote(headerIter.form_note());
			header.setReturnerId(headerIter.returner_id());
			header.setReturner(headerIter.returner());
			header.setReturnTime(SqlDateUtil.getUtilDate(headerIter.return_time()));
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setProvider(headerIter.provider());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setRequester(headerIter.requester());
			header.setInputer(headerIter.inputer());
			header.setInputTime(SqlDateUtil.getUtilDate(headerIter.input_time()));
			header.setSnote(headerIter.snote());
			header.setMaxPayItem(headerIter.smax());
			header.setStatus(headerIter.status());
			header.setAuditTimeActual(SqlDateUtil.getUtilDate(headerIter.audit_time_actual()));
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ReturnGoodsHeaderBean_SJProfileKeys 
{
  private static ReturnGoodsHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ReturnGoodsHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ReturnGoodsHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ReturnGoodsHeaderBean_SJProfile0");
  }
}
