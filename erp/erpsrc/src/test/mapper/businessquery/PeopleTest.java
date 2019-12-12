/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package test.mapper.businessquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import logic.NoConnection;
import logic.businessquery.PeopleBean;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.tanry.framework.acl.NoPrivilegeException;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
@ContextConfiguration(locations = {"file:WEB-INF/testContest.xml"})
public class PeopleTest {
	  
	@Resource
//	private PeopleMapper peopleMapper;
	private PeopleBean peopleBean;
	@Test
	public void testlistMan() throws NoPrivilegeException, SQLException, NoConnection{
		System.out.println("test listMan...");
		List<Map> result = peopleBean.listMan("cCreateMan", "cCreateMan");
		System.out.println(result);
		System.out.println("test OK!");
	}

//	@Test
//	public void testlistMan(){
//		System.out.println("test listMan...");
//		List<Map> result = peopleMapper.listMan("jono", "cCreateMan", "cCreateMan");
//		System.out.println(result);
//		System.out.println("test OK!");
//	}
}