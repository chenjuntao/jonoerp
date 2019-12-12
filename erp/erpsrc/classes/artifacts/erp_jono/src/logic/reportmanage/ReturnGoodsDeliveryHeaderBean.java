/*@lineinfo:filename=ReturnGoodsDeliveryHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class ReturnGoodsDeliveryHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ReturnGoodsDeliveryHeaderBean.class);
	
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
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    out_storageNdx = findColumn("out_storage");
    requesterNdx = findColumn("requester");
    in_storageNdx = findColumn("in_storage");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
    return_countNdx = findColumn("return_count");
    return_pay_amtNdx = findColumn("return_pay_amt");
  }
  public String provider_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_idNdx);
  }
  private int provider_idNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String out_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(out_storageNdx);
  }
  private int out_storageNdx;
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
  public String in_storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(in_storageNdx);
  }
  private int in_storageNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:40^2*/

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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^25*/
	
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("provider_id", "String");
		map.put("provider", "String");
		map.put("out_storage", "String");
		map.put("requester", "String");
		map.put("in_storage", "String");
		map.put("category_id", "String"); 
		map.put("category_name", "String"); 
		map.put("item_id", "String"); 
		map.put("item_name", "String"); 
		map.put("return_count", "Number");
		map.put("return_pay_amt", "Number");
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("provider_id", "ph");
		map.put("provider", "ph");
		map.put("out_storage", "ph");
		map.put("requester", "ph");
		map.put("in_storage", "ph");
		map.put("category_id", "im"); 
		map.put("category_name", "c"); 
		map.put("item_id", "d"); 
		map.put("item_name", "im"); 
		map.put("return_count", "d"); 
		map.put("return_pay_amt", "d");
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (
//  		        SELECT
//  		            COUNT(*)
//  		        FROM
//  		            :Com_("D_T1_RETURN_GOODS_HEADER") h
//  	            INNER JOIN
//  		            :Com_("D_T0_FORM_STATUS") s
//  		        ON
//  		            h.FORM_ID = s.FORM_ID
//  		        AND s.IS_CURRENT = 1
//  		        INNER JOIN
//  		            :Com_("D_T1_SHIPPING_HEADER") ph
//  		        ON
//  		            h.FORM_REF_ID= ph.FORM_ID
//  		        INNER JOIN
//  		            :Com_("D_T1_RETURN_GOODS_DETAIL") d
//  		        ON
//  		            h.FORM_ID = d.FORM_ID
//  		        LEFT JOIN
//  		            :Com_("D_T2_ITEM_META") im
//  		        ON
//  		            d.ITEM_ID = im.ITEM_ID
//  		        LEFT JOIN
//  		            :Com_("D_T2_ITEM_CATEGORY") c
//  		        ON
//  		            im.CATEGORY_ID = c.CATEGORY_ID
//  		        WHERE
//  		            s.STATUS = '已审核' :queryStr
//  		        GROUP BY
//  		            ph.PROVIDER_ID,
//  		            ph.PROVIDER,
//  		            ph.OUT_STORAGE_ID,
//  		            ph.OUT_STORAGE,
//  		            ph.REQUESTER_ID,
//  		            ph.REQUESTER,
//  		            ph.IN_STORAGE_ID,
//  		            ph.IN_STORAGE,
//  		            d.ITEM_ID,
//  		            im.ITEM_NAME,
//  		            im.CATEGORY_ID,
//  		            c.CATEGORY_NAME)a };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_4 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDeliveryHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:128^32*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:139^3*/

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
//  			                        ph.PROVIDER_ID,
//  			                        ph.PROVIDER,
//  			                        ph.OUT_STORAGE_ID,
//  			                        ph.OUT_STORAGE,
//  			                        ph.REQUESTER_ID,
//  			                        ph.REQUESTER,
//  			                        ph.IN_STORAGE_ID,
//  			                        ph.IN_STORAGE,
//  			                        d.ITEM_ID,
//  			                        im.ITEM_NAME,
//  			                        im.CATEGORY_ID,
//  			                        c.CATEGORY_NAME,
//  			                        SUM(NVL(d.RETURN_COUNT,0))   RETURN_COUNT,
//  			                        SUM(NVL(d.RETURN_PAY_AMT,0)) RETURN_PAY_AMT
//  			                    FROM
//  			                        :Com_("D_T1_RETURN_GOODS_HEADER") h
//  			                    INNER JOIN
//  			                        :Com_("D_T1_SHIPPING_HEADER") ph
//  			                    ON
//  			                        h.FORM_REF_ID= ph.FORM_ID
//  			                    INNER JOIN
//  			                        :Com_("D_T1_RETURN_GOODS_DETAIL") d
//  			                    ON
//  			                        h.FORM_ID = d.FORM_ID
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_ITEM_META") im
//  			                    ON
//  			                        d.ITEM_ID = im.ITEM_ID
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_ITEM_CATEGORY") c
//  			                    ON
//  			                        im.CATEGORY_ID = c.CATEGORY_ID
//  			                    WHERE
//  			                        1=1 AND h.AUDIT_TIME IS NOT NULL :queryStr
//  			                    GROUP BY
//  			                        ph.PROVIDER_ID,
//  			                        ph.PROVIDER,
//  			                        ph.OUT_STORAGE_ID,
//  			                        ph.OUT_STORAGE,
//  			                        ph.REQUESTER_ID,
//  			                        ph.REQUESTER,
//  			                        ph.IN_STORAGE_ID,
//  			                        ph.IN_STORAGE,
//  			                        d.ITEM_ID,
//  			                        im.ITEM_NAME,
//  			                        im.CATEGORY_ID,
//  			                        c.CATEGORY_NAME :sortStr) t
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
  String __sJT_2 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_3 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  String __sJT_7 = sortStr;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDeliveryHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:200^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:214^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    ROUND(SUM (RETURN_COUNT),2)  RETURN_COUNT,
//  			    ROUND(SUM(RETURN_PAY_AMT),2) RETURN_PAY_AMT
//  			FROM
//  			    (
//  			        SELECT
//  			            SUM(NVL(d.RETURN_COUNT,0))   RETURN_COUNT,
//  			            SUM(NVL(d.RETURN_PAY_AMT,0)) RETURN_PAY_AMT
//  			        FROM
//  			            :Com_("D_T1_RETURN_GOODS_HEADER") h
//  		            INNER JOIN
//  			            :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  	        		INNER JOIN
//  			            :Com_("D_T1_SHIPPING_HEADER") ph
//  			        ON
//  			            h.FORM_REF_ID= ph.FORM_ID
//  			        INNER JOIN
//  			            :Com_("D_T1_RETURN_GOODS_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_META") im
//  			        ON
//  			            d.ITEM_ID = im.ITEM_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            im.CATEGORY_ID = c.CATEGORY_ID
//  			        WHERE
//  			        	s.STATUS = '已审核' :queryStr
//  			        GROUP BY
//  			            ph.PROVIDER_ID,
//  			            ph.PROVIDER,
//  			            ph.OUT_STORAGE_ID,
//  			            ph.OUT_STORAGE,
//  			            ph.REQUESTER_ID,
//  			            ph.REQUESTER,
//  			            ph.IN_STORAGE_ID,
//  			            ph.IN_STORAGE,
//  			            d.ITEM_ID,
//  			            im.ITEM_NAME,
//  			            im.CATEGORY_ID,
//  			            c.CATEGORY_NAME ) a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_RETURN_GOODS_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_4 = Com_("D_T1_RETURN_GOODS_DETAIL");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ReturnGoodsDeliveryHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:259^35*/
		
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
		}
			
		return sumMap;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("provider_id", headerIter.provider_id());
			header.put("provider", headerIter.provider());
			header.put("out_storage", headerIter.out_storage());
			header.put("requester", headerIter.requester());
			header.put("in_storage", headerIter.in_storage());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("return_count", headerIter.return_count());
			header.put("return_pay_amt", headerIter.return_pay_amt());
			header.put("category_id", headerIter.category_id());
			header.put("category_name", headerIter.category_name());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ReturnGoodsDeliveryHeaderBean_SJProfileKeys 
{
  private static ReturnGoodsDeliveryHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ReturnGoodsDeliveryHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ReturnGoodsDeliveryHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.ReturnGoodsDeliveryHeaderBean_SJProfile0");
  }
}
