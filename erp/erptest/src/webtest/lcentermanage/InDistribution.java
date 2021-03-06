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
package webtest.lcentermanage;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webtest.util.Tester;

/**
 * 说明：
 */
public class InDistribution {
	private Tester tester;
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();

	}

	public boolean doesWebElementExist(WebDriver driver, By selector) {
		try {
			driver.findElement(selector);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Test
	public void testTt() throws Exception {

		// pw登陆
		tester.login("", "lc", "3456");

		// 数据查询测试-------------------------------------------------------------
		tester.clickTopMenu("物流中心");

		// 央厂半成品入库
		tester.clickLeftMenu("库存基础管理", "央厂半成品入库");
		tester.switchRightTab("央厂半成品入库");
		// driver.findElement(By.id("startDate")).clear();
		// driver.findElement(By.id("startDate")).sendKeys("2016-09-12");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTabLike("半成品入库-");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='确认填充配送单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

	}

	public void testCFInput(Tester tester,WebDriver driver, Logger logger) throws Exception {

		// 央厂半成品入库
		tester.clickLeftMenu("库存基础管理", "央厂半成品入库");
		tester.switchRightTab("央厂半成品入库");
		// driver.findElement(By.id("startDate")).clear();
		// driver.findElement(By.id("startDate")).sendKeys("2016-09-12");
		Thread.sleep(1000);
		try{
			driver.findElement(By.xpath("//input[@value='查    询']")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("operate1")).click();
		}catch(Exception e){
			 logger.debug("央厂半成品入库 无前置单据");
			 logger.error(e);
		 }
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTabLike("半成品入库-");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='确认填充配送单']")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("央厂半成品入库填充失败");
		}
		logger.debug("央厂半成品入库完成");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String result = tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}
}
