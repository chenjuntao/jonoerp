/*@lineinfo:filename=RequestPurchaseBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 3, 2014 by liyzh
 * Last edited on Nov 3, 2014 by liyzh
 * 
 * 说明： 餐厅要货、外部订货商订货单直接转化为采购单
 */
package logic.module.lc;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.CollectDetail;
import pojo.form.RequestHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class RequestPurchaseBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequestPurchaseBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_unit_priceNdx = findColumn("item_unit_price");
    order_countNdx = findColumn("order_count");
    pay_amtNdx = findColumn("pay_amt");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
  }
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^148*/
	
	/*@lineinfo:generated-code*//*@lineinfo:42^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ProviderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ProviderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
  }
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^72*/
	
	public List<Map> queryProvider(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ProviderIter providerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:48^3*/

//  ************************************************************
//  #sql [myCtx] providerIter = { SELECT
//  			    s.BRANCH_ID as PROVIDER_ID,
//  			    s.BRANCH_NAME as PROVIDER
//  			FROM
//  				:Com_("D_T1_REQUEST_DETAIL") d,
//  				:Com_("D_T2_BRANCH") s,
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") si
//  			WHERE
//  		    	d.FORM_ID = :formId
//  		    AND d.ITEM_ID = si.ITEM_ID
//  			AND si.SUPPLIER_ID = s.BRANCH_ID
//  			AND si.PRIORITY = 0
//  			GROUP BY
//  			    s.BRANCH_ID,
//  			    s.BRANCH_NAME
//  			ORDER BY
//  				s.BRANCH_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestPurchaseBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      providerIter = new ProviderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:66^3*/
		List<Map> providerLst = new ArrayList<Map>();
		while(providerIter.next()) {
			Map<String, String> provider = new HashMap<String, String>();
			provider.put("providerId", providerIter.provider_id());
			provider.put("provider", providerIter.provider());
			providerLst.add(provider);
		}
		providerIter.close();
		closeDefaultContext(myCtx);
		return providerLst;
	}

	public List<CollectDetail> query(String formId, String providerId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:83^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  				d.FORM_ID,
//  				d.ITEM_ID,
//  				d.ITEM_NAME,
//  				d.ITEM_CATEGORY,
//  				d.ITEM_DIMENSION,
//  				d.ITEM_SPECIFICATION,
//  				d.ORDER_COUNT,
//  				d.ITEM_UNIT_PRICE,
//  				d.PAY_AMT,
//  			    s.BRANCH_ID as PROVIDER_ID,
//  			    s.BRANCH_NAME as PROVIDER
//  			FROM
//  				:Com_("D_T1_REQUEST_DETAIL") d,
//  		    	:Com_("D_T2_BRANCH") s,
//  			    :Com_("D_T2_SUPPLIER_BRANCH_ITEM") si
//  			WHERE
//  		    	d.FORM_ID = :formId
//  		    AND si.SUPPLIER_ID = :providerId
//  		    AND d.ITEM_ID = si.ITEM_ID
//  			AND si.SUPPLIER_ID = s.BRANCH_ID
//  			AND si.PRIORITY = 0
//  			ORDER BY
//  				d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_4 = formId;
  String __sJT_5 = providerId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestPurchaseBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:108^3*/
		List<CollectDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<CollectDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:119^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  				d.FORM_ID,
//  				d.ITEM_ID,
//  				d.ITEM_NAME,
//  				d.ITEM_CATEGORY,
//  				d.ITEM_DIMENSION,
//  				d.ITEM_SPECIFICATION,
//  				d.ORDER_COUNT,
//  				d.ITEM_UNIT_PRICE,
//  				d.PAY_AMT,
//  			    s.BRANCH_ID as PROVIDER_ID,
//  			    s.BRANCH_NAME as PROVIDER
//  			FROM
//  				:Com_("D_T1_REQUEST_DETAIL") d,
//  		    	:Com_("D_T2_BRANCH") s,
//  			    :Com_("D_T2_SUPPLIER_BRANCH_ITEM") si
//  			WHERE
//  		    	d.FORM_ID = :formId
//  		    AND d.ITEM_ID = si.ITEM_ID
//  			AND si.SUPPLIER_ID = s.BRANCH_ID
//  			AND si.PRIORITY = 0
//  			ORDER BY
//  				s.BRANCH_ID, d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_4 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestPurchaseBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:143^3*/
		List<CollectDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<CollectDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<CollectDetail> detailLst = new ArrayList<CollectDetail>();
		while(detailIter.next()) {
			CollectDetail detail = new CollectDetail();
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemCategory(detailIter.item_category());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCount(detailIter.order_count());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setPayAmt(detailIter.pay_amt());
			detail.setProviderId(detailIter.provider_id());
			detail.setProvider(detailIter.provider());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class RequestPurchaseBean_SJProfileKeys 
{
  private static RequestPurchaseBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestPurchaseBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestPurchaseBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.RequestPurchaseBean_SJProfile0");
  }
}
