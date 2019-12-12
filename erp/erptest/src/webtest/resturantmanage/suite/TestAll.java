/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年10月31日 by yunshucjt
 * Last edited on 2016年10月31日 by yunshu
 */
package webtest.resturantmanage.suite;

/**
 * 说明： test all for resturantmanage
 */
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import webtest.resturantmanage.Check;
import webtest.resturantmanage.DistributionInput;
import webtest.resturantmanage.DistributionReturn;
import webtest.resturantmanage.DistributionTurnAudit;
import webtest.resturantmanage.HalfFoodPandI;
import webtest.resturantmanage.Loss;
import webtest.resturantmanage.Requeried;
import webtest.resturantmanage.Settle;
import webtest.resturantmanage.BuyReturn;
import webtest.resturantmanage.Allot;
import webtest.resturantmanage.BuyInput;
import webtest.util.Tester;
public class TestAll {
	private Tester tester;
	private WebDriver driver;
	private Logger logger;
	private Allot allot;
	private BuyInput buyInput;
	private BuyReturn buyRetuen;
	private Check check;
	private DistributionInput dbi;
	private DistributionReturn dbr;
	private DistributionTurnAudit dbta;
	private HalfFoodPandI hfp;
	private Loss loss;
	private Requeried r;
	private Settle s;
	
	
	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		logger = tester.getLogger();
		allot = new Allot();
		buyInput = new BuyInput();
		buyRetuen = new BuyReturn();
		check = new Check();
		dbi = new DistributionInput();
		dbr = new DistributionReturn();
		dbta = new DistributionTurnAudit();
		hfp = new HalfFoodPandI();
		loss = new Loss();
		r = new Requeried();
		s = new Settle();
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "pw", "admin");
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
		logger.debug("餐厅管理");
		try{
			allot.testRequirement(tester, driver, logger);
			buyInput.testRequirement(tester, driver, logger);
			buyRetuen.testRequirement(tester, driver, logger);
	//		check.testRequirement(tester, driver, logger);
			dbi.testRequirement(tester, driver, logger);
			dbr.testRequirement(tester, driver, logger);
			dbta.testRequirement(tester, driver, logger);
			hfp.testRequirement(tester, driver, logger);
			loss.testRequirement(tester, driver, logger);
			r.testRequirement(tester, driver, logger);
	//		s.testRequirement(tester, driver, logger);
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
