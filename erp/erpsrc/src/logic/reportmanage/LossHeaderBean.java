/*@lineinfo:filename=LossHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class LossHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(LossHeaderBean.class);
	
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
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    loss_branch_idNdx = findColumn("loss_branch_id");
    loss_branchNdx = findColumn("loss_branch");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    category_nameNdx = findColumn("category_name");
    item_countNdx = findColumn("item_count");
    pay_amtNdx = findColumn("pay_amt");
    storage_countNdx = findColumn("storage_count");
  }
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
  public Double storage_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(storage_countNdx);
  }
  private int storage_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^26*/
	
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

/*@lineinfo:user-code*//*@lineinfo:43^18*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("storage_id", "String");
		map.put("storage", "String");
		map.put("loss_branch_id", "String");
		map.put("loss_branch", "String");
		map.put("item_id", "String");
		map.put("item_name", "String");
		map.put("item_dimension", "String"); 
		map.put("item_specification", "String"); 
		map.put("item_category", "String"); 
		map.put("category_name", "String"); 
		map.put("item_count", "Number");
		map.put("pay_amt", "Number");
		map.put("storage_count", "Number");
		
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("storage_id", "h");
		map.put("storage", "h");
		map.put("loss_branch_id", "h");
		map.put("loss_branch", "h");
		map.put("item_id", "d");
		map.put("item_name", "d");
		map.put("item_dimension", "d"); 
		map.put("item_specification", "d"); 
		map.put("item_category", "d"); 
		map.put("category_name", "c"); 
		map.put("item_count", "d");
		map.put("pay_amt", "d");
		map.put("storage_count", "d");
		return map;
	}
	
	public int count(String queryStr, String sortStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:87^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    (SELECT
//  		    	    COUNT(*)
//  		    FROM
//  		    :Com_("D_T1_LOSS_DETAIL") d
//  		    LEFT JOIN
//  		    :Com_("D_T2_ITEM_CATEGORY") c
//  		    ON
//  		        d.ITEM_CATEGORY = c.CATEGORY_ID
//  		    LEFT JOIN
//  		    :Com_("D_T1_LOSS_HEADER") h
//  		    ON
//  		        h.FORM_ID = d.FORM_ID
//  		    LEFT JOIN
//  		    :Com_("D_T2_BRANCH") b
//  		    ON
//  		        h.LOSS_BRANCH_ID = b.BRANCH_ID
//  		    LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS")s 
//  		    ON
//  		        s.FORM_ID = d.FORM_ID
//  		    AND s.IS_CURRENT = 1
//  		    WHERE
//  		        1 = 1  AND s.STATUS NOT IN ('未审核') :queryStr
//  		    GROUP BY
//  		        h.LOSS_BRANCH_ID ,
//  		        h.LOSS_BRANCH,
//  		        h.STORAGE_ID,
//  		        h.STORAGE,
//  		        d.ITEM_ID,
//  		        d.ITEM_NAME,
//  		        d.ITEM_CATEGORY,
//  		        c.CATEGORY_NAME,
//  		        d.ITEM_DIMENSION,
//  		        d.ITEM_SPECIFICATION ) a };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T1_LOSS_HEADER");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:126^35*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,  String sortStr)  
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
//  			                (SELECT
//  			                	    h.LOSS_BRANCH_ID ,
//  			                	    h.LOSS_BRANCH,
//  			                	    h.STORAGE_ID,
//  			                	    h.STORAGE,
//  			                	    d.ITEM_ID,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION,
//  			                	    d.ITEM_CATEGORY,
//  			                	    c.CATEGORY_NAME,
//  			                	    SUM(d.ITEM_COUNT)    ITEM_COUNT,
//  			                	    SUM(d.PAY_AMT)       PAY_AMT ,
//  			                	    SUM(d.STORAGE_COUNT) STORAGE_COUNT
//  			                	FROM
//  			                	:Com_("D_T1_LOSS_DETAIL") d
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_ITEM_CATEGORY") c
//  			                	ON
//  			                	    d.ITEM_CATEGORY = c.CATEGORY_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T1_LOSS_HEADER") h
//  			                	ON
//  			                	    h.FORM_ID = d.FORM_ID
//  			                	LEFT JOIN
//  			                	:Com_("D_T2_BRANCH") b
//  			                	ON
//  			                	    h.LOSS_BRANCH_ID = b.BRANCH_ID
//  			                	WHERE
//  			                	    1 = 1 AND h.AUDIT_TIME IS NOT NULL :queryStr
//  			                	GROUP BY
//  			                	    h.LOSS_BRANCH_ID ,
//  			                	    h.LOSS_BRANCH,
//  			                	    h.STORAGE_ID,
//  			                	    h.STORAGE,
//  			                	    d.ITEM_ID,
//  			                	    d.ITEM_NAME,
//  			                	    d.ITEM_CATEGORY,
//  			                	    c.CATEGORY_NAME,
//  			                	    d.ITEM_DIMENSION,
//  			                	    d.ITEM_SPECIFICATION :sortStr) t
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
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T1_LOSS_HEADER");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = queryStr;
  String __sJT_6 = sortStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:189^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public Map sum( String queryStr, String sortStr) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		
		SumIter sumIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:203^3*/

//  ************************************************************
//  #sql [myCtx] sumIter = { SELECT
//  			    SUM (ITEM_COUNT) ITEM_COUNT,
//  			    SUM(PAY_AMT)     PAY_AMT
//  			FROM
//  			    (
//  			        SELECT
//  			            SUM( d.ITEM_COUNT) ITEM_COUNT,
//  			            SUM(d.PAY_AMT)     PAY_AMT
//  			        FROM
//  			        :Com_("D_T1_LOSS_DETAIL") d
//  			        LEFT JOIN
//  			        :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            d.ITEM_CATEGORY = c.CATEGORY_ID
//  			        LEFT JOIN
//  			        :Com_("D_T1_LOSS_HEADER") h
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        LEFT JOIN
//  			        :Com_("D_T2_BRANCH") b
//  			        ON
//  			            h.LOSS_BRANCH_ID = b.BRANCH_ID
//  			        LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            s.FORM_ID = d.FORM_ID
//  			        AND s.IS_CURRENT = 1
//  			        WHERE
//  			            1 = 1  AND s.STATUS NOT IN ('未审核') :queryStr
//  			        GROUP BY
//  			            h.LOSS_BRANCH_ID ,
//  			            h.LOSS_BRANCH,
//  			            h.STORAGE_ID,
//  			            h.STORAGE,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            d.ITEM_CATEGORY,
//  			            c.CATEGORY_NAME,
//  			            d.ITEM_DIMENSION,
//  			            d.ITEM_SPECIFICATION ) a };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_LOSS_DETAIL");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T1_LOSS_HEADER");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = Com_("D_T0_FORM_STATUS");
  String __sJT_6 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, LossHeaderBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:242^40*/
		
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
			header.put("storage_id", headerIter.storage_id());
			header.put("storage", headerIter.storage());
			header.put("loss_branch_id", headerIter.loss_branch_id());
			header.put("loss_branch", headerIter.loss_branch());
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_specification", headerIter.item_specification());
			header.put("item_category", headerIter.item_category());
			header.put("category_name", headerIter.category_name());
			header.put("item_count", headerIter.item_count());
			header.put("pay_amt", headerIter.pay_amt());
			header.put("storage_count", headerIter.storage_count());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class LossHeaderBean_SJProfileKeys 
{
  private static LossHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new LossHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private LossHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.LossHeaderBean_SJProfile0");
  }
}
