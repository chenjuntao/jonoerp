/*@lineinfo:filename=MaterialListBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 20, 2014 by liyzh
 * Last edited on Nov 20, 2014 by liyzh
 * 
 * 说明： 物流中心物资清单查询
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

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class MaterialListBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(MaterialListBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class MaterialIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MaterialIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    category_idNdx = findColumn("category_id");
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
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^21*/
	
	/**
	 * 查询两部分数据：
	 * 1、物流中心汇总后生成的采购单，通过汇总单，间接与要货单关联
	 * 2、直配单、越库单与要货单直接关联
	 */
	public List<Map> query(java.util.Date startDate, java.util.Date endDate, String itemName, String branchId, String supplierId)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		if (!"-1".equals(supplierId)) {
			query = query + " AND h.PROVIDER_ID = '" + supplierId + "'";
		}
		itemName = "%" + itemName + "%";
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		MaterialIter materialIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:60^3*/

//  ************************************************************
//  #sql [myCtx] materialIter = { SELECT
//  			    m.*, 0 as item_count
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			RIGHT JOIN
//  			    (
//  			    	SELECT
//  			    	    d.item_id
//  			    	FROM
//  			    	:Com_("D_T1_PURCHASING_DETAIL") d
//  			    	INNER JOIN
//  			    	:Com_("D_T1_PURCHASING_HEADER") h
//  			    	ON
//  			    	    h.FORM_ID = d.FORM_ID
//  			    	WHERE
//  			    	    (d.ITEM_ID LIKE :itemName
//  			    	OR  d.ITEM_NAME LIKE :itemName)
//  	                AND :query
//                      AND (h.FORM_TIME >= :sDate or :sDate is null)
//          			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  			    	GROUP BY
//  			    	    d.ITEM_ID
//  			        ORDER BY
//  			            1) i
//  			ON
//  			    i.item_id = m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_3 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_4 = itemName;
  String __sJT_5 = itemName;
  String __sJT_6 = query;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MaterialListBean_SJProfileKeys.getKey(0), 0);
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
      materialIter = new MaterialIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:87^3*/
		List<Map> materialLst = processIter(materialIter);
		materialIter.close();
		closeDefaultContext(myCtx);
		return materialLst;
	}

	private List<Map> processIter(MaterialIter materialIter) 
			throws SQLException {
		List<Map> materialLst = new ArrayList<Map>();
		while(materialIter.next()) {
			Map item = new HashMap<String, Object>();
			item.put("itemId", materialIter.item_id());
			item.put("itemName", materialIter.item_name());
			item.put("itemCategory", materialIter.category_id());
			item.put("itemDimension", materialIter.item_dimension());
			item.put("itemSpecification", materialIter.item_specification());
			item.put("itemCount", materialIter.item_count());
			materialLst.add(item);
		}
		return materialLst;
	}
}/*@lineinfo:generated-code*/class MaterialListBean_SJProfileKeys 
{
  private static MaterialListBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new MaterialListBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private MaterialListBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.MaterialListBean_SJProfile0");
  }
}
