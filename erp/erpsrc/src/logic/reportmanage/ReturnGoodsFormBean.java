/*@lineinfo:filename=ReturnGoodsFormBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

//餐厅采购退货 物流中心采购
public class ReturnGoodsFormBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ReturnGoodsFormBean.class);
	
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
    form_idNdx = findColumn("form_id");
    form_ref_idNdx = findColumn("form_ref_id");
    input_departmentNdx = findColumn("input_department");
    storageNdx = findColumn("storage");
    returnerNdx = findColumn("returner");
    input_timeNdx = findColumn("input_time");
    return_timeNdx = findColumn("return_time");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    statusNdx = findColumn("status");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String input_department() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_departmentNdx);
  }
  private int input_departmentNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public String returner() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(returnerNdx);
  }
  private int returnerNdx;
  public Date input_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(input_timeNdx);
  }
  private int input_timeNdx;
  public Date return_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(return_timeNdx);
  }
  private int return_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public String max_pay_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(max_pay_itemNdx);
  }
  private int max_pay_itemNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^3*/
	
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
    all_pay_amtNdx = findColumn("all_pay_amt");
  }
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^22*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("form_ref_id", "String");
		map.put("input_department", "String");
		map.put("storage", "String");
		map.put("returner", "String");
		map.put("input_time", "Date"); 
		map.put("return_time", "Date"); 
		map.put("form_note", "String"); 
		map.put("all_pay_amt", "Number");
		map.put("max_pay_item", "String");
		map.put("status", "String");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "h");
		map.put("form_ref_id", "h");
		map.put("input_department", "i");
		map.put("storage", "i");
		map.put("returner", "h");
		map.put("input_time", "i"); 
		map.put("return_time", "h"); 
		map.put("form_note", "h"); 
		map.put("all_pay_amt", "h");
		map.put("max_pay_item", "h");
		map.put("status", "s");
		return map;
	}
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			count(*) 
//      	FROM
//      	:Com_("D_T1_RETURN_GOODS_HEADER") h
//      	JOIN
//      	:Com_("D_T1_INPUT_HEADER") i
//      	ON
//      	    h.FORM_REF_ID= i.FORM_ID
//      	LEFT JOIN
//      	:Com_("D_T2_BRANCH") b
//      	ON
//      	    i.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//      	JOIN
//      	:Com_("D_T0_FORM_STATUS") s
//      	ON
//      	    h.FORM_ID = s.FORM_ID
//      	AND s.IS_CURRENT = 1
//      	WHERE
//      	 	1 = 1  :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_HEADER");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsFormBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:101^32*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:112^3*/

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
//  			                	    h.FORM_ID,
//  			                	    h.FORM_REF_ID,
//  			                	    i.INPUT_DEPARTMENT,
//  			                	    i.STORAGE,
//  			                	    i.INPUT_TIME,
//  			                	    h.RETURNER,
//  			                	    h.RETURN_TIME,
//  			                	    h.FORM_NOTE,
//  			                	    nvl(h.ALL_PAY_AMT,0) ALL_PAY_AMT,
//  			                	    h.MAX_PAY_ITEM,
//  			                	    s.STATUS
//  			                	FROM
//  			                	:Com_("D_T1_RETURN_GOODS_HEADER") h
//  			                	JOIN
//  			                	:Com_("D_T1_INPUT_HEADER") i
//  			                	ON
//  			                	    h.FORM_REF_ID= i.FORM_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    i.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  			                	JOIN
//  			                	:Com_("D_T0_FORM_STATUS") s
//  			                	ON
//  			                	    h.FORM_ID = s.FORM_ID
//  			                	AND s.IS_CURRENT = 1
//  			                	WHERE
//  			                	 	1 = 1  :queryStr :sortStr) t
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
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_HEADER");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsFormBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:153^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:167^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  				sum(h.all_pay_amt) all_pay_amt
//          	FROM
//          	:Com_("D_T1_RETURN_GOODS_HEADER") h
//          	JOIN
//          	:Com_("D_T1_INPUT_HEADER") i
//          	ON
//          	    h.FORM_REF_ID= i.FORM_ID
//          	LEFT JOIN
//          	:Com_("D_T2_BRANCH") b
//          	ON
//          	    i.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//          	JOIN
//          	:Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	WHERE
//          	 	1 = 1  :queryStr  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T1_INPUT_HEADER");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsFormBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:186^37*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("all_pay_amt", sumIter.all_pay_amt());
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("form_id", headerIter.form_id());
			header.put("form_ref_id", headerIter.form_ref_id());
			header.put("input_department", headerIter.input_department());
			header.put("storage", headerIter.storage());
			header.put("returner", headerIter.returner());
			header.put("input_time", headerIter.input_time());
			header.put("return_time", headerIter.return_time());
			header.put("form_note", headerIter.form_note());
			header.put("all_pay_amt", headerIter.all_pay_amt());
			header.put("max_pay_item", headerIter.max_pay_item());
			header.put("status", headerIter.status());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ReturnGoodsFormBean_SJProfileKeys 
{
  private static ReturnGoodsFormBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ReturnGoodsFormBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ReturnGoodsFormBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ReturnGoodsFormBean_SJProfile0");
  }
}
