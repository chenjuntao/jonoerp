/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.FoodSellCount;
import logic.NoConnection;
import logic.businessquery.FoodSellCountBean;
import mapper.businessquery.FoodSellCountMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-01
 *  FoodSellCount的DAO层
 */
public class FoodSellCountDao extends BaseDao {
	 
	private FoodSellCountMapper foodSellCountMapper;	
	public void setFoodSellCountMapper(FoodSellCountMapper foodSellCountMapper) {
		this.foodSellCountMapper = foodSellCountMapper;
	}

	private FoodSellCountBean foodSellCountBean;
	public void setFoodSellCountBean(FoodSellCountBean foodSellCountBean) {
		this.foodSellCountBean = foodSellCountBean;
	}
	

	public FoodSellCount bigCategorySum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodSellCountMapper.bigCategorySum(getCom(), startDate, endDate, shopC);
		}else{
			return foodSellCountBean.bigCategorySum(startDate, endDate, shopC);
		}
	}

	public FoodSellCount littleCategorySum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodSellCountMapper.littleCategorySum(getCom(), startDate, endDate, shopC);
		}else{
			return foodSellCountBean.littleCategorySum(startDate, endDate, shopC);
		}
	}

	public List<FoodSellCount> bigCategory(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodSellCountMapper.bigCategory(getCom(), startDate, endDate, shopC);
		}else{
			return foodSellCountBean.bigCategory(startDate, endDate, shopC);
		}
	}

	public List<FoodSellCount> littleCategory(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodSellCountMapper.littleCategory(getCom(), startDate, endDate, shopC);
		}else{
			return foodSellCountBean.littleCategory(startDate, endDate, shopC);
		}
	}
}