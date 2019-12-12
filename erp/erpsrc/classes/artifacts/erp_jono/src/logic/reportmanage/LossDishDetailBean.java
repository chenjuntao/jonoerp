/*@lineinfo:filename=LossDishDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class LossDishDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(LossDishDetailBean.class);
	
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
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_id2Ndx = findColumn("item_id2");
    item_nameNdx = findColumn("item_name");
    item_name2Ndx = findColumn("item_name2");
    item_dimensionNdx = findColumn("item_dimension");
    item_countNdx = findColumn("item_count");
    item_count2Ndx = findColumn("item_count2");
    unit_priceNdx = findColumn("unit_price");
    pay_amtNdx = findColumn("pay_amt");
    loss_timeNdx = findColumn("loss_time");
    audit_timeNdx = findColumn("audit_time");
    reasonNdx = findColumn("reason");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    loss_branch_idNdx = findColumn("loss_branch_id");
    loss_branchNdx = findColumn("loss_branch");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String item_id2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_id2Ndx);
  }
  private int item_id2Ndx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String item_name2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_name2Ndx);
  }
  private int item_name2Ndx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double item_count2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count2Ndx);
  }
  private int item_count2Ndx;
  public Double unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_priceNdx);
  }
  private int unit_priceNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
  public Date loss_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(loss_timeNdx);
  }
  private int loss_timeNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(reasonNdx);
  }
  private int reasonNdx;
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public String loss_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(loss_branch_idNdx);
  }
  private int loss_branch_idNdx;
  public String loss_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(loss_branchNdx);
  }
  private int loss_branchNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^24*/
	
	/*@lineinfo:generated-code*//*@lineinfo:45^2*/

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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^18*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("item_id", "String");
		map.put("item_id2", "String");
		map.put("item_name", "String");
		map.put("item_name2", "String");
		map.put("item_dimension", "String");
		map.put("item_count", "Number"); 
		map.put("item_count2", "Number"); 
		map.put("loss_time", "Date"); 
		map.put("audit_time", "Date"); 
		map.put("reason", "String"); 
		map.put("storage_id", "String"); 
		map.put("storage", "String"); 
		map.put("loss_branch_id", "String"); 
		map.put("loss_branch", "String"); 
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "d");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		map.put("item_count", "d"); 
		map.put("loss_time", "h"); 
		map.put("audit_time", "h"); 
		map.put("storage_id", "h"); 
		map.put("storage", "h"); 
		map.put("loss_branch_id", "h"); 
		map.put("loss_branch", "h"); 
		map.put("reason", "d"); 
		
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
//  		    FROM
//             :Com_("D_T1_LOSS_HEADER") h
//          INNER JOIN
//             :Com_("D_T1_LOSS_DETAIL") d
//          ON
//              d.FORM_ID = h.FORM_ID
//          AND h.AUDIT_TIME IS NOT NULL
//          LEFT JOIN
//             :Com_("D_T2_BRANCH") b
//          ON
//              h.LOSS_BRANCH_ID = b.BRANCH_ID
//          LEFT JOIN
//             :Com_("D_T2_THERAPY") t
//          ON
//              d.ITEM_ID = t.THERAPY_ID
//          AND t.OWNER = 'RESTAURANT'
//          LEFT JOIN
//             :Com_("D_T2_ITEM_PRICE") p
//          ON
//              t.ITEM_ID = p.ITEM_ID
//          AND p.IS_CURRENT =1
//          AND p.PRICE_TYPE = 'BENCHMARK'
//          WHERE
//          	 1 = 1  :queryStr  };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_THERAPY");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDishDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:122^36*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:133^3*/

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
//  			                    SELECT
//  			                        d.FORM_ID,
//  			                        d.ITEM_ID,
//  			                        d.ITEM_NAME,
//  			                        d.ITEM_DIMENSION,
//  			                        d.ITEM_COUNT,
//  			                        d.REASON,
//  			                        h.loss_type,
//  			                        h.storage_id,
//  			                        h.storage,
//  			                        h.loss_branch_id,
//  			                        h.loss_branch,
//  			                        h.loss_time,
//  			                        h.audit_time,
//  			                        t.ITEM_ID                                        item_id2,
//  			                        t.ITEM_NAME                                      item_name2,
//  			                        t.ITEM_GROSS_COUNT*d.item_count                       item_count2,
//  			                        p.ITEM_PRICE                                          UNIT_PRICE,
//  			                        ROUND(t.ITEM_GROSS_COUNT*d.item_count*p.ITEM_PRICE,2) PAY_AMT
//  			                    FROM
//  			                       :Com_("D_T1_LOSS_HEADER") h
//  			                    INNER JOIN
//  			                       :Com_("D_T1_LOSS_DETAIL") d
//  			                    ON
//  			                        d.FORM_ID = h.FORM_ID
//  			                    AND h.AUDIT_TIME IS NOT NULL
//  			                    LEFT JOIN
//  			                       :Com_("D_T2_BRANCH") b
//  			                    ON
//  			                        h.LOSS_BRANCH_ID = b.BRANCH_ID
//  			                    LEFT JOIN
//  			                       :Com_("D_T2_THERAPY") t
//  			                    ON
//  			                        d.ITEM_ID = t.THERAPY_ID
//  			                    AND t.OWNER = 'RESTAURANT'
//  			                    LEFT JOIN
//  			                       :Com_("D_T2_ITEM_PRICE") p
//  			                    ON
//  			                        t.ITEM_ID = p.ITEM_ID
//  			                    AND p.IS_CURRENT =1
//  			                    AND p.PRICE_TYPE = 'BENCHMARK'
//  			                    WHERE
//  			                        1 = 1 :queryStr :sortStr) t
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
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_THERAPY");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = queryStr;
  String __sJT_7 = sortStr;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDishDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:189^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:203^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    SUM( d.ITEM_COUNT) ITEM_COUNT,
//  			    SUM(t.ITEM_GROSS_COUNT*d.item_count) ITEM_COUNT2,
//  			    SUM(round(t.ITEM_GROSS_COUNT*d.item_count*p.ITEM_PRICE,2))     PAY_AMT
//  		    FROM
//                 :Com_("D_T1_LOSS_HEADER") h
//              INNER JOIN
//                 :Com_("D_T1_LOSS_DETAIL") d
//              ON
//                  d.FORM_ID = h.FORM_ID
//              AND h.AUDIT_TIME IS NOT NULL
//              LEFT JOIN
//                 :Com_("D_T2_BRANCH") b
//              ON
//                  h.LOSS_BRANCH_ID = b.BRANCH_ID
//              LEFT JOIN
//                 :Com_("D_T2_THERAPY") t
//              ON
//                  d.ITEM_ID = t.THERAPY_ID
//              AND t.OWNER = 'RESTAURANT'
//              LEFT JOIN
//                 :Com_("D_T2_ITEM_PRICE") p
//              ON
//                  t.ITEM_ID = p.ITEM_ID
//              AND p.IS_CURRENT =1
//              AND p.PRICE_TYPE = 'BENCHMARK'
//              WHERE
//              	 1 = 1  :queryStr  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_HEADER");
  String __sJT_2 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_THERAPY");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossDishDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:231^40*/
		
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
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("form_id", headerIter.form_id());
			header.put("item_id", headerIter.item_id());
			header.put("item_id2", headerIter.item_id2());
			header.put("item_name", headerIter.item_name());
			header.put("item_name2", headerIter.item_name2());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_count", headerIter.item_count());
			header.put("item_count2", headerIter.item_count2());
			header.put("unit_price", headerIter.unit_price());
			header.put("pay_amt", headerIter.pay_amt());
			header.put("loss_time", headerIter.loss_time());
			header.put("audit_time", headerIter.audit_time());
			header.put("reason", headerIter.reason());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage", headerIter.storage());
			header.put("loss_branch_id", headerIter.loss_branch_id());
			header.put("loss_branch", headerIter.loss_branch());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class LossDishDetailBean_SJProfileKeys 
{
  private static LossDishDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new LossDishDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private LossDishDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.LossDishDetailBean_SJProfile0");
  }
}
