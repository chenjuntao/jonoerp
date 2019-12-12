/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年11月1日 by yunshucjt
 * Last edited on 2016年11月1日 by yunshu
 */
package webtest.cf.suite;

/**
 * 说明： test for cf
 */

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import webtest.cf.*;
import webtest.util.Tester;

public class testAll {
	private Tester tester;
	private WebDriver driver;
	private Logger logger;
	private cfTest cf;
	
	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		logger = tester.getLogger();
		cf = new cfTest();
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "cf", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("中央工厂");
		Thread.sleep(1000);
		logger.debug("中央工厂");
		try
		{
//			cf.testProducePlan(tester, driver, logger);
//			cf.testItemBuy(tester, driver, logger);
			logger.debug("手动生成采购单");
			//cf.testUnaiBuy(tester, driver, logger);
//			cf.testBuyAudit(tester, driver, logger);
//			cf.testAchieve(tester, driver, logger);
//			cf.testAchieveAudit(tester, driver, logger);
//			cf.testBuyInput(tester, driver, logger);
			logger.debug("批量领料单生成");
			//cf.testBatchAchieve(tester, driver, logger);
			logger.debug("超领单生成");
			//cf.testOverAchieve(tester, driver, logger);
			logger.debug("生产退料单");
			cf.testReturn(tester, driver, logger);
//			cf.testItemInput(tester, driver, logger);
//			cf.testItemOutput(tester, driver, logger);
			logger.debug("原料报损单");
			cf.testItemLoss(tester, driver, logger);
			logger.debug("出品报损单");
			cf.testFoodLoss(tester, driver, logger);
			logger.debug("采购退货");
			cf.testBuyReturn(tester, driver, logger);
			logger.debug("调拨单");
			cf.testAllot(tester, driver, logger);
			logger.debug("外部调拨单");
			cf.testOuterAllot(tester, driver, logger);
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
