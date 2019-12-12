/*@lineinfo:filename=SampleInfoBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Sep 18 08:48:46 CST 2015 by lyz
 * Last edited on Fri Sep 18 08:48:46 CST 2015 by lyz
 * 
 * comment: 总部-研发样品信息表
 */
package logic.module.hq;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.SampleInfo;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;

public class SampleInfoBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SampleInfoBean.class);
	
	public int saveEntity(SampleInfo sample)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String id = TextUtil.uuid();
		sample.setId(id);
		String productName = sample.getProductName();
		Integer minimumOrderQuantity = sample.getMinimumOrderQuantity();
		String producePlace = sample.getProducePlace();
		Integer orderCycle = sample.getOrderCycle();
		Integer useCycle = sample.getUseCycle();
		String qualificationPic = sample.getQualificationPic();
		String samplePic = sample.getSamplePic();
		Double price = sample.getPrice();
		Double grossPrice = sample.getGrossPrice();
		Double grossWeight = sample.getGrossWeight();
		Double netWeight = sample.getNetWeight();
		Double netRatio = sample.getNetRatio();
		Integer shelfLife = sample.getShelfLife();
		Integer deliveryTime = sample.getDeliveryTime();
		String accptanceCriteria = sample.getAccptanceCriteria();
		String supplier = sample.getSupplier();
		String createUserId = sample.getCreateUserId();
		Date createTime = SqlDateUtil.getSqlDate(new java.util.Date());

		/*@lineinfo:generated-code*//*@lineinfo:62^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_SAMPLE_INFO")
//  				(ID, PRODUCT_NAME, MINIMUM_ORDER_QUANTITY, PRODUCE_PLACE, ORDER_CYCLE, USE_CYCLE, QUALIFICATION_PIC, SAMPLE_PIC, PRICE, GROSS_PRICE, GROSS_WEIGHT, NET_WEIGHT, 
//  						NET_RATIO, SHELF_LIFE, DELIVERY_TIME, ACCPTANCE_CRITERIA, SUPPLIER, CREATE_USER_ID, CREATE_TIME) 
//  			VALUES (:id, :productName, :minimumOrderQuantity, :producePlace, :orderCycle, :useCycle, :qualificationPic, :samplePic, :price, :grossPrice, :grossWeight, :netWeight, 
//  					:netRatio, :shelfLife, :deliveryTime, :accptanceCriteria, :supplier, :createUserId, :createTime)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SAMPLE_INFO");
  String __sJT_2 = id;
  String __sJT_3 = productName;
  Integer __sJT_4 = minimumOrderQuantity;
  String __sJT_5 = producePlace;
  Integer __sJT_6 = orderCycle;
  Integer __sJT_7 = useCycle;
  String __sJT_8 = qualificationPic;
  String __sJT_9 = samplePic;
  Double __sJT_10 = price;
  Double __sJT_11 = grossPrice;
  Double __sJT_12 = grossWeight;
  Double __sJT_13 = netWeight;
  Double __sJT_14 = netRatio;
  Integer __sJT_15 = shelfLife;
  Integer __sJT_16 = deliveryTime;
  String __sJT_17 = accptanceCriteria;
  String __sJT_18 = supplier;
  String __sJT_19 = createUserId;
  Date __sJT_20 = createTime;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SampleInfoBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_stmt.setIntWrapper(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setDoubleWrapper(14, __sJT_14);
      __sJT_stmt.setIntWrapper(15, __sJT_15);
      __sJT_stmt.setIntWrapper(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setDate(20, __sJT_20);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:68^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(SampleInfo sample)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String id = sample.getId();
		String productName = sample.getProductName();
		Integer minimumOrderQuantity = sample.getMinimumOrderQuantity();
		String producePlace = sample.getProducePlace();
		Integer orderCycle = sample.getOrderCycle();
		Integer useCycle = sample.getUseCycle();
		String qualificationPic = sample.getQualificationPic();
		String samplePic = sample.getSamplePic();
		Double price = sample.getPrice();
		Double grossPrice = sample.getGrossPrice();
		Double grossWeight = sample.getGrossWeight();
		Double netWeight = sample.getNetWeight();
		Double netRatio = sample.getNetRatio();
		Integer shelfLife = sample.getShelfLife();
		Integer deliveryTime = sample.getDeliveryTime();
		String accptanceCriteria = sample.getAccptanceCriteria();
		String supplier = sample.getSupplier();

		/*@lineinfo:generated-code*//*@lineinfo:96^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SAMPLE_INFO")
//  			SET
//  				ID = :id,
//  				PRODUCT_NAME = :productName,
//  				MINIMUM_ORDER_QUANTITY = :minimumOrderQuantity,
//  				PRODUCE_PLACE = :producePlace,
//  				ORDER_CYCLE = :orderCycle,
//  				USE_CYCLE = :useCycle,
//  				-- QUALIFICATION_PIC = :qualificationPic,
//  				-- SAMPLE_PIC = :samplePic,
//  				PRICE = :price,
//  				GROSS_PRICE = :grossPrice,
//  				GROSS_WEIGHT = :grossWeight,
//  				NET_WEIGHT = :netWeight,
//  				NET_RATIO = :netRatio,
//  				SHELF_LIFE = :shelfLife,
//  				DELIVERY_TIME = :deliveryTime,
//  				ACCPTANCE_CRITERIA = :accptanceCriteria,
//  				SUPPLIER = :supplier
//  			WHERE 
//  				ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SAMPLE_INFO");
  String __sJT_2 = id;
  String __sJT_3 = productName;
  Integer __sJT_4 = minimumOrderQuantity;
  String __sJT_5 = producePlace;
  Integer __sJT_6 = orderCycle;
  Integer __sJT_7 = useCycle;
  Double __sJT_8 = price;
  Double __sJT_9 = grossPrice;
  Double __sJT_10 = grossWeight;
  Double __sJT_11 = netWeight;
  Double __sJT_12 = netRatio;
  Integer __sJT_13 = shelfLife;
  Integer __sJT_14 = deliveryTime;
  String __sJT_15 = accptanceCriteria;
  String __sJT_16 = supplier;
  String __sJT_17 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SampleInfoBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setIntWrapper(6, __sJT_6);
      __sJT_stmt.setIntWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setIntWrapper(13, __sJT_13);
      __sJT_stmt.setIntWrapper(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:119^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateQualificationPic(String id, String picId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:129^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SAMPLE_INFO")
//  			SET
//  				QUALIFICATION_PIC = :picId
//  			WHERE 
//  				ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SAMPLE_INFO");
  String __sJT_2 = picId;
  String __sJT_3 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SampleInfoBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:136^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateSamplePic(String id, String picId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:146^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SAMPLE_INFO")
//  			SET
//  				SAMPLE_PIC = :picId
//  			WHERE 
//  				ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SAMPLE_INFO");
  String __sJT_2 = picId;
  String __sJT_3 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SampleInfoBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:153^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String id)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:163^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_SAMPLE_INFO")
//  			WHERE
//  				ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SAMPLE_INFO");
  String __sJT_2 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SampleInfoBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:169^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:175^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SampleInfoIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SampleInfoIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    product_nameNdx = findColumn("product_name");
    minimum_order_quantityNdx = findColumn("minimum_order_quantity");
    produce_placeNdx = findColumn("produce_place");
    order_cycleNdx = findColumn("order_cycle");
    use_cycleNdx = findColumn("use_cycle");
    qualification_picNdx = findColumn("qualification_pic");
    sample_picNdx = findColumn("sample_pic");
    priceNdx = findColumn("price");
    gross_priceNdx = findColumn("gross_price");
    gross_weightNdx = findColumn("gross_weight");
    net_weightNdx = findColumn("net_weight");
    net_ratioNdx = findColumn("net_ratio");
    shelf_lifeNdx = findColumn("shelf_life");
    delivery_timeNdx = findColumn("delivery_time");
    accptance_criteriaNdx = findColumn("accptance_criteria");
    supplierNdx = findColumn("supplier");
    form_statusNdx = findColumn("form_status");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String product_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(product_nameNdx);
  }
  private int product_nameNdx;
  public Integer minimum_order_quantity() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(minimum_order_quantityNdx);
  }
  private int minimum_order_quantityNdx;
  public String produce_place() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(produce_placeNdx);
  }
  private int produce_placeNdx;
  public Integer order_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(order_cycleNdx);
  }
  private int order_cycleNdx;
  public Integer use_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(use_cycleNdx);
  }
  private int use_cycleNdx;
  public String qualification_pic() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(qualification_picNdx);
  }
  private int qualification_picNdx;
  public String sample_pic() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sample_picNdx);
  }
  private int sample_picNdx;
  public Double price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(priceNdx);
  }
  private int priceNdx;
  public Double gross_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(gross_priceNdx);
  }
  private int gross_priceNdx;
  public Double gross_weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(gross_weightNdx);
  }
  private int gross_weightNdx;
  public Double net_weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(net_weightNdx);
  }
  private int net_weightNdx;
  public Double net_ratio() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(net_ratioNdx);
  }
  private int net_ratioNdx;
  public Integer shelf_life() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(shelf_lifeNdx);
  }
  private int shelf_lifeNdx;
  public Integer delivery_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(delivery_timeNdx);
  }
  private int delivery_timeNdx;
  public String accptance_criteria() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(accptance_criteriaNdx);
  }
  private int accptance_criteriaNdx;
  public String supplier() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplierNdx);
  }
  private int supplierNdx;
  public String form_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_statusNdx);
  }
  private int form_statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:178^22*/

	public SampleInfo queryById(String id) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SampleInfoIter sampleIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:184^3*/

//  ************************************************************
//  #sql [myCtx] sampleIter = { SELECT
//  			    i.*,
//  			    '' as form_status
//  			FROM
//  			:Com_("D_T1_SAMPLE_INFO") i
//  			WHERE
//  				ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SAMPLE_INFO");
  String __sJT_2 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SampleInfoBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      sampleIter = new SampleInfoIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:192^3*/
		List<SampleInfo> sampleInfoLst = processIter(sampleIter);
		sampleIter.close();
		closeDefaultContext(myCtx);
		if (sampleInfoLst !=null && !sampleInfoLst.isEmpty()) {
			return sampleInfoLst.get(0);
		}
		return null;
	}
	
	public List<SampleInfo> query(String productName, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		productName = "%" + productName + "%";

		String query = " 1=1";
		if (BillStatus.UNADUIT_US.equals(queryType)) { // 获取未审核记录
			query = query + " AND NVL( s.STATUS, 'null') = '" + BillStatus.UNADUIT_CN + "'"; 
		}
		
		DefaultContext myCtx = getDefaultContext();
		SampleInfoIter sampleIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:213^3*/

//  ************************************************************
//  #sql [myCtx] sampleIter = { SELECT
//  			    i.*,
//  	            s.STATUS AS form_status
//  			FROM
//  			:Com_("D_T1_SAMPLE_INFO") i
//  	        LEFT JOIN
//  	        :Com_("D_T0_FORM_STATUS") s
//  	        ON
//  	            s.FORM_ID = i.ID
//  	        AND s.IS_CURRENT = 1
//  			WHERE
//  				:query
//  			AND	PRODUCT_NAME LIKE :productName
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SAMPLE_INFO");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  String __sJT_4 = productName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SampleInfoBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      sampleIter = new SampleInfoIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:227^3*/
		List<SampleInfo> sampleInfoLst = processIter(sampleIter);
		sampleIter.close();
		closeDefaultContext(myCtx);
		return sampleInfoLst;
	}

	private List<SampleInfo> processIter(SampleInfoIter sampleIter) 
			throws SQLException {
		List<SampleInfo> sampleInfoLst = new ArrayList<SampleInfo>();
		while(sampleIter.next()) {
			SampleInfo sample = new SampleInfo();
			sample.setId(sampleIter.id());
			sample.setProductName(sampleIter.product_name());
			sample.setMinimumOrderQuantity(sampleIter.minimum_order_quantity());
			sample.setProducePlace(sampleIter.produce_place());
			sample.setOrderCycle(sampleIter.order_cycle());
			sample.setUseCycle(sampleIter.use_cycle());
			sample.setQualificationPic(sampleIter.qualification_pic());
			sample.setSamplePic(sampleIter.sample_pic());
			sample.setPrice(sampleIter.price());
			sample.setGrossPrice(sampleIter.gross_price());
			sample.setGrossWeight(sampleIter.gross_weight());
			sample.setNetWeight(sampleIter.net_weight());
			sample.setNetRatio(sampleIter.net_ratio());
			sample.setShelfLife(sampleIter.shelf_life());
			sample.setDeliveryTime(sampleIter.delivery_time());
			sample.setAccptanceCriteria(sampleIter.accptance_criteria());
			sample.setSupplier(sampleIter.supplier());
			sample.setFormStatus(sampleIter.form_status());
			sampleInfoLst.add(sample);
		}
		return sampleInfoLst;
	}
}/*@lineinfo:generated-code*/class SampleInfoBean_SJProfileKeys 
{
  private static SampleInfoBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SampleInfoBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SampleInfoBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.hq.SampleInfoBean_SJProfile0");
  }
}
