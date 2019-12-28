/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.PresentReason;
import logic.NoConnection;
import logic.businessquery.PresentReasonBean;
import mapper.businessquery.PresentReasonMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-19
 *  PresentReason的DAO层
 */
public class PresentReasonDao extends BaseDao {
	 
	private PresentReasonMapper presentReasonMapper;	
	public void setPresentReasonMapper(PresentReasonMapper presentReasonMapper) {
		this.presentReasonMapper = presentReasonMapper;
	}

	private PresentReasonBean presentReasonBean;
	public void setPresentReasonBean(PresentReasonBean presentReasonBean) {
		this.presentReasonBean = presentReasonBean;
	}
	

	public PresentReason presentReasonSum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return presentReasonMapper.presentReasonSum(getCom(), startDate, endDate, shopC);
		}else{
			return presentReasonBean.presentReasonSum(startDate, endDate, shopC);
		}
	}

	public List<PresentReason> presentReason(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return presentReasonMapper.presentReason(getCom(), startDate, endDate, shopC);
		}else{
			return presentReasonBean.presentReason(startDate, endDate, shopC);
		}
	}
}