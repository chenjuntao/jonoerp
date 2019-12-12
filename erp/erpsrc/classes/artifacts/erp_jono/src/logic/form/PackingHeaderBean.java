/*@lineinfo:filename=PackingHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 11 15:06:52 CST 2014 by lyz
 * Last edited on Wed July 22 8:17:12 CST 2015 by lyz
 * 
 * comment: 装箱单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PackingHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PackingHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PackingHeaderBean.class);
	
	public int newSerial(java.util.Date businessDate, String branchId) 
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String maxId = null;
		Date bDate = SqlDateUtil.getSqlDate(businessDate);
		/*@lineinfo:generated-code*//*@lineinfo:44^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    MAX(h.FORM_ID) 
//  			FROM
//  			:Com_("D_T1_PACKING_HEADER") h
//  			WHERE
//  			    h.FORM_TIME = :bDate
//  			AND h.PACKING_BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  Date __sJT_2 = bDate;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:52^3*/
		closeDefaultContext(myCtx);

		int result = 1;
		if (maxId != null) {
			maxId = maxId.substring(maxId.length() - FormConstant.DEFAULT_SERIAL_LENGTH);
			result = Integer.parseInt(maxId) + 1;
		}
		return result;
	}
	
	public int saveEntity(PackingHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String packingBranchId = header.getPackingBranchId();
		String formRefId = header.getFormRefId();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		Date packingTime = SqlDateUtil.getSqlDate(header.getPackingTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:77^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_PACKING_HEADER") 
//  				(FORM_ID, PACKING_BRANCH_ID, FORM_REF_ID, FORM_MAKER_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL, PACKING_TIME, FORM_NOTE) 
//  			VALUES (:formId, :packingBranchId, :formRefId, :formMakerId, :formMaker, :formTime, :formTimeActual, :packingTime, :formNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = packingBranchId;
  String __sJT_4 = formRefId;
  String __sJT_5 = formMakerId;
  String __sJT_6 = formMaker;
  Date __sJT_7 = formTime;
  Date __sJT_8 = formTimeActual;
  Date __sJT_9 = packingTime;
  String __sJT_10 = formNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:81^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(PackingHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date packingTime = SqlDateUtil.getSqlDate(header.getPackingTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:98^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PACKING_HEADER")
//  			SET
//  				FORM_ID = :formId,
//  				FORM_MAKER_ID = :formMakerId,
//  				FORM_MAKER = :formMaker,
//  				FORM_TIME = :formTime,
//  				PACKING_TIME = :packingTime,
//  				FORM_NOTE = :formNote
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formMakerId;
  String __sJT_4 = formMaker;
  Date __sJT_5 = formTime;
  Date __sJT_6 = packingTime;
  String __sJT_7 = formNote;
  String __sJT_8 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:110^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:120^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PACKING_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:126^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int audit(String formId, String userId, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:137^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PACKING_HEADER")
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
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  String __sJT_2 = userId;
  Date __sJT_3 = sAuditTime;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:145^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:159^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_PACKING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//  			AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:173^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String queryType) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.REQUESTER_ID = '" + branchId + "'";
		}
		if ("unaudit".equals(queryType)) { // 获取未处理记录
			query = query + " AND NVL( s.STATUS, 'null') = '未处理'"; 
		}
		return query;
	}
	
	public List<PackingHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:197^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_PACKING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//  			AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:211^3*/
		List<PackingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public PackingHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:222^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_PACKING_HEADER") h
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:229^3*/
		List<PackingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}

	/*@lineinfo:generated-code*//*@lineinfo:239^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class NodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public NodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(nameNdx);
  }
  private int nameNdx;
  public String parent() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parentNdx);
  }
  private int parentNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:239^70*/

	/**
	 * 查询装箱单，以树形控件组织，制单日期-〉装箱单
	 */
	public List<Map> queryTree() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:248^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			                TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			                'root'                              AS parent
//  			            FROM
//  			            :Com_("D_T1_PACKING_HEADER") h
//  			            GROUP BY
//  			                h.FORM_TIME
//  			            ORDER BY
//  			                h.FORM_TIME DESC)
//  			    UNION ALL
//  			    SELECT
//  			        *
//  			    FROM
//  			        (
//  			            SELECT
//  			                h.form_id                           AS id,
//  			                h.form_id                           AS name,
//  			                TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS parent
//  			            FROM
//  			            :Com_("D_T1_PACKING_HEADER") h
//  			            ORDER BY
//  			                h.FORM_ID) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  String __sJT_2 = Com_("D_T1_PACKING_HEADER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:275^30*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}

	/**
	 * 查询装箱单，以树形控件组织，制单日期-〉装箱单  添加时间查询条件
	 */
	public List<Map> queryTree(java.util.Date startDate, java.util.Date endDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:298^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { SELECT
//  			    *
//  			    FROM(
//  			            SELECT
//  			                TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS id,
//  			                TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS name,
//  			                'root'                              AS parent
//  			            FROM
//  			            :Com_("D_T1_PACKING_HEADER") h
//  			                WHERE
//  					           (h.FORM_TIME >= :sDate or :sDate is null)
//  								AND (h.FORM_TIME <= :eDate or :eDate is null)
//  			            GROUP BY
//  			                h.FORM_TIME
//  			            ORDER BY
//  			                h.FORM_TIME DESC)
//  			    UNION ALL
//  			    SELECT
//  			        *
//  			    FROM
//  			        (
//  			            SELECT
//  			                h.form_id                           AS id,
//  			                h.form_id                           AS name,
//  			                TO_CHAR( h.FORM_TIME, 'yyyy-MM-dd') AS parent
//  			            FROM
//  			            :Com_("D_T1_PACKING_HEADER") h
//  			            ORDER BY
//  			                h.FORM_ID) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_HEADER");
  Date __sJT_2 = sDate;
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  Date __sJT_5 = eDate;
  String __sJT_6 = Com_("D_T1_PACKING_HEADER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingHeaderBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:327^30*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:341^2*/

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
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    form_time_actualNdx = findColumn("form_time_actual");
    packing_timeNdx = findColumn("packing_time");
    form_noteNdx = findColumn("form_note");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public Date packing_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(packing_timeNdx);
  }
  private int packing_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:342^77*/
	
	private List<PackingHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<PackingHeader> headerLst = new ArrayList<PackingHeader>();
		while(headerIter.next()) {
			PackingHeader header = new PackingHeader();
			header.setFormId(headerIter.form_id());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setFormTimeActual(SqlDateUtil.getUtilDate(headerIter.form_time_actual()));
			header.setPackingTime(SqlDateUtil.getUtilDate(headerIter.packing_time()));
			header.setFormNote(headerIter.form_note());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class PackingHeaderBean_SJProfileKeys 
{
  private static PackingHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PackingHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PackingHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.PackingHeaderBean_SJProfile0");
  }
}
