/*@lineinfo:filename=StorageInOutDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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
import com.tanry.framework.acl.NoPrivilegeException;

public class StorageInOutDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StorageInOutDetailBean.class);
	
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
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    storage_idNdx = findColumn("storage_id");
    storage_nameNdx = findColumn("storage_name");
    item_dimensionNdx = findColumn("item_dimension");
    business_dateNdx = findColumn("business_date");
    operation_timeNdx = findColumn("operation_time");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_unit_priceNdx = findColumn("item_unit_price");
    orgi_countNdx = findColumn("orgi_count");
    item_count_inNdx = findColumn("item_count_in");
    itemCountInMoneyNdx = findColumn("itemCountInMoney");
    item_count_outNdx = findColumn("item_count_out");
    itemCountOutMoneyNdx = findColumn("itemCountOutMoney");
    result_countNdx = findColumn("result_count");
    resultCountMoneyNdx = findColumn("resultCountMoney");
    orgiCountMoneyNdx = findColumn("orgiCountMoney");
    form_idNdx = findColumn("form_id");
    reasonNdx = findColumn("reason");
    reason_textNdx = findColumn("reason_text");
  }
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String branch_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_nameNdx);
  }
  private int branch_nameNdx;
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_nameNdx);
  }
  private int storage_nameNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Date business_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(business_dateNdx);
  }
  private int business_dateNdx;
  public Date operation_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(operation_timeNdx);
  }
  private int operation_timeNdx;
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double orgi_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(orgi_countNdx);
  }
  private int orgi_countNdx;
  public Double item_count_in() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_inNdx);
  }
  private int item_count_inNdx;
  public Double itemCountInMoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemCountInMoneyNdx);
  }
  private int itemCountInMoneyNdx;
  public Double item_count_out() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_outNdx);
  }
  private int item_count_outNdx;
  public Double itemCountOutMoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemCountOutMoneyNdx);
  }
  private int itemCountOutMoneyNdx;
  public Double result_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(result_countNdx);
  }
  private int result_countNdx;
  public Double resultCountMoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(resultCountMoneyNdx);
  }
  private int resultCountMoneyNdx;
  public Double orgiCountMoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(orgiCountMoneyNdx);
  }
  private int orgiCountMoneyNdx;
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String reason() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(reasonNdx);
  }
  private int reasonNdx;
  public String reason_text() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(reason_textNdx);
  }
  private int reason_textNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:49^3*/
	
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
    item_count_inNdx = findColumn("item_count_in");
    itemCountInMoneyNdx = findColumn("itemCountInMoney");
    itemCountOutMoneyNdx = findColumn("itemCountOutMoney");
    item_count_outNdx = findColumn("item_count_out");
  }
  public Double item_count_in() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_inNdx);
  }
  private int item_count_inNdx;
  public Double itemCountInMoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemCountInMoneyNdx);
  }
  private int itemCountInMoneyNdx;
  public Double itemCountOutMoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemCountOutMoneyNdx);
  }
  private int itemCountOutMoneyNdx;
  public Double item_count_out() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_outNdx);
  }
  private int item_count_outNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:55^25*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("branch_id", "String");
		map.put("branch_name", "String");
		map.put("storage_id", "String");
		map.put("storage_name", "String");
		map.put("business_date", "Date");
		map.put("operation_time", "Date");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_unit_price", "Number");
		map.put("orgi_count", "Number");
		map.put("item_count_in", "Number");
		map.put("item_count_out", "Number");
		map.put("result_count", "Number");
		map.put("form_id", "String");
		map.put("reason", "String");
		map.put("reason_text", "String");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("branch_id", "io");
		map.put("branch_name", "b");
		map.put("storage_id", "io");
		map.put("storage_name", "bs");
		map.put("business_date", "io");
		map.put("operation_time", "io");
		map.put("item_id", "io");
		map.put("item_name", "m");
		map.put("item_unit_price", "io");
		map.put("orgi_count", "io");
		map.put("item_count_in", "io");
		map.put("item_count_out", "io");
		map.put("result_count", "io");
		map.put("form_id", "io");
		map.put("reason", "io");
		map.put("reason_text", "io");
		return map;
	}
	
	public Integer count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		Integer total = null;
		/*@lineinfo:generated-code*//*@lineinfo:105^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*) 
//  		FROM
//  		:Com_("D_T0_STORAGE_IN_OUT") io
//  		LEFT JOIN
//  		:Com_("D_T2_BRANCH_STORAGE") bs
//  		ON
//  		    io.STORAGE_ID = bs.STORAGE_ID
//  		LEFT JOIN
//  		:Com_("D_T2_BRANCH") b
//  		ON
//  		    io.BRANCH_ID = b.BRANCH_ID
//  		LEFT JOIN
//  		:Com_("D_T2_ITEM_META") m
//  		ON
//  		    io.ITEM_ID = m.ITEM_ID
//  		WHERE
//  		    1=1    :queryStr
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutDetailBean_SJProfileKeys.getKey(0), 0);
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
    total = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:124^3*/
		closeDefaultContext(myCtx);
		
		total = total == null?0:total;
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:137^3*/

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
//  		                		SELECT
//  		                			io.BRANCH_ID,
//  		                		    io.STORAGE_ID,
//  		                		    io.BUSINESS_DATE,
//  		                		    io.OPERATION_TIME,
//  		                		    io.ITEM_ID,
//  		                		    io.ITEM_UNIT_PRICE,
//  		                		    nvl(round(io.ORGI_COUNT,4),0) ORGI_COUNT,
//                  		    		nvl(round(io.ORGI_COUNT * p.ITEM_PRICE,4),0) orgiCountMoney,
//          		    				nvl(round(io.ITEM_COUNT_IN,4),0) ITEM_COUNT_IN,
//  		    						nvl(round(io.ITEM_COUNT_OUT,4),0) ITEM_COUNT_OUT,
//      								nvl(round(io.RESULT_COUNT,4),0) RESULT_COUNT,
//  		                		    nvl(round(io.RESULT_COUNT * io.ITEM_UNIT_PRICE,4),0) resultCountMoney,
//  		                		    io.FORM_ID,
//  		                		    io.REASON,
//  		                		    CASE
//  			                        WHEN io.REASON = 'YLBS'
//  			                        THEN '原料报损'
//  			                        WHEN io.REASON = 'CGTH'
//  			                        THEN '采购退货'
//  			                        WHEN io.REASON = 'PSTH'
//  			                        THEN '配送退货'
//  			                        WHEN io.REASON = 'PSFSH'
//  			                        THEN '配送反审核'
//  			                        WHEN io.REASON = 'PSRK'
//  			                        THEN '配送入库'
//  			                        WHEN io.REASON = 'CGRK'
//  			                        THEN '采购入库'
//  			                        WHEN io.REASON = 'PSCK'
//  			                        THEN '配送出库'
//  			                        WHEN io.REASON = 'JSRK'
//  			                        THEN '拒收入库'
//  			                        WHEN io.REASON = 'PD'
//  			                        THEN '盘点'
//  			                        WHEN io.REASON = 'CPBS'
//  			                        THEN '出品报损'
//  			                        WHEN io.REASON = 'SCLL'
//  			                        THEN '生产领料'
//  			                        WHEN io.REASON = 'SCCL'
//  			                        THEN '生产超领'
//  			                        WHEN io.REASON = 'SCTL'
//  			                        THEN '生产退料'
//  			                        WHEN io.REASON = 'FGLL'
//  			                        THEN '非工单领料'
//  			                        WHEN io.REASON = 'DB'
//  			                        THEN '调拨'
//  			                        WHEN io.REASON = 'LLKU'
//  			                        THEN '销售扣仓'
//  			                        WHEN io.REASON = 'CPRK'
//  			                        THEN '产品入库'
//  			                        WHEN io.REASON = 'CPCK'
//  			                        THEN '产品出库'
//  			                        WHEN io.REASON = 'YKCK'
//  			                        THEN '越库出库'
//  			                        WHEN io.REASON = 'SSRK'
//  			                        THEN '自产入库'
//  			                        WHEN io.REASON = 'SSHL'
//  			                        THEN '自产耗料'
//  			                        END REASON_TEXT,
//  			                	    b.BRANCH_NAME ,
//  			                	    STORAGE_NAME,
//  			                	    m.ITEM_NAME,
//  			                	    m.item_dimension,
//  			                	    nvl(round(item_count_in*item_unit_price,4),0) itemCountInMoney,
//  			                	    nvl(round(item_count_out*item_unit_price,4),0) itemCountOutMoney
//  			                	FROM
//  			                	:Com_("D_T0_STORAGE_IN_OUT") io
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_BRANCH_STORAGE") bs
//  			                	ON
//  			                	    io.STORAGE_ID = bs.STORAGE_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    io.BRANCH_ID = b.BRANCH_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_ITEM_META") m
//  			                	ON
//  			                	    io.ITEM_ID = m.ITEM_ID
//                  	    		LEFT JOIN
//                  	    		:Com_("D_T2_ITEM_PRICE") p
//  	                	    	ON
//  	                	    	    m.ITEM_ID = p.item_id
//  	                	    	AND p.PRICE_TYPE = 'BENCHMARK'
//  	                	    	AND p.IS_CURRENT = 1
//  			                	WHERE
//  			                    	1 = 1   :queryStr 
//  		                		ORDER BY
//  				                    io.OPERATION_TIME,MY_TIMESTAMP
//  			            		) t
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
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_PRICE");
  String __sJT_6 = queryStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:240^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private Map processSumIter(SumIter sumIter) 
			throws SQLException {
		Map sumMap = new HashMap();
		
		while(sumIter.next()) {
			sumMap.put("item_count_in", sumIter.item_count_in());
			sumMap.put("item_count_out", sumIter.item_count_out());
			sumMap.put("itemCountInMoney", sumIter.itemCountInMoney());
			sumMap.put("itemCountOutMoney", sumIter.itemCountOutMoney());
		}
			
		return sumMap;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:268^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//          		   round(SUM(nvl(io.item_count_in,0)),4) item_count_in,
//          		   round(SUM(nvl(io.item_count_in*item_unit_price,0)),4) itemCountInMoney,
//          		   round(SUM(nvl(io.item_count_out*item_unit_price,0)),4) itemCountOutMoney,
//          		   round(SUM(nvl(io.item_count_out,0)),4)     item_count_out
//              	FROM
//              	:Com_("D_T0_STORAGE_IN_OUT") io
//              	LEFT JOIN
//              	:Com_("D_T2_BRANCH_STORAGE") bs
//              	ON
//              	    io.STORAGE_ID = bs.STORAGE_ID
//              	LEFT JOIN
//              	:Com_("D_T2_BRANCH") b
//              	ON
//              	    io.BRANCH_ID = b.BRANCH_ID
//              	LEFT JOIN
//              	:Com_("D_T2_ITEM_META") m
//              	ON
//              	    io.ITEM_ID = m.ITEM_ID
//              	WHERE
//                  	1 = 1
//                      :queryStr 
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:291^3*/
		
		closeDefaultContext(myCtx);
		
		Map sumMap = processSumIter(sumIter);
		sumIter.close();
		
		return sumMap;
	}

	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			
			header.put("branch_id", headerIter.branch_id());
			header.put("branch_name", headerIter.branch_name());
			header.put("storage_id", headerIter.storage_id());
			header.put("storage_name", headerIter.storage_name());
			header.put("business_date", headerIter.business_date());
			header.put("operation_time", headerIter.operation_time());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_unit_price", headerIter.item_unit_price());
			header.put("orgi_count", headerIter.orgi_count());
			header.put("item_count_in", headerIter.item_count_in());
			header.put("item_count_out", headerIter.item_count_out());
			header.put("result_count", headerIter.result_count());
			header.put("resultCountMoney", headerIter.resultCountMoney());
			header.put("form_id", headerIter.form_id());
			header.put("reason", headerIter.reason());
			header.put("reason_text", headerIter.reason_text());
			header.put("itemCountInMoney", headerIter.itemCountInMoney());
			header.put("itemCountOutMoney", headerIter.itemCountOutMoney());
			header.put("orgiCountMoney", headerIter.orgiCountMoney()); 
			header.put("item_dimension", headerIter.item_dimension());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class StorageInOutDetailBean_SJProfileKeys 
{
  private static StorageInOutDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StorageInOutDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StorageInOutDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.StorageInOutDetailBean_SJProfile0");
  }
}
