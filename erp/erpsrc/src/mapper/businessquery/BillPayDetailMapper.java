/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package mapper.businessquery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.businessquery.BillPayDetail;

/**
 *  created by yxg, 2016-09-13
 *  BillPayDetail的Mybatis接口
 */
public interface BillPayDetailMapper {


	public BillPayDetail sum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift, @Param("table")String table);

	public int count(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift, @Param("table")String table);

	public List<BillPayDetail> billPay(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift, @Param("table")String table, @Param("startRow")int startRow, @Param("endRow")int endRow);
}