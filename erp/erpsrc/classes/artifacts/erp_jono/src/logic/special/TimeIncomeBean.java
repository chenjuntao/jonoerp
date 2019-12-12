/*@lineinfo:filename=TimeIncomeBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.special;

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

public class TimeIncomeBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(TimeIncomeBean.class);
	
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TimeIncomeBean_SJProfileKeys.getKey(0), 0);
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
//  			            b.CPERIOD_C,
//  			            b.CPERIOD_N,
//  			            SUM(nFoodAmt)  nFoodAmt,
//  			            COUNT(*)       bills,
//  			            SUM(iGuestNum) iGuestNum
//  			        FROM
//  			            :Com_("D_T_FOOD_BILL") b
//  			        WHERE
//  			            :queryStr
//  			        GROUP BY
//  			            b.DBUSINESS,
//  			            b.CPERIOD_C,
//  			            b.CPERIOD_N
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            TO_CHAR(DBUSINESS , 'iw')weekinfo,
//  			            TO_CHAR(DBUSINESS,'day') dayinfo,
//  			            CPERIOD_C,
//  			            CPERIOD_N,
//  			            SUM(nFoodAmt)  nFoodAmt,
//  			            SUM(bills)     bills,
//  			            SUM(iGuestNum) iGuestNum
//  			        FROM
//  			            a
//  			        GROUP BY
//  			            GROUPING sets(( DBUSINESS, TO_CHAR(DBUSINESS , 'iw'),TO_CHAR(DBUSINESS,'day'),
//  			            CPERIOD_C, CPERIOD_N),DBUSINESS,(TO_CHAR(DBUSINESS , 'iw'),CPERIOD_C),TO_CHAR(DBUSINESS
//  			            , 'iw'),CPERIOD_C,NULL)
//  			    )
//  			    ,
//  			    base AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            b
//  			        WHERE
//  			            DBUSINESS IS NOT NULL
//  			        AND WEEKINFO IS NOT NULL
//  			    )
//  			    ,
//  			    dsum AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            b
//  			        WHERE
//  			            DBUSINESS IS NOT NULL
//  			        AND WEEKINFO IS NULL
//  			    )
//  			    ,
//  			    wbase AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            b
//  			        WHERE
//  			            DBUSINESS IS NULL
//  			        AND WEEKINFO IS NOT NULL
//  			        AND DAYINFO IS NULL
//  			        AND CPERIOD_C IS NOT NULL
//  			    )
//  			    ,
//  			    wsum AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            b
//  			        WHERE
//  			            DBUSINESS IS NULL
//  			        AND WEEKINFO IS NOT NULL
//  			        AND DAYINFO IS NULL
//  			        AND CPERIOD_C IS NULL
//  			    )
//  			    ,
//  			    sbase AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            b
//  			        WHERE
//  			            DBUSINESS IS NULL
//  			        AND WEEKINFO IS NULL
//  			        AND DAYINFO IS NULL
//  			        AND CPERIOD_C IS NOT NULL
//  			    )
//  			    ,
//  			    ssum AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            b
//  			        WHERE
//  			            DBUSINESS IS NULL
//  			        AND WEEKINFO IS NULL
//  			        AND DAYINFO IS NULL
//  			        AND CPERIOD_C IS NULL
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT
//  			            bs.*,
//  			            ROUND(bs.BILLS/ds.BILLS,4)        BILLS_per,
//  			            ROUND(bs.IGUESTNUM/ds.IGUESTNUM,4)IGUESTNUM_per,
//  			            ROUND(bs.NFOODAMT/bs.BILLS,2)     ac
//  			        FROM
//  			            base bs
//  			        INNER JOIN
//  			            dsum ds
//  			        ON
//  			            bs.DBUSINESS = ds.DBUSINESS
//  			    )
//  			    ,
//  			    d AS
//  			    (
//  			        SELECT
//  			            wb.*,
//  			            ROUND(wb.BILLS/ws.BILLS,4)        BILLS_per,
//  			            ROUND(wb.IGUESTNUM/ws.IGUESTNUM,4)IGUESTNUM_per,
//  			            ROUND(wb.NFOODAMT/wb.BILLS,2)     ac
//  			        FROM
//  			            wbase wb
//  			        INNER JOIN
//  			            wsum ws
//  			        ON
//  			            wb.WEEKINFO = ws.WEEKINFO
//  			    )
//  			    ,
//  			    e AS
//  			    (
//  			        SELECT
//  			            sb.*,
//  			            ROUND(sb.BILLS/ss.BILLS,4)        BILLS_per,
//  			            ROUND(sb.IGUESTNUM/ss.IGUESTNUM,4)IGUESTNUM_per,
//  			            ROUND(sb.NFOODAMT/sb.BILLS,2)     ac
//  			        FROM
//  			            sbase sb ,
//  			            ssum ss
//  			    )
//  			    ,
//  			    f AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            c
//  			        UNION ALL
//  			        SELECT
//  			            *
//  			        FROM
//  			            d
//  			        UNION ALL
//  			        SELECT
//  			            *
//  			        FROM
//  			            e
//  			    )
//  			    ,
//  			    g AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            WEEKINFO,
//  			            DAYINFO,
//  			            LISTAGG(CPERIOD_C||'AMT'||'|'||NFOODAMT ||','||CPERIOD_C||'BILLS'||'|'||BILLS ||','||
//  			            CPERIOD_C ||'BILLS_PER'||'|'||BILLS_PER ||','||CPERIOD_C||'IGUESTNUM'||'|'||IGUESTNUM
//  			            ||','||CPERIOD_C|| 'IGUESTNUM_PER'||'|'||IGUESTNUM_PER ||','||CPERIOD_C||'AC'||'|'||AC
//  			            , ',') WITHIN GROUP (ORDER BY CPERIOD_C) AS infos
//  			        FROM
//  			            (
//  			                SELECT
//  			                    *
//  			                FROM
//  			                    f)
//  			        GROUP BY
//  			            DBUSINESS,
//  			            WEEKINFO,
//  			            DAYINFO
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
//  			                	DECODE(DBUSINESS,NULL,DECODE(WEEKINFO,NULL,'合计',DECODE(DAYINFO,NULL,'周汇总',TO_CHAR
//  						            (DBUSINESS, 'YYYY-MM-DD'))),TO_CHAR(DBUSINESS, 'YYYY-MM-DD') ) DBUSINESS,
//  					            WEEKINFO,
//  					            DAYINFO,
//  					            infos
//  			                FROM
//  			                    g ) t
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
  String __sJT_2 = queryStr;
  int __sJT_3 = endRow;
  int __sJT_4 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TimeIncomeBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setInt(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:304^30*/
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
}/*@lineinfo:generated-code*/class TimeIncomeBean_SJProfileKeys 
{
  private static TimeIncomeBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TimeIncomeBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TimeIncomeBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.special.TimeIncomeBean_SJProfile0");
  }
}
