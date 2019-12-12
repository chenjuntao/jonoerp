/*@lineinfo:filename=StorageInOutBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 18 11:25:34 CST 2014 by pw
 * Last edited on Tue Nov 18 11:25:34 CST 2014 by pw
 * 
 * comment: 出入库表
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.StorageInOut;
import pojo.form.StorageInOutSummary;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class StorageInOutBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StorageInOutBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    unitNdx = findColumn("unit");
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    storage_IdNdx = findColumn("storage_Id");
    storage_nameNdx = findColumn("storage_name");
    business_dateNdx = findColumn("business_date");
    operation_timeNdx = findColumn("operation_time");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_unit_priceNdx = findColumn("item_unit_price");
    orgi_countNdx = findColumn("orgi_count");
    item_count_inNdx = findColumn("item_count_in");
    item_count_outNdx = findColumn("item_count_out");
    result_countNdx = findColumn("result_count");
    form_idNdx = findColumn("form_id");
    reasonNdx = findColumn("reason");
  }
  public String unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(unitNdx);
  }
  private int unitNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String branch_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_nameNdx);
  }
  private int branch_nameNdx;
  public String storage_Id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_IdNdx);
  }
  private int storage_IdNdx;
  public String storage_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_nameNdx);
  }
  private int storage_nameNdx;
  public Date business_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(business_dateNdx);
  }
  private int business_dateNdx;
  public Date operation_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(operation_timeNdx);
  }
  private int operation_timeNdx;
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double orgi_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(orgi_countNdx);
  }
  private int orgi_countNdx;
  public Double item_count_in() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_inNdx);
  }
  private int item_count_inNdx;
  public Double item_count_out() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_outNdx);
  }
  private int item_count_outNdx;
  public Double result_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(result_countNdx);
  }
  private int result_countNdx;
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(reasonNdx);
  }
  private int reasonNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^334*/
	/*@lineinfo:generated-code*//*@lineinfo:41^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DetailSumIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DetailSumIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    itemCountInNdx = findColumn("itemCountIn");
    itemCountInMoneyNdx = findColumn("itemCountInMoney");
    itemCountOutNdx = findColumn("itemCountOut");
    itemCountOutMoneyNdx = findColumn("itemCountOutMoney");
  }
  public double itemCountIn() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(itemCountInNdx);
  }
  private int itemCountInNdx;
  public double itemCountInMoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(itemCountInMoneyNdx);
  }
  private int itemCountInMoneyNdx;
  public double itemCountOut() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(itemCountOutNdx);
  }
  private int itemCountOutNdx;
  public double itemCountOutMoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(itemCountOutMoneyNdx);
  }
  private int itemCountOutMoneyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^126*/
	
	/*@lineinfo:generated-code*//*@lineinfo:43^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SummaryIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SummaryIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    itemidNdx = findColumn("itemid");
    itemnameNdx = findColumn("itemname");
    unitNdx = findColumn("unit");
    itemcountinNdx = findColumn("itemcountin");
    itemcountinmoneyNdx = findColumn("itemcountinmoney");
    itemcountoutNdx = findColumn("itemcountout");
    itemcountoutmoneyNdx = findColumn("itemcountoutmoney");
    putinstorageNdx = findColumn("putinstorage");
    distributionNdx = findColumn("distribution");
    antiauditinNdx = findColumn("antiauditin");
    antiauditoutNdx = findColumn("antiauditout");
    drejectNdx = findColumn("dreject");
    prejectNdx = findColumn("preject");
    rawlossNdx = findColumn("rawloss");
    dishlossNdx = findColumn("dishloss");
    allocateiteminNdx = findColumn("allocateitemin");
    allocateitemoutNdx = findColumn("allocateitemout");
    checkstorageinNdx = findColumn("checkstoragein");
    checkstorageoutNdx = findColumn("checkstorageout");
    theoryDeductNdx = findColumn("theoryDeduct");
    orgiCountNdx = findColumn("orgiCount");
    branchnameNdx = findColumn("branchname");
    storageNameNdx = findColumn("storageName");
    itemcategoryNdx = findColumn("itemcategory");
  }
  public String itemid() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemidNdx);
  }
  private int itemidNdx;
  public String itemname() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemnameNdx);
  }
  private int itemnameNdx;
  public String unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(unitNdx);
  }
  private int unitNdx;
  public double itemcountin() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(itemcountinNdx);
  }
  private int itemcountinNdx;
  public double itemcountinmoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(itemcountinmoneyNdx);
  }
  private int itemcountinmoneyNdx;
  public double itemcountout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(itemcountoutNdx);
  }
  private int itemcountoutNdx;
  public double itemcountoutmoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(itemcountoutmoneyNdx);
  }
  private int itemcountoutmoneyNdx;
  public double putinstorage() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(putinstorageNdx);
  }
  private int putinstorageNdx;
  public double distribution() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(distributionNdx);
  }
  private int distributionNdx;
  public double antiauditin() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(antiauditinNdx);
  }
  private int antiauditinNdx;
  public double antiauditout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(antiauditoutNdx);
  }
  private int antiauditoutNdx;
  public double dreject() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(drejectNdx);
  }
  private int drejectNdx;
  public double preject() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(prejectNdx);
  }
  private int prejectNdx;
  public double rawloss() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(rawlossNdx);
  }
  private int rawlossNdx;
  public double dishloss() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(dishlossNdx);
  }
  private int dishlossNdx;
  public double allocateitemin() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(allocateiteminNdx);
  }
  private int allocateiteminNdx;
  public double allocateitemout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(allocateitemoutNdx);
  }
  private int allocateitemoutNdx;
  public double checkstoragein() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(checkstorageinNdx);
  }
  private int checkstorageinNdx;
  public double checkstorageout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(checkstorageoutNdx);
  }
  private int checkstorageoutNdx;
  public double theoryDeduct() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(theoryDeductNdx);
  }
  private int theoryDeductNdx;
  public double orgiCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(orgiCountNdx);
  }
  private int orgiCountNdx;
  public String branchname() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchnameNdx);
  }
  private int branchnameNdx;
  public String storageName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNameNdx);
  }
  private int storageNameNdx;
  public String itemcategory() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemcategoryNdx);
  }
  private int itemcategoryNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:49^63*/
	
	
	public int updateEntity(StorageInOut header)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			//期初数量
			Double orgiCount = header.getOrgiCount();
			
			// 入库数量
			Double itemCountIn = header.getItemCountIn();
			
			// 出库数量
			Double itemCountOut = header.getItemCountOut();
						
			String formId = header.getFormId();

			/*@lineinfo:generated-code*//*@lineinfo:67^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T0_STORAGE_IN_OUT")
//  				SET
//  					ORGI_COUNT = :orgiCount,
//  					ITEM_COUNT_IN = :itemCountIn,
//  					ITEM_COUNT_OUT = :itemCountOut
//  				WHERE 
//  					FORM_ID = :formId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  Double __sJT_2 = orgiCount;
  Double __sJT_3 = itemCountIn;
  Double __sJT_4 = itemCountOut;
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:76^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int saveEntity(StorageInOut header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String branchId = header.getBranchId();
		String storageId = header.getStorageId();
		Date businessDate = SqlDateUtil.getSqlDate(header.getBusinessDate());
		Date operationTime = SqlDateUtil.getSqlDate(header.getOperationTime());
		String itemId = header.getItemId();
		Double itemUnitPrice = header.getItemUnitPrice();
		Double orgiCount = header.getOrgiCount();
		Double itemCountIn = header.getItemCountIn();
		Double itemCountOut = header.getItemCountOut();
		Double resultCount = header.getResultCount();
		String formId = header.getFormId();
		String reason = header.getReason();
		String myTimeStamp = header.getTimestamp();

		/*@lineinfo:generated-code*//*@lineinfo:100^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T0_STORAGE_IN_OUT") 
//  				(BRANCH_ID, STORAGE_ID,BUSINESS_DATE, OPERATION_TIME, ITEM_ID, ITEM_UNIT_PRICE, ORGI_COUNT, ITEM_COUNT_IN, ITEM_COUNT_OUT, RESULT_COUNT, FORM_ID, REASON,MY_TIMESTAMP) 
//  			VALUES (:branchId,:storageId, :businessDate, :operationTime, :itemId, :itemUnitPrice, :orgiCount, :itemCountIn, :itemCountOut, :resultCount, :formId, :reason,:myTimeStamp)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = branchId;
  String __sJT_3 = storageId;
  Date __sJT_4 = businessDate;
  Date __sJT_5 = operationTime;
  String __sJT_6 = itemId;
  Double __sJT_7 = itemUnitPrice;
  Double __sJT_8 = orgiCount;
  Double __sJT_9 = itemCountIn;
  Double __sJT_10 = itemCountOut;
  Double __sJT_11 = resultCount;
  String __sJT_12 = formId;
  String __sJT_13 = reason;
  String __sJT_14 = myTimeStamp;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
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

/*@lineinfo:user-code*//*@lineinfo:104^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int count(java.util.Date startDate, java.util.Date endDate, String branchId,String storageId, String itemId,String reason,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId,itemId,reason);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//          	 	count(*) 
//      	 	FROM
//      	 	:Com_("D_T0_STORAGE_IN_OUT") io
//          	LEFT JOIN
//          	:Com_("D_T2_BRANCH") b
//          	ON
//          	    io.BRANCH_ID = b.BRANCH_ID
//          	LEFT JOIN
//          	:Com_("D_T2_ITEM_META") m
//          	ON
//          	    io.ITEM_ID = m.ITEM_ID
//          	WHERE
//          	    :query
//          	AND (
//          	        BUSINESS_DATE >= :sDate
//          	    OR  :sDate IS NULL)
//          	AND (
//          	        BUSINESS_DATE <= :eDate
//          	    OR  :eDate IS NULL)
//          	AND b.BRANCH_TYPE = :branchType
//          	ORDER BY
//          	    io.OPERATION_TIME
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = branchType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
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
    total = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:142^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:147^2*/

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
    BRANCH_IDNdx = findColumn("BRANCH_ID");
    STORAGE_IDNdx = findColumn("STORAGE_ID");
    BUSINESS_DATENdx = findColumn("BUSINESS_DATE");
    OPERATION_TIMENdx = findColumn("OPERATION_TIME");
    ITEM_IDNdx = findColumn("ITEM_ID");
    ITEM_UNIT_PRICENdx = findColumn("ITEM_UNIT_PRICE");
    ORGI_COUNTNdx = findColumn("ORGI_COUNT");
    ITEM_COUNT_INNdx = findColumn("ITEM_COUNT_IN");
    ITEM_COUNT_OUTNdx = findColumn("ITEM_COUNT_OUT");
    RESULT_COUNTNdx = findColumn("RESULT_COUNT");
    FORM_IDNdx = findColumn("FORM_ID");
    REASONNdx = findColumn("REASON");
  }
  public String BRANCH_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(BRANCH_IDNdx);
  }
  private int BRANCH_IDNdx;
  public String STORAGE_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(STORAGE_IDNdx);
  }
  private int STORAGE_IDNdx;
  public Date BUSINESS_DATE() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(BUSINESS_DATENdx);
  }
  private int BUSINESS_DATENdx;
  public Date OPERATION_TIME() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(OPERATION_TIMENdx);
  }
  private int OPERATION_TIMENdx;
  public String ITEM_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_IDNdx);
  }
  private int ITEM_IDNdx;
  public double ITEM_UNIT_PRICE() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(ITEM_UNIT_PRICENdx);
  }
  private int ITEM_UNIT_PRICENdx;
  public double ORGI_COUNT() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(ORGI_COUNTNdx);
  }
  private int ORGI_COUNTNdx;
  public double ITEM_COUNT_IN() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(ITEM_COUNT_INNdx);
  }
  private int ITEM_COUNT_INNdx;
  public double ITEM_COUNT_OUT() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(ITEM_COUNT_OUTNdx);
  }
  private int ITEM_COUNT_OUTNdx;
  public double RESULT_COUNT() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleNoNull(RESULT_COUNTNdx);
  }
  private int RESULT_COUNTNdx;
  public String FORM_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(FORM_IDNdx);
  }
  private int FORM_IDNdx;
  public String REASON() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(REASONNdx);
  }
  private int REASONNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:147^257*/
	public List<StorageInOut> query(java.util.Date startDate, java.util.Date endDate, String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		DetailIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:155^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T0_STORAGE_IN_OUT") s
//  				WHERE
//  				 s.BUSINESS_DATE >= :sDate 
//  				AND s.BUSINESS_DATE <= :eDate
//  				AND s.BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  Date __sJT_2 = sDate;
  Date __sJT_3 = eDate;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:164^3*/
		List<StorageInOut> headerLst = new ArrayList<StorageInOut>();
		while(headerIter.next()) {
			StorageInOut header = new StorageInOut();
			header.setBranchId(headerIter.BRANCH_ID());
			header.setStorageId(headerIter.STORAGE_ID());
			header.setBusinessDate(SqlDateUtil.getUtilDate(headerIter.BUSINESS_DATE()));
			header.setOperationTime(SqlDateUtil.getUtilDate(headerIter.OPERATION_TIME()));
			header.setItemId(headerIter.ITEM_ID());
			header.setItemUnitPrice(headerIter.ITEM_UNIT_PRICE());
			header.setOrgiCount(headerIter.ORGI_COUNT());
			header.setItemCountIn(headerIter.ITEM_COUNT_IN());
			header.setItemCountOut(headerIter.ITEM_COUNT_OUT());
			header.setResultCount(headerIter.RESULT_COUNT());
			header.setFormId(headerIter.FORM_ID());
			header.setReason(headerIter.REASON());
			headerLst.add(header);
		}
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}	
	
	public int count(java.util.Date startDate, java.util.Date endDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:194^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//          	 	count(*) 
//      	 	FROM
//      	 	:Com_("D_T0_STORAGE_IN_OUT") s
//          	    WHERE
//  				 s.BUSINESS_DATE >= :sDate 
//  				AND s.BUSINESS_DATE <= :eDate
//  				AND s.BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  Date __sJT_2 = sDate;
  Date __sJT_3 = eDate;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
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
    total = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:203^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public int summaryCount(java.util.Date startDate, java.util.Date endDate, String branchId,String storageId, String branchType,String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId,itemId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:216^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (
//  		        SELECT
//  		            io.ITEM_ID                               itemId,
//  		            im.ITEM_NAME                             itemName,
//  		            im.ITEM_DIMENSION                        unit,
//  		            '['|| b.BRANCH_ID || ']'|| b.BRANCH_NAME branchName,
//  		            '['|| bs.STORAGE_ID || ']'|| bs.STORAGE_NAME STORAGENAME,
//  		            ic.CATEGORY_NAME                         itemCategory
//  		        FROM
//  		        :Com_("D_T0_STORAGE_IN_OUT") io
//  		        LEFT JOIN
//  		        :Com_("D_T2_BRANCH_STORAGE") bs
//  		        ON
//  		            io.STORAGE_ID = bs.STORAGE_ID
//  		        LEFT JOIN
//  		        :Com_("D_T2_ITEM_META") im
//  		        ON
//  		            im.ITEM_ID = io.ITEM_ID
//  		        LEFT JOIN
//  		        :Com_("D_T2_ITEM_CATEGORY") ic
//  		        ON
//  		            im.CATEGORY_ID = ic.CATEGORY_ID
//  		        LEFT JOIN
//  		        :Com_("D_T2_BRANCH") b
//  		        ON
//  		            io.BRANCH_ID = b.BRANCH_ID
//          		WHERE
//  					    	:query
//  		                AND (
//  		                        BUSINESS_DATE >= :sDate
//  		                    OR  :sDate IS NULL)
//  		                AND (
//  		                        BUSINESS_DATE <= :eDate
//  		                    OR  :eDate IS NULL)
//  		                AND    
//  						    b.BRANCH_TYPE = :branchType
//  		        GROUP BY
//  			        bs.STORAGE_ID,
//  			        bs.STORAGE_NAME,
//  			        b.BRANCH_ID ,
//  			        b.BRANCH_NAME,
//  			        io.ITEM_ID,
//  			        ic.CATEGORY_NAME,
//  			        im.ITEM_NAME ,
//  			        im.ITEM_DIMENSION )t };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = query;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  String __sJT_11 = branchType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 5);
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
      __sJT_stmt.setString(11, __sJT_11);
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
    total = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:266^32*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public StorageInOut detailSum(java.util.Date startDate, java.util.Date endDate, String branchId,String storageId,String itemId,String reason,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId,itemId,reason);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DetailSumIter detailSumIter = null;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:279^3*/

//  ************************************************************
//  #sql [myCtx] detailSumIter = { SELECT
//  				SUM(NVL(ITEM_COUNT_IN,0))                   itemCountIn,
//  			    SUM(NVL(ITEM_COUNT_IN * ITEM_UNIT_PRICE,0)) itemCountInMoney,
//  			    SUM(NVL(ITEM_COUNT_OUT,0))                  itemCountOut,
//  			    SUM(NVL(ITEM_COUNT_OUT * ITEM_UNIT_PRICE,0))itemCountOutMoney
//  			FROM
//  				:Com_("D_T0_STORAGE_IN_OUT") io
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  		    ON
//  		        io.BRANCH_ID = b.BRANCH_ID
//  			WHERE
//  			:query
//  			AND b.BRANCH_TYPE = :branchType
//  			AND (BUSINESS_DATE >= :sDate or :sDate is null)
//  			AND (BUSINESS_DATE <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = query;
  String __sJT_4 = branchType;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailSumIter = new DetailSumIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:296^3*/
		
		StorageInOut storageInOut = new StorageInOut();
		while(detailSumIter.next()) {
			storageInOut.setItemCountIn(detailSumIter.itemCountIn());
			storageInOut.setItemCountInMoney(detailSumIter.itemCountInMoney());
			storageInOut.setItemCountOut(detailSumIter.itemCountOut());
			storageInOut.setItemCountOutMoney(detailSumIter.itemCountOutMoney());
		}
		
		closeDefaultContext(myCtx);
		return storageInOut;
	}
	
	private String formQuery(String branchId,String storageId,String itemId) {
		return formQuery(branchId,storageId,itemId,"head");
	}
	
	private String formQuery(String branchId,String storageId,String itemId,String reason) {
		String query = " 1=1";
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND b.BRANCH_ID = '" + branchId + "'";
		}
		
		if (!TextUtil.isEmpty(storageId)) {
			query = query + " AND io.STORAGE_ID = '" + storageId + "'";
		}
		
		if (!TextUtil.isEmpty(itemId)) {
			query = query + " AND io.ITEM_ID = '" + itemId + "'";
		}
		
		if (!"head".equals(reason)) {
			query = query + " AND io.REASON = '" + reason + "'";
		}
		
		return query;
	}
	
	public List<StorageInOut> query(java.util.Date startDate, java.util.Date endDate, String branchId,String storageId,String itemId,String reason,String branchType,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId,itemId,reason);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:343^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            t.*,
//  			            ROWNUM rowNumber
//  			        FROM
//  			            (
//  		            		SELECT
//  			            	    io. *,
//  			            	    '['|| b.BRANCH_ID || ']'|| b.BRANCH_NAME     BRANCH_NAME,
//  			            	    '['|| bs.STORAGE_ID || ']'|| bs.STORAGE_NAME STORAGE_NAME,
//  			            	    m.ITEM_DIMENSION                                 unit,
//  			            	    nvl(m.ITEM_NAME, ' ')ITEM_NAME
//  			            	FROM
//  			            	:Com_("D_T0_STORAGE_IN_OUT") io
//  			            	LEFT JOIN
//  			            	:Com_("D_T2_BRANCH_STORAGE") bs
//  			            	ON
//  			            	    io.STORAGE_ID = bs.STORAGE_ID
//  			            	LEFT JOIN
//  			            	:Com_("D_T2_BRANCH") b
//  			            	ON
//  			            	    io.BRANCH_ID = b.BRANCH_ID
//  			            	LEFT JOIN
//  			            	:Com_("D_T2_ITEM_META") m
//  			            	ON
//  			            	    io.ITEM_ID = m.ITEM_ID
//  			            	WHERE
//  			            	    :query
//  			            	AND (
//  			            	        BUSINESS_DATE >= :sDate
//  			            	    OR  :sDate IS NULL)
//  			            	AND (
//  			            	        BUSINESS_DATE <= :eDate
//  			            	    OR  :eDate IS NULL)
//  			            	AND b.BRANCH_TYPE = :branchType
//  			            	ORDER BY
//  			            	 io.BUSINESS_DATE,
//  			            	    CASE
//  			            	        WHEN io.REASON = 'PD'
//  			            	        THEN 1
//  			            	        ELSE 0
//  			            	    END ,
//  			            	    io.OPERATION_TIME) t
//  			        WHERE
//  			            ROWNUM < :endRow)
//  			WHERE
//  			    rowNumber >= :startRow
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  String __sJT_10 = branchType;
  int __sJT_11 = endRow;
  int __sJT_12 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
      __sJT_stmt.setInt(12, __sJT_12);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:394^3*/
		List<StorageInOut> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<StorageInOutSummary> summaryQuery(java.util.Date startDate, java.util.Date endDate, String branchId,String storageId,String branchType,String itemId,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,storageId,itemId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		SummaryIter summaryIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:409^3*/

//  ************************************************************
//  #sql [myCtx] summaryIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                    WITH
//  			                        a AS
//  			                        (
//  			                            SELECT
//  			                                io.ITEM_ID                                        itemId,
//  			                                im.ITEM_NAME                                      itemName,
//  			                                im.ITEM_DIMENSION                                    unit,
//  			                                SUM(NVL(ITEM_COUNT_IN,0))                            itemCountIn,
//  			                                SUM(NVL(ITEM_COUNT_IN,0)* NVL(io.ITEM_UNIT_PRICE,0)) itemCountInMoney,
//  			                                SUM(NVL(ITEM_COUNT_OUT,0))                           itemCountOut,
//  			                                SUM(NVL(ITEM_COUNT_OUT,0)* NVL(io.ITEM_UNIT_PRICE,0))itemCountOutMoney,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CGRK'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) putinstorage,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSRK'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) distribution,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSFSH'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) antiauditIn,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSFSH'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) antiauditOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSTH'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) dreject,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CGTH'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) preject,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'YLBS'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) rawLoss,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CPBS'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) dishLoss,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'DB'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) allocateitemIn,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'DB'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) allocateitemOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PD'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) checkstorageIn,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PD'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) checkstorageOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'LLKU'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END)                                 theoryDeduct,
//  			                                '['|| b.BRANCH_ID || ']'|| b.BRANCH_NAME     branchName,
//  			                                '['|| bs.STORAGE_ID || ']'|| bs.STORAGE_NAME STORAGENAME,
//  			                                ic.CATEGORY_NAME                             itemCategory
//  			                            FROM
//  			                            :Com_("D_T0_STORAGE_IN_OUT") io
//  			                            LEFT JOIN
//  			                            :Com_("D_T2_BRANCH_STORAGE") bs
//  			                            ON
//  			                                io.STORAGE_ID = bs.STORAGE_ID
//  			                            LEFT JOIN
//  			                            :Com_("D_T2_ITEM_META") im
//  			                            ON
//  			                                im.ITEM_ID = io.ITEM_ID
//  			                            LEFT JOIN
//  			                            :Com_("D_T2_ITEM_CATEGORY") ic
//  			                            ON
//  			                                im.CATEGORY_ID = ic.CATEGORY_ID
//  			                            LEFT JOIN
//  			                            :Com_("D_T2_BRANCH") b
//  			                            ON
//  			                                io.BRANCH_ID = b.BRANCH_ID
//  			                            WHERE
//  			                            	:query
//  			                            AND (
//  						                        BUSINESS_DATE >= :sDate
//  						                    OR  :sDate IS NULL)
//  						                AND (
//  						                        BUSINESS_DATE <= :eDate
//  						                    OR  :eDate IS NULL)
//  			                            AND b.BRANCH_TYPE = :branchType
//  			                            GROUP BY
//  			                                bs.STORAGE_ID,
//  			                                bs.STORAGE_NAME,
//  			                                b.BRANCH_ID ,
//  			                                b.BRANCH_NAME,
//  			                                io.ITEM_ID,
//  			                                ic.CATEGORY_NAME,
//  			                                im.ITEM_NAME ,
//  			                                im.ITEM_DIMENSION
//  			                            ORDER BY
//  			                                b.BRANCH_ID ,
//  			                                bs.STORAGE_ID,
//  			                                io.ITEM_ID
//  			                        )
//  			                        ,
//  			                        begining AS
//  			                        (
//  			                            SELECT
//  			                                io.ITEM_ID,
//  			                                io.ORGI_COUNT,
//  			                                io.BUSINESS_DATE
//  			                            FROM
//  			                            :Com_("D_T0_STORAGE_IN_OUT") io
//  			                            LEFT JOIN
//  			                            :Com_("D_T2_BRANCH_STORAGE") bs
//  			                            ON
//  			                                io.STORAGE_ID = bs.STORAGE_ID
//  			                            LEFT JOIN
//  			                            :Com_("D_T2_BRANCH") b
//  			                            ON
//  			                                io.BRANCH_ID = b.BRANCH_ID
//  			                            LEFT JOIN
//  			                            :Com_("D_T2_ITEM_META") m
//  			                            ON
//  			                                io.ITEM_ID = m.ITEM_ID
//  			                            WHERE
//  			                            	:query
//  			                        	AND (
//  						            	        BUSINESS_DATE >= :sDate
//  						            	    OR  :sDate IS NULL)
//  						            	AND (
//  						            	        BUSINESS_DATE <= :eDate
//  						            	    OR  :eDate IS NULL)
//  						            	AND b.BRANCH_TYPE = :branchType
//  			                            ORDER BY
//  			                                io.BUSINESS_DATE,
//  			                                CASE
//  			                                    WHEN io.REASON = 'PD'
//  			                                    THEN 1
//  			                                    ELSE 0
//  			                                END ,
//  			                                io.OPERATION_TIME
//  			                        )
//  			                    SELECT
//  			                        a.*,
//  			                        b.ORGI_COUNT orgiCount
//  			                    FROM
//  			                        a
//  			                    LEFT JOIN
//  			                        (
//  			                            SELECT
//  			                                t. *
//  			                            FROM
//  			                                (
//  			                                    SELECT
//  			                                        row_number()over(partition BY ITEM_ID ORDER BY BUSINESS_DATE )
//  			                                        rn,
//  			                                        begining.*
//  			                                    FROM
//  			                                        begining)t
//  			                            WHERE
//  			                                t.rn=1) b
//  			                    ON
//  			                        a.itemId = b.item_id) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = query;
  Date __sJT_7 = sDate;
  Date __sJT_8 = sDate;
  Date __sJT_9 = eDate;
  Date __sJT_10 = eDate;
  String __sJT_11 = branchType;
  String __sJT_12 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_13 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_14 = Com_("D_T2_BRANCH");
  String __sJT_15 = Com_("D_T2_ITEM_META");
  String __sJT_16 = query;
  Date __sJT_17 = sDate;
  Date __sJT_18 = sDate;
  Date __sJT_19 = eDate;
  Date __sJT_20 = eDate;
  String __sJT_21 = branchType;
  int __sJT_22 = endRow;
  int __sJT_23 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 8);
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
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      __sJT_stmt.setDate(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setInt(22, __sJT_22);
      __sJT_stmt.setInt(23, __sJT_23);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      summaryIter = new SummaryIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:615^34*/
		List<StorageInOutSummary> headerLst = summaryprocessIter(summaryIter);
		summaryIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	

	public StorageInOut queryByCondition(String branchId, String storageId,java.util.Date businessDate,String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		Date sbusinessDate = SqlDateUtil.getSqlDate(businessDate);
		
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:630^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    io. *,
//  			    '['|| b.BRANCH_ID || ']'|| b.BRANCH_NAME     BRANCH_NAME,
//  			    '['|| bs.STORAGE_ID || ']'|| bs.STORAGE_NAME STORAGE_NAME,
//  			    m.ITEM_NAME
//  			FROM
//  			:Com_("D_T0_STORAGE_IN_OUT") io
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH_STORAGE") bs
//  			ON
//  			    io.STORAGE_ID = bs.STORAGE_ID
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    io.BRANCH_ID = b.BRANCH_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    io.ITEM_ID = m.ITEM_ID
//  			WHERE
//  			    io.STORAGE_ID = :storageId
//      		AND io.BRANCH_ID =:branchId
//  			AND io.BUSINESS_DATE =:sbusinessDate
//  			AND io.ITEM_ID = :itemId
//  			AND io.REASON = 'PD' };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = storageId;
  String __sJT_6 = branchId;
  Date __sJT_7 = sbusinessDate;
  String __sJT_8 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:655^24*/
		
		List<StorageInOut> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<StorageInOutSummary> summaryprocessIter(SummaryIter summaryIter) 
			throws SQLException {
		List<StorageInOutSummary> headerLst = new ArrayList<StorageInOutSummary>();
		while(summaryIter.next()) {
			StorageInOutSummary header = new StorageInOutSummary();
			header.setItemId(summaryIter.itemid());
			header.setItemName(summaryIter.itemname());
			header.setUnit(summaryIter.unit());
			header.setItemCountIn(summaryIter.itemcountin());
			header.setItemCountInMoney(summaryIter.itemcountinmoney());
			header.setItemCountOut(summaryIter.itemcountout());
			header.setItemCountOutMoney(summaryIter.itemcountoutmoney());
			header.setPutinstorage(summaryIter.putinstorage());
			header.setDistribution(summaryIter.distribution());
			header.setAntiauditIn(summaryIter.antiauditin());
			header.setAntiauditOut(summaryIter.antiauditout());
			header.setDreject(summaryIter.dreject());
			header.setPreject(summaryIter.preject());
			header.setRawLoss(summaryIter.rawloss());
			header.setDishLoss(summaryIter.dishloss());
			header.setAllocateitemIn(summaryIter.allocateitemin());
			header.setAllocateitemOut(summaryIter.allocateitemout());
			header.setCheckstorageIn(summaryIter.checkstoragein());
			header.setCheckstorageOut(summaryIter.checkstorageout());
			header.setTheoryDeduct(summaryIter.theoryDeduct());
			header.setBranchName(summaryIter.branchname());
			header.setStorageName(summaryIter.storageName());
			header.setItemCategory(summaryIter.itemcategory());
			header.setOrgiCount(summaryIter.orgiCount());
			headerLst.add(header);
		}
		return headerLst;
	}
	public int deleteEntity(String startDate, String endDate, String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
	        DefaultContext myCtx = getDefaultContext();
			
//			Date sDate = SqlDateUtil.getSqlDate(startDate);
//			Date eDate = SqlDateUtil.getSqlDate(endDate);

			/*@lineinfo:generated-code*//*@lineinfo:706^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM
//  				(
//  					SELECT
//  					    *
//  					FROM
//  					:Com_("D_T0_STORAGE_IN_OUT") s
//  	        	    WHERE
//  	        	    s.OPERATION_TIME>= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND s.OPERATION_TIME <= to_date(:endDate,'YYYY-MM-DD HH24:mi:ss')
//  					AND s.BRANCH_ID = :branchId
//  					)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = startDate;
  String __sJT_3 = endDate;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:718^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	private List<StorageInOut> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<StorageInOut> headerLst = new ArrayList<StorageInOut>();
		while(headerIter.next()) {
			StorageInOut header = new StorageInOut();
			header.setBranchId(headerIter.branch_id());
			header.setBranchName(headerIter.branch_name());
			header.setStorageId(headerIter.storage_Id());
			header.setStorageName(headerIter.storage_name());
			header.setBusinessDate(SqlDateUtil.getUtilDate(headerIter.business_date()));
			header.setOperationTime(SqlDateUtil.getUtilDate(headerIter.operation_time()));
			header.setItemId(headerIter.item_id());
			header.setItemName(headerIter.item_name());
			header.setUnit(headerIter.unit());
			header.setItemUnitPrice(headerIter.item_unit_price());
			header.setOrgiCount(headerIter.orgi_count());
			header.setItemCountIn(headerIter.item_count_in());
			header.setItemCountOut(headerIter.item_count_out());
			header.setResultCount(headerIter.result_count());
			header.setFormId(headerIter.form_id());
			header.setReason(headerIter.reason());
			headerLst.add(header);
		}
		return headerLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:749^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class itemQueryIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public itemQueryIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    branchIdNdx = findColumn("branchId");
    item_countNdx = findColumn("item_count");
    formTypeNdx = findColumn("formType");
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
  public String branchId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchIdNdx);
  }
  private int branchIdNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public String formType() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(formTypeNdx);
  }
  private int formTypeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:749^141*/
	public List<Map> queryByItemId(String startDate,String itemId, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		itemQueryIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:754^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT--采购入库
//  			        	ih.FORM_ID,
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            ih.INPUT_DEPARTMENT_ID branchId,
//  			            '采购入库' formType
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId
//  			        AND id.ITEM_ID = :itemId
//  		        UNION ALL--配送入库
//  		        SELECT
//  		            sh.FORM_ID,
//  			        sd.ITEM_ID,
//  			        sd.ITEM_NAME,
//  			        sd.RECEIVE_COUNT ITEM_COUNT,
//  			        sh.REQUESTER_ID branchId,
//  			        '配送入库' formType
//  		        FROM
//  	        	:Com_("D_T1_SHIPPING_HEADER") sh
//  	        	INNER JOIN
//  	        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  			    ON
//  			        sd.FORM_ID= sh.FORM_ID
//  			    WHERE
//  			    	sh.INPUT_TIME = to_date(:startDate,'yyyy-mm-dd') 
//  		        AND sh.REQUESTER_ID = :branchId 
//  		        AND sd.ITEM_ID = :itemId
//          		UNION ALL--配送出库
//  		        SELECT
//  		        	sh.FORM_ID,
//  			        sd.ITEM_ID,
//  			        sd.ITEM_NAME,
//  			        -sd.DELIVERY_COUNT ITEM_COUNT,
//  			        sh.REQUESTER_ID branchId,
//  			        '配送出库' formType
//  		        FROM
//  	        	:Com_("D_T1_SHIPPING_HEADER") sh
//  	        	INNER JOIN
//  	        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  			    ON
//  			        sd.FORM_ID= sh.FORM_ID
//  			    WHERE
//  			    	sh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd') 
//  		        AND sh.PROVIDER_ID = :branchId 
//  		        AND sd.ITEM_ID = :itemId
//  		        UNION ALL
//  		        SELECT--配送采购退货
//  		        	rh.FORM_ID,
//  		            rd.ITEM_ID,
//  		            NVL(sd.ITEM_NAME,id.ITEM_NAME)                             ITEM_NAME,
//  		            -rd.RETURN_COUNT                                            ITEM_COUNT,
//  		            NVL(ih.INPUT_DEPARTMENT_ID,sh.REQUESTER_ID)                branchId,
//  		            DECODE(ih.INPUT_DEPARTMENT_ID,'','配送退货','采购退货') formType
//  		        FROM
//  		        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  		        INNER JOIN
//  		        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  		        ON
//  		            rh.FORM_ID=rd.FORM_ID
//      		 	LEFT JOIN
//      		 	 :Com_("D_T1_SHIPPING_HEADER") sh
//                   ON
//                       sh.FORM_ID=rd.FORM_REF_ID
//  		        LEFT JOIN
//  		        :Com_("D_T1_SHIPPING_DETAIL") sd
//  		        ON
//  		            sd.FORM_ID = rd.FORM_REF_ID
//  		        AND sd.item_id = rd.ITEM_ID
//      		  LEFT JOIN
//      		  :Com_("D_T1_INPUT_HEADER") ih
//                ON
//                    ih.FORM_ID=rd.FORM_REF_ID
//  		        LEFT JOIN
//  		        :Com_("D_T1_INPUT_DETAIL") id
//  		        ON
//  		            id.FORM_ID = rd.FORM_REF_ID
//  		        AND id.item_id = rd.ITEM_ID
//  		        WHERE
//  		            rh.AUDIT_TIME= to_date(:startDate,'yyyy-mm-dd')
//  		        AND (ih.INPUT_DEPARTMENT_ID=:branchId  OR  sh.REQUESTER_ID=:branchId )
//  		        AND (id.ITEM_ID = :itemId OR sd.ITEM_ID = :itemId)
//  		        UNION ALL
//  		        SELECT --报损单
//  		        	lh.FORM_ID,
//  		            ld.ITEM_ID,
//  		            ld.ITEM_NAME,
//  		            -ld.ITEM_COUNT ITEM_COUNT,
//  		            lh.LOSS_BRANCH_ID     branchId,
//  		            '报损单扣库'	formType
//  		        FROM
//  		        :Com_("D_T1_LOSS_HEADER") lh
//  		        INNER JOIN
//  		        :Com_("D_T1_LOSS_DETAIL") ld
//  		        ON
//  		            lh.FORM_ID=ld.FORM_ID
//          		WHERE
//   		            lh.AUDIT_TIME= to_date(:startDate,'yyyy-mm-dd')
//  		        AND lh.LOSS_BRANCH_ID =:branchId
//  		        AND (
//  		                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  		            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  		        AND ld.ITEM_ID = :itemId
//  	    		UNION ALL
//  	    		SELECT --调拨出入
//  	    			th.FORM_ID,
//  		    	    td.ITEM_ID,
//  		    	    td.ITEM_NAME,
//  		    	    DECODE(:branchId ,th.IN_BRANCH_ID,td.ACTUAL_COUNT,-td.ACTUAL_COUNT) ITEM_COUNT,
//  		    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId,
//  		    	    '调拨出入库' formType
//  	    	    FROM
//  		    	:Com_("D_T1_TRANSFER_HEADER") th
//  		    	INNER JOIN
//  		    	:Com_("D_T1_TRANSFER_DETAIL") td
//  		    	ON
//  		    	    th.FORM_ID=td.FORM_ID
//  	    		WHERE
//   		            th.CONFIRM_TIME = to_date(:startDate,'yyyy-mm-dd')
//  		    	AND (
//  		    	        th.IN_BRANCH_ID = :branchId
//  		    	    OR  th.OUT_BRANCH_ID= :branchId)	
//  	    	    AND td.ITEM_ID = :itemId
//  	    	    UNION ALL
//  	            SELECT
//  	            	'' formId,
//  	                trim(t.item_id) item_id,
//  	                t.ITEM_NAME,
//  	                -SUM(t.item_gross_count * d.nqty) ITEM_COUNT,
//  	                b.cbranch_c BRANCH_ID,
//  	                '销售扣仓' formType
//  	            FROM
//  	            :Com_("d_t_food_bill") b,
//  	            :Com_("D_T_FOOD_BILLS") d,
//  	            :Com_("D_T2_THERAPY") t
//  	            WHERE
//  	                trim(d.cbill_c) = trim(b.cbill_c)
//  	            AND trim(t.therapy_id) = trim(d.cfood_c)
//  	            AND trim(b.cbranch_c) = :branchId
//  	            AND t.ITEM_ID = :itemId
//  	            AND b.dbusiness = to_date(:startDate,'yyyy-mm-dd')
//  	            GROUP BY
//  		            trim(t.item_id),
//  		            t.ITEM_NAME,
//  		            b.cbranch_c
//  		        UNION ALL	
//      	        SELECT
//      	        	'' formId,
//      	            s.ITEM_ID,
//      	            m.ITEM_NAME,
//      	            Round(SUM(s.ITEM_COUNT_IN-s.ITEM_COUNT_OUT),4) item_count,
//      	            s.BRANCH_ID                           branchId,
//      	            '仓库汇总' fromType
//      	        FROM
//      	        :Com_("D_T0_STORAGE_IN_OUT") s
//      	        LEFT JOIN
//      	        :Com_("D_T2_ITEM_META") m
//  	    	    ON
//  	    	        m.ITEM_ID=s.ITEM_ID
//      	        WHERE
//      	        	s.REASON!='PD'
//      	        AND s.BUSINESS_DATE = to_date(:startDate,'yyyy-mm-dd')
//          		AND s.BRANCH_ID= :branchId
//          		AND s.ITEM_ID = :itemId
//      	        GROUP BY
//      	            s.ITEM_ID,
//      	            s.BRANCH_ID,
//      	            m.ITEM_NAME
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = branchId;
  String __sJT_5 = itemId;
  String __sJT_6 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_7 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_8 = startDate;
  String __sJT_9 = branchId;
  String __sJT_10 = itemId;
  String __sJT_11 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_12 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_13 = startDate;
  String __sJT_14 = branchId;
  String __sJT_15 = itemId;
  String __sJT_16 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_17 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_18 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_19 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_20 = Com_("D_T1_INPUT_HEADER");
  String __sJT_21 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_22 = startDate;
  String __sJT_23 = branchId;
  String __sJT_24 = branchId;
  String __sJT_25 = itemId;
  String __sJT_26 = itemId;
  String __sJT_27 = Com_("D_T1_LOSS_HEADER");
  String __sJT_28 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_29 = startDate;
  String __sJT_30 = branchId;
  String __sJT_31 = itemId;
  String __sJT_32 = branchId;
  String __sJT_33 = branchId;
  String __sJT_34 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_35 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_36 = startDate;
  String __sJT_37 = branchId;
  String __sJT_38 = branchId;
  String __sJT_39 = itemId;
  String __sJT_40 = Com_("d_t_food_bill");
  String __sJT_41 = Com_("D_T_FOOD_BILLS");
  String __sJT_42 = Com_("D_T2_THERAPY");
  String __sJT_43 = branchId;
  String __sJT_44 = itemId;
  String __sJT_45 = startDate;
  String __sJT_46 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_47 = Com_("D_T2_ITEM_META");
  String __sJT_48 = startDate;
  String __sJT_49 = branchId;
  String __sJT_50 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 11);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_stmt.setString(35, __sJT_35);
      __sJT_stmt.setString(36, __sJT_36);
      __sJT_stmt.setString(37, __sJT_37);
      __sJT_stmt.setString(38, __sJT_38);
      __sJT_stmt.setString(39, __sJT_39);
      __sJT_stmt.setString(40, __sJT_40);
      __sJT_stmt.setString(41, __sJT_41);
      __sJT_stmt.setString(42, __sJT_42);
      __sJT_stmt.setString(43, __sJT_43);
      __sJT_stmt.setString(44, __sJT_44);
      __sJT_stmt.setString(45, __sJT_45);
      __sJT_stmt.setString(46, __sJT_46);
      __sJT_stmt.setString(47, __sJT_47);
      __sJT_stmt.setString(48, __sJT_48);
      __sJT_stmt.setString(49, __sJT_49);
      __sJT_stmt.setString(50, __sJT_50);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new itemQueryIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:930^3*/
		List<Map> detailLst = processQueryItemIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> queryLcItemId(String startDate, String itemId,String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		itemQueryIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:941^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT--入库
//  			        	ih.FORM_ID,
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            ih.INPUT_DEPARTMENT_ID branchId,
//  			            '验收/半成品入库' formType
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId 
//  			        AND id.ITEM_ID = :itemId
//  			        UNION ALL
//  			        SELECT --配退入库，采退出库
//  			        	rh.FORM_ID,
//  				        rd.ITEM_ID,
//  				        NVL(sd.ITEM_NAME,id.ITEM_NAME)                             ITEM_NAME,
//  				        DECODE(SUBSTR(rd.FORM_ID,0,2) ,'CT',-rd.RETURN_COUNT,'PT',rd.RETURN_COUNT,0)    ITEM_COUNT,
//  				        NVL(ih.INPUT_DEPARTMENT_ID,sh.PROVIDER_ID)                branchId,
//  				        DECODE(SUBSTR(rd.FORM_ID,0,2) ,'CT','采退出库','PT','配退入库','未知') formType
//  			        FROM
//  			        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			        INNER JOIN
//  			        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  			        ON
//  			            rh.FORM_ID=rd.FORM_ID
//          		 	LEFT JOIN
//          		 	 :Com_("D_T1_SHIPPING_HEADER") sh
//  	                 ON
//  	                     sh.FORM_ID=rd.FORM_REF_ID
//  			        LEFT JOIN
//  			        :Com_("D_T1_SHIPPING_DETAIL") sd
//  			        ON
//  			            sd.FORM_ID = rd.FORM_REF_ID
//  			        AND sd.item_id = rd.ITEM_ID
//  	        		LEFT JOIN
//  	        		:Com_("D_T1_INPUT_HEADER") ih
//  		        	ON
//  		        	    ih.FORM_ID=rd.FORM_REF_ID
//  		        	LEFT JOIN
//  		        	:Com_("D_T1_INPUT_DETAIL") id
//  		        	ON
//  		        	    id.FORM_ID = rd.FORM_REF_ID
//  		        	AND id.item_id = rd.ITEM_ID
//  			        WHERE
//  			            rh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND (sh.PROVIDER_ID = :branchId OR  ih.INPUT_DEPARTMENT_ID = :branchId)
//  			        AND rd.ITEM_ID = :itemId
//  	        		UNION ALL
//  	        		SELECT--配送出库，越库出库，外部出库
//  	        			sh.FORM_ID,
//  		        	    sd.ITEM_ID,
//  		        	    sd.ITEM_NAME,
//  		        	    -sd.DELIVERY_COUNT ITEM_COUNT,
//  		        	    sh.PROVIDER_ID      branchId,
//  		        	    '配送/越库/外部出库'	formType
//  	        	    FROM
//  		        	:Com_("D_T1_SHIPPING_HEADER") sh
//  		        	INNER JOIN
//  		        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  		        	ON
//  		        	    sd.FORM_ID= sh.FORM_ID
//      	    		WHERE
//  				    	sh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND sh.PROVIDER_ID = :branchId 
//  			        AND	sd.ITEM_ID = :itemId
//  	        		UNION ALL
//  			        SELECT --报损单
//  			        	lh.FORM_ID,
//  			            ld.ITEM_ID,
//  			            ld.ITEM_NAME,
//  			            -ld.ITEM_COUNT ITEM_COUNT,
//  			            lh.LOSS_BRANCH_ID                                           branchId,
//  			            '报损单出库'	formType
//  			        FROM
//  			        :Com_("D_T1_LOSS_HEADER") lh
//  			        INNER JOIN
//  			        :Com_("D_T1_LOSS_DETAIL") ld
//  			        ON
//  			            lh.FORM_ID=ld.FORM_ID
//  	        		WHERE
//  	 		            lh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND lh.LOSS_BRANCH_ID =:branchId
//  			        AND (
//  			                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  		            AND ld.ITEM_ID = :itemId
//  		            UNION ALL
//  		    		SELECT --调拨出入
//  		    			th.FORM_ID,
//  			    	    td.ITEM_ID,
//  			    	    td.ITEM_NAME,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,td.ACTUAL_COUNT,-td.ACTUAL_COUNT) ITEM_COUNT,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,'调拨入库','调拨出库') formType 
//  		    	    FROM
//  			    	:Com_("D_T1_TRANSFER_HEADER") th
//  			    	INNER JOIN
//  			    	:Com_("D_T1_TRANSFER_DETAIL") td
//  			    	ON
//  			    	    th.FORM_ID=td.FORM_ID
//  		    		WHERE
//  	 		            th.CONFIRM_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			    	AND (
//  			    	        th.IN_BRANCH_ID = :branchId
//  			    	    OR  th.OUT_BRANCH_ID= :branchId)
//  		    	    AND td.ITEM_ID = :itemId
//  		    		UNION ALL
//  	    	        SELECT
//  	    	        	'' FORM_ID,
//  	    	            s.ITEM_ID,
//  	    	            m.ITEM_NAME,
//  	    	            ROUND(SUM(s.ITEM_COUNT_IN-s.ITEM_COUNT_OUT),4) item_count,
//  	    	            s.BRANCH_ID                           branchId,
//  	    	            '仓库出入库'	formType
//  	    	        FROM
//  	    	        :Com_("D_T0_STORAGE_IN_OUT") s
//  	    	        LEFT JOIN
//  	    	        :Com_("D_T2_ITEM_META") m
//  		    	    ON
//  		    	        m.ITEM_ID=s.ITEM_ID
//  	    	        WHERE
//  	    	        	s.REASON!='PD'
//  	    	        AND s.BUSINESS_DATE = to_date(:startDate,'yyyy-mm-dd')
//  	    	        AND s.BRANCH_ID= :branchId
//  	    	        AND s.ITEM_ID = :itemId
//  	    	        GROUP BY
//  	    	            s.ITEM_ID,
//  	    	            m.ITEM_NAME,
//  	    	            s.BRANCH_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = branchId;
  String __sJT_5 = itemId;
  String __sJT_6 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_7 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_8 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_9 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_10 = Com_("D_T1_INPUT_HEADER");
  String __sJT_11 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_12 = startDate;
  String __sJT_13 = branchId;
  String __sJT_14 = branchId;
  String __sJT_15 = itemId;
  String __sJT_16 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_17 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_18 = startDate;
  String __sJT_19 = branchId;
  String __sJT_20 = itemId;
  String __sJT_21 = Com_("D_T1_LOSS_HEADER");
  String __sJT_22 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_23 = startDate;
  String __sJT_24 = branchId;
  String __sJT_25 = itemId;
  String __sJT_26 = branchId;
  String __sJT_27 = branchId;
  String __sJT_28 = branchId;
  String __sJT_29 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_30 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_31 = startDate;
  String __sJT_32 = branchId;
  String __sJT_33 = branchId;
  String __sJT_34 = itemId;
  String __sJT_35 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_36 = Com_("D_T2_ITEM_META");
  String __sJT_37 = startDate;
  String __sJT_38 = branchId;
  String __sJT_39 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 12);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_stmt.setString(35, __sJT_35);
      __sJT_stmt.setString(36, __sJT_36);
      __sJT_stmt.setString(37, __sJT_37);
      __sJT_stmt.setString(38, __sJT_38);
      __sJT_stmt.setString(39, __sJT_39);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new itemQueryIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1077^4*/
			List<Map> detailLst = processQueryItemIter(detailIter);
			detailIter.close();
			closeDefaultContext(myCtx);
			return detailLst;
	}
	
	public List<Map> queryCFItemId(String startDate,String itemId, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		itemQueryIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:1088^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT --采购入库,产品入库
//  			        	ih.FORM_ID,
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            ih.INPUT_DEPARTMENT_ID branchId,
//  			            '采购/产品入库' formType
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId 
//  			        AND id.ITEM_ID = :itemId
//  			        UNION ALL
//  			        SELECT--物流中心采购退货加库存，自己退货减库存
//  			        	rh.FORM_ID,
//  			            rd.ITEM_ID,
//  			            id.ITEM_NAME            ITEM_NAME,
//  			            DECODE(ih.INPUT_DEPARTMENT_ID,:branchId,-rd.RETURN_COUNT ,rd.RETURN_COUNT )  ITEM_COUNT,
//  			            DECODE(ih.INPUT_DEPARTMENT_ID, :branchId ,ih.INPUT_DEPARTMENT_ID,ih.PROVIDER_ID )     branchId,
//  			            DECODE(ih.INPUT_DEPARTMENT_ID, :branchId ,'央采购退货入库','物采购退货出库' ) formType
//  			        FROM
//  			        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			        INNER JOIN
//  			        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  			        ON
//  			            rh.FORM_ID=rd.FORM_ID
//              		 LEFT JOIN
//              		 :Com_("D_T1_INPUT_HEADER") ih
//  	                 ON
//  	                     ih.FORM_ID=rd.FORM_REF_ID
//  	                 LEFT JOIN
//  	                 :Com_("D_T1_INPUT_DETAIL") id
//  	                 ON
//  	                     id.FORM_ID = rd.FORM_REF_ID
//  	                 AND id.item_id = rd.ITEM_ID
//  			        WHERE
//  			            rh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  					AND (ih.INPUT_DEPARTMENT_ID=:branchId or ih.PROVIDER_ID=:branchId)
//  					AND rd.ITEM_ID = :itemId
//  	        		UNION ALL
//  			        SELECT --报损单
//  			        	lh.FORM_ID,
//  			            ld.ITEM_ID,
//  			            ld.ITEM_NAME,
//  			            -ld.ITEM_COUNT ITEM_COUNT,
//  			            lh.LOSS_BRANCH_ID         branchId,
//  			            '报损单'	formType
//  			        FROM
//  			        :Com_("D_T1_LOSS_HEADER") lh
//  			        INNER JOIN
//  			        :Com_("D_T1_LOSS_DETAIL") ld
//  			        ON
//  			            lh.FORM_ID=ld.FORM_ID
//  	        		WHERE
//  	 		            lh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND lh.LOSS_BRANCH_ID =:branchId
//  			        AND (
//  			                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  		            AND ld.ITEM_ID = :itemId
//  		           UNION ALL
//  		    		SELECT --调拨
//  		    			th.FORM_ID,
//  			    	    td.ITEM_ID,
//  			    	    td.ITEM_NAME,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,td.ACTUAL_COUNT,-td.ACTUAL_COUNT) ITEM_COUNT,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,'调拨入库','调拨出库') formType
//  			    	FROM
//  			    	:Com_("D_T1_TRANSFER_HEADER") th
//  			    	INNER JOIN
//  			    	:Com_("D_T1_TRANSFER_DETAIL") td
//  			    	ON
//  			    	    th.FORM_ID=td.FORM_ID
//  		    		WHERE
//  	 		            th.CONFIRM_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			    	AND (
//  			    	        th.IN_BRANCH_ID = :branchId
//  			    	    OR  th.OUT_BRANCH_ID= :branchId)
//  	    	    	AND td.ITEM_ID = :itemId
//  		    	    UNION ALL
//  		    	    SELECT --领料,超领,退料,非工单领料
//  		    	    	rh.FORM_ID,
//  			    	    rd.ITEM_ID,
//  			    	    rd.ITEM_NAME,
//  			    	    DECODE(rh.FORM_TYPE,'return',rd.ITEM_COUNT,-rd.RECEIVE_COUNT) ITEM_COUNT,
//  			    	    rh.FORM_BRANCH_ID            branchId,
//  			    	    DECODE(rh.FORM_TYPE,'return','退料入库','领料出库') formType
//  			    	FROM
//  			    	:Com_("D_T1_REQUISITION_HEADER") rh
//  			    	INNER JOIN
//  			    	:Com_("D_T1_REQUISITION_DETAIL") rd
//  			    	ON
//  			    	    rd.FORM_ID= rh.FORM_ID
//  			    	LEFT JOIN
//  			    	:Com_("D_T2_ITEM_PRICE") p
//  			    	ON
//  			    	    p.ITEM_ID = rd.ITEM_ID
//  			    	AND p.PRICE_TYPE LIKE 'BENCHMARK%' --标准价
//  			    	AND p.IS_CURRENT =1
//  			    	WHERE
//  		    	 		rh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			    	AND rh.FORM_BRANCH_ID = :branchId 
//  			    	AND rd.ITEM_ID = :itemId
//  			    	UNION ALL
//  	        		SELECT--出品出库
//  	        			sh.FORM_ID,
//  		        	    sd.ITEM_ID,
//  		        	    sd.ITEM_NAME,
//  		        	    -sd.DELIVERY_COUNT ITEM_COUNT,
//  		        	    sh.PROVIDER_ID      branchId,
//  		        	    '出品出库'	formType
//  		        	FROM
//  		        	:Com_("D_T1_SHIPPING_HEADER") sh
//  		        	INNER JOIN
//  		        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  		        	ON
//  		        	    sd.FORM_ID= sh.FORM_ID
//      	    		WHERE
//  				    	sh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND sh.PROVIDER_ID = :branchId
//  			        AND sd.ITEM_ID = :itemId
//  		    		UNION ALL
//  		    	        SELECT
//  		    	        	'' FORM_ID,
//  		    	            s.ITEM_ID,
//  		    	            m.ITEM_NAME,
//  		    	            ROUND(SUM(s.ITEM_COUNT_IN-s.ITEM_COUNT_OUT),4) item_count,
//  		    	            s.BRANCH_ID                           branchId,
//  			    	        '仓库出入库'	formType
//  			    	        FROM
//  			    	        :Com_("D_T0_STORAGE_IN_OUT") s
//  			    	        LEFT JOIN
//  			    	        :Com_("D_T2_ITEM_META") m
//  				    	    ON
//  				    	        m.ITEM_ID=s.ITEM_ID
//  			    	        WHERE
//  			    	        	s.REASON!='PD'
//  			    	        AND s.BUSINESS_DATE = to_date(:startDate,'yyyy-mm-dd')
//  			    	        AND s.BRANCH_ID= :branchId
//  			    	        AND s.ITEM_ID = :itemId
//  			    	        GROUP BY
//  			    	            s.ITEM_ID,
//  			    	            m.ITEM_NAME,
//  			    	            s.BRANCH_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = branchId;
  String __sJT_5 = itemId;
  String __sJT_6 = branchId;
  String __sJT_7 = branchId;
  String __sJT_8 = branchId;
  String __sJT_9 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_10 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_11 = Com_("D_T1_INPUT_HEADER");
  String __sJT_12 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_13 = startDate;
  String __sJT_14 = branchId;
  String __sJT_15 = branchId;
  String __sJT_16 = itemId;
  String __sJT_17 = Com_("D_T1_LOSS_HEADER");
  String __sJT_18 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_19 = startDate;
  String __sJT_20 = branchId;
  String __sJT_21 = itemId;
  String __sJT_22 = branchId;
  String __sJT_23 = branchId;
  String __sJT_24 = branchId;
  String __sJT_25 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_26 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_27 = startDate;
  String __sJT_28 = branchId;
  String __sJT_29 = branchId;
  String __sJT_30 = itemId;
  String __sJT_31 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_32 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_33 = Com_("D_T2_ITEM_PRICE");
  String __sJT_34 = startDate;
  String __sJT_35 = branchId;
  String __sJT_36 = itemId;
  String __sJT_37 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_38 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_39 = startDate;
  String __sJT_40 = branchId;
  String __sJT_41 = itemId;
  String __sJT_42 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_43 = Com_("D_T2_ITEM_META");
  String __sJT_44 = startDate;
  String __sJT_45 = branchId;
  String __sJT_46 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 13);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_stmt.setString(35, __sJT_35);
      __sJT_stmt.setString(36, __sJT_36);
      __sJT_stmt.setString(37, __sJT_37);
      __sJT_stmt.setString(38, __sJT_38);
      __sJT_stmt.setString(39, __sJT_39);
      __sJT_stmt.setString(40, __sJT_40);
      __sJT_stmt.setString(41, __sJT_41);
      __sJT_stmt.setString(42, __sJT_42);
      __sJT_stmt.setString(43, __sJT_43);
      __sJT_stmt.setString(44, __sJT_44);
      __sJT_stmt.setString(45, __sJT_45);
      __sJT_stmt.setString(46, __sJT_46);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new itemQueryIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1239^4*/
			List<Map> detailLst = processQueryItemIter(detailIter);
			detailIter.close();
			closeDefaultContext(myCtx);
			return detailLst;
	}
	
	private List<Map> processQueryItemIter(itemQueryIter detailIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("formId", detailIter.form_id());
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemCount", detailIter.item_count());
			detail.put("formType", detailIter.formType());
			headerLst.add(detail);
		}
		return headerLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:1261^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemComputeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemComputeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    branchIdNdx = findColumn("branchId");
    item_countNdx = findColumn("item_count");
    storage_countNdx = findColumn("storage_count");
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
  public String branchId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchIdNdx);
  }
  private int branchIdNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double storage_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(storage_countNdx);
  }
  private int storage_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1261^127*/
	public List<Map> computeItem(String startDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemComputeIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:1266^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT--采购入库
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            ih.INPUT_DEPARTMENT_ID branchId
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId 
//  		        UNION ALL--配送入库
//  		        SELECT
//  			        sd.ITEM_ID,
//  			        sd.ITEM_NAME,
//  			        sd.RECEIVE_COUNT ITEM_COUNT,
//  			        sh.REQUESTER_ID branchId
//  		        FROM
//  	        	:Com_("D_T1_SHIPPING_HEADER") sh
//  	        	INNER JOIN
//  	        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  			    ON
//  			        sd.FORM_ID= sh.FORM_ID
//  			    WHERE
//  			    	sh.INPUT_TIME = to_date(:startDate,'yyyy-mm-dd') 
//  		        AND sh.REQUESTER_ID = :branchId 
//          		UNION ALL--配送出库
//  		        SELECT
//  			        sd.ITEM_ID,
//  			        sd.ITEM_NAME,
//  			        -sd.RECEIVE_COUNT ITEM_COUNT,
//  			        sh.REQUESTER_ID branchId
//  		        FROM
//  	        	:Com_("D_T1_SHIPPING_HEADER") sh
//  	        	INNER JOIN
//  	        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  			    ON
//  			        sd.FORM_ID= sh.FORM_ID
//  			    WHERE
//  			    	sh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd') 
//  		        AND sh.PROVIDER_ID = :branchId 
//  		        UNION ALL
//  		        SELECT--配送采购退货
//  		            rd.ITEM_ID,
//  		            NVL(sd.ITEM_NAME,id.ITEM_NAME)                             ITEM_NAME,
//  		            -rd.RETURN_COUNT                                            ITEM_COUNT,
//  		            NVL(ih.INPUT_DEPARTMENT_ID,sh.REQUESTER_ID)                branchId
//  		        FROM
//  		        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  		        INNER JOIN
//  		        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  		        ON
//  		            rh.FORM_ID=rd.FORM_ID
//      		 	LEFT JOIN
//      		 	 :Com_("D_T1_SHIPPING_HEADER") sh
//                   ON
//                       sh.FORM_ID=rd.FORM_REF_ID
//  		        LEFT JOIN
//  		        :Com_("D_T1_SHIPPING_DETAIL") sd
//  		        ON
//  		            sd.FORM_ID = rd.FORM_REF_ID
//  		        AND sd.item_id = rd.ITEM_ID
//      		  LEFT JOIN
//      		  :Com_("D_T1_INPUT_HEADER") ih
//                ON
//                    ih.FORM_ID=rd.FORM_REF_ID
//  		        LEFT JOIN
//  		        :Com_("D_T1_INPUT_DETAIL") id
//  		        ON
//  		            id.FORM_ID = rd.FORM_REF_ID
//  		        AND id.item_id = rd.ITEM_ID
//  		        WHERE
//  		            rh.AUDIT_TIME= to_date(:startDate,'yyyy-mm-dd')
//  		        AND (ih.INPUT_DEPARTMENT_ID=:branchId  OR  sh.REQUESTER_ID=:branchId )
//  		        UNION ALL
//  		        SELECT --报损单
//  		            ld.ITEM_ID,
//  		            ld.ITEM_NAME,
//  		            -ld.ITEM_COUNT ITEM_COUNT,
//  		            lh.LOSS_BRANCH_ID                                           branchId
//  		        FROM
//  		        :Com_("D_T1_LOSS_HEADER") lh
//  		        INNER JOIN
//  		        :Com_("D_T1_LOSS_DETAIL") ld
//  		        ON
//  		            lh.FORM_ID=ld.FORM_ID
//          		WHERE
//   		            lh.AUDIT_TIME= to_date(:startDate,'yyyy-mm-dd')
//  		        AND lh.LOSS_BRANCH_ID =:branchId
//  		        AND (
//  		                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  		            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  	    		UNION ALL
//  	    		SELECT --调拨出入
//  		    	    td.ITEM_ID,
//  		    	    td.ITEM_NAME,
//  		    	    DECODE(:branchId ,th.IN_BRANCH_ID,td.ACTUAL_COUNT,-td.ACTUAL_COUNT) ITEM_COUNT,
//  		    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId
//  	    	    FROM
//  		    	:Com_("D_T1_TRANSFER_HEADER") th
//  		    	INNER JOIN
//  		    	:Com_("D_T1_TRANSFER_DETAIL") td
//  		    	ON
//  		    	    th.FORM_ID=td.FORM_ID
//  	    		WHERE
//   		            th.CONFIRM_TIME = to_date(:startDate,'yyyy-mm-dd')
//  		    	AND (
//  		    	        th.IN_BRANCH_ID = :branchId
//  		    	    OR  th.OUT_BRANCH_ID= :branchId)	
//  	    	    UNION ALL
//  	            SELECT
//  	                trim(t.item_id) item_id,
//  	                t.ITEM_NAME,
//  	                -SUM(t.item_gross_count * d.nqty) ITEM_COUNT,
//  	                b.cbranch_c BRANCH_ID
//  	            FROM
//  	            :Com_("d_t_food_bill") b,
//  	            :Com_("D_T_FOOD_BILLS") d,
//  	            :Com_("D_T2_THERAPY") t
//  	            WHERE
//  	                trim(d.cbill_c) = trim(b.cbill_c)
//  	            AND trim(t.therapy_id) = trim(d.cfood_c)
//  	            AND trim(b.cbranch_c) = :branchId
//  	            AND b.dbusiness = to_date(:startDate,'yyyy-mm-dd')
//  	            GROUP BY
//  		            trim(t.item_id),
//  		            t.ITEM_NAME,
//  		            b.cbranch_c
//  			    		),
//  			    	    b AS
//  			    	    (
//  			    	        SELECT
//  			    	            s.ITEM_ID,
//  			    	            s.BRANCH_ID                           branchId,
//  			    	            Round(SUM(s.ITEM_COUNT_IN-s.ITEM_COUNT_OUT),4) item_count
//  			    	        FROM
//  			    	        :Com_("D_T0_STORAGE_IN_OUT") s
//  			    	        WHERE
//  			    	        	s.REASON!='PD'
//  			    	        AND s.BUSINESS_DATE = to_date(:startDate,'yyyy-mm-dd')
//  	    	        		AND s.BRANCH_ID= :branchId 
//  			    	        GROUP BY
//  			    	            s.ITEM_ID,
//  			    	            s.BRANCH_ID
//  			    	    )
//  			    	    ,
//  			    	    c AS
//  			    	    (
//  			    	        SELECT
//  			    	            a.item_id,
//  			    	            a.branchId,
//  			    	            Round(SUM(a.ITEM_COUNT),4) ITEM_COUNT
//  			    	        FROM
//  			    	            a
//  			    	        GROUP BY
//  			    	            a.item_id,
//  			    	            a.branchId
//  			    	    )
//  			    	SELECT
//  			    	    c.item_id,
//  			    	    m.ITEM_NAME,
//  			    	    c.branchId,
//  			    	    c.ITEM_COUNT item_count,
//  			    	    b.item_count storage_count
//  			    	FROM
//  			    	    c
//  			    	LEFT JOIN
//  			    	    b
//  			    	ON
//  			    	    b.item_id = c.item_id
//  			    	AND b.branchId = c.branchId
//  	    			LEFT JOIN
//  	    			:Com_("D_T2_ITEM_META") m
//  		    		ON
//  		    		    m.ITEM_ID=b.item_id
//  			    	WHERE
//  			    	 b.item_count!=c.item_count
//  			    	 AND ABS(b.item_count-c.item_count)>0.0004
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = branchId;
  String __sJT_5 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_6 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_7 = startDate;
  String __sJT_8 = branchId;
  String __sJT_9 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_10 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_11 = startDate;
  String __sJT_12 = branchId;
  String __sJT_13 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_14 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_15 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_16 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_17 = Com_("D_T1_INPUT_HEADER");
  String __sJT_18 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_19 = startDate;
  String __sJT_20 = branchId;
  String __sJT_21 = branchId;
  String __sJT_22 = Com_("D_T1_LOSS_HEADER");
  String __sJT_23 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_24 = startDate;
  String __sJT_25 = branchId;
  String __sJT_26 = branchId;
  String __sJT_27 = branchId;
  String __sJT_28 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_29 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_30 = startDate;
  String __sJT_31 = branchId;
  String __sJT_32 = branchId;
  String __sJT_33 = Com_("d_t_food_bill");
  String __sJT_34 = Com_("D_T_FOOD_BILLS");
  String __sJT_35 = Com_("D_T2_THERAPY");
  String __sJT_36 = branchId;
  String __sJT_37 = startDate;
  String __sJT_38 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_39 = startDate;
  String __sJT_40 = branchId;
  String __sJT_41 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 14);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_stmt.setString(35, __sJT_35);
      __sJT_stmt.setString(36, __sJT_36);
      __sJT_stmt.setString(37, __sJT_37);
      __sJT_stmt.setString(38, __sJT_38);
      __sJT_stmt.setString(39, __sJT_39);
      __sJT_stmt.setString(40, __sJT_40);
      __sJT_stmt.setString(41, __sJT_41);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new ItemComputeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1451^3*/
		List<Map> detailLst = processComputeIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> computeLcItem(String startDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemComputeIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:1462^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT--入库
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            ih.INPUT_DEPARTMENT_ID branchId
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId 
//  			        UNION ALL
//  			        SELECT --配退入库，采退出库
//  				        rd.ITEM_ID,
//  				        NVL(sd.ITEM_NAME,id.ITEM_NAME)                             ITEM_NAME,
//  				        DECODE(SUBSTR(rd.FORM_ID,0,2) ,'CT',-rd.RETURN_COUNT,'PT',rd.RETURN_COUNT,0)    ITEM_COUNT,
//  				        NVL(ih.INPUT_DEPARTMENT_ID,sh.PROVIDER_ID)                branchId
//  			        FROM
//  			        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			        INNER JOIN
//  			        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  			        ON
//  			            rh.FORM_ID=rd.FORM_ID
//          		 	LEFT JOIN
//          		 	 :Com_("D_T1_SHIPPING_HEADER") sh
//  	                 ON
//  	                     sh.FORM_ID=rd.FORM_REF_ID
//  			        LEFT JOIN
//  			        :Com_("D_T1_SHIPPING_DETAIL") sd
//  			        ON
//  			            sd.FORM_ID = rd.FORM_REF_ID
//  			        AND sd.item_id = rd.ITEM_ID
//  	        		LEFT JOIN
//  	        		:Com_("D_T1_INPUT_HEADER") ih
//  		        	ON
//  		        	    ih.FORM_ID=rd.FORM_REF_ID
//  		        	LEFT JOIN
//  		        	:Com_("D_T1_INPUT_DETAIL") id
//  		        	ON
//  		        	    id.FORM_ID = rd.FORM_REF_ID
//  		        	AND id.item_id = rd.ITEM_ID
//  			        WHERE
//  			            rh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND (sh.PROVIDER_ID = :branchId OR  ih.INPUT_DEPARTMENT_ID = :branchId)
//  	        		UNION ALL
//  	        		SELECT--配送出库，越库出库，外部出库
//  		        	    sd.ITEM_ID,
//  		        	    sd.ITEM_NAME,
//  		        	    -sd.DELIVERY_COUNT ITEM_COUNT,
//  		        	    sh.PROVIDER_ID      branchId
//  	        	    FROM
//  		        	:Com_("D_T1_SHIPPING_HEADER") sh
//  		        	INNER JOIN
//  		        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  		        	ON
//  		        	    sd.FORM_ID= sh.FORM_ID
//      	    		WHERE
//  				    	sh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND sh.PROVIDER_ID = :branchId 
//  	        		UNION ALL
//  			        SELECT --报损单
//  			            ld.ITEM_ID,
//  			            ld.ITEM_NAME,
//  			            -ld.ITEM_COUNT ITEM_COUNT,
//  			            lh.LOSS_BRANCH_ID                                           branchId
//  			        FROM
//  			        :Com_("D_T1_LOSS_HEADER") lh
//  			        INNER JOIN
//  			        :Com_("D_T1_LOSS_DETAIL") ld
//  			        ON
//  			            lh.FORM_ID=ld.FORM_ID
//  	        		WHERE
//  	 		            lh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND lh.LOSS_BRANCH_ID =:branchId
//  			        AND (
//  			                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  		            UNION ALL
//  		    		SELECT --调拨出入
//  			    	    td.ITEM_ID,
//  			    	    td.ITEM_NAME,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,td.ACTUAL_COUNT,-td.ACTUAL_COUNT) ITEM_COUNT,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId
//  		    	    FROM
//  			    	:Com_("D_T1_TRANSFER_HEADER") th
//  			    	INNER JOIN
//  			    	:Com_("D_T1_TRANSFER_DETAIL") td
//  			    	ON
//  			    	    th.FORM_ID=td.FORM_ID
//  		    		WHERE
//  	 		            th.CONFIRM_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			    	AND (
//  			    	        th.IN_BRANCH_ID = :branchId
//  			    	    OR  th.OUT_BRANCH_ID= :branchId)	
//  				    		),
//  				    	    b AS
//  				    	    (
//  				    	        SELECT
//  				    	            s.ITEM_ID,
//  				    	            s.BRANCH_ID                           branchId,
//  				    	            ROUND(SUM(s.ITEM_COUNT_IN-s.ITEM_COUNT_OUT),4) item_count
//  				    	        FROM
//  				    	        :Com_("D_T0_STORAGE_IN_OUT") s
//  				    	        WHERE
//  				    	        	s.REASON!='PD'
//  				    	        AND s.BUSINESS_DATE = to_date(:startDate,'yyyy-mm-dd')
//  				    	        AND s.BRANCH_ID= :branchId 
//  				    	        GROUP BY
//  				    	            s.ITEM_ID,
//  				    	            s.BRANCH_ID
//  				    	    )
//  				    	    ,
//  				    	    c AS
//  				    	    (
//  				    	        SELECT
//  				    	            a.item_id,
//  				    	            a.branchId,
//  				    	            ROUND(SUM(a.ITEM_COUNT),4) ITEM_COUNT
//  				    	        FROM
//  				    	            a
//  				    	        GROUP BY
//  				    	            a.item_id,
//  				    	            a.branchId
//  				    	    )
//  				    	SELECT
//  				    	    c.item_id,
//  				    	    m.item_name,
//  				    	    c.branchId,
//  				    	    c.ITEM_COUNT item_count,
//  				    	    b.item_count storage_count
//  				    	FROM
//  				    	    c
//  				    	LEFT JOIN
//  				    	    b
//  				    	ON
//  				    	    b.item_id = c.item_id
//  				    	AND b.branchId = c.branchId
//  		    			LEFT JOIN
//  		    			:Com_("D_T2_ITEM_META") m
//  			    		ON
//  			    		    m.ITEM_ID=b.item_id
//  				    	WHERE
//  				    	 b.item_count!=c.item_count
//  				    	 AND ABS(b.item_count-c.item_count)>0.0004
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = branchId;
  String __sJT_5 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_6 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_7 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_8 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_9 = Com_("D_T1_INPUT_HEADER");
  String __sJT_10 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_11 = startDate;
  String __sJT_12 = branchId;
  String __sJT_13 = branchId;
  String __sJT_14 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_15 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_16 = startDate;
  String __sJT_17 = branchId;
  String __sJT_18 = Com_("D_T1_LOSS_HEADER");
  String __sJT_19 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_20 = startDate;
  String __sJT_21 = branchId;
  String __sJT_22 = branchId;
  String __sJT_23 = branchId;
  String __sJT_24 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_25 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_26 = startDate;
  String __sJT_27 = branchId;
  String __sJT_28 = branchId;
  String __sJT_29 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_30 = startDate;
  String __sJT_31 = branchId;
  String __sJT_32 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 15);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new ItemComputeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1613^4*/
			List<Map> detailLst = processComputeIter(detailIter);
			detailIter.close();
			closeDefaultContext(myCtx);
			return detailLst;
	}
	
	public List<Map> computeCFItem(String startDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemComputeIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:1624^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT --采购入库,产品入库
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            ih.INPUT_DEPARTMENT_ID branchId
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId 
//  			        UNION ALL
//  			        SELECT--物流中心采购退货加库存，自己退货减库存
//  			            rd.ITEM_ID,
//  			            id.ITEM_NAME            ITEM_NAME,
//  			            DECODE(ih.INPUT_DEPARTMENT_ID,:branchId,-rd.RETURN_COUNT ,rd.RETURN_COUNT )  ITEM_COUNT,
//  			            DECODE(ih.INPUT_DEPARTMENT_ID, 'F00' ,ih.INPUT_DEPARTMENT_ID,ih.PROVIDER_ID )     branchId
//  			        FROM
//  			        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			        INNER JOIN
//  			        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  			        ON
//  			            rh.FORM_ID=rd.FORM_ID
//              		 LEFT JOIN
//              		 :Com_("D_T1_INPUT_HEADER") ih
//  	                 ON
//  	                     ih.FORM_ID=rd.FORM_REF_ID
//  	                 LEFT JOIN
//  	                 :Com_("D_T1_INPUT_DETAIL") id
//  	                 ON
//  	                     id.FORM_ID = rd.FORM_REF_ID
//  	                 AND id.item_id = rd.ITEM_ID
//  			        WHERE
//  			            rh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  					AND (ih.INPUT_DEPARTMENT_ID=:branchId or ih.PROVIDER_ID=:branchId)
//  	        		UNION ALL
//  			        SELECT --报损单
//  			            ld.ITEM_ID,
//  			            ld.ITEM_NAME,
//  			            -ld.ITEM_COUNT ITEM_COUNT,
//  			            lh.LOSS_BRANCH_ID         branchId
//  			        FROM
//  			        :Com_("D_T1_LOSS_HEADER") lh
//  			        INNER JOIN
//  			        :Com_("D_T1_LOSS_DETAIL") ld
//  			        ON
//  			            lh.FORM_ID=ld.FORM_ID
//  	        		WHERE
//  	 		            lh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND lh.LOSS_BRANCH_ID =:branchId
//  			        AND (
//  			                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  		           UNION ALL
//  		    		SELECT --调拨
//  			    	    td.ITEM_ID,
//  			    	    td.ITEM_NAME,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,td.ACTUAL_COUNT,-td.ACTUAL_COUNT) ITEM_COUNT,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId
//  			    	FROM
//  			    	:Com_("D_T1_TRANSFER_HEADER") th
//  			    	INNER JOIN
//  			    	:Com_("D_T1_TRANSFER_DETAIL") td
//  			    	ON
//  			    	    th.FORM_ID=td.FORM_ID
//  		    		WHERE
//  	 		            th.CONFIRM_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			    	AND (
//  			    	        th.IN_BRANCH_ID = :branchId
//  			    	    OR  th.OUT_BRANCH_ID= :branchId)
//  		    	    UNION ALL
//  		    	    SELECT --领料,超领,退料,非工单领料
//  			    	    rd.ITEM_ID,
//  			    	    rd.ITEM_NAME,
//  			    	    DECODE(rh.FORM_TYPE,'return',rd.ITEM_COUNT,-rd.RECEIVE_COUNT) ITEM_COUNT,
//  			    	    rh.FORM_BRANCH_ID            branchId
//  			    	FROM
//  			    	:Com_("D_T1_REQUISITION_HEADER") rh
//  			    	INNER JOIN
//  			    	:Com_("D_T1_REQUISITION_DETAIL") rd
//  			    	ON
//  			    	    rd.FORM_ID= rh.FORM_ID
//  			    	LEFT JOIN
//  			    	:Com_("D_T2_ITEM_PRICE") p
//  			    	ON
//  			    	    p.ITEM_ID = rd.ITEM_ID
//  			    	AND p.PRICE_TYPE LIKE 'BENCHMARK%' --标准价
//  			    	AND p.IS_CURRENT =1
//  			    	WHERE
//  		    	 		rh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			    	AND rh.FORM_BRANCH_ID = :branchId 
//  			    	UNION ALL
//  	        		SELECT--出品出库
//  		        	    sd.ITEM_ID,
//  		        	    sd.ITEM_NAME,
//  		        	    -sd.DELIVERY_COUNT ITEM_COUNT,
//  		        	    sh.PROVIDER_ID      branchId
//  		        	FROM
//  		        	:Com_("D_T1_SHIPPING_HEADER") sh
//  		        	INNER JOIN
//  		        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  		        	ON
//  		        	    sd.FORM_ID= sh.FORM_ID
//      	    		WHERE
//  				    	sh.AUDIT_TIME = to_date(:startDate,'yyyy-mm-dd')
//  			        AND sh.PROVIDER_ID = :branchId 
//  				    		),
//  				    	    b AS
//  				    	    (
//  				    	        SELECT
//  				    	            s.ITEM_ID,
//  				    	            s.BRANCH_ID                           branchId,
//  				    	            ROUND(SUM(s.ITEM_COUNT_IN-s.ITEM_COUNT_OUT),4) item_count
//  				    	        FROM
//  				    	        :Com_("D_T0_STORAGE_IN_OUT") s
//  				    	        WHERE
//  				    	        	s.REASON!='PD'
//  				    	        AND s.BUSINESS_DATE = to_date(:startDate,'yyyy-mm-dd')
//  		    	        		AND s.BRANCH_ID= :branchId 
//  				    	        GROUP BY
//  				    	            s.ITEM_ID,
//  				    	            s.BRANCH_ID
//  				    	    )
//  				    	    ,
//  				    	    c AS
//  				    	    (
//  				    	        SELECT
//  				    	            a.item_id,
//  				    	            a.branchId,
//  				    	            ROUND(SUM(a.ITEM_COUNT),4) ITEM_COUNT
//  				    	        FROM
//  				    	            a
//  				    	        GROUP BY
//  				    	            a.item_id,
//  				    	            a.branchId
//  				    	    )
//  				    	SELECT
//  				    	    c.item_id,
//  				    	    m.item_name,
//  				    	    c.branchId,
//  				    	    c.ITEM_COUNT item_count,
//  				    	    b.item_count storage_count
//  				    	FROM
//  				    	    c
//  				    	LEFT JOIN
//  				    	    b
//  				    	ON
//  				    	    b.item_id = c.item_id
//  				    	AND b.branchId = c.branchId
//  		    			LEFT JOIN
//  		    			:Com_("D_T2_ITEM_META") m
//  			    		ON
//  			    		    m.ITEM_ID=b.item_id
//  				    	WHERE
//  				    	 b.item_count!=c.item_count
//  				    	 AND ABS(b.item_count-c.item_count)>0.0004
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = branchId;
  String __sJT_5 = branchId;
  String __sJT_6 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_7 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_8 = Com_("D_T1_INPUT_HEADER");
  String __sJT_9 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_10 = startDate;
  String __sJT_11 = branchId;
  String __sJT_12 = branchId;
  String __sJT_13 = Com_("D_T1_LOSS_HEADER");
  String __sJT_14 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_15 = startDate;
  String __sJT_16 = branchId;
  String __sJT_17 = branchId;
  String __sJT_18 = branchId;
  String __sJT_19 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_20 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_21 = startDate;
  String __sJT_22 = branchId;
  String __sJT_23 = branchId;
  String __sJT_24 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_25 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_26 = Com_("D_T2_ITEM_PRICE");
  String __sJT_27 = startDate;
  String __sJT_28 = branchId;
  String __sJT_29 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_30 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_31 = startDate;
  String __sJT_32 = branchId;
  String __sJT_33 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_34 = startDate;
  String __sJT_35 = branchId;
  String __sJT_36 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 16);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_stmt.setString(35, __sJT_35);
      __sJT_stmt.setString(36, __sJT_36);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new ItemComputeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1787^4*/
			List<Map> detailLst = processComputeIter(detailIter);
			detailIter.close();
			closeDefaultContext(myCtx);
			return detailLst;
	}
	
	private List<Map> processComputeIter(ItemComputeIter detailIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("branchId", detailIter.branchId());
			detail.put("itemCount", detailIter.item_count());
			detail.put("storageCount", detailIter.storage_count());
			headerLst.add(detail);
		}
		return headerLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:1809^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemDetailIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemDetailIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_dimensionNdx = findColumn("item_dimension");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_unit_priceNdx = findColumn("item_unit_price");
    item_countNdx = findColumn("item_count");
    form_idNdx = findColumn("form_id");
    pay_amtNdx = findColumn("pay_amt");
    type_flagNdx = findColumn("type_flag");
    branchIdNdx = findColumn("branchId");
    reasonNdx = findColumn("reason");
    operationTimeNdx = findColumn("operationTime");
    restaurantDateNdx = findColumn("restaurantDate");
  }
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
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
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public String type_flag() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(type_flagNdx);
  }
  private int type_flagNdx;
  public String branchId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchIdNdx);
  }
  private int branchIdNdx;
  public String reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(reasonNdx);
  }
  private int reasonNdx;
  public Date operationTime() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(operationTimeNdx);
  }
  private int operationTimeNdx;
  public Date restaurantDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(restaurantDateNdx);
  }
  private int restaurantDateNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1809^256*/
	public List<Map> queryItem(String startDate, String endDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
//		Date sDate = SqlDateUtil.getSqlDate(startDate);
//		Date eDate = SqlDateUtil.getSqlDate(endDate);
//		if(!TextUtil.isEmpty(branchId)){
//			
//		}
		DefaultContext myCtx = getDefaultContext();
		ItemDetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:1819^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT--采购入库
//  			            id.FORM_ID,
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.ITEM_DIMENSION,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            id.ITEM_UNIT_PRICE,
//  			            id.PAY_AMT,
//  			            ih.INPUT_DEPARTMENT_ID branchId,
//  			            ih.AUDIT_TIME          restaurantDate,
//  			            ih.AUDIT_TIME_ACTUAL   operationTime,
//  			            DECODE(ih.FORM_TYPE,'PURCHASE','CGRK','PRODUCE','CPRK','MANUAL','CGRK','SEMIS','PSRK' ,'')  reason,
//  			            'IN' type_flag
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND ih.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId 
//  		        UNION ALL--配送入库
//  		        SELECT
//  			        sd.FORM_ID,
//  			        sd.ITEM_ID,
//  			        sd.ITEM_NAME,
//  			        sd.ITEM_DIMENSION,
//  			        sd.RECEIVE_COUNT ITEM_COUNT,
//  			        sd.ITEM_UNIT_PRICE,
//  			        sd.PAY_AMT,
//  			        sh.REQUESTER_ID branchId,
//  			        sh.INPUT_TIME        restaurantDate,
//  			        sh.INPUT_TIME_ACTUAL operationTime,
//  			        'PSRK'          reason,
//  			        'IN'            type_flag
//  		        FROM
//  	        	:Com_("D_T1_SHIPPING_HEADER") sh
//  	        	INNER JOIN
//  	        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  			    ON
//  			        sd.FORM_ID= sh.FORM_ID
//  			    WHERE
//  			    	sh.INPUT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss') 
//  				AND sh.INPUT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  		        AND sh.REQUESTER_ID = :branchId 
//          		UNION ALL--配送出库
//  		        SELECT
//  			        sd.FORM_ID,
//  			        sd.ITEM_ID,
//  			        sd.ITEM_NAME,
//  			        sd.ITEM_DIMENSION,
//  			        sd.RECEIVE_COUNT ITEM_COUNT,
//  			        sd.ITEM_UNIT_PRICE,
//  			        sd.PAY_AMT,
//  			        sh.REQUESTER_ID branchId,
//  			        sh.INPUT_TIME        restaurantDate,
//  			        sh.INPUT_TIME_ACTUAL operationTime,
//  			        'PSCK'          reason,
//  			        'OUT'            type_flag
//  		        FROM
//  	        	:Com_("D_T1_SHIPPING_HEADER") sh
//  	        	INNER JOIN
//  	        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  			    ON
//  			        sd.FORM_ID= sh.FORM_ID
//  			    WHERE
//  			    	sh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss') 
//  				AND sh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  		        AND sh.PROVIDER_ID = :branchId 
//  		        UNION ALL
//  		        SELECT--配送采购退货
//  		            rd.FORM_ID,
//  		            rd.ITEM_ID,
//  		            NVL(sd.ITEM_NAME,id.ITEM_NAME)                             ITEM_NAME,
//  		            NVL(sd.ITEM_DIMENSION,id.ITEM_DIMENSION)                   ITEM_DIMENSION,
//  		            rd.RETURN_COUNT                                            ITEM_COUNT,
//  		            NVL(sd.ITEM_UNIT_PRICE,id.ITEM_UNIT_PRICE)                 ITEM_UNIT_PRICE,
//  		            rd.RETURN_PAY_AMT                                          PAY_AMT,
//  		            NVL(ih.INPUT_DEPARTMENT_ID,sh.REQUESTER_ID)                branchId,
//  		            rh.AUDIT_TIME                                              restaurantDate,
//  		            rh.AUDIT_TIME_ACTUAL                                       operationTime,
//  		            DECODE(SUBSTR(rd.FORM_ID,0,2) ,'CT','CGTH','PT','PSTH','') reason,
//  		            'OUT'                                                      type_flag
//  		        FROM
//  		        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  		        INNER JOIN
//  		        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  		        ON
//  		            rh.FORM_ID=rd.FORM_ID
//      		 	LEFT JOIN
//      		 	 :Com_("D_T1_SHIPPING_HEADER") sh
//                   ON
//                       sh.FORM_ID=rd.FORM_REF_ID
//  		        LEFT JOIN
//  		        :Com_("D_T1_SHIPPING_DETAIL") sd
//  		        ON
//  		            sd.FORM_ID = rd.FORM_REF_ID
//  		        AND sd.item_id = rd.ITEM_ID
//      		  LEFT JOIN
//      		  :Com_("D_T1_INPUT_HEADER") ih
//                ON
//                    ih.FORM_ID=rd.FORM_REF_ID
//  		        LEFT JOIN
//  		        :Com_("D_T1_INPUT_DETAIL") id
//  		        ON
//  		            id.FORM_ID = rd.FORM_REF_ID
//  		        AND id.item_id = rd.ITEM_ID
//  		        WHERE
//  		            rh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  				AND rh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  		        AND (ih.INPUT_DEPARTMENT_ID=:branchId  OR  sh.REQUESTER_ID=:branchId )
//  		        UNION ALL
//  		        SELECT --报损单
//  		            ld.FORM_ID,
//  		            ld.ITEM_ID,
//  		            ld.ITEM_NAME,
//  		            ld.ITEM_DIMENSION,
//  		            ld.ITEM_COUNT,
//  		            ld.UNIT_PRICE ITEM_UNIT_PRICE,
//  		            ld.PAY_AMT,
//  		            lh.LOSS_BRANCH_ID                                           branchId,
//  		            lh.AUDIT_TIME                                               restaurantDate,
//  		            lh.AUDIT_TIME_ACTUAL                                        operationTime,
//  		            DECODE(lh.LOSS_TYPE,'RAWLOSS','YLBS','DISHLOSS','CPBS', '') reason,
//  		            'OUT'                                                       type_flag
//  		        FROM
//  		        :Com_("D_T1_LOSS_HEADER") lh
//  		        INNER JOIN
//  		        :Com_("D_T1_LOSS_DETAIL") ld
//  		        ON
//  		            lh.FORM_ID=ld.FORM_ID
//          		WHERE
//   		            lh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//   				AND lh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  		        AND lh.LOSS_BRANCH_ID =:branchId
//  		        AND (
//  		                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  		            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  	    		UNION ALL
//  	    		SELECT --调拨出入
//  		    	    td.FORM_ID,
//  		    	    td.ITEM_ID,
//  		    	    td.ITEM_NAME,
//  		    	    td.ITEM_DIMENSION,
//  		    	    td.ACTUAL_COUNT ITEM_COUNT,
//  		    	    td.UNIT_PRICE ITEM_UNIT_PRICE,
//  		    	    td.PAY_AMT,
//  		    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId,
//  		    	    th.CONFIRM_TIME                                                     restaurantDate,
//  		    	    th.AUDIT_TIME_ACTUAL                                                operationTime,
//  		    	    'DB'                                                                reason,
//  		    	    DECODE(:branchId ,th.IN_BRANCH_ID,'IN','OUT')                       type_flag
//  	    	    FROM
//  		    	:Com_("D_T1_TRANSFER_HEADER") th
//  		    	INNER JOIN
//  		    	:Com_("D_T1_TRANSFER_DETAIL") td
//  		    	ON
//  		    	    th.FORM_ID=td.FORM_ID
//  	    		WHERE
//   		            th.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//   				AND th.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  		    	AND (
//  		    	        th.IN_BRANCH_ID = :branchId
//  		    	    OR  th.OUT_BRANCH_ID= :branchId)	
//  	    	    UNION ALL
//  	            SELECT
//  	                ''              form_id,
//  	                trim(t.item_id) item_id,
//  	                t.ITEM_NAME,
//  	                t.ITEM_DIMENSION,
//  	                SUM(t.item_gross_count * d.nqty) ITEM_COUNT,
//  	                0                                ITEM_UNIT_PRICE,
//  	                0                                PAY_AMT,
//  	                a.BRANCH_ID,
//  	                b.dbusiness                      restaurantDate,
//  	                a.OPERATING_TIME                 operationTime,
//  	                'LLKU'                           reason,
//  	                'OUT'                            type_flag
//  	            FROM
//  	            :Com_("d_t_food_bill") b,
//  	            :Com_("D_T_FOOD_BILLS") d,
//  	            :Com_("D_T2_THERAPY") t,
//  	            :Com_("D_T3_DAILY_SETTLE_RECORD") a
//  	            WHERE
//  	                trim(d.cbill_c) = trim(b.cbill_c)
//  	            AND trim(t.therapy_id) = trim(d.cfood_c)
//  	            AND trim(b.cbranch_c) = :branchId
//  	            AND a.BRANCH_ID=trim(b.cbranch_c)
//  	            AND b.dbusiness = a.BUSINESS_DATE
//  	            AND a.OPERATING_TIME >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  	            AND a.OPERATING_TIME <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  	            GROUP BY
//  	                trim(t.item_id),
//  	                t.ITEM_NAME,
//  	                t.ITEM_DIMENSION,
//  	                b.dbusiness,
//  	                a.BRANCH_ID,
//  	                a.OPERATING_TIME
//  			    		)
//  			SELECT
//  			    *
//  			FROM
//  			    a
//  			ORDER BY
//  			    a.item_id,a.operationTime
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = branchId;
  String __sJT_6 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_7 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_8 = startDate;
  String __sJT_9 = endDate;
  String __sJT_10 = branchId;
  String __sJT_11 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_12 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_13 = startDate;
  String __sJT_14 = endDate;
  String __sJT_15 = branchId;
  String __sJT_16 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_17 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_18 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_19 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_20 = Com_("D_T1_INPUT_HEADER");
  String __sJT_21 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_22 = startDate;
  String __sJT_23 = endDate;
  String __sJT_24 = branchId;
  String __sJT_25 = branchId;
  String __sJT_26 = Com_("D_T1_LOSS_HEADER");
  String __sJT_27 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_28 = startDate;
  String __sJT_29 = endDate;
  String __sJT_30 = branchId;
  String __sJT_31 = branchId;
  String __sJT_32 = branchId;
  String __sJT_33 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_34 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_35 = startDate;
  String __sJT_36 = endDate;
  String __sJT_37 = branchId;
  String __sJT_38 = branchId;
  String __sJT_39 = Com_("d_t_food_bill");
  String __sJT_40 = Com_("D_T_FOOD_BILLS");
  String __sJT_41 = Com_("D_T2_THERAPY");
  String __sJT_42 = Com_("D_T3_DAILY_SETTLE_RECORD");
  String __sJT_43 = branchId;
  String __sJT_44 = startDate;
  String __sJT_45 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 17);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_stmt.setString(35, __sJT_35);
      __sJT_stmt.setString(36, __sJT_36);
      __sJT_stmt.setString(37, __sJT_37);
      __sJT_stmt.setString(38, __sJT_38);
      __sJT_stmt.setString(39, __sJT_39);
      __sJT_stmt.setString(40, __sJT_40);
      __sJT_stmt.setString(41, __sJT_41);
      __sJT_stmt.setString(42, __sJT_42);
      __sJT_stmt.setString(43, __sJT_43);
      __sJT_stmt.setString(44, __sJT_44);
      __sJT_stmt.setString(45, __sJT_45);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new ItemDetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:2030^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> queryLcItem(String startDate,String endDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
//		Date sDate = SqlDateUtil.getSqlDate(startDate);
//		Date eDate = SqlDateUtil.getSqlDate(endDate);
//		if(!TextUtil.isEmpty(branchId)){
//			
//		}
		DefaultContext myCtx = getDefaultContext();
		ItemDetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:2046^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT--入库
//  			            id.FORM_ID,
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.ITEM_DIMENSION,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            id.ITEM_UNIT_PRICE,
//  			            id.PAY_AMT,
//  			            ih.INPUT_DEPARTMENT_ID branchId,
//  			            ih.AUDIT_TIME          restaurantDate,
//  			            ih.AUDIT_TIME_ACTUAL   operationTime,
//  			            DECODE(ih.FORM_TYPE,'PURCHASE','CGRK','PRODUCE','CPRK','MANUAL','CGRK','SEMIS','PSRK','JSRK','JSRK' ,'')  reason,
//  			            'IN' type_flag
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND ih.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId 
//  			        UNION ALL
//  			        SELECT --配退入库，采退出库
//  				        rd.FORM_ID,
//  				        rd.ITEM_ID,
//  				        NVL(sd.ITEM_NAME,id.ITEM_NAME)                             ITEM_NAME,
//  				        NVL(sd.ITEM_DIMENSION,id.ITEM_DIMENSION)                   ITEM_DIMENSION,
//  				        rd.RETURN_COUNT                                            ITEM_COUNT,
//  				        NVL(sd.ITEM_UNIT_PRICE,id.ITEM_UNIT_PRICE)                 ITEM_UNIT_PRICE,
//  				        rd.RETURN_PAY_AMT                                          PAY_AMT,
//  				        NVL(ih.INPUT_DEPARTMENT_ID,sh.PROVIDER_ID)                branchId,
//  				        rh.AUDIT_TIME                                              restaurantDate,
//  				        rh.AUDIT_TIME_ACTUAL                                       operationTime,
//  				        DECODE(SUBSTR(rd.FORM_ID,0,2) ,'CT','CGTH','PT','PSTH','') reason,
//  				        DECODE(SUBSTR(rd.FORM_ID,0,2) ,'CT','OUT','PT','IN','')   type_flag
//  			        FROM
//  			        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			        INNER JOIN
//  			        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  			        ON
//  			            rh.FORM_ID=rd.FORM_ID
//          		 	LEFT JOIN
//          		 	 :Com_("D_T1_SHIPPING_HEADER") sh
//  	                 ON
//  	                     sh.FORM_ID=rd.FORM_REF_ID
//  			        LEFT JOIN
//  			        :Com_("D_T1_SHIPPING_DETAIL") sd
//  			        ON
//  			            sd.FORM_ID = rd.FORM_REF_ID
//  			        AND sd.item_id = rd.ITEM_ID
//  	        		LEFT JOIN
//  	        		:Com_("D_T1_INPUT_HEADER") ih
//  		        	ON
//  		        	    ih.FORM_ID=rd.FORM_REF_ID
//  		        	LEFT JOIN
//  		        	:Com_("D_T1_INPUT_DETAIL") id
//  		        	ON
//  		        	    id.FORM_ID = rd.FORM_REF_ID
//  		        	AND id.item_id = rd.ITEM_ID
//  			        WHERE
//  			            rh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND rh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND (sh.PROVIDER_ID = :branchId OR  ih.INPUT_DEPARTMENT_ID = :branchId)
//  			        UNION ALL--半成品配送入库
//  			        SELECT
//  				        sd.FORM_ID,
//  				        sd.ITEM_ID,
//  				        sd.ITEM_NAME,
//  				        sd.ITEM_DIMENSION,
//  				        sd.RECEIVE_COUNT ITEM_COUNT,
//  				        sd.ITEM_UNIT_PRICE,
//  				        sd.PAY_AMT,
//  				        sh.REQUESTER_ID branchId,
//  				        sh.INPUT_TIME        restaurantDate,
//  				        sh.INPUT_TIME_ACTUAL operationTime,
//  				        'PSRK'          reason,
//  				        'IN'            type_flag
//  			        FROM
//  		        	:Com_("D_T1_SHIPPING_HEADER") sh
//  		        	INNER JOIN
//  		        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  				    ON
//  				        sd.FORM_ID= sh.FORM_ID
//  				    WHERE
//  				    	sh.INPUT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND sh.INPUT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND sh.REQUESTER_ID = :branchId 
//  	        		UNION ALL
//  	        		SELECT--配送出库，越库出库，外部出库
//  		        	    sd.FORM_ID,
//  		        	    sd.ITEM_ID,
//  		        	    sd.ITEM_NAME,
//  		        	    sd.ITEM_DIMENSION,
//  		        	    sd.SHIPPING_COUNT ITEM_COUNT,
//  		        	    sd.ITEM_UNIT_PRICE,
//  		        	    sd.PAY_AMT,
//  		        	    sh.PROVIDER_ID      branchId,
//  		        	    sh.AUDIT_TIME        restaurantDate,
//  		        	    sh.AUDIT_TIME_ACTUAL operationTime,
//  		        	    decode(sh.FORM_TYPE,'INNER_UNIFIED','PSCK','INNER_CROSS','YKCK','WBCH')               reason,
//  		        	    'OUT'                 type_flag
//  	        	    FROM
//  		        	:Com_("D_T1_SHIPPING_HEADER") sh
//  		        	INNER JOIN
//  		        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  		        	ON
//  		        	    sd.FORM_ID= sh.FORM_ID
//      	    		WHERE
//  				    	sh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND sh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND sh.PROVIDER_ID = :branchId 
//  	        		UNION ALL
//  			        SELECT --报损单
//  			            ld.FORM_ID,
//  			            ld.ITEM_ID,
//  			            ld.ITEM_NAME,
//  			            ld.ITEM_DIMENSION,
//  			            ld.ITEM_COUNT,
//  			            ld.UNIT_PRICE ITEM_UNIT_PRICE,
//  			            ld.PAY_AMT,
//  			            lh.LOSS_BRANCH_ID                                           branchId,
//  			            lh.AUDIT_TIME                                               restaurantDate,
//  			            lh.AUDIT_TIME_ACTUAL                                        operationTime,
//  			            DECODE(lh.LOSS_TYPE,'RAWLOSS','YLBS','DISHLOSS','CPBS', '') reason,
//  			            'OUT'                                                       type_flag
//  			        FROM
//  			        :Com_("D_T1_LOSS_HEADER") lh
//  			        INNER JOIN
//  			        :Com_("D_T1_LOSS_DETAIL") ld
//  			        ON
//  			            lh.FORM_ID=ld.FORM_ID
//  	        		WHERE
//  	 		            lh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  	 				AND lh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND lh.LOSS_BRANCH_ID =:branchId
//  			        AND (
//  			                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  		            UNION ALL
//  		    		SELECT --调拨出入
//  			    	    td.FORM_ID,
//  			    	    td.ITEM_ID,
//  			    	    td.ITEM_NAME,
//  			    	    td.ITEM_DIMENSION,
//  			    	    td.ACTUAL_COUNT ITEM_COUNT,
//  			    	    td.UNIT_PRICE ITEM_UNIT_PRICE,
//  			    	    td.PAY_AMT,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId,
//  			    	    th.CONFIRM_TIME                                                     restaurantDate,
//  			    	    th.AUDIT_TIME_ACTUAL                                                operationTime,
//  			    	    'DB'                                                                reason,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,'IN','OUT')                       type_flag
//  		    	    FROM
//  			    	:Com_("D_T1_TRANSFER_HEADER") th
//  			    	INNER JOIN
//  			    	:Com_("D_T1_TRANSFER_DETAIL") td
//  			    	ON
//  			    	    th.FORM_ID=td.FORM_ID
//  		    		WHERE
//  	 		            th.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  	 				AND th.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			    	AND (
//  			    	        th.IN_BRANCH_ID = :branchId
//  			    	    OR  th.OUT_BRANCH_ID= :branchId)	
//  				    		)
//  			SELECT
//  			    *
//  			FROM
//  			    a
//  			ORDER BY
//  			    a.item_id,a.operationTime
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = branchId;
  String __sJT_6 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_7 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_8 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_9 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_10 = Com_("D_T1_INPUT_HEADER");
  String __sJT_11 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_12 = startDate;
  String __sJT_13 = endDate;
  String __sJT_14 = branchId;
  String __sJT_15 = branchId;
  String __sJT_16 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_17 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_18 = startDate;
  String __sJT_19 = endDate;
  String __sJT_20 = branchId;
  String __sJT_21 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_22 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_23 = startDate;
  String __sJT_24 = endDate;
  String __sJT_25 = branchId;
  String __sJT_26 = Com_("D_T1_LOSS_HEADER");
  String __sJT_27 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_28 = startDate;
  String __sJT_29 = endDate;
  String __sJT_30 = branchId;
  String __sJT_31 = branchId;
  String __sJT_32 = branchId;
  String __sJT_33 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_34 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_35 = startDate;
  String __sJT_36 = endDate;
  String __sJT_37 = branchId;
  String __sJT_38 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 18);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_stmt.setString(35, __sJT_35);
      __sJT_stmt.setString(36, __sJT_36);
      __sJT_stmt.setString(37, __sJT_37);
      __sJT_stmt.setString(38, __sJT_38);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new ItemDetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:2223^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> queryCFItem(String startDate, String endDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
//		Date sDate = SqlDateUtil.getSqlDate(startDate);
//		Date eDate = SqlDateUtil.getSqlDate(endDate);
//		if(!TextUtil.isEmpty(branchId)){
//			
//		}
		DefaultContext myCtx = getDefaultContext();
		ItemDetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:2239^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT --采购入库,产品入库
//  			            id.FORM_ID,
//  			            id.ITEM_ID,
//  			            id.ITEM_NAME,
//  			            id.ITEM_DIMENSION,
//  			            id.RECEIVE_COUNT ITEM_COUNT,
//  			            id.ITEM_UNIT_PRICE,
//  			            id.PAY_AMT,
//  			            ih.INPUT_DEPARTMENT_ID branchId,
//  			            ih.AUDIT_TIME          restaurantDate,
//  			            ih.AUDIT_TIME_ACTUAL   operationTime,
//  			            DECODE(ih.FORM_TYPE,'PURCHASE','CGRK','PRODUCE','CPRK','MANUAL','CGRK','SEMIS','PSRK' ,'')  reason,
//  			            'IN' type_flag
//  			        FROM
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            ih.FORM_ID=id.FORM_ID
//  			        WHERE
//  			        	ih.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND ih.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND ih.INPUT_DEPARTMENT_ID = :branchId 
//  			        UNION ALL
//  			        SELECT--采购退货（物流退到央厂加库存，央厂退货减库存）
//  			            rd.FORM_ID,
//  			            rd.ITEM_ID,
//  			            id.ITEM_NAME            ITEM_NAME,
//  			            id.ITEM_DIMENSION  ITEM_DIMENSION,
//  			            rd.RETURN_COUNT         ITEM_COUNT,
//  			            id.ITEM_UNIT_PRICE,
//  			            rd.RETURN_PAY_AMT       PAY_AMT,
//  			            ih.INPUT_DEPARTMENT_ID                                            branchId,
//  			            rh.AUDIT_TIME                                              restaurantDate,
//  			            rh.AUDIT_TIME_ACTUAL                                       operationTime,
//  			            DECODE(SUBSTR(rd.FORM_ID,0,2) ,'CT','CGTH','PT','PSTH','') reason,
//  			            DECODE(ih.INPUT_DEPARTMENT_ID,:branchId,'OUT','IN')        type_flag
//  			        FROM
//  			        :Com_("D_T1_RETURN_GOODS_HEADER") rh
//  			        INNER JOIN
//  			        :Com_("D_T1_RETURN_GOODS_DETAIL") rd
//  			        ON
//  			            rh.FORM_ID=rd.FORM_ID
//              		 LEFT JOIN
//              		 :Com_("D_T1_INPUT_HEADER") ih
//  	                 ON
//  	                     ih.FORM_ID=rd.FORM_REF_ID
//  	                 LEFT JOIN
//  	                 :Com_("D_T1_INPUT_DETAIL") id
//  	                 ON
//  	                     id.FORM_ID = rd.FORM_REF_ID
//  	                 AND id.item_id = rd.ITEM_ID
//  			        WHERE
//  			            rh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND rh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND (ih.INPUT_DEPARTMENT_ID=:branchId or ih.PROVIDER_ID=:branchId)
//  	        		UNION ALL
//  			        SELECT --报损单
//  			            ld.FORM_ID,
//  			            ld.ITEM_ID,
//  			            ld.ITEM_NAME,
//  			            ld.ITEM_DIMENSION,
//  			            ld.ITEM_COUNT,
//  			            ld.UNIT_PRICE ITEM_UNIT_PRICE,
//  			            ld.PAY_AMT,
//  			            lh.LOSS_BRANCH_ID                                           branchId,
//  			            lh.AUDIT_TIME                                               restaurantDate,
//  			            lh.AUDIT_TIME_ACTUAL                                        operationTime,
//  			            DECODE(lh.LOSS_TYPE,'RAWLOSS','YLBS','DISHLOSS','CPBS', '') reason,
//  			            'OUT'                                                       type_flag
//  			        FROM
//  			        :Com_("D_T1_LOSS_HEADER") lh
//  			        INNER JOIN
//  			        :Com_("D_T1_LOSS_DETAIL") ld
//  			        ON
//  			            lh.FORM_ID=ld.FORM_ID
//  	        		WHERE
//  	 		            lh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  	 				AND lh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND lh.LOSS_BRANCH_ID =:branchId
//  			        AND (
//  			                ld.REASON= DECODE(lh.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  lh.LOSS_TYPE = 'RAWLOSS')
//  		           UNION ALL
//  		    		SELECT --调拨
//  			    	    td.FORM_ID,
//  			    	    td.ITEM_ID,
//  			    	    td.ITEM_NAME,
//  			    	    td.ITEM_DIMENSION,
//  			    	    td.ACTUAL_COUNT ITEM_COUNT,
//  			    	    td.UNIT_PRICE ITEM_UNIT_PRICE,
//  			    	    td.PAY_AMT,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,th.IN_BRANCH_ID,th.OUT_BRANCH_ID) branchId,
//  			    	    th.CONFIRM_TIME                                                     restaurantDate,
//  			    	    th.AUDIT_TIME_ACTUAL                                                operationTime,
//  			    	    'DB'																reason,
//  			    	    DECODE(:branchId ,th.IN_BRANCH_ID,'IN','OUT')                       type_flag
//  			    	FROM
//  			    	:Com_("D_T1_TRANSFER_HEADER") th
//  			    	INNER JOIN
//  			    	:Com_("D_T1_TRANSFER_DETAIL") td
//  			    	ON
//  			    	    th.FORM_ID=td.FORM_ID
//  		    		WHERE
//  	 		            th.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  	 				AND th.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			    	AND (
//  			    	        th.IN_BRANCH_ID = :branchId
//  			    	    OR  th.OUT_BRANCH_ID= :branchId)
//  		    	    UNION ALL
//  		    	    SELECT --领料,超领,退料,非工单领料
//  			    	    rd.FORM_ID,
//  			    	    rd.ITEM_ID,
//  			    	    rd.ITEM_NAME,
//  			    	    rd.ITEM_DIMENSION,
//  			    	    DECODE(rh.FORM_TYPE,'return',rd.ITEM_COUNT,rd.RECEIVE_COUNT) ITEM_COUNT,
//  			    	    p.ITEM_PRICE,
//  			    	    DECODE(rh.FORM_TYPE,'return',rd.ITEM_COUNT,rd.RECEIVE_COUNT)*p.ITEM_PRICE               PAY_AMT,
//  			    	    rh.FORM_BRANCH_ID                                                                      branchId,
//  			    	    rh.AUDIT_TIME                                                                    restaurantDate,
//  			    	    rh.AUDIT_TIME_ACTUAL                                                              operationTime,
//  			    	    DECODE(rh.FORM_TYPE,'manual','FGLL','produce', 'SCLL','extra','SCCL','return','SCTL','') reason,
//  			    	    DECODE(rh.FORM_TYPE,'return','IN','OUT') type_flag
//  			    	FROM
//  			    	:Com_("D_T1_REQUISITION_HEADER") rh
//  			    	INNER JOIN
//  			    	:Com_("D_T1_REQUISITION_DETAIL") rd
//  			    	ON
//  			    	    rd.FORM_ID= rh.FORM_ID
//  			    	LEFT JOIN
//  			    	:Com_("D_T2_ITEM_PRICE") p
//  			    	ON
//  			    	    p.ITEM_ID = rd.ITEM_ID
//  			    	AND p.PRICE_TYPE LIKE 'BENCHMARK%' --标准价
//  			    	AND p.IS_CURRENT =1
//  			    	WHERE
//  		    	 		rh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  	 				AND rh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			    	AND rh.FORM_BRANCH_ID = :branchId 
//  			    	UNION ALL
//  	        		SELECT--出品出库
//  		        	    sd.FORM_ID,
//  		        	    sd.ITEM_ID,
//  		        	    sd.ITEM_NAME,
//  		        	    sd.ITEM_DIMENSION,
//  		        	    sd.DELIVERY_COUNT ITEM_COUNT,
//  		        	    sd.ITEM_UNIT_PRICE,
//  		        	    sd.PAY_AMT,
//  		        	    sh.PROVIDER_ID      branchId,
//  		        	    sh.AUDIT_TIME        restaurantDate,
//  		        	    sh.AUDIT_TIME_ACTUAL operationTime,
//  		        	    'CPCK'               reason,
//  		        	    'OUT'                 type_flag
//  		        	FROM
//  		        	:Com_("D_T1_SHIPPING_HEADER") sh
//  		        	INNER JOIN
//  		        	:Com_("D_T1_SHIPPING_DETAIL") sd
//  		        	ON
//  		        	    sd.FORM_ID= sh.FORM_ID
//      	    		WHERE
//  				    	sh.AUDIT_TIME_ACTUAL >= to_date(:startDate,'yyyy-mm-dd hh24:mi:ss')
//  					AND sh.AUDIT_TIME_ACTUAL <= to_date(:endDate,'yyyy-mm-dd hh24:mi:ss')
//  			        AND sh.PROVIDER_ID = :branchId 
//  				    		)
//  			SELECT
//  			    *
//  			FROM
//  			    a
//  			ORDER BY
//  			    a.item_id,a.operationTime
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_INPUT_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = branchId;
  String __sJT_6 = branchId;
  String __sJT_7 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_8 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_9 = Com_("D_T1_INPUT_HEADER");
  String __sJT_10 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_11 = startDate;
  String __sJT_12 = endDate;
  String __sJT_13 = branchId;
  String __sJT_14 = branchId;
  String __sJT_15 = Com_("D_T1_LOSS_HEADER");
  String __sJT_16 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_17 = startDate;
  String __sJT_18 = endDate;
  String __sJT_19 = branchId;
  String __sJT_20 = branchId;
  String __sJT_21 = branchId;
  String __sJT_22 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_23 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_24 = startDate;
  String __sJT_25 = endDate;
  String __sJT_26 = branchId;
  String __sJT_27 = branchId;
  String __sJT_28 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_29 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_30 = Com_("D_T2_ITEM_PRICE");
  String __sJT_31 = startDate;
  String __sJT_32 = endDate;
  String __sJT_33 = branchId;
  String __sJT_34 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_35 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_36 = startDate;
  String __sJT_37 = endDate;
  String __sJT_38 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutBean_SJProfileKeys.getKey(0), 19);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      __sJT_stmt.setString(32, __sJT_32);
      __sJT_stmt.setString(33, __sJT_33);
      __sJT_stmt.setString(34, __sJT_34);
      __sJT_stmt.setString(35, __sJT_35);
      __sJT_stmt.setString(36, __sJT_36);
      __sJT_stmt.setString(37, __sJT_37);
      __sJT_stmt.setString(38, __sJT_38);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new ItemDetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:2413^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	private List<Map> processDetailIter(ItemDetailIter detailIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("formId", detailIter.form_id());
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("itemCount", detailIter.item_count());
			detail.put("itemUnitPrice", detailIter.item_unit_price());
			detail.put("payAmt", detailIter.pay_amt());
			detail.put("branchId", detailIter.branchId());
			detail.put("reason", detailIter.reason());
			detail.put("typeFlag", detailIter.type_flag());
			detail.put("restaurantDate", SqlDateUtil.getUtilDate(detailIter.restaurantDate()));
			detail.put("operationTime", SqlDateUtil.getUtilDate(detailIter.operationTime()));
			headerLst.add(detail);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class StorageInOutBean_SJProfileKeys 
{
  private static StorageInOutBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StorageInOutBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StorageInOutBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.StorageInOutBean_SJProfile0");
  }
}
