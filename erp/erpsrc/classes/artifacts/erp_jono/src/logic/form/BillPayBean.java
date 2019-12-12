/*@lineinfo:filename=BillPayBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.BillPay;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class BillPayBean extends ConnectionPool {

	public static Logger log = Logger.getLogger("logic.form.BillPayBean");
	
	/*@lineinfo:generated-code*//*@lineinfo:24^2*/

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
    cpay_cNdx = findColumn("cpay_c");
    cpay_nNdx = findColumn("cpay_n");
    sunitNdx = findColumn("sunit");
    npayamtNdx = findColumn("npayamt");
    cvip_cNdx = findColumn("cvip_c");
    cvip_nNdx = findColumn("cvip_n");
    carrear_cNdx = findColumn("carrear_c");
    carrear_nNdx = findColumn("carrear_n");
  }
  public String cbill_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cbill_cNdx);
  }
  private int cbill_cNdx;
  public String cpay_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cpay_cNdx);
  }
  private int cpay_cNdx;
  public String cpay_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cpay_nNdx);
  }
  private int cpay_nNdx;
  public String sunit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sunitNdx);
  }
  private int sunitNdx;
  public Double npayamt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(npayamtNdx);
  }
  private int npayamtNdx;
  public String cvip_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cvip_cNdx);
  }
  private int cvip_cNdx;
  public String cvip_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cvip_nNdx);
  }
  private int cvip_nNdx;
  public String carrear_c() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(carrear_cNdx);
  }
  private int carrear_cNdx;
  public String carrear_n() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(carrear_nNdx);
  }
  private int carrear_nNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:26^68*/
	
	/**
	 * companyId由调用者通过参数指定
	 */
	public int saveEntity(BillPay header, String comId)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);

		String cbillC = header.getCbillC();
		String cpayC = header.getCpayC();
		String cpayN = header.getCpayN();
		String sunit = header.getSunit();
		Double npayamt = header.getNpayamt();
		String cvipC = header.getCvipC();
		String cvipN = header.getCvipN();
		String carrearC = header.getCarrearC();
		String carrearN = header.getCarrearN();
		
		String comTb = comId + ".D_T_BILL_PAY";

		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :comTb 
//  				(cBill_c, cPay_c, cPay_n, sUnit, nPayAmt, cvip_c, cvip_n, cArrear_c, cArrear_n) 
//  			VALUES (:cbillC, :cpayC, :cpayN, :sunit, :npayamt, :cvipC, :cvipN, :carrearC, :carrearN)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = comTb;
  String __sJT_2 = cbillC;
  String __sJT_3 = cpayC;
  String __sJT_4 = cpayN;
  String __sJT_5 = sunit;
  Double __sJT_6 = npayamt;
  String __sJT_7 = cvipC;
  String __sJT_8 = cvipN;
  String __sJT_9 = carrearC;
  String __sJT_10 = carrearN;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillPayBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setDoubleWrapper(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
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

	public int deleteEntity(java.util.Date startDate, java.util.Date endDate, String branchId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);

		/*@lineinfo:generated-code*//*@lineinfo:63^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE FROM
//  		(
//  					SELECT
//  					p.*
//  				FROM
//  					:Com_("d_t_bill_pay") p
//  				INNER JOIN
//  					:Com_("d_t_food_bill") fb
//  				ON
//  					fb.CBILL_C = p.CBILL_C
//  				WHERE
//  					fb.DBUSINESS >= :sDate
//  				AND fb.DBUSINESS <= :eDate
//  				AND fb.CBRANCH_C = :branchId
//  		)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_bill_pay");
  String __sJT_2 = Com_("d_t_food_bill");
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillPayBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:79^3*/
		closeDefaultContext(myCtx);
		return 1;
	}

	public int count(java.util.Date startDate, java.util.Date endDate, String branchId)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		int total = 0;
		/*@lineinfo:generated-code*//*@lineinfo:92^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				count(*) 
//  			FROM
//  				:Com_("d_t_bill_pay") p
//  			INNER JOIN
//  				:Com_("d_t_food_bill") fb
//  			ON
//  				fb.CBILL_C = p.CBILL_C
//  			WHERE
//  				fb.DBUSINESS >= :sDate
//  			AND fb.DBUSINESS <= :eDate
//  			AND fb.CBRANCH_C = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_bill_pay");
  String __sJT_2 = Com_("d_t_food_bill");
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillPayBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:105^3*/
		closeDefaultContext(myCtx);
		return total;
	}

	public List<BillPay> query(java.util.Date startDate, java.util.Date endDate, String branchId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		
		Date sDate = SqlDateUtil.getSqlDate(startDate);
		Date eDate = SqlDateUtil.getSqlDate(endDate);
		
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:118^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			p.cBill_c, p.cPay_c, p.cPay_n, p.sUnit, p.nPayAmt, 
//  			p.cVIP_c, p.cVIP_n, p.cArrear_c, p.cArrear_n,
//  				fb.DBUSINESS,
//  				fb.CBRANCH_C,
//  				fb.CBRANCH_N
//  			FROM
//  				:Com_("d_t_bill_pay") p
//  			INNER JOIN
//  				:Com_("d_t_food_bill") fb
//  			ON
//  				fb.CBILL_C = p.CBILL_C
//  			WHERE
//  				fb.DBUSINESS >= :sDate
//  			AND fb.DBUSINESS <= :eDate
//  			AND fb.CBRANCH_C = :branchId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_bill_pay");
  String __sJT_2 = Com_("d_t_food_bill");
  Date __sJT_3 = sDate;
  Date __sJT_4 = eDate;
  String __sJT_5 = branchId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, BillPayBean_SJProfileKeys.getKey(0), 3);
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

/*@lineinfo:user-code*//*@lineinfo:135^3*/
		List<BillPay> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private List<BillPay> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<BillPay> headerLst = new ArrayList<BillPay>();
		while(headerIter.next()) {
			BillPay header = new BillPay();
			header.setCbillC(headerIter.cbill_c());
			header.setCpayC(headerIter.cpay_c());
			header.setCpayN(headerIter.cpay_n());
			header.setSunit(headerIter.sunit());
			header.setNpayamt(headerIter.npayamt());
			header.setCvipC(headerIter.cvip_c());
			header.setCvipN(headerIter.cvip_n());
			header.setCarrearC(headerIter.carrear_c());
			header.setCarrearN(headerIter.carrear_n());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class BillPayBean_SJProfileKeys 
{
  private static BillPayBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new BillPayBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private BillPayBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.BillPayBean_SJProfile0");
  }
}
