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

import com.tanry.framework.util.enums.BranchTypeEnum;

import pojo.store.Branch;

/**
 *  created by cjt, 2016-07-25
 *  Branch的Mybatis接口
 */
public interface BranchMapper {
	
	public int saveEntity(@Param("com")String com, @Param("branch")Branch branch);

	public int createBusinessDate(@Param("com")String com, @Param("branchId")String branchId, @Param("businessDate")String businessDate);

	public int updateEntity(@Param("com")String com, @Param("oldBranchId")String oldBranchId, @Param("branch")Branch branch);

	public boolean containsEntity(@Param("com")String com, @Param("branchId")String branchId, @Param("branchType")String branchType);

	public int deleteEntity(@Param("com")String com, @Param("branchId")String branchId);

	public int enableBranch(@Param("com")String com, @Param("branchId")String branchId, @Param("enabled")Integer enabled);

	public Branch GetBranchById(@Param("com")String com, @Param("id")String id);

	public List<Branch> queryByTypeName(@Param("com")String com, @Param("branchType")BranchTypeEnum branchType, @Param("branchName")String branchName);

	public List<Branch> queryRestaurant(@Param("com")String com, @Param("branchType")BranchTypeEnum branchType);

	public List<Map> listShop(@Param("com")String com);

	public List<Map> listShopByType(@Param("com")String com, @Param("branchType")BranchTypeEnum branchType);

	public List<Map> queryById(@Param("com")String com, @Param("branchId")String branchId);
}