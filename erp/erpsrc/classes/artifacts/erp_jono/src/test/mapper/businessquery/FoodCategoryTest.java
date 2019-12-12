/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package test.mapper.businessquery;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import mapper.businessquery.FoodCategoryMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.FoodCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class FoodCategoryTest {
	  
	@Resource
	private FoodCategoryMapper foodCategoryMapper;
	 

	@Test
	public void testgetAmt(){
		System.out.println("test getAmt...");
		BigDecimal result = foodCategoryMapper.getAmt("jono", "10", "2016-04-08", "2016-04-30");
		System.out.println("test OK!");
	}

	@Test
	public void testlistCategory(){
		System.out.println("test listCategory...");
		List<FoodCategory> result = foodCategoryMapper.listCategory("jono", "10", "2016-04-08", "2016-04-30");
		System.out.println("test OK!");
	}

	@Test
	public void testlistRawCategory(){
		System.out.println("test listRawCategory...");
		List<FoodCategory> result = foodCategoryMapper.listRawCategory("jono");
		System.out.println("result is:"+result.get(0).getSubCategoryName());
		System.out.println("test OK!");
	}
}