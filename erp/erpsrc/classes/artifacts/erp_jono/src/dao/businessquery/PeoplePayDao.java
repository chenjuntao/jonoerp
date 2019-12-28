/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.PeoplePayBean;
import mapper.businessquery.PeoplePayMapper;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-04
 *  PeoplePay的DAO层
 */
public class PeoplePayDao extends BaseDao {
	 
	private PeoplePayMapper peoplePayMapper;	
	public void setPeoplePayMapper(PeoplePayMapper peoplePayMapper) {
		this.peoplePayMapper = peoplePayMapper;
	}

	private PeoplePayBean peoplePayBean;
	public void setPeoplePayBean(PeoplePayBean peoplePayBean) {
		this.peoplePayBean = peoplePayBean;
	}
	

	public List<Map> query(java.util.Date startDate, java.util.Date endDate, String branchId, String peopleId)
		throws NoPrivilegeException, SQLException, NoConnection, ParseException{
		if(beUseMySql){
			String query = " 1=1";
			if (!TextUtil.isEmpty(branchId)) { 
				query = query + " AND b.cBranch_C = '" + branchId + "'";
			}
			if (!TextUtil.isEmpty(peopleId)) { 
				query = query + " AND b.cSettleMan = '" + peopleId + "'";
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			return peoplePayMapper.query(getCom(), sdf.format(startDate), sdf.format(endDate), query);
		}else{
			return peoplePayBean.query(startDate, endDate, branchId, peopleId);
		}
	}

	public List<Map> queryColumns(java.util.Date startDate, java.util.Date endDate, String branchId, String peopleId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			String query = " 1=1";
			if (!TextUtil.isEmpty(branchId)) { 
				query = query + " AND b.cBranch_C = '" + branchId + "'";
			}
			if (!TextUtil.isEmpty(peopleId)) { 
				query = query + " AND b.cSettleMan = '" + peopleId + "'";
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			return peoplePayMapper.queryColumns(getCom(), sdf.format(startDate), sdf.format(endDate), query);
		}else{
			return peoplePayBean.queryColumns(startDate, endDate, branchId, peopleId);
		}
	}
}