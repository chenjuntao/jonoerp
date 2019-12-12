/*@lineinfo:filename=BranchIncomeBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.special;

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
import com.tanry.framework.util.TextUtil;

public class BranchIncomeBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(BranchIncomeBean.class);
	
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
    dbusinessNdx = findColumn("dbusiness");
    weekinfoNdx = findColumn("weekinfo");
    dayinfoNdx = findColumn("dayinfo");
    infosNdx = findColumn("infos");
  }
  public String dbusiness() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(dbusinessNdx);
  }
  private int dbusinessNdx;
  public String weekinfo() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(weekinfoNdx);
  }
  private int weekinfoNdx;
  public String dayinfo() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(dayinfoNdx);
  }
  private int dayinfoNdx;
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:32^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:34^2*/

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

/*@lineinfo:user-code*//*@lineinfo:36^3*/
	
	public Integer count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		TotalIter totalIter = null;
		Integer total = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] totalIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT DISTINCT
//  			            b.DBUSINESS,
//  			            TO_CHAR(b.DBUSINESS , 'iw') weekinfo
//  			        FROM
//  			            :Com_("D_T_FOOD_BILL") b
//  			        WHERE
//  			            :queryStr
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            weekinfo
//  			        FROM
//  			            a
//  			        GROUP BY
//  			            GROUPING sets((DBUSINESS,weekinfo),weekinfo,NULL)
//  			    )
//  			SELECT
//  			    COUNT(*) totals
//  			FROM
//  			    b };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  String __sJT_2 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchIncomeBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:71^9*/
    
		if(totalIter.next()) {
			total = totalIter.totals();
		}
		
		total = total == null ? 0:total;
	    closeDefaultContext(myCtx);
		return total;
	}
	
	public List<Map> query(String queryStr,int startRow,int endRow)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:87^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            b.DBUSINESS,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C = '3036'
//  			                    THEN -bs.NPRC* bs.NQTY
//  			                    ELSE 0
//  			                END ) amt1,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.ERETSENDFLAG = '赠送'
//  			                    THEN bs.NPRC* bs.NQTY
//  			                    ELSE 0
//  			                END ) amt2
//  			        FROM
//  			            :Com_("D_T_FOOD_BILL") b
//  			        INNER JOIN
//  			            :Com_("D_T_FOOD_BILLS") bs
//  			        ON
//  			            b.CBILL_C = bs.CBILL_C
//  			        WHERE
//  			            :queryStr
//  			        GROUP BY
//  			            b.DBUSINESS,
//  			            b.CBRANCH_C
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            CPAY_C,
//  			            NPAYAMT
//  			        FROM
//  			            a unpivot (NPAYAMT FOR CPAY_C IN (amt1,
//  			                                              amt2) )
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT
//  			            b.DBUSINESS,
//  			            p.CPAY_C,
//  			            SUM(p.NPAYAMT) NPAYAMT
//  			        FROM
//  			            :Com_("D_T_BILL_PAY") p
//  			        INNER JOIN
//  			            :Com_("D_T_FOOD_BILL") b
//  			        ON
//  			            p.CBILL_C = b.CBILL_C
//  			        WHERE
//  			            :queryStr
//  			        AND p.CPAY_C IN('01',
//  			                        '02',
//  			                        '03',
//  			                        '07',
//  			                        '15',
//  			                        '16',
//  			                        '23',
//  			                        '18',
//  			                        '04',
//  			                        '05',
//  			                        '08',
//  			                        '09')
//  			        GROUP BY
//  			            b.DBUSINESS,
//  			            p.CPAY_C
//  			    )
//  			    ,
//  			    d AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            b
//  			        UNION ALL
//  			        SELECT
//  			            *
//  			        FROM
//  			            c
//  			    )
//  			    ,
//  			    e AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            d
//  			        WHERE
//  			            d.CPAY_C IN ('08',
//  			                         '09',
//  			                         'AMT2')
//  			    )
//  			    ,
//  			    f AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            d
//  			        WHERE
//  			            d.CPAY_C NOT IN ('08',
//  			                             '09',
//  			                             'AMT2')
//  			    )
//  			    ,
//  			    g AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            DECODE(CPAY_C,NULL,'lsum',CPAY_C)CPAY_C,
//  			            SUM(NPAYAMT)                     NPAYAMT
//  			        FROM
//  			            d
//  			        GROUP BY
//  			            GROUPING SETS ((DBUSINESS,CPAY_C,NPAYAMT), (DBUSINESS,NULL))
//  			        UNION ALL
//  			        SELECT
//  			            DBUSINESS,
//  			            'nsum' ,
//  			            SUM(NPAYAMT)
//  			        FROM
//  			            e
//  			        GROUP BY
//  			            DBUSINESS
//  			        UNION ALL
//  			        SELECT
//  			            DBUSINESS,
//  			            'psum' ,
//  			            SUM(NPAYAMT)
//  			        FROM
//  			            f
//  			        GROUP BY
//  			            DBUSINESS
//  			    )
//  			    ,
//  			    h AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            TO_CHAR(DBUSINESS , 'iw') weekinfo,
//  			            TO_CHAR(DBUSINESS,'day')  dayinfo,
//  			            CPAY_C,
//  			            NPAYAMT
//  			        FROM
//  			            g
//  			    )
//  			    ,
//  			    i AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            weekinfo,
//  			            dayinfo,
//  			            CPAY_C,
//  			            SUM(NPAYAMT) NPAYAMT
//  			        FROM
//  			            h
//  			        GROUP BY
//  			            GROUPING sets (( DBUSINESS, weekinfo,dayinfo, CPAY_C, NPAYAMT),(weekinfo,CPAY_C ,NULL)
//  			            , CPAY_C)
//  			    )
//  			    ,
//  			    i2 AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            weekinfo,
//  			            dayinfo,
//  			            CPAY_C,
//  			            DECODE(CPAY_C,'AMT1',-NPAYAMT,NPAYAMT)NPAYAMT
//  			        FROM
//  			            i
//  			    )
//  			    ,
//  			    j AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            WEEKINFO,
//  			            DAYINFO,
//  			            LISTAGG('('||CPAY_C||'|' ||NPAYAMT||')',',') WITHIN GROUP (ORDER BY CPAY_C) AS infos
//  			        FROM
//  			            (
//  			                SELECT
//  			                    *
//  			                FROM
//  			                    i2)
//  			        GROUP BY
//  			            DBUSINESS,
//  			            WEEKINFO,
//  			            DAYINFO
//  			    )
//  			    ,
//  			    k AS
//  			    (
//  			        SELECT
//  			            DECODE(DBUSINESS,NULL,DECODE(WEEKINFO,NULL,'合计',DECODE(DAYINFO,NULL,'周汇总',TO_CHAR
//  			            (DBUSINESS, 'YYYY-MM-DD'))),TO_CHAR(DBUSINESS, 'YYYY-MM-DD') ) DBUSINESS,
//  			            WEEKINFO,
//  			            DAYINFO,
//  			            infos
//  			        FROM
//  			            j
//  			        ORDER BY
//  			            2,1
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            t.*,
//  			            ROWNUM rowNumber
//  			        FROM
//  			            (
//  			                SELECT
//  			                    *
//  			                FROM
//  			                    k ) t
//  			        WHERE
//  			            ROWNUM < :endRow)
//  			WHERE
//  			    rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  String __sJT_2 = Com_("D_T_FOOD_BILLS");
  String __sJT_3 = queryStr;
  String __sJT_4 = Com_("D_T_BILL_PAY");
  String __sJT_5 = Com_("D_T_FOOD_BILL");
  String __sJT_6 = queryStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchIncomeBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
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

/*@lineinfo:user-code*//*@lineinfo:314^30*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<Map> queryInfo(String queryStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		List<Map> branchLst = new ArrayList<Map>();
		return branchLst;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("dbusiness", headerIter.dbusiness());
			header.put("weekinfo", headerIter.weekinfo());
			header.put("dayinfo", headerIter.dayinfo());
			
			String[] infos = headerIter.infos().toString().split(",");
			for (int i = 0; i < infos.length; i++) {
				String[] temp = infos[i].replace("(", "").replace(")", "").split("\\|");
				
				for (int j = 0; j < temp.length; j++) {
					header.put(temp[0], temp[1]);
				}
			}
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class BranchIncomeBean_SJProfileKeys 
{
  private static BranchIncomeBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BranchIncomeBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BranchIncomeBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.special.BranchIncomeBean_SJProfile0");
  }
}
