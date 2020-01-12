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

import mapper.businessquery.PresentReasonMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.PresentReason;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class PresentReasonTest {
	  
	@Resource
	private PresentReasonMapper presentReasonMapper;
	 

	@Test
	public void testpresentReasonSum(){
		System.out.println("test presentReasonSum...");
		PresentReason result = presentReasonMapper.presentReasonSum("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}

	@Test
	public void testpresentReason(){
		System.out.println("test presentReason...");
		List<PresentReason> result = presentReasonMapper.presentReason("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}
}