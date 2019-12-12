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
public class DistributionInput {
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
	
	public void doClear(WebElement ele){
		try{
			ele.clear();
		}catch (Exception e){
			return;
		}
	}
	
	public void doSendKey(WebElement ele){
		try{
			ele.sendKeys("12");
		}catch (Exception e){
			return;
		}
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "pw", "admin");
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
		tester.clickLeftMenu("餐厅入库管理", "餐厅配送入库管理");
		tester.switchRightTab("餐厅配送入库管理");
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("配送单入库-");
		driver.findElement(By.xpath("//input[@value='确认填充配送单']")).click();
		tester.switchRightTab("配送单填充确认");
		driver.findElement(By.xpath("//input[@value='确认填充配送单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(1000);
		
	}
	
	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		tester.clickLeftMenu("餐厅入库管理", "餐厅配送入库管理");
		tester.switchRightTab("餐厅配送入库管理");
		try{
			driver.findElement(By.id("operate1")).click();
		}catch (Exception e){
			logger.debug("餐厅配送入库管理 TP/YK未生成配送单");
			logger.error(e);
		}
		tester.switchRightTabLike("配送单入库-");
		driver.findElement(By.xpath("//input[@value='确认填充配送单']")).click();
		tester.switchRightTab("配送单填充确认");
		driver.findElement(By.xpath("//input[@value='确认填充配送单']")).click();
		if(tester.isAlertPresent()){
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("餐厅配送入库 配送单填充失败");
		}
		tester.closeRightTab("餐厅配送入库管理");
		

		tester.clickLeftMenu("餐厅入库管理", "餐厅配送入库管理");
		tester.switchRightTab("餐厅配送入库管理");
		try{
			driver.findElement(By.id("operate1")).click();
		}catch(Exception e){
			logger.debug("餐厅配送入库管理 TP/YK未生成配送单");
			logger.error(e);
		}
		tester.switchRightTabLike("配送单入库-");
		driver.findElement(By.xpath("//input[@value='确认填充配送单']")).click();
		tester.switchRightTab("配送单填充确认");
		driver.findElement(By.xpath("//input[@value='确认填充配送单']")).click();
		if(tester.isAlertPresent()){
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("餐厅配送入库 配送单填充失败");
		}
		Thread.sleep(1000);
		logger.debug("餐厅配送入库 配送单填充完毕");
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
