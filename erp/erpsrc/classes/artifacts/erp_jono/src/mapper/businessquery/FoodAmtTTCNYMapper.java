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

import pojo.businessquery.FoodAmtTTCNY;

/**
 *  created by yxg, 2016-07-28
 *  FoodAmtTTCNY的Mybatis接口
 */
public interface FoodAmtTTCNYMapper {

	public Object getSum(@Param("com")String com, @Param("shopC")String shopC, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	public List<FoodAmtTTCNY> listFoodAmtTTCNY(@Param("com")String com, @Param("shopC")String shopC, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("categoryId")String categoryId, @Param("theSum")Object theSum);
}