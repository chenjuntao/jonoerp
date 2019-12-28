/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.IncomeDetail;
import logic.NoConnection;
import logic.businessquery.IncomeDetailBean;
import mapper.businessquery.IncomeDetailMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-02
 *  IncomeDetail的DAO层
 */
public class IncomeDetailDao extends BaseDao {
	 
	private IncomeDetailMapper incomeDetailMapper;	
	public void setIncomeDetailMapper(IncomeDetailMapper incomeDetailMapper) {
		this.incomeDetailMapper = incomeDetailMapper;
	}

	private IncomeDetailBean incomeDetailBean;
	public void setIncomeDetailBean(IncomeDetailBean incomeDetailBean) {
		this.incomeDetailBean = incomeDetailBean;
	}
	

	public IncomeDetail daySum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return incomeDetailMapper.daySum(getCom(), startDate, endDate, shopC);
		}else{
			return incomeDetailBean.daySum(startDate, endDate, shopC);
		}
	}

	public IncomeDetail monthSum(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return incomeDetailMapper.monthSum(getCom(), startDate, endDate, shopC);
		}else{
			return incomeDetailBean.monthSum(startDate, endDate, shopC);
		}
	}

	public List<IncomeDetail> monthIncomeDetail(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return incomeDetailMapper.monthIncomeDetail(getCom(), startDate, endDate, shopC);
		}else{
			return incomeDetailBean.monthIncomeDetail(startDate, endDate, shopC);
		}
	}

	public List<IncomeDetail> dayIncomeDetail(String startDate, String endDate, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return incomeDetailMapper.dayIncomeDetail(getCom(), startDate, endDate, shopC);
		}else{
			return incomeDetailBean.dayIncomeDetail(startDate, endDate, shopC);
		}
	}
}