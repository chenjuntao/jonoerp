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

import mapper.businessquery.PeriodBusinessMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.tanry.framework.util.TextUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class PeriodBusinessTest {
	  
	@Resource
	private PeriodBusinessMapper periodBusinessMapper;
	 

	@Test
	public void testlistPeriodBusiness(){
		System.out.println("test listPeriodBusiness...");
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		sb.append(" AND DBUSINESS >= '2016-04-08'");
		sb.append(" AND DBUSINESS <= '2016-04-30'");
		sb.append(" AND b.CBRANCH_C = '10' ");
		List<Map> result = periodBusinessMapper.listPeriodBusiness("jono", sb.toString());
		Map tmp = result.get(result.size()-1);
		System.out.println("test OK!");
	}
}