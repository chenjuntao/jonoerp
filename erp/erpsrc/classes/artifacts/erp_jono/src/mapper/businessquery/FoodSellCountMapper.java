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

import pojo.businessquery.FoodSellCount;

/**
 *  created by yxg, 2016-08-01
 *  FoodSellCount的Mybatis接口
 */
public interface FoodSellCountMapper {


	public FoodSellCount bigCategorySum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);

	public FoodSellCount littleCategorySum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);

	public List<FoodSellCount> bigCategory(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);

	public List<FoodSellCount> littleCategory(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);
}