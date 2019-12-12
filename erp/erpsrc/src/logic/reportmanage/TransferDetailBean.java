/*@lineinfo:filename=TransferDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class TransferDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(TransferDetailBean.class);
	
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
    in_branch_idNdx = findColumn("in_branch_id");
    in_branchNdx = findColumn("in_branch");
    in_storage_idNdx = findColumn("in_storage_id");
    in_storageNdx = findColumn("in_storage");
    out_branch_idNdx = findColumn("out_branch_id");
    out_branchNdx = findColumn("out_branch");
    out_storage_idNdx = findColumn("out_storage_id");
    out_storageNdx = findColumn("out_storage");
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    category_nameNdx = findColumn("category_name");
    apply_countNdx = findColumn("apply_count");
    actual_countNdx = findColumn("actual_count");
    different_countNdx = findColumn("different_count");
    unit_priceNdx = findColumn("unit_price");
    pay_amtNdx = findColumn("pay_amt");
    form_timeNdx = findColumn("form_time");
    confirm_timeNdx = findColumn("confirm_time");
    statusNdx = findColumn("status");
  }
  public String in_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_branch_idNdx);
  }
  private int in_branch_idNdx;
  public String in_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_branchNdx);
  }
  private int in_branchNdx;
  public String in_storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_storage_idNdx);
  }
  private int in_storage_idNdx;
  public String in_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_storageNdx);
  }
  private int in_storageNdx;
  public String out_branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_branch_idNdx);
  }
  private int out_branch_idNdx;
  public String out_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_branchNdx);
  }
  private int out_branchNdx;
  public String out_storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storage_idNdx);
  }
  private int out_storage_idNdx;
  public String out_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storageNdx);
  }
  private int out_storageNdx;
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
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public Double apply_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(apply_countNdx);
  }
  private int apply_countNdx;
  public Double actual_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actual_countNdx);
  }
  private int actual_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
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
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public Date confirm_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(confirm_timeNdx);
  }
  private int confirm_timeNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:49^17*/
	
	/*@lineinfo:generated-code*//*@lineinfo:51^2*/

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
    apply_countNdx = findColumn("apply_count");
    actual_countNdx = findColumn("actual_count");
    different_countNdx = findColumn("different_count");
    pay_amtNdx = findColumn("pay_amt");
  }
  public Double apply_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(apply_countNdx);
  }
  private int apply_countNdx;
  public Double actual_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actual_countNdx);
  }
  private int actual_countNdx;
  public Double different_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(different_countNdx);
  }
  private int different_countNdx;
  public Double pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(pay_amtNdx);
  }
  private int pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:55^18*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("in_branch_id", "String");
		map.put("in_branch", "String");
		map.put("in_storage_id", "String");
		map.put("in_storage", "String");
		map.put("out_branch_id", "String");
		map.put("out_branch", "String");
		map.put("out_storage_id", "String");
		map.put("out_storage", "String");
		
		map.put("form_id", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("item_category", "String");
		map.put("category_name", "String"); 
		map.put("apply_count", "Number"); 
		map.put("actual_count", "Number"); 
		map.put("different_count", "Number"); 
		map.put("unit_price", "Number"); 
		map.put("pay_amt", "Number"); 
		map.put("form_time", "Date"); 
		map.put("confirm_time", "Date"); 
		map.put("status", "String"); 
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		
		map.put("in_branch_id", "h");
		map.put("in_branch", "h");
		map.put("in_storage_id", "h");
		map.put("in_storage", "h");
		map.put("out_branch_id", "h");
		map.put("out_branch", "h");
		map.put("out_storage_id", "h");
		map.put("out_storage", "h");
		
		map.put("form_id", "d");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		map.put("item_category", "d");
		map.put("category_name", "c");
		map.put("apply_count", "d"); 
		map.put("actual_count", "d"); 
		map.put("different_count", "d"); 
		map.put("unit_price", "d"); 
		map.put("pay_amt", "d"); 
		map.put("form_time", "h"); 
		map.put("confirm_time", "h"); 
		map.put("status", "s"); 
		
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:123^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_TRANSFER_HEADER") h
//  		INNER JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    h.FORM_ID = s.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T2_BRANCH") b
//  		ON
//  		    h.IN_BRANCH_ID = b.BRANCH_ID
//  		INNER JOIN
//  		    :Com_("D_T1_TRANSFER_DETAIL") d
//  		ON
//  		    h.FORM_ID = d.FORM_ID
//  		LEFT JOIN
//  		    :Com_("D_T2_ITEM_CATEGORY") c
//  		ON
//  		    d.ITEM_CATEGORY = c.CATEGORY_ID
//  		WHERE
//  		    1 = 1
//  		AND s.STATUS ='已审核' :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:149^40*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:160^3*/

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
//  			                	    d.FORM_ID,
//  			                	    d.ITEM_ID,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION,
//  			                	    d.ITEM_CATEGORY,
//  			                	    NVL(d.APPLY_COUNT,0)     APPLY_COUNT,
//  			                	    NVL(d.ACTUAL_COUNT,0)    ACTUAL_COUNT,
//  			                	    NVL(d.DIFFERENT_COUNT,0) DIFFERENT_COUNT,
//  			                	    NVL(d.UNIT_PRICE,0)      UNIT_PRICE,
//  			                	    NVL(d.PAY_AMT,0)         PAY_AMT,
//  			                	    h.form_time,
//  			                	    h.confirm_time,
//  			                	    h.in_branch_id,
//  			                	    h.in_branch,
//  			                	    h.in_storage_id,
//  			                	    h.in_storage,
//  			                	    h.out_branch_id,
//  			                	    h.out_branch,
//  			                	    h.out_storage_id,
//  			                	    h.out_storage,
//  			                	    c.CATEGORY_NAME,
//  			                	    '已审核' STATUS
//  			                	FROM
//  			                	    :Com_("D_T1_TRANSFER_HEADER") h
//  			                	INNER JOIN
//  			                	    :Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.IN_BRANCH_ID = b.BRANCH_ID
//  			                	INNER JOIN
//  			                	    :Com_("D_T1_TRANSFER_DETAIL") d
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    d.ITEM_CATEGORY = c.CATEGORY_ID
//  			                	WHERE
//  			                	    1 = 1
//                  	    		AND h.CONFIRM_TIME IS NOT NULL :queryStr:sortStr) t
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
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:213^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:225^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  				SUM( d.apply_count)     apply_count,
//  			    SUM( d.actual_count)    actual_count,
//  			    SUM( d.different_count) different_count,
//  			    SUM(d.pay_amt)          pay_amt
//          	FROM
//          	    :Com_("D_T1_TRANSFER_HEADER") h
//          	INNER JOIN
//          	    :Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	INNER JOIN
//          	    :Com_("D_T2_BRANCH") b
//          	ON
//          	    h.IN_BRANCH_ID = b.BRANCH_ID
//          	INNER JOIN
//          	    :Com_("D_T1_TRANSFER_DETAIL") d
//          	ON
//          	    h.FORM_ID = d.FORM_ID
//          	LEFT JOIN
//          	    :Com_("D_T2_ITEM_CATEGORY") c
//          	ON
//          	    d.ITEM_CATEGORY = c.CATEGORY_ID
//          	WHERE
//          	    1 = 1
//          	AND s.STATUS ='已审核' :queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T1_TRANSFER_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:252^47*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		while(sumIter.next()) {
			sumMap.put("apply_count", sumIter.apply_count());
			sumMap.put("actual_count", sumIter.actual_count());
			sumMap.put("different_count", sumIter.different_count());
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
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("item_category", headerIter.item_category());
			header.put("category_name", headerIter.category_name());
			header.put("apply_count", headerIter.apply_count());
			header.put("actual_count", headerIter.actual_count());
			header.put("different_count", headerIter.different_count());
			header.put("unit_price", headerIter.unit_price());
			header.put("pay_amt", headerIter.pay_amt());
			header.put("form_time", headerIter.form_time());
			header.put("confirm_time", headerIter.confirm_time());
			header.put("status", headerIter.status());
			
			header.put("in_branch_id", headerIter.in_branch_id());
			header.put("in_branch", headerIter.in_branch());
			header.put("in_storage_id", headerIter.in_storage_id());
			header.put("in_storage", headerIter.in_storage());
			header.put("out_branch_id", headerIter.out_branch_id());
			header.put("out_branch", headerIter.out_branch());
			header.put("out_storage_id", headerIter.out_storage_id());
			header.put("out_storage", headerIter.out_storage());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class TransferDetailBean_SJProfileKeys 
{
  private static TransferDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TransferDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TransferDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.TransferDetailBean_SJProfile0");
  }
}
