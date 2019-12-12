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
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodRawMaterialBean;
import mapper.businessquery.FoodRawMaterialMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-07-28
 *  FoodRawMaterial的DAO层
 */
public class FoodRawMaterialDao extends BaseDao {
	 
	private FoodRawMaterialMapper foodRawMaterialMapper;	
	public void setFoodRawMaterialMapper(FoodRawMaterialMapper foodRawMaterialMapper) {
		this.foodRawMaterialMapper = foodRawMaterialMapper;
	}

	private FoodRawMaterialBean foodRawMaterialBean;
	public void setFoodRawMaterialBean(FoodRawMaterialBean foodRawMaterialBean) {
		this.foodRawMaterialBean = foodRawMaterialBean;
	}


	public BigDecimal getAmt(String shopC, String startDate, String endDate)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			BigDecimal result1 = foodRawMaterialMapper.getSum(getCom(), shopC, startDate, endDate);
			BigDecimal result2 = foodRawMaterialMapper.getAmt(getCom(), shopC, startDate, endDate);
			if (result1 != null) {
				result1 = (BigDecimal)result1;
			}else{
				result1 = BigDecimal.ZERO;
			}
			if (result2 != null) {
				result2 = (BigDecimal)result2;
			}else{
				result2 = BigDecimal.ZERO;
			}
			BigDecimal result3 = result1.subtract(result2);
			result3 = result3.setScale(2, BigDecimal.ROUND_HALF_UP);
			return result3;
		}else{
			return foodRawMaterialBean.getAmt(shopC, startDate, endDate);
		}
	}

	public List<Map> getDetail(String shopC, String startDate, String endDate, String categoryId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return foodRawMaterialMapper.getDetail(getCom(), shopC, startDate, endDate, categoryId);
		}else{
			return foodRawMaterialBean.getDetail(shopC, startDate, endDate, categoryId);
		}
	}

	public List<Map> getAllAmtTTCNY(String shopC, String startDate1, String endDate1, String startDate2, String endDate2, String startDate3, String endDate3, String query)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			BigDecimal amt1 = getAmt(shopC, startDate1, endDate1);
			BigDecimal amt2 = getAmt(shopC, startDate2, endDate2);
			BigDecimal amt3 = getAmt(shopC, startDate3, endDate3);
			return foodRawMaterialMapper.getAllAmtTTCNY(getCom(), shopC, startDate1, endDate1, startDate2, endDate2, startDate3, endDate3, query, amt1, amt2, amt3);
		}else{
			return foodRawMaterialBean.getAllAmtTTCNY(shopC, startDate1, endDate1, startDate2, endDate2, startDate3, endDate3, query);
		}
	}
}