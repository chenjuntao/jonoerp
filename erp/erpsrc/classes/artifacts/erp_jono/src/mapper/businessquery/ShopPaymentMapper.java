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

import pojo.businessquery.ShopPayment;

/**
 *  created by yxg, 2016-09-08
 *  ShopPayment的Mybatis接口
 */
public interface ShopPaymentMapper {


	public List<ShopPayment> getShopPayInfo(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate);

	public List<ShopPayment> getShopPayInfoByDay(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate);
}