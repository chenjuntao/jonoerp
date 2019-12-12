/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年6月16日 by cjt
 * Last edited on 2016年6月16日 by cjt
 * 
 * 说明： 
 */
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FreeBillBean;
import mapper.businessquery.FreeBillMapper;
import pojo.businessquery.FreeBill;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

public class FreeBillDao extends BaseDao {

	private FreeBillMapper freeBillMapper;

	public void setFreeBillMapper(FreeBillMapper freeBillMapper) {
		this.freeBillMapper = freeBillMapper;
	}

	private FreeBillBean freeBillBean;

	public void setFreeBillBean(FreeBillBean freeBillBean) {
		this.freeBillBean = freeBillBean;
	}

	// 获取表头信息
	public List<FreeBill> getHead(String startDate, String endDate, String shopC, String period, String shift,
			int startRow, int pageNum) throws NoPrivilegeException, SQLException, NoConnection {
		if (beUseMySql) {
			return freeBillMapper.getHead(getCom(), startDate, endDate, shopC, period, shift, startRow, pageNum);
		} else {
			return freeBillBean.getHead(startDate, endDate, shopC, period, shift, startRow, startRow + pageNum);
		}
	}

	public int getHeadCount(String startDate, String endDate, String shopC, String period, String shift)
			throws NoPrivilegeException, SQLException, NoConnection {
		if (beUseMySql) {
			return freeBillMapper.getHeadCount(getCom(), startDate, endDate, shopC, period, shift);
		} else {
			return freeBillBean.getHeadCount(startDate, endDate, shopC, period, shift);
		}
	}

	public List<Map> getDetail(String queryText,
			int startRow, int pageNum) throws NoPrivilegeException, SQLException, NoConnection {
		if (beUseMySql) {
			return freeBillMapper.getDetail(getCom(), queryText, startRow, pageNum);
		} else {
			return freeBillBean.getDetail(queryText, startRow, startRow + pageNum);
		}
	}

}
