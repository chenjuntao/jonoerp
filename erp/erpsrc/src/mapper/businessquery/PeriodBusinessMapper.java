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
 *  created by yxg, 2016-08-08
 *  PeriodBusiness的Mybatis接口
 */
public interface PeriodBusinessMapper {


	public List<Map> listPeriodBusiness(@Param("com")String com, @Param("queryText")String queryText);
}