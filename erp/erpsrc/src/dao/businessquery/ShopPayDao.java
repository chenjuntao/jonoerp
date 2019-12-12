/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.businessquery.ShopPay;
import logic.NoConnection;
import logic.businessquery.ShopPayBean;
import mapper.businessquery.ShopPayMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-08-26
 *  ShopPay的DAO层
 */
public class ShopPayDao extends BaseDao {
	 
	private ShopPayMapper shopPayMapper;	
	public void setShopPayMapper(ShopPayMapper shopPayMapper) {
		this.shopPayMapper = shopPayMapper;
	}

	private ShopPayBean shopPayBean;
	public void setShopPayBean(ShopPayBean shopPayBean) {
		this.shopPayBean = shopPayBean;
	}
	

	public List<Map> getBillPay(String CBILL_C)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopPayMapper.getBillPay(getCom(), CBILL_C);
		}else{
			return shopPayBean.getBillPay(CBILL_C);
		}
	}

	public List<ShopPay> byDay(String startTime, String endTime)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopPayMapper.byDay(getCom(), startTime, endTime);
		}else{
			return shopPayBean.byDay(startTime, endTime);
		}
	}

	public List<ShopPay> listOneShopPay(String startTime, String endTime, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			List<ShopPay> shops = new ArrayList<ShopPay>();
			int tmp = shopPayMapper.listOneShopPay(getCom(), startTime, endTime, shopC);
			if (tmp > 0){
				shops = shopPayMapper.listOneShopPay1(getCom(), startTime, endTime, shopC);
				List<ShopPay> tmp2 = shopPayMapper.listOneShopPay2(getCom(), startTime, endTime, shopC);
				for (int j=0; j<tmp2.size(); j++)
				{
					for (int i=0; i<shops.size(); i++) 
					{
						ShopPay shop = shops.get(i);
						if (shop.shopC.equals(tmp2.get(i).getShopC()))
						{
							shop.pay.put((tmp2.get(i).getPayCode()+"separator"+tmp2.get(i).getPayCode()), tmp2.get(i).getPayAmt());
							break;
						}
					}
				}
			}
			return shops;
		}else{
			return shopPayBean.listOneShopPay(startTime, endTime, shopC);
		}
	}

	public List<ShopPay> listShopPay(String startTime, String endTime)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			List<ShopPay> shops = new ArrayList<ShopPay>();
			int tmp = shopPayMapper.listShopPay(getCom(), startTime, endTime);
			if (tmp > 0){
				shops = shopPayMapper.listShopPay1(getCom(), startTime, endTime);
				List<ShopPay> tmp2 = shopPayMapper.listShopPay2(getCom(), startTime, endTime);
				for (int j=0; j<tmp2.size(); j++)
				{
					for (int i=0; i<shops.size(); i++) 
					{
						ShopPay shop = shops.get(i);
						if (shop.shopC.equals(tmp2.get(i).getShopC()))
						{
							shop.pay.put((tmp2.get(i).getPayCode()+"separator"+tmp2.get(i).getPayCode()), tmp2.get(i).getPayAmt());
							break;
						}
					}
				}
			}
			return shops;
		}else{
			return shopPayBean.listShopPay(startTime, endTime);
		}
	}

	public int countByDay(Date startTime, Date endTime)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopPayMapper.countByDay(getCom(), startTime, endTime);
		}else{
			return shopPayBean.countByDay(startTime, endTime);
		}
	}

	public String getPayName(String payC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			return shopPayMapper.getPayName(getCom(), payC);
		}else{
			return shopPayBean.getPayName(payC);
		}
	}

	public Map<String,List> listShopPayByDay(Date startTime, Date endTime, int startRow, int endRow)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			Map<String, List> result = new HashMap<String, List>();
			List<String> tmp = shopPayMapper.listShopPayByDay(getCom(), startTime, endTime, startRow, endRow);
			for (int i = 0; i < tmp.size(); i++) {
				String date = tmp.get(i);
				List shopList = listShopPay(date, date); 
				if(shopList.size()>0) {
					result.put(date, shopList);
				}
			}
			return result;
		}else{
			return shopPayBean.listShopPayByDay(startTime, endTime, startRow, endRow);
		}
		
	}
}
			