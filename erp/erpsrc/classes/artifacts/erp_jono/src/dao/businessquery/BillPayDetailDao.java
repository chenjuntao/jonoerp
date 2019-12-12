/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.BillPayDetail;
import logic.NoConnection;
import logic.businessquery.BillPayDetailBean;
import mapper.businessquery.BillPayDetailMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-09-13
 *  BillPayDetail的DAO层
 */
public class BillPayDetailDao extends BaseDao {
	 
	private BillPayDetailMapper billPayDetailMapper;	
	public void setBillPayDetailMapper(BillPayDetailMapper billPayDetailMapper) {
		this.billPayDetailMapper = billPayDetailMapper;
	}

	private BillPayDetailBean billPayDetailBean;
	public void setBillPayDetailBean(BillPayDetailBean billPayDetailBean) {
		this.billPayDetailBean = billPayDetailBean;
	}
	

	public BillPayDetail sum(String startDate, String endDate, String shopC, String period, String shift, String table)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return billPayDetailMapper.sum(getCom(), startDate, endDate, shopC, period, shift, table);
		}else{
			return billPayDetailBean.sum(startDate, endDate, shopC, period, shift, table);
		}
	}

	public int count(String startDate, String endDate, String shopC, String period, String shift, String table)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return billPayDetailMapper.count(getCom(), startDate, endDate, shopC, period, shift, table);
		}else{
			return billPayDetailBean.count(startDate, endDate, shopC, period, shift, table);
		}
	}

	public List<BillPayDetail> billPay(String startDate, String endDate, String shopC, String period, String shift, String table, int startRow, int endRow)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return billPayDetailMapper.billPay(getCom(), startDate, endDate, shopC, period, shift, table, startRow, endRow);
		}else{
			return billPayDetailBean.billPay(startDate, endDate, shopC, period, shift, table, startRow, endRow);
		}
	}
}