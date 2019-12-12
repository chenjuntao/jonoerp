/*@lineinfo:filename=FoodCategoryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**查询食品分类的营业信息列表
 *read food by category business info list
 *by cjt
 *first created on 2014.7.21
 *last edited on 2014.7.21
 *
 *edited by pengwei on 2014/7/30
 */

package logic.businessquery;

import java.math.BigDecimal;
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
import pojo.businessquery.FoodCategory;

public class FoodCategoryBean extends ConnectionPool
{
	private String strDateFormat = "yyyy-MM-dd";//设置日期格式
	
	/*@lineinfo:generated-code*//*@lineinfo:31^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class CategoryIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CategoryIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    nQtyNdx = findColumn("nQty");
    SUB_CATEGORY_IDNdx = findColumn("SUB_CATEGORY_ID");
    SUB_CATEGORY_NAMENdx = findColumn("SUB_CATEGORY_NAME");
    CATEGORY_NAMENdx = findColumn("CATEGORY_NAME");
  }
  public int nQty() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntNoNull(nQtyNdx);
  }
  private int nQtyNdx;
  public String SUB_CATEGORY_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(SUB_CATEGORY_IDNdx);
  }
  private int SUB_CATEGORY_IDNdx;
  public String SUB_CATEGORY_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(SUB_CATEGORY_NAMENdx);
  }
  private int SUB_CATEGORY_NAMENdx;
  public String CATEGORY_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CATEGORY_NAMENdx);
  }
  private int CATEGORY_NAMENdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:35^24*/ 

	/*@lineinfo:generated-code*//*@lineinfo:37^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class RawCategoryIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public RawCategoryIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    CATEGORY_IDNdx = findColumn("CATEGORY_ID");
    CATEGORY_NAMENdx = findColumn("CATEGORY_NAME");
    parent_nameNdx = findColumn("parent_name");
  }
  public String CATEGORY_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CATEGORY_IDNdx);
  }
  private int CATEGORY_IDNdx;
  public String CATEGORY_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CATEGORY_NAMENdx);
  }
  private int CATEGORY_NAMENdx;
  public String parent_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(parent_nameNdx);
  }
  private int parent_nameNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:40^22*/
	
     //查询某个时间段某个门店的营业额，by cjt.
	public BigDecimal getAmt(String shopC, String startDate, String endDate)
	throws NoPrivilegeException,SQLException,NoConnection{
		DefaultContext myCtx = getDefaultContext();
		BigDecimal amt = BigDecimal.ZERO;
		Object amtObj = null;
		if (myCtx != null) {
			//查询该时间段内某餐厅总营业额
			String shopCStr = shopC + "%";
			/*@lineinfo:generated-code*//*@lineinfo:51^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT sum(npayamt) 
//  				FROM :Com_("d_t_food_bill")
//  				WHERE cbranch_c like :shopCStr
//    				AND dbusiness >= to_date(:startDate, :strDateFormat)
//    				AND dbusiness <= to_date(:endDate, :strDateFormat)
//  			 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopCStr;
  String __sJT_3 = startDate;
  String __sJT_4 = strDateFormat;
  String __sJT_5 = endDate;
  String __sJT_6 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodCategoryBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
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
    amtObj = __sJT_rtRs.getObject(1, Object.class);
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

/*@lineinfo:user-code*//*@lineinfo:57^4*/
			if (amtObj != null) {
				amt = (BigDecimal)amtObj;
			}
			amt = amt.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		closeDefaultContext(myCtx);
		return amt;
	}

	public List<FoodCategory> listCategory(String shopC, String startTime, String endTime)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		List<FoodCategory> categories = new ArrayList<FoodCategory>();
		DefaultContext myCtx = getDefaultContext();
		CategoryIterator categoryIterator = null;
		String shopCStr = shopC + "%";
		if (myCtx != null)
		{
			//查询所有类别的出品数量
			/*@lineinfo:generated-code*//*@lineinfo:77^4*/

//  ************************************************************
//  #sql [myCtx] categoryIterator = { SELECT
//  				    SUM(b.nQty)     nQty,
//  				    c.CATEGORY_ID   SUB_CATEGORY_ID,
//  				    c.CATEGORY_NAME SUB_CATEGORY_NAME,
//  				    d.CATEGORY_NAME CATEGORY_NAME
//  				FROM
//  					:Com_("d_t_food_bill") a
//  				LEFT JOIN
//  				    :Com_("d_t_food_bills") b
//  				ON
//  				    a.cBill_C = b.cBill_C
//  				LEFT JOIN
//  					:Com_("d_t2_item_category") c
//  				ON
//  				    b.cLitCls_C = c.CATEGORY_ID
//  				LEFT JOIN
//  					:Com_("d_t2_item_category") d
//  				ON
//  				    c.PARENT_ID = d.CATEGORY_ID
//  				WHERE
//  				 	c.ITEM_TYPE = 'PRODUCT'
//  			    AND a.cbranch_c like :shopCStr
//  				AND a.DBUSINESS >= to_date(:startTime, :strDateFormat)
//  				AND a.DBUSINESS <= to_date(:endTime, :strDateFormat)
//  				GROUP BY
//  				    c.CATEGORY_ID,
//  				    c.CATEGORY_NAME,
//  				    d.CATEGORY_NAME
//  				ORDER BY
//  				    c.CATEGORY_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_food_bills");
  String __sJT_3 = Com_("d_t2_item_category");
  String __sJT_4 = Com_("d_t2_item_category");
  String __sJT_5 = shopCStr;
  String __sJT_6 = startTime;
  String __sJT_7 = strDateFormat;
  String __sJT_8 = endTime;
  String __sJT_9 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodCategoryBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setString(8, __sJT_8);
      __sJT_stmt.setString(9, __sJT_9);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      categoryIterator = new CategoryIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:107^22*/
			try {
				while (categoryIterator.next()) {
					FoodCategory category = new FoodCategory();
					if(categoryIterator.SUB_CATEGORY_ID()!= null){
						category.foodCount = categoryIterator.nQty();
						category.subCategoryId = categoryIterator.SUB_CATEGORY_ID();
						category.subCategoryName = categoryIterator.SUB_CATEGORY_NAME();
						category.categoryName = categoryIterator.CATEGORY_NAME();
						categories.add(category);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			categoryIterator.close();
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return categories;
	}
	
	public List<FoodCategory> listRawCategory()
			throws NoPrivilegeException,SQLException,NoConnection
	{
		List<FoodCategory> categories = new ArrayList<FoodCategory>();
		DefaultContext myCtx = getDefaultContext();
		RawCategoryIterator rawCategoryIterator = null;
		if (myCtx != null)
		{
			//先将所有的类别列出来
			/*@lineinfo:generated-code*//*@lineinfo:141^4*/

//  ************************************************************
//  #sql [myCtx] rawCategoryIterator = { SELECT
//  				    c.CATEGORY_ID,
//  				    c.CATEGORY_NAME,
//  				    cp.CATEGORY_NAME AS parent_name
//  				FROM
//  					:Com_("d_t2_item_category") c,
//  					:Com_("d_t2_item_category") cp
//  				WHERE
//  				    trim(c.PARENT_ID) = trim(cp.CATEGORY_ID)
//  				AND c.PARENT_ID LIKE '020%'
//  				GROUP BY
//  				    c.CATEGORY_ID,
//  				    c.CATEGORY_NAME ,
//  				    cp.CATEGORY_NAME
//  				ORDER BY
//  				    c.CATEGORY_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_category");
  String __sJT_2 = Com_("d_t2_item_category");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodCategoryBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      rawCategoryIterator = new RawCategoryIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:158^4*/
			
			while(rawCategoryIterator.next()){
				FoodCategory category = new FoodCategory();
				category.subCategoryId = rawCategoryIterator.CATEGORY_ID();
				category.subCategoryName = rawCategoryIterator.CATEGORY_NAME();
				category.categoryName = rawCategoryIterator.parent_name();
				categories.add(category);
			}
			rawCategoryIterator.close();
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return categories;
	}
}/*@lineinfo:generated-code*/class FoodCategoryBean_SJProfileKeys 
{
  private static FoodCategoryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodCategoryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodCategoryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.FoodCategoryBean_SJProfile0");
  }
}
