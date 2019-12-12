/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.FoodSummary;
import logic.NoConnection;
import logic.businessquery.FoodSummaryBean;
import mapper.businessquery.FoodSummaryMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-02
 *  FoodSummary的DAO层
 */
public class FoodSummaryDao extends BaseDao {
	 
	private FoodSummaryMapper foodSummaryMapper;	
	public void setFoodSummaryMapper(FoodSummaryMapper foodSummaryMapper) {
		this.foodSummaryMapper = foodSummaryMapper;
	}

	private FoodSummaryBean foodSummaryBean;
	public void setFoodSummaryBean(FoodSummaryBean foodSummaryBean) {
		this.foodSummaryBean = foodSummaryBean;
	}
	

	public FoodSummary foodSummarySum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodSummaryMapper.foodSummarySum(getCom(), startDate, endDate, shopC);
		}else{
			return foodSummaryBean.foodSummarySum(startDate, endDate, shopC);
		}
	}

	public List<FoodSummary> foodSummary(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodSummaryMapper.foodSummary(getCom(), startDate, endDate, shopC);
		}else{
			return foodSummaryBean.foodSummary(startDate, endDate, shopC);
		}
	}
}