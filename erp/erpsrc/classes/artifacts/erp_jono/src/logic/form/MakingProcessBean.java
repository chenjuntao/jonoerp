/*@lineinfo:filename=MakingProcessBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sat Nov 15 11:33:05 CST 2014 by pw
 * Last edited on Sat Nov 15 11:33:05 CST 2014 by pw
 * 
 * comment: 半成品制程表
 */
package logic.form;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.MakingProcess;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class MakingProcessBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(MakingProcessBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:36^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ProcessIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ProcessIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    stepNdx = findColumn("step");
    step_operationNdx = findColumn("step_operation");
    step_noteNdx = findColumn("step_note");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Integer step() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(stepNdx);
  }
  private int stepNdx;
  public String step_operation() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(step_operationNdx);
  }
  private int step_operationNdx;
  public String step_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(step_noteNdx);
  }
  private int step_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:36^105*/
	
	public int saveEntity(MakingProcess process)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String itemId = process.getItemId();
		Integer step = process.getStep();
		String stepOperation = process.getStepOperation();
		String stepNote = process.getStepNote();

		/*@lineinfo:generated-code*//*@lineinfo:47^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_MAKING_PROCESS") 
//  				(ITEM_ID, STEP, STEP_OPERATION, STEP_NOTE) 
//  			VALUES (:itemId, :step, :stepOperation, :stepNote)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MAKING_PROCESS");
  String __sJT_2 = itemId;
  Integer __sJT_3 = step;
  String __sJT_4 = stepOperation;
  String __sJT_5 = stepNote;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MakingProcessBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setIntWrapper(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:51^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	public int updateEntity(String oldStep, MakingProcess process)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String itemId = process.getItemId();
		Integer step = process.getStep();
		String stepOperation = process.getStepOperation();
		String stepNote = process.getStepNote();

		/*@lineinfo:generated-code*//*@lineinfo:66^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_MAKING_PROCESS")
//  			SET
//  				STEP = :step,
//  	    		STEP_OPERATION = :stepOperation,
//  	    		STEP_NOTE = :stepNote
//  			WHERE 
//  				ITEM_ID = :itemId
//  			AND STEP = :oldStep
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MAKING_PROCESS");
  Integer __sJT_2 = step;
  String __sJT_3 = stepOperation;
  String __sJT_4 = stepNote;
  String __sJT_5 = itemId;
  String __sJT_6 = oldStep;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MakingProcessBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setIntWrapper(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:76^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int delete(String itemId, String step)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:86^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  			:Com_("D_T2_MAKING_PROCESS")
//  			WHERE
//  				ITEM_ID = :itemId
//  			AND STEP = :step
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MAKING_PROCESS");
  String __sJT_2 = itemId;
  String __sJT_3 = step;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MakingProcessBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:93^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}

	/**
	 * 创建生产工单时获取工序信息
	 */
	public List<MakingProcess> queryDetail(String workOrderId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ProcessIter processIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:106^3*/

//  ************************************************************
//  #sql [myCtx] processIter = { SELECT
//  			    	p.*
//  			    FROM
//  			    :Com_("D_T1_ARRENGMENT_DETAIL") d
//  			    LEFT JOIN
//  			    :Com_("D_T2_MAKING_PROCESS") p
//  			    ON
//  			        d.ITEM_ID = p.ITEM_ID
//  			    WHERE
//  			        d.WORK_ORDER_ID = :workOrderId
//  			    ORDER BY
//  			        p.STEP };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T1_ARRENGMENT_DETAIL");
  String __sJT_2 = Com_("D_T2_MAKING_PROCESS");
  String __sJT_3 = workOrderId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MakingProcessBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      processIter = new ProcessIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:118^18*/
		List<MakingProcess> processLst = processIter(processIter);
		processIter.close();
		closeDefaultContext(myCtx);
		return processLst;
	}

	public List<MakingProcess> queryByItem(String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ProcessIter processIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:129^3*/

//  ************************************************************
//  #sql [myCtx] processIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_MAKING_PROCESS") p
//  			WHERE
//  				p.ITEM_ID = :itemId 
//  			ORDER BY
//  		        p.STEP
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MAKING_PROCESS");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MakingProcessBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      processIter = new ProcessIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:138^3*/
		List<MakingProcess> processLst = processIter(processIter);
		processIter.close();
		closeDefaultContext(myCtx);
		return processLst;
	}

	public MakingProcess query(String itemId, String step) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ProcessIter processIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:149^3*/

//  ************************************************************
//  #sql [myCtx] processIter = { SELECT
//  			    *
//  			FROM
//  			:Com_("D_T2_MAKING_PROCESS") p
//  			WHERE
//  				p.ITEM_ID = :itemId 
//  			AND p.STEP = :step
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_MAKING_PROCESS");
  String __sJT_2 = itemId;
  String __sJT_3 = step;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, MakingProcessBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      processIter = new ProcessIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:157^3*/
		List<MakingProcess> processLst = processIter(processIter);
		processIter.close();
		closeDefaultContext(myCtx);
		
		if (processLst !=null && !processLst.isEmpty()) {
			return processLst.get(0);
		}
		return null;
	}

	private List<MakingProcess> processIter(ProcessIter processIter) 
			throws SQLException {
		List<MakingProcess> processLst = new ArrayList<MakingProcess>();
		while(processIter.next()) {
			MakingProcess process = new MakingProcess();
			process.setItemId(processIter.item_id());
			process.setStep(processIter.step());
			process.setStepOperation(processIter.step_operation());
			process.setStepNote(processIter.step_note());
			processLst.add(process);
		}
		return processLst;
	}
}/*@lineinfo:generated-code*/class MakingProcessBean_SJProfileKeys 
{
  private static MakingProcessBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new MakingProcessBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private MakingProcessBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.MakingProcessBean_SJProfile0");
  }
}
