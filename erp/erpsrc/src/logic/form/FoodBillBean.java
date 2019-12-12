/*@lineinfo:filename=FoodBillBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Jul 22 13:05:34 CST 2015 by pw
 * Last edited on Wed Jul 22 13:05:34 CST 2015 by pw
 * 
 * comment: 单据明细表
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.FoodBill;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class FoodBillBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(FoodBillBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

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
    cbill_cNdx = findColumn("cbill_c");
    dbusinessNdx = findColumn("dbusiness");
    ctable_nNdx = findColumn("ctable_n");
    cperiod_nNdx = findColumn("cperiod_n");
    cshift_nNdx = findColumn("cshift_n");
    iguestnumNdx = findColumn("iguestnum");
    dtbilltimeNdx = findColumn("dtbilltime");
    dtsettletimeNdx = findColumn("dtsettletime");
    nfoodamtNdx = findColumn("nfoodamt");
    ndisamtNdx = findColumn("ndisamt");
    nroundamtNdx = findColumn("nroundamt");
    noughtamtNdx = findColumn("noughtamt");
    ccreatemanNdx = findColumn("ccreateman");
    csettlemanNdx = findColumn("csettleman");
    sremarkNdx = findColumn("sremark");
    npayamtNdx = findColumn("npayamt");
    ssendmanNdx = findColumn("ssendman");
    cbranch_cNdx = findColumn("cbranch_c");
    cbranch_nNdx = findColumn("cbranch_n");
    cdismancur_nNdx = findColumn("cdismancur_n");
    npresentamtNdx = findColumn("npresentamt");
    cdiscurwhyNdx = findColumn("cdiscurwhy");
  }
  public String cbill_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cbill_cNdx);
  }
  private int cbill_cNdx;
  public Date dbusiness() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(dbusinessNdx);
  }
  private int dbusinessNdx;
  public String ctable_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ctable_nNdx);
  }
  private int ctable_nNdx;
  public String cperiod_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cperiod_nNdx);
  }
  private int cperiod_nNdx;
  public String cshift_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cshift_nNdx);
  }
  private int cshift_nNdx;
  public Integer iguestnum() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(iguestnumNdx);
  }
  private int iguestnumNdx;
  public Date dtbilltime() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(dtbilltimeNdx);
  }
  private int dtbilltimeNdx;
  public Date dtsettletime() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(dtsettletimeNdx);
  }
  private int dtsettletimeNdx;
  public Double nfoodamt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nfoodamtNdx);
  }
  private int nfoodamtNdx;
  public Double ndisamt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(ndisamtNdx);
  }
  private int ndisamtNdx;
  public Double nroundamt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nroundamtNdx);
  }
  private int nroundamtNdx;
  public Double noughtamt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(noughtamtNdx);
  }
  private int noughtamtNdx;
  public String ccreateman() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ccreatemanNdx);
  }
  private int ccreatemanNdx;
  public String csettleman() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(csettlemanNdx);
  }
  private int csettlemanNdx;
  public String sremark() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sremarkNdx);
  }
  private int sremarkNdx;
  public Double npayamt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(npayamtNdx);
  }
  private int npayamtNdx;
  public String ssendman() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ssendmanNdx);
  }
  private int ssendmanNdx;
  public String cbranch_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cbranch_cNdx);
  }
  private int cbranch_cNdx;
  public String cbranch_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cbranch_nNdx);
  }
  private int cbranch_nNdx;
  public String cdismancur_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cdismancur_nNdx);
  }
  private int cdismancur_nNdx;
  public Double npresentamt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(npresentamtNdx);
  }
  private int npresentamtNdx;
  public String cdiscurwhy() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cdiscurwhyNdx);
  }
  private int cdiscurwhyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:57^21*/
	
	/**
	 * companyId由调用者通过参数指定,by cjt
	 */
	public int saveEntity(FoodBill detail, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		
		DefaultContext myCtx = getComContext(comId);
		
		String cbillC = detail.getCbillC();
		Date dbusiness = SqlDateUtil.getSqlDate(detail.getDbusiness());
		String ctable = detail.getCtable();
		String cperiod = detail.getCperiod();
		String cshift = detail.getCshift();
		Integer iguestnum = detail.getIguestnum();
		Date dtbilltime = SqlDateUtil.getSqlDate(detail.getDtbilltime());
		Date dtsettletime = SqlDateUtil.getSqlDate(detail.getDtsettletime());
		Double nfoodamt = detail.getNfoodamt();
		Double ndisamt = detail.getNdisamt();
		Double nroundamt = detail.getNroundamt();
		Double noughtamt = detail.getNoughtamt();
		String ccreateman = detail.getCcreateman();
		String csettleman = detail.getCsettleman();
		String sremark = detail.getSremark();
		Double npayamt = detail.getNpayamt();
		String ssendman = detail.getSsendman();
		String cbranchC = detail.getCbranchC();
		String cbranchN = detail.getCbranchN();
		String cdismancurN = detail.getCdismancurN();
		Double npresentamt = detail.getNpresentamt();
		String cdiscurwhy = detail.getCdiscurwhy();

		String comTb = comId + ".d_t_food_bill";
		
		/*@lineinfo:generated-code*//*@lineinfo:92^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :comTb
//  				(CBILL_C, DBUSINESS, CTABLE_N, CPERIOD_N, CSHIFT_N, 
//  						IGUESTNUM, DTBILLTIME, DTSETTLETIME, NFOODAMT,
//  						NDISAMT, NROUNDAMT,	NOUGHTAMT, CCREATEMAN,
//  						CSETTLEMAN,	SREMARK, NPAYAMT, SSENDMAN, CBRANCH_C, 
//  						CBRANCH_N, CDISMANCUR_N,NPRESENTAMT, CDISCURWHY) 
//  			VALUES (:cbillC, :dbusiness, :ctable, :cperiod, :cshift, 
//  					:iguestnum, :dtbilltime, :dtsettletime, :nfoodamt,
//  					:ndisamt, :nroundamt, :noughtamt, :ccreateman, 
//  					:csettleman, :sremark, :npayamt, :ssendman, :cbranchC, 
//  					:cbranchN, :cdismancurN, :npresentamt, :cdiscurwhy)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = comTb;
  String __sJT_2 = cbillC;
  Date __sJT_3 = dbusiness;
  String __sJT_4 = ctable;
  String __sJT_5 = cperiod;
  String __sJT_6 = cshift;
  Integer __sJT_7 = iguestnum;
  Date __sJT_8 = dtbilltime;
  Date __sJT_9 = dtsettletime;
  Double __sJT_10 = nfoodamt;
  Double __sJT_11 = ndisamt;
  Double __sJT_12 = nroundamt;
  Double __sJT_13 = noughtamt;
  String __sJT_14 = ccreateman;
  String __sJT_15 = csettleman;
  String __sJT_16 = sremark;
  Double __sJT_17 = npayamt;
  String __sJT_18 = ssendman;
  String __sJT_19 = cbranchC;
  String __sJT_20 = cbranchN;
  String __sJT_21 = cdismancurN;
  Double __sJT_22 = npresentamt;
  String __sJT_23 = cdiscurwhy;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setIntWrapper(7, __sJT_7);
      __sJT_stmt.setDate(8, __sJT_8);
      __sJT_stmt.setDate(9, __sJT_9);
      __sJT_stmt.setDoubleWrapper(10, __sJT_10);
      __sJT_stmt.setDoubleWrapper(11, __sJT_11);
      __sJT_stmt.setDoubleWrapper(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setDoubleWrapper(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setDoubleWrapper(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:104^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int deleteEntity(java.util.Date startDate, java.util.Date endDate, String branchId)
			throws NoPrivilegeException, SQLException, NoConnection {
	        DefaultContext myCtx = getDefaultContext();
			
			Date sDate = SqlDateUtil.getSqlDate(startDate);
			Date eDate = SqlDateUtil.getSqlDate(endDate);

			/*@lineinfo:generated-code*//*@lineinfo:117^4*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM
//  				(
//  					SELECT
//  					    *
//  					FROM
//  					:Com_("d_t_food_bill") s
//  					WHERE
//  					 s.DBUSINESS >= :sDate 
//  					AND s.DBUSINESS <= :eDate
//  					AND s.CBRANCH_C = :branchId
//  					)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  Date __sJT_2 = sDate;
  Date __sJT_3 = eDate;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:129^4*/
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	 public int count(java.util.Date startDate, java.util.Date endDate, String branchId)
				throws NoPrivilegeException,SQLException,NoConnection {
			Date sDate = SqlDateUtil.getSqlDate(startDate);
			Date eDate = SqlDateUtil.getSqlDate(endDate);
			
			DefaultContext myCtx = getDefaultContext();
			int total = 0;
			/*@lineinfo:generated-code*//*@lineinfo:142^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				  count(*) 
//  				FROM
//  				:Com_("d_t_food_bill") s
//  				WHERE
//  				s.DBUSINESS >= :sDate
//  				AND s.DBUSINESS <= :eDate
//  				AND s.CBRANCH_C = :branchId
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  Date __sJT_2 = sDate;
  Date __sJT_3 = eDate;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:151^4*/
			closeDefaultContext(myCtx);
			return total;
		}	
	 
	 /*@lineinfo:generated-code*//*@lineinfo:156^3*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class DateIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public DateIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    dbusinessNdx = findColumn("dbusiness");
  }
  public Date dbusiness() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(dbusinessNdx);
  }
  private int dbusinessNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:156^48*/
	 public Date queryDate(java.util.Date startDate, java.util.Date endDate, String branchId)
			 throws NoPrivilegeException,SQLException,NoConnection {
			Date sDate = SqlDateUtil.getSqlDate(startDate);
			Date eDate = SqlDateUtil.getSqlDate(endDate);
			
			DefaultContext myCtx = getDefaultContext();
			DateIter dateIter = null;
			/*@lineinfo:generated-code*//*@lineinfo:164^4*/

//  ************************************************************
//  #sql [myCtx] dateIter = { SELECT
//  				  s.DBUSINESS
//  				FROM
//  				:Com_("d_t_food_bill") s
//  				WHERE
//  				s.DBUSINESS >= :sDate
//  				AND s.DBUSINESS <= :eDate
//  				AND s.CBRANCH_C = :branchId
//  				GROUP BY
//  			    s.DBUSINESS
//  			ORDER BY
//  			    s.DBUSINESS
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  Date __sJT_2 = sDate;
  Date __sJT_3 = eDate;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      dateIter = new DateIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:177^4*/
			List<Date> headerLst = new ArrayList<Date>();
			while(dateIter.next()) {
				headerLst.add(dateIter.dbusiness());
			}
			dateIter.close();
			closeDefaultContext(myCtx);
			if (headerLst !=null && !headerLst.isEmpty()) {
				return headerLst.get(0);
			}
			return eDate;
		}
	 
	public List<FoodBill> query(java.util.Date startDate, java.util.Date endDate, String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:197^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			CBILL_C, DBUSINESS, CTABLE_N, CPERIOD_N, CSHIFT_N, 
//  			IGUESTNUM, DTBILLTIME, DTSETTLETIME, NFOODAMT, NDISAMT, NROUNDAMT,
//  			NOUGHTAMT, CCREATEMAN, CSETTLEMAN, SREMARK, NPAYAMT, SSENDMAN, CBRANCH_C, 
//  			CBRANCH_N, CDISMANCUR_N, NPRESENTAMT, CDISCURWHY
//  			FROM
//  				:Com_("d_t_food_bill") s
//  			WHERE
//  			 	s.DBUSINESS >= :sDate 
//  			AND s.DBUSINESS <= :eDate
//  			AND s.CBRANCH_C = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  Date __sJT_2 = sDate;
  Date __sJT_3 = eDate;
  String __sJT_4 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setDate(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:209^3*/
		List<FoodBill> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	
	private List<FoodBill> processIter(DetailIter detailIter) 
			throws SQLException {
		List<FoodBill> detailLst = new ArrayList<FoodBill>();
		while(detailIter.next()) {
			FoodBill detail = new FoodBill();
			detail.setCbillC(detailIter.cbill_c());
			detail.setDbusiness(SqlDateUtil.getUtilDate(detailIter.dbusiness()));
			detail.setCtable(detailIter.ctable_n());
			detail.setCperiod(detailIter.cperiod_n());
			detail.setCshift(detailIter.cshift_n());
			detail.setIguestnum(detailIter.iguestnum());
			detail.setDtbilltime(SqlDateUtil.getUtilDate(detailIter.dtbilltime()));
			detail.setDtsettletime(SqlDateUtil.getUtilDate(detailIter.dtsettletime()));
			detail.setNfoodamt(detailIter.nfoodamt());
			detail.setNdisamt(detailIter.ndisamt());
			detail.setNroundamt(detailIter.nroundamt());
			detail.setNoughtamt(detailIter.noughtamt());
			detail.setCcreateman(detailIter.ccreateman());
			detail.setCsettleman(detailIter.csettleman());
			detail.setSremark(detailIter.sremark());
			detail.setNpayamt(detailIter.npayamt());
			detail.setSsendman(detailIter.ssendman());
			detail.setCbranchC(detailIter.cbranch_c());
			detail.setCbranchN(detailIter.cbranch_n());
			detail.setCdismancurN(detailIter.cdismancur_n());
			detail.setNpresentamt(detailIter.npresentamt());
			detail.setCdiscurwhy(detailIter.cdiscurwhy());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class FoodBillBean_SJProfileKeys 
{
  private static FoodBillBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodBillBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodBillBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.FoodBillBean_SJProfile0");
  }
}
