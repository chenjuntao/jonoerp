/*@lineinfo:filename=ReturnGoodsDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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
public class ReturnGoodsDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ReturnGoodsDetailBean.class);
	
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
    audit_timeNdx = findColumn("audit_time");
    item_idNdx = findColumn("item_id");
    providerNdx = findColumn("provider");
    provider_idNdx = findColumn("provider_id");
    return_countNdx = findColumn("return_count");
    return_pay_amtNdx = findColumn("return_pay_amt");
    return_reasonNdx = findColumn("return_reason");
    item_nameNdx = findColumn("item_name");
    category_nameNdx = findColumn("category_name");
    item_unit_priceNdx = findColumn("item_unit_price");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
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
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String provider_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_idNdx);
  }
  private int provider_idNdx;
  public Double return_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_countNdx);
  }
  private int return_countNdx;
  public Double return_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_pay_amtNdx);
  }
  private int return_pay_amtNdx;
  public String return_reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(return_reasonNdx);
  }
  private int return_reasonNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:49^2*/

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
    return_countNdx = findColumn("return_count");
    return_pay_amtNdx = findColumn("return_pay_amt");
    receive_amtNdx = findColumn("receive_amt");
  }
  public Double return_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_countNdx);
  }
  private int return_countNdx;
  public Double return_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(return_pay_amtNdx);
  }
  private int return_pay_amtNdx;
  public Double receive_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(receive_amtNdx);
  }
  private int receive_amtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:53^4*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("form_ref_id", "String");
		map.put("input_department", "String");
		map.put("storage", "String");
		map.put("returner", "String");
		map.put("input_time", "Date"); 
		map.put("audit_time", "Date"); 
		map.put("return_time", "Date"); 
		map.put("item_id", "String");
		map.put("provider_id", "String");
		map.put("provider", "String");
		map.put("return_count", "Number");
		map.put("return_pay_amt", "Number");
		map.put("item_unit_price", "Number");
		map.put("receive_amt", "Number");
		map.put("receive_price", "Number");
		map.put("return_reason", "String");
		map.put("item_name", "String");
		map.put("category_name", "String");
		
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
		map.put("audit_time", "h");
		map.put("provider_id", "i");
		map.put("provider", "i");
		map.put("return_time", "h"); 
		map.put("item_id", "d"); 
		map.put("return_count", "d");
		map.put("return_pay_amt", "d");
		map.put("item_unit_price", "id");
		map.put("receive_amt", "id");
		map.put("receive_price", "id");
		map.put("return_reason", "d");
		map.put("item_name", "im");
		map.put("category_name", "c");
		return map;
	}
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:109^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//      		count(*) 
//      	FROM
//      	:Com_("D_T1_RETURN_GOODS_HEADER") h
//      	INNER JOIN
//      	:Com_("D_T0_FORM_STATUS") s
//      	ON
//      	    h.FORM_ID = s.FORM_ID
//      	AND s.IS_CURRENT = 1
//      	INNER JOIN
//      		:Com_("D_T1_RETURN_GOODS_DETAIL") d
//      	ON
//      	    h.FORM_ID = d.FORM_ID
//      	LEFT JOIN
//      		:Com_("D_T2_ITEM_META") im
//      	ON
//      	    d.ITEM_ID = im.ITEM_ID
//      	LEFT JOIN
//      		:Com_("D_T2_ITEM_CATEGORY") c
//      	ON
//      	    im.CATEGORY_ID = c.CATEGORY_ID
//      	JOIN
//      		:Com_("D_T1_INPUT_HEADER") i
//      	ON
//      	    h.FORM_REF_ID= i.FORM_ID
//      	LEFT JOIN
//      		:Com_("D_T2_BRANCH") b
//      	ON
//      	    i.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//      	WHERE
//      	    1 = 1 AND s.STATUS = '已审核'
//      	:queryStr  };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = Com_("D_T1_INPUT_HEADER");
  String __sJT_7 = Com_("D_T2_BRANCH");
  String __sJT_8 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:141^24*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:152^3*/

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
//  			                	    h.audit_time,
//  			                	    i.PROVIDER_ID,
//  			                	    i.PROVIDER,
//  			                	    h.RETURNER,
//  			                	    h.RETURN_TIME,
//  			                	    d.ITEM_ID,
//  			                	    d.RETURN_COUNT,
//  			                	    d.RETURN_PAY_AMT,
//  			                	    d.RETURN_REASON,
//  			                	    im.ITEM_NAME,
//  			                	    c.CATEGORY_NAME,
//  			                	    id.RECEIVE_PRICE,
//  			                	    id.ITEM_UNIT_PRICE,
//  			                	    id.RECEIVE_PRICE*d.RETURN_COUNT RECEIVE_AMT
//  			                	FROM
//  			                		:Com_("D_T1_RETURN_GOODS_HEADER") h
//  			                	INNER JOIN
//  			                		:Com_("D_T1_RETURN_GOODS_DETAIL") d
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	LEFT JOIN
//  			                		:Com_("D_T2_ITEM_META") im
//  			                	ON
//  			                	    d.ITEM_ID = im.ITEM_ID
//  			                	LEFT JOIN
//  			                		:Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    im.CATEGORY_ID = c.CATEGORY_ID
//  			                	JOIN
//  			                		:Com_("D_T1_INPUT_HEADER") i
//  			                	ON
//  			                	    h.FORM_REF_ID= i.FORM_ID
//                  	    		LEFT JOIN
//                  	    			:Com_("D_T1_INPUT_DETAIL") id
//  	                	    	ON
//  	                	    	    i.FORM_ID = id.FORM_ID
//  	                	    	AND id.ITEM_ID = d.ITEM_ID
//  			                	LEFT JOIN
//  			                		:Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    i.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  			                	WHERE
//  			                	    1 = 1 AND h.AUDIT_TIME IS NOT NULL
//  			                	:queryStr :sortStr) t
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
  String __sJT_2 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = Com_("D_T1_INPUT_HEADER");
  String __sJT_6 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_7 = Com_("D_T2_BRANCH");
  String __sJT_8 = queryStr;
  String __sJT_9 = sortStr;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:214^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:228^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//          	    sum(nvl(d.RETURN_COUNT,0)) RETURN_COUNT,
//          	    sum(nvl(d.RETURN_PAY_AMT,0)) RETURN_PAY_AMT,
//          	    sum(nvl(d.RETURN_COUNT,0)*id.RECEIVE_PRICE) RECEIVE_AMT
//          	FROM
//          	:Com_("D_T1_RETURN_GOODS_HEADER") h
//          	INNER JOIN
//          		:Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	INNER JOIN
//          		:Com_("D_T1_RETURN_GOODS_DETAIL") d
//          	ON
//          	    h.FORM_ID = d.FORM_ID
//          	LEFT JOIN
//          		:Com_("D_T2_ITEM_META") im
//          	ON
//          	    d.ITEM_ID = im.ITEM_ID
//          	LEFT JOIN
//          		:Com_("D_T2_ITEM_CATEGORY") c
//          	ON
//          	    im.CATEGORY_ID = c.CATEGORY_ID
//          	JOIN
//          		:Com_("D_T1_INPUT_HEADER") i
//          	ON
//          	    h.FORM_REF_ID= i.FORM_ID
//      		LEFT JOIN
//      			:Com_("D_T1_INPUT_DETAIL") id
//  	    	ON
//  	    	    i.FORM_ID = id.FORM_ID
//  	    	AND id.ITEM_ID = d.ITEM_ID
//          	LEFT JOIN
//          		:Com_("D_T2_BRANCH") b
//          	ON
//          	    i.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//          	WHERE
//          	    1 = 1 AND s.STATUS = '已审核'
//          	:queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = Com_("D_T1_INPUT_HEADER");
  String __sJT_7 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_8 = Com_("D_T2_BRANCH");
  String __sJT_9 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:267^27*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("return_count", sumIter.return_count());
			sumMap.put("return_pay_amt", sumIter.return_pay_amt());
			sumMap.put("receive_amt", sumIter.receive_amt());
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
			header.put("audit_time", headerIter.audit_time());
			header.put("item_id", headerIter.item_id());
			header.put("return_count", headerIter.return_count());
			header.put("receive_price", headerIter.receive_price());
			header.put("item_unit_price", headerIter.item_unit_price());
			header.put("return_pay_amt", headerIter.return_pay_amt());
			header.put("receive_amt", headerIter.receive_amt());
			header.put("provider_id", headerIter.provider_id());
			header.put("provider", headerIter.provider());
			header.put("return_reason", headerIter.return_reason());
			header.put("item_name", headerIter.item_name());
			header.put("category_name", headerIter.category_name());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ReturnGoodsDetailBean_SJProfileKeys 
{
  private static ReturnGoodsDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ReturnGoodsDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ReturnGoodsDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ReturnGoodsDetailBean_SJProfile0");
  }
}
