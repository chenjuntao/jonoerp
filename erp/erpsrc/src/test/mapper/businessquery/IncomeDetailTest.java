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

import mapper.businessquery.IncomeDetailMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.IncomeDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class IncomeDetailTest {
	  
	@Resource
	private IncomeDetailMapper incomeDetailMapper;
	 

	@Test
	public void testdaySum(){
		System.out.println("test daySum...");
		IncomeDetail result = incomeDetailMapper.daySum("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("daysum is:"+result.getCounts());
		System.out.println("test OK!");
	}

	@Test
	public void testmonthSum(){
		System.out.println("test monthSum...");
		IncomeDetail result = incomeDetailMapper.monthSum("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("monthSum is:"+result.getCounts());
		System.out.println("test OK!");
	}

	@Test
	public void testmonthIncomeDetail(){
		System.out.println("test monthIncomeDetail...");
		List<IncomeDetail> result = incomeDetailMapper.monthIncomeDetail("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("monthIncomeDetail size is:"+result.size());
		System.out.println("test OK!");
	}

	@Test
	public void testdayIncomeDetail(){
		System.out.println("test dayIncomeDetail...");
		List<IncomeDetail> result = incomeDetailMapper.dayIncomeDetail("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("dayIncomeDetail size is:"+result.size());
		System.out.println("test OK!");
	}
}