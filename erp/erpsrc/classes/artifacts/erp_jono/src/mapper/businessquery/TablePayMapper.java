/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package mapper.businessquery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.businessquery.TablePay;

/**
 *  created by yxg, 2016-09-09
 *  TablePay的Mybatis接口
 */
public interface TablePayMapper {


	public List<String> listTablePay(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("shopC")String shopC);
	public List<TablePay> listTablePay1(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("shopC")String shopC, @Param("table_N")String table_N);
	public List<TablePay> listTablePay2(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("shopC")String shopC, @Param("billC")String billC);

}