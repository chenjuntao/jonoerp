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

import pojo.businessquery.FoodSummary;

/**
 *  created by yxg, 2016-08-02
 *  FoodSummary的Mybatis接口
 */
public interface FoodSummaryMapper {


	public FoodSummary foodSummarySum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);

	public List<FoodSummary> foodSummary(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);
}