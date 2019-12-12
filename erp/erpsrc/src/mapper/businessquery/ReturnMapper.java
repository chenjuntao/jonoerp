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

import pojo.businessquery.ReturnBill;

/**
 *  created by yxg, 2016-08-22
 *  Return的Mybatis接口
 */
public interface ReturnMapper {


	public ReturnBill getReturnSum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift);

	public int getHeadCount(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift);

	public List<ReturnBill> getReturn(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift, @Param("startRow")int startRow, @Param("endRow")int endRow);
}