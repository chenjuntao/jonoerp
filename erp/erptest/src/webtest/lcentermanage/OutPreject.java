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
public class OutPreject {
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

		// 生成采购单
		tester.clickLeftMenu("物流中心退货管理", "外部订货方退货生成");
		tester.switchRightTab("外部订货方退货生成");
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("入库单退货-");
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		driver.findElement(By.xpath("//input[@value='生成采购退货单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

		// 采购退货审核
		tester.clickLeftMenu("物流中心退货管理", "外部订货方退货");
		tester.switchRightTab("外部订货方退货");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("审核采购退货单-");
		driver.findElement(By.xpath("//input[@value='采购退货单实施']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
	}
	public void testTt(Tester tester,WebDriver driver,Logger logger) throws Exception {
		logger.debug("外部订货方退货生成");
		tester.clickLeftMenu("物流中心退货管理", "外部订货方退货生成");
		tester.switchRightTab("外部订货方退货生成");
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("入库单退货-");
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		driver.findElement(By.xpath("//input[@value='生成采购退货单']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

		// 采购退货审核
		tester.clickLeftMenu("物流中心退货管理", "外部订货方退货");
		tester.switchRightTab("外部订货方退货");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("审核采购退货单-");
		driver.findElement(By.xpath("//input[@value='采购退货单实施']")).click();
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
