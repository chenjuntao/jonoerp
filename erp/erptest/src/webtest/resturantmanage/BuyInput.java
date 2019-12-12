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
package webtest.resturantmanage;

import static org.junit.Assert.fail;






import java.util.List;
import java.util.NoSuchElementException;

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
public class BuyInput {
	private Tester tester;
	private WebDriver driver;
	private Logger logger;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		logger = tester.getLogger();
	}
	
	public boolean doesWebElementExist(WebDriver driver, By selector)
	{ 
	        try 
	        { 
	               driver.findElement(selector); 
	               return true; 
	        } 
	        catch (Exception e) 
	        {
	                return false; 
	        } 
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "pw", "admin");
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
		/*
		 * 生成
		 */
		tester.clickLeftMenu("餐厅入库管理", "采购单入库");
		tester.switchRightTab("采购单入库");
		if (doesWebElementExist(driver, By.id("lodopWarning"))){
			driver.findElement(By.id("lodopWarning"));
			driver.findElement(By.className("dijitDialogTitleBar"));
			driver.findElement(By.className("dijitDialogCloseIcon")).click();
			tester.switchRightTab("采购单入库");
		}
		driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
		WebElement ele;
		int i = 0;
		while (doesWebElementExist(driver,By.xpath("//div[@id='dijit__TreeNode_"+i+"']/div"))){
			ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_"+i+++"']/div"));
			if (ele.getText().startsWith("ZP")){
				ele.click();
				break;
			}
		}
//		driver.findElement(By.xpath("//div[@class='dijitTreeNodeContainer']/div")).click();
//		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='根据采购单入库']")).click();
		tester.switchRightTabLike("采购单入库-");
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTab("入库单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅入库管理", "餐厅入库单审核");
		tester.switchRightTab("餐厅入库单审核");
		driver.findElement(By.id("operate1")).click();
		Thread.sleep(1000);
		tester.switchRightTabLike("审核入库单-");
		driver.findElement(By.xpath("//input[@value='入库单审核确认']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
	}
	
	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		/*
		 * 生成
		 */
		tester.clickLeftMenu("餐厅入库管理", "采购单入库");
		tester.switchRightTab("采购单入库");
		if (doesWebElementExist(driver, By.id("lodopWarning"))){
			driver.findElement(By.id("lodopWarning"));
			driver.findElement(By.className("dijitDialogTitleBar"));
			driver.findElement(By.className("dijitDialogCloseIcon")).click();
			tester.switchRightTab("采购单入库");
		}
		try{
			driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
			WebElement ele;
			int i = 0;
			while (doesWebElementExist(driver,By.xpath("//div[@id='dijit__TreeNode_"+i+"']/div"))){
				ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_"+i+++"']/div"));
				if (ele.getText().startsWith("ZP")){
					ele.click();
					break;
				}
			}
			driver.findElement(By.xpath("//input[@value='根据采购单入库']")).click();
		}catch (Exception e){
			logger.debug("餐厅采购入库 ZP单未生成");
			logger.error(e);
		}
		tester.switchRightTabLike("采购单入库-");
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		tester.switchRightTab("入库单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		if(tester.isAlertPresent()){
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("餐厅采购入库入库单生成失败");
		}
		logger.debug("餐厅采购入库完成");
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅入库管理", "餐厅入库单审核");
		tester.switchRightTab("餐厅入库单审核");
		try{
			driver.findElement(By.id("operate1")).click();
		}catch (Exception e){
			logger.debug("餐厅采购单入库审核 未生成入库单");
			logger.error(e);
		}
		Thread.sleep(1000);
		tester.switchRightTabLike("审核入库单-");
		driver.findElement(By.xpath("//input[@value='入库单审核确认']")).click();
		if(tester.isAlertPresent()){
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("餐厅采购入库审核失败");
		}
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		logger.debug("餐厅采购入库审核完成");
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String result =	tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}

}
