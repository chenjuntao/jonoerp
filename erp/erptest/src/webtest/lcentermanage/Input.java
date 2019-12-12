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
public class Input {
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

		// 央厂半成品入库
		tester.clickLeftMenu("库存基础管理", "验收入库单生成");
		tester.switchRightTab("验收入库单生成");
		if (doesWebElementExist(driver, By.id("lodopWarning"))) {
			driver.findElement(By.id("lodopWarning"));
			driver.findElement(By.className("dijitDialogTitleBar"));
			driver.findElement(By.className("dijitDialogCloseIcon")).click();
			tester.switchRightTab("验收入库单生成");
		}
		driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
		WebElement ele;
		int i = 0;
		while (doesWebElementExist(driver, By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
			ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i++ + "']/div"));
			if (ele.getText().startsWith("TP")) {
				ele.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@class='dijitTreeNodeContainer']/div")).click();
		// Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='根据采购单入库']")).click();
		tester.switchRightTabLike("采购单入库-");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		tester.switchRightTabLike("入库单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

	}

	public void testInput(Tester tester,WebDriver driver,Logger logger) throws Exception {
		logger.debug("验收入库单生成");
		tester.clickLeftMenu("库存基础管理", "验收入库单生成");
		tester.switchRightTab("验收入库单生成");
		int i = 0;
		WebElement ele;
		try{
			driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
			i = 0;
			while (doesWebElementExist(driver, By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
				ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i++ + "']/div"));
				if (ele.getText().startsWith("TP")) {
					ele.click();
					break;
				}
			}
			// Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='根据采购单入库']")).click();
		}catch (Exception e){
			logger.debug("物流中心验收入库单生成 tp采购单异常");
			logger.error(e);
		}
		tester.switchRightTabLike("采购单入库-");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		tester.switchRightTabLike("入库单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("物流中心验收入库单tp生成失败");
		}
		
		tester.logout();
		tester.login("", "lc", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("物流中心");
		Thread.sleep(1000);
		tester.clickLeftMenu("库存基础管理", "验收入库单生成");
		tester.switchRightTab("验收入库单生成");
		try{
			driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
			i = 0;
			while (doesWebElementExist(driver, By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
				ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i++ + "']/div"));
				if (ele.getText().startsWith("YK")) {
					ele.click();
					break;
				}
			}
			// Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='根据采购单入库']")).click();
		}catch (Exception e){
			logger.debug("物流中心验收入库单生成 yk采购单异常");
			logger.error(e);
		}
		tester.switchRightTabLike("采购单入库-");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		tester.switchRightTabLike("入库单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("物流中心验收入库单yk生成失败");
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
