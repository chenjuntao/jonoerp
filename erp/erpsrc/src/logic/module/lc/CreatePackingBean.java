/*@lineinfo:filename=CreatePackingBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 15, 2014 by liyzh
 * Last edited on Dec 15, 2014 by liyzh
 * 
 * 说明： 创建装箱单
 */
package logic.module.lc;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PackingDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class CreatePackingBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(CreatePackingBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:38^2*/

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
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
    box_type_idNdx = findColumn("box_type_id");
    box_type_nameNdx = findColumn("box_type_name");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    item_countNdx = findColumn("item_count");
    unit_volumeNdx = findColumn("unit_volume");
    unit_weightNdx = findColumn("unit_weight");
    box_volumeNdx = findColumn("box_volume");
    box_weightNdx = findColumn("box_weight");
    item_categoryNdx = findColumn("item_category");
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
  public String box_type_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_type_idNdx);
  }
  private int box_type_idNdx;
  public String box_type_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_type_nameNdx);
  }
  private int box_type_nameNdx;
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
  public Double box_volume() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(box_volumeNdx);
  }
  private int box_volumeNdx;
  public Double box_weight() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(box_weightNdx);
  }
  private int box_weightNdx;
  public String item_category() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_categoryNdx);
  }
  private int item_categoryNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^102*/
	
	/**
	 * 根据捡货单构造装箱需要的信息
	 */
	public List<PackingDetail> queryPacking(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:49^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    d.*,
//  			    b.BRANCH_NAME,
//  			    u.UNIT_VOLUME,
//  			    u.UNIT_WEIGHT,
//  			    b.TYPE_ID AS BOX_TYPE_ID,
//  			    b.TYPE_NAME AS BOX_TYPE_NAME,
//  			    b.VOLUME AS BOX_VOLUME,
//  			    b.WEIGHT AS BOX_WEIGHT
//  			FROM
//  			:Com_("D_T1_PICKING_DETAIL") d
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    b.BRANCH_ID = d.BRANCH_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = d.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID= m.ITEM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_BOX_TYPE") b
//  			ON
//  			    b.TYPE_ID = m.BOX_TYPE
//  			WHERE
//  			    d.FORM_ID = :formId
//  			ORDER BY
//  			    d.BRANCH_ID,
//  			    m.BOX_TYPE,
//  			    d.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PICKING_DETAIL");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_5 = Com_("D_T2_BOX_TYPE");
  String __sJT_6 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CreatePackingBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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

/*@lineinfo:user-code*//*@lineinfo:83^3*/
		List<PackingDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<PackingDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<PackingDetail> detailLst = new ArrayList<PackingDetail>();
		while(detailIter.next()) {
			PackingDetail detail = new PackingDetail();
			detail.setBranchId(detailIter.branch_id());
			detail.setBranchName(detailIter.branch_name());
			detail.setBoxTypeId(detailIter.box_type_id());
			detail.setBoxTypeName(detailIter.box_type_name());
			detail.setItemId(detailIter.item_id());
			detail.setItemName(detailIter.item_name());
			detail.setItemDimension(detailIter.item_dimension());
			detail.setItemCount(detailIter.item_count());
			detail.setUnitVolume(detailIter.unit_volume());
			detail.setUnitWeight(detailIter.unit_weight());
			detail.setBoxVolume(detailIter.box_volume());
			detail.setBoxWeight(detailIter.box_weight());
			detail.setItemCategory(detailIter.item_category());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class CreatePackingBean_SJProfileKeys 
{
  private static CreatePackingBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CreatePackingBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CreatePackingBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.CreatePackingBean_SJProfile0");
  }
}
