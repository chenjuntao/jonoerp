/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.ReturnBill;
import logic.NoConnection;
import logic.store.ReturnBean;
import mapper.businessquery.ReturnMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-22
 *  Return的DAO层
 */
public class ReturnDao extends BaseDao {
	 
	private ReturnMapper returnMapper;	
	public void setReturnMapper(ReturnMapper returnMapper) {
		this.returnMapper = returnMapper;
	}

	private ReturnBean returnBean;
	public void setReturnBean(ReturnBean returnBean) {
		this.returnBean = returnBean;
	}
	

	public ReturnBill getReturnSum(String startDate, String endDate, String shopC, String period, String shift)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return returnMapper.getReturnSum(getCom(), startDate, endDate, shopC, period, shift);
		}else{
			return returnBean.getReturnSum(startDate, endDate, shopC, period, shift);
		}
	}

	public int getHeadCount(String startDate, String endDate, String shopC, String period, String shift)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return returnMapper.getHeadCount(getCom(), startDate, endDate, shopC, period, shift);
		}else{
			return returnBean.getHeadCount(startDate, endDate, shopC, period, shift);
		}
	}

	public List<ReturnBill> getReturn(String startDate, String endDate, String shopC, String period, String shift, int startRow, int endRow)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return returnMapper.getReturn(getCom(), startDate, endDate, shopC, period, shift, startRow, endRow);
		}else{
			return returnBean.getReturn(startDate, endDate, shopC, period, shift, startRow, endRow);
		}
	}
}