/*@lineinfo:filename=RequestTemplateBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Oct 29, 2014 by liyzh
 * Last edited on Oct 29, 2014 by liyzh
 * 
 * 说明： 餐厅要货模板查询
 */
package logic.module.restaurant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.form.TemplateMeta;
import vo.restaurant.RequestItemVO;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class RequestTemplateBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(RequestTemplateBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class MetaIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MetaIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    template_idNdx = findColumn("template_id");
    template_nameNdx = findColumn("template_name");
    template_typeNdx = findColumn("template_type");
    branch_idNdx = findColumn("branch_id");
    category_idNdx = findColumn("category_id");
    template_noteNdx = findColumn("template_note");
    arrive_periodNdx = findColumn("arrive_period");
  }
  public String template_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_idNdx);
  }
  private int template_idNdx;
  public String template_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_nameNdx);
  }
  private int template_nameNdx;
  public String template_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_typeNdx);
  }
  private int template_typeNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String template_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_noteNdx);
  }
  private int template_noteNdx;
  public Integer arrive_period() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(arrive_periodNdx);
  }
  private int arrive_periodNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^47*/
	
	
	private String formQuery(String branchId, String regionId, int arrivePeriod) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND m.BRANCH_ID = '" + branchId + "'";
		}
		if (!"-1".equals(regionId)) {
			query = query + " AND m.delivery_region = '" + regionId + "'";
		}
		if (arrivePeriod != -1) {
			query = query + " AND m.arrive_period = " + arrivePeriod;
		}
		return query;
	}
	
	public List<TemplateMeta> query(String branchId, String regionId, String templateType, String itemName, int arrivePeriod)  
			throws NoPrivilegeException,SQLException,NoConnection {
		if (!TextUtil.isEmpty(itemName)) {
			return queryByItem(branchId, regionId, templateType, itemName, arrivePeriod);
		}
		String query = formQuery(branchId, regionId, arrivePeriod);
		DefaultContext myCtx = getDefaultContext();
		MetaIter metaIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] metaIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_TEMPLATE_META") m
//  			WHERE
//  				:query 
//  			AND m.TEMPLATE_TYPE = :templateType 
//  			order by  m.TEMPLATE_ID desc
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = query;
  String __sJT_3 = templateType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestTemplateBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      metaIter = new MetaIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:76^3*/
		List<TemplateMeta> metaLst = processIter(myCtx, metaIter);
		metaIter.close();
		closeDefaultContext(myCtx);
		return metaLst;
	}

	/**
	 * 根据原料名称或编码筛选模板
	 */
	public List<TemplateMeta> queryByItem(String branchId, String regionId, String templateType, String itemName, int arrivePeriod)   
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId, regionId, arrivePeriod);
		itemName = "%" + itemName + "%";
		DefaultContext myCtx = getDefaultContext();
		MetaIter metaIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:92^3*/

//  ************************************************************
//  #sql [myCtx] metaIter = { SELECT
//  			    m.TEMPLATE_ID,
//  			    m.TEMPLATE_NAME,
//  			    m.TEMPLATE_TYPE,
//  			    m.BRANCH_ID,
//  			    m.CATEGORY_ID,
//  			    m.TEMPLATE_NOTE,
//  			    m.ARRIVE_PERIOD
//  			FROM
//  				:Com_("D_T1_TEMPLATE_META") m,
//  			    :Com_("D_T1_TEMPLATE_ITEM") i,
//  			    :Com_("D_T2_ITEM_META") im
//  			WHERE
//  	            m.TEMPLATE_ID = i.TEMPLATE_ID
//  	        AND im.item_id = i.ITEM_ID
//  	        AND :query
//  	        AND m.TEMPLATE_TYPE = :templateType
//  	        AND (
//  	                i.ITEM_ID LIKE :itemName
//  	            OR  im.ITEM_NAME LIKE :itemName)
//  			GROUP BY
//  			    m.TEMPLATE_ID,
//  			    m.TEMPLATE_NAME,
//  			    m.TEMPLATE_TYPE,
//  			    m.BRANCH_ID,
//  			    m.CATEGORY_ID,
//  			    m.TEMPLATE_NOTE,
//  			    m.ARRIVE_PERIOD
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = Com_("D_T1_TEMPLATE_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = query;
  String __sJT_5 = templateType;
  String __sJT_6 = itemName;
  String __sJT_7 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestTemplateBean_SJProfileKeys.getKey(0), 1);
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
      metaIter = new MetaIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:121^3*/
		List<TemplateMeta> metaLst = processIter(myCtx, metaIter);
		metaIter.close();
		closeDefaultContext(myCtx);
		return metaLst;
	}
	
	private List<TemplateMeta> processIter(DefaultContext myCtx, MetaIter metaIter) 
			throws NoPrivilegeException,SQLException, NoConnection {
		List<TemplateMeta> metaLst = new ArrayList<TemplateMeta>();
		while(metaIter.next()) {
			TemplateMeta meta = new TemplateMeta();
			meta.setTemplateId(metaIter.template_id());
			meta.setTemplateName(metaIter.template_name());
			meta.setTemplateType(metaIter.template_type());
			meta.setBranchId(metaIter.branch_id());
			String cateIds = metaIter.category_id();
			meta.setCategoryId(cateIds);
			String cateName = queryName(myCtx, cateIds);
			meta.setCategoryName(cateName);
			meta.setTemplateNote(metaIter.template_note());
			meta.setArrivePeriod(metaIter.arrive_period());
			metaLst.add(meta);
		}
		return metaLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:148^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    template_idNdx = findColumn("template_id");
    item_idNdx = findColumn("item_id");
    item_orderNdx = findColumn("item_order");
    item_nameNdx = findColumn("item_name");
    item_categoryNdx = findColumn("item_category");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_priceNdx = findColumn("item_price");
  }
  public String template_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(template_idNdx);
  }
  private int template_idNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Integer item_order() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(item_orderNdx);
  }
  private int item_orderNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
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
  public Double item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_priceNdx);
  }
  private int item_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:154^48*/
	
	/**
	 * 根据模板ID获取相应的原料信息，价格等
	 */
	public List<RequestItemVO> queryItem(String templateId, String priceType)   
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:163^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    i.TEMPLATE_ID,
//  			    i.ITEM_ORDER,
//  			    m.ITEM_ID,
//  			    m.ITEM_NAME,
//  			    m.CATEGORY_ID AS item_category,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    p.ITEM_PRICE
//  			FROM
//  			:Com_("D_T1_TEMPLATE_ITEM") i
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = i.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_PRICE") p
//  			ON
//  			    p.ITEM_ID = m.ITEM_ID
//  			AND p.PRICE_TYPE = :priceType
//  			WHERE
//  			    i.TEMPLATE_ID = :templateId
//  			ORDER BY
//  			    i.ITEM_ORDER
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_ITEM");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_ITEM_PRICE");
  String __sJT_4 = priceType;
  String __sJT_5 = templateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestTemplateBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:188^3*/
		List<RequestItemVO> itemLst = new ArrayList<RequestItemVO>();
		while(itemIter.next()) {
			RequestItemVO item = new RequestItemVO();
			item.setTemplateId(itemIter.template_id());
			item.setItemId(itemIter.item_id());
			item.setItemOrder(itemIter.item_order());
			item.setItemName(itemIter.item_name());
			String cateId = itemIter.item_category();
			item.setItemCategory(cateId);
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			item.setItemPrice(itemIter.item_price());
			itemLst.add(item);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:208^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class CateNameIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CateNameIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    category_nameNdx = findColumn("category_name");
  }
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:208^57*/
	
	private String queryName(DefaultContext myCtx, String cateIds) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " CATEGORY_ID IN ('" + cateIds.replaceAll(",", "','") + "') ";
		
		CateNameIter nameIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:215^3*/

//  ************************************************************
//  #sql [myCtx] nameIter = { SELECT
//  				CATEGORY_NAME
//  			FROM
//  			:Com_("D_T2_ITEM_CATEGORY")
//  			WHERE
//  				:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_2 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, RequestTemplateBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nameIter = new CateNameIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:222^3*/
		List<String> nameLst = new ArrayList<String>();
		while(nameIter.next()) {
			String categoryName = nameIter.category_name();
			nameLst.add(categoryName);
		}
		nameIter.close();
		
		return StringUtils.join(nameLst, ",");
	}
}/*@lineinfo:generated-code*/class RequestTemplateBean_SJProfileKeys 
{
  private static RequestTemplateBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new RequestTemplateBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private RequestTemplateBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.restaurant.RequestTemplateBean_SJProfile0");
  }
}
