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
package webtest.outermanage;

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
public class Acscan {
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
	public void testRequirement() throws Exception {
		// 外部登陆
		tester.login("", "wbps1", "3456");

		// 数据查询测试-------------------------------------------------------------
		tester.clickTopMenu("外部订货");
		tester.clickLeftMenu("外部订货对账", "外部订货付款");
		tester.switchRightTab("外部订货付款");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys("2016-05-21");
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("付款");
		Thread.sleep(1000);
		driver.findElement(By.id("operateButton")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
	}

	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		tester.clickLeftMenu("外部订货对账", "外部订货付款");
		tester.switchRightTab("外部订货付款");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys("2016-05-21");
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("付款");
		Thread.sleep(1000);
		driver.findElement(By.id("operateButton")).click();
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
