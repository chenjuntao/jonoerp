/*@lineinfo:filename=StatementDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Mar 13 15:24:46 CST 2015 by pw
 * Last edited on Fri Mar 13 15:24:46 CST 2015 by pw
 * 
 * comment: 对账单明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.StatementDetail;
import com.tanry.framework.util.DateTimeUtil;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class StatementDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StatementDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

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
    statusNdx = findColumn("status");
    form_idNdx = findColumn("form_id");
    sub_form_idNdx = findColumn("sub_form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    all_pay_amtNdx = findColumn("all_pay_amt");
    form_operate_idNdx = findColumn("form_operate_id");
    form_operateNdx = findColumn("form_operate");
    form_operate_timeNdx = findColumn("form_operate_time");
    form_noteNdx = findColumn("form_note");
  }
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String sub_form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sub_form_idNdx);
  }
  private int sub_form_idNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public String form_operate_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_operate_idNdx);
  }
  private int form_operate_idNdx;
  public String form_operate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_operateNdx);
  }
  private int form_operateNdx;
  public Date form_operate_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_operate_timeNdx);
  }
  private int form_operate_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^210*/
	
	public int saveEntity(StatementDetail detail)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();
			
			String formId = detail.getFormId();
			String subFormId = detail.getSubFormId();
			String formRefId = detail.getFormRefId();
			Double allPayAmt = detail.getAllPayAmt();
			String formOperateId = detail.getFormOperateId();
			String formOperate = detail.getFormOperate();
			Date formOperateTime = SqlDateUtil.getSqlDate(detail.getFormOperateTime());
			String formNote = detail.getFormNote();

			/*@lineinfo:generated-code*//*@lineinfo:53^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_STATEMENT_DETAIL") 
//  					(FORM_ID, SUB_FORM_ID, FORM_REF_ID, ALL_PAY_AMT, FORM_OPERATE_ID, FORM_OPERATE, FORM_OPERATE_TIME, FORM_NOTE) 
//  				VALUES (:formId, :subFormId, :formRefId, :allPayAmt, :formOperateId, :formOperate, :formOperateTime, :formNote)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = subFormId;
  String __sJT_4 = formRefId;
  Double __sJT_5 = allPayAmt;
  String __sJT_6 = formOperateId;
  String __sJT_7 = formOperate;
  Date __sJT_8 = formOperateTime;
  String __sJT_9 = formNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:57^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_STATEMENT_DETAIL")
//  			WHERE
//  				SUB_FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:73^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<StatementDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:83^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.*,
//  			    s.STATUS
//  			FROM
//  			:Com_("D_T1_STATEMENT_DETAIL") d
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    d.SUB_FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 2
//  			WHERE
//  			    d.FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:95^4*/
		List<StatementDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public Double sumByFormId(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		Double total = 0.0;
		/*@lineinfo:generated-code*//*@lineinfo:107^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    SUM(d.ALL_PAY_AMT)
//  		
//  		FROM
//  		:Com_("D_T1_STATEMENT_DETAIL") d
//  		WHERE
//  		    d.FORM_ID = :formId };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementDetailBean_SJProfileKeys.getKey(0), 3);
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
    total = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:115^26*/
		closeDefaultContext(myCtx);
		return total;
	}

	private List<StatementDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<StatementDetail> detailLst = new ArrayList<StatementDetail>();
		while(detailIter.next()) {
			StatementDetail detail = new StatementDetail();
			detail.setFormId(detailIter.form_id());
			detail.setSubFormId(detailIter.sub_form_id());
			detail.setFormRefId(detailIter.form_ref_id());
			detail.setAllPayAmt(detailIter.all_pay_amt());
			detail.setFormOperateId(detailIter.form_operate_id());
			detail.setFormOperate(detailIter.form_operate());
			detail.setFormOperateTime(SqlDateUtil.getUtilDate(detailIter.form_operate_time()));
			detail.setFormNote(detailIter.form_note());
			detail.setStatus(detailIter.status());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	
	/*@lineinfo:generated-code*//*@lineinfo:140^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ExportIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ExportIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    AUDIT_TIMENdx = findColumn("AUDIT_TIME");
    FORM_IDNdx = findColumn("FORM_ID");
    ITEM_IDNdx = findColumn("ITEM_ID");
    ITEM_NAMENdx = findColumn("ITEM_NAME");
    ITEM_DIMENSIONNdx = findColumn("ITEM_DIMENSION");
    ITEM_COUNTNdx = findColumn("ITEM_COUNT");
    PRICENdx = findColumn("PRICE");
    AMTNdx = findColumn("AMT");
  }
  public Date AUDIT_TIME() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(AUDIT_TIMENdx);
  }
  private int AUDIT_TIMENdx;
  public String FORM_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(FORM_IDNdx);
  }
  private int FORM_IDNdx;
  public String ITEM_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_IDNdx);
  }
  private int ITEM_IDNdx;
  public String ITEM_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_NAMENdx);
  }
  private int ITEM_NAMENdx;
  public String ITEM_DIMENSION() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_DIMENSIONNdx);
  }
  private int ITEM_DIMENSIONNdx;
  public Double ITEM_COUNT() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(ITEM_COUNTNdx);
  }
  private int ITEM_COUNTNdx;
  public Double PRICE() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(PRICENdx);
  }
  private int PRICENdx;
  public Double AMT() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(AMTNdx);
  }
  private int AMTNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:149^4*/
	
	public List<Map> exportQuery(String formIds,String storageId,String sppId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " A.FORM_ID IN ('" + formIds.replaceAll(",", "','") + "') ";
		DefaultContext myCtx = getDefaultContext();
		ExportIter iter = null;
		/*@lineinfo:generated-code*//*@lineinfo:156^3*/

//  ************************************************************
//  #sql [myCtx] iter = { WITH 
//  					A AS(
//  						SELECT
//  							RH.RETURN_TIME AUDIT_TIME,
//  							RH.FORM_ID FORM_ID,
//  							RD.ITEM_ID ITEM_ID,
//  							RD.RETURN_COUNT ITEM_COUNT,
//  							ID1.RECEIVE_PRICE PRICE,
//  							ID1.RECEIVE_AMT AMT 
//  						FROM 
//  							D_T1_RETURN_GOODS_HEADER RH 
//  						LEFT JOIN 
//  							D_T1_RETURN_GOODS_DETAIL RD 
//  						ON 
//  							RH.FORM_ID = RD.FORM_ID 
//  						INNER JOIN 
//  							D_T1_INPUT_DETAIL ID1 
//  						ON 
//  							RH.FORM_REF_ID = ID1.FORM_ID
//  						AND 
//  							RD.ITEM_ID = ID1.ITEM_ID
//  					UNION ALL
//  						SELECT 
//  							RH.RETURN_TIME AUDIT_TIME,
//  							RH.FORM_ID FORM_ID,
//  							RD.ITEM_ID ITEM_ID,
//  							RD.RETURN_COUNT ITEM_COUNT,
//  							SD1.ITEM_UNIT_PRICE PRICE,
//  							SD1.PAY_AMT AMT 
//  						FROM 
//  							D_T1_RETURN_GOODS_HEADER RH 
//  						LEFT JOIN 
//  							D_T1_RETURN_GOODS_DETAIL RD 
//  						ON 
//  							RH.FORM_ID = RD.FORM_ID 
//  						INNER JOIN 
//  							D_T1_SHIPPING_DETAIL SD1 
//  						ON 
//  							RH.FORM_REF_ID = SD1.FORM_ID
//  						AND 
//  							RD.ITEM_ID = SD1.ITEM_ID
//  					UNION ALL
//  						SELECT 
//  							SH.FORM_TIME_ACTUAL AUDIT_TIME,
//  							SH.FORM_ID FORM_ID,
//  							SD.ITEM_ID ITEM_ID,
//  							SD.RECEIVE_COUNT ITEM_COUNT,
//  							SD.ITEM_UNIT_PRICE PRICE,
//  							SD.PAY_AMT AMT 
//  						FROM
//  							D_T1_SHIPPING_HEADER SH 
//  						LEFT JOIN 
//  							D_T1_SHIPPING_DETAIL SD 
//  						ON 
//  							SH.FORM_ID = SD.FORM_ID
//  					UNION ALL
//  						SELECT 
//  							IH.AUDIT_TIME AUDIT_TIME,
//  							IH.FORM_ID FORM_ID,
//  							ID.ITEM_ID ITEM_ID,
//  							ID.RECEIVE_COUNT ITEM_COUNT,
//  							ID.RECEIVE_PRICE PRICE,
//  							ID.RECEIVE_AMT AMT 
//  						FROM 
//  							D_T1_INPUT_HEADER IH 
//  						LEFT JOIN 
//  							D_T1_INPUT_DETAIL ID 
//  						ON 
//  							IH.FORM_ID = ID.FORM_ID
//  					),
//  					B AS
//  					(
//  						SELECT 
//  							M.ITEM_ID,M.ITEM_NAME,M.ITEM_DIMENSION 
//  						FROM 
//  							D_T2_ITEM_META M
//  					)
//  				SELECT 
//  					A.AUDIT_TIME,
//  					A.FORM_ID,
//  					A.ITEM_ID,
//  					B.ITEM_NAME,
//  					B.ITEM_DIMENSION,
//  					A.ITEM_COUNT,
//  					A.PRICE,
//  					A.AMT
//  				FROM 
//  					A 
//  				LEFT JOIN 
//  					B 
//  				ON 
//  					A.ITEM_ID = B.ITEM_ID 
//  				WHERE 
//  					:query
//  				ORDER BY 
//  					A.FORM_ID,A.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementDetailBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new ExportIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:253^3*/
		List<Map> detailLst = new ArrayList();
		while(iter.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("AUDIT_TIME", DateTimeUtil.getDateStr(SqlDateUtil.getUtilDate(iter.AUDIT_TIME())));
			map.put("FORM_ID",iter.FORM_ID());
			map.put("ITEM_ID",iter.ITEM_ID());
			map.put("ITEM_NAME",iter.ITEM_NAME());
			map.put("ITEM_DIMENSION",iter.ITEM_DIMENSION());
			map.put("ITEM_COUNT",iter.ITEM_COUNT());
			map.put("PRICE",iter.PRICE());
			map.put("STORAGE",storageId);
			map.put("SUPPLIER",sppId);
			map.put("AMT",iter.AMT());
			detailLst.add(map);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class StatementDetailBean_SJProfileKeys 
{
  private static StatementDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StatementDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StatementDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.StatementDetailBean_SJProfile0");
  }
}
