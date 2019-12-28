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

import mapper.businessquery.PresentMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.PresentBill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class PresentTest {
	  
	@Resource
	private PresentMapper presentMapper;
	 

	@Test
	public void testgetDetailSum(){
		System.out.println("test getDetailSum...");
		PresentBill result = presentMapper.getDetailSum("jono", "2016-04-08", "2016-04-30", "10", null, null);
		System.out.println("test OK!"+result.getPresentAmount() + " " + result.getPresentPrice());
	}

	@Test
	public void testgetHeadSum(){
		System.out.println("test getHeadSum...");
		PresentBill result = presentMapper.getHeadSum("jono", "2016-04-08", "2016-04-30", "10", null, null);
		System.out.println("test OK!");
	}

	@Test
	public void testgetDetailCount(){
		System.out.println("test getDetailCount...");
		int result = presentMapper.getDetailCount("jono", "2016-04-08", "2016-04-30", "10", null, null);
		System.out.println("test OK!");
	}

	@Test
	public void testgetHeadCount(){
		System.out.println("test getHeadCount...");
		int result = presentMapper.getHeadCount("jono", "2016-04-08", "2016-04-30", "10", null, null);
		System.out.println("test OK!");
	}

	@Test
	public void testgetDetail(){
		System.out.println("test getDetail...");
		List<PresentBill> result = presentMapper.getDetail("jono", "2016-04-08", "2016-04-30", "10", null, null, 0, 10);
		System.out.println("test OK!");
	}

	@Test
	public void testgetHead(){
		System.out.println("test getHead...");
		List<PresentBill> result = presentMapper.getHead("jono", "2016-04-08", "2016-04-30", "10", null, null, 0, 10);
		System.out.println("test OK!");
	}
}