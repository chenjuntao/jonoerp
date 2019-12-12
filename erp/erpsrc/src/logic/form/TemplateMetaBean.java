/*@lineinfo:filename=TemplateMetaBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 29, 2014 by liyzh
 * Last edited on Aug 29, 2014 by liyzh
 * 
 * 说明：餐厅要货、外部订货商订货需求模板元信息
 */
package logic.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.TemplateMeta;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class TemplateMetaBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(TemplateMetaBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:33^2*/

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
    delivery_regionNdx = findColumn("delivery_region");
    delivery_typeNdx = findColumn("delivery_type");
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
  public String delivery_region() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_regionNdx);
  }
  private int delivery_regionNdx;
  public String delivery_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_typeNdx);
  }
  private int delivery_typeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^93*/
	
	/**
	 * 保存
	 */
	public int saveEntity(TemplateMeta meta)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String templateId = meta.getTemplateId();
		String templateName = meta.getTemplateName();
		String templateType = meta.getTemplateType();
		String branchId = meta.getBranchId();
		String categoryId = meta.getCategoryId();
		String templateNote = meta.getTemplateNote();
		Integer arrivePeriod = meta.getArrivePeriod();
		String deliveryRegion = meta.getDeliveryRegion();
		String deliveryType = meta.getDeliveryType();

		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_TEMPLATE_META") 
//  				(TEMPLATE_ID, TEMPLATE_NAME, TEMPLATE_TYPE, BRANCH_ID, CATEGORY_ID, TEMPLATE_NOTE, ARRIVE_PERIOD, DELIVERY_REGION, DELIVERY_TYPE) 
//  			VALUES (:templateId, :templateName, :templateType, :branchId, :categoryId, :templateNote, :arrivePeriod, :deliveryRegion, :deliveryType)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = templateId;
  String __sJT_3 = templateName;
  String __sJT_4 = templateType;
  String __sJT_5 = branchId;
  String __sJT_6 = categoryId;
  String __sJT_7 = templateNote;
  Integer __sJT_8 = arrivePeriod;
  String __sJT_9 = deliveryRegion;
  String __sJT_10 = deliveryType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateMetaBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setIntWrapper(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:60^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(TemplateMeta meta)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String templateId = meta.getTemplateId();
		String templateName = meta.getTemplateName();
		String categoryId = meta.getCategoryId();
		Integer arrivePeriod = meta.getArrivePeriod();

		/*@lineinfo:generated-code*//*@lineinfo:74^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE :Com_("D_T1_TEMPLATE_META")
//  			SET
//  				TEMPLATE_NAME = :templateName,
//  				CATEGORY_ID = :categoryId,
//  				ARRIVE_PERIOD = :arrivePeriod
//  			WHERE 
//  				TEMPLATE_ID = :templateId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = templateName;
  String __sJT_3 = categoryId;
  Integer __sJT_4 = arrivePeriod;
  String __sJT_5 = templateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateMetaBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:82^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String templateId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:92^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_TEMPLATE_META")
//  			WHERE
//  				TEMPLATE_ID = :templateId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = templateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateMetaBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:98^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int count(String branchId, String templateType, String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		if (!TextUtil.isEmpty(itemName)) {
			return countByItem(branchId, templateType, itemName);
		}
		String query = formQuery(branchId);
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:112^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T1_TEMPLATE_META") m
//  		    INNER JOIN
//  		    :Com_("D_T2_BRANCH") b
//  		    ON
//  		        b.BRANCH_ID = m.BRANCH_ID
//  			WHERE
//  				:query
//  			AND TEMPLATE_TYPE = :templateType
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = query;
  String __sJT_4 = templateType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateMetaBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:124^3*/
		closeDefaultContext(myCtx);
		return total;
	}

	/**
	 * 根据原料名称或编码筛选模板
	 */
	public int countByItem(String branchId, String templateType, String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId);
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		itemName = "%" + itemName + "%";
		/*@lineinfo:generated-code*//*@lineinfo:138^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			    (
//  			        SELECT
//  			            m.TEMPLATE_ID
//  			        FROM
//  			        	:Com_("D_T1_TEMPLATE_META") m,
//  			        	:Com_("D_T1_TEMPLATE_ITEM") i,
//  			        	:Com_("D_T2_ITEM_META") im,
//  			        	:Com_("D_T2_BRANCH") b
//  			        WHERE
//  			            m.TEMPLATE_ID = i.TEMPLATE_ID
//  			        AND im.item_id = i.ITEM_ID
//  			    	AND b.BRANCH_ID = m.BRANCH_ID
//  			        AND :query
//  			        AND m.TEMPLATE_TYPE = :templateType
//  	                AND (
//  	                        i.ITEM_ID LIKE :itemName
//  	                    OR  im.ITEM_NAME LIKE :itemName)
//  			        GROUP BY
//  			            m.TEMPLATE_ID)
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = Com_("D_T1_TEMPLATE_ITEM");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = query;
  String __sJT_6 = templateType;
  String __sJT_7 = itemName;
  String __sJT_8 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateMetaBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:161^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	private String formQuery(String branchId) {
		String query = " 1=1";
		if (!"-1".equals(branchId)) {
			query = query + " AND m.BRANCH_ID = '" + branchId + "'";
		} else {// 默认查询所有餐厅的模板
			query = query + " AND b.BRANCH_TYPE = 'RESTAURANT'";
		}
		return query;
	}
	
	public List<TemplateMeta> query(String branchId, String templateType, String itemName)  
			throws NoPrivilegeException,SQLException,NoConnection {
		if (!TextUtil.isEmpty(itemName)) {
			return queryByItem(branchId, templateType, itemName);
		}
		String query = formQuery(branchId);
		DefaultContext myCtx = getDefaultContext();
		MetaIter metaIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:184^3*/

//  ************************************************************
//  #sql [myCtx] metaIter = { SELECT
//  			    m.*
//  			FROM
//  			:Com_("D_T1_TEMPLATE_META") m
//  		    INNER JOIN
//  		    :Com_("D_T2_BRANCH") b
//  		    ON
//  		        b.BRANCH_ID = m.BRANCH_ID
//  			WHERE
//  				:query 
//  			AND (m.TEMPLATE_TYPE = :templateType)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = query;
  String __sJT_4 = templateType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateMetaBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:196^3*/
		List<TemplateMeta> metaLst = processIter(metaIter);
		metaIter.close();
		closeDefaultContext(myCtx);
		return metaLst;
	}

	/**
	 * 根据原料名称或编码筛选模板
	 */
	public List<TemplateMeta> queryByItem(String branchId, String templateType, String itemName)  
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(branchId);
		itemName = "%" + itemName + "%";
		DefaultContext myCtx = getDefaultContext();
		MetaIter metaIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:212^3*/

//  ************************************************************
//  #sql [myCtx] metaIter = { SELECT
//  			    m.TEMPLATE_ID,
//  			    m.TEMPLATE_NAME,
//  			    m.TEMPLATE_TYPE,
//  			    m.BRANCH_ID,
//  			    m.CATEGORY_ID,
//  			    m.TEMPLATE_NOTE,
//  			    m.ARRIVE_PERIOD,
//  			    m.DELIVERY_TYPE
//  			FROM
//  				:Com_("D_T1_TEMPLATE_META") m,
//  				:Com_("D_T1_TEMPLATE_ITEM") i,
//  				:Com_("D_T2_ITEM_META") im,
//  				:Com_("D_T2_BRANCH") b
//  			WHERE
//  	            m.TEMPLATE_ID = i.TEMPLATE_ID
//  	        AND im.item_id = i.ITEM_ID
//  	        AND b.BRANCH_ID = m.BRANCH_ID
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
//  			    m.ARRIVE_PERIOD,
//  			    m.DELIVERY_TYPE
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
  String __sJT_4 = Com_("D_T2_BRANCH");
  String __sJT_5 = query;
  String __sJT_6 = templateType;
  String __sJT_7 = itemName;
  String __sJT_8 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateMetaBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:245^3*/
		List<TemplateMeta> metaLst = processIter(metaIter);
		metaIter.close();
		closeDefaultContext(myCtx);
		return metaLst;
	}

	public TemplateMeta queryById(String templateId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		MetaIter metaIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:256^3*/

//  ************************************************************
//  #sql [myCtx] metaIter = { SELECT
//  			    *
//  			FROM
//  				:Com_("D_T1_TEMPLATE_META") m
//  			WHERE
//  				m.TEMPLATE_ID = :templateId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_META");
  String __sJT_2 = templateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateMetaBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:263^3*/
		List<TemplateMeta> metaLst = processIter(metaIter);
		metaIter.close();
		closeDefaultContext(myCtx);
		if (metaLst !=null && !metaLst.isEmpty()) {
			return metaLst.get(0);
		}
		return null;
	}
	
	private List<TemplateMeta> processIter(MetaIter metaIter) 
			throws SQLException {
		List<TemplateMeta> metaLst = new ArrayList<TemplateMeta>();
		while(metaIter.next()) {
			TemplateMeta meta = new TemplateMeta();
			meta.setTemplateId(metaIter.template_id());
			meta.setTemplateName(metaIter.template_name());
			meta.setTemplateType(metaIter.template_type());
			meta.setBranchId(metaIter.branch_id());
			meta.setCategoryId(metaIter.category_id());
			meta.setTemplateNote(metaIter.template_note());
			meta.setArrivePeriod(metaIter.arrive_period());
			meta.setDeliveryRegion(metaIter.delivery_region());
			meta.setDeliveryType(metaIter.delivery_type());
			metaLst.add(meta);
		}
		return metaLst;
	}
}/*@lineinfo:generated-code*/class TemplateMetaBean_SJProfileKeys 
{
  private static TemplateMetaBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TemplateMetaBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TemplateMetaBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.TemplateMetaBean_SJProfile0");
  }
}
