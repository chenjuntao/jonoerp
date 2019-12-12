/*@lineinfo:filename=PurchasingHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class PurchasingHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PurchasingHeaderBean.class);
	
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
    requesterNdx = findColumn("requester");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    category_idNdx = findColumn("category_id");
    item_countNdx = findColumn("item_count");
    category_nameNdx = findColumn("category_name");
    pay_amtNdx = findColumn("pay_amt");
    item_unit_priceNdx = findColumn("item_unit_price");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    different_amtNdx = findColumn("different_amt");
  }
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
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
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double receive_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_priceNdx);
  }
  private int receive_priceNdx;
  public Double receive_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtNdx);
  }
  private int receive_amtNdx;
  public Double different_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_amtNdx);
  }
  private int different_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^24*/
	
	/*@lineinfo:generated-code*//*@lineinfo:41^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SumIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SumIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_countNdx = findColumn("item_count");
    pay_amtNdx = findColumn("pay_amt");
    receive_amtNdx = findColumn("receive_amt");
  }
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Double receive_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtNdx);
  }
  private int receive_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:44^22*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("requester_id", "String");
		map.put("requester", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("category_id", "String");
		map.put("category_name", "String"); 
		map.put("item_count", "Number"); 
		map.put("item_unit_price", "Number"); 
		map.put("pay_amt", "Number"); 
		map.put("receive_price", "Number");
		map.put("receive_amt", "Number");
		map.put("different_amt", "Number");
		
		return map;
	}
	
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("item_id", "d");
		map.put("item_name", "m");
		map.put("item_dimension", "m");
		map.put("item_specification", "m");
		map.put("category_id", "m");
		map.put("category_name", "c");
		map.put("item_count", "d"); 
		map.put("item_unit_price", "pb"); 
		map.put("pay_amt", "d"); 
		map.put("receive_price",  "pp");
		map.put("receive_amt",  "d");
		map.put("different_amt",  "d");
		map.put("provider", "h"); 
		map.put("requester", "h"); 
		map.put("requester_id", "h"); 
		
		return map;
	}
	
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:94^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (SELECT
//  		    		 COUNT(*)
//              	FROM
//              	    :Com_("D_T1_PURCHASING_HEADER") h
//              	INNER JOIN
//              	    :Com_("D_T0_FORM_STATUS") s
//              	ON
//              	    h.FORM_ID = s.FORM_ID
//              	AND s.IS_CURRENT = 1
//              	INNER JOIN
//              	    :Com_("D_T2_BRANCH") b
//              	ON
//              	    h.REQUESTER_ID = b.BRANCH_ID
//              	INNER JOIN
//              	    :Com_("D_T1_PURCHASING_DETAIL") d
//              	ON
//              	    h.FORM_ID = d.FORM_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_META") m
//              	ON
//              	    m.ITEM_ID = d.ITEM_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_CATEGORY") c
//              	ON
//              	    c.CATEGORY_ID = d.ITEM_CATEGORY
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_PRICE") pp
//              	ON
//              	    pp.PRICE_TYPE = 'PURCHASE'
//              	AND pp.ITEM_ID = d.ITEM_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_PRICE") pb
//              	ON
//              	    pb.PRICE_TYPE = 'BENCHMARK'
//              	AND pb.ITEM_ID = d.ITEM_ID
//              	WHERE
//              	    1 = 1
//  	    		AND s.STATUS in ('已审核','已结案') :queryStr
//              	GROUP BY
//              	    h.REQUESTER_ID,
//              	    h.REQUESTER,
//              	    d.ITEM_ID,
//              	    m.ITEM_NAME,
//              	    m.ITEM_DIMENSION,
//              	    m.ITEM_SPECIFICATION,
//              	    m.CATEGORY_ID,
//              	    pp.ITEM_PRICE,
//              	    pb.ITEM_PRICE,
//              	    c.CATEGORY_NAME) a };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = Com_("D_T2_ITEM_PRICE");
  String __sJT_9 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingHeaderBean_SJProfileKeys.getKey(0), 0);
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
    total = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:148^36*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:159^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM 
//  			                (SELECT
//  			                	    h.REQUESTER_ID,
//  			                	    h.REQUESTER,
//  			                	    d.ITEM_ID,
//  			                	    m.ITEM_NAME,
//  			                	    m.ITEM_DIMENSION,
//  			                	    m.ITEM_SPECIFICATION,
//  			                	    m.CATEGORY_ID,
//  			                	    c.CATEGORY_NAME,
//  			                	    NVL(pp.ITEM_PRICE,0)                                  RECEIVE_PRICE,
//  			                	    NVL(pb.ITEM_PRICE,0)                                  ITEM_UNIT_PRICE,
//  			                	    ROUND( SUM(NVL(ITEM_COUNT,0)),2)                               ITEM_COUNT,
//  			                	    ROUND( SUM(NVL(ITEM_COUNT,0)),2)*NVL(pp.ITEM_PRICE,0)                        RECEIVE_AMT,
//  			                	    ROUND( SUM(NVL(ITEM_COUNT,0)),2)*NVL(pb.ITEM_PRICE,0)                        PAY_AMT,
//  			                	    ROUND( SUM(NVL(ITEM_COUNT,0)),2)*(NVL(pb.ITEM_PRICE,0)-NVL(pp.ITEM_PRICE,0)) DIFFERENT_AMT
//  			                	FROM
//  			                	    :Com_("D_T1_PURCHASING_HEADER") h
//  			                	INNER JOIN
//  			                	    :Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.REQUESTER_ID = b.BRANCH_ID
//  			                	INNER JOIN
//  			                	    :Com_("D_T1_PURCHASING_DETAIL") d
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_META") m
//  			                	ON
//  			                	    m.ITEM_ID = d.ITEM_ID
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    c.CATEGORY_ID = d.ITEM_CATEGORY
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_PRICE") pp
//  			                	ON
//  			                	    pp.PRICE_TYPE = 'PURCHASE'
//  			                	AND pp.ITEM_ID = d.ITEM_ID
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_PRICE") pb
//  			                	ON
//  			                	    pb.PRICE_TYPE = 'BENCHMARK'
//  			                	AND pb.ITEM_ID = d.ITEM_ID
//  			                	WHERE
//  			                	    1 = 1
//                  	    		AND h.AUDIT_TIME IS NOT NULL :queryStr
//  			                	GROUP BY
//  			                	    h.REQUESTER_ID,
//  			                	    h.REQUESTER,
//  			                	    d.ITEM_ID,
//  			                	    m.ITEM_NAME,
//  			                	    m.ITEM_DIMENSION,
//  			                	    m.ITEM_SPECIFICATION,
//  			                	    m.CATEGORY_ID,
//  			                	    pp.ITEM_PRICE,
//  			                	    pb.ITEM_PRICE,
//  			                	    c.CATEGORY_NAME :sortStr) t
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
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = queryStr;
  String __sJT_9 = sortStr;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingHeaderBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setInt(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:228^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:240^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  		    round(SUM (ITEM_COUNT),2) ITEM_COUNT,
//  		    round(SUM(PAY_AMT),2)     PAY_AMT,
//  		    round(SUM(RECEIVE_AMT),2) RECEIVE_AMT
//  		FROM
//  		    (SELECT
//  	    		    round(SUM (ITEM_COUNT),2) ITEM_COUNT,
//  		  		    round(SUM(PAY_AMT),2)     PAY_AMT,
//  		  		    round(SUM(RECEIVE_AMT),2) RECEIVE_AMT
//              	FROM
//              	    :Com_("D_T1_PURCHASING_HEADER") h
//              	INNER JOIN
//              	    :Com_("D_T0_FORM_STATUS") s
//              	ON
//              	    h.FORM_ID = s.FORM_ID
//              	AND s.IS_CURRENT = 1
//              	INNER JOIN
//              	    :Com_("D_T2_BRANCH") b
//              	ON
//              	    h.REQUESTER_ID = b.BRANCH_ID
//              	INNER JOIN
//              	    :Com_("D_T1_PURCHASING_DETAIL") d
//              	ON
//              	    h.FORM_ID = d.FORM_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_META") m
//              	ON
//              	    m.ITEM_ID = d.ITEM_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_CATEGORY") c
//              	ON
//              	    c.CATEGORY_ID = d.ITEM_CATEGORY
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_PRICE") pp
//              	ON
//              	    pp.PRICE_TYPE = 'PURCHASE'
//              	AND pp.ITEM_ID = d.ITEM_ID
//              	LEFT JOIN
//              	    :Com_("D_T2_ITEM_PRICE") pb
//              	ON
//              	    pb.PRICE_TYPE = 'BENCHMARK'
//              	AND pb.ITEM_ID = d.ITEM_ID
//              	WHERE
//              	    1 = 1
//  	    		AND s.STATUS in ('已审核','已结案'):queryStr
//              	GROUP BY
//              	    h.REQUESTER_ID,
//              	    h.REQUESTER,
//              	    d.ITEM_ID,
//              	    m.ITEM_NAME,
//              	    m.ITEM_DIMENSION,
//              	    m.ITEM_SPECIFICATION,
//              	    m.CATEGORY_ID,
//              	    pp.ITEM_PRICE,
//              	    pb.ITEM_PRICE,
//              	    c.CATEGORY_NAME ) a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = Com_("D_T2_ITEM_PRICE");
  String __sJT_9 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingHeaderBean_SJProfileKeys.getKey(0), 2);
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
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      sumIter = new SumIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:296^37*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("item_count", sumIter.item_count());
			sumMap.put("pay_amt", sumIter.pay_amt());
			sumMap.put("receive_amt", sumIter.receive_amt());
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("category_id", headerIter.category_id());
			header.put("item_count", headerIter.item_count());
			header.put("item_unit_price", headerIter.item_unit_price());
			header.put("pay_amt", headerIter.pay_amt());
			header.put("receive_price", headerIter.receive_price());
			header.put("receive_amt", headerIter.receive_amt());
			header.put("different_amt", headerIter.different_amt());
			header.put("category_name", headerIter.category_name());
			header.put("requester", headerIter.requester());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class PurchasingHeaderBean_SJProfileKeys 
{
  private static PurchasingHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PurchasingHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PurchasingHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.PurchasingHeaderBean_SJProfile0");
  }
}
