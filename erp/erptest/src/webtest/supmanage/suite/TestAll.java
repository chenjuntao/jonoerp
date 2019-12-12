/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年11月2日 by yunshucjt
 * Last edited on 2016年11月2日 by yunshu
 */
package webtest.supmanage.suite;

/**
 * 说明： 
 */
import webtest.supmanage.*;
import webtest.util.Tester;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
public class TestAll {
	private Tester tester;
	private WebDriver driver;
	private Logger logger;
	private Acscan a;
	private AuditStatement as;
	private PayedScan ps;
	private Poscan p;
	private Returnsp r;
	private Statement s;
	
	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		logger = tester.getLogger();
		a = new Acscan();
		ps = new PayedScan();
		p = new Poscan();
		r = new Returnsp();
		s = new Statement();
		as = new AuditStatement();
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "s1005", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("供应管理");
		Thread.sleep(1000);
		logger.debug("供应管理");
		try{
			logger.debug("财务付款");
			a.testRequirement(tester, driver, logger);
			logger.debug("供应商收款确认");
			ps.testRequirement(tester, driver, logger);
			logger.debug("采购单查询");
			p.testRequirement(tester, driver, logger);
			logger.debug("退货单查询");
			r.testRequirement(tester, driver, logger);
			logger.debug("对账单生成");
			s.testRequirement(tester, driver, logger);
			logger.debug("供应商对账确认");
			as.testRequirement(tester, driver, logger);
		}catch (Exception e){
			logger.error(e);
		}
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String result =	tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}
}
