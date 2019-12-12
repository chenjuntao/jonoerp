/*@lineinfo:filename=CrossDistributionBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 7, 2015 by liyzh
 * Last edited on Apr 7, 2015 by liyzh
 * 
 * 说明： 物流中心生成越库配送单
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

public class CrossDistributionBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(CrossDistributionBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

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
    receiver_idNdx = findColumn("receiver_id");
    receiverNdx = findColumn("receiver");
    branch_addressNdx = findColumn("branch_address");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_unit_priceNdx = findColumn("item_unit_price");
    item_countNdx = findColumn("item_count");
    delivery_unitNdx = findColumn("delivery_unit");
    delivery_factorNdx = findColumn("delivery_factor");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public String branch_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_addressNdx);
  }
  private int branch_addressNdx;
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public String delivery_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_unitNdx);
  }
  private int delivery_unitNdx;
  public Double delivery_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(delivery_factorNdx);
  }
  private int delivery_factorNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^48*/
	
	/**
	 * 查询越库采购单，使用标准价，用于生成越库形式的配送单
	 */
	public List<Map> queryDetail(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    c.CATEGORY_NAME ITEM_CATEGORY,
//  			    d.ITEM_COUNT,
//  			    p.item_price as ITEM_UNIT_PRICE,
//  			   -- d.PAY_AMT,
//  			    d.RECEIVER_ID,
//  			    d.RECEIVER,
//  			    b.BRANCH_ADDRESS,
//  			    u.DELIVERY_UNIT,
//  			    u.delivery_factor
//  			FROM
//  			:Com_("D_T1_PURCHASING_DETAIL") d
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    d.ITEM_CATEGORY = c.CATEGORY_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = d.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = d.ITEM_ID
//      		LEFT JOIN
//      		:Com_("D_T2_BRANCH") b
//  			 ON 
//  			 	b.BRANCH_ID=d.RECEIVER_ID
//  			WHERE
//  			    d.FORM_ID = :formId
//  			ORDER BY
//  			    d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CrossDistributionBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:95^3*/
		List<Map> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	private List<Map> processIter(DetailIter detailIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("formId", detailIter.form_id());
			detail.put("receiverId", detailIter.receiver_id());
			detail.put("receiver", detailIter.receiver());
			detail.put("branchAddress", detailIter.branch_address());
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemCategory", detailIter.item_category());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("itemSpecification", detailIter.item_specification());
			detail.put("itemCount", detailIter.item_count());
			detail.put("itemUnitPrice", detailIter.item_unit_price());
			// detail.put("payAmt", detailIter.pay_amt());
			detail.put("deliveryUnit", detailIter.delivery_unit());
			detail.put("unitConvertFactor", detailIter.delivery_factor());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class CrossDistributionBean_SJProfileKeys 
{
  private static CrossDistributionBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CrossDistributionBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CrossDistributionBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.CrossDistributionBean_SJProfile0");
  }
}
