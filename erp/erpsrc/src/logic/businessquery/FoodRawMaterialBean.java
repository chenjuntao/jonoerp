/*@lineinfo:filename=FoodRawMaterialBean*//*@lineinfo:user-code*//*@lineinfo:1^1*///===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.8.7 by lyz
// Last edited on 2014.8.21 by cjt
//
// Comments:Calculate amount of raw per ten thousands of CNY from business data.
// 说明：根据营业数据来计算原材料的万元用量。
//
//===============================================

package logic.businessquery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import logic.NoConnection;
import logic.pool.ConnectionPool;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.acl.NoPrivilegeException;
import org.apache.commons.lang3.StringUtils;
import com.tanry.framework.util.TextUtil;

public class FoodRawMaterialBean extends ConnectionPool {
	
	public static Logger log = Logger.getLogger(FoodRawMaterialBean.class);
	
	private String strDateFormat = "yyyy-MM-dd";//设置日期格式
	
	/*@lineinfo:generated-code*//*@lineinfo:39^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class MaterialIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MaterialIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    ITEM_IDNdx = findColumn("ITEM_ID");
    ITEM_NAMENdx = findColumn("ITEM_NAME");
    ITEM_DIMENSIONNdx = findColumn("ITEM_DIMENSION");
    nqtyNdx = findColumn("nqty");
  }
  public String ITEM_ID() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_IDNdx);
  }
  private int ITEM_IDNdx;
  public String ITEM_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_NAMENdx);
  }
  private int ITEM_NAMENdx;
  public String ITEM_DIMENSION() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_DIMENSIONNdx);
  }
  private int ITEM_DIMENSIONNdx;
  public BigDecimal nqty() 
    throws java.sql.SQLException 
  {
    return resultSet.getBigDecimal(nqtyNdx);
  }
  private int nqtyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:43^19*/ 
	
	public BigDecimal getAmt(String shopC, String startDate, String endDate,DefaultContext myCtx)
		throws NoPrivilegeException,SQLException,NoConnection{
			BigDecimal amt = BigDecimal.ZERO;
			BigDecimal amt2 = BigDecimal.ZERO;
			BigDecimal amt3 = BigDecimal.ZERO;
			
				try {
					// 统计餐厅营业额应用 应付款
					/*@lineinfo:generated-code*//*@lineinfo:53^6*/

//  ************************************************************
//  #sql [myCtx] { SELECT round(sum(nOughtAmt),4) 
//  							FROM :Com_("d_t_food_bill")
//  						WHERE cbranch_c = :shopC
//  		  				AND dbusiness >= to_date(:startDate, :strDateFormat)
//  		  				AND dbusiness <= to_date(:endDate, :strDateFormat)
//  					 };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = shopC;
  String __sJT_3 = startDate;
  String __sJT_4 = strDateFormat;
  String __sJT_5 = endDate;
  String __sJT_6 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodRawMaterialBean_SJProfileKeys.getKey(0), 0);
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
    amt = __sJT_rtRs.getBigDecimal(1);
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

/*@lineinfo:user-code*//*@lineinfo:59^6*/
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if (amt != null) {
					amt = (BigDecimal)amt;
				}else{
					amt = BigDecimal.ZERO;
				}
				
				try {
					//3033 卡类  3036 ID售卖/预冲
					//金牛角中的营业额要扣除这种类型的数据，但是这是不规范的做法
					//正常的标准版不应该有这样的扣除
					/*@lineinfo:generated-code*//*@lineinfo:74^6*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  			            round(SUM(bs.NAMT),4) 
//  			        FROM
//  			        	:Com_("d_t_food_bills") bs
//  			        LEFT JOIN
//  			        	:Com_("d_t_food_bill") b
//  			        ON
//  			            bs.CBILL_C = b.CBILL_C
//  			        WHERE
//  			        	b.cbranch_c = :shopC
//  					AND dbusiness >= to_date(:startDate, :strDateFormat)
//  					AND dbusiness <= to_date(:endDate, :strDateFormat)
//  			        AND bs.cLitCls_C in('3033','3036')
//  			        GROUP BY
//  			            b.CBRANCH_C 
//  			     };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = shopC;
  String __sJT_4 = startDate;
  String __sJT_5 = strDateFormat;
  String __sJT_6 = endDate;
  String __sJT_7 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodRawMaterialBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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
    amt2 = __sJT_rtRs.getBigDecimal(1);
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

/*@lineinfo:user-code*//*@lineinfo:90^8*/
					} catch (SQLException e) {
					e.printStackTrace();
				}
				
		    if (amt2 != null) {
		    	amt2 = (BigDecimal)amt2;
			}else{
				amt2 = BigDecimal.ZERO;
			}
		    
		    amt3 = amt.subtract(amt2);
		    amt3 = amt3.setScale(2, BigDecimal.ROUND_HALF_UP);
			
		return amt3;
	}
	
	public BigDecimal getAmt(String shopC, String startDate, String endDate)
				throws NoPrivilegeException,SQLException,NoConnection{
		DefaultContext myCtx = getDefaultContext();
		BigDecimal amt = BigDecimal.ZERO;
		BigDecimal amt2 = BigDecimal.ZERO;
		BigDecimal amt3 = BigDecimal.ZERO;
		
		try {
			/*@lineinfo:generated-code*//*@lineinfo:115^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT round(sum(nOughtAmt),4) 
//  					FROM :Com_("d_t_food_bill")
//  				WHERE cbranch_c = :shopC
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
  String __sJT_2 = shopC;
  String __sJT_3 = startDate;
  String __sJT_4 = strDateFormat;
  String __sJT_5 = endDate;
  String __sJT_6 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodRawMaterialBean_SJProfileKeys.getKey(0), 2);
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
    amt = __sJT_rtRs.getBigDecimal(1);
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

/*@lineinfo:user-code*//*@lineinfo:121^4*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (amt != null) {
			amt = (BigDecimal)amt;
		}else{
			amt = BigDecimal.ZERO;
		}
		
		try {
			/*@lineinfo:generated-code*//*@lineinfo:133^4*/

//  ************************************************************
//  #sql [myCtx] { SELECT
//  		            round(SUM(bs.NAMT),4) NAMT 
//  		        FROM
//  		            :Com_("d_t_food_bills") bs
//  		        LEFT JOIN
//  		            :Com_("d_t_food_bill") b
//  		        ON
//  		            bs.CBILL_C = b.CBILL_C
//  		        WHERE
//  		        	b.cbranch_c = :shopC
//  				AND dbusiness >= to_date(:startDate, :strDateFormat)
//  				AND dbusiness <= to_date(:endDate, :strDateFormat)
//  		        AND bs.cLitCls_C in('3033','3036')
//  		        GROUP BY
//  		            b.CBRANCH_C 
//  		     };
//  ************************************************************

{
  sqlj.runtime.profile.RTResultSet __sJT_rtRs;
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = shopC;
  String __sJT_4 = startDate;
  String __sJT_5 = strDateFormat;
  String __sJT_6 = endDate;
  String __sJT_7 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodRawMaterialBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
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
    amt2 = __sJT_rtRs.getBigDecimal(1);
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

/*@lineinfo:user-code*//*@lineinfo:149^7*/
		}catch (SQLException e) {
			e.printStackTrace();
		}
	    
		if (amt2 != null) {
	    	amt2 = (BigDecimal)amt2;
		}else{
			amt2 = BigDecimal.ZERO;
		}
		    
	    amt3 = amt.subtract(amt2);
	    amt3 = amt3.setScale(4, BigDecimal.ROUND_HALF_UP);
		
		closeDefaultContext(myCtx);
		return amt3;
	}
	
	//根据原材料种类查询万元用量，by lyz.
	public List<Map> getDetail(String shopC, String startDate, String endDate, String categoryId)
			throws NoPrivilegeException,SQLException,NoConnection{
		List<Map> detailLst = new ArrayList();
		DefaultContext myCtx = getDefaultContext();
		MaterialIterator materialIterator = null;
		
		if (myCtx != null) {
			BigDecimal amt = getAmt(shopC,startDate,endDate,myCtx);
			
			amt = amt.setScale(4, BigDecimal.ROUND_HALF_UP);

			//查询某类别下所有的原料数量
			/*@lineinfo:generated-code*//*@lineinfo:180^4*/

//  ************************************************************
//  #sql [myCtx] materialIterator = { SELECT
//  					im.ITEM_ID,
//  				    im.ITEM_NAME,
//  				    im.ITEM_DIMENSION,
//  				    round(SUM(t.item_gross_count * d.nqty),4) AS nqty
//  				FROM
//  					:Com_("d_t_food_bill") b,
//  				    :Com_("d_t_food_bills") d,
//  				    :Com_("d_t2_therapy") t,
//  				    :Com_("d_t2_item_meta") im
//  				WHERE
//  				    d.CBILL_C = b.CBILL_C
//  				AND b.cbranch_c = :shopC
//  				AND t.THERAPY_ID = d.cfood_c
//  				AND t.item_id =trim(im.ITEM_ID)
//  				AND trim(im.CATEGORY_ID )= :categoryId
//  				AND b.dbusiness >= to_date(:startDate, :strDateFormat)
//  				AND b.dbusiness <= to_date(:endDate, :strDateFormat)
//  				GROUP BY
//  			    	im.ITEM_ID,
//  				    im.ITEM_NAME,
//  				    im.ITEM_DIMENSION
//  				ORDER BY
//  				    im.ITEM_ID
//  			 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bill");
  String __sJT_2 = Com_("d_t_food_bills");
  String __sJT_3 = Com_("d_t2_therapy");
  String __sJT_4 = Com_("d_t2_item_meta");
  String __sJT_5 = shopC;
  String __sJT_6 = categoryId;
  String __sJT_7 = startDate;
  String __sJT_8 = strDateFormat;
  String __sJT_9 = endDate;
  String __sJT_10 = strDateFormat;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodRawMaterialBean_SJProfileKeys.getKey(0), 4);
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
      __sJT_stmt.setString(10, __sJT_10);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      materialIterator = new MaterialIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:205^4*/
			while(materialIterator.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itemId", materialIterator.ITEM_ID());
				map.put("itemName", materialIterator.ITEM_NAME());
				map.put("unit", materialIterator.ITEM_DIMENSION());

				// 每万元材料数量计算公式： amt / nqty = 10000 / 每万元材料数量				
				BigDecimal nqty = materialIterator.nqty().setScale(4, BigDecimal.ROUND_HALF_UP);
				map.put("nqty", nqty);
				BigDecimal unitQty = BigDecimal.ZERO;
				if (amt.compareTo(BigDecimal.ZERO) != 0) {
					unitQty = nqty.multiply(new BigDecimal(10000)).divide(amt,4, BigDecimal.ROUND_HALF_UP);
				}
				map.put("unitQty", unitQty);
				detailLst.add(map);
			}
			materialIterator.close();
		} else {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return detailLst;
	}

	/*@lineinfo:generated-code*//*@lineinfo:230^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class MyIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MyIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    item_idNdx = findColumn("item_id");
    nqty1Ndx = findColumn("nqty1");
    nqty2Ndx = findColumn("nqty2");
    nqty3Ndx = findColumn("nqty3");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public Double nqty1() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nqty1Ndx);
  }
  private int nqty1Ndx;
  public Double nqty2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nqty2Ndx);
  }
  private int nqty2Ndx;
  public Double nqty3() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nqty3Ndx);
  }
  private int nqty3Ndx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:235^3*/ 
	
	public List<Map> getAllAmtTTCNY(String shopC, String startDate1, String endDate1,String startDate2, String endDate2,
			String startDate3, String endDate3,String query)
			throws NoPrivilegeException,SQLException,NoConnection {
		BigDecimal amt1 = BigDecimal.ZERO; 
		BigDecimal amt2 = BigDecimal.ZERO; 
		BigDecimal amt3 = BigDecimal.ZERO; 
		
		DefaultContext myCtx = getDefaultContext();

		if (!TextUtil.isEmpty(startDate1)) {
			amt1 = getAmt(shopC, startDate1, endDate1, myCtx);
		}

		if (!TextUtil.isEmpty(startDate2)) {
			amt2 = getAmt(shopC, startDate2, endDate2, myCtx);
		}

		if (!TextUtil.isEmpty(startDate3)) {
			amt3 = getAmt(shopC, startDate3, endDate3, myCtx);
		}
		
		MyIterator myIterator = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:260^3*/

//  ************************************************************
//  #sql [myCtx] myIterator = { WITH
//  		    items AS
//  		    (
//  		        SELECT
//  		            m.item_id
//  		        FROM
//  		            :Com_("d_t2_item_meta") m
//  		        WHERE
//  		            :query
//  		    )
//  		    ,
//  		    g1 AS
//  		    (
//  		        SELECT
//  		            round(SUM(t.item_gross_count * d.nqty),4) qty1,
//  		            t.item_id
//  		        FROM
//  		        	:Com_("d_t_food_bill") b,
//  		            :Com_("d_t_food_bills") d,
//  		            :Com_("d_t2_therapy") t
//  		        WHERE
//  		            d.CBILL_C = b.CBILL_C
//  		        AND t.THERAPY_ID = trim(d.cfood_c)
//  		        AND b.cbranch_c = :shopC
//          		AND b.dbusiness >= to_date(:startDate1, :strDateFormat)
//      			AND b.dbusiness <= to_date(:endDate1, :strDateFormat)
//  		        GROUP BY
//  		            t.item_id
//  		    )
//  		    ,
//  		    g2 AS
//  		    (
//  		        SELECT
//  		            round(SUM(t.item_gross_count * d.nqty),4) qty2,
//  		            t.item_id
//  		        FROM
//  		        	:Com_("d_t_food_bill") b,
//  		        	:Com_("d_t_food_bills") d,
//  		            :Com_("d_t2_therapy") t
//  		        WHERE
//  		            d.CBILL_C = b.CBILL_C
//  		        AND t.THERAPY_ID = d.cfood_c
//  		        AND b.cbranch_c = :shopC
//          		AND b.dbusiness >= to_date(:startDate2, :strDateFormat)
//      			AND b.dbusiness <= to_date(:endDate2, :strDateFormat)
//  		        GROUP BY
//  		            t.item_id
//  		    )
//  		    ,
//  		    g3 AS
//  		    (
//  		        SELECT
//  		            round(SUM(t.item_gross_count * d.nqty),4) qty3,
//  		            t.item_id
//  		        FROM
//  		        	:Com_("d_t_food_bill") b,
//  		            :Com_("d_t_food_bills") d,
//  		            :Com_("d_t2_therapy") t
//  		        WHERE
//  		            d.CBILL_C = b.CBILL_C
//  		        AND t.THERAPY_ID = d.cfood_c
//  		        AND b.cbranch_c = :shopC
//          		AND b.dbusiness >= to_date(:startDate3, :strDateFormat)
//      			AND b.dbusiness <= to_date(:endDate3, :strDateFormat)
//  		        GROUP BY
//  		            t.item_id
//  		    )
//  		SELECT
//  		    m.item_id,
//  		    DECODE(:amt1,0,'',ROUND(qty1*10000/:amt1,4)) nqty1,
//  		    DECODE(:amt2,0,'',ROUND(qty2*10000/:amt2,4)) nqty2,
//  		    DECODE(:amt3,0,'',ROUND(qty3*10000/:amt3,4)) nqty3
//  		FROM
//  		    items m
//  		LEFT JOIN
//  		    g1
//  		ON
//  		    m.item_id = g1.item_id
//  		LEFT JOIN
//  		    g2
//  		ON
//  		    m.item_id = g2.item_id
//  		LEFT JOIN
//  		    g3
//  		ON
//  		    m.item_id = g3.item_id };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t2_item_meta");
  String __sJT_2 = query;
  String __sJT_3 = Com_("d_t_food_bill");
  String __sJT_4 = Com_("d_t_food_bills");
  String __sJT_5 = Com_("d_t2_therapy");
  String __sJT_6 = shopC;
  String __sJT_7 = startDate1;
  String __sJT_8 = strDateFormat;
  String __sJT_9 = endDate1;
  String __sJT_10 = strDateFormat;
  String __sJT_11 = Com_("d_t_food_bill");
  String __sJT_12 = Com_("d_t_food_bills");
  String __sJT_13 = Com_("d_t2_therapy");
  String __sJT_14 = shopC;
  String __sJT_15 = startDate2;
  String __sJT_16 = strDateFormat;
  String __sJT_17 = endDate2;
  String __sJT_18 = strDateFormat;
  String __sJT_19 = Com_("d_t_food_bill");
  String __sJT_20 = Com_("d_t_food_bills");
  String __sJT_21 = Com_("d_t2_therapy");
  String __sJT_22 = shopC;
  String __sJT_23 = startDate3;
  String __sJT_24 = strDateFormat;
  String __sJT_25 = endDate3;
  String __sJT_26 = strDateFormat;
  BigDecimal __sJT_27 = amt1;
  BigDecimal __sJT_28 = amt1;
  BigDecimal __sJT_29 = amt2;
  BigDecimal __sJT_30 = amt2;
  BigDecimal __sJT_31 = amt3;
  BigDecimal __sJT_32 = amt3;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodRawMaterialBean_SJProfileKeys.getKey(0), 5);
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
      __sJT_stmt.setString(10, __sJT_10);
      __sJT_stmt.setString(11, __sJT_11);
      __sJT_stmt.setString(12, __sJT_12);
      __sJT_stmt.setString(13, __sJT_13);
      __sJT_stmt.setString(14, __sJT_14);
      __sJT_stmt.setString(15, __sJT_15);
      __sJT_stmt.setString(16, __sJT_16);
      __sJT_stmt.setString(17, __sJT_17);
      __sJT_stmt.setString(18, __sJT_18);
      __sJT_stmt.setString(19, __sJT_19);
      __sJT_stmt.setString(20, __sJT_20);
      __sJT_stmt.setString(21, __sJT_21);
      __sJT_stmt.setString(22, __sJT_22);
      __sJT_stmt.setString(23, __sJT_23);
      __sJT_stmt.setString(24, __sJT_24);
      __sJT_stmt.setString(25, __sJT_25);
      __sJT_stmt.setString(26, __sJT_26);
      __sJT_stmt.setBigDecimal(27, __sJT_27);
      __sJT_stmt.setBigDecimal(28, __sJT_28);
      __sJT_stmt.setBigDecimal(29, __sJT_29);
      __sJT_stmt.setBigDecimal(30, __sJT_30);
      __sJT_stmt.setBigDecimal(31, __sJT_31);
      __sJT_stmt.setBigDecimal(32, __sJT_32);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      myIterator = new MyIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:346^29*/
		
		List<Map> detailLst = new ArrayList<Map>();
		
		while(myIterator.next()) {
			Map map = new HashMap();
			map.put("itemId", myIterator.item_id());
			
			if( myIterator.nqty1()==null){
				map.put("nqty1", null);
			}else{
				BigDecimal nqty1 = new BigDecimal(Double.valueOf(myIterator.nqty1().toString())).setScale(4, BigDecimal.ROUND_HALF_UP);
				map.put("nqty1", nqty1);
			}
			
			if(myIterator.nqty2() == null){
				map.put("nqty2", null);
			}else{
				BigDecimal nqty2 = new BigDecimal(Double.valueOf(myIterator.nqty2().toString())).setScale(4, BigDecimal.ROUND_HALF_UP);
				map.put("nqty2", nqty2);
			}
			
			if(myIterator.nqty3() == null){
				map.put("nqty3", null);
			}else{
				BigDecimal nqty3 = new BigDecimal(Double.valueOf(myIterator.nqty3().toString())).setScale(4, BigDecimal.ROUND_HALF_UP);
				map.put("nqty3", nqty3);
			}
			
			detailLst.add(map);
		}
		
		closeDefaultContext(myCtx);
		return detailLst;
	}
}/*@lineinfo:generated-code*/class FoodRawMaterialBean_SJProfileKeys 
{
  private static FoodRawMaterialBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodRawMaterialBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodRawMaterialBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.FoodRawMaterialBean_SJProfile0");
  }
}
