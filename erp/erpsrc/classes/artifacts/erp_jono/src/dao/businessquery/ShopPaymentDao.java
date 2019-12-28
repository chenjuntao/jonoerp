/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import pojo.businessquery.ShopPayment;
import logic.NoConnection;
import logic.businessquery.ShopPaymentBean;
import mapper.businessquery.ShopPaymentMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-09-08
 *  ShopPayment的DAO层
 */
public class ShopPaymentDao extends BaseDao {
	 
	private ShopPaymentMapper shopPaymentMapper;	
	public void setShopPaymentMapper(ShopPaymentMapper shopPaymentMapper) {
		this.shopPaymentMapper = shopPaymentMapper;
	}

	private ShopPaymentBean shopPaymentBean;
	public void setShopPaymentBean(ShopPaymentBean shopPaymentBean) {
		this.shopPaymentBean = shopPaymentBean;
	}
	

	public List<ShopPayment> getShopPayInfo(String startDate, String endDate)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopPaymentMapper.getShopPayInfo(getCom(), startDate, endDate);
		}else{
			return shopPaymentBean.getShopPayInfo(startDate, endDate);
		}
	}

	public List<ShopPayment> getShopPayInfoByDay(String startDate, String endDate)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopPaymentMapper.getShopPayInfoByDay(getCom(), startDate, endDate);
		}else{
			return shopPaymentBean.getShopPayInfoByDay(startDate, endDate);
		}
	}
}