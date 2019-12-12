/*@lineinfo:filename=ObservationDetailBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.form;

import java.sql.Date;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.form.ObservationDetail;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class ObservationDetailBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(ObservationDetailBean.class);
	
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
    item_idNdx = findColumn("item_id");
    part_idNdx = findColumn("part_id");
    process_idNdx = findColumn("process_id");
    processed_nameNdx = findColumn("processed_name");
    stepNdx = findColumn("step");
    step_pic_blobNdx = findColumn("step_pic_blob");
    step_remarkNdx = findColumn("step_remark");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String part_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(part_idNdx);
  }
  private int part_idNdx;
  public String process_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(process_idNdx);
  }
  private int process_idNdx;
  public String processed_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(processed_nameNdx);
  }
  private int processed_nameNdx;
  public int step() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(stepNdx);
  }
  private int stepNdx;
  public byte[] step_pic_blob() 
    throws java.sql.SQLException 
  {
    return resultSet.getBytes(step_pic_blobNdx);
  }
  private int step_pic_blobNdx;
  public String step_remark() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(step_remarkNdx);
  }
  private int step_remarkNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:32^3*/
	
	public int saveEntity(ObservationDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
	    return saveEntity(detail, myCtx);
	}
	
	public int saveEntity(ObservationDetail detail, DefaultContext myCtx )
		throws NoPrivilegeException, SQLException, NoConnection {
		
		String itemId = detail.getItemId();
		String partId = detail.getPartId();
		String processId = detail.getProcessId();
		String processedName = detail.getProcessedName();
		int step = detail.getStep();
		byte[] stepPicBlob = detail.getStepPicBlob();
		String stepRemark = detail.getStepRemark();

		/*@lineinfo:generated-code*//*@lineinfo:52^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_OBSERVATION_DETAIL")
//  				(ITEM_ID, PART_ID, PROCESS_ID, PROCESSED_NAME,STEP,STEP_PIC_BLOB,STEP_REMARK) 
//  			VALUES (:itemId, :partId, :processId, :processedName, :step,:stepPicBlob,:stepRemark)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = itemId;
  String __sJT_3 = partId;
  String __sJT_4 = processId;
  String __sJT_5 = processedName;
  int __sJT_6 = step;
  byte[] __sJT_7 = stepPicBlob;
  String __sJT_8 = stepRemark;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setInt(6, __sJT_6);
      __sJT_stmt.setBytes(7, __sJT_7);
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

/*@lineinfo:user-code*//*@lineinfo:56^3*/
		
		return 1;
	}
	

	public int deleteEntity(ObservationDetail detail)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String itemId = detail.getItemId();
		String processId = detail.getProcessId();
		int step = detail.getStep();
		
		/*@lineinfo:generated-code*//*@lineinfo:70^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE  FROM	:Com_("D_T2_OBSERVATION_DETAIL")
//  			WHERE
//  				ITEM_ID = :itemId AND PROCESS_ID = :processId AND STEP = :step
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = itemId;
  String __sJT_3 = processId;
  int __sJT_4 = step;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:74^3*/
		
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public int updateStep(ObservationDetail detail)
			throws NoPrivilegeException, SQLException, NoConnection {
			DefaultContext myCtx = getDefaultContext();

			String itemId = detail.getItemId();
			String processId = detail.getProcessId();
			int step = detail.getStep();
			byte[] stepPicBlob = detail.getStepPicBlob();
			String stepRemark = detail.getStepRemark();

			if(stepPicBlob == null){
				/*@lineinfo:generated-code*//*@lineinfo:92^5*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  					:Com_("D_T2_OBSERVATION_DETAIL")
//  					SET
//  						STEP_REMARK = :stepRemark
//  					WHERE 
//  						PROCESS_ID = :processId
//  					AND ITEM_ID = :itemId
//  					AND STEP = :step
//  				 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = stepRemark;
  String __sJT_3 = processId;
  String __sJT_4 = itemId;
  int __sJT_5 = step;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:101^5*/
			}else{
				/*@lineinfo:generated-code*//*@lineinfo:103^5*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  					FROM
//  					:Com_("D_T2_OBSERVATION_DETAIL")
//  				WHERE
//  				    PROCESS_ID = :processId
//  				AND ITEM_ID = :itemId
//  				AND STEP = :step };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = processId;
  String __sJT_3 = itemId;
  int __sJT_4 = step;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:110^21*/
				
				saveEntity(detail, myCtx);
			}
			
			closeDefaultContext(myCtx);
			
			return 1;
		}
	
	public int updateStepByDelete(String itemId,String processId,int updateStep,int currentStep)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:124^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  			:Com_("D_T2_OBSERVATION_DETAIL")
//  			SET
//  				STEP = :updateStep
//  			WHERE 
//  				PROCESS_ID = :processId		AND ITEM_ID = :itemId AND STEP = :currentStep
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  int __sJT_2 = updateStep;
  String __sJT_3 = processId;
  String __sJT_4 = itemId;
  int __sJT_5 = currentStep;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setInt(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setInt(5, __sJT_5);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:131^3*/
		
		closeDefaultContext(myCtx);
		return 1;
	}
	

	public Integer count(ObservationDetail detail)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		Integer total = 0;
		
		String itemId = detail.getItemId();
		String processId = detail.getProcessId();
		int step = detail.getStep();
		
		/*@lineinfo:generated-code*//*@lineinfo:148^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			    count(*) 
//  			FROM
//  			:Com_("D_T2_OBSERVATION_DETAIL") d
//  			WHERE
//  				ITEM_ID = :itemId AND PROCESS_ID = :processId AND STEP = :step
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = itemId;
  String __sJT_3 = processId;
  int __sJT_4 = step;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:155^3*/
		
		if(total == null){
			total  = 0;
		}
		
		closeDefaultContext(myCtx);
		return total;
	}
	
	public Integer maxStep(ObservationDetail detail)
			throws NoPrivilegeException,SQLException,NoConnection {
		
		DefaultContext myCtx = getDefaultContext();
		Integer total = 0;
		
		String itemId = detail.getItemId();
		String processId = detail.getProcessId();
		
		/*@lineinfo:generated-code*//*@lineinfo:174^3*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  				max(STEP) 
//  			FROM
//  			:Com_("D_T2_OBSERVATION_DETAIL") d
//  			WHERE
//  				ITEM_ID = :itemId AND PROCESS_ID = :processId
//  		 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = itemId;
  String __sJT_3 = processId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:181^3*/
		
		if(total == null){
			total  = 0;
		}
		
		closeDefaultContext(myCtx);
		return total;
	}
	
	/**
	 * 根据出品图片查询观察表明细
	 */
	public List<ObservationDetail> query(String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:198^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			    :Com_("D_T2_OBSERVATION_DETAIL") d
//  			    WHERE
//  			        ITEM_ID = :itemId
//  			    ORDER BY
//  			        d.PROCESS_ID,
//  			        d.STEP };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
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

/*@lineinfo:user-code*//*@lineinfo:207^18*/
		List<ObservationDetail> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public List<ObservationDetail> queryByProcessId(String itemId,String processId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:218^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  					*
//  				FROM
//  				:Com_("D_T2_OBSERVATION_DETAIL") d
//  				WHERE
//  					ITEM_ID = :itemId AND PROCESS_ID = :processId
//  				ORDER BY
//  					d.PROCESS_ID,
//  					d.STEP };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = itemId;
  String __sJT_3 = processId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 8);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
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

/*@lineinfo:user-code*//*@lineinfo:227^12*/
		List<ObservationDetail> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/**
	 * 查询出品的某一操作步骤
	 */
	public ObservationDetail queryStep(String itemId,String processId, int step) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		HeaderIter headerIter = null;
		ObservationDetail header = new ObservationDetail();
		
		/*@lineinfo:generated-code*//*@lineinfo:243^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			    :Com_("D_T2_OBSERVATION_DETAIL") d
//  			    WHERE
//  			        d.ITEM_ID = :itemId
//  			    AND d.PROCESS_ID = :processId
//  			    AND d.STEP = :step };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = itemId;
  String __sJT_3 = processId;
  int __sJT_4 = step;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 9);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setInt(4, __sJT_4);
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

/*@lineinfo:user-code*//*@lineinfo:251^26*/
	
		if(headerIter.next()) {
			header.setItemId(headerIter.item_id());
			header.setPartId(headerIter.part_id());
			header.setProcessId(headerIter.process_id());
			header.setProcessedName(headerIter.processed_name());
			header.setStep(headerIter.step());
			header.setStepRemark(headerIter.step_remark());
		}
		
		headerIter.close();
		closeDefaultContext(myCtx);
		return header;
	}
	
	/**
	 * 根据出品ID获取相应的图片数据
	 */
	public byte[] getPicData(String itemId,String  processId, Integer step)
		throws NoPrivilegeException, SQLException, NoConnection {
		byte[] picData = null;
		DefaultContext myCtx = getDefaultContext();
		try {
			/*@lineinfo:generated-code*//*@lineinfo:275^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT 
//  					d.STEP_PIC_BLOB  
//  				FROM
//  				:Com_("D_T2_OBSERVATION_DETAIL") d
//  				WHERE
//  				    d.ITEM_ID = :itemId AND d.PROCESS_ID = :processId AND d.STEP = :step
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_OBSERVATION_DETAIL");
  String __sJT_2 = itemId;
  String __sJT_3 = processId;
  Integer __sJT_4 = step;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ObservationDetailBean_SJProfileKeys.getKey(0), 10);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setIntWrapper(4, __sJT_4);
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
    picData = __sJT_rtRs.getBytes(1);
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

/*@lineinfo:user-code*//*@lineinfo:282^4*/
		} catch (SQLException e) {
			log.error("itemId ： " + itemId + ", picData : " + picData);
			log.error("Error running the query: " + e);
			String sqlState = e.getSQLState();
			log.error("SQL state = " + sqlState);
			e.printStackTrace();
		}
		closeDefaultContext(myCtx);
		
		return picData;
	}
	
	private List<ObservationDetail> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<ObservationDetail> headerLst = new ArrayList<ObservationDetail>();
		while(headerIter.next()) {
			ObservationDetail header = new ObservationDetail();
			header.setItemId(headerIter.item_id());
			header.setPartId(headerIter.part_id());
			header.setProcessId(headerIter.process_id());
			header.setProcessedName(headerIter.processed_name());
			header.setStep(headerIter.step());
			header.setStepRemark(headerIter.step_remark());
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class ObservationDetailBean_SJProfileKeys 
{
  private static ObservationDetailBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ObservationDetailBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ObservationDetailBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.form.ObservationDetailBean_SJProfile0");
  }
}
