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

import mapper.businessquery.BillPayDetailMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.BillPayDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class BillPayDetailTest {
	  
	@Resource
	private BillPayDetailMapper billPayDetailMapper;
	 

	@Test
	public void testsum(){
		System.out.println("test sum...");
		BillPayDetail result = billPayDetailMapper.sum("jono", "2016-04-08", "2016-04-30", "10", null, null, null);
		System.out.println("test OK!");
	}

	@Test
	public void testcount(){
		System.out.println("test count...");
		int result = billPayDetailMapper.count("jono", "2016-04-08", "2016-04-30", "10", null, null, null);
		System.out.println("test OK!");
	}

	@Test
	public void testbillPay(){
		System.out.println("test billPay...");
		List<BillPayDetail> result = billPayDetailMapper.billPay("jono", "2016-04-08", "2016-04-30", "10", null, null, null,1,10);
		System.out.println("test OK!");
	}
}