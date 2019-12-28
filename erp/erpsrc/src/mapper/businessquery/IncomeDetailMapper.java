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

import pojo.businessquery.IncomeDetail;

/**
 *  created by yxg, 2016-08-02
 *  IncomeDetail的Mybatis接口
 */
public interface IncomeDetailMapper {


	public IncomeDetail daySum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);

	public IncomeDetail monthSum(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);

	public List<IncomeDetail> monthIncomeDetail(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);

	public List<IncomeDetail> dayIncomeDetail(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("shopC")String shopC);
}