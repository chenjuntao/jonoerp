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

import mapper.businessquery.PeoplePayMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.tanry.framework.util.TextUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class PeoplePayTest {
	  
	@Resource
	private PeoplePayMapper peoplePayMapper;
	 

	@Test
	public void testquery(){
		
		System.out.println("test query...");
		String query = " 1=1";
		query = query + " AND b.cBranch_C = '" + "10" + "'";
//		query = query + " AND b.cSettleMan = '" + "001" + "'";
		List<Map> result = peoplePayMapper.query("jono", "2016-04-08", "2016-04-30", query);
		System.out.println("test OK!");
	}

	@Test
	public void testqueryColumns(){
		System.out.println("test queryColumns...");
		String query = " 1=1";
		query = query + " AND b.cBranch_C = '" + "10" + "'";
//		query = query + " AND b.cSettleMan = '" + "001" + "'";
		List<Map> result = peoplePayMapper.queryColumns("jono", "2016-04-08", "2016-04-30", query);
		System.out.println("test OK!");
	}
}