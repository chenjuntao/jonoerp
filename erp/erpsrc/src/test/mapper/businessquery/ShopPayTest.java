/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package test.mapper.businessquery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import mapper.businessquery.ShopPayMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.tanry.framework.acl.NoPrivilegeException;

import dao.businessquery.ShopPayDao;
import pojo.businessquery.ShopPay;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class ShopPayTest {
	  
	@Resource
	private ShopPayMapper shopPayMapper;
	 

	@Test
	public void testgetBillPay(){
		System.out.println("test getBillPay...");
		List<Map> result = shopPayMapper.getBillPay("jono", "153");
		System.out.println("test OK!");
	}

	@Test
	public void testbyDay(){
		System.out.println("test byDay...");
		List<ShopPay> result = shopPayMapper.byDay("jono", "2016-04-08", "2016-04-30");
		System.out.println("test OK!");
	}

	@Test
	public void testlistOneShopPay() throws NoPrivilegeException{
		System.out.println("test listOneShopPay...");
		List<ShopPay> shops = new ArrayList<ShopPay>();
		int tmp = shopPayMapper.listOneShopPay("jono", "2016-04-08", "2016-04-30", "10");
		if (tmp > 0){
			shops = shopPayMapper.listOneShopPay1("jono", "2016-04-08", "2016-04-30", "10");
			List<ShopPay> tmp2 = shopPayMapper.listOneShopPay2("jono", "2016-04-08", "2016-04-30", "10");
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
		System.out.println("test OK!");
	}

	@Test
	public void testlistShopPay() throws NoPrivilegeException{
		System.out.println("test listShopPay...");
		List<ShopPay> shops = new ArrayList<ShopPay>();
		int tmp = shopPayMapper.listShopPay("jono", "2016-04-08", "2016-04-30");
		if (tmp > 0){
			shops = shopPayMapper.listShopPay1("jono", "2016-04-08", "2016-04-30");
			List<ShopPay> tmp2 = shopPayMapper.listShopPay2("jono", "2016-04-08", "2016-04-30");
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
		System.out.println("test OK!");
	}

	@Test
	public void testcountByDay(){
		System.out.println("test countByDay...");
		int result = shopPayMapper.countByDay("jono", new Date(20160408), new Date(20160430));
		System.out.println("test OK!");
	}

	@Test
	public void testgetPayName(){
		System.out.println("test getPayName...");
		String result = shopPayMapper.getPayName("jono", "47");
		System.out.println("test OK!");
	}

	@Test
	public void testlistShopPayByDay(){
		System.out.println("test null...");
		Map<String, List> result = new HashMap<String, List>();
		List<String> tmp = shopPayMapper.listShopPayByDay("jono", new Date(20160408), new Date(20160430), 1, 10);
		System.out.println("test OK!");
	}
}