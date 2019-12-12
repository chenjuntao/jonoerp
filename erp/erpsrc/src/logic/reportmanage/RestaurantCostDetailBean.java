/*@lineinfo:filename=RestaurantCostDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class RestaurantCostDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RestaurantCostDetailBean.class);
	
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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_priceNdx = findColumn("item_price");
    begin_countNdx = findColumn("begin_count");
    begin_amtNdx = findColumn("begin_amt");
    end_countNdx = findColumn("end_count");
    end_amtNdx = findColumn("end_amt");
    sum1Ndx = findColumn("sum1");
    amt1Ndx = findColumn("amt1");
    sum2Ndx = findColumn("sum2");
    amt2Ndx = findColumn("amt2");
    sum3Ndx = findColumn("sum3");
    amt3Ndx = findColumn("amt3");
    sum4Ndx = findColumn("sum4");
    amt4Ndx = findColumn("amt4");
    sum5Ndx = findColumn("sum5");
    amt5Ndx = findColumn("amt5");
    sum6Ndx = findColumn("sum6");
    amt6Ndx = findColumn("amt6");
    sum7Ndx = findColumn("sum7");
    amt7Ndx = findColumn("amt7");
    sum8Ndx = findColumn("sum8");
    amt8Ndx = findColumn("amt8");
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
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double begin_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(begin_countNdx);
  }
  private int begin_countNdx;
  public Double begin_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(begin_amtNdx);
  }
  private int begin_amtNdx;
  public Double end_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(end_countNdx);
  }
  private int end_countNdx;
  public Double end_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(end_amtNdx);
  }
  private int end_amtNdx;
  public Double sum1() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum1Ndx);
  }
  private int sum1Ndx;
  public Double amt1() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt1Ndx);
  }
  private int amt1Ndx;
  public Double sum2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum2Ndx);
  }
  private int sum2Ndx;
  public Double amt2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt2Ndx);
  }
  private int amt2Ndx;
  public Double sum3() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum3Ndx);
  }
  private int sum3Ndx;
  public Double amt3() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt3Ndx);
  }
  private int amt3Ndx;
  public Double sum4() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum4Ndx);
  }
  private int sum4Ndx;
  public Double amt4() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt4Ndx);
  }
  private int amt4Ndx;
  public Double sum5() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum5Ndx);
  }
  private int sum5Ndx;
  public Double amt5() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt5Ndx);
  }
  private int amt5Ndx;
  public Double sum6() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum6Ndx);
  }
  private int sum6Ndx;
  public Double amt6() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt6Ndx);
  }
  private int amt6Ndx;
  public Double sum7() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum7Ndx);
  }
  private int sum7Ndx;
  public Double amt7() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt7Ndx);
  }
  private int amt7Ndx;
  public Double sum8() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sum8Ndx);
  }
  private int sum8Ndx;
  public Double amt8() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(amt8Ndx);
  }
  private int amt8Ndx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:60^3*/
	
	public List<Map> query(String branchId,java.util.Date startDate, java.util.Date endDate, java.util.Date startPreDate,String tagId)  
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		Date sPreDate = SqlDateUtil.getSqlDate(startPreDate);
		
		String query = " 1=1";
		if(!TextUtil.isEmpty(tagId)){
			query = "  d1.TAG_ID = '" + tagId+ "' ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:76^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    p AS
//  			    (
//  			        SELECT
//  			            p.ITEM_ID,
//  			            p.ITEM_PRICE
//  			        FROM
//  			            :Com_("D_T2_ITEM_PRICE") p
//  			        WHERE
//  			            p.IS_CURRENT = 1
//  			        AND p.PRICE_TYPE = 'BENCHMARK'
//  			    )
//  			    ,
//  			    a AS
//  			    (
//  			        SELECT
//  			            io.BRANCH_ID,
//  			            io.ITEM_ID,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN('CGRK',
//  			                                      'PSRK',
//  			                                      'PSTH',
//  			                                      'CGTH',
//  			                                      'PSFSH')
//  			                    THEN io.ITEM_COUNT_IN-io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END) sum1,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ( 'DB')
//  			                    THEN io.ITEM_COUNT_IN
//  			                    ELSE 0
//  			                END)sum2,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ( 'DB')
//  			                    THEN io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END)sum3,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ( 'LLKU')
//  			                    THEN io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END)sum4,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ( 'YLBS')
//  			                    THEN io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END)sum5,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ( 'CPBS')
//  			                    THEN io.ITEM_COUNT_OUT
//  			                    ELSE 0
//  			                END)sum6
//  			        FROM
//  			            :Com_("D_T0_STORAGE_IN_OUT") io
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_DETAIL") d1
//  			        ON
//  			            io.ITEM_ID = d1.CODE
//  			        AND d1.TAG_ID = :tagId
//  			        WHERE
//  			            :query
//  			        AND io.BUSINESS_DATE >= :sDate
//  			        AND io.BUSINESS_DATE <= :eDate
//  			        AND io.BRANCH_ID = :branchId
//  			        GROUP BY
//  			            io.BRANCH_ID,
//  			            io.ITEM_ID
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            BRANCH_ID,
//  			            ITEM_ID,
//  			            sum1,
//  			            sum2,
//  			            sum3,
//  			            sum4,
//  			            sum5,
//  			            sum6
//  			        FROM
//  			            a
//  			        WHERE
//  			            (
//  			                sum1>0
//  			            OR  sum2>0
//  			            OR  sum3>0
//  			            OR  sum4>0
//  			            OR  sum5>0
//  			            OR  sum6 >0)
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
//  			                            :Com_("D_T1_CHECK_HEADER") h
//  			                        WHERE
//  			                            1=1
//  			                        AND h.FORM_TYPE = 'form'
//  			                        AND h.CHECK_BRANCH_ID = :branchId
//  			                        AND h.AUDIT_TIME <= :sPreDate ) t )
//  			        WHERE
//  			            rn=1
//  			    )
//  			    ,
//  			    g AS
//  			    (
//  			        SELECT
//  			            d.ITEM_ID,
//  			            d.CHECK_COUNT begin_count
//  			        FROM
//  			            f
//  			        LEFT JOIN
//  			            :Com_("D_T1_CHECK_DETAIL") d
//  			        ON
//  			            f.form_id = d.FORM_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_DETAIL") d1
//  			        ON
//  			            d.ITEM_ID = d1.CODE
//  			        AND d1.TAG_ID = :tagId
//  			        WHERE
//  			            :query
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
//  			                            :Com_("D_T1_CHECK_HEADER") h
//  			                        WHERE
//  			                            1=1
//  			                        AND h.FORM_TYPE = 'form'
//  			                        AND h.CHECK_BRANCH_ID = :branchId
//  			                        AND h.AUDIT_TIME <= :eDate ) t )
//  			        WHERE
//  			            rn=1
//  			    )
//  			    ,
//  			    i AS
//  			    (
//  			        SELECT
//  			            d.ITEM_ID,
//  			            d.CHECK_COUNT end_count
//  			        FROM
//  			            h
//  			        LEFT JOIN
//  			            :Com_("D_T1_CHECK_DETAIL") d
//  			        ON
//  			            h.form_id = d.FORM_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_DETAIL") d1
//  			        ON
//  			            d.ITEM_ID = d1.CODE
//  			        AND d1.TAG_ID = :tagId
//  			        WHERE
//  			            :query
//  			    )
//  			    ,
//  			    j AS
//  			    (
//  			        SELECT
//  			            DECODE(g.item_id,NULL,i.item_id,g.item_id) item_id,
//  			            NVL(begin_count,0)                         begin_count,
//  			            NVL(end_count,0)                           end_count
//  			        FROM
//  			            g
//  			        FULL JOIN
//  			            i
//  			        ON
//  			            g.item_id = i.item_id
//  			    )
//  			    ,
//  			    k AS
//  			    (
//  			        SELECT
//  			            DECODE(j.item_id,NULL,b.item_id,j.item_id) item_id ,
//  			            NVL(BEGIN_COUNT,0)                         BEGIN_COUNT,
//  			            NVL(END_COUNT,0)                           END_COUNT,
//  			            NVL(b.sum1,0)                              sum1,
//  			            NVL(b.sum2,0)                              sum2,
//  			            NVL(b.sum3,0)                              sum3,
//  			            NVL(b.sum4,0)                              sum4,
//  			            NVL(b.sum5,0)                              sum5,
//  			            NVL(b.sum6,0)                              sum6
//  			        FROM
//  			            j
//  			        FULL JOIN
//  			            b
//  			        ON
//  			            j.item_id = b.item_id
//  			        WHERE
//  			            (
//  			                j.BEGIN_COUNT >0
//  			            OR  j.END_COUNT >0
//  			            OR  b.item_id IS NOT NULL)
//  			    )
//  			    ,
//  			    l AS
//  			    (
//  			        SELECT
//  			            k.item_id,
//  			            m.item_name,
//  			            m.ITEM_DIMENSION,
//  			            p.ITEM_PRICE,
//  			            BEGIN_COUNT,
//  			            BEGIN_COUNT*p.item_price begin_amt,
//  			            end_count,
//  			            end_count*p.item_price end_amt,
//  			            sum1                   sum1,
//  			            sum1*p.item_price      amt1,
//  			            sum2,
//  			            sum2*p.item_price amt2,
//  			            sum3,
//  			            sum3*p.item_price amt3,
//  			            sum4,
//  			            sum4*p.item_price amt4,
//  			            sum5,
//  			            sum5*p.item_price amt5,
//  			            sum6,
//  			            sum6*p.item_price                          amt6,
//  			            BEGIN_COUNT +sum1-end_count                sum7,
//  			            (BEGIN_COUNT +sum1-end_count)*p.item_price amt7
//  			        FROM
//  			            k
//  			        LEFT JOIN
//  			            p
//  			        ON
//  			            k.item_id = p.item_id
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_META") m
//  			        ON
//  			            k.item_id = m.ITEM_ID
//  			    )
//  			SELECT
//  			    item_id,
//  			    item_name,
//  			    ITEM_DIMENSION,
//  			    ITEM_PRICE,
//  			    BEGIN_COUNT,
//  			    SUM(begin_amt)begin_amt,
//  			    end_count,
//  			    SUM(end_amt) end_amt,
//  			    sum1,
//  			    ROUND(SUM(amt1),2) amt1,
//  			    sum2,
//  			    ROUND(SUM(amt2),2)amt2,
//  			    sum3,
//  			    ROUND(SUM(amt3),2) amt3,
//  			    sum4,
//  			    ROUND(SUM(amt4),2)amt4,
//  			    sum5,
//  			    ROUND(SUM(amt5),2)amt5,
//  			    sum6,
//  			    ROUND(SUM(amt6),2)amt6,
//  			    sum7,
//  			    ROUND(SUM(amt7),2)                amt7,
//  			    (sum7-sum4-sum5-sum6)             sum8,
//  			    ROUND(SUM(amt7-amt4-amt5-amt6),2) amt8
//  			FROM
//  			    l
//  			GROUP BY
//  			    GROUPING SETS((item_id,item_name,ITEM_DIMENSION,ITEM_PRICE,BEGIN_COUNT,begin_amt,end_count,
//  			    end_amt, sum1,amt1,sum2,amt2,sum3,amt3,sum4,amt4,sum5,amt5,sum6,amt6,sum7,amt7,sum7-sum4-sum5-
//  			    sum6,amt7-amt4-amt5-amt6),NULL)
//  			ORDER BY
//  			    1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_3 = Com_("D_T2_TAG_DETAIL");
  String __sJT_4 = tagId;
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  String __sJT_8 = branchId;
  String __sJT_9 = Com_("D_T1_CHECK_HEADER");
  String __sJT_10 = branchId;
  Date __sJT_11 = sPreDate;
  String __sJT_12 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_13 = Com_("D_T2_TAG_DETAIL");
  String __sJT_14 = tagId;
  String __sJT_15 = query;
  String __sJT_16 = Com_("D_T1_CHECK_HEADER");
  String __sJT_17 = branchId;
  Date __sJT_18 = eDate;
  String __sJT_19 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_20 = Com_("D_T2_TAG_DETAIL");
  String __sJT_21 = tagId;
  String __sJT_22 = query;
  String __sJT_23 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RestaurantCostDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
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

/*@lineinfo:user-code*//*@lineinfo:374^9*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		int rownumber = 1;
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_price", headerIter.item_price());
			header.put("begin_count", headerIter.begin_count());
			header.put("begin_amt", headerIter.begin_amt());
			header.put("end_count", headerIter.end_count());
			header.put("end_amt", headerIter.end_amt());
			
			header.put("sum1", headerIter.sum1());
			header.put("amt1", headerIter.amt1());
			header.put("sum2", headerIter.sum2());
			header.put("amt2", headerIter.amt2());
			header.put("sum3", headerIter.sum3());
			header.put("amt3", headerIter.amt3());
			header.put("sum4", headerIter.sum4());
			header.put("amt4", headerIter.amt4());
			
			header.put("sum5", headerIter.sum5());
			header.put("amt5", headerIter.amt5());
			header.put("sum6", headerIter.sum6());
			header.put("amt6", headerIter.amt6());
			
			header.put("sum7", headerIter.sum7());
			header.put("amt7", headerIter.amt7());
			header.put("sum8", headerIter.sum8());
			header.put("amt8", headerIter.amt8());
			
			headerLst.add(header);
			
			rownumber++;
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RestaurantCostDetailBean_SJProfileKeys 
{
  private static RestaurantCostDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RestaurantCostDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RestaurantCostDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.RestaurantCostDetailBean_SJProfile0");
  }
}
