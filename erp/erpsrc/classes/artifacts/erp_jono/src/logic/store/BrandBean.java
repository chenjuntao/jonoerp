/*@lineinfo:filename=BrandBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Mar 03 17:06:46 CST 2015 by lyz
 * Last edited on Tue Mar 03 17:06:46 CST 2015 by lyz
 * 
 * comment: 门店多品牌
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.Brand;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class BrandBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(BrandBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class BrandIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BrandIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    brand_idNdx = findColumn("brand_id");
    brand_nameNdx = findColumn("brand_name");
    brand_noteNdx = findColumn("brand_note");
  }
  public String brand_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(brand_idNdx);
  }
  private int brand_idNdx;
  public String brand_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(brand_nameNdx);
  }
  private int brand_nameNdx;
  public String brand_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(brand_noteNdx);
  }
  private int brand_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^87*/
	
	public int saveEntity(Brand brand)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String brandId = brand.getBrandId();
		String brandName = brand.getBrandName();
		String brandNote = brand.getBrandNote();

		/*@lineinfo:generated-code*//*@lineinfo:45^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_BRAND_GROUP") 
//  				(BRAND_ID, BRAND_NAME, BRAND_NOTE) 
//  			VALUES (:brandId, :brandName, :brandNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_GROUP");
  String __sJT_2 = brandId;
  String __sJT_3 = brandName;
  String __sJT_4 = brandNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:49^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(String oldBrandId, Brand brand)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String brandId = brand.getBrandId();
		String brandName = brand.getBrandName();
		String brandNote = brand.getBrandNote();

		/*@lineinfo:generated-code*//*@lineinfo:63^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BRAND_GROUP")
//  			SET
//  				BRAND_ID = :brandId,
//  				BRAND_NAME = :brandName,
//  				BRAND_NOTE = :brandNote
//  			WHERE 
//  				BRAND_ID = :oldBrandId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_GROUP");
  String __sJT_2 = brandId;
  String __sJT_3 = brandName;
  String __sJT_4 = brandNote;
  String __sJT_5 = oldBrandId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:72^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String brandId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:82^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_BRAND_GROUP")
//  			WHERE
//  				BRAND_ID = :brandId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_GROUP");
  String __sJT_2 = brandId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:88^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public List<Brand> query() 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		BrandIter brandIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:98^3*/

//  ************************************************************
//  #sql [myCtx] brandIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_BRAND_GROUP") b
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_GROUP");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      brandIter = new BrandIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:103^3*/
		List<Brand> brandLst = processIter(brandIter);
		brandIter.close();
		closeDefaultContext(myCtx);
		return brandLst;
	}
	
	public Brand queryById(String brandId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		BrandIter brandIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:114^3*/

//  ************************************************************
//  #sql [myCtx] brandIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_BRAND_GROUP") b
//  			WHERE
//  				b.BRAND_ID = :brandId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRAND_GROUP");
  String __sJT_2 = brandId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BrandBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      brandIter = new BrandIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:121^3*/
		List<Brand> brandLst = processIter(brandIter);
		brandIter.close();
		closeDefaultContext(myCtx);
		if (brandLst !=null && !brandLst.isEmpty()) {
			return brandLst.get(0);
		}
		return null;
	}
	
	private List<Brand> processIter(BrandIter brandIter) 
			throws SQLException {
		List<Brand> brandLst = new ArrayList<Brand>();
		while(brandIter.next()) {
			Brand brand = new Brand();
			brand.setBrandId(brandIter.brand_id());
			brand.setBrandName(brandIter.brand_name());
			brand.setBrandNote(brandIter.brand_note());
			brandLst.add(brand);
		}
		return brandLst;
	}
}/*@lineinfo:generated-code*/class BrandBean_SJProfileKeys 
{
  private static BrandBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BrandBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BrandBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.BrandBean_SJProfile0");
  }
}
