/*@lineinfo:filename=RestaurantCostBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class RestaurantCostBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RestaurantCostBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:27^2*/

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
    branchIdNdx = findColumn("branchId");
    branchNameNdx = findColumn("branchName");
    produceAmtNdx = findColumn("produceAmt");
    baseAmtNdx = findColumn("baseAmt");
    businessAmtNdx = findColumn("businessAmt");
    beforeCostPerNdx = findColumn("beforeCostPer");
    afterCostPerNdx = findColumn("afterCostPer");
    staffCostNdx = findColumn("staffCost");
    presentCostNdx = findColumn("presentCost");
    staffCostPerNdx = findColumn("staffCostPer");
    presentCostPerNdx = findColumn("presentCostPer");
    dishAmtNdx = findColumn("dishAmt");
    rawAmtNdx = findColumn("rawAmt");
    dishPerNdx = findColumn("dishPer");
    rawPerNdx = findColumn("rawPer");
    beginAmtNdx = findColumn("beginAmt");
    inAmtNdx = findColumn("inAmt");
    resultAmtNdx = findColumn("resultAmt");
    actualCostNdx = findColumn("actualCost");
    actualCostPerNdx = findColumn("actualCostPer");
    lossGainNdx = findColumn("lossGain");
    lossGainPerNdx = findColumn("lossGainPer");
    totalPerNdx = findColumn("totalPer");
  }
  public String branchId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchIdNdx);
  }
  private int branchIdNdx;
  public String branchName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branchNameNdx);
  }
  private int branchNameNdx;
  public Double produceAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(produceAmtNdx);
  }
  private int produceAmtNdx;
  public Double baseAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(baseAmtNdx);
  }
  private int baseAmtNdx;
  public Double businessAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(businessAmtNdx);
  }
  private int businessAmtNdx;
  public Double beforeCostPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(beforeCostPerNdx);
  }
  private int beforeCostPerNdx;
  public Double afterCostPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(afterCostPerNdx);
  }
  private int afterCostPerNdx;
  public Double staffCost() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(staffCostNdx);
  }
  private int staffCostNdx;
  public Double presentCost() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(presentCostNdx);
  }
  private int presentCostNdx;
  public Double staffCostPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(staffCostPerNdx);
  }
  private int staffCostPerNdx;
  public Double presentCostPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(presentCostPerNdx);
  }
  private int presentCostPerNdx;
  public Double dishAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(dishAmtNdx);
  }
  private int dishAmtNdx;
  public Double rawAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(rawAmtNdx);
  }
  private int rawAmtNdx;
  public Double dishPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(dishPerNdx);
  }
  private int dishPerNdx;
  public Double rawPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(rawPerNdx);
  }
  private int rawPerNdx;
  public Double beginAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(beginAmtNdx);
  }
  private int beginAmtNdx;
  public Double inAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inAmtNdx);
  }
  private int inAmtNdx;
  public Double resultAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(resultAmtNdx);
  }
  private int resultAmtNdx;
  public Double actualCost() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actualCostNdx);
  }
  private int actualCostNdx;
  public Double actualCostPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actualCostPerNdx);
  }
  private int actualCostPerNdx;
  public Double lossGain() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(lossGainNdx);
  }
  private int lossGainNdx;
  public Double lossGainPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(lossGainPerNdx);
  }
  private int lossGainPerNdx;
  public Double totalPer() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(totalPerNdx);
  }
  private int totalPerNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:51^4*/
	
	public List<Map> query(java.util.Date startDate, java.util.Date endDate,java.util.Date startPreDate)  
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		Date sPreDate = SqlDateUtil.getSqlDate(startPreDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:62^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    p AS
//  			    (
//  			        SELECT
//  			            p.ITEM_ID,
//  			            p.ITEM_PRICE
//  			        FROM
//  			        	:Com_("D_T2_ITEM_PRICE") p
//  			        WHERE
//  			            p.IS_CURRENT = 1
//  			        AND p.PRICE_TYPE = 'BENCHMARK'
//  			    )
//  			    ,
//  			    tp AS
//  			    (
//  			        SELECT
//  			            trim(t.THERAPY_ID)                                      ITEM_ID,
//  			            NVL(ROUND( SUM(t.ITEM_GROSS_COUNT * p.item_price),3),0) unitPrice
//  			        FROM
//  			        	:Com_("D_T2_THERAPY") t
//  			        LEFT JOIN
//  			            p
//  			        ON
//  			            t.ITEM_ID = p.ITEM_ID
//  			        GROUP BY
//  			            trim(t.THERAPY_ID)
//  			    )
//  			    ,
//  			    a AS
//  			    (
//  			        SELECT
//  			            b.CBILL_C,
//  			            trim(bs.cFood_c)cFood_c,
//  			            bs.cLitCls_C,
//  			            b.nFoodAmt,
//  			            b.nPresentAmt ,
//  			            b.nOughtAmt ,
//  			            b.CBRANCH_C,
//  			            b.CBRANCH_N,
//  			            SUM(bs.NAMT) NAMT,
//  			            bs.nQty,
//  			            bs.ERETSENDFLAG
//  			        FROM
//  			            :Com_("d_t_food_Bill") b
//  			        INNER JOIN
//  			        	:Com_("D_T_FOOD_BILLS") bs 
//  			        ON
//  			            b.CBILL_C = bs.cBill_c
//  			        WHERE
//  			            1=1
//  			        AND b.DBUSINESS >= :sDate
//  			        AND b.DBUSINESS <= :eDate
//  			        GROUP BY
//  			            GROUPING SETS(( b.CBILL_C, bs.CFOODBILL,trim(bs.cFood_c), bs.cLitCls_C,
//  			            b.CBRANCH_C, b.nFoodAmt, b.nPresentAmt, b.nOughtAmt,bs.NAMT, bs.nQty,
//  			            bs.ERETSENDFLAG), ( b.CBILL_C, b.CBRANCH_C, b.CBRANCH_N, b.nFoodAmt, b.nPresentAmt ,
//  			            b.nOughtAmt ))
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            CBRANCH_C,
//  			            SUM(
//  			                CASE
//  			                    WHEN cFood_c IS NULL
//  			                    THEN nFoodAmt + NVL(nPresentAmt, 0)
//  			                    ELSE 0
//  			                END) nFoodAmt,
//  			            SUM(
//  			                CASE
//  			                    WHEN cFood_c IS NULL
//  			                    THEN nOughtAmt
//  			                    ELSE 0
//  			                END) nOughtAmt,
//  			            SUM(
//  			                CASE
//  			                    WHEN cLitCls_C IN('3033',
//  			                                      '3036')
//  			                    THEN NAMT
//  			                    ELSE 0
//  			                END) NAMT,
//  			            SUM(
//  			                CASE
//  			                    WHEN cLitCls_C ='3031'
//  			                    THEN nQty*tp.unitPrice
//  			                    ELSE 0
//  			                END) staffCost,
//  			            SUM(
//  			                CASE
//  			                    WHEN ERETSENDFLAG = '赠送'
//  			                    THEN nQty*tp.unitPrice
//  			                    ELSE 0
//  			                END) presentCost,
//  		                SUM(
//  	                        CASE
//  	                            WHEN (ERETSENDFLAG = '正常'
//  	                                OR  ERETSENDFLAG = '退品')
//  	                            THEN nQty*tp.unitPrice
//  	                            ELSE 0
//  	                        END)                baseAmt
//  			        FROM
//  			            a
//  			        LEFT JOIN
//  			            tp
//  			        ON
//  			            a.cFood_c = tp.ITEM_ID
//              		LEFT JOIN
//              			:Com_("D_T2_ITEM_META") m 
//  	            	ON
//  	            	    a.cFood_c = m.ITEM_ID
//  			        GROUP BY
//  			            CBRANCH_C
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT
//  			            b.cBranch_C,
//  			            NVL(SUM(
//  			                CASE
//  			                    WHEN p.CPAY_C = '03'
//  			                    THEN p.nPayAmt
//  			                END),0) idAmt,
//  			            NVL(SUM(
//  			                CASE
//  			                    WHEN p.CPAY_C = '19'
//  			                    THEN p.nPayAmt
//  			                END),0) freeAmt,
//  			            NVL(SUM(
//  			                CASE
//  			                    WHEN p.CPAY_C = '08'
//  			                    THEN p.nPayAmt
//  			                END),0) weixin1,
//  			            NVL(SUM(
//  			                CASE
//  			                    WHEN p.CPAY_C = '09'
//  			                    THEN p.nPayAmt
//  			                END),0) weixin2,
//  			            NVL(SUM(
//  			                CASE
//  			                    WHEN p.CPAY_C = '23'
//  			                    THEN p.nPayAmt
//  			                END),0) zsyh
//  			        FROM
//  			        	:Com_("d_t_food_Bill") b
//  			        LEFT JOIN
//  			        	:Com_("d_t_bill_pay") p 
//  			        ON
//  			            b.CBILL_C = p.CBILL_C
//  			        WHERE
//  			            b.DBUSINESS >= :sDate
//  			        AND b.DBUSINESS <= :eDate
//  			        GROUP BY
//  			            b.cBranch_C
//  			    )
//  			    ,
//  			    f AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            (
//  			                SELECT
//  			                    t.*,
//  			                    row_number() over(partition BY CHECK_BRANCH_ID ORDER BY AUDIT_TIME DESC ) rn
//  			                FROM
//  			                    (
//  			                        SELECT
//  			                            h.CHECK_BRANCH_ID,
//  			                            h.FORM_ID,
//  			                            h.AUDIT_TIME
//  			                        FROM
//  			                        	:Com_("D_T1_CHECK_HEADER") h  
//  			                        WHERE
//  			                            1=1
//  			                        AND h.FORM_TYPE = 'form'
//  			                        AND h.AUDIT_TIME <= :sPreDate ) t )
//  			        WHERE
//  			            rn=1
//  			    )
//  			    ,
//  			    g AS
//  			    (
//  			        SELECT
//  			            f.CHECK_BRANCH_ID ,
//  			            SUM(d.CHECK_COUNT*p.ITEM_PRICE) beginAmt
//  			        FROM
//  			            f
//  			        LEFT JOIN
//  			        	:Com_("D_T1_CHECK_DETAIL") d 
//  			        ON
//  			            f.form_id = d.FORM_ID
//  			        LEFT JOIN
//  			            p
//  			        ON
//  			            d.ITEM_ID = p.item_id
//  			        GROUP BY
//  			            f.CHECK_BRANCH_ID
//  			    )
//  			    ,
//  			    h AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            (
//  			                SELECT
//  			                    t.*,
//  			                    row_number() over(partition BY CHECK_BRANCH_ID ORDER BY AUDIT_TIME DESC ) rn
//  			                FROM
//  			                    (
//  			                        SELECT
//  			                            h.CHECK_BRANCH_ID,
//  			                            h.FORM_ID,
//  			                            h.AUDIT_TIME
//  			                        FROM
//  			                        	:Com_("D_T1_CHECK_HEADER") h 
//  			                        WHERE
//  			                            1=1
//  			                        AND h.FORM_TYPE = 'form'
//  			                        AND h.AUDIT_TIME <= :eDate ) t )
//  			        WHERE
//  			            rn=1
//  			    )
//  			    ,
//  			    i AS
//  			    (
//  			        SELECT
//  			            h.CHECK_BRANCH_ID,
//  			            SUM(d.CHECK_COUNT*p.ITEM_PRICE) resultAmt
//  			        FROM
//  			            h
//  			        LEFT JOIN
//  			        	:Com_("D_T1_CHECK_DETAIL") d 
//  			        ON
//  			            h.form_id = d.FORM_ID
//  			        LEFT JOIN
//  			            p
//  			        ON
//  			            d.ITEM_ID = p.item_id
//  			        GROUP BY
//  			            h.CHECK_BRANCH_ID
//  			    )
//  			    ,
//  			    j AS
//  			    (
//  			        SELECT
//  			            io.BRANCH_ID,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN('CGRK',
//  			                                      'PSRK')
//  			                    THEN p.ITEM_PRICE*io.ITEM_COUNT_IN
//  			                    ELSE 0
//  			                END) amt1,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ( 'DB',
//  			                                       'PSFSH')
//  			                    THEN p.ITEM_PRICE*io.ITEM_COUNT_IN-p.ITEM_PRICE*io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END)amt2,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN('PSTH',
//  			                                      'CGTH')
//  			                    THEN -p.ITEM_PRICE*io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END) amt3,
//  			          
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ( 'YLBS')
//  			                    THEN p.ITEM_PRICE*io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END)rawAmt,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ( 'CPBS')
//  			                    THEN p.ITEM_PRICE*io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END)dishAmt
//  			        FROM
//  			            :Com_("D_T0_STORAGE_IN_OUT") io
//  			        LEFT JOIN
//  			            p
//  			        ON
//  			            io.item_id = p.item_id
//  			        WHERE
//  			            io.BUSINESS_DATE >= :sDate
//  			        AND io.BUSINESS_DATE <= :eDate
//  			        GROUP BY
//  			            io.BRANCH_ID)
//  			    ,
//  			    d AS
//  			    (
//  			        SELECT
//  				        b.CBRANCH_C                                         branchId,
//  			            '['||b.CBRANCH_C||']'||b2.BRANCH_NAME                  branchName,
//  			            nFoodAmt- NAMT                                          produceAmt,
//  			            nOughtAmt- NAMT-idAmt*0.15-freeAmt-weixin1-weixin2-zsyh businessAmt,
//  			            staffCost,
//  			            presentCost,
//  			            baseAmt,
//  			            dishAmt,
//  			            rawAmt,
//  			            nvl(beginAmt, 0) as beginAmt,
//  			            amt1 + amt2+amt3 inAmt ,
//  			            resultAmt
//  			        FROM
//  			            b
//  		            LEFT JOIN
//  		            	:Com_("D_T2_BRANCH") b2
//  			        ON
//  			            b.CBRANCH_C = b2.BRANCH_ID
//  			        LEFT JOIN
//  			            c
//  			        ON
//  			            b.CBRANCH_C = c.CBRANCH_C
//  			        LEFT JOIN
//  			            g
//  			        ON
//  			            b.CBRANCH_C = g.CHECK_BRANCH_ID
//  			        LEFT JOIN
//  			            i
//  			        ON
//  			            b.CBRANCH_C = i.CHECK_BRANCH_ID
//  			        LEFT JOIN
//  			            j
//  			        ON
//  			            b.CBRANCH_C = j.BRANCH_ID
//  			    )
//  			    ,
//  			    k AS
//  			    (
//  			        SELECT
//  			        	branchId,
//  			        	branchName,
//  			            produceAmt,
//  			            businessAmt,
//  			            beginAmt,
//  			            inAmt,
//  			            resultAmt,
//  			            beginAmt+inAmt-resultAmt                              actualCost,
//  			            ROUND((beginAmt+inAmt-resultAmt) /BUSINESSAMT *100,2) actualCostPer,
//  			            baseAmt,
//  			            ROUND ( BASEAMT/PRODUCEAMT *100,2 )  beforeCostPer,
//  			            ROUND ( BASEAMT/BUSINESSAMT *100,2 ) afterCostPer,
//  			            staffCost,
//  			            ROUND ( staffCost/BUSINESSAMT *100,2 ) staffCostPer,
//  			            presentCost,
//  			            ROUND ( presentCost/BUSINESSAMT *100,2 ) presentCostPer,
//  			            dishAmt,
//  			            ROUND ( dishAmt/BUSINESSAMT *100,2 ) dishPer,
//  			            rawAmt,
//  			            ROUND ( rawAmt/BUSINESSAMT *100,2 ) rawPer,
//  			            beginAmt+inAmt-resultAmt-BASEAMT -staffCost-presentCost-dishAmt-rawAmt lossGain,
//  			            ROUND ( ( beginAmt+inAmt-resultAmt-BASEAMT -staffCost-presentCost-dishAmt-rawAmt ) /
//  			            BUSINESSAMT * 100,2 ) lossGainPer
//  			        FROM
//  			            d
//  			    )
//  		SELECT
//  			branchId,
//  			DECODE(branchName,NULL,'合计',branchName) branchName ,
//  		    round(nvl(SUM(produceAmt),0),2)                                                    produceAmt,
//  		    round(nvl(SUM(businessAmt),0),2)                                                   businessAmt,
//  		    round(nvl(SUM(beginAmt),0),2)                                                      beginAmt,
//  		    round(nvl(SUM(inAmt),0),2)                                                         inAmt ,
//  		    round(nvl(SUM(staffCost),0),2)                                                     staffCost ,
//  		    round(nvl(SUM(presentCost),0),2)                                                    presentCost ,
//  		    round(nvl(SUM(resultAmt),0),2)                                                     resultAmt,
//  		    round(nvl(SUM(actualCost),0),2)                                                    actualCost,
//  		    round(nvl(avg(actualCostPer),0),2)                                                 actualCostPer,
//  		    round(nvl(SUM(baseAmt),0),2)                                                       baseAmt,
//  		    round(nvl(avg(beforeCostPer),0),2)                                                 beforeCostPer,
//  		    round(nvl(avg(afterCostPer),0),2)                                                  afterCostPer,
//  		    round(nvl(avg(staffCostPer),0),2)                                                  staffCostPer,
//  		    round(nvl(avg(presentCostPer),0),2)                                                presentCostPer,
//  		    round(nvl(SUM(dishAmt),0),2)                                                       dishAmt,
//  		    round(nvl(avg(dishPer),0),2)                                                       dishPer,
//  		    round(nvl(SUM(rawAmt),0),2)                                                        rawAmt,
//  		    round(nvl(avg(rawPer),0),2)                                                        rawPer,
//  		    round(nvl(SUM(lossGain),0),2)                                                      lossGain,
//  		    round(nvl(avg(lossGainPer),0),2)                                                   lossGainPer,
//  		    round(nvl(avg(staffCostPer+presentCostPer+dishPer+rawPer+lossGainPer),0),2) totalPer
//  		FROM
//  		    k
//  		GROUP BY
//  		    GROUPING SETS(( branchId,BRANCHNAME, produceAmt, BUSINESSAMT, beginAmt, inAmt, resultAmt, actualCost,
//  		    actualCostPer, BASEAMT, beforeCostPer, afterCostPer, staffCostPer, presentCostPer, dishAmt,
//  		    dishPer, rawAmt, rawPer, lossGain, lossGainPer, staffCostPer+presentCostPer+
//  		    dishPer+rawPer+lossGainPer),NULL)
//  		order by 1	
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_THERAPY");
  String __sJT_3 = Com_("d_t_food_Bill");
  String __sJT_4 = Com_("D_T_FOOD_BILLS");
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  String __sJT_7 = Com_("D_T2_ITEM_META");
  String __sJT_8 = Com_("d_t_food_Bill");
  String __sJT_9 = Com_("d_t_bill_pay");
  Date __sJT_10 = sDate;
  Date __sJT_11 = eDate;
  String __sJT_12 = Com_("D_T1_CHECK_HEADER");
  Date __sJT_13 = sPreDate;
  String __sJT_14 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_15 = Com_("D_T1_CHECK_HEADER");
  Date __sJT_16 = eDate;
  String __sJT_17 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_18 = Com_("D_T0_STORAGE_IN_OUT");
  Date __sJT_19 = sDate;
  Date __sJT_20 = eDate;
  String __sJT_21 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestaurantCostBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      __sJT_stmt.setDate(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
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

/*@lineinfo:user-code*//*@lineinfo:458^3*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("branchId", headerIter.branchId());
			header.put("branchName", headerIter.branchName());
			header.put("produceAmt", headerIter.produceAmt());
			header.put("businessAmt", headerIter.businessAmt());
			header.put("baseAmt", headerIter.baseAmt());
			header.put("beforeCostPer", headerIter.beforeCostPer());
			header.put("afterCostPer", headerIter.afterCostPer());
			header.put("staffCost", headerIter.staffCost());
			header.put("presentCost", headerIter.presentCost());
			header.put("staffCostPer", headerIter.staffCostPer());
			header.put("presentCostPer", headerIter.presentCostPer());
			header.put("dishAmt", headerIter.dishAmt());
			header.put("rawAmt", headerIter.rawAmt());
			header.put("dishPer", headerIter.dishPer());
			header.put("rawPer", headerIter.rawPer());
			
			header.put("beginAmt", headerIter.beginAmt());
			header.put("inAmt", headerIter.inAmt());
			header.put("resultAmt", headerIter.resultAmt());
			header.put("actualCost", headerIter.actualCost());
			header.put("actualCostPer", headerIter.actualCostPer());
			header.put("lossGain", headerIter.lossGain());
			header.put("lossGainPer", headerIter.lossGainPer());
			header.put("totalPer", headerIter.totalPer());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RestaurantCostBean_SJProfileKeys 
{
  private static RestaurantCostBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RestaurantCostBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RestaurantCostBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.RestaurantCostBean_SJProfile0");
  }
}
