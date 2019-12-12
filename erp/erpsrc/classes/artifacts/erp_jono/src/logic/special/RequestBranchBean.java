/*@lineinfo:filename=RequestBranchBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.special;

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
import com.tanry.framework.util.TextUtil;

public class RequestBranchBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequestBranchBean.class);
	
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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    infosNdx = findColumn("infos");
    item_dimensionNdx = findColumn("item_dimension");
  }
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
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:32^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:34^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class BranchIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BranchIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class TotalIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TotalIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    totalsNdx = findColumn("totals");
  }
  public Integer totals() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(totalsNdx);
  }
  private int totalsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^3*/
	
	public Integer count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		TotalIter totalIter = null;
		Integer total = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:50^3*/

//  ************************************************************
//  #sql [myCtx] totalIter = { WITH
//      	    d AS
//      	    (
//      	        SELECT
//      	            d.ITEM_ID,
//      	            h.BUYER_ID,
//      	            SUM(d.ORDER_COUNT) ORDER_COUNT
//      	        FROM
//      	        :Com_("D_T1_REQUEST_HEADER") h
//      	        INNER JOIN
//      	        :Com_("D_T0_FORM_STATUS") s
//      	        ON
//      	            h.FORM_ID = s.FORM_ID
//      	        AND s.IS_CURRENT =1
//      	        AND s.STATUS = '已审核'
//      	        INNER JOIN
//      	        :Com_("D_T1_REQUEST_DETAIL") d
//      	        ON
//      	            h.FORM_ID = d.FORM_ID
//      	        WHERE
//      	        	1 = 1 :queryStr
//      	        GROUP BY
//      	            ROLLUP( d.ITEM_ID, h.BUYER_ID)
//      	    )
//      	    ,
//      	    r AS
//      	    (
//      	        SELECT
//      	            DECODE(ITEM_ID,NULL,'合计',ITEM_ID) ITEM_ID,
//      	            listagg('('||BUYER_ID||'|' || ORDER_COUNT ||')',',') within GROUP
//      	            (ORDER BY BUYER_ID) infos
//      	        FROM
//      	            (
//      	                SELECT
//      	                    d.ITEM_ID,
//      	                    d.BUYER_ID,
//      	                    b.BRANCH_NAME,
//      	                    d.ORDER_COUNT
//      	                FROM
//      	                    d
//      	                LEFT JOIN
//      	                :Com_("D_T2_BRANCH") b
//      	                ON
//      	                    d.BUYER_ID = b.BRANCH_ID
//      	                ORDER BY
//      	                    ITEM_ID)
//      	        GROUP BY
//      	            ITEM_ID
//      	    )
//  		SELECT
//  		  count(ITEM_ID) totals
//  		FROM
//  		    r
//  	 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_4 = queryStr;
  String __sJT_5 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestBranchBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      totalIter = new TotalIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:104^2*/
    
	if(totalIter.next()) {
		total = totalIter.totals();
	}
	
	total = total == null ? 0:total;
    closeDefaultContext(myCtx);
	return total;
}
	
	public List<Map> query(String queryStr,int startRow,int endRow)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:120^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//          	    d AS
//          	    (
//          	        SELECT
//          	            d.ITEM_ID,
//          	            h.BUYER_ID,
//          	            SUM(d.ORDER_COUNT) ORDER_COUNT
//          	        FROM
//          	        :Com_("D_T1_REQUEST_HEADER") h
//          	        INNER JOIN
//          	        :Com_("D_T0_FORM_STATUS") s
//          	        ON
//          	            h.FORM_ID = s.FORM_ID
//          	        AND s.IS_CURRENT =1
//          	        AND s.STATUS = '已审核'
//          	        INNER JOIN
//          	        :Com_("D_T1_REQUEST_DETAIL") d
//          	        ON
//          	            h.FORM_ID = d.FORM_ID
//          	        WHERE
//          	        	1 = 1  :queryStr
//          	        GROUP BY
//          	            ROLLUP( d.ITEM_ID, h.BUYER_ID)
//          	    )
//          	    ,
//          	    r AS
//          	    (
//          	        SELECT
//          	            DECODE(ITEM_ID,NULL,'合计',ITEM_ID) ITEM_ID,
//          	            listagg('('||BUYER_ID||'|' || ORDER_COUNT ||')',',') within GROUP
//          	            (ORDER BY BUYER_ID) infos
//          	        FROM
//          	            (
//          	                SELECT
//          	                    d.ITEM_ID,
//          	                    d.BUYER_ID,
//          	                    b.BRANCH_NAME,
//          	                    d.ORDER_COUNT
//          	                FROM
//          	                    d
//          	                LEFT JOIN
//          	                :Com_("D_T2_BRANCH") b
//          	                ON
//          	                    d.BUYER_ID = b.BRANCH_ID
//          	                ORDER BY
//          	                    ITEM_ID)
//          	        GROUP BY
//          	            ITEM_ID
//          	    )
//          	SELECT
//          	    r.*,
//          	    m.ITEM_DIMENSION,
//          	    m.ITEM_NAME
//          	FROM
//          	    r
//          	LEFT JOIN
//          	:Com_("D_T2_ITEM_META") m
//          	ON
//          	    r.item_id = m.item_id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_4 = queryStr;
  String __sJT_5 = Com_("D_T2_BRANCH");
  String __sJT_6 = Com_("D_T2_ITEM_META");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestBranchBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:179^35*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<Map> queryBranch(String queryStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		BranchIter branchIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:191^3*/

//  ************************************************************
//  #sql [myCtx] branchIter = { SELECT DISTINCT
//  			    h.BUYER_ID BRANCH_ID,
//  			    b.BRANCH_NAME
//  			FROM
//  			:Com_("D_T1_REQUEST_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT =1
//  			AND s.STATUS = '已审核'
//  			INNER JOIN
//  				:Com_("D_T1_REQUEST_DETAIL") d 
//  			ON
//  			    h.FORM_ID = d.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    h.BUYER_ID = b.BRANCH_ID
//  			WHERE
//  				1 = 1  :queryStr
//  			ORDER BY
//  				BRANCH_ID DESC };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_REQUEST_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_REQUEST_DETAIL");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestBranchBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      branchIter = new BranchIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:214^19*/
		List<Map> branchLst = new ArrayList<Map>();
		
		while(branchIter.next()) {
			Map header = new HashMap();
			header.put("branch_id", branchIter.branch_id());
			header.put("branch_name", branchIter.branch_name());
			
			branchLst.add(header);
		}
		
		branchIter.close();
		closeDefaultContext(myCtx);
		return branchLst;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("item_id", headerIter.item_id());
			
			String[] infos = headerIter.infos().toString().split(",");
			for (int i = 0; i < infos.length; i++) {
				String[] temp = infos[i].replace("(", "").replace(")", "").split("\\|");
				
				for (int j = 0; j < temp.length; j++) {
					if(!TextUtil.isEmpty(temp[0])){
						header.put(temp[0], temp[1]);
					}else{
						header.put("sum", temp[1]);
					}
				}
			}
			
			header.put("item_dimension", headerIter.item_dimension());
			header.put("item_name", headerIter.item_name());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class RequestBranchBean_SJProfileKeys 
{
  private static RequestBranchBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestBranchBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestBranchBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.special.RequestBranchBean_SJProfile0");
  }
}
