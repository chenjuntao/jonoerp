/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年6月8日 by cjt
 * Last edited on 2016年6月8日 by cjt
 * 
 * 说明： 
 */
package mapper.restapi;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 公司/企业DAO
 */
public interface CompanyMapper {
	public int saveEntity(@Param("com")String com, @Param("map")Map map);
	public List<Map> listCompany(@Param("com")String com);
	public String getDbNameByComId(@Param("com")String com, @Param("companyId")String companyId);
	
	//DDL测试
	public void ddlTest(@Param("com")String com);
}
