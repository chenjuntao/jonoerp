/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年6月8日 by cjt
 * Last edited on 2016年6月8日 by cjt
 * 
 * 说明： FreeBillDAO测试  
 */
package test.mapper.businessquery;

import java.util.List;

import javax.annotation.Resource;

import mapper.businessquery.FreeBillMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pojo.businessquery.FreeBill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WEB-INF/spring-mybatis.xml" })
public class FreeBillTest {
	@Resource
	private FreeBillMapper freeBillMapper;

	@Test
	public void TestGetHead() {
		System.out.println("TestGetHead...");
		List freeBills = freeBillMapper.getHead("jono", "2016-04-08", "2016-04-30", "10", null, null, 0, 1000);
		System.out.println("表头：");
		for (int i = 0; i < freeBills.size(); i++) {
			FreeBill freeBill = (FreeBill) freeBills.get(i);
			System.out.println(freeBill.billC);
		}

		System.out.println("TestGetHead ok!");
	}

	// @Test
	public void TestGetHeadCount() {
		System.out.println("TestGetHead...");
		int freeBills = freeBillMapper.getHeadCount("jono", "2016-04-08", "2016-04-30", "10", null, null);
		System.out.println("总数：" + freeBills);
		System.out.println("TestGetHeadcount ok!");
	}

	@Test
	public void TestGetDetail() {
		System.out.println("TestGetDetail...");
		// List freeBills = freeBillMapper.getDetail("jono", "", "2016-04-00",
		// "2016-04-30", "10", null, null, 0, 10);
		// System.out.println("明细：");
		// for (int i = 0; i < freeBills.size(); i++) {
		// FreeBill freeBill = (FreeBill)freeBills.get(i);
		// System.out.println(freeBill.billC);
		// }

		System.out.println("TestGetDetail ok!");
	}
}
