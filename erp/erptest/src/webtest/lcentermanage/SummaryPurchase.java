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
public class SummaryPurchase {
	private Tester tester;
	private WebDriver driver;
	private Logger logger;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		logger = tester.getLogger();
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
		tester.clickLeftMenu("物流中心需求管理", "物流中心汇总生成采购单");
		tester.switchRightTab("物流中心汇总生成采购单");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys("2016-09-17");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='查   询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='汇总预估']")).click();
		tester.switchRightTab("汇总预估");
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		} else {
			// Thread.sleep(3000);
			driver.findElement(By.id("refDateStart1")).sendKeys("2016-09-13");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateEnd1")).sendKeys("2016-09-20");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateStart2")).sendKeys("2016-09-01");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateEnd2")).sendKeys("2016-09-30");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateStart3")).sendKeys("2016-09-01");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateEnd3")).sendKeys("2016-09-13");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='计算建议值']")).click();
		}

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		tester.switchRightTab("采购单生成");
		driver.findElement(By.xpath("//input[@value='确认生成采购单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

	}
	
	public void testSummaryPurchase(Tester tester,WebDriver driver,Logger logger) throws Exception {

		tester.clickLeftMenu("物流中心需求管理", "物流中心汇总生成采购单");
		tester.switchRightTab("物流中心汇总生成采购单");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys("2016-09-17");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='查   询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='汇总预估']")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("为空")!=-1)
				logger.debug("物流中心汇总生成采购单 无要货单无法汇总");
		}
		tester.switchRightTab("汇总预估");
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
			logger.debug("物流中心汇总生成采购单 汇总预估出错");
		} else {
			driver.findElement(By.id("refDateStart1")).sendKeys("2016-09-13");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateEnd1")).sendKeys("2016-09-20");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateStart2")).sendKeys("2016-09-01");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateEnd2")).sendKeys("2016-09-30");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateStart3")).sendKeys("2016-09-01");
			Thread.sleep(1000);
			driver.findElement(By.id("refDateEnd3")).sendKeys("2016-09-13");
			Thread.sleep(1000);
			try{
			driver.findElement(By.xpath("//input[@value='计算建议值']")).click();
			}catch (Exception E){
				logger.debug("物流中心汇总生成采购单 计算建议值出错");
				logger.error(E);
			}
			Thread.sleep(3000);
		}

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		tester.switchRightTab("采购单生成");
		driver.findElement(By.xpath("//input[@value='确认生成采购单']")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("物流中心汇总生成采购单确认生成失败");
		}
		logger.debug("物流中心汇总生成采购单完成");
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
