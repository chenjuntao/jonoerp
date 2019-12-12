/*@lineinfo:filename=PeopleBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * read createman, settleman, and others list
 * 
 * created by cjt 2014.6.25
 * 
 * modified by lyz 2015.6.2
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
import com.tanry.framework.util.Cn2Spell;
import com.tanry.framework.util.TextUtil;

public class PeopleBean extends ConnectionPool {
	
	/*@lineinfo:generated-code*//*@lineinfo:27^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class ManIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public ManIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    codeNdx = findColumn("code");
    code_nameNdx = findColumn("code_name");
    nameNdx = findColumn("name");
  }
  public String code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(codeNdx);
  }
  private int codeNdx;
  public String code_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(code_nameNdx);
  }
  private int code_nameNdx;
  public String name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(nameNdx);
  }
  private int nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:27^78*/
	
	/**
	 * 根据字段查询相应的人员信息，按拼音排序.
	 */
	public List<Map> listMan(String codeCol, String nameCol) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		ManIterator manIterator = null;
		//全角的数字/字母/标点符号转半角，比如中文括号转英文括号
		/*@lineinfo:generated-code*//*@lineinfo:37^3*/

//  ************************************************************
//  #sql [myCtx] manIterator = { SELECT
//  			    trim(code) as code,
//  			    trim(name) as name,
//  			    '[' || trim(code) || ']' || trim(name) code_name
//  			FROM
//  			    (
//  			        SELECT DISTINCT
//  			        	to_single_byte(:codeCol) code,
//  			        	to_single_byte(:nameCol) name
//  			        FROM
//  			            :Com_("d_t_food_bill")
//  			        WHERE
//  			            ascii(:nameCol) <> 0 ) p
//  			ORDER BY
//  			    trim(name)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = codeCol;
  String __sJT_2 = nameCol;
  String __sJT_3 = Com_("d_t_food_bill");
  String __sJT_4 = nameCol;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, PeopleBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      manIterator = new ManIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:53^3*/
		
		List<Map> manLst = new ArrayList<Map>();
		while(manIterator.next()) {
			Map<String, String> people = new HashMap<String, String>();
			people.put("peopleC", manIterator.code());
			people.put("peopleName", manIterator.code_name());
			String name = manIterator.name();
			if (TextUtil.isEmpty(name)) {
				continue;
			}
			people.put("peoplePinyin", Cn2Spell.converterToFirstSpell(name));
			manLst.add(people);
		}
		manIterator.close();
		closeDefaultContext(myCtx);
		
		return manLst;
	}
}/*@lineinfo:generated-code*/class PeopleBean_SJProfileKeys 
{
  private static PeopleBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new PeopleBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private PeopleBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.PeopleBean_SJProfile0");
  }
}
