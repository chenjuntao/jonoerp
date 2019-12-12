/*@lineinfo:filename=LcCostBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class LcCostBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(LcCostBean.class);
	
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
    storageIdNdx = findColumn("storageId");
    storageNameNdx = findColumn("storageName");
    rawAmtNdx = findColumn("rawAmt");
    beginAmtNdx = findColumn("beginAmt");
    inAmtNdx = findColumn("inAmt");
    resultAmtNdx = findColumn("resultAmt");
    actualCostNdx = findColumn("actualCost");
    receiveAmtNdx = findColumn("receiveAmt");
    diffAmtNdx = findColumn("diffAmt");
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
  public String storageId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageIdNdx);
  }
  private int storageIdNdx;
  public String storageName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNameNdx);
  }
  private int storageNameNdx;
  public Double rawAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(rawAmtNdx);
  }
  private int rawAmtNdx;
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
  public Double receiveAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receiveAmtNdx);
  }
  private int receiveAmtNdx;
  public Double diffAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(diffAmtNdx);
  }
  private int diffAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^3*/
	
	public List<Map> query(java.util.Date startDate, java.util.Date endDate,java.util.Date startPreDate)  
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		Date sPreDate = SqlDateUtil.getSqlDate(startPreDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:50^3*/

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
//  			    f AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            (
//  			                SELECT
//  			                    t.*,
//  			                    row_number() over(partition BY CHECK_BRANCH_ID,CHECK_STORAGE_ID ORDER BY AUDIT_TIME DESC ) rn
//  			                FROM
//  			                    (
//  			                        SELECT
//  			                            h.CHECK_BRANCH_ID,
//  			                            h.CHECK_STORAGE_ID,
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
//  			            f.CHECK_STORAGE_ID,
//  			            SUM(d.CHECK_COUNT) beginAmt
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
//  			            f.CHECK_BRANCH_ID ,f.CHECK_STORAGE_ID
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
//  			                    row_number() over(partition BY CHECK_BRANCH_ID,CHECK_STORAGE_ID ORDER BY AUDIT_TIME DESC ) rn
//  			                FROM
//  			                    (
//  			                        SELECT
//  			                            h.CHECK_BRANCH_ID,
//  			                            h.CHECK_STORAGE_ID,
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
//  			            h.CHECK_STORAGE_ID,
//  			            SUM(d.CHECK_COUNT) resultAmt
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
//  			            h.CHECK_BRANCH_ID,h.CHECK_STORAGE_ID
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  		        	SELECT
//  			    	    b.BRANCH_ID,
//  			    	    b.BRANCH_NAME,
//  			    	    bs.STORAGE_ID,
//  			    	    bs.STORAGE_NAME
//  			    	FROM
//  			    		:Com_("D_T2_BRANCH") b 
//  			    	INNER JOIN
//  			    		:Com_("D_T2_BRANCH_STORAGE") bs 
//  			    	ON
//  			    	    b.BRANCH_ID = bs.BRANCH_ID
//  			    	WHERE
//  			    	    b.BRANCH_TYPE = 'LOGISTICSCENTER'
//  			    )
//  			    ,
//  			    j AS
//  			    (
//  			        SELECT
//  			            io.BRANCH_ID,
//  			            io.STORAGE_ID,
//  			    	    SUM(
//  			    	            CASE
//  			    	                WHEN io.REASON IN ('PSTH',
//  			    	                                   'PSRK',
//  			    	                                   'CGRK',
//  			    	                                   'JSRK',
//  			    	                                   'DB',
//  			    	                                   'PSFSH',
//  			    	                                   'CGTH',
//  			    	                                   'PSCK')
//  			    	                THEN nvl(p.ITEM_PRICE*io.ITEM_COUNT_IN,0)-nvl(p.ITEM_PRICE*io.ITEM_COUNT_OUT,0)
//  			    	                ELSE 0
//  			    	            END)amt1,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN('YLBS')
//  			                    THEN p.ITEM_PRICE*io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END) amt2
//  			        FROM
//  			        	:Com_("D_T0_STORAGE_IN_OUT") io 
//  			        LEFT JOIN
//  			            p
//  			        ON
//  			            io.item_id = p.item_id
//  			        WHERE
//  			            io.BUSINESS_DATE >= :sDate
//  			        AND io.BUSINESS_DATE <= :eDate
//  			        GROUP BY
//  			        	io.BRANCH_ID,io.STORAGE_ID
//  			    )
//  			    ,
//  			    k AS
//  			    (
//  			        SELECT
//  			            h.PROVIDER_ID,
//  			            h.OUT_STORAGE_ID,
//  			            SUM(d.RECEIVE_COUNT* p.ITEM_PRICE)                                   receiveAmt,
//  			            SUM(d.DELIVERY_COUNT* p.ITEM_PRICE - d.RECEIVE_COUNT* p.ITEM_PRICE ) diffAmt
//  			        FROM
//  			        	:Com_("D_T1_SHIPPING_HEADER") h 
//  			        INNER JOIN
//  			        	:Com_("D_T2_BRANCH") b 
//  			        ON
//  			            h.PROVIDER_ID = b.BRANCH_ID
//  			        AND b.BRANCH_TYPE = 'LOGISTICSCENTER'
//  			        INNER JOIN
//  			        	:Com_("D_T1_SHIPPING_DETAIL") d 
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        LEFT JOIN
//  			            p
//  			        ON
//  			            d.ITEM_ID = p.item_id
//  			        WHERE
//  			            h.INPUT_TIME IS NOT NULL
//  			        GROUP BY
//  			            h.PROVIDER_ID,h.OUT_STORAGE_ID
//  			    )
//  			    ,
//  			    m AS
//  			    (
//  			        SELECT
//  			            b.*,
//  			            ROUND(NVL(g.beginAmt,0),2)  beginAmt,
//  			            ROUND(NVL(i.RESULTAMT,0),2) RESULTAMT,
//  			            ROUND(NVL(amt1,0),2)   inAmt,
//  			            ROUND(NVL(amt2,0),2)        lossAmt,
//  			            ROUND(NVL(receiveAmt,0),2)  receiveAmt,
//  			            ROUND(NVL(diffAmt,0),2)     diffAmt
//  			        FROM
//  			            b
//  			        LEFT JOIN
//  			            g
//  			        ON
//  			            b.BRANCH_ID = g.CHECK_BRANCH_ID  AND b.STORAGE_ID = g.CHECK_STORAGE_ID
//  			        LEFT JOIN
//  			            i
//  			        ON
//  			            b.BRANCH_ID = i.CHECK_BRANCH_ID  AND b.STORAGE_ID = i.CHECK_STORAGE_ID
//  			        LEFT JOIN
//  			            j
//  			        ON
//  			            b.BRANCH_ID = j.BRANCH_ID AND b.STORAGE_ID = j.STORAGE_ID
//  			        LEFT JOIN
//  			            k
//  			        ON
//  			            b.BRANCH_ID = k.PROVIDER_ID AND b.STORAGE_ID = k.OUT_STORAGE_ID
//  			    )
//  			SELECT
//  			    BRANCH_ID branchId,
//  			    DECODE(BRANCH_ID , NULL,'合计', '['|| BRANCH_ID|| ']' ||BRANCH_NAME) branchName,
//  			    STORAGE_ID storageId,
//  	    	    STORAGE_NAME storageName,
//  			    SUM(BEGINAMT)                                                      beginAmt,
//  			    SUM(RESULTAMT)                                                     resultAmt,
//  			    SUM(INAMT)                                                         inAmt ,
//  			    SUM(BEGINAMT +INAMT - RESULTAMT) 								   actualCost,
//  			    SUM(LOSSAMT)                                                       rawAmt,
//  			    SUM(RECEIVEAMT)                                                    receiveAmt,
//  			    SUM(DIFFAMT)                                                       diffAmt
//  			FROM
//  			    m
//  			GROUP BY
//  			    GROUPING SETS((BRANCH_ID,BRANCH_NAME,STORAGE_ID,STORAGE_NAME,BEGINAMT,RESULTAMT,INAMT,LOSSAMT, RECEIVEAMT,DIFFAMT),NULL
//  			    )order by 1,3 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_THERAPY");
  String __sJT_3 = Com_("D_T1_CHECK_HEADER");
  Date __sJT_4 = sPreDate;
  String __sJT_5 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_6 = Com_("D_T1_CHECK_HEADER");
  Date __sJT_7 = eDate;
  String __sJT_8 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_9 = Com_("D_T2_BRANCH");
  String __sJT_10 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_11 = Com_("D_T0_STORAGE_IN_OUT");
  Date __sJT_12 = sDate;
  Date __sJT_13 = eDate;
  String __sJT_14 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_15 = Com_("D_T2_BRANCH");
  String __sJT_16 = Com_("D_T1_SHIPPING_DETAIL");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LcCostBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setDate(12, __sJT_12);
      __sJT_stmt.setDate(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
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

/*@lineinfo:user-code*//*@lineinfo:298^21*/
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
			header.put("rawAmt", headerIter.rawAmt());
			header.put("beginAmt", headerIter.beginAmt());
			header.put("inAmt", headerIter.inAmt());
			header.put("resultAmt", headerIter.resultAmt());
			header.put("actualCost", headerIter.actualCost());
			header.put("receiveAmt", headerIter.receiveAmt());
			header.put("diffAmt", headerIter.diffAmt());
			header.put("storageId", headerIter.storageId());
			header.put("storageName", headerIter.storageName());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class LcCostBean_SJProfileKeys 
{
  private static LcCostBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new LcCostBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private LcCostBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.LcCostBean_SJProfile0");
  }
}
