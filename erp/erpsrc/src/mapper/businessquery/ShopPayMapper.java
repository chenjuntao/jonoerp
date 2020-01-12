/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package mapper.businessquery;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.businessquery.ShopPay;

/**
 *  created by yxg, 2016-08-26
 *  ShopPay的Mybatis接口
 */
public interface ShopPayMapper {


	public List<Map> getBillPay(@Param("com")String com, @Param("CBILL_C")String CBILL_C);

	public List<ShopPay> byDay(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime);

	public int listOneShopPay(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("shopC")String shopC);
	
	public List<ShopPay> listOneShopPay1(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("shopC")String shopC);
	
	public List<ShopPay> listOneShopPay2(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("shopC")String shopC);

	public int listShopPay(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	public List<ShopPay> listShopPay1(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	public List<ShopPay> listShopPay2(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime);

	public int countByDay(@Param("com")String com, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

	public String getPayName(@Param("com")String com, @Param("payC")String payC);

	public List<String> listShopPayByDay(@Param("com")String com, @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("startRow")int startRow, @Param("endRow")int endRow);
}