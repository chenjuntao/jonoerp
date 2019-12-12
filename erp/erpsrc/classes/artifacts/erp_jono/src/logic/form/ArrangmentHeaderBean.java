/*@lineinfo:filename=ArrangmentHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Nov 04 17:07:13 CST 2014 by pw
 * Last edited on Tue Nov 04 17:07:13 CST 2014 by pw
 * 
 * comment: 中央工厂生产计划/排程单表头
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ArrangmentHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchCode;

public class ArrangmentHeaderBean extends ConnectionPool {
	
	private static final int UN_AUDIT = 1;
	private static final int AUDIT_ED = 2;
	private static final int UN_OUT = 3;

	public static Logger log = Logger.getLogger(ArrangmentHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:44^2*/

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
    form_ref_idNdx = findColumn("form_ref_id");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    purchase_statusNdx = findColumn("purchase_status");
    statusNdx = findColumn("status");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String form_maker_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_maker_idNdx);
  }
  private int form_maker_idNdx;
  public String form_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_makerNdx);
  }
  private int form_makerNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public String auditor_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditor_idNdx);
  }
  private int auditor_idNdx;
  public String auditor() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditorNdx);
  }
  private int auditorNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public String purchase_status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(purchase_statusNdx);
  }
  private int purchase_statusNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:46^76*/
	
	/*@lineinfo:generated-code*//*@lineinfo:48^2*/

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
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_countNdx = findColumn("item_count");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
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

/*@lineinfo:user-code*//*@lineinfo:50^59*/
	
	/*@lineinfo:generated-code*//*@lineinfo:52^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class SummaryIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SummaryIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    itemIdNdx = findColumn("itemId");
    itemNameNdx = findColumn("itemName");
    itemDimensionNdx = findColumn("itemDimension");
    itemDimension2Ndx = findColumn("itemDimension2");
    deliveryFactorNdx = findColumn("deliveryFactor");
    itemSpecificationNdx = findColumn("itemSpecification");
    workshopNdx = findColumn("workshop");
    itemCategoryNameNdx = findColumn("itemCategoryName");
    itemCountNdx = findColumn("itemCount");
    itemUnitPriceNdx = findColumn("itemUnitPrice");
    productionCycleNdx = findColumn("productionCycle");
    businessDateNdx = findColumn("businessDate");
    completeTimeNdx = findColumn("completeTime");
    minOrderCountNdx = findColumn("minOrderCount");
    suggestAmtNdx = findColumn("suggestAmt");
  }
  public String itemId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemIdNdx);
  }
  private int itemIdNdx;
  public String itemName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemNameNdx);
  }
  private int itemNameNdx;
  public String itemDimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemDimensionNdx);
  }
  private int itemDimensionNdx;
  public String itemDimension2() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemDimension2Ndx);
  }
  private int itemDimension2Ndx;
  public Double deliveryFactor() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(deliveryFactorNdx);
  }
  private int deliveryFactorNdx;
  public String itemSpecification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemSpecificationNdx);
  }
  private int itemSpecificationNdx;
  public String workshop() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(workshopNdx);
  }
  private int workshopNdx;
  public String itemCategoryName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(itemCategoryNameNdx);
  }
  private int itemCategoryNameNdx;
  public Double itemCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemCountNdx);
  }
  private int itemCountNdx;
  public Double itemUnitPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemUnitPriceNdx);
  }
  private int itemUnitPriceNdx;
  public Double productionCycle() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(productionCycleNdx);
  }
  private int productionCycleNdx;
  public Date businessDate() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(businessDateNdx);
  }
  private int businessDateNdx;
  public Date completeTime() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(completeTimeNdx);
  }
  private int completeTimeNdx;
  public Double minOrderCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(minOrderCountNdx);
  }
  private int minOrderCountNdx;
  public Double suggestAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(suggestAmtNdx);
  }
  private int suggestAmtNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:55^60*/
	
	
	public List<Map> querySummary(String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " h.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		String orderClause = " sb.SUPPLIER_ID, t.ITEM_ID ";
		String lcCode = BranchCode.DEFAULT_LOGISTICSCENTER;
		
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:66^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    sb.SUPPLIER_ID AS PROVIDER_ID,
//  			    b.BRANCH_NAME  AS PROVIDER,
//  			    h.FORM_ID,
//  			    t.ITEM_ID ,
//  			    t.ITEM_NAME ,
//  			    m.ITEM_DIMENSION ,
//  			    SUM( NVL(d.PRODUCE_COUNT * t.ITEM_GROSS_COUNT,0)) ITEM_COUNT ,
//  			    m.ITEM_SPECIFICATION
//  			FROM
//  			    :Com_("D_T1_ARRENGMENT_HEADER") h
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			LEFT JOIN
//  			    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			ON
//  			    d.FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_THERAPY") t
//  			ON
//  			    d.ITEM_ID = t.THERAPY_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_META") m
//  			ON
//  			    t.ITEM_ID = m.ITEM_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_SUPPLIER_BRANCH_ITEM") sb
//  			ON
//  			    sb.ITEM_ID = t.ITEM_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = sb.SUPPLIER_ID
//  		    WHERE
//  	    		:query
//  				AND sb.PRIORITY = 0
//  				AND sb.BRANCH_ID = :lcCode
//  				AND s.STATUS = '已审核'
//  			GROUP BY
//  			    sb.SUPPLIER_ID ,
//  			    b.BRANCH_NAME,
//  			    h.FORM_ID,
//  			    t.ITEM_ID ,
//  			    t.ITEM_NAME ,
//  			    m.ITEM_DIMENSION ,
//  			    m.ITEM_SPECIFICATION
//  			ORDER BY
//  				:orderClause
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_4 = Com_("D_T2_THERAPY");
  String __sJT_5 = Com_("D_T2_ITEM_META");
  String __sJT_6 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_7 = Com_("D_T2_BRANCH");
  String __sJT_8 = query;
  String __sJT_9 = lcCode;
  String __sJT_10 = orderClause;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:117^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	public List<Map> queryEstimateSummary(String cfCode, String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " d.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		SummaryIter summaryIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:130^3*/

//  ************************************************************
//  #sql [myCtx] summaryIter = { WITH
//  			    purchase AS
//  			    (
//  			        SELECT
//  			            d.ITEM_ID ,
//  			            SUM(d.ITEM_COUNT) itemCount
//  			        FROM
//  			            :Com_("D_T1_PURCHASING_HEADER") h
//  			        LEFT JOIN
//  			            :Com_("D_T1_PURCHASING_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        LEFT JOIN
//  			            :Com_("D_T0_FORM_STATUS") s
//  			        ON
//  			            h.FORM_ID = s.FORM_ID
//  			        WHERE
//  			            h.PROVIDER_ID = :cfCode
//  			        AND s.STATUS = '已审核'
//  			        AND s.IS_CURRENT = 1
//  			        AND :query
//  			        GROUP BY
//  			            d.ITEM_ID
//  			        ORDER BY
//  			            d.ITEM_ID
//  			    )
//  			SELECT
//  			    p.item_id            itemId,
//  			    m.ITEM_NAME          itemName,
//  			    c.CATEGORY_NAME      itemCategoryName,
//  			    NVL(ip.ITEM_PRICE,0) itemUnitPrice ,
//  			    NVL(fw.WORKSHOP,fws.WORKSHOP)          workshop,
//  			    p.itemCount ,
//  			    m.ITEM_SPECIFICATION                                 itemSpecification,
//  			    m.ITEM_DIMENSION                                         itemDimension,
//  			    u.DELIVERY_UNIT                                         itemDimension2,
//  			    u.DELIVERY_FACTOR                                        deliveryFactor,
//  			    bd.BUSINESS_DATE                                          businessDate,
//  			    NVL(pc.PRODUCTION_CYCLE,0)                                productionCycle,
//  			    bd.BUSINESS_DATE + NVL(pc.PRODUCTION_CYCLE,0)             completeTime,
//  			    ceil(u.MIN_ORDER_COUNT / u.DELIVERY_FACTOR)             minOrderCount,
//  			    ceil(p.itemCount / u.DELIVERY_FACTOR) suggestAmt
//  			FROM
//  			    purchase p
//  			LEFT JOIN
//  			    :Com_("D_T2_BRANCH") bd
//  			ON
//  			    bd.BRANCH_ID = :cfCode
//  			LEFT JOIN
//  			    :Com_("D_T2_PRODUCTION_CYCLE") pc
//  			ON
//  			    pc.ITEM_ID = p.item_id
//  			LEFT JOIN
//  			    :Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    p.item_id = u.ITEM_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_META") m
//  			ON
//  			    p.item_id = m.ITEM_ID
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    m.CATEGORY_ID = c.CATEGORY_ID
//  	    	LEFT JOIN
//  	    	    :Com_("D_T2_ITEM_WORKSHOP") wp
//  	    	ON
//  	    	    p.ITEM_ID=wp.ITEM_ID
//  	    	LEFT JOIN
//  	    	    :Com_("D_T2_FACTORY_WORKSHOP") fw
//  	    	ON
//  	    	    fw.WORK_ORDER_ID=wp.WORK_ORDER_ID
//      		LEFT JOIN
//  	    	    :Com_("D_T2_FACTORY_WORKSHOP") fws
//  	    	ON
//  	    	    fws.FACTORY_ID ='F00'
//  	    	AND fws.PRIORITY ='0'
//  			LEFT JOIN
//  			    :Com_("D_T2_ITEM_PRICE") ip
//  			ON
//  			    p.item_id = ip.ITEM_ID
//  			AND ip.IS_CURRENT = 1
//  			AND ip.PRICE_TYPE = 'BENCHMARK'
//  			ORDER BY
//  				productionCycle  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = cfCode;
  String __sJT_5 = query;
  String __sJT_6 = Com_("D_T2_BRANCH");
  String __sJT_7 = cfCode;
  String __sJT_8 = Com_("D_T2_PRODUCTION_CYCLE");
  String __sJT_9 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_10 = Com_("D_T2_ITEM_META");
  String __sJT_11 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_12 = Com_("D_T2_ITEM_WORKSHOP");
  String __sJT_13 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_14 = Com_("D_T2_FACTORY_WORKSHOP");
  String __sJT_15 = Com_("D_T2_ITEM_PRICE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 1);
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
      summaryIter = new SummaryIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:215^21*/
		List<Map> detailLst = processSummaryIter(summaryIter);
		summaryIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}
	
	private List<Map> processSummaryIter(SummaryIter summaryIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(summaryIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("itemId", summaryIter.itemId());
			detail.put("itemName", summaryIter.itemName());
			detail.put("itemDimension", summaryIter.itemDimension());
			detail.put("itemDimension2", summaryIter.itemDimension2());
			detail.put("deliveryFactor", summaryIter.deliveryFactor());
			detail.put("workshop", summaryIter.workshop());
			detail.put("itemSpecification", summaryIter.itemSpecification());
			detail.put("itemCategoryName", summaryIter.itemCategoryName());
			detail.put("itemCount", summaryIter.itemCount());
			detail.put("itemUnitPrice", summaryIter.itemUnitPrice());
			detail.put("productionCycle", summaryIter.productionCycle());
			detail.put("businessDate", SqlDateUtil.getUtilDate(summaryIter.businessDate()));
			detail.put("completeTime", SqlDateUtil.getUtilDate(summaryIter.completeTime()));
			detail.put("minOrderCount", summaryIter.minOrderCount());
			detail.put("suggestAmt", summaryIter.suggestAmt());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	private List<Map> processDetailIter(DetailIter detailIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("formId", detailIter.form_id());
			detail.put("itemId", detailIter.item_id());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("itemSpecification", detailIter.item_specification());
			detail.put("item_count", detailIter.item_count());
			detail.put("providerId", detailIter.provider_id());
			detail.put("provider", detailIter.provider());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	public int saveEntity(ArrangmentHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String formRefId = header.getFormRefId();
		String formBranchId = header.getFormBranchId();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		Date formTimeActual = SqlDateUtil.getSqlDate(new java.util.Date());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:281^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_ARRENGMENT_HEADER") 
//  				(FORM_ID, FORM_REF_ID, FORM_BRANCH_ID, FORM_MAKER_ID, FORM_MAKER, FORM_TIME, FORM_TIME_ACTUAL, AUDITOR_ID, AUDITOR, AUDIT_TIME, FORM_NOTE) 
//  			VALUES (:formId, :formRefId, :formBranchId, :formMakerId, :formMaker, :formTime, :formTimeActual, :auditorId, :auditor, :auditTime, :formNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formRefId;
  String __sJT_4 = formBranchId;
  String __sJT_5 = formMakerId;
  String __sJT_6 = formMaker;
  Date __sJT_7 = formTime;
  Date __sJT_8 = formTimeActual;
  String __sJT_9 = auditorId;
  String __sJT_10 = auditor;
  Date __sJT_11 = auditTime;
  String __sJT_12 = formNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setDate(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:285^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(ArrangmentHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String formRefId = header.getFormRefId();
		String formMakerId = header.getFormMakerId();
		String formMaker = header.getFormMaker();
		Date formTime = SqlDateUtil.getSqlDate(header.getFormTime());
		String auditorId = header.getAuditorId();
		String auditor = header.getAuditor();
		Date auditTime = SqlDateUtil.getSqlDate(header.getAuditTime());
		String formNote = header.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:305^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_ARRENGMENT_HEADER")
//  			SET
//  				FORM_ID = :formId,
//  				FORM_REF_ID = :formRefId,
//  				FORM_MAKER_ID = :formMakerId,
//  				FORM_MAKER = :formMaker,
//  				FORM_TIME = :formTime,
//  				AUDITOR_ID = :auditorId,
//  				AUDITOR = :auditor,
//  				AUDIT_TIME = :auditTime,
//  				FORM_NOTE = :formNote
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = formId;
  String __sJT_3 = formRefId;
  String __sJT_4 = formMakerId;
  String __sJT_5 = formMaker;
  Date __sJT_6 = formTime;
  String __sJT_7 = auditorId;
  String __sJT_8 = auditor;
  Date __sJT_9 = auditTime;
  String __sJT_10 = formNote;
  String __sJT_11 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:320^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:330^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T1_ARRENGMENT_HEADER")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:336^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int audit(String formId, String userId,String userName, java.util.Date auditTime)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sAuditTime = SqlDateUtil.getSqlDate(auditTime);
		/*@lineinfo:generated-code*//*@lineinfo:347^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_ARRENGMENT_HEADER")
//  			SET
//  				auditor_id = :userId,
//  				auditor = :userName,
//  				audit_time = :sAuditTime
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = userId;
  String __sJT_3 = userName;
  Date __sJT_4 = sAuditTime;
  String __sJT_5 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:356^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/**
	 * 设置是否下发采购单
	 */
	public int savePurchaseStatus(String formId, String status)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:367^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T1_ARRENGMENT_HEADER")
//  			SET
//  				PURCHASE_STATUS = :status
//  			WHERE 
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = status;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:374^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:388^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  				:Com_("D_T1_ARRENGMENT_HEADER") h
//  			WHERE
//  				:query
//  			AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = query;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:397^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId) {
		String query = " 1=1";
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND h.BUYER_NAME = " + branchId;
		}
		return query;
	}
	
	public List<ArrangmentHeader> query(java.util.Date startDate, java.util.Date endDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:418^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  				:Com_("D_T1_ARRENGMENT_HEADER") h
//  			WHERE
//  				:query
//  			AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = query;
  Date __sJT_3 = sDate;
  Date __sJT_4 = sDate;
  Date __sJT_5 = eDate;
  Date __sJT_6 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:427^3*/
		List<ArrangmentHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public int countAudit(java.util.Date startDate, java.util.Date endDate, String branchId,int queryTag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,queryTag);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:442^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT DISTINCT
//  		    COUNT(DISTINCT h.FORM_ID)
//  		
//  		FROM
//  		    :Com_("D_T1_ARRENGMENT_HEADER") h
//  		LEFT JOIN
//  		    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  		ON
//  		    h.FORM_ID = d.FORM_ID
//  		LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s2
//  		ON
//  		    d.WORK_ORDER_ID = s2.FORM_ID
//  		WHERE
//  		    :query
//  		AND (
//  		        h.AUDIT_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.AUDIT_TIME <= :eDate
//  		    OR  :eDate IS NULL)
//  		AND s.IS_CURRENT = 1
//  		AND s2.STATUS IS NULL
//  		ORDER BY
//  		    h.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 9);
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

/*@lineinfo:user-code*//*@lineinfo:472^16*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public int countManual(java.util.Date startDate, java.util.Date endDate, String branchId,int queryTag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,queryTag);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext() ;
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:485^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT DISTINCT
//  		    COUNT(DISTINCT h.FORM_ID)
//  		
//  		FROM
//  		    :Com_("D_T1_ARRENGMENT_HEADER") h
//  		LEFT JOIN
//  		    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  		ON
//  		    h.FORM_ID = d.FORM_ID
//  		LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s2
//  		ON
//  		    d.WORK_ORDER_ID = s2.FORM_ID
//  		WHERE
//  		    :query
//  		AND (
//  		        h.AUDIT_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.AUDIT_TIME <= :eDate
//  		    OR  :eDate IS NULL)
//  	    AND h.FORM_REF_ID IS NULL
//  		AND s.IS_CURRENT = 1
//  		AND s2.STATUS IS NOT NULL
//  		ORDER BY
//  		    h.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 10);
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

/*@lineinfo:user-code*//*@lineinfo:516^16*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public int countUnAudit(java.util.Date startDate, java.util.Date endDate, String branchId,int queryTag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery1(branchId,queryTag);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:529^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    COUNT(*)
//  		
//  		FROM
//  		    :Com_("D_T1_ARRENGMENT_HEADER") h
//  		LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		ON
//  		    s.FORM_ID = h.FORM_ID
//  		AND s.IS_CURRENT = 1
//  		WHERE
//  		    :query
//  		AND (
//  		        h.FORM_TIME >= :sDate
//  		    OR  :sDate IS NULL)
//  		AND (
//  		        h.FORM_TIME <= :eDate
//  		    OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:548^26*/
		closeDefaultContext(myCtx);
		return total;
	}

	private String formQuery(String branchId, int queryTag) {
		String query = " 1=1";
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND h.BUYER_NAME = '" + branchId + "'";
		}
		
		switch (queryTag) {
		case UN_AUDIT:
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'";
			break;
		case AUDIT_ED:
			query = query + " AND (NVL( s.STATUS, 'null') = '已审核' OR NVL( s.STATUS, 'null') = '已出库')";
			break;
		case UN_OUT:
			query = query + " AND NVL( s.STATUS, 'null') = '已审核'";
			break;
		}
		
		return query;
	}

	private String formQuery1(String branchId, int queryTag) {
		String query = " 1=1";
		if (!TextUtil.isEmpty(branchId)) {
			query = query + " AND h.FORM_BRANCH_ID = '" + branchId + "'";
		}
		
		switch (queryTag) {
		case UN_AUDIT:
			query = query + " AND NVL( s.STATUS, 'null') = '未审核'";
			break;
		case AUDIT_ED:
			query = query + " AND (NVL( s.STATUS, 'null') = '已审核' OR NVL( s.STATUS, 'null') = '已出库')";
			break;
		case UN_OUT:
			query = query + " AND NVL( s.STATUS, 'null') = '已审核'";
			break;
		}
		
		return query;
	}
	
	public List<ArrangmentHeader> queryAudit(java.util.Date startDate, java.util.Date endDate, String branchId, int queryTag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,queryTag);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:603^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT DISTINCT
//  			    h.FORM_ID,
//  			    h.FORM_REF_ID,
//  			    h.FORM_MAKER_ID,
//  			    h.FORM_MAKER,
//  			    h.FORM_TIME,
//  			    h.AUDITOR_ID,
//  			    h.AUDITOR,
//  			    h.AUDIT_TIME,
//  			    h.FORM_NOTE,
//  			    h.PURCHASE_STATUS,
//  		    	s.STATUS
//  			FROM
//  			    :Com_("D_T1_ARRENGMENT_HEADER") h
//  			LEFT JOIN
//  			    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.FORM_ID
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s2
//  			ON
//  			    d.WORK_ORDER_ID = s2.FORM_ID
//  			WHERE
//  			    :query
//  			AND (
//  			        h.AUDIT_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.AUDIT_TIME <= :eDate
//  			    OR  :eDate IS NULL)
//  			AND s.IS_CURRENT = 1
//  			AND s2.STATUS IS NULL
//  			ORDER BY
//  			    h.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 12);
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

/*@lineinfo:user-code*//*@lineinfo:641^17*/
		List<ArrangmentHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ArrangmentHeader> queryManual(java.util.Date startDate, java.util.Date endDate, String branchId, int queryTag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,queryTag);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:656^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT DISTINCT
//  			    h.FORM_ID,
//  			    h.FORM_REF_ID,
//  			    h.FORM_MAKER_ID,
//  			    h.FORM_MAKER,
//  			    h.FORM_TIME,
//  			    h.AUDITOR_ID,
//  			    h.AUDITOR,
//  			    h.AUDIT_TIME,
//  			    h.FORM_NOTE,
//  			    h.PURCHASE_STATUS,
//  		    	s.STATUS
//  			FROM
//  			    :Com_("D_T1_ARRENGMENT_HEADER") h
//  			LEFT JOIN
//  			    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			ON
//  			    h.FORM_ID = d.FORM_ID
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s2
//  			ON
//  			    d.WORK_ORDER_ID = s2.FORM_ID
//  			WHERE
//  			    :query
//  			AND (
//  			        h.AUDIT_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.AUDIT_TIME <= :eDate
//  			    OR  :eDate IS NULL)
//  		    AND h.FORM_REF_ID IS NULL
//  			AND s.IS_CURRENT = 1
//  			AND s2.STATUS IS NOT NULL
//  			ORDER BY
//  			    h.FORM_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 13);
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

/*@lineinfo:user-code*//*@lineinfo:695^17*/
		List<ArrangmentHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ArrangmentHeader> queryUnAudit(java.util.Date startDate, java.util.Date endDate, String branchId,int queryTag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId,queryTag);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:710^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				    h.FORM_ID,
//  				    h.FORM_REF_ID,
//  				    h.FORM_MAKER_ID,
//  				    h.FORM_MAKER,
//  				    h.FORM_TIME,
//  				    h.AUDITOR_ID,
//  				    h.AUDITOR,
//  				    h.AUDIT_TIME,
//  				    h.FORM_NOTE,
//  				    h.PURCHASE_STATUS,
//  				    h.FORM_TIME_ACTUAL,
//  				    h.FORM_BRANCH_ID,
//  			    	s.STATUS
//  			    FROM
//  			        :Com_("D_T1_ARRENGMENT_HEADER") h
//  			    LEFT JOIN
//  			        :Com_("D_T0_FORM_STATUS") s
//  			    ON
//  			        s.FORM_ID = h.FORM_ID
//  			    WHERE
//  			        :query
//  				AND s.IS_CURRENT = 1
//  			    AND (
//  			            h.FORM_TIME >= :sDate
//  			        OR  :sDate IS NULL)
//  			    AND (
//  			            h.FORM_TIME <= :eDate
//  			        OR  :eDate IS NULL) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = query;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 14);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:739^31*/
		List<ArrangmentHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/**
	 * 查询可以用于生成原料采购单的生产计划单
	 */
	public List<ArrangmentHeader> queryCFAudit(java.util.Date startDate, java.util.Date endDate) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String formStatus = BillStatus.AUDITED_CN;
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:757^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			    :Com_("D_T1_ARRENGMENT_HEADER") h
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  		    	NVL( s.STATUS, 'null') = :formStatus
//  		    AND NVL(h.PURCHASE_STATUS,'null') != '已下发采购'
//  			AND	(h.FORM_TIME >= :sDate or :sDate is null)
//  			AND (h.FORM_TIME <= :eDate or :eDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formStatus;
  Date __sJT_4 = sDate;
  Date __sJT_5 = sDate;
  Date __sJT_6 = eDate;
  Date __sJT_7 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 15);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:772^3*/
		List<ArrangmentHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	public ArrangmentHeader queryById(String formId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:783^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    h.*,
//  			    s.status
//  			FROM
//  				:Com_("D_T1_ARRENGMENT_HEADER") h
//  			LEFT JOIN
//  			    :Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    s.FORM_ID = h.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  				h.form_id = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ArrangmentHeaderBean_SJProfileKeys.getKey(0), 16);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:796^3*/
		List<ArrangmentHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		if (headerLst !=null && !headerLst.isEmpty()) {
			return headerLst.get(0);
		}
		return null;
	}
	
	private List<ArrangmentHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ArrangmentHeader> headerLst = new ArrayList<ArrangmentHeader>();
		while(headerIter.next()) {
			ArrangmentHeader header = new ArrangmentHeader();
			header.setFormId(headerIter.form_id());
			header.setFormRefId(headerIter.form_ref_id());
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setPurchaseStatus(headerIter.purchase_status());
			header.setStatus(headerIter.status());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ArrangmentHeaderBean_SJProfileKeys 
{
  private static ArrangmentHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ArrangmentHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ArrangmentHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ArrangmentHeaderBean_SJProfile0");
  }
}
