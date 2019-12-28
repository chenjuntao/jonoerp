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

import mapper.businessquery.DiscountMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.DiscountBill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class DiscountTest {
	  
	@Resource
	private DiscountMapper discountMapper;
	 

	@Test
	public void testgetCount(){
		System.out.println("test getCount...");
		int result = discountMapper.getCount("jono","2016-04-08", "2016-04-30", null, null, null);
		System.out.println("count is " + result);
		System.out.println("test OK!");
	}

	@Test
	public void testgetDiscount(){
		System.out.println("test getDiscount...");
		List<DiscountBill> result = discountMapper.getDiscount("jono","2016-04-08", "2016-04-30", null, null, null, 0, 10000);
		System.out.println("the size is "+result.size());
		System.out.println("test OK!");
	}
}