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

import mapper.businessquery.BusinessCountMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.BusinessCount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class BusinessCountTest {
	  
	@Resource
	private BusinessCountMapper businessCountMapper;
	 

	@Test
	public void testshiftSum(){
		System.out.println("test shiftSum...");
		BusinessCount result = businessCountMapper.shiftSum("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}

	@Test
	public void testperiodSum(){
		System.out.println("test periodSum...");
		BusinessCount result = businessCountMapper.periodSum("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}

	@Test
	public void testfloorSum(){
		System.out.println("test floorSum...");
		BusinessCount result = businessCountMapper.floorSum("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}

	@Test
	public void testshift(){
		System.out.println("test shift...");
		List<BusinessCount> result = businessCountMapper.shift("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}

	@Test
	public void testperiod(){
		System.out.println("test period...");
		List<BusinessCount> result = businessCountMapper.period("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}

	@Test
	public void testfloor(){
		System.out.println("test floor...");
		List<BusinessCount> result = businessCountMapper.floor("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}
}