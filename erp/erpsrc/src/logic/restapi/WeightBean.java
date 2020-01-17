/*@lineinfo:filename=WeightBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 *by wuqing
 *first created on 2014.6.12
 *last edited on 2014.7.7
 *
 *edited by cjt on 2014.7.28
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
    isokNdx = findColumn("isok");
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
  public Integer isok() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(isokNdx);
  }
  private int isokNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:29^3*/
	/*@lineinfo:generated-code*//*@lineinfo:30^2*/

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

/*@lineinfo:user-code*//*@lineinfo:33^6*/
	
	public int saveEntity(String myid,String num,String pic) throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getComContext(comId);

		//String id = map.get("id").toString();
		//String num = map.get("num").toString();
		//String pic = map.get("pic").toString();

		/*@lineinfo:generated-code*//*@lineinfo:42^3*/

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

/*@lineinfo:user-code*//*@lineinfo:47^4*/
		closeDefaultContext(myCtx);
		
		return 1;
	}


	public int saveStore(String id,String num,String pic) throws NoPrivilegeException, SQLException, NoConnection {
    		DefaultContext myCtx = getComContext(comId);

    		//String id = map.get("id").toString();
    		//String num = map.get("num").toString();
    		//String pic = map.get("pic").toString();
    		/*@lineinfo:generated-code*//*@lineinfo:60^7*/

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

/*@lineinfo:user-code*//*@lineinfo:65^8*/
    		closeDefaultContext(myCtx);
    		return 1;
    	}

	       public List<Weight> selectTest()
    			throws NoPrivilegeException,SQLException,NoConnection {
    		DefaultContext myCtx = getDefaultContext();
    		WeightIter weightIter = null;
    		/*@lineinfo:generated-code*//*@lineinfo:74^7*/

//  ************************************************************
//  #sql [myCtx] weightIter = { SELECT
//      			           h.myid,
//      			           h.num,
//      			           h.pic,
//      			           h.isok
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

/*@lineinfo:user-code*//*@lineinfo:83^7*/
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
        	        weight.setIsok(weightIter.isok());
        		    weightList.add(weight);
        		}
        		return weightList;
        	}


        	 public List<Weight> selectTests()
                			throws NoPrivilegeException,SQLException,NoConnection {
                		DefaultContext myCtx = getDefaultContext();
                		WeightIter weightIter = null;
                		/*@lineinfo:generated-code*//*@lineinfo:109^19*/

//  ************************************************************
//  #sql [myCtx] weightIter = { SELECT
//                  			           h.myid,
//                  			           h.num,
//                  			           h.pic,
//                  			           h.isok
//                  			        FROM
//                  			       jono.weight h
//                  			       where h.isok=0
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
      weightIter = new WeightIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:118^19*/
                		List<Weight> weightList = processItert(weightIter);
                		weightIter.close();
                		closeDefaultContext(myCtx);
                		return weightList;
                	}

                	private List<Weight> processItert(WeightIter weightIter)
                    			throws SQLException {
                    		List<Weight> weightList = new ArrayList<Weight>();
                    		while(weightIter.next()) {
                    			Weight weight = new Weight();
                    			weight.setMyid(weightIter.myid());
                    	        weight.setNum(weightIter.num());
                    	        weight.setPic(weightIter.pic());
                    	        weight.setIsok(weightIter.isok());
                    		    weightList.add(weight);
                    		}
                    		return weightList;
                    	}





        	public List<Store> selectStore()
                			throws NoPrivilegeException,SQLException,NoConnection {
                		DefaultContext myCtx = getDefaultContext();
                		StoreIter storeIter = null;
                		/*@lineinfo:generated-code*//*@lineinfo:147^19*/

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
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WeightBean_SJProfileKeys.getKey(0), 4);
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

/*@lineinfo:user-code*//*@lineinfo:153^19*/
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




	public int updateByWorkId(String myid,Integer isok)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:180^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//  				jono.weight
//  		    SET
//  		         isok =:isok
//  		    WHERE
//  		        myid =:myid
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  Integer __sJT_1 = isok;
  String __sJT_2 = myid;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WeightBean_SJProfileKeys.getKey(0), 5);
    try 
    {
      __sJT_stmt.setIntWrapper(1, __sJT_1);
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

/*@lineinfo:user-code*//*@lineinfo:187^3*/
		closeDefaultContext(myCtx);

		return 1;
	}


	public int updateEntity(Weight weight)
    			throws NoPrivilegeException, SQLException, NoConnection {
    		DefaultContext myCtx = getDefaultContext();
    		String myid=weight.getMyid();
    		/*@lineinfo:generated-code*//*@lineinfo:198^7*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//                         jono.weight
//                         SET
//                            isok= 0
//                         WHERE
//                             myid= :myid
//                           };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = myid;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WeightBean_SJProfileKeys.getKey(0), 6);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:205^25*/
    		closeDefaultContext(myCtx);

    		return 1;
    }

	public int updateStatus(Weight weight)
			throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		String myid=weight.getMyid();

		/*@lineinfo:generated-code*//*@lineinfo:217^3*/

//  ************************************************************
//  #sql [myCtx] { UPDATE
//                     jono.weight
//                     SET
//                        isok= 1
//                     WHERE
//                         myid= :myid
//                       };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = myid;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, WeightBean_SJProfileKeys.getKey(0), 7);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:224^21*/
		closeDefaultContext(myCtx);

		return 1;
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
