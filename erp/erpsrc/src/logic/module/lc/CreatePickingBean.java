/*@lineinfo:filename=CreatePickingBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 10, 2014 by liyzh
 * Last edited on Dec 10, 2014 by liyzh
 * 
 * 说明： 创建拣货单
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

import pojo.form.CollectDetail;
import pojo.form.RequestHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class CreatePickingBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(CreatePickingBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

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
    shelf_idNdx = findColumn("shelf_id");
    shelf_nameNdx = findColumn("shelf_name");
    box_typeNdx = findColumn("box_type");
    category_nameNdx = findColumn("category_name");
    form_idNdx = findColumn("form_id");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    delivery_unitNdx = findColumn("delivery_unit");
    unit_volumeNdx = findColumn("unit_volume");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
    requester_idNdx = findColumn("requester_id");
    requesterNdx = findColumn("requester");
    packaging_countNdx = findColumn("packaging_count");
  }
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
  public String box_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_typeNdx);
  }
  private int box_typeNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
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
  public String delivery_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_unitNdx);
  }
  private int delivery_unitNdx;
  public Double unit_volume() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_volumeNdx);
  }
  private int unit_volumeNdx;
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
  public String requester_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requester_idNdx);
  }
  private int requester_idNdx;
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
  public Double packaging_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(packaging_countNdx);
  }
  private int packaging_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^65*/
	
	/**
	 * 根据配送单编号查询库位等信息，用于手动构造捡货单（方便保存捡货单与配送单的对应关系）
	 */
	public List<Map> query(String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " h.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:53^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    s.SHELF_ID,
//  			    s.SHELF_NAME,
//  			    m.BOX_TYPE,
//  			    c.CATEGORY_NAME,
//  			    u.DELIVERY_UNIT,
//  			    u.UNIT_VOLUME AS unit_volume,
//  			    d.FORM_ID,
//  			    d.ITEM_ID,
//  			    d.ITEM_NAME,
//  			    d.ITEM_DIMENSION,
//  			    d.ITEM_SPECIFICATION,
//  			    d.ITEM_CATEGORY,
//  			    h.REQUESTER_ID,
//  			    h.REQUESTER,
//  			    d.PACKAGING_COUNT
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  			INNER JOIN
//  			:Com_("D_T1_SHIPPING_DETAIL") d
//  			ON
//  			    d.FORM_ID = h.FORM_ID
//  			LEFT JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = d.ITEM_CATEGORY
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = d.ITEM_ID
//  			INNER JOIN
//  			:Com_("D_T2_SHELF_ITEM") i
//  			ON
//  			    i.ITEM_ID = m.ITEM_ID AND i.PRIORITY = 0
//  			INNER JOIN
//  			:Com_("D_T2_SHELF") s
//  			ON
//  			    s.SHELF_ID = i.SHELF_ID
//  			LEFT JOIN
//  				:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = m.ITEM_ID
//  	        WHERE
//  				:query
//  			ORDER BY
//  			    s.SHELF_ID,
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_3 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_SHELF_ITEM");
  String __sJT_6 = Com_("D_T2_SHELF");
  String __sJT_7 = Com_("D_T2_DELIVERY_UNIT");
  String __sJT_8 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CreatePickingBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:101^3*/
		List<Map> detailLst = processDetailIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<Map> processDetailIter(DetailIter detailIter) 
			throws SQLException {
		List<Map> detailLst = new ArrayList<Map>();
		while(detailIter.next()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			detail.put("shelfId", detailIter.shelf_id());
			detail.put("shelfName", detailIter.shelf_name());
			detail.put("formId", detailIter.form_id());
			detail.put("itemId", detailIter.item_id());
			detail.put("categoryName", detailIter.category_name());
			detail.put("itemName", detailIter.item_name());
			detail.put("itemDimension", detailIter.item_dimension());
			detail.put("deliveryUnit", detailIter.delivery_unit());
			detail.put("unitVolume", detailIter.unit_volume());
			detail.put("itemSpecification", detailIter.item_specification());
			detail.put("itemCategory", detailIter.item_category());
			detail.put("requesterId", detailIter.requester_id());
			detail.put("requester", detailIter.requester());
			detail.put("packagingCount", detailIter.packaging_count());
			detail.put("boxType", detailIter.box_type());
			detailLst.add(detail);
		}
		return detailLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:133^2*/

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
    shelf_idNdx = findColumn("shelf_id");
    shelf_nameNdx = findColumn("shelf_name");
    box_typeNdx = findColumn("box_type");
    category_nameNdx = findColumn("category_name");
    item_idNdx = findColumn("item_id");
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    infosNdx = findColumn("infos");
    delivery_unitNdx = findColumn("delivery_unit");
    unit_volumeNdx = findColumn("unit_volume");
    item_specificationNdx = findColumn("item_specification");
    item_categoryNdx = findColumn("item_category");
  }
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
  public String box_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(box_typeNdx);
  }
  private int box_typeNdx;
  public String category_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(category_nameNdx);
  }
  private int category_nameNdx;
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
  public String infos() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(infosNdx);
  }
  private int infosNdx;
  public String delivery_unit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_unitNdx);
  }
  private int delivery_unitNdx;
  public Double unit_volume() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(unit_volumeNdx);
  }
  private int unit_volumeNdx;
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:135^93*/
	
	public List<Map> queryGroup(String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " h.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:143^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    temp AS
//  			    (
//  			        SELECT
//  			            d.ITEM_ID,
//  			            h.REQUESTER_ID,
//  			            SUM(d.SHIPPING_COUNT) SHIPPING_COUNT
//  			        FROM
//  			        :Com_("D_T1_SHIPPING_DETAIL") d
//  			        INNER JOIN
//  			        :Com_("D_T1_SHIPPING_HEADER") h
//  			        ON
//  			            d.form_id = h.form_id
//  			        WHERE
//  					 :query
//  			        GROUP BY
//  			            ROLLUP( d.ITEM_ID, h.REQUESTER_ID)
//  			    )
//  			    ,
//  			    temp2 AS
//  			    (
//  			        SELECT
//  			            DECODE(p.ITEM_ID,NULL,'合计',p.ITEM_ID) ITEM_ID,
//  			            listagg('('||REQUESTER_ID||'|' || SHIPPING_COUNT ||')',',') within GROUP (ORDER BY
//  			            REQUESTER_ID) infos
//  			        FROM
//  			            temp p
//  			        GROUP BY
//  			            p.ITEM_ID
//  			    )
//  			SELECT
//  			    s.SHELF_ID,
//  			    s.SHELF_NAME,
//  			    m.BOX_TYPE,
//  			    c.CATEGORY_NAME,
//  			    u.DELIVERY_UNIT,
//  			    u.UNIT_VOLUME AS unit_volume,
//  			    m.ITEM_NAME,
//  			    m.ITEM_DIMENSION,
//  			    m.ITEM_SPECIFICATION,
//  			    m.CATEGORY_ID ITEM_CATEGORY,
//  			    p2.*
//  			FROM
//  			    temp2 p2
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_META") m
//  			ON
//  			    m.ITEM_ID = p2.ITEM_ID
//  			INNER JOIN
//  			:Com_("D_T2_ITEM_CATEGORY") c
//  			ON
//  			    c.CATEGORY_ID = m.CATEGORY_ID
//  			INNER JOIN
//  			:Com_("D_T2_SHELF_ITEM") i
//  			ON
//  			    i.ITEM_ID = m.ITEM_ID
//  			AND i.PRIORITY = 0
//  			INNER JOIN
//  			:Com_("D_T2_SHELF") s
//  			ON
//  			    s.SHELF_ID = i.SHELF_ID
//  			LEFT JOIN
//  			:Com_("D_T2_DELIVERY_UNIT") u
//  			ON
//  			    u.ITEM_ID = m.ITEM_ID
//  			ORDER BY
//  			    s.SHELF_ID,
//  			    m.ITEM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_2 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_3 = query;
  String __sJT_4 = Com_("D_T2_ITEM_META");
  String __sJT_5 = Com_("D_T2_ITEM_CATEGORY");
  String __sJT_6 = Com_("D_T2_SHELF_ITEM");
  String __sJT_7 = Com_("D_T2_SHELF");
  String __sJT_8 = Com_("D_T2_DELIVERY_UNIT");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CreatePickingBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:212^3*/
		List<Map> headerLst = processHeaderIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	private List<Map> processHeaderIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			String[] infos = headerIter.infos().toString().split(",");
			for (int i = 0; i < infos.length; i++) {
				String[] temp = infos[i].replace("(", "").replace(")", "").split("\\|");
				
				for (int j = 0; j < temp.length; j++) {
					if(!TextUtil.isEmpty(temp[0])){
						header.put(temp[0], temp[1]);
					}else{
						header.put("sum", temp[1]);
					}
				}
			}
			header.put("shelfId", headerIter.shelf_id());
			header.put("shelfName", headerIter.shelf_name());
			header.put("itemId", headerIter.item_id());
			header.put("categoryName", headerIter.category_name());
			header.put("item_name", headerIter.item_name());
			header.put("itemDimension", headerIter.item_dimension());
			header.put("deliveryUnit", headerIter.delivery_unit());
			header.put("unitVolume", headerIter.unit_volume());
			header.put("itemSpecification", headerIter.item_specification());
			header.put("itemCategory", headerIter.item_category());
			header.put("boxType", headerIter.box_type());
			
			headerLst.add(header);
		}
		return headerLst;
	}
	/*@lineinfo:generated-code*//*@lineinfo:252^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class BranchIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BranchIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    branch_nameNdx = findColumn("branch_name");
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
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:255^3*/
	
	public List<Map> queryBranch(String ids)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " h.FORM_ID IN ('" + ids.replaceAll(",", "','") + "') ";
		
		DefaultContext myCtx = getDefaultContext();
		BranchIter branchIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:263^3*/

//  ************************************************************
//  #sql [myCtx] branchIter = { SELECT DISTINCT
//  			    h.REQUESTER_ID BRANCH_ID,
//  			    h.REQUESTER BRANCH_NAME
//  			FROM
//  			:Com_("D_T1_SHIPPING_HEADER") h
//  		     WHERE
//  			 	:query
//  			  order by h.REQUESTER_ID desc
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = query;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, CreatePickingBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:272^4*/
		List<Map> branchLst = new ArrayList<Map>();
		
		while(branchIter.next()) {
			Map header = new HashMap();
			header.put("branch_id", branchIter.branch_id());
			header.put("branch_name", branchIter.branch_name());
			
			branchLst.add(header);
		}
		
		branchIter.close();
		closeDefaultContext(myCtx);
		return branchLst;
	}
}/*@lineinfo:generated-code*/class CreatePickingBean_SJProfileKeys 
{
  private static CreatePickingBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new CreatePickingBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private CreatePickingBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.lc.CreatePickingBean_SJProfile0");
  }
}
