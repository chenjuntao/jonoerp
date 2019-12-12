/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.FoodCategory;
import logic.NoConnection;
import logic.businessquery.FoodCategoryBean;
import mapper.businessquery.FoodCategoryMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-07-28
 *  FoodCategory的DAO层
 */
public class FoodCategoryDao extends BaseDao {
	 
	private FoodCategoryMapper foodCategoryMapper;	
	public void setFoodCategoryMapper(FoodCategoryMapper foodCategoryMapper) {
		this.foodCategoryMapper = foodCategoryMapper;
	}

	private FoodCategoryBean foodCategoryBean;
	public void setFoodCategoryBean(FoodCategoryBean foodCategoryBean) {
		this.foodCategoryBean = foodCategoryBean;
	}
	

	public BigDecimal getAmt(String shopC, String startDate, String endDate)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodCategoryMapper.getAmt(getCom(), shopC, startDate, endDate);
		}else{
			return foodCategoryBean.getAmt(shopC, startDate, endDate);
		}
	}

	public List<FoodCategory> listCategory(String shopC, String startTime, String endTime)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodCategoryMapper.listCategory(getCom(), shopC, startTime, endTime);
		}else{
			return foodCategoryBean.listCategory(shopC, startTime, endTime);
		}
	}

	public List<FoodCategory> listRawCategory()
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodCategoryMapper.listRawCategory(getCom());
		}else{
			return foodCategoryBean.listRawCategory();
		}
	}
}