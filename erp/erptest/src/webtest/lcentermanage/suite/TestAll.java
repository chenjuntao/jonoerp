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
package webtest.lcentermanage.suite;

/**
 * 说明： test for lcmanage
 */
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import webtest.test1;
import webtest.lcentermanage.*;
import webtest.util.Tester;

public class TestAll {
	private Tester tester;
	private WebDriver driver;
	private Logger logger;
	private AntiAudit aa;
	private AuditCross ac;
	private AuditDistribution ad;
	private AuditInput ai;
	private AuditPurchase ap;
	private BatchDistribution bd;
	private Delivery d;
	private Diffhandle dh;
	private Distribution db;
	private InDistribution idb;
	private Input i;
	private ManualPurchase mp;
	private OutPreject op;
	private Picking p;
	private Preject pj;
	private SummaryPurchase sp;
	private Template t;
	

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		logger = tester.getLogger();
		aa = new AntiAudit();
		ac = new AuditCross();
		ad = new AuditDistribution();
		ai = new AuditInput();
		ap = new AuditPurchase();
		bd = new BatchDistribution();
		d = new Delivery();
		dh = new Diffhandle();
		db = new Distribution();
		idb = new InDistribution();
		i = new Input();
		mp = new ManualPurchase();
		op = new OutPreject();
		p = new Picking();
		pj = new Preject();
		sp = new SummaryPurchase();
		t = new Template();
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "lc", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("物流中心");
		Thread.sleep(1000);
		logger.debug("物流中心");
		try{
			aa.testRequirement(tester, driver, logger);
//			ac.testAuditCross(tester, driver, logger);
//			ad.testAuditDistribution(tester, driver, logger);
//			ai.testAuditInput(tester, driver, logger);
//			ap.testAuditPurchase(tester, driver, logger);
			bd.testTt(tester, driver, logger);
			d.testTt(tester, driver, logger);
			dh.testTt(tester, driver, logger);
//			db.testDistribution(tester, driver, logger);
//			idb.testCFInput(tester, driver, logger);
			i.testInput(tester, driver, logger);
//			mp.testManualPurchase(tester, driver, logger);
			op.testTt(tester, driver, logger);
//			p.testPicking(tester, driver, logger);
			pj.testTt(tester, driver, logger);
//			sp.testSummaryPurchase(tester, driver, logger);
//			t.testTt(tester, driver, logger);
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
