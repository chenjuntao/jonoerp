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
 * 说明： 
 */
package test.mapper.restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import mapper.restapi.CompanyMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
@SuppressWarnings({"rawtypes", "unchecked"})
public class CompanyTest {
	  
	 @Resource
	 private CompanyMapper companyMapper;

//	 @Test
	public void testSave(){
		 System.out.println("test saveEntity...");
		 
		 Map newCom = new HashMap();
		 newCom.put("companyId", "100");
		 newCom.put("companyName", "新的测试企业");
		 newCom.put("dbName", "test100");
		 newCom.put("creator", "test");
		 newCom.put("remark", "新的测试企业");
		 companyMapper.saveEntity("com" ,newCom);
		 System.out.println("saveEntity ok!");
	 }
	 
	 
	@Test
	 public void testList(){
		System.out.println("test listCompany...");
		List<Map> coms = companyMapper.listCompany("com");
		for (int i = 0; i < coms.size(); i++) {
			System.out.println(coms.get(i).get("COMPANY_ID").toString()+coms.get(i).get("COMPANY_NAME"));
		}
	 }
	 
//	 @Test
	 public void testDDL(){
		 System.out.println("test DDL in mybatis...");
		 List<Map> coms = companyMapper.listCompany("com");
			for (int i = 0; i < coms.size(); i++) {
				String db = coms.get(i).get("DB_NAME").toString().toUpperCase();
				companyMapper.ddlTest(db);
				System.out.println(db + "is executing...");
			}
	 }
}
