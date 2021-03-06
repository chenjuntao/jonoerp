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
public class AuditPicking {
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

		// 审核捡货单
		tester.clickLeftMenu("库存基础管理", "物流中心捡货单审核");
		tester.switchRightTab("物流中心捡货单审核");
		if (doesWebElementExist(driver, By.id("lodopWarning"))) {
			driver.findElement(By.id("lodopWarning"));
			driver.findElement(By.className("dijitDialogTitleBar"));
			driver.findElement(By.className("dijitDialogCloseIcon")).click();
			tester.switchRightTab("物流中心捡货单审核");
		}
		driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
		WebElement ele;
		int i = 0;
		while (doesWebElementExist(driver, By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
			ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i++ + "']/div"));
			if (ele.getText().startsWith("JH")) {
				ele.click();
				break;
			}
		}
		//
		driver.findElement(By.xpath("//div[@class='dijitTreeNodeContainer']/div")).click();
		// Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='审    核']")).click();
		tester.switchRightTabLike("审核捡货单-");
		Thread.sleep(2000);
		driver.findElement(By.id("submit_label")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}

	}
	
	public void testAuditPicking(Tester tester,WebDriver driver,Logger logger) throws Exception {
		// 审核捡货单
		tester.clickLeftMenu("库存基础管理", "物流中心捡货单审核");
		tester.switchRightTab("物流中心捡货单审核");
//		if (doesWebElementExist(driver, By.id("lodopWarning"))) {
//			driver.findElement(By.id("lodopWarning"));
//			driver.findElement(By.className("dijitDialogTitleBar"));
//			driver.findElement(By.className("dijitDialogCloseIcon")).click();
//			tester.switchRightTab("物流中心捡货单审核");
//		}
		try{
			driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
			WebElement ele;
			int i = 0;
			while (doesWebElementExist(driver, By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
				ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i++ + "']/div"));
				if (ele.getText().startsWith("JH")) {
					ele.click();
					break;
				}
			}
			//
			// Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='审    核']")).click();
		}catch(Exception e){
			logger.debug("物流中心捡货单审核 捡货单未生成");
			logger.error(e);
		}
		tester.switchRightTabLike("审核捡货单-");
		Thread.sleep(2000);
		driver.findElement(By.id("submit_label")).click();
		if (tester.isAlertPresent()) {
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("物流中心捡货单审核失败");		
		}
		logger.debug("物流中心捡货单审核成功");	
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
