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

import mapper.businessquery.FoodAmtTTCNYMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.FoodAmtTTCNY;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class FoodAmtTTCNYTest {
	  
	@Resource
	private FoodAmtTTCNYMapper foodAmtTTCNYMapper;
	 

	@Test
	public void testlistFoodAmtTTCNY(){
		System.out.println("test listFoodAmtTTCNY...");
		Object theSum = foodAmtTTCNYMapper.getSum("jono","10", "2016-04-08", "2016-04-30");
		System.out.println("the sum is:"+theSum);
		List<FoodAmtTTCNY> result = foodAmtTTCNYMapper.listFoodAmtTTCNY("jono","10", "2016-04-08", "2016-04-30", "32", theSum);
		System.out.println("the cny is:"+result.get(0).getAmtTTCNY());
		System.out.println("test OK!");
	}
}