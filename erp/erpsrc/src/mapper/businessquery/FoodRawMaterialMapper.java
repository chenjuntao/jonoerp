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

/**
 *  created by yxg, 2016-07-28
 *  FoodRawMaterial的Mybatis接口
 */
public interface FoodRawMaterialMapper {

	public BigDecimal getSum(@Param("com")String com, @Param("shopC")String shopC, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	public BigDecimal getAmt(@Param("com")String com, @Param("shopC")String shopC, @Param("startDate")String startDate, @Param("endDate")String endDate);

	public List<Map> getDetail(@Param("com")String com, @Param("shopC")String shopC, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("categoryId")String categoryId);

	public List<Map> getAllAmtTTCNY(@Param("com")String com, @Param("shopC")String shopC, @Param("startDate1")String startDate1, 
			@Param("endDate1")String endDate1, @Param("startDate2")String startDate2, @Param("endDate2")String endDate2, 
			@Param("startDate3")String startDate3, @Param("endDate3")String endDate3, @Param("query")String query,
			@Param("amt1")BigDecimal amt1, @Param("amt2")BigDecimal amt2, @Param("amt3")BigDecimal amt3);
}