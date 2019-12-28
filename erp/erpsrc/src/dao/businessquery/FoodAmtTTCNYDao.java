/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.FoodAmtTTCNY;
import logic.NoConnection;
import logic.businessquery.FoodAmtTTCNYBean;
import mapper.businessquery.FoodAmtTTCNYMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-07-28
 *  FoodAmtTTCNY的DAO层
 */
public class FoodAmtTTCNYDao extends BaseDao {
	 
	private FoodAmtTTCNYMapper foodAmtTTCNYMapper;	
	public void setFoodAmtTTCNYMapper(FoodAmtTTCNYMapper foodAmtTTCNYMapper) {
		this.foodAmtTTCNYMapper = foodAmtTTCNYMapper;
	}

	private FoodAmtTTCNYBean foodAmtTTCNYBean;
	public void setFoodAmtTTCNYBean(FoodAmtTTCNYBean foodAmtTTCNYBean) {
		this.foodAmtTTCNYBean = foodAmtTTCNYBean;
	}
	

	public List<FoodAmtTTCNY> listFoodAmtTTCNY(String shopC, String startTime, String endTime, String categoryId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			Object theSum = foodAmtTTCNYMapper.getSum(getCom(), shopC, startTime, endTime);
			return foodAmtTTCNYMapper.listFoodAmtTTCNY(getCom(), shopC, startTime, endTime, categoryId, theSum);
		}else{
			return foodAmtTTCNYBean.listFoodAmtTTCNY(shopC, startTime, endTime, categoryId);
		}
	}
}