/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年6月16日 by cjt
 * Last edited on 2016年6月16日 by cjt
 * 
 * 说明： 
 */
package mapper.businessquery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.businessquery.FreeBill;

/**
 * @author cjt
 */
public interface FreeBillMapper {
	
	//指定门店的指定单据的单据信息
	public List<FreeBill> getHead(@Param("com")String com, @Param("startDate")String startDate,
			@Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift,
			@Param("startRow")int startRow, @Param("pageNum")int pageNum);
	
	public int getHeadCount(@Param("com")String com, @Param("startDate")String startDate,
			@Param("endDate")String endDate, @Param("shopC")String shopC, @Param("period")String period, @Param("shift")String shift);
	
	public List<Map> getDetail(@Param("com")String com, @Param("queryText")String queryText, 
			@Param("startRow")int startRow, @Param("pageNum")int pageNum);
}
