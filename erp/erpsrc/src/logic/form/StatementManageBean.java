/*@lineinfo:filename=StatementManageBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.StatementDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class StatementManageBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StatementManageBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:23^2*/

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
    sub_form_idNdx = findColumn("sub_form_id");
    all_pay_amtNdx = findColumn("all_pay_amt");
    form_operate_idNdx = findColumn("form_operate_id");
    form_operateNdx = findColumn("form_operate");
    form_operate_timeNdx = findColumn("form_operate_time");
    form_noteNdx = findColumn("form_note");
  }
  public String form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_idNdx);
  }
  private int form_idNdx;
  public String sub_form_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sub_form_idNdx);
  }
  private int sub_form_idNdx;
  public Double all_pay_amt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(all_pay_amtNdx);
  }
  private int all_pay_amtNdx;
  public String form_operate_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_operate_idNdx);
  }
  private int form_operate_idNdx;
  public String form_operate() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_operateNdx);
  }
  private int form_operateNdx;
  public Date form_operate_time() 
    throws java.sql.SQLException 
  {
    return resultSet.getDate(form_operate_timeNdx);
  }
  private int form_operate_timeNdx;
  public String form_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(form_noteNdx);
  }
  private int form_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:23^176*/
	
	public int saveEntity(StatementDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String formId = detail.getFormId();
		String subFormId = detail.getSubFormId();
		Double allPayAmt = detail.getAllPayAmt();
		String formOperateId = detail.getFormOperateId();
		String formOperate = detail.getFormOperate();
		Date formOperateTime = SqlDateUtil.getSqlDate(detail.getFormOperateTime());
		String formNote = detail.getFormNote();

		/*@lineinfo:generated-code*//*@lineinfo:37^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T1_STATEMENT_DETAIL") 
//  				(FORM_ID, SUB_FORM_ID, ALL_PAY_AMT, FORM_OPERATE_ID, FORM_OPERATE, FORM_OPERATE_TIME, FORM_NOTE) 
//  			VALUES (:formId, :subFormId, :allPayAmt, :formOperateId, :formOperate, :formOperateTime, :formNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_2 = formId;
  String __sJT_3 = subFormId;
  Double __sJT_4 = allPayAmt;
  String __sJT_5 = formOperateId;
  String __sJT_6 = formOperate;
  Date __sJT_7 = formOperateTime;
  String __sJT_8 = formNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementManageBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setDoubleWrapper(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setDate(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int deleteByForm(String formId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:51^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T1_STATEMENT_DETAIL")
//  			WHERE
//  				FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementManageBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:57^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<StatementDetail> query(String formId)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		DetailIter detailIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:67^3*/

//  ************************************************************
//  #sql [myCtx] detailIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T1_STATEMENT_DETAIL") d
//  			WHERE
//  			    d.FORM_ID = :formId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_STATEMENT_DETAIL");
  String __sJT_2 = formId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StatementManageBean_SJProfileKeys.getKey(0), 2);
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

/*@lineinfo:user-code*//*@lineinfo:74^3*/
		List<StatementDetail> detailLst = processIter(detailIter);
		detailIter.close();
		closeDefaultContext(myCtx);
		return detailLst;
	}

	private List<StatementDetail> processIter(DetailIter detailIter) 
			throws SQLException {
		List<StatementDetail> detailLst = new ArrayList<StatementDetail>();
		while(detailIter.next()) {
			StatementDetail detail = new StatementDetail();
			detail.setFormId(detailIter.form_id());
			detail.setSubFormId(detailIter.sub_form_id());
			detail.setAllPayAmt(detailIter.all_pay_amt());
			detail.setFormOperateId(detailIter.form_operate_id());
			detail.setFormOperate(detailIter.form_operate());
			detail.setFormOperateTime(SqlDateUtil.getUtilDate(detailIter.form_operate_time()));
			detail.setFormNote(detailIter.form_note());
			detailLst.add(detail);
		}
		return detailLst;
	}
}/*@lineinfo:generated-code*/class StatementManageBean_SJProfileKeys 
{
  private static StatementManageBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StatementManageBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StatementManageBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.StatementManageBean_SJProfile0");
  }
}
