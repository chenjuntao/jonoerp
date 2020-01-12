/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.BusinessCount;
import logic.NoConnection;
import logic.businessquery.BusinessCountBean;
import mapper.businessquery.BusinessCountMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-09-13
 *  BusinessCount的DAO层
 */
public class BusinessCountDao extends BaseDao {
	 
	private BusinessCountMapper businessCountMapper;	
	public void setBusinessCountMapper(BusinessCountMapper businessCountMapper) {
		this.businessCountMapper = businessCountMapper;
	}

	private BusinessCountBean businessCountBean;
	public void setBusinessCountBean(BusinessCountBean businessCountBean) {
		this.businessCountBean = businessCountBean;
	}
	

	public BusinessCount shiftSum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return businessCountMapper.shiftSum(getCom(), startDate, endDate, shopC);
		}else{
			return businessCountBean.shiftSum(startDate, endDate, shopC);
		}
	}

	public BusinessCount periodSum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return businessCountMapper.periodSum(getCom(), startDate, endDate, shopC);
		}else{
			return businessCountBean.periodSum(startDate, endDate, shopC);
		}
	}

	public BusinessCount floorSum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return businessCountMapper.floorSum(getCom(), startDate, endDate, shopC);
		}else{
			return businessCountBean.floorSum(startDate, endDate, shopC);
		}
	}

	public List<BusinessCount> shift(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return businessCountMapper.shift(getCom(), startDate, endDate, shopC);
		}else{
			return businessCountBean.shift(startDate, endDate, shopC);
		}
	}

	public List<BusinessCount> period(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return businessCountMapper.period(getCom(), startDate, endDate, shopC);
		}else{
			return businessCountBean.period(startDate, endDate, shopC);
		}
	}

	public List<BusinessCount> floor(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return businessCountMapper.floor(getCom(), startDate, endDate, shopC);
		}else{
			return businessCountBean.floor(startDate, endDate, shopC);
		}
	}

}