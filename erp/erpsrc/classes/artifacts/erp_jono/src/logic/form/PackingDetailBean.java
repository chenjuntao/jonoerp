/*@lineinfo:filename=PackingDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Dec 11 15:06:51 CST 2014 by lyz
 * Last edited on Thu Dec 11 15:06:51 CST 2014 by lyz
 * 
 * comment: 装箱单明细
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PackingDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PackingDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PackingDetailBean.class);
	
	public int saveEntity(PackingDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String branchId = detail.getBranchId();
		String boxId = detail.getBoxId();
		String boxName = detail.getBoxName();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		Double unitVolume = detail.getUnitVolume();
		Double unitWeight = detail.getUnitWeight();
		Double itemCount = detail.getItemCount();
		Double volume = detail.getVolume();
		Double weight = detail.getWeight();
		String itemCategory = detail.getItemCategory();

		/*@lineinfo:generated-code*//*@lineinfo:53^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_PACKING_DETAIL") 
//  				(FORM_ID, BRANCH_ID, BOX_ID, BOX_NAME, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, UNIT_VOLUME, UNIT_WEIGHT, ITEM_COUNT, VOLUME, WEIGHT, ITEM_CATEGORY) 
//  			VALUES (:formId, :branchId, :boxId, :boxName, :itemId, :itemName, :itemDimension, :unitVolume, :unitWeight, :itemCount, :volume, :weight, :itemCategory)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = branchId;
  String __sJT_4 = boxId;
  String __sJT_5 = boxName;
  String __sJT_6 = itemId;
  String __sJT_7 = itemName;
  String __sJT_8 = itemDimension;
  Double __sJT_9 = unitVolume;
  Double __sJT_10 = unitWeight;
  Double __sJT_11 = itemCount;
  Double __sJT_12 = volume;
  Double __sJT_13 = weight;
  String __sJT_14 = itemCategory;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingDetailBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:57^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateEntity(PackingDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String formId = detail.getFormId();
		String branchId = detail.getBranchId();
		String boxId = detail.getBoxId();
		String itemId = detail.getItemId();
		String itemName = detail.getItemName();
		String itemDimension = detail.getItemDimension();
		Double unitVolume = detail.getUnitVolume();
		Double unitWeight = detail.getUnitWeight();
		Double itemCount = detail.getItemCount();
		Double volume = detail.getVolume();
		Double weight = detail.getWeight();
		String itemCategory = detail.getItemCategory();

		/*@lineinfo:generated-code*//*@lineinfo:80^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T1_PACKING_DETAIL")
//  			SET
//  				FORM_ID = :formId,
//  				ITEM_ID = :itemId,
//  				ITEM_NAME = :itemName,
//  				ITEM_DIMENSION = :itemDimension,
//  				UNIT_VOLUME = :unitVolume,
//  				ITEM_COUNT = :itemCount,
//  				VOLUME = :volume,
//  				ITEM_CATEGORY = :itemCategory
//  			WHERE 
//  				FORM_ID = :formId
//  			AND ITEM_ID = :itemId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = itemId;
  String __sJT_4 = itemName;
  String __sJT_5 = itemDimension;
  Double __sJT_6 = unitVolume;
  Double __sJT_7 = itemCount;
  Double __sJT_8 = volume;
  String __sJT_9 = itemCategory;
  String __sJT_10 = formId;
  String __sJT_11 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_stmt.setDoubleWrapper(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:95^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:105^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_PACKING_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingDetailBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:111^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<PackingDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:121^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_PACKING_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = :formId
//  			ORDER BY
//  			    d.BRANCH_ID,
//  			    d.BOX_ID,
//  			    d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PACKING_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PackingDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:132^3*/
		List<PackingDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:139^2*/

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
    branch_idNdx = findColumn("branch_id");
    box_idNdx = findColumn("box_id");
    box_nameNdx = findColumn("box_name");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_countNdx = findColumn("item_count");
    unit_volumeNdx = findColumn("unit_volume");
    unit_weightNdx = findColumn("unit_weight");
    volumeNdx = findColumn("volume");
    weightNdx = findColumn("weight");
    item_categoryNdx = findColumn("item_category");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public String box_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_idNdx);
  }
  private int box_idNdx;
  public String box_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_nameNdx);
  }
  private int box_nameNdx;
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
  public Double item_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_countNdx);
  }
  private int item_countNdx;
  public Double unit_volume() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_volumeNdx);
  }
  private int unit_volumeNdx;
  public Double unit_weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_weightNdx);
  }
  private int unit_weightNdx;
  public Double volume() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(volumeNdx);
  }
  private int volumeNdx;
  public Double weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(weightNdx);
  }
  private int weightNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:141^94*/
	
	private List<PackingDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<PackingDetail> detailLst = new ArrayList<PackingDetail>();
		while(detailIter.next()) {
			PackingDetail detail = new PackingDetail();
			detail.setFormId(detailIter.form_id());
			detail.setBranchId(detailIter.branch_id());
			detail.setBoxId(detailIter.box_id());
			detail.setBoxName(detailIter.box_name());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemCount(detailIter.item_count());
			detail.setUnitVolume(detailIter.unit_volume());
			detail.setVolume(detailIter.volume());
			detail.setUnitWeight(detailIter.unit_weight());
			detail.setWeight(detailIter.weight());
			detail.setItemCategory(detailIter.item_category());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class PackingDetailBean_SJProfileKeys 
{
  private static PackingDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PackingDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PackingDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.PackingDetailBean_SJProfile0");
  }
}
