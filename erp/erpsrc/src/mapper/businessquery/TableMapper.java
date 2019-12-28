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

import pojo.businessquery.TableBill;

/**
 *  created by yxg, 2016-09-09
 *  Table的Mybatis接口
 */
public interface TableMapper {


	public String getShopName(@Param("com")String com, @Param("shopC")String shopC);

	public List<TableBill> listTable(@Param("com")String com, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("shopC")String shopC);
}