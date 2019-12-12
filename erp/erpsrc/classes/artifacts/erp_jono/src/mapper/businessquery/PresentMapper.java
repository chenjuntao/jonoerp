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

import pojo.businessquery.PresentBill;

/**
 *  created by yxg, 2016-08-10
 *  Present的Mybatis接口
 */
public interface PresentMapper {


	public PresentBill getDetailSum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift);

	public PresentBill getHeadSum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift);

	public int getDetailCount(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift);

	public int getHeadCount(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift);

	public List<PresentBill> getDetail(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift, @Param("startRow")int startRow, @Param("endRow")int endRow);

	public List<PresentBill> getHead(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift, @Param("startRow")int startRow, @Param("endRow")int endRow);
}