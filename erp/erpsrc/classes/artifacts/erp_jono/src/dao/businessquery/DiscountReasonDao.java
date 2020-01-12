/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.DiscountReason;
import logic.NoConnection;
import logic.businessquery.DiscountReasonBean;
import mapper.businessquery.DiscountReasonMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-07-25
 *  DiscountReason的DAO层
 */
public class DiscountReasonDao extends BaseDao {
	 
	private DiscountReasonMapper discountReasonMapper;	
	public void setDiscountReasonMapper(DiscountReasonMapper discountReasonMapper) {
		this.discountReasonMapper = discountReasonMapper;
	}

	private DiscountReasonBean discountReasonBean;
	public void setDiscountReasonBean(DiscountReasonBean discountReasonBean) {
		this.discountReasonBean = discountReasonBean;
	}
	

	public DiscountReason discountReasonSum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return discountReasonMapper.discountReasonSum(getCom(), startDate, endDate, shopC);
		}else{
			return discountReasonBean.discountReasonSum(startDate, endDate, shopC);
		}
	}

	public List<DiscountReason> discountReason(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return discountReasonMapper.discountReason(getCom(), startDate, endDate, shopC);
		}else{
			return discountReasonBean.discountReason(startDate, endDate, shopC);
		}
	}
}