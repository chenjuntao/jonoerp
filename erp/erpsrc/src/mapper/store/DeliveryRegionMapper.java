/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package mapper.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.store.DeliveryRegion;
import pojo.store.DeliveryRegionBranch;

/**
 *  created by cjt, 2016-07-29
 *  DeliveryRegion的Mybatis接口
 */
public interface DeliveryRegionMapper {

	//DeliveryRegionBranch相关--------------------------------------------------------
	public int saveRegionBranch(@Param("com")String com, @Param("regionBranch")DeliveryRegionBranch regionBranch);

	public int updateRegionBranch(@Param("com")String com, @Param("oldRegionId")String oldRegionId, @Param("regionBranch")DeliveryRegionBranch regionBranch);

	public int deleteRegionBranch(@Param("com")String com, @Param("branchId")String branchId);
	
	//DeliveryRegion相关--------------------------------------------------------
	public int saveRegion(@Param("com")String com, @Param("region")DeliveryRegion region);

	public int updateRegion(@Param("com")String com, @Param("oldRegionId")String oldRegionId, @Param("region")DeliveryRegion region);

	public int deleteRegion(@Param("com")String com, @Param("regionId")String regionId);

	public int enableRegion(@Param("com")String com, @Param("regionId")String regionId, @Param("enabled")Integer enabled);

	public List<DeliveryRegion> queryAllRegion(@Param("com")String com);

	public DeliveryRegion queryRegionById(@Param("com")String com, @Param("regionId")String regionId);
	
	public List<DeliveryRegion> queryRegionByLcId(@Param("com")String com, @Param("lcId")String lcId);
	
	public DeliveryRegion getRegionByBranch(@Param("com")String com, @Param("branchId")String branchId);
}