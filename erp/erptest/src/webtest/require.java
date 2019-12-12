/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年9月22日 by yunshucjt
 * Last edited on 2016年9月22日 by yunshu
 */
package webtest;

import static org.junit.Assert.fail;






























import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;





import webtest.cf.cfTest;
import webtest.lcentermanage.AuditCross;
import webtest.lcentermanage.AuditInput;
import webtest.lcentermanage.AuditPicking;
import webtest.lcentermanage.AuditPurchase;
import webtest.lcentermanage.Distribution;
import webtest.lcentermanage.Input;
import webtest.lcentermanage.Picking;
import webtest.lcentermanage.SummaryPurchase;
import webtest.resturantmanage.BuyInput;
import webtest.resturantmanage.DistributionInput;
import webtest.resturantmanage.Requeried;
import webtest.util.Tester;


/**
 * 说明： 
 */
public class require {
	private Tester tester;
	private WebDriver driver;
	private Requeried r1;
	private SummaryPurchase s;
	private AuditPurchase ap;
	private Input ti;
	private AuditInput ai;
	private Distribution db;
	private AuditCross ac;
	private Picking p;
	private AuditPicking api;
	private cfTest ct;
	private DistributionInput di;
	private BuyInput r2;
	private Logger logger;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		r1 = new Requeried();
		s = new SummaryPurchase();
		ap = new AuditPurchase();
		ti = new Input();
		ai = new AuditInput();
		db = new Distribution();
		ac = new AuditCross();
		p = new Picking();
		api = new AuditPicking();
		ct = new cfTest();
		di = new DistributionInput();
		r2 = new BuyInput();
		logger = tester.getLogger();
	}
	
	public boolean doesWebElementExist(WebDriver driver, By selector)
	{ 
	        try 
	        { 
	               driver.findElement(selector); 
	               return true; 
	        } 
	        catch (Exception e) 
	        {
	                return false; 
	        } 
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "pw", "admin");
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
		
		//餐厅要货
		r1.testRequirement(tester,driver,logger);
		
		tester.logout();
		tester.login("", "lc", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("物流中心");
		Thread.sleep(1000);
		
		//物流中心汇总生产采购单
		s.testSummaryPurchase(tester, driver,logger);
		Thread.sleep(1000);
		//物流中心采购单审核
		ap.testAuditPurchase(tester, driver,logger);
		Thread.sleep(1000);
		ti.testInput(tester, driver,logger);
		ai.testAuditInput(tester, driver,logger);
		System.out.println("要货采购流程测试完成");
		Thread.sleep(1000);
		//物流中心配送单生成
		db.testDistribution(tester, driver,logger);
		//拣货单
		p.testPicking(tester, driver,logger);
		tester.clickTopMenu("物流中心");
		Thread.sleep(1000);
		api.testAuditPicking(tester, driver,logger);
		//越库配送单
		ac.testAuditCross(tester, driver,logger);
		
		//餐厅配送入库
		tester.logout();
		tester.login("", "pw", "admin");
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
		di.testRequirement(tester, driver,logger);
		r2.testRequirement(tester, driver,logger);
		
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
