/*@lineinfo:filename=PurchaseQueryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 23, 2015 by liyzh
 * Last edited on Apr 23, 2015 by liyzh
 * 
 * 说明： 中央工厂对物流中心采购单的处理（汇总生成计划单、出库）
 */
package logic.module.cf;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.PurchasingHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BranchCode;

public class PurchaseQueryBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(PurchaseQueryBean.class);

	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

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
    form_ref_idNdx = findColumn("form_ref_id");
    delivery_typeNdx = findColumn("delivery_type");
    provider_idNdx = findColumn("provider_id");
    providerNdx = findColumn("provider");
    requester_idNdx = findColumn("requester_id");
    requesterNdx = findColumn("requester");
    receiver_idNdx = findColumn("receiver_id");
    receiverNdx = findColumn("receiver");
    receive_addressNdx = findColumn("receive_address");
    receive_timeNdx = findColumn("receive_time");
    form_maker_idNdx = findColumn("form_maker_id");
    form_makerNdx = findColumn("form_maker");
    form_timeNdx = findColumn("form_time");
    auditor_idNdx = findColumn("auditor_id");
    auditorNdx = findColumn("auditor");
    audit_timeNdx = findColumn("audit_time");
    form_noteNdx = findColumn("form_note");
    storage_idNdx = findColumn("storage_id");
    storageNdx = findColumn("storage");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String form_ref_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_ref_idNdx);
  }
  private int form_ref_idNdx;
  public String delivery_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_typeNdx);
  }
  private int delivery_typeNdx;
  public String provider_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(provider_idNdx);
  }
  private int provider_idNdx;
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
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
  public String receiver_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receiver_idNdx);
  }
  private int receiver_idNdx;
  public String receiver() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receiverNdx);
  }
  private int receiverNdx;
  public String receive_address() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(receive_addressNdx);
  }
  private int receive_addressNdx;
  public Date receive_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(receive_timeNdx);
  }
  private int receive_timeNdx;
  public String form_maker_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_maker_idNdx);
  }
  private int form_maker_idNdx;
  public String form_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_makerNdx);
  }
  private int form_makerNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
  public String auditor_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditor_idNdx);
  }
  private int auditor_idNdx;
  public String auditor() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(auditorNdx);
  }
  private int auditorNdx;
  public Date audit_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(audit_timeNdx);
  }
  private int audit_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
  public String storage_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_idNdx);
  }
  private int storage_idNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public String max_pay_item() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(max_pay_itemNdx);
  }
  private int max_pay_itemNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:55^23*/
	
	/**
	 * 中央工厂对物流中心采购单的处理（汇总生成计划单、出库）
	 */
	public List<PurchasingHeader> query(String cfCode, java.util.Date startDate, java.util.Date endDate, String queryType)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		String query = "";
		if ("unsummary".equals(queryType)) { // 获取未审核记录
			query = query + " NVL(h.PLAN_STATUS, 'null') != '已汇总'";
		} else if ("un_out".equals(queryType)) {
			query = query + " NVL(h.OUT_STATUS, 'null') != '已出库'";
		}
		
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:75^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				h.*
//  		    FROM
//  		    :Com_("D_T1_PURCHASING_HEADER") h
//  		    LEFT JOIN
//  		    :Com_("D_T0_FORM_STATUS") s
//  		    ON
//  		        h.FORM_ID = s.FORM_ID
//  		    AND IS_CURRENT = '1'
//  		    WHERE
//  		    --	h.REQUESTER_ID = :lcCode
//  		    h.PROVIDER_ID = :cfCode
//  		    AND s.STATUS = '已审核'
//  		    AND :query
//  		    AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  		    ORDER BY
//  		        h.FORM_ID
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T0_FORM_STATUS");
  String __sJT_3 = cfCode;
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseQueryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setDate(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
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

/*@lineinfo:user-code*//*@lineinfo:94^3*/
		List<PurchasingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	public List<PurchasingHeader> queryUnOrder(String cfCode,String itemName, java.util.Date startDate, java.util.Date endDate)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		itemName = "%" + itemName + "%";
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:110^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { WITH
//  			    DELIVERY AS --已出货数
//  			    (
//  			        SELECT DISTINCT
//  			            sh.FORM_REF_ID FORM_ID,
//  			            sd.ITEM_ID,
//  			            sd.ITEM_NAME,
//  			            NVL(SUM(sd.DELIVERY_COUNT),0) DELIVERY_COUNT
//  			        FROM
//  			        :Com_("D_T1_SHIPPING_HEADER") sh
//  			        INNER JOIN
//  			        :Com_("D_T1_SHIPPING_DETAIL") sd
//  			        ON
//  			            sd.FORM_ID = sh.FORM_ID
//  			        AND sh.PROVIDER_ID='F00'
//  	        		INNER JOIN
//          			:Com_("D_T0_FORM_STATUS") s
//          			ON
//          				sh.FORM_ID = s.FORM_ID
//          			AND IS_CURRENT = '1' 
//              		AND s.STATUS = '已审核'
//  			        GROUP BY
//  			            (sh.FORM_REF_ID, sd.ITEM_ID,sd.ITEM_NAME)
//  			    )
//  			    ,
//  			    RECEIVE AS --已入库数
//  			    (
//  			        SELECT DISTINCT
//  			            d.FORM_ID,
//  			            d.ITEM_ID,
//  			            d.ITEM_NAME,
//  			            NVL(SUM(id.RECEIVE_COUNT),0) RECEIVE_COUNT
//  			        FROM
//  			        :Com_("D_T1_PURCHASING_DETAIL") d
//  			        INNER JOIN
//  			        :Com_("D_T1_COLLECT_REF_FORM") f
//  			        ON
//  			            f.REF_FORM_ID=d.FORM_ID
//  			        AND d.ITEM_ID = f.ITEM_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_ARRENGMENT_DETAIL") ad
//  			        ON
//  			            f.COLLECT_FORM_ID=ad.FORM_ID
//  			        AND ad.ITEM_ID =f.ITEM_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_HEADER") ih
//  			        ON
//  			            ih.FORM_REF_ID=ad.WORK_ORDER_ID
//  			        INNER JOIN
//  			        :Com_("D_T1_INPUT_DETAIL") id
//  			        ON
//  			            id.FORM_ID = ih.FORM_ID
//              		INNER JOIN
//          			:Com_("D_T0_FORM_STATUS") s
//          			ON
//          			    ih.FORM_ID = s.FORM_ID
//          			AND IS_CURRENT = '1' 
//              		AND s.STATUS = '已审核'
//  			        GROUP BY
//  			            ( d.FORM_ID, d.ITEM_ID ,d.ITEM_NAME)
//  			    )
//  			SELECT DISTINCT
//  			    h.*
//  			FROM
//  			:Com_("D_T1_PURCHASING_HEADER") h
//  			INNER JOIN
//  			    RECEIVE r
//  			ON
//  			    r.FORM_ID=h.FORM_ID
//  			LEFT JOIN
//  			    DELIVERY d
//  			ON
//  			    r.ITEM_ID =d.ITEM_ID
//  			AND d.FORM_ID=r.form_id
//  			INNER JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND IS_CURRENT = '1' 
//  			WHERE
//  			    --	h.REQUESTER_ID = :lcCode
//  			    h.PROVIDER_ID = :cfCode
//      		AND s.STATUS = '已审核'
//  			AND NVL(d.DELIVERY_COUNT,0) <NVL(r.RECEIVE_COUNT,0)
//  			AND (
//                      r.ITEM_ID LIKE :itemName
//                  OR  r.ITEM_NAME LIKE :itemName)
//  		    AND (h.AUDIT_TIME >= :sDate or :sDate is null)
//  			AND (h.AUDIT_TIME <= :eDate or :eDate is null)
//  		    ORDER BY
//  		        h.FORM_ID	
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_SHIPPING_HEADER");
  String __sJT_2 = Com_("D_T1_SHIPPING_DETAIL");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = Com_("D_T1_PURCHASING_DETAIL");
  String __sJT_5 = Com_("D_T1_COLLECT_REF_FORM");
  String __sJT_6 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_7 = Com_("D_T1_INPUT_HEADER");
  String __sJT_8 = Com_("D_T1_INPUT_DETAIL");
  String __sJT_9 = Com_("D_T0_FORM_STATUS");
  String __sJT_10 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_11 = Com_("D_T0_FORM_STATUS");
  String __sJT_12 = cfCode;
  String __sJT_13 = itemName;
  String __sJT_14 = itemName;
  Date __sJT_15 = sDate;
  Date __sJT_16 = sDate;
  Date __sJT_17 = eDate;
  Date __sJT_18 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PurchaseQueryBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setDate(15, __sJT_15);
      __sJT_stmt.setDate(16, __sJT_16);
      __sJT_stmt.setDate(17, __sJT_17);
      __sJT_stmt.setDate(18, __sJT_18);
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

/*@lineinfo:user-code*//*@lineinfo:202^3*/
		List<PurchasingHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	private List<PurchasingHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<PurchasingHeader> headerLst = new ArrayList<PurchasingHeader>();
		while(headerIter.next()) {
			PurchasingHeader header = new PurchasingHeader();
			header.setFormId(headerIter.form_id());
			header.setDeliveryType(headerIter.delivery_type());
			header.setProviderId(headerIter.provider_id());
			header.setProvider(headerIter.provider());
			header.setRequesterId(headerIter.requester_id());
			header.setRequester(headerIter.requester());
			header.setReceiverId(headerIter.receiver_id());
			header.setReceiver(headerIter.receiver());
			header.setReceiveAddress(headerIter.receive_address());
			header.setReceiveTime(SqlDateUtil.getUtilDate(headerIter.receive_time()));
			header.setFormMakerId(headerIter.form_maker_id());
			header.setFormMaker(headerIter.form_maker());
			header.setFormTime(SqlDateUtil.getUtilDate(headerIter.form_time()));
			header.setAuditorId(headerIter.auditor_id());
			header.setAuditor(headerIter.auditor());
			header.setAuditTime(SqlDateUtil.getUtilDate(headerIter.audit_time()));
			header.setFormNote(headerIter.form_note());
			header.setStorage(headerIter.storage());
			header.setStorageId(headerIter.storage_id());
			header.setAllPayAmt(headerIter.all_pay_amt());
			header.setMaxPayItem(headerIter.max_pay_item());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class PurchaseQueryBean_SJProfileKeys 
{
  private static PurchaseQueryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PurchaseQueryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PurchaseQueryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.cf.PurchaseQueryBean_SJProfile0");
  }
}
