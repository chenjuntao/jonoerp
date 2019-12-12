/*@lineinfo:filename=RequestDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 26, 2014 by liyzh
 * Last edited on Aug 26, 2014 by liyzh
 * 
 * 说明： 餐厅要货、外部订货商订货需求表明细
 */
package logic.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.RequestDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class RequestDetailBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(RequestDetailBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:33^2*/

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
    intl_bar_codeNdx = findColumn("intl_bar_code");
    item_nameNdx = findColumn("item_name");
    item_categoryNdx = findColumn("item_category");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    amt_tt_cny1Ndx = findColumn("amt_tt_cny1");
    amt_tt_cny2Ndx = findColumn("amt_tt_cny2");
    amt_tt_cny3Ndx = findColumn("amt_tt_cny3");
    require_count1Ndx = findColumn("require_count1");
    require_count2Ndx = findColumn("require_count2");
    require_count3Ndx = findColumn("require_count3");
    inventoryNdx = findColumn("inventory");
    suggest_count1Ndx = findColumn("suggest_count1");
    suggest_count2Ndx = findColumn("suggest_count2");
    suggest_count3Ndx = findColumn("suggest_count3");
    order_countNdx = findColumn("order_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    pay_amtNdx = findColumn("pay_amt");
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
  public String intl_bar_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(intl_bar_codeNdx);
  }
  private int intl_bar_codeNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
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
  public Double amt_tt_cny1() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt_tt_cny1Ndx);
  }
  private int amt_tt_cny1Ndx;
  public Double amt_tt_cny2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt_tt_cny2Ndx);
  }
  private int amt_tt_cny2Ndx;
  public Double amt_tt_cny3() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt_tt_cny3Ndx);
  }
  private int amt_tt_cny3Ndx;
  public Double require_count1() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(require_count1Ndx);
  }
  private int require_count1Ndx;
  public Double require_count2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(require_count2Ndx);
  }
  private int require_count2Ndx;
  public Double require_count3() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(require_count3Ndx);
  }
  private int require_count3Ndx;
  public Double inventory() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventoryNdx);
  }
  private int inventoryNdx;
  public Double suggest_count1() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(suggest_count1Ndx);
  }
  private int suggest_count1Ndx;
  public Double suggest_count2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(suggest_count2Ndx);
  }
  private int suggest_count2Ndx;
  public Double suggest_count3() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(suggest_count3Ndx);
  }
  private int suggest_count3Ndx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:54^18*/
	
	public List<RequestDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		return query(formId,null);
	}

	public List<RequestDetail> query(String formId,String hasSum)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:65^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    DECODE(FORM_ID,NULL,'合计',ROWNUM) rownumber,
//  			    a.*
//  			FROM
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.INTL_BAR_CODE,
//  			            d.ITEM_NAME,
//  			            d.ITEM_CATEGORY,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            d.AMT_TT_CNY1,
//  			            d.AMT_TT_CNY2,
//  			            d.AMT_TT_CNY3,
//  			            d.REQUIRE_COUNT1,
//  			            d.REQUIRE_COUNT2,
//  			            d.REQUIRE_COUNT3,
//  			            d.INVENTORY,
//  			            d.SUGGEST_COUNT1,
//  			            d.SUGGEST_COUNT2,
//  			            d.SUGGEST_COUNT3,
//  			            SUM(d.ORDER_COUNT) ORDER_COUNT,
//  			            d.ITEM_UNIT_PRICE,
//  			            SUM(d.PAY_AMT) PAY_AMT
//  			        FROM
//  			        	:Com_("D_T1_REQUEST_DETAIL") d
//  			        WHERE
//  			        	d.FORM_ID = :formId
//  			        GROUP BY
//  			            GROUPING SETS( (d.FORM_ID, d.ITEM_ID, d.INTL_BAR_CODE, d.ITEM_NAME, d.ITEM_CATEGORY,
//  			            d.ITEM_DIMENSION, d.ITEM_SPECIFICATION, d.AMT_TT_CNY1, d.AMT_TT_CNY2, d.AMT_TT_CNY3,
//  			            d.REQUIRE_COUNT1, d.REQUIRE_COUNT2, d.REQUIRE_COUNT3, d.INVENTORY, d.SUGGEST_COUNT1,
//  			            d.SUGGEST_COUNT2, d.SUGGEST_COUNT3, d.ORDER_COUNT, d.ITEM_UNIT_PRICE, d.PAY_AMT),NULL)
//  			        ORDER BY
//  			            d.ITEM_ID)a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:102^27*/
		List<RequestDetail> detailLst = processIter(detailIter,hasSum);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:109^2*/

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
    item_idNdx = findColumn("item_id");
    order_countNdx = findColumn("order_count");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:109^67*/
	/**
	 * 查询统配原料
	 */
	public List<RequestDetail> queryUnified(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:117^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    d.ITEM_ID,
//  			    d.ORDER_COUNT
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_REQUEST_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			ON
//  			    rb.BRANCH_ID = h.BUYER_ID
//  			INNER JOIN
//  			:Com_("D_T2_DELIVERY_TYPE") dt
//  			ON
//  			    dt.REGION_ID = rb.REGION_ID
//  			AND dt.ITEM_ID = d.item_id
//  			AND dt.DELIVERY_TYPE = 'UNIFIED'
//  			WHERE
//  		    	h.FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_4 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:139^3*/
		List<RequestDetail> detailLst = new ArrayList<RequestDetail>();
		while(itemIter.next()) {
			RequestDetail detail = new RequestDetail();
			detail.setItemId(itemIter.item_id());
			detail.setOrderCount(itemIter.order_count());
			detailLst.add(detail);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<RequestDetail> processIter(DetailIter detailIter, String hasSum) 
			throws SQLException {
		List<RequestDetail> detailLst = new ArrayList<RequestDetail>();
		while(detailIter.next()) {
			RequestDetail detail = new RequestDetail();
			detail.setRownumber(detailIter.rownumber());
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setIntlBarCode(detailIter.intl_bar_code());
			detail.setItemName(detailIter.item_name());
			detail.setItemCategory(detailIter.item_category());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setAmtTTCNY1(detailIter.amt_tt_cny1());
			detail.setAmtTTCNY2(detailIter.amt_tt_cny2());
			detail.setAmtTTCNY3(detailIter.amt_tt_cny3());
			detail.setRequireCount1(detailIter.require_count1());
			detail.setRequireCount2(detailIter.require_count2());
			detail.setRequireCount3(detailIter.require_count3());
			detail.setInventory(detailIter.inventory());
			detail.setSuggestCount1(detailIter.suggest_count1());
			detail.setSuggestCount2(detailIter.suggest_count2());
			detail.setSuggestCount3(detailIter.suggest_count3());
			detail.setOrderCount(detailIter.order_count());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setPayAmt(detailIter.pay_amt());
			detailLst.add(detail);
		}
		
		int length = detailLst.size();
		if(length >0){
			if(TextUtil.isEmpty(hasSum)){
				detailLst.remove(length -1);
			}
		}
		
		return detailLst;
	}
	
	/**
	 * 保存明细
	 */
	public int saveEntity(RequestDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String intlBarCode = detail.getIntlBarCode();
		String itemName = detail.getItemName();
		String itemCategory = detail.getItemCategory();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		Double amtTTCNY1 = detail.getAmtTTCNY1();
		Double amtTTCNY2 = detail.getAmtTTCNY2();
		Double amtTTCNY3 = detail.getAmtTTCNY3();
		Double requireCount1 = detail.getRequireCount1();
		Double requireCount2 = detail.getRequireCount2();
		Double requireCount3 = detail.getRequireCount3();
		Double inventory = detail.getInventory();
		Double suggestCount1 = detail.getSuggestCount1();
		Double suggestCount2 = detail.getSuggestCount2();
		Double suggestCount3 = detail.getSuggestCount3();
		Double orderCount = detail.getOrderCount();
		Double itemUnitPrice = detail.getItemUnitPrice();
		Double payAmt = detail.getPayAmt();
	
		/*@lineinfo:generated-code*//*@lineinfo:218^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_REQUEST_DETAIL") 
//  				(FORM_ID, ITEM_ID, INTL_BAR_CODE, ITEM_NAME, ITEM_CATEGORY, ITEM_DIMENSION, ITEM_SPECIFICATION,
//  						AMT_TT_CNY1, AMT_TT_CNY2, AMT_TT_CNY3, REQUIRE_COUNT1, REQUIRE_COUNT2, REQUIRE_COUNT3, 
//  						INVENTORY, SUGGEST_COUNT1, SUGGEST_COUNT2, SUGGEST_COUNT3, ORDER_COUNT, ITEM_UNIT_PRICE, PAY_AMT) 
//  						VALUES (:formId, 
//  								:itemId, 
//  								:intlBarCode, 
//  								:itemName, 
//  								:itemCategory, 
//  								:itemDimension, 
//  								:itemSpecification, 
//  								:amtTTCNY1, 
//  								:amtTTCNY2, 
//  								:amtTTCNY3, 
//  								:requireCount1, 
//  								:requireCount2, 
//  								:requireCount3, 
//  								:inventory, 
//  								:suggestCount1, 
//  								:suggestCount2, 
//  								:suggestCount3, 
//  								:orderCount, 
//  								:itemUnitPrice, 
//  								:payAmt)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = intlBarCode;
  String __sJT_5 = itemName;
  String __sJT_6 = itemCategory;
  String __sJT_7 = itemDimension;
  String __sJT_8 = itemSpecification;
  Double __sJT_9 = amtTTCNY1;
  Double __sJT_10 = amtTTCNY2;
  Double __sJT_11 = amtTTCNY3;
  Double __sJT_12 = requireCount1;
  Double __sJT_13 = requireCount2;
  Double __sJT_14 = requireCount3;
  Double __sJT_15 = inventory;
  Double __sJT_16 = suggestCount1;
  Double __sJT_17 = suggestCount2;
  Double __sJT_18 = suggestCount3;
  Double __sJT_19 = orderCount;
  Double __sJT_20 = itemUnitPrice;
  Double __sJT_21 = payAmt;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestDetailBean_SJProfileKeys.getKey(0), 2);
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
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setDoubleWrapper(14, __sJT_14);
      __sJT_stmt.setDoubleWrapper(15, __sJT_15);
      __sJT_stmt.setDoubleWrapper(16, __sJT_16);
      __sJT_stmt.setDoubleWrapper(17, __sJT_17);
      __sJT_stmt.setDoubleWrapper(18, __sJT_18);
      __sJT_stmt.setDoubleWrapper(19, __sJT_19);
      __sJT_stmt.setDoubleWrapper(20, __sJT_20);
      __sJT_stmt.setDoubleWrapper(21, __sJT_21);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:243^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateEntity(RequestDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		Double inventory = detail.getInventory();
		Double orderCount = detail.getOrderCount();
		Double itemUnitPrice = detail.getItemUnitPrice();
		Double payAmt = detail.getPayAmt();
		Double amtTTCNY1 = detail.getAmtTTCNY1();
		Double amtTTCNY2 = detail.getAmtTTCNY2();
		Double amtTTCNY3 = detail.getAmtTTCNY3();
		Double requireCount1 = detail.getRequireCount1();
		Double requireCount2 = detail.getRequireCount2();
		Double requireCount3 = detail.getRequireCount3();
		Double suggestCount1 = detail.getSuggestCount1();
		Double suggestCount2 = detail.getSuggestCount2();
		Double suggestCount3 = detail.getSuggestCount3();
		/*@lineinfo:generated-code*//*@lineinfo:267^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_REQUEST_DETAIL")
//  			SET
//  --			    INVENTORY = :inventory,
//  			    ORDER_COUNT = :orderCount,
//  			    PAY_AMT = :payAmt,
//  	    		AMT_TT_CNY1 = :amtTTCNY1, 
//  				AMT_TT_CNY2 = :amtTTCNY2, 
//  				AMT_TT_CNY3 = :amtTTCNY3, 
//  				REQUIRE_COUNT1 = :requireCount1, 
//  				REQUIRE_COUNT2 = :requireCount2, 
//  				REQUIRE_COUNT3 = :requireCount3, 
//  				SUGGEST_COUNT1 = :suggestCount1, 
//  				SUGGEST_COUNT2 = :suggestCount2, 
//  				SUGGEST_COUNT3 = :suggestCount3
//  			WHERE
//  				FORM_ID = :formId
//  			and ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  Double __sJT_2 = orderCount;
  Double __sJT_3 = payAmt;
  Double __sJT_4 = amtTTCNY1;
  Double __sJT_5 = amtTTCNY2;
  Double __sJT_6 = amtTTCNY3;
  Double __sJT_7 = requireCount1;
  Double __sJT_8 = requireCount2;
  Double __sJT_9 = requireCount3;
  Double __sJT_10 = suggestCount1;
  Double __sJT_11 = suggestCount2;
  Double __sJT_12 = suggestCount3;
  String __sJT_13 = formId;
  String __sJT_14 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:286^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:296^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_REQUEST_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestDetailBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:302^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteByForm(String formId,String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:312^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T1_REQUEST_DETAIL")
//  				WHERE
//  					FORM_ID = :formId
//  					AND ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestDetailBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:319^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
}/*@lineinfo:generated-code*/class RequestDetailBean_SJProfileKeys 
{
  private static RequestDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.RequestDetailBean_SJProfile0");
  }
}
