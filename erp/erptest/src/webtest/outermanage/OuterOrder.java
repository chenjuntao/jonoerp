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
import org.openqa.selenium.WebElement;

import webtest.util.Tester;

/**
 * 说明：
 */
public class OuterOrder {
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

		// 外部登陆
		tester.login("", "wbps1", "3456");

		// 数据查询测试-------------------------------------------------------------
		tester.clickTopMenu("外部订货");

		// 添加餐厅要货模板
		tester.clickLeftMenu("外部订货管理", "外部订货生成");
		tester.switchRightTab("外部订货生成");
		driver.findElement(By.xpath("//input[@value='使用模板']")).click();
		WebElement pickModel = driver.findElement(By.id("ifr_pickModel"));
		driver.switchTo().frame(pickModel);
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();
		driver.findElement(By.xpath("//input[@value='确定选择模板']")).click();
		driver.switchTo().parentFrame();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("10");
		driver.findElement(By.id("dijit_form_NumberTextBox_1")).sendKeys("12");
		tester.switchRightTab("外部订货生成");
		driver.findElement(By.id("btn_submit")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTab("外部订货单提交");
		driver.findElement(By.id("btn_submit")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

		Thread.sleep(1000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("外部订货管理", "外部订货审核");
		tester.switchRightTab("外部订货审核");
		driver.findElement(By.id("operate1")).click();
		Thread.sleep(1000);
		tester.switchRightTabLike("审核外部订货单-");
		driver.findElement(By.xpath("//input[@value='审核确认']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
	}
	
	public void testTt(Tester tester,WebDriver driver,Logger logger) throws Exception {
		tester.clickLeftMenu("外部订货管理", "外部订货生成");
		tester.switchRightTab("外部订货生成");
		driver.findElement(By.xpath("//input[@value='使用模板']")).click();
		WebElement pickModel = driver.findElement(By.id("ifr_pickModel"));
		driver.switchTo().frame(pickModel);
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();
		driver.findElement(By.xpath("//input[@value='确定选择模板']")).click();
		driver.switchTo().parentFrame();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("10");
		driver.findElement(By.id("dijit_form_NumberTextBox_1")).sendKeys("12");
		tester.switchRightTab("外部订货生成");
		driver.findElement(By.id("btn_submit")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTab("外部订货单提交");
		driver.findElement(By.id("btn_submit")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

		Thread.sleep(1000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("外部订货管理", "外部订货审核");
		tester.switchRightTab("外部订货审核");
		driver.findElement(By.id("operate1")).click();
		Thread.sleep(1000);
		tester.switchRightTabLike("审核外部订货单-");
		driver.findElement(By.xpath("//input[@value='审核确认']")).click();
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
