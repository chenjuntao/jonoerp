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
public class HalfFoodPandI {
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
		/*
		 * 生成
		 */
		tester.clickLeftMenu("半成品自产管理", "半成品自产入库生成");
		tester.switchRightTab("半成品自产入库生成");
		driver.findElement(By.xpath("//input[@value='选择半成品']")).click();
		WebElement selProduct = driver.findElement(By.id("ifr_selProduct"));
		driver.switchTo().frame(selProduct);
		driver.findElement(By.id("itemName")).sendKeys("123");
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		driver.switchTo().parentFrame();
		
		WebElement ele;
		int i = 0;
		while (doesWebElementExist(driver,By.id("dijit_form_NumberTextBox_"+i))){
			ele = driver.findElement(By.id("dijit_form_NumberTextBox_"+ i++));
			doClear(ele);
			doSendKey(ele);
		}
		tester.switchRightTab("半成品自产入库生成");
		driver.findElement(By.xpath("//input[@value='生成半成品生产单']")).click();

		tester.switchRightTab("半成品入库单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成半成品入库单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		
		
		/*
		 * 审核
		 */
		tester.clickLeftMenu("半成品自产管理", "半成品自产入库审核");
		tester.switchRightTab("半成品自产入库审核");

		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("审核半成品入库单-");
		driver.findElement(By.xpath("//input[@value='审核半成品入库单']")).click();
		tester.switchRightTab("半成品入库单审核确认");
		driver.findElement(By.xpath("//input[@value='审核半成品入库单']")).click();
		
		
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
	}
	
	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		/*
		 * 生成
		 */
		tester.clickLeftMenu("半成品自产管理", "半成品自产入库生成");
		tester.switchRightTab("半成品自产入库生成");
		driver.findElement(By.xpath("//input[@value='选择半成品']")).click();
		WebElement selProduct = driver.findElement(By.id("ifr_selProduct"));
		driver.switchTo().frame(selProduct);
		driver.findElement(By.id("itemName")).sendKeys("123");
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		driver.switchTo().parentFrame();
		
		WebElement ele;
		int i = 0;
		while (doesWebElementExist(driver,By.id("dijit_form_NumberTextBox_"+i))){
			ele = driver.findElement(By.id("dijit_form_NumberTextBox_"+ i++));
			doClear(ele);
			doSendKey(ele);
		}
		tester.switchRightTab("半成品自产入库生成");
		driver.findElement(By.xpath("//input[@value='生成半成品生产单']")).click();

		tester.switchRightTab("半成品入库单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成半成品入库单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		
		
		/*
		 * 审核
		 */
		tester.clickLeftMenu("半成品自产管理", "半成品自产入库审核");
		tester.switchRightTab("半成品自产入库审核");

		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("审核半成品入库单-");
		driver.findElement(By.xpath("//input[@value='审核半成品入库单']")).click();
		tester.switchRightTab("半成品入库单审核确认");
		driver.findElement(By.xpath("//input[@value='审核半成品入库单']")).click();
		
		
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
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
