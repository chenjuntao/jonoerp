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

/**
 *  created by yxg, 2016-08-04
 *  PeoplePay的Mybatis接口
 */
public interface PeoplePayMapper {


	public List<Map> query(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("query")String query);

	public List<Map> queryColumns(@Param("com")String com, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("query")String query);
}