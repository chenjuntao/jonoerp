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

import pojo.businessquery.DiscountReason;

/**
 *  created by yxg, 2016-07-25
 *  DiscountReason的Mybatis接口
 */
public interface DiscountReasonMapper {


	public DiscountReason discountReasonSum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);

	public List<DiscountReason> discountReason(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);
}