/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pojo.businessquery.FoodBill;
import logic.NoConnection;
import logic.businessquery.FoodBean;
import mapper.businessquery.FoodMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-07-26
 *  Food的DAO层
 */
public class FoodDao extends BaseDao {
	 
	private FoodMapper foodMapper;	
	public void setFoodMapper(FoodMapper foodMapper) {
		this.foodMapper = foodMapper;
	}

	private FoodBean foodBean;
	public void setFoodBean(FoodBean foodBean) {
		this.foodBean = foodBean;
	}
	
	public List<FoodBill> listFood(String billC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodMapper.listFood(getCom(), billC);
		}else{
			return foodBean.listFood(billC);
		}
	}

	public List<Map> ctrQuery(String startDate, String endDate)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			int tmp = foodMapper.getSum(getCom(), startDate, endDate);
			return foodMapper.ctrQuery(getCom(),tmp, startDate, endDate);
		}else{
			return foodBean.ctrQuery(startDate, endDate);
		}
	}
}