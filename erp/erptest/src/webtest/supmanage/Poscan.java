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
public class Poscan {
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
		tester.clickLeftMenu("供应商供应流程管理", "采购单查询");
		tester.switchRightTab("采购单查询");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys("2016-05-21");
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("formId0")).click();
		tester.switchRightTabLike("采购单查看-");
	}
	
	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		tester.clickLeftMenu("供应商供应流程管理", "采购单查询");
		tester.switchRightTab("采购单查询");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys("2016-05-21");
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("formId0")).click();
		tester.switchRightTabLike("采购单查看-");
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
