/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年6月14日 by cjt
 * Last edited on 2016年6月14日 by cjt
 * 
 * 说明： 
 */
package dao.restapi;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.restapi.CompanyBean;
import mapper.restapi.CompanyMapper;

import org.springframework.stereotype.Repository;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 * @author cjt
 */
@Repository
public class CompanyDao extends BaseDao {
	 
//	@Resource
	private CompanyMapper companyMapper;	
	public void setCompanyMapper(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}

//	@Resource
	private CompanyBean companyBean;
	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	public int saveEntity(Map map) throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return companyMapper.saveEntity("com", map);
		}else{
			return companyBean.saveEntity(map);
		}
	}
	
	public List<Map> listCompany() throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return companyMapper.listCompany("com");
		}else{
			return companyBean.listCompany();
		}
	}
	
	public String getDbNameByComId(String companyId)throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return companyMapper.getDbNameByComId("com", companyId);
		}else{
			return companyBean.getDbNameByComId(companyId);
		}
	}
}
