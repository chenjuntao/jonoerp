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
import logic.businessquery.PeopleBean;
import mapper.businessquery.PeopleMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-02
 *  People的DAO层
 */
public class PeopleDao extends BaseDao {
	 
	private PeopleMapper peopleMapper;	
	public void setPeopleMapper(PeopleMapper peopleMapper) {
		this.peopleMapper = peopleMapper;
	}

	private PeopleBean peopleBean;
	public void setPeopleBean(PeopleBean peopleBean) {
		this.peopleBean = peopleBean;
	}
	

	public List<Map> listMan(String codeCol, String nameCol)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return peopleMapper.listMan(getCom(), codeCol, nameCol);
		}else{
			return peopleBean.listMan(codeCol, nameCol);
		}
	}
}