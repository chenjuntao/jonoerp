/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.TableBill;
import logic.NoConnection;
import logic.businessquery.TableBean;
import mapper.businessquery.TableMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-09-09
 *  Table的DAO层
 */
public class TableDao extends BaseDao {
	 
	private TableMapper tableMapper;	
	public void setTableMapper(TableMapper tableMapper) {
		this.tableMapper = tableMapper;
	}

	private TableBean tableBean;
	public void setTableBean(TableBean tableBean) {
		this.tableBean = tableBean;
	}
	

	public String getShopName(String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return tableMapper.getShopName(getCom(), shopC);
		}else{
			return tableBean.getShopName(shopC);
		}
	}

	public List<TableBill> listTable(String startTime, String endTime, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return tableMapper.listTable(getCom(), startTime, endTime, shopC);
		}else{
			return tableBean.listTable(startTime, endTime, shopC);
		}
	}
}