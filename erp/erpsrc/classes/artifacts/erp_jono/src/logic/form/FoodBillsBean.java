/*@lineinfo:filename=FoodBillsBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.FoodBills;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class FoodBillsBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(FoodBillsBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:23^2*/

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
    cbill_cNdx = findColumn("cbill_c");
    cfood_cNdx = findColumn("cfood_c");
    cfood_nNdx = findColumn("cfood_n");
    sunitNdx = findColumn("sunit");
    nprcNdx = findColumn("nprc");
    nqtyNdx = findColumn("nqty");
    namtNdx = findColumn("namt");
    esuitflagNdx = findColumn("esuitflag");
    csuitbillNdx = findColumn("csuitbill");
    eretsendflagNdx = findColumn("eretsendflag");
    sretsendremarkNdx = findColumn("sretsendremark");
    ndisamtNdx = findColumn("ndisamt");
    csuitnameNdx = findColumn("csuitname");
    clitcls_cNdx = findColumn("clitcls_c");
    clitcls_nNdx = findColumn("clitcls_n");
    cPresentBackManNdx = findColumn("cPresentBackMan");
  }
  public String cbill_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cbill_cNdx);
  }
  private int cbill_cNdx;
  public String cfood_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cfood_cNdx);
  }
  private int cfood_cNdx;
  public String cfood_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cfood_nNdx);
  }
  private int cfood_nNdx;
  public String sunit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sunitNdx);
  }
  private int sunitNdx;
  public Double nprc() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nprcNdx);
  }
  private int nprcNdx;
  public Double nqty() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nqtyNdx);
  }
  private int nqtyNdx;
  public Double namt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(namtNdx);
  }
  private int namtNdx;
  public String esuitflag() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(esuitflagNdx);
  }
  private int esuitflagNdx;
  public String csuitbill() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(csuitbillNdx);
  }
  private int csuitbillNdx;
  public String eretsendflag() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(eretsendflagNdx);
  }
  private int eretsendflagNdx;
  public String sretsendremark() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sretsendremarkNdx);
  }
  private int sretsendremarkNdx;
  public Double ndisamt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(ndisamtNdx);
  }
  private int ndisamtNdx;
  public String csuitname() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(csuitnameNdx);
  }
  private int csuitnameNdx;
  public String clitcls_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(clitcls_cNdx);
  }
  private int clitcls_cNdx;
  public String clitcls_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(clitcls_nNdx);
  }
  private int clitcls_nNdx;
  public String cPresentBackMan() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPresentBackManNdx);
  }
  private int cPresentBackManNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:39^26*/
	
	/**
	 * companyId由调用者通过参数指定,by cjt
	 */
	public int saveEntity(FoodBills header, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);

		String cbillC = header.getCbillC();
		String cfoodC = header.getCfoodC();
		String cfoodN = header.getCfoodN();
		String sunit = header.getSunit();
		Double nprc = header.getNprc();
		Double nqty = header.getNqty();
		Double namt = header.getNamt();
		String esuitflag = header.getEsuitflag();
		String csuitbill = header.getCsuitbill();
		String eretsendflag = header.getEretsendflag();
		String sretsendremark = header.getSretsendremark();
		Double ndisamt = header.getNdisamt();
		String csuitname = header.getCsuitname();
		String clitclsC = header.getClitclsC();
		String clitclsN = header.getClitclsN();
		String cpresentbackman = header.getPresentBackMan();

		String comTb = comId + ".d_t_food_bills";
		
		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :comTb
//  				(cBill_c, cFood_c, cFood_n, sUnit, nPrc, nQty, nAmt,
//  						eSuitFlag, cSuitBill, eRetSendFlag, sRetSendReMark,
//  						nDisAmt, cSuitName, cLitCls_c, clitCls_n, cPresentBackMan) 
//  			VALUES (:cbillC, :cfoodC, :cfoodN, :sunit, :nprc, :nqty, :namt,
//  					:esuitflag, :csuitbill, :eretsendflag, :sretsendremark,
//  					:ndisamt, :csuitname, :clitclsC, :clitclsN, :cpresentbackman)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = comTb;
  String __sJT_2 = cbillC;
  String __sJT_3 = cfoodC;
  String __sJT_4 = cfoodN;
  String __sJT_5 = sunit;
  Double __sJT_6 = nprc;
  Double __sJT_7 = nqty;
  Double __sJT_8 = namt;
  String __sJT_9 = esuitflag;
  String __sJT_10 = csuitbill;
  String __sJT_11 = eretsendflag;
  String __sJT_12 = sretsendremark;
  Double __sJT_13 = ndisamt;
  String __sJT_14 = csuitname;
  String __sJT_15 = clitclsC;
  String __sJT_16 = clitclsN;
  String __sJT_17 = cpresentbackman;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillsBean_SJProfileKeys.getKey(0), 0);
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
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setDoubleWrapper(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:75^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteEntity(java.util.Date startDate, java.util.Date endDate, String branchId)
		throws NoPrivilegeException, SQLException, NoConnection {
        DefaultContext myCtx = getDefaultContext();
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		/*@lineinfo:generated-code*//*@lineinfo:88^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM
//  			(
//  					SELECT
//  				    h.*
//  				FROM
//  				:Com_("d_t_food_bills") h
//  				LEFT JOIN
//  				:Com_("d_t_food_bill") s
//  				ON
//  				    s.CBILL_C = h.CBILL_C
//  				WHERE
//  				 s.DBUSINESS >= :sDate 
//  				AND s.DBUSINESS <= :eDate
//  				AND s.CBRANCH_C = :branchId
//  				)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillsBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:104^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

  public int count(java.util.Date startDate, java.util.Date endDate, String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {

		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			  count(*) 
//  			FROM
//  			:Com_("d_t_food_bills") h
//  			LEFT JOIN
//  			:Com_("d_t_food_bill") s
//  			ON
//  			    s.CBILL_C = h.CBILL_C
//  			WHERE
//  			s.DBUSINESS >= :sDate
//  			AND s.DBUSINESS <= :eDate
//  			AND s.CBRANCH_C = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillsBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:131^3*/
		closeDefaultContext(myCtx);
		return total;
	}
	
	public List<FoodBills> query(java.util.Date startDate, java.util.Date endDate, String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
//		String query = formQuery(branchId, queryType);
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:144^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			h.cBill_c, h.cFood_c, h.cFood_n, h.sUnit, h.nPrc, h.nQty, h.nAmt,
//  			h.eSuitFlag, h.cSuitBill, h.eRetSendFlag, h.sRetSendReMark,
//  			 h.nDisAmt, h.cSuitName, h.cLitCls_c, h.clitCls_n,h.cPresentBackMan,
//  			    s.DBUSINESS,
//  				s.CBRANCH_C,
//  				s.CBRANCH_N
//  			FROM
//  			:Com_("d_t_food_bills") h
//  			LEFT JOIN
//  			:Com_("d_t_food_bill") s
//  			ON
//  			    s.CBILL_C = h.CBILL_C
//  			WHERE
//  			 s.DBUSINESS >= :sDate 
//  			AND s.DBUSINESS <= :eDate
//  			AND s.CBRANCH_C = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBillsBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setDate(3, __sJT_3);
      __sJT_stmt.setDate(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
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

/*@lineinfo:user-code*//*@lineinfo:162^3*/
		List<FoodBills> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}

	private List<FoodBills> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<FoodBills> headerLst = new ArrayList<FoodBills>();
		while(headerIter.next()) {
			FoodBills header = new FoodBills();
			header.setCbillC(headerIter.cbill_c());
			header.setCfoodC(headerIter.cfood_c());
			header.setCfoodN(headerIter.cfood_n());
			header.setSunit(headerIter.sunit());
			header.setNprc(headerIter.nprc());
			header.setNqty(headerIter.nqty());
			header.setNamt(headerIter.namt());
			header.setEsuitflag(headerIter.esuitflag());
			header.setCsuitbill(headerIter.csuitbill());
			header.setEretsendflag(headerIter.eretsendflag());
			header.setSretsendremark(headerIter.sretsendremark());
			header.setNdisamt(headerIter.ndisamt());
			header.setCsuitname(headerIter.csuitname());
			header.setClitclsC(headerIter.clitcls_c());
			header.setClitclsN(headerIter.clitcls_n());
			header.setPresentBackMan(headerIter.cPresentBackMan());		
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class FoodBillsBean_SJProfileKeys 
{
  private static FoodBillsBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodBillsBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodBillsBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.FoodBillsBean_SJProfile0");
  }
}
