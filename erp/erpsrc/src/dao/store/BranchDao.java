/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pojo.store.Branch;
import logic.NoConnection;
import logic.store.BranchBean;
import mapper.store.BranchMapper;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.enums.BranchTypeEnum;

import dao.BaseDao;

/**
 *  created by cjt, 2016-07-25
 *  Branch的DAO层
 */
public class BranchDao extends BaseDao {
	 
	private BranchMapper branchMapper;	
	public void setBranchMapper(BranchMapper branchMapper) {
		this.branchMapper = branchMapper;
	}

	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}
	
	public int saveEntity(Branch branch)
			throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			String branchId = branch.getBranchId();
			String branchType = branch.getBranchType();
			//如果是供应商，则不插入营业时间
			if (branchType.equals(BranchType.SUPPLIER)) {
				return 0;
			}
			String dateStr = DateTimeUtil.getDateTime(new java.util.Date(), DateTimeUtil.DEFAULT_DATE_FORMAT);
			Date businessDate = SqlDateUtil.getSqlDate(DateTimeUtil.parse(DateTimeUtil.getDateTime(new java.util.Date(), DateTimeUtil.DEFAULT_DATE_FORMAT), DateTimeUtil.DEFAULT_DATE_FORMAT));
			branchMapper.createBusinessDate(getCom(), branchId, dateStr);
			return branchMapper.saveEntity(getCom(), branch);
		}else{
			return branchBean.saveEntity(branch);
		}
	}

	public int saveEntity(Branch branch, String comId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			String branchId = branch.getBranchId();
			String branchType = branch.getBranchType();
			//如果是供应商，则不插入营业时间
			if (branchType.equals(BranchType.SUPPLIER)) {
				return 0;
			}
			String dateStr = DateTimeUtil.getDateTime(new java.util.Date(), DateTimeUtil.DEFAULT_DATE_FORMAT);
			Date businessDate = SqlDateUtil.getSqlDate(DateTimeUtil.parse(DateTimeUtil.getDateTime(new java.util.Date(), DateTimeUtil.DEFAULT_DATE_FORMAT), DateTimeUtil.DEFAULT_DATE_FORMAT));
			branchMapper.createBusinessDate(getCom(), branchId, dateStr);
			
			return branchMapper.saveEntity(comId, branch);
		}else{
			return branchBean.saveEntity(branch, comId);
		}
	}
	
	public int updateEntity(String oldBranchId, Branch branch)
			throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.updateEntity(getCom(), oldBranchId, branch);
		}else{
			return branchBean.updateEntity(oldBranchId, branch);
		}
	}

	public int updateEntity(String oldBranchId, Branch branch, String comId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.updateEntity(comId, oldBranchId, branch);
		}else{
			return branchBean.updateEntity(oldBranchId, branch, comId);
		}
	}

	public boolean containsEntity(String branchId, String branchType, String comId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.containsEntity(comId, branchId, branchType);
		}else{
			return branchBean.containsEntity(branchId, branchType, comId);
		}
	}

	public int deleteEntity(String branchId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.deleteEntity(getCom(), branchId);
		}else{
			return branchBean.deleteEntity(branchId);
		}
	}

	public int enableBranch(String branchId, Integer enabled)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.enableBranch(getCom(), branchId, enabled);
		}else{
			return branchBean.enableBranch(branchId, enabled);
		}
	}

	public Branch GetBranchById(String id)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.GetBranchById(getCom(), id);
		}else{
			return branchBean.GetBranchById(id);
		}
	}

	public List<Branch> queryByTypeName(BranchTypeEnum branchType, String branchName)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.queryByTypeName(getCom(), branchType, branchName);
		}else{
			return branchBean.queryByTypeName(branchType, branchName);
		}
	}

	public List<Branch> queryRestaurant(BranchTypeEnum branchType)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.queryRestaurant(getCom(), branchType);
		}else{
			return branchBean.queryRestaurant(branchType);
		}
	}

	public List<Map> listShopByType(BranchTypeEnum branchType)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.listShopByType(getCom(), branchType);
		}else{
			return branchBean.listShopByType(branchType);
		}
	}

	public List<Map> queryById(String branchId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return branchMapper.queryById(getCom(), branchId);
		}else{
			return branchBean.queryById(branchId);
		}
	}
}