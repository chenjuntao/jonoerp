/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.store;

import java.sql.SQLException;
import java.util.List;

import pojo.store.DeliveryRegion;
import pojo.store.DeliveryRegionBranch;
import logic.NoConnection;
import logic.store.DeliveryRegionBean;
import mapper.store.DeliveryRegionMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by cjt, 2016-07-29
 *  DeliveryRegion和DeliveryRegionBranch的DAO层
 */
public class DeliveryRegionDao extends BaseDao {
	 
	private DeliveryRegionMapper deliveryRegionMapper;	
	public void setDeliveryRegionMapper(DeliveryRegionMapper deliveryRegionMapper) {
		this.deliveryRegionMapper = deliveryRegionMapper;
	}

	private DeliveryRegionBean deliveryRegionBean;
	public void setDeliveryRegionBean(DeliveryRegionBean deliveryRegionBean) {
		this.deliveryRegionBean = deliveryRegionBean;
	}
	
	//DeliveryRegionBranch相关--------------------------------------------------------
	public int saveRegionBranch(DeliveryRegionBranch regionBranch)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.saveRegionBranch(getCom(), regionBranch);
		}else{
			return deliveryRegionBean.saveRegionBranch(regionBranch);
		}
	}

	public int updateRegionBranch(String oldRegionId, DeliveryRegionBranch regionBranch)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.updateRegionBranch(getCom(), oldRegionId, regionBranch);
		}else{
			return deliveryRegionBean.updateRegionBranch(oldRegionId, regionBranch);
		}
	}

	public int deleteRegionBranch(String regionBranchId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.deleteRegionBranch(getCom(), regionBranchId);
		}else{
			return deliveryRegionBean.deleteRegionBranch(regionBranchId);
		}
	}

	//DeliveryRegion相关--------------------------------------------------------
	public int saveRegion(DeliveryRegion region)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.saveRegion(getCom(), region);
		}else{
			return deliveryRegionBean.saveRegion(region);
		}
	}

	public int updateRegion(String oldRegionId, DeliveryRegion region)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.updateRegion(getCom(), oldRegionId, region);
		}else{
			return deliveryRegionBean.updateRegion(oldRegionId, region);
		}
	}

	public int deleteRegion(String regionId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.deleteRegion(getCom(), regionId);
		}else{
			return deliveryRegionBean.deleteRegion(regionId);
		}
	}

	//启用/停用区域
	public int enableRegion(String regionId, Integer enabled)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.enableRegion(getCom(), regionId, enabled);
		}else{
			return deliveryRegionBean.enableRegion(regionId, enabled);
		}
	}

	//查询全部区域
	public List<DeliveryRegion> queryAllRegion()
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.queryAllRegion(getCom());
		}else{
			return deliveryRegionBean.queryAllRegion();
		}
	}

	//根据区域编码regionId查询区域
	public DeliveryRegion queryRegionById(String regionId)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.queryRegionById(getCom(), regionId);
		}else{
			return deliveryRegionBean.queryRegionById(regionId);
		}
	}
	
	//根据区域所属的物流中心编码lcId查询该物流中心下的下所有区域
	public List<DeliveryRegion> queryRegionByLcId(String lcId)
			throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.queryRegionByLcId(getCom(), lcId);
		}else{
			return deliveryRegionBean.queryRegionByLcId(lcId);
		}
	}
	
	//根据餐厅编码branchId查询该餐厅所属的区域
	public DeliveryRegion getRegionByBranch(String branchId) 
			throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return deliveryRegionMapper.getRegionByBranch(getCom(), branchId);
		}else{
			return deliveryRegionBean.getRegionByBranch(branchId);
		}
	}
}