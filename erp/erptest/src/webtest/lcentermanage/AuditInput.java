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
public class AuditInput {
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

		// 审核采购单
		tester.clickLeftMenu("库存基础管理", "验收入库单审核");
		tester.switchRightTab("验收入库单审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys("2016-09-12");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTabLike("审核入库单-");
		driver.findElement(By.xpath("//input[@value='入库单审核确认']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

	}
	
	public void testAuditInput(Tester tester,WebDriver driver,Logger logger) throws Exception {
		tester.clickLeftMenu("库存基础管理", "验收入库单审核");
		tester.switchRightTab("验收入库单审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys("2016-09-12");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		try{
			driver.findElement(By.id("operate1")).click();
		}catch(Exception e){
			logger.debug("物流中心验收入库单审核 入库单生成错误");
			logger.error(e);
		}
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
			logger.debug("物流中心验收入库单审核 入库单状态错误");
		}
		tester.switchRightTabLike("审核入库单-");
		driver.findElement(By.xpath("//input[@value='入库单审核确认']")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("餐厅要货审核审核要货单失败");
		}

		tester.switchRightTab("验收入库单审核");
		try{
			driver.findElement(By.id("operate1")).click();
		}catch(Exception e){
			logger.debug("物流中心验收入库单审核 入库单生成错误");
			logger.error(e);
		}
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
			logger.debug("物流中心验收入库单审核 入库单状态错误");
		}
		tester.switchRightTabLike("审核入库单-");
		driver.findElement(By.xpath("//input[@value='入库单审核确认']")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("餐厅要货审核审核要货单失败");
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
