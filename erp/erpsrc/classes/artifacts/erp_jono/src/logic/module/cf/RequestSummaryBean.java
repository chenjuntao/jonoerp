/*@lineinfo:filename=RequestSummaryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jul 3, 2015 by pw
 * Last edited on Jul 3, 2015 by pw
 * 
 * 说明： 中央工厂原料采购汇总生成
 */
package logic.module.cf;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.form.CollectDetail;
import pojo.form.RequestHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BranchCode;

public class RequestSummaryBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(RequestSummaryBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:42^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class AverageIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public AverageIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    avg_countNdx = findColumn("avg_count");
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
  public Double avg_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(avg_countNdx);
  }
  private int avg_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^20*/

	/**
	 * 查询日均要货量
	 */
	public List<Map> queryAverageRequest(java.util.Date startDate, java.util.Date endDate, String[] ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		long days = DateTimeUtil.diffDays(endDate, startDate) + 1;
		String query = " t.ITEM_ID IN ('" + StringUtils.join(ids, ",").replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		AverageIter avgIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:57^3*/

//  ************************************************************
//  #sql [myCtx] avgIter = { SELECT
//  			    t.ITEM_ID,
//  			    t.ITEM_NAME,
//  			    ROUND(SUM( d.PRODUCE_COUNT * t.ITEM_GROSS_COUNT),4) / :days as avg_count
//  			FROM
//  			:Com_("D_T1_ARRENGMENT_DETAIL") d
//  			INNER JOIN
//  			:Com_("D_T1_ARRENGMENT_HEADER") h
//  			ON
//  			    d.FORM_ID = h.FORM_ID
//  			INNER JOIN
//  			:Com_("D_T2_THERAPY") t
//  			ON
//  			    d.ITEM_ID = t.THERAPY_ID
//  			AND t.OWNER = 'CENTRALFACTORY'
//  			WHERE
//  			    h.FORM_TIME >= :sDate
//  			AND h.FORM_TIME <= :eDate
//  			AND	:query
//  			GROUP BY
//  			    t.ITEM_ID,
//  			    t.ITEM_NAME
//  			ORDER BY
//  			    t.ITEM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  long __sJT_1 = days;
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_4 = Com_("D_T2_THERAPY");
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  String __sJT_7 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setLong(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      avgIter = new AverageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:81^17*/
		
		List<Map> itemLst = new ArrayList<Map>();
		while(avgIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", avgIter.item_id());
			item.put("itemName", avgIter.item_name());
			item.put("avgCount", avgIter.avg_count());
			itemLst.add(item);
		}
		avgIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:96^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class MRPIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MRPIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    shelf_nameNdx = findColumn("shelf_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    category_nameNdx = findColumn("category_name");
    item_unit_priceNdx = findColumn("item_unit_price");
    item_priceNdx = findColumn("item_price");
    order_countNdx = findColumn("order_count");
    allocationNdx = findColumn("allocation");
    real_countNdx = findColumn("real_count");
    on_the_wayNdx = findColumn("on_the_way");
    min_order_countNdx = findColumn("min_order_count");
    supply_periodNdx = findColumn("supply_period");
    inventorys_cycleNdx = findColumn("inventorys_cycle");
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double allocation() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(allocationNdx);
  }
  private int allocationNdx;
  public Double real_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(real_countNdx);
  }
  private int real_countNdx;
  public Double on_the_way() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(on_the_wayNdx);
  }
  private int on_the_wayNdx;
  public Double min_order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(min_order_countNdx);
  }
  private int min_order_countNdx;
  public Double supply_period() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(supply_periodNdx);
  }
  private int supply_periodNdx;
  public Double inventorys_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventorys_cycleNdx);
  }
  private int inventorys_cycleNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:98^111*/
			
	public List<Map> queryMRP(String cfCode, String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " d.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		MRPIter mrpIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:106^3*/

//  ************************************************************
//  #sql [myCtx] mrpIter = { with shelf_one AS
//  			    (
//  		    		 SELECT
//  			             s.ITEM_ID,
//  			             s.SHELF_ID,
//  			             ts.SHELF_NAME
//  			         FROM
//  			         :Com_("D_T2_SHELF_ITEM") s
//  			         LEFT JOIN
//  			         :Com_("D_T2_SHELF") ts
//  			         ON
//  			             ts.SHELF_ID =s.SHELF_ID
//  			         WHERE
//  			             s.PRIORITY =0
//  			    ) 
//  			SELECT
//  			    i.*,
//  			    m.ITEM_NAME,
//  			    so.SHELF_NAME,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    c.CATEGORY_ID item_category,
//  			    c.CATEGORY_NAME,
//  			    NVL(p.ITEM_PRICE, p2.ITEM_PRICE) AS ITEM_PRICE,
//  			    p3.ITEM_PRICE                    AS ITEM_UNIT_PRICE,
//  			    ma.REAL_COUNT,
//  			    ma.MIN_ORDER_COUNT,
//  			    ma.PERIOD supply_period,
//  			    ma.inventorysCycle inventorys_cycle
//  			FROM
//  			    (
//  			        WITH
//  			            semisItems AS
//  			            (
//  			                SELECT
//  			                    t.ITEM_ID,
//  			                    m.ITEM_TYPE,
//  			                    ROUND(SUM(d.PRODUCE_COUNT * t.ITEM_GROSS_COUNT),4) order_count
//  			                FROM
//  			                :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			                INNER JOIN
//  			                :Com_("D_T2_THERAPY") t
//  			                ON
//  			                    d.ITEM_ID = t.THERAPY_ID
//  			                AND t.OWNER = 'CENTRALFACTORY'
//  			                INNER JOIN
//  			                :Com_("D_T2_ITEM_META") m
//  			                ON
//  			                    t.ITEM_ID = m.ITEM_ID
//  			                WHERE
//  			                    :query
//  			                AND m.ITEM_TYPE = 'SEMIS'
//  			                GROUP BY
//  			                    t.ITEM_ID,
//  			                    m.ITEM_TYPE
//  			                ORDER BY
//  			                    t.ITEM_ID
//  			            )
//  			            ,
//  			            rawItems AS
//  			            (
//  			                SELECT
//  			                    t.ITEM_ID,
//  			                    ROUND(SUM(d.PRODUCE_COUNT * t.ITEM_GROSS_COUNT),4) order_count
//  			                FROM
//  			                :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			                INNER JOIN
//  			                :Com_("D_T2_THERAPY") t
//  			                ON
//  			                    d.ITEM_ID = t.THERAPY_ID
//  			                AND t.OWNER = 'CENTRALFACTORY'
//  			                INNER JOIN
//  			                :Com_("D_T2_ITEM_META") m
//  			                ON
//  			                    t.ITEM_ID = m.ITEM_ID
//  			                WHERE
//  			                    :query
//  			                AND m.ITEM_TYPE = 'RAW'
//  			                GROUP BY
//  			                    t.ITEM_ID
//  			                ORDER BY
//  			                    t.ITEM_ID
//  			            )
//  			            ,
//  			            temp_a AS
//  			            (
//  			                SELECT
//  			                    i.FORM_ID,
//  			                    i.ITEM_ID,
//  			                    i.ITEM_COUNT,
//  			                    rd.FORM_ID ref_form_id,
//  			                    rd.RECEIVE_COUNT
//  			                FROM
//  			                :Com_("D_T1_WORK_ORDER_HEADER") h
//  			                INNER JOIN
//  			                :Com_("D_T0_FORM_STATUS") s
//  			                ON
//  			                    h.FORM_ID = s.FORM_ID
//  			                AND s.IS_CURRENT = 1
//  			                AND s.STATUS = '已审核'
//  			                INNER JOIN
//  			                :Com_("D_T1_WORKORDER_ITEM") i
//  			                ON
//  			                    h.FORM_ID = i.FORM_ID
//  			                LEFT JOIN
//  			                :Com_("D_T1_REQUISITION_HEADER") rh
//  			                ON
//  			                    i.FORM_ID = rh.FORM_REF_ID
//  			                AND rh.AUDIT_TIME IS NOT NULL
//  			                LEFT JOIN
//  			                :Com_("D_T1_REQUISITION_DETAIL") rd
//  			                ON
//  			                    rh.FORM_ID = rd.FORM_ID
//  			                AND i.ITEM_ID = rd.ITEM_ID
//  	                		WHERE
//  		                	    (
//  		                	        h.BATCH_FLAG='Y'
//  		                	    AND rh.AUDIT_TIME IS NULL)
//  		                	OR  (
//  		                	        h.BATCH_FLAG!='Y' )
//  			            )
//  			            ,
//  			            temp2_a AS
//  			            (
//  			                SELECT
//  			                    t.FORM_ID,
//  			                    t.ITEM_ID,
//  			                    decode(sign(t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))),-1,0,t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))) Allocation ,
//  			                    listagg('('||t.ref_form_id ||','||t.RECEIVE_COUNT ||')',',') within GROUP
//  			                    (ORDER BY t.ITEM_COUNT) infos
//  			                FROM
//  			                    temp_a t
//  			                GROUP BY
//  			                    t.FORM_ID,
//  			                    t.ITEM_ID,
//  			                    t.ITEM_COUNT
//  			            )
//  			            ,
//  			            temp3_a AS
//  			            (
//  			                SELECT
//  			                    t.item_id,
//  			                    SUM(NVL(t.Allocation,0)) Allocation
//  			                FROM
//  			                    temp2_a t
//  			                GROUP BY
//  			                    t.item_id
//  			            )
//  			            ,
//  			            temp_o AS
//  			            (
//  			                SELECT
//  			                    d.FORM_ID,
//  			                    d.ITEM_ID,
//  			                    d.ITEM_COUNT,
//  			                    id.FORM_ID ref_form_id,
//  			                    id.RECEIVE_COUNT
//  			                FROM
//  			                :Com_("D_T1_PURCHASING_HEADER") h
//  			                INNER JOIN
//  			                :Com_("D_T0_FORM_STATUS") s
//  			                ON
//  			                    h.FORM_ID = s.FORM_ID
//  			                AND s.STATUS IN ('已审核',
//  			                                 '已入库')
//  			                AND s.IS_CURRENT = 1
//  			                INNER JOIN
//  			                :Com_("D_T1_PURCHASING_DETAIL") d
//  			                ON
//  			                    h.FORM_ID = d.FORM_ID
//  			                AND h.REQUESTER_ID = :cfCode
//  			                LEFT JOIN
//  			                :Com_("D_T1_INPUT_HEADER") ih
//  			                ON
//  			                    d.FORM_ID = ih.FORM_REF_ID
//  			                AND ih.AUDIT_TIME IS NOT NULL
//  			                LEFT JOIN
//  			                :Com_("D_T1_INPUT_DETAIL") id
//  			                ON
//  			                    ih.FORM_ID = id.FORM_ID
//  			                AND d.ITEM_ID = id.ITEM_ID
//  			            )
//  			            ,
//  			            temp2_o AS
//  			            (
//  			                SELECT
//  			                    t.FORM_ID,
//  			                    t.ITEM_ID,
//  			                    DECODE(SIGN(t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))),-1,0,t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0)))       ontheway ,
//  			                    listagg('('||t.ref_form_id ||','||t.RECEIVE_COUNT ||')',',') within GROUP
//  			                    (ORDER BY t.ITEM_COUNT) infos
//  			                FROM
//  			                    temp_o t
//  			                GROUP BY
//  			                    t.FORM_ID,
//  			                    t.ITEM_ID,
//  			                    t.ITEM_COUNT
//  			            )
//  			            ,
//  			            temp3_o AS
//  			            (
//  			                SELECT
//  			                    t.item_id,
//  			                    SUM(NVL(t.ontheway,0)) ontheway
//  			                FROM
//  			                    temp2_o t
//  			                GROUP BY
//  			                    t.item_id
//  			            )
//  			            ,
//  			            temp_info AS
//  			            (
//  			                SELECT
//  			                    t.item_id,
//  			                    SUM(order_count)order_count
//  			                FROM
//  			                    (
//  			                        SELECT
//  			                            t.ITEM_ID,
//  			                            ROUND(SUM(si.order_count * t.ITEM_GROSS_COUNT),4) order_count
//  			                        FROM
//  			                            semisItems si
//  			                        INNER JOIN
//  			                        :Com_("D_T2_THERAPY") t
//  			                        ON
//  			                            si.ITEM_ID = t.THERAPY_ID
//  			                        AND t.OWNER = 'CENTRALFACTORY'
//  			                        GROUP BY
//  			                            t.ITEM_ID
//  			                        UNION ALL
//  			                        SELECT
//  			                            *
//  			                        FROM
//  			                            rawItems) t
//  			                GROUP BY
//  			                    t.item_id
//  			            )
//  			        SELECT
//  			            t.*,
//  			            Allocation,
//  			            ontheway on_the_way
//  			        FROM
//  			            temp_info t
//  			        LEFT JOIN
//  			            temp3_a a
//  			        ON
//  			            t.item_id = a.item_id
//  			        LEFT JOIN
//  			            temp3_o o
//  			        ON
//  			            t.item_id = o.item_id )i
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    i.ITEM_ID = m.ITEM_ID
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    m.CATEGORY_ID = c.CATEGORY_ID
//  			LEFT JOIN
//  				:Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			ON
//  			    s.BRANCH_ID = :cfCode
//  			AND s.ITEM_ID = i.ITEM_ID
//  			AND s.PRIORITY = 0
//  			LEFT JOIN
//  				:Com_("D_V_MRP_AMOUNT_CF") ma
//  			ON
//  			    ma.BRANCH_ID = :cfCode
//  			AND ma.ITEM_ID = i.item_id
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.PRICE_TYPE = 'SUPPLIER'
//  			AND p.SUPPLIER_ID = ma.SUPPLIER_ID
//  			AND p.ITEM_ID = i.ITEM_ID
//  			AND p.IS_CURRENT =1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p2
//  			ON
//  			    p2.PRICE_TYPE = 'PURCHASE'
//  			AND p2.ITEM_ID = i.ITEM_ID
//  			AND p2.IS_CURRENT =1
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p3
//  			ON
//  			    p3.PRICE_TYPE = 'BENCHMARK'
//  			AND p3.ITEM_ID = i.ITEM_ID
//  			AND p3.IS_CURRENT =1
//  			LEFT JOIN
//  			 	shelf_one so
//  			ON
//  			    so.ITEM_ID=i.ITEM_ID
//  			ORDER BY
//  			    so.SHELF_ID,
//  			    i.item_id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SHELF_ITEM");
  String __sJT_2 = Com_("D_T2_SHELF");
  String __sJT_3 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_4 = Com_("D_T2_THERAPY");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = query;
  String __sJT_7 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_8 = Com_("D_T2_THERAPY");
  String __sJT_9 = Com_("D_T2_ITEM_META");
  String __sJT_10 = query;
  String __sJT_11 = Com_("D_T1_WORK_ORDER_HEADER");
  String __sJT_12 = Com_("D_T0_FORM_STATUS");
  String __sJT_13 = Com_("D_T1_WORKORDER_ITEM");
  String __sJT_14 = Com_("D_T1_REQUISITION_HEADER");
  String __sJT_15 = Com_("D_T1_REQUISITION_DETAIL");
  String __sJT_16 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_17 = Com_("D_T0_FORM_STATUS");
  String __sJT_18 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_19 = cfCode;
  String __sJT_20 = Com_("D_T1_INPUT_HEADER");
  String __sJT_21 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_22 = Com_("D_T2_THERAPY");
  String __sJT_23 = Com_("D_T2_ITEM_META");
  String __sJT_24 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_25 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_26 = cfCode;
  String __sJT_27 = Com_("D_V_MRP_AMOUNT_CF");
  String __sJT_28 = cfCode;
  String __sJT_29 = Com_("D_T2_ITEM_PRICE");
  String __sJT_30 = Com_("D_T2_ITEM_PRICE");
  String __sJT_31 = Com_("D_T2_ITEM_PRICE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setString(27, __sJT_27);
      __sJT_stmt.setString(28, __sJT_28);
      __sJT_stmt.setString(29, __sJT_29);
      __sJT_stmt.setString(30, __sJT_30);
      __sJT_stmt.setString(31, __sJT_31);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      mrpIter = new MRPIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:402^17*/
		List<Map> itemLst = new ArrayList<Map>();
		while(mrpIter.next()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("itemId", mrpIter.item_id());
			item.put("itemName", mrpIter.item_name());
			item.put("shelfName", mrpIter.shelf_name());
			item.put("itemCategory", mrpIter.item_category());
			item.put("categoryName", mrpIter.category_name());
			item.put("itemDimension", mrpIter.item_dimension());
			item.put("itemSpecification", mrpIter.item_specification());
			item.put("itemUnitPrice", mrpIter.item_unit_price());
			item.put("itemPrice", mrpIter.item_price());
			item.put("requestCount", mrpIter.order_count());
			item.put("minOrderCount", mrpIter.min_order_count());
			item.put("realCount", mrpIter.real_count());
			item.put("allocation", mrpIter.allocation());
			item.put("roadCount", mrpIter.on_the_way());
			item.put("supplyPeriod", mrpIter.supply_period());
			item.put("inventorysCycle", mrpIter.inventorys_cycle());
			itemLst.add(item);
		}
		mrpIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:429^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DetailIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DetailIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    supply_periodNdx = findColumn("supply_period");
    inventorys_cycleNdx = findColumn("inventorys_cycle");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_unit_priceNdx = findColumn("item_unit_price");
    item_priceNdx = findColumn("item_price");
    order_countNdx = findColumn("order_count");
    min_order_countNdx = findColumn("min_order_count");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
  }
  public Integer supply_period() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(supply_periodNdx);
  }
  private int supply_periodNdx;
  public Double inventorys_cycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(inventorys_cycleNdx);
  }
  private int inventorys_cycleNdx;
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
  public Double item_unit_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_unit_priceNdx);
  }
  private int item_unit_priceNdx;
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
  public Double order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(order_countNdx);
  }
  private int order_countNdx;
  public Double min_order_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(min_order_countNdx);
  }
  private int min_order_countNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:443^3*/
	
	
	public List<Map> querySummary(String cfCode, String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " d.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:452^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    i.*,
//  			    m.ITEM_NAME,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    c.CATEGORY_ID item_category,
//  			    c.CATEGORY_NAME,
//  			    NVL(p.ITEM_PRICE, p2.ITEM_PRICE) AS ITEM_PRICE,
//  			    p3.ITEM_PRICE AS ITEM_UNIT_PRICE,
//  			    s.SUPPLIER_ID AS PROVIDER_ID,
//  			    b.BRANCH_NAME AS PROVIDER ,
//  			    cf.MIN_ORDER_COUNT,
//  			    cf.PERIOD         supply_period,
//  			    cf.inventorysCycle inventorys_cycle
//  			FROM
//  		    (
//  		        WITH
//  		            semisItems AS
//  		            (
//  		                SELECT
//  		                    t.ITEM_ID,
//  		                    m.ITEM_TYPE,
//  		                    ROUND(SUM(d.PRODUCE_COUNT * t.ITEM_GROSS_COUNT),4) order_count
//  		                FROM
//  		                :Com_("D_T1_ARRENGMENT_DETAIL") d
//  		                INNER JOIN
//  		                :Com_("D_T2_THERAPY") t
//  		                ON
//  		                    d.ITEM_ID = t.THERAPY_ID
//  		                AND t.OWNER = 'CENTRALFACTORY'
//  		                INNER JOIN
//  		                :Com_("D_T2_ITEM_META") m
//  		                ON
//  		                    t.ITEM_ID = m.ITEM_ID
//  		                WHERE
//  		                	:query
//  		                AND m.ITEM_TYPE = 'SEMIS'
//  		                GROUP BY
//  		                    t.ITEM_ID,
//  		                    m.ITEM_TYPE
//  		                ORDER BY
//  		                    t.ITEM_ID
//  		            )
//  		            ,
//  		            rawItems AS
//  		            (
//  		                SELECT
//  		                    t.ITEM_ID,
//  		                    ROUND(SUM(d.PRODUCE_COUNT * t.ITEM_GROSS_COUNT),4) order_count
//  		                FROM
//  		                :Com_("D_T1_ARRENGMENT_DETAIL") d
//  		                INNER JOIN
//  		                :Com_("D_T2_THERAPY") t
//  		                ON
//  		                    d.ITEM_ID = t.THERAPY_ID
//  		                AND t.OWNER = 'CENTRALFACTORY'
//  		                INNER JOIN
//  		                :Com_("D_T2_ITEM_META") m
//  		                ON
//  		                    t.ITEM_ID = m.ITEM_ID
//  		                WHERE
//  		                	:query
//  		                AND m.ITEM_TYPE = 'RAW'
//  		                GROUP BY
//  		                    t.ITEM_ID
//  		                ORDER BY
//  		                    t.ITEM_ID
//  		            )
//  		        SELECT
//  		            t.item_id,
//  		            SUM(order_count)order_count
//  		        FROM
//  		            (
//  		                SELECT
//  		                    t.ITEM_ID,
//  		                    ROUND(SUM(si.order_count * t.ITEM_GROSS_COUNT),4) order_count
//  		                FROM
//  		                    semisItems si
//  		                INNER JOIN
//  		                :Com_("D_T2_THERAPY") t
//  		                ON
//  		                    si.ITEM_ID = t.THERAPY_ID
//  		                AND t.OWNER = 'CENTRALFACTORY'
//  		                GROUP BY
//  		                    t.ITEM_ID
//  		                UNION ALL
//  		                SELECT
//  		                    *
//  		                FROM
//  		                    rawItems) t
//  		        GROUP BY
//  		            t.item_id
//  		        ORDER BY
//  		            t.item_id)  i
//  			LEFT JOIN
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM") s
//  			ON
//  			    s.BRANCH_ID = :cfCode
//  			AND s.ITEM_ID = i.ITEM_ID
//  			AND s.PRIORITY = 0
//  					LEFT JOIN
//  					:Com_("D_T2_ITEM_PRICE") p
//  				ON
//  				    p.PRICE_TYPE = 'SUPPLIER'
//  				AND p.SUPPLIER_ID = s.SUPPLIER_ID
//  				AND p.ITEM_ID = i.ITEM_ID
//  				AND p.IS_CURRENT =1
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p2
//  				ON
//  				    p2.PRICE_TYPE = 'PURCHASE'
//  				AND p2.ITEM_ID = i.ITEM_ID
//  				AND p2.IS_CURRENT =1
//  				LEFT JOIN
//  				:Com_("D_T2_ITEM_PRICE") p3
//  			ON
//  			    p3.PRICE_TYPE = 'BENCHMARK'
//  			AND p3.ITEM_ID = i.ITEM_ID
//  			AND p3.IS_CURRENT =1
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = s.SUPPLIER_ID
//  			LEFT JOIN
//  				:Com_("D_V_MRP_AMOUNT_CF") cf
//  			ON
//  				cf.BRANCH_ID = :cfCode
//  			AND cf.ITEM_ID = i.item_id
//  		  INNER JOIN
//  		  :Com_("D_T2_ITEM_META") m
//  		  ON
//  		    i.ITEM_ID = m.ITEM_ID
//  		  INNER JOIN
//  		  :Com_("D_T2_ITEM_CATEGORY") c
//  		  ON
//  		    m.CATEGORY_ID = c.CATEGORY_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_2 = Com_("D_T2_THERAPY");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = query;
  String __sJT_5 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_6 = Com_("D_T2_THERAPY");
  String __sJT_7 = Com_("D_T2_ITEM_META");
  String __sJT_8 = query;
  String __sJT_9 = Com_("D_T2_THERAPY");
  String __sJT_10 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_11 = cfCode;
  String __sJT_12 = Com_("D_T2_ITEM_PRICE");
  String __sJT_13 = Com_("D_T2_ITEM_PRICE");
  String __sJT_14 = Com_("D_T2_ITEM_PRICE");
  String __sJT_15 = Com_("D_T2_BRANCH");
  String __sJT_16 = Com_("D_V_MRP_AMOUNT_CF");
  String __sJT_17 = cfCode;
  String __sJT_18 = Com_("D_T2_ITEM_META");
  String __sJT_19 = Com_("D_T2_ITEM_CATEGORY");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestSummaryBean_SJProfileKeys.getKey(0), 2);
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
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      detailIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:589^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<Map> processDetailIter(DetailIter detailIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("supplyPeriod", detailIter.supply_period());
			detail.put("inventorysCycle", detailIter.inventorys_cycle());
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemCategory", detailIter.item_category());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("itemSpecification", detailIter.item_specification());
			
			Double orderCount = detailIter.order_count();
			Double itemUnitPrice = detailIter.item_unit_price();
			Double minOrderCount = detailIter.min_order_count();
			Double payAmt = 0.0;
			if (itemUnitPrice != null) {
				payAmt = orderCount * itemUnitPrice;
				
				 if(itemUnitPrice == 0){
						payAmt = 0.0;
				}
			}
			Double itemPrice = detailIter.item_price();
			Double unitAmt = 0.0;
			if (itemPrice != null) {
				unitAmt = orderCount * itemPrice;
				 if(itemPrice == 0){
						unitAmt = 0.0;
				}
			}
			detail.put("orderCount", orderCount);
			detail.put("itemUnitPrice", itemUnitPrice);
			detail.put("payAmt", payAmt);
			detail.put("itemPrice", itemPrice);
			detail.put("unitAmt", unitAmt);
			detail.put("providerId", detailIter.provider_id());
			detail.put("provider", detailIter.provider());
			detail.put("minOrderCount",minOrderCount);
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class RequestSummaryBean_SJProfileKeys 
{
  private static RequestSummaryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestSummaryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestSummaryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.cf.RequestSummaryBean_SJProfile0");
  }
}
