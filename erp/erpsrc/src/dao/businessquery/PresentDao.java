/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.PresentBill;
import logic.NoConnection;
import logic.store.PresentBean;
import mapper.businessquery.PresentMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-10
 *  Present的DAO层
 */
public class PresentDao extends BaseDao {
	 
	private PresentMapper presentMapper;	
	public void setPresentMapper(PresentMapper presentMapper) {
		this.presentMapper = presentMapper;
	}

	private PresentBean presentBean;
	public void setPresentBean(PresentBean presentBean) {
		this.presentBean = presentBean;
	}
	

	public PresentBill getDetailSum(String startDate, String endDate, String shopC, String period, String shift)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return presentMapper.getDetailSum(getCom(), startDate, endDate, shopC, period, shift);
		}else{
			return presentBean.getDetailSum(startDate, endDate, shopC, period, shift);
		}
	}

	public PresentBill getHeadSum(String startDate, String endDate, String shopC, String period, String shift)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return presentMapper.getHeadSum(getCom(), startDate, endDate, shopC, period, shift);
		}else{
			return presentBean.getHeadSum(startDate, endDate, shopC, period, shift);
		}
	}

	public int getDetailCount(String startDate, String endDate, String shopC, String period, String shift)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return presentMapper.getDetailCount(getCom(), startDate, endDate, shopC, period, shift);
		}else{
			return presentBean.getDetailCount(startDate, endDate, shopC, period, shift);
		}
	}

	public int getHeadCount(String startDate, String endDate, String shopC, String period, String shift)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return presentMapper.getHeadCount(getCom(), startDate, endDate, shopC, period, shift);
		}else{
			return presentBean.getHeadCount(startDate, endDate, shopC, period, shift);
		}
	}

	public List<PresentBill> getDetail(String startDate, String endDate, String shopC, String period, String shift, int startRow, int endRow)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return presentMapper.getDetail(getCom(), startDate, endDate, shopC, period, shift, startRow, endRow);
		}else{
			return presentBean.getDetail(startDate, endDate, shopC, period, shift, startRow, endRow);
		}
	}

	public List<PresentBill> getHead(String startDate, String endDate, String shopC, String period, String shift, int startRow, int endRow)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return presentMapper.getHead(getCom(), startDate, endDate, shopC, period, shift, startRow, endRow);
		}else{
			return presentBean.getHead(startDate, endDate, shopC, period, shift, startRow, endRow);
		}
	}
}