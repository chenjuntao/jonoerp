/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.ReturnReason;
import logic.NoConnection;
import logic.businessquery.ReturnReasonBean;
import mapper.businessquery.ReturnReasonMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-22
 *  ReturnReason的DAO层
 */
public class ReturnReasonDao extends BaseDao {
	 
	private ReturnReasonMapper returnReasonMapper;	
	public void setReturnReasonMapper(ReturnReasonMapper returnReasonMapper) {
		this.returnReasonMapper = returnReasonMapper;
	}

	private ReturnReasonBean returnReasonBean;
	public void setReturnReasonBean(ReturnReasonBean returnReasonBean) {
		this.returnReasonBean = returnReasonBean;
	}
	

	public ReturnReason returnReasonSum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return returnReasonMapper.returnReasonSum(getCom(), startDate, endDate, shopC);
		}else{
			return returnReasonBean.returnReasonSum(startDate, endDate, shopC);
		}
	}

	public List<ReturnReason> returnReason(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return returnReasonMapper.returnReason(getCom(), startDate, endDate, shopC);
		}else{
			return returnReasonBean.returnReason(startDate, endDate, shopC);
		}
	}
}