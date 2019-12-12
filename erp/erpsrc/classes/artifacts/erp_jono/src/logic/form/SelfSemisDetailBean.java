/*@lineinfo:filename=SelfSemisDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Jul 08 12:10:12 CST 2016 by pw
 * Last edited on Fri Jul 08 12:10:12 CST 2016 by pw
 * 
 * comment: 半成品加工单明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

import pojo.form.SelfSemisDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class SelfSemisDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SelfSemisDetailBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_count_planNdx = findColumn("item_count_plan");
    item_count_actualNdx = findColumn("item_count_actual");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
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
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
  public Double item_count_plan() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_planNdx);
  }
  private int item_count_planNdx;
  public Double item_count_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_actualNdx);
  }
  private int item_count_actualNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:37^205*/
	
	public int saveEntity(SelfSemisDetail header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = header.getFormId();
		String itemId = header.getItemId();
		String itemName = header.getItemName();
		String itemDimension = header.getItemDimension();
		String itemSpecification = header.getItemSpecification();
		String itemCategory = header.getItemCategory();
		Double itemCountPlan = header.getItemCountPlan();
		Double itemCountActual = header.getItemCountActual();

		/*@lineinfo:generated-code*//*@lineinfo:52^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_SELF_SEMIS_DETAIL")
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_SPECIFICATION, ITEM_CATEGORY, ITEM_COUNT_PLAN, ITEM_COUNT_ACTUAL) 
//  			VALUES (:formId, :itemId, :itemName, :itemDimension, :itemSpecification, :itemCategory, :itemCountPlan, :itemCountActual)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemSpecification;
  String __sJT_7 = itemCategory;
  Double __sJT_8 = itemCountPlan;
  Double __sJT_9 = itemCountActual;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:56^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(SelfSemisDetail header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = header.getFormId();
		String itemId = header.getItemId();
		String itemName = header.getItemName();
		String itemDimension = header.getItemDimension();
		String itemSpecification = header.getItemSpecification();
		String itemCategory = header.getItemCategory();
		Double itemCountPlan = header.getItemCountPlan();
		Double itemCountActual = header.getItemCountActual();

		/*@lineinfo:generated-code*//*@lineinfo:75^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_SELF_SEMIS_DETAIL")
//  			SET
//  				ITEM_COUNT_PLAN = :itemCountPlan,
//  				ITEM_COUNT_ACTUAL = :itemCountActual
//  			WHERE 
//  				FORM_ID = :formId
//  				AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_DETAIL");
  Double __sJT_2 = itemCountPlan;
  Double __sJT_3 = itemCountActual;
  String __sJT_4 = formId;
  String __sJT_5 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
      __sJT_stmt.setDoubleWrapper(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:84^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:94^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_SELF_SEMIS_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:100^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	private String formQuery(String itemType) {
		String query = " 1=1";
		
		if ("SEMIS".equals(itemType)) { // 获取半成品记录
			query = query + " AND d.ITEM_SPECIFICATION='FOOD'"; 
		}else if("RAW".equals(itemType)){
			query = query + " AND d.ITEM_SPECIFICATION!='FOOD'"; 
		}
		return query;
	}
	
	public List<SelfSemisDetail> queryDetail(String formId,String itemType) 
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = formQuery(itemType);
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:122^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_SELF_SEMIS_DETAIL") d
//  			WHERE
//  				:query
//  			AND 
//  				d.FORM_ID =:formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_DETAIL");
  String __sJT_2 = query;
  String __sJT_3 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:131^3*/
		List<SelfSemisDetail> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:138^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class StorageIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public StorageIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_countNdx = findColumn("item_count");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:141^3*/
	
	/**
	 * query items storage
	 */
	public Map<String,Double> queryStorage(String formId,String storageId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		StorageIter storageIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:151^3*/

//  ************************************************************
//  #sql [myCtx] storageIter = { WITH
//  			    items AS
//  			    (
//  			        SELECT DISTINCT
//  			            ITEM_ID
//  			        FROM
//  			        	:Com_("D_T1_SELF_SEMIS_HEADER") h
//  			        INNER JOIN
//  			            :Com_("D_T1_SELF_SEMIS_DETAIL") d
//  			        ON
//  			            h.FORM_ID = d.FORM_ID
//  			        WHERE
//  			            d.FORM_ID = :formId
//  			    )
//  			SELECT
//  			    i.ITEM_ID,
//  			    NVL(s.ITEM_COUNT,0)ITEM_COUNT
//  			FROM
//  			    items i
//  			LEFT JOIN
//  			 :Com_("D_T2_STORAGE") s
//  			ON
//  			    i.ITEM_ID = s.ITEM_ID
//  			AND s.STORAGE_ID = :storageId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SELF_SEMIS_HEADER");
  String __sJT_2 = Com_("D_T1_SELF_SEMIS_DETAIL");
  String __sJT_3 = formId;
  String __sJT_4 = Com_("D_T2_STORAGE");
  String __sJT_5 = storageId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SelfSemisDetailBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      storageIter = new StorageIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:175^33*/
		
		Map<String,Double> resultMap = new HashMap<String,Double>();
		while(storageIter.next()) {
			resultMap.put(storageIter.item_id(),storageIter.item_count());
		}
		
		storageIter.close();
		closeDefaultContext(myCtx);
		return resultMap;
	}

	private List<SelfSemisDetail> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<SelfSemisDetail> headerLst = new ArrayList<SelfSemisDetail>();
		while(headerIter.next()) {
			SelfSemisDetail header = new SelfSemisDetail();
			header.setFormId(headerIter.form_id());
			header.setItemId(headerIter.item_id());
			header.setItemName(headerIter.item_name());
			header.setItemDimension(headerIter.item_dimension());
			header.setItemSpecification(headerIter.item_specification());
			header.setItemCategory(headerIter.item_category());
			header.setItemCountPlan(headerIter.item_count_plan());
			header.setItemCountActual(headerIter.item_count_actual());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class SelfSemisDetailBean_SJProfileKeys 
{
  private static SelfSemisDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SelfSemisDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SelfSemisDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.SelfSemisDetailBean_SJProfile0");
  }
}
