/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.DiscountBill;
import logic.NoConnection;
import logic.store.DiscountBean;
import mapper.businessquery.DiscountMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-07-25
 *  Discount的DAO层
 */
public class DiscountDao extends BaseDao {
	 
	private DiscountMapper discountMapper;	
	public void setDiscountMapper(DiscountMapper discountMapper) {
		this.discountMapper = discountMapper;
	}

	private DiscountBean discountBean;
	public void setDiscountBean(DiscountBean discountBean) {
		this.discountBean = discountBean;
	}
	

	public int getCount(String startDate, String endDate, String shopC, String period, String shift)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return discountMapper.getCount(getCom(), startDate, endDate, shopC, period, shift);
		}else{
			return discountBean.getCount(startDate, endDate, shopC, period, shift);
		}
	}

	public List<DiscountBill> getDiscount(String startDate, String endDate, String shopC, String period, String shift, int startRow, int endRow)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return discountMapper.getDiscount(getCom(), startDate, endDate, shopC, period, shift, startRow, endRow);
		}else{
			return discountBean.getDiscount(startDate, endDate, shopC, period, shift, startRow, endRow);
		}
	}
}