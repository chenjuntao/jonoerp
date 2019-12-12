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
import org.openqa.selenium.WebElement;

import webtest.util.Tester;

/**
 * 说明：
 */
public class Template {
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

		// 添加餐厅要货模板
		tester.clickLeftMenu("物流中心需求管理", "餐厅要货模板管理");
		tester.switchRightTab("餐厅要货模板管理");
		driver.findElement(By.xpath("//input[@value='新增模板']")).click();
		tester.switchRightTab("新增要货单模板");
		driver.findElement(By.xpath("//input[@value='新增原料']")).click();
		WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
		driver.switchTo().frame(selMaterial);
		driver.findElement(By.id("itemName")).sendKeys("11111");
		driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		// 从子Frame切换到父Frame
		driver.switchTo().parentFrame();

		String templateName = "test" + Math.random() * 10 + 1;
		driver.findElement(By.id("templateName")).sendKeys(templateName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='保存模板']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

	}
	
	public void testTt(Tester tester,WebDriver driver,Logger logger) throws Exception {

		// 添加餐厅要货模板
		logger.debug("餐厅要货模板管理");
		tester.clickLeftMenu("物流中心需求管理", "餐厅要货模板管理");
		tester.switchRightTab("餐厅要货模板管理");
		driver.findElement(By.xpath("//input[@value='新增模板']")).click();
		tester.switchRightTab("新增要货单模板");
		driver.findElement(By.xpath("//input[@value='新增原料']")).click();
		WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
		driver.switchTo().frame(selMaterial);
		driver.findElement(By.id("itemName")).sendKeys("11111");
		driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		// 从子Frame切换到父Frame
		driver.switchTo().parentFrame();

		String templateName = "test" + Math.random() * 10 + 1;
		driver.findElement(By.id("templateName")).sendKeys(templateName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='保存模板']")).click();
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
