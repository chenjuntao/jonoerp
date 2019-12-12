/*@lineinfo:filename=BrandBranchBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 03 17:06:46 CST 2015 by lyz
 * Last edited on Tue Mar 03 17:06:46 CST 2015 by lyz
 * 
 * comment: 多品牌分组与门店的对应关系
 */
package logic.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.BrandBranch;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class BrandBranchBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(BrandBranchBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

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
    brand_idNdx = findColumn("brand_id");
    branch_idNdx = findColumn("branch_id");
  }
  public String brand_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(brand_idNdx);
  }
  private int brand_idNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^70*/
	
	public int saveEntity(BrandBranch relation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String brandId = relation.getBrandId();
		String branchId = relation.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_BRAND_BRANCH")
//  				(BRAND_ID, BRANCH_ID) 
//  			VALUES (:brandId, :branchId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_BRANCH");
  String __sJT_2 = brandId;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBranchBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:49^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(BrandBranch relation)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String brandId = relation.getBrandId();
		String branchId = relation.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:62^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BRAND_BRANCH")
//  			SET
//  				BRAND_ID = :brandId,
//  				BRANCH_ID = :branchId
//  			WHERE 
//  				FORM_ID = :brandId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_BRANCH");
  String __sJT_2 = brandId;
  String __sJT_3 = branchId;
  String __sJT_4 = brandId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBranchBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:70^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByBrand(String brandId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:80^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_BRAND_BRANCH")
//  			WHERE
//  				BRAND_ID = :brandId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_BRANCH");
  String __sJT_2 = brandId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBranchBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:86^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 删除除此之外的其它记录
	 */
	public int deleteExcept(String brandId, String branchIds) 
			throws NoPrivilegeException, SQLException, NoConnection {
		String query = " BRANCH_ID NOT IN ('" + branchIds.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:100^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_BRAND_BRANCH")
//  			WHERE
//  				BRAND_ID = :brandId
//  			AND	:query
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_BRANCH");
  String __sJT_2 = brandId;
  String __sJT_3 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBranchBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:107^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public boolean exist(String brandId, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:117^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				COUNT(*) 
//  			FROM
//  			:Com_("D_T2_BRAND_BRANCH") b
//  			WHERE
//  				b.BRAND_ID = :brandId
//  			AND b.BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_BRANCH");
  String __sJT_2 = brandId;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBranchBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:125^3*/
		closeDefaultContext(myCtx);
		
		if (total > 0) {
			return true;
		}
		return false;
	}
	
	public List<BrandBranch> query() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		RelationIter relationIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:138^3*/

//  ************************************************************
//  #sql [myCtx] relationIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_BRAND_BRANCH") h
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBranchBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:143^3*/
		List<BrandBranch> relationLst = processIter(relationIter);
		relationIter.close();
		closeDefaultContext(myCtx);
		return relationLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:150^2*/

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

/*@lineinfo:user-code*//*@lineinfo:150^99*/
	
	public List<Map> queryTree(String brandId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		NodeIter nodeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:156^3*/

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
//  			        WHEN bb.BRANCH_ID IS NULL
//  			        THEN ''
//  			        ELSE 'checked'
//  			    END AS checked
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = rb.BRANCH_ID
//  			LEFT JOIN
//  			:Com_("D_T2_BRAND_BRANCH") bb
//  			ON
//  			    b.BRANCH_ID = bb.BRANCH_ID
//  			AND bb.BRAND_ID = :brandId
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
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBranchBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:187^3*/
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
	
	private List<BrandBranch> processIter(RelationIter relationIter) 
			throws SQLException {
		List<BrandBranch> relationLst = new ArrayList<BrandBranch>();
		while(relationIter.next()) {
			BrandBranch relation = new BrandBranch();
			relation.setBrandId(relationIter.brand_id());
			relation.setBranchId(relationIter.branch_id());
			relationLst.add(relation);
		}
		return relationLst;
	}
}/*@lineinfo:generated-code*/class BrandBranchBean_SJProfileKeys 
{
  private static BrandBranchBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BrandBranchBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BrandBranchBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.BrandBranchBean_SJProfile0");
  }
}
