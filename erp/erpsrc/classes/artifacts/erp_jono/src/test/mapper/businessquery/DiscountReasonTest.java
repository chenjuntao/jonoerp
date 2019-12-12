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

import mapper.businessquery.DiscountReasonMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.DiscountReason;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class DiscountReasonTest {
	  
	@Resource
	private DiscountReasonMapper discountReasonMapper;
	 

	@Test
	public void testdiscountReasonSum(){
		System.out.println("test discountReasonSum...");
		DiscountReason result = discountReasonMapper.discountReasonSum("jono","2016-04-08", "2016-04-30", null);
		System.out.println("test OK!");
	}

	@Test
	public void testdiscountReason(){
		System.out.println("test discountReason...");
		List<DiscountReason> result = discountReasonMapper.discountReason("jono","2016-04-08", "2016-04-30", null);
		System.out.println("test OK!");
	}
}