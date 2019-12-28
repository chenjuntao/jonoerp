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
 *  created by yxg, 2016-08-02
 *  People的Mybatis接口
 */
public interface PeopleMapper {


	public List<Map> listMan(@Param("com")String com, @Param("codeCol")String codeCol, @Param("nameCol")String nameCol);
}