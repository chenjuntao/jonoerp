/*@lineinfo:filename=ReturnGoodsHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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
public class ReturnGoodsHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ReturnGoodsHeaderBean.class);
	
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
    input_department_idNdx = findColumn("input_department_id");
    input_departmentNdx = findColumn("input_department");
    storageNdx = findColumn("storage");
    item_idNdx = findColumn("item_id");
    return_countNdx = findColumn("return_count");
    return_pay_amtNdx = findColumn("return_pay_amt");
    item_unit_priceNdx = findColumn("item_unit_price");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    providerNdx = findColumn("provider");
    provider_idNdx = findColumn("provider_id");
    item_nameNdx = findColumn("item_name");
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
  }
  public String input_department_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(input_department_idNdx);
  }
  private int input_department_idNdx;
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
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
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
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:44^2*/

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

/*@lineinfo:user-code*//*@lineinfo:47^22*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("input_department_id", "String");
		map.put("input_department", "String");
		map.put("storage", "String");
		map.put("item_id", "String");
		map.put("provider", "String");
		map.put("provider_id", "String");
		map.put("return_count", "Number");
		map.put("return_pay_amt", "Number");
		map.put("item_unit_price", "Number");
		map.put("receive_amt", "Number");
		map.put("receive_price", "Number");
		map.put("item_name", "String");
		map.put("category_name", "String");
		map.put("category_id", "String");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("input_department_id", "i");
		map.put("input_department", "i");
		map.put("storage", "i");
		map.put("item_id", "d"); 
		map.put("return_count", "d");
		map.put("return_pay_amt", "d");
		map.put("item_unit_price", "id");
		map.put("receive_amt", "id");
		map.put("receive_price", "id");
		map.put("provider_id", "i");
		map.put("provider", "i");
		map.put("item_name", "im");
		map.put("category_name", "c");
		map.put("category_id", "c");
		return map;
	}
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:93^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*) 
//  			from (
//  				SELECT
//  		    		count(*) 
//  		    	FROM
//  		    		:Com_("D_T1_RETURN_GOODS_HEADER") h
//  		    	INNER JOIN
//  	    			:Com_("D_T0_FORM_STATUS") s
//      			ON
//      				h.FORM_ID = s.FORM_ID  	AND s.IS_CURRENT = 1
//  				INNER JOIN
//  		    		:Com_("D_T1_RETURN_GOODS_DETAIL") d
//  		    	ON
//  		    	    h.FORM_ID = d.FORM_ID
//  		    	LEFT JOIN
//  		    		:Com_("D_T2_ITEM_META") im
//  		    	ON
//  		    	    d.ITEM_ID = im.ITEM_ID
//  		    	LEFT JOIN
//  		    		:Com_("D_T2_ITEM_CATEGORY") c
//  		    	ON
//  		    	    im.CATEGORY_ID = c.CATEGORY_ID
//  		    	LEFT JOIN
//  		    		:Com_("D_T1_INPUT_HEADER") i
//  		    	ON
//  		    	    h.FORM_REF_ID= i.FORM_ID
//  		    	LEFT JOIN
//  		    		:Com_("D_T2_BRANCH") b
//  		    	ON
//  		    	    i.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//  		    	WHERE
//  		    	    1 = 1 
//  		    	AND s.STATUS = '已审核' :queryStr
//  		    	GROUP BY
//  		    	    i.INPUT_DEPARTMENT_ID,
//  		    	    i.INPUT_DEPARTMENT,
//  		    	    i.STORAGE_ID,
//  		    	    i.STORAGE,
//  		    	    d.ITEM_ID,
//  		    	    im.ITEM_NAME,
//  		    	    c.CATEGORY_ID,
//  		    	    c.CATEGORY_NAME  )a
//  		 };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:136^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:147^3*/

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
//  			                	    i.INPUT_DEPARTMENT_ID,
//  			                	    i.INPUT_DEPARTMENT,
//  			                	    i.STORAGE_ID,
//  			                	    i.STORAGE,
//  			                	    i.PROVIDER_ID,
//  			                	    i.PROVIDER,
//  			                	    d.ITEM_ID,
//  			                	    im.ITEM_NAME,
//  			                	    SUM( NVL(d.RETURN_COUNT,0))  return_count,
//  			                	    SUM(NVL(d.RETURN_PAY_AMT,0)) RETURN_PAY_AMT,
//  			                	    id.RECEIVE_PRICE,
//  			                	    id.ITEM_UNIT_PRICE,
//  			                	    id.RECEIVE_PRICE*d.RETURN_COUNT RECEIVE_AMT,
//  			                	    c.CATEGORY_ID,
//  			                	    c.CATEGORY_NAME
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
//  			                	LEFT JOIN
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
//  			                	    1 = 1 
//                  	    		AND h.AUDIT_TIME IS NOT NULL :queryStr
//  			                	GROUP BY
//  			                	    i.INPUT_DEPARTMENT_ID,
//  			                	    i.INPUT_DEPARTMENT,
//  			                	    i.STORAGE_ID,
//  			                	    i.STORAGE,
//  			                	    i.PROVIDER,
//  			                	    i.PROVIDER_ID,
//  			                	    d.ITEM_ID,
//  			                	    d.RETURN_COUNT,
//  			                	    im.ITEM_NAME,
//  			                	    id.RECEIVE_PRICE,
//  			                	    id.ITEM_UNIT_PRICE,
//  			                	    c.CATEGORY_ID,
//  			                	    c.CATEGORY_NAME  :sortStr) t
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:219^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:233^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    round(SUM (RETURN_COUNT),2) RETURN_COUNT,
//  			    round(SUM(RETURN_PAY_AMT),2)     RETURN_PAY_AMT,
//  			    ROUND(SUM(RECEIVE_AMT),2) RECEIVE_AMT
//  		FROM
//  		    (SELECT
//              	    SUM( NVL(d.RETURN_COUNT,0))  return_count,
//              	    SUM(NVL(d.RETURN_PAY_AMT,0)) RETURN_PAY_AMT,
//              	    sum(nvl(d.RETURN_COUNT,0)*id.RECEIVE_PRICE) RECEIVE_AMT
//              	FROM
//              		:Com_("D_T1_RETURN_GOODS_HEADER") h
//          		INNER JOIN
//  	    			:Com_("D_T0_FORM_STATUS") s
//      			ON
//      				h.FORM_ID = s.FORM_ID  	AND s.IS_CURRENT = 1
//              	INNER JOIN
//              		:Com_("D_T1_RETURN_GOODS_DETAIL") d
//              	ON
//              	    h.FORM_ID = d.FORM_ID
//              	LEFT JOIN
//              		:Com_("D_T2_ITEM_META") im
//              	ON
//              	    d.ITEM_ID = im.ITEM_ID
//              	LEFT JOIN
//              	:Com_("D_T2_ITEM_CATEGORY") c
//              	ON
//              	    im.CATEGORY_ID = c.CATEGORY_ID
//  	    		LEFT JOIN
//              		:Com_("D_T1_INPUT_HEADER") i
//              	ON
//              	    h.FORM_REF_ID= i.FORM_ID
//  	    		LEFT JOIN
//  	    			:Com_("D_T1_INPUT_DETAIL") id
//  		    	ON
//  		    	    i.FORM_ID = id.FORM_ID
//  		    	AND id.ITEM_ID = d.ITEM_ID
//              	LEFT JOIN
//              		:Com_("D_T2_BRANCH") b
//              	ON
//              	    i.INPUT_DEPARTMENT_ID = b.BRANCH_ID
//              	WHERE
//              	    1 = 1 
//  	    		AND s.STATUS = '已审核' :queryStr
//              	GROUP BY
//              	    i.INPUT_DEPARTMENT_ID,
//              	    i.INPUT_DEPARTMENT,
//              	    i.STORAGE_ID,
//              	    i.STORAGE,
//              	    d.ITEM_ID,
//              	    im.ITEM_NAME,
//              	    c.CATEGORY_ID,
//              	    c.CATEGORY_NAME  ) a };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:285^38*/
		
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
			header.put("input_department", headerIter.input_department());
			header.put("storage", headerIter.storage());
			header.put("item_id", headerIter.item_id());
			header.put("provider_id", headerIter.provider_id());
			header.put("provider", headerIter.provider());
			header.put("return_count", headerIter.return_count());
			header.put("return_pay_amt", headerIter.return_pay_amt());
			header.put("item_name", headerIter.item_name());
			header.put("category_name", headerIter.category_name());
			header.put("category_id", headerIter.category_id());
			header.put("return_count", headerIter.return_count());
			header.put("receive_price", headerIter.receive_price());
			header.put("item_unit_price", headerIter.item_unit_price());
			header.put("return_pay_amt", headerIter.return_pay_amt());
			header.put("receive_amt", headerIter.receive_amt());
			header.put("input_department_id", headerIter.input_department_id());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ReturnGoodsHeaderBean_SJProfileKeys 
{
  private static ReturnGoodsHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ReturnGoodsHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ReturnGoodsHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ReturnGoodsHeaderBean_SJProfile0");
  }
}
