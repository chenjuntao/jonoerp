/*@lineinfo:filename=DepartmentIncomeBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.special;

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

public class DepartmentIncomeBean extends ConnectionPool {
	public static Logger log = Logger.getLogger(DepartmentIncomeBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:26^2*/

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
    id_1011Ndx = findColumn("id_1011");
    id_1011_perNdx = findColumn("id_1011_per");
    chineseNdx = findColumn("chinese");
    chinese_perNdx = findColumn("chinese_per");
    id_1017Ndx = findColumn("id_1017");
    id_1017_perNdx = findColumn("id_1017_per");
    id_1013Ndx = findColumn("id_1013");
    id_1013_perNdx = findColumn("id_1013_per");
    id_1014Ndx = findColumn("id_1014");
    id_1014_perNdx = findColumn("id_1014_per");
    id_2024Ndx = findColumn("id_2024");
    id_2024_perNdx = findColumn("id_2024_per");
    id_2029Ndx = findColumn("id_2029");
    id_2029_perNdx = findColumn("id_2029_per");
    drinkNdx = findColumn("drink");
    drink_perNdx = findColumn("drink_per");
    westNdx = findColumn("west");
    west_perNdx = findColumn("west_per");
    otherNdx = findColumn("other");
    other_perNdx = findColumn("other_per");
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
  public Double id_1011() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_1011Ndx);
  }
  private int id_1011Ndx;
  public Double id_1011_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_1011_perNdx);
  }
  private int id_1011_perNdx;
  public Double chinese() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(chineseNdx);
  }
  private int chineseNdx;
  public Double chinese_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(chinese_perNdx);
  }
  private int chinese_perNdx;
  public Double id_1017() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_1017Ndx);
  }
  private int id_1017Ndx;
  public Double id_1017_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_1017_perNdx);
  }
  private int id_1017_perNdx;
  public Double id_1013() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_1013Ndx);
  }
  private int id_1013Ndx;
  public Double id_1013_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_1013_perNdx);
  }
  private int id_1013_perNdx;
  public Double id_1014() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_1014Ndx);
  }
  private int id_1014Ndx;
  public Double id_1014_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_1014_perNdx);
  }
  private int id_1014_perNdx;
  public Double id_2024() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_2024Ndx);
  }
  private int id_2024Ndx;
  public Double id_2024_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_2024_perNdx);
  }
  private int id_2024_perNdx;
  public Double id_2029() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_2029Ndx);
  }
  private int id_2029Ndx;
  public Double id_2029_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(id_2029_perNdx);
  }
  private int id_2029_perNdx;
  public Double drink() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(drinkNdx);
  }
  private int drinkNdx;
  public Double drink_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(drink_perNdx);
  }
  private int drink_perNdx;
  public Double west() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(westNdx);
  }
  private int westNdx;
  public Double west_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(west_perNdx);
  }
  private int west_perNdx;
  public Double other() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(otherNdx);
  }
  private int otherNdx;
  public Double other_per() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(other_perNdx);
  }
  private int other_perNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:50^3*/
	
	
	/*@lineinfo:generated-code*//*@lineinfo:53^2*/

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

/*@lineinfo:user-code*//*@lineinfo:55^3*/
	
	public Integer count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		TotalIter totalIter = null;
		Integer total = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:64^3*/

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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DepartmentIncomeBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:90^9*/
    
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
		/*@lineinfo:generated-code*//*@lineinfo:106^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            b.DBUSINESS,
//  			            bs.clitcls_c,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C = '3036'
//  			                    THEN -bs.NAMT
//  			                    ELSE 0
//  			                END ) amt1,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.ERETSENDFLAG = '赠送'
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) amt2,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C = '1011'
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) id_1011,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C IN ('1015',
//  			                                          '1016',
//  			                                          '1012',
//  			                                          '2023')
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) chinese,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C ='1017'
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) id_1017,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C ='1013'
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) id_1013,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C ='1014'
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) id_1014,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C ='2024'
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) id_2024,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C ='2029'
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) id_2029,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C IN ('2026',
//  			                                          '2027',
//  			                                          '2028' )
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) drink,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C IN ('2022',
//  			                                          '2025',
//  			                                          '2030',
//  			                                          '1018',
//  			                                          '2021')
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) west,
//  			            SUM(
//  			                CASE
//  			                    WHEN bs.CLITCLS_C IN ('3032',
//  			                                          '3033')
//  			                    THEN bs.NAMT
//  			                    ELSE 0
//  			                END ) other
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
//  			            bs.clitcls_c
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            DBUSINESS,
//  			            SUM(AMT1)    AMT1,
//  			            SUM(AMT2)    AMT2,
//  			            SUM(ID_1011) ID_1011,
//  			            SUM(CHINESE) CHINESE,
//  			            SUM(ID_1017) ID_1017,
//  			            SUM(ID_1013) ID_1013,
//  			            SUM(ID_1014) ID_1014,
//  			            SUM(ID_2024) ID_2024,
//  			            SUM(ID_2029) ID_2029,
//  			            SUM(DRINK)   DRINK,
//  			            SUM(WEST)    WEST,
//  			            SUM(OTHER)   OTHER
//  			        FROM
//  			            a
//  			        GROUP BY
//  			            DBUSINESS
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT
//  			            b.DBUSINESS,
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
//  			            b.DBUSINESS
//  			    )
//  			    ,
//  			    d AS
//  			    (
//  			        SELECT
//  			            TO_CHAR(b.DBUSINESS , 'iw') weekinfo,
//  			            TO_CHAR(b.DBUSINESS,'day')  dayinfo,
//  			            b.*,
//  			            c.NPAYAMT,
//  			            amt1+amt2+c.NPAYAMT sumAmt
//  			        FROM
//  			            b
//  			        LEFT JOIN
//  			            c
//  			        ON
//  			            b.DBUSINESS = c.DBUSINESS
//  			    )
//  			    ,
//  			    e AS
//  			    (
//  			        SELECT
//  			            DECODE(DBUSINESS,NULL,DECODE(WEEKINFO,NULL,'合计',DECODE(DAYINFO,NULL,'周汇总',TO_CHAR
//  			            (DBUSINESS, 'YYYY-MM-DD'))),TO_CHAR(DBUSINESS, 'YYYY-MM-DD') ) DBUSINESS,
//  			            DAYINFO,
//  			            WEEKINFO,
//  			            SUM( ID_1011) ID_1011 ,
//  			            SUM( CHINESE) CHINESE,
//  			            SUM(ID_1017)  ID_1017 ,
//  			            SUM(ID_1013)  ID_1013 ,
//  			            SUM(ID_1014)  ID_1014 ,
//  			            SUM(ID_2024)  ID_2024,
//  			            SUM( ID_2029) ID_2029 ,
//  			            SUM(DRINK)    DRINK ,
//  			            SUM(WEST)     WEST ,
//  			            SUM(OTHER)    OTHER ,
//  			            SUM(sumAmt)   sumAmt
//  			        FROM
//  			            d
//  			        GROUP BY
//  			            GROUPING SETS ((DBUSINESS, DAYINFO,WEEKINFO,ID_1011, CHINESE, ID_1017, ID_1013, ID_1014
//  			            , ID_2024, ID_2029, DRINK, WEST, OTHER , sumAmt),WEEKINFO,NULL)
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
//  			                    DBUSINESS,
//  			                    DAYINFO,
//  			                    WEEKINFO,
//  			                    ID_1011 ,
//  			                    ROUND(ID_1011/sumAmt,4)ID_1011_per,
//  			                    
//  			                    CHINESE,
//  			                    ROUND(CHINESE/sumAmt,4)CHINESE_per,
//  			                    
//  			                    ID_1017 ,
//  			                    ROUND(ID_1017/sumAmt,4)ID_1017_per,
//  			                    
//  			                    ID_1013 ,
//  			                    ROUND(ID_1013/sumAmt,4)ID_1013_per,
//  			                    
//  			                    ID_1014 ,
//  			                    ROUND(ID_1014/sumAmt,4) ID_1014_per,
//  			                    
//  			                    ID_2024,
//  			                    ROUND(ID_2024/sumAmt,4)ID_2024_per,
//  			                    
//  			                    ID_2029 ,
//  			                    ROUND(ID_2029/sumAmt,4)ID_2029_per,
//  			                    
//  			                    DRINK ,
//  			                    ROUND(DRINK/sumAmt,4)DRINK_per,
//  			                    
//  			                    WEST ,
//  			                    ROUND(WEST/sumAmt,4)WEST_per,
//  			                    
//  			                    OTHER ,
//  			                    ROUND(OTHER/sumAmt,4)OTHER_per,
//  			                    sumAmt
//  			                FROM
//  			                    e) t
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DepartmentIncomeBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:347^30*/
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
			header.put("id_1011", headerIter.id_1011());
			header.put("id_1011_per", headerIter.id_1011_per());
			header.put("chinese", headerIter.chinese());
			header.put("chinese_per", headerIter.chinese_per());
			header.put("id_1017", headerIter.id_1017());
			header.put("id_1017_per", headerIter.id_1017_per());
			header.put("id_1013", headerIter.id_1013());
			header.put("id_1013_per", headerIter.id_1013_per());
			header.put("id_1014", headerIter.id_1014());
			header.put("id_1014_per", headerIter.id_1014_per());
			header.put("id_2024", headerIter.id_2024());
			header.put("id_2024_per", headerIter.id_2024_per());
			header.put("id_2029", headerIter.id_2029());
			header.put("id_2029_per", headerIter.id_2029_per());
			header.put("drink", headerIter.drink());
			header.put("drink_per", headerIter.drink_per());
			header.put("west", headerIter.west());
			header.put("west_per", headerIter.west_per());
			header.put("other", headerIter.other());
			header.put("other_per", headerIter.other_per());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class DepartmentIncomeBean_SJProfileKeys 
{
  private static DepartmentIncomeBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DepartmentIncomeBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DepartmentIncomeBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.special.DepartmentIncomeBean_SJProfile0");
  }
}
