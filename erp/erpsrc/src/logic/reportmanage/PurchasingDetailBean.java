/*@lineinfo:filename=PurchasingDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class PurchasingDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PurchasingDetailBean.class);
	
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
    delivery_typeNdx = findColumn("delivery_type");
    delivery_textNdx = findColumn("delivery_text");
    providerNdx = findColumn("provider");
    provider_idNdx = findColumn("provider_id");
    requesterNdx = findColumn("requester");
    receiver_idNdx = findColumn("receiver_id");
    receiverNdx = findColumn("receiver");
    storageNdx = findColumn("storage");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_countNdx = findColumn("item_count");
    item_unit_priceNdx = findColumn("item_unit_price");
    receive_priceNdx = findColumn("receive_price");
    receive_amtNdx = findColumn("receive_amt");
    different_amtNdx = findColumn("different_amt");
    category_nameNdx = findColumn("category_name");
    pay_amtNdx = findColumn("pay_amt");
    form_timeNdx = findColumn("form_time");
    audit_timeNdx = findColumn("audit_time");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String delivery_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_typeNdx);
  }
  private int delivery_typeNdx;
  public String delivery_text() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_textNdx);
  }
  private int delivery_textNdx;
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
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
  public String receiver_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receiver_idNdx);
  }
  private int receiver_idNdx;
  public String receiver() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receiverNdx);
  }
  private int receiverNdx;
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
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
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:49^19*/
	
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

/*@lineinfo:user-code*//*@lineinfo:54^22*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("form_id", "String");
		map.put("delivery_text", "String");
		map.put("delivery_type", "String");
		map.put("provider", "String");
		map.put("provider_id", "String");
		map.put("requester", "String");
		map.put("receiver_id", "String");
		map.put("receiver", "String");
		map.put("storage", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_dimension", "String");
		map.put("item_specification", "String");
		map.put("item_category", "String");
		map.put("category_name", "String"); 
		map.put("item_count", "Number"); 
		map.put("item_unit_price", "Number"); 
		map.put("pay_amt", "Number"); 
		map.put("receive_price", "Number");
		map.put("receive_amt", "Number");
		map.put("different_amt", "Number");
		map.put("form_time", "Date"); 
		map.put("audit_time", "Date"); 
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("form_id", "d");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("item_dimension", "d");
		map.put("item_specification", "d");
		map.put("item_category", "d");
		map.put("category_name", "c");
		map.put("receiver_id", "h"); 
		map.put("receiver", "h"); 
		map.put("item_count", "d"); 
		map.put("item_unit_price", "d"); 
		map.put("pay_amt", "d"); 
		map.put("receive_price",  "d");
		map.put("receive_amt",  "d");
		map.put("different_amt",  "d");
		map.put("form_time", "h"); 
		map.put("audit_time", "h"); 
		map.put("provider", "h"); 
		map.put("provider_id", "h"); 
		map.put("requester", "h"); 
		map.put("storage", "h"); 
		map.put("delivery_type", "h"); 
		
		return map;
	}
	
	public int count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_PURCHASING_HEADER") h
//  		INNER JOIN
//  		    :Com_("D_T1_PURCHASING_DETAIL") d
//  		ON
//  		    h.FORM_ID = d.FORM_ID
//  		INNER JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    h.FORM_ID = s.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		INNER JOIN
//  		    :Com_("D_T2_BRANCH") b
//  		ON
//  		    h.REQUESTER_ID = b.BRANCH_ID
//  		LEFT JOIN
//  		    :Com_("D_T2_ITEM_CATEGORY") c
//  		ON
//  		    d.ITEM_CATEGORY = c.CATEGORY_ID
//  		WHERE
//  		    1 = 1
//  		AND s.STATUS in ('已审核','已结案') :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:144^50*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr, String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:155^3*/

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
//  			                	    ROUND(d.ITEM_COUNT,2) ITEM_COUNT,
//  			                	    d.ITEM_UNIT_PRICE,
//  			                	    ROUND(d.PAY_AMT,2) PAY_AMT,
//  			                	    d.RECEIVE_PRICE,
//  			                	    ROUND(NVL(d.RECEIVE_AMT,0),2)           RECEIVE_AMT,
//  			                	    ROUND(d.PAY_AMT-NVL(d.RECEIVE_AMT,0),2) DIFFERENT_AMT,
//  			                	    c.CATEGORY_NAME,
//  			                	    h.FORM_TIME,
//  			                	    h.AUDIT_TIME,
//  			                	    h.provider_id,
//  			                	    h.provider ,
//  			                	    h.requester,
//  			                	    h.receiver_id,
//  			                	    h.receiver,
//  			                	    h.delivery_type,
//  			                	    CASE
//  			                	        WHEN h.delivery_type = 'DIRECT'
//  			                	        THEN '直配'
//  			                	        WHEN h.delivery_type = 'UNIFIED'
//  			                	        THEN '统配'
//  			                	        WHEN h.delivery_type = 'CROSS'
//  			                	        THEN '越库'
//  			                	    END delivery_text,
//  			                	    h.storage
//  			                	FROM
//  			                	    :Com_("D_T1_PURCHASING_HEADER") h
//  			                	INNER JOIN
//  			                	    :Com_("D_T1_PURCHASING_DETAIL") d
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	INNER JOIN
//  			                	    :Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.REQUESTER_ID = b.BRANCH_ID
//  			                	LEFT JOIN
//  			                	    :Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    d.ITEM_CATEGORY = c.CATEGORY_ID
//  			                	WHERE
//  			                	    1 = 1
//  			                	AND h.AUDIT_TIME IS NOT NULL :queryStr :sortStr) t
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
  String __sJT_2 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:215^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:227^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			 	ROUND(SUM(d.ITEM_COUNT),2) ITEM_COUNT,
//  			    ROUND(SUM(d.PAY_AMT),2)    PAY_AMT,
//  			    ROUND(SUM(d.RECEIVE_AMT),2) RECEIVE_AMT
//          	FROM
//          	    :Com_("D_T1_PURCHASING_HEADER") h
//          	INNER JOIN
//          	    :Com_("D_T1_PURCHASING_DETAIL") d
//          	ON
//          	    h.FORM_ID = d.FORM_ID
//          	INNER JOIN
//          	    :Com_("D_T0_FORM_STATUS") s
//          	ON
//          	    h.FORM_ID = s.FORM_ID
//          	AND s.IS_CURRENT = 1
//          	INNER JOIN
//          	    :Com_("D_T2_BRANCH") b
//          	ON
//          	    h.REQUESTER_ID = b.BRANCH_ID
//          	LEFT JOIN
//          	    :Com_("D_T2_ITEM_CATEGORY") c
//          	ON
//          	    d.ITEM_CATEGORY = c.CATEGORY_ID
//          	WHERE
//          	    1 = 1
//      		AND s.STATUS in ('已审核','已结案') :queryStr };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchasingDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:253^54*/
		
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
			header.put("form_id", headerIter.form_id());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("item_category", headerIter.item_category());
			header.put("receiver", headerIter.receiver());
			header.put("item_count", headerIter.item_count());
			header.put("item_unit_price", headerIter.item_unit_price());
			header.put("pay_amt", headerIter.pay_amt());
			header.put("receive_price", headerIter.receive_price());
			header.put("receive_amt", headerIter.receive_amt());
			header.put("different_amt", headerIter.different_amt());
			header.put("form_time", headerIter.form_time());
			header.put("audit_time", headerIter.audit_time());
			header.put("category_name", headerIter.category_name());
			header.put("provider", headerIter.provider());
			header.put("provider_id", headerIter.provider_id());
			header.put("requester", headerIter.requester());
			header.put("receiver", headerIter.receiver());
			header.put("receiver_id", headerIter.receiver_id());
			header.put("storage", headerIter.storage());
			header.put("delivery_type", headerIter.delivery_type());
			header.put("delivery_text", headerIter.delivery_text());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class PurchasingDetailBean_SJProfileKeys 
{
  private static PurchasingDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PurchasingDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PurchasingDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.PurchasingDetailBean_SJProfile0");
  }
}
