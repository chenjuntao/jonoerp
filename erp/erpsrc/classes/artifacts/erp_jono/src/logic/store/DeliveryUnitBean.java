/*@lineinfo:filename=DeliveryUnitBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Feb 04 09:39:01 CST 2015 by lyz
 * Last edited on Wed Feb 04 09:39:01 CST 2015 by lyz
 * 
 * comment: 配送单位表
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.DeliveryUnit;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class DeliveryUnitBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(DeliveryUnitBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ParamIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ParamIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    recipe_unitNdx = findColumn("recipe_unit");
    recipe_factorNdx = findColumn("recipe_factor");
    delivery_unitNdx = findColumn("delivery_unit");
    delivery_factorNdx = findColumn("delivery_factor");
    unit_volumeNdx = findColumn("unit_volume");
    unit_weightNdx = findColumn("unit_weight");
    min_order_countNdx = findColumn("min_order_count");
    out_receive_rateNdx = findColumn("out_receive_rate");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String recipe_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(recipe_unitNdx);
  }
  private int recipe_unitNdx;
  public Double recipe_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(recipe_factorNdx);
  }
  private int recipe_factorNdx;
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
  public Double unit_volume() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_volumeNdx);
  }
  private int unit_volumeNdx;
  public Double unit_weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_weightNdx);
  }
  private int unit_weightNdx;
  public Double min_order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(min_order_countNdx);
  }
  private int min_order_countNdx;
  public Double out_receive_rate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(out_receive_rateNdx);
  }
  private int out_receive_rateNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^51*/
	
	public int saveEntity(DeliveryUnit param)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String itemId = param.getItemId();
		String recipeUnit = param.getRecipeUnit();
		Double recipeFactor = param.getRecipeFactor();
		String deliveryUnit = param.getDeliveryUnit();
		Double deliveryFactor = param.getDeliveryFactor();
		Double unitVolume = param.getUnitVolume();
		Double unitWeight = param.getUnitWeight();
		Double minOrderCount = param.getMinOrderCount();
		Double outReceiveRate = param.getOutReceiveRate();

		/*@lineinfo:generated-code*//*@lineinfo:53^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_DELIVERY_UNIT") 
//  				(ITEM_ID, RECIPE_UNIT, RECIPE_FACTOR, DELIVERY_UNIT, DELIVERY_FACTOR, UNIT_VOLUME, UNIT_WEIGHT, MIN_ORDER_COUNT, OUT_RECEIVE_RATE) 
//  			VALUES (:itemId, :recipeUnit, :recipeFactor, :deliveryUnit, :deliveryFactor, :unitVolume, :unitWeight, :minOrderCount, :outReceiveRate)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_2 = itemId;
  String __sJT_3 = recipeUnit;
  Double __sJT_4 = recipeFactor;
  String __sJT_5 = deliveryUnit;
  Double __sJT_6 = deliveryFactor;
  Double __sJT_7 = unitVolume;
  Double __sJT_8 = unitWeight;
  Double __sJT_9 = minOrderCount;
  Double __sJT_10 = outReceiveRate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryUnitBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:57^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(String oldItemId, DeliveryUnit param)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String itemId = param.getItemId();
		String recipeUnit = param.getRecipeUnit();
		Double recipeFactor = param.getRecipeFactor();
		String deliveryUnit = param.getDeliveryUnit();
		Double deliveryFactor = param.getDeliveryFactor();
		Double unitVolume = param.getUnitVolume();
		Double unitWeight = param.getUnitWeight();
		Double minOrderCount = param.getMinOrderCount();
		Double outReceiveRate = param.getOutReceiveRate();

		/*@lineinfo:generated-code*//*@lineinfo:77^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_DELIVERY_UNIT")
//  			SET
//  				ITEM_ID = :itemId,
//  				RECIPE_UNIT = :recipeUnit,
//  				RECIPE_FACTOR = :recipeFactor,
//  				DELIVERY_UNIT = :deliveryUnit,
//  				DELIVERY_FACTOR = :deliveryFactor,
//  				UNIT_VOLUME = :unitVolume,
//  				UNIT_WEIGHT = :unitWeight,
//  				MIN_ORDER_COUNT = :minOrderCount,
//  				OUT_RECEIVE_RATE = :outReceiveRate
//  			WHERE 
//  				ITEM_ID = :oldItemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_2 = itemId;
  String __sJT_3 = recipeUnit;
  Double __sJT_4 = recipeFactor;
  String __sJT_5 = deliveryUnit;
  Double __sJT_6 = deliveryFactor;
  Double __sJT_7 = unitVolume;
  Double __sJT_8 = unitWeight;
  Double __sJT_9 = minOrderCount;
  Double __sJT_10 = outReceiveRate;
  String __sJT_11 = oldItemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryUnitBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:92^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String itemId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:102^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_DELIVERY_UNIT")
//  			WHERE
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryUnitBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:108^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public DeliveryUnit queryById(String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ParamIter paramIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] paramIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_DELIVERY_UNIT") h
//  			WHERE
//  				h.ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DeliveryUnitBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      paramIter = new ParamIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:125^3*/
		List<DeliveryUnit> paramLst = processIter(paramIter);
		paramIter.close();
		closeDefaultContext(myCtx);
		if (paramLst !=null && !paramLst.isEmpty()) {
			return paramLst.get(0);
		}
		return null;
	}
	
	private List<DeliveryUnit> processIter(ParamIter paramIter) 
			throws SQLException {
		List<DeliveryUnit> paramLst = new ArrayList<DeliveryUnit>();
		while(paramIter.next()) {
			DeliveryUnit param = new DeliveryUnit();
			param.setItemId(paramIter.item_id());
			param.setRecipeUnit(paramIter.recipe_unit());
			param.setRecipeFactor(paramIter.recipe_factor());
			param.setDeliveryUnit(paramIter.delivery_unit());
			param.setDeliveryFactor(paramIter.delivery_factor());
			param.setUnitVolume(paramIter.unit_volume());
			param.setUnitWeight(paramIter.unit_weight());
			param.setMinOrderCount(paramIter.min_order_count());
			param.setOutReceiveRate(paramIter.out_receive_rate());
			paramLst.add(param);
		}
		return paramLst;
	}
}/*@lineinfo:generated-code*/class DeliveryUnitBean_SJProfileKeys 
{
  private static DeliveryUnitBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DeliveryUnitBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DeliveryUnitBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.DeliveryUnitBean_SJProfile0");
  }
}
