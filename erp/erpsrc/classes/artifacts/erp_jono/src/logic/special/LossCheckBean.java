/*@lineinfo:filename=LossCheckBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.special;

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

public class LossCheckBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(LossCheckBean.class);
	
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
    rownumberNdx = findColumn("rownumber");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    branch_nameNdx = findColumn("branch_name");
    branch_idNdx = findColumn("branch_id");
    item_idNdx = findColumn("item_id");
    item_priceNdx = findColumn("item_price");
    ylbsNdx = findColumn("ylbs");
    cpbsNdx = findColumn("cpbs");
    pdiNdx = findColumn("pdi");
    pdoNdx = findColumn("pdo");
    sshlNdx = findColumn("sshl");
    llkuNdx = findColumn("llku");
    actualNdx = findColumn("actual");
    theoryNdx = findColumn("theory");
    sshl2Ndx = findColumn("sshl2");
    theory_amtNdx = findColumn("theory_amt");
    actual_amtNdx = findColumn("actual_amt");
  }
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
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
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public String branch_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_nameNdx);
  }
  private int branch_nameNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double ylbs() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(ylbsNdx);
  }
  private int ylbsNdx;
  public Double cpbs() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(cpbsNdx);
  }
  private int cpbsNdx;
  public Double pdi() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pdiNdx);
  }
  private int pdiNdx;
  public Double pdo() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pdoNdx);
  }
  private int pdoNdx;
  public Double sshl() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sshlNdx);
  }
  private int sshlNdx;
  public Double llku() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(llkuNdx);
  }
  private int llkuNdx;
  public Double actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actualNdx);
  }
  private int actualNdx;
  public Double theory() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(theoryNdx);
  }
  private int theoryNdx;
  public Double sshl2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sshl2Ndx);
  }
  private int sshl2Ndx;
  public Double theory_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(theory_amtNdx);
  }
  private int theory_amtNdx;
  public Double actual_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actual_amtNdx);
  }
  private int actual_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:49^2*/

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

/*@lineinfo:user-code*//*@lineinfo:51^3*/
	
	
	public Integer count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String[] strs = queryStr.split("\\|_\\|"); 
		TotalIter totalIter = null;
		Integer total = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:62^3*/

//  ************************************************************
//  #sql [myCtx] totalIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            io.ITEM_ID
//  			        FROM
//  			            :Com_("D_T0_STORAGE_IN_OUT") io
//  			        INNER JOIN
//  			            :Com_("D_T2_ITEM_META") m
//  			        ON
//  			            io.ITEM_ID = m.ITEM_ID
//  			        WHERE
//  			            :strs[1]
//  			        GROUP BY
//  			            io.ITEM_ID
//  			    )
//  			SELECT
//  			    COUNT(*) totals
//  			FROM
//  			    a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = strs[1];
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossCheckBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:82^9*/
    
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
		
		String[] strs = queryStr.split("\\|_\\|");
		
		/*@lineinfo:generated-code*//*@lineinfo:100^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT
//  			            h.BRANCH_ID,
//  			            d.ITEM_ID,
//  			            SUM(NVL(d.ITEM_COUNT_PLAN,0)) ITEM_COUNT_PLAN
//  			        FROM
//  			        	:Com_("D_T1_SELF_SEMIS_HEADER") h 
//  			        INNER JOIN
//  			        	:Com_("D_T1_SELF_SEMIS_DETAIL") d 
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        INNER JOIN
//  			        	:Com_("D_T2_ITEM_META") m 
//  			        ON
//  			            d.ITEM_ID = m.ITEM_ID
//  			        WHERE
//  			        	:strs[0]
//  			        GROUP BY
//  			            h.BRANCH_ID,
//  			            d.ITEM_ID
//  			    )
//  			    ,
//  			    b AS
//  			    (
//  			        SELECT
//  			            io.BRANCH_ID,
//  			            io.ITEM_ID,
//  			            SUM(DECODE(io.REASON,'YLBS',io.ITEM_COUNT_OUT,0)) ylbs,
//  			            SUM(DECODE(io.REASON,'CPBS',io.ITEM_COUNT_OUT,0)) cpbs,
//  			            SUM(DECODE(io.REASON,'PD',io.ITEM_COUNT_IN,0))    pdi,
//  			            SUM(DECODE(io.REASON,'PD',io.ITEM_COUNT_OUT,0))   pdo,
//  			            SUM(DECODE(io.REASON,'SSHL',io.ITEM_COUNT_OUT,0)) sshl,
//  			            SUM(DECODE(io.REASON,'LLKU',io.ITEM_COUNT_OUT,0)) llku,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ('YLBS',
//  			                                       'CPBS',
//  			                                       'PD',
//  			                                       'SSHL',
//  			                                       'LLKU')
//  			                    THEN NVL(ITEM_COUNT_IN,0)+NVL(ITEM_COUNT_OUT,0)
//  			                    ELSE 0
//  			                END ) actual ,
//  			            SUM(
//  			                CASE
//  			                    WHEN io.REASON IN ('SSHL',
//  			                                       'LLKU')
//  			                    THEN NVL(ITEM_COUNT_OUT,0)
//  			                    ELSE 0
//  			                END ) theory
//  			        FROM
//  			        	:Com_("D_T0_STORAGE_IN_OUT") io 
//  			        INNER JOIN
//  			        	:Com_("D_T2_ITEM_META") m 
//  			        ON
//  			            io.ITEM_ID = m.ITEM_ID
//  			        WHERE
//  			        	:strs[1]
//  			        GROUP BY
//  			            io.BRANCH_ID,
//  			            io.ITEM_ID
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
//  			                    NVL(p.ITEM_PRICE,0) ITEM_PRICE,
//  			                    m.ITEM_NAME,
//  			                    m.ITEM_DIMENSION,
//  			                    m.ITEM_SPECIFICATION,
//  			                    m.CATEGORY_ID,
//  			                    b2.BRANCH_NAME,
//  			                    b.*,
//  			                    NVL(b.sshl,0) - NVL(ITEM_COUNT_PLAN ,0) sshl2,
//  			                    THEORY * NVL(p.ITEM_PRICE,0)            THEORY_amt,
//  			                    ACTUAL * NVL(p.ITEM_PRICE,0)            ACTUAL_amt
//  			                FROM
//  			                    b
//  			                INNER JOIN
//  			                	:Com_("D_T2_BRANCH") b2 
//  			                ON
//  			                    b.BRANCH_ID = b2.BRANCH_ID
//  			                INNER JOIN
//  			                	:Com_("D_T2_ITEM_META") m 
//  			                ON
//  			                    b.item_id = m.ITEM_ID
//  			                LEFT JOIN
//  			                    a
//  			                ON
//  			                    b.BRANCH_ID = a.BRANCH_ID
//  			                AND b.ITEM_ID = a.ITEM_ID
//  			                LEFT JOIN
//  			                :Com_("D_T2_ITEM_PRICE") p
//  			                ON
//  			                    b.item_id = p.ITEM_ID
//  			                AND p.IS_CURRENT = 1
//  			                AND p.PRICE_TYPE = 'BENCHMARK'
//  			                ORDER BY
//  			                    1,2 ) t
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
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = Com_("D_T1_SELF_SEMIS_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = strs[0];
  String __sJT_5 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_6 = Com_("D_T2_ITEM_META");
  String __sJT_7 = strs[1];
  String __sJT_8 = Com_("D_T2_BRANCH");
  String __sJT_9 = Com_("D_T2_ITEM_META");
  String __sJT_10 = Com_("D_T2_ITEM_PRICE");
  int __sJT_11 = endRow;
  int __sJT_12 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossCheckBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:211^30*/
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
			header.put("rownumber", headerIter.rownumber());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("branch_name", headerIter.branch_name());
			header.put("branch_id", headerIter.branch_id());
			header.put("item_id", headerIter.item_id());
			header.put("item_price", headerIter.item_price());
			header.put("ylbs", headerIter.ylbs());
			header.put("cpbs", headerIter.cpbs());
			header.put("pdi", headerIter.pdi());
			header.put("pdo", headerIter.pdo());
			header.put("sshl", headerIter.sshl());
			header.put("llku", headerIter.llku());
			header.put("actual", headerIter.actual());
			header.put("theory", headerIter.theory());
			header.put("sshl2", headerIter.sshl2());
			header.put("theory_amt", headerIter.theory_amt());
			header.put("actual_amt", headerIter.actual_amt());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class LossCheckBean_SJProfileKeys 
{
  private static LossCheckBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new LossCheckBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private LossCheckBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.special.LossCheckBean_SJProfile0");
  }
}
