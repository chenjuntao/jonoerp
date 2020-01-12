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

import pojo.businessquery.ShopBill;

/**
 *  created by yxg, 2016-08-25
 *  Shop的Mybatis接口
 */
public interface ShopMapper {


	public int countByDay(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime);

	public List<ShopBill> byDay(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime);

	public List<ShopBill> listShop(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime);

	public List<String> listShopByDay(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("startRow")int startRow, @Param("endRow")int endRow);

}