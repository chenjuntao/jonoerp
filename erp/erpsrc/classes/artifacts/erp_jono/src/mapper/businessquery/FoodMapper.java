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

import pojo.businessquery.FoodBill;

/**
 *  created by yxg, 2016-07-26
 *  Food的Mybatis接口
 */
public interface FoodMapper {

	public int getSum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate);

	public List<FoodBill> listFood(@Param("com")String com, @Param("billC")String billC);

	public List<Map> ctrQuery(@Param("com")String com, @Param("thesum")int thesum, @Param("startDate")String startDate, @Param("endDate")String endDate);
}