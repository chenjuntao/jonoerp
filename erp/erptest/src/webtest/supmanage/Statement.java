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
package webtest.supmanage;

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
public class Statement {
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
		tester.login("", "s1005", "3456");
		Thread.sleep(3000);
		/*
		 * 生成
		 */
		tester.clickTopMenu("供应管理");
		Thread.sleep(1000);
		tester.clickLeftMenu("供应商供应流程管理", "对账单生成");
		tester.switchRightTab("对账单生成");
		driver.findElement(By.id("supplierId")).sendKeys("[1005]欧爱群");
		driver.findElement(By.id("branchId")).sendKeys("[L00]长沙物流中心");
		// driver.findElement(By.id("startDate")).clear();
		// driver.findElement(By.id("startDate")).sendKeys("2016-05-21");
		// driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='生成对账单']")).click();
		tester.switchRightTabLike("生成对账单");
		driver.findElement(By.xpath("//input[@value='确认生成对账单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
	}
	
	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		tester.clickLeftMenu("供应商供应流程管理", "对账单生成");
		tester.switchRightTab("对账单生成");
		driver.findElement(By.id("supplierId")).sendKeys("[1005]欧爱群");
		driver.findElement(By.id("branchId")).sendKeys("[L00]长沙物流中心");
		// driver.findElement(By.id("startDate")).clear();
		// driver.findElement(By.id("startDate")).sendKeys("2016-05-21");
		// driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='生成对账单']")).click();
		tester.switchRightTabLike("生成对账单");
		driver.findElement(By.xpath("//input[@value='确认生成对账单']")).click();
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
