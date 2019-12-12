/*@lineinfo:filename=TherapyBean*//*@lineinfo:user-code*//*@lineinfo:1^1*///===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.8.7 by cjt
// Last edited on 2014.8.25 by lyz
//
// Comments:read:{Com_("D_T2_THERAPY")} table.
// 说明：读取配方明细信息表。
//
//===============================================

package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;


import logic.NoConnection;
import logic.pool.ConnectionPool;
//import org.apache.commons.lang.StringUtils;
import pojo.store.Therapy;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.hq.config.item.vo.ItemVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class TherapyBean extends ConnectionPool
{
	
	public int saveEntity(Therapy therapy)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String therapyId = therapy.getTherapyId();
		String therapyName = therapy.getTherapyName();
		String therapyDimension = therapy.getTherapyDimension();
		String itemId = therapy.getItemId();
		String itemName = therapy.getItemName();
		String itemDimension = therapy.getItemDimension();
		Double unitConvertFactor = therapy.getUnitConvertFactor();
		Double itemCount = therapy.getItemCount();
		Double itemUsageRate = therapy.getItemUsageRate();
		Double itemGrossCount = therapy.getItemGrossCount();
		Double itemAmt = therapy.getItemAmt();
		String itemType = therapy.getItemType();
		String owner = therapy.getOwner();

		/*@lineinfo:generated-code*//*@lineinfo:61^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT
//  			INTO
//  			:Com_("D_T2_THERAPY")
//  			    (
//  			        THERAPY_ID,
//  			        THERAPY_NAME,
//  			        THERAPY_DIMENSION,
//  			        ITEM_ID,
//  			        ITEM_NAME,
//  			        ITEM_DIMENSION,
//  			        UNIT_CONVERT_FACTOR,
//  			        ITEM_COUNT,
//  			        ITEM_USEAGE_RATE,
//  			        ITEM_GROSS_COUNT,
//  			        ITEM_AMT,
//  			        ITEM_TYPE,
//  			        OWNER
//  			    )
//  			    VALUES
//  			    (
//  			    	:therapyId,
//  			    	:therapyName,
//  			        :therapyDimension,
//  			    	:itemId,
//  			    	:itemName,
//  			        :itemDimension,
//  			        :unitConvertFactor,
//  			    	:itemCount,
//  			    	:itemUsageRate,
//  			        :itemGrossCount,
//  			    	:itemAmt,
//  			        :itemType, :owner
//  			    )
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = therapyId;
  String __sJT_3 = therapyName;
  String __sJT_4 = therapyDimension;
  String __sJT_5 = itemId;
  String __sJT_6 = itemName;
  String __sJT_7 = itemDimension;
  Double __sJT_8 = unitConvertFactor;
  Double __sJT_9 = itemCount;
  Double __sJT_10 = itemUsageRate;
  Double __sJT_11 = itemGrossCount;
  Double __sJT_12 = itemAmt;
  String __sJT_13 = itemType;
  String __sJT_14 = owner;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:95^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(Therapy therapy)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String therapyId = therapy.getTherapyId();
		String therapyName = therapy.getTherapyName();
		String therapyDimension = therapy.getTherapyDimension();
		String itemId = therapy.getItemId();
		String itemName = therapy.getItemName();
		String itemDimension = therapy.getItemDimension();
		Double unitConvertFactor = therapy.getUnitConvertFactor();
		Double itemCount = therapy.getItemCount();
		Double itemUsageRate = therapy.getItemUsageRate();
		Double itemGrossCount = therapy.getItemGrossCount();
		Double itemAmt = therapy.getItemAmt();
		String itemType = therapy.getItemType();
		String owner = therapy.getOwner();

		/*@lineinfo:generated-code*//*@lineinfo:119^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_THERAPY")
//  			SET
//  			    THERAPY_DIMENSION = :therapyDimension,
//  			    ITEM_DIMENSION = :itemDimension,
//  			    UNIT_CONVERT_FACTOR = :unitConvertFactor,
//  			    ITEM_COUNT = :itemCount,
//  			    ITEM_USEAGE_RATE = :itemUsageRate,
//  			    ITEM_GROSS_COUNT = :itemGrossCount,
//  			    ITEM_AMT = :itemAmt
//  			WHERE 
//  		    	THERAPY_ID = :therapyId
//  			AND	ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = therapyDimension;
  String __sJT_3 = itemDimension;
  Double __sJT_4 = unitConvertFactor;
  Double __sJT_5 = itemCount;
  Double __sJT_6 = itemUsageRate;
  Double __sJT_7 = itemGrossCount;
  Double __sJT_8 = itemAmt;
  String __sJT_9 = therapyId;
  String __sJT_10 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:133^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateEntity(Therapy therapy,Double ratio)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String itemId = therapy.getItemId();
		String itemDimension = therapy.getItemDimension();
		Double unitConvertFactor = therapy.getUnitConvertFactor();
		Double itemCount = therapy.getItemCount();
		Double itemUsageRate = therapy.getItemUsageRate();
		Double itemGrossCount = therapy.getItemGrossCount();

		/*@lineinfo:generated-code*//*@lineinfo:150^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T2_THERAPY") t
//  			SET
//  			    t.ITEM_DIMENSION = :itemDimension,
//  			    t.UNIT_CONVERT_FACTOR = :unitConvertFactor,
//  			    t.ITEM_COUNT =t.ITEM_COUNT*:ratio,
//  			    t.ITEM_GROSS_COUNT =t.ITEM_GROSS_COUNT*:ratio
//  			WHERE 
//  		    	ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = itemDimension;
  Double __sJT_3 = unitConvertFactor;
  Double __sJT_4 = ratio;
  Double __sJT_5 = ratio;
  String __sJT_6 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setDoubleWrapper(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:160^3*/
		
		/*@lineinfo:generated-code*//*@lineinfo:162^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T2_THERAPY") t
//  			SET
//  				ITEM_GROSS_COUNT = ITEM_COUNT*100/(ITEM_USEAGE_RATE*UNIT_CONVERT_FACTOR)
//  			WHERE 
//  		    	ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 3);
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
	
	/**
	 * 删除除此之外的其它记录
	 */
	public int deleteExcept(String therapyId, List<String> itemIdLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " 1=1 ";
		if (!itemIdLst.isEmpty()) {
			query = " ITEM_ID NOT IN ('" + StringUtils.join(itemIdLst, ",").replaceAll(",", "','") + "') ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:186^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_THERAPY")
//  			WHERE
//  		    	THERAPY_ID = :therapyId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = therapyId;
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:193^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteEntity(String therapyId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:202^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_THERAPY")
//  			WHERE
//  		    	THERAPY_ID = :therapyId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = therapyId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:208^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:214^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class TherapyIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TherapyIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    therapy_idNdx = findColumn("therapy_id");
    therapy_nameNdx = findColumn("therapy_name");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    stock_dimensionNdx = findColumn("stock_dimension");
    item_dimensionNdx = findColumn("item_dimension");
    unit_convert_factorNdx = findColumn("unit_convert_factor");
    item_categoryNdx = findColumn("item_category");
    item_countNdx = findColumn("item_count");
    item_useage_rateNdx = findColumn("item_useage_rate");
    item_gross_countNdx = findColumn("item_gross_count");
    item_priceNdx = findColumn("item_price");
    item_amtNdx = findColumn("item_amt");
    item_typeNdx = findColumn("item_type");
  }
  public String therapy_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_idNdx);
  }
  private int therapy_idNdx;
  public String therapy_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_nameNdx);
  }
  private int therapy_nameNdx;
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
  public String stock_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(stock_dimensionNdx);
  }
  private int stock_dimensionNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Double unit_convert_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_convert_factorNdx);
  }
  private int unit_convert_factorNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double item_useage_rate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_useage_rateNdx);
  }
  private int item_useage_rateNdx;
  public Double item_gross_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_gross_countNdx);
  }
  private int item_gross_countNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double item_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_amtNdx);
  }
  private int item_amtNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:229^2*/

	/**
	 * 根据ID获取配方明细信息项
	 * 
	 * @param therapyId
	 * @param itemId
	 */
	public Therapy queryDetail(String therapyId, String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Therapy therapy = null;
		DefaultContext myCtx = getDefaultContext();
		TherapyIter therapyIter = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:244^4*/

//  ************************************************************
//  #sql [myCtx] therapyIter = { SELECT
//  				    t.*,
//  				    '' AS stock_dimension,
//  				    '' AS item_category,
//  				    0 AS item_price
//  				FROM
//  				:Com_("D_T2_THERAPY") t
//  				WHERE
//  				    THERAPY_ID = :therapyId
//  				AND ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = therapyId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      therapyIter = new TherapyIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:255^4*/
			if(therapyIter.next()) {
				therapy = new Therapy();
				therapy.setTherapyId(therapyIter.therapy_id());
				therapy.setItemId(therapyIter.item_id());
				therapy.setItemName(therapyIter.item_name());
				therapy.setItemDimension(therapyIter.item_dimension());
				therapy.setItemCount(therapyIter.item_count());
			}
			therapyIter.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return therapy;
	}

	/*@lineinfo:generated-code*//*@lineinfo:274^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class TherapyItem 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TherapyItem(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    therapy_idNdx = findColumn("therapy_id");
    therapy_nameNdx = findColumn("therapy_name");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    stock_dimensionNdx = findColumn("stock_dimension");
    item_dimensionNdx = findColumn("item_dimension");
    unit_convert_factorNdx = findColumn("unit_convert_factor");
    item_categoryNdx = findColumn("item_category");
    item_countNdx = findColumn("item_count");
    item_useage_rateNdx = findColumn("item_useage_rate");
    item_gross_countNdx = findColumn("item_gross_count");
    purchase_priceNdx = findColumn("purchase_price");
    purchaseAmtNdx = findColumn("purchaseAmt");
    benchmark_priceNdx = findColumn("benchmark_price");
    benchmarkAmtNdx = findColumn("benchmarkAmt");
    item_amtNdx = findColumn("item_amt");
    item_typeNdx = findColumn("item_type");
    existFlagNdx = findColumn("existFlag");
  }
  public String therapy_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_idNdx);
  }
  private int therapy_idNdx;
  public String therapy_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_nameNdx);
  }
  private int therapy_nameNdx;
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
  public String stock_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(stock_dimensionNdx);
  }
  private int stock_dimensionNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Double unit_convert_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_convert_factorNdx);
  }
  private int unit_convert_factorNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double item_useage_rate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_useage_rateNdx);
  }
  private int item_useage_rateNdx;
  public Double item_gross_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_gross_countNdx);
  }
  private int item_gross_countNdx;
  public Double purchase_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(purchase_priceNdx);
  }
  private int purchase_priceNdx;
  public Double purchaseAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(purchaseAmtNdx);
  }
  private int purchaseAmtNdx;
  public Double benchmark_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(benchmark_priceNdx);
  }
  private int benchmark_priceNdx;
  public Double benchmarkAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(benchmarkAmtNdx);
  }
  private int benchmarkAmtNdx;
  public Double item_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_amtNdx);
  }
  private int item_amtNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
  public String existFlag() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(existFlagNdx);
  }
  private int existFlagNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:293^3*/
	
	//根据配方编码获取属于该配方的明细信息项集合
	public List<Therapy> GetTherapysById(String therapyId,String hasSum) 
			throws NoPrivilegeException,SQLException,NoConnection
	{
		List<Therapy> result = new ArrayList<Therapy>();
		DefaultContext myCtx = getDefaultContext();
		TherapyItem therapyIter = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:304^4*/

//  ************************************************************
//  #sql [myCtx] therapyIter = { SELECT
//  				  	t.THERAPY_ID,
//  				    t.THERAPY_NAME,
//  				    t.THERAPY_DIMENSION,
//  				    t.ITEM_ID,
//  				    m.ITEM_NAME,
//  				    t.ITEM_DIMENSION,
//  				    t.UNIT_CONVERT_FACTOR,
//  				    t.ITEM_COUNT,
//  				    t.ITEM_USEAGE_RATE,
//  				    DECODE(NVL(t.UNIT_CONVERT_FACTOR,0),0,'',ROUND(t.ITEM_COUNT*100/t.ITEM_USEAGE_RATE,2)) ITEM_GROSS_COUNT,
//  				    t.ITEM_AMT,
//  				    t.ITEM_TYPE,
//  				    t.OWNER,
//  				    m.ITEM_DIMENSION                               AS stock_dimension,
//  				    m.CATEGORY_ID                                   AS item_category,
//  				    p.ITEM_PRICE                                    AS purchase_price,
//  				    SUM(ROUND(t.ITEM_GROSS_COUNT* p.ITEM_PRICE,4))     purchaseAmt ,
//  				    bp.ITEM_PRICE                                   AS benchmark_price,
//  				    SUM(ROUND(t.ITEM_GROSS_COUNT*bp.ITEM_PRICE ,4))    benchmarkAmt ,
//  				    DECODE(m.ITEM_ID,NULL,'不存在','')                    existFlag
//  				FROM
//  				:Com_("d_t2_therapy") t
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_META") m
//  				ON
//  				    m.ITEM_ID = t.ITEM_ID
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p
//  				ON
//  				    t.ITEM_ID = p.ITEM_ID
//  				AND p.PRICE_TYPE = 'PURCHASE'  --进货价
//  				AND p.IS_CURRENT =1
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") bp
//  				ON
//  				    t.ITEM_ID = bp.ITEM_ID
//  				AND bp.PRICE_TYPE = 'BENCHMARK'  --标准价
//  				AND bp.IS_CURRENT =1
//  				WHERE 
//  					t.therapy_id = :therapyId
//  				GROUP BY
//  			    GROUPING SETS( ( t.THERAPY_ID, t.THERAPY_NAME, t.THERAPY_DIMENSION, t.ITEM_ID, m.ITEM_NAME,
//  			    	    t.ITEM_DIMENSION, t.UNIT_CONVERT_FACTOR, t.ITEM_COUNT, t.ITEM_USEAGE_RATE, t.ITEM_GROSS_COUNT,
//  			    	    t.ITEM_AMT, t.ITEM_TYPE, t.OWNER, m.ITEM_DIMENSION, m.CATEGORY_ID ,p.ITEM_PRICE, ROUND
//  			    	    (t.ITEM_GROSS_COUNT* p.ITEM_PRICE,4) ,bp.ITEM_PRICE, ROUND(t.ITEM_GROSS_COUNT*bp.ITEM_PRICE ,4)
//  			    	    , DECODE(m.ITEM_ID,NULL,'不存在','')) ,NULL)
//  				ORDER BY
//  					t.ITEM_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_therapy");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = therapyId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      therapyIter = new TherapyItem(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:354^4*/
			while(therapyIter.next())
			{
				Therapy therapy = new Therapy();
				therapy.setTherapyId(therapyIter.therapy_id());
				therapy.setTherapyName(therapyIter.therapy_name());
				therapy.setItemId(therapyIter.item_id());
				therapy.setItemName(therapyIter.item_name());
				therapy.setStockDimension(therapyIter.stock_dimension());
				therapy.setItemDimension(therapyIter.item_dimension());
				therapy.setUnitConvertFactor(therapyIter.unit_convert_factor());
				therapy.setItemCategory(therapyIter.item_category());
				therapy.setItemCount(therapyIter.item_count());
				therapy.setItemUsageRate(therapyIter.item_useage_rate());
				therapy.setItemGrossCount(therapyIter.item_gross_count());
				therapy.setItemAmt(therapyIter.item_amt());
				therapy.setItemType(therapyIter.item_type());
				therapy.setPurchasePrice(therapyIter.purchase_price());
				therapy.setPurchaseAmt(therapyIter.purchaseAmt());
				therapy.setBenchmarkAmt(therapyIter.benchmarkAmt());
				therapy.setBenchmarkPrice(therapyIter.benchmark_price());
				therapy.setExistFlag(therapyIter.existFlag());
				result.add(therapy);
			}
			
			if(result.size()>0){
				if(TextUtil.isEmpty(hasSum)){
					result.remove(result.size()-1);
				}
			}
			
			therapyIter.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	//根据配方编码获取属于该配方的明细信息项集合
	public List<Therapy> GetTherapysById(String therapyId) 
			throws NoPrivilegeException,SQLException,NoConnection
	{
		List<Therapy> result = new ArrayList<Therapy>();
		DefaultContext myCtx = getDefaultContext();
		TherapyItem therapyIter = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:404^4*/

//  ************************************************************
//  #sql [myCtx] therapyIter = { SELECT
//  					t.THERAPY_ID,
//  				    t.THERAPY_NAME,
//  				    t.THERAPY_DIMENSION,
//  				    t.ITEM_ID,
//  				    m.ITEM_NAME ITEM_NAME,
//  				    t.ITEM_DIMENSION,
//  				    t.UNIT_CONVERT_FACTOR,
//  				    t.ITEM_COUNT,
//  				    t.ITEM_USEAGE_RATE,
//  				    t.ITEM_GROSS_COUNT,
//  				    t.ITEM_AMT,
//  				    t.ITEM_TYPE,
//  				    t.OWNER,
//  				    m.ITEM_DIMENSION AS stock_dimension,
//  				    m.CATEGORY_ID AS item_category,
//  				    p.ITEM_PRICE AS purchase_price,
//  				    bp.ITEM_PRICE AS benchmark_price,
//  				    '' purchaseAmt,
//  				    '' benchmarkAmt,
//  				    decode(m.ITEM_ID,null,'不存在','') existFlag
//  				FROM
//  				:Com_("d_t2_therapy") t
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_META") m
//  				ON
//  				    m.ITEM_ID = t.ITEM_ID
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p
//  				ON
//  				    t.ITEM_ID = p.ITEM_ID
//  				AND p.PRICE_TYPE = 'PURCHASE'  --进货价
//  				AND p.IS_CURRENT =1
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") bp
//  				ON
//  				    t.ITEM_ID = bp.ITEM_ID
//  				AND bp.PRICE_TYPE = 'BENCHMARK'  --标准价
//  				AND bp.IS_CURRENT =1
//  				WHERE 
//  					t.therapy_id = :therapyId
//  				ORDER BY
//  					t.ITEM_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_therapy");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = therapyId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      therapyIter = new TherapyItem(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:448^4*/
			while(therapyIter.next())
			{
				Therapy therapy = new Therapy();
				therapy.setTherapyId(therapyIter.therapy_id());
				therapy.setTherapyName(therapyIter.therapy_name());
				therapy.setItemId(therapyIter.item_id());
				therapy.setItemName(therapyIter.item_name());
				therapy.setStockDimension(therapyIter.stock_dimension());
				therapy.setItemDimension(therapyIter.item_dimension());
				therapy.setUnitConvertFactor(therapyIter.unit_convert_factor());
				therapy.setItemCategory(therapyIter.item_category());
				therapy.setItemCount(therapyIter.item_count());
				therapy.setItemUsageRate(therapyIter.item_useage_rate());
				therapy.setItemGrossCount(therapyIter.item_gross_count());
				therapy.setItemAmt(therapyIter.item_amt());
				therapy.setItemType(therapyIter.item_type());
				therapy.setPurchasePrice(therapyIter.purchase_price());
				therapy.setBenchmarkPrice(therapyIter.benchmark_price());
				therapy.setPurchaseAmt(therapyIter.purchaseAmt());
				therapy.setBenchmarkAmt(therapyIter.benchmarkAmt());
				therapy.setExistFlag(therapyIter.existFlag());
				result.add(therapy);
			}
			therapyIter.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	
	/*@lineinfo:generated-code*//*@lineinfo:483^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class TherapyItems 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TherapyItems(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    therapy_idNdx = findColumn("therapy_id");
    item_gross_countNdx = findColumn("item_gross_count");
    unit_convert_factorNdx = findColumn("unit_convert_factor");
    item_nameNdx = findColumn("item_name");
    therapy_nameNdx = findColumn("therapy_name");
    item_specificationNdx = findColumn("item_specification");
    item_dimensionNdx = findColumn("item_dimension");
    therapy_dimensionNdx = findColumn("therapy_dimension");
    item_categoryNdx = findColumn("item_category");
    benchmark_priceNdx = findColumn("benchmark_price");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String therapy_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_idNdx);
  }
  private int therapy_idNdx;
  public Double item_gross_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_gross_countNdx);
  }
  private int item_gross_countNdx;
  public Double unit_convert_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_convert_factorNdx);
  }
  private int unit_convert_factorNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String therapy_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_nameNdx);
  }
  private int therapy_nameNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String therapy_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_dimensionNdx);
  }
  private int therapy_dimensionNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public Double benchmark_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(benchmark_priceNdx);
  }
  private int benchmark_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:484^164*/
	//根据配方编码获取属于该配方的明细信息项集合
	public List<Therapy> GetTherapyItems(String therapyIds) 
			throws NoPrivilegeException,SQLException,NoConnection
	{
		String query = " t.THERAPY_ID IN ('" + therapyIds.replaceAll(",", "','") + "') ";
		List<Therapy> result = new ArrayList<Therapy>();
		DefaultContext myCtx = getDefaultContext();
		TherapyItems therapyIter = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:495^4*/

//  ************************************************************
//  #sql [myCtx] therapyIter = { SELECT
//  				    m.ITEM_ID,
//  				    m.ITEM_NAME,
//  				    m.ITEM_SPECIFICATION,
//  				    m.ITEM_DIMENSION AS item_dimension,
//  				    m.CATEGORY_ID    AS item_category,
//  				    bp.ITEM_PRICE    AS benchmark_price,
//  				    t.ITEM_GROSS_COUNT,
//  				    t.UNIT_CONVERT_FACTOR,
//  				    t.THERAPY_ID ,
//  				    m2.ITEM_NAME THERAPY_NAME ,
//  				    nvl(t.THERAPY_DIMENSION,' ') THERAPY_DIMENSION
//  				FROM
//  				:Com_("d_t2_therapy") t
//  				INNER JOIN
//  				:Com_("D_T2_ITEM_META") m
//  				ON
//  				    m.ITEM_ID = t.ITEM_ID
//  	    		INNER JOIN
//  					:Com_("D_T2_ITEM_META") m2
//  				ON
//  				    m2.ITEM_ID = t.THERAPY_ID
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") bp
//  				ON
//  				    t.ITEM_ID = bp.ITEM_ID
//  				AND bp.PRICE_TYPE = 'BENCHMARK' --标准价
//  				AND bp.IS_CURRENT =1
//  				WHERE 
//  					:query
//  				ORDER BY
//  					t.THERAPY_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_therapy");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      therapyIter = new TherapyItems(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:528^4*/
			while(therapyIter.next())
			{
				Therapy therapy = new Therapy();
				therapy.setItemId(therapyIter.item_id());
				therapy.setItemName(therapyIter.item_name());
				therapy.setItemDimension(therapyIter.item_dimension());
				therapy.setTherapyId(therapyIter.therapy_id());
				therapy.setTherapyName(therapyIter.therapy_name());
				therapy.setTherapyDimension(therapyIter.therapy_dimension());
				therapy.setItemSpecification(therapyIter.item_specification());
				therapy.setItemCategory(therapyIter.item_category());
				therapy.setBenchmarkPrice(therapyIter.benchmark_price());
				therapy.setItemGrossCount(therapyIter.item_gross_count());
				therapy.setUnitConvertFactor(therapyIter.unit_convert_factor());
				result.add(therapy);
			}
			therapyIter.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:555^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class Therapys 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public Therapys(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    therapy_idNdx = findColumn("therapy_id");
    item_gross_countNdx = findColumn("item_gross_count");
    unit_convert_factorNdx = findColumn("unit_convert_factor");
    item_nameNdx = findColumn("item_name");
    therapy_nameNdx = findColumn("therapy_name");
    purchase_priceNdx = findColumn("purchase_price");
    item_amtNdx = findColumn("item_amt");
    item_priceNdx = findColumn("item_price");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String therapy_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_idNdx);
  }
  private int therapy_idNdx;
  public Double item_gross_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_gross_countNdx);
  }
  private int item_gross_countNdx;
  public Double unit_convert_factor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_convert_factorNdx);
  }
  private int unit_convert_factorNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String therapy_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_nameNdx);
  }
  private int therapy_nameNdx;
  public Double purchase_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(purchase_priceNdx);
  }
  private int purchase_priceNdx;
  public Double item_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_amtNdx);
  }
  private int item_amtNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:556^97*/
	
	//根据原料编码获取包含此原料的半成品项集合
		public List<Therapy> GetTherapys(String itemIds) 
				throws NoPrivilegeException,SQLException,NoConnection
		{
			String query = " t.ITEM_ID IN ('" + itemIds.replaceAll(",", "','") + "') ";
			List<Therapy> result = new ArrayList<Therapy>();
			DefaultContext myCtx = getDefaultContext();
			Therapys therapyIter = null;
			if (myCtx != null)
			{
				/*@lineinfo:generated-code*//*@lineinfo:568^5*/

//  ************************************************************
//  #sql [myCtx] therapyIter = { SELECT
//  					    t.ITEM_ID,
//  					    t.ITEM_NAME,
//  					    nvl(bp.ITEM_PRICE,0)    AS purchase_price,
//  					    nvl(p.ITEM_PRICE,0) AS ITEM_PRICE,
//  					    t.ITEM_GROSS_COUNT,
//  					    t.UNIT_CONVERT_FACTOR,
//  					    t.THERAPY_ID ,
//  					    t.THERAPY_NAME,
//  					    t.ITEM_GROSS_COUNT / t.UNIT_CONVERT_FACTOR * p.ITEM_PRICE ITEM_AMT
//  					FROM
//  					:Com_("d_t2_therapy") t
//  					INNER JOIN
//  					:Com_("D_T2_ITEM_META") m
//  				    ON
//  				    	t.THERAPY_ID=m.ITEM_ID 
//  				    	and m.ITEM_TYPE='SEMIS'
//  					INNER JOIN
//  					:Com_("D_T2_ITEM_PRICE") p
//  					ON
//  					    t.ITEM_ID = p.ITEM_ID
//  					AND p.PRICE_TYPE = 'PURCHASE' --进货价
//  					LEFT JOIN
//  					:Com_("D_T2_ITEM_PRICE") bp
//  					ON
//  					    t.THERAPY_ID = bp.ITEM_ID
//  					AND bp.PRICE_TYPE = 'PURCHASE' --进货价
//  					AND bp.IS_CURRENT =1
//  					WHERE 
//  						:query
//  					ORDER BY
//  						t.THERAPY_ID
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_therapy");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      therapyIter = new Therapys(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:601^5*/
				while(therapyIter.next())
				{
					Therapy therapy = new Therapy();
					therapy.setItemId(therapyIter.item_id());
					therapy.setItemName(therapyIter.item_name());
					therapy.setTherapyId(therapyIter.therapy_id());
					therapy.setTherapyName(therapyIter.therapy_name());
					therapy.setPurchasePrice(therapyIter.purchase_price());
					therapy.setItemAmt(therapyIter.item_amt());
					therapy.setItemPrice(therapyIter.item_price());
					therapy.setItemGrossCount(therapyIter.item_gross_count());
					therapy.setUnitConvertFactor(therapyIter.unit_convert_factor());
					result.add(therapy);
				}
				therapyIter.close();
			}
			else
			{
				throw new NoConnection();
			}
			closeDefaultContext(myCtx);
			return result;
		}

		
		/*@lineinfo:generated-code*//*@lineinfo:627^3*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class TherapyAmtItem 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TherapyAmtItem(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    therapy_idNdx = findColumn("therapy_id");
    purchase_priceNdx = findColumn("purchase_price");
  }
  public String therapy_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(therapy_idNdx);
  }
  private int therapy_idNdx;
  public Double purchase_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(purchase_priceNdx);
  }
  private int purchase_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:630^4*/
		//根据配方编码获取该配方的成本价集合
		public List<Therapy> GetTherapysAmt(String therapyIds,String itemType) 
				throws NoPrivilegeException,SQLException,NoConnection
		{
			String query="1=1";
			if(!TextUtil.isEmpty(therapyIds)){
				query = " t.THERAPY_ID IN ('" + therapyIds.replaceAll(",", "','") + "') ";
			}
			List<Therapy> result = new ArrayList<Therapy>();
			DefaultContext myCtx = getDefaultContext();
			TherapyAmtItem therapyIter = null;
			if (myCtx != null)
			{
				/*@lineinfo:generated-code*//*@lineinfo:644^5*/

//  ************************************************************
//  #sql [myCtx] therapyIter = { WITH
//  					    item AS
//  					    (
//  					        SELECT
//  					            t.THERAPY_ID,
//  					            SUM(ROUND(t.ITEM_GROSS_COUNT* p.ITEM_PRICE,4)) purchaseAmt
//  					        FROM
//  					        :Com_("d_t2_therapy") t
//  					        INNER JOIN
//  					        :Com_("D_T2_ITEM_META") m
//  					        ON
//  					            m.ITEM_ID = t.THERAPY_ID 
//  					            AND m.ITEM_TYPE=:itemType
//  					        LEFT JOIN
//  					        :Com_("D_T2_ITEM_PRICE") p
//  					        ON
//  					            t.ITEM_ID = p.ITEM_ID
//  					        AND p.PRICE_TYPE = 'PURCHASE' --进货价
//  					        AND p.IS_CURRENT =1
//  			        		WHERE 
//  							:query
//  					        GROUP BY
//  					            t.THERAPY_ID,
//  					            t.ITEM_GROSS_COUNT,
//  					            p.ITEM_PRICE
//  					        ORDER BY
//  					            t.THERAPY_ID
//  					    )
//  					SELECT
//  					    i.therapy_id,
//  					    SUM(i.purchaseAmt) purchase_price
//  					FROM
//  					    item i
//  					GROUP BY
//  					    i.therapy_id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_therapy");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = itemType;
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      therapyIter = new TherapyAmtItem(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:679^22*/
				while(therapyIter.next())
				{
					Therapy therapy = new Therapy();
					therapy.setTherapyId(therapyIter.therapy_id());
					therapy.setPurchasePrice(therapyIter.purchase_price());
					result.add(therapy);
				}
				therapyIter.close();
			}
			else
			{
				throw new NoConnection();
			}
			closeDefaultContext(myCtx);
			return result;
		}
		
		
		public List<Therapy> GetTherapys(String itemIds,String itemType) 
				throws NoPrivilegeException,SQLException,NoConnection
		{
			String query="1=1";
			if(!TextUtil.isEmpty(itemIds)){
				query = "  m.ITEM_ID IN ('" + itemIds.replaceAll(",", "','") + "') ";
			}
			List<Therapy> result = new ArrayList<Therapy>();
			DefaultContext myCtx = getDefaultContext();
			TherapyAmtItem therapyIter = null;
			if (myCtx != null)
			{
				/*@lineinfo:generated-code*//*@lineinfo:710^5*/

//  ************************************************************
//  #sql [myCtx] therapyIter = { WITH
//  					    item AS
//  					    (
//  					        SELECT DISTINCT
//  					            t.THERAPY_ID
//  					        FROM
//  					        	:Com_("D_T2_THERAPY") t
//  					        INNER JOIN
//  					        	:Com_("D_T2_ITEM_META") m
//  					        ON
//  					            m.ITEM_ID = t.ITEM_ID
//  			        		WHERE 
//  							:query
//  					    )
//  					    ,
//  					    item2 AS
//  					    (
//  					    		SELECT
//  					            t.THERAPY_ID,
//  					            SUM(ROUND(t.ITEM_GROSS_COUNT* p.ITEM_PRICE,4)) purchaseAmt
//  					        FROM
//  					        :Com_("d_t2_therapy") t
//  					        INNER JOIN
//  					        :Com_("D_T2_ITEM_META") m
//  					        ON
//  					        m.ITEM_ID = t.THERAPY_ID 
//  					        AND m.ITEM_TYPE=:itemType
//  					        LEFT JOIN
//  					        :Com_("D_T2_ITEM_PRICE") p
//  					        ON
//  					            t.ITEM_ID = p.ITEM_ID
//  					        AND p.PRICE_TYPE = 'PURCHASE' --进货价
//  					        AND p.IS_CURRENT =1
//  					        INNER JOIN
//  					            item a
//  					        ON
//  					            a.THERAPY_ID=t.THERAPY_ID
//  					        GROUP BY
//  					            t.THERAPY_ID,
//  					            t.ITEM_GROSS_COUNT,
//  					            p.ITEM_PRICE
//  					        ORDER BY
//  					            t.THERAPY_ID
//  					    )
//  					SELECT
//  					    i.therapy_id,
//  					    SUM(i.purchaseAmt) purchase_price
//  					FROM
//  					    item2 i
//  					GROUP BY
//  					    i.therapy_id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = query;
  String __sJT_4 = Com_("d_t2_therapy");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = itemType;
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      therapyIter = new TherapyAmtItem(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:761^22*/
				while(therapyIter.next())
				{
					Therapy therapy = new Therapy();
					therapy.setTherapyId(therapyIter.therapy_id());
					therapy.setPurchasePrice(therapyIter.purchase_price());
					result.add(therapy);
				}
				therapyIter.close();
			}
			else
			{
				throw new NoConnection();
			}
			closeDefaultContext(myCtx);
			return result;
		}
	/**
	 * 根据配方编码累计该出口的成本价格
	 */
	public Double getItemAmt(String therapyId) 
		throws NoPrivilegeException,SQLException,NoConnection
	{
		Double sum = null;
		DefaultContext myCtx = getDefaultContext();
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:788^4*/

//  ************************************************************
//  #sql [myCtx] { select sum(item_amt)  
//  				from :Com_("d_t2_therapy")
//  			where therapy_id = :therapyId };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_therapy");
  String __sJT_2 = therapyId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 13);
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
    sum = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:790^33*/
		}
		else
		{
			throw new NoConnection();
		}
		myCtx.close();
		return sum;
	}
	
	
	/*@lineinfo:generated-code*//*@lineinfo:801^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class NodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public NodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parent_idNdx = findColumn("parent_id");
    isLeafNdx = findColumn("isLeaf");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(nameNdx);
  }
  private int nameNdx;
  public String parent_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parent_idNdx);
  }
  private int parent_idNdx;
  public String isLeaf() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(isLeafNdx);
  }
  private int isLeafNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:801^91*/
	
	public void queryTree(String therapyId,JSONArray arr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		List<String> idLists = new ArrayList<String>();
		
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:810^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  			    items AS
//  			    (
//  			        SELECT
//  			            LEVEL        levelNum,
//  			            t.THERAPY_ID parent_id,
//  			            t.ITEM_ID    id,
//  			            t.ITEM_NAME  name
//  			        FROM
//  			        :Com_("d_t2_therapy") t 
//  			        CONNECT BY PRIOR t.ITEM_ID = t.THERAPY_ID 
//  			        START WITH t.THERAPY_ID = :therapyId
//  			    )
//  			SELECT
//  			    i.*,
//  			    DECODE(m.ITEM_TYPE,'','Y','RAW','Y','SEMIS','N') isLeaf
//  			FROM
//  			    items i
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    i.id = m.ITEM_ID
//  			ORDER BY
//  			    i.levelNum,
//  			    i.parent_id,
//  			    isLeaf DESC,
//  			    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_therapy");
  String __sJT_2 = therapyId;
  String __sJT_3 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 14);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:837^17*/
		
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			JSONObject item = new JSONObject();
			item.put("id",nodeIter.id());
			item.put("name",nodeIter.name());
			item.put("parent_id",nodeIter.parent_id());
			item.put("isLeaf",nodeIter.isLeaf());
			arr.add(item);
		}
		
		nodeIter.close();
		closeDefaultContext(myCtx);
	}
	

	/*@lineinfo:generated-code*//*@lineinfo:854^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private static class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    shelf_nameNdx = findColumn("shelf_name");
    item_typeNdx = findColumn("item_type");
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
    box_typeNdx = findColumn("box_type");
    enabledNdx = findColumn("enabled");
    infosNdx = findColumn("infos");
    mainSuppilerNdx = findColumn("mainSuppiler");
    item_countNdx = findColumn("item_count");
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
  public String shelf_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_nameNdx);
  }
  private int shelf_nameNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public String box_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_typeNdx);
  }
  private int box_typeNdx;
  public Integer enabled() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(enabledNdx);
  }
  private int enabledNdx;
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
  public String mainSuppiler() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(mainSuppilerNdx);
  }
  private int mainSuppilerNdx;
  public Integer item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(item_countNdx);
  }
  private int item_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:858^55*/
	
	public int countItemByCate(String categoryId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		int counts = 0;
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:865^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//          			COUNT(*)
//      			
//          	    FROM
//          	    :Com_("D_T2_ITEM_META") h
//          	    WHERE
//  			    h.CATEGORY_ID = :categoryId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = categoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 15);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:874^3*/
		closeDefaultContext(myCtx);
		return counts;
	}
	
	/**
	 * 根据类别查询商品列表，显示是否存在成本卡
	 */
	public List<Map> queryItemByCate(String categoryId, int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:886^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  	    *
//  	    FROM
//  	        (
//  	            SELECT
//  	                t.*,
//  	                ROWNUM rowNumber
//  	            FROM
//  	                (
//  				WITH
//  	    	    d AS
//  	    	    (
//      	    		 SELECT
//  	    	             ITEM_ID,
//  	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  	    	            (ORDER BY PRICE_TYPE) infos
//  	    	        FROM
//  	    	            (
//  	    	            		 SELECT DISTINCT
//   	    	                    ps.ITEM_ID,
//   	    	                    ps.PRICE_TYPE,
//   	    	                   '供应商价' PRICE_GROUP_NAME,
//   	    	                   nvl(ps.ITEM_PRICE, 0) ITEM_PRICE
//   	    	                FROM
//   	    	                :Com_("D_T2_ITEM_PRICE") ps
//   	    	               INNER JOIN 
//   	    	              :Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//   	    	              ON
//   	    	              s.ITEM_ID = ps.ITEM_ID
//   	            		  WHERE
//  	    	                      ps.PRICE_TYPE ='SUPPLIER'
//  	    	                  AND s.PRIORITY =0
//  	    	                  AND s.BRANCH_ID='L00'
//  	    	                  AND s.SUPPLIER_ID=ps.SUPPLIER_ID
//   	    	             UNION ALL
//  	    	            		SELECT
//  	    	                    p.ITEM_ID,
//  	    	                    p.PRICE_TYPE,
//  	    	                    g.PRICE_GROUP_NAME,
//  	    	                    nvl(p.ITEM_PRICE, 0) ITEM_PRICE
//  	    	                FROM
//  	    	                :Com_("D_T2_ITEM_PRICE") p
//  	    	                LEFT JOIN
//  	    	                :Com_("D_T2_PRICE_GROUP")  g
//  	    	                ON
//  	    	                    p.PRICE_TYPE = g.PRICE_GROUP_ID
//   	                    WHERE
//   	                   (p.SUPPLIER_ID IS NULL or p.SUPPLIER_ID='NULL'))
//  	    	        GROUP BY
//  	    	          ITEM_ID
//  	    	    )
//  			SELECT
//  			    m.*,
//  			    c.CATEGORY_NAME,
//  			    sf.SHELF_NAME,
//  			    d.infos,
//  			    b.BRANCH_NAME    mainSuppiler,
//  			    t2.item_count
//  			    FROM
//  				:Com_("D_T2_ITEM_META") m
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_CATEGORY") c
//  				ON
//  				    c.CATEGORY_ID = m.CATEGORY_ID
//  				LEFT JOIN
//  				:Com_("D_T2_SHELF_ITEM") si 
//  				ON
//  				    si.ITEM_ID= m.ITEM_ID  AND si.PRIORITY = '0'
//  				LEFT JOIN
//  				:Com_("D_T2_SHELF") sf
//  				ON
//  				    si.SHELF_ID= sf.SHELF_ID
//  				LEFT JOIN
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") bi
//  				ON
//  				    m.ITEM_ID = bi.ITEM_ID
//  				AND bi.BRANCH_ID = 'L00'
//  				AND bi.PRIORITY = 0
//  				LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  				ON
//  				    b.BRANCH_ID = bi.SUPPLIER_ID
//  	    		LEFT JOIN
//  	    			d
//  	    		ON 
//  	    			d.ITEM_ID = m.ITEM_ID
//      			LEFT JOIN
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            COUNT(*) AS item_count
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        WHERE
//  			            t.OWNER = 'CENTRALFACTORY'
//  			        GROUP BY
//  			            t.THERAPY_ID) t2
//  			ON
//  			    m.ITEM_ID = t2.THERAPY_ID
//  			WHERE
//  			    m.CATEGORY_ID = :categoryId
//  			ORDER BY
//  				m.ITEM_ID) t
//  	        	            WHERE
//  	                        ROWNUM < :endRow)
//  	            WHERE
//  	                rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_SHELF_ITEM");
  String __sJT_8 = Com_("D_T2_SHELF");
  String __sJT_9 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = Com_("D_T2_THERAPY");
  String __sJT_12 = categoryId;
  int __sJT_13 = endRow;
  int __sJT_14 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 16);
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
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setInt(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
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

/*@lineinfo:user-code*//*@lineinfo:993^40*/
		
		List<Map> itemLst =processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	public int countByItemName(String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		int counts = 0;
		itemName = "%" + itemName + "%";
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:1006^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//          			COUNT(*)
//      			
//          	    FROM
//          	    :Com_("D_T2_ITEM_META") m
//      			WHERE
//      			    1=1
//      			AND (
//      			        m.ITEM_ID LIKE :itemName
//      			    OR  m.ITEM_NAME LIKE :itemName)
//      			AND m.ITEM_TYPE IN (
//      			                    'SEMIS')
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 17);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:1020^3*/
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public List<Map> queryByItemName(String itemName, int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:1031^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  	    *
//  	    FROM
//  	        (
//  	            SELECT
//  	                t.*,
//  	                ROWNUM rowNumber
//  	            FROM
//  	                (
//  				WITH
//  	    	    d AS
//  	    	    (
//      	    		 SELECT
//  	    	             ITEM_ID,
//  	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  	    	            (ORDER BY PRICE_TYPE) infos
//  	    	        FROM
//  	    	            (
//  	    	            		 SELECT DISTINCT
//   	    	                    ps.ITEM_ID,
//   	    	                    ps.PRICE_TYPE,
//   	    	                   '供应商价' PRICE_GROUP_NAME,
//   	    	                   nvl(ps.ITEM_PRICE, 0) ITEM_PRICE
//   	    	                FROM
//   	    	                :Com_("D_T2_ITEM_PRICE") ps
//   	    	               INNER JOIN 
//   	    	              :Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//   	    	              ON
//   	    	              s.ITEM_ID = ps.ITEM_ID
//   	            		  WHERE
//  	    	                      ps.PRICE_TYPE ='SUPPLIER'
//  	    	                  AND s.PRIORITY =0
//  	    	                  AND s.BRANCH_ID='L00'
//  	    	                  AND s.SUPPLIER_ID=ps.SUPPLIER_ID
//   	    	             UNION ALL
//  	    	            		SELECT
//  	    	                    p.ITEM_ID,
//  	    	                    p.PRICE_TYPE,
//  	    	                    g.PRICE_GROUP_NAME,
//  	    	                    nvl(p.ITEM_PRICE, 0) ITEM_PRICE
//  	    	                FROM
//  	    	                :Com_("D_T2_ITEM_PRICE") p
//  	    	                LEFT JOIN
//  	    	                :Com_("D_T2_PRICE_GROUP")  g
//  	    	                ON
//  	    	                    p.PRICE_TYPE = g.PRICE_GROUP_ID
//   	                    WHERE
//   	                   (p.SUPPLIER_ID IS NULL or p.SUPPLIER_ID='NULL'))
//  	    	        GROUP BY
//  	    	          ITEM_ID
//  	    	    )
//  			SELECT
//  			    m.*,
//  			    c.CATEGORY_NAME,
//  			    sf.SHELF_NAME,
//  			    d.infos,
//  			    b.BRANCH_NAME    mainSuppiler,
//  			    t2.item_count
//  		    FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			LEFT JOIN
//  			:Com_("D_T2_SHELF_ITEM") si 
//  			ON
//  			    si.ITEM_ID= m.ITEM_ID  AND si.PRIORITY = '0'
//  			LEFT JOIN
//  			:Com_("D_T2_SHELF") sf
//  			ON
//  			    si.SHELF_ID= sf.SHELF_ID
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") bi
//  			ON
//  			    m.ITEM_ID = bi.ITEM_ID
//  			AND bi.BRANCH_ID = 'L00'
//  			AND bi.PRIORITY = 0
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = bi.SUPPLIER_ID
//      		LEFT JOIN
//      			d
//      		ON 
//      			d.ITEM_ID = m.ITEM_ID
//  			LEFT JOIN
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            COUNT(*) AS item_count
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        WHERE
//  			            t.OWNER = 'CENTRALFACTORY'
//  			        GROUP BY
//  			            t.THERAPY_ID) t2
//  			ON
//  			    m.ITEM_ID = t2.THERAPY_ID
//  			WHERE
//  			    m.ITEM_TYPE = 'SEMIS'
//  			AND (
//  			        m.ITEM_ID LIKE :itemName
//  			    OR  m.ITEM_NAME LIKE :itemName)
//  			ORDER BY
//  			    m.ITEM_ID) t
//  	            WHERE
//                  ROWNUM < :endRow)
//      WHERE
//          rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_SHELF_ITEM");
  String __sJT_8 = Com_("D_T2_SHELF");
  String __sJT_9 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = Com_("D_T2_THERAPY");
  String __sJT_12 = itemName;
  String __sJT_13 = itemName;
  int __sJT_14 = endRow;
  int __sJT_15 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 18);
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
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
      __sJT_stmt.setInt(15, __sJT_15);
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

/*@lineinfo:user-code*//*@lineinfo:1141^31*/
		
		List<Map> itemLst =processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:1149^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class CountIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CountIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    totalNdx = findColumn("total");
  }
  public int total() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(totalNdx);
  }
  private int totalNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1150^13*/
	
	public int countByRaw(String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		CountIter countIter =null;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:1156^3*/

//  ************************************************************
//  #sql [myCtx] countIter = { WITH
//  		    filter AS
//  		    (
//  		        SELECT DISTINCT
//  		            t.THERAPY_ID
//  		        FROM
//  		        :Com_("D_T2_THERAPY") t
//  		        WHERE
//  		            1=1
//  		        AND (
//  		                t.ITEM_ID = :itemName
//  		            OR  t.ITEM_NAME = :itemName)
//  		        ORDER BY
//  		            t.THERAPY_ID
//  		    )
//  			SELECT
//      			COUNT(*) total
//      	    FROM
//      	    filter
//      	    INNER JOIN
//      	    :Com_("D_T2_ITEM_META") m
//  		ON
//  		    filter.THERAPY_ID = m.ITEM_ID
//      	    WHERE
//      			m.ITEM_TYPE IN ('SEMIS')
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 19);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      countIter = new CountIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1182^3*/
		int total = 0;
		while(countIter.next()) {
			total= countIter.total();
		}
		countIter.close();
		closeDefaultContext(myCtx);
		return total;
	}
	public List<Map> queryByRaw(String itemName, int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:1195^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  	    *
//  	    FROM
//  	        (
//  	            SELECT
//  	                t.*,
//  	                ROWNUM rowNumber
//  	            FROM
//  	                (			
//  				WITH
//  	    	    d AS
//  	    	    (
//      	    		 SELECT
//  	    	             ITEM_ID,
//  	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  	    	            (ORDER BY PRICE_TYPE) infos
//  	    	        FROM
//  	    	            (
//  	    	            		 SELECT DISTINCT
//   	    	                    ps.ITEM_ID,
//   	    	                    ps.PRICE_TYPE,
//   	    	                   '供应商价' PRICE_GROUP_NAME,
//   	    	                   nvl(ps.ITEM_PRICE, 0) ITEM_PRICE
//   	    	                FROM
//   	    	                :Com_("D_T2_ITEM_PRICE") ps
//   	    	               INNER JOIN 
//   	    	              :Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//   	    	              ON
//   	    	              s.ITEM_ID = ps.ITEM_ID
//   	            		  WHERE
//  	    	                      ps.PRICE_TYPE ='SUPPLIER'
//  	    	                  AND s.PRIORITY =0
//  	    	                  AND s.BRANCH_ID='L00'
//  	    	                  AND s.SUPPLIER_ID=ps.SUPPLIER_ID
//   	    	             UNION ALL
//  	    	            		SELECT
//  	    	                    p.ITEM_ID,
//  	    	                    p.PRICE_TYPE,
//  	    	                    g.PRICE_GROUP_NAME,
//  	    	                    nvl(p.ITEM_PRICE, 0) ITEM_PRICE
//  	    	                FROM
//  	    	                :Com_("D_T2_ITEM_PRICE") p
//  	    	                LEFT JOIN
//  	    	                :Com_("D_T2_PRICE_GROUP")  g
//  	    	                ON
//  	    	                    p.PRICE_TYPE = g.PRICE_GROUP_ID
//   	                    WHERE
//   	                   (p.SUPPLIER_ID IS NULL or p.SUPPLIER_ID='NULL'))
//  	    	        GROUP BY
//  	    	          ITEM_ID
//  	    	    ),
//  			    filter AS
//  			    (
//  			        SELECT DISTINCT
//  			            t.THERAPY_ID
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        WHERE
//  			            1=1
//  			        AND (
//  			                t.ITEM_ID = :itemName
//  			            OR  t.ITEM_NAME = :itemName)
//  			        ORDER BY
//  			            t.THERAPY_ID
//  			    )
//  			SELECT
//  			    m.*,
//  			    c.CATEGORY_NAME,
//  			    sf.SHELF_NAME,
//  			    d.infos,
//  			    b.BRANCH_NAME    mainSuppiler,
//  			    1 item_count
//  		    FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			LEFT JOIN
//  			:Com_("D_T2_SHELF_ITEM") si 
//  			ON
//  			    si.ITEM_ID= m.ITEM_ID  AND si.PRIORITY = '0'
//  			LEFT JOIN
//  			:Com_("D_T2_SHELF") sf
//  			ON
//  			    si.SHELF_ID= sf.SHELF_ID
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") bi
//  			ON
//  			    m.ITEM_ID = bi.ITEM_ID
//  			AND bi.BRANCH_ID = 'L00'
//  			AND bi.PRIORITY = 0
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = bi.SUPPLIER_ID
//      		LEFT JOIN
//      			d
//      		ON 
//      			d.ITEM_ID = m.ITEM_ID
//  			INNER JOIN
//  			    filter
//  			ON
//  			    filter.THERAPY_ID = m.ITEM_ID
//  			WHERE
//  			    m.ITEM_TYPE = 'SEMIS'
//  			ORDER BY
//  			    m.ITEM_ID) t
//  	            WHERE
//                  ROWNUM < :endRow)
//      WHERE
//          rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP");
  String __sJT_5 = Com_("D_T2_THERAPY");
  String __sJT_6 = itemName;
  String __sJT_7 = itemName;
  String __sJT_8 = Com_("D_T2_ITEM_META");
  String __sJT_9 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_10 = Com_("D_T2_SHELF_ITEM");
  String __sJT_11 = Com_("D_T2_SHELF");
  String __sJT_12 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_13 = Com_("D_T2_BRANCH");
  int __sJT_14 = endRow;
  int __sJT_15 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TherapyBean_SJProfileKeys.getKey(0), 20);
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
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
      __sJT_stmt.setInt(15, __sJT_15);
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

/*@lineinfo:user-code*//*@lineinfo:1307^31*/
		
		List<Map> itemLst =processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	private List<Map> processIter(ItemIter itemIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(itemIter.next()) {
			Map header = new HashMap();
			header.put("itemId", itemIter.item_id());
			header.put("itemName", itemIter.item_name());
			header.put("itemDimension", itemIter.item_dimension());
			header.put("shelfName",itemIter.shelf_name());
			header.put("itemType",itemIter.item_type());
			header.put("categoryId",itemIter.category_id());
			header.put("categoryName",itemIter.category_name());
			header.put("queryCode",itemIter.query_code());
			header.put("itemSpecification",itemIter.item_specification());
			header.put("boxType",itemIter.box_type());
			header.put("enabled",itemIter.enabled());
			header.put("mainSuppiler",itemIter.mainSuppiler());
			Integer itemCount = itemIter.item_count();
			if (itemCount != null) {
				header.put("hasTherapy", true);
			}
			String info = itemIter.infos();
			if(!TextUtil.isEmpty(info)){
				String[] infos = info.toString().split(",");
				for (int i = 0; i < infos.length; i++) {
					String[] temp = infos[i].replace("(", "").replace(")", "").split("\\|");
					
					for (int j = 0; j < temp.length; j++) {
						header.put(temp[0], temp[1]);
					}
				}
			}
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class TherapyBean_SJProfileKeys 
{
  private static TherapyBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TherapyBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TherapyBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.TherapyBean_SJProfile0");
  }
}
