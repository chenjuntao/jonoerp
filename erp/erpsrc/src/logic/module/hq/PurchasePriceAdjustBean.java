/*@lineinfo:filename=PurchasePriceAdjustBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 12, 2015 by liyzh
 * Last edited on Mar 12, 2015 by liyzh
 * 
 * 说明： 采购调价单管理
 */
package logic.module.hq;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PriceAdjustHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormPrefix;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.TextUtil;

public class PurchasePriceAdjustBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PurchasePriceAdjustBean.class);
	
	public int updateEntity(PriceAdjustHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		String adjustType = header.getAdjustType();
		String adjustScope = header.getAdjustScope();
		String effectType = header.getEffectType();
		Date effectTime = SqlDateUtil.getSqlDate(header.getEffectTime());
		String note = header.getNote();
		String formMaker = header.getFormMaker();
		Date makeTime = SqlDateUtil.getSqlDate(header.getMakeTime());
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:60^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PRICE_ADJUST_HEADER")
//  			SET
//  				FORM_ID = :formId,
//  			--	FORM_TIME = :formTime,
//  			--	ADJUST_TYPE = :adjustType,
//  			--	ADJUST_SCOPE = :adjustScope,
//  				EFFECT_TYPE = :effectType,
//  				EFFECT_TIME = :effectTime,
//  				NOTE = :note,
//  			--	FORM_MAKER = :formMaker,
//  			--	MAKE_TIME = :makeTime,
//  				AUDITOR = :auditor,
//  				AUDIT_TIME = :auditTime,
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
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = effectType;
  Date __sJT_4 = effectTime;
  String __sJT_5 = note;
  String __sJT_6 = auditor;
  Date __sJT_7 = auditTime;
  String __sJT_8 = formNote;
  String __sJT_9 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasePriceAdjustBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
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

/*@lineinfo:user-code*//*@lineinfo:78^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:88^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PRICE_ADJUST_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasePriceAdjustBean_SJProfileKeys.getKey(0), 1);
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
	
	public int audit(String formId, String userId, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:105^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PRICE_ADJUST_HEADER")
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
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = userId;
  Date __sJT_3 = sAuditTime;
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasePriceAdjustBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:113^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String adjustType,String itemId, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(queryType,itemId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:127^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT( DISTINCT h.form_id)
//  		
//  		FROM
//  		    :Com_("D_T1_PRICE_ADJUST_HEADER") h
//  		INNER JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  		ON
//  		    h.form_id = d.form_id
//  		WHERE
//  		    :query
//  		AND h.ADJUST_TYPE = :adjustType
//  		AND (
//  		        h.FORM_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.FORM_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_4 = query;
  String __sJT_5 = adjustType;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasePriceAdjustBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:151^26*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String queryType,String itemId) {
		String query = " 1=1";
		if (BillStatus.UNADUIT_US.equals(queryType)) {
			query = query + " AND NVL( s.STATUS, 'null') = '" + BillStatus.UNADUIT_CN + "'";
		}
		
		if(!TextUtil.isEmpty(itemId)){
			query = query + " AND d.ITEM_ID = '" +itemId + "' ";
		}
		return query;
	}

	/*@lineinfo:generated-code*//*@lineinfo:168^2*/

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
    form_timeNdx = findColumn("form_time");
    adjust_typeNdx = findColumn("adjust_type");
    adjust_scopeNdx = findColumn("adjust_scope");
    supplier_idNdx = findColumn("supplier_id");
    supplier_nameNdx = findColumn("supplier_name");
    effect_typeNdx = findColumn("effect_type");
    effect_timeNdx = findColumn("effect_time");
    noteNdx = findColumn("note");
    form_makerNdx = findColumn("form_maker");
    make_timeNdx = findColumn("make_time");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    form_statusNdx = findColumn("form_status");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public String adjust_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(adjust_typeNdx);
  }
  private int adjust_typeNdx;
  public String adjust_scope() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(adjust_scopeNdx);
  }
  private int adjust_scopeNdx;
  public String supplier_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplier_idNdx);
  }
  private int supplier_idNdx;
  public String supplier_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplier_nameNdx);
  }
  private int supplier_nameNdx;
  public String effect_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(effect_typeNdx);
  }
  private int effect_typeNdx;
  public Date effect_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(effect_timeNdx);
  }
  private int effect_timeNdx;
  public String note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(noteNdx);
  }
  private int noteNdx;
  public String form_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_makerNdx);
  }
  private int form_makerNdx;
  public Date make_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(make_timeNdx);
  }
  private int make_timeNdx;
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
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:171^57*/
	
	public List<PriceAdjustHeader> query(java.util.Date startDate, java.util.Date endDate, 
			String adjustType, String queryType,String itemId, int startRow, int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		String query = formQuery(queryType,itemId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:184^3*/

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
//  			                    	DISTINCT  h.*,
//  			                        s.status      AS form_status,
//  			                        b.BRANCH_NAME AS SUPPLIER_NAME
//  			                    FROM
//  			                        :Com_("D_T1_PRICE_ADJUST_HEADER") h
//  			                    INNER JOIN
//  			                        :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        s.FORM_ID = h.FORM_ID
//  			                    AND s.IS_CURRENT = 1
//  			                    INNER JOIN
//  			                        :Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  			                    ON
//  			                        h.form_id = d.form_id
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_BRANCH") b
//  			                    ON
//  			                        b.BRANCH_ID = h.SUPPLIER_ID
//  			                    WHERE
//  			                        :query
//  			                    AND h.ADJUST_TYPE = :adjustType
//  			                    AND (
//  			                            h.FORM_TIME >= :sDate
//  			                        OR  :sDate IS NULL)
//  			                    AND (
//  			                            h.FORM_TIME <= :eDate
//  			                        OR  :eDate IS NULL)
//  			                    ORDER BY
//  			                        h.FORM_ID DESC ) t
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
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = query;
  String __sJT_6 = adjustType;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  int __sJT_11 = endRow;
  int __sJT_12 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasePriceAdjustBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:227^34*/
		List<PriceAdjustHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public PriceAdjustHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:238^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    '' as form_status,
//  			    b.BRANCH_NAME as SUPPLIER_NAME
//  			FROM
//  				:Com_("D_T1_PRICE_ADJUST_HEADER") h
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  			ON
//  				b.BRANCH_ID = h.SUPPLIER_ID
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasePriceAdjustBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:251^3*/
		List<PriceAdjustHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<PriceAdjustHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<PriceAdjustHeader> headerLst = new ArrayList<PriceAdjustHeader>();
		while(headerIter.next()) {
			PriceAdjustHeader header = new PriceAdjustHeader();
			header.setFormId(headerIter.form_id());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setAdjustType(headerIter.adjust_type());
			header.setAdjustScope(headerIter.adjust_scope());
			header.setSupplierId(headerIter.supplier_id());
			header.setSupplierName(headerIter.supplier_name());
			header.setEffectType(headerIter.effect_type());
			header.setEffectTime(SqlDateUtil.getUtilDate(headerIter.effect_time()));
			header.setNote(headerIter.note());
			header.setFormMaker(headerIter.form_maker());
			header.setMakeTime(SqlDateUtil.getUtilDate(headerIter.make_time()));
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setFormStatus(headerIter.form_status());
			headerLst.add(header);
		}
		return headerLst;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	/*@lineinfo:generated-code*//*@lineinfo:287^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    rownumberNdx = findColumn("rownumber");
    itemIdNdx = findColumn("itemId");
    itemNameNdx = findColumn("itemName");
    itemDimensionNdx = findColumn("itemDimension");
    itemSpecificationNdx = findColumn("itemSpecification");
    receivePriceNdx = findColumn("receivePrice");
  }
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
  public String itemId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemIdNdx);
  }
  private int itemIdNdx;
  public String itemName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemNameNdx);
  }
  private int itemNameNdx;
  public String itemDimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemDimensionNdx);
  }
  private int itemDimensionNdx;
  public String itemSpecification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemSpecificationNdx);
  }
  private int itemSpecificationNdx;
  public Double receivePrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receivePriceNdx);
  }
  private int receivePriceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:287^145*/
	
	public List<Map> queryPItem(String formId)  
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:293^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    ROWNUM               rownumber,
//  			    d.ITEM_ID            itemId,
//  			    d.ITEM_NAME          itemName,
//  			    d.ITEM_DIMENSION     itemDimension,
//  			    d.ITEM_SPECIFICATION itemSpecification,
//  			    NVL(p.ITEM_PRICE,0)  receivePrice
//  			FROM
//  				:Com_("D_T1_PURCHASING_DETAIL") d 
//  			INNER JOIN
//  				:Com_("D_T1_PURCHASING_HEADER") h 
//  			ON
//  			    d.FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p 
//  			ON
//  			    d.ITEM_ID = p.ITEM_ID
//  			AND p.IS_CURRENT =1
//  			AND p.SUPPLIER_ID = h.PROVIDER_ID
//  			WHERE
//  			    d.FORM_ID = :formId
//      		 ORDER BY
//               DECODE(d.RECEIVER_ID,NULL,'',d.RECEIVER_ID) ,
//               DECODE(d.ITEM_ID,NULL,'',d.ITEM_ID)  
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasePriceAdjustBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:318^3*/
		
		List<Map> headerLst = new ArrayList<Map>();
		
		while(itemIter.next()) {
			Map header = new HashMap();
			
			header.put("rownumber", itemIter.rownumber());
			header.put("itemId", itemIter.itemId());
			header.put("itemName", itemIter.itemName());
			header.put("itemDimension", itemIter.itemDimension());
			header.put("itemSpecification", itemIter.itemSpecification());
			header.put("receivePrice", itemIter.receivePrice());
			
			headerLst.add(header);
		}
		
		itemIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	
	public List<Map> queryZItem(String formId)  
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:345^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT DISTINCT
//  			            d.ITEM_ID            itemId,
//  			            d.ITEM_NAME          itemName,
//  			            d.ITEM_DIMENSION     itemDimension,
//  			            d.ITEM_SPECIFICATION itemSpecification,
//  			            p.ITEM_PRICE         receivePrice
//  			        FROM
//  			            :Com_("D_T1_PURCHASING_MAPPING") m
//  			        INNER JOIN
//  			            :Com_("D_T1_PURCHASING_HEADER") h
//  			        ON
//  			            h.FORM_ID = m.SMALL_FORM_ID
//  			        INNER JOIN
//  			            :Com_("D_T1_PURCHASING_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.form_id
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            d.ITEM_ID = p.ITEM_ID
//  			        AND p.IS_CURRENT =1
//  			        AND p.SUPPLIER_ID = h.PROVIDER_ID
//  			        WHERE
//  			            m.BIG_FORM_ID = :formId
//  			        ORDER BY
//  			            1
//  			    )
//  			SELECT
//  			    ROWNUM rownumber,
//  			    a.*
//  			FROM
//  			    a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_MAPPING");
  String __sJT_2 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_3 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasePriceAdjustBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:380^9*/
		
		List<Map> headerLst = new ArrayList<Map>();
		
		while(itemIter.next()) {
			Map header = new HashMap();
			
			header.put("rownumber", itemIter.rownumber());
			header.put("itemId", itemIter.itemId());
			header.put("itemName", itemIter.itemName());
			header.put("itemDimension", itemIter.itemDimension());
			header.put("itemSpecification", itemIter.itemSpecification());
			header.put("receivePrice", itemIter.receivePrice());
			
			headerLst.add(header);
		}
		
		itemIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
}/*@lineinfo:generated-code*/class PurchasePriceAdjustBean_SJProfileKeys 
{
  private static PurchasePriceAdjustBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PurchasePriceAdjustBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PurchasePriceAdjustBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.hq.PurchasePriceAdjustBean_SJProfile0");
  }
}
