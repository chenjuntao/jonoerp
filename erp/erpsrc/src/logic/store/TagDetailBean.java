/*@lineinfo:filename=TagDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Jun 02 16:10:43 CST 2016 by pw
 * Last edited on Thu Jun 02 16:10:43 CST 2016 by pw
 * 
 * comment: 标签明细表
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.TagDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class TagDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(TagDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

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
    tag_idNdx = findColumn("tag_id");
    codeNdx = findColumn("code");
  }
  public String tag_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tag_idNdx);
  }
  private int tag_idNdx;
  public String code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(codeNdx);
  }
  private int codeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^61*/
	
	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemsIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemsIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    rownumberNdx = findColumn("rownumber");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    tagsNdx = findColumn("tags");
    category_nameNdx = findColumn("category_name");
  }
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
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
  public String tags() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tagsNdx);
  }
  private int tagsNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^116*/
	
	public int saveEntity(TagDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String tagId = detail.getTagId();
		String code = detail.getCode();

		/*@lineinfo:generated-code*//*@lineinfo:48^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_TAG_DETAIL")   
//  				(TAG_ID, CODE) 
//  			VALUES (:tagId, :code)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_DETAIL");
  String __sJT_2 = tagId;
  String __sJT_3 = code;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagDetailBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:52^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	
	public int saveByCateIds(String cateIds,String tagIds,String bindTag)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String query = " 1=1";
		if(!TextUtil.isEmpty(cateIds)){
			query += " AND m.CATEGORY_ID IN ('" + cateIds.replaceAll(",", "','") + "') ";
		}
		
		String[] tagIdArr = tagIds.split(",");
		
		for (int i = 0; i < tagIdArr.length; i++) {
			String tagId = tagIdArr[i];
			
			/*@lineinfo:generated-code*//*@lineinfo:73^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  				:Com_("D_T2_TAG_DETAIL") d2 
//  			WHERE
//  			    (
//  			        d2.TAG_ID,d2.CODE) =
//  			    (
//  			        SELECT
//  			            d.TAG_ID,
//  			            d.CODE
//  			        FROM
//  			        	:Com_("D_T2_TAG_DETAIL") d 
//  			        LEFT JOIN
//  			        	:Com_("D_T2_ITEM_META") m 
//  			        ON
//  			            d.CODE = m.ITEM_ID
//  			        WHERE
//  					:query
//  		        	AND d.TAG_ID = :tagId
//  			        AND d2.TAG_ID = d.TAG_ID
//  			        AND d2.CODE = d.CODE) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_DETAIL");
  String __sJT_2 = Com_("D_T2_TAG_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = query;
  String __sJT_5 = tagId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:94^33*/
		}
		
		if("bind".equals(bindTag)){
			for (int i = 0; i < tagIdArr.length; i++) {
				String tagId = tagIdArr[i];
				
				/*@lineinfo:generated-code*//*@lineinfo:101^5*/

//  ************************************************************
//  #sql [myCtx] { INSERT	INTO
//  						:Com_("D_T2_TAG_DETAIL") d 
//  					SELECT
//  					    :tagId     tag_id,
//  					    m.ITEM_ID code
//  					FROM
//  						:Com_("D_T2_ITEM_META") m 
//  					WHERE
//  					 	:query };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_DETAIL");
  String __sJT_2 = tagId;
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:110^23*/
			}
		}
		
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int saveByItemId(String itemId,String tagIds)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();
			
			/*@lineinfo:generated-code*//*@lineinfo:124^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  				FROM
//  					:Com_("D_T2_TAG_DETAIL") 
//  				WHERE
//  					CODE = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_DETAIL");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagDetailBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:130^4*/
			
			String[] tagIdArr = tagIds.split(",");
			int length = tagIdArr.length;
			
			if(TextUtil.isEmpty(tagIds)){
				length = 0;
			}
			
			for (int i = 0; i < length; i++) {
				String tagId = tagIdArr[i];
				
				/*@lineinfo:generated-code*//*@lineinfo:142^5*/

//  ************************************************************
//  #sql [myCtx] { INSERT	INTO  :Com_("D_T2_TAG_DETAIL")  (TAG_ID,CODE)VALUES(:tagId,:itemId) };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_DETAIL");
  String __sJT_2 = tagId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagDetailBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:143^81*/
			}
			
			closeDefaultContext(myCtx);
			
			return 1;
		}
	

	public int deleteByTagId(String tagIds)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:156^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T2_TAG_DETAIL") 
//  			WHERE
//  				TAG_ID = :tagIds
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_DETAIL");
  String __sJT_2 = tagIds;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagDetailBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:162^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:168^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class TotalIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TotalIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    totalNdx = findColumn("total");
  }
  public Integer total() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(totalNdx);
  }
  private int totalNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:168^47*/
	
	public int itemsCount(String itemCondition,String cateIds,String tagCondition,String itemCategoryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		Integer total = null;
		itemCondition = "%" + itemCondition + "%";
		
		String query = " m.ITEM_TYPE IN ('" + itemCategoryType.replaceAll(",", "','") + "') ";;
		if(!TextUtil.isEmpty(cateIds)){
			query += " AND m.CATEGORY_ID IN ('" + cateIds.replaceAll(",", "','") + "') ";
		}
		
		String query2 = "";
		if(!TextUtil.isEmpty(tagCondition)){
			query2 = " AND d.TAG_ID IN ('" + tagCondition.replaceAll(",", "','") + "') ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		TotalIter totalIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:187^3*/

//  ************************************************************
//  #sql [myCtx] totalIter = { WITH
//  			    items AS
//  			    (
//  			        SELECT DISTINCT
//  			            m.ITEM_ID,
//  			            m.ITEM_NAME,
//  			            c.CATEGORY_NAME
//  			        FROM
//  			            :Com_("D_T2_ITEM_META") m
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            m.CATEGORY_ID = c.CATEGORY_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_DETAIL") d
//  			        ON
//  			            m.ITEM_ID = d.CODE
//  			        WHERE
//  			            :query
//  			        AND (
//  			                m.ITEM_ID LIKE :itemCondition
//  			            OR  m.ITEM_NAME LIKE :itemCondition) :query2
//  			    )
//  			    ,
//  			    a AS
//  			    (
//  			        SELECT
//  			            i.*,
//  			            listagg(h.TAG_NAME,',') within GROUP (ORDER BY h.TAG_ID) tags
//  			        FROM
//  			            items i
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_DETAIL") d
//  			        ON
//  			            i.ITEM_ID = d.CODE
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_HEADER") h
//  			        ON
//  			            h.TAG_ID = d.TAG_ID
//  			        GROUP BY
//  			            i.ITEM_ID,
//  			            i.ITEM_NAME,
//  			            i.CATEGORY_NAME
//  			    )
//  
//              SELECT
//                  count(*)  total
//              FROM
//                  a  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T2_TAG_DETAIL");
  String __sJT_4 = query;
  String __sJT_5 = itemCondition;
  String __sJT_6 = itemCondition;
  String __sJT_7 = query2;
  String __sJT_8 = Com_("D_T2_TAG_DETAIL");
  String __sJT_9 = Com_("D_T2_TAG_HEADER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagDetailBean_SJProfileKeys.getKey(0), 6);
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
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      totalIter = new TotalIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:236^19*/
		
		if(totalIter.next()) {
			total = totalIter.total();
		}
		
		closeDefaultContext(myCtx);
		
		return total ==null?0:total;
	}
	
	public List<Map> queryItems(String itemCondition,String cateIds,String tagCondition,String itemCategoryType,int startRow, int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		itemCondition = "%" + itemCondition + "%";
		
		String query = " m.ITEM_TYPE IN ('" + itemCategoryType.replaceAll(",", "','") + "') ";;
		if(!TextUtil.isEmpty(cateIds)){
			query += " AND m.CATEGORY_ID IN ('" + cateIds.replaceAll(",", "','") + "') ";
		}
		
		String query2 = "";
		if(!TextUtil.isEmpty(tagCondition)){
			query2 = " AND d.TAG_ID IN ('" + tagCondition.replaceAll(",", "','") + "') ";
		}
		
		DefaultContext myCtx = getDefaultContext();
		ItemsIter itemsIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:263^3*/

//  ************************************************************
//  #sql [myCtx] itemsIter = { WITH
//  			    items AS
//  			    (
//  			        SELECT DISTINCT
//  			            m.ITEM_ID,
//  			            m.ITEM_NAME,
//  			            c.CATEGORY_NAME
//  			        FROM
//  			            :Com_("D_T2_ITEM_META") m
//  			        LEFT JOIN
//  			            :Com_("D_T2_ITEM_CATEGORY") c
//  			        ON
//  			            m.CATEGORY_ID = c.CATEGORY_ID
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_DETAIL") d
//  			        ON
//  			            m.ITEM_ID = d.CODE
//  			        WHERE
//  			            :query
//  			        AND (
//  			                m.ITEM_ID LIKE :itemCondition
//  			            OR  m.ITEM_NAME LIKE :itemCondition) :query2
//  			    )
//  			    ,
//  			    a AS
//  			    (
//  			        SELECT
//  			            i.*,
//  			            listagg(h.TAG_NAME,',') within GROUP (ORDER BY h.TAG_ID) tags
//  			        FROM
//  			            items i
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_DETAIL") d
//  			        ON
//  			            i.ITEM_ID = d.CODE
//  			        LEFT JOIN
//  			            :Com_("D_T2_TAG_HEADER") h
//  			        ON
//  			            h.TAG_ID = d.TAG_ID
//  			        GROUP BY
//  			            i.ITEM_ID,
//  			            i.ITEM_NAME,
//  			            i.CATEGORY_NAME
//  			        ORDER BY
//  			            1
//  			    )
//  			SELECT
//  			    *
//  			FROM
//  			    (
//  			        SELECT
//  			            t.*,
//  			            ROWNUM rowNumber
//  			        FROM
//  			            (
//  			                SELECT
//  			                    a.*
//  			                FROM
//  			                    a ) t
//  			        WHERE
//  			            ROWNUM < :endRow)
//  			WHERE
//  			    rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_3 = Com_("D_T2_TAG_DETAIL");
  String __sJT_4 = query;
  String __sJT_5 = itemCondition;
  String __sJT_6 = itemCondition;
  String __sJT_7 = query2;
  String __sJT_8 = Com_("D_T2_TAG_DETAIL");
  String __sJT_9 = Com_("D_T2_TAG_HEADER");
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagDetailBean_SJProfileKeys.getKey(0), 7);
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
      itemsIter = new ItemsIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:326^30*/
		List<Map> detailLst = processItemsIter(itemsIter);
		itemsIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<TagDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<TagDetail> detailLst = new ArrayList<TagDetail>();
		while(detailIter.next()) {
			TagDetail detail = new TagDetail();
			detail.setTagId(detailIter.tag_id());
			detail.setCode(detailIter.code());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	private List<Map> processItemsIter(ItemsIter itemsIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(itemsIter.next()) {
			Map map  = new HashMap();
			map.put("rownumber", itemsIter.rownumber());
			map.put("item_id", itemsIter.item_id());
			map.put("item_name", itemsIter.item_name());
			map.put("category_name", itemsIter.category_name());
			map.put("tags", itemsIter.tags());
			detailLst.add(map);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class TagDetailBean_SJProfileKeys 
{
  private static TagDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TagDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TagDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.TagDetailBean_SJProfile0");
  }
}
