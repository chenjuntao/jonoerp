/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package mapper.businessquery;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.businessquery.FoodCategory;

/**
 *  created by yxg, 2016-07-28
 *  FoodCategory的Mybatis接口
 */
public interface FoodCategoryMapper {


	public BigDecimal getAmt(@Param("com")String com, @Param("shopC")String shopC, @Param("startDate")String startDate, @Param("endDate")String endDate);

	public List<FoodCategory> listCategory(@Param("com")String com, @Param("shopC")String shopC, @Param("startTime")String startTime, @Param("endTime")String endTime);

	public List<FoodCategory> listRawCategory(@Param("com")String com);
}