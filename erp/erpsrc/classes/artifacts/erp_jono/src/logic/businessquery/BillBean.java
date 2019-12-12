/*@lineinfo:filename=BillBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 *read bill list
  *by cjt
 *first created on 2014.6.19
 *latt edited on 2014.7.10
 */

package logic.businessquery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.acl.NoPrivilegeException;
import pojo.businessquery.Bill;

public class BillBean extends ConnectionPool
{
	private  String strDateFormat = "yyyy-MM-dd";//设置日期格式

	/*@lineinfo:generated-code*//*@lineinfo:26^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class BillIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public BillIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cBill_CNdx = findColumn("cBill_C");
    DBUSINESSNdx = findColumn("DBUSINESS");
    cPeriod_NNdx = findColumn("cPeriod_N");
    cShift_NNdx = findColumn("cShift_N");
    iGuestNumNdx = findColumn("iGuestNum");
    cBranch_NNdx = findColumn("cBranch_N");
    dtBillTimeNdx = findColumn("dtBillTime");
    dtSettleTimeNdx = findColumn("dtSettleTime");
    cCreateManNdx = findColumn("cCreateMan");
    cSettleManNdx = findColumn("cSettleMan");
    nFoodAmtNdx = findColumn("nFoodAmt");
    nRoundAmtNdx = findColumn("nRoundAmt");
    nPresentAmtNdx = findColumn("nPresentAmt");
    nOughtAmtNdx = findColumn("nOughtAmt");
    nPayAmtNdx = findColumn("nPayAmt");
    cTable_NNdx = findColumn("cTable_N");
    nDisAmtNdx = findColumn("nDisAmt");
    cDisManCur_nNdx = findColumn("cDisManCur_n");
    cDisCurWhyNdx = findColumn("cDisCurWhy");
    sRemarkNdx = findColumn("sRemark");
  }
  public String cBill_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBill_CNdx);
  }
  private int cBill_CNdx;
  public String DBUSINESS() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(DBUSINESSNdx);
  }
  private int DBUSINESSNdx;
  public String cPeriod_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPeriod_NNdx);
  }
  private int cPeriod_NNdx;
  public String cShift_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cShift_NNdx);
  }
  private int cShift_NNdx;
  public Integer iGuestNum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(iGuestNumNdx);
  }
  private int iGuestNumNdx;
  public String cBranch_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBranch_NNdx);
  }
  private int cBranch_NNdx;
  public String dtBillTime() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(dtBillTimeNdx);
  }
  private int dtBillTimeNdx;
  public String dtSettleTime() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(dtSettleTimeNdx);
  }
  private int dtSettleTimeNdx;
  public String cCreateMan() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cCreateManNdx);
  }
  private int cCreateManNdx;
  public String cSettleMan() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cSettleManNdx);
  }
  private int cSettleManNdx;
  public Double nFoodAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nFoodAmtNdx);
  }
  private int nFoodAmtNdx;
  public Double nRoundAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nRoundAmtNdx);
  }
  private int nRoundAmtNdx;
  public Double nPresentAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nPresentAmtNdx);
  }
  private int nPresentAmtNdx;
  public Double nOughtAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nOughtAmtNdx);
  }
  private int nOughtAmtNdx;
  public Double nPayAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nPayAmtNdx);
  }
  private int nPayAmtNdx;
  public String cTable_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cTable_NNdx);
  }
  private int cTable_NNdx;
  public Double nDisAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nDisAmtNdx);
  }
  private int nDisAmtNdx;
  public String cDisManCur_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cDisManCur_nNdx);
  }
  private int cDisManCur_nNdx;
  public String cDisCurWhy() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cDisCurWhyNdx);
  }
  private int cDisCurWhyNdx;
  public String sRemark() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sRemarkNdx);
  }
  private int sRemarkNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:45^26*/
	
	/*@lineinfo:generated-code*//*@lineinfo:47^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ShopTableInfoIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ShopTableInfoIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cBranch_CNdx = findColumn("cBranch_C");
    CBRANCH_NNdx = findColumn("CBRANCH_N");
    CTABLE_NNdx = findColumn("CTABLE_N");
  }
  public String cBranch_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cBranch_CNdx);
  }
  private int cBranch_CNdx;
  public String CBRANCH_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CBRANCH_NNdx);
  }
  private int CBRANCH_NNdx;
  public String CTABLE_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CTABLE_NNdx);
  }
  private int CTABLE_NNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:49^30*/
	
	//将ASCII码为0（即null）的字符串转换为空字符串，前者在IE中显示会出现问题
	private String ConvertAsciii0ToSpace(String str)
	{
		if (str != null)
		 {	
			if (str.length() == 1) 
			{
				int asciiValue = str.charAt(0);
				if (asciiValue == 0) 
				{
					return "";
				}
			}
		}
		return str;
	}
	
	//根据门店编号和台位编号获取门店名字和台位名字
	public Bill getBillBean(String shopC, String table)
		throws NoPrivilegeException,SQLException,NoConnection{
		DefaultContext myCtx = getDefaultContext();
		ShopTableInfoIterator shopTableInfoIterator = null;
		Bill bill = new Bill();
							
		/*@lineinfo:generated-code*//*@lineinfo:75^3*/

//  ************************************************************
//  #sql [myCtx] shopTableInfoIterator = { SELECT
//  			    cBranch_C,
//  			    CBRANCH_N,
//  			    CTABLE_N
//  			FROM
//  			    :Com_("d_t_food_bill")
//  			WHERE
//  			    cBranch_C = :shopC
//  			AND cTable_N = :table
//  			AND ROWNUM = 1
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopC;
  String __sJT_3 = table;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shopTableInfoIterator = new ShopTableInfoIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:86^3*/
		
		while(shopTableInfoIterator.next()){
			bill.shopName = shopTableInfoIterator.CBRANCH_N();
			bill.table = shopTableInfoIterator.CTABLE_N();
		}
		
		shopTableInfoIterator.close();
		closeDefaultContext(myCtx);
		return bill;
	}

	//指定门店的指定台位的单据数量
	public int billCountByST(String startTime, String endTime, String shopC, String table)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		int billCount = 0;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:104^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*)  from :Com_("d_t_food_bill")
//  						where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  						and DBUSINESS <= to_date(:endTime, :strDateFormat)
//  						and cBranch_C = :shopC and cTable_N = :table };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  String __sJT_6 = shopC;
  String __sJT_7 = table;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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
    billCount = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:107^51*/
		closeDefaultContext(myCtx);
		return billCount;
	}
	
	//指定门店的指定台位的单据列表
	public List listBillByST(String startTime, String endTime, String shopC, String table, int startRow, int pageNum)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		List bills = new ArrayList();
		DefaultContext myCtx = getDefaultContext();
		BillIterator billIterator = null;
		int endRow = startRow + pageNum;
		//遍历查找指定门店的指定台位的单据列表
		/*@lineinfo:generated-code*//*@lineinfo:121^3*/

//  ************************************************************
//  #sql [myCtx] billIterator = { select * from 
//  									(select rownum rn, cBill_C, cPeriod_N, cShift_N, iGuestNum, 
//  									dtBillTime, dtSettleTime, DBUSINESS, cCreateMan, cSettleMan,
//  									nFoodAmt, cTable_N,	nRoundAmt, nPresentAmt, nOughtAmt, nPayAmt, 
//  									cBranch_N, nDisAmt, cDisManCur_n, cDisCurWhy, sRemark 
//  									from :Com_("d_t_food_bill")
//  									where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  									and DBUSINESS <= to_date(:endTime, :strDateFormat)
//  									and cBranch_C = :shopC and cTable_N = :table
//  									and rownum <= :endRow) where rn > :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  String __sJT_6 = shopC;
  String __sJT_7 = table;
  int __sJT_8 = endRow;
  int __sJT_9 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      __sJT_stmt.setInt(9, __sJT_9);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      billIterator = new BillIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:130^53*/
		while(billIterator.next())
		{
			Bill bill = processIter(billIterator);
			bills.add(bill);
		}
		billIterator.close();
		closeDefaultContext(myCtx);
		return bills;
	}

	//指定开台人和指定结账人等的账单数量
	public int billCountByMan(String startTime, String endTime, 
			String createMan, String settleMan, String disManCur_N)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		String conditionStr = " 1=1";
		if(!createMan.equals("%")){
			conditionStr += " and cCreateMan like '" + createMan + "'";
		}
		if(!settleMan.equals("%")){
			conditionStr += " and cSettleMan like '" + settleMan + "'";
		}
		if(!disManCur_N.equals("%")){
			conditionStr += " and cDisManCur_n like '" + disManCur_N + "'";
		}
		
		int billCount = 0;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:159^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*)  
//  			from :Com_("d_t_food_bill")
//  			where 
//  				DBUSINESS >= to_date(:startTime, :strDateFormat)
//  			and DBUSINESS <= to_date(:endTime, :strDateFormat)
//  			and :conditionStr
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  String __sJT_6 = conditionStr;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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
    billCount = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:166^3*/
		closeDefaultContext(myCtx);
		return billCount;
	}

	//指定开台人和指定结账人等的账单列表
	public List listBillByMan(String startTime, String endTime, String createMan, 
			String settleMan, String disManCur_N, int startRow, int pageNum)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		String conditionStr = " 1=1";
		if(!createMan.equals("%")){
			conditionStr += " and cCreateMan like '" + createMan + "'";
		}
		if(!settleMan.equals("%")){
			conditionStr += " and cSettleMan like '" + settleMan + "'";
		}
		if(!disManCur_N.equals("%")){
			conditionStr += " and cDisManCur_n like '" + disManCur_N + "'";
		}
		
		List bills = new ArrayList();
		DefaultContext myCtx = getDefaultContext();
		BillIterator billIterator = null;
		int endRow = startRow + pageNum;
		//遍历查找指定开台人和指定结账人等的账单列表
		/*@lineinfo:generated-code*//*@lineinfo:192^3*/

//  ************************************************************
//  #sql [myCtx] billIterator = { select * from
//  					(select rownum rn, cBill_C, cPeriod_N, cShift_N, iGuestNum, 
//  					dtBillTime, dtSettleTime, DBUSINESS, cCreateMan, cSettleMan,
//  				    nFoodAmt, cTable_N, nRoundAmt, nPresentAmt, nOughtAmt, nPayAmt, 
//  				    cBranch_N, nDisAmt, cDisManCur_n, cDisCurWhy, sRemark 
//  					from :Com_("d_t_food_bill")
//  					where DBUSINESS >= to_date(:startTime, :strDateFormat)
//  					and DBUSINESS <= to_date(:endTime, :strDateFormat)
//  					and :conditionStr
//  					and rownum <= :endRow) 
//  				where rn > :startRow
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = startTime;
  String __sJT_3 = strDateFormat;
  String __sJT_4 = endTime;
  String __sJT_5 = strDateFormat;
  String __sJT_6 = conditionStr;
  int __sJT_7 = endRow;
  int __sJT_8 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
      __sJT_stmt.setInt(8, __sJT_8);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      billIterator = new BillIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:204^3*/
		while(billIterator.next())
		{
			Bill bill = processIter(billIterator);
			bills.add(bill);
		}
		billIterator.close();
		closeDefaultContext(myCtx);
		return bills;
	}

	//指定门店的指定付款类型的单据数量
	public int billCountByPay(String startTime, String endTime, String shopC, String payType)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		int billCount = 0;
		DefaultContext myCtx = getDefaultContext();
		/*@lineinfo:generated-code*//*@lineinfo:221^3*/

//  ************************************************************
//  #sql [myCtx] { select count(*)  
//  			from :Com_("d_t_food_bill") a,:Com_("d_t_bill_pay") b
//  			where 
//  				a.DBUSINESS >= to_date(:startTime, :strDateFormat)
//  			and a.DBUSINESS <= to_date(:endTime, :strDateFormat)
//  			and a.cBill_C = b.cBill_C and a.cBranch_C = :shopC and b.cPay_C = :payType
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = startTime;
  String __sJT_4 = strDateFormat;
  String __sJT_5 = endTime;
  String __sJT_6 = strDateFormat;
  String __sJT_7 = shopC;
  String __sJT_8 = payType;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 5);
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
    billCount = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:228^3*/
		closeDefaultContext(myCtx);
		return billCount;
	}
	
	//指定门店的指定付款类型的单据列表
	public List listBillByPay(String startTime, String endTime, String shopC, String payType, int startRow, int pageNum)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		List bills = new ArrayList();
		DefaultContext myCtx = getDefaultContext();
		BillIterator billIterator = null;
		int endRow = startRow + pageNum;
		//遍历查找指定门店的指定台位的单据列表
		/*@lineinfo:generated-code*//*@lineinfo:242^3*/

//  ************************************************************
//  #sql [myCtx] billIterator = { select * from 
//  									(select rownum rn, a.cBill_C, a.cPeriod_N, a.cShift_N, a.iGuestNum, 
//  									a.dtBillTime, dtSettleTime, a.DBUSINESS, a.cCreateMan, a.cSettleMan,
//  									a.nFoodAmt, a.cTable_N,a.nRoundAmt, a.nPresentAmt, a.nOughtAmt, a.nPayAmt,
//  									a.cBranch_N, a.nDisAmt, a.cDisManCur_n, a.cDisCurWhy, a.sRemark 
//  									from :Com_("d_t_food_bill") a,:Com_("d_t_bill_pay") b
//  									where a.DBUSINESS >= to_date(:startTime, :strDateFormat)
//  									and a.DBUSINESS <= to_date(:endTime, :strDateFormat)
//  									and a.cBill_C = b.cBill_C and a.cBranch_C = :shopC and b.cPay_C = :payType
//  									and rownum <= :endRow) where rn > :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_bill_pay");
  String __sJT_3 = startTime;
  String __sJT_4 = strDateFormat;
  String __sJT_5 = endTime;
  String __sJT_6 = strDateFormat;
  String __sJT_7 = shopC;
  String __sJT_8 = payType;
  int __sJT_9 = endRow;
  int __sJT_10 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 6);
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
      __sJT_stmt.setInt(9, __sJT_9);
      __sJT_stmt.setInt(10, __sJT_10);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      billIterator = new BillIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:251^53*/
		while(billIterator.next())
		{
			Bill bill = processIter(billIterator);
			bills.add(bill);
		}
		billIterator.close();
		closeDefaultContext(myCtx);
		return bills;
	}

	//根据指定的单据号查询指定的单据信息
	public Bill listBillById(String id)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		DefaultContext myCtx = getDefaultContext();
		BillIterator billIterator = null;
		Bill bill = new Bill();
		
		/*@lineinfo:generated-code*//*@lineinfo:270^3*/

//  ************************************************************
//  #sql [myCtx] billIterator = { select rownum rn, cBill_C, cPeriod_N, cShift_N, iGuestNum, 
//  				dtBillTime, dtSettleTime, DBUSINESS, cCreateMan, cSettleMan,
//  				nFoodAmt, cTable_N,	nRoundAmt, nPresentAmt, nOughtAmt, nPayAmt,
//  				cBranch_N,nDisAmt, cDisManCur_n, cDisCurWhy, sRemark 
//  				from :Com_("d_t_food_bill")
//  				where cBill_C = :id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = id;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      billIterator = new BillIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:276^24*/
		while(billIterator.next())
		{
			bill = processIter(billIterator);
		}
		billIterator.close();
		closeDefaultContext(myCtx);
		return bill;
	}
	
	
	//指定门店的指定台位的单据数量
		public int listBillBylikeCodeCount(String startDate,String endDate,String code ,String shopC)
			throws NoPrivilegeException,SQLException,NoConnection
		{
			int billCount = 0;
			DefaultContext myCtx = getDefaultContext();
			/*@lineinfo:generated-code*//*@lineinfo:293^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				 count(*) 
//  				  
//  				 FROM
//  				     :Com_("d_t_food_bill") d
//  				 WHERE
//  				     d.CBRANCH_C LIKE :shopC
//  				 AND d.cBill_C LIKE :code
//  				 AND d.DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  				 AND d.DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd') };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopC;
  String __sJT_3 = code;
  String __sJT_4 = startDate;
  String __sJT_5 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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
    billCount = __sJT_rtRs.getIntNoNull(1);
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

/*@lineinfo:user-code*//*@lineinfo:302^56*/
			closeDefaultContext(myCtx);
			return billCount;
		}
	
		public List<Bill> listBillBylikeCode(String startDate,String endDate,String code ,String shopC,int startRow,int pageNum)
			throws NoPrivilegeException,SQLException,NoConnection
		{
			List<Bill> bills = new ArrayList<Bill>();
			DefaultContext myCtx = getDefaultContext();
			BillIterator billIterator = null;
			int endRow = startRow + pageNum;
			/*@lineinfo:generated-code*//*@lineinfo:314^4*/

//  ************************************************************
//  #sql [myCtx] billIterator = { SELECT
//  				    *
//  				    FROM
//  				        (
//  				            SELECT
//  				                t.*,
//  				                ROWNUM RN
//  				            FROM
//  				                (
//  				                    SELECT
//  				                        cBill_C,
//  				                        dBusiness,
//  				                        cPeriod_N,
//  				                        cShift_N,
//  				                        iGuestNum,
//  				                        dtBillTime,
//  				                        dtSettleTime,
//  				                        cCreateMan,
//  				                        cSettleMan,
//  				                        nFoodAmt,
//  				                        cTable_N,
//  				                        nRoundAmt,
//  				                        nPresentAmt,
//  				                        nOughtAmt,
//  				                        nPayAmt,
//  				                        cBranch_N,
//  				                        nDisAmt,
//  				                        cDisManCur_n,
//  				                        cDisCurWhy,
//  				                        sRemark
//  				                    FROM
//  				                        :Com_("d_t_food_bill") d
//  				                    WHERE
//  				                    	d.CBRANCH_C LIKE :shopC
//  				                    AND d.cBill_C LIKE :code
//  				                    AND d.DBUSINESS >= to_date(:startDate, 'yyyy-mm-dd')
//  				                    AND d.DBUSINESS <= to_date(:endDate, 'yyyy-mm-dd')
//  				                    ORDER BY d.DBUSINESS DESC, d.dtBillTime DESC)t
//  				            WHERE
//  				                ROWNUM <= :endRow)
//  				    WHERE
//  				        RN >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopC;
  String __sJT_3 = code;
  String __sJT_4 = startDate;
  String __sJT_5 = endDate;
  int __sJT_6 = endRow;
  int __sJT_7 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      __sJT_stmt.setInt(7, __sJT_7);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      billIterator = new BillIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:355^28*/
			while(billIterator.next())
			{
				Bill bill = processIter(billIterator);
				bills.add(bill);
			}
			billIterator.close();
			closeDefaultContext(myCtx);
			return bills;
		}
		
		private Bill processIter(BillIterator billIterator) throws SQLException {
			Bill bill = new Bill();
			bill.dBusiness = billIterator.DBUSINESS();
			bill.billC = billIterator.cBill_C();
			bill.period = billIterator.cPeriod_N();
			bill.shift = ConvertAsciii0ToSpace(billIterator.cShift_N());
			bill.guestNum = billIterator.iGuestNum();
			bill.billTime = billIterator.dtBillTime();
			bill.settleTime = billIterator.dtSettleTime();
			bill.shopName = billIterator.cBranch_N();
			bill.table = billIterator.cTable_N();
			bill.createMan = ConvertAsciii0ToSpace(billIterator.cCreateMan());
			bill.settleMan = ConvertAsciii0ToSpace(billIterator.cSettleMan());
			bill.foodAmt = billIterator.nFoodAmt();
			bill.roundAmt = billIterator.nRoundAmt();
			bill.presentAmt = billIterator.nPresentAmt();
			bill.oughtAmt = billIterator.nOughtAmt();
			bill.payAmt = billIterator.nPayAmt();
			bill.disAmt = billIterator.nDisAmt();
			bill.disCurMan =  ConvertAsciii0ToSpace(billIterator.cDisManCur_n());
			bill.disCurWhy = ConvertAsciii0ToSpace(billIterator.cDisCurWhy());
			bill.remark = ConvertAsciii0ToSpace(billIterator.sRemark());
			return bill;
		}
}/*@lineinfo:generated-code*/class BillBean_SJProfileKeys 
{
  private static BillBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BillBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BillBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.BillBean_SJProfile0");
  }
}
