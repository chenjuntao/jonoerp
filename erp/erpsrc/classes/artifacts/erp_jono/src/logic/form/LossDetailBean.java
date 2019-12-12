/*@lineinfo:filename=LossDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 17, 2014 by cjt
 * Last edited on Sep 17, 2014 by cjt
 * 
 * 说明： 餐厅报损单表明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.LossDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class LossDetailBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(LossDetailBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

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
    item_id2Ndx = findColumn("item_id2");
    item_name2Ndx = findColumn("item_name2");
    item_dimension2Ndx = findColumn("item_dimension2");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_countNdx = findColumn("item_count");
    item_count2Ndx = findColumn("item_count2");
    unit_priceNdx = findColumn("unit_price");
    pay_amtNdx = findColumn("pay_amt");
    storage_countNdx = findColumn("storage_count");
    expired_timeNdx = findColumn("expired_time");
    reasonNdx = findColumn("reason");
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
  public String item_id2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_id2Ndx);
  }
  private int item_id2Ndx;
  public String item_name2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_name2Ndx);
  }
  private int item_name2Ndx;
  public String item_dimension2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimension2Ndx);
  }
  private int item_dimension2Ndx;
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double item_count2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count2Ndx);
  }
  private int item_count2Ndx;
  public Double unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_priceNdx);
  }
  private int unit_priceNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double storage_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(storage_countNdx);
  }
  private int storage_countNdx;
  public Date expired_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(expired_timeNdx);
  }
  private int expired_timeNdx;
  public String reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(reasonNdx);
  }
  private int reasonNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:54^18*/
	
	/*@lineinfo:generated-code*//*@lineinfo:56^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class StorageIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public StorageIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_countNdx = findColumn("item_count");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:59^3*/
	
	/**
	 * query items storage
	 */
	public Map<String,Double> queryStorage(String formId,String storageId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		StorageIter storageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:69^3*/

//  ************************************************************
//  #sql [myCtx] storageIter = { WITH
//  			    items AS
//  			    (
//  			        SELECT DISTINCT
//  			            ITEM_ID
//  			        FROM
//  			        	:Com_("D_T1_LOSS_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T1_LOSS_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        WHERE
//  			            d.FORM_ID = :formId
//  			        AND (
//  			                d.REASON= DECODE(h.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  h.LOSS_TYPE = 'RAWLOSS')
//  			    )
//  			SELECT
//  			    i.ITEM_ID,
//  			    NVL(s.ITEM_COUNT,0)ITEM_COUNT
//  			FROM
//  			    items i
//  			LEFT JOIN
//  			 :Com_("D_T2_STORAGE") s
//  			ON
//  			    i.ITEM_ID = s.ITEM_ID
//  			AND s.STORAGE_ID = :storageId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_3 = formId;
  String __sJT_4 = Com_("D_T2_STORAGE");
  String __sJT_5 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      storageIter = new StorageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:96^33*/
		
		Map<String,Double> resultMap = new HashMap<String,Double>();
		while(storageIter.next()) {
			resultMap.put(storageIter.item_id(),storageIter.item_count());
		}
		
		storageIter.close();
		closeDefaultContext(myCtx);
		return resultMap;
	}

	public List<LossDetail> queryRawDetail(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		return queryRawDetail(formId,null);
	}
	
	public List<LossDetail> queryDishDetail(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		return queryDishDetail(formId,null);
	}
	
	public List<LossDetail> queryRawDetail (String formId,String hasSum)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:122^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    forms AS
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            d.ITEM_CATEGORY,
//  			            SUM(d.ITEM_COUNT)ITEM_COUNT,
//  			            d.UNIT_PRICE,
//  			            SUM(d.PAY_AMT) PAY_AMT,
//  			            d.STORAGE_COUNT,
//  			            d.EXPIRED_TIME,
//  			            d.REASON,
//  			            d.ITEM_ID2,
//  			            d.ITEM_NAME2,
//  			            d.ITEM_DIMENSION2,
//  			            d.ITEM_COUNT2
//  			        FROM
//  			            :Com_("D_T1_LOSS_DETAIL") d
//  			        INNER JOIN
//  			            :Com_("D_T1_LOSS_HEADER") h
//  			        ON
//  			            d.FORM_ID = h.FORM_ID
//  			        WHERE
//  			            d.FORM_ID = :formId
//  			        AND (
//  			                d.REASON= DECODE(h.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  h.LOSS_TYPE = 'RAWLOSS')
//  			        GROUP BY
//  			        	GROUPING SETS( ( d.FORM_ID, d.ITEM_ID,d.ITEM_ID2, d.ITEM_NAME2, d.ITEM_NAME,
//  			                d.ITEM_DIMENSION, d.ITEM_SPECIFICATION, d.ITEM_CATEGORY, d.ITEM_COUNT, d.UNIT_PRICE,
//  			                d.PAY_AMT, d.STORAGE_COUNT, d.EXPIRED_TIME, d.REASON, d.ITEM_DIMENSION2, d.ITEM_COUNT2)
//  			                ,NULL)
//  			    )
//  			    ,
//  			    temp AS
//  			    (
//  			        SELECT
//  			            s.*
//  			        FROM
//  			            forms s
//  			        ORDER BY
//  			            ITEM_ID2,
//  			            ITEM_ID
//  			    )
//  			SELECT
//  			    DECODE(t.FORM_ID,NULL,'合计',TO_CHAR(rownum)) rownumber,
//  			    t.FORM_ID,
//  			    t.ITEM_ID,
//  			    t.ITEM_NAME,
//  			    t.ITEM_DIMENSION,
//  			    t.ITEM_SPECIFICATION,
//  			    t.ITEM_CATEGORY,
//  			    t.ITEM_COUNT,
//  			    t.UNIT_PRICE,
//  			    t.PAY_AMT,
//  			    t.STORAGE_COUNT,
//  			    t.EXPIRED_TIME,
//  			    t.REASON,
//  			    t.ITEM_ID2,
//  			    t.ITEM_NAME2,
//  			    t.ITEM_DIMENSION2,
//  			    t.ITEM_COUNT2
//  			FROM
//  			    temp t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = Com_("D_T1_LOSS_HEADER");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:190^14*/
		List<LossDetail> detailLst = processIter(detailIter,hasSum);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:197^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class LossIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public LossIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    rownumberNdx = findColumn("rownumber");
    item_IdNdx = findColumn("item_Id");
    item_NameNdx = findColumn("item_Name");
    item_dimensionNdx = findColumn("item_dimension");
    item_countNdx = findColumn("item_count");
    unit_priceNdx = findColumn("unit_price");
    pay_amtNdx = findColumn("pay_amt");
    reasonNdx = findColumn("reason");
  }
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
  public String item_Id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_IdNdx);
  }
  private int item_IdNdx;
  public String item_Name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_NameNdx);
  }
  private int item_NameNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_priceNdx);
  }
  private int unit_priceNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public String reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(reasonNdx);
  }
  private int reasonNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:198^139*/
	public List<LossDetail> queryLossItem(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		LossIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:203^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT 
//  				DECODE(D.FORM_ID,NULL,'合计',D.FORM_ID) rownumber,
//  				D.ITEM_ID item_Id,
//  				D.ITEM_NAME item_Name,
//  				D.ITEM_DIMENSION item_dimension,
//  				D.ITEM_COUNT item_count,
//  				D.UNIT_PRICE unit_price,
//  				SUM(D.PAY_AMT) pay_amt,
//  				D.REASON reason
//  			FROM  
//  			:Com_("D_T1_LOSS_HEADER") H INNER 
//  		        JOIN  
//  			:Com_("D_T1_LOSS_DETAIL") D
//  			ON 
//  				H.FORM_ID = D.FORM_ID    
//  			WHERE 
//  				H.FORM_ID = :formId
//  				AND NVL(D.REASON,' ') !='DISH2RAW'
//  			GROUP BY
//  				GROUPING SETS ((D.FORM_ID,D.ITEM_ID,D.ITEM_NAME,D.ITEM_DIMENSION,D.ITEM_COUNT,D.UNIT_PRICE,D.PAY_AMT,D.REASON),NULL)
//  			ORDER BY
//  				D.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new LossIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:226^3*/
		List<LossDetail> detailLst = new ArrayList<LossDetail>();
		int cnt = 0;
		while(detailIter.next()) {
			cnt++;
			LossDetail detail = new LossDetail();
			detail.setItem_id(detailIter.item_Id());
			detail.setItem_name(detailIter.item_Name());
			detail.setItem_dimension(detailIter.item_dimension());
			detail.setItem_count(detailIter.item_count());
			detail.setUnit_price(detailIter.unit_price());
			detail.setPay_amt(detailIter.pay_amt());
			detail.setRownumber(detailIter.rownumber());
			detail.setReason(detailIter.reason());
			if (!detail.getRownumber().equals("合计")) detail.setRownumber(String.valueOf(cnt));
			detailLst.add(detail);
		}
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<LossDetail> queryDishDetail(String formId,String hasSum)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:252^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { WITH
//  			    forms AS
//  			    (
//  			        SELECT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION,
//  			            d.ITEM_CATEGORY,
//  			            SUM(d.ITEM_COUNT)ITEM_COUNT,
//  			            d.UNIT_PRICE,
//  			            SUM(d.PAY_AMT) PAY_AMT,
//  			            d.STORAGE_COUNT,
//  			            d.EXPIRED_TIME,
//  			            d.REASON,
//  			            d.ITEM_ID2,
//  			            d.ITEM_NAME2,
//  			            d.ITEM_DIMENSION2,
//  			            d.ITEM_COUNT2
//  			        FROM
//  			            :Com_("D_T1_LOSS_DETAIL") d
//  			        INNER JOIN
//  			            :Com_("D_T1_LOSS_HEADER") h
//  			        ON
//  			            d.FORM_ID = h.FORM_ID
//  			        WHERE
//  			            d.FORM_ID = :formId
//  			        AND (
//  			                d.REASON= DECODE(h.LOSS_TYPE,'DISHLOSS','DISH2RAW')
//  			            OR  h.LOSS_TYPE = 'RAWLOSS')
//  			        GROUP BY
//  			        	GROUPING SETS( ( d.FORM_ID, d.ITEM_ID,d.ITEM_ID2, d.ITEM_NAME2, d.ITEM_NAME,
//  			                d.ITEM_DIMENSION, d.ITEM_SPECIFICATION, d.ITEM_CATEGORY, d.ITEM_COUNT, d.UNIT_PRICE,
//  			                d.PAY_AMT, d.STORAGE_COUNT, d.EXPIRED_TIME, d.REASON, d.ITEM_DIMENSION2, d.ITEM_COUNT2)
//  			                ,( d.FORM_ID, d.ITEM_ID2),NULL)
//  			    )
//  			    ,
//  			    temp AS
//  			    (
//  			        SELECT
//  			            s.*
//  			        FROM
//  			            forms s
//  			        ORDER BY
//  			            ITEM_ID2,
//  			            ITEM_ID
//  			    )
//  			SELECT
//  			    DECODE(t.FORM_ID,NULL,'合计',TO_CHAR(rownum)) rownumber,
//  			    t.FORM_ID,
//  			    DECODE(t.ITEM_ID2,NULL,NULL,DECODE(t.ITEM_ID,NULL,'小计',t.ITEM_ID2)) ITEM_ID2,
//  			    t.ITEM_NAME,
//  			    t.ITEM_DIMENSION,
//  			    t.ITEM_SPECIFICATION,
//  			    t.ITEM_CATEGORY,
//  			    t.ITEM_COUNT,
//  			    t.UNIT_PRICE,
//  			    t.PAY_AMT,
//  			    t.STORAGE_COUNT,
//  			    t.EXPIRED_TIME,
//  			    t.REASON,
//  			    t.ITEM_ID,
//  			    t.ITEM_NAME2,
//  			    t.ITEM_DIMENSION2,
//  			    t.ITEM_COUNT2
//  			FROM
//  			    temp t };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = Com_("D_T1_LOSS_HEADER");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:320^14*/
		List<LossDetail> detailLst = processIter(detailIter,hasSum);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<LossDetail> queryDish(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:331^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  				ROWNUM rownumber,
//  			    d.*
//  			    FROM
//  			        :Com_("D_T1_LOSS_DETAIL") d
//  			    INNER JOIN
//  			        :Com_("D_T1_LOSS_HEADER") h
//  			    ON
//  			        d.FORM_ID = h.FORM_ID
//  			    WHERE
//  			    	NVL(d.REASON,' ') != 'DISH2RAW'
//  			    AND d.FORM_ID = :formId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = Com_("D_T1_LOSS_HEADER");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:343^31*/
		List<LossDetail> detailLst = processIter(detailIter,"Y");
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<LossDetail> processIter(DetailIter detailIter,String hasSum) 
			throws SQLException {
		List<LossDetail> detailLst = new ArrayList<LossDetail>();
		while(detailIter.next()) {
			LossDetail detail = new LossDetail();
			detail.setForm_id(detailIter.form_id());
			detail.setItem_id(detailIter.item_id());
			detail.setItem_name(detailIter.item_name());
			detail.setItem_category(detailIter.item_category());
			detail.setItem_dimension(detailIter.item_dimension());
			detail.setItem_id2(detailIter.item_id2());
			detail.setItem_name2(detailIter.item_name2());
			detail.setItem_count2(detailIter.item_count2());
			detail.setItem_dimension2(detailIter.item_dimension2());
			detail.setItem_specification(detailIter.item_specification());
			detail.setItem_count(detailIter.item_count());
			detail.setUnit_price(detailIter.unit_price());
			detail.setPay_amt(detailIter.pay_amt());
			detail.setStorage_count(detailIter.storage_count());
			detail.setExpired_time(SqlDateUtil.getUtilDate(detailIter.expired_time()));
			detail.setReason(detailIter.reason());
			detail.setRownumber(detailIter.rownumber());
			detailLst.add(detail);
		}
		
		if(hasSum == null){
			if(detailLst.size()>0){
				detailLst.remove(detailLst.size()-1);
			}
		}
		return detailLst;
	}
	
	/**
	 * 保存明细
	 */
	public int saveEntity(LossDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = detail.getForm_id();
		String itemId = detail.getItem_id();
		String itemName = detail.getItem_name();
		String itemCategory = detail.getItem_category();
		String itemDimension = detail.getItem_dimension();
		String itemId2 = detail.getItem_id2();
		String itemName2 = detail.getItem_name2();
		Double itemCount2 = detail.getItem_count2();
		String itemDimension2 = detail.getItem_dimension2();
		String itemSpecification = detail.getItem_specification();
		Double itemCount = detail.getItem_count();
		Double unitPrice = detail.getUnit_price();
 	 	Double payAmt = detail.getPay_amt();
 	 	Double storageCount = detail.getStorage_count();
 	 	Date expiredTime = SqlDateUtil.getSqlDate(detail.getExpired_time());
 	 	String reason = detail.getReason();
	
		/*@lineinfo:generated-code*//*@lineinfo:406^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_LOSS_DETAIL") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_CATEGORY, ITEM_DIMENSION,ITEM_ID2, ITEM_NAME2, ITEM_COUNT2, ITEM_DIMENSION2, ITEM_SPECIFICATION,
//  						ITEM_COUNT, UNIT_PRICE, PAY_AMT, STORAGE_COUNT, EXPIRED_TIME, REASON) 
//  						VALUES (:formId,
//  								:itemId, 
//  								:itemName, 
//  								:itemCategory, 
//  								:itemDimension, 
//  								:itemId2, 
//  								:itemName2, 
//  								:itemCount2, 
//  								:itemDimension2, 
//  								:itemSpecification, 
//  								:itemCount, 
//  								:unitPrice, 
//  								:payAmt, 
//  								:storageCount, 
//  								:expiredTime, 
//  								:reason)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemCategory;
  String __sJT_6 = itemDimension;
  String __sJT_7 = itemId2;
  String __sJT_8 = itemName2;
  Double __sJT_9 = itemCount2;
  String __sJT_10 = itemDimension2;
  String __sJT_11 = itemSpecification;
  Double __sJT_12 = itemCount;
  Double __sJT_13 = unitPrice;
  Double __sJT_14 = payAmt;
  Double __sJT_15 = storageCount;
  Date __sJT_16 = expiredTime;
  String __sJT_17 = reason;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 5);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setDoubleWrapper(14, __sJT_14);
      __sJT_stmt.setDoubleWrapper(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
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

/*@lineinfo:user-code*//*@lineinfo:426^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateEntity(LossDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String formId = detail.getForm_id();
		String itemId = detail.getItem_id();
		Double itemCount = detail.getItem_count();
		Double itemCount2 = detail.getItem_count2();
		Double payAmt = detail.getPay_amt();
		String reason =detail.getReason();
	
		/*@lineinfo:generated-code*//*@lineinfo:442^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_LOSS_DETAIL")
//  			SET
//  			    ITEM_COUNT = :itemCount,
//  	    		ITEM_COUNT2 = :itemCount2,
//  			    PAY_AMT = :payAmt,
//  			    REASON = :reason
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
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  Double __sJT_2 = itemCount;
  Double __sJT_3 = itemCount2;
  Double __sJT_4 = payAmt;
  String __sJT_5 = reason;
  String __sJT_6 = formId;
  String __sJT_7 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:453^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:463^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_LOSS_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:469^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteDish2RawByForm(String formId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:479^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T1_LOSS_DETAIL")
//  				WHERE
//  					FORM_ID = :formId
//  				AND
//  					REASON = 'DISH2RAW'
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDetailBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:487^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
}/*@lineinfo:generated-code*/class LossDetailBean_SJProfileKeys 
{
  private static LossDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new LossDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private LossDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.LossDetailBean_SJProfile0");
  }
}
