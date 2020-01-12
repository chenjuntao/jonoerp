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

import mapper.businessquery.TableMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.TableBill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class TableTest {
	  
	@Resource
	private TableMapper tableMapper;
	 

	@Test
	public void testgetShopName(){
		System.out.println("test getShopName...");
		String result = tableMapper.getShopName("jono", "10");
		System.out.println("test OK!");
	}

	@Test
	public void testlistTable(){
		System.out.println("test listTable...");
		List<TableBill> result = tableMapper.listTable("jono", "2016-04-08", "2016-04-30", "10");
		System.out.println("test OK!");
	}
}