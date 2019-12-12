/*@lineinfo:filename=FoodBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 *read food list
 *2014.6.20
 *by cjt
 */

package logic.businessquery;

import sqlj.runtime.ref.*;
import java.sql.*;
import java.util.*;
import logic.pool.ConnectionPool;
import logic.*;
import com.tanry.framework.acl.*;
import pojo.businessquery.FoodBill;

public class FoodBean extends ConnectionPool
{
	/*@lineinfo:generated-code*//*@lineinfo:19^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class FoodIterator 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public FoodIterator(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    cFood_CNdx = findColumn("cFood_C");
    cFood_NNdx = findColumn("cFood_N");
    cLitCls_CNdx = findColumn("cLitCls_C");
    cLitCls_NNdx = findColumn("cLitCls_N");
    sUnitNdx = findColumn("sUnit");
    nPrcNdx = findColumn("nPrc");
    nQtyNdx = findColumn("nQty");
    nAmtNdx = findColumn("nAmt");
    nDisAmtNdx = findColumn("nDisAmt");
    eSuitFlagNdx = findColumn("eSuitFlag");
    cSuitBillNdx = findColumn("cSuitBill");
    cSuitNameNdx = findColumn("cSuitName");
    eRetSendFlagNdx = findColumn("eRetSendFlag");
    sRetSendRemarkNdx = findColumn("sRetSendRemark");
    cPresentBackManNdx = findColumn("cPresentBackMan");
  }
  public String cFood_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cFood_CNdx);
  }
  private int cFood_CNdx;
  public String cFood_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cFood_NNdx);
  }
  private int cFood_NNdx;
  public String cLitCls_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cLitCls_CNdx);
  }
  private int cLitCls_CNdx;
  public String cLitCls_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cLitCls_NNdx);
  }
  private int cLitCls_NNdx;
  public String sUnit() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sUnitNdx);
  }
  private int sUnitNdx;
  public Double nPrc() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nPrcNdx);
  }
  private int nPrcNdx;
  public Double nQty() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nQtyNdx);
  }
  private int nQtyNdx;
  public Double nAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nAmtNdx);
  }
  private int nAmtNdx;
  public Double nDisAmt() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(nDisAmtNdx);
  }
  private int nDisAmtNdx;
  public String eSuitFlag() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(eSuitFlagNdx);
  }
  private int eSuitFlagNdx;
  public String cSuitBill() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cSuitBillNdx);
  }
  private int cSuitBillNdx;
  public String cSuitName() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cSuitNameNdx);
  }
  private int cSuitNameNdx;
  public String eRetSendFlag() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(eRetSendFlagNdx);
  }
  private int eRetSendFlagNdx;
  public String sRetSendRemark() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(sRetSendRemarkNdx);
  }
  private int sRetSendRemarkNdx;
  public String cPresentBackMan() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(cPresentBackManNdx);
  }
  private int cPresentBackManNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:25^70*/
	
	//将ASCII码为0（即null）的字符串转换为空字符串，前者在IE中显示会出现问题。
	private String ConvertAsciii0ToSpace(String str)
	{
		if (str != null)
		 {	
			if (str.length() == 1) 
			{
				int asciiValue = str.charAt(0);
				if (asciiValue == 0) 
				{
					return "";
				}
			}
		}
		return str;
	}

	public List<FoodBill> listFood(String billC)
		throws NoPrivilegeException,SQLException,NoConnection
	{
		List<FoodBill> foodList = new ArrayList<FoodBill>();
		DefaultContext myCtx = getDefaultContext();
		FoodIterator foodIterator = null;
		if (myCtx != null)
		{
			//遍历查找指定单据的所有出品信息
			/*@lineinfo:generated-code*//*@lineinfo:53^4*/

//  ************************************************************
//  #sql [myCtx] foodIterator = { select cFood_C, cFood_N, cLitCls_C, cLitCls_N, sUnit, nPrc, nQty, nAmt, 
//  										nDisAmt, eSuitFlag, cSuitBill, cSuitName, eRetSendFlag, sRetSendRemark, cPresentBackMan
//  										from :Com_("d_t_food_bills") where cBill_C = :billC };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("d_t_food_bills");
  String __sJT_2 = billC;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      foodIterator = new FoodIterator(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:55^64*/
			while(foodIterator.next())
			{
				FoodBill food = new FoodBill();	
				food.foodC = foodIterator.cFood_C();
				food.foodN = foodIterator.cFood_N();
				food.litClsC = foodIterator.cLitCls_C();
				food.litClsN = foodIterator.cLitCls_N();
				food.unit = foodIterator.sUnit();
				food.price = foodIterator.nPrc();
				food.quantity = foodIterator.nQty();
				food.amt = foodIterator.nAmt();
				food.disAmt = foodIterator.nDisAmt();
				food.suitFlag = foodIterator.eSuitFlag();
				food.suitBill = foodIterator.cSuitBill();
				food.suitName = foodIterator.cSuitName();
				food.retSendFlag = foodIterator.eRetSendFlag();
				food.retSendRemark = ConvertAsciii0ToSpace(foodIterator.sRetSendRemark());
				food.presentBackMan = foodIterator.cPresentBackMan();
				foodList.add(food);
			}
			foodIterator.close();
		}
		else
        {
            throw new NoConnection();
        }
		closeDefaultContext(myCtx);
		return foodList;
	}
	
	//出品点击率查询--------------------------------------------------------------
	//by yxg
	/*@lineinfo:generated-code*//*@lineinfo:88^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class SumIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public SumIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    sumNdx = findColumn("sum");
  }
  public Double sum() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sumNdx);
  }
  private int sumNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:90^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:92^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

public static class CtrIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CtrIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    CLITCLS_NNdx = findColumn("CLITCLS_N");
    CFOOD_CNdx = findColumn("CFOOD_C");
    CFOOD_NNdx = findColumn("CFOOD_N");
    itemCountNdx = findColumn("itemCount");
    tableCountNdx = findColumn("tableCount");
    ctrNdx = findColumn("ctr");
    gdpNdx = findColumn("gdp");
    salePriceNdx = findColumn("salePrice");
    stockPriceNdx = findColumn("stockPrice");
    benchPriceNdx = findColumn("benchPrice");
    stockGdpNdx = findColumn("stockGdp");
    benchGdpNdx = findColumn("benchGdp");
  }
  public String CLITCLS_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CLITCLS_NNdx);
  }
  private int CLITCLS_NNdx;
  public String CFOOD_C() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CFOOD_CNdx);
  }
  private int CFOOD_CNdx;
  public String CFOOD_N() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(CFOOD_NNdx);
  }
  private int CFOOD_NNdx;
  public Double itemCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemCountNdx);
  }
  private int itemCountNdx;
  public Double tableCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(tableCountNdx);
  }
  private int tableCountNdx;
  public Double ctr() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(ctrNdx);
  }
  private int ctrNdx;
  public Double gdp() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(gdpNdx);
  }
  private int gdpNdx;
  public Double salePrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(salePriceNdx);
  }
  private int salePriceNdx;
  public Double stockPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(stockPriceNdx);
  }
  private int stockPriceNdx;
  public Double benchPrice() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(benchPriceNdx);
  }
  private int benchPriceNdx;
  public Double stockGdp() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(stockGdpNdx);
  }
  private int stockGdpNdx;
  public Double benchGdp() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(benchGdpNdx);
  }
  private int benchGdpNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:105^4*/
	
	//出品点击率查询，by yxg
	public List<Map> ctrQuery(String startDate,String endDate)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		CtrIter iter = null;
		SumIter siter = null;
		/*@lineinfo:generated-code*//*@lineinfo:113^3*/

//  ************************************************************
//  #sql [myCtx] siter = { select
//  				count(*) sum
//  			from
//  				:Com_("D_T_FOOD_BILL") b
//  			where 
//  				(b.DTBILLTIME >= to_date(:startDate,'yyyy-mm-dd') or :startDate is null)
//  			and
//  				(b.DTBILLTIME <= to_date(:endDate,'yyyy-mm-dd') or :endDate is null)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILL");
  String __sJT_2 = startDate;
  String __sJT_3 = startDate;
  String __sJT_4 = endDate;
  String __sJT_5 = endDate;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      siter = new SumIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:122^3*/
		Double sum = 0.0;
		while(siter.next()) {
			sum = siter.sum();
			break;
		}
		/*@lineinfo:generated-code*//*@lineinfo:128^3*/

//  ************************************************************
//  #sql [myCtx] iter = { with 
//  					sun as (
//  						select 
//  							trim(a.clitcls_c),
//  							trim(a.CLITCLS_N) CLITCLS_N,
//  							trim(a.CFOOD_C) CFOOD_C,
//  							c.item_name cfood_n,
//  							sum(trim(a.NQTY)) x 
//  						from 
//  							:Com_("D_T_FOOD_BILLS") A 
//  						inner join 
//  							:Com_("d_t_food_bill") b 
//  						on 
//  							trim(a.CBILL_C) = trim(b.CBILL_C)
//  						inner join
//  							:Com_("D_T2_ITEM_META") c
//  						on
//  							trim(a.cfood_c) = c.item_id
//  						where 
//  							(b.DTBILLTIME >= to_date(:startDate,'yyyy-mm-dd') or :startDate is null)
//  						and
//  							(b.DTBILLTIME <= to_date(:endDate,'yyyy-mm-dd') or :endDate is null)
//  						and 
//  							trim(a.clitcls_c) not in ('0501','3036','3033','4041')
//  		                and
//  		                	c.item_name not like '%测试%'
//  		                and
//  		                	a.NQTY != 0
//  						group by rollup
//  							((trim(a.clitcls_c),trim(a.CLITCLS_N)),(trim(a.CFOOD_C),c.item_name))
//  						),
//  					money as(
//  							select 
//  								t.THERAPY_ID,sum(round(t.ITEM_GROSS_COUNT*p.item_price,4)) stockPrice,sum(round(t.ITEM_GROSS_COUNT*p2.item_price,4)) benchPrice
//  							from D_T2_ITEM_META m 
//  							left join d_t2_therapy t
//  							on m.ITEM_ID = t.THERAPY_ID
//  							left join D_T2_ITEM_PRICE p
//  							on t.ITEM_ID = p.ITEM_ID
//  							and p.IS_CURRENT = 1
//  							and p.PRICE_TYPE = 'BENCHMARK'
//  							left join D_T2_ITEM_PRICE p2
//  							on t.ITEM_ID = p2.ITEM_ID
//  							and p2.IS_CURRENT = 1
//  							and p2.PRICE_TYPE = 'PURCHASE'
//  							where m.ITEM_TYPE = 'PRODUCT'
//  							group by t.THERAPY_ID
//  						)
//  					select 
//  						sun.CLITCLS_N CLITCLS_N,
//  						sun.CFOOD_C CFOOD_C,
//  						sun.CFOOD_N CFOOD_N,
//  						sun.x itemCount,
//  						case when cfood_c is not null then :sum end tableCount,
//  						case when cfood_c is not null then round(x/:sum,4)*100 end ctr,
//                          case when cfood_c is null 
//                               then round(sun.x/(
//                                    select sum(case when cfood_c is not null then x else 0 end) from sun
//                                    ),4)*100
//                               end   gdp,
//                          p.item_price salePrice,
//                          money.stockPrice,
//                          decode(p.item_price,null,null,0,null,round((p.item_price-money.stockPrice)/p.item_price,4)) stockGdp,
//                          money.benchPrice,
//                          decode(p.item_price,null,null,0,null,round((p.item_price-money.benchPrice)/p.item_price,4)) benchGdp
//  					from 
//  						sun
//  					left join
//  						money
//  					on sun.CFOOD_C = money.THERAPY_ID
//  					left join
//  						:Com_("D_T2_ITEM_PRICE") p
//  					on sun.CFOOD_C = p.item_id
//  					and p.price_type = 'SALE'
//  					where
//  						sun.x != 0
//  					order by 
//  						1,2
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T_FOOD_BILLS");
  String __sJT_2 = Com_("d_t_food_bill");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = startDate;
  String __sJT_5 = startDate;
  String __sJT_6 = endDate;
  String __sJT_7 = endDate;
  Double __sJT_8 = sum;
  Double __sJT_9 = sum;
  String __sJT_10 = Com_("D_T2_ITEM_PRICE");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, FoodBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_stmt.setString(6, __sJT_6);
      __sJT_stmt.setString(7, __sJT_7);
      __sJT_stmt.setDoubleWrapper(8, __sJT_8);
      __sJT_stmt.setDoubleWrapper(9, __sJT_9);
      __sJT_stmt.setString(10, __sJT_10);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new CtrIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:207^3*/
		
		List<Map> nodeLst = new ArrayList<Map>();
		while(iter.next()) {
			Map<String, Object> node = new HashMap<String, Object>();
			node.put("CLITCLS_N", iter.CLITCLS_N());
			node.put("CFOOD_C", iter.CFOOD_C());
			node.put("CFOOD_N", iter.CFOOD_N());
			node.put("itemCount", iter.itemCount());
			node.put("tableCount", iter.tableCount());
			node.put("ctr", iter.ctr());
			node.put("gdp", iter.gdp());
			node.put("stockPrice", iter.stockPrice());
			node.put("benchPrice", iter.benchPrice());
			node.put("stockGdp", iter.stockGdp());
			node.put("benchGdp", iter.benchGdp());
			node.put("salePrice", iter.salePrice());
			nodeLst.add(node);
		}
		iter.close();
		closeDefaultContext(myCtx);
		return nodeLst;
	}
}/*@lineinfo:generated-code*/class FoodBean_SJProfileKeys 
{
  private static FoodBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new FoodBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private FoodBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.businessquery.FoodBean_SJProfile0");
  }
}
