/*@lineinfo:filename=PriceChangeBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

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

public class PriceChangeBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PriceChangeBean.class);
	
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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    adjust_typeNdx = findColumn("adjust_type");
    effect_timeNdx = findColumn("effect_time");
    old_priceNdx = findColumn("old_price");
    new_priceNdx = findColumn("new_price");
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
  public String adjust_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(adjust_typeNdx);
  }
  private int adjust_typeNdx;
  public Date effect_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(effect_timeNdx);
  }
  private int effect_timeNdx;
  public Double old_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(old_priceNdx);
  }
  private int old_priceNdx;
  public Double new_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(new_priceNdx);
  }
  private int new_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:33^3*/
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class PriceIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PriceIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    priceNdx = findColumn("price");
  }
  public Double price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(priceNdx);
  }
  private int priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^2*/
	
	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class EffectTimeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public EffectTimeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    effect_timeNdx = findColumn("effect_time");
  }
  public Date effect_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(effect_timeNdx);
  }
  private int effect_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^2*/
	
	public Double getStartPrice(String itemId,String adjustType,java.util.Date startDate, java.util.Date endDate)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		Double startPrice = null;
		
		PriceIter priceIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:52^3*/

//  ************************************************************
//  #sql [myCtx] priceIter = { SELECT
//  			OLD_PRICE price
//  		    FROM
//  		        (
//  		            WITH
//  		                a AS
//  		                (
//  		                    SELECT DISTINCT
//  		                        h.EFFECT_TIME
//  		                    FROM
//  		                    :Com_("D_T1_PRICE_ADJUST_HEADER") h
//  		                    LEFT JOIN
//  		                    :Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  		                    ON
//  		                        h.FORM_ID = d.FORM_ID
//  		                    LEFT JOIN
//  		                    :Com_("D_T0_FORM_STATUS") s
//  		                    ON
//  		                        h.FORM_ID = s.FORM_ID
//  		                    AND s.IS_CURRENT = 1
//  		                    WHERE
//  		                        h.ADJUST_TYPE =:adjustType
//                      		AND (h.EFFECT_TIME >= :sDate or :sDate is null)
//  	            			AND (h.EFFECT_TIME <= :eDate or :eDate is null)
//  		                    AND d.ITEM_ID = :itemId
//  		                    AND s.STATUS = '已审核'
//  		                    ORDER BY
//  		                        h.EFFECT_TIME
//  		                )
//  		                ,
//  		                b AS
//  		                (
//  		                    SELECT
//  		                        d.ITEM_ID,
//  		                        '[' || d.ITEM_ID || ']' ||d.ITEM_NAME ITEM_NAME,
//  		                        h.EFFECT_TIME,
//  		                        d.OLD_PRICE ,
//  		                        d.NEW_PRICE
//  		                    FROM
//  		                    :Com_("D_T1_PRICE_ADJUST_HEADER") h
//  		                    LEFT JOIN
//  		                    :Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  		                    ON
//  		                        h.FORM_ID = d.FORM_ID
//  		                    LEFT JOIN
//  		                    :Com_("D_T2_ITEM_META") im
//  		                    ON
//  		                        d.ITEM_ID = im.ITEM_ID
//  		                    LEFT JOIN
//  		                    :Com_("D_T0_FORM_STATUS") s
//  		                    ON
//  		                        h.FORM_ID = s.FORM_ID
//  		                    AND s.IS_CURRENT = 1
//  		                    WHERE
//  		                        d.ITEM_ID = :itemId
//  		                    AND h.ADJUST_TYPE = :adjustType
//                      		AND (h.EFFECT_TIME >= :sDate or :sDate is null)
//  		            	    AND (h.EFFECT_TIME <= :eDate or :eDate is null)
//  		                    AND s.STATUS = '已审核'
//  		                    ORDER BY
//  		                        h.EFFECT_TIME
//  		                )
//  		            SELECT
//  		                b.OLD_PRICE 
//  		            FROM
//  		                a
//  		            LEFT JOIN
//  		                b
//  		            ON
//  		                a.EFFECT_TIME = b.EFFECT_TIME
//  		            WHERE
//  		            	OLD_PRICE IS NOT NULL
//  		            ORDER BY
//  		                b.EFFECT_TIME ) a
//  		    WHERE
//  		        rownum=1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = adjustType;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = itemId;
  String __sJT_10 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_11 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_12 = Com_("D_T2_ITEM_META");
  String __sJT_13 = Com_("D_T0_FORM_STATUS");
  String __sJT_14 = itemId;
  String __sJT_15 = adjustType;
  Date __sJT_16 = sDate;
  Date __sJT_17 = sDate;
  Date __sJT_18 = eDate;
  Date __sJT_19 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceChangeBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setDate(19, __sJT_19);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      priceIter = new PriceIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:128^19*/
		        
        if(priceIter.next()){
        	startPrice = priceIter.price();
		}        
		        
		closeDefaultContext(myCtx);
		return startPrice;
	}
	
	public Double getEndPrice(String itemId,String adjustType,java.util.Date startDate, java.util.Date endDate)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		Double endPrice = null;
		
		PriceIter priceIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:147^3*/

//  ************************************************************
//  #sql [myCtx] priceIter = { SELECT 
//  			NEW_PRICE  price
//  		    FROM
//  		        (
//  		            WITH
//  		                a AS
//  		                (
//  		                    SELECT DISTINCT
//  		                        h.EFFECT_TIME
//  		                    FROM
//  		                    :Com_("D_T1_PRICE_ADJUST_HEADER") h
//  		                    LEFT JOIN
//  		                    :Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  		                    ON
//  		                        h.FORM_ID = d.FORM_ID
//  		                    LEFT JOIN
//  		                    :Com_("D_T0_FORM_STATUS") s
//  		                    ON
//  		                        h.FORM_ID = s.FORM_ID
//  		                    AND s.IS_CURRENT = 1
//  		                    WHERE
//  		                        h.ADJUST_TYPE =:adjustType
//                      		AND (h.EFFECT_TIME >= :sDate or :sDate is null)
//  	                	    AND (h.EFFECT_TIME <= :eDate or :eDate is null)
//  		                    AND d.ITEM_ID = :itemId
//  		                    AND s.STATUS = '已审核'
//  		                    ORDER BY
//  		                        h.EFFECT_TIME
//  		                )
//  		                ,
//  		                b AS
//  		                (
//  		                    SELECT
//  		                        d.ITEM_ID,
//  		                        '[' || d.ITEM_ID || ']' ||d.ITEM_NAME ITEM_NAME,
//  		                        h.EFFECT_TIME,
//  		                        d.OLD_PRICE ,
//  		                        d.NEW_PRICE
//  		                    FROM
//  		                    :Com_("D_T1_PRICE_ADJUST_HEADER") h
//  		                    LEFT JOIN
//  		                    :Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  		                    ON
//  		                        h.FORM_ID = d.FORM_ID
//  		                    LEFT JOIN
//  		                    :Com_("D_T2_ITEM_META") im
//  		                    ON
//  		                        d.ITEM_ID = im.ITEM_ID
//  		                    LEFT JOIN
//  		                    :Com_("D_T0_FORM_STATUS") s
//  		                    ON
//  		                        h.FORM_ID = s.FORM_ID
//  		                    AND s.IS_CURRENT = 1
//  		                    WHERE
//  		                        d.ITEM_ID = :itemId
//                      		AND (h.EFFECT_TIME >= :sDate or :sDate is null)
//  	                	    AND (h.EFFECT_TIME <= :eDate or :eDate is null)
//  		                    AND h.ADJUST_TYPE = :adjustType
//  		                    AND s.STATUS = '已审核'
//  		                    ORDER BY
//  		                        h.EFFECT_TIME
//  		                )
//  		            SELECT
//  		                b.NEW_PRICE
//  		            FROM
//  		                a
//  		            LEFT JOIN
//  		                b
//  		            ON
//  		                a.EFFECT_TIME = b.EFFECT_TIME
//  		            WHERE
//  		            	NEW_PRICE IS NOT NULL
//  		            ORDER BY
//  		                b.EFFECT_TIME desc) a
//  		    WHERE
//  		        rownum=1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = adjustType;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  String __sJT_9 = itemId;
  String __sJT_10 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_11 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_12 = Com_("D_T2_ITEM_META");
  String __sJT_13 = Com_("D_T0_FORM_STATUS");
  String __sJT_14 = itemId;
  Date __sJT_15 = sDate;
  Date __sJT_16 = sDate;
  Date __sJT_17 = eDate;
  Date __sJT_18 = eDate;
  String __sJT_19 = adjustType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceChangeBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      priceIter = new PriceIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:223^19*/
		
		if(priceIter.next()){
			endPrice = priceIter.price();
		}
		
		closeDefaultContext(myCtx);
					
		return endPrice;
	}
	
	public List<Map> query(String itemId,java.util.Date startDate, java.util.Date endDate)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:242^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    d.ITEM_ID,
//  			    '[' || d.ITEM_ID || ']' ||d.ITEM_NAME ITEM_NAME,
//  			    h.adjust_type,
//  			    h.EFFECT_TIME,
//  			    d.OLD_PRICE ,
//  			    d.NEW_PRICE
//  			FROM
//  			:Com_("D_T1_PRICE_ADJUST_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") im
//  			ON
//  			    d.ITEM_ID = im.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    d.ITEM_ID = :itemId
//      		AND (h.EFFECT_TIME >= :sDate or :sDate is null)
//  		    AND (h.EFFECT_TIME <= :eDate or :eDate is null)
//  			AND s.STATUS = '已审核'
//  			ORDER BY
//  			    h.EFFECT_TIME };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = itemId;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceChangeBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
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

/*@lineinfo:user-code*//*@lineinfo:271^21*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<Map> getEffectTime(String itemId,String adjustType)  
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		EffectTimeIter effectTimeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:283^3*/

//  ************************************************************
//  #sql [myCtx] effectTimeIter = { SELECT DISTINCT
//  			    	h.EFFECT_TIME
//  			    FROM
//  			    :Com_("D_T1_PRICE_ADJUST_HEADER") h
//  			    LEFT JOIN
//  			    :Com_("D_T1_PRICE_ADJUST_DETAIL") d
//  			    ON
//  			        h.FORM_ID = d.FORM_ID
//  			    LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			    ON
//  			        h.FORM_ID = s.FORM_ID
//  			    AND s.IS_CURRENT = 1
//  			    WHERE
//  			        d.ITEM_ID = :itemId
//  			    AND s.STATUS = '已审核'
//  			    AND h.ADJUST_TYPE IN ('BENCHMARK',
//  			                          'JOIN',
//  			                          'RETAIL',
//  			                          'WHOLESALE',
//  			                          'PURCHASE')
//  			    ORDER BY
//  			        h.EFFECT_TIME };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PRICE_ADJUST_HEADER");
  String __sJT_2 = Com_("D_T1_PRICE_ADJUST_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceChangeBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      effectTimeIter = new EffectTimeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:306^25*/
		List<Map> headerLst = processEffectTimeIter(effectTimeIter);
		effectTimeIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	
	private List<Map> processEffectTimeIter(EffectTimeIter effectTimeIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		int index = 1;
		while(effectTimeIter.next()) {
			Map header = new HashMap();
			header.put("index", index);
			header.put("effect_time", effectTimeIter.effect_time());
			headerLst.add(header);
			index++;
		}
		return headerLst;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("adjust_type", headerIter.adjust_type());
			header.put("effect_time", headerIter.effect_time());
			header.put("old_price", headerIter.old_price());
			header.put("new_price", headerIter.new_price());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class PriceChangeBean_SJProfileKeys 
{
  private static PriceChangeBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PriceChangeBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PriceChangeBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.PriceChangeBean_SJProfile0");
  }
}
