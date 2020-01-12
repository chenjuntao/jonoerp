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

import mapper.businessquery.ReturnMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.ReturnBill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class ReturnTest {
	  
	@Resource
	private ReturnMapper returnMapper;
	 

	@Test
	public void testgetReturnSum(){
		System.out.println("test getReturnSum...");
		ReturnBill result = returnMapper.getReturnSum("jono", "2016-04-08", "2016-04-30", "10", null, null);
		System.out.println("test OK!");
	}

	@Test
	public void testgetHeadCount(){
		System.out.println("test getHeadCount...");
		int result = returnMapper.getHeadCount("jono", "2016-04-08", "2016-04-30", "10", null, null);
		System.out.println("test OK!");
	}

	@Test
	public void testgetReturn(){
		System.out.println("test getReturn...");
		List<ReturnBill> result = returnMapper.getReturn("jono", "2016-04-08", "2016-04-30", "10", null, null, 1, 10);
		System.out.println("test OK!");
	}
}