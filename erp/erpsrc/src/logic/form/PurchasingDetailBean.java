/*@lineinfo:filename=PurchasingDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 26, 2014 by liyzh
 * Last edited on Aug 26, 2014 by liyzh
 * 
 * 说明： 物流中心采购单、中央工厂采购单明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PurchasingDetail;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchCode;

public class PurchasingDetailBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(PurchasingDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

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
    rownumberNdx = findColumn("rownumber");
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    receiver_idNdx = findColumn("receiver_id");
    receiverNdx = findColumn("receiver");
    item_countNdx = findColumn("item_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    pay_amtNdx = findColumn("pay_amt");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    expired_timeNdx = findColumn("expired_time");
    supply_cycleNdx = findColumn("supply_cycle");
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
  public String receiver_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receiver_idNdx);
  }
  private int receiver_idNdx;
  public String receiver() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receiverNdx);
  }
  private int receiverNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double receive_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_priceNdx);
  }
  private int receive_priceNdx;
  public Double receive_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtNdx);
  }
  private int receive_amtNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
  public Double supply_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(supply_cycleNdx);
  }
  private int supply_cycleNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:53^23*/
	
	/*@lineinfo:generated-code*//*@lineinfo:55^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class FormIdsIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public FormIdsIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:55^50*/

	public List<PurchasingDetail> query(String formId)throws NoPrivilegeException,SQLException,NoConnection{
		return query(formId,null);
	}
	
	public List<PurchasingDetail> query(String formId,String hasSum)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:65^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    DECODE(FORM_ID,NULL,'合计',ROWNUM)rownumber ,
//  			    t.*,
//  	            0.0 SUPPLY_CYCLE
//  			FROM
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            c.CATEGORY_NAME ITEM_CATEGORY,
//  			            sum(d.ITEM_COUNT) ITEM_COUNT,
//  			            d.ITEM_UNIT_PRICE,
//  			            sum(d.PAY_AMT) PAY_AMT,
//  			            d.RECEIVE_PRICE,
//  			            sum(d.RECEIVE_AMT)RECEIVE_AMT,
//  			            d.RECEIVER_ID,
//  			            d.RECEIVER,
//  			            d.EXPIRED_TIME
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_DETAIL") d
//  			        LEFT JOIN
//  			        :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            d.ITEM_CATEGORY = c.CATEGORY_ID
//  			        WHERE
//  			            d.FORM_ID = :formId
//  			        GROUP BY
//  			            GROUPING SETS( ( d.FORM_ID, d.ITEM_ID,d.ITEM_NAME,d.ITEM_DIMENSION,d.ITEM_SPECIFICATION,
//  			            c.CATEGORY_NAME,d.ITEM_COUNT, d.ITEM_UNIT_PRICE , d.PAY_AMT, d.RECEIVE_PRICE,
//  			            d.RECEIVE_AMT,d.RECEIVER_ID,d.RECEIVER,d.EXPIRED_TIME),NULL)
//  			        ORDER BY
//  			            DECODE(RECEIVER_ID,NULL,'',RECEIVER_ID)  ,DECODE(ITEM_ID,NULL,'',ITEM_ID) ) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:100^93*/
		List<PurchasingDetail> detailLst = processIter(detailIter);
		
		if (TextUtil.isEmpty(hasSum)) {
			detailLst.remove(detailLst.size()-1);
		}
		
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<PurchasingDetail> query(String formId,String lcCode,String hasSum)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:116^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    DECODE(FORM_ID,NULL,'合计',ROWNUM)rownumber ,
//  			    t.*
//  			FROM
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            c.CATEGORY_NAME ITEM_CATEGORY,
//  			            sum(d.ITEM_COUNT) ITEM_COUNT,
//  			            d.ITEM_UNIT_PRICE,
//  			            sum(d.PAY_AMT) PAY_AMT,
//  			            d.RECEIVE_PRICE,
//  			            sum(d.RECEIVE_AMT)RECEIVE_AMT,
//  			            d.RECEIVER_ID,
//  			            d.RECEIVER,
//  			            d.EXPIRED_TIME,
//  			            DECODE (ma.SUPPLY_CYCLE, NULL, ma.PRODUCTION_CYCLE,ma.SUPPLY_CYCLE) SUPPLY_CYCLE
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_DETAIL") d
//  			        LEFT JOIN
//  			        :Com_("D_V_MRP_AMOUNT_LC") ma
//  			        ON
//  			            ma.ITEM_ID = d.ITEM_ID
//  			        AND ma.BRANCH_ID = :lcCode
//  			        LEFT JOIN
//  			        :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            d.ITEM_CATEGORY = c.CATEGORY_ID
//  			        WHERE
//  			            d.FORM_ID = :formId
//  			        GROUP BY
//  			            GROUPING SETS( ( d.FORM_ID, d.ITEM_ID,d.ITEM_NAME,d.ITEM_DIMENSION,d.ITEM_SPECIFICATION,
//  			            c.CATEGORY_NAME,d.ITEM_COUNT, d.ITEM_UNIT_PRICE , d.PAY_AMT, d.RECEIVE_PRICE,
//  			            d.RECEIVE_AMT,d.RECEIVER_ID,d.RECEIVER,d.EXPIRED_TIME,ma.SUPPLY_CYCLE,ma.PRODUCTION_CYCLE),NULL)
//  			        ORDER BY
//  			            DECODE(RECEIVER_ID,NULL,'',RECEIVER_ID)  ,DECODE(ITEM_ID,NULL,'',ITEM_ID) ) t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = Com_("D_V_MRP_AMOUNT_LC");
  String __sJT_3 = lcCode;
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:156^93*/
		List<PurchasingDetail> detailLst = processIter(detailIter);
		
		if (TextUtil.isEmpty(hasSum)) {
			detailLst.remove(detailLst.size()-1);
		}
		
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	public List<PurchasingDetail> queryByBranch(String formId, String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:171^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.*,
//  			    '' rownumber,
//  	            0.0 SUPPLY_CYCLE
//  			FROM
//  			:Com_("D_T1_PURCHASING_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = :formId
//  			AND d.RECEIVER_ID = :branchId
//  			ORDER BY
//  				d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:183^3*/
		List<PurchasingDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<PurchasingDetail> queryByItemId(String itemId ,java.util.Date startDate, java.util.Date endDate)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		Date _startDate = SqlDateUtil.getSqlDate(startDate);
		Date _endDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:199^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.*,
//  			    '' rownumber,
//  	            0.0 SUPPLY_CYCLE
//  			    FROM
//  			    :Com_("D_T1_PURCHASING_DETAIL") d
//  		        LEFT JOIN
//  		        :Com_("D_T1_PURCHASING_HEADER") h
//  			    ON
//  			        d.FORM_ID = h.FORM_ID
//  			    LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			    ON
//  			        d.FORM_ID = s.FORM_ID
//  			    WHERE
//  			        d.ITEM_ID = :itemId
//  			    AND (
//  			            FORM_TIME >= :_startDate
//  			        OR  :_startDate IS NULL)
//  			    AND (
//  			            FORM_TIME <= :_endDate
//  			        OR  :_endDate IS NULL)
//  			    AND s.STATUS = '已审核'
//  			    AND IS_CURRENT = '1'
//  			    ORDER BY
//  			        d.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = itemId;
  Date __sJT_5 = _startDate;
  Date __sJT_6 = _startDate;
  Date __sJT_7 = _endDate;
  Date __sJT_8 = _endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 3);
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
      detailIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:225^21*/
		List<PurchasingDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<String> queryFormIds(String lcCode, String cfCode, java.util.Date startDate, java.util.Date endDate)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date _startDate = SqlDateUtil.getSqlDate(startDate);
		Date _endDate = SqlDateUtil.getSqlDate(endDate);
		
		FormIdsIter formIdsIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:240^3*/

//  ************************************************************
//  #sql [myCtx] formIdsIter = { SELECT 
//  				DISTINCT d.FORM_ID
//  		    FROM
//  		    :Com_("D_T1_PURCHASING_DETAIL") d
//  		    LEFT JOIN
//  		    :Com_("D_T1_PURCHASING_HEADER") h
//  		    ON
//  		        d.FORM_ID = h.FORM_ID
//  		    LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		    ON
//  		        d.FORM_ID = s.FORM_ID
//  		    WHERE
//  		    	h.REQUESTER_ID = :lcCode
//  		    AND h.PROVIDER_ID = :cfCode
//  		    AND
//  		        (
//  		            FORM_TIME >= :_startDate
//  		        OR  :_startDate IS NULL)
//  		    AND (
//  		            FORM_TIME <= :_endDate
//  		        OR  :_endDate IS NULL)
//  		    AND s.STATUS = '已审核'  AND IS_CURRENT = '1'
//  		    ORDER BY
//  		        d.FORM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = lcCode;
  String __sJT_5 = cfCode;
  Date __sJT_6 = _startDate;
  Date __sJT_7 = _startDate;
  Date __sJT_8 = _endDate;
  Date __sJT_9 = _endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 4);
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
      formIdsIter = new FormIdsIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:266^3*/
		
		List<String> formIdLists = new ArrayList<String>();
		while(formIdsIter.next()) {
			formIdLists.add(formIdsIter.form_id());
		}
		formIdsIter.close();
		closeDefaultContext(myCtx);
		
		return formIdLists;
	}
	
	//单据状态表中已汇总的单据就不能被查询出来
	public List<PurchasingDetail> queryAllSummary(String lcCode, String cfCode, java.util.Date startDate, java.util.Date endDate)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date _startDate = SqlDateUtil.getSqlDate(startDate);
		Date _endDate = SqlDateUtil.getSqlDate(endDate);
		
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:287^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  				'' rownumber,
//  			    d.ITEM_ID,
//  			    d.ITEM_ID FORM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    c.CATEGORY_NAME    ITEM_CATEGORY,
//  			    SUM( d.ITEM_COUNT) ITEM_COUNT,
//  			    d.ITEM_UNIT_PRICE,
//  			    SUM(d.PAY_AMT) PAY_AMT,
//  			    d.RECEIVER_PRICE,
//  			    SUM(d.RECEIVE_AMT) RECEIVE_AMT,
//  			    d.EXPIRED_TIME,
//  			    d.RECEIVER,
//  			    d.RECEIVER_ID,
//  	            0.0 SUPPLY_CYCLE
//  			FROM
//  			:Com_("D_T1_PURCHASING_DETAIL") d
//  			LEFT JOIN
//  			:Com_("D_T1_PURCHASING_HEADER") h
//  			ON
//  			    d.FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    d.FORM_ID = s.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    d.ITEM_CATEGORY = c.CATEGORY_ID
//  			WHERE
//  			    h.REQUESTER_ID = :lcCode
//  			AND h.PROVIDER_ID = :cfCode
//  			AND (
//  					AUDIT_TIME >= :_startDate
//  			    OR  :_startDate IS NULL)
//  			AND (
//  					AUDIT_TIME <= :_endDate
//  			    OR  :_endDate IS NULL)
//  			AND s.STATUS = '已审核'
//  			AND IS_CURRENT = '1'
//  			GROUP BY
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    c.CATEGORY_NAME ,
//  			    d.ITEM_UNIT_PRICE,
//  			    d.RECEIVE_PRICE,
//  			    d.EXPIRED_TIME,
//  			    d.RECEIVER,
//  			    d.RECEIVER_ID
//  			ORDER BY
//  			    d.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = lcCode;
  String __sJT_6 = cfCode;
  Date __sJT_7 = _startDate;
  Date __sJT_8 = _startDate;
  Date __sJT_9 = _endDate;
  Date __sJT_10 = _endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:342^17*/
		List<PurchasingDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<PurchasingDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<PurchasingDetail> detailLst = new ArrayList<PurchasingDetail>();
		while(detailIter.next()) {
			PurchasingDetail detail = new PurchasingDetail();
			detail.setRownumber(detailIter.rownumber());
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setReceiverId(detailIter.receiver_id());
			detail.setReceiver(detailIter.receiver());
			detail.setItemCount(detailIter.item_count());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setPayAmt(detailIter.pay_amt());
			detail.setReceivePrice(detailIter.receive_price());
			detail.setReceiveAmt(detailIter.receive_amt());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setSupplyCycle(detailIter.supply_cycle());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	/**
	 * 保存明细
	 */
	public int saveEntity(PurchasingDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		String itemCategory = detail.getItemCategory();
		String receiverId = detail.getReceiverId();
		String receiver = detail.getReceiver();
		Double itemCount = detail.getItemCount();
		Double itemUnitPrice = detail.getItemUnitPrice();
		Double payAmt = detail.getPayAmt();
		Double receivePrice = detail.getReceivePrice();
		Double receiveAmt = detail.getReceiveAmt();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());
	
		/*@lineinfo:generated-code*//*@lineinfo:397^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_PURCHASING_DETAIL") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, 
//  					ITEM_SPECIFICATION, ITEM_CATEGORY, ITEM_COUNT, 
//  					RECEIVER_ID, RECEIVER, ITEM_UNIT_PRICE, PAY_AMT,RECEIVE_PRICE,RECEIVE_AMT, EXPIRED_TIME)
//  			VALUES (:formId, 
//  					:itemId, 
//  					:itemName, 
//  					:itemDimension, 
//  					:itemSpecification, 
//  					:itemCategory, 
//  					:itemCount, :receiverId, :receiver,
//  					:itemUnitPrice, 
//  					:payAmt,
//  					:receivePrice,
//  					:receiveAmt,
//  					:expiredTime)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  String __sJT_7 = itemCategory;
  Double __sJT_8 = itemCount;
  String __sJT_9 = receiverId;
  String __sJT_10 = receiver;
  Double __sJT_11 = itemUnitPrice;
  Double __sJT_12 = payAmt;
  Double __sJT_13 = receivePrice;
  Double __sJT_14 = receiveAmt;
  Date __sJT_15 = expiredTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setDoubleWrapper(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
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

	public int updateEntity(PurchasingDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String receiverId = detail.getReceiverId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		String itemCategory = detail.getItemCategory();
		Double itemCount = detail.getItemCount();
		Double itemUnitPrice = detail.getItemUnitPrice();
		Double payAmt = detail.getPayAmt();
		Double receivePrice = detail.getReceivePrice();
		Double receiveAmt = detail.getReceiveAmt();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());
		
		String query = " 1=1";
		if (!TextUtil.isEmpty(receiverId)) {
			query = query + " AND RECEIVER_ID = '" + receiverId + "'";
		}
		
		/*@lineinfo:generated-code*//*@lineinfo:443^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PURCHASING_DETAIL")
//  			SET
//  			    ITEM_COUNT = :itemCount,
//  			    PAY_AMT = :payAmt,
//  			    RECEIVE_AMT=:receiveAmt
//  			WHERE
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  			AND :query 
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  Double __sJT_2 = itemCount;
  Double __sJT_3 = payAmt;
  Double __sJT_4 = receiveAmt;
  String __sJT_5 = formId;
  String __sJT_6 = itemId;
  String __sJT_7 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:454^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:464^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PURCHASING_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:470^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
}/*@lineinfo:generated-code*/class PurchasingDetailBean_SJProfileKeys 
{
  private static PurchasingDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PurchasingDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PurchasingDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.PurchasingDetailBean_SJProfile0");
  }
}
