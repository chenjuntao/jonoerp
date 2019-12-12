/*@lineinfo:filename=PriceGroupBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 03 17:07:55 CST 2015 by lyz
 * Last edited on Tue Mar 03 17:07:55 CST 2015 by lyz
 * 
 * comment: 价格组
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.PriceGroup;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PriceGroupBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PriceGroupBean.class);
	
	public int saveEntity(PriceGroup group)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String priceGroupId = group.getPriceGroupId();
		String priceGroupName = group.getPriceGroupName();
		String priceGroupType = group.getPriceGroupType();
		String priceGroupNote = group.getPriceGroupNote();
		String owner = group.getOwner();

		/*@lineinfo:generated-code*//*@lineinfo:46^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_PRICE_GROUP") 
//  				(PRICE_GROUP_ID, PRICE_GROUP_NAME, PRICE_GROUP_TYPE, PRICE_GROUP_NOTE, OWNER) 
//  			VALUES (:priceGroupId, :priceGroupName, :priceGroupType, :priceGroupNote, :owner)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = priceGroupId;
  String __sJT_3 = priceGroupName;
  String __sJT_4 = priceGroupType;
  String __sJT_5 = priceGroupNote;
  String __sJT_6 = owner;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:50^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(String oldGroupId, PriceGroup group)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String priceGroupName = group.getPriceGroupName();
		String priceGroupType = group.getPriceGroupType();
		String priceGroupNote = group.getPriceGroupNote();
		String owner = group.getOwner();

		/*@lineinfo:generated-code*//*@lineinfo:65^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_PRICE_GROUP")
//  			SET
//  				PRICE_GROUP_NAME = :priceGroupName,
//  				PRICE_GROUP_TYPE = :priceGroupType,
//  				PRICE_GROUP_NOTE = :priceGroupNote,
//  				OWNER = :owner
//  			WHERE 
//  				PRICE_GROUP_ID = :oldGroupId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = priceGroupName;
  String __sJT_3 = priceGroupType;
  String __sJT_4 = priceGroupNote;
  String __sJT_5 = owner;
  String __sJT_6 = oldGroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:75^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateName(String pgroupId, String pgroupName)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:85^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_PRICE_GROUP")
//  			SET
//  				PRICE_GROUP_NAME = :pgroupName
//  			WHERE 
//  				PRICE_GROUP_ID = :pgroupId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = pgroupName;
  String __sJT_3 = pgroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:92^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String priceGroupId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:102^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP")
//  			WHERE
//  				PRICE_GROUP_ID = :priceGroupId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = priceGroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:108^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:114^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class GroupIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public GroupIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    price_group_idNdx = findColumn("price_group_id");
    price_group_nameNdx = findColumn("price_group_name");
    price_group_typeNdx = findColumn("price_group_type");
    price_group_noteNdx = findColumn("price_group_note");
    ownerNdx = findColumn("owner");
    owner_nameNdx = findColumn("owner_name");
  }
  public String price_group_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_idNdx);
  }
  private int price_group_idNdx;
  public String price_group_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_nameNdx);
  }
  private int price_group_nameNdx;
  public String price_group_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_typeNdx);
  }
  private int price_group_typeNdx;
  public String price_group_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_noteNdx);
  }
  private int price_group_noteNdx;
  public String owner() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ownerNdx);
  }
  private int ownerNdx;
  public String owner_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(owner_nameNdx);
  }
  private int owner_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:115^85*/
	
	public List<PriceGroup> query(String priceGroupType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		GroupIter groupIter = null;
		
		if ("BRAND".equals(priceGroupType)) {// 品牌下分价格组
			/*@lineinfo:generated-code*//*@lineinfo:123^4*/

//  ************************************************************
//  #sql [myCtx] groupIter = { SELECT
//  				    g.*,
//  				    b.BRAND_NAME as owner_name
//  				FROM
//  				:Com_("D_T2_PRICE_GROUP") g
//  				INNER JOIN
//  				:Com_("D_T2_BRAND_GROUP") b
//  				ON
//  				    b.BRAND_ID = g.OWNER
//  				WHERE
//  					g.PRICE_GROUP_TYPE = :priceGroupType
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = Com_("D_T2_BRAND_GROUP");
  String __sJT_3 = priceGroupType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      groupIter = new GroupIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:135^4*/
		} else {
			/*@lineinfo:generated-code*//*@lineinfo:137^4*/

//  ************************************************************
//  #sql [myCtx] groupIter = { SELECT
//  				    g.*,
//  				    '' owner_name
//  				FROM
//  				:Com_("D_T2_PRICE_GROUP") g
//  				WHERE
//  					g.PRICE_GROUP_TYPE = :priceGroupType
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = priceGroupType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      groupIter = new GroupIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:145^4*/
		}
		List<PriceGroup> groupLst = processIter(groupIter);
		groupIter.close();
		closeDefaultContext(myCtx);
		return groupLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:153^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class FGroupIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public FGroupIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    price_group_idNdx = findColumn("price_group_id");
    price_group_nameNdx = findColumn("price_group_name");
    price_group_typeNdx = findColumn("price_group_type");
    price_group_noteNdx = findColumn("price_group_note");
    ownerNdx = findColumn("owner");
    owner_nameNdx = findColumn("owner_name");
  }
  public String price_group_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_idNdx);
  }
  private int price_group_idNdx;
  public String price_group_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_nameNdx);
  }
  private int price_group_nameNdx;
  public String price_group_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_typeNdx);
  }
  private int price_group_typeNdx;
  public String price_group_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_noteNdx);
  }
  private int price_group_noteNdx;
  public String owner() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ownerNdx);
  }
  private int ownerNdx;
  public String owner_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(owner_nameNdx);
  }
  private int owner_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:154^85*/
	
	public List<PriceGroup> Fquery() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		FGroupIter groupIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:160^4*/

//  ************************************************************
//  #sql [myCtx] groupIter = { SELECT
//  				    g.*,
//  				    '' owner_name
//  				FROM
//  					:Com_("D_T2_PRICE_GROUP") g
//  				WHERE
//  					g.PRICE_GROUP_TYPE = 'LC'
//  					and (g.PRICE_GROUP_ID LIKE 'BENCHMARK%' or g.PRICE_GROUP_ID LIKE 'JOIN%' or g.PRICE_GROUP_ID LIKE 'RETAIL%' or g.PRICE_GROUP_ID LIKE 'SALE%')
//  					order by g.PRICE_GROUP_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      groupIter = new FGroupIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:170^4*/
		List<PriceGroup> groupLst = fprocessIter(groupIter);
		groupIter.close();
		closeDefaultContext(myCtx);
		return groupLst;
	}
	
	
	/*@lineinfo:generated-code*//*@lineinfo:178^2*/

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
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    business_idNdx = findColumn("business_id");
    business_nameNdx = findColumn("business_name");
    item_priceNdx = findColumn("item_price");
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
  public String business_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(business_idNdx);
  }
  private int business_idNdx;
  public String business_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(business_nameNdx);
  }
  private int business_nameNdx;
  public String item_price() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_priceNdx);
  }
  private int item_priceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:184^4*/
	public List<Map> queryPrice(String itemId,String supplierId,String priceType,int startRow,int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		PriceIter iter = null;
		String str = "and 1=1";
		if (!TextUtil.isEmpty(itemId)){
			str += "and p.item_id like '%" + itemId + "%'";
		}
		
		if (!TextUtil.isEmpty(supplierId)){
			str += "and p.supplier_id like '%" + supplierId + "%'";
		}
		/*@lineinfo:generated-code*//*@lineinfo:197^3*/

//  ************************************************************
//  #sql [myCtx] iter = { select * from(
//  					select t.*,
//  	                ROWNUM rowNumber from
//  				(select 
//  					p.ITEM_ID item_id,
//  					m.ITEM_NAME item_name,
//  					p.SUPPLIER_ID business_id,
//  					b.BRANCH_NAME business_name,
//  					p.ITEM_PRICE item_price
//  				from 
//  					:Com_("D_T2_ITEM_PRICE")  p 
//  				inner join 
//  					:Com_("D_T2_BRANCH")  b 
//  				on 
//  					p.SUPPLIER_ID = b.BRANCH_ID 
//  				inner join 
//  					:Com_("D_T2_ITEM_META") m  
//  				on 
//  					p.ITEM_ID = m.ITEM_ID 
//  				where 
//  					p.PRICE_TYPE = 'SUPPLIER' :str
//  				order by 
//  					p.ITEM_ID) t
//  					WHERE
//  	                ROWNUM < :endRow
//  					)
//  					 WHERE
//  				        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = str;
  int __sJT_5 = endRow;
  int __sJT_6 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new PriceIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:225^35*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(iter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("item_id", iter.item_id());
			node.put("item_name", iter.item_name());
			node.put("business_id", iter.business_id());
			node.put("business_name", iter.business_name());
			node.put("item_price", iter.item_price());
			nodeLst.add(node);
		}
		iter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:241^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class sumIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public sumIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    sumNdx = findColumn("sum");
  }
  public int sum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(sumNdx);
  }
  private int sumNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:243^4*/
	public int queryPriceCount(String itemId,String supplierId,String priceType)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		sumIter iter = null;
		String str = "and 1=1";
		if (!TextUtil.isEmpty(itemId)){
			str += "and p.item_id like '%" + itemId + "%'";
		}
		
		if (!TextUtil.isEmpty(supplierId)){
			str += "and p.supplier_id like '%" + supplierId + "%'";
		}
		/*@lineinfo:generated-code*//*@lineinfo:256^3*/

//  ************************************************************
//  #sql [myCtx] iter = { select 
//  					count(*) sum
//  				from 
//  					:Com_("D_T2_ITEM_PRICE") p  
//  				inner join 
//  					:Com_("D_T2_BRANCH") b  
//  				on 
//  					p.SUPPLIER_ID = b.BRANCH_ID 
//  				inner join 
//  					:Com_("D_T2_ITEM_META") m  
//  				on 
//  					p.ITEM_ID = m.ITEM_ID 
//  				where 
//  					p.PRICE_TYPE = 'SUPPLIER' :str
//  				order by 
//  					p.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = str;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new sumIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:273^3*/
		int result = 0;
		while(iter.next()) {
			result = iter.sum();
			break;
		}
		iter.close();
		closeDefaultContext(myCtx);
		return result;
	}
	
	public PriceGroup queryById(String priceGroupId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		GroupIter groupIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:288^3*/

//  ************************************************************
//  #sql [myCtx] groupIter = { SELECT
//  		    	g.*,
//  			    '' owner_name
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP") g
//  			WHERE
//  				g.PRICE_GROUP_ID = :priceGroupId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = priceGroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      groupIter = new GroupIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:296^3*/
		List<PriceGroup> groupLst = processIter(groupIter);
		groupIter.close();
		closeDefaultContext(myCtx);
		if (groupLst !=null && !groupLst.isEmpty()) {
			return groupLst.get(0);
		}
		return null;
	}

	//查询所有价格组
	public List<PriceGroup> queryAll() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		GroupIter groupIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:311^3*/

//  ************************************************************
//  #sql [myCtx] groupIter = { SELECT
//  		    	g.*,
//  			    '' owner_name
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP") g
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      groupIter = new GroupIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:317^3*/
		List<PriceGroup> groupLst = processIter(groupIter);
		groupIter.close();
		closeDefaultContext(myCtx);
		return groupLst;
	}
	
		
	public List<PriceGroup> queryByType(String priceType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		if(TextUtil.isEmpty(priceType)){
			priceType="( g.PRICE_GROUP_ID LIKE 'BENCHMARK%' or g.PRICE_GROUP_ID LIKE 'JOIN%' or g.PRICE_GROUP_ID LIKE 'RETAIL%' or g.PRICE_GROUP_ID LIKE 'PURCHASE%' or g.PRICE_GROUP_ID LIKE 'WHOLESALE%' )";
		}
		GroupIter groupIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:332^3*/

//  ************************************************************
//  #sql [myCtx] groupIter = { SELECT
//  				DISTINCT
//  		    	g.*,
//  			    '' owner_name
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP") g
//  			WHERE 
//  			:priceType
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = priceType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      groupIter = new GroupIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:341^3*/
		List<PriceGroup> groupLst = processIter(groupIter);
		groupIter.close();
		closeDefaultContext(myCtx);
		return groupLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:348^2*/

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
    ItemIdNdx = findColumn("ItemId");
    ItemNameNdx = findColumn("ItemName");
    ItemPriceNdx = findColumn("ItemPrice");
  }
  public String ItemId() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ItemIdNdx);
  }
  private int ItemIdNdx;
  public String ItemName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ItemNameNdx);
  }
  private int ItemNameNdx;
  public String ItemPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ItemPriceNdx);
  }
  private int ItemPriceNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:348^81*/
	
	public int count(String priceGroupId)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:355^3*/

//  ************************************************************
//  #sql [myCtx] { select 
//  			count(*) 
//  		from 
//  				
//  			:Com_("D_T2_ITEM_PRICE") A
//  		left join 
//  			:Com_("D_T2_ITEM_META") B
//  		on
//  			A.ITEM_ID = B.ITEM_ID
//  		where 
//  			A.PRICE_TYPE = :priceGroupId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = priceGroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 12);
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

/*@lineinfo:user-code*//*@lineinfo:367^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	//根据门店id和价格组类型找到对应价格组
		public List<PriceGroup> queryByType(String priceType,String branchId) 
				throws NoPrivilegeException,SQLException,NoConnection {
			DefaultContext myCtx = getDefaultContext();
			if(TextUtil.isEmpty(priceType)){
				priceType="( g.PRICE_GROUP_ID LIKE 'BENCHMARK%' or g.PRICE_GROUP_ID LIKE 'JOIN%' or g.PRICE_GROUP_ID LIKE 'RETAIL%' or g.PRICE_GROUP_ID LIKE 'PURCHASE%' or g.PRICE_GROUP_ID LIKE 'WHOLESALE%' )";
			}
			GroupIter groupIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:380^4*/

//  ************************************************************
//  #sql [myCtx] groupIter = { SELECT
//  					DISTINCT
//  			    	g.*,
//  				    '' owner_name
//  				FROM
//  				:Com_("D_T2_PRICE_GROUP_BRANCH") pg
//  				INNER JOIN
//  				:Com_("D_T2_PRICE_GROUP") g
//  				ON
//  				pg.PRICE_GROUP_ID=g.PRICE_GROUP_ID
//  				WHERE 
//  				pg.BRANCH_ID =:branchId 
//  				AND :priceType
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = Com_("D_T2_PRICE_GROUP");
  String __sJT_3 = branchId;
  String __sJT_4 = priceType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 13);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      groupIter = new GroupIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:394^4*/
			List<PriceGroup> groupLst = processIter(groupIter);
			groupIter.close();
			closeDefaultContext(myCtx);
			return groupLst;
		}
	public List<Map> queryItem(String priceGroupId,String itemIdorName,int startRow,int endRow) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String tmp = "%"+itemIdorName+"%";
		DefaultContext myCtx = getDefaultContext();
		ItemIter iIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:405^3*/

//  ************************************************************
//  #sql [myCtx] iIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM 
//  			                (
//  		select 
//  			a.ITEM_ID AS ItemId,
//  			a.ITEM_PRICE AS ItemPrice,
//  			b.ITEM_NAME AS ItemName
//  		from 
//  				
//  			:Com_("D_T2_ITEM_PRICE") A
//  		left join 
//  			 :Com_("D_T2_ITEM_META") B
//  		on
//  			A.ITEM_ID = B.ITEM_ID
//  		where 
//  			A.PRICE_TYPE = :priceGroupId AND (A.ITEM_ID LIKE :tmp or B.ITEM_NAME LIKE :tmp)
//  		) t
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
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = priceGroupId;
  String __sJT_4 = tmp;
  String __sJT_5 = tmp;
  int __sJT_6 = endRow;
  int __sJT_7 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 14);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iIter = new ItemIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:432^34*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(iIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", iIter.ItemId());
			node.put("name", iIter.ItemName());
			node.put("price", iIter.ItemPrice());
			nodeLst.add(node);
		}
		iIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	public PriceGroup fqueryById(String priceGroupId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		FGroupIter groupIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:450^3*/

//  ************************************************************
//  #sql [myCtx] groupIter = { SELECT
//  		    	g.*,
//  			    '' owner_name
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP") g
//  			WHERE
//  				g.PRICE_GROUP_ID = :priceGroupId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = priceGroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 15);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      groupIter = new FGroupIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:458^3*/
		List<PriceGroup> groupLst = fprocessIter(groupIter);
		groupIter.close();
		closeDefaultContext(myCtx);
		if (groupLst !=null && !groupLst.isEmpty()) {
			return groupLst.get(0);
		}
		return null;
	}
	
	private List<PriceGroup> processIter(GroupIter groupIter) 
			throws SQLException {
		List<PriceGroup> groupLst = new ArrayList<PriceGroup>();
		while(groupIter.next()) {
			PriceGroup group = new PriceGroup();
			group.setPriceGroupId(groupIter.price_group_id());
			group.setPriceGroupName(groupIter.price_group_name());
			group.setPriceGroupType(groupIter.price_group_type());
			group.setPriceGroupNote(groupIter.price_group_note());
			group.setOwner(groupIter.owner());
			group.setOwnerName(groupIter.owner_name());
			groupLst.add(group);
		}
		return groupLst;
	}
	
	private List<PriceGroup> fprocessIter(FGroupIter groupIter) 
			throws SQLException {
		List<PriceGroup> groupLst = new ArrayList<PriceGroup>();
		while(groupIter.next()) {
			PriceGroup group = new PriceGroup();
			group.setPriceGroupId(groupIter.price_group_id());
			group.setPriceGroupName(groupIter.price_group_name());
			group.setPriceGroupType(groupIter.price_group_type());
			group.setPriceGroupNote(groupIter.price_group_note());
			group.setOwner(groupIter.owner());
			group.setOwnerName(groupIter.owner_name());
			groupLst.add(group);
		}
		return groupLst;
	}
	
	public int deleteAll(String priceGroup,String defaultId)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			/*@lineinfo:generated-code*//*@lineinfo:504^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T2_PRICE_GROUP_BRANCH") A
//  				SET 
//  					A.PRICE_GROUP_ID = :defaultId
//  				WHERE 
//  					A.PRICE_GROUP_ID = :priceGroup
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = defaultId;
  String __sJT_3 = priceGroup;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 16);
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

/*@lineinfo:user-code*//*@lineinfo:511^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int deletePriceGroupSoOn(String pType)
	throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:521^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_ITEM_PRICE")
//  			WHERE
//  				PRICE_TYPE = :pType
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_PRICE");
  String __sJT_2 = pType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 17);
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

/*@lineinfo:user-code*//*@lineinfo:527^3*/
//		#sql [myCtx] {
//			DELETE
//			FROM
//			:{Com_("D_T2_PRICE_GROUP_BRANCH")}
//			WHERE
//				PRICE_GROUP_ID = :pType
//		};
//		#sql [myCtx] {
//			UPDATE
//			:{Com_("D_T2_PRICE_GROUP_BRANCH")} A
//			SET 
//				A.PRICE_GROUP_ID = :defaultId
//			WHERE 
//				A.PRICE_GROUP_ID = :pType
//		};
		/*@lineinfo:generated-code*//*@lineinfo:543^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP")
//  			WHERE
//  				PRICE_GROUP_ID = :pType
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP");
  String __sJT_2 = pType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PriceGroupBean_SJProfileKeys.getKey(0), 18);
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

/*@lineinfo:user-code*//*@lineinfo:549^3*/
		closeDefaultContext(myCtx);
		return 1;
	}
}/*@lineinfo:generated-code*/class PriceGroupBean_SJProfileKeys 
{
  private static PriceGroupBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PriceGroupBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PriceGroupBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.PriceGroupBean_SJProfile0");
  }
}
