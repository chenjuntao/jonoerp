/*@lineinfo:filename=ItemMetaBean*//*@lineinfo:user-code*//*@lineinfo:1^1*///===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.8.6 by cjt
// Last edited on 2014.10.23 by lyz
//
// Comments:read:{Com_("D_T2_ITEM_META")} table.
// 说明：读取原材料、成品、半成品的元信息。
//
//===============================================

package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import pojo.store.ItemMeta;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.constant.ItemType;

public class ItemMetaBean extends ConnectionPool {

	/**
	 * companyId由调用者通过参数指定,by cjt
	 */
	public int saveEntity(ItemMeta itemMeta, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return saveEntityImp(itemMeta, myCtx);
	}
	
	/**
	 * companyId默认通过session访问,by cjt
	 */
	public int saveEntity(ItemMeta itemMeta)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return saveEntityImp(itemMeta, myCtx);
	}
	
	private int saveEntityImp(ItemMeta itemMeta, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {

		String itemId = itemMeta.getItemId();
		String itemBarCode = itemMeta.getItemBarCode();
		String itemName = itemMeta.getItemName();
		String itemNameEng = itemMeta.getItemNameEng();
		String itemType = itemMeta.getItemType();
		String categoryId = itemMeta.getCategoryId();
		String itemDimension = itemMeta.getItemDimension();
		String queryCode = itemMeta.getQueryCode();
		String itemSpecification = itemMeta.getItemSpecification();
		String itemPic = itemMeta.getItemPic();
		String itemNote = itemMeta.getItemNote();
		String boxType = itemMeta.getBoxType();
		int enabled = itemMeta.getEnabled();
		Date effectDate = SqlDateUtil.getSqlDate(itemMeta.getEffectDate());
		String cBranch_c = itemMeta.getCBranchC();
		String categoryName = itemMeta.getCategoryName();

		/*@lineinfo:generated-code*//*@lineinfo:77^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO
//  			:Com_("D_T2_ITEM_META")
//  			    (
//  			        ITEM_ID,
//  			        ITEM_BAR_CODE,
//  			        ITEM_NAME,
//  			        ITEM_NAME_ENG,
//  			        ITEM_DIMENSION,
//  			        QUERY_CODE,
//  			        CATEGORY_ID,
//  			        ITEM_SPECIFICATION,
//  			        ITEM_TYPE,
//  			        ITEM_PIC,
//  			        ITEM_NOTE, 
//  			        BOX_TYPE,
//  			        ENABLED,
//  			        EFFECT_DATE
//  			    )
//  			    VALUES
//  			    (
//  			    	:itemId,
//  			    	:itemBarCode,
//  			    	:itemName,
//  			    	:itemNameEng,
//  			        :itemDimension,
//  			        :queryCode,
//  			        :categoryId,
//  			        :itemSpecification,
//  			        :itemType,
//  			        :itemPic,
//  			        :itemNote,
//  			        :boxType,
//  			        :enabled,
//  			        :effectDate
//  			    )
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = itemId;
  String __sJT_3 = itemBarCode;
  String __sJT_4 = itemName;
  String __sJT_5 = itemNameEng;
  String __sJT_6 = itemDimension;
  String __sJT_7 = queryCode;
  String __sJT_8 = categoryId;
  String __sJT_9 = itemSpecification;
  String __sJT_10 = itemType;
  String __sJT_11 = itemPic;
  String __sJT_12 = itemNote;
  String __sJT_13 = boxType;
  int __sJT_14 = enabled;
  Date __sJT_15 = effectDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:113^3*/
		
		int bInHandPro = itemMeta.getbInHandPro();
		int bDisFood = itemMeta.getbDisFood();
		int bDisCount = itemMeta.getbDisCount();
		int bwaimai = itemMeta.getBwaimai();
		//如果是出品,则推送到前台系统，by cjt
		if (itemType.equals(ItemType.PRODUCT)){
			if(existTableCTFOOD(myCtx)){
				/*@lineinfo:generated-code*//*@lineinfo:122^5*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO
//  					:Com_("C_T_FOOD")
//  				    (
//  				        ITEM_ID,
//  				        ITEM_BAR_CODE,
//  				        ITEM_NAME,
//  				        ITEM_NAME_ENG,
//  				        ITEM_DIMENSION,
//  				        QUERY_CODE,
//  				        CATEGORY_ID,
//  				        CATEGORY_NAME,
//  				        CBRANCH_C,
//  				        ITEM_NOTE,
//  				        ENABLED,
//  				        BINHANDPRO,
//  				        BDISFOOD,
//  				        BDISCOUNT,
//  				        BWAIMAI
//  				    )
//  				    VALUES
//  				    (
//  				    	:itemId,
//  				    	:itemBarCode,
//  				    	:itemName,
//  				    	:itemNameEng,
//  				        :itemDimension,
//  				        :queryCode,
//  				        :categoryId,
//  				        :categoryName,
//  				        :cBranch_c,
//  				        :itemNote,
//  				        :enabled,
//  				        :bInHandPro,
//  					    :bDisFood,
//  					    :bDisCount,
//  					    :bwaimai
//  				    )
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("C_T_FOOD");
  String __sJT_2 = itemId;
  String __sJT_3 = itemBarCode;
  String __sJT_4 = itemName;
  String __sJT_5 = itemNameEng;
  String __sJT_6 = itemDimension;
  String __sJT_7 = queryCode;
  String __sJT_8 = categoryId;
  String __sJT_9 = categoryName;
  String __sJT_10 = cBranch_c;
  String __sJT_11 = itemNote;
  int __sJT_12 = enabled;
  int __sJT_13 = bInHandPro;
  int __sJT_14 = bDisFood;
  int __sJT_15 = bDisCount;
  int __sJT_16 = bwaimai;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setInt(12, __sJT_12);
      __sJT_stmt.setInt(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
      __sJT_stmt.setInt(15, __sJT_15);
      __sJT_stmt.setInt(16, __sJT_16);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:160^5*/
			}
		}
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * companyId由调用者通过参数指定,by cjt
	 */
	public int updateEntity(String oldItemId, ItemMeta itemMeta, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);
		return updateEntityImp(oldItemId, itemMeta, myCtx);
	}
		
	/**
	 * companyId默认通过session访问,by cjt
	 */
	public int updateEntity(String oldItemId, ItemMeta itemMeta) 
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		return updateEntityImp(oldItemId, itemMeta, myCtx);
	}
	
	private int updateEntityImp(String oldItemId, ItemMeta itemMeta, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		
		String itemId = itemMeta.getItemId();
		String itemBarCode = itemMeta.getItemBarCode();
		String itemName = itemMeta.getItemName();
		String itemNameEng = itemMeta.getItemNameEng();
		String itemType = itemMeta.getItemType();
		String categoryId = itemMeta.getCategoryId();
		String itemDimension = itemMeta.getItemDimension();
		String queryCode = itemMeta.getQueryCode();
		String itemSpecification = itemMeta.getItemSpecification();
		String itemPic = itemMeta.getItemPic();
		String itemNote = itemMeta.getItemNote();
		String boxType = itemMeta.getBoxType();
		int enabled = itemMeta.getEnabled();
		Date effectDate = SqlDateUtil.getSqlDate(itemMeta.getEffectDate());
		String cBranch_c = itemMeta.getCBranchC();
		String categoryName = itemMeta.getCategoryName();

		/*@lineinfo:generated-code*//*@lineinfo:207^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_ITEM_META")
//  			SET
//  			    ITEM_ID = :itemId,
//  			    ITEM_BAR_CODE = :itemBarCode,
//  			    ITEM_NAME = :itemName,
//  			    ITEM_NAME_ENG = :itemNameEng,
//  			    ITEM_TYPE = :itemType,
//  			    CATEGORY_ID = :categoryId,
//  			    ITEM_DIMENSION = :itemDimension,
//  			    QUERY_CODE = :queryCode,
//  			    ITEM_SPECIFICATION = :itemSpecification,
//  			    ITEM_PIC = :itemPic,
//  			    ITEM_NOTE = :itemNote,
//  			    BOX_TYPE = :boxType,
//  			    ENABLED = :enabled,
//  			    EFFECT_DATE = :effectDate
//  			WHERE 
//  				ITEM_ID = :oldItemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = itemId;
  String __sJT_3 = itemBarCode;
  String __sJT_4 = itemName;
  String __sJT_5 = itemNameEng;
  String __sJT_6 = itemType;
  String __sJT_7 = categoryId;
  String __sJT_8 = itemDimension;
  String __sJT_9 = queryCode;
  String __sJT_10 = itemSpecification;
  String __sJT_11 = itemPic;
  String __sJT_12 = itemNote;
  String __sJT_13 = boxType;
  int __sJT_14 = enabled;
  Date __sJT_15 = effectDate;
  String __sJT_16 = oldItemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 2);
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
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:227^3*/
		
		int bInHandPro = itemMeta.getbInHandPro();
		int bDisFood = itemMeta.getbDisFood();
		int bDisCount = itemMeta.getbDisCount();
		int bwaimai = itemMeta.getBwaimai();
		//如果是出品,则推送到前台系统，by cjt
		if (itemType.equals(ItemType.PRODUCT)){
			if(existTableCTFOOD(myCtx)){
				/*@lineinfo:generated-code*//*@lineinfo:236^5*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  						:Com_("C_T_FOOD")
//  					SET
//  					    ITEM_BAR_CODE = :itemBarCode,
//  					    ITEM_NAME = :itemName,
//  					    ITEM_NAME_ENG = :itemNameEng,
//  					    CATEGORY_ID = :categoryId,
//  					    CATEGORY_NAME = :categoryName,
//  					    CBRANCH_C = :cBranch_c,
//  					    ITEM_DIMENSION = :itemDimension,
//  					    QUERY_CODE = :queryCode,
//  					    ITEM_NOTE = :itemNote,
//  					    ENABLED = :enabled,
//  					    BINHANDPRO = :bInHandPro,
//  					    BDISFOOD = :bDisFood,
//  					    BDISCOUNT = :bDisCount,
//  					    BWAIMAI = :bwaimai
//  					WHERE 
//  						ITEM_ID = :itemId
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("C_T_FOOD");
  String __sJT_2 = itemBarCode;
  String __sJT_3 = itemName;
  String __sJT_4 = itemNameEng;
  String __sJT_5 = categoryId;
  String __sJT_6 = categoryName;
  String __sJT_7 = cBranch_c;
  String __sJT_8 = itemDimension;
  String __sJT_9 = queryCode;
  String __sJT_10 = itemNote;
  int __sJT_11 = enabled;
  int __sJT_12 = bInHandPro;
  int __sJT_13 = bDisFood;
  int __sJT_14 = bDisCount;
  int __sJT_15 = bwaimai;
  String __sJT_16 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 3);
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
      __sJT_stmt.setInt(11, __sJT_11);
      __sJT_stmt.setInt(12, __sJT_12);
      __sJT_stmt.setInt(13, __sJT_13);
      __sJT_stmt.setInt(14, __sJT_14);
      __sJT_stmt.setInt(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:256^5*/
			}
		}
		
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String itemId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:269^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM
//  			:Com_("D_T2_ITEM_META")
//  			WHERE
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:274^3*/
		
		//如果是出品,则推送到前台系统，by cjt
		if(existTableCTFOOD(myCtx)){
			/*@lineinfo:generated-code*//*@lineinfo:278^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM
//  					:Com_("C_T_FOOD")
//  				WHERE
//  					ITEM_ID = :itemId
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("C_T_FOOD");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 5);
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

/*@lineinfo:user-code*//*@lineinfo:283^4*/
		}
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 是否包含该物料,by cjt
	 */
	public boolean containsEntity(String itemId, String itemType, String comId)
			throws NoPrivilegeException, SQLException, NoPrivilegeException{
		DefaultContext myCtx = getComContext(comId);
		int count = 0;
		/*@lineinfo:generated-code*//*@lineinfo:298^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*) 
//  			from :Com_("D_T2_ITEM_META")
//  			where item_id = :itemId
//  			and item_type = :itemType
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = itemId;
  String __sJT_3 = itemType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 6);
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

/*@lineinfo:user-code*//*@lineinfo:303^3*/

		closeDefaultContext(myCtx);
		return count > 0;
	}
	
	/**
	 * 修改物料类别
	 */
	public int moveCate(String itemId, String newCateId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String itemType = null;
		String newCateName = null;
		/*@lineinfo:generated-code*//*@lineinfo:318^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		    	c.ITEM_TYPE,c.CATEGORY_NAME 
//  		    FROM
//  		    :Com_("D_T2_ITEM_CATEGORY") c
//  		    WHERE
//  			   	CATEGORY_ID = :newCateId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_2 = newCateId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 7);
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
    sqlj.runtime.ref.ResultSetIterImpl.checkColumns(__sJT_rtRs, 2);
    if (!__sJT_rtRs.next())
    {
      sqlj.runtime.error.RuntimeRefErrors.raise_NO_ROW_SELECT_INTO();
    }
    itemType = __sJT_rtRs.getString(1);
    newCateName = __sJT_rtRs.getString(2);
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

/*@lineinfo:user-code*//*@lineinfo:325^3*/
		/*@lineinfo:generated-code*//*@lineinfo:326^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_ITEM_META")
//  			SET
//  			   	CATEGORY_ID = :newCateId,
//  			   	ITEM_TYPE = :itemType
//  			WHERE
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = newCateId;
  String __sJT_3 = itemType;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 8);
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

/*@lineinfo:user-code*//*@lineinfo:334^3*/
		
		//如果是出品,则推送到前台系统，by cjt
		if (itemType.equals(ItemType.PRODUCT)){
			if(existTableCTFOOD(myCtx)){
				/*@lineinfo:generated-code*//*@lineinfo:339^5*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  					:Com_("C_T_FOOD")
//  				SET
//  					CATEGORY_ID = :newCateId,
//  					CATEGORY_NAME = :newCateName
//  			   	WHERE
//  			   		ITEM_ID = :itemId
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("C_T_FOOD");
  String __sJT_2 = newCateId;
  String __sJT_3 = newCateName;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 9);
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

/*@lineinfo:user-code*//*@lineinfo:347^5*/
			}
		}
				
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/**
	 * 启停用物料
	 */
	public int enableItem(String itemId, String itemType, Integer enabled, java.util.Date effectDate)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		Date sDate = SqlDateUtil.getSqlDate(effectDate);
		/*@lineinfo:generated-code*//*@lineinfo:364^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("D_T2_ITEM_META")
//  			SET
//  			   	ENABLED = :enabled,
//  			   	EFFECT_DATE = :sDate
//  			WHERE 
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  Integer __sJT_2 = enabled;
  Date __sJT_3 = sDate;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setIntWrapper(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:372^3*/
		
		//如果是出品,则推送到前台系统，by cjt
		if(existTableCTFOOD(myCtx)){
			/*@lineinfo:generated-code*//*@lineinfo:376^4*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				:Com_("C_T_FOOD")
//  			SET
//  			ENABLED = :enabled
//  			WHERE 
//  			ITEM_ID = :itemId 
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("C_T_FOOD");
  Integer __sJT_2 = enabled;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 11);
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

/*@lineinfo:user-code*//*@lineinfo:383^4*/
		}
		
		if (enabled == 1) {
			if (ItemType.SEMIS.equals(itemType)) {
				initSupplier(myCtx, itemId);
			}
			
			initDeliveryType(myCtx, itemId);
		}
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:398^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class DtypeIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DtypeIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    region_idNdx = findColumn("region_id");
    numNdx = findColumn("num");
  }
  public String region_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(region_idNdx);
  }
  private int region_idNdx;
  public Integer num() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(numNdx);
  }
  private int numNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:398^69*/
	
	/**
	 * 启用物料时初始化配送方式为统配
	 */
	private void initDeliveryType(DefaultContext myCtx, String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DtypeIter dtypeIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:406^3*/

//  ************************************************************
//  #sql [myCtx] dtypeIter = { SELECT
//  			    r.REGION_ID,
//  			    t.num
//  			FROM
//  			:Com_("D_T2_DELIVERY_REGION") r
//  			LEFT JOIN
//  			    (
//  			        SELECT
//  			            d.REGION_ID,
//  			            COUNT(*) AS num
//  			        FROM
//  			        :Com_("D_T2_DELIVERY_TYPE") d
//  			        WHERE
//  			            d.ITEM_ID = :itemId
//  			        GROUP BY
//  			            d.REGION_ID) t
//  			ON
//  			    t.REGION_ID = r.REGION_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_REGION");
  String __sJT_2 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 12);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      dtypeIter = new DtypeIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:425^3*/
		String dtype = FormConstant.UNIFIED_DELIVERY;
		while(dtypeIter.next()) {
			String regionId = dtypeIter.region_id();
			Integer num = dtypeIter.num();
			if (num == null) { // 初始化为统配
				/*@lineinfo:generated-code*//*@lineinfo:431^5*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_DELIVERY_TYPE") 
//  						(REGION_ID, ITEM_ID, DELIVERY_TYPE) 
//  					VALUES (:regionId, :itemId, :dtype)
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_DELIVERY_TYPE");
  String __sJT_2 = regionId;
  String __sJT_3 = itemId;
  String __sJT_4 = dtype;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 13);
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

/*@lineinfo:user-code*//*@lineinfo:435^5*/
			}
		}
	}
	
	/**
	 * 启用物料时，如果是半成品，则将供应商设置为中央工厂，保证数据的连通性
	 */
	private void initSupplier(DefaultContext myCtx, String itemId)
			throws NoPrivilegeException, SQLException, NoConnection {
		String lcCode = BranchCode.DEFAULT_LOGISTICSCENTER;
		String cfCode = BranchCode.DEFAULT_CENTRALFACTORY;
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:448^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(*) 
//  			FROM
//  			:Com_("D_T2_SUPPLIER_BRANCH_ITEM")
//  			WHERE
//  			    ITEM_ID = :itemId
//  			AND BRANCH_ID = :lcCode
//  			AND PRIORITY = 0
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = itemId;
  String __sJT_3 = lcCode;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 14);
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

/*@lineinfo:user-code*//*@lineinfo:457^3*/
		if (total == 0) {
			/*@lineinfo:generated-code*//*@lineinfo:459^4*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_SUPPLIER_BRANCH_ITEM") 
//  					(SUPPLIER_ID, BRANCH_ID, ITEM_ID, PRIORITY) 
//  				VALUES (:cfCode, :lcCode, :itemId, 0)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_SUPPLIER_BRANCH_ITEM");
  String __sJT_2 = cfCode;
  String __sJT_3 = lcCode;
  String __sJT_4 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 15);
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

/*@lineinfo:user-code*//*@lineinfo:463^4*/
		}
	}

	public int updatePic(String itemId, String picId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:471^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_ITEM_META")
//  			SET
//  			    ITEM_PIC = :picId
//  			WHERE 
//  				ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = picId;
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 16);
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

/*@lineinfo:user-code*//*@lineinfo:478^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	/*@lineinfo:generated-code*//*@lineinfo:483^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ColumnIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ColumnIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    infoNdx = findColumn("info");
    item_nameNdx = findColumn("item_name");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String info() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infoNdx);
  }
  private int infoNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:483^85*/
	
	public List<Map> GetItemCount(String ids)throws NoPrivilegeException,SQLException,NoConnection {
		String query = " s.ITEM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		DefaultContext myCtx = getDefaultContext();
		ColumnIter columnIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:489^3*/

//  ************************************************************
//  #sql [myCtx] columnIter = { WITH
//  			    item AS
//  			    (
//  			        SELECT
//  			            s.ITEM_ID,
//  			            m.ITEM_NAME,
//  			            b.STORAGE_NAME
//  			        FROM
//  			        :Com_("D_T2_STORAGE") s
//  		            LEFT JOIN
//  		            :Com_("D_T2_ITEM_META") m
//  			        ON
//  			            m.ITEM_ID =s.ITEM_ID
//  			        LEFT JOIN
//  			        :Com_("D_T2_BRANCH_STORAGE") b
//  			        ON
//  			            b.STORAGE_ID=s.STORAGE_ID
//  			        WHERE
//  			        :query
//  			        AND s.ITEM_COUNT!=0
//  			    )
//  			SELECT
//  			    ITEM_ID,
//  			    ITEM_NAME,
//  			    listagg(STORAGE_NAME,',')within GROUP (ORDER BY ITEM_ID) info
//  			FROM
//  			    item
//  			GROUP BY
//  			    ITEM_ID,
//  			    ITEM_NAME
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_STORAGE");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_4 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 17);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      columnIter = new ColumnIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:520^3*/
		List<Map> returnLst = returnColumnIter(columnIter);
		columnIter.close();
		closeDefaultContext(myCtx);
		return returnLst;
	}
	
	public List<Map> GetIsTherapy(String ids)throws NoPrivilegeException,SQLException,NoConnection {
		String query = " t.ITEM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		DefaultContext myCtx = getDefaultContext();
		ColumnIter columnIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:531^3*/

//  ************************************************************
//  #sql [myCtx] columnIter = { WITH
//  			    item AS
//  			    (
//  			        SELECT
//  			            t.ITEM_ID,
//  			            t.THERAPY_NAME,
//  			            t.ITEM_NAME
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			        WHERE
//  			        	:query	
//  			    )
//  			SELECT
//  			    ITEM_ID,
//  			    ITEM_NAME,
//  			    listagg(THERAPY_NAME,',')within GROUP (ORDER BY ITEM_ID) info
//  			FROM
//  			    item
//  			GROUP BY
//  			    ITEM_ID,
//  			    ITEM_NAME
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 18);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      columnIter = new ColumnIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:553^3*/
		List<Map> returnLst = returnColumnIter(columnIter);
		columnIter.close();
		closeDefaultContext(myCtx);
		return returnLst;
	}
	
	private List<Map> returnColumnIter(ColumnIter columnIter) 
			throws SQLException {
		List<Map> headLst = new ArrayList<Map>();
		while(columnIter.next()) {
			Map<String, Object> head = new HashMap<String, Object>();
			head.put("itemId", columnIter.item_id());
			head.put("info", columnIter.info());
			head.put("itemName", columnIter.item_name());
			
			headLst.add(head);
		}
		return headLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:574^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ItemIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_name_engNdx = findColumn("item_name_eng");
    item_typeNdx = findColumn("item_type");
    category_idNdx = findColumn("category_id");
    item_dimensionNdx = findColumn("item_dimension");
    query_codeNdx = findColumn("query_code");
    item_specificationNdx = findColumn("item_specification");
    item_picNdx = findColumn("item_pic");
    box_typeNdx = findColumn("box_type");
    enabledNdx = findColumn("enabled");
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
  public String item_name_eng() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_name_engNdx);
  }
  private int item_name_engNdx;
  public String item_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_typeNdx);
  }
  private int item_typeNdx;
  public String category_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_idNdx);
  }
  private int category_idNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String query_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(query_codeNdx);
  }
  private int query_codeNdx;
  public String item_specification() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_specificationNdx);
  }
  private int item_specificationNdx;
  public String item_pic() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_picNdx);
  }
  private int item_picNdx;
  public String box_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_typeNdx);
  }
  private int box_typeNdx;
  public Integer enabled() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(enabledNdx);
  }
  private int enabledNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:582^53*/
	
	public ItemMeta GetItemById(String id) 
			throws NoPrivilegeException,SQLException,NoConnection {
		ItemMeta item = null;
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:589^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT 
//  				* 
//  			FROM 
//  			:Com_("D_T2_ITEM_META") 
//  				
//  			WHERE ITEM_ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 19);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:596^3*/
		if(itemIter.next()) {
			item = constructItemMetaBean(itemIter);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return item;
	}
	
	public ItemMeta GetById(String id) 
			throws NoPrivilegeException,SQLException,NoConnection {
		ItemMeta item = null;
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:610^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { WITH
//  			    a AS
//  			    (
//  			        SELECT DISTINCT
//  			            t.item_id,
//  			            nvl(t.ITEM_DIMENSION,'-') ITEM_DIMENSION
//  			        FROM
//  			        :Com_("D_T2_THERAPY") t
//  			    )
//  			SELECT
//  			m.item_id,
//  			m.item_bar_code,
//  			m.item_name, 
//  			m.item_name_eng, 
//  			m.item_type, 
//  			m.category_id, 
//  			a.item_dimension, 
//  			m.query_code,
//  			m.item_specification,
//  			m.item_pic, 
//  			m.box_type,
//  			m.item_note,
//  			m.effect_date,
//  			m.enabled
//  			FROM
//  			:Com_("D_T2_ITEM_META") m
//  			LEFT JOIN
//  			    a
//  			ON
//  			    a.item_id= m.item_id
//  			WHERE m.ITEM_ID = :id
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_THERAPY");
  String __sJT_2 = Com_("D_T2_ITEM_META");
  String __sJT_3 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 20);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:642^3*/
		if(itemIter.next()) {
			item = constructItemMetaBean(itemIter);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return item;
	}
	
	public List<ItemMeta> getItemsByName(String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<ItemMeta> result = new ArrayList<ItemMeta>();
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:656^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  		    	*
//  		    FROM
//  		    :Com_("D_T2_ITEM_META") m
//  		    WHERE
//  		        trim(m.ITEM_NAME) = :itemName
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 21);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:663^3*/
		while(itemIter.next()) {
			result.add(constructItemMetaBean(itemIter));
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return result;
	}

	public List<ItemMeta> GetAllItemsByType(String itemType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<ItemMeta> result = new ArrayList<ItemMeta>();
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:677^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  		    	*
//  		    FROM
//  		    :Com_("D_T2_ITEM_META") m
//  		    WHERE
//  		    	ITEM_TYPE = :itemType
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_ITEM_META");
  String __sJT_2 = itemType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 22);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:684^3*/
		while(itemIter.next()) {
			result.add(constructItemMetaBean(itemIter));
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return result;
	}
	
	public List<ItemMeta> queryItem(String itemType, String itemName) 
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		String query = " ITEM_TYPE = '" + itemType + "'";
		if (ItemType.R_S.equals(itemType)) {
			query = " (ITEM_TYPE = '" + ItemType.RAW 
					+ "' OR  ITEM_TYPE = '" + ItemType.SEMIS
					+ "' OR  ITEM_TYPE = '" + ItemType.SELFSEMIS + "')";
		}
		
		List<ItemMeta> result = new ArrayList<ItemMeta>();
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:706^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("d_t2_item_meta") m
//  			WHERE
//  				:query
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_meta");
  String __sJT_2 = query;
  String __sJT_3 = itemName;
  String __sJT_4 = itemName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 23);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:716^3*/
		while (itemIter.next()) {
			result.add(constructItemMetaBean(itemIter));
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return result;
	}

	public List<ItemMeta> queryItem(String itemType, String itemName, String exceptId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		itemName = "%" + itemName + "%";
		
		List<ItemMeta> result = new ArrayList<ItemMeta>();
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:732^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("d_t2_item_meta") m
//  			WHERE
//  			    item_type = :itemType
//              AND (
//                      m.ITEM_ID LIKE :itemName
//                  OR  m.ITEM_NAME LIKE :itemName)
//              AND m.ITEM_ID != :exceptId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_meta");
  String __sJT_2 = itemType;
  String __sJT_3 = itemName;
  String __sJT_4 = itemName;
  String __sJT_5 = exceptId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 24);
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

/*@lineinfo:user-code*//*@lineinfo:743^3*/
		while (itemIter.next()) {
			result.add(constructItemMetaBean(itemIter));
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return result;
	}

	public List<ItemMeta> GetAllItemMeta() 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<ItemMeta> result = new ArrayList<ItemMeta>();
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:757^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("d_t2_item_meta") m
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_meta");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 25);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
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

/*@lineinfo:user-code*//*@lineinfo:762^3*/
		while (itemIter.next()) {
			result.add(constructItemMetaBean(itemIter));
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return result;
	}
	
	public List<ItemMeta> queryByCategory(String categoryId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<ItemMeta> result = new ArrayList<ItemMeta>();
		DefaultContext myCtx = getDefaultContext();
		ItemIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:776^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    *
//  			FROM
//  				:Com_("d_t2_item_meta") m
//  			WHERE
//  			    trim(category_id) = :categoryId
//  			AND m.ENABLED = 1
//  			ORDER BY
//  				ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_meta");
  String __sJT_2 = categoryId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 26);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:786^3*/
		while(itemIter.next()) {
			result.add(constructItemMetaBean(itemIter));
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return result;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:795^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ItemIdIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ItemIdIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:795^49*/
	public List<ItemMeta> listItemMeta() 
			throws NoPrivilegeException,SQLException,NoConnection {
		List<ItemMeta> result = new ArrayList<ItemMeta>();
		DefaultContext myCtx = getDefaultContext();
		ItemIdIter itemIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:801^3*/

//  ************************************************************
//  #sql [myCtx] itemIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("d_t2_item_meta")
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_meta");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 27);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      itemIter = new ItemIdIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:806^3*/
		while(itemIter.next()) {
			ItemMeta itemMeta = new ItemMeta();
			itemMeta.setItemId(itemIter.item_id());
			result.add(itemMeta);
		}
		itemIter.close();
		closeDefaultContext(myCtx);
		return result;
	}
	
	/**
	 * 从ItemIter构建ItemMeta
	 */
	private ItemMeta constructItemMetaBean(ItemIter itemIter) throws SQLException {
		ItemMeta item = new ItemMeta();
		item.setItemId(itemIter.item_id());
		item.setItemName(itemIter.item_name());
		item.setItemNameEng(itemIter.item_name_eng());
		item.setItemType(itemIter.item_type());
		item.setCategoryId(itemIter.category_id());
		item.setItemDimension(itemIter.item_dimension());
		item.setQueryCode(itemIter.query_code());
		String itemSpecification = itemIter.item_specification();
		if (itemSpecification != null) {
			itemSpecification = itemSpecification.trim();
		}else{
			itemSpecification = "";
		}
		item.setItemSpecification(itemSpecification);
		item.setItemPic(itemIter.item_pic());
		item.setBoxType(itemIter.box_type());
		item.setEnabled(itemIter.enabled());

		return item;
	}
	
	//判断是否存在用于推送出品信息的表
	public boolean existTableCTFOOD (DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection {
		int count = 0;
		String dbName = this.getCompanyId().toUpperCase();
		/*@lineinfo:generated-code*//*@lineinfo:848^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*) 
//  			from dba_tables 
//  			where owner = :dbName
//  			and TABLE_NAME = 'C_T_FOOD'
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = dbName;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ItemMetaBean_SJProfileKeys.getKey(0), 28);
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

/*@lineinfo:user-code*//*@lineinfo:853^3*/
		return count > 0;
	}
}/*@lineinfo:generated-code*/class ItemMetaBean_SJProfileKeys 
{
  private static ItemMetaBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ItemMetaBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ItemMetaBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.ItemMetaBean_SJProfile0");
  }
}
