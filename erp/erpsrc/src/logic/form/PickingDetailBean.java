/*@lineinfo:filename=PickingDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 11 15:05:12 CST 2014 by lyz
 * Last edited on Thu Dec 11 15:05:12 CST 2014 by lyz
 * 
 * comment: 捡货单表明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PickingDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PickingDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PickingDetailBean.class);
	
	public int saveEntity(PickingDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemCategory = detail.getItemCategory();
		Double itemCount = detail.getItemCount();
		String branchId = detail.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_PICKING_DETAIL") 
//  				(FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_CATEGORY, ITEM_COUNT, BRANCH_ID) 
//  			VALUES (:formId, :itemId, :itemName, :itemDimension, :itemCategory, :itemCount, :branchId)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  String __sJT_6 = itemCategory;
  Double __sJT_7 = itemCount;
  String __sJT_8 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:51^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(PickingDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		String itemCategory = detail.getItemCategory();
		Double itemCount = detail.getItemCount();
		String branchId = detail.getBranchId();

		/*@lineinfo:generated-code*//*@lineinfo:69^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PICKING_DETAIL")
//  			SET
//  				ITEM_COUNT = :itemCount
//  			WHERE 
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  			AND BRANCH_ID = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_DETAIL");
  Double __sJT_2 = itemCount;
  String __sJT_3 = formId;
  String __sJT_4 = itemId;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDoubleWrapper(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:78^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:88^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PICKING_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:94^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:100^2*/

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
    form_idNdx = findColumn("form_id");
    shelf_idNdx = findColumn("shelf_id");
    shelf_nameNdx = findColumn("shelf_name");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    item_countNdx = findColumn("item_count");
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    sumCountNdx = findColumn("sumCount");
    storage_countNdx = findColumn("storage_count");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String shelf_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_idNdx);
  }
  private int shelf_idNdx;
  public String shelf_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(shelf_nameNdx);
  }
  private int shelf_nameNdx;
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
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
  public Double sumCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sumCountNdx);
  }
  private int sumCountNdx;
  public Double storage_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(storage_countNdx);
  }
  private int storage_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:114^3*/
	
	public List<PickingDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:120^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { with a as(
//  				select     
//  			       d.ITEM_NAME,
//  			       d.ITEM_ID,
//  			        SUM(d.ITEM_COUNT) sumCount
//  			        FROM
//  			        :Com_("D_T1_PICKING_DETAIL") d
//  			        WHERE
//  					d.FORM_ID=:formId
//  				  GROUP BY d.ITEM_NAME,d.ITEM_ID
//  				),
//  	               bs as(
//  	               	 select
//  	               	    bs.STORAGE_ID,
//  	               	    bs.STORAGE_NAME,
//  	               	    t.ITEM_ID,
//  	               	    t.ITEM_COUNT
//  	               	    from 
//  	               	 :Com_("D_T2_BRANCH_STORAGE") bs
//  				   LEFT JOIN 
//  				   :Com_("D_T2_STORAGE") t
//  				   on 
//  				   bs.STORAGE_ID=t.STORAGE_ID
//  				   where  
//  				   bs.BRANCH_ID='L00' AND bs.PRIORITY='0'
//  				   
//  	               )
//  			SELECT
//  			    s.SHELF_ID,
//  			    s.SHELF_NAME,
//  			    d.*,
//  			    m.item_specification,
//  			    b.BRANCH_NAME,
//  			    a.sumCount,
//  			    bs.ITEM_COUNT STORAGE_COUNT
//  			FROM
//  				:Com_("D_T1_PICKING_DETAIL") d
//  			LEFT JOIN
//  				:Com_("D_T2_ITEM_META") m 
//  		    ON
//  		    	d.ITEM_ID = m.ITEM_ID
//  			INNER JOIN
//  			:Com_("D_T2_SHELF_ITEM") i
//  			ON
//  			    i.ITEM_ID = d.ITEM_ID AND i.PRIORITY = 0
//  			INNER JOIN
//  				:Com_("D_T2_SHELF") s
//  			ON
//  			    s.SHELF_ID = i.SHELF_ID
//  			LEFT JOIN
//  				:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = d.BRANCH_ID
//      	    LEFT JOIN 
//      	    	a 
//  		    ON 
//  		    	a.ITEM_ID = d.ITEM_ID
//  		    LEFT JOIN 
//  			   bs 
//  		   ON 
//  			   bs.ITEM_ID=d.ITEM_ID
//  			WHERE
//  			    d.FORM_ID = :formId
//  			ORDER BY
//  				s.SHELF_ID,
//  				d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_4 = Com_("D_T2_STORAGE");
  String __sJT_5 = Com_("D_T1_PICKING_DETAIL");
  String __sJT_6 = Com_("D_T2_ITEM_META");
  String __sJT_7 = Com_("D_T2_SHELF_ITEM");
  String __sJT_8 = Com_("D_T2_SHELF");
  String __sJT_9 = Com_("D_T2_BRANCH");
  String __sJT_10 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PickingDetailBean_SJProfileKeys.getKey(0), 3);
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
      detailIter = new DetailIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:187^3*/
		List<PickingDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<PickingDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<PickingDetail> detailLst = new ArrayList<PickingDetail>();
		while(detailIter.next()) {
			PickingDetail detail = new PickingDetail();
			detail.setFormId(detailIter.form_id());
			detail.setShelfId(detailIter.shelf_id());
			detail.setShelfName(detailIter.shelf_name());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemSpecification(detailIter.item_specification());
			detail.setItemCategory(detailIter.item_category());
			detail.setItemCount(detailIter.item_count());
			detail.setBranchId(detailIter.branch_id());
			detail.setBranchName(detailIter.branch_name());
			detail.setSumCount(detailIter.sumCount());
			detail.setStorageCount(detailIter.storage_count());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class PickingDetailBean_SJProfileKeys 
{
  private static PickingDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PickingDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PickingDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.PickingDetailBean_SJProfile0");
  }
}
