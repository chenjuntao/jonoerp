/*@lineinfo:filename=StorageInOutSummaryBean*//*@lineinfo:user-code*//*@lineinfo:1^1*/package logic.reportmanage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.HashMap;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import sqlj.runtime.ref.DefaultContext;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class StorageInOutSummaryBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(StorageInOutSummaryBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:27^2*/

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
    item_nameNdx = findColumn("item_name");
    item_dimensionNdx = findColumn("item_dimension");
    branch_nameNdx = findColumn("branch_name");
    storage_nameNdx = findColumn("storage_name");
    itemcountinNdx = findColumn("itemcountin");
    itemcountinmoneyNdx = findColumn("itemcountinmoney");
    itemcountoutNdx = findColumn("itemcountout");
    itemcountoutmoneyNdx = findColumn("itemcountoutmoney");
    distributionOutNdx = findColumn("distributionOut");
    outerOutNdx = findColumn("outerOut");
    rejectinstorageNdx = findColumn("rejectinstorage");
    putinstorageNdx = findColumn("putinstorage");
    distributionNdx = findColumn("distribution");
    antiauditinNdx = findColumn("antiauditin");
    antiauditoutNdx = findColumn("antiauditout");
    drejectNdx = findColumn("dreject");
    dreject2Ndx = findColumn("dreject2");
    prejectNdx = findColumn("preject");
    preject2Ndx = findColumn("preject2");
    rawlossNdx = findColumn("rawloss");
    dishlossNdx = findColumn("dishloss");
    allocateiteminNdx = findColumn("allocateitemin");
    allocateitemoutNdx = findColumn("allocateitemout");
    checkstorageinNdx = findColumn("checkstoragein");
    checkstorageoutNdx = findColumn("checkstorageout");
    theoryDeductNdx = findColumn("theoryDeduct");
    publicstorageinNdx = findColumn("publicstoragein");
    publicstorageoutNdx = findColumn("publicstorageout");
    crossstorageoutNdx = findColumn("crossstorageout");
    semiesinNdx = findColumn("semiesin");
    rawoutNdx = findColumn("rawout");
    scllNdx = findColumn("scll");
    scclNdx = findColumn("sccl");
    sctlNdx = findColumn("sctl");
    fgllNdx = findColumn("fgll");
    orgiCountNdx = findColumn("orgiCount");
    orgicountmoneyNdx = findColumn("orgicountmoney");
    resultcountNdx = findColumn("resultcount");
    resultcountmoneyNdx = findColumn("resultcountmoney");
  }
  public String item_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_idNdx);
  }
  private int item_idNdx;
  public String item_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_nameNdx);
  }
  private int item_nameNdx;
  public String item_dimension() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(item_dimensionNdx);
  }
  private int item_dimensionNdx;
  public String branch_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(branch_nameNdx);
  }
  private int branch_nameNdx;
  public String storage_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(storage_nameNdx);
  }
  private int storage_nameNdx;
  public Double itemcountin() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemcountinNdx);
  }
  private int itemcountinNdx;
  public Double itemcountinmoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemcountinmoneyNdx);
  }
  private int itemcountinmoneyNdx;
  public Double itemcountout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemcountoutNdx);
  }
  private int itemcountoutNdx;
  public Double itemcountoutmoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(itemcountoutmoneyNdx);
  }
  private int itemcountoutmoneyNdx;
  public Double distributionOut() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(distributionOutNdx);
  }
  private int distributionOutNdx;
  public Double outerOut() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(outerOutNdx);
  }
  private int outerOutNdx;
  public Double rejectinstorage() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(rejectinstorageNdx);
  }
  private int rejectinstorageNdx;
  public Double putinstorage() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(putinstorageNdx);
  }
  private int putinstorageNdx;
  public Double distribution() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(distributionNdx);
  }
  private int distributionNdx;
  public Double antiauditin() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(antiauditinNdx);
  }
  private int antiauditinNdx;
  public Double antiauditout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(antiauditoutNdx);
  }
  private int antiauditoutNdx;
  public Double dreject() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(drejectNdx);
  }
  private int drejectNdx;
  public Double dreject2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(dreject2Ndx);
  }
  private int dreject2Ndx;
  public Double preject() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(prejectNdx);
  }
  private int prejectNdx;
  public Double preject2() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(preject2Ndx);
  }
  private int preject2Ndx;
  public Double rawloss() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(rawlossNdx);
  }
  private int rawlossNdx;
  public Double dishloss() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(dishlossNdx);
  }
  private int dishlossNdx;
  public Double allocateitemin() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(allocateiteminNdx);
  }
  private int allocateiteminNdx;
  public Double allocateitemout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(allocateitemoutNdx);
  }
  private int allocateitemoutNdx;
  public Double checkstoragein() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(checkstorageinNdx);
  }
  private int checkstorageinNdx;
  public Double checkstorageout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(checkstorageoutNdx);
  }
  private int checkstorageoutNdx;
  public Double theoryDeduct() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(theoryDeductNdx);
  }
  private int theoryDeductNdx;
  public Double publicstoragein() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(publicstorageinNdx);
  }
  private int publicstorageinNdx;
  public Double publicstorageout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(publicstorageoutNdx);
  }
  private int publicstorageoutNdx;
  public Double crossstorageout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(crossstorageoutNdx);
  }
  private int crossstorageoutNdx;
  public Double semiesin() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(semiesinNdx);
  }
  private int semiesinNdx;
  public Double rawout() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(rawoutNdx);
  }
  private int rawoutNdx;
  public Double scll() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(scllNdx);
  }
  private int scllNdx;
  public Double sccl() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(scclNdx);
  }
  private int scclNdx;
  public Double sctl() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(sctlNdx);
  }
  private int sctlNdx;
  public Double fgll() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(fgllNdx);
  }
  private int fgllNdx;
  public Double orgiCount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(orgiCountNdx);
  }
  private int orgiCountNdx;
  public Double orgicountmoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(orgicountmoneyNdx);
  }
  private int orgicountmoneyNdx;
  public Double resultcount() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(resultcountNdx);
  }
  private int resultcountNdx;
  public Double resultcountmoney() 
    throws java.sql.SQLException 
  {
    return resultSet.getDoubleWrapper(resultcountmoneyNdx);
  }
  private int resultcountmoneyNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:74^4*/
	
	/*@lineinfo:generated-code*//*@lineinfo:76^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class CountIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CountIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    countsNdx = findColumn("counts");
  }
  public Integer counts() 
    throws java.sql.SQLException 
  {
    return resultSet.getIntWrapper(countsNdx);
  }
  private int countsNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:78^3*/
	
	public Map getColmunType(){
		Map map  = new HashMap();
		map.put("item_id", "String");
		map.put("item_name", "String");
		return map;
	}
	
	public Map getColmunPrefix(){
		Map map  = new HashMap();
		map.put("item_id", "io");
		map.put("item_name", "m");
		return map;
	}
	
	/**
	 * 计算count时，尽量优化sql
	 */
	public Integer count(String queryStr)
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		CountIter countIter = null;
		
		String[] strs = queryStr.split("\\|_\\|");
		
		/*@lineinfo:generated-code*//*@lineinfo:104^3*/

//  ************************************************************
//  #sql [myCtx] countIter = { WITH
//  			    items AS
//  			    (
//  			        SELECT
//  			            io.*,
//  			            m.item_name,
//  			            item_dimension
//  			        FROM
//  			            :Com_("D_T0_STORAGE_IN_OUT") io
//  		            LEFT JOIN
//                      	:Com_("D_T2_BRANCH") b 
//                      ON
//                          io.BRANCH_ID = b.BRANCH_ID
//  			        INNER JOIN
//  			            :Com_("D_T2_ITEM_META") m
//  			        ON
//  			            io.item_id = m.item_id
//  			        WHERE
//  			            1 = 1 :strs[1]+strs[3]
//  			    )
//  			    ,
//  			    begins AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            (
//  			                SELECT
//  			                    t.branch_id,
//  			                    t.STORAGE_ID,
//  			                    t.ITEM_ID,
//  			                    t.item_name,
//  			                    t.item_dimension,
//  			                    t.RESULT_COUNT orgiCount,
//  			                    row_number() over(partition BY branch_id,STORAGE_ID,ITEM_ID, item_name ,
//  			                    item_dimension ORDER BY OPERATION_TIME DESC ) rn
//  			                FROM
//  			                    items t )
//  			        WHERE
//  			            rn=1
//  			    )
//  			    ,
//  			    items2 AS
//  			    (
//  			        SELECT
//  			            io.*,
//  			            m.item_name,
//  			            item_dimension
//  			        FROM
//  			            :Com_("D_T0_STORAGE_IN_OUT") io
//  		            LEFT JOIN
//                      	:Com_("D_T2_BRANCH") b 
//                      ON
//                          io.BRANCH_ID = b.BRANCH_ID
//  			        INNER JOIN
//  			            :Com_("D_T2_ITEM_META") m
//  			        ON
//  			            io.item_id = m.item_id
//  			        WHERE
//  			            1 = 1 :strs[2]+strs[3]
//  			    )
//  			    ,
//  			    ends AS
//  			    (
//  			        SELECT
//  			            *
//  			        FROM
//  			            (
//  			                SELECT
//  			                    t.branch_id,
//  			                    t.STORAGE_ID,
//  			                    t.ITEM_ID,
//  			                    t.item_name,
//  			                    t.item_dimension,
//  			                    t.RESULT_COUNT resultcount,
//  			                    row_number() over(partition BY branch_id,STORAGE_ID,ITEM_ID, item_name ,
//  			                    item_dimension ORDER BY OPERATION_TIME DESC ) rn
//  			                FROM
//  			                    items2 t )
//  			        WHERE
//  			            rn=1
//  			    )
//  			    ,
//  			    c AS
//  			    (
//  			        SELECT
//  			            io.BRANCH_ID ,
//  			            io.STORAGE_ID,
//  			            io.ITEM_ID ,
//  			            m.item_name,
//  			            m.item_dimension
//  			        FROM
//  			            :Com_("D_T0_STORAGE_IN_OUT") io
//  		            LEFT JOIN
//                      	:Com_("D_T2_BRANCH") b 
//                      ON
//                          io.BRANCH_ID = b.BRANCH_ID
//  			        INNER JOIN
//  			            :Com_("D_T2_ITEM_META") m
//  			        ON
//  			            io.item_id = m.item_id
//  			        WHERE
//  			            1 = 1 :strs[0]+strs[3]
//  			        GROUP BY
//  			            io.BRANCH_ID ,
//  			            io.STORAGE_ID,
//  			            io.ITEM_ID,
//  			            m.item_name,
//  			            m.item_dimension
//  			    )
//  			SELECT
//  			    COUNT(*) counts
//  			FROM
//  			    c
//  			FULL JOIN
//  			    begins s
//  			ON
//  			    c.item_id = s.item_id AND c.STORAGE_ID = s.STORAGE_ID };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = strs[1]+strs[3];
  String __sJT_5 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_6 = Com_("D_T2_BRANCH");
  String __sJT_7 = Com_("D_T2_ITEM_META");
  String __sJT_8 = strs[2]+strs[3];
  String __sJT_9 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = Com_("D_T2_ITEM_META");
  String __sJT_12 = strs[0]+strs[3];
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutSummaryBean_SJProfileKeys.getKey(0), 0);
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
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      countIter = new CountIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:222^61*/
		
		Integer counts = 0;
		while(countIter.next()) {
			counts = countIter.counts();
		}
		
		closeDefaultContext(myCtx);
		countIter.close();
		
		return counts;
	}
	
	
	public List<Map> query(int startRow, int endRow,String queryStr,String sortStr)  
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String[] strs = queryStr.split("\\|_\\|");
		
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:243^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  			    *
//  			    FROM
//  			        (
//  			            SELECT
//  			                t.*,
//  			                ROUND((orgiCount* t.ITEM_PRICE),4 )   orgicountmoney,
//  			                ROUND((resultcount* t.ITEM_PRICE),4 ) resultcountmoney,
//  			                ROWNUM                                rowNumber
//  			            FROM
//  			                (
//  			                    WITH
//  			                        items AS
//  			                        (
//  			                            SELECT
//  			                                io.*,
//  			                                m.item_name,
//  			                                item_dimension
//  			                            FROM
//  			                                :Com_("D_T0_STORAGE_IN_OUT") io
//  			                            LEFT JOIN
//  			                                :Com_("D_T2_BRANCH") b
//  			                            ON
//  			                                io.BRANCH_ID = b.BRANCH_ID
//  			                            INNER JOIN
//  			                                :Com_("D_T2_ITEM_META") m
//  			                            ON
//  			                                io.item_id = m.item_id
//  			                            WHERE
//  			                                1 = 1 :strs[1]+strs[3]
//  			                        )
//  			                        ,
//  			                        begins AS
//  			                        (
//  			                            SELECT
//  			                                *
//  			                            FROM
//  			                                (
//  			                                    SELECT
//  			                                        t.branch_id,
//  			                                        t.STORAGE_ID,
//  			                                        t.ITEM_ID,
//  			                                        t.item_name,
//  			                                        t.item_dimension,
//  			                                        t.RESULT_COUNT orgiCount,
//  			                                        row_number() over(partition BY branch_id,STORAGE_ID,ITEM_ID,
//  			                                        item_name ,item_dimension ORDER BY OPERATION_TIME DESC ) rn
//  			                                    FROM
//  			                                        items t )
//  			                            WHERE
//  			                                rn=1
//  			                        )
//  			                        ,
//  			                        items2 AS
//  			                        (
//  			                            SELECT
//  			                                io.*,
//  			                                m.item_name,
//  			                                item_dimension
//  			                            FROM
//  			                                :Com_("D_T0_STORAGE_IN_OUT") io
//  			                            LEFT JOIN
//  			                                :Com_("D_T2_BRANCH") b
//  			                            ON
//  			                                io.BRANCH_ID = b.BRANCH_ID
//  			                            INNER JOIN
//  			                                :Com_("D_T2_ITEM_META") m
//  			                            ON
//  			                                io.item_id = m.item_id
//  			                            WHERE
//  			                                1 = 1 :strs[2]+strs[3]
//  			                        )
//  			                        ,
//  			                        ends AS
//  			                        (
//  			                            SELECT
//  			                                *
//  			                            FROM
//  			                                (
//  			                                    SELECT
//  			                                        t.branch_id,
//  			                                        t.STORAGE_ID,
//  			                                        t.ITEM_ID,
//  			                                        t.item_name,
//  			                                        t.item_dimension,
//  			                                        t.RESULT_COUNT resultcount,
//  			                                        row_number() over(partition BY branch_id,STORAGE_ID,ITEM_ID,
//  			                                        item_name ,item_dimension ORDER BY OPERATION_TIME DESC ) rn
//  			                                    FROM
//  			                                        items2 t )
//  			                            WHERE
//  			                                rn=1
//  			                        )
//  			                        ,
//  			                        c AS
//  			                        (
//  			                            SELECT
//  			                                io.BRANCH_ID ,
//  			                                io.STORAGE_ID,
//  			                                io.ITEM_ID ,
//  			                                m.item_name,
//  			                                m.item_dimension,
//  			                                ROUND(SUM(NVL(ITEM_COUNT_IN,0)),4) itemCountIn,
//  			                                ROUND(SUM(NVL(ITEM_COUNT_IN,0)* NVL(io.ITEM_UNIT_PRICE,0)),4)
//  			                                                                    itemCountInMoney,
//  			                                ROUND(SUM(NVL(ITEM_COUNT_OUT,0)),4) itemCountOut,
//  			                                ROUND(SUM(NVL(ITEM_COUNT_OUT,0)* NVL(io.ITEM_UNIT_PRICE,0)),4)
//  			                                itemCountOutMoney,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CGRK'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) putinstorage,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'JSRK'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) rejectinstorage,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSRK'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) distribution,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSCK'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) distributionOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSFSH'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) antiauditIn,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSFSH'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) antiauditOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSTH'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) dreject,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PSTH'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) dreject2,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CGTH'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) preject,
//  			                                    
//  		                                    SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CGTH'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) preject2,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'YLBS'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) rawLoss,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CPBS'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) dishLoss,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'DB'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) allocateitemIn,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'DB'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) allocateitemOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PD'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) checkstorageIn,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'PD'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) checkstorageOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'LLKU'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) theoryDeduct,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CPRK'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) publicstorageIn,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'CPCK'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) publicstorageOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'YKCK'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) crossstorageOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'WBCH'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) outerOut,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'SCLL'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) scll,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'SCCL'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) sccl,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'SCTL'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) sctl,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'FGLL'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) fgll,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'SSRK'
//  			                                        THEN NVL(ITEM_COUNT_IN,0)
//  			                                        ELSE 0
//  			                                    END) semiesin,
//  			                                SUM(
//  			                                    CASE
//  			                                        WHEN io.REASON = 'SSHL'
//  			                                        THEN NVL(ITEM_COUNT_OUT,0)
//  			                                        ELSE 0
//  			                                    END) rawout
//  			                            FROM
//  			                                :Com_("D_T0_STORAGE_IN_OUT") io
//  			                            LEFT JOIN
//  			                                :Com_("D_T2_BRANCH") b
//  			                            ON
//  			                                io.BRANCH_ID = b.BRANCH_ID
//  			                            INNER JOIN
//  			                                :Com_("D_T2_ITEM_META") m
//  			                            ON
//  			                                io.item_id = m.item_id
//  			                            WHERE
//  			                                1 = 1 :strs[0]+strs[3]
//  			                            GROUP BY
//  			                                io.BRANCH_ID ,
//  			                                io.STORAGE_ID,
//  			                                io.ITEM_ID,
//  			                                m.item_name,
//  			                                m.item_dimension
//  			                        )
//  			                    SELECT
//  			                        NVL(s.orgiCount,0)                            orgiCount,
//  			                        NVL(s2.resultcount,0)                          resultcount,
//  			                        NVL(p.ITEM_PRICE,0)                            ITEM_PRICE,
//  			                        DECODE(s.item_id,NULL,c.ITEM_ID,s.item_id)                      item_id,
//  			                        DECODE(s.item_id,NULL,c.item_name,s.item_name)                  item_name,
//  			                        DECODE(s.item_dimension,NULL,c.item_dimension,s.item_dimension) item_dimension,
//  			                        NVL(itemCountIn-checkstorageIn,0)                               itemCountIn,
//  			                        NVL(itemCountInMoney-checkstorageIn*p.ITEM_PRICE,0)             itemCountInMoney,
//  			                        NVL(itemCountOut-checkstorageOut,0)      itemCountOut,
//  			                        NVL(itemCountOutMoney-checkstorageOut*p.ITEM_PRICE,0) itemCountOutMoney,
//  			                        NVL(putinstorage,0)      putinstorage,
//  			                        NVL(distribution,0)      distribution ,
//  			                        NVL(antiauditIn,0)       antiauditIn,
//  			                        NVL(antiauditOut,0)      antiauditOut,
//  			                        NVL(dreject,0)           dreject,
//  			                        NVL(dreject2,0)          dreject2,
//  			                        NVL(preject,0)           preject,
//  			                        NVL(preject2,0)           preject2,
//  			                        NVL(rawLoss,0)           rawLoss,
//  			                        NVL(dishLoss,0)          dishLoss,
//  			                        NVL(allocateitemIn,0)    allocateitemIn,
//  			                        NVL(allocateitemOut,0)   allocateitemOut,
//  			                        NVL(outerOut,0)          outerOut,
//  			                        NVL(checkstorageIn,0)    checkstorageIn,
//  			                        NVL(checkstorageOut,0)   checkstorageOut,
//  			                        NVL(theoryDeduct,0)      theoryDeduct,
//  			                        NVL(publicstorageIn,0)   publicstorageIn,
//  			                        NVL(publicstorageOut,0)  publicstorageOut,
//  			                        NVL(crossstorageOut,0)   crossstorageOut,
//  			                        NVL(distributionOut,0)   distributionOut,
//  			                        NVL(rejectinstorage,0)   rejectinstorage,
//  			                        NVL(scll,0)              scll,
//  			                        NVL(sccl,0)              sccl,
//  			                        NVL(sctl,0)              sctl,
//  			                        NVL(fgll,0)              fgll,
//  			                        NVL(semiesin,0)          semiesin,
//  			                        NVL(rawout,0)            rawout,
//  			                        b.BRANCH_NAME,
//  			                        bs.STORAGE_NAME
//  			                    FROM
//  			                        c
//  			                    FULL JOIN
//  			                        begins s
//  			                    ON
//  			                        c.item_id = s.item_id
//  			                    AND c.STORAGE_ID = s.STORAGE_ID
//  			                    LEFT JOIN
//  			                        ends s2
//  			                    ON
//  			                        DECODE(s.item_id,NULL,c.ITEM_ID,s.item_id) = s2.item_id
//  			                    AND DECODE(s.STORAGE_ID ,NULL,c.STORAGE_ID,s.STORAGE_ID) = s2.STORAGE_ID
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_BRANCH") b
//  			                    ON
//  			                        b.BRANCH_ID = DECODE( s.branch_id ,NULL,c.branch_id,s.branch_id)
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_BRANCH_STORAGE") bs
//  			                    ON
//  			                        bs.STORAGE_ID = DECODE(s.STORAGE_ID ,NULL,c.STORAGE_ID,s.STORAGE_ID)
//  			                    LEFT JOIN
//  			                        :Com_("D_T2_ITEM_PRICE") p
//  			                    ON
//  			                        DECODE(s.item_id,NULL,c.ITEM_ID,s.item_id) = p.item_id
//  			                    AND p.PRICE_TYPE = 'BENCHMARK'
//  			                    AND p.IS_CURRENT = 1) t
//  			            WHERE
//  			                ROWNUM < :endRow)
//  			    WHERE
//  			        rowNumber >= :startRow };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_2 = Com_("D_T2_BRANCH");
  String __sJT_3 = Com_("D_T2_ITEM_META");
  String __sJT_4 = strs[1]+strs[3];
  String __sJT_5 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_6 = Com_("D_T2_BRANCH");
  String __sJT_7 = Com_("D_T2_ITEM_META");
  String __sJT_8 = strs[2]+strs[3];
  String __sJT_9 = Com_("D_T0_STORAGE_IN_OUT");
  String __sJT_10 = Com_("D_T2_BRANCH");
  String __sJT_11 = Com_("D_T2_ITEM_META");
  String __sJT_12 = strs[0]+strs[3];
  String __sJT_13 = Com_("D_T2_BRANCH");
  String __sJT_14 = Com_("D_T2_BRANCH_STORAGE");
  String __sJT_15 = Com_("D_T2_ITEM_PRICE");
  int __sJT_16 = endRow;
  int __sJT_17 = startRow;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, StorageInOutSummaryBean_SJProfileKeys.getKey(0), 1);
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
      __sJT_stmt.setInt(16, __sJT_16);
      __sJT_stmt.setInt(17, __sJT_17);
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

/*@lineinfo:user-code*//*@lineinfo:603^34*/
		List<Map> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	private List<Map> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<Map> headerLst = new ArrayList<Map>();
		
		while(headerIter.next()) {
			Map header = new HashMap();
			
			header.put("item_id", headerIter.item_id());
			header.put("item_name", headerIter.item_name());
			header.put("item_dimension", headerIter.item_dimension());
			header.put("branch_name", headerIter.branch_name());
			header.put("storage_name", headerIter.storage_name());
			header.put("itemcountin", headerIter.itemcountin());
			header.put("itemcountinmoney", headerIter.itemcountinmoney());
			header.put("itemcountout", headerIter.itemcountout());
			header.put("itemcountoutmoney", headerIter.itemcountoutmoney());
			header.put("putinstorage", headerIter.putinstorage());
			header.put("distribution", headerIter.distribution());
			header.put("antiauditin", headerIter.antiauditin());
			header.put("antiauditout", headerIter.antiauditout());
			header.put("dreject", headerIter.dreject());
			header.put("dreject2", headerIter.dreject2());
			
			header.put("preject", headerIter.preject());
			header.put("preject2", headerIter.preject2());
			
			header.put("rawloss", headerIter.rawloss());
			header.put("dishloss", headerIter.dishloss());
			header.put("allocateitemin", headerIter.allocateitemin());
			header.put("allocateitemout", headerIter.allocateitemout());
			header.put("checkstoragein", headerIter.checkstoragein());
			header.put("checkstorageout", headerIter.checkstorageout());
			header.put("publicstoragein", headerIter.publicstoragein());
			header.put("publicstorageout", headerIter.publicstorageout());
			header.put("crossstorageout", headerIter.crossstorageout());
			header.put("theoryDeduct", headerIter.theoryDeduct());
			header.put("orgiCount", headerIter.orgiCount());
			header.put("orgicountmoney", headerIter.orgicountmoney());
			header.put("resultcount", headerIter.resultcount());
			header.put("resultcountmoney", headerIter.resultcountmoney());
			
			header.put("distributionOut", headerIter.distributionOut());
			header.put("rejectinstorage", headerIter.rejectinstorage());
			
			header.put("scll", headerIter.scll());
			header.put("sccl", headerIter.sccl());
			header.put("sctl", headerIter.sctl());
			header.put("fgll", headerIter.fgll());
			header.put("semiesin", headerIter.semiesin());
			header.put("rawout", headerIter.rawout());
			
			header.put("outerOut", headerIter.outerOut());
			
			headerLst.add(header);
		}
		return headerLst;
	}
}/*@lineinfo:generated-code*/class StorageInOutSummaryBean_SJProfileKeys 
{
  private static StorageInOutSummaryBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new StorageInOutSummaryBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private StorageInOutSummaryBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.reportmanage.StorageInOutSummaryBean_SJProfile0");
  }
}
