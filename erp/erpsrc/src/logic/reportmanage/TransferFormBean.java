/*@lineinfo:filename=TransferFormBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class TransferFormBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(TransferFormBean.class);
	
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
    form_timeNdx = findColumn("form_time");
    confirm_timeNdx = findColumn("confirm_time");
    from_makerNdx = findColumn("from_maker");
    confirmerNdx = findColumn("confirmer");
    in_branchNdx = findColumn("in_branch");
    in_storageNdx = findColumn("in_storage");
    out_branchNdx = findColumn("out_branch");
    out_storageNdx = findColumn("out_storage");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    form_noteNdx = findColumn("form_note");
    statusNdx = findColumn("status");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public String from_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(from_makerNdx);
  }
  private int from_makerNdx;
  public String confirmer() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(confirmerNdx);
  }
  private int confirmerNdx;
  public String in_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_branchNdx);
  }
  private int in_branchNdx;
  public String in_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_storageNdx);
  }
  private int in_storageNdx;
  public String out_branch() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_branchNdx);
  }
  private int out_branchNdx;
  public String out_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storageNdx);
  }
  private int out_storageNdx;
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
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^17*/
	
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
		map.put("form_time", "Date"); 
		map.put("confirm_time", "Date"); 
		map.put("from_maker", "String");
		map.put("confirmer", "String");
		map.put("in_branch", "String");
		map.put("in_storage", "String");
		map.put("out_branch", "String");
		map.put("out_storage", "String");
		map.put("all_pay_amt", "Number"); 
		map.put("max_pay_item", "String"); 
		map.put("form_note", "String"); 
		map.put("status", "String"); 
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		
		map.put("form_id", "h");
		map.put("form_time", "h"); 
		map.put("confirm_time", "h"); 
		map.put("from_maker", "h");
		map.put("confirmer", "h");
		map.put("in_branch", "h");
		map.put("in_storage", "h");
		map.put("out_branch", "h");
		map.put("out_storage", "h");
		map.put("all_pay_amt", "h"); 
		map.put("max_pay_item", "h"); 
		map.put("form_note", "h"); 
		map.put("status", "h"); 
		map.put("status", "s"); 
		
		return map;
	}
	
	public int count(String queryStr,String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:89^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//      	   count(*) 
//      	FROM
//      	:Com_("D_T1_TRANSFER_HEADER") h
//  	    LEFT JOIN
//  	    :Com_("D_T2_BRANCH") b
//      	ON
//      	    h.IN_BRANCH_ID = b.BRANCH_ID
//      	JOIN
//      	:Com_("D_T0_FORM_STATUS") s
//      	ON
//      	    h.FORM_ID = s.FORM_ID
//      	AND s.IS_CURRENT = 1
//      	WHERE
//          	    1 = 1 :queryStr  };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferFormBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:104^38*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:115^3*/

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
//  			                	    h.IN_BRANCH,
//  			                	    h.IN_STORAGE,
//  			                	    h.OUT_BRANCH,
//  			                	    h.OUT_STORAGE,
//  			                	    h.FROM_MAKER,
//  			                	    h.FORM_TIME,
//  			                	    h.CONFIRMER_ID,
//  			                	    h.CONFIRMER,
//  			                	    h.CONFIRM_TIME,
//  			                	    h.FORM_NOTE,
//  			                	    h.ALL_PAY_AMT,
//  			                	    h.MAX_PAY_ITEM,
//  			                	    s.status
//  			                	FROM
//  			                	:Com_("D_T1_TRANSFER_HEADER") h
//  		                	    LEFT JOIN
//  		                	    :Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.IN_BRANCH_ID = b.BRANCH_ID
//  			                	JOIN
//  			                	:Com_("D_T0_FORM_STATUS") s
//  			                	ON
//  			                	    h.FORM_ID = s.FORM_ID
//  			                	AND s.IS_CURRENT = 1
//  			                	WHERE
//  				                	    1 = 1 :queryStr :sortStr) t
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
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  String __sJT_5 = sortStr;
  int __sJT_6 = endRow;
  int __sJT_7 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferFormBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:155^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr,String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:169^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//          	    sum(nvl(h.all_pay_amt,0)) all_pay_amt
//          	FROM
//          	:Com_("D_T1_TRANSFER_HEADER") h
//      	    LEFT JOIN
//      	    :Com_("D_T2_BRANCH") b
//          	ON
//          	    h.IN_BRANCH_ID = b.BRANCH_ID
//          	JOIN
//          	:Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	WHERE
//              	    1 = 1 :queryStr :sortStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TRANSFER_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = queryStr;
  String __sJT_5 = sortStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TransferFormBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:184^58*/
		
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
			header.put("form_time", headerIter.form_time());
			header.put("confirm_time", headerIter.confirm_time());
			header.put("from_maker", headerIter.from_maker());
			header.put("confirmer", headerIter.confirmer());
			header.put("form_note", headerIter.form_note());
			header.put("all_pay_amt", headerIter.all_pay_amt());
			header.put("max_pay_item", headerIter.max_pay_item());
			
			header.put("in_branch", headerIter.in_branch());
			header.put("in_storage", headerIter.in_storage());
			header.put("out_branch", headerIter.out_branch());
			header.put("out_storage", headerIter.out_storage());
			header.put("status", headerIter.status());
			
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class TransferFormBean_SJProfileKeys 
{
  private static TransferFormBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TransferFormBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TransferFormBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.TransferFormBean_SJProfile0");
  }
}
