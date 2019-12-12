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

import mapper.businessquery.FoodSummaryMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.FoodSummary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class FoodSummaryTest {
	  
	@Resource
	private FoodSummaryMapper foodSummaryMapper;
	 

	@Test
	public void testfoodSummarySum(){
		System.out.println("test foodSummarySum...");
		FoodSummary result = foodSummaryMapper.foodSummarySum("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("qty is:"+result.getQty());
		System.out.println("test OK!");
	}

	@Test
	public void testfoodSummary(){
		System.out.println("test foodSummary...");
		List<FoodSummary> result = foodSummaryMapper.foodSummary("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("size is:"+result.size());
		System.out.println("test OK!");
	}
}