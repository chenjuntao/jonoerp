/*@lineinfo:filename=InputDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*///===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.8.29 by wkb
// Last edited on 2014.9.11 by lyz
//
//===============================================


package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import org.apache.log4j.Logger;
import pojo.form.InputDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class InputDetailBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(InputDetailBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    order_countNdx = findColumn("order_count");
    receive_countNdx = findColumn("receive_count");
    different_countNdx = findColumn("different_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    pay_amtNdx = findColumn("pay_amt");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    batchNdx = findColumn("batch");
    expired_timeNdx = findColumn("expired_time");
    out_receive_rateNdx = findColumn("out_receive_rate");
    received_countNdx = findColumn("received_count");
  }
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
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double receive_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_countNdx);
  }
  private int receive_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
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
  public String batch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(batchNdx);
  }
  private int batchNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
  public Double out_receive_rate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(out_receive_rateNdx);
  }
  private int out_receive_rateNdx;
  public Double received_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(received_countNdx);
  }
  private int received_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^69*/
	
	public List<InputDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		return query(formId,null);
	}

	/*餐厅改为标准价*/
	public List<InputDetail> query(String formId,String hasSum)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String pFormId = null;
		/*@lineinfo:generated-code*//*@lineinfo:60^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    h.FORM_REF_ID 
//  			FROM
//  			:Com_("D_T1_INPUT_HEADER") h
//  			WHERE
//  			    h.FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 0);
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
    pFormId = __sJT_rtRs.getString(1);
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

/*@lineinfo:user-code*//*@lineinfo:67^3*/
		
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:70^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.form_id,
//  			    DECODE(d.ITEM_ID,NULL,'合计',d.ITEM_ID) ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    SUM(d.ORDER_COUNT)ORDER_COUNT,
//  			    d.RECEIVED_COUNT,
//  			    sum(d.RECEIVE_COUNT) RECEIVE_COUNT,
//  			    d.DIFFERENT_COUNT,
//  			    d.ITEM_UNIT_PRICE,
//  			    sum(d.PAY_AMT) PAY_AMT,
//  			    d.RECEIVE_PRICE,
//  			    sum(d.RECEIVE_AMT) RECEIVE_AMT,
//  			    d.BATCH,
//  			    d.EXPIRED_TIME,
//  			    u.OUT_RECEIVE_RATE
//  			FROM
//  			:Com_("D_T1_INPUT_DETAIL") d
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = d.ITEM_ID
//  			WHERE
//  			    d.FORM_ID = :formId
//  			GROUP BY
//  			    GROUPING SETS( (d.FORM_ID, d.ITEM_ID, d.ITEM_NAME, d.ITEM_DIMENSION,d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY, d.ORDER_COUNT,d.RECEIVED_COUNT, d.RECEIVE_COUNT, d.DIFFERENT_COUNT,
//  			    d.ITEM_UNIT_PRICE, d.PAY_AMT, d.RECEIVE_PRICE, d.RECEIVE_AMT, d.BATCH, d.EXPIRED_TIME, u.OUT_RECEIVE_RATE),NULL)
//  			ORDER BY
//  			    d.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_2 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:102^17*/
		List<InputDetail> detailLst = processIter(detailIter);
		
		if(detailLst.size() >0){
			if(TextUtil.isEmpty(hasSum)){
				detailLst.remove(detailLst.size() -1);
			}
		}
		
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<InputDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<InputDetail> detailLst = new ArrayList<InputDetail>();
		while(detailIter.next()) {
			InputDetail detail = new InputDetail();
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setOrderCount(detailIter.order_count());
			detail.setReceiveCount(detailIter.receive_count());
			detail.setDifferentCount(detailIter.different_count());
			detail.setItemUnitPrice(detailIter.item_unit_price());
			detail.setPayAmt(detailIter.pay_amt());
			detail.setReceivePrice(detailIter.receive_price());
			detail.setReceiveAmt(detailIter.receive_amt());
			detail.setBatch(detailIter.batch());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));

			detail.setReceivedCount(detailIter.received_count());
			detail.setOutReceiveRate(detailIter.out_receive_rate());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	/**
	 * 保存明细
	 */
	public int saveEntity(InputDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		String itemCategory = detail.getItemCategory();
		Double orderCount = detail.getOrderCount();
		Double receivedCount = detail.getReceivedCount();
		Double receiveCount = detail.getReceiveCount();
		Double differentCount = detail.getDifferentCount();
		Double itemUnitPrice = detail.getItemUnitPrice();
		Double payAmt = detail.getPayAmt();
		Double receivePrice = detail.getReceivePrice();
		Double receiveAmt = detail.getReceiveAmt();
		String batch = detail.getBatch();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());
	
		/*@lineinfo:generated-code*//*@lineinfo:168^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_INPUT_DETAIL")
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_SPECIFICATION, ITEM_CATEGORY, 
//  					ORDER_COUNT, RECEIVED_COUNT, RECEIVE_COUNT, DIFFERENT_COUNT, ITEM_UNIT_PRICE, PAY_AMT,RECEIVE_PRICE,RECEIVE_AMT, BATCH, EXPIRED_TIME) 
//  			VALUES (:formId, 
//  					:itemId, 
//  					:itemName, 
//  					:itemDimension, 
//  					:itemSpecification, 
//  					:itemCategory, 
//  					:orderCount,  :receivedCount, 
//  					:receiveCount, 
//  					:differentCount, 
//  					:itemUnitPrice, 
//  					:payAmt, 
//  					:receivePrice,
//  					:receiveAmt,
//  					:batch, 
//  					:expiredTime)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  String __sJT_7 = itemCategory;
  Double __sJT_8 = orderCount;
  Double __sJT_9 = receivedCount;
  Double __sJT_10 = receiveCount;
  Double __sJT_11 = differentCount;
  Double __sJT_12 = itemUnitPrice;
  Double __sJT_13 = payAmt;
  Double __sJT_14 = receivePrice;
  Double __sJT_15 = receiveAmt;
  String __sJT_16 = batch;
  Date __sJT_17 = expiredTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 2);
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
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setDoubleWrapper(14, __sJT_14);
      __sJT_stmt.setDoubleWrapper(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:187^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateEntity(InputDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		String itemCategory = detail.getItemCategory();
		Double orderCount = detail.getOrderCount();
		Double receiveCount = detail.getReceiveCount();
		Double receivedCount = detail.getReceivedCount();
		Double differentCount = detail.getDifferentCount();
		Double itemUnitPrice = detail.getItemUnitPrice();
		Double payAmt = detail.getPayAmt();
		Double receivePrice = detail.getReceivePrice();
		Double receiveAmt = detail.getReceiveAmt();
		String batch = detail.getBatch();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());
	
		/*@lineinfo:generated-code*//*@lineinfo:214^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_INPUT_DETAIL")
//  			SET
//  				RECEIVE_COUNT = :receiveCount,
//  				DIFFERENT_COUNT = :differentCount,
//  			    PAY_AMT = :payAmt,
//  			    RECEIVE_AMT = :receiveAmt,
//  			    RECEIVED_COUNT = :receivedCount	
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
  String __sJT_1 = Com_("D_T1_INPUT_DETAIL");
  Double __sJT_2 = receiveCount;
  Double __sJT_3 = differentCount;
  Double __sJT_4 = payAmt;
  Double __sJT_5 = receiveAmt;
  Double __sJT_6 = receivedCount;
  String __sJT_7 = formId;
  String __sJT_8 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:226^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:236^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_INPUT_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, InputDetailBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:242^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
}/*@lineinfo:generated-code*/class InputDetailBean_SJProfileKeys 
{
  private static InputDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new InputDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private InputDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.InputDetailBean_SJProfile0");
  }
}
