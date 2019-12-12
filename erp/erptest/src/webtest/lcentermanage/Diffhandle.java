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
public class Diffhandle {
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

		// 审核采购单
		tester.clickLeftMenu("库存基础管理", "配送差异处理");
		tester.switchRightTab("配送差异处理");
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTabLike("处理配送差异");
		driver.findElement(By.id("operate1")).click();
		driver.findElement(By.xpath("//input[@value='确认处理差异']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

	}
	
	public void testTt(Tester tester,WebDriver driver,Logger logger) throws Exception {
		// 审核采购单
		logger.debug("配送差异处理");
		tester.clickLeftMenu("库存基础管理", "配送差异处理");
		tester.switchRightTab("配送差异处理");
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTabLike("处理配送差异");
		driver.findElement(By.id("operate1")).click();
		driver.findElement(By.xpath("//input[@value='确认处理差异']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

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
