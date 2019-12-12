/*@lineinfo:filename=CheckDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sun Sep 28 10:22:31 CST 2014 by lyz
 * Last edited on Sun Sep 28 10:22:31 CST 2014 by lyz
 * 
 * comment: 盘点单/清单明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.CheckDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class CheckDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(CheckDetailBean.class);
	
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
    rownumberNdx = findColumn("rownumber");
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    check_countNdx = findColumn("check_count");
    theory_countNdx = findColumn("theory_count");
    expired_timeNdx = findColumn("expired_time");
    item_orderNdx = findColumn("item_order");
    item_statusNdx = findColumn("item_status");
    itemPriceNdx = findColumn("itemPrice");
    itemAmtNdx = findColumn("itemAmt");
    diffAmtNdx = findColumn("diffAmt");
    diffCountNdx = findColumn("diffCount");
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
  public Double check_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(check_countNdx);
  }
  private int check_countNdx;
  public Double theory_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(theory_countNdx);
  }
  private int theory_countNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
  public Integer item_order() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(item_orderNdx);
  }
  private int item_orderNdx;
  public String item_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_statusNdx);
  }
  private int item_statusNdx;
  public Double itemPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemPriceNdx);
  }
  private int itemPriceNdx;
  public Double itemAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemAmtNdx);
  }
  private int itemAmtNdx;
  public Double diffAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(diffAmtNdx);
  }
  private int diffAmtNdx;
  public Double diffCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(diffCountNdx);
  }
  private int diffCountNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:51^20*/
	
	public int saveEntity(CheckDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		String itemCategory = detail.getItemCategory();
		Double checkCount = detail.getCheckCount();
		Double theoryCount = detail.getTheoryCount();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());
		Integer itemOrder = detail.getItemOrder();
		String itemStatus = detail.getItemStatus();

		/*@lineinfo:generated-code*//*@lineinfo:69^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_CHECK_DETAIL")
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_SPECIFICATION, ITEM_CATEGORY,
//  						CHECK_COUNT, THEORY_COUNT, EXPIRED_TIME, ITEM_ORDER, ITEM_STATUS) 
//  			VALUES (:formId, :itemId, :itemName, :itemDimension, :itemSpecification, :itemCategory,
//  					:checkCount, :theoryCount, :expiredTime, :itemOrder, :itemStatus)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  String __sJT_7 = itemCategory;
  Double __sJT_8 = checkCount;
  Double __sJT_9 = theoryCount;
  Date __sJT_10 = expiredTime;
  Integer __sJT_11 = itemOrder;
  String __sJT_12 = itemStatus;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setIntWrapper(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:75^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(CheckDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemSpecification = detail.getItemSpecification();
		String itemCategory = detail.getItemCategory();
		Double checkCount = detail.getCheckCount();
		Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpiredTime());
		Integer itemOrder = detail.getItemOrder();

		/*@lineinfo:generated-code*//*@lineinfo:95^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_CHECK_DETAIL")
//  			SET
//  				CHECK_COUNT = :checkCount,
//  				EXPIRED_TIME = :expiredTime
//  			WHERE 
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  Double __sJT_2 = checkCount;
  Date __sJT_3 = expiredTime;
  String __sJT_4 = formId;
  String __sJT_5 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:104^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateSupplement(CheckDetail detail)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		Double theoryCount = detail.getTheoryCount();
		
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_CHECK_DETAIL")
//  			SET
//  				THEORY_COUNT = :theoryCount
//  			WHERE 
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  Double __sJT_2 = theoryCount;
  String __sJT_3 = formId;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:126^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:136^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T1_CHECK_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:142^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 根据批次删除相应的盘点清单和盘点汇总单明细数据
	 */
	public int deleteByBatch(String batchId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:155^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T1_CHECK_DETAIL") d
//  			WHERE
//  			    d.FORM_ID IN
//  			    (
//  			        SELECT
//  			            h.FORM_ID
//  			        FROM
//  			        	:Com_("D_T1_CHECK_HEADER") h
//  			        WHERE
//  			            h.CHECK_BATCH_ID = :batchId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_2 = Com_("D_T1_CHECK_HEADER");
  String __sJT_3 = batchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:168^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<CheckDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		return query(formId,null);
	}
	
	public List<CheckDetail> query(String formId,String hasSum)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:183^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    items AS
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            d.ITEM_CATEGORY,
//  			            d.SHELF_ID,
//  			            SUM(NVL(d.CHECK_COUNT,0)) CHECK_COUNT,
//  			            SUM(d.THEORY_COUNT)THEORY_COUNT,
//  			            d.EXPIRED_TIME,
//  			            d.ITEM_ORDER,
//  			            d.ITEM_STATUS,
//  			            SUM(NVL(d.CHECK_COUNT,0) - NVL(d.THEORY_COUNT,0))                 diffCount,
//  			            SUM((NVL(d.CHECK_COUNT,0) - NVL (d.THEORY_COUNT,0))*p.ITEM_PRICE) diffAmt,
//  			            p.ITEM_PRICE                                                      itemPrice,
//  			            SUM(d.CHECK_COUNT * p.ITEM_PRICE)                                 itemAmt
//  			        FROM
//  			            :Com_("D_T1_CHECK_DETAIL") d
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            d.ITEM_ID = p.ITEM_ID
//  			        AND p.PRICE_TYPE = 'BENCHMARK'
//  			        AND p.IS_CURRENT = 1
//  			        WHERE
//  			            d.FORM_ID = :formId
//  			        GROUP BY
//  			            GROUPING SETS( (d.FORM_ID, d.ITEM_ID, d.ITEM_NAME, d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION, d.ITEM_CATEGORY, d.SHELF_ID, d.CHECK_COUNT, d.THEORY_COUNT,
//  			            d.EXPIRED_TIME, d.ITEM_ORDER, d.ITEM_STATUS,p.ITEM_PRICE),NULL)
//  			        ORDER BY
//  			        	d.FORM_ID,d.ITEM_ORDER
//  			    )
//  			SELECT
//  			    DECODE (i.form_id,NULL,'合计',rownum)rownumber,
//  			    i.*
//  			FROM
//  			    items i };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:225^15*/
		List<CheckDetail> detailLst = processIter(detailIter,hasSum);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public boolean exist(String batchId, String itemId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int count = 0;
		/*@lineinfo:generated-code*//*@lineinfo:236^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*) 
//  			FROM
//  				:Com_("D_T1_CHECK_DETAIL") d,
//  				:Com_("D_T1_CHECK_HEADER") h
//  			WHERE
//  			    h.FORM_ID = d.FORM_ID
//  			AND d.ITEM_ID = :itemId
//  			AND h.CHECK_BATCH_ID = :batchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_2 = Com_("D_T1_CHECK_HEADER");
  String __sJT_3 = itemId;
  String __sJT_4 = batchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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
    count = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:246^3*/
		closeDefaultContext(myCtx);
		if(count == 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 餐厅盘点
	 * 1、根据批次查询盘点清单明细，分组求和，同时关联查询相应的理论库存，用于计算生成盘点单
	 * 2、根据批次限定的原料类别，检查漏盘原料，标志状态
	 */
	public List<CheckDetail> queryByBatch(String batchId,String storageId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String checkCategory = null;
		String branchId = null; // 用作关联理论库存
		/*@lineinfo:generated-code*//*@lineinfo:265^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    c.ITEM_CATEGORY, c.LOCK_BRANCH_ID 
//  			FROM
//  				:Com_("D_T1_CHECK_LOCK") c
//  			WHERE
//  			    c.CHECK_BATCH_ID = :batchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = batchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 7);
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
    sqlj.runtime.ref.ResultSetIterImpl.checkColumns(__sJT_rtRs, 2);
    if (!__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_NO_ROW_SELECT_INTO();
    }
    checkCategory = __sJT_rtRs.getString(1);
    branchId = __sJT_rtRs.getString(2);
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

/*@lineinfo:user-code*//*@lineinfo:272^3*/
		
		if (checkCategory == null) {
			checkCategory = "";
		}
		
		String query = " c.CATEGORY_ID IN ('" + checkCategory.replaceAll(",", "','") + "') ";
		
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:281^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    normal_item AS --正常盘点
//  			    (
//  			        SELECT
//  			            d.ITEM_ID,
//  			            SUM(d.CHECK_COUNT) AS check_count,
//  			            'NORMAL'           AS item_status
//  			        FROM
//  			        	:Com_("D_T1_CHECK_DETAIL") d
//  			        INNER JOIN
//  			        	:Com_("D_T1_CHECK_HEADER") h
//  			        ON
//  			            d.FORM_ID = h.FORM_ID
//  			        AND h.FORM_TYPE = 'list'
//  			        LEFT JOIN
//  			        	:Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  			            h.CHECK_BATCH_ID = :batchId
//  			        AND NVL( s.STATUS, 'null') != '已作废'
//  			        GROUP BY
//  			            d.ITEM_ID
//  			        ORDER BY
//  			            d.ITEM_ID
//  			    )
//  			    ,
//  			    leak AS --漏盘原料
//  			    (
//  			        SELECT
//  			            m.item_id
//  			        FROM
//  			        	:Com_("D_T2_ITEM_CATEGORY") c,
//  			            :Com_("D_T2_ITEM_META") m
//  			        WHERE
//  			            m.CATEGORY_ID = c.CATEGORY_ID
//  			        AND :query
//  			        MINUS --差集运算
//  			        SELECT
//  			            ITEM_ID
//  			        FROM
//  			            normal_item
//  			    )
//  			    ,
//  			    scheck AS --取并集
//  			    (
//  			    	SELECT
//  					    l.*,
//  					    0      AS check_count,
//  					    'LEAK' AS item_status
//  					FROM
//  					    leak l
//  					UNION ALL
//  					SELECT
//  					    *
//  					FROM
//  					    normal_item
//  			    )
//  			    ,
//  			    theory_storage AS --查询理论库存
//  			    (
//  			        SELECT
//  			            s.item_id,
//  			            s.item_count
//  			        FROM
//  			        	:Com_("D_T2_STORAGE") s
//  			        INNER JOIN
//  			            scheck c
//  			        ON
//  			            c.item_id = s.item_id
//  			        AND s.storage_id = :storageId
//  			        ORDER BY
//  			            s.item_id
//  			    )
//  			SELECT
//  			    s.*,
//  			    '' AS FORM_ID,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_NAME,
//  			    m.ITEM_SPECIFICATION,
//  			    m.CATEGORY_ID AS ITEM_CATEGORY,
//  			    ''            AS EXPIRED_TIME,
//  			    0             AS ITEM_ORDER,
//  			    ts.item_count theory_count,
//  			    p.ITEM_PRICE                          itemPrice,
//  			    ROUND(s.check_count * p.ITEM_PRICE,2) itemAmt,
//  			    ROWNUM rownumber,
//  			    '' diffAmt,
//  			    '' diffCount
//  			FROM
//  			    scheck s
//  			INNER JOIN
//  				:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.item_id = s.item_id
//  			LEFT JOIN
//  			    theory_storage ts
//  			ON
//  			    ts.item_id = s.item_id
//      		LEFT JOIN
//      			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    s.ITEM_ID = p.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			AND p.IS_CURRENT = 1
//  			ORDER BY
//  		    	s.item_id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_2 = Com_("D_T1_CHECK_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = batchId;
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = Com_("D_T2_ITEM_META");
  String __sJT_7 = query;
  String __sJT_8 = Com_("D_T2_STORAGE");
  String __sJT_9 = storageId;
  String __sJT_10 = Com_("D_T2_ITEM_META");
  String __sJT_11 = Com_("D_T2_ITEM_PRICE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:390^3*/
		List<CheckDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	/**
	 * 物流中心，中央工厂盘点
	 * 1、根据批次查询盘点清单明细，分组求和，同时关联查询相应的理论库存，用于计算生成盘点单
	 * 2、根据批次限定的原料类别，检查漏盘原料，标志状态
	 */
	public List<CheckDetail> queryLc(String batchId,String storageId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String checkCategory = null;
		String branchId = null; // 用作关联理论库存
		/*@lineinfo:generated-code*//*@lineinfo:408^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    c.ITEM_CATEGORY, c.LOCK_BRANCH_ID 
//  			FROM
//  				:Com_("D_T1_CHECK_LOCK") c
//  			WHERE
//  			    c.CHECK_BATCH_ID = :batchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_LOCK");
  String __sJT_2 = batchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 9);
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
    sqlj.runtime.ref.ResultSetIterImpl.checkColumns(__sJT_rtRs, 2);
    if (!__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_NO_ROW_SELECT_INTO();
    }
    checkCategory = __sJT_rtRs.getString(1);
    branchId = __sJT_rtRs.getString(2);
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

/*@lineinfo:user-code*//*@lineinfo:415^3*/
		if (checkCategory == null) {
			checkCategory = "";
		}
		String query = " c.CATEGORY_ID IN ('" + checkCategory.replaceAll(",", "','") + "') ";
		
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:422^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    normal_item AS --正常盘点
//  			    (
//  			        SELECT
//  			            d.ITEM_ID,
//  			            SUM(d.CHECK_COUNT) AS check_count,
//  			            'NORMAL'           AS item_status
//  			        FROM
//  			        	:Com_("D_T1_CHECK_DETAIL") d
//  			        INNER JOIN
//  			        	:Com_("D_T1_CHECK_HEADER") h
//  			        ON
//  			            d.FORM_ID = h.FORM_ID
//  			        AND h.FORM_TYPE = 'list'
//  			        LEFT JOIN
//  			        	:Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = h.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  			            h.CHECK_BATCH_ID = :batchId
//  			        AND NVL( s.STATUS, 'null') != '已作废'
//  			        GROUP BY
//  			            d.ITEM_ID
//  			        ORDER BY
//  			            d.ITEM_ID
//  			    )
//  			    ,
//  			    leak AS --漏盘原料
//  			    (
//  			        SELECT
//  			            m.item_id
//  			        FROM
//  			        	:Com_("D_T2_ITEM_CATEGORY") c,
//  			        	:Com_("D_T2_ITEM_META") m
//  			        WHERE
//  			            m.CATEGORY_ID = c.CATEGORY_ID
//  			        AND :query
//  			        MINUS --差集运算
//  			        SELECT
//  			            ITEM_ID
//  			        FROM
//  			            normal_item
//  			    )
//  			    ,
//  			    scheck AS --取并集
//  			    (
//  			    	SELECT
//  					    l.*,
//  					    0      AS check_count,
//  					    'LEAK' AS item_status
//  					FROM
//  					    leak l
//  					UNION ALL
//  					SELECT
//  					    *
//  					FROM
//  					    normal_item
//  			    )
//  			    ,
//  			    theory_storage AS --查询理论库存
//  			    (
//  			        SELECT
//  			            s.item_id,
//  			            s.item_count,
//  			            ROUND(s.item_count , 2) AS theory_count
//  			        FROM
//  			        	:Com_("D_T2_STORAGE") s
//  			        INNER JOIN
//  			            scheck c
//  			        ON
//  			            c.item_id = s.item_id
//  			        AND s.storage_id = :storageId
//  			        ORDER BY
//  			            s.item_id
//  			    )
//  			SELECT
//  			    s.*,
//  			    '' AS FORM_ID,
//  			    m.item_name,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    m.CATEGORY_ID AS ITEM_CATEGORY,
//  			    ''            AS EXPIRED_TIME,
//  			    0             AS ITEM_ORDER,
//  			    p.ITEM_PRICE                          itemPrice,
//  			    ROUND(s.check_count * p.ITEM_PRICE,2) itemAmt,
//  			    ts.item_count theory_count,
//  			    ROWNUM rownumber,
//  			    '' diffAmt,
//  			    '' diffCount
//  			FROM
//  			    scheck s
//  			INNER JOIN
//  				:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.item_id = s.item_id
//  			LEFT JOIN
//  			    theory_storage ts
//  			ON
//  			    ts.item_id = s.item_id
//      		LEFT JOIN
//      			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    s.ITEM_ID = p.ITEM_ID
//  			AND p.PRICE_TYPE = 'BENCHMARK'
//  			AND p.IS_CURRENT = 1
//  			ORDER BY
//  		    	s.item_id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_2 = Com_("D_T1_CHECK_HEADER");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = batchId;
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = Com_("D_T2_ITEM_META");
  String __sJT_7 = query;
  String __sJT_8 = Com_("D_T2_STORAGE");
  String __sJT_9 = storageId;
  String __sJT_10 = Com_("D_T2_ITEM_META");
  String __sJT_11 = Com_("D_T2_ITEM_PRICE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CheckDetailBean_SJProfileKeys.getKey(0), 10);
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

/*@lineinfo:user-code*//*@lineinfo:532^3*/
		List<CheckDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	private List<CheckDetail> processIter(DetailIter detailIter,String hasSum) 
			throws SQLException {
		List<CheckDetail> detailLst = new ArrayList<CheckDetail>();
		while(detailIter.next()) {
			CheckDetail detail = new CheckDetail();
			detail.setRownumber(detailIter.rownumber());
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setCheckCount(detailIter.check_count());
			detail.setTheoryCount(detailIter.theory_count());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setItemOrder(detailIter.item_order());
			detail.setItemStatus(detailIter.item_status());
			detail.setItemPrice(detailIter.itemPrice());
			detail.setItemAmt(detailIter.itemAmt());
			detail.setDiffAmt(detailIter.diffAmt());
			detail.setDiffCount(detailIter.diffCount());
			detailLst.add(detail);
		}
		
		if(hasSum == null){
			if(detailLst.size()>0){
				detailLst.remove(detailLst.size()-1);
			}
		}
		return detailLst;
	}
	
	private List<CheckDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<CheckDetail> detailLst = new ArrayList<CheckDetail>();
		while(detailIter.next()) {
			CheckDetail detail = new CheckDetail();
			detail.setRownumber(detailIter.rownumber());
			detail.setFormId(detailIter.form_id());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setCheckCount(detailIter.check_count());
			detail.setTheoryCount(detailIter.theory_count());
			detail.setExpiredTime(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setItemOrder(detailIter.item_order());
			detail.setItemStatus(detailIter.item_status());
			detail.setItemPrice(detailIter.itemPrice());
			detail.setItemAmt(detailIter.itemAmt());
			detail.setDiffAmt(detailIter.diffAmt());
			detail.setDiffCount(detailIter.diffCount());
			detailLst.add(detail);
		}
		
		return detailLst;
	}
}/*@lineinfo:generated-code*/class CheckDetailBean_SJProfileKeys 
{
  private static CheckDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CheckDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CheckDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.CheckDetailBean_SJProfile0");
  }
}
