/*@lineinfo:filename=FoodCTRBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.special;

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
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.acl.NoPrivilegeException;

public class FoodCTRBean extends ConnectionPool {
	public static Logger log = Logger.getLogger(FoodCTRBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:25^2*/

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
    clitcls_cNdx = findColumn("clitcls_c");
    clitcls_nNdx = findColumn("clitcls_n");
    cfood_cNdx = findColumn("cfood_c");
    cfood_nNdx = findColumn("cfood_n");
    nqtyNdx = findColumn("nqty");
    billsNdx = findColumn("bills");
    ctrNdx = findColumn("ctr");
    gdpNdx = findColumn("gdp");
    item_idNdx = findColumn("item_id");
    spriceNdx = findColumn("sprice");
    infosNdx = findColumn("infos");
  }
  public String clitcls_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(clitcls_cNdx);
  }
  private int clitcls_cNdx;
  public String clitcls_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(clitcls_nNdx);
  }
  private int clitcls_nNdx;
  public String cfood_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cfood_cNdx);
  }
  private int cfood_cNdx;
  public String cfood_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cfood_nNdx);
  }
  private int cfood_nNdx;
  public Double nqty() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nqtyNdx);
  }
  private int nqtyNdx;
  public Double bills() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(billsNdx);
  }
  private int billsNdx;
  public Double ctr() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(ctrNdx);
  }
  private int ctrNdx;
  public Double gdp() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(gdpNdx);
  }
  private int gdpNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double sprice() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(spriceNdx);
  }
  private int spriceNdx;
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^16*/
	
	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class TotalIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TotalIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    totalsNdx = findColumn("totals");
  }
  public Integer totals() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(totalsNdx);
  }
  private int totalsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^2*/
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		TotalIter totalIter = null;
		Integer total = null;
		
		String[] strs = queryStr.split("\\|_\\|");
		String str= strs[0];
		String str1= strs[1];
		String str2= strs[2];
		
		/*@lineinfo:generated-code*//*@lineinfo:54^3*/

//  ************************************************************
//  #sql [myCtx] totalIter = { WITH
//  		    a AS
//  		    (
//  		        SELECT
//  		            bs.CBILL_C,
//  		            bs.CFOOD_C,
//  		            bs.CFOOD_N,
//  		            bs.cLitCls_c,
//  		            bs.clitCls_n,
//  		            bs.nQty
//  		        FROM
//  		        	:Com_("d_t_food_bill") b 
//  		        INNER JOIN
//  		        	:Com_("D_T_FOOD_BILLS")  bs
//  		        ON
//  		            b.CBILL_C = bs.CBILL_C
//          		LEFT JOIN
//               		:Com_("D_T2_TAG_DETAIL") d1
//               	ON
//               		bs.CFOOD_C = d1.CODE
//              	AND d1.TAG_ID = :str1
//  		        WHERE
//  		        	:str2 + str
//  		    )
//  		    ,
//  		    b AS
//  		    (
//  		        SELECT
//  		            CFOOD_C,
//  		            CFOOD_N,
//  		            cLitCls_c,
//  		            clitCls_n,
//  		            SUM(nQty) nQty
//  		        FROM
//  		            a
//  		        GROUP BY
//  		            grouping sets (( CFOOD_C, CFOOD_N, cLitCls_c, clitCls_n),cLitCls_c,NULL)
//  		    )
//  		SELECT
//  		    count(*) totals
//  		FROM
//  		    b };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("D_T_FOOD_BILLS");
  String __sJT_3 = Com_("D_T2_TAG_DETAIL");
  String __sJT_4 = str1;
  String __sJT_5 = str2 + str;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodCTRBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      totalIter = new TotalIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:96^8*/
		
		if(totalIter.next()) {
			total = totalIter.totals();
		}
		
		total = total == null ? 0:total;
		closeDefaultContext(myCtx);
		return total;
	}
	
	public List<Map> query(String queryStr,int startRow, int endRow)  
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String[] strs = queryStr.split("\\|_\\|");
		String str= strs[0];
		String str1= strs[1];
		String str2= strs[2];
		
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:117^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                    WITH
//  			                        p AS
//  			                        (
//  			                            SELECT
//  			                                p.ITEM_ID,
//  			                                p.PRICE_TYPE,
//  			                                p.ITEM_PRICE
//  			                            FROM
//  			                                :Com_("D_T2_ITEM_PRICE") p
//  			                            WHERE
//  			                                p.IS_CURRENT = 1
//  			                            AND p.PRICE_TYPE IN ('BENCHMARK',
//  			                                                 'PURCHASE')
//  			                        )
//  			                        ,
//  			                        tp AS
//  			                        (
//  			                            SELECT
//  			                                t.THERAPY_ID ITEM_ID,
//  			                                p.PRICE_TYPE,
//  			                                NVL(ROUND( SUM(t.ITEM_GROSS_COUNT * p.item_price),2),0) unitPrice
//  			                            FROM
//  			                                :Com_("D_T2_THERAPY") t
//  			                            LEFT JOIN
//  			                                p
//  			                            ON
//  			                                t.ITEM_ID = p.ITEM_ID
//  			                            WHERE
//  			                                p.PRICE_TYPE IS NOT NULL
//  			                            GROUP BY
//  			                                t.THERAPY_ID,
//  			                                p.PRICE_TYPE
//  			                        )
//  			                        ,
//  			                        items AS
//  			                        (
//  			                            SELECT
//  			                                tp.*,
//  			                                CASE
//  			                                    WHEN NVL(p2.ITEM_PRICE,0)=0
//  			                                    THEN NULL
//  			                                    ELSE ROUND((p2.ITEM_PRICE-UNITPRICE)/p2.ITEM_PRICE,4)
//  			                                END           prams,
//  			                                p2.ITEM_PRICE sPrice,
//  			                                m.CATEGORY_ID
//  			                            FROM
//  			                                tp
//  			                            INNER JOIN
//  			                                :Com_("D_T2_ITEM_META") m
//  			                            ON
//  			                                tp.ITEM_ID = m.ITEM_ID
//  			                            LEFT JOIN
//  			                                :Com_("D_T2_ITEM_PRICE") p2
//  			                            ON
//  			                                tp.ITEM_ID = p2.ITEM_ID
//  			                            AND p2.IS_CURRENT = 1
//  			                            AND p2.PRICE_TYPE= 'SALE'
//  			                            LEFT JOIN
//  			                            	:Com_("D_T2_TAG_DETAIL") d1
//  			    			            ON
//  			    			            	tp.ITEM_ID = d1.CODE
//      			            			 AND d1.TAG_ID = :str1
//      			            			 WHERE
//  			                            	1=1   :str
//  			                        )
//  			                        ,
//  			                        items2 AS
//  			                        (
//  			                            SELECT
//  			                                CATEGORY_ID,
//  			                                ITEM_ID,
//  			                                SPRICE,
//  			                                LISTAGG('('||PRICE_TYPE||'|'||UNITPRICE||'|'||PRAMS||')', ',') WITHIN
//  			                                GROUP (ORDER BY ITEM_ID) AS infos
//  			                            FROM
//  			                                (
//  			                                    SELECT
//  			                                        *
//  			                                    FROM
//  			                                        items )
//  			                            GROUP BY
//  			                                CATEGORY_ID,
//  			                                ITEM_ID,
//  			                                SPRICE
//  			                        )
//  			                        ,
//  			                        bs AS
//  			                        (
//  			                            SELECT
//  			                                *
//  			                            FROM
//  			                                :Com_("D_T_FOOD_BILLS") bs
//  					                    LEFT JOIN
//  		                            		:Com_("D_T2_TAG_DETAIL") d1
//  		                            	ON
//  			    			            	bs.CFOOD_C = d1.CODE  AND d1.TAG_ID = :str1		
//  			                            WHERE
//  			                                1=1 :str
//  			                        )
//  			                        ,
//  			                        a AS
//  			                        (
//  			                            SELECT
//  			                                bs.CBILL_C,
//  			                                bs.CFOOD_C,
//  			                                bs.CFOOD_N,
//  			                                bs.cLitCls_c,
//  			                                bs.clitCls_n,
//  			                                bs.nQty
//  			                            FROM
//  			                                :Com_("d_t_food_bill") b
//  			                            INNER JOIN
//  			                                bs
//  			                            ON
//  			                                b.CBILL_C = bs.CBILL_C
//  			                            WHERE
//  			                            	:str2
//  			                        )
//  			                        ,
//  			                        b AS
//  			                        (
//  			                            SELECT
//  			                                CFOOD_C,
//  			                                CFOOD_N,
//  			                                cLitCls_c,
//  			                                clitCls_n,
//  			                                SUM(nQty) nQty
//  			                            FROM
//  			                                a
//  			                            GROUP BY
//  			                                grouping sets (( CFOOD_C, CFOOD_N, cLitCls_c, clitCls_n),(cLitCls_c,
//  			                                clitCls_n),NULL )
//  			                        )
//  			                        ,
//  			                        c AS
//  			                        (
//  			                            SELECT
//  			                                COUNT(DISTINCT CBILL_C) bills
//  			                            FROM
//  			                                a
//  			                        )
//  			                        ,
//  			                        d AS
//  			                        (
//  			                            SELECT
//  			                                b.CLITCLS_C,
//  			                                b.CLITCLS_N,
//  			                                b.CFOOD_C,
//  			                                b.CFOOD_N,
//  			                                b.nQty,
//  			                                c.*,
//  			                                ROUND(NQTY/BILLS,4) ctr,
//  			                                DECODE ( b.CFOOD_C,NULL,ROUND(NQTY/
//  			                                (
//  			                                    SELECT
//  			                                        NQTY
//  			                                    FROM
//  			                                        b
//  			                                    WHERE
//  			                                        CFOOD_C IS NULL
//  			                                    AND CLITCLS_C IS NULL),4), '' ) gdp
//  			                            FROM
//  			                                b,
//  			                                c
//  			                        )
//  			                    SELECT
//  			                    	CLITCLS_C,
//  			                        CASE
//  			                        WHEN CLITCLS_C IS NOT NULL
//  				                        AND CFOOD_N IS NULL
//  				                        THEN CLITCLS_N||'汇总'
//  				                        WHEN CLITCLS_C IS NULL
//  				                        THEN '合计'
//  				                        ELSE CLITCLS_N
//  			                        END CLITCLS_N ,
//  			                        d.CFOOD_C,
//  			                        d.CFOOD_N,
//  			                        d.NQTY,
//  			                        d.BILLS,
//  			                        d.CTR,
//  			                        d.GDP,
//  			                        i.*
//  			                    FROM
//  			                        d
//  			                    LEFT JOIN
//  			                        items2 i
//  			                    ON
//  			                        d.CFOOD_C = i.item_id
//  			                    AND d.CLITCLS_C = i.CATEGORY_ID
//  			                    ORDER BY
//  			                        CLITCLS_C,
//  			                        CFOOD_C) t
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
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_THERAPY");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = Com_("D_T2_TAG_DETAIL");
  String __sJT_6 = str1;
  String __sJT_7 = str;
  String __sJT_8 = Com_("D_T_FOOD_BILLS");
  String __sJT_9 = Com_("D_T2_TAG_DETAIL");
  String __sJT_10 = str1;
  String __sJT_11 = str;
  String __sJT_12 = Com_("d_t_food_bill");
  String __sJT_13 = str2;
  int __sJT_14 = endRow;
  int __sJT_15 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodCTRBean_SJProfileKeys.getKey(0), 1);
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
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:322^34*/
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
			header.put("clitcls_c", headerIter.clitcls_c());
			header.put("clitcls_n", headerIter.clitcls_n());
			header.put("cfood_c", headerIter.cfood_c());
			header.put("cfood_n", headerIter.cfood_n());
			header.put("nqty", headerIter.nqty());
			header.put("bills", headerIter.bills());
			header.put("ctr", headerIter.ctr());
			header.put("gdp", headerIter.gdp());
			header.put("item_id", headerIter.item_id());
			header.put("sprice", headerIter.sprice());
			
			if(headerIter.infos() != null){
				String[] infos = headerIter.infos().toString().split(",");
				for (int i = 0; i < infos.length; i++) {
					String[] temp = infos[i].replace("(", "").replace(")", "").split("\\|");
					
					
					for (int j = 0,length =temp.length ; j < length; j++) {
						if("BENCHMARK".equals(temp[0])){
							if(length>=3){
								header.put("bprice", temp[1]);
								header.put("bper", temp[2]);
							}else if(length>=2){
								header.put("bprice", temp[1]);
							}
						}
						
						if("PURCHASE".equals(temp[0])){
							if(length>=3){
								header.put("pprice", temp[1]);
								header.put("pper", temp[2]);
							}else if(length>=2){
								header.put("pprice", temp[1]);
							}
						}
					}
				}
			}
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class FoodCTRBean_SJProfileKeys 
{
  private static FoodCTRBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodCTRBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodCTRBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.special.FoodCTRBean_SJProfile0");
  }
}
