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

import mapper.businessquery.ReturnReasonMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.ReturnReason;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class ReturnReasonTest {
	  
	@Resource
	private ReturnReasonMapper returnReasonMapper;
	 

	@Test
	public void testreturnReasonSum(){
		System.out.println("test returnReasonSum...");
		ReturnReason result = returnReasonMapper.returnReasonSum("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}

	@Test
	public void testreturnReason(){
		System.out.println("test returnReason...");
		List<ReturnReason> result = returnReasonMapper.returnReason("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}
}