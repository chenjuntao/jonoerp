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
package webtest.outermanage.suite;

/**
 * 说明： 
 */
import webtest.outermanage.*;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import webtest.util.Tester;

public class TestAll {
	private Tester tester;
	private WebDriver driver;
	private Logger logger;
	private Acscan a;
	private AuditStatement as;
	private OuterOrder oo;
	private OutScan os;
	private PayedScan ps;
	private Statement s;
	private Template t;
	
	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		logger = tester.getLogger();
		a = new Acscan();
		as = new AuditStatement();
		oo = new OuterOrder();
		os = new OutScan();
		ps = new PayedScan();
		s = new Statement();
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "wbps1", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("外部订货");
		Thread.sleep(1000);
		logger.debug("外部订货");
		try{
			logger.debug("外部订货生成");
			oo.testTt(tester, driver, logger);
			logger.debug("外部订货付款");
			a.testRequirement(tester, driver, logger);
			logger.debug("出货单入库管理");
			os.testTt(tester, driver, logger);
			logger.debug("收款确认");
			ps.testRequirement(tester, driver, logger);
			logger.debug("对账单生成");
			s.testRequirement(tester, driver, logger);
			logger.debug("外部订货对账确认");
			as.testRequirement(tester, driver, logger);
		}catch(Exception e){
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
