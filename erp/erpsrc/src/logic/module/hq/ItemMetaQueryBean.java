/*@lineinfo:filename=ItemMetaQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 19, 2015 by liyzh
 * Last edited on Mar 19, 2015 by liyzh
 * 
 * 说明： 根据分类获取原料基础信息，各种价格等
 */
package logic.module.hq;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.tanry.framework.util.TextUtil;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.module.hq.config.item.vo.ItemVO;
import pojo.store.ShelfItem;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.ItemType;
import com.tanry.framework.util.TextUtil;

public class ItemMetaQueryBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(ItemMetaQueryBean.class);
	/*@lineinfo:generated-code*//*@lineinfo:41^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private static class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    shelf_nameNdx = findColumn("shelf_name");
    item_typeNdx = findColumn("item_type");
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
    box_typeNdx = findColumn("box_type");
    enabledNdx = findColumn("enabled");
    infosNdx = findColumn("infos");
    mainSuppilerNdx = findColumn("mainSuppiler");
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
  public String shelf_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_nameNdx);
  }
  private int shelf_nameNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
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
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public String box_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_typeNdx);
  }
  private int box_typeNdx;
  public Integer enabled() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(enabledNdx);
  }
  private int enabledNdx;
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
  public String mainSuppiler() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(mainSuppilerNdx);
  }
  private int mainSuppilerNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:45^36*/
	
	public int countItem(String categoryId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return countItem(categoryId,null);
	}
	
	public int countItem(String categoryId,String displayAllFlag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		int counts = 0;
		
		String queryStr = "";
		if(TextUtil.isEmpty(displayAllFlag)){
			queryStr = " AND m.ENABLED = 1 ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:62^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T2_ITEM_META") m
//  		WHERE
//  		    m.CATEGORY_ID = :categoryId :queryStr };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = categoryId;
  String __sJT_3 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:70^49*/
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public List<Map> queryItem(String categoryId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:79^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { WITH
//  			    d AS
//  			    (
//  			        SELECT
//  			            ITEM_ID,
//  			            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP (ORDER BY PRICE_TYPE
//  			            ) infos
//  			        FROM
//  			            (
//  			                SELECT DISTINCT
//  			                    ps.ITEM_ID,
//  			                    ps.PRICE_TYPE,
//  			                    '供应商价'                PRICE_GROUP_NAME,
//  			                    NVL(ps.ITEM_PRICE, 0) ITEM_PRICE
//  			                FROM
//  			                    :Com_("D_T2_ITEM_PRICE") ps
//  			                INNER JOIN
//  			                    :Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			                ON
//  			                    s.ITEM_ID = ps.ITEM_ID
//  			                WHERE
//  			                    ps.PRICE_TYPE ='SUPPLIER'
//  			                AND s.PRIORITY =0
//  			                AND s.BRANCH_ID='L00'
//  			                AND s.SUPPLIER_ID=ps.SUPPLIER_ID
//  			                UNION ALL
//  			                SELECT
//  			                    p.ITEM_ID,
//  			                    p.PRICE_TYPE,
//  			                    g.PRICE_GROUP_NAME,
//  			                    NVL(p.ITEM_PRICE, 0) ITEM_PRICE
//  			                FROM
//  			                    :Com_("D_T2_ITEM_PRICE") p
//  			                LEFT JOIN
//  			                    :Com_("D_T2_PRICE_GROUP") g
//  			                ON
//  			                    p.PRICE_TYPE = g.PRICE_GROUP_ID
//  			                WHERE
//  			                    (
//  			                        p.SUPPLIER_ID IS NULL
//  			                    OR  p.SUPPLIER_ID='NULL'))
//  			        GROUP BY
//  			            ITEM_ID
//  			    )
//  			SELECT
//  			    m.*,
//  			    c.CATEGORY_NAME,
//  			    sf.SHELF_NAME,
//  			    d.infos,
//  			    b.BRANCH_NAME mainSuppiler
//  			FROM
//  			    :Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_SHELF_ITEM") si
//  			ON
//  			    si.ITEM_ID= m.ITEM_ID
//  			AND si.PRIORITY = '0'
//  			LEFT JOIN
//  			    :Com_("D_T2_SHELF") sf
//  			ON
//  			    si.SHELF_ID= sf.SHELF_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_SUPPLIER_BRANCH_ITEM") bi
//  			ON
//  			    m.ITEM_ID = bi.ITEM_ID
//  			AND bi.BRANCH_ID = 'L00'
//  			AND bi.PRIORITY = 0
//  			LEFT JOIN
//  			    :Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = bi.SUPPLIER_ID
//  			LEFT JOIN
//  			    d
//  			ON
//  			    d.ITEM_ID = m.ITEM_ID
//  			WHERE
//  			    m.CATEGORY_ID = :categoryId
//  			AND m.ENABLED = 1
//  			ORDER BY
//  			    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_SHELF_ITEM");
  String __sJT_8 = Com_("D_T2_SHELF");
  String __sJT_9 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = categoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:163^17*/
		List<Map> itemLst =processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	public int countByItemName(String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return countByItemName(itemName,null);
	}
	
	public int countByItemName(String itemName,String displayAllFlag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		int counts = 0;
		
		String queryStr = "";
		if(TextUtil.isEmpty(displayAllFlag)){
			queryStr = " AND m.ENABLED = 1 ";
		}
		
		itemName = "%" + itemName + "%";
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:186^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//          			COUNT(*)
//      			
//          	    FROM
//          	    :Com_("D_T2_ITEM_META") m
//      			WHERE
//      				(
//      			        m.ITEM_ID LIKE :itemName
//      			    OR  m.ITEM_NAME LIKE :itemName)
//      			AND m.ITEM_TYPE IN ('RAW')
//          	    :queryStr
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = itemName;
  String __sJT_3 = itemName;
  String __sJT_4 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 2);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:199^3*/
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public List<Map> queryByItemName(String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		itemName = "%" + itemName + "%";
		
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:211^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { WITH
//  	    	    d AS
//  	    	    (
//  	    	    		 SELECT
//  	    	             ITEM_ID,
//  	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  	    	            (ORDER BY PRICE_TYPE) infos
//  	    	        FROM
//  	    	            (
//  	    	            		 SELECT DISTINCT
//   	    	                    ps.ITEM_ID,
//   	    	                    ps.PRICE_TYPE,
//   	    	                   '供应商价' PRICE_GROUP_NAME,
//   	    	                   nvl(ps.ITEM_PRICE, 0) ITEM_PRICE
//   	    	                FROM
//   	    	                :Com_("D_T2_ITEM_PRICE") ps
//   	    	               INNER JOIN 
//   	    	              :Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//   	    	              ON
//   	    	              s.ITEM_ID = ps.ITEM_ID
//   	            		  WHERE
//  	    	                      ps.PRICE_TYPE ='SUPPLIER'
//  	    	                  AND s.PRIORITY =0
//  	    	                  AND s.BRANCH_ID='L00'
//  	    	                  AND s.SUPPLIER_ID=ps.SUPPLIER_ID
//   	    	             UNION ALL
//  	    	            		SELECT
//  	    	                    p.ITEM_ID,
//  	    	                    p.PRICE_TYPE,
//  	    	                    g.PRICE_GROUP_NAME,
//  	    	                    nvl(p.ITEM_PRICE, 0) ITEM_PRICE
//  	    	                FROM
//  	    	                :Com_("D_T2_ITEM_PRICE") p
//  	    	                LEFT JOIN
//  	    	                :Com_("D_T2_PRICE_GROUP")  g
//  	    	                ON
//  	    	                    p.PRICE_TYPE = g.PRICE_GROUP_ID
//   	                    WHERE
//   	                   (p.SUPPLIER_ID IS NULL or p.SUPPLIER_ID='NULL'))
//  	    	        GROUP BY
//  	    	          ITEM_ID
//  	    	    )
//  			SELECT
//  			    m.*,
//  			    c.CATEGORY_NAME,
//  			    sf.SHELF_NAME,
//  			    d.infos,
//  			    b.BRANCH_NAME    mainSuppiler
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//      		LEFT JOIN
//      			d
//      		ON 
//      			d.ITEM_ID = m.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_SHELF_ITEM") si
//  			ON
//  			    si.ITEM_ID= m.ITEM_ID AND si.PRIORITY = '0'
//  			LEFT JOIN
//  			:Com_("D_T2_SHELF") sf
//  			ON
//  			    si.SHELF_ID= sf.SHELF_ID
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") bi
//  			ON
//  			    m.ITEM_ID = bi.ITEM_ID
//  			AND bi.BRANCH_ID = 'L00'
//  			AND bi.PRIORITY = 0
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = bi.SUPPLIER_ID
//  			WHERE
//  			    1=1
//  			AND (
//  			        m.ITEM_ID LIKE :itemName
//  			    OR  m.ITEM_NAME LIKE :itemName)
//  			AND m.ITEM_TYPE IN ('RAW')
//  			AND m.ENABLED = 1 
//  			ORDER BY
//  			    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_SHELF_ITEM");
  String __sJT_8 = Com_("D_T2_SHELF");
  String __sJT_9 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = itemName;
  String __sJT_12 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 3);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:296^17*/
		List<Map> itemLst =processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	
	public List<Map> queryItem(String categoryId, int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return queryItem(categoryId,null,startRow,endRow);
	}
	
	
	public List<Map> queryItem(String categoryId, String displayAllFlag,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String queryStr = "";
		if(TextUtil.isEmpty(displayAllFlag)){
			queryStr = " AND m.ENABLED = 1 ";
		}
		
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:320^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			        				WITH
//  			        	    	    d AS
//  			        	    	    (
//  			        	    	    		 SELECT
//  			        	    	             ITEM_ID,
//  			        	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  			        	    	            (ORDER BY PRICE_TYPE) infos
//  			        	    	        FROM
//  			        	    	            (
//  		        	    	            		 SELECT DISTINCT
//  				        	    	                    ps.ITEM_ID,
//  				        	    	                    ps.PRICE_TYPE,
//  				        	    	                   '供应商价' PRICE_GROUP_NAME,
//  				        	    	                   nvl(ps.ITEM_PRICE, 0) ITEM_PRICE
//  				        	    	                FROM
//  				        	    	                :Com_("D_T2_ITEM_PRICE") ps
//  				        	    	               INNER JOIN 
//  				        	    	              :Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  				        	    	              ON
//  				        	    	              s.ITEM_ID = ps.ITEM_ID
//  		        	    	            		  WHERE
//  			        	    	                      ps.PRICE_TYPE ='SUPPLIER'
//  			        	    	                  AND s.PRIORITY =0
//  			        	    	                  AND s.BRANCH_ID='L00'
//  			        	    	                  AND s.SUPPLIER_ID=ps.SUPPLIER_ID
//  				        	    	             UNION ALL
//  			        	    	            		SELECT
//  			        	    	                    p.ITEM_ID,
//  			        	    	                    p.PRICE_TYPE,
//  			        	    	                    g.PRICE_GROUP_NAME,
//  			        	    	                    nvl(p.ITEM_PRICE, 0) ITEM_PRICE
//  			        	    	                FROM
//  			        	    	                :Com_("D_T2_ITEM_PRICE") p
//  			        	    	                LEFT JOIN
//  			        	    	                :Com_("D_T2_PRICE_GROUP")  g
//  			        	    	                ON
//  			        	    	                    p.PRICE_TYPE = g.PRICE_GROUP_ID
//  		        	    	                    WHERE
//  		        	    	                    (p.SUPPLIER_ID IS NULL or p.SUPPLIER_ID='NULL'))
//  			        	    	        GROUP BY
//  			        	    	          ITEM_ID
//  			        	    	    )
//  			        			SELECT
//  			        			    m.*,
//  			        			    c.CATEGORY_NAME,
//  			        			    sf.SHELF_NAME,
//  			        			    d.infos,
//  			        			    b.BRANCH_NAME    mainSuppiler
//  			        			FROM
//  			        			:Com_("D_T2_ITEM_META") m
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_ITEM_CATEGORY") c
//  			        			ON
//  			        			    c.CATEGORY_ID = m.CATEGORY_ID
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_SHELF_ITEM") si 
//  			        			ON
//  			        			    si.ITEM_ID= m.ITEM_ID  AND si.PRIORITY = '0'
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_SHELF") sf
//  			        			ON
//  			        			    si.SHELF_ID= sf.SHELF_ID
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") bi
//  			        			ON
//  			        			    m.ITEM_ID = bi.ITEM_ID
//  			        			AND bi.BRANCH_ID = 'L00'
//  			        			AND bi.PRIORITY = 0
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_BRANCH") b
//  			        			ON
//  			        			    b.BRANCH_ID = bi.SUPPLIER_ID
//  			            		LEFT JOIN
//  			            			d
//  			            		ON 
//  			            			d.ITEM_ID = m.ITEM_ID
//  			        			WHERE
//  			        			    m.CATEGORY_ID = :categoryId
//          			    		AND m.ITEM_TYPE IN ('RAW')
//  			        			:queryStr
//  			        			ORDER BY
//  			        			    m.ITEM_ID) t
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
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_SHELF_ITEM");
  String __sJT_8 = Com_("D_T2_SHELF");
  String __sJT_9 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = categoryId;
  String __sJT_12 = queryStr;
  int __sJT_13 = endRow;
  int __sJT_14 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 4);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setInt(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:415^34*/
		List<Map> itemLst =processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	public List<Map> queryByItemName(String itemName, int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return queryByItemName(itemName,null,startRow,endRow);
	}
	
	public List<Map> queryByItemName(String itemName,String displayAllFlag, int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		itemName = "%" + itemName + "%";
		
		String queryStr = "";
		if(TextUtil.isEmpty(displayAllFlag)){
			queryStr = " AND m.ENABLED = 1 ";
		}
		
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:439^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			        				WITH
//  			        	    	    d AS
//  			        	    	    (
//  			        	    	    		 SELECT
//  			        	    	             ITEM_ID,
//  			        	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  			        	    	            (ORDER BY PRICE_TYPE) infos
//  			        	    	        FROM
//  			        	    	            (
//  			        	    	            		 SELECT DISTINCT
//  				        	    	                    ps.ITEM_ID,
//  				        	    	                    ps.PRICE_TYPE,
//  				        	    	                   '供应商价' PRICE_GROUP_NAME,
//  				        	    	                   nvl(ps.ITEM_PRICE, 0) ITEM_PRICE
//  				        	    	                FROM
//  				        	    	                :Com_("D_T2_ITEM_PRICE") ps
//  				        	    	               INNER JOIN 
//  				        	    	              :Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  				        	    	              ON
//  				        	    	              s.ITEM_ID = ps.ITEM_ID
//  		        	    	            		  WHERE
//  			        	    	                      ps.PRICE_TYPE ='SUPPLIER'
//  			        	    	                  AND s.PRIORITY =0
//  			        	    	                  AND s.BRANCH_ID='L00'
//  			        	    	                  AND s.SUPPLIER_ID=ps.SUPPLIER_ID
//  				        	    	             UNION ALL
//  			        	    	            		SELECT
//  			        	    	                    p.ITEM_ID,
//  			        	    	                    p.PRICE_TYPE,
//  			        	    	                    g.PRICE_GROUP_NAME,
//  			        	    	                    nvl(p.ITEM_PRICE, 0) ITEM_PRICE
//  			        	    	                FROM
//  			        	    	                :Com_("D_T2_ITEM_PRICE") p
//  			        	    	                LEFT JOIN
//  			        	    	                :Com_("D_T2_PRICE_GROUP")  g
//  			        	    	                ON
//  			        	    	                    p.PRICE_TYPE = g.PRICE_GROUP_ID
//  		        	    	                    WHERE
//  		        	    	                    (p.SUPPLIER_ID IS NULL or p.SUPPLIER_ID='NULL'))
//  			        	    	        GROUP BY
//  			        	    	          ITEM_ID
//  			        	    	    )
//  			        			SELECT
//  			        			    m.*,
//  			        			    c.CATEGORY_NAME,
//  			        			    sf.SHELF_NAME,
//  			        			    d.infos,
//  			        			    b.BRANCH_NAME    mainSuppiler
//  			        			FROM
//  			        			:Com_("D_T2_ITEM_META") m
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_ITEM_CATEGORY") c
//  			        			ON
//  			        			    c.CATEGORY_ID = m.CATEGORY_ID
//  			            		LEFT JOIN
//  			            			d
//  			            		ON 
//  			            			d.ITEM_ID = m.ITEM_ID
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_SHELF_ITEM") si
//  			        			ON
//  			        			    si.ITEM_ID= m.ITEM_ID AND si.PRIORITY = '0'
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_SHELF") sf
//  			        			ON
//  			        			    si.SHELF_ID= sf.SHELF_ID
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") bi
//  			        			ON
//  			        			    m.ITEM_ID = bi.ITEM_ID
//  			        			AND bi.BRANCH_ID = 'L00'
//  			        			AND bi.PRIORITY = 0
//  			        			LEFT JOIN
//  			        			:Com_("D_T2_BRANCH") b
//  			        			ON
//  			        			    b.BRANCH_ID = bi.SUPPLIER_ID
//  			        			WHERE
//  			        				(
//  			        			        m.ITEM_ID LIKE :itemName
//  			        			    OR  m.ITEM_NAME LIKE :itemName)
//  			        			AND m.ITEM_TYPE = 'RAW' :queryStr
//  			        			ORDER BY
//  			        			    m.ITEM_ID) t
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
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_SHELF_ITEM");
  String __sJT_8 = Com_("D_T2_SHELF");
  String __sJT_9 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = itemName;
  String __sJT_12 = itemName;
  String __sJT_13 = queryStr;
  int __sJT_14 = endRow;
  int __sJT_15 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 5);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
      __sJT_stmt.setInt(15, __sJT_15);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:535^34*/
		List<Map> itemLst =processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	private List<Map> processIter(ItemIter itemIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(itemIter.next()) {
			Map header = new HashMap();
			header.put("itemId", itemIter.item_id());
			header.put("itemName", itemIter.item_name());
			header.put("itemDimension", itemIter.item_dimension());
			header.put("shelfName",itemIter.shelf_name());
			header.put("itemType",itemIter.item_type());
			header.put("categoryId",itemIter.category_id());
			header.put("categoryName",itemIter.category_name());
			header.put("queryCode",itemIter.query_code());
			header.put("itemSpecification",itemIter.item_specification());
			header.put("boxType",itemIter.box_type());
			header.put("enabled",itemIter.enabled());
			header.put("mainSuppiler",itemIter.mainSuppiler());
			String info = itemIter.infos();
			if(!TextUtil.isEmpty(info)){
				String[] infos = info.toString().split(",");
				for (int i = 0; i < infos.length; i++) {
					String[] temp = infos[i].replace("(", "").replace(")", "").split("\\|");
					
					for (int j = 0; j < temp.length; j++) {
						header.put(temp[0], temp[1]);
					}
				}
			}
			headerLst.add(header);
		}
		return headerLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:576^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private static class ProductIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ProductIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_typeNdx = findColumn("item_type");
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    category_nameNdx = findColumn("category_name");
    query_codeNdx = findColumn("query_code");
    enabledNdx = findColumn("enabled");
    effect_dateNdx = findColumn("effect_date");
    item_specificationNdx = findColumn("item_specification");
    item_countNdx = findColumn("item_count");
    infosNdx = findColumn("infos");
    cost_priceNdx = findColumn("cost_price");
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
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public Integer enabled() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(enabledNdx);
  }
  private int enabledNdx;
  public Date effect_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(effect_dateNdx);
  }
  private int effect_dateNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public Integer item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(item_countNdx);
  }
  private int item_countNdx;
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
  public Double cost_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(cost_priceNdx);
  }
  private int cost_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:579^20*/
	
	public int countProduct(String categoryId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return countProduct(categoryId,null);
	}
	
	public int countProduct(String categoryId,String displayAllFlag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		int counts = 0;
		
		String queryStr = "";
		if(TextUtil.isEmpty(displayAllFlag)){
			queryStr = " AND m.ENABLED = 1 ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:596^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T2_ITEM_META") m
//  		WHERE
//  			1=1	:queryStr
//  			AND m.CATEGORY_ID = :categoryId 
//  			AND m.ITEM_TYPE = 'PRODUCT' };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = queryStr;
  String __sJT_3 = categoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:606^31*/
		closeDefaultContext(myCtx);
		return counts;
	}
	
	
	public List<Map> queryProduct(String categoryId, int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return queryProduct(categoryId,null,startRow,endRow);
	}
	
	public List<Map> queryProduct(String categoryId,String displayAllFlag,  int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String queryStr ="";
		
		if(TextUtil.isEmpty(displayAllFlag)){
			queryStr = " AND m.ENABLED = 1 ";
		}
		
		ProductIter productIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:628^3*/

//  ************************************************************
//  #sql [myCtx] productIter = { SELECT
//  	    *
//  	    FROM
//  	        (
//  	            SELECT
//  	                t.*,
//  	                ROWNUM rowNumber
//  	            FROM
//  	                (
//  			WITH
//  			    ty AS
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            COUNT(*) AS item_count
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        WHERE
//  			            1=1
//  			        GROUP BY
//  			            t.THERAPY_ID
//  			        ORDER BY
//  			            t.THERAPY_ID
//  			    ),
//  			    fs AS
//  			    (
//  				        SELECT
//  				            s.FOOD_SUIT_ID,
//  				            COUNT(*) AS item_count
//  				        FROM
//  				        :Com_("D_T2_ITEM_FOOD_SUIT") s
//  				        WHERE
//  				            1=1
//  				        GROUP BY
//  				            s.FOOD_SUIT_ID
//  				        ORDER BY
//  				            s.FOOD_SUIT_ID
//  				    ),
//  	    	    d AS
//  	    	    (
//      	    		 SELECT
//  	    	            ITEM_ID,
//  	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  	    	            (ORDER BY PRICE_TYPE) infos
//              		FROM
//              	    :Com_("D_T2_ITEM_PRICE") p
//  	            	WHERE
//  	            	    p.PRICE_TYPE LIKE 'SALE%'
//  	            	GROUP BY
//  	            	    ITEM_ID
//  	    	    )
//  			SELECT
//  			    m.*,
//  			    c.CATEGORY_NAME,
//  			    d.infos,
//  			    nvl(t.cost_price,0.0) cost_price,
//  			    nvl(ty.item_count,fs.item_count) item_count
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//      		LEFT JOIN
//  				d
//  			ON 
//  				d.ITEM_ID = m.ITEM_ID
//  			LEFT JOIN
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            nvl(ROUND(SUM(t.ITEM_GROSS_COUNT*p.ITEM_PRICE), 2),0.0) AS cost_price
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        LEFT JOIN
//  			        :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            p.ITEM_ID = t.ITEM_ID
//  			        AND p.PRICE_TYPE = 'PURCHASE'
//  			        GROUP BY
//  			            t.THERAPY_ID) t
//  			ON
//  			    t.THERAPY_ID = m.ITEM_ID
//  			LEFT JOIN
//  			    ty
//  			ON
//  			    m.ITEM_ID = ty.THERAPY_ID
//  	    	LEFT JOIN
//  			    fs
//  			ON
//  			    m.ITEM_ID = fs.FOOD_SUIT_ID
//  			WHERE
//  				1=1	:queryStr
//  				AND  m.CATEGORY_ID = :categoryId
//  				AND m.ITEM_TYPE = 'PRODUCT'
//  			ORDER BY
//  			    m.ITEM_ID
//  			    ) t
//  	            WHERE
//  	                ROWNUM < :endRow)
//  	    WHERE
//  	        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = Com_("D_T2_ITEM_FOOD_SUIT");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = Com_("D_T2_THERAPY");
  String __sJT_7 = Com_("D_T2_ITEM_PRICE");
  String __sJT_8 = queryStr;
  String __sJT_9 = categoryId;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 7);
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
      productIter = new ProductIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:730^32*/
		List<Map> itemLst = new ArrayList<Map>();
		while (productIter.next()) {
			itemLst.add(iterateProduct(productIter));
		}
		productIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	public int countByProduct(String condition) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return countByProduct(condition,null);
	}
	
	public int countByProduct(String condition,String displayAllFlag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		int counts = 0;
		condition = "%" + condition + "%";
		
		String queryStr ="";
		
		if(TextUtil.isEmpty(displayAllFlag)){
			queryStr = " AND m.ENABLED = 1 ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:757^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T2_ITEM_META") m
//  		WHERE
//  		 m.ITEM_TYPE = 'PRODUCT'
//  		:queryStr
//  		AND (
//  		        m.ITEM_ID LIKE :condition
//  		    OR  m.ITEM_NAME LIKE :condition) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = queryStr;
  String __sJT_3 = condition;
  String __sJT_4 = condition;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 8);
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
    counts = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:769^39*/
		closeDefaultContext(myCtx);
		return counts;
	}
	
	public List<Map> queryByProduct(String condition, int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return queryByProduct(condition,null,startRow,endRow);
	}
	
	public List<Map> queryByProduct(String condition, String displayAllFlag,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ProductIter productIter = null;
		
		condition = "%" + condition + "%";
		
		String queryStr = "";
		if(TextUtil.isEmpty(displayAllFlag)){
			queryStr = " AND m.ENABLED = 1 ";
		}
		
		/*@lineinfo:generated-code*//*@lineinfo:791^3*/

//  ************************************************************
//  #sql [myCtx] productIter = { SELECT
//  	    *
//  	    FROM
//  	        (
//  	            SELECT
//  	                t.*,
//  	                ROWNUM rowNumber
//  	            FROM
//  	                (
//  			WITH
//  			    ty AS
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            COUNT(*) AS item_count
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        GROUP BY
//  			            t.THERAPY_ID
//  			        ORDER BY
//  			            t.THERAPY_ID
//  			    ),
//  	    	    d AS
//  	    	    (
//      	    		 SELECT
//  	    	            ITEM_ID,
//  	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  	    	            (ORDER BY PRICE_TYPE) infos
//              		FROM
//              	    :Com_("D_T2_ITEM_PRICE") p
//  	            	WHERE
//  	            	    p.PRICE_TYPE LIKE 'SALE%'
//  	            	GROUP BY
//  	            	    ITEM_ID
//  	    	    )
//  			SELECT
//  			    m.*,
//  			    c.CATEGORY_NAME,
//  			    d.infos,
//  			    nvl(t.cost_price,0.0) cost_price,
//  			    ty.item_count
//  			FROM
//  				:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//      		LEFT JOIN
//  				d
//  			ON 
//  				d.ITEM_ID = m.ITEM_ID
//  			LEFT JOIN
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            nvl(ROUND(SUM(t.ITEM_GROSS_COUNT*p.ITEM_PRICE), 2),0.0) AS cost_price
//  			        FROM
//  			        	:Com_("D_T2_THERAPY") t
//  			        LEFT JOIN
//  			        	:Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            p.ITEM_ID = t.ITEM_ID
//  			        AND p.PRICE_TYPE = 'PURCHASE'
//  			        GROUP BY
//  			            t.THERAPY_ID) t
//  			ON
//  			    t.THERAPY_ID = m.ITEM_ID
//  			LEFT JOIN
//  			    ty
//  			ON
//  			    m.ITEM_ID = ty.THERAPY_ID
//  			WHERE
//  			m.ITEM_TYPE = 'PRODUCT' 
//  			:queryStr
//  			AND (
//  			        m.ITEM_ID LIKE :condition
//  			    OR  m.ITEM_NAME LIKE :condition)
//  			ORDER BY
//  			    m.ITEM_ID) t
//  	            WHERE
//                  ROWNUM < :endRow)
//      WHERE
//          rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = Com_("D_T2_ITEM_PRICE");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_5 = Com_("D_T2_THERAPY");
  String __sJT_6 = Com_("D_T2_ITEM_PRICE");
  String __sJT_7 = queryStr;
  String __sJT_8 = condition;
  String __sJT_9 = condition;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 9);
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
      productIter = new ProductIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:874^31*/

		List<Map> itemLst = new ArrayList<Map>();
		while (productIter.next()) {
			itemLst.add(iterateProduct(productIter));
		}
		productIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:885^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class CountIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CountIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    totalNdx = findColumn("total");
  }
  public int total() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(totalNdx);
  }
  private int totalNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:886^13*/
	
	public int countRaw(String condition, String displayAllFlag)
			throws NoPrivilegeException,SQLException,NoConnection {
		CountIter countIter =null;
		DefaultContext myCtx = getDefaultContext();

        String queryStr = "";
        if(TextUtil.isEmpty(displayAllFlag)){
            queryStr = " AND m.ENABLED = 1 ";
        }

		/*@lineinfo:generated-code*//*@lineinfo:898^3*/

//  ************************************************************
//  #sql [myCtx] countIter = { WITH
//  			    filter AS
//  			    (
//  			        SELECT DISTINCT
//  			            t.THERAPY_ID
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        WHERE
//  			            1=1
//  			        AND (
//  			                t.ITEM_ID = :condition
//  			            OR  t.ITEM_NAME = :condition)
//  			        ORDER BY
//  			            t.THERAPY_ID
//  			    )
//  				SELECT
//          			COUNT(*) total
//          	    FROM
//          	    filter
//          	    INNER JOIN
//          	    :Com_("D_T2_ITEM_META") m
//  			ON
//  			    filter.THERAPY_ID = m.ITEM_ID
//          	    WHERE
//  			    m.ITEM_TYPE = 'PRODUCT'
//  			    :queryStr
//  
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = condition;
  String __sJT_3 = condition;
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = queryStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      countIter = new CountIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:926^3*/
		
		int total = 0;
		while(countIter.next()) {
			total= countIter.total();
		}
		countIter.close();
		closeDefaultContext(myCtx);
		return total;
	}
	
	public List<Map> queryByRaw(String condition, String displayAllFlag, int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ProductIter productIter = null;

        String queryStr = "";
        if(TextUtil.isEmpty(displayAllFlag)){
            queryStr = " AND m.ENABLED = 1 ";
        }

		/*@lineinfo:generated-code*//*@lineinfo:947^3*/

//  ************************************************************
//  #sql [myCtx] productIter = { SELECT
//  	    *
//  	    FROM
//  	        (
//  	            SELECT
//  	                t.*,
//  	                ROWNUM rowNumber
//  	            FROM
//  	                (
//  			WITH
//  			    filter AS
//  			    (
//  			        SELECT DISTINCT
//  			            t.THERAPY_ID
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        WHERE
//  			            1=1
//  			        AND (
//  			                t.ITEM_ID = :condition
//  			            OR  t.ITEM_NAME = :condition)
//  			        ORDER BY
//  			            t.THERAPY_ID
//  			    ),
//  	    	    d AS
//  	    	    (
//      	    		 SELECT
//  	    	            ITEM_ID,
//  	    	            listagg('('||PRICE_TYPE||'|' || ITEM_PRICE ||')',',') within GROUP
//  	    	            (ORDER BY PRICE_TYPE) infos
//              		FROM
//              	    :Com_("D_T2_ITEM_PRICE") p
//  	            	WHERE
//  	            	    p.PRICE_TYPE LIKE 'SALE%'
//  	            	GROUP BY
//  	            	    ITEM_ID
//  	    	    )
//  			SELECT
//  			    m.*,
//  			    c.CATEGORY_NAME,
//  			    d.infos,
//  			    nvl(t.cost_price,0.0) cost_price,
//  			    1 item_count
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//      		LEFT JOIN
//  				d
//  			ON 
//  				d.ITEM_ID = m.ITEM_ID
//  			INNER JOIN
//  			    filter
//  			ON
//  			    filter.THERAPY_ID = m.ITEM_ID
//      		LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			LEFT JOIN
//  			    (
//  			        SELECT
//  			            t.THERAPY_ID,
//  			            nvl(ROUND(SUM(t.ITEM_GROSS_COUNT*p.ITEM_PRICE), 2),0.0) AS cost_price
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        LEFT JOIN
//  			        :Com_("D_T2_ITEM_PRICE") p
//  			        ON
//  			            p.ITEM_ID = t.ITEM_ID
//  			        AND p.PRICE_TYPE = 'PURCHASE'
//  			        GROUP BY
//  			            t.THERAPY_ID) t
//  			ON
//  			    t.THERAPY_ID = m.ITEM_ID
//  			WHERE
//  			    m.ITEM_TYPE = 'PRODUCT'
//  			    :queryStr
//  
//  			ORDER BY
//  			    m.ITEM_ID) t
//  	            WHERE
//                  ROWNUM < :endRow)
//      WHERE
//          rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = condition;
  String __sJT_3 = condition;
  String __sJT_4 = Com_("D_T2_ITEM_PRICE");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_7 = Com_("D_T2_THERAPY");
  String __sJT_8 = Com_("D_T2_ITEM_PRICE");
  String __sJT_9 = queryStr;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaQueryBean_SJProfileKeys.getKey(0), 11);
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
      productIter = new ProductIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:1030^31*/

		List<Map> itemLst = new ArrayList<Map>();
		while (productIter.next()) {
			itemLst.add(iterateProduct(productIter));
		}
		productIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	private Map iterateProduct(ProductIter itemIter) throws SQLException {
		Map header = new HashMap();
		header.put("itemId", itemIter.item_id());
		header.put("itemName", itemIter.item_name());
		header.put("itemType",itemIter.item_type());
		header.put("categoryId",itemIter.category_id());
		header.put("itemDimension", itemIter.item_dimension());
		header.put("queryCode",itemIter.query_code());
		header.put("categoryName",itemIter.category_name());
		header.put("enabled",itemIter.enabled());
		header.put("effectDate",SqlDateUtil.getUtilDate(itemIter.effect_date()));
		header.put("costPrice",itemIter.cost_price());
		Integer itemCount = itemIter.item_count();
		if (itemCount != null) {
			header.put("hasTherapy",true);
		}else{
			header.put("hasTherapy",false);
		}
		String itemSpecification=itemIter.item_specification();
		if(itemIter.item_type().equals(ItemType.PRODUCT)){
			try{
				header.put("bInHandPro",Integer.parseInt(itemSpecification.substring(0,1)));//手持下载
				header.put("bDisFood",Integer.parseInt(itemSpecification.substring(1,2)));//出品折扣
				header.put("bDisCount",Integer.parseInt(itemSpecification.substring(2,3)));//照单折扣
			}catch(Exception ex){
				header.put("bInHandPro",0);
				header.put("bDisFood",0);
				header.put("bDisCount",0);
			}
		}
		String info = itemIter.infos();
		if(!TextUtil.isEmpty(info)){
			String[] infos = info.toString().split(",");
			for (int i = 0; i < infos.length; i++) {
				String[] temp = infos[i].replace("(", "").replace(")", "").split("\\|");
				
				for (int j = 0; j < temp.length; j++) {
					header.put(temp[0], temp[1]);
				}
			}
		}
		
		return header;
}
}/*@lineinfo:generated-code*/class ItemMetaQueryBean_SJProfileKeys 
{
  private static ItemMetaQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ItemMetaQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ItemMetaQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.hq.ItemMetaQueryBean_SJProfile0");
  }
}
