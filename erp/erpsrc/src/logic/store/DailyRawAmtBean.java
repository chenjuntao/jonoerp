/*@lineinfo:filename=DailyRawAmtBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
*
* Copyright (c) 2014
* Tanry Electronic Technology Co., Ltd.
* ChangSha, China
*
* All Rights Reserved.
*
* First created on Sep 29, 2014 by cjt
* Last edited on Sep 29, 2014 by cjt
*
* 说明： 访问餐厅每日原料万元用量表
*
*/

package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.util.DateTimeUtil;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.SysOption;

public class DailyRawAmtBean extends ConnectionPool {
	
//    #sql private iterator MaterialIterator(String ITEM_ID, Double nqty,String PRICE_TYPE, Double ITEM_PRICE); 
    
    private OptionBean optionBean;
    public void SetOptionBean(OptionBean optionBean){
    	this.optionBean = optionBean;
    }

    //计算理论用量
    public int calcTheoryRawAmt(String branchId, java.util.Date settleDate) throws NoPrivilegeException, SQLException, NoConnection {
    	DefaultContext myCtx = getDefaultContext();
//		MaterialIterator materialIterator = null;
		Date sDate = SqlDateUtil.getSqlDate(settleDate);
		if (myCtx != null) {
			//查询当天所有的原料理论扣库数量
			/*@lineinfo:generated-code*//*@lineinfo:51^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T3_DAILY_RAW_AMT")
//  					(BRANCH_ID, BUSINESS_DATE,ITEM_PRICE, PRICE_TYPE, ITEM_ID, AMT_THEORY, AMT_ACTUAL)
//  				(
//  					SELECT
//  						:branchId,
//  						:sDate,
//  					    ip.ITEM_PRICE ,
//  					    ip.PRICE_TYPE,
//  					    trim(t.ITEM_ID) ITEM_ID,
//  					    SUM(t.item_gross_count * d.nqty) AS nqty,
//  					    NULL
//  					FROM
//  						:Com_("D_T_FOOD_BILL") b,
//  					    :Com_("D_T_FOOD_BILLS") d,
//  					    :Com_("D_T2_THERAPY") t,
//  					    :Com_("D_T2_ITEM_PRICE") ip
//  					WHERE
//  						trim(d.CBILL_C) = trim(b.CBILL_C)
//  					AND trim(b.cbranch_c) = :branchId
//  					AND trim(t.THERAPY_ID) = trim(d.cfood_c)
//  					AND b.dbusiness = :sDate
//  					AND trim(t.ITEM_ID) = trim(ip.ITEM_ID)
//  					AND ip.IS_CURRENT = 1
//  					AND ip.PRICE_TYPE = 'BENCHMARK'
//  					GROUP BY
//  					    trim(t.ITEM_ID),
//  					    ip.ITEM_PRICE ,
//  					    ip.PRICE_TYPE
//  				 )
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_RAW_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  String __sJT_4 = Com_("D_T_FOOD_BILL");
  String __sJT_5 = Com_("D_T_FOOD_BILLS");
  String __sJT_6 = Com_("D_T2_THERAPY");
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = branchId;
  Date __sJT_9 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyRawAmtBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:81^4*/
		} else {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return 1;
    }

    /*@lineinfo:generated-code*//*@lineinfo:89^5*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemUseUpIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemUseUpIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    dayDiffNdx = findColumn("dayDiff");
    checkDiffNdx = findColumn("checkDiff");
    itemOutNdx = findColumn("itemOut");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Integer dayDiff() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(dayDiffNdx);
  }
  private int dayDiffNdx;
  public Double checkDiff() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(checkDiffNdx);
  }
  private int checkDiffNdx;
  public Double itemOut() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemOutNdx);
  }
  private int itemOutNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:89^106*/
    //计算实际用量
    public int calcActualRawAmt(String branchId, java.util.Date settleDate) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		ItemUseUpIter itemUseUpIter = null;
		String formType = FormConstant.CHECK_FORM;
		Date sDate = SqlDateUtil.getSqlDate(settleDate);

		/*-------------------------------------------------------------------------------------------
		 * 计算实际用量
		 * 实际用量 = 期初 + ∑入库量 - 期末 = ∑出库量
		 * 出库量包含：理论扣仓和报损，不包含：调拨、配送退货（统配）、采购退货（直配）
		 */
		/*@lineinfo:generated-code*//*@lineinfo:103^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO	:Com_("D_T3_DAILY_RAW_AMT") 
//  				(BRANCH_ID, BUSINESS_DATE, ITEM_ID, AMT_THEORY, AMT_ACTUAL) 
//  			(
//  				SELECT d.ITEM_ID,
//  					   d.BUSINESS_DATE,
//  					   d.ITEM_ID,--//当天盘点的物料id
//  					   b.checkDiff, --//原料出库量
//  				       SUM(d.ITEM_COUNT_OUT) - b.checkDiff --//原料实际用量=原料出库量-盘点差异
//  				FROM 
//  					 (--//查询当天盘点的物料上一次最近的盘点日期
//  					    SELECT a.ITEM_ID,
//  					    	   a.checkDiff,
//  						       MAX(s.BUSINESS_DATE) AS lastDate,
//  						       :sDate - MAX(s.BUSINESS_DATE) AS dayDiff
//  					    FROM 
//  					    (--//查询当天有哪些物料进行过盘点
//  							SELECT 
//  							    cd.ITEM_ID,
//  							    cd.CHECK_COUNT-cd.THEORY_COUNT AS checkDiff
//  							 FROM
//  							 	:Com_("D_T1_CHECK_HEADER") ch,
//  							    :Com_("D_T1_CHECK_DETAIL") cd
//  							 WHERE
//  							    ch.FORM_ID = cd.FORM_ID
//  							 AND 
//  								ch.FORM_TYPE = :formType
//  							 AND
//  								ch.AUDIT_TIME = :sDate
//  							 AND
//  								ch.CHECK_BRANCH_ID = :branchId
//  						) a,
//  					    :Com_("D_T0_STORAGE_IN_OUT") s
//  					    WHERE a.ITEM_ID = s.ITEM_ID
//  					    AND s.REASON = 'PD'
//  					    AND s.BRANCH_ID = :branchId
//  					    AND s.BUSINESS_DATE < :sDate
//  					    GROUP BY a.ITEM_ID, a.checkDiff
//  					) b,
//  					:Com_("D_T0_STORAGE_IN_OUT") d
//  				WHERE d.ITEM_ID = b.ITEM_ID
//  				AND d.BRANCH_ID = :branchId
//  				AND d.BUSINESS_DATE <= :sDate
//  				AND d.BUSINESS_DATE > b.lastDate
//  				--//以下为计算原料用量主要考虑的出库因素：理论扣仓和报损
//  				AND d.REASON = 'LLKU' --//计算原料用量主要考虑销售扣仓(理论扣库)
//  				AND d.REASON = 'YLBS' --//计算原料用量还要考虑原料报损
//  				AND d.REASON = 'CPBS' --//计算原料用量还要考虑出品报损
//  				--//原料用量不需考虑的出库因素包括：调拨、配送退货（统配）、采购退货（直配）
//  				GROUP BY 
//  					d.ITEM_ID,
//  					d.BUSINESS_DATE,
//  					d.ITEM_ID,
//  					b.checkDiff
//  			)
//      	 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_RAW_AMT");
  Date __sJT_2 = sDate;
  String __sJT_3 = Com_("D_T1_CHECK_HEADER");
  String __sJT_4 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_5 = formType;
  Date __sJT_6 = sDate;
  String __sJT_7 = branchId;
  String __sJT_8 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_9 = branchId;
  Date __sJT_10 = sDate;
  String __sJT_11 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_12 = branchId;
  Date __sJT_13 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyRawAmtBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:158^6*/
		
		closeDefaultContext(myCtx);
		return 1;
	}
    
    /**
     * 查询当天的营业日期与之前最近的盘点日期之间所消耗的物料数量
     */
    public List<Map> queryUseup(String branchId, java.util.Date settleDate,DefaultContext myCtx) 
    		throws NoPrivilegeException, SQLException, NoConnection {

		ItemUseUpIter itemUseUpIter = null;
		String formType = FormConstant.CHECK_FORM;
		Date sDate = SqlDateUtil.getSqlDate(settleDate);

		/*@lineinfo:generated-code*//*@lineinfo:174^3*/

//  ************************************************************
//  #sql [myCtx] itemUseUpIter = { WITH a AS (--//查询当天有哪些物料进行过盘点
//  				SELECT 
//  				    cd.ITEM_ID,
//  				    cd.CHECK_COUNT-cd.THEORY_COUNT AS checkDiff
//  				 FROM
//  				 	:Com_("D_T1_CHECK_HEADER") ch,
//  				    :Com_("D_T1_CHECK_DETAIL") cd
//  				 WHERE
//  				    ch.FORM_ID = cd.FORM_ID
//  				 AND 
//  					ch.FORM_TYPE = :formType
//  				 AND
//  					ch.AUDIT_TIME = :sDate
//  				 AND
//  					ch.CHECK_BRANCH_ID = :branchId
//  		     ),
//  			 b AS (--//查询当天盘点的物料上一次最近的盘点日期
//  			    SELECT 
//  			    	a.ITEM_ID,
//  				    MAX(s.BUSINESS_DATE) AS lastDate,
//  				    :sDate - MAX(s.BUSINESS_DATE) AS dayDiff
//  			    FROM a,
//  			    :Com_("D_T3_DAILY_STORAGE") s
//  			    WHERE 
//  			    	a.ITEM_ID = s.ITEM_ID
//  			    AND s.ITEM_COUNT_ACTUAL IS NOT NULL
//  			    AND s.BRANCH_ID = :branchId
//  			    AND s.BUSINESS_DATE < :sDate
//  			    GROUP BY 
//  			    	a.ITEM_ID
//  	        )
//  			SELECT 
//  				b.ITEM_ID,--//当天盘点的物料id
//  			    b.dayDiff, --//当天的营业日期与上一次最近的盘点日期之间相差的天数
//  			    a.checkDiff, --//本次盘点的盘点差异
//  			    SUM(d.ITEM_COUNT_OUT) AS itemOut --//当天与上一次最近的盘点日期之间的原料出库量
//  		    FROM a, 
//  				 b,
//  				 :Com_("D_T0_STORAGE_IN_OUT") d
//  			WHERE 
//  				b.ITEM_ID = a.ITEM_ID
//              AND d.ITEM_ID = a.ITEM_ID
//  			AND d.BRANCH_ID = :branchId
//  			AND d.BUSINESS_DATE <= :sDate
//              AND d.BUSINESS_DATE > b.lastDate
//              GROUP BY 
//              	b.ITEM_ID,
//                  b.dayDiff,
//                  a.checkDiff
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_CHECK_HEADER");
  String __sJT_2 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_3 = formType;
  Date __sJT_4 = sDate;
  String __sJT_5 = branchId;
  Date __sJT_6 = sDate;
  String __sJT_7 = Com_("D_T3_DAILY_STORAGE");
  String __sJT_8 = branchId;
  Date __sJT_9 = sDate;
  String __sJT_10 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_11 = branchId;
  Date __sJT_12 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyRawAmtBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemUseUpIter = new ItemUseUpIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:224^3*/
		
		List<Map> itemLst = new ArrayList<Map>();
		while(itemUseUpIter.next()) {
			String itemId = itemUseUpIter.item_id();
			Integer dayDiff = itemUseUpIter.dayDiff();
			Double itemUseup = itemUseUpIter.itemOut() - itemUseUpIter.checkDiff();
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", itemId);
			item.put("dayDiff", dayDiff);
			item.put("itemUseup", itemUseup);
			itemLst.add(item);
		}
		itemUseUpIter.close();
		
		return itemLst;
	}

    /*@lineinfo:generated-code*//*@lineinfo:242^5*/

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
    business_dateNdx = findColumn("business_date");
    amt_theoryNdx = findColumn("amt_theory");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Date business_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(business_dateNdx);
  }
  private int business_dateNdx;
  public Double amt_theory() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt_theoryNdx);
  }
  private int amt_theoryNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:242^89*/
    /**
     * 查询当天的营业日期与之前最近的盘点日期之间每天的理论用量
     */
    public List<Map> queryTheoryAmt(String branchId, java.util.Date startDate, java.util.Date endDate,DefaultContext myCtx) 
    		throws NoPrivilegeException, SQLException, NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:252^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT 
//              	ITEM_ID,
//              	BUSINESS_DATE,
//              	AMT_THEORY
//              FROM 
//              :Com_("D_T3_DAILY_RAW_AMT")
//              WHERE
//       	    	BRANCH_ID = :branchId
//              AND BUSINESS_DATE <= :eDate
//              AND BUSINESS_DATE > :sDate
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_RAW_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = eDate;
  Date __sJT_4 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyRawAmtBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:263^3*/
		
		List<Map> itemLst = new ArrayList<Map>();
		while(itemIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", itemIter.item_id());
			item.put("businessDate", SqlDateUtil.getUtilDate(itemIter.business_date()));
			item.put("amtTheory", itemIter.amt_theory());
			itemLst.add(item);
		}
		itemIter.close();
		
		return itemLst;
	}

	//查询原材料的万元用量
	public Double queryRawAmtTTCNY(java.util.Date startDate, java.util.Date endDate, String branchId, String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		//查询万元用量类型(理论值:THEORY/实际值:ACTUAL)
		String amtTTCNYType = optionBean.getOption(SysOption.AMT_TTCNY);
				
		DefaultContext myCtx = getDefaultContext();
		Double rawAmt = 0.0;
		if(amtTTCNYType == "ACTUAL"){
			/*@lineinfo:generated-code*//*@lineinfo:289^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT SUM(CASE WHEN amt_actual IS NULL then amt_theory ELSE amt_actual END) 
//  				FROM :Com_("D_T3_DAILY_RAW_AMT") 
//  				WHERE BRANCH_ID = :branchId
//  			    AND (BUSINESS_DATE >= :sDate or :sDate is null)
//  				AND (BUSINESS_DATE <= :eDate or :eDate is null)
//  				AND ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_RAW_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  String __sJT_7 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyRawAmtBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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
    rawAmt = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:296^4*/
		}else{ //默认为理论值
			/*@lineinfo:generated-code*//*@lineinfo:298^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT SUM(AMT_THEORY) 
//  				FROM :Com_("D_T3_DAILY_RAW_AMT") 
//  				WHERE BRANCH_ID = :branchId
//  			    AND (BUSINESS_DATE >= :sDate or :sDate is null)
//  				AND (BUSINESS_DATE <= :eDate or :eDate is null)
//  				AND ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_RAW_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  String __sJT_7 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyRawAmtBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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
    rawAmt = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:305^4*/
		}
		Double businessAmt = 0.0;
		/*@lineinfo:generated-code*//*@lineinfo:308^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT SUM(BUSINESS_AMT) 
//  			FROM :Com_("D_T3_DAILY_BUSINESS_AMT") 
//  			WHERE BRANCH_ID = :branchId
//  		    AND (BUSINESS_DATE >= :sDate or :sDate is null)
//  			AND (BUSINESS_DATE <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_BUSINESS_AMT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyRawAmtBean_SJProfileKeys.getKey(0), 6);
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
    businessAmt = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:314^3*/
		closeDefaultContext(myCtx);
		if(businessAmt == 0.0)
		{
			return 0.0;
		}else{
		    return rawAmt/businessAmt;
		}
	}
}/*@lineinfo:generated-code*/class DailyRawAmtBean_SJProfileKeys 
{
  private static DailyRawAmtBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DailyRawAmtBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DailyRawAmtBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.DailyRawAmtBean_SJProfile0");
  }
}
