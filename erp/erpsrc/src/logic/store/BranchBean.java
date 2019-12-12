/*@lineinfo:filename=BranchBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2014.8.6 by cjt
 * Last edited on Nov 20, 2014 by liyzh
 * 
 * 说明： 读取部门门店基本信息
 */
package logic.store;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import pojo.store.Branch;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
//import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class BranchBean extends ConnectionPool {
	
	/**
	 * companyId由调用者通过参数指定,by cjt
	 */
	public int saveEntity(Branch branch, String comId)throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return saveEntityImp(branch, myCtx);
	}
	
	/**
	 * companyId默认通过session访问,by cjt
	 */
	public int saveEntity(Branch branch)throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return saveEntityImp(branch, myCtx);
	}
	
	private int saveEntityImp(Branch branch, DefaultContext myCtx)throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = branch.getBranchId();
		String branchName = branch.getBranchName();
		String queryCode = branch.getQueryCode();
		String branchType = branch.getBranchType();
		String priceType = branch.getPriceType();
		String branchAddress = branch.getBranchAddress();
		String contactMan = branch.getContactMan();
		String email = branch.getEmail();
		String telephone = branch.getTelephone();
		String fax = branch.getFax();
		String remark = branch.getRemark();
		Integer enabled = branch.getEnabled();
		String park = branch.getPark();
		String tag = branch.getRemark();
		double lon = branch.getLon();
		double lat = branch.getLat();
		Date businessDate = SqlDateUtil.getSqlDate(branch.getBusinessDate());
		if(businessDate == null){ //如果没指定营业日期，则取当前日期
			String dtfmt = DateTimeUtil.DEFAULT_DATE_FORMAT;
			businessDate = SqlDateUtil.getSqlDate(DateTimeUtil.parse(DateTimeUtil.getDateTime(new java.util.Date(), dtfmt), dtfmt));
		}
		
		/*@lineinfo:generated-code*//*@lineinfo:83^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_BRANCH") (BRANCH_ID, BRANCH_NAME, QUERY_CODE, BRANCH_ADDRESS, CONTACT_MAN, 
//  					EMAIL, TELEPHONE, FAX, BRANCH_TYPE, PRICE_TYPE, REMARK, ENABLED, PARK, TAG, LON, LAT, BUSINESS_DATE) 
//  				VALUES (:branchId, :branchName, :queryCode, :branchAddress, :contactMan, :email, :telephone, 
//  						:fax, :branchType, :priceType, :remark, :enabled, :park, :tag, :lon, :lat, :businessDate)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = branchId;
  String __sJT_3 = branchName;
  String __sJT_4 = queryCode;
  String __sJT_5 = branchAddress;
  String __sJT_6 = contactMan;
  String __sJT_7 = email;
  String __sJT_8 = telephone;
  String __sJT_9 = fax;
  String __sJT_10 = branchType;
  String __sJT_11 = priceType;
  String __sJT_12 = remark;
  Integer __sJT_13 = enabled;
  String __sJT_14 = park;
  String __sJT_15 = tag;
  double __sJT_16 = lon;
  double __sJT_17 = lat;
  Date __sJT_18 = businessDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setIntWrapper(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setDouble(16, __sJT_16);
      __sJT_stmt.setDouble(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
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
	public int updateEntity(String oldBranchId, Branch branch, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return updateEntityImp(oldBranchId, branch, myCtx);
	}
		
	/**
	 * companyId默认通过session访问,by cjt
	 */
	public int updateEntity(String oldBranchId, Branch branch) 
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return updateEntityImp(oldBranchId, branch, myCtx);
	}
	
	private int updateEntityImp(String oldBranchId, Branch branch, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		
		String branchId = branch.getBranchId();
		String branchName = branch.getBranchName();
		String queryCode = branch.getQueryCode();
		String branchType = branch.getBranchType();
		String priceType = branch.getPriceType();
		String branchAddress = branch.getBranchAddress();
		String contactMan = branch.getContactMan();
		String email = branch.getEmail();
		String telephone = branch.getTelephone();
		String fax = branch.getFax();
		String remark = branch.getRemark();
		Integer enabled = branch.getEnabled();
		String park = branch.getPark();
		String tag = branch.getRemark();
		double lon = branch.getLon();
		double lat = branch.getLat();

		/*@lineinfo:generated-code*//*@lineinfo:133^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BRANCH")
//  			SET
//  			    BRANCH_ID = :branchId,
//  			    BRANCH_NAME = :branchName,
//  			    QUERY_CODE = :queryCode,
//  			    PRICE_TYPE = :priceType,
//  			    BRANCH_ADDRESS = :branchAddress,
//  			    CONTACT_MAN = :contactMan,
//  			    EMAIL = :email,
//  			    TELEPHONE = :telephone,
//  			    FAX = :fax,
//  			    REMARK = :remark,
//  			    ENABLED = :enabled,
//  			    PARK = :park,
//  			    TAG = :tag,
//  			    LON = :lon,
//  			    LAT = :lat
//  			WHERE 
//  				BRANCH_ID = :oldBranchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = branchId;
  String __sJT_3 = branchName;
  String __sJT_4 = queryCode;
  String __sJT_5 = priceType;
  String __sJT_6 = branchAddress;
  String __sJT_7 = contactMan;
  String __sJT_8 = email;
  String __sJT_9 = telephone;
  String __sJT_10 = fax;
  String __sJT_11 = remark;
  Integer __sJT_12 = enabled;
  String __sJT_13 = park;
  String __sJT_14 = tag;
  double __sJT_15 = lon;
  double __sJT_16 = lat;
  String __sJT_17 = oldBranchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setIntWrapper(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setDouble(15, __sJT_15);
      __sJT_stmt.setDouble(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:154^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 是否包含该项,by cjt
	 */
	public boolean containsEntity(String branchId, String branchType, String comId)
			throws NoPrivilegeException, SQLException, NoPrivilegeException{
		DefaultContext myCtx = getComContext(comId);
		int count = 0;
		/*@lineinfo:generated-code*//*@lineinfo:167^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*) 
//  			from :Com_("D_T2_BRANCH")
//  			where BRANCH_ID = :branchId
//  			and BRANCH_TYPE = :branchType
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = branchId;
  String __sJT_3 = branchType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:172^3*/

		closeDefaultContext(myCtx);
		return count > 0;
	}

	public int deleteEntity(String branchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:182^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_BRANCH")
//  			WHERE
//  				BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:188^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	// the branch has contain items with  supplier or not 
	public boolean containsItemsWithSupplier(String branchId)
			throws NoPrivilegeException, SQLException, NoPrivilegeException{
		DefaultContext myCtx = getDefaultContext();
		int count = 0;
		try {
			/*@lineinfo:generated-code*//*@lineinfo:200^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				    COUNT(*)
//  				
//  				FROM
//  					:Com_("D_T2_SUPPLIER_BRANCH_ITEM") i 
//  				WHERE
//  				    i.BRANCH_ID = :branchId };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:208^32*/
		} catch (Exception e) {
		}
		
		closeDefaultContext(myCtx);
		return count > 0;
	}
	
	// the supplier has contain items with  branch or not 
		public boolean containsItemsWithBranch(String supplierId)
				throws NoPrivilegeException, SQLException, NoPrivilegeException{
			DefaultContext myCtx = getDefaultContext();
			int count = 0;
			try {
				/*@lineinfo:generated-code*//*@lineinfo:222^5*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  					    COUNT(*)
//  					
//  					FROM
//  						:Com_("D_T2_SUPPLIER_BRANCH_ITEM") i 
//  					WHERE
//  					    i.SUPPLIER_ID = :supplierId };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = supplierId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:230^37*/
			} catch (Exception e) {
			}
			
			closeDefaultContext(myCtx);
			return count > 0;
		}
		
	
	/**
	 * 启停用部门
	 */
	public int enableBranch(String branchId, Integer enabled)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		/*@lineinfo:generated-code*//*@lineinfo:246^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_BRANCH")
//  			SET
//  			   	ENABLED = :enabled
//  			WHERE 
//  				BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  Integer __sJT_2 = enabled;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setIntWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:253^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/*@lineinfo:generated-code*//*@lineinfo:259^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class BranchIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BranchIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    query_codeNdx = findColumn("query_code");
    branch_typeNdx = findColumn("branch_type");
    price_typeNdx = findColumn("price_type");
    branch_addressNdx = findColumn("branch_address");
    emailNdx = findColumn("email");
    telephoneNdx = findColumn("telephone");
    faxNdx = findColumn("fax");
    remarkNdx = findColumn("remark");
    contact_manNdx = findColumn("contact_man");
    enabledNdx = findColumn("enabled");
    business_dateNdx = findColumn("business_date");
    region_idNdx = findColumn("region_id");
    region_nameNdx = findColumn("region_name");
  }
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String branch_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_nameNdx);
  }
  private int branch_nameNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String branch_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_typeNdx);
  }
  private int branch_typeNdx;
  public String price_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(price_typeNdx);
  }
  private int price_typeNdx;
  public String branch_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_addressNdx);
  }
  private int branch_addressNdx;
  public String email() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(emailNdx);
  }
  private int emailNdx;
  public String telephone() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(telephoneNdx);
  }
  private int telephoneNdx;
  public String fax() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(faxNdx);
  }
  private int faxNdx;
  public String remark() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(remarkNdx);
  }
  private int remarkNdx;
  public String contact_man() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(contact_manNdx);
  }
  private int contact_manNdx;
  public Integer enabled() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(enabledNdx);
  }
  private int enabledNdx;
  public Date business_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(business_dateNdx);
  }
  private int business_dateNdx;
  public String region_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(region_idNdx);
  }
  private int region_idNdx;
  public String region_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(region_nameNdx);
  }
  private int region_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:262^39*/

	public Branch GetBranchById(String id) 
			throws NoPrivilegeException,SQLException,NoConnection {
		Branch branch = null;
		DefaultContext myCtx = getDefaultContext();
		BranchIter branchIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:269^3*/

//  ************************************************************
//  #sql [myCtx] branchIter = { SELECT
//  		    	b.*,
//  			    '' region_id,
//  			    '' region_name
//  			FROM
//  			:Com_("d_t2_branch") b
//  			WHERE
//  			    branch_id = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_branch");
  String __sJT_2 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      branchIter = new BranchIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:278^3*/
		
		List<Branch> branchLst = processIter(branchIter);
		branchIter.close();
		closeDefaultContext(myCtx);
		
		if (branchLst !=null && !branchLst.isEmpty()) {
			return branchLst.get(0);
		}

		return null;
	}
	
	/**
	 * 根据部门类型以及部门ID或者部门名称作为关键字进行查询
	 */
	public List<Branch> queryByTypeName(BranchTypeEnum branchType, String branchName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		return queryByTypeName(branchType,branchName,null);
	}
	
	public List<Branch> queryByTypeName(BranchTypeEnum branchType, String branchName,String queryAllFlag) 
			throws NoPrivilegeException,SQLException,NoConnection {
		branchName = "%" + branchName + "%";
		String query = formBranchTypeSql(branchType,queryAllFlag);
		
		DefaultContext myCtx = getDefaultContext();
		BranchIter branchIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:306^3*/

//  ************************************************************
//  #sql [myCtx] branchIter = { SELECT
//  			    b.*,
//  			    '' region_id,
//  			    '' region_name
//  			FROM
//  			:Com_("D_T2_BRANCH") b
//  			WHERE
//  				1=1   :query
//  			AND (
//  			        BRANCH_ID LIKE :branchName
//  			    OR  BRANCH_NAME LIKE :branchName)
//  			ORDER BY
//  			    BRANCH_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = query;
  String __sJT_3 = branchName;
  String __sJT_4 = branchName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      branchIter = new BranchIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:319^17*/
		List<Branch> branchLst = processIter(branchIter);
		branchIter.close();
		closeDefaultContext(myCtx);
	
		return branchLst;
	}

	/**
	 * 查询餐厅及对应的分组信息
	 */
	public List<Branch> queryRestaurant(BranchTypeEnum branchType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<Branch> result = new ArrayList<Branch>();
		DefaultContext myCtx = getDefaultContext();
		BranchIter branchIter = null;
		String branchTypeStr = branchType.toString();
		/*@lineinfo:generated-code*//*@lineinfo:336^3*/

//  ************************************************************
//  #sql [myCtx] branchIter = { SELECT
//  			    b.*,
//  			    rb.REGION_ID,
//  			    r.REGION_NAME
//  			FROM
//  				:Com_("D_T2_BRANCH") b
//  			LEFT JOIN
//  				:Com_("D_T2_DELIVERY_REGION_BRANCH") rb
//  			ON
//  			    rb.BRANCH_ID = b.BRANCH_ID
//  			LEFT JOIN
//  				:Com_("D_T2_DELIVERY_REGION") r
//  			ON
//  			    r.REGION_ID = rb.REGION_ID
//  			WHERE
//  			    b.BRANCH_TYPE = :branchTypeStr
//  			ORDER BY
//  				1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = Com_("D_T2_DELIVERY_REGION_BRANCH");
  String __sJT_3 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_4 = branchTypeStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      branchIter = new BranchIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:355^3*/
		List<Branch> branchLst = processIter(branchIter);
		branchIter.close();
		closeDefaultContext(myCtx);
		return branchLst;
	}

	private List<Branch> processIter(BranchIter branchIter) 
			throws SQLException {
		List<Branch> branchLst = new ArrayList<Branch>();
		while(branchIter.next()) {
			Branch branch = new Branch();
			branch.setBranchId(branchIter.branch_id());
			branch.setBranchName(branchIter.branch_name());
			branch.setQueryCode(branchIter.query_code());
			branch.setBranchType(branchIter.branch_type());
			branch.setPriceType(branchIter.price_type());
			branch.setBranchAddress(branchIter.branch_address());
			branch.setEmail(branchIter.email());
			branch.setTelephone(branchIter.telephone());
			branch.setFax(branchIter.fax());
			branch.setRemark(branchIter.remark());
			branch.setContactMan(branchIter.contact_man());
			branch.setEnabled(branchIter.enabled());
			branch.setRegionId(branchIter.region_id());
			branch.setRegionName(branchIter.region_name());
			branch.setBusinessDate(SqlDateUtil.getUtilDate(branchIter.business_date()));
			branchLst.add(branch);
		}
		return branchLst;
	}
	
	//根据BranchTypeEnum构建查询门店类型的SQL语句
	private String formBranchTypeSql(BranchTypeEnum branchType,String queryAllFlag){
		String query = " AND BRANCH_TYPE = 'RESTAURANT'";
		switch(branchType){
			case R_Out:
				query = " AND BRANCH_TYPE IN('RESTAURANT','OUTERORDER')";
				break;
			case S_Center:
				query = " AND BRANCH_TYPE IN('SUPPLIER','CENTRALFACTORY')";
				break;
			case R_C_L:
				query = " AND BRANCH_TYPE IN('RESTAURANT','CENTRALFACTORY','LOGISTICSCENTER')";
				break;
			case R_F_O:
				query = " AND BRANCH_TYPE IN('RESTAURANT','FRANCHISEE','OUTERORDER')";
				break;
			default:
				query = " AND BRANCH_TYPE = '" + branchType.toString() + "'";
				break;
		}
		
		if(TextUtil.isEmpty(queryAllFlag)){
			 query += " AND b.ENABLED = 1 ";
		}
		
		return query;
	}
	
	//根据BranchTypeEnum构建查询门店类型的SQL语句
		private String formBranchTypeSql(String queryHasComma,String queryAllFlag){
			String query = " AND BRANCH_TYPE IN ('" + queryHasComma.replaceAll(",", "','") + "')";
			
			if(TextUtil.isEmpty(queryAllFlag)){
				 query += " AND b.ENABLED = 1 ";
			}
			
			return query;
		}
	
	//门店简单信息-------------------------------------------------------------------------------------------
	/*@lineinfo:generated-code*//*@lineinfo:427^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ShopIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ShopIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    BRANCH_IDNdx = findColumn("BRANCH_ID");
    BRANCH_NAMENdx = findColumn("BRANCH_NAME");
    QUERY_CODENdx = findColumn("QUERY_CODE");
    BRANCH_TYPENdx = findColumn("BRANCH_TYPE");
  }
  public String BRANCH_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(BRANCH_IDNdx);
  }
  private int BRANCH_IDNdx;
  public String BRANCH_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(BRANCH_NAMENdx);
  }
  private int BRANCH_NAMENdx;
  public String QUERY_CODE() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(QUERY_CODENdx);
  }
  private int QUERY_CODENdx;
  public String BRANCH_TYPE() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(BRANCH_TYPENdx);
  }
  private int BRANCH_TYPENdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:431^22*/

	/**
	 * 根据部门类型查询门店简单信息，如果branchType为null，则默认查询餐厅
	 */
	public List<Map> listShopByType(BranchTypeEnum branchType) throws NoPrivilegeException,SQLException,NoConnection	{
		String query = formBranchTypeSql(branchType,null);
		
		DefaultContext myCtx = getDefaultContext();
		ShopIterator shopItr = null;
		/*@lineinfo:generated-code*//*@lineinfo:441^3*/

//  ************************************************************
//  #sql [myCtx] shopItr = { SELECT
//  			    BRANCH_ID,
//  			    BRANCH_NAME,
//  			    QUERY_CODE,
//  			    BRANCH_TYPE
//  			FROM
//  				:Com_("D_T2_BRANCH") b
//  			WHERE
//  			  1=1 :query
//  			ORDER BY
//  				BRANCH_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopItr = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:453^3*/
		
		List<Map> shopLst = new ArrayList();
		try {
			while(shopItr.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", shopItr.BRANCH_ID());
				map.put("name","["+shopItr.BRANCH_ID()+"]" + shopItr.BRANCH_NAME()); 
				map.put("qcode", shopItr.QUERY_CODE()); 
				map.put("branchType", shopItr.BRANCH_TYPE()); 
				shopLst.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		shopItr.close();
		closeDefaultContext(myCtx);

		return shopLst;
	}
	
	public List<Map> listShopByType(String queryHasComma) throws NoPrivilegeException,SQLException,NoConnection	{
		String query = formBranchTypeSql(queryHasComma,null);
		
		DefaultContext myCtx = getDefaultContext();
		ShopIterator shopItr = null;
		/*@lineinfo:generated-code*//*@lineinfo:479^3*/

//  ************************************************************
//  #sql [myCtx] shopItr = { SELECT
//  			    BRANCH_ID,
//  			    BRANCH_NAME,
//  			    QUERY_CODE,
//  			    BRANCH_TYPE
//  			FROM
//  				:Com_("D_T2_BRANCH") b
//  			WHERE
//  				1=1	:query
//  			ORDER BY
//  				BRANCH_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 11);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopItr = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:491^3*/
		
		List<Map> shopLst = new ArrayList();
		try {
			while(shopItr.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", shopItr.BRANCH_ID());
				map.put("name","["+shopItr.BRANCH_ID()+"]" + shopItr.BRANCH_NAME()); 
				map.put("qcode", shopItr.QUERY_CODE()); 
				map.put("branchType", shopItr.BRANCH_TYPE()); 
				shopLst.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		shopItr.close();
		closeDefaultContext(myCtx);

		return shopLst;
	}
	
	/**
	 * 根据部门ID查询部门信息，返回一个列表对象（主要是为了考虑与以前的写法相兼容）
	 */
	public List<Map> queryById(String branchId) throws NoPrivilegeException,SQLException,NoConnection	{
		DefaultContext myCtx = getDefaultContext();
		ShopIterator shopItr = null;
		/*@lineinfo:generated-code*//*@lineinfo:518^3*/

//  ************************************************************
//  #sql [myCtx] shopItr = { SELECT
//  			    BRANCH_ID,
//  			    BRANCH_NAME,
//  			    QUERY_CODE,
//  			    BRANCH_TYPE
//  			FROM
//  			:Com_("D_T2_BRANCH")
//  			WHERE
//  				BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  String __sJT_2 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopItr = new ShopIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:528^3*/
		
		List<Map> shopLst = new ArrayList();
		while(shopItr.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", shopItr.BRANCH_ID());
			map.put("name","[" + shopItr.BRANCH_ID() + "]" + shopItr.BRANCH_NAME()); 
			map.put("qcode", shopItr.QUERY_CODE()); 
			map.put("branchType", shopItr.BRANCH_TYPE()); 
			shopLst.add(map);
			break;
		}
		shopItr.close();
		closeDefaultContext(myCtx);
		return shopLst;
	}
	
	/**
	 * 设置部门营业时间
	 */
	public void setBusinessDate(String branchId, java.util.Date newBusinessDate)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Date sBusinessDate = SqlDateUtil.getSqlDate(newBusinessDate);

		/*@lineinfo:generated-code*//*@lineinfo:553^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE 
//  				:Com_("D_T2_BRANCH")
//  			SET
//  			    BUSINESS_DATE = :sBusinessDate
//  			WHERE
//  			    BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  Date __sJT_2 = sBusinessDate;
  String __sJT_3 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 13);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:560^3*/
		closeDefaultContext(myCtx);
	}
	
	/**
	 * 获取餐厅的最大营业时间
	 */
	public java.util.Date getMaxBusinessDate()
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Date businessDate = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:572^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    MAX(b.BUSINESS_DATE)
//  		
//  		FROM
//  			:Com_("D_T2_BRANCH") b
//  		WHERE
//  		    b.BRANCH_TYPE = 'RESTAURANT'
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_BRANCH");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BranchBean_SJProfileKeys.getKey(0), 14);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
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
    businessDate = __sJT_rtRs.getDate(1);
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

/*@lineinfo:user-code*//*@lineinfo:581^3*/
		closeDefaultContext(myCtx);
		
		return SqlDateUtil.getUtilDate(businessDate);
	}
}/*@lineinfo:generated-code*/class BranchBean_SJProfileKeys 
{
  private static BranchBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BranchBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BranchBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.BranchBean_SJProfile0");
  }
}
