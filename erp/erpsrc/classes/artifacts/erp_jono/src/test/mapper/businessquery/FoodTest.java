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

import mapper.businessquery.FoodMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.FoodBill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class FoodTest {
	  
	@Resource
	private FoodMapper foodMapper;

	//@Test
	public void testlistFood(){
		System.out.println("test listFood...");
		List<FoodBill> result = foodMapper.listFood("jono","test");
		System.out.println("size is:"+result.size());
		System.out.println("test OK!");
	}

	@Test
	public void testctrQuery(){
		System.out.println("test ctrQuery...");
		int tmp = foodMapper.getSum("jono", "2016-04-08", "2016-04-30");
		System.out.println("sum is:"+tmp);
		List<Map> result = foodMapper.ctrQuery("jono",tmp+1,"2016-04-08", "2016-04-30");
		System.out.println("size of ctr is:"+result.size());
		System.out.println("test OK!");
	}
}