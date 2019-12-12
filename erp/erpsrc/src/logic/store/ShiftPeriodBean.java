/*@lineinfo:filename=ShiftPeriodBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/ 
package logic.store;

import sqlj.runtime.*;
import sqlj.runtime.ref.*;
import java.sql.*;
import oracle.sql.*;
import oracle.sqlj.runtime.*;
import oracle.jdbc.driver.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import logic.pool.ConnectionPool;
import logic.*;
import com.tanry.framework.acl.*;

//获取班次和市别信息
public class ShiftPeriodBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd"; //设置日期格式

	/*@lineinfo:generated-code*//*@lineinfo:22^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class ShiftIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ShiftIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cShift_NNdx = findColumn("cShift_N");
  }
  public String cShift_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cShift_NNdx);
  }
  private int cShift_NNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:22^59*/
	
	/*@lineinfo:generated-code*//*@lineinfo:24^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class PeriodIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public PeriodIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cPeriod_NNdx = findColumn("cPeriod_N");
  }
  public String cPeriod_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPeriod_NNdx);
  }
  private int cPeriod_NNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:24^61*/

	public ShiftPeriodBean()
	{
	}
	
	public List<Map> getPeriod() throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<Map> result = new ArrayList<Map>();
		DefaultContext myCtx = getDefaultContext();
		PeriodIterator periodIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:37^4*/

//  ************************************************************
//  #sql [myCtx] periodIterator = { SELECT DISTINCT cPeriod_N 
//  					FROM :Com_("D_T_FOOD_BILL") b
//  					ORDER BY b.CPERIOD_N
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShiftPeriodBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      periodIterator = new PeriodIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^4*/
			
			while(periodIterator.next())
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code",periodIterator.cPeriod_N());
				map.put("name",periodIterator.cPeriod_N());
				result.add(map);
			}
			periodIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}

	public List<Map> getShift() throws NoPrivilegeException,SQLException,NoConnection	{
		
		List<Map> result = new ArrayList<Map>();
		DefaultContext myCtx = getDefaultContext();
		ShiftIterator shiftIterator = null;
		if (myCtx != null)
		{
			/*@lineinfo:generated-code*//*@lineinfo:67^4*/

//  ************************************************************
//  #sql [myCtx] shiftIterator = { SELECT DISTINCT cShift_N 
//  					FROM :Com_("D_T_FOOD_BILL") b 
//  					ORDER BY b.cShift_N  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ShiftPeriodBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      shiftIterator = new ShiftIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:70^26*/
			while(shiftIterator.next())
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code",shiftIterator.cShift_N());
				map.put("name",shiftIterator.cShift_N());
				result.add(map);
			}
			shiftIterator.close();
		}
		else
		{
			throw new NoConnection();
		}
		closeDefaultContext(myCtx);
		return result;
	}

	
}/*@lineinfo:generated-code*/class ShiftPeriodBean_SJProfileKeys 
{
  private static ShiftPeriodBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new ShiftPeriodBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private ShiftPeriodBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.ShiftPeriodBean_SJProfile0");
  }
}
