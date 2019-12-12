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

import mapper.businessquery.ShopPaymentMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.ShopPayment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class ShopPaymentTest {
	  
	@Resource
	private ShopPaymentMapper shopPaymentMapper;
	 

	@Test
	public void testgetShopPayInfo(){
		System.out.println("test getShopPayInfo...");
		List<ShopPayment> result = shopPaymentMapper.getShopPayInfo("jono", "2016-04-08", "2016-04-30");
		System.out.println("test OK!");
	}

	@Test
	public void testgetShopPayInfoByDay(){
		System.out.println("test getShopPayInfoByDay...");
		List<ShopPayment> result = shopPaymentMapper.getShopPayInfoByDay("jono", "2016-04-08", "2016-04-30");
		System.out.println("test OK!");
	}
}