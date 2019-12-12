/*@lineinfo:filename=DailyBranchStorageBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
*
* Copyright (c) 2014
* Tanry Electronic Technology Co., Ltd.
* ChangSha, China
*
* All Rights Reserved.
*
* First created on Sep 28, 2014 by cjt
* Last edited on Sep 28, 2014 by cjt
*
* 说明： 访问餐厅每日库存表
*
*/

package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

import com.tanry.framework.util.DateTimeUtil;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

public class DailyBranchStorageBean extends ConnectionPool {
	public static Logger log = Logger.getLogger(DailyBranchStorageBean.class);
	
    /*@lineinfo:generated-code*//*@lineinfo:42^5*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DBIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DBIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    branch_idNdx = findColumn("branch_id");
    business_dateNdx = findColumn("business_date");
    item_idNdx = findColumn("item_id");
    item_count_theoryNdx = findColumn("item_count_theory");
    item_count_actualNdx = findColumn("item_count_actual");
  }
  public String branch_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_idNdx);
  }
  private int branch_idNdx;
  public Date business_date() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(business_dateNdx);
  }
  private int business_dateNdx;
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double item_count_theory() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_theoryNdx);
  }
  private int item_count_theoryNdx;
  public Double item_count_actual() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(item_count_actualNdx);
  }
  private int item_count_actualNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:42^138*/
    /*@lineinfo:generated-code*//*@lineinfo:43^5*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class TheoryStorageIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public TheoryStorageIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    item_countNdx = findColumn("item_count");
    useCountNdx = findColumn("useCount");
    resultCountNdx = findColumn("resultCount");
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
  public Double useCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(useCountNdx);
  }
  private int useCountNdx;
  public Double resultCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(resultCountNdx);
  }
  private int resultCountNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^115*/
    /*@lineinfo:generated-code*//*@lineinfo:44^5*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ActualStorageIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ActualStorageIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    check_countNdx = findColumn("check_count");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double check_count() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(check_countNdx);
  }
  private int check_countNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:44^79*/
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

    private void theoryBacth(String branchId,String storageId, java.util.Date settleDate) {
		String theoryBacthSql = "SELECT bill.item_id, nvl(s.item_count,0), bill.useCount, nvl(s.item_count,0) - bill.useCount AS resultCount FROM "
				+ getCompanyId() + ".D_T2_STORAGE s RIGHT JOIN (SELECT trim(t.item_id) item_id, SUM(t.item_gross_count * d.nqty) AS useCount FROM "
				+ getCompanyId() + ".d_t_food_bill b, " + getCompanyId() + ".D_T_FOOD_BILLS d, " + getCompanyId() + ".D_T2_THERAPY t "
			    + "WHERE trim(d.cbill_c) = trim(b.cbill_c) AND trim(t.therapy_id) = trim(d.cfood_c) "
				+ "AND trim(b.cbranch_c) = ? AND b.dbusiness = ? GROUP BY trim(t.item_id) ORDER BY trim(t.item_id) " 
			    + ") bill ON trim(s.item_id) = trim(bill.item_id) AND trim(s.storage_id) = ? ";
		
		List<Map<String, Object>> itemLst = jdbcTemplate.queryForList(theoryBacthSql,
				new Object[] {branchId, settleDate, storageId});
		
		String insertSql = " INSERT INTO " + getCompanyId() + ".D_T0_STORAGE_IN_OUT (BRANCH_ID, STORAGE_ID,BUSINESS_DATE, OPERATION_TIME, ITEM_ID, ITEM_UNIT_PRICE, ORGI_COUNT, ITEM_COUNT_IN, ITEM_COUNT_OUT, RESULT_COUNT, FORM_ID, REASON) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String updateSql = " UPDATE " + getCompanyId() + ".D_T2_STORAGE SET ITEM_COUNT = ? WHERE ITEM_ID = ? AND STORAGE_ID = ? ";
		
		List<Object[]> batchArgsI = new ArrayList<Object[]>();
		List<Object[]> batchArgsU = new ArrayList<Object[]>();
		
		for (Map<String, Object> item : itemLst) {
			Object[] argsI = new Object[] { branchId,storageId, settleDate, SqlDateUtil.getSqlDate(DateTimeUtil.getNow()),
					item.get("item_id"), 0,item.get("item_count") ,0,item.get("useCount"),item.get("resultCount"), "", "LLKU" };
			batchArgsI.add(argsI);
			
			Object[] argsU = new Object[] {item.get("resultCount"),item.get("item_id"),storageId};
			batchArgsU.add(argsU);
		}

		jdbcTemplate.batchUpdate(insertSql, batchArgsI);
		jdbcTemplate.batchUpdate(updateSql, batchArgsU);
	}
    
    //计算理论库存与实际库存（查当天的出品-->映射成本卡-->得到出品转换的原料消耗量-->实时库存表更新-->历史库存表添加库存记录）
    public int calcTheoryStorage(String branchId,String storageId, java.util.Date settleDate) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		Date sDate = SqlDateUtil.getSqlDate(settleDate);
		String formType = FormConstant.CHECK_FORM;
		
		//判断当天是否进行过理论扣库
		int checkHeaderCount = 0;
		
		try {
			/*@lineinfo:generated-code*//*@lineinfo:94^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    COUNT(o.BRANCH_ID) 
//  			FROM
//  				:Com_("D_T0_STORAGE_IN_OUT") o 
//  			WHERE
//  			    o.BRANCH_ID = :branchId
//  			AND o.BUSINESS_DATE = :sDate
//  			AND o.REASON = 'LLKU' };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = branchId;
  Date __sJT_3 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyBranchStorageBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
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
    checkHeaderCount = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:102^25*/
		} catch (SQLException e) {
			log.debug("Error running the query: " + e);
			String sqlState = e.getSQLState();
			log.debug("SQL state = " + sqlState);
			checkHeaderCount = 0;
		}
	
		if(checkHeaderCount > 0)
		{
			//如果当天已经进行理论库存计算，这里直接跳过，不需要再重复计算
			return 0;
		}else{
			String dateStr = DateTimeUtil.getDateStr(settleDate);
		}

		closeDefaultContext(myCtx);
		
		theoryBacth(branchId,storageId, settleDate);
//		//查询当天根据出品和成本卡进行扣库后的理论库存
//		TheoryStorageIter theoryStorageIter = null;
//		#sql [myCtx] theoryStorageIter = {
//			SELECT 
//			    bill.item_id,
//			 	nvl(s.item_count,0),
//			 	bill.useCount,
//			    nvl(s.item_count,0) - bill.useCount AS resultCount
//			FROM
//			    D_T2_STORAGE s
//			    
//			RIGHT JOIN
//			(
//				SELECT
//				    trim(t.item_id) item_id,
//				    SUM(t.item_gross_count * d.nqty) AS useCount
//				FROM
//				    d_t_food_bill b,
//				    D_T_FOOD_BILLS d,
//				    D_T2_THERAPY t 
//				WHERE trim(d.cbill_c) = trim(b.cbill_c)
//				AND trim(t.therapy_id) = trim(d.cfood_c)
//				AND trim(b.cbranch_c) = :branchId
//				AND b.dbusiness = :sDate
//				GROUP BY trim(t.item_id)
//				ORDER BY trim(t.item_id)
//			) bill
//			ON trim(s.item_id) = trim(bill.item_id)
//			AND trim(s.storage_id) = :storageId
//		};
//
//		//将上面计算出的结果更新到实时库存表中
//		while(theoryStorageIter.next()) {
//			String itemId = theoryStorageIter.item_id();
//			Double resultCount = theoryStorageIter.resultCount();
//			Double item_count = theoryStorageIter.item_count();
//			Double useCount = theoryStorageIter.useCount();
//			
//			//-----------------------------------------------------------------------
//			// 出入库表
//			
//		    item_count   = (item_count != null ? item_count : 0);
//		    useCount   = (useCount != null ? useCount : 0);
//		    resultCount   = (resultCount != null ? resultCount : 0);
//			
//			Date operationTime = SqlDateUtil.getSqlDate(DateTimeUtil.getNow());
//
//			#sql [myCtx] {
//				INSERT INTO D_T0_STORAGE_IN_OUT 
//					(BRANCH_ID, STORAGE_ID,BUSINESS_DATE, OPERATION_TIME, ITEM_ID, ITEM_UNIT_PRICE, ORGI_COUNT, ITEM_COUNT_IN, ITEM_COUNT_OUT, RESULT_COUNT, FORM_ID, REASON) 
//				VALUES (:branchId,:storageId, :sDate, :operationTime, :itemId, 0, :item_count, 0, :useCount, :resultCount, '', 'LLKU')
//			};
//			
//			//-----------------------------------------------------------------------
//			
//			#sql [myCtx] {
//				UPDATE 
//					D_T2_STORAGE 
//				SET 
//					ITEM_COUNT = :resultCount
//				WHERE 
//					ITEM_ID = :itemId 
//				AND STORAGE_ID = :storageId
//			};
//		}
		
//		theoryStorageIter.close();
//		
//		//直接从实时库存表中将数据查出来复制到日结历史库存表中
//		#sql [myCtx] {
//			INSERT INTO D_T3_DAILY_STORAGE 
//				SELECT A.STORAGE_ID, :sDate, A.ITEM_ID,	A.ITEM_COUNT, NULL 
//				FROM D_T2_STORAGE A WHERE A.STORAGE_ID = :branchId
//		};


		return 1;
	}

	//将实际库存记录保存在日结库存实际记录中
    public int calcActualStorage(String branchId, java.util.Date settleDate) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		Date sDate = SqlDateUtil.getSqlDate(settleDate);
		String formType = FormConstant.CHECK_FORM;
		
		//直接从实时库存表中将数据查出来复制到日结历史库存表中
		/*@lineinfo:generated-code*//*@lineinfo:208^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT
//  			INTO
//  			:Com_("D_T3_DAILY_STORAGE")
//  		    (
//  		        SELECT
//  		            A.STORAGE_ID,
//  		            :sDate,
//  		            A.ITEM_ID,
//  		            A.ITEM_COUNT,
//  		            actualstorage.CHECK_COUNT
//  		        FROM
//  		        :Com_("D_T2_STORAGE") A
//  		        LEFT JOIN
//  		            (
//  		                --//查询当天盘点过的物料，获取其实际库存
//  		                SELECT
//  		                    cd.ITEM_ID,
//  		                    cd.CHECK_COUNT
//  		                FROM
//  		                :Com_("D_T1_CHECK_HEADER") ch,
//  		                :Com_("D_T1_CHECK_DETAIL") cd
//  		                WHERE
//  		                    ch.FORM_ID = cd.FORM_ID
//  		                AND ch.FORM_TYPE =  :formType
//  		                AND ch.AUDIT_TIME =  :sDate
//  		                AND ch.CHECK_BRANCH_ID = :branchId ) actualstorage
//  		        ON
//  		            a.item_id = actualstorage.item_id
//  		        where A.STORAGE_ID = :branchId
//  		    )
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_STORAGE");
  Date __sJT_2 = sDate;
  String __sJT_3 = Com_("D_T2_STORAGE");
  String __sJT_4 = Com_("D_T1_CHECK_HEADER");
  String __sJT_5 = Com_("D_T1_CHECK_DETAIL");
  String __sJT_6 = formType;
  Date __sJT_7 = sDate;
  String __sJT_8 = branchId;
  String __sJT_9 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyBranchStorageBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:239^3*/
				
		closeDefaultContext(myCtx);
		
		return 1;
	}
    
    public Double getDailyBranchStorage(String branchId, String itemId,java.util.Date date)
    		throws NoPrivilegeException, SQLException, NoConnection {
    		
    		Date sDate = SqlDateUtil.getSqlDate(date);
    		
    		Double storage = 0.0;
    		DefaultContext myCtx = getDefaultContext();
    		try {
    			/*@lineinfo:generated-code*//*@lineinfo:254^8*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//      			    CASE
//      		        WHEN s.ITEM_COUNT_ACTUAL IS NULL
//      		        THEN s.ITEM_COUNT_THEORY
//      		        ELSE s.ITEM_COUNT_ACTUAL
//      		    END 
//      		FROM
//      		:Com_("D_T3_DAILY_STORAGE") s
//      		WHERE
//      		    s.BRANCH_ID = :branchId
//      		AND s.ITEM_ID = :itemId
//      		AND s.BUSINESS_DATE = :sDate };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T3_DAILY_STORAGE");
  String __sJT_2 = branchId;
  String __sJT_3 = itemId;
  Date __sJT_4 = sDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, DailyBranchStorageBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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
    storage = __sJT_rtRs.getDoubleWrapper(1);
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

/*@lineinfo:user-code*//*@lineinfo:266^35*/
    		} catch (SQLException e) {
    			String sqlState = e.getSQLState();
    			e.printStackTrace();
    		}
    		closeDefaultContext(myCtx);
    		
    		return storage;
    	}
}/*@lineinfo:generated-code*/class DailyBranchStorageBean_SJProfileKeys 
{
  private static DailyBranchStorageBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new DailyBranchStorageBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private DailyBranchStorageBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.DailyBranchStorageBean_SJProfile0");
  }
}
