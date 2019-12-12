/*@lineinfo:filename=TemplateItemBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 29, 2014 by liyzh
 * Last edited on Aug 29, 2014 by liyzh
 * 
 * 说明： 餐厅要货、外部订货商订货需求模板详细信息
 */
package logic.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.TemplateItem;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.acl.NoPrivilegeException;

public class TemplateItemBean extends ConnectionPool {	
	
	public static Logger log = Logger.getLogger(TemplateItemBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:32^2*/

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
    shelf_nameNdx = findColumn("shelf_name");
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
  public String shelf_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_nameNdx);
  }
  private int shelf_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^21*/
	
	public List<TemplateItem> query(String templateId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    i.TEMPLATE_ID,
//  			    i.ITEM_ORDER,
//  			    m.item_id,
//  			    m.item_name,
//  			    m.CATEGORY_ID AS item_category,
//  			    m.item_dimension,
//  			    m.item_specification,
//  			    s.SHELF_NAME
//  			FROM
//  				:Com_("D_T2_ITEM_META") m 
//  			LEFT JOIN
//  				:Com_("D_T1_TEMPLATE_ITEM") i
//  			ON
//  			    m.ITEM_ID = i.ITEM_ID
//  			LEFT JOIN
//  				:Com_("D_T2_SHELF_ITEM")   si  
//  			ON
//  			    i.ITEM_ID = si.ITEM_ID
//  			AND si.PRIORITY = 0
//  			LEFT JOIN
//  			   :Com_("D_T2_SHELF") s
//  			ON
//  			    si.SHELF_ID = s.SHELF_ID
//  			WHERE
//  			    i.TEMPLATE_ID = :templateId
//  			ORDER BY
//  			    i.ITEM_ORDER };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T1_TEMPLATE_ITEM");
  String __sJT_3 = Com_("D_T2_SHELF_ITEM");
  String __sJT_4 = Com_("D_T2_SHELF");
  String __sJT_5 = templateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateItemBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:73^20*/
		List<TemplateItem> itemLst = processIter(itemIter);
		itemIter.close();
		closeDefaultContext(myCtx);
		return itemLst;
	}
	
	private List<TemplateItem> processIter(ItemIter itemIter) 
			throws SQLException {
		List<TemplateItem> itemLst = new ArrayList<TemplateItem>();
		while(itemIter.next()) {
			TemplateItem item = new TemplateItem();
			item.setTemplateId(itemIter.template_id());
			item.setItemId(itemIter.item_id());
			item.setItemOrder(itemIter.item_order());
			item.setItemName(itemIter.item_name());
			item.setItemCategory(itemIter.item_category());
			item.setItemDimension(itemIter.item_dimension());
			item.setItemSpecification(itemIter.item_specification());
			item.setShelfName(itemIter.shelf_name());
			itemLst.add(item);
		}
		return itemLst;
	}		
	
	/**
	 * 保存
	 */
	public int saveEntity(TemplateItem item)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String templateId = item.getTemplateId();
		String itemId = item.getItemId();
		Integer itemOrder = item.getItemOrder();

		/*@lineinfo:generated-code*//*@lineinfo:108^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_TEMPLATE_ITEM") 
//  				(TEMPLATE_ID, ITEM_ID, ITEM_ORDER) VALUES (:templateId, 
//  						:itemId, :itemOrder)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_ITEM");
  String __sJT_2 = templateId;
  String __sJT_3 = itemId;
  Integer __sJT_4 = itemOrder;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateItemBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:112^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteItem(String templateId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:122^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_TEMPLATE_ITEM")
//  			WHERE
//  				TEMPLATE_ID = :templateId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_ITEM");
  String __sJT_2 = templateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateItemBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:128^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteByItemId(String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:138^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  					:Com_("D_T1_TEMPLATE_ITEM")
//  				WHERE
//  					ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_ITEM");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateItemBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:144^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int deleteEntity(String templateId, String itemId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:154^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_TEMPLATE_ITEM")
//  			WHERE
//  				TEMPLATE_ID = :templateId
//  			and ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_TEMPLATE_ITEM");
  String __sJT_2 = templateId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TemplateItemBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:161^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
}/*@lineinfo:generated-code*/class TemplateItemBean_SJProfileKeys 
{
  private static TemplateItemBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TemplateItemBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TemplateItemBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.TemplateItemBean_SJProfile0");
  }
}
