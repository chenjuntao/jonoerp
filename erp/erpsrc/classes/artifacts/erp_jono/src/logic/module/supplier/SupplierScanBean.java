/*@lineinfo:filename=SupplierScanBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.module.supplier;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pojo.form.CollectDetail;
import pojo.form.RequestHeader;
import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;

public class SupplierScanBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(SupplierScanBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:30^2*/

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
    providerNdx = findColumn("provider");
    requesterNdx = findColumn("requester");
    storageNdx = findColumn("storage");
    form_makerNdx = findColumn("form_maker");
    form_noteNdx = findColumn("form_note");
    all_pay_amtNdx = findColumn("all_pay_amt");
    max_pay_itemNdx = findColumn("max_pay_item");
    statusNdx = findColumn("status");
    delivery_typeNdx = findColumn("delivery_type");
    form_timeNdx = findColumn("form_time");
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
  public String provider() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(providerNdx);
  }
  private int providerNdx;
  public String requester() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(requesterNdx);
  }
  private int requesterNdx;
  public String storage() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storageNdx);
  }
  private int storageNdx;
  public String form_maker() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_makerNdx);
  }
  private int form_makerNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
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
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
  public String delivery_type() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(delivery_typeNdx);
  }
  private int delivery_typeNdx;
  public Date form_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_timeNdx);
  }
  private int form_timeNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^4*/
	
	
	/**
	 * startDate 	审核开始日期
	 * endDate	  	审核结束日期
	 * supplierId 	供应商Id
	 */
	public List<Map> scan(java.util.Date startDate, java.util.Date endDate, String supplierId,String formIdText,String orderType,int startRow, int endRow)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		
		if (!TextUtil.isEmpty(supplierId)) { 
			query = query + " AND h.PROVIDER_ID = '" + supplierId + "'";
		}
		
		if (!TextUtil.isEmpty(formIdText)) { 
			query = query + " AND upper(h.FORM_ID)  like'%" + formIdText + "%'";
		}
		
		if (!TextUtil.isEmpty(orderType)) { 
			query = query + " AND h.DELIVERY_TYPE  = '" + orderType + "'";
		}
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:72^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROWNUM rowNumber
//  			            FROM
//  			                (
//  			                    SELECT
//  			                        h.FORM_ID,
//  			                        h.FORM_REF_ID,
//  			                        h.PROVIDER,
//  			                        h.REQUESTER,
//  			                        h.STORAGE,
//  			                        h.FORM_MAKER,
//  			                        h.FORM_NOTE,
//  			                        h.DELIVERY_TYPE,
//  			                        h.ALL_PAY_AMT,
//  			                        h.MAX_PAY_ITEM,
//  			                        h.FORM_TIME,
//  			                        CASE
//  			                            WHEN (
//  			                                    SELECT
//  			                                        COUNT(*)
//  			                                    FROM
//  			                                    :Com_("D_T0_FORM_STATUS") s
//  			                                    WHERE
//  			                                        s.STATUS = '已查看'
//  			                                    AND s.FORM_ID = h.FORM_ID) > 0
//  			                            THEN 'Y'
//  			                            ELSE 'N'
//  			                        END status
//  			                    FROM
//  			                    :Com_("D_T1_PURCHASING_HEADER") h
//  			                    LEFT JOIN
//  			                    :Com_("D_T2_BRANCH") b
//  			                    ON
//  			                        h.PROVIDER_ID = b.BRANCH_ID
//                          		LEFT JOIN
//                          		:Com_("D_T0_FORM_STATUS") s
//  	                        	ON
//  	                        	    h.FORM_ID = s.FORM_ID
//  	                        	AND s.IS_CURRENT = 1
//  			                    WHERE
//  			                        :query
//                                  AND s.STATUS NOT IN ('未审核')
//  			                    AND b.BRANCH_TYPE = 'SUPPLIER'
//  			                    AND (
//  			                            h.FORM_TIME >= :sDate
//  			                        OR  :sDate IS NULL)
//  			                    AND (
//  			                            h.FORM_TIME <= :eDate
//  			                        OR  :eDate IS NULL)
//  			                    AND h.RECEIVER_ID IS NULL --不看直配小单
//  			                    ORDER BY
//  			                        h.FORM_TIME DESC,
//  			                        h.FORM_ID DESC) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_FORM_STATUS");
  String __sJT_2 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_3 = Com_("D_T2_BRANCH");
  String __sJT_4 = Com_("D_T0_FORM_STATUS");
  String __sJT_5 = query;
  Date __sJT_6 = sDate;
  Date __sJT_7 = sDate;
  Date __sJT_8 = eDate;
  Date __sJT_9 = eDate;
  int __sJT_10 = endRow;
  int __sJT_11 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierScanBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDate(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setInt(10, __sJT_10);
      __sJT_stmt.setInt(11, __sJT_11);
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

/*@lineinfo:user-code*//*@lineinfo:134^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/**
	 * startDate 	审核开始日期
	 * endDate	  	审核结束日期
	 * supplierId 	供应商Id
	 */
	public Integer count(java.util.Date startDate, java.util.Date endDate, String supplierId,String formIdText,String orderType)
			throws NoPrivilegeException,SQLException,NoConnection {
		String query = " 1=1";
		
		if (!TextUtil.isEmpty(supplierId)) { 
			query = query + " AND h.PROVIDER_ID = '" + supplierId + "'";
		}
		
		if (!TextUtil.isEmpty(formIdText)) { 
			query = query + " AND upper(h.FORM_ID)  like'%" + formIdText + "%'";
		}
		
		if (!TextUtil.isEmpty(orderType)) { 
			query = query + " AND h.DELIVERY_TYPE  = '" + orderType + "'";
		}
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		Integer total = 0;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:167^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT( h.FORM_ID )
//  			
//  			FROM
//  			:Com_("D_T1_PURCHASING_HEADER") h
//  			LEFT JOIN
//  			:Com_("D_T2_BRANCH") b
//  			ON
//  			    h.PROVIDER_ID = b.BRANCH_ID
//  			LEFT JOIN
//  			:Com_("D_T0_FORM_STATUS") s
//  			ON
//  			    h.FORM_ID = s.FORM_ID
//  			AND s.IS_CURRENT = 1
//  			WHERE
//  			    :query
//  			AND s.STATUS NOT IN ('未审核')
//  			AND b.BRANCH_TYPE = 'SUPPLIER'
//  			AND (
//  			        h.FORM_TIME >= :sDate
//  			    OR  :sDate IS NULL)
//  			AND (
//  			        h.FORM_TIME <= :eDate
//  			    OR  :eDate IS NULL)
//              AND h.RECEIVER_ID IS NULL --不看直配小单
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_PURCHASING_HEADER");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T0_FORM_STATUS");
  String __sJT_4 = query;
  Date __sJT_5 = sDate;
  Date __sJT_6 = sDate;
  Date __sJT_7 = eDate;
  Date __sJT_8 = eDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, SupplierScanBean_SJProfileKeys.getKey(0), 1);
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
    total = __sJT_rtRs.getIntWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:194^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headLst = new ArrayList<Map>();
		while(headerIter.next()) {
			Map<String, Object> head = new HashMap<String, Object>();
			head.put("form_id", headerIter.form_id());
			head.put("form_ref_id", headerIter.form_ref_id());
			head.put("provider", headerIter.provider());
			head.put("requester", headerIter.requester());
			head.put("storage", headerIter.storage());
			head.put("form_maker", headerIter.form_maker());
			head.put("form_note", headerIter.form_note());
			head.put("all_pay_amt", headerIter.all_pay_amt());
			head.put("max_pay_item", headerIter.max_pay_item());
			head.put("status", headerIter.status());
			head.put("form_time", headerIter.form_time());
			head.put("delivery_type", headerIter.delivery_type());
			headLst.add(head);
		}
		return headLst;
	}
	
}/*@lineinfo:generated-code*/class SupplierScanBean_SJProfileKeys 
{
  private static SupplierScanBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new SupplierScanBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private SupplierScanBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.module.supplier.SupplierScanBean_SJProfile0");
  }
}
