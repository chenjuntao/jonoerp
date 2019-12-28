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

import pojo.businessquery.DiscountBill;

/**
 *  created by yxg, 2016-07-25
 *  Discount的Mybatis接口
 */
public interface DiscountMapper {


	public int getCount(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift);

	public List<DiscountBill> getDiscount(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift, @Param("startRow")int startRow, @Param("endRow")int endRow);
}