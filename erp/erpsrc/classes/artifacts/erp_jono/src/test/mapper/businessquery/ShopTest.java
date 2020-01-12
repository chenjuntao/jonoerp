/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package test.mapper.businessquery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import mapper.businessquery.ShopMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.ShopBill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class ShopTest {
	  
	@Resource
	private ShopMapper shopMapper;
	 

	@Test
	public void testcountByDay(){
		System.out.println("test countByDay...");
		int result = shopMapper.countByDay("jono", "2016-04-08", "2016-04-30");
		System.out.println("test OK!");
	}

	@Test
	public void testbyDay(){
		System.out.println("test byDay...");
		List<ShopBill> result = shopMapper.byDay("jono", "2016-04-08", "2016-04-30");
		System.out.println("test OK!");
	}

	@Test
	public void testlistShop(){
		System.out.println("test listShop...");
		List<ShopBill> result = shopMapper.listShop("jono", "2016-04-08", "2016-04-30");
		System.out.println("test OK!");
	}

	@Test
	public void testlistShopByDay(){
		System.out.println("test null...");
		List<String>result = shopMapper.listShopByDay("jono", "2016-04-08", "2016-04-30", 1, 10);
		System.out.println("test OK!");
	}

}