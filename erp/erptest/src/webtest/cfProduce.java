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
import webtest.lcentermanage.AuditPurchase;
import webtest.lcentermanage.InDistribution;
import webtest.lcentermanage.ManualPurchase;
import webtest.util.Tester;


/**
 * 说明： 
 */
public class cfProduce {
	private Tester tester;
	private WebDriver driver;
	private cfTest ct;
	private ManualPurchase mp;
	private AuditPurchase ap;
	private InDistribution idb;
	private Logger logger;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		ct = new cfTest();
		mp = new ManualPurchase();
		ap = new AuditPurchase();
		idb = new InDistribution();
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
		//中央工厂采购流程
		tester.login("", "lc", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("物流中心");
		Thread.sleep(1000);
//		手动生成采购单
		mp.testManualPurchase(tester, driver, logger);
		Thread.sleep(1000);
		ap.testAuditPurchaseForLc(tester, driver, logger);
		Thread.sleep(1000);
		
		tester.logout();
		
		tester.login("", "cf", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("中央工厂");
		Thread.sleep(1000);
		ct.testProducePlan(tester, driver, logger);
		Thread.sleep(1000);
		
		//领料
		ct.testAchieve(tester, driver, logger);
		Thread.sleep(1000);
		ct.testAchieveAudit(tester, driver, logger);
		Thread.sleep(1000);
		
		ct.testItemBuy(tester, driver, logger);
		Thread.sleep(1000);
		ct.testBuyAudit(tester, driver, logger);
		Thread.sleep(1000);
		ct.testBuyInput(tester, driver, logger);
		Thread.sleep(1000);
		System.out.println("中央工厂采购流程完成");
		
		//中央工厂生产流程
		ct.testItemInput(tester, driver, logger);
		Thread.sleep(1000);
		ct.testItemOutput(tester, driver, logger);
		Thread.sleep(1000);
		tester.logout();

		tester.login("", "lc", "3456");
		Thread.sleep(1000);
		tester.clickTopMenu("物流中心");
		Thread.sleep(3000);
		idb.testCFInput(tester, driver, logger);
		
	System.out.println("中央工厂生产流程完成");
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
