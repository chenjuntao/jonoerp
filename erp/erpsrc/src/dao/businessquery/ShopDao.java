/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.ShopBean;
import mapper.businessquery.ShopMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import pojo.businessquery.ShopBill;
import sqlj.runtime.ref.DefaultContext;
import dao.BaseDao;

/**
 *  created by yxg, 2016-08-25
 *  Shop的DAO层
 */
public class ShopDao extends BaseDao {
	 
	private ShopMapper shopMapper;	
	public void setShopMapper(ShopMapper shopMapper) {
		this.shopMapper = shopMapper;
	}

	private ShopBean shopBean;
	public void setShopBean(ShopBean shopBean) {
		this.shopBean = shopBean;
	}
	

	public int countByDay(String startTime, String endTime)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopMapper.countByDay(getCom(), startTime, endTime);
		}else{
			return shopBean.countByDay(startTime, endTime);
		}
	}

	public List<ShopBill> byDay(String startTime, String endTime)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopMapper.byDay(getCom(), startTime, endTime);
		}else{
			return shopBean.byDay(startTime, endTime);
		}
	}

	public List<ShopBill> listShop(String startTime, String endTime)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopMapper.listShop(getCom(), startTime, endTime);
		}else{
			return shopBean.listShop(startTime, endTime);
		}
	}
	
	public List<ShopBill> listShop(String startTime, String endTime, DefaultContext myCtx)
			throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return shopMapper.listShop(getCom(), startTime, endTime);
			}else{
				return shopBean.listShop(startTime, endTime, myCtx);
			}
		}
	
	public Map<String, List> listShopByDay(String startTime, String endTime,int startRow, int endRow)
			throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				List<String> ls = shopMapper.listShopByDay(getCom(), startTime, endTime, startRow, endRow);
				Map<String, List> result = new HashMap<String, List>();
				for (int i = 0; i < ls.size(); i++) {
					String date = ls.get(i);
					List shopList = listShop(date, date); 
					if(shopList.size()>0) {
						result.put(date, shopList);
					}
				}
				return result;
			}else{
				return shopBean.listShopByDay(startTime, endTime, startRow, endRow);
			}
		}
}
