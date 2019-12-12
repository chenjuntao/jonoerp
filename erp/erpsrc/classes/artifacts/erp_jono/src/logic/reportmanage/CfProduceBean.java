/*@lineinfo:filename=CfProduceBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class CfProduceBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(CfProduceBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:27^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private static class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    getAmtNdx = findColumn("getAmt");
    extraAmtNdx = findColumn("extraAmt");
    returnAmtNdx = findColumn("returnAmt");
    standardCountNdx = findColumn("standardCount");
    actualCountNdx = findColumn("actualCount");
    diffNdx = findColumn("diff");
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
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public Double getAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(getAmtNdx);
  }
  private int getAmtNdx;
  public Double extraAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(extraAmtNdx);
  }
  private int extraAmtNdx;
  public Double returnAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(returnAmtNdx);
  }
  private int returnAmtNdx;
  public Double standardCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(standardCountNdx);
  }
  private int standardCountNdx;
  public Double actualCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(actualCountNdx);
  }
  private int actualCountNdx;
  public Double diff() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(diffNdx);
  }
  private int diffNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^2*/
	
	public List<Map> query(String branchId,java.util.Date completeDate)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		Date sDate = SqlDateUtil.getSqlDate(completeDate);
		
		DefaultContext myCtx = getDefaultContext();
		
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    a AS
//  			    (SELECT
//  			    	    t.ITEM_ID,
//  			    	    t.ITEM_NAME,
//  			    	    m.ITEM_DIMENSION,
//  			    	    ROUND(SUM(h.ITEM_COUNT*t.ITEM_GROSS_COUNT/t.UNIT_CONVERT_FACTOR),2)   standardCount,
//  			    	    ROUND(SUM(h.ACTUAL_COUNT*t.ITEM_GROSS_COUNT/t.UNIT_CONVERT_FACTOR),2) actualCount
//  			    	FROM
//  			    	:Com_("D_T1_WORK_ORDER_HEADER") h
//  			    	LEFT JOIN
//  			    	:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			    	ON
//  			    	    h.FORM_ID = d.WORK_ORDER_ID
//  			    	LEFT JOIN
//  			    	:Com_("D_T2_THERAPY") t
//  			    	ON
//  			    	    h.ITEM_ID = t.THERAPY_ID
//  			    	LEFT JOIN
//  			    	:Com_("D_T2_ITEM_META") m
//  			    	ON
//  			    	    t.ITEM_ID = m.ITEM_ID
//  			    	WHERE
//  			    		h.ACTUAL_COUNT IS NOT NULL
//  			    	AND t.OWNER = 'CENTRALFACTORY'
//  	    			AND d.PRODUCE_TIME <= :sDate
//  			    	GROUP BY
//  			    	    t.ITEM_ID,
//  			    	    t.ITEM_NAME,
//  			    	    m.ITEM_DIMENSION
//  			    	ORDER BY
//  			    	    t.ITEM_ID)
//  			    ,
//  			    b AS
//  			    (-- 未解决 需关联工单以及生产明细表，完工时间进行过滤数据
//  			    		SELECT
//  			    	    d.ITEM_ID,
//  			    	    d.ITEM_NAME,
//  			    	    SUM(
//  			    	        CASE
//  			    	            WHEN h.FORM_TYPE = 'produce'
//  			    	            THEN d.RECEIVE_COUNT
//  			    	            ELSE 0
//  			    	        END )getAmt,
//  			    	    SUM(
//  			    	        CASE
//  			    	            WHEN h.FORM_TYPE = 'extra'
//  			    	            THEN d.RECEIVE_COUNT
//  			    	            ELSE 0
//  			    	        END )extraAmt,
//  			    	    SUM(
//  			    	        CASE
//  			    	            WHEN h.FORM_TYPE = 'return'
//  			    	            THEN d.ITEM_COUNT
//  			    	            ELSE 0
//  			    	        END )returnAmt
//  			    	FROM
//  			    	:Com_("D_T1_REQUISITION_HEADER") h
//  			    	LEFT JOIN
//  			    	:Com_("D_T1_REQUISITION_DETAIL") d
//  			    	ON
//  			    	    h.FORM_ID = d.FORM_ID
//  			    	LEFT JOIN
//  			    	:Com_("D_T1_WORK_ORDER_HEADER") oh
//  			    	ON
//  			    	    h.FORM_REF_ID = oh.FORM_ID
//      	    		LEFT JOIN
//      	    		:Com_("D_T1_ARRENGMENT_DETAIL") ad
//  			    	ON
//  			    		oh.FORM_ID = ad.WORK_ORDER_ID
//  			    	WHERE
//  			    	    oh.ACTUAL_COUNT IS NOT NULL
//                      AND ad.PRODUCE_TIME <= :sDate
//  			    	AND d.ITEM_ID IS NOT NULL
//  			    	GROUP BY
//  			    	    d.ITEM_ID,
//  			    	    d.ITEM_NAME
//  			    	ORDER BY
//  			    	    d.ITEM_ID
//  			    )
//  			
//  			  SELECT
//  	            a.item_id,
//  	            a.item_name,
//  	            a.item_dimension,
//  	            NVL(b.getAmt,0)                                    getAmt,
//  	            NVL(b.extraAmt,0)                                  extraAmt,
//  	            NVL(b.returnAmt,0)                                 returnAmt,
//  	            ROUND(NVL(a.standardCount,0),2)                    standardCount,
//  	            ROUND(NVL(a.actualCount,0),2)                      actualCount,
//  	            ROUND(NVL(NVL(actualCount,0) - standardCount,0) -  NVL(b.returnAmt,0) ,2) diff
//  	        FROM
//  	            a
//  	        LEFT JOIN
//  	            b
//  	        ON
//  	            a.ITEM_ID = b.ITEM_ID
//  	        ORDER BY
//  	            a.item_id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T2_THERAPY");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  Date __sJT_5 = sDate;
  String __sJT_6 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_7 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_8 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_9 = Com_("D_T1_ARRENGMENT_DETAIL");
  Date __sJT_10 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CfProduceBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setDate(10, __sJT_10);
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

/*@lineinfo:user-code*//*@lineinfo:146^23*/
		
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("getAmt", headerIter.getAmt());
			header.put("extraAmt", headerIter.extraAmt());
			header.put("returnAmt", headerIter.returnAmt());
			header.put("standardCount", headerIter.standardCount());
			header.put("actualCount", headerIter.actualCount());
			header.put("diff", headerIter.diff());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class CfProduceBean_SJProfileKeys 
{
  private static CfProduceBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CfProduceBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CfProduceBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.CfProduceBean_SJProfile0");
  }
}
