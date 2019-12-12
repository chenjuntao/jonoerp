/*@lineinfo:filename=PgroupBranchBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 03 17:07:55 CST 2015 by lyz
 * Last edited on Tue Mar 03 17:07:55 CST 2015 by lyz
 * 
 * comment: 价格组与门店的对应关系
 */
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.PgroupBranch;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PgroupBranchBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PgroupBranchBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class RelationIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public RelationIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    price_group_idNdx = findColumn("price_group_id");
    branch_idNdx = findColumn("branch_id");
  }
  public String price_group_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_group_idNdx);
  }
  private int price_group_idNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^76*/
	public int saveEntity(PgroupBranch relation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return saveEntityImp(relation, myCtx);
	}
	
	public int saveEntity(PgroupBranch relation, String comId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return saveEntityImp(relation, myCtx);
	}
	
	private int saveEntityImp(PgroupBranch relation, DefaultContext myCtx)
		throws NoPrivilegeException, SQLException, NoConnection {
		
		String priceGroupId = relation.getPriceGroupId();
		String branchId = relation.getBranchId();
		
		/*@lineinfo:generated-code*//*@lineinfo:56^3*/

//  ************************************************************
//  #sql [myCtx] { MERGE INTO :Com_("D_T2_PRICE_GROUP_BRANCH") USING dual 
//  			ON (PRICE_GROUP_ID=:priceGroupId AND BRANCH_ID=:branchId)
//  			WHEN NOT MATCHED THEN INSERT (PRICE_GROUP_ID, BRANCH_ID) 
//  			    VALUES (:priceGroupId, :branchId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = priceGroupId;
  String __sJT_3 = branchId;
  String __sJT_4 = priceGroupId;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:61^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(PgroupBranch relation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String priceGroupId = relation.getPriceGroupId();
		String branchId = relation.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:74^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_PRICE_GROUP_BRANCH")
//  			SET
//  				PRICE_GROUP_ID = :priceGroupId,
//  				BRANCH_ID = :branchId
//  			WHERE 
//  				FORM_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = priceGroupId;
  String __sJT_3 = branchId;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:82^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String branchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:92^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP_BRANCH")
//  			WHERE
//  				BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 2);
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
	public int deleteAll(String priceGroup,String defaultId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:107^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_PRICE_GROUP_BRANCH") A
//  			SET 
//  				A.PRICE_GROUP_ID = :defaultId
//  			WHERE 
//  				A.PRICE_GROUP_ID = :priceGroup
//  		 };
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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:114^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/**
	 * 删除除此之外的其它记录
	 */
	public int deleteExcept(String priceGroupId, String branchIds) 
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " BRANCH_ID NOT IN ('" + branchIds.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:128^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP_BRANCH")
//  			WHERE
//  				PRICE_GROUP_ID = :priceGroupId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = priceGroupId;
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:135^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateExcept(String priceGroupId, String branchIds, String defaultId) 
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " BRANCH_ID NOT IN ('" + branchIds.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:146^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_PRICE_GROUP_BRANCH") A
//  			SET 
//  				A.PRICE_GROUP_ID = :defaultId
//  			WHERE 
//  				:query
//  			AND 
//  				A.PRICE_GROUP_ID = :priceGroupId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = defaultId;
  String __sJT_3 = query;
  String __sJT_4 = priceGroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:155^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deletePreAll(String branchIds,String pGId) 
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " BRANCH_ID IN ('" + branchIds.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:166^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP_BRANCH")
//  			WHERE
//  				:query
//  			AND
//  				PRICE_GROUP_ID = :pGId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = query;
  String __sJT_3 = pGId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:174^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public boolean exist(String priceGroupId, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:184^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				COUNT(*) 
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP_BRANCH") b
//  			WHERE
//  				b.PRICE_GROUP_ID = :priceGroupId
//  			AND b.BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = priceGroupId;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 7);
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

/*@lineinfo:user-code*//*@lineinfo:192^3*/
		closeDefaultContext(myCtx);
		
		if (total > 0) {
			return true;
		}
		return false;
	}

	/*@lineinfo:generated-code*//*@lineinfo:201^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class NodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public NodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    parentNdx = findColumn("parent");
    typeNdx = findColumn("type");
    checkedNdx = findColumn("checked");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(nameNdx);
  }
  private int nameNdx;
  public String parent() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parentNdx);
  }
  private int parentNdx;
  public String type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(typeNdx);
  }
  private int typeNdx;
  public String checked() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(checkedNdx);
  }
  private int checkedNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:201^99*/
	
	public List<Map> queryTree(String priceGroupId, String brandId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:207^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { SELECT
//  			    r.REGION_ID   AS id ,
//  			    r.REGION_NAME AS name,
//  			    'root'        AS parent,
//  			    'region'      AS type,
//  			    ''               checked
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION") r
//  			UNION ALL
//  			SELECT
//  			    b.BRANCH_ID   AS id ,
//  			    b.BRANCH_NAME AS name,
//  			    rb.REGION_ID  AS parent,
//  			    'branch'      AS type,
//  			    CASE
//  			        WHEN pb.BRANCH_ID IS NULL
//  			        THEN ''
//  			        ELSE 'checked'
//  			    END AS checked
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = rb.BRANCH_ID
//  			INNER JOIN --筛选某品牌下相关的门店
//  			:Com_("D_T2_BRAND_BRANCH") bb
//  			ON
//  			    b.BRANCH_ID = bb.BRANCH_ID
//  			AND bb.BRAND_ID = :brandId
//  			LEFT JOIN
//  			:Com_("D_T2_PRICE_GROUP_BRANCH") pb
//  			ON
//  			    pb.BRANCH_ID = b.BRANCH_ID
//  			WHERE
//  			    pb.PRICE_GROUP_ID = :priceGroupId
//  			OR  pb.PRICE_GROUP_ID IS NULL --排除属于其它价格组的门店
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_2 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T2_BRAND_BRANCH");
  String __sJT_5 = brandId;
  String __sJT_6 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_7 = priceGroupId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 8);
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
      nodeIter = new NodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:245^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("parent", nodeIter.parent());
			node.put("type", nodeIter.type());
			node.put("checked", nodeIter.checked());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:261^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class FNodeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public FNodeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    idNdx = findColumn("id");
    nameNdx = findColumn("name");
    typeNdx = findColumn("type");
    parentNdx = findColumn("parent");
    checkedNdx = findColumn("checked");
  }
  public String id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(idNdx);
  }
  private int idNdx;
  public String name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(nameNdx);
  }
  private int nameNdx;
  public String type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(typeNdx);
  }
  private int typeNdx;
  public String parent() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parentNdx);
  }
  private int parentNdx;
  public String checked() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(checkedNdx);
  }
  private int checkedNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:261^100*/
	
	public List<Map> queryAllFranchisee(String PgType,String branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String query = " A.BRANCH_TYPE IN ('" + branchType.replaceAll(",", "','") + "') ";
		
		FNodeIter nodeIter = null;
		String group = "and b.PRICE_GROUP_ID like 'JOIN%'";
		if (branchType.equals("OUTERORDER")) group = "and b.PRICE_GROUP_ID like 'RETAIL%'";
		if (branchType.equals("RESTAURANT")) group = "and b.PRICE_GROUP_ID like 'BENCHMARK%'";
		if (branchType.equals("CENTRALFACTORY,LOGISTICSCENTER,RESTAURANT")) group = "and b.PRICE_GROUP_ID like 'BENCHMARK%'";
		if (PgType.indexOf("SALE")!=-1) group = "and b.PRICE_GROUP_ID like 'SALE%'";
		/*@lineinfo:generated-code*//*@lineinfo:274^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { select 
//  				A.BRANCH_ID as id,
//  				A.BRANCH_NAME as name ,
//  				A.BRANCH_TYPE as type ,
//  				'root' as parent,
//  				DECODE(B.PRICE_GROUP_ID,:PgType,'Y','N') as checked
//  			from 
//  			:Com_("D_T2_BRANCH") A 
//  			left join 
//  			:Com_("D_T2_PRICE_GROUP_BRANCH") B 
//  			on
//  				A.BRANCH_ID = B.BRANCH_ID  :group
//  			where 
//  				:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = PgType;
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_4 = group;
  String __sJT_5 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      nodeIter = new FNodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:289^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("type", nodeIter.type());
			node.put("parent", nodeIter.parent());
			node.put("checked",nodeIter.checked());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	public List<Map> queryFranchisee(String PgID,String BranchType,String PgType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		String tmp = PgType + "%";
		FNodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:309^3*/

//  ************************************************************
//  #sql [myCtx] nodeIter = { WITH
//  		    a AS
//  		    (
//  		        SELECT
//  		            b.BRANCH_ID
//  		        FROM
//  		        	:Com_("D_T2_BRANCH") b
//  		        WHERE
//  		            b.BRANCH_TYPE = :BranchType
//  		    )
//  		    ,
//  		    b AS
//  		    (
//  		        SELECT
//  		            b.BRANCH_ID
//  		        FROM
//  		           :Com_("D_T2_PRICE_GROUP") A
//  		        LEFT JOIN
//  		           :Com_("D_T2_PRICE_GROUP_BRANCH") B
//  		        ON
//  		            A.PRICE_GROUP_ID = B.PRICE_GROUP_ID
//  		        WHERE
//  		            A.PRICE_GROUP_ID LIKE :tmp
//  		        AND a.PRICE_GROUP_ID != :PgID
//  		        AND b.BRANCH_ID IS NOT NULL
//  		    )
//  		    ,
//  		    c AS
//  		    (
//  		        SELECT
//  		            *
//  		        FROM
//  		            a
//  		        MINUS
//  		        SELECT
//  		            *
//  		        FROM
//  		            b
//  		    )
//  		    ,
//  		    d AS
//  		    (
//  		        SELECT
//  		            b.BRANCH_ID
//  		        FROM
//  		           :Com_("D_T2_PRICE_GROUP") A
//  		        LEFT JOIN
//  		           :Com_("D_T2_PRICE_GROUP_BRANCH") B
//  		        ON
//  		            A.PRICE_GROUP_ID = B.PRICE_GROUP_ID
//  		        WHERE
//  		            a.PRICE_GROUP_ID = :PgID
//  		    )
//  			,
//  			e as
//  			(
//  				SELECT
//  				    c.branch_id,
//  				    DECODE(d.branch_id,NULL,'N','Y') flag
//  				FROM
//  				    c
//  				LEFT JOIN
//  				    d
//  				ON
//  				    c.branch_id = d.branch_id
//  			)
//  			,
//  			f as
//  			(
//  				select S.BRANCH_ID,S.BRANCH_NAME,S.BRANCH_TYPE,E.FLAG from:Com_("D_T2_BRANCH") S 
//  				LEFT JOIN E ON E.BRANCH_ID = S.BRANCH_ID 
//  			)
//  			select f.branch_id as id,
//  			f.branch_name as name,
//  			f.branch_type as type,
//  			'root' as parent,
//  			f.flag as checked from f where f.flag = 'Y' or f.flag = 'N' 
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = BranchType;
  String __sJT_3 = Com_("D_T2_PRICE_GROUP");
  String __sJT_4 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_5 = tmp;
  String __sJT_6 = PgID;
  String __sJT_7 = Com_("D_T2_PRICE_GROUP");
  String __sJT_8 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_9 = PgID;
  String __sJT_10 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 10);
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
      nodeIter = new FNodeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:387^3*/
		List<Map> nodeLst = new ArrayList<Map>();
		while(nodeIter.next()) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", nodeIter.id());
			node.put("name", nodeIter.name());
			node.put("type", nodeIter.type());
			node.put("parent", nodeIter.parent());
			node.put("checked",nodeIter.checked());
			nodeLst.add(node);
		}
		nodeIter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
	
	public List<PgroupBranch> query() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RelationIter relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:407^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP_BRANCH") h where h.PRICE_GROUP_ID != 'SALE' 
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      relationIter = new RelationIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:412^3*/
		List<PgroupBranch> relationLst = processIter(relationIter);
		relationIter.close();
		closeDefaultContext(myCtx);
		return relationLst;
	}
	
	public PgroupBranch queryBenchmark(String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RelationIter relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:423^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  			    h.*
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP_BRANCH") h 
//  			  where 
//  			  h.BRANCH_ID = :branchId  and  h.PRICE_GROUP_ID like 'BENCHMARK%' 
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      relationIter = new RelationIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:430^3*/
		List<PgroupBranch> relationLst = processIter(relationIter);
		relationIter.close();
		closeDefaultContext(myCtx);
		if (relationLst !=null && !relationLst.isEmpty()) {
			return relationLst.get(0);
		}
		return null;
	}
	
	public PgroupBranch querySale(String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RelationIter relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:444^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  			    h.*
//  			FROM
//  			:Com_("D_T2_PRICE_GROUP_BRANCH") h 
//  			  where 
//  			  h.BRANCH_ID = :branchId  and  h.PRICE_GROUP_ID like 'SALE%' 
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_PRICE_GROUP_BRANCH");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PgroupBranchBean_SJProfileKeys.getKey(0), 13);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      relationIter = new RelationIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:451^3*/
		List<PgroupBranch> relationLst = processIter(relationIter);
		relationIter.close();
		closeDefaultContext(myCtx);
		if (relationLst !=null && !relationLst.isEmpty()) {
			return relationLst.get(0);
		}
		return null;
	}
	
	private List<PgroupBranch> processIter(RelationIter relationIter) 
			throws SQLException {
		List<PgroupBranch> relationLst = new ArrayList<PgroupBranch>();
		while(relationIter.next()) {
			PgroupBranch relation = new PgroupBranch();
			relation.setPriceGroupId(relationIter.price_group_id());
			relation.setBranchId(relationIter.branch_id());
			relationLst.add(relation);
		}
		return relationLst;
	}
}/*@lineinfo:generated-code*/class PgroupBranchBean_SJProfileKeys 
{
  private static PgroupBranchBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PgroupBranchBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PgroupBranchBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.PgroupBranchBean_SJProfile0");
  }
}
