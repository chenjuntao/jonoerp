/*@lineinfo:filename=DemandTTCNYBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 3, 2014 by liyzh
 * Last edited on Nov 3, 2014 by liyzh
 * 
 * 说明： 内部万元需求量查询
 */
package logic.module.lc;

import java.math.BigDecimal;
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

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;

/**
 * TTCNY is "ten thousand CNY(china yuan)"
 */
public class DemandTTCNYBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(DemandTTCNYBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

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
    ITEM_IDNdx = findColumn("ITEM_ID");
    ITEM_NAMENdx = findColumn("ITEM_NAME");
    ITEM_DIMENSIONNdx = findColumn("ITEM_DIMENSION");
    nqtyNdx = findColumn("nqty");
  }
  public String ITEM_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_IDNdx);
  }
  private int ITEM_IDNdx;
  public String ITEM_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_NAMENdx);
  }
  private int ITEM_NAMENdx;
  public String ITEM_DIMENSION() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_DIMENSIONNdx);
  }
  private int ITEM_DIMENSIONNdx;
  public BigDecimal nqty() 
    throws java.sql.SQLException 
  {
    return resultSet.getBigDecimal(nqtyNdx);
  }
  private int nqtyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^109*/ 
	
	/**
	 * 查询某个时间段所有餐厅总的要货金额
	 */
	public BigDecimal queryTotal(java.util.Date startDate, java.util.Date endDate)
			throws NoPrivilegeException,SQLException,NoConnection{

		DefaultContext myCtx = getDefaultContext();

		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		BigDecimal total = BigDecimal.ZERO;
		Object amtObj = null;
		//查询该时间段内所有餐厅总的要货金额
		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    SUM(d.PAY_AMT) 
//  			FROM
//  				:Com_("D_T1_REQUEST_HEADER") h,
//  				:Com_("D_T1_REQUEST_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = h.FORM_ID
//  			AND h.FORM_TYPE = 'request'
//  			AND (h.FORM_TIME >= :sDate or :sDate is null)
//  			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DemandTTCNYBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
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
    amtObj = __sJT_rtRs.getObject(1, Object.class);
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
		if (amtObj != null) {
			total = (BigDecimal)amtObj;
		}
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);

		closeDefaultContext(myCtx);

		return total;
	}
	
	/**
	 * 根据原材料种类查询万元需求量
	 */
	public List<Map> queryDetail(java.util.Date startDate, java.util.Date endDate, String categoryId)
			throws NoPrivilegeException,SQLException,NoConnection{

		//查询该时间段内所有餐厅总的要货金额
		BigDecimal total = queryTotal(startDate, endDate);

		DefaultContext myCtx = getDefaultContext();

		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		//查询某类别下所有的原料数量
		MaterialIter materialIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:93^3*/

//  ************************************************************
//  #sql [myCtx] materialIter = { SELECT
//  				d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    SUM(d.ORDER_COUNT) AS nqty
//  			FROM
//  				:Com_("D_T1_REQUEST_HEADER") h,
//  				:Com_("D_T1_REQUEST_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = h.FORM_ID
//  			AND h.FORM_TYPE = 'request'
//  			AND d.ITEM_CATEGORY = :categoryId
//  			AND (h.FORM_TIME >= :sDate or :sDate is null)
//  			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  			GROUP BY
//  				d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION
//  			ORDER BY
//  			    d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_3 = categoryId;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DemandTTCNYBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:114^3*/
		List<Map> detailLst = new ArrayList();
		while(materialIter.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemId", materialIter.ITEM_ID());
			map.put("itemName", materialIter.ITEM_NAME());
			map.put("unit", materialIter.ITEM_DIMENSION());

			// 每万元材料数量计算公式： amt / nqty = 10000 / 每万元材料数量				
			BigDecimal nqty = materialIter.nqty().setScale(2, BigDecimal.ROUND_HALF_UP);
			map.put("nqty", nqty);
			BigDecimal unitQty = BigDecimal.ZERO;
			if (total.compareTo(BigDecimal.ZERO) != 0) {
				unitQty = nqty.multiply(new BigDecimal(10000)).divide(total, 2, BigDecimal.ROUND_HALF_UP);
			}
			map.put("unitQty", unitQty);
			detailLst.add(map);
		}
		materialIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
}/*@lineinfo:generated-code*/class DemandTTCNYBean_SJProfileKeys 
{
  private static DemandTTCNYBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DemandTTCNYBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DemandTTCNYBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.DemandTTCNYBean_SJProfile0");
  }
}
