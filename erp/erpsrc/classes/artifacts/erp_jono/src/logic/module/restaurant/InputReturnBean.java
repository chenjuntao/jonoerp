/*@lineinfo:filename=InputReturnBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Sep 20 21:22:57 CST 2014 by lyz
 * Last edited on Sat Sep 20 21:22:57 CST 2014 by lyz
 * 
 * comment: 采购退货单查询
 */
package logic.module.restaurant;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ReturnGoodsDetail;
import pojo.form.ReturnGoodsHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class InputReturnBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(InputReturnBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

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
    input_departmentNdx = findColumn("input_department");
    inputerNdx = findColumn("inputer");
    storageNdx = findColumn("storage");
    storage_idNdx = findColumn("storage_id");
    input_timeNdx = findColumn("input_time");
    providerNdx = findColumn("provider");
    statusNdx = findColumn("status");
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
  public String input_department() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_departmentNdx);
  }
  private int input_departmentNdx;
  public String inputer() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(inputerNdx);
  }
  private int inputerNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public Date input_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timeNdx);
  }
  private int input_timeNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^56*/
	
	/*@lineinfo:generated-code*//*@lineinfo:42^2*/

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
    form_idNdx = findColumn("form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    item_idNdx = findColumn("item_id");
    return_countNdx = findColumn("return_count");
    return_pay_amtNdx = findColumn("return_pay_amt");
    return_reasonNdx = findColumn("return_reason");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_unit_priceNdx = findColumn("item_unit_price");
    receive_priceNdx = findColumn("receive_price");
    expired_timeNdx = findColumn("expired_time");
    receive_countNdx = findColumn("receive_count");
    order_countNdx = findColumn("order_count");
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
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double return_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_countNdx);
  }
  private int return_countNdx;
  public Double return_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_pay_amtNdx);
  }
  private int return_pay_amtNdx;
  public String return_reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(return_reasonNdx);
  }
  private int return_reasonNdx;
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double receive_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_priceNdx);
  }
  private int receive_priceNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:49^63*/
	
	/*@lineinfo:generated-code*//*@lineinfo:51^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DetailOutIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DetailOutIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    item_idNdx = findColumn("item_id");
    return_countNdx = findColumn("return_count");
    return_pay_amtNdx = findColumn("return_pay_amt");
    return_reasonNdx = findColumn("return_reason");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_unit_priceNdx = findColumn("item_unit_price");
    expired_timeNdx = findColumn("expired_time");
    shipping_countNdx = findColumn("shipping_count");
    request_countNdx = findColumn("request_count");
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
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double return_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_countNdx);
  }
  private int return_countNdx;
  public Double return_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_pay_amtNdx);
  }
  private int return_pay_amtNdx;
  public String return_reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(return_reasonNdx);
  }
  private int return_reasonNdx;
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
  public Double shipping_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(shipping_countNdx);
  }
  private int shipping_countNdx;
  public Double request_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(request_countNdx);
  }
  private int request_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:59^65*/

	/*@lineinfo:generated-code*//*@lineinfo:61^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class OutHeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public OutHeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
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
    requesterNdx = findColumn("requester");
    providerNdx = findColumn("provider");
    receive_timeNdx = findColumn("receive_time");
    request_addressNdx = findColumn("request_address");
    statusNdx = findColumn("status");
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
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
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
  public String request_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(request_addressNdx);
  }
  private int request_addressNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:64^59*/
	
	public int count(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,String branchType)
			throws NoPrivilegeException,SQLException,NoConnection {
		return count(startDate,endDate,branchId, "",queryType,branchType);
	}
	
	public int count(java.util.Date startDate, java.util.Date endDate, String branchId,String storageId, String queryType,String branchType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:79^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  		LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = rh.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T1_INPUT_HEADER") h
//  		ON
//  		    h.FORM_ID = rh.FORM_REF_ID
//  		LEFT JOIN
//  		    :Com_("d_t2_branch") b
//  		ON
//  		    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  		WHERE
//  		    :query
//  		AND b.BRANCH_TYPE = :branchType
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
  String __sJT_3 = Com_("D_T1_INPUT_HEADER");
  String __sJT_4 = Com_("d_t2_branch");
  String __sJT_5 = query;
  String __sJT_6 = branchType;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:107^26*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId, String queryType) {
		return formQuery(branchId, "", queryType);
	}
	
	private String formQuery(String branchId,String storageId, String queryType) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND h.INPUT_DEPARTMENT_ID = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(storageId)) { 
			query = query + " AND h.STORAGE_ID = '" + storageId + "'";
		}
		
		if ("audit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		
		
		return query;
	}
	
	public double sum(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return sum(startDate,endDate,branchId,"",queryType,branchType);
	}
	
	public double sum(java.util.Date startDate, java.util.Date endDate, String branchId, String storageId,String queryType,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		double allPayAmt = 0;
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:148^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    CASE
//  		        WHEN SUM(rh.ALL_PAY_AMT) IS NULL
//  		        THEN 0
//  		        ELSE SUM(rh.ALL_PAY_AMT)
//  		    END
//  		
//  		FROM
//  		    :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  		LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = rh.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T1_INPUT_HEADER") h
//  		ON
//  		    h.FORM_ID = rh.FORM_REF_ID
//  		LEFT JOIN
//  		    :Com_("d_t2_branch") b
//  		ON
//  		    h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  		WHERE
//  		    :query
//  		AND b.BRANCH_TYPE = :branchType
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
  String __sJT_3 = Com_("D_T1_INPUT_HEADER");
  String __sJT_4 = Com_("d_t2_branch");
  String __sJT_5 = query;
  String __sJT_6 = branchType;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:180^26*/
						closeDefaultContext(myCtx);
		return allPayAmt;
   }
	
	
	public List<ReturnGoodsHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId, String queryType,String branchType,
			int startRow, int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		return query(startDate,endDate,branchId,"",queryType,branchType,startRow,endRow);
	}
	
	public List<ReturnGoodsHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId,String storageId , String queryType,String branchType,
			int startRow, int endRow)
					throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:201^3*/

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
//  			                        rh.FORM_ID,
//  			                        rh.FORM_REF_ID,
//  			                        rh.RETURNER_ID,
//  			                        rh.RETURNER,
//  			                        rh.RETURN_TIME,
//  			                        rh.FORM_NOTE,
//  			                        CASE
//  			                            WHEN rh.ALL_PAY_AMT IS NULL
//  			                            THEN 0
//  			                            ELSE rh.ALL_PAY_AMT
//  			                        END ALL_PAY_AMT,
//  			                        rh.MAX_PAY_ITEM,
//  			                        h.PROVIDER,
//  			                        h.INPUT_DEPARTMENT,
//  			                        h.INPUTER,
//  			                        h.STORAGE,
//  			                        h.STORAGE_ID,
//  			                        h.INPUT_TIME,
//  			                        s.status
//  			                    FROM
//  			                        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			                    LEFT JOIN
//  			                        :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        s.FORM_ID = rh.FORM_ID
//  			                    AND s.IS_CURRENT = 1
//  			                    INNER JOIN
//  			                        :Com_("D_T1_INPUT_HEADER") h
//  			                    ON
//  			                        h.FORM_ID = rh.FORM_REF_ID
//  			                    LEFT JOIN
//  			                        :Com_("d_t2_branch") b
//  			                    ON
//  			                        h.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  			                    WHERE
//  			                        :query
//  			                    AND b.BRANCH_TYPE = :branchType
//  			                    AND (
//  			                            rh.RETURN_TIME >= :sDate
//  			                        OR  :sDate IS NULL)
//  			                    AND (
//  			                            rh.RETURN_TIME <= :eDate
//  			                        OR  :eDate IS NULL)
//  			                    ORDER BY
//  			                        rh.form_id) t
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
  String __sJT_3 = Com_("D_T1_INPUT_HEADER");
  String __sJT_4 = Com_("d_t2_branch");
  String __sJT_5 = query;
  String __sJT_6 = branchType;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  int __sJT_11 = endRow;
  int __sJT_12 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:260^34*/
		List<ReturnGoodsHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public int countOut(java.util.Date startDate, java.util.Date endDate, String formType, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if ("audit".equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		if ("process".equals(queryType)) { // 获取未处理记录
			query = query + " AND NVL( s.STATUS, 'null') = '未处理'"; 
		}
		
		if ("confirm".equals(queryType)) { // 获取未处理记录
			query = query + " AND NVL( s.STATUS, 'null') = '已处理'"; 
		}
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:285^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  		LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = rh.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T1_SHIPPING_HEADER") h
//  		ON
//  		    h.FORM_ID = rh.FORM_REF_ID
//  		AND h.form_type = :formType
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
  String __sJT_4 = formType;
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:309^26*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public List<ReturnGoodsHeader> queryOut(java.util.Date startDate, java.util.Date endDate, String formType , String queryType,
			int startRow, int endRow)throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if ("audit".equals(queryType)) { 
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'"; 
		}
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		OutHeaderIter outHeaderIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:326^3*/

//  ************************************************************
//  #sql [myCtx] outHeaderIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                    SELECT
//  			                        rh.FORM_ID,
//  			                        rh.FORM_REF_ID,
//  			                        rh.RETURNER_ID,
//  			                        rh.RETURNER,
//  			                        rh.RETURN_TIME,
//  			                        rh.FORM_NOTE,
//  			                        CASE
//  			                            WHEN rh.ALL_PAY_AMT IS NULL
//  			                            THEN 0
//  			                            ELSE rh.ALL_PAY_AMT
//  			                        END ALL_PAY_AMT,
//  			                        rh.MAX_PAY_ITEM,
//  			                        h.REQUESTER,
//  			                        h.PROVIDER,
//  			                        h.RECEIVE_TIME,
//  			                        h.REQUEST_ADDRESS,
//  			                        s.status
//  			                    FROM
//  			                        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			                    LEFT JOIN
//  			                        :Com_("D_T0_FORM_STATUS") s
//  			                    ON
//  			                        s.FORM_ID = rh.FORM_ID
//  			                    AND s.IS_CURRENT = 1
//  			                    INNER JOIN
//  			                        :Com_("D_T1_SHIPPING_HEADER") h
//  			                    ON
//  			                        h.FORM_ID = rh.FORM_REF_ID
//  			                    AND h.form_type = :formType
//  			                    WHERE
//  			                        :query
//  			                    AND (
//  			                            rh.RETURN_TIME >= :sDate
//  			                        OR  :sDate IS NULL)
//  			                    AND (
//  			                            rh.RETURN_TIME <= :eDate
//  			                        OR  :eDate IS NULL)
//  			                    ORDER BY
//  			                        rh.form_id) t
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
  String __sJT_4 = formType;
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 4);
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
      outHeaderIter = new OutHeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:379^34*/
		List<ReturnGoodsHeader> headerLst = processIterOut(outHeaderIter);
		outHeaderIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public ReturnGoodsHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:390^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    rh.*,
//  			    NVL(h.PROVIDER,sh.PROVIDER)          PROVIDER,
//  			    NVL(h.INPUT_DEPARTMENT,sh.REQUESTER) INPUT_DEPARTMENT,
//  			    NVL( h.INPUTER,sh.INPUTER)           INPUTER,
//  			    NVL( h.STORAGE,sh.IN_STORAGE)        STORAGE,
//  			    NVL( h.STORAGE_ID,sh.IN_STORAGE_ID)  STORAGE_ID,
//  			    NVL(h.INPUT_TIME,sh.RECEIVE_TIME)    INPUT_TIME,
//  			    s.status
//  			FROM
//  			:Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = rh.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			LEFT JOIN
//  			:Com_("D_T1_INPUT_HEADER") h
//  			ON
//  			    h.FORM_ID = rh.FORM_REF_ID
//      		LEFT JOIN
//  			:Com_("D_T1_SHIPPING_HEADER") sh
//  			ON
//  		    sh.FORM_ID = rh.FORM_REF_ID
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
  String __sJT_3 = Com_("D_T1_INPUT_HEADER");
  String __sJT_4 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:417^3*/
		List<ReturnGoodsHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	public ReturnGoodsHeader queryOutById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		OutHeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:431^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    rh.*,
//  				h.REQUESTER,
//  				h.PROVIDER,
//  				h.RECEIVE_TIME,
//  				h.REQUEST_ADDRESS,
//  				s.status
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
//  			h.FORM_ID = rh.FORM_REF_ID
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new OutHeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:452^3*/
		List<ReturnGoodsHeader> headerLst = processIterOut(headerIter);
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
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());

			header.setProvider(headerIter.provider());
			header.setStorage(headerIter.storage());
			header.setStorageId(headerIter.storage_id());
			header.setInputDepartment(headerIter.input_department());
			header.setInputer(headerIter.inputer());
			header.setInputTime(SqlDateUtil.getUtilDate(headerIter.input_time()));
			
			header.setStatus(headerIter.status());
			
			headerLst.add(header);
		}
		return headerLst;
	}

	private List<ReturnGoodsHeader> processIterOut(OutHeaderIter headerIter) 
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
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			header.setRequester(headerIter.requester());
			header.setProvider(headerIter.provider());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setRequestAddress(headerIter.request_address());
			header.setStatus(headerIter.status());
			headerLst.add(header);
		}
		return headerLst;
	}
	
	public List<ReturnGoodsDetail> queryDetail(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:516^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    rd.*,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.ITEM_UNIT_PRICE,
//  			    d.RECEIVE_PRICE,
//  			    d.EXPIRED_TIME,
//  			    d.RECEIVE_COUNT,
//  			    d.ORDER_COUNT
//  			FROM
//  			:Com_("D_T1_RETURN_GOODS_DETAIL") rd,
//  			:Com_("D_T1_INPUT_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = rd.FORM_REF_ID
//  			AND d.item_id = rd.ITEM_ID
//  			AND rd.FORM_ID = :formId
//  			ORDER BY
//  				rd.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:537^3*/
		List<ReturnGoodsDetail> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	public List<ReturnGoodsDetail> queryOutDetail(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailOutIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:548^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    rd.*,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    d.ITEM_UNIT_PRICE,
//  			    d.EXPIRED_TIME,
//  			    d.REQUEST_COUNT,
//  			    d.SHIPPING_COUNT
//  			FROM
//  			:Com_("D_T1_RETURN_GOODS_DETAIL") rd,
//  			:Com_("D_T1_SHIPPING_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = rd.FORM_REF_ID
//  			AND d.item_id = rd.ITEM_ID
//  			AND rd.FORM_ID = :formId
//  			ORDER BY
//  				rd.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_2 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputReturnBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new DetailOutIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:568^3*/
		List<ReturnGoodsDetail> detailLst = processOutDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	private List<ReturnGoodsDetail> processOutDetailIter(DetailOutIter detailIter) 
			throws SQLException {
		List<ReturnGoodsDetail> detailLst = new ArrayList<ReturnGoodsDetail>();
		while(detailIter.next()) {
			ReturnGoodsDetail detail = new ReturnGoodsDetail();
			detail.setFormId(detailIter.form_id());
			detail.setFormRefId(detailIter.form_ref_id());
			detail.setItemId(detailIter.item_id());
			detail.setReturnCount(detailIter.return_count());
			detail.setReturnPayAmt(detailIter.return_pay_amt());
			detail.setReturnReason(detailIter.return_reason());

			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setRequestCount(detailIter.request_count());
			detail.setShippingCount(detailIter.shipping_count());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	private List<ReturnGoodsDetail> processDetailIter(DetailIter detailIter) 
			throws SQLException {
		List<ReturnGoodsDetail> detailLst = new ArrayList<ReturnGoodsDetail>();
		while(detailIter.next()) {
			ReturnGoodsDetail detail = new ReturnGoodsDetail();
			detail.setFormId(detailIter.form_id());
			detail.setFormRefId(detailIter.form_ref_id());
			detail.setItemId(detailIter.item_id());
			detail.setReturnCount(detailIter.return_count());
			detail.setReturnPayAmt(detailIter.return_pay_amt());
			detail.setReturnReason(detailIter.return_reason());

			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setReceivePrice(detailIter.receive_price());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setReceiveCount(detailIter.receive_count());
			detail.setOrderCount(detailIter.order_count());
			
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class InputReturnBean_SJProfileKeys 
{
  private static InputReturnBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new InputReturnBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private InputReturnBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.InputReturnBean_SJProfile0");
  }
}
