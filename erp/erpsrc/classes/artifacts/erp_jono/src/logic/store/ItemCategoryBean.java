/*@lineinfo:filename=ItemCategoryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*///===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.8.6 by cjt
// Last edited on 2014.8.6 by cjt
//
// Comments:read:{Com_("D_T2_ITEM_CATEGORY")} table.
// 说明：读取原材料、成品、半成品的类别信息。
//
//===============================================
// 
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;

import pojo.store.ItemCategory;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.ItemType;
import com.tanry.framework.util.constant.SysOption;

public class ItemCategoryBean extends ConnectionPool {

	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class CateIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CateIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
    category_levelNdx = findColumn("category_level");
    parent_idNdx = findColumn("parent_id");
    pathNdx = findColumn("path");
    item_typeNdx = findColumn("item_type");
  }
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
  public String category_level() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_levelNdx);
  }
  private int category_levelNdx;
  public String parent_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parent_idNdx);
  }
  private int parent_idNdx;
  public String path() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(pathNdx);
  }
  private int pathNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:38^75*/
	
	/**
	 * companyId由调用者通过参数指定,by cjt
	 */
	public int saveEntity(ItemCategory itemCategory, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return saveEntityImp(itemCategory, myCtx);
	}
	
	/**
	 * companyId默认通过session访问,by cjt
	 */
	public int saveEntity(ItemCategory itemCategory)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return saveEntityImp(itemCategory, myCtx);
	}
	
	private int saveEntityImp(ItemCategory itemCategory, DefaultContext myCtx) 
			throws NoPrivilegeException, SQLException, NoConnection {
		String id = itemCategory.getCategoryId();
		String name = itemCategory.getCategoryName();
		String level = itemCategory.getCategoryLevel();
		String parentId = itemCategory.getParentId();
		String path = itemCategory.getPath();
		String itemType = itemCategory.getItemType();

		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT
//  			INTO
//  			:Com_("D_T2_ITEM_CATEGORY")
//  			    (
//  			        CATEGORY_ID,
//  			        CATEGORY_NAME,
//  			        PARENT_ID,
//  			        CATEGORY_LEVEL,
//  			        ITEM_TYPE,
//  			        PATH
//  			    )
//  			    VALUES
//  			    (
//  			    	:id,
//  			    	:name,
//  			        :parentId,
//  			        :level,
//  			        :itemType,
//  			        :path
//  			    )
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_2 = id;
  String __sJT_3 = name;
  String __sJT_4 = parentId;
  String __sJT_5 = level;
  String __sJT_6 = itemType;
  String __sJT_7 = path;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemCategoryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:88^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * companyId由调用者通过参数指定,by cjt
	 */
	public int updateEntity(ItemCategory itemCategory, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return updateEntityImp(itemCategory, myCtx);
	}
		
	/**
	 * companyId默认通过session访问,by cjt
	 */
	public int updateEntity(ItemCategory itemCategory) 
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return updateEntityImp(itemCategory, myCtx);
	}
	
	private int updateEntityImp(ItemCategory itemCategory, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		
		String id = itemCategory.getCategoryId();
		String name = itemCategory.getCategoryName();
		String level = itemCategory.getCategoryLevel();
		String parentId = itemCategory.getParentId();
		String path = itemCategory.getPath();
		String itemType = itemCategory.getItemType();

		/*@lineinfo:generated-code*//*@lineinfo:122^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_ITEM_CATEGORY")
//  			SET
//  			    CATEGORY_NAME = :name,
//  			    PARENT_ID = :parentId,
//  			    CATEGORY_LEVEL = :level,
//  			    ITEM_TYPE = :itemType,
//  			    PATH = :path
//  			WHERE 
//  				CATEGORY_ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_2 = name;
  String __sJT_3 = parentId;
  String __sJT_4 = level;
  String __sJT_5 = itemType;
  String __sJT_6 = path;
  String __sJT_7 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemCategoryBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:133^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 是否包含该类别,by cjt
	 */
	public boolean containsEntity(String itemId, String itemType, String comId)
			throws NoPrivilegeException, SQLException, NoPrivilegeException{
		DefaultContext myCtx = getComContext(comId);
		int count = 0;
		/*@lineinfo:generated-code*//*@lineinfo:146^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*) 
//  			from :Com_("D_T2_ITEM_CATEGORY")
//  			where CATEGORY_ID = :itemId
//  			and ITEM_TYPE = :itemType
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_2 = itemId;
  String __sJT_3 = itemType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemCategoryBean_SJProfileKeys.getKey(0), 2);
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
    count = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:151^3*/

		closeDefaultContext(myCtx);
		return count > 0;
	}
	
	public ItemCategory queryById(String cateId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		CateIter cateIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:161^3*/

//  ************************************************************
//  #sql [myCtx] cateIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_ITEM_CATEGORY")
//  			WHERE
//  				CATEGORY_ID = :cateId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_2 = cateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemCategoryBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      cateIter = new CateIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:168^3*/
		ItemCategory category = null;
		while(cateIter.next()) {
			category = new ItemCategory();
			category.setCategoryId(cateIter.category_id());
			category.setCategoryName(cateIter.category_name());
			category.setCategoryLevel(cateIter.category_level());
			category.setParentId(cateIter.parent_id());
			category.setPath(cateIter.path());
			category.setItemType(cateIter.item_type());
		}
		cateIter.close();
		closeDefaultContext(myCtx);
		return category;
	}

	/*@lineinfo:generated-code*//*@lineinfo:184^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class NodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public NodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    category_idNdx = findColumn("category_id");
    category_nameNdx = findColumn("category_name");
    parent_idNdx = findColumn("parent_id");
    pathNdx = findColumn("path");
    item_typeNdx = findColumn("item_type");
    isLeafNdx = findColumn("isLeaf");
  }
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
  public String parent_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parent_idNdx);
  }
  private int parent_idNdx;
  public String path() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(pathNdx);
  }
  private int pathNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
  public String isLeaf() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(isLeafNdx);
  }
  private int isLeafNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:185^65*/
	
	/**
	 * 按照（1原材料/2半成品/3成品）来查询该类型的所有类别集合
	 */
	public List<ItemCategory> queryTree(String itemType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String inClause = "'" + itemType + "'";
		if (ItemType.R_S.equals(itemType)) {
			inClause = "'RAW', 'SEMIS','SELFSEMIS'";
		}
		String query = " AND c.ITEM_TYPE IN (" + inClause + ")";
		
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:200^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { SELECT
//  			    LEVEL                               levelNum,
//  			    DECODE(CONNECT_BY_ISLEAF,0,'N','Y') isLeaf,
//  			    c.*
//  			FROM
//  				D_T2_ITEM_CATEGORY c CONNECT BY PRIOR c.CATEGORY_ID = c.PARENT_ID START WITH c.PARENT_ID = '*'
//  			    	:query  ORDER siblings BY c.CATEGORY_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemCategoryBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:208^3*/
		
		List<ItemCategory> nodeLst = new ArrayList<ItemCategory>();
		while(nodeIter.next()) {
			ItemCategory node = new ItemCategory();
			node.setCategoryId(nodeIter.category_id());
			node.setCategoryName(nodeIter.category_name());
			node.setParentId(nodeIter.parent_id());
			node.setPath(nodeIter.path());
			node.setItemType(nodeIter.item_type());
			node.setIsLeaf(nodeIter.isLeaf());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		
		return nodeLst;
	}

	public List<ItemCategory> GetAllItemCategory() 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<ItemCategory> result = new ArrayList<ItemCategory>();
		DefaultContext myCtx = getDefaultContext();
		CateIter cateIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:232^3*/

//  ************************************************************
//  #sql [myCtx] cateIter = { SELECT
//  		    	*
//  			FROM
//  			:Com_("D_T2_ITEM_CATEGORY") b
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemCategoryBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      cateIter = new CateIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:237^3*/
		while(cateIter.next()) {
			ItemCategory category = new ItemCategory();
			category.setCategoryId(cateIter.category_id());
			category.setCategoryName(cateIter.category_name());
			category.setCategoryLevel(cateIter.category_level());
			category.setParentId(cateIter.parent_id());
			category.setPath(cateIter.path());
			category.setItemType(cateIter.item_type());
			result.add(category);
		}
		cateIter.close();
		closeDefaultContext(myCtx);
		
		return result;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:254^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class CateIdIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CateIdIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    category_idNdx = findColumn("category_id");
  }
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:254^53*/
	/**
	 * 获取所有的分类id，用于导入数据时比较异同
	 */
	public List<ItemCategory> listCategoryId() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		CateIdIter idIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:262^3*/

//  ************************************************************
//  #sql [myCtx] idIter = { select category_id from :Com_("D_T2_ITEM_CATEGORY") 
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemCategoryBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      idIter = new CateIdIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:264^3*/

		List<ItemCategory> idLst = new ArrayList<ItemCategory>();
		while(idIter.next())
		{
			ItemCategory category = new ItemCategory();
			category.setCategoryId(idIter.category_id());
			idLst.add(category);
		}
		idIter.close();
		closeDefaultContext(myCtx);
		return idLst;
	}

}/*@lineinfo:generated-code*/class ItemCategoryBean_SJProfileKeys 
{
  private static ItemCategoryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ItemCategoryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ItemCategoryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.ItemCategoryBean_SJProfile0");
  }
}
