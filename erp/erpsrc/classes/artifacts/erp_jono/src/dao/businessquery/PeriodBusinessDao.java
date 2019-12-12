/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.PeriodBusinessBean;
import mapper.businessquery.PeriodBusinessMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-08
 *  PeriodBusiness的DAO层
 */
public class PeriodBusinessDao extends BaseDao {
	 
	private PeriodBusinessMapper periodBusinessMapper;	
	public void setPeriodBusinessMapper(PeriodBusinessMapper periodBusinessMapper) {
		this.periodBusinessMapper = periodBusinessMapper;
	}

	private PeriodBusinessBean periodBusinessBean;
	public void setPeriodBusinessBean(PeriodBusinessBean periodBusinessBean) {
		this.periodBusinessBean = periodBusinessBean;
	}
	

	public List<Map> listPeriodBusiness(String queryText)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return periodBusinessMapper.listPeriodBusiness(getCom(), queryText);
		}else{
			return periodBusinessBean.listPeriodBusiness(queryText);
		}
	}
}