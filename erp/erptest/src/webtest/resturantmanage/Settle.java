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
public class Settle {
	private Tester tester;
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
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
		tester.clickLeftMenu("餐厅日结管理", "餐厅日结管理");
		tester.switchRightTab("餐厅日结管理");
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		if(tester.isAlertPresent()){
			System.out.println("存在未审核单据");
			tester.closeAlertAndGetItsText();
			return;
		}
		driver.findElement(By.xpath("//input[@value='进 行 日 结']")).click();
		
		tester.switchRightTab("餐厅日结");
		driver.findElement(By.xpath("//input[@value='确认进行日结']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(1000);
		
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
	}
	
//	public void testRequirement() throws Exception {
//		tester.login("", "pw", "admin");
//		Thread.sleep(3000);
//		tester.clickTopMenu("餐厅管理");
//		Thread.sleep(1000);
//		/*
//		 * 生成
//		 */
//		tester.clickLeftMenu("餐厅日结管理", "餐厅日结管理");
//		tester.switchRightTab("餐厅日结管理");
//		driver.findElement(By.xpath("//input[@value='查    询']")).click();
//		if(tester.isAlertPresent()){
//			System.out.println("存在未审核单据");
//			tester.closeAlertAndGetItsText();
//			return;
//		}
//		driver.findElement(By.xpath("//input[@value='进 行 日 结']")).click();
//		
//		tester.switchRightTab("餐厅日结");
//		driver.findElement(By.xpath("//input[@value='确认进行日结']")).click();
//		if(tester.isAlertPresent()){
//			tester.closeAlertAndGetItsText();
//		}
//		Thread.sleep(1000);
//		
//		if(tester.isAlertPresent()){
//			tester.closeAlertAndGetItsText();
//		}
//	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String result =	tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}

}
