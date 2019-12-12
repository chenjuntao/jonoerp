/*@lineinfo:filename=StockQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 25, 2014 by liyzh
 * Last edited on Nov 25, 2014 by liyzh
 * 
 * 说明： 查询库存
 */
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.ItemMeta;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.TextUtil;

import com.hp.hpl.sparta.Text;
import com.tanry.framework.acl.NoPrivilegeException;

public class StockQueryBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(StockQueryBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_typeNdx = findColumn("item_type");
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
    item_countNdx = findColumn("item_count");
    supplier_nameNdx = findColumn("supplier_name");
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public String supplier_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(supplier_nameNdx);
  }
  private int supplier_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^70*/
	
	/*@lineinfo:generated-code*//*@lineinfo:43^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class MrpItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MrpItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
    allocationNdx = findColumn("allocation");
    on_the_wayNdx = findColumn("on_the_way");
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
  public Double allocation() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(allocationNdx);
  }
  private int allocationNdx;
  public Double on_the_way() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(on_the_wayNdx);
  }
  private int on_the_wayNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:52^3*/
	
	/**
	 * 根据材料ID和门店ID获取相应的库存量
	 */
	public int count(String cateIds, String branchId, String storageId, String itemName,String types)
		throws NoPrivilegeException, SQLException, NoConnection {
		String query = " 1=1";
		if(!TextUtil.isEmpty(cateIds)){
			query = " c.CATEGORY_ID IN ('" + cateIds.replaceAll(",", "','") + "') ";
		}
		
		String q1 = null;
		if (types.equals("nonegtive")) q1 = " s.ITEM_COUNT >= 0 ";
		if (types.equals("negtive")) q1 = " s.ITEM_COUNT < 0 ";
		if (types.equals("all")) q1 = " 1 = 1 ";
		itemName = "%" + itemName + "%";
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:72^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			LEFT JOIN
//  			:Com_("D_T2_STORAGE") s
//  			ON
//  			    s.ITEM_ID = m.ITEM_ID
//  			AND s.STORAGE_ID = :storageId
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") sb
//  			ON
//  			    sb.ITEM_ID = m.ITEM_ID
//  			AND sb.BRANCH_ID = :branchId
//  			AND sb.PRIORITY = 0
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    sb.SUPPLIER_ID = b.BRANCH_ID
//  			WHERE
//  			    :query
//  			AND  m.ITEM_TYPE in ('RAW','SEMIS')
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//              AND
//              	:q1
//  			ORDER BY
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T2_STORAGE");
  String __sJT_4 = storageId;
  String __sJT_5 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_6 = branchId;
  String __sJT_7 = Com_("D_T2_BRANCH");
  String __sJT_8 = query;
  String __sJT_9 = itemName;
  String __sJT_10 = itemName;
  String __sJT_11 = q1;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StockQueryBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:106^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	/**
	 * 根据材料ID和门店ID获取相应的库存量
	 */
	public List<ItemMeta> queryStock(String cateIds, String branchId, String storageId, String itemName,String types, int startIndex, int endIndex)
		throws NoPrivilegeException, SQLException, NoConnection {
		String query = " 1=1";
		if(!TextUtil.isEmpty(cateIds)){
			query = " c.CATEGORY_ID IN ('" + cateIds.replaceAll(",", "','") + "') ";
		}
		itemName = "%" + itemName + "%";
		String q1 = "";
		if (types.equals("nonegtive")) q1 = " s.ITEM_COUNT >= 0 ";
		if (types.equals("negtive")) q1 = " s.ITEM_COUNT < 0 ";
		if (types.equals("all")) q1 = " 1 = 1 ";
		
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:128^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT * FROM ( SELECT t.*, ROWNUM rowNumber FROM (
//  				SELECT
//  				    m.*,
//  				    round(s.ITEM_COUNT,2) ITEM_COUNT,
//  				    b.BRANCH_NAME AS supplier_name
//  				FROM
//  				:Com_("D_T2_ITEM_META") m
//  				INNER JOIN
//  				:Com_("D_T2_ITEM_CATEGORY") c
//  				ON
//  				    c.CATEGORY_ID = m.CATEGORY_ID
//  				LEFT JOIN
//  				:Com_("D_T2_STORAGE") s
//  				ON
//  				    s.ITEM_ID = m.ITEM_ID
//  				AND s.STORAGE_ID = :storageId
//  				LEFT JOIN
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") sb
//  				ON
//  				    sb.ITEM_ID = m.ITEM_ID
//  				AND sb.BRANCH_ID = :branchId
//  				AND sb.PRIORITY = 0
//  				LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  				ON
//  				    sb.SUPPLIER_ID = b.BRANCH_ID
//  				WHERE
//  				    :query
//  				AND  m.ITEM_TYPE in ('RAW','SEMIS')
//  	            AND (
//  	                    m.ITEM_ID LIKE :itemName
//  	                OR  m.ITEM_NAME LIKE :itemName)
//  	            AND
//  	            	:q1
//  				ORDER BY
//  				    m.ITEM_ID
//  			) t WHERE ROWNUM < :endIndex) WHERE rowNumber >= :startIndex
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T2_STORAGE");
  String __sJT_4 = storageId;
  String __sJT_5 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_6 = branchId;
  String __sJT_7 = Com_("D_T2_BRANCH");
  String __sJT_8 = query;
  String __sJT_9 = itemName;
  String __sJT_10 = itemName;
  String __sJT_11 = q1;
  int __sJT_12 = endIndex;
  int __sJT_13 = startIndex;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StockQueryBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setInt(12, __sJT_12);
      __sJT_stmt.setInt(13, __sJT_13);
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

/*@lineinfo:user-code*//*@lineinfo:166^3*/
		List<ItemMeta> itemLst = processIter(itemIter);
		itemIter.close();
		
		closeDefaultContext(myCtx);
		return itemLst;
	}

	
	public List<ItemMeta> queryLcMrp(String cateIds, String branchId, String itemName)
			throws NoPrivilegeException, SQLException, NoConnection {
			String query = " 1=1";
			if(!TextUtil.isEmpty(cateIds)){
				 query = " m.CATEGORY_ID IN ('" + cateIds.replaceAll(",", "','") + "') ";
			}
			itemName = "%" + itemName + "%";
			
			DefaultContext myCtx = getDefaultContext();
			MrpItemIter itemIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:185^4*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  				    m.ITEM_ID,
//  				    m.ITEM_NAME,
//  				    m.QUERY_CODE,
//  				    m.CATEGORY_ID,
//  				    m.ITEM_DIMENSION,
//  				    m.ITEM_SPECIFICATION,
//  				    v1.ITEM_COUNT allocation,
//  				    v2.ITEM_COUNT on_the_way
//  				FROM
//  					:Com_("D_T2_ITEM_META") m
//  				LEFT JOIN
//  					:Com_("d_v_mrp_a_lc") v1
//  				ON
//  				    m.item_id = v1.item_id
//  				LEFT JOIN
//  					:Com_("d_v_mrp_o_lc") v2
//  				ON
//  				    m.item_id = v2.item_id
//  				WHERE
//  				    :query
//  				AND (
//  				        m.ITEM_ID LIKE :itemName
//  				    OR  m.ITEM_NAME LIKE :itemName)
//  				AND (
//  				        v1.ITEM_COUNT IS NOT NULL
//  				    OR  v2.item_count IS NOT NULL)
//  				ORDER BY
//  				    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("d_v_mrp_a_lc");
  String __sJT_3 = Com_("d_v_mrp_o_lc");
  String __sJT_4 = query;
  String __sJT_5 = itemName;
  String __sJT_6 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StockQueryBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new MrpItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:214^18*/
			List<ItemMeta> itemLst = processMrpIter(itemIter);
			itemIter.close();
			
			closeDefaultContext(myCtx);
			return itemLst;
		}
	
	public List<ItemMeta> queryCfMrp(String cateIds, String branchId, String itemName)
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " 1=1";
		if(!TextUtil.isEmpty(cateIds)){
			 query = " m.CATEGORY_ID IN ('" + cateIds.replaceAll(",", "','") + "') ";
		}
			itemName = "%" + itemName + "%";
			
			DefaultContext myCtx = getDefaultContext();
			MrpItemIter itemIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:232^4*/

//  ************************************************************
//  #sql [myCtx] itemIter = { WITH
//  				    temp_o AS
//  				    (
//  				        SELECT
//  				            d.FORM_ID,
//  				            d.ITEM_ID,
//  				            d.ITEM_COUNT,
//  				            id.FORM_ID ref_form_id,
//  				            id.RECEIVE_COUNT
//  				        FROM
//  				        :Com_("D_T1_PURCHASING_HEADER") h
//  				        INNER JOIN
//  				        :Com_("D_T0_FORM_STATUS") s
//  				        ON
//  				            h.FORM_ID = s.FORM_ID
//  				        AND s.STATUS IN ('已审核',
//  				                         '已入库')
//  				        AND s.IS_CURRENT = 1
//  				        INNER JOIN
//  				        :Com_("D_T1_PURCHASING_DETAIL") d
//  				        ON
//  				            h.FORM_ID = d.FORM_ID
//  				        AND h.REQUESTER_ID = :branchId
//  				        LEFT JOIN
//  				        :Com_("D_T1_INPUT_HEADER") ih
//  				        ON
//  				            d.FORM_ID = ih.FORM_REF_ID
//  				        AND ih.AUDIT_TIME IS NOT NULL
//  				        LEFT JOIN
//  				        :Com_("D_T1_INPUT_DETAIL") id
//  				        ON
//  				            ih.FORM_ID = id.FORM_ID
//  				        AND d.ITEM_ID = id.ITEM_ID
//  				    )
//  				    ,
//  				    temp2_o AS
//  				    (
//  				        SELECT
//  				            t.FORM_ID,
//  				            t.ITEM_ID,
//  				            DECODE(SIGN(t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))),-1,0,t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0)))       ontheway ,
//  				            listagg('('||t.ref_form_id ||','||t.RECEIVE_COUNT ||')',',') within GROUP (ORDER BY
//  				            t.ITEM_COUNT) infos
//  				        FROM
//  				            temp_o t
//  				        GROUP BY
//  				            t.FORM_ID,
//  				            t.ITEM_ID,
//  				            t.ITEM_COUNT
//  				    )
//  				    ,
//  				    temp3_o AS
//  				    (
//  				        SELECT
//  				            t.item_id,
//  				            SUM(NVL(t.ontheway,0)) ontheway
//  				        FROM
//  				            temp2_o t
//  				        GROUP BY
//  				            t.item_id
//  				    )
//  				    ,
//  				    temp_a AS
//  				    (
//  				        SELECT
//  				            i.FORM_ID,
//  				            i.ITEM_ID,
//  				            i.ITEM_COUNT,
//  				            rd.FORM_ID ref_form_id,
//  				            rd.RECEIVE_COUNT
//  				        FROM
//  				        :Com_("D_T1_WORK_ORDER_HEADER") h
//  				        INNER JOIN
//  				        :Com_("D_T0_FORM_STATUS") s
//  				        ON
//  				            h.FORM_ID = s.FORM_ID
//  				        AND s.IS_CURRENT = 1
//  				        AND s.STATUS = '已审核'
//  				        INNER JOIN
//  				        :Com_("D_T1_WORKORDER_ITEM") i
//  				        ON
//  				            h.FORM_ID = i.FORM_ID
//  				        LEFT JOIN
//  				        :Com_("D_T1_REQUISITION_HEADER") rh
//  				        ON
//  				            i.FORM_ID = rh.FORM_REF_ID
//  				        AND rh.AUDIT_TIME IS NOT NULL
//  				        LEFT JOIN
//  				        :Com_("D_T1_REQUISITION_DETAIL") rd
//  				        ON
//  				            rh.FORM_ID = rd.FORM_ID
//  				        AND i.ITEM_ID = rd.ITEM_ID
//  		        		WHERE
//  			        	    (
//  			        	        h.BATCH_FLAG='Y'
//  			        	    AND rh.AUDIT_TIME IS NULL)
//  			        	OR  (
//  			        	        h.BATCH_FLAG!='Y' )
//  				    )
//  				    ,
//  				    temp2_a AS
//  				    (
//  				        SELECT
//  				            t.FORM_ID,
//  				            t.ITEM_ID,
//  				            decode(sign(t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))),-1,0,t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))) Allocation ,
//  				            listagg('('||t.ref_form_id ||','||t.RECEIVE_COUNT ||')',',') within GROUP (ORDER BY
//  				            t.ITEM_COUNT) infos
//  				        FROM
//  				            temp_a t
//  				        GROUP BY
//  				            t.FORM_ID,
//  				            t.ITEM_ID,
//  				            t.ITEM_COUNT
//  				    )
//  				    ,
//  				    temp3_a AS
//  				    (
//  				        SELECT
//  				            t.item_id,
//  				            SUM(NVL(t.Allocation,0)) Allocation
//  				        FROM
//  				            temp2_a t
//  				        GROUP BY
//  				            t.item_id
//  				    )
//  				SELECT
//  				    m.ITEM_ID,
//  				    m.ITEM_NAME,
//  				    m.query_code,
//  				    m.ITEM_DIMENSION,
//  				    m.CATEGORY_ID,
//  				    m.item_specification,
//  				    NVL(o.ontheway,0)   on_the_way,
//  				    NVL(a.allocation,0) allocation
//  				FROM
//  				:Com_("D_T2_ITEM_META") m
//  				LEFT JOIN
//  				    temp3_o o
//  				ON
//  				    m.ITEM_ID = o.item_id
//  				LEFT JOIN
//  				    temp3_a a
//  				ON
//  				    m.ITEM_ID = a.item_id
//  				WHERE
//  					:query
//                  AND (
//                          m.ITEM_ID LIKE :itemName
//                      OR  m.ITEM_NAME LIKE :itemName)
//  				AND (
//  				        o.ontheway IS NOT NULL
//  				    OR  a.allocation IS NOT NULL)
//  				ORDER BY
//  				    m.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_4 = branchId;
  String __sJT_5 = Com_("D_T1_INPUT_HEADER");
  String __sJT_6 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_7 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_8 = Com_("D_T0_FORM_STATUS");
  String __sJT_9 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_10 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_11 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_12 = Com_("D_T2_ITEM_META");
  String __sJT_13 = query;
  String __sJT_14 = itemName;
  String __sJT_15 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StockQueryBean_SJProfileKeys.getKey(0), 3);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new MrpItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:387^18*/
			List<ItemMeta> itemLst = processMrpIter(itemIter);
			itemIter.close();
			
			closeDefaultContext(myCtx);
			return itemLst;
		}
	private List<ItemMeta> processIter(ItemIter itemIter) 
			throws SQLException {
		List<ItemMeta> itemLst = new ArrayList<ItemMeta>();
		while(itemIter.next()) {
			ItemMeta item = new ItemMeta();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setQueryCode(itemIter.query_code());
			item.setCategoryId(itemIter.category_id());
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			item.setItemCount(itemIter.item_count());
			item.setSupplierName(itemIter.supplier_name());
			itemLst.add(item);
		}
		return itemLst;
	}
	
	private List<ItemMeta> processMrpIter(MrpItemIter itemIter) 
			throws SQLException {
		List<ItemMeta> itemLst = new ArrayList<ItemMeta>();
		while(itemIter.next()) {
			ItemMeta item = new ItemMeta();
			item.setItemId(itemIter.item_id());
			item.setItemName(itemIter.item_name());
			item.setCategoryId(itemIter.category_id());
			item.setQueryCode(itemIter.query_code());
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			item.setAllocation(itemIter.allocation());
			item.setOnTheWay(itemIter.on_the_way());
			itemLst.add(item);
		}
		return itemLst;
	}
}/*@lineinfo:generated-code*/class StockQueryBean_SJProfileKeys 
{
  private static StockQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StockQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StockQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.StockQueryBean_SJProfile0");
  }
}
