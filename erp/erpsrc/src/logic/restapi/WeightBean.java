/*@lineinfo:filename=WeightBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 *by cjt
 *first created on 2014.6.12
 *last edited on 2014.7.7
 *
 *edited by pw on 2014.7.28
 */

package logic.restapi;

import pojo.form.Weight;
import sqlj.runtime.ref.*;
import java.sql.*;
import java.util.*;
import java.text.ParseException;
import logic.pool.ConnectionPool;
import logic.*;
import com.tanry.framework.acl.*;
import java.sql.Date;
import pojo.form.Store;

public class WeightBean extends ConnectionPool{
	private String comId = "jono";
	/*@lineinfo:generated-code*//*@lineinfo:24^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class WeightIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public WeightIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    myidNdx = findColumn("myid");
    numNdx = findColumn("num");
    picNdx = findColumn("pic");
  }
  public String myid() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(myidNdx);
  }
  private int myidNdx;
  public String num() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(numNdx);
  }
  private int numNdx;
  public String pic() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(picNdx);
  }
  private int picNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:28^3*/
	/*@lineinfo:generated-code*//*@lineinfo:29^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class StoreIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public StoreIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    storeidNdx = findColumn("storeid");
    statusNdx = findColumn("status");
  }
  public String storeid() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storeidNdx);
  }
  private int storeidNdx;
  public String status() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(statusNdx);
  }
  private int statusNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:32^6*/
	
	public int saveEntity(String myid,String num,String pic) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);

		//String id = map.get("id").toString();
		//String num = map.get("num").toString();
		//String pic = map.get("pic").toString();

		/*@lineinfo:generated-code*//*@lineinfo:41^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO jono.weight
//  				(myid, num, pic)
//  			VALUES 
//  				(:myid, :num, :pic)
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = myid;
  String __sJT_2 = num;
  String __sJT_3 = pic;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WeightBean_SJProfileKeys.getKey(0), 0);
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

/*@lineinfo:user-code*//*@lineinfo:46^4*/
		closeDefaultContext(myCtx);
		
		return 1;
	}


	public int saveStore(String id,String num,String pic) throws NoPrivilegeException, SQLException, NoConnection {
    		DefaultContext myCtx = getComContext(comId);

    		//String id = map.get("id").toString();
    		//String num = map.get("num").toString();
    		//String pic = map.get("pic").toString();
    		/*@lineinfo:generated-code*//*@lineinfo:59^7*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO jono.d_store
//      				(id, weight, pic)
//      			VALUES
//      				(:id, :num, :pic)
//      			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = id;
  String __sJT_2 = num;
  String __sJT_3 = pic;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WeightBean_SJProfileKeys.getKey(0), 1);
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

/*@lineinfo:user-code*//*@lineinfo:64^8*/
    		closeDefaultContext(myCtx);
    		return 1;
    	}

	       public List<Weight> selectTest()
    			throws NoPrivilegeException,SQLException,NoConnection {
    		DefaultContext myCtx = getDefaultContext();
    		WeightIter weightIter = null;
    		/*@lineinfo:generated-code*//*@lineinfo:73^7*/
//  ************************************************************
//  #sql [myCtx] weightIter = { SELECT
//      			           h.myid,
//      			           h.num,
//      			           h.pic
//      			        FROM
//      			       jono.weight h
//      		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WeightBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      weightIter = new WeightIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:81^7*/
    		List<Weight> weightList = processIter(weightIter);
    		weightIter.close();
    		closeDefaultContext(myCtx);
    		return weightList;
    	}

    	private List<Weight> processIter(WeightIter weightIter)
        			throws SQLException {
        		List<Weight> weightList = new ArrayList<Weight>();
        		while(weightIter.next()) {
        			Weight weight = new Weight();
        			weight.setMyid(weightIter.myid());
        	        weight.setNum(weightIter.num());
        	        weight.setPic(weightIter.pic());
        		    weightList.add(weight);
        		}
        		return weightList;
        	}


        	public List<Store> selectStore()
                			throws NoPrivilegeException,SQLException,NoConnection {
                		DefaultContext myCtx = getDefaultContext();
                		StoreIter storeIter = null;
                		/*@lineinfo:generated-code*//*@lineinfo:106^19*/

//  ************************************************************
//  #sql [myCtx] storeIter = { SELECT
//                  			           h.storeid,
//                  			           h.status
//                  			        FROM
//                  			       jono.stores h
//                  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WeightBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      storeIter = new StoreIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:112^19*/
                		List<Store> storeList = processIters(storeIter);
                		//storeList.close();
                		closeDefaultContext(myCtx);
                		return storeList;
                	}


            private List<Store> processIters(StoreIter storeIter)
        			throws SQLException {
        		List<Store> storeList = new ArrayList<Store>();
        		while(storeIter.next()) {
        			 Store store = new Store();
                     store.setStoreid(storeIter.storeid());
                     store.setStatus(storeIter.status());
        		    storeList.add(store);
        		}
        		return storeList;
        	}










}/*@lineinfo:generated-code*/class WeightBean_SJProfileKeys 
{
  private static WeightBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new WeightBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private WeightBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.restapi.WeightBean_SJProfile0");
  }
}
