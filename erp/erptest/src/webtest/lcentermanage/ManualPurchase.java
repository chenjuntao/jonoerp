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
public class ManualPurchase {
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

		// 手动生成采购单
		tester.clickLeftMenu("物流中心需求管理", "手动生成采购单");
		tester.switchRightTab("手动生成采购单");
		driver.findElement(By.xpath("//input[@value='选择供应商']")).click();
		WebElement selSupplier = driver.findElement(By.id("ifr_selSupplier"));
		driver.switchTo().frame(selSupplier);
		driver.findElement(By.id("supplierName")).sendKeys("0000");
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.cssSelector("input[type=\"radio\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		// 从子Frame切换到父Frame
		driver.switchTo().parentFrame();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='自选原料']")).click();
		WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
		driver.switchTo().frame(selMaterial);
		driver.findElement(By.id("itemName")).sendKeys("001");
		driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='确    定']")).click();
		// 从子Frame切换到父Frame
		driver.switchTo().parentFrame();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

		tester.switchRightTab("生成采购单");
		driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

	}

	public void testManualPurchase(Tester tester,WebDriver driver, Logger logger) throws Exception {

		// 手动生成采购单
		tester.clickLeftMenu("物流中心需求管理", "手动生成采购单");
		tester.switchRightTab("手动生成采购单");
		try{
			driver.findElement(By.xpath("//input[@value='选择供应商']")).click();
			WebElement selSupplier = driver.findElement(By.id("ifr_selSupplier"));
			driver.switchTo().frame(selSupplier);
			driver.findElement(By.id("supplierName")).sendKeys("F00");
			driver.findElement(By.xpath("//input[@value='查询']")).click();
			driver.findElement(By.cssSelector("input[type=\"radio\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='确定']")).click();
			// 从子Frame切换到父Frame
			driver.switchTo().parentFrame();
		}catch(Exception e){
			logger.debug("物流中心手动生成采购单 选择供应商出错");
			logger.error(e);
		}

		Thread.sleep(2000);
		try{
			driver.findElement(By.xpath("//input[@value='自选原料']")).click();
			WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
			driver.switchTo().frame(selMaterial);
			driver.findElement(By.id("itemName")).sendKeys("001");
			driver.findElement(By.xpath("//input[@value='查询原料']")).click();
			driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
	
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='确    定']")).click();
			// 从子Frame切换到父Frame
			driver.switchTo().parentFrame();
		}catch(Exception e){
			logger.debug("物流中心手动生成采购单 自选原料出错");
			logger.error(e);
		}
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
			logger.debug("物流中心手动生成采购单 填写订货数出错");
		}

		tester.switchRightTab("生成采购单");
		driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("物流中心手动生成采购单生成失败");
		}
		logger.debug("物流中心手动生成采购单完成");
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
